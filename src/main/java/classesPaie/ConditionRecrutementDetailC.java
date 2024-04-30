package classesPaie;

import java.io.Serializable;

public class ConditionRecrutementDetailC implements Serializable {
	private static final long serialVersionUID = 6642149213942091301L;
	private int id;
	private boolean rempli;
	private Base personnel;
	private Base critereRecrutement;
	private ConditionRecrutementC entete;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isRempli() {
		return this.rempli;
	}

	public void setRempli(boolean rempli) {
		this.rempli = rempli;
	}

	public Base getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Base personnel) {
		this.personnel = personnel;
	}

	public Base getCritereRecrutement() {
		return this.critereRecrutement;
	}

	public void setCritereRecrutement(Base critereRecrutement) {
		this.critereRecrutement = critereRecrutement;
	}

	public ConditionRecrutementC getEntete() {
		return this.entete;
	}

	public void setEntete(ConditionRecrutementC entete) {
		this.entete = entete;
	}
}
