package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class PlanningCongeC implements Serializable {
	private static final long serialVersionUID = -1877413862387418376L;
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private String dateDebS;
	private String dateFiS;
	private EmployeC employe;
	private Base typeConge;
	private OperateurC operateurCreation;
	private Historique historic;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public Base getTypeConge() {
		return this.typeConge;
	}

	public void setTypeConge(Base typeConge) {
		this.typeConge = typeConge;
	}

	public OperateurC getOperateurCreation() {
		return this.operateurCreation;
	}

	public void setOperateurCreation(OperateurC operateurCreation) {
		this.operateurCreation = operateurCreation;
	}

	public Historique getHistoric() {
		return this.historic;
	}

	public void setHistoric(Historique historic) {
		this.historic = historic;
	}

	public String getDateDebS() {
		return this.dateDebS;
	}

	public void setDateDebS(String dateDebS) {
		this.dateDebS = dateDebS;
	}

	public String getDateFiS() {
		return this.dateFiS;
	}

	public void setDateFiS(String dateFiS) {
		this.dateFiS = dateFiS;
	}
}
