package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditC implements Serializable {
	private Historique historique;
	private ExerciceC exercice;
	private OperateurC operateur;
	private MarcheProgrammeC marche;
	private BanqueC banque;
	private EmployeC employe;

	private List<CreditDetailC> listDeleted = new ArrayList<CreditDetailC>();
	private List<CreditDetailC> listDetail = new ArrayList<CreditDetailC>();

	private double montantCredit, capital;
	private double montantRembourse, reste;
	private Date datePret;
	private String numeroDossier, printDateCrd;
	private String numeroCompte;
	private int frequence;
	private int duree;
	private int id;
	private int idEmploye;

	private static final long serialVersionUID = -8105196239575754642L;

	public EmployeC getEmploye() {
		return employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public double getMontantCredit() {
		return montantCredit;
	}

	public void setMontantCredit(double montantCredit) {
		this.montantCredit = montantCredit;
	}

	public double getMontantRembourse() {
		return montantRembourse;
	}

	public void setMontantRembourse(double montantRembourse) {
		this.montantRembourse = montantRembourse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BanqueC getBanque() {
		return this.banque;
	}

	public void setBanque(BanqueC banque) {
		this.banque = banque;
	}

	public String getNumeroCompte() {
		return this.numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public Date getDatePret() {
		return this.datePret;
	}

	public void setDatePret(Date datePret) {
		this.datePret = datePret;
	}

	public String getNumeroDossier() {
		return this.numeroDossier;
	}

	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	public MarcheProgrammeC getMarche() {
		return this.marche;
	}

	public void setMarche(MarcheProgrammeC marche) {
		this.marche = marche;
	}

	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
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

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public int getFrequence() {
		return this.frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public List<CreditDetailC> getListDetail() {
		return this.listDetail;
	}

	public void setListDetail(List<CreditDetailC> listDetail) {
		this.listDetail = listDetail;
	}

	public List<CreditDetailC> getListDeleted() {
		return this.listDeleted;
	}

	public void setListDeleted(List<CreditDetailC> listDeleted) {
		this.listDeleted = listDeleted;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public double getReste() {
		return reste;
	}

	public void setReste(double reste) {
		this.reste = reste;
	}

	public int getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public String getPrintDateCrd() {
		printDateCrd = HelperC.convertDate(getDatePret());
		return printDateCrd;
	}

	public void setPrintDateCrd(String printDateCrd) {
		this.printDateCrd = printDateCrd;
	}

	public void calculRemboursement() {
		reste = getCapital() - getMontantRembourse();

	}
}
