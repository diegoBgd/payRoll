package classesPaie;

import java.io.Serializable;

public class ParametrageBonificationTitreComplementaireC implements Serializable {
	private static final long serialVersionUID = 4340476378254564536L;
	private int id;
	private int dureeFormation;
	private double pourcentage;
	private String pourcentageS;
	private Historique historique;
	private GradePersonnelC grade;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDureeFormation() {
		return this.dureeFormation;
	}

	public void setDureeFormation(int dureeFormation) {
		this.dureeFormation = dureeFormation;
	}

	public double getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public String getPourcentageS() {
		return this.pourcentageS;
	}

	public void setPourcentageS(String pourcentageS) {
		this.pourcentageS = pourcentageS;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public GradePersonnelC getGrade() {
		return this.grade;
	}

	public void setGrade(GradePersonnelC grade) {
		this.grade = grade;
	}
}
