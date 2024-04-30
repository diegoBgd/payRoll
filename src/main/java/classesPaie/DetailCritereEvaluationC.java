package classesPaie;

import java.io.Serializable;

public class DetailCritereEvaluationC implements Serializable {
	private static final long serialVersionUID = -1961113854282081175L;
	private int id;
	private int index;
	private double note;
	private String noteS;
	private String designation;
	private CritereEvaluationC critere;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getNote() {
		return this.note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public String getNoteS() {
		return this.noteS;
	}

	public void setNoteS(String noteS) {
		this.noteS = noteS;
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

	public CritereEvaluationC getCritere() {
		return this.critere;
	}

	public void setCritere(CritereEvaluationC critere) {
		this.critere = critere;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
