package classesPaie;

import java.io.Serializable;

public class FaculteInstitutC implements Serializable {
	private static final long serialVersionUID = -1069093849975401360L;
	private int id;
	private String code;
	private String designation;
	private Historique historique;
	private Base campus;

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

	public Base getCampus() {
		return this.campus;
	}

	public void setCampus(Base campus) {
		this.campus = campus;
	}
}
