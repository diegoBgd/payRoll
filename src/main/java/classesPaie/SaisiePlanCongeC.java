package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisiePlanCongeC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5799535481329630324L;
	private int id,idEmploye,idExercice,idTypeConge,dureeConge;
	private Date dateDebut,dateFin;
	private String dateDebutS,dateFinS;
	private EmployeC employe;
	public SaisiePlanCongeC() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public int getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

	public int getIdTypeConge() {
		return idTypeConge;
	}

	public void setIdTypeConge(int idTypeConge) {
		this.idTypeConge = idTypeConge;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDateDebutS() {
		return dateDebutS;
	}

	public void setDateDebutS(String dateDebutS) {
		this.dateDebutS = dateDebutS;
	}

	public String getDateFinS() {
		return dateFinS;
	}

	public void setDateFinS(String dateFinS) {
		this.dateFinS = dateFinS;
	}

	
	public int getDureeConge() {
		if(getDateDebut()!=null && getDateFin()!=null)
			dureeConge=(int)HelperC.daysBetween(getDateDebut(), getDateFin());
		return dureeConge;
	}

	public void setDureeConge(int dureeConge) {
		this.dureeConge = dureeConge;
	}

	public EmployeC getEmploye() {
		return employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

}
