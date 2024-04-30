package classesPaie;

import java.io.Serializable;

public class DetailBulletinPaieCotisationC implements Serializable {
	private static final long serialVersionUID = -4089084913068900460L;
	private int id;
	private BulletinPaieC bulletinPaie;
	private ParametreCotisationC retenueCotisation;
	private double tauxSalarial;
	private double tauxPatronal;
	private double partSalarial;
	private double partPatronal;
	private String tauxSalarialString;
	private String tauxPatronalString;
	private String partSalarialString;
	private String partPatronalString;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BulletinPaieC getBulletinPaie() {
		return this.bulletinPaie;
	}

	public void setBulletinPaie(BulletinPaieC bulletinPaie) {
		this.bulletinPaie = bulletinPaie;
	}

	public ParametreCotisationC getRetenueCotisation() {
		return this.retenueCotisation;
	}

	public void setRetenueCotisation(ParametreCotisationC retenueCotisation) {
		this.retenueCotisation = retenueCotisation;
	}

	public double getTauxSalarial() {
		return this.tauxSalarial;
	}

	public void setTauxSalarial(double tauxSalarial) {
		this.tauxSalarial = tauxSalarial;
	}

	public double getTauxPatronal() {
		return this.tauxPatronal;
	}

	public void setTauxPatronal(double tauxPatronal) {
		this.tauxPatronal = tauxPatronal;
	}

	public String getTauxSalarialString() {
		return this.tauxSalarialString;
	}

	public void setTauxSalarialString(String tauxSalarialString) {
		this.tauxSalarialString = tauxSalarialString;
	}

	public String getTauxPatronalString() {
		return this.tauxPatronalString;
	}

	public void setTauxPatronalString(String tauxPatronalString) {
		this.tauxPatronalString = tauxPatronalString;
	}

	public double getPartSalarial() {
		return this.partSalarial;
	}

	public void setPartSalarial(double partSalarial) {
		this.partSalarial = partSalarial;
	}

	public double getPartPatronal() {
		return this.partPatronal;
	}

	public void setPartPatronal(double partPatronal) {
		this.partPatronal = partPatronal;
	}

	public String getPartSalarialString() {
		return this.partSalarialString;
	}

	public void setPartSalarialString(String partSalarialString) {
		this.partSalarialString = partSalarialString;
	}

	public String getPartPatronalString() {
		return this.partPatronalString;
	}

	public void setPartPatronalString(String partPatronalString) {
		this.partPatronalString = partPatronalString;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public void changeTauxSalarial() {
		try {
			this.tauxSalarial = Double.valueOf(this.tauxSalarialString.replace(" ", "").replace(",", ".").trim())
					.doubleValue();

		} catch (Exception e) {

			this.tauxSalarial = 0.0D;
		}
		this.tauxSalarialString = HelperC.TraitementMontant.getMontantFormate(this.tauxSalarial, 0);
		this.tauxSalarial = Double.valueOf(this.tauxSalarialString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();

		this.tauxSalarialString = HelperC.TraitementMontant.getMontantFormate(this.tauxSalarial, 0);
		this.tauxSalarial = Double.valueOf(this.tauxSalarialString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();

		this.tauxSalarialString = HelperC.TraitementMontant.getMontantFormate(this.tauxSalarial, 0);
		this.tauxSalarial = Double.valueOf(this.tauxSalarialString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();
	}

	public void changeTauxPatronal() {
		try {
			this.tauxPatronal = Double.valueOf(this.tauxPatronalString.replace(" ", "").replace(",", ".").trim())
					.doubleValue();

		} catch (Exception e) {

			this.tauxPatronal = 0.0D;
		}
		this.tauxPatronalString = HelperC.TraitementMontant.getMontantFormate(this.tauxPatronal, 0);
		this.tauxPatronal = Double.valueOf(this.tauxPatronalString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();

		this.tauxPatronalString = HelperC.TraitementMontant.getMontantFormate(this.tauxPatronal, 0);
		this.tauxPatronal = Double.valueOf(this.tauxPatronalString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();

		this.tauxPatronalString = HelperC.TraitementMontant.getMontantFormate(this.tauxPatronal, 0);
		this.tauxPatronal = Double.valueOf(this.tauxPatronalString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();
	}

	public void changePartSalarial() {
		try {
			this.partSalarial = Double.valueOf(this.partSalarialString.replace(" ", "").replace(",", ".").trim())
					.doubleValue();

		} catch (Exception e) {

			this.partSalarial = 0.0D;
		}
		this.partSalarialString = HelperC.TraitementMontant.getMontantFormate(this.partSalarial, 0);
		this.partSalarial = Double.valueOf(this.partSalarialString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();

		this.partSalarialString = HelperC.TraitementMontant.getMontantFormate(this.partSalarial, 0);
		this.partSalarial = Double.valueOf(this.partSalarialString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();

		this.partSalarialString = HelperC.TraitementMontant.getMontantFormate(this.partSalarial, 0);
		this.partSalarial = Double.valueOf(this.partSalarialString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();
	}

	public void changePartPatronal() {
		try {
			this.partPatronal = Double.valueOf(this.partPatronalString.replace(" ", "").replace(",", ".").trim())
					.doubleValue();

		} catch (Exception e) {

			this.partPatronal = 0.0D;
		}
		this.partPatronalString = HelperC.TraitementMontant.getMontantFormate(this.partPatronal, 0);
		this.partPatronal = Double.valueOf(this.partPatronalString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();

		this.partPatronalString = HelperC.TraitementMontant.getMontantFormate(this.partPatronal, 0);
		this.partPatronal = Double.valueOf(this.partPatronalString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();

		this.partPatronalString = HelperC.TraitementMontant.getMontantFormate(this.partPatronal, 0);
		this.partPatronal = Double.valueOf(this.partPatronalString.replace(" ", "").replace(",", ".").trim())
				.doubleValue();
	}
}
