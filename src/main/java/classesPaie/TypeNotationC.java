package classesPaie;

import java.io.Serializable;

public class TypeNotationC implements Serializable {
	private static final long serialVersionUID = -6759490662542323253L;
	private int id;
	private String code;
	private String designation;
	private String pourcentageMinS;
	private String pourcentageMaxS;
	private String tauxAugmentationS;
	private double pourcentageMin;
	private double pourcentageMax;
	private double tauxAugmentation;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public String getPourcentageMinS() {
		return this.pourcentageMinS;
	}

	public void setPourcentageMinS(String pourcentageMinS) {
		this.pourcentageMinS = pourcentageMinS;
	}

	public String getPourcentageMaxS() {
		return this.pourcentageMaxS;
	}

	public void setPourcentageMaxS(String pourcentageMaxS) {
		this.pourcentageMaxS = pourcentageMaxS;
	}

	public String getTauxAugmentationS() {
		return this.tauxAugmentationS;
	}

	public void setTauxAugmentationS(String tauxAugmentationS) {
		this.tauxAugmentationS = tauxAugmentationS;
	}

	public double getPourcentageMin() {
		return this.pourcentageMin;
	}

	public void setPourcentageMin(double pourcentageMin) {
		this.pourcentageMin = pourcentageMin;
	}

	public double getPourcentageMax() {
		return this.pourcentageMax;
	}

	public void setPourcentageMax(double pourcentageMax) {
		this.pourcentageMax = pourcentageMax;
	}

	public double getTauxAugmentation() {
		return this.tauxAugmentation;
	}

	public void setTauxAugmentation(double tauxAugmentation) {
		this.tauxAugmentation = tauxAugmentation;
	}
}
