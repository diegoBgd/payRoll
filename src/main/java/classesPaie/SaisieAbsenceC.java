package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisieAbsenceC implements Serializable {
	private static final long serialVersionUID = 2123801105427480157L;
	private int id;
	private int idExercice;
	private int duree;
	private int moisPaie;
	private String observation;
	private String dateS;
	private Date date = new Date();
	private boolean retenuPaie;
	private Base absence;

	public int getId() {
		return this.id;
	}

	private EmployeC employe;
	private Historique historique;

	public void setId(int id) {
		this.id = id;
	}

	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getDateS() {
		return this.dateS;
	}

	public void setDateS(String dateS) {
		this.dateS = dateS;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Base getAbsence() {
		return this.absence;
	}

	public void setAbsence(Base absence) {
		this.absence = absence;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public boolean isRetenuPaie() {
		return retenuPaie;
	}

	public void setRetenuPaie(boolean retenuPaie) {
		this.retenuPaie = retenuPaie;
	}

	public int getMoisPaie() {
		return this.moisPaie;
	}

	public void setMoisPaie(int moisPaie) {
		this.moisPaie = moisPaie;
	}

	public int getIdExercice() {
		return this.idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}
}
