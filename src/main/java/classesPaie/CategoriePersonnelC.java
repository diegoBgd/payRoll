package classesPaie;

import java.io.Serializable;

public class CategoriePersonnelC implements Serializable {
	private static final long serialVersionUID = 5280309449245943005L;
	private int id;
	private String code;
	private String designation;
	private Base personnel;

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

	public Base getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Base personnel) {
		this.personnel = personnel;
	}
}
