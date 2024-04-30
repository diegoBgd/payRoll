package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoleC implements Serializable {
	private static final long serialVersionUID = 8989238819289599136L;
	private int id;
	private String code;
	private String designation;
	private Base fonction;
	private List<DroitC> details = new ArrayList<DroitC>();

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

	public Base getFonction() {
		return this.fonction;
	}

	public void setFonction(Base fonction) {
		this.fonction = fonction;
	}

	public List<DroitC> getDetails() {
		return this.details;
	}

	public void setDetails(List<DroitC> details) {
		this.details = details;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
