package classesPaie;

import java.io.Serializable;

public class ParametrageAllocationFamillialeC implements Serializable {
	private static final long serialVersionUID = 124794847895351487L;
	private int id;
	private int ageMaximal;
	private int ageReportMaximal;
	private int statutPersonnel;
	private double montantAllocation;
	private String statutPersonnelS;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAgeMaximal() {
		return this.ageMaximal;
	}

	public void setAgeMaximal(int ageMaximal) {
		this.ageMaximal = ageMaximal;
	}

	public int getAgeReportMaximal() {
		return this.ageReportMaximal;
	}

	public void setAgeReportMaximal(int ageReportMaximal) {
		this.ageReportMaximal = ageReportMaximal;
	}

	public double getMontantAllocation() {
		return this.montantAllocation;
	}

	public void setMontantAllocation(double montantAllocation) {
		this.montantAllocation = montantAllocation;
	}

	public int getStatutPersonnel() {
		return this.statutPersonnel;
	}

	public void setStatutPersonnel(int statutPersonnel) {
		this.statutPersonnel = statutPersonnel;
	}

	public String getStatutPersonnelS() {
		return this.statutPersonnelS;
	}

	public void setStatutPersonnelS(String statutPersonnelS) {
		this.statutPersonnelS = statutPersonnelS;
	}
}
