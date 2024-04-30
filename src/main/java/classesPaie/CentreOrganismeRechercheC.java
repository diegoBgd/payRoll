package classesPaie;

import java.io.Serializable;

public class CentreOrganismeRechercheC implements Serializable {
	private static final long serialVersionUID = -1139667665648814076L;
	private int id;
	private String code;
	private String designation;
	private Historique historique;
	private Base campus;
	private FaculteInstitutC faculteInstitut;

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

	public FaculteInstitutC getFaculteInstitut() {
		return this.faculteInstitut;
	}

	public void setFaculteInstitut(FaculteInstitutC faculteInstitut) {
		this.faculteInstitut = faculteInstitut;
	}
}
