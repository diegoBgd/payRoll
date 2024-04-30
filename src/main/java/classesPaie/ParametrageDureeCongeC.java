package classesPaie;

import java.io.Serializable;

public class ParametrageDureeCongeC implements Serializable {
	private static final long serialVersionUID = -4088278207128196381L;
	private int id;
	private int nombreJoursAnnuel;
	private int nombreJoursAjoutes;
	private int nombreAnneesAjoutJour;

	private int joursConge;

	private String libelleJoursConge;
	private String libelleSorteConge;
	private Historique historique;
	private TypeCongeC type;
	private Base personnel;
	private Constante.SorteConge sorteConge;

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

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
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

	public Constante.SorteConge getSorteConge() {
		return this.sorteConge;
	}

	public void setSorteConge(Constante.SorteConge sorteConge) {
		this.sorteConge = sorteConge;
	}

	public String getLibelleSorteConge() {
		return this.libelleSorteConge;
	}

	public void setLibelleSorteConge(String libelleSorteConge) {
		this.libelleSorteConge = libelleSorteConge;
	}

}
