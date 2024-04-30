package classesPaie;

import java.io.Serializable;

public class DetailPrimeEmployeC implements Serializable {
	private static final long serialVersionUID = 8727764517753702677L;
	private int id;
	private int idParametre;
	private int index;
	private double montant;
	private double taux;
	private String montantS;
	private EmployeC employe;
	private PrimeIndemniteC prime;
	private PrimeIndemniteC indemnite;
	private Historique historique;
	private boolean existe, bloque;
	private boolean calculated;

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

	public PrimeIndemniteC getPrime() {
		return this.prime;
	}

	public void setPrime(PrimeIndemniteC prime) {
		this.prime = prime;
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

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public boolean isCalculated() {
		return this.calculated;
	}

	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}

	public int getIdParametre() {
		return this.idParametre;
	}

	public void setIdParametre(int idParametre) {
		this.idParametre = idParametre;
	}

	public boolean isBloque() {
		return bloque;
	}

	public void setBloque(boolean bloque) {
		this.bloque = bloque;
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

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}
}
