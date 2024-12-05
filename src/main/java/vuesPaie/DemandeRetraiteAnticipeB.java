package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DemandeProlongationRetraiteC;
import classesPaie.DemandeRetraiteAnticipeC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.ParametrageFinCarriereC;
import classesPaie.ParametrageGeneralC;
import classesPaie.Tables;
import classesPaie.Tables.TableName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.primefaces.event.SelectEvent;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class DemandeRetraiteAnticipeB extends DemandeRetraiteAnticipeC {
	private static final long serialVersionUID = 5352140340946268540L;
	private DemandeRetraiteAnticipeC selected;
	private EmployeC selection;
	private List<DemandeRetraiteAnticipeC> listDemande = new ArrayList<DemandeRetraiteAnticipeC>();
	private List<DemandeRetraiteAnticipeC> listDecisionDemande = new ArrayList<DemandeRetraiteAnticipeC>();
	private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
	private OperateurC operateur;
	private ExerciceC exercice;
	private HttpSession session = HelperC.getSession();
	private String personel, categorie, grade, niveauFrm, fonction;
	private String code, msgInfo;
	private String codeEmployeRecherche;
	private String nomEmployeRecherche;
	private String prenomEmployeRecherche;
	private int age, typeDec;
	private List<SelectItem> listMotif;
	private boolean disableSave;
	ParametrageFinCarriereC parm;
	ParametrageGeneralC parmGn;

	public DemandeRetraiteAnticipeC getSelected() {
		return this.selected;
	}

	public void setSelected(DemandeRetraiteAnticipeC selected) {
		this.selected = selected;
	}

	public EmployeC getSelection() {
		return this.selection;
	}

	public void setSelection(EmployeC selection) {
		this.selection = selection;
	}

	public List<DemandeRetraiteAnticipeC> getListDemande() {
		return this.listDemande;
	}

	public void setListDemande(List<DemandeRetraiteAnticipeC> listDemande) {
		this.listDemande = listDemande;
	}

	public List<DemandeRetraiteAnticipeC> getListDecisionDemande() {
		return this.listDecisionDemande;
	}

	public void setListDecisionDemande(List<DemandeRetraiteAnticipeC> listDecisionDemande) {
		this.listDecisionDemande = listDecisionDemande;
	}

	public List<EmployeC> getListEmploye() {
		return this.listEmploye;
	}

	public void setListEmploye(List<EmployeC> listEmploye) {
		this.listEmploye = listEmploye;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public static long getSerialversionuid() {
		return 5352140340946268540L;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public int getTypeDec() {
		return typeDec;
	}

	public void setTypeDec(int typeDec) {
		this.typeDec = typeDec;
	}

	public boolean isDisableSave() {
		return disableSave;
	}

	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
	}

	public List<SelectItem> getListMotif() {
		return listMotif;
	}

	public void setListMotif(List<SelectItem> listMotif) {
		this.listMotif = listMotif;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPersonel() {
		return personel;
	}

	public void setPersonel(String personel) {
		this.personel = personel;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNiveauFrm() {
		return niveauFrm;
	}

	public void setNiveauFrm(String niveauFrm) {
		this.niveauFrm = niveauFrm;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

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
			parmGn = FichierBaseDAO.getInstance().getParametrageGeneral();
			UIComponent frm = null;
			FacesContext context = FacesContext.getCurrentInstance();
			frm = context.getViewRoot().findComponent("frmRtr");
			if (frm != null) {

				setEtat(Constante
						.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.demandeRetraiteAnticipe));

				setDateDemande(new Date());
				setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
			}
			frm = context.getViewRoot().findComponent("frmVld");
			if (frm != null) {

				setEtat(Constante
						.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.traitementRetraite));
				setDateDecision(new Date());
				setDateDecisionS(HelperC.changeDateFormate(getDateDecision()));
			}

			frm = context.getViewRoot().findComponent("frmRtrLn");
			if (frm != null) {

				setEtat(Constante
						.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.demandeRetraiteAnticipe));
				setDateDemande(new Date());
				setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
				employeInLine();

			}
		}
		chargementMotif();
	}

	private void employeInLine() {
		selection = FactoryDAO.getInstance().getEmploye(operateur.getIdEmploye(), false);
		if (this.selection != null) {
			setObject1();
		}
	}

	private void completeEmploye() {
		if (getEmploye() != null) {
			Base fx = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdFnctn(),
					Tables.getTableName(Tables.TableName.fonction));
			Base nv = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdNvFormt(),
					Tables.getTableName(Tables.TableName.niveauFormation));
			Base catg = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdCatgrie(),
					Tables.getTableName(Tables.TableName.categoriePersonnel));
			Base pers = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdPersnl(),
					Tables.getTableName(Tables.TableName.personnel));
			Base grd = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdGrd(),
					Tables.getTableName(Tables.TableName.gradePersonnel));
			if (fx != null)
				fonction = fx.getDesignation();
			else
				fonction = "";

			if (nv != null)
				niveauFrm = nv.getDesignation();
			else
				niveauFrm = "";

			if (catg != null)
				categorie = catg.getDesignation();
			else
				categorie = "";
			if (pers != null)
				personel = pers.getDesignation();
			else
				personel = "";
			if (grd != null)
				grade = grd.getDesignation();
			else
				grade = "";
			age = getEmploye().getAge();
			searchParametre();
		}
	}

	private void chargementMotif() {
		listMotif = new ArrayList<SelectItem>();
		listMotif.add(new SelectItem(0, ""));
		for (Base b : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.motifRetraite))) {
			listMotif.add(new SelectItem(b.getId(), b.getDesignation()));
		}
	}

	private void searchParametre() {
		disableSave = false;
		parm = FichierBaseDAO.getInstance().getParametrageFinCarriere(selection.getIdPersnl());
		if (parm != null) {
			if (getAnciennette() < parm.getPeriodeAnticipe()) {
				disableSave = true;
				HelperC.afficherAttention("Information",
						"L'employé doit avoir " + parm.getPeriodeAnticipe() + " ans d'ancienneté !");
				msgInfo = "Il faut avoir l'anciennété de " + parm.getPeriodeAnticipe()
						+ " ans pour demander la retraite anticipée ";
				return;
			}
		}
	}

	public void onRowVldSelected(SelectEvent event) {
		this.selected = (DemandeRetraiteAnticipeC) event.getObject();
		if (this.selected != null)
			setObject();
		PrimeFaces.current().executeScript("PF('dlgVld').hide();");
	}

	public void chargementTraite() {
		listDecisionDemande = FactoryDAO.getInstance().getListeRetraiteAnticipeValide(2, typeDec);
	}

	public void findByCode() {
		this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
		if (this.selection != null) {

			setObject1();

		} else {
			clear1(true);
		}
	}

	private void clear1(boolean b) {
		if (b) {
			this.selection = null;
		}
	}

	private void setObject() {
		if (this.selected != null) {
			setId(this.selected.getId());
			setEmploye(this.selected.getEmploye());
			selection = selected.getEmploye();
			if (getEmploye() != null) {
				setObject1();
			}
			setDateDemande(this.selected.getDateDemande());
			setDateDemandeS(this.selected.getDateDemandeS());

			setIdMotifDemande(this.selected.getIdMotifDemande());
			setAnciennette(this.selected.getAnciennette());
			setDateDebutRetraite(this.selected.getDateDebutRetraite());
			setDateDebutRetraiteS(this.selected.getDateDebutRetraiteS());
			setDecision(this.selected.getDecision());
			if (this.selected.getDateDecision() != null) {
				setDateDecision(this.selected.getDateDecision());
			} else {
				setDateDecision(null);
			}
			setDateDecisionS(HelperC.changeDateFormate(getDateDecision()));
			setLibelleDemandeRetraite(this.selected.getLibelleDemandeRetraite());
		}
	}

	private void clear() {
		setId(0);
		setEmploye(null);
		setDateDemande(null);
		setDateDemandeS("");
		setEtat(0);
		setDateDemande(null);
		setDateDemandeS("");
		setEtat(0);
		personel = "";
		categorie = "";
		grade = "";
		niveauFrm = "";
		fonction = "";
		setIdMotifDemande(0);
		setAnciennette(0);
		setDateDebutRetraite(null);
		setDateDebutRetraiteS("");
		setDecision(0);
		setDateDecision(null);
		setDateDecisionS("");
		setLibelleDemandeRetraite("");
		this.nomEmployeRecherche = "";
		this.code = "";
		this.prenomEmployeRecherche = "";
		this.selected = null;
	}

	public void clearLine() {
		setId(0);

		setEtat(0);
		setIdMotifDemande(0);

		setDateDebutRetraite(null);
		setDateDebutRetraiteS("");
		setDecision(0);
		setDateDecision(null);
		setDateDecisionS("");
		setLibelleDemandeRetraite("");
		this.selected = null;
	}

	public void changeDateDemande() {
		if (getDateDemandeS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateDemande(null);
		} else {

			setDateDemande(HelperC.validerDate(getDateDemandeS()));
			if (getDateDemande() == null) {
				setDateDemandeS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {

				setDateDemandeS(HelperC.convertDate(getDateDemande()));
			}
		}
	}

	public void changeDateDebutRetraite() {
		if (getDateDebutRetraiteS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateDebutRetraite(null);
		} else {

			setDateDebutRetraite(HelperC.validerDate(getDateDebutRetraiteS()));
			if (getDateDebutRetraite() == null) {
				setDateDebutRetraiteS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {

				setDateDebutRetraiteS(HelperC.convertDate(getDateDebutRetraite()));
				if (selection != null)
					prolongation(selection.getId());
			}
		}
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

	public void onRowSelected(SelectEvent event) {
		this.selected = (DemandeRetraiteAnticipeC) event.getObject();
		if (this.selected != null)
			setObject();
		PrimeFaces.current().executeScript("PF('dlgDmd').hide();");
	}

	public void onRowDmdSelected(SelectEvent event) {
		this.selected = (DemandeRetraiteAnticipeC) event.getObject();
		if (this.selected != null)
			setObject();
		PrimeFaces.current().executeScript("PF('dlgDem').hide();");
	}

	private void setObject1() {
		if (this.selection != null) {
			setEmploye(this.selection);

			if (getEmploye() != null) {
				setCode(this.selection.getCode());
				setEmploye(this.selection);
				setAnciennette(selection.getAnciennete());

				completeEmploye();
			} else {
				setCode("");
			}
		}
	}

	private void prolongation(int idEmp) {
		disableSave = false;
		int dif = 0;
		DemandeProlongationRetraiteC prol = FactoryDAO.getInstance().getDemandeProlongationParEmploye(idEmp, 2, 2);
		if (prol != null) {
			dif = HelperC.getYearFromDate(getDateDebutRetraite()) - HelperC.getYearFromDate(prol.getDateDemande());
			if (dif <= prol.getAgeRetraiteDemande()) {
				disableSave = true;
				HelperC.afficherAttention("Information", "L'employé est  en prolongation de travail !");
				return;
			}
		}

	}

	public void chargement() {
		listDemande = FactoryDAO.getInstance().getListeDemandeRetraiteAnticipe(1);
	}

	public void chargementLine() {
		listDemande = FactoryDAO.getInstance().getListeDemandeRetraiteAnticipeLine(selection.getId());
	}

	public void changeMotif(ValueChangeEvent event) {
		setIdMotifDemande(((Integer) event.getNewValue()).intValue());
	}

	public void onRowselected1(SelectEvent event) {
		this.selection = (EmployeC) event.getObject();
		setObject1();
		PrimeFaces.current().executeScript("PF('dlg').hide();");
	}

	public void chargerEmploye() {
		this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,
				true);
	}

	public void enregistrerDemande() {
		if (getEmploye() == null) {
			HelperC.afficherMessage("Information", "Veillez saisir l'employé");
		} else if (getDateDemande() == null) {
			HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
		} else if (getDateDemande() == null) {
			HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de debut de la retraite");
		} else if (getIdMotifDemande() == 0) {
			HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
		} else if (getAnciennette() == 0) {
			HelperC.afficherAttention("Information", "Veillez indiquer l'ancienneté de l'employé");
		} else {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {
				hist.setOperation("Création de la demande de la retraite anticipée de l'employé "
						+ getEmploye().getNom() + " " + getEmploye().getPrenom());
			} else {
				hist.setOperation("Modification de la demande de la retraite anticipée  de l'employé "
						+ getEmploye().getNom() + " " + getEmploye().getPrenom());
			}
			hist.setTable(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
			setHistorique(hist);
			setEtat(Constante
					.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.demandeRetraiteAnticipe));
			if (FactoryDAO.getInstance().insertUpdateDemandeRetraiteAnticipe(this)) {

				HelperC.afficherMessage("Information", "Succès de l'opération");
				chargement();
				clear();
			} else {

				HelperC.afficherMessage("Désolé", "Echec de l'opération ");
			}
		}
	}

	public void enregistrerDemandeLine() {
		if (getDecision() == 0 && getDateDecision() == null) {
			if (getEmploye() == null) {
				HelperC.afficherMessage("Information", "Veillez saisir l'employé");
			} else if (getDateDemande() == null) {
				HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
			} else if (getDateDemande() == null) {
				HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de debut de la retraite");
			} else if (getIdMotifDemande() == 0) {
				HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
			} else {

				Historique hist = new Historique();
				hist.setDateOperation(Calendar.getInstance().getTime());
				hist.setOperateur(this.operateur);
				if (getId() == 0) {
					hist.setOperation("Création de la demande de la retraite anticipée de l'employé "
							+ getEmploye().getNom() + " " + getEmploye().getPrenom());
				} else {
					hist.setOperation("Modification de la demande de la retraite anticipée  de l'employé "
							+ getEmploye().getNom() + " " + getEmploye().getPrenom());
				}
				hist.setTable(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
				setHistorique(hist);
				setInLine(true);
				if (FactoryDAO.getInstance().insertUpdateDemandeRetraiteAnticipe(this)) {

					HelperC.afficherMessage("Information", "Succès de l'opération");
					chargementLine();
					clearLine();
				} else {

					HelperC.afficherMessage("Désolé", "Echec de l'opération ");
				}
			}
		} else {
			HelperC.afficherAttention("Information", "Impossible de modifier car votre demande ést déjé traitée ");
		}
	}

	public void enregistrerDecision() {
		if (getEmploye() == null) {
			HelperC.afficherMessage("Information", "Veillez saisir l'employé");
		} else if (getDateDemande() == null) {
			HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
		} else if (getDateDemande() == null) {
			HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de debut de la retraite");
		} else if (getDateDecision() == null) {
			HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de decision");
		} else if (getIdMotifDemande() == 0) {
			HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
		} else if (getAnciennette() == 0) {
			HelperC.afficherAttention("Information", "Veillez indiquer l'ancienneté de l'employé");
		}

		Historique hist = new Historique();
		hist.setDateOperation(Calendar.getInstance().getTime());
		hist.setOperateur(this.operateur);
		if (getId() == 0) {
			hist.setOperation("Création de la demande de la retraite anticipée de l'employé " + getEmploye().getNom()
					+ " " + getEmploye().getPrenom());
		} else {
			hist.setOperation("Modification de la demande de la retraite anticipée  de l'employé "
					+ getEmploye().getNom() + " " + getEmploye().getPrenom());
		}
		hist.setTable(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
		setHistorique(hist);
		if (FactoryDAO.getInstance().insertUpdateDemandeRetraiteAnticipe(this)) {

			HelperC.afficherMessage("Information", "Succès de l'opération");
			/*
			 * if (parmGn != null) { if (getDecision() == 1)
			 * HelperC.sendEmail(parmGn.getSmtpServer(), parmGn.getMailOrigine(),
			 * parmGn.getMailOrigine(), parmGn.getPwdOrigine(), getEmploye().getEmail(),
			 * "Votre demande de retraite anticipée a été accptée ",
			 * "Demande retraite anticipée"); if (getDecision() == 2)
			 * HelperC.sendEmail(parmGn.getSmtpServer(), parmGn.getMailOrigine(),
			 * parmGn.getMailOrigine(), parmGn.getPwdOrigine(), getEmploye().getEmail(),
			 * "Votre demande de retraite anticipée a été refusée",
			 * "Demande retraite anticipée"); }
			 */
			chargementTraite();
			clear();
		} else {

			HelperC.afficherMessage("Désolé", "Echec de l'opération ");
		}
	}

	public void annuler() {
		if (selected != null)
			if (FactoryDAO.getInstance().annulerRetraiteAnticipe(selected)) {
				HelperC.afficherMessage("Information", "Succès de l'opération");
				clear();
			} else
				HelperC.afficherMessage("Désolé", "Echec de l'opération ");
	}

	public void supprimer() {
		if (getId() == 0) {
			HelperC.afficherDeleteMessage();
		} else {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {
				hist.setOperation("Création de la demande de la retraite anticipée de l'employé "
						+ getEmploye().getNom() + " " + getEmploye().getPrenom());
			} else {
				hist.setOperation("Modification de la demande de la retraite anticipée  de l'employé "
						+ getEmploye().getNom() + " " + getEmploye().getPrenom());
			}
			hist.setTable(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
			setHistorique(hist);
			if (FactoryDAO.getInstance().deleteDemandeRetraiteAnticipe(this)) {
				chargement();
				clear();
				HelperC.afficherMessage("Information", "Succès de l'opération ");
			} else {

				HelperC.afficherMessage("Désolé", "Echec de suppression");
			}
		}
	}

	public void initialiser() {
		clear();
	}
}
