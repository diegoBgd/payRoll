package classesPaie;

import java.io.Serializable;

public class ParametrageBonificationAvancementGradeC implements Serializable {
	private static final long serialVersionUID = 4426951655302157259L;
	private int id;
	private double tauxBonification;
	private String tauxBonificationS;
	private Historique historique;
	private GradePersonnelC grade;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTauxBonification() {
		return this.tauxBonification;
	}

	public void setTauxBonification(double tauxBonification) {
		this.tauxBonification = tauxBonification;
	}

	public String getTauxBonificationS() {
		return this.tauxBonificationS;
	}

	public void setTauxBonificationS(String tauxBonificationS) {
		this.tauxBonificationS = tauxBonificationS;
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
