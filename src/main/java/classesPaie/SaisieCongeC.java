package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisieCongeC implements Serializable {
	private static final long serialVersionUID = 18058660863343923L;
	private int id;
	private int idEmploye;
	private int idConge;
	private double nombreJours;
	private String observation;
	private String dateDebutS;
	private String dateFinS;
	private Date dateDebut = new Date();
	private Date dateFin = new Date();

	private Base conge;

	public Base getConge() {
		return this.conge;
	}

	private EmployeC employe;
	private Historique historique;

	public void setConge(Base conge) {
		this.conge = conge;
	}

	public String getDateDebutS() {
		return this.dateDebutS;
	}

	public void setDateDebutS(String dateDebutS) {
		this.dateDebutS = dateDebutS;
	}

	public String getDateFinS() {
		return this.dateFinS;
	}

	public void setDateFinS(String dateFinS) {
		this.dateFinS = dateFinS;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public int getIdConge() {
		return this.idConge;
	}

	public void setIdConge(int idConge) {
		this.idConge = idConge;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public double getNombreJours() {
		return this.nombreJours;
	}

	public void setNombreJours(double nombreJours) {
		this.nombreJours = nombreJours;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
