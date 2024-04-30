package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class FichePresenceC implements Serializable {
	private static final long serialVersionUID = -3071418887960181690L;
	private int id;
	private int idTypeHeurePreste;
	private double totalheureTravail;
	private String heureEntreee;
	private String heureSortie;
	private Date datePointage;
	private ExerciceC exercise;
	private EmployeC employe;
	private Historique historic;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTypeHeurePreste() {
		return this.idTypeHeurePreste;
	}

	public void setIdTypeHeurePreste(int idTypeHeurePreste) {
		this.idTypeHeurePreste = idTypeHeurePreste;
	}

	public double getTotalheureTravail() {
		return this.totalheureTravail;
	}

	public void setTotalheureTravail(double totalheureTravail) {
		this.totalheureTravail = totalheureTravail;
	}

	public String getHeureEntreee() {
		return this.heureEntreee;
	}

	public void setHeureEntreee(String heureEntreee) {
		this.heureEntreee = heureEntreee;
	}

	public String getHeureSortie() {
		return this.heureSortie;
	}

	public void setHeureSortie(String heureSortie) {
		this.heureSortie = heureSortie;
	}

	public ExerciceC getExercise() {
		return this.exercise;
	}

	public void setExercise(ExerciceC exercise) {
		this.exercise = exercise;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public Historique getHistoric() {
		return this.historic;
	}

	public void setHistoric(Historique historic) {
		this.historic = historic;
	}

	public Date getDatePointage() {
		return this.datePointage;
	}

	public void setDatePointage(Date datePointage) {
		this.datePointage = datePointage;
	}
}
