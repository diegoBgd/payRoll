package classesPaie;

import java.io.Serializable;

public class DetailBanqueEmployeC implements Serializable {
	private static final long serialVersionUID = 1749676701332269245L;
	private int id;
	private int idEmploye;
	private EmployeC employe;
	private BanqueC banque;
	private double pourcentageSalaire;
	private double montant;
	private String numeroCompte;
	private Historique historique;
	private String montantS;
	private boolean existe;
	private boolean principal;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public BanqueC getBanque() {
		return this.banque;
	}

	public void setBanque(BanqueC banque) {
		this.banque = banque;
	}

	public double getPourcentageSalaire() {
		return this.pourcentageSalaire;
	}

	public void setPourcentageSalaire(double pourcentageSalaire) {
		this.pourcentageSalaire = pourcentageSalaire;
	}

	public String getNumeroCompte() {
		return this.numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
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

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
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

	public boolean isPrincipal() {
		return this.principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
}
