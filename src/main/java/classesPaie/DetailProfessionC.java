package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class DetailProfessionC implements Serializable {
	private static final long serialVersionUID = 8364515305030206476L;
	private int id;
	private Base profession;
	private EmployeC employe;
	private Date dateDebut;
	private Date dateFin;
	private String dateDebutS;
	private String dateFinS;
	private Historique historique;
	private boolean existe;
	private boolean etat;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Base getProfession() {
		return this.profession;
	}

	public void setProfession(Base profession) {
		this.profession = profession;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
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
}
