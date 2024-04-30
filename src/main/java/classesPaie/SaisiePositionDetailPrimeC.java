package classesPaie;

import java.io.Serializable;

public class SaisiePositionDetailPrimeC implements Serializable {
	private static final long serialVersionUID = 7495182057595167337L;
	private int id, idParm;
	private int indexe;
	private double montant;
	private String montantS;
	private Historique historique;
	private boolean existe, kept;
	private SaisiePositionEmployeC saisie;
	private PrimeIndemniteC prime;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public SaisiePositionEmployeC getSaisie() {
		return this.saisie;
	}

	public void setSaisie(SaisiePositionEmployeC saisie) {
		this.saisie = saisie;
	}

	public PrimeIndemniteC getPrime() {
		return this.prime;
	}

	public void setPrime(PrimeIndemniteC prime) {
		this.prime = prime;
	}

	public String getMontantS() {
		return this.montantS;
	}

	public void setMontantS(String montantS) {
		this.montantS = montantS;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getIndexe() {
		return this.indexe;
	}

	public void setIndexe(int indexe) {
		this.indexe = indexe;
	}

	public int getIdParm() {
		return idParm;
	}

	public void setIdParm(int idParm) {
		this.idParm = idParm;
	}

	public boolean isKept() {
		return kept;
	}

	public void setKept(boolean kept) {
		this.kept = kept;
	}

	public void checkPrime() {

	}
}
