package classesPaie;

import java.io.Serializable;

public class BulletinDeductionC implements Serializable {
	private static final long serialVersionUID = -7216623808137583037L;
	private int id;
	private int idRetenu;
	private int idBulletin;
	private int indexNum;
	private double montantRetenu;
	private double taux;
	private double base;
	private String codeDeduction;
	private String libelleDeduction;
	private DeductionC deduction;
	private String printMontant;

	public int getIndexNum() {
		return this.indexNum;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
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

	public DeductionC getDeduction() {
		return this.deduction;
	}

	public void setDeduction(DeductionC deduction) {
		this.deduction = deduction;
	}

	public int getIdBulletin() {
		return this.idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRetenu() {
		return this.idRetenu;
	}

	public void setIdRetenu(int idRetenu) {
		this.idRetenu = idRetenu;
	}

	public double getMontantRetenu() {
		return this.montantRetenu;
	}

	public void setMontantRetenu(double montantRetenu) {
		this.montantRetenu = montantRetenu;
	}

	public String getCodeDeduction() {
		return this.codeDeduction;
	}

	public void setCodeDeduction(String codeDeduction) {
		this.codeDeduction = codeDeduction;
	}

	public String getLibelleDeduction() {
		if (this.deduction != null) {
			this.libelleDeduction = this.deduction.getDesignation();
		}
		return this.libelleDeduction;
	}

	public void setLibelleDeduction(String libelleDeduction) {
		this.libelleDeduction = libelleDeduction;
	}

	public String getPrintMontant() {
		this.printMontant = HelperC.decimalNumber(getMontantRetenu(), 0, true);
		return this.printMontant;
	}

	public void setPrintMontant(String printMontant) {
		this.printMontant = printMontant;
	}
}
