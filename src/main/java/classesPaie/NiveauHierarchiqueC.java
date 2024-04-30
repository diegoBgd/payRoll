package classesPaie;

import java.io.Serializable;

public class NiveauHierarchiqueC implements Serializable {
	private static final long serialVersionUID = -7021278099806345456L;
	private int id;
	private int niveau;
	private String code;
	private String designation;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
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
}
