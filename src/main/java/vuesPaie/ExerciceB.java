package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.Tables;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class ExerciceB extends ExerciceC {
	private static final long serialVersionUID = -3839790073144427524L;
	private String dateDebutS;
	private String dateFinS;
	private String codeExercicePrecedent;
	private String libelleExercicePrecedent;
	private String dateDebutExercicePrecedent;
	private String dateFinExercicePrecedent;
	private HttpSession session = HelperC.getSession();
	private OperateurC operateur;
	private ExerciceC selectedExercice;
	private List<ExerciceC> listExercice;
	private boolean disableMsg, userExist;

	public ExerciceC getExercisePrcd() {
		return this.exercisePrcd;
	}

	private ExerciceC exercisePrcd;
	private DroitC droit;
	Base userFonction;

	public void setExercisePrcd(ExerciceC exercisePrcd) {
		this.exercisePrcd = exercisePrcd;
	}

	public String getDateDebutS() {
		return this.dateDebutS;
	}

	public void setDateDebutS(String dateDebutS) {
		this.dateDebutS = dateDebutS;
	}

	public String getDateFinS() {
		return this.dateFinS;
	}

	public void setDateFinS(String dateFinS) {
		this.dateFinS = dateFinS;
	}

	public OperateurC getOperateur() {
		return this.operateur;
	}

	public void setOperateur(OperateurC operateur) {
		this.operateur = operateur;
	}

	public String getCodeExercicePrecedent() {
		return this.codeExercicePrecedent;
	}

	public ExerciceC getSelectedExercice() {
		return selectedExercice;
	}

	public void setSelectedExercice(ExerciceC selectedExercice) {
		this.selectedExercice = selectedExercice;
	}

	public void setCodeExercicePrecedent(String codeExercicePrecedent) {
		this.codeExercicePrecedent = codeExercicePrecedent;
	}

	public String getLibelleExercicePrecedent() {
		return this.libelleExercicePrecedent;
	}

	public void setLibelleExercicePrecedent(String libelleExercicePrecedent) {
		this.libelleExercicePrecedent = libelleExercicePrecedent;
	}

	public String getDateDebutExercicePrecedent() {
		return this.dateDebutExercicePrecedent;
	}

	public void setDateDebutExercicePrecedent(String dateDebutExercicePrecedent) {
		this.dateDebutExercicePrecedent = dateDebutExercicePrecedent;
	}

	public String getDateFinExercicePrecedent() {
		return this.dateFinExercicePrecedent;
	}

	public void setDateFinExercicePrecedent(String dateFinExercicePrecedent) {
		this.dateFinExercicePrecedent = dateFinExercicePrecedent;
	}

	public boolean isUserExist() {
		return userExist;
	}

	public void setUserExist(boolean userExist) {
		this.userExist = userExist;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	public List<ExerciceC> getListExercice() {
		return listExercice;
	}

	public void setListExercice(List<ExerciceC> listExercice) {
		this.listExercice = listExercice;
	}

	@PostConstruct
	private void init() {
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		selectedExercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
		this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		disableMsg = true;

		if (this.operateur == null) {

			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {

			this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (this.userFonction != null) {
				this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
						Constante.Role.parametrage);
			}
			affecterExercice();
		}
	}

	public void changeDateDebut() {
		if (this.dateDebutS.replace("/", "").replace("_", "").trim().equals("")) {

			setDateDebut(null);
		} else {

			setDateDebut(HelperC.validerDate(this.dateDebutS));
			if (getDateDebut() == null) {

				this.dateDebutS = "";
				HelperC.afficherMessage("Information", "Date invalide!");
			} else {

				this.dateDebutS = HelperC.convertDate(getDateDebut());
			}
		}
	}

	public void changeDateFin() {
		if (this.dateFinS.replace("/", "").replace("_", "").trim().equals("")) {

			setDateFin(null);
		} else {

			setDateFin(HelperC.validerDate(this.dateFinS));
			if (getDateFin() == null) {

				this.dateFinS = "";
				HelperC.afficherMessage("Information", "Date invalide!");
			} else {

				this.dateFinS = HelperC.convertDate(getDateFin());
			}
		}
	}

	private void clear(boolean b) {
		if (b) {
			setCode("");
		}
		disableMsg = true;
		setId(0);
		setDesignation("");
		setDateDebut(null);
		setDateFin(null);
		this.dateDebutS = "";
		this.dateFinS = "";
		clearPrecedent(true);
	}

	private void clearPrecedent(boolean b) {
		if (b) {
			this.codeExercicePrecedent = "";
		}
		setExercicePrecedent(null);
		this.libelleExercicePrecedent = "";
		this.dateDebutExercicePrecedent = "";
		this.dateFinExercicePrecedent = "";
	}

	public void getSelectedExercise() {
		affecterExercice();
	}

	private void affecterExercice() {
		if (selectedExercice != null) {

			setId(selectedExercice.getId());
			setCode(selectedExercice.getCode());
			setDesignation(selectedExercice.getDesignation());
			setDateDebut(selectedExercice.getDateDebut());
			this.dateDebutS = HelperC.convertDate(getDateDebut());
			setDateFin(selectedExercice.getDateFin());
			this.dateFinS = HelperC.convertDate(getDateFin());
			this.codeExercicePrecedent = selectedExercice.getExercicePrecedent();
			disableMsg = false;
			if (this.codeExercicePrecedent != null && !this.codeExercicePrecedent.equals("")) {

				changeExercicePrecedente();
			} else {

				clearPrecedent(true);
			}
		} else {

			clear(false);
		}
	}

	private void affecterExercicePrecedent() {
		if (this.exercisePrcd != null) {
			setExercicePrecedent(this.codeExercicePrecedent);
			this.libelleExercicePrecedent = this.exercisePrcd.getDesignation();
			this.dateDebutExercicePrecedent = HelperC.convertDate(this.exercisePrcd.getDateDebut());
			this.dateFinExercicePrecedent = HelperC.convertDate(this.exercisePrcd.getDateFin());
		}
	}

	public void changeExercice() {
		if (getCode().trim().equals("")) {

			clear(true);
		} else {

			selectedExercice = FichierBaseDAO.getInstance().getExercice(getCode());
			if (selectedExercice != null) {

				affecterExercice();
			} else {

				clear(false);
			}
		}
	}

	public void chargerListe() {
		listExercice = FichierBaseDAO.getInstance().getListExercice();
	}

	public void changeExercicePrecedente() {
		if (!this.codeExercicePrecedent.trim().equals("")) {

			this.exercisePrcd = FichierBaseDAO.getInstance().getExercice(this.codeExercicePrecedent);
			if (this.exercisePrcd != null) {
				affecterExercicePrecedent();
			} else {
				clearPrecedent(false);
				HelperC.afficherMessage("Information", "Cet exercice n'existe pas!");
			}
		} else {
			clearPrecedent(true);
		}
	}

	public void enregistrer() {
		if (getId() == 0 && !this.droit.isCreer()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
		} else if (getId() > 0 && !this.droit.isModifier()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
		} else if (getCode().trim().equals("")) {

			HelperC.afficherMessage("Information", "Précisez le code");
		} else if (getDesignation().trim().equals("")) {

			HelperC.afficherMessage("Information", "Précisez le libellé");
		} else if (getDateDebut() == null || getDateFin() == null) {

			HelperC.afficherMessage("Information", "Précisez le début et la fin de l'exercice");
		} else if (getDateDebut().after(getDateFin())) {

			HelperC.afficherMessage("Information", "La date de Début doit précéder celle de la fin de l'Exercice");
		} else if (FichierBaseDAO.getInstance().getExerciceNotCurrent(getDateDebut(), getId()) != null) {

			HelperC.afficherMessage("Information", "La date Début se trouve dans un autre Exercice");
		} else if (FichierBaseDAO.getInstance().getExerciceNotCurrent(getDateFin(), getId()) != null) {

			HelperC.afficherMessage("Information", "La date Fin se trouve dans un autre Exercice");
		} else {

			boolean passe = true;
			if (this.exercisePrcd != null && this.exercisePrcd.getId() == getId()) {

				HelperC.afficherMessage("Attention", "Exercice précédent");
				passe = false;
			}
			if (passe) {

				setOperCreation(this.operateur);
				setDateCreation(Calendar.getInstance().getTime());
				Historique hist = new Historique();
				hist.setDateOperation(Calendar.getInstance().getTime());
				hist.setOperateur(this.operateur);
				if (getId() == 0) {

					hist.setOperation("Création de l'exercice " + getCode());
				} else {

					hist.setOperation("Modification de l'exercice " + getCode());
				}
				hist.setTable(Tables.getTableName(Tables.TableName.exercice));
				setHistorique(hist);
				if (FichierBaseDAO.getInstance().insertUpdateExercice(this)) {

					HelperC.afficherMessage("Information", "Succès de l'opération ");
					clear(true);
				} else {

					HelperC.afficherMessage("Désolé", "Echec de l'opération");
				}
			}
		}
	}

	public void delete() {
		if (getId() == 0) {

			HelperC.afficherDeleteMessage();
		} else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.exercice), getId())) {

			HelperC.afficherMessage("Information", "Succès de l'opération");
			clear(true);
		} else {

			HelperC.afficherMessage("Désolé", "Echec de l'opération ");
		}
	}

	public void initialiser() {
		clear(true);
	}

}
