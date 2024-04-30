package classesPaie;

import java.io.Serializable;

public class TypeCongeC implements Serializable {
	private static final long serialVersionUID = -5149893284486573347L;
	private int id;
	private int sorteConge, nombreJour;
	private String code;
	private String designation;
	private Historique historique;
	private boolean annuel;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSorteConge() {
		return this.sorteConge;
	}

	public void setSorteConge(int sorteConge) {
		this.sorteConge = sorteConge;
	}

	public boolean isAnnuel() {
		return this.annuel;
	}

	public void setAnnuel(boolean annuel) {
		this.annuel = annuel;
	}

	public int getNombreJour() {
		return nombreJour;
	}

	public void setNombreJour(int nombreJour) {
		this.nombreJour = nombreJour;
	}

}
