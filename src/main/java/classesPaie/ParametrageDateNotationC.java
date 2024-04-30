package classesPaie;

import java.io.Serializable;

public class ParametrageDateNotationC implements Serializable {
	private static final long serialVersionUID = -6964197087581883937L;
	private int id;
	private int jourDebut;
	private int moisDebut;
	private int jourFin;
	private int moisFin;
	private int dureeRecoursNotation;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJourDebut() {
		return this.jourDebut;
	}

	public void setJourDebut(int jourDebut) {
		this.jourDebut = jourDebut;
	}

	public int getMoisDebut() {
		return this.moisDebut;
	}

	public void setMoisDebut(int moisDebut) {
		this.moisDebut = moisDebut;
	}

	public int getJourFin() {
		return this.jourFin;
	}

	public void setJourFin(int jourFin) {
		this.jourFin = jourFin;
	}

	public int getMoisFin() {
		return this.moisFin;
	}

	public void setMoisFin(int moisFin) {
		this.moisFin = moisFin;
	}

	public int getDureeRecoursNotation() {
		return this.dureeRecoursNotation;
	}

	public void setDureeRecoursNotation(int dureeRecoursNotation) {
		this.dureeRecoursNotation = dureeRecoursNotation;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
