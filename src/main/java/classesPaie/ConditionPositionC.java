package classesPaie;

import java.io.Serializable;

public class ConditionPositionC implements Serializable {
	private static final long serialVersionUID = -4143318173055503397L;
	private int id;
	private String condition;
	private String libellePosition;
	private Constante.Position position;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Constante.Position getPosition() {
		return this.position;
	}

	public void setPosition(Constante.Position position) {
		this.position = position;
	}

	public String getLibellePosition() {
		return this.libellePosition;
	}

	public void setLibellePosition(String libellePosition) {
		this.libellePosition = libellePosition;
	}
}
