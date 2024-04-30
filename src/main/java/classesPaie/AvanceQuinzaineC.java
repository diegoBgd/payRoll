package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AvanceQuinzaineC implements Serializable {
	private static final long serialVersionUID = -4426935757480190419L;
	private int id;
	private int modePaiement;
	private int moisConcerne;
	private int typeAvance;
	private double montant;
	private String montantString;
	private String numeroCompte;
	private String dateString;
	private String nomEmploye;
	private Date date;
	private EmployeC employe;
	private BanqueC banque;
	private Historique historique;
	private ExerciceC exercice;
	private OperateurC operateur;
	private List<AvanceDetailC> listDetail = new ArrayList<AvanceDetailC>();
	private List<AvanceDetailC> listDeleted = new ArrayList<AvanceDetailC>();
	private int idPaie;

	public String getNomEmploye() {
		return this.nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public ExerciceC getExercice() {
		return this.exercice;
	}

	public void setExercice(ExerciceC exercice) {
		this.exercice = exercice;
	}

	public OperateurC getOperateur() {
		return this.operateur;
	}

	public void setOperateur(OperateurC operateur) {
		this.operateur = operateur;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getMontantString() {
		return this.montantString;
	}

	public void setMontantString(String montantString) {
		this.montantString = montantString;
	}

	public BanqueC getBanque() {
		return this.banque;
	}

	public void setBanque(BanqueC banque) {
		this.banque = banque;
	}

	public int getModePaiement() {
		return this.modePaiement;
	}

	public void setModePaiement(int modePaiement) {
		this.modePaiement = modePaiement;
	}

	public String getNumeroCompte() {
		return this.numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public int getTypeAvance() {
		return this.typeAvance;
	}

	public void setTypeAvance(int typeAvance) {
		this.typeAvance = typeAvance;
	}

	public String getDateString() {
		return this.dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public int getMoisConcerne() {
		return this.moisConcerne;
	}

	public void setMoisConcerne(int moisConcerne) {
		this.moisConcerne = moisConcerne;
	}

	public List<AvanceDetailC> getListDetail() {
		return this.listDetail;
	}

	public void setListDetail(List<AvanceDetailC> listDetail) {
		this.listDetail = listDetail;
	}

	public List<AvanceDetailC> getListDeleted() {
		return this.listDeleted;
	}

	public void setListDeleted(List<AvanceDetailC> listDeleted) {
		this.listDeleted = listDeleted;
	}

	public int getIdPaie() {
		return idPaie;
	}

	public void setIdPaie(int idPaie) {
		this.idPaie = idPaie;
	}

}
