package classesPaie;

import java.io.Serializable;

public class ServicesC implements Serializable {
	private static final long serialVersionUID = 1274221364609373764L;
	private int id;
	private String code;
	private String designation;
	private Historique historique;
	private DirectionC direction;

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

	public DirectionC getDirection() {
		return this.direction;
	}

	public void setDirection(DirectionC direction) {
		this.direction = direction;
	}

}
