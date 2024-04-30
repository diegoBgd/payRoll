package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class DetailNiveauFormationC implements Serializable {
	private static final long serialVersionUID = 5555752774471306048L;
	private int id;
	private EmployeC employe;
	private Base niveau;
	private Date dateDebut;
	private Date dateFin;
	private String dateDebutS;
	private String dateFinS;
	private boolean etat;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
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

	public boolean isEtat() {
		return this.etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public Base getNiveau() {
		return this.niveau;
	}

	public void setNiveau(Base niveau) {
		this.niveau = niveau;
	}
}
