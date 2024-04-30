package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisiePresenceC implements Serializable {
	private static final long serialVersionUID = 2877475969362762521L;
	private int id;
	private int duree;
	private Date date;
	private EmployeC employe;
	private Base departement;
	private String dateS;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public Base getDepartement() {
		return this.departement;
	}

	public void setDepartement(Base departement) {
		this.departement = departement;
	}

	public String getDateS() {
		return this.dateS;
	}

	public void setDateS(String dateS) {
		this.dateS = dateS;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
