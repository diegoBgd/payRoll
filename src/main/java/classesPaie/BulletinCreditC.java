package classesPaie;

import java.io.Serializable;

public class BulletinCreditC implements Serializable {
	private static final long serialVersionUID = 1191699993152399825L;
	private int id;
	private int idBulletin;
	private String noDossier, libelle;
	private String printMontant;
	private double montantTranche;
	private double tranchAdded;
	private double montant;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBulletin() {
		return this.idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}

	public String getNoDossier() {
		return this.noDossier;
	}

	public void setNoDossier(String noDossier) {
		this.noDossier = noDossier;
	}

	public double getMontantTranche() {
		return this.montantTranche;
	}

	public void setMontantTranche(double montantTranche) {
		this.montantTranche = montantTranche;
	}

	public double getTranchAdded() {
		return this.tranchAdded;
	}

	public void setTranchAdded(double tranchAdded) {
		this.tranchAdded = tranchAdded;
	}

	public double getMontant() {
		this.montant = this.tranchAdded + this.montantTranche;
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getPrintMontant() {
		this.printMontant = HelperC.decimalNumber(getMontant(), 0, true);
		return this.printMontant;
	}

	public void setPrintMontant(String printMontant) {
		this.printMontant = printMontant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
