package classesPaie;

import java.io.Serializable;

public class OrganismeSupportantBaseSalarialC implements Serializable {
	private static final long serialVersionUID = -7015928892045967341L;
	private int id;
	private String code;
	private String designation;
	private String compteBase;
	private Historique historique;

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

	public String getCompteBase() {
		return this.compteBase;
	}

	public void setCompteBase(String compteBase) {
		this.compteBase = compteBase;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
