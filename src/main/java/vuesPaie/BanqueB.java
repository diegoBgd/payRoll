package vuesPaie;

import classesPaie.BanqueC;
import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.Tables;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class BanqueB extends BanqueC implements Serializable {
	private static final long serialVersionUID = 7945184805910695243L;
	private BanqueC banque;
	private BanqueC banqueVirment;
	private List<BanqueC> banques;
	private List<BanqueC> banqueVirementS;
	private String codeBanqueVirement;
	private String designationBanqueVirement;
	private String compteBanqueVirement;
	private String codeBanqueRecherche;
	private String designationBanqueRecherche;
	private OperateurC operateur;
	private ExerciceC exercice;
	private DroitC droit;
	private boolean disableMsg;
	private HttpSession session = HelperC.getSession();

	Base userFonction;

	public BanqueC getBanque() {
		return this.banque;
	}

	public void setBanque(BanqueC banque) {
		this.banque = banque;
	}

	public String getCodeBanqueVirement() {
		return this.codeBanqueVirement;
	}

	public void setCodeBanqueVirement(String codeBanqueVirement) {
		this.codeBanqueVirement = codeBanqueVirement;
	}

	public String getDesignationBanqueVirement() {
		return this.designationBanqueVirement;
	}

	public void setDesignationBanqueVirement(String designationBanqueVirement) {
		this.designationBanqueVirement = designationBanqueVirement;
	}

	public String getCompteBanqueVirement() {
		return this.compteBanqueVirement;
	}

	public void setCompteBanqueVirement(String compteBanqueVirement) {
		this.compteBanqueVirement = compteBanqueVirement;
	}

	public List<BanqueC> getBanques() {
		return this.banques;
	}

	public void setBanques(List<BanqueC> banques) {
		this.banques = banques;
	}

	public List<BanqueC> getBanqueVirementS() {
		return this.banqueVirementS;
	}

	public void setBanqueVirementS(List<BanqueC> banqueVirementS) {
		this.banqueVirementS = banqueVirementS;
	}

	public String getCodeBanqueRecherche() {
		return this.codeBanqueRecherche;
	}

	public void setCodeBanqueRecherche(String codeBanqueRecherche) {
		this.codeBanqueRecherche = codeBanqueRecherche;
	}

	public String getDesignationBanqueRecherche() {
		return this.designationBanqueRecherche;
	}

	public void setDesignationBanqueRecherche(String designationBanqueRecherche) {
		this.designationBanqueRecherche = designationBanqueRecherche;
	}

	public OperateurC getOperateur() {
		return this.operateur;
	}

	public void setOperateur(OperateurC operateur) {
		this.operateur = operateur;
	}

	public ExerciceC getExercice() {
		return this.exercice;
	}

	public void setExercice(ExerciceC exercice) {
		this.exercice = exercice;
	}

	public HttpSession getSession() {
		return this.session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public BanqueC getBanqueVirment() {
		return this.banqueVirment;
	}

	public void setBanqueVirment(BanqueC banqueVirment) {
		this.banqueVirment = banqueVirment;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	@PostConstruct
	private void init() {
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
		if (this.operateur == null || this.exercice == null) {

			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			disableMsg = true;
			this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
			this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
			this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (this.userFonction != null) {
				this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
						Constante.Role.fichierBase);
			}
			chargerBanque();
		}
	}

	public void chargerBanque() {
		this.banques = FichierBaseDAO.getInstance().getBanques();
	}

	private void clear(boolean b) {
		if (b) {
			setCode("");
		}
		disableMsg = true;
		setId(0);
		setDesignation("");
		setTelephone1("");
		setTelephone2("");
		setAdresse("");
		setCompte("");
		setCodeBanqueVirement("");
		setDesignationBanqueVirement("");
		setCompteBanqueVirement("");
		setBanqueVirement("");

		if (this.banqueVirementS != null) {
			this.banqueVirementS.clear();
		}
	}

	private void setObject() {
		disableMsg = true;
		if (this.banque != null) {

			setId(this.banque.getId());
			setCode(this.banque.getCode());
			setDesignation(this.banque.getDesignation());
			setTelephone1(this.banque.getTelephone1());
			setTelephone2(this.banque.getTelephone2());
			setAdresse(this.banque.getAdresse());
			setCompte(this.banque.getCompte());
			disableMsg = false;
			if (this.banque.getBanqueVirement() != null && !this.banque.getBanqueVirement().equals("")) {

				this.banqueVirment = FichierBaseDAO.getInstance().getBanque(this.banque.getBanqueVirement());
				this.codeBanqueVirement = this.banqueVirment.getCode();
				this.designationBanqueVirement = this.banqueVirment.getDesignation();
				this.compteBanqueVirement = this.banqueVirment.getCompte();
				setBanqueVirement(this.codeBanqueVirement);
			}
		}
	}

	public void changeCode() {
		if (getCode().trim().equals("")) {

			clear(true);
		} else {

			this.banque = FichierBaseDAO.getInstance().getBanque(getCode());
			if (this.banque == null) {

				clear(false);
			} else {

				setObject();
			}
		}
	}

	public void onRowSelected() {
		if (this.banque != null) {

			setObject();
			PrimeFaces.current().executeScript("PF('dlgBanque').hide();");
		}
	}

	public void focusBanque() {
		this.codeBanqueVirement = "";
		this.designationBanqueVirement = "";
		if (this.banqueVirementS != null) {
			this.banqueVirementS.clear();
		}
		HelperC.afficherMessage("Information", "Double-cliquer pour lance une recherche");
	}

	private void setObjectBanque() {
		setObject();
	}

	public void changeBanque() {
		if (getBanqueVirement() != null) {
			setObjectBanque();
		}
	}

	public void enregistrer() {
		if (getId() == 0 && !this.droit.isCreer()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
		} else if (getId() > 0 && !this.droit.isModifier()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
		} else if (getCode().trim().equals("")) {

			HelperC.afficherMessage("Information", "Completez le code");
		} else if (getDesignation().trim().equals("")) {

			HelperC.afficherMessage("Information", "Completez la désignation");
		} else {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {

				hist.setOperation("Création de la banque " + getCode());
			} else {

				hist.setOperation("Modification de la banque " + getCode());
			}
			hist.setTable(Tables.getTableName(Tables.TableName.banque));
			setHistorique(hist);
			if (FichierBaseDAO.getInstance().insertUpdateBanque(this)) {

				chargerBanque();
				HelperC.afficherMessage("Information", "succès de l'opération ");
				clear(true);
			} else {

				HelperC.afficherMessage("Information", "Echec de l'opération ");
			}
		}
	}

	public void supprimer() {
		if (getId() != 0) {

			if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.banque), getId())) {

				HelperC.afficherMessage("Information", "succès de l'opération ");
				clear(true);
				chargerBanque();
			} else {

				HelperC.afficherMessage("Désole", "Echec de suppression");
			}
		} else {

			HelperC.afficherDeleteMessage();
		}
	}

	public void initialiser() {
		clear(true);
	}

}
