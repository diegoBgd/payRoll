package classesPaie;

import java.io.Serializable;

public class SaisiePositionDetailIndemniteC implements Serializable {
	private static final long serialVersionUID = 5683316363021446290L;
	private int id;
	private int indexe;
	private double montant;
	private Historique historique;
	private String montantS;
	private SaisiePositionEmployeC saisie;
	private PrimeIndemniteC indemnite;
	private boolean existe;

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

	public PrimeIndemniteC getIndemnite() {
		return this.indemnite;
	}

	public void setIndemnite(PrimeIndemniteC indemnite) {
		this.indemnite = indemnite;
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

	public void changeMontant() {
		try {
			setMontant(Double.valueOf(this.montantS.replace("-", "").replace(" ", "").replace(",", ".").trim())
					.doubleValue());
		} catch (Exception e) {

			setMontant(0.0D);
		}
		this.montantS = HelperC.TraitementMontant.getMontantFormate(getMontant(), 0);
		setMontant(
				Integer.valueOf(this.montantS.replace("-", "").replace(" ", "").replace(",", ".").trim()).intValue());
	}
}
