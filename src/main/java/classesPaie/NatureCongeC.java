package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class NatureCongeC implements Serializable {
	private static final long serialVersionUID = -4192532924603418202L;
	private int id;
	private int nombreJoursAnnuel;
	private int nombreJoursAjoutes;
	private int nombreAnneesAjoutJour;
	private int dureeMax;
	private int joursConge;
	private String code;
	private String designation;
	private String libelleJoursConge;
	private String numReferenceDecision;
	private Date dateDecision;
	private Date dateFinDecision;
	private Historique historique;
	private TypeCongeC type;
	private Base personnel;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNombreJoursAnnuel() {
		return this.nombreJoursAnnuel;
	}

	public void setNombreJoursAnnuel(int nombreJoursAnnuel) {
		this.nombreJoursAnnuel = nombreJoursAnnuel;
	}

	public int getNombreJoursAjoutes() {
		return this.nombreJoursAjoutes;
	}

	public void setNombreJoursAjoutes(int nombreJoursAjoutes) {
		this.nombreJoursAjoutes = nombreJoursAjoutes;
	}

	public int getNombreAnneesAjoutJour() {
		return this.nombreAnneesAjoutJour;
	}

	public void setNombreAnneesAjoutJour(int nombreAnneesAjoutJour) {
		this.nombreAnneesAjoutJour = nombreAnneesAjoutJour;
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

	public int getDureeMax() {
		return this.dureeMax;
	}

	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}

	public int getJoursConge() {
		return this.joursConge;
	}

	public void setJoursConge(int joursConge) {
		this.joursConge = joursConge;
	}

	public TypeCongeC getType() {
		return this.type;
	}

	public void setType(TypeCongeC type) {
		this.type = type;
	}

	public String getLibelleJoursConge() {
		return this.libelleJoursConge;
	}

	public void setLibelleJoursConge(String libelleJoursConge) {
		this.libelleJoursConge = libelleJoursConge;
	}

	public Base getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Base personnel) {
		this.personnel = personnel;
	}

	public String getNumReferenceDecision() {
		return this.numReferenceDecision;
	}

	public void setNumReferenceDecision(String numReferenceDecision) {
		this.numReferenceDecision = numReferenceDecision;
	}

	public Date getDateDecision() {
		return this.dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	public Date getDateFinDecision() {
		return this.dateFinDecision;
	}

	public void setDateFinDecision(Date dateFinDecision) {
		this.dateFinDecision = dateFinDecision;
	}
}
