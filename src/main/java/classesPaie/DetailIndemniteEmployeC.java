package classesPaie;

import java.io.Serializable;

public class DetailIndemniteEmployeC implements Serializable {
	private static final long serialVersionUID = -4005857446114462398L;
	private int id;
	private double montant;
	private double taux;
	private String montantS;
	private EmployeC employe;
	private PrimeIndemniteC indemnite;
	private Historique historique;
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

	public String getMontantS() {
		return this.montantS;
	}

	public void setMontantS(String montantS) {
		this.montantS = montantS;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public PrimeIndemniteC getIndemnite() {
		return this.indemnite;
	}

	public void setIndemnite(PrimeIndemniteC indemnite) {
		this.indemnite = indemnite;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
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
