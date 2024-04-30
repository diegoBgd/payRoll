package classesPaie;

import java.io.Serializable;

public class FinCarriereDetailPrimeC implements Serializable {
	private static final long serialVersionUID = 2898858119167601453L;
	private int id, idParm;
	private int indexe;
	private double montant;
	private Historique historique;
	private String libellePrime;
	private boolean existe;
	private FinCarriereC entete;
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

	public FinCarriereC getEntete() {
		return this.entete;
	}

	public void setEntete(FinCarriereC entete) {
		this.entete = entete;
	}

	public PrimeIndemniteC getPrime() {
		return this.prime;
	}

	public void setPrime(PrimeIndemniteC prime) {
		this.prime = prime;
	}

	public int getIndexe() {
		return this.indexe;
	}

	public void setIndexe(int indexe) {
		this.indexe = indexe;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public String getLibellePrime() {
		return libellePrime;
	}

	public void setLibellePrime(String libellePrime) {
		this.libellePrime = libellePrime;
	}

	public int getIdParm() {
		return idParm;
	}

	public void setIdParm(int idParm) {
		this.idParm = idParm;
	}

}
