package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.ParametrageDureeCongeC;
import classesPaie.ParametrageGeneralC;
import classesPaie.SaisieDemandeCongeC;
import classesPaie.SaisiePlanCongeC;
import classesPaie.Tables;
import classesPaie.TypeCongeC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import editionPaie.EditionConge;
import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class SaisieDemandeCongeB extends SaisieDemandeCongeC {
	private static final long serialVersionUID = -7807048688759477121L;
	private int idType;
	private int idSorte;
	private SaisieDemandeCongeC selected;
	private EmployeC selection;
	private List<SaisieDemandeCongeC> listSaisieDemandeConge = new ArrayList<SaisieDemandeCongeC>();
	private List<SaisieDemandeCongeC> listSaisieRetardRetourConge = new ArrayList<SaisieDemandeCongeC>();
	private List<SelectItem> listTypeConge = new ArrayList<SelectItem>();
	private List<SelectItem> listNatureConge = new ArrayList<SelectItem>();
	private String code, dateDecisionS;
	private String codeRecherche;
	private String nomRecherche;
	private String nom;
	private String service;
	private String unite;
	private String categorie;
	private String grade;
	private String echelon;
	private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
	private String jourCongeAnnuelDuS;
	private String jourCongeAnnuelPrisS;
	private String soldeS;
	private int jourCongeAnnuelDu;
	private int jourCongeAnnuelPris;
	private int solde, typeDecision;
	private String codeEmployeRecherche;
	private String nomEmployeRecherche;
	private String prenomEmployeRecherche;
	private OperateurC operateur;
	private ExerciceC exercice;
	private DroitC droit;
	private HttpSession session = HelperC.getSession();

	private boolean disableSave, disableMsg;

	private boolean disableMotif;
	boolean onLine;
	SaisiePlanCongeC plan;
	ParametrageDureeCongeC prmDur;
	ParametrageGeneralC parm;

	@PostConstruct
	private void charger() {
		this.operateur = new OperateurC();
		this.exercice = new ExerciceC();
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
			Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (userFonction != null)
				this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.gestionConge);
			disableSave = true;
			this.listTypeConge.add(new SelectItem(0, ""));

			for (TypeCongeC conge : FichierBaseDAO.getInstance().getAllTypeConge()) {
				this.listTypeConge.add(new SelectItem(Integer.valueOf(conge.getId()), conge.getDesignation()));
			}

			parm = FichierBaseDAO.getInstance().getParametrageGeneral();
		}
	}

	public List<SaisieDemandeCongeC> getListSaisieRetardRetourConge() {
		return this.listSaisieRetardRetourConge;
	}

	public void setListSaisieRetardRetourConge(List<SaisieDemandeCongeC> listSaisieRetardRetourConge) {
		this.listSaisieRetardRetourConge = listSaisieRetardRetourConge;
	}

	public int getIdSorte() {
		return this.idSorte;
	}

	public void setIdSorte(int idSorte) {
		this.idSorte = idSorte;
	}

	public String getCodeRecherche() {
		return this.codeRecherche;
	}

	public void setCodeRecherche(String codeRecherche) {
		this.codeRecherche = codeRecherche;
	}

	public String getNomRecherche() {
		return this.nomRecherche;
	}

	public void setNomRecherche(String nomRecherche) {
		this.nomRecherche = nomRecherche;
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

	public List<EmployeC> getListEmploye() {
		return this.listEmploye;
	}

	public void setListEmploye(List<EmployeC> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public int getJourCongeAnnuelDu() {
		return this.jourCongeAnnuelDu;
	}

	public void setJourCongeAnnuelDu(int jourCongeAnnuelDu) {
		this.jourCongeAnnuelDu = jourCongeAnnuelDu;
	}

	public double getJourCongeAnnuelPris() {
		return this.jourCongeAnnuelPris;
	}

	public void setJourCongeAnnuelPris(int jourCongeAnnuelPris) {
		this.jourCongeAnnuelPris = jourCongeAnnuelPris;
	}

	public int getSolde() {
		return this.solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNiveau() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getUnite() {
		return this.unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEchelon() {
		return this.echelon;
	}

	public void setEchelon(String echelon) {
		this.echelon = echelon;
	}

	public SaisieDemandeCongeC getSelected() {
		return this.selected;
	}

	public void setSelected(SaisieDemandeCongeC selected) {
		this.selected = selected;
	}

	public EmployeC getSelection() {
		return this.selection;
	}

	public void setSelection(EmployeC selection) {
		this.selection = selection;
	}

	public int getIdType() {
		return this.idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getJourCongeAnnuelDuS() {
		return this.jourCongeAnnuelDuS;
	}

	public void setJourCongeAnnuelDuS(String jourCongeAnnuelDuS) {
		this.jourCongeAnnuelDuS = jourCongeAnnuelDuS;
	}

	public String getJourCongeAnnuelPrisS() {
		return this.jourCongeAnnuelPrisS;
	}

	public void setJourCongeAnnuelPrisS(String jourCongeAnnuelPrisS) {
		this.jourCongeAnnuelPrisS = jourCongeAnnuelPrisS;
	}

	public String getSoldeS() {
		return this.soldeS;
	}

	public void setSoldeS(String soldeS) {
		this.soldeS = soldeS;
	}

	public String getCodeEmployeRecherche() {
		return this.codeEmployeRecherche;
	}

	public void setCodeEmployeRecherche(String codeEmployeRecherche) {
		this.codeEmployeRecherche = codeEmployeRecherche;
	}

	public String getNomEmployeRecherche() {
		return this.nomEmployeRecherche;
	}

	public void setNomEmployeRecherche(String nomEmployeRecherche) {
		this.nomEmployeRecherche = nomEmployeRecherche;
	}

	public String getPrenomEmployeRecherche() {
		return this.prenomEmployeRecherche;
	}

	public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
		this.prenomEmployeRecherche = prenomEmployeRecherche;
	}

	public List<SelectItem> getListTypeConge() {
		return this.listTypeConge;
	}

	public void setListTypeConge(List<SelectItem> listTypeConge) {
		this.listTypeConge = listTypeConge;
	}

	public List<SelectItem> getListNatureConge() {
		return this.listNatureConge;
	}

	public void setListNatureConge(List<SelectItem> listNatureConge) {
		this.listNatureConge = listNatureConge;
	}

	public String getService() {
		return this.service;
	}

	public List<SaisieDemandeCongeC> getListSaisieDemandeConge() {
		return this.listSaisieDemandeConge;
	}

	public void setListSaisieDemandeConge(List<SaisieDemandeCongeC> listSaisieDemandeConge) {
		this.listSaisieDemandeConge = listSaisieDemandeConge;
	}

	public boolean isDisableSave() {
		return disableSave;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	public boolean isDisableMotif() {
		return disableMotif;
	}

	public void setDisableMotif(boolean disableMotif) {
		this.disableMotif = disableMotif;
	}

	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
	}

	public int getTypeDecision() {
		return typeDecision;
	}

	public void setTypeDecision(int typeDecision) {
		this.typeDecision = typeDecision;
	}

	public String getDateDecisionS() {
		return dateDecisionS;
	}

	public void setDateDecisionS(String dateDecisionS) {
		this.dateDecisionS = dateDecisionS;
	}

	public void chargerTypeConge(ValueChangeEvent event) {
		this.idType = ((Integer) event.getNewValue()).intValue();
		if (this.idType != 0) {
			setTypeConge(FichierBaseDAO.getInstance().getTypeConge(this.idType));
			if (getTypeConge() != null && selection!=null)
				calculerJour();
		}
	}

	public void findByCode() {
		this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);

		if (this.selection != null) {
			setObject1();

		}
	}

	public void findEmployeRecherche() {
		this.selection = FactoryDAO.getInstance().getEmploye(this.codeRecherche, false);

		if (this.selection != null) {
			setObject1();
		}
	}

	public void chargerEmploye() {
		this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,
				true);
	}

	private void calculerJour() {
		int jrsPris = FactoryDAO.getInstance().getCongeDejaPris(selection, idType, exercice.getId(), null, null);
		
		setJourCongeAnnuelPris(jrsPris);
		setJourCongeAnnuelDu(selection.getJourConge());
		solde = jourCongeAnnuelDu - jrsPris;
	}

	public void changeDateDecision() {
		if (getDateDecisionS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateDecision(null);
		} else {

			setDateDecision(HelperC.validerDate(getDateDecisionS()));
			if (getDateDecision() == null) {
				setDateDecisionS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {

				setDateDecisionS(HelperC.convertDate(getDateDecision()));

			}
		}
	}

	private void clearPrevision() {
		this.jourCongeAnnuelDu = 0;
		this.jourCongeAnnuelDuS = "";
		this.jourCongeAnnuelPris = 0;
		this.jourCongeAnnuelPrisS = "";
		this.solde = 0;
		this.soldeS = "";
	}

	private void setObject() {
		if (this.selected != null) {
			
			setId(this.selected.getId());
			setEmploye(this.selected.getEmploye());
			this.selection = getEmploye();
			setDateDebut(this.selected.getDateDebut());
			setDateDebutS(this.selected.getDateDebutS());
			setDateFin(this.selected.getDateFin());
			setDateFinS(this.selected.getDateFinS());
			setDateDecision(selected.getDateDecision());
			setMotif(this.selected.getMotif());
			setDuree(this.selected.getDuree());
			setDateDecisionS(HelperC.convertDate(selected.getDateDecision()));
			setEtat(this.selected.getEtat());
			disableSave = false;
			setDecision(this.selected.getDecision());
			selection = this.selected.getEmploye();
			setTypeConge(this.selected.getTypeConge());
			idType = selected.getTypeConge().getId();
			jourCongeAnnuelDu=selection.getJourConge();
			calculerJour();
			setObject1();

		}
	}

	public void onValidRowSelected(SelectEvent event) {
		this.selected = (SaisieDemandeCongeC) event.getObject();
		setObject();
		PrimeFaces.current().executeScript("PF('rechercheDialog1').hide();");
	}

	public void changeDays() {
		disableSave = false;
		if (getDuree() > 0 && getDateDebut() != null) {
			disableSave = false;
			
			setDateFin(HelperC.addDay(getDateDebut(), getDuree()));
			setDateFinS(HelperC.convertDate(getDateFin()));
			
			checkDelay();
		} else {
			HelperC.afficherAttention("Attention", "Il faut préciser lla date début et la durée");
			disableSave = true;
		}
	}

	private void setObject1() {
		if (this.selection != null) {

			setEmploye(this.selection);
			if (getEmploye() != null) {
				this.code = getEmploye().getCode();
				this.codeRecherche = getEmploye().getCode();
			} else {
				this.code = "";
				this.codeRecherche = "";
			}
		}
	}

	private void clear(boolean b) {
		if (b)
			setId(0);
		setEmploye(null);
		setTypeConge(null);
		setDateDebut(null);
		setDateDebutS("");
		setDateFin(null);
		setDateFinS("");
		dateDecisionS = "";
		setDateAttributionS("");
		setEtat(0);
		setMotif("");
		setDuree(0);
		setDecision(0);
		setTypeConge(null);
		setLibelleEtatDemandeConge("");
		setLibelleSorteConge("");
		setDateRetourConge(null);
		setDateRetourCongeS("");
		this.selected = null;
		this.code = "";
		this.nom = "";
		this.service = "";
		this.unite = "";
		this.categorie = "";
		this.grade = "";
		this.echelon = "";
		this.idType = 0;
		this.idSorte = 0;
		this.listNatureConge.clear();
		clearPrevision();
		disableSave = true;
	}

	public void initialiser() {
		clear(true);
	}

	public void changeDateDebut() {
		if (getDateDebutS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateDebut(null);
		} else {

			setDateDebut(HelperC.validerDate(getDateDebutS()));
			if (getDateDebut() == null) {
				setDateDebutS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {

				setDateDebutS(HelperC.convertDate(getDateDebut()));
				changeDays();
			}
		}
	}

	private void checkDelay() {
		getDuree();
		disableSave = false;
		
		if (solde > 0) {
			if (getDuree() > solde) {
				disableSave = true;
				HelperC.afficherAttention("Attention", "La durée de congé ne doit pas dépasser les jours restants  ");
				return;
			}
			else
			{
				
			}
		}
	}

	public void changeDateFin() {
		if (getDateFinS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateFin(null);
		} else {

			setDateFin(HelperC.validerDate(getDateFinS()));
			if (getDateFin() == null) {
				setDateFinS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {

				setDateFinS(HelperC.convertDate(getDateFin()));
				//checkDelay();
			}
		}
	}

	public void changeDateRetour() {
		if (getDateRetourCongeS().replace("/", "").trim().equals("")) {
			setDateRetourConge(null);
		} else {
			setDateRetourConge(HelperC.validerDate(getDateRetourCongeS()));
			if (getDateRetourConge() == null) {
				setDateRetourCongeS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {
				setDateRetourCongeS(HelperC.convertDate(getDateRetourConge()));
			}
		}
	}

	public void modifDemande() {
		setObject();
	}

	public void annulerModifDemande() {
		setDateDebut(null);
		setDateDebutS("");
	}

	public void changeDecision(ValueChangeEvent event) {
		disableMotif = false;
		setDecision(((Integer) event.getNewValue()).intValue());
		if (getDecision() == 2) {
			disableMotif = false;
		}
		if (getDecision() == 1) {
			disableMotif = true;
			setMotif("");
		}
	}

	public void clear2() {
		this.listEmploye.clear();
		this.codeEmployeRecherche = "";
		this.nomEmployeRecherche = "";
		this.prenomEmployeRecherche = "";
	}

	public void clear3() {
		this.listSaisieDemandeConge.clear();
		this.codeRecherche = "";
		PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
	}

	public void findDemandeConge() {

		this.listSaisieDemandeConge = FactoryDAO.getInstance().getListSaisieDemandeConge(typeDecision,
				exercice.getId());
	}

	public void findDemandeEncour() {

		this.listSaisieDemandeConge = FactoryDAO.getInstance().getListSaisieDemandeConge(1,
				exercice.getId());
	}
	public void printConge() {
		EditionConge ed = new EditionConge();
		ed.setConge(selected);
		ed.setEmploye(selection);
		ed.setIdExercice(exercice.getId());
		ed.setIdTypeConge(idType);
		ed.setNbrJrConge(jourCongeAnnuelDu);
		ed.visualiser();
	}

	public void saveDemande() {
		
		if (getId() == 0 && !this.droit.isCreer()) {
			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
		} else if (getId() > 0 && !this.droit.isModifier()) {
			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
		} else if (getEmploye() == null || getTypeConge() == null || getDateDebut() == null || getDateFin() == null
				|| getDuree() == 0.0D) {
			HelperC.afficherMessage("Information", "Veillez remplir tous les champs obligatoires");
		} else {

			setEtat(Constante.getEtatDemandeConge(Constante.EtatDemandeConge.demandeConge));
			this.setDecision(Constante.getEtatDemandeConge(Constante.EtatDemandeConge.demandeConge));
		
			
			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {
				hist.setOperation("Création de la saisie de demande de congé ");
			} else {
				hist.setOperation("Modification de la saisie de demande de congé ");
			}
			hist.setTable(Tables.getTableName(Tables.TableName.saisieDemandeConge));
			setHistorique(hist);
			setExercise(exercice);
			if (FactoryDAO.getInstance().insertUpdateSaisieDemandeConge(this)) {
				HelperC.afficherMessage("Félicitation", "Enregistrement avec succès");
				clear(true);
			} else {

				HelperC.afficherMessage("Dèsolé", "Echec d'enregistrement");
			}
		}
	}
	public void saveDecision() {
		
		 if (getId() > 0 && this.droit.isModifier()) {
		

			setEtat(typeDecision);

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			hist.setOperation("Traitement de la demande de congé ");
			hist.setTable(Tables.getTableName(Tables.TableName.saisieDemandeConge));
			setHistorique(hist);
			setExercise(exercice);
			if (FactoryDAO.getInstance().insertUpdateSaisieDemandeConge(this)) {
				HelperC.afficherMessage("Félicitation", "Enregistrement avec succès");
				clear(true);
			} else {

				HelperC.afficherMessage("Dèsolé", "Echec d'enregistrement");
			}
		}
	}
	public void delete() {
		if (this.selected == null) {
			HelperC.afficherDeleteMessage();
		} else if (this.selected != null && !this.droit.isSupprimer()) {
			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
		}
		if (this.selected != null) {
			FactoryDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.saisieDemandeConge));

			clear(true);
			HelperC.afficherMessage("Félicitation", "Suppression avec succès");
		}
	}

	public void onRowselected(SelectEvent event) {
		this.selected = (SaisieDemandeCongeC) event.getObject();
		setObject();
		PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
	}

	public void onRowLineselected(SelectEvent event) {
		this.selected = (SaisieDemandeCongeC) event.getObject();
		setObject();
		PrimeFaces.current().executeScript("PF('dlgLine').hide();");
	}

	public void onRowselected1(SelectEvent event) {
		this.selection = (EmployeC) event.getObject();
		setObject1();

		PrimeFaces.current().executeScript("PF('dlg').hide();");
	}

	public void annuler() {
		if (selected != null)
			if (FactoryDAO.getInstance().annulerConge(selected)) {
				HelperC.afficherMessage("Info", "Annulation avec succès");
				clear(true);
			}
	}
}
