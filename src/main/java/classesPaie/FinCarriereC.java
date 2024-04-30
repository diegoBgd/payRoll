package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinCarriereC implements Serializable {
	private static final long serialVersionUID = 8522503876419276120L;
	private int id, dureSalaireTotal;
	private int age;
	private int typeRetraite;
	private Date dateRetraite, dateSalaire;
	private Date dateFin;
	private Historique historique;
	private String dateEvenementS;
	private String dateFinS, printDateRetraite;
	private double montant, montantApres;
	private TraitementSalarialC traitementAvant, traitementApres;
	private PrimeIndemniteC prime;
	private EmployeC employe;

	private List<FinCarriereDetailPrimeC> listeDetailPrime;

	private List<FinCarriereDetailPrimeC> listeDetailPrimeDeleted;

	public FinCarriereC() {
		listeDetailPrime = new ArrayList<FinCarriereDetailPrimeC>();
		listeDetailPrimeDeleted = new ArrayList<FinCarriereDetailPrimeC>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public String getDateEvenementS() {
		return this.dateEvenementS;
	}

	public void setDateEvenementS(String dateEvenementS) {
		this.dateEvenementS = dateEvenementS;
	}

	public String getDateFinS() {
		return this.dateFinS;
	}

	public void setDateFinS(String dateFinS) {
		this.dateFinS = dateFinS;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public int getTypeRetraite() {
		return this.typeRetraite;
	}

	public void setTypeRetraite(int typeRetraite) {
		this.typeRetraite = typeRetraite;
	}

	public PrimeIndemniteC getPrime() {
		return this.prime;
	}

	public void setPrime(PrimeIndemniteC prime) {
		this.prime = prime;
	}

	public List<FinCarriereDetailPrimeC> getListeDetailPrime() {
		return this.listeDetailPrime;
	}

	public void setListeDetailPrime(List<FinCarriereDetailPrimeC> listeDetailPrime) {
		this.listeDetailPrime = listeDetailPrime;
	}

	public List<FinCarriereDetailPrimeC> getListeDetailPrimeDeleted() {
		return this.listeDetailPrimeDeleted;
	}

	public void setListeDetailPrimeDeleted(List<FinCarriereDetailPrimeC> listeDetailPrimeDeleted) {
		this.listeDetailPrimeDeleted = listeDetailPrimeDeleted;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public double getMontantApres() {
		return montantApres;
	}

	public void setMontantApres(double montantApres) {
		this.montantApres = montantApres;
	}

	public int getDureSalaireTotal() {
		return dureSalaireTotal;
	}

	public void setDureSalaireTotal(int dureSalaireTotal) {
		this.dureSalaireTotal = dureSalaireTotal;
	}

	public String getPrintDateRetraite() {
		return printDateRetraite;
	}

	public void setPrintDateRetraite(String printDateRetraite) {
		this.printDateRetraite = printDateRetraite;
	}

	public Date getDateRetraite() {
		return dateRetraite;
	}

	public void setDateRetraite(Date dateRetraite) {
		this.dateRetraite = dateRetraite;
	}

	public TraitementSalarialC getTraitementAvant() {
		return traitementAvant;
	}

	public void setTraitementAvant(TraitementSalarialC traitementAvant) {
		this.traitementAvant = traitementAvant;
	}

	public TraitementSalarialC getTraitementApres() {
		return traitementApres;
	}

	public void setTraitementApres(TraitementSalarialC traitementApres) {
		this.traitementApres = traitementApres;
	}

	public Date getDateSalaire() {
		return dateSalaire;
	}

	public void setDateSalaire(Date dateSalaire) {
		this.dateSalaire = dateSalaire;
	}

}
