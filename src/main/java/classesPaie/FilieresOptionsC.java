package classesPaie;

import java.io.Serializable;

public class FilieresOptionsC implements Serializable {
	private static final long serialVersionUID = -224980853991299994L;
	private int id;
	private String code;
	private String designation;
	private Historique historique;
	private DepartementSectionC departementSection;

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

	public DepartementSectionC getDepartementSection() {
		return this.departementSection;
	}

	public void setDepartementSection(DepartementSectionC departementSection) {
		this.departementSection = departementSection;
	}
}
