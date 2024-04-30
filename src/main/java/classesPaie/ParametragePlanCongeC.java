package classesPaie;

import java.io.Serializable;

public class ParametragePlanCongeC implements Serializable {
	private static final long serialVersionUID = 4744495137381872850L;
	private int id;
	private int nombreEmploye, idExercice;

	private Historique historique;

	private DirectionC direction;
	private ServicesC service;

	private String libelleServie;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNombreEmploye() {
		return this.nombreEmploye;
	}

	public void setNombreEmploye(int nombreEmploye) {
		this.nombreEmploye = nombreEmploye;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public DirectionC getDirection() {
		return this.direction;
	}

	public void setDirection(DirectionC direction) {
		this.direction = direction;
	}

	public ServicesC getService() {
		return this.service;
	}

	public void setService(ServicesC service) {
		this.service = service;
	}

	public String getLibelleServie() {
		return libelleServie;
	}

	public void setLibelleServie(String libelleServie) {
		this.libelleServie = libelleServie;
	}

	public int getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

}
