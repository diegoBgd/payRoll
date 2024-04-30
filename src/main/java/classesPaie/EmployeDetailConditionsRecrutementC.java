package classesPaie;

import java.io.Serializable;

public class EmployeDetailConditionsRecrutementC implements Serializable {
	private static final long serialVersionUID = 2739627766095516065L;
	private int id;
	private boolean remplie;
	private EmployeC entete;
	private ConditionRecrutementDetailC condition;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isRemplie() {
		return this.remplie;
	}

	public void setRemplie(boolean remplie) {
		this.remplie = remplie;
	}

	public ConditionRecrutementDetailC getCondition() {
		return this.condition;
	}

	public void setCondition(ConditionRecrutementDetailC condition) {
		this.condition = condition;
	}

	public EmployeC getEntete() {
		return this.entete;
	}

	public void setEntete(EmployeC entete) {
		this.entete = entete;
	}
}
