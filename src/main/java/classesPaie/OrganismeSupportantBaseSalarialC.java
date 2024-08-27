package classesPaie;

import java.io.Serializable;

public class OrganismeSupportantBaseSalarialC implements Serializable {
	private static final long serialVersionUID = -7015928892045967341L;
	private int id;
	private String code;
	private String designation;
	private String compteCptbl;

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

	public String getCompteCptbl() {
		return compteCptbl;
	}

	public void setCompteCptbl(String compteCptbl) {
		this.compteCptbl = compteCptbl;
	}

	
	
}
