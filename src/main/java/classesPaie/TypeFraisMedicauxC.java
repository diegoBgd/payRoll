package classesPaie;

public class TypeFraisMedicauxC extends Base {
	private static final long serialVersionUID = -481670971769809016L;
	private int pourcentage;
	private double montant;
	private String pourcentageS;
	private String montantS;

	public int getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getPourcentageS() {
		return this.pourcentageS;
	}

	public void setPourcentageS(String pourcentageS) {
		this.pourcentageS = pourcentageS;
	}

	public String getMontantS() {
		return this.montantS;
	}

	public void setMontantS(String montantS) {
		this.montantS = montantS;
	}
}
