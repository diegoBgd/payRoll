package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class HeuresPrestees implements Serializable {
	private static final long serialVersionUID = -961611621728285318L;
	private double pourcent;
	private Date datePrestation;
	private String temps;
	private String numeroHs;
	private int id;
	private int heure;
	private int minute;
	private int sec;
	private int idEmploye;
	private int mois;
	private int idExercice;

	public int getIdExercice() {
		return this.idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

	public double getPourcent() {
		return this.pourcent;
	}

	public void setPourcent(double pourcent) {
		this.pourcent = pourcent;
	}

	public String getTemps() {
		return this.temps;
	}

	public void setTemps(String temps) {
		this.temps = temps;
	}

	public Date getDatePrestation() {
		return this.datePrestation;
	}

	public void setDatePrestation(Date datePrestation) {
		this.datePrestation = datePrestation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHeure() {
		return this.heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public int getMinute() {
		return this.minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSec() {
		return this.sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public int getMois() {
		return this.mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public String getNumeroHs() {
		return this.numeroHs;
	}

	public void setNumeroHs(String numeroHs) {
		this.numeroHs = numeroHs;
	}

	public void onHourChange() {
	}

	public void onMinuteChange() {
		if (this.minute >= 60) {

			this.heure++;
			this.minute -= 60;
		}
	}

	public void onSecondchange() {
		if (this.sec >= 60) {

			this.minute++;
			this.sec -= 60;
			onMinuteChange();
		}
	}
}
