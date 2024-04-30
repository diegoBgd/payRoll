package classesPaie;

import java.io.Serializable;

public class FinCarriereDetailIndemniteC implements Serializable {
	private static final long serialVersionUID = -8098122230538362578L;
	private int id;
	private int indexe;
	private double montant;
	private Historique historique;
	private String montantS;
	private boolean existe;
	private FinCarriereC entete;
	private PrimeIndemniteC indemnite;

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

	public PrimeIndemniteC getIndemnite() {
		return this.indemnite;
	}

	public void setIndemnite(PrimeIndemniteC indemnite) {
		this.indemnite = indemnite;
	}

	public int getIndexe() {
		return this.indexe;
	}

	public void setIndexe(int indexe) {
		this.indexe = indexe;
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
}
