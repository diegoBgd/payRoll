package vuesPaie;

import classesPaie.AffectationC;
import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.Tables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import persistencePaie.Connexion;
import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class OperateurB extends OperateurC {
	private static final long serialVersionUID = -6229597997939330894L;
	private OperateurC operateur;
	private OperateurC operateurSelected;
	private DroitC droit;
	Base userFonction;
	private String confirmationPwd;
	private String matricule, codeLine;
	private String nom, infoMsg, mail;
	private HttpSession session;

	private EmployeC employe;
	AffectationC affect = null;
	private Base fonction;
	private List<SelectItem> listeFonction;
	private boolean userExist, disableMsg;
	private List<OperateurC>listUser;
	private boolean enableFonction;
	private boolean desableSave;
	ExerciceC exercice;

	public OperateurB() {
		session = HelperC.getSession();
	}

	public boolean isEnableFonction() {
		return this.enableFonction;
	}

	public void setEnableFonction(boolean enableFonction) {
		this.enableFonction = enableFonction;
	}

	public Base getFonction() {
		return this.fonction;
	}

	public void setFonction(Base fonction) {
		this.fonction = fonction;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public String getConfirmationPwd() {
		return this.confirmationPwd;
	}

	public void setConfirmationPwd(String confirmationPwd) {
		this.confirmationPwd = confirmationPwd;
	}

	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public OperateurC getOperateurSelected() {
		return this.operateurSelected;
	}

	public void setOperateurSelected(OperateurC operateurSelected) {
		this.operateurSelected = operateurSelected;
	}

	public boolean isUserExist() {
		return this.userExist;
	}

	public void setUserExist(boolean userExist) {
		this.userExist = userExist;
	}

	public boolean isDesableSave() {
		return this.desableSave;
	}

	public void setDesableSave(boolean desableSave) {
		this.desableSave = desableSave;
	}

	public List<SelectItem> getListeFonction() {
		return this.listeFonction;
	}

	public void setListeFonction(List<SelectItem> listeFonction) {
		this.listeFonction = listeFonction;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCodeLine() {
		return codeLine;
	}

	public void setCodeLine(String codeLine) {
		this.codeLine = codeLine;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	public List<OperateurC> getListUser() {
		return listUser;
	}

	public void setListUser(List<OperateurC> listUser) {
		this.listUser = listUser;
	}

	@PostConstruct
	public void init() {
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		String exist = "false";
		disableMsg = true;
		if (this.session.getAttribute("existUser") != null) {
			exist = this.session.getAttribute("existUser").toString();
		}

		if (exist != null) {
			if (exist.equals("true")) {

				this.userExist = true;
			} else {

				this.userExist = false;
			}
		}
		if (this.userExist) {
			if (codeOperateur == null || codeExercice == null) {

				try {
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("/payRoll/login.jsf");
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {

				operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
				exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);

				this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
				if (this.userFonction != null) {
					this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
							Constante.Role.superUtilisateur);
				}

				UIComponent frm = null;
				FacesContext context = FacesContext.getCurrentInstance();
				frm = context.getViewRoot().findComponent("frmIns");
				if (frm != null) {
					setLineUser(true);
					userExist = true;

				}

			}

		}
		if (Connexion.getConnection() != null)//
			chargementFonction();
	}

	public void searchEmploye() {
		this.desableSave = false;
		this.enableFonction = false;

		if (this.matricule != null && !this.matricule.equals("")) {

			this.employe = FactoryDAO.getInstance().getEmployeSimple(this.matricule);

			if (this.employe != null) {

				employeValue();
				operateurSelected = FichierBaseDAO.getInstance().getOperateur(employe);
				if (operateurSelected != null) {
					affecter();
				}

			} else if (userExist) { // S'IL Y A AU MOINS UN EMPLOYE

				this.nom = "";
				this.fonction = null;
				this.setIdFonction(0);
				this.desableSave = true;
				HelperC.afficherErreur("ERREUR", "L'employe de matricule " + this.matricule + " n'existe pas !");
			}
		}
	}

	private void employeValue() {
		if (employe != null) {
			nom = this.employe.getNomPrenom();
			matricule=this.employe.getCode();
			setIdEmploye(this.employe.getId());
			this.setIdFonction(employe.getIdFnctn());
			fonction = FichierBaseDAO.getInstance().getBaseById(getIdFonction(),
					Tables.getTableName(Tables.TableName.fonction));

		}
	}

	public void searchOperator() {
		operateurSelected = FichierBaseDAO.getInstance().getOperateur(getLogin());
		if (operateurSelected != null) {
			affecter();
			if (getIdFonction() > 0)
				this.enableFonction = true;
			else
				this.enableFonction = false;
		} else {
			this.setId(0);
			this.enableFonction = false;
			setCodeSecret("");
			this.confirmationPwd = "";
			setLogin("");
		}
	}

	
	private void chargementFonction() {
		this.listeFonction = new ArrayList<SelectItem>();
		this.listeFonction.add(new SelectItem(Integer.valueOf(0), ""));

		for (Base f : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction))) {
			this.listeFonction.add(new SelectItem(Integer.valueOf(f.getId()),
					String.valueOf(f.getCode()) + " || " + f.getDesignation()));
		}
	}

	public void changeFonction(ValueChangeEvent event) {
		setIdFonction(((Integer) event.getNewValue()).intValue());
		this.fonction = FichierBaseDAO.getInstance().getBaseById(getIdFonction(),
				Tables.getTableName(Tables.TableName.fonction));
	}

	private void affecter() {
		disableMsg = true;
		if (this.operateurSelected != null) {

			setId(this.operateurSelected.getId());
			setCodeSecret(this.operateurSelected.getCodeSecret());
			setLogin(this.operateurSelected.getLogin());
			setActif(this.operateurSelected.isActif());
			setLineUser(operateurSelected.isLineUser());
			confirmationPwd = this.operateurSelected.getCodeSecret();
			employe=operateurSelected.getEmploye();
			employeValue();
			disableMsg = false;
		}
	}

	private void clear(boolean b) {
		if (b) {

			this.matricule = "";
			this.nom = "";
		}
		setId(0);
		setActif(true);
		setCodeSecret("");
		this.confirmationPwd = "";
		setLogin("");
		this.fonction = null;
		setIdFonction(0);
		setLineUser(false);
		disableMsg = false;
	}

	public void chargerOperateur() {
		listUser=FichierBaseDAO.getInstance().getListOperateur();
	}
	public void saveOperator() {
		if (this.userExist) {

			if (getId() > 0 && !this.droit.isModifier()) {

				HelperC.afficherInformation("Information", "Vous n'avez pas le droit de Modifier");
				return;
			}
			if (getId() == 0 && !this.droit.isCreer()) {

				HelperC.afficherInformation("Information", "Vous n'avez pas le droit de Créer un nouveau Opérateur");
				return;
			}
			if (getCodeSecret().trim().equals("")) {

				HelperC.afficherInformation("Information", "Précisez le code Secret");
				return;
			}
			if (fonction == null) {
				HelperC.afficherInformation("Information", "Précisez la fonction de l'opérateur");
				return;
			}
			setDateModif(Calendar.getInstance().getTime());
			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			hist.setTable(Tables.getTableName(Tables.TableName.operateur));
			setHistorique(hist);

			if (FichierBaseDAO.getInstance().insertUpdateOperateur(this)) {

				clear(true);
				HelperC.afficherInformation("FELICITATION", "Succès de l'opération");
			} else {

				HelperC.afficherAttention("DESOLE!", "Echec de l'opération");
			}
		} else {

			if (employe == null) {
				this.employe = new EmployeC();
				this.employe.setNom(this.nom);
				this.employe.setMatricule(this.matricule);
				this.employe.setPartenaire(true);
				this.employe.setCode(this.matricule);
				this.employe.setIdFnctn(getIdFonction());
			}

			setEmploye(this.employe);
			setDateModif(Calendar.getInstance().getTime());
			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			hist.setTable(Tables.getTableName(Tables.TableName.operateur));
			setHistorique(hist);
			if (FichierBaseDAO.getInstance().insertPremierOperateur(this)) {

				clear(true);
				HelperC.afficherInformation("INFO", "Succès de l'opération");
			} else {

				HelperC.afficherAttention("DESOLE!", "Echec de l'opération");
			}

		}
	}

	public void controlePassword() {
		this.desableSave = false;

		if (getCodeSecret() == null || getCodeSecret().equals("")) {

			HelperC.afficherErreur("ERREUR", "Vous devez saisir le mot de passe");
			this.desableSave = true;
			return;
		}
		if (this.confirmationPwd == null || this.confirmationPwd.equals("")) {

			HelperC.afficherErreur("ERREUR", "Vous devez confirmer le mot de passe");
			this.desableSave = true;
			return;
		}
		if (!getCodeSecret().equals(this.confirmationPwd)) {

			HelperC.afficherErreur("ERREUR", "Mot de passe invalide !");
			this.desableSave = true;
			return;
		}
	}

	public void deleteOperateur() {
		if (this.droit != null && getId() > 0 && !this.droit.isSupprimer()) {

			HelperC.afficherInformation("Information", "Vous n'avez pas le droit de Supprimer");
		} else if (getId() == 0) {

			HelperC.afficherDeleteMessage();
		} else if (this.operateur.getId() == getId()) {

			HelperC.afficherAttention("ATTENTION", "Un opérateur ne peut pas se supprimer !");
		} else {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			hist.setTable(Tables.getTableName(Tables.TableName.operateur));
			setHistorique(hist);
			if (FichierBaseDAO.getInstance().deleteOperateur(this)) {

				clear(true);
				HelperC.afficherInformation("FELICITATION", "Succès de l'opération");
			} else {

				HelperC.afficherAttention("DESOLE!", "Echec de l'opération");
			}
		}
	}

	public void initialiser() {
		clear(true);
	}

	public void takeOperateur() {
		if (this.operateurSelected != null) {

			affecter();
			PrimeFaces.current().executeScript("PF('dlgResearch').hide();");
		}
	}
}
