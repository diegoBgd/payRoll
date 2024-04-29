package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.ParametrageAllocationFamillialeC;
import classesPaie.PersonneChargeC;
import classesPaie.Tables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class PersonneChargeB extends PersonneChargeC {
	private String dateNss;
 
	private List<SelectItem> listEmploye = new ArrayList<SelectItem>();
	private List<SelectItem> listStatuts = new ArrayList<SelectItem>();
	private HttpSession session = HelperC.getSession();
	int index = 0;
	private static final long serialVersionUID = -7899214160761018726L;
	private int idEmploye;
	private int statutPersonnel;

	private PersonneChargeC detailSelected;
	private ParametrageAllocationFamillialeC parametrageAllocation;
	private DroitC droit;
	private boolean desableAdd, disableMsg;
	private List<PersonneChargeC> listPerson;
	OperateurC operateur;
	ExerciceC exercice;
	Base userFonction;
	boolean selected;
	boolean isConjoint;

	@PostConstruct
	public void init() {
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
		this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		if (this.operateur == null || this.exercice == null) {

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
						Constante.Role.profilEmploye);
			}
			disableMsg = true;
			setNombre(1);
			this.listStatuts.add(new SelectItem(Integer.valueOf(0), ""));
			this.listStatuts.add(new SelectItem(
					Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.Conjoint)),
					Constante.getLibelleStatutPersonneACharge(
							Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.Conjoint))));
			this.listStatuts.add(new SelectItem(
					Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantLegitime)),
					Constante.getLibelleStatutPersonneACharge(
							Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantLegitime))));
			this.listStatuts.add(new SelectItem(
					Integer.valueOf(
							Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantNaturelReconnu)),
					Constante.getLibelleStatutPersonneACharge(
							Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantNaturelReconnu))));
			this.listStatuts.add(new SelectItem(
					Integer.valueOf(Constante
							.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantOrphelinSousTutelle)),
					Constante.getLibelleStatutPersonneACharge(Constante
							.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantOrphelinSousTutelle))));
			this.listStatuts.add(new SelectItem(
					Integer.valueOf(Constante
							.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantInaptePhysiquement)),
					Constante.getLibelleStatutPersonneACharge(Constante
							.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantInaptePhysiquement))));
			this.listEmploye.add(new SelectItem(Integer.valueOf(0), ""));

			for (EmployeC emp : FactoryDAO.getInstance().getAllEmploye(false, 0)) {
				this.listEmploye.add(new SelectItem(Integer.valueOf(emp.getId()),
						String.valueOf(emp.getCode()) + " " + emp.getNomPrenom()));
			}
		}
	}

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public List<SelectItem> getListEmploye() {
		return this.listEmploye;
	}

	public void setListEmploye(List<SelectItem> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public PersonneChargeC getDetailSelected() {
		return this.detailSelected;
	}

	public void setDetailSelected(PersonneChargeC detailSelected) {
		this.detailSelected = detailSelected;
	}

	public int getStatutPersonnel() {
		return this.statutPersonnel;
	}

	public void setStatutPersonnel(int statutPersonnel) {
		this.statutPersonnel = statutPersonnel;
	}

	public List<SelectItem> getListStatuts() {
		return this.listStatuts;
	}

	public void setListStatuts(List<SelectItem> listStatuts) {
		this.listStatuts = listStatuts;
	}

	public boolean isDesableAdd() {
		return this.desableAdd;
	}

	public void setDesableAdd(boolean desableAdd) {
		this.desableAdd = desableAdd;
	}

	public List<PersonneChargeC> getListPerson() {
		return listPerson;
	}

	public void setListPerson(List<PersonneChargeC> listPerson) {
		this.listPerson = listPerson;
	}

	public String getDateNss() {
		return dateNss;
	}

	public void setDateNss(String dateNss) {
		this.dateNss = dateNss;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	public void changeStatutPersonneCharge() {
		if (this.statutPersonnel > 0) {
			setRelation(statutPersonnel);
			this.desableAdd = false;
			this.parametrageAllocation = FichierBaseDAO.getInstance()
					.getParametrageAllocationFamillialeParStatutPersonne(this.statutPersonnel);
			if (this.parametrageAllocation != null) {

				setMontant(parametrageAllocation.getMontantAllocation());
				setNomPersonneCharge(Constante.getLibelleStatutPersonneACharge(statutPersonnel));
			} else {

				setMontant(0);
			}

		}
	}

	public void changeDateNaiss() {
		if (dateNss.replace("/", "").replace("_", "").trim().equals("")) {

			setDateNaissance(null);
		} else {

			setDateNaissance(HelperC.validerDate(dateNss));
			if (getDateNaissance() == null) {

				dateNss = "";

			} else {
				dateNss = HelperC.convertDate(getDateNaissance());

			}
		}
	}

	public void changeEmploye(ValueChangeEvent event) {
		this.idEmploye = ((Integer) event.getNewValue()).intValue();
		setEmploye(FactoryDAO.getInstance().getEmploye(this.idEmploye, false));
		chargerPersonneChrg();

	}

	private void chargerPersonneChrg() {
		listPerson = FactoryDAO.getInstance().getAllPersonnesChargeByEmploye(idEmploye);
	}

	public void onRowSelectedPerson() {
		this.selected = true;
		disableMsg = false;
		setId(detailSelected.getId());
		setNomPersonneCharge(this.detailSelected.getNomPersonneCharge());
		setSexe(detailSelected.getSexe());
		statutPersonnel = detailSelected.getRelation();
		setMontant(detailSelected.getMontant());
		setNombre(detailSelected.getNombre());
		if (this.detailSelected.getDateNaissance() != null) {

			setDateNaissance(detailSelected.getDateNaissance());
			dateNss = HelperC.convertDat(getDateNaissance());
		} else {
			dateNss = "";
			setDateNaissance(null);
		}

	}

	private void clearDetail() {
		setNomPersonneCharge("");
		setSexe("");
		this.statutPersonnel = 0;
		setDateNaissance(null);
		setId(0);
		setMontant(0);
		this.selected = false;
		this.index = 0;
		this.detailSelected = null;
		chargerPersonneChrg();
		disableMsg = true;
		dateNss = "";
	}

	public void enregistrer() {
		if (getId() == 0 && !this.droit.isCreer()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
		} else if (getId() > 0 && !this.droit.isModifier()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
		} else if (getEmploye() != null) {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(getOperator());
			if (getId() == 0) {

				hist.setOperation("Enregistrement des personnes é charge de " + getEmploye().getNom() + " "
						+ getEmploye().getPrenom());
			} else {

				hist.setOperation("Modification des personnes é charge de " + getEmploye().getNom() + " "
						+ getEmploye().getPrenom());
			}
			hist.setTable(Tables.getTableName(Tables.TableName.personneCharge));
			setHistoric(hist);
			if (FactoryDAO.getInstance().insertUpdatePersonneCharge(this)) {

				HelperC.afficherMessage("Information", "Opération effectuée avec succès!", FacesMessage.SEVERITY_INFO);
				clearDetail();
			} else {

				HelperC.afficherMessage("Information", "Echec de l'Opération!", FacesMessage.SEVERITY_ERROR);
			}
		} else {

			HelperC.afficherMessage("Information", "Veuillez préciser l'employé SVP!", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void supprimer() {
		if (getId() > 0) {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(getOperator());
			hist.setOperation(
					"Suppression des personnes é charge de " + getEmploye().getNom() + " " + getEmploye().getPrenom());
			hist.setTable(Tables.getTableName(Tables.TableName.personneCharge));
			setHistoric(hist);
			if (FactoryDAO.getInstance().deletePersonneEnCharge(this)) {

				HelperC.afficherMessage("Information", "Opération effectuée avec succès!", FacesMessage.SEVERITY_INFO);
				clearDetail();
			} else {

				HelperC.afficherMessage("Information", "Echec de l'Opération!", FacesMessage.SEVERITY_ERROR);
			}
		} else {

			HelperC.afficherDeleteMessage();
		}
	}

	private void viderControls() {

		this.idEmploye = 0;
		setEmploye(null);

		setNombre(1);
		clearDetail();
	}

	public void initialiser() {
		viderControls();
		clearDetail();
	}
}
