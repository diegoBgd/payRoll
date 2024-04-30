package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConditionRecrutementC implements Serializable {
	private static final long serialVersionUID = -6104212712347946217L;
	private int id;
	private String numeroDecision;
	private Date dateDebApplication;
	private Date dateFinApplication;
	private List<ConditionRecrutementDetailC> listDetailCondition = new ArrayList<ConditionRecrutementDetailC>();
	private List<ConditionRecrutementDetailC> listDetailConditionDeleted = new ArrayList<ConditionRecrutementDetailC>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroDecision() {
		return this.numeroDecision;
	}

	public void setNumeroDecision(String numeroDecision) {
		this.numeroDecision = numeroDecision;
	}

	public Date getDateDebApplication() {
		return this.dateDebApplication;
	}

	public void setDateDebApplication(Date dateDebApplication) {
		this.dateDebApplication = dateDebApplication;
	}

	public Date getDateFinApplication() {
		return this.dateFinApplication;
	}

	public void setDateFinApplication(Date dateFinApplication) {
		this.dateFinApplication = dateFinApplication;
	}

	public List<ConditionRecrutementDetailC> getListDetailCondition() {
		return this.listDetailCondition;
	}

	public void setListDetailCondition(List<ConditionRecrutementDetailC> listDetailCondition) {
		this.listDetailCondition = listDetailCondition;
	}

	public List<ConditionRecrutementDetailC> getListDetailConditionDeleted() {
		return this.listDetailConditionDeleted;
	}

	public void setListDetailConditionDeleted(List<ConditionRecrutementDetailC> listDetailConditionDeleted) {
		this.listDetailConditionDeleted = listDetailConditionDeleted;
	}
}
