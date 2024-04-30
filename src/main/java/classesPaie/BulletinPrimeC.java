package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class BulletinPrimeC implements Serializable {
	private static final long serialVersionUID = -3908431611077935721L;
	private int id;
	private int priority;
	private int idPrime;
	private int idParametre;
	private int idBulletin;
	private int indexNum;
	private double montantPrime;
	private double montantSalaireBase;
	private double taux;
	private double base;
	private String codePrime;
	private String libellePrime;
	private String printMontant;
	private PrimeIndemniteC primeBulletin;
	private Date datePaie;
	private boolean calculated;

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

	public double getMontantSalaireBase() {
		return this.montantSalaireBase;
	}

	public void setMontantSalaireBase(double montantSalaireBase) {
		this.montantSalaireBase = montantSalaireBase;
	}

	public String getPrintMontant() {
		this.printMontant = HelperC.decimalNumber(getMontantPrime(), 0, true);
		return this.printMontant;
	}

	public void setPrintMontant(String printMontant) {
		this.printMontant = printMontant;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPrime() {
		return this.idPrime;
	}

	public void setIdPrime(int idPrime) {
		this.idPrime = idPrime;
	}

	public double getMontantPrime() {
		return this.montantPrime;
	}

	public void setMontantPrime(double montantPrime) {
		this.montantPrime = montantPrime;
	}

	public String getCodePrime() {
		return this.codePrime;
	}

	public void setCodePrime(String codePrime) {
		this.codePrime = codePrime;
	}

	public String getLibellePrime() {
		if (this.primeBulletin != null) {
			this.libellePrime = this.primeBulletin.getDesignation();
		}
		return this.libellePrime;
	}

	public void setLibellePrime(String libellePrime) {
		this.libellePrime = libellePrime;
	}

	public int getIdBulletin() {
		return this.idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}

	public PrimeIndemniteC getPrimeBulletin() {
		return this.primeBulletin;
	}

	public void setPrimeBulletin(PrimeIndemniteC primeBulletin) {
		this.primeBulletin = primeBulletin;
	}

	public int getIndexNum() {
		return this.indexNum;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}

	public Date getDatePaie() {
		return this.datePaie;
	}

	public void setDatePaie(Date datePaie) {
		this.datePaie = datePaie;
	}

	public boolean isCalculated() {
		return this.calculated;
	}

	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getIdParametre() {
		return this.idParametre;
	}

	public void setIdParametre(int idParametre) {
		this.idParametre = idParametre;
	}
}
