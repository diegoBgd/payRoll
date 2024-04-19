package classesPaie;

import java.io.Serializable;

public class BulletinComissionC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1933259106393182920L;
	private int id,idBulletin,idComission;
	private CotisationC comission;
	private String libelleComission;
	private String printMontant;
	private double taux,montant;
	private boolean calculated;
	
	public BulletinComissionC() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBulletin() {
		return idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}


	public int getIdComission() {
		return idComission;
	}

	public void setIdComission(int idComission) {
		this.idComission = idComission;
	}

	public String getLibelleComission() {
		libelleComission=getComission().getDesignation();
		return libelleComission;
	}

	public void setLibelleComission(String libelleComission) {
		this.libelleComission = libelleComission;
	}

	public String getPrintMontant() {
		printMontant=HelperC.decimalNumber(getMontant(), 0, true);
		return printMontant;
	}

	public void setPrintMontant(String printMontant) {
	
		this.printMontant = printMontant;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public CotisationC getComission() {
		return comission;
	}

	public void setComission(CotisationC comission) {
		this.comission = comission;
	}

	public boolean isCalculated() {
		return calculated;
	}

	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}
	
	
}
