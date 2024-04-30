package classesPaie;

import java.io.Serializable;

public class DirectionC implements Serializable {
	private static final long serialVersionUID = -5571513694333872045L;
	private int id;
	private String code;
	private String designation;
	private Base organe;
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

	public Base getOrgane() {
		return this.organe;
	}

	public void setOrgane(Base organe) {
		this.organe = organe;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
