package classesPaie;

import java.io.Serializable;

public class DetailDeductionC implements Serializable {
	private static final long serialVersionUID = -6256376370150051926L;
	private int id;
	private int idBanque;
	private DeductionC deduction;
	private BanqueC banque;
	private EmployeC employe;
	private String montantS;
	private String numeroCpte;
	private double montant;
	private double taux;
	private double base;
	private Historique historique;
	private boolean existe;

	public int getIdBanque() {
		return this.idBanque;
	}

	public void setIdBanque(int idBanque) {
		this.idBanque = idBanque;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DeductionC getDeduction() {
		return this.deduction;
	}

	public void setDeduction(DeductionC deduction) {
		this.deduction = deduction;
	}

	public BanqueC getBanque() {
		return this.banque;
	}

	public void setBanque(BanqueC banque) {
		this.banque = banque;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public String getMontantS() {
		return this.montantS;
	}

	public void setMontantS(String montantS) {
		this.montantS = montantS;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getNumeroCpte() {
		return this.numeroCpte;
	}

	public void setNumeroCpte(String numeroCpte) {
		this.numeroCpte = numeroCpte;
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

	public double getBase() {
		return this.base;
	}

	public void setBase(double base) {
		this.base = base;
	}
}
