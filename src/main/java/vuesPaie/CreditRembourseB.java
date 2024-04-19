package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.CreditC;
import classesPaie.CreditDetailC;
import classesPaie.CreditRembourseC;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.MarcheProgrammeC;
import classesPaie.OperateurC;
import classesPaie.Tables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class CreditRembourseB extends CreditRembourseC {
	private static final long serialVersionUID = 5137930733889118284L;
	private String numeroDossier;
	private String codeEmploye;
	private String nomEmploye;
	private String prenomEmploye;
	private String profession;
	private String designationTypeCredit;
	private String codeSourceFinancement;
	private String designationSourceFinancement;
	private String taxeS;
	private String datePretS;
	private String dateEcheanceS;
	private String frequenceEcheance;
	private String dateDebRech;
	private String dateFinRech;
	private String tauxInteretS;
	private String terme;
	private String codeRech;
	private String nomRech;
	private String prenomRech;
	private String dateRemboursementS;
	private List<CreditRembourseC> listRemboursement;
	private List<SelectItem> listEmploye;
	private List<CreditC> listeCredit;
	private EmployeC employe;
	private CreditRembourseC creditRembourse;
	private MarcheProgrammeC marche;
	private OperateurC operateur;
	private ExerciceC exercice;
	private HttpSession session = HelperC.getSession();
	private CreditC credit;
	private CreditDetailC detailCredit;
	private double tauxInteret;
	private double tauxTaxe;
	private double totalCapital;
	Date dateDeb = null;
	private double totalRembourse;
	private double resteRembourse;
	private double montantOctroye;
	private DroitC droit;
	private int periode, idEmploye;
	private boolean desable, disableMsg;
	Date dateFin = null;

	Base userFonction;

	public DroitC getDroit() {
		return this.droit;
	}

	public void setDroit(DroitC droit) {
		this.droit = droit;
	}

	public String getDateDebRech() {
		return this.dateDebRech;
	}

	public void setDateDebRech(String dateDebRech) {
		this.dateDebRech = dateDebRech;
	}

	public String getDateFinRech() {
		return this.dateFinRech;
	}

	public void setDateFinRech(String dateFinRech) {
		this.dateFinRech = dateFinRech;
	}

	public String getDateRemboursementS() {
		return this.dateRemboursementS;
	}

	public void setDateRemboursementS(String dateRemboursementS) {
		this.dateRemboursementS = dateRemboursementS;
	}

	public String getCodeRech() {
		return this.codeRech;
	}

	public void setCodeRech(String codeRech) {
		this.codeRech = codeRech;
	}

	public String getNomRech() {
		return this.nomRech;
	}

	public void setNomRech(String nomRech) {
		this.nomRech = nomRech;
	}

	public String getPrenomRech() {
		return this.prenomRech;
	}

	public void setPrenomRech(String prenomRech) {
		this.prenomRech = prenomRech;
	}

	public double getResteRembourse() {
		return this.resteRembourse;
	}

	public void setResteRembourse(double resteRembourse) {
		this.resteRembourse = resteRembourse;
	}

	public double getTotalCapital() {
		return this.totalCapital;
	}

	public void setTotalCapital(double totalCapital) {
		this.totalCapital = totalCapital;
	}

	public double getTotalRembourse() {
		return this.totalRembourse;
	}

	public void setTotalRembourse(double totalRembourse) {
		this.totalRembourse = totalRembourse;
	}

	public int getPeriode() {
		return this.periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public double getTauxTaxe() {
		return this.tauxTaxe;
	}

	public void setTauxTaxe(double tauxTaxe) {
		this.tauxTaxe = tauxTaxe;
	}

	public double getTauxInteret() {
		return this.tauxInteret;
	}

	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public CreditDetailC getDetailCredit() {
		return this.detailCredit;
	}

	public void setDetailCredit(CreditDetailC detailCredit) {
		this.detailCredit = detailCredit;
	}

	public CreditC getCredit() {
		return this.credit;
	}

	public void setCredit(CreditC credit) {
		this.credit = credit;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public String getNumeroDossier() {
		return this.numeroDossier;
	}

	public CreditRembourseC getCreditRembourse() {
		return this.creditRembourse;
	}

	public void setCreditRembourse(CreditRembourseC creditRembourse) {
		this.creditRembourse = creditRembourse;
	}

	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	public String getCodeEmploye() {
		return this.codeEmploye;
	}

	public void setCodeEmploye(String codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public String getNomEmploye() {
		return this.nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getPrenomEmploye() {
		return this.prenomEmploye;
	}

	public void setPrenomEmploye(String prenomEmploye) {
		this.prenomEmploye = prenomEmploye;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDesignationTypeCredit() {
		return this.designationTypeCredit;
	}

	public void setDesignationTypeCredit(String designationTypeCredit) {
		this.designationTypeCredit = designationTypeCredit;
	}

	public String getCodeSourceFinancement() {
		return this.codeSourceFinancement;
	}

	public void setCodeSourceFinancement(String codeSourceFinancement) {
		this.codeSourceFinancement = codeSourceFinancement;
	}

	public String getDesignationSourceFinancement() {
		return this.designationSourceFinancement;
	}

	public void setDesignationSourceFinancement(String designationSourceFinancement) {
		this.designationSourceFinancement = designationSourceFinancement;
	}

	public double getMontantOctroye() {
		return this.montantOctroye;
	}

	public void setMontantOctroye(double montantOctroye) {
		this.montantOctroye = montantOctroye;
	}

	public String getTaxeS() {
		return this.taxeS;
	}

	public void setTaxeS(String taxeS) {
		this.taxeS = taxeS;
	}

	public String getDatePretS() {
		return this.datePretS;
	}

	public void setDatePretS(String datePretS) {
		this.datePretS = datePretS;
	}

	public String getDateEcheanceS() {
		return this.dateEcheanceS;
	}

	public void setDateEcheanceS(String dateEcheanceS) {
		this.dateEcheanceS = dateEcheanceS;
	}

	public String getFrequenceEcheance() {
		return this.frequenceEcheance;
	}

	public void setFrequenceEcheance(String frequenceEcheance) {
		this.frequenceEcheance = frequenceEcheance;
	}

	public String getTauxInteretS() {
		return this.tauxInteretS;
	}

	public void setTauxInteretS(String tauxInteretS) {
		this.tauxInteretS = tauxInteretS;
	}

	public String getTerme() {
		return this.terme;
	}

	public void setTerme(String terme) {
		this.terme = terme;
	}

	public MarcheProgrammeC getMarche() {
		return this.marche;
	}

	public void setMarche(MarcheProgrammeC marche) {
		this.marche = marche;
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

	public List<CreditRembourseC> getListRemboursement() {
		return this.listRemboursement;
	}

	public void setListRemboursement(List<CreditRembourseC> listRemboursement) {
		this.listRemboursement = listRemboursement;
	}

	public List<CreditC> getListeCredit() {
		return this.listeCredit;
	}

	public void setListeCredit(List<CreditC> listeCredit) {
		this.listeCredit = listeCredit;
	}

	public int getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public List<SelectItem> getListEmploye() {
		return listEmploye;
	}

	public void setListEmploye(List<SelectItem> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public boolean isDesable() {
		return desable;
	}

	public void setDesable(boolean desable) {
		this.desable = desable;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	@PostConstruct
	private void init() {
		this.operateur = new OperateurC();
		this.exercice = new ExerciceC();
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
		this.codeRech = "";
		this.nomRech = "";
		this.prenomRech = "";
		disableMsg = true;
		if (this.operateur == null || this.exercice == null) {

			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			listEmploye = new ArrayList<SelectItem>();
			chargementEmploye();
			this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (this.userFonction != null) {
				this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
						Constante.Role.creditAvance);
			}
		}
	}

	public void changeDateDebut() {
		if (this.dateDebRech.replace("/", "").replace("_", "").trim().equals("")) {

			this.dateDeb = null;
		} else {

			this.dateDeb = HelperC.stringTodate(this.dateDebRech);
		}
	}

	public void changeDateFin() {
		if (this.dateFinRech.replace("/", "").replace("_", "").trim().equals("")) {

			this.dateFin = null;
		} else {

			this.dateFin = HelperC.stringTodate(this.dateFinRech);
		}
	}

	public void chargementRemboursement() {
		listRemboursement = FactoryDAO.getInstance().getListRemboursementCredit(idEmploye);
	}

	public void chargementEmploye() {

		listEmploye.add(new SelectItem(0, ""));
		for (EmployeC empl : FactoryDAO.getInstance().getListEmployeSimple("")) {
			listEmploye.add(new SelectItem(empl.getId(), empl.getCode() + " " + empl.getNomPrenom()));
		}
	}

	public void chargementDossier() {
		this.listeCredit = FactoryDAO.getInstance().getListCredit(idEmploye);

	}

	public void changeNumeroDossier() {
		if (this.numeroDossier != "") {
			this.credit = FactoryDAO.getInstance().getCreditEmploye(this.numeroDossier);
			creditValues();
		}
	}

	private void creditValues() {
		this.numeroDossier = this.credit.getNumeroDossier();
		if (credit != null) {
			setIdCredit(credit.getId());
			montantOctroye = credit.getMontantCredit();
			employe = credit.getEmploye();
			datePretS = HelperC.convertDate(this.credit.getDatePret());
			periode = this.credit.getDuree();
			codeEmploye = this.employe.getCode();
			nomEmploye = this.employe.getNomPrenom();
			totalRembourse = FactoryDAO.getInstance().getTotalRembourse(credit.getId());
			totalCapital = credit.getCapital();
			resteRembourse = credit.getCapital() - totalRembourse;
		}

	}

	public void changeEmploye(ValueChangeEvent event) {
		this.idEmploye = ((Integer) event.getNewValue()).intValue();

	}

	public void changeMontantRembourse() {
		desable = false;
		if (getMontantRembourse() > resteRembourse) {
			HelperC.afficherAttention("ATTENTION",
					"On ne peut pas rembours� le montant sup�rieur au montant restant ! ");
			desable = true;
		}
	}

	public void onRowSelectedCredit(SelectEvent event) {
		this.credit = (CreditC) event.getObject();
		if (this.credit != null) {

			this.numeroDossier = this.credit.getNumeroDossier();
			creditValues();
			PrimeFaces.current().executeScript("PF('dlgCrd').hide();");
		}
	}

	public void onRowSelectedRemboursement(SelectEvent event) {
		this.creditRembourse = (CreditRembourseC) event.getObject();
		if (this.creditRembourse != null) {
			credit = FactoryDAO.getInstance().getCreditEmploye(creditRembourse.getIdCredit());
			if (credit != null)
				creditValues();
			disableMsg = false;
			setId(creditRembourse.getId());
			setDateRemboursement(creditRembourse.getDateRemboursement());
			dateRemboursementS = HelperC.convertDate(getDateRemboursement());
			setMontantRembourse(creditRembourse.getMontantRembourse());
			totalRembourse -= creditRembourse.getMontantRembourse();
			resteRembourse = totalCapital - totalRembourse;

			PrimeFaces.current().executeScript("PF('dlgRmb').hide();");
		}
	}

	public void changeDateRembourse() {
		if (this.dateRemboursementS.replace("/", "").replace("_", "").trim().equals("")) {

			setDateRemboursement(null);
		} else {

			setDateRemboursement(HelperC.validerDate(this.dateRemboursementS));
			if (getDateRemboursement() == null) {

				this.dateRemboursementS = "";
			} else {

				this.dateRemboursementS = HelperC.convertDate(getDateRemboursement());
			}
		}
	}

	private void createHistoric(boolean delete) {
		Historique hist = new Historique();
		hist.setDateOperation(Calendar.getInstance().getTime());
		hist.setOperateur(this.operateur);
		if (getId() == 0) {

			hist.setOperation("Enregistrement Remboursement :Employe=" + codeEmploye + " " + nomEmploye);
		} else {

			if (delete)
				hist.setOperation("Suppression Remboursement :Employe=" + codeEmploye + " " + nomEmploye);
			else
				hist.setOperation("Modification Remboursement :Employe=" + codeEmploye + " " + nomEmploye);
		}
		hist.setTable(Tables.getTableName(Tables.TableName.credit));
		setHistorique(hist);
	}

	public void enregistrer() {
		setExercice(this.exercice);

		if (getId() == 0 && !this.droit.isCreer()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er ");
			return;
		}
		if (getId() > 0 && !this.droit.isModifier()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier ");
			return;
		}
		createHistoric(false);
		if (FactoryDAO.getInstance().insertUpdateRembourssementCredit(this)) {

			HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
			initialiser();
		} else {

			HelperC.afficherAttention("D�sol�", "Echec d'enregistrement");
		}
	}

	public void supprimer() {
		if (!this.droit.isSupprimer()) {

			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
			return;
		}
		createHistoric(true);
		if (this.getId() > 0) {
			if (FactoryDAO.getInstance().deleteRemboursement(this)) {

				HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
				initialiser();
			} else
				HelperC.afficherMessage("D�sol�", "Echec de suppression");
		} else {
			HelperC.afficherDeleteMessage();

		}
	}

	public void initialiser() {
		setId(0);
		setCredit((CreditC) null);
		setMontantCapital(0.0D);
		setMontantRembourse(0.0D);
		this.totalRembourse = 0.0D;
		this.totalCapital = 0.0D;
		this.resteRembourse = 0.0D;
		this.numeroDossier = "";
		this.designationSourceFinancement = "";
		this.designationTypeCredit = "";
		this.datePretS = "";
		this.periode = 0;
		this.tauxInteret = 0.0D;
		this.employe = null;
		this.detailCredit = null;
		this.montantOctroye = 0.0D;
		this.codeRech = "";
		this.nomRech = "";
		this.prenomRech = "";
		this.codeEmploye = "";
		this.nomEmploye = "";
		dateRemboursementS = "";
		desable = false;
		disableMsg = true;
	}
}
