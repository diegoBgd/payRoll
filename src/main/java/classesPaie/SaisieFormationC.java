package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisieFormationC implements Serializable {
	private static final long serialVersionUID = -7431517431793550065L;
	private int id;
	private int idEmploye;
	private int idFormation;
	private String observation;
	private String dateDebutS;
	private String dateFinS;
	private Date dateDebut = new Date();
	private Date dateFin = new Date();

	private Base formation;

	public int getId() {
		return this.id;
	}

	private EmployeC employe;
	private Historique historique;

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public int getIdFormation() {
		return this.idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getDateDebutS() {
		return this.dateDebutS;
	}

	public void setDateDebutS(String dateDebutS) {
		this.dateDebutS = dateDebutS;
	}

	public String getDateFinS() {
		return this.dateFinS;
	}

	public void setDateFinS(String dateFinS) {
		this.dateFinS = dateFinS;
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

	public Base getFormation() {
		return this.formation;
	}

	public void setFormation(Base formation) {
		this.formation = formation;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
