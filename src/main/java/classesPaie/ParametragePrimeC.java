package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametragePrimeC implements Serializable {
	private List<ParametragePrimeDetailC> listDeleted = new ArrayList<ParametragePrimeDetailC>();
	private List<ParametragePrimeDetailC> listDetail = new ArrayList<ParametragePrimeDetailC>();
	private String libelleCategorie;
	private String libelleFonction;
	private String libelleGrade;
	private String libellePrime;
	private String libellePersonnel;
	private Historique hist;
	private boolean calculHeurSup;
	private double plafond;
	private double plancher;
	private double forfait;

	public List<ParametragePrimeDetailC> getListDetail() {
		return this.listDetail;
	}

	private double taux;
	private int priorite;
	private int typeBase;
	private int idPrime;
	private int idFonction;
	private int idGrade;
	private int idCategorie;
	private int idPersonnel;
	private int id;
	private static final long serialVersionUID = 5809956522017435354L;

	public void setListDetail(List<ParametragePrimeDetailC> listDetail) {
		this.listDetail = listDetail;
	}

	public List<ParametragePrimeDetailC> getListDeleted() {
		return this.listDeleted;
	}

	public void setListDeleted(List<ParametragePrimeDetailC> listDeleted) {
		this.listDeleted = listDeleted;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPersonnel() {
		return this.idPersonnel;
	}

	public void setIdPersonnel(int idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public int getIdCategorie() {
		return this.idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getIdGrade() {
		return this.idGrade;
	}

	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}

	public int getIdFonction() {
		return this.idFonction;
	}

	public void setIdFonction(int idFonction) {
		this.idFonction = idFonction;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getForfait() {
		return this.forfait;
	}

	public void setForfait(double forfait) {
		this.forfait = forfait;
	}

	public double getPlancher() {
		return this.plancher;
	}

	public void setPlancher(double plancher) {
		this.plancher = plancher;
	}

	public double getPlafond() {
		return this.plafond;
	}

	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}

	public boolean isCalculHeurSup() {
		return this.calculHeurSup;
	}

	public void setCalculHeurSup(boolean calculHeurSup) {
		this.calculHeurSup = calculHeurSup;
	}

	public Historique getHist() {
		return this.hist;
	}

	public void setHist(Historique hist) {
		this.hist = hist;
	}

	public int getIdPrime() {
		return this.idPrime;
	}

	public void setIdPrime(int idPrime) {
		this.idPrime = idPrime;
	}

	public String getLibellePersonnel() {
		return this.libellePersonnel;
	}

	public void setLibellePersonnel(String libellePersonnel) {
		this.libellePersonnel = libellePersonnel;
	}

	public String getLibellePrime() {
		return this.libellePrime;
	}

	public void setLibellePrime(String libellePrime) {
		this.libellePrime = libellePrime;
	}

	public String getLibelleGrade() {
		return this.libelleGrade;
	}

	public void setLibelleGrade(String libelleGrade) {
		this.libelleGrade = libelleGrade;
	}

	public String getLibelleFonction() {
		return this.libelleFonction;
	}

	public void setLibelleFonction(String libelleFonction) {
		this.libelleFonction = libelleFonction;
	}

	public String getLibelleCategorie() {
		return this.libelleCategorie;
	}

	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	public int getTypeBase() {
		return this.typeBase;
	}

	public void setTypeBase(int typeBase) {
		this.typeBase = typeBase;
	}

	public int getPriorite() {
		return this.priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
}
