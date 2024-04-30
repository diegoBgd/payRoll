package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class TraitementSalarialC implements Serializable {
	private static final long serialVersionUID = 1665864970233221058L;
	private int id, idRef;
	private int typeAvancement;
	private double salaireBase;
	private double pourcentage;
	private double ancienSalaire;
	private Date dateDebut;
	private Date dateFin;
	private String dateDebutS;
	private String salaireBaseS;
	private String pourcentageS;
	private String ancienSalaireS, comment;
	private Historique historique;
	private EmployeC employe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeAvancement() {
		return this.typeAvancement;
	}

	public void setTypeAvancement(int typeAvancement) {
		this.typeAvancement = typeAvancement;
	}

	public double getSalaireBase() {
		return this.salaireBase;
	}

	public void setSalaireBase(double salaireBase) {
		this.salaireBase = salaireBase;
	}

	public double getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateDebutS() {
		return this.dateDebutS;
	}

	public void setDateDebutS(String dateDebutS) {
		this.dateDebutS = dateDebutS;
	}

	public String getSalaireBaseS() {
		return this.salaireBaseS;
	}

	public void setSalaireBaseS(String salaireBaseS) {
		this.salaireBaseS = salaireBaseS;
	}

	public String getPourcentageS() {
		return this.pourcentageS;
	}

	public void setPourcentageS(String pourcentageS) {
		this.pourcentageS = pourcentageS;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public double getAncienSalaire() {
		return this.ancienSalaire;
	}

	public void setAncienSalaire(double ancienSalaire) {
		this.ancienSalaire = ancienSalaire;
	}

	public String getAncienSalaireS() {
		return this.ancienSalaireS;
	}

	public void setAncienSalaireS(String ancienSalaireS) {
		this.ancienSalaireS = ancienSalaireS;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getIdRef() {
		return idRef;
	}

	public void setIdRef(int idRef) {
		this.idRef = idRef;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
