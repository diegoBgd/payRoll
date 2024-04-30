package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisieDemandePlanCongeC implements Serializable {
	private static final long serialVersionUID = 6328431253063915857L;
	private int id;
	private int etat;
	private String motif;
	private String dateDebutS;
	private String dateFinS;
	private String dateAttributionS;
	private String dureeS;
	private String motifRejet;
	private String libelleDemandePlanning;
	private String libelleSorteConge;
	private double duree;
	private Date dateDebut;
	private Date dateFin;
	private Date dateAttribution;
	private Historique historique;
	private Constante.EtatDemandePlanningConge etatDemandePlanning;
	private Constante.SorteConge sorteConge;
	private EmployeC employe;
	private TypeCongeC typeConge;
	private ParametrageDureeCongeC natureConge;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
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

	public String getDateAttributionS() {
		return this.dateAttributionS;
	}

	public void setDateAttributionS(String dateAttributionS) {
		this.dateAttributionS = dateAttributionS;
	}

	public double getDuree() {
		return this.duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
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

	public Date getDateAttribution() {
		return this.dateAttribution;
	}

	public void setDateAttribution(Date dateAttribution) {
		this.dateAttribution = dateAttribution;
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

	public ParametrageDureeCongeC getNatureConge() {
		return this.natureConge;
	}

	public void setNatureConge(ParametrageDureeCongeC natureConge) {
		this.natureConge = natureConge;
	}

	public TypeCongeC getTypeConge() {
		return this.typeConge;
	}

	public void setTypeConge(TypeCongeC typeConge) {
		this.typeConge = typeConge;
	}

	public String getDureeS() {
		return this.dureeS;
	}

	public void setDureeS(String dureeS) {
		this.dureeS = dureeS;
	}

	public String getLibelleDemandePlanning() {
		return this.libelleDemandePlanning;
	}

	public void setLibelleDemandePlanning(String libelleDemandePlanning) {
		this.libelleDemandePlanning = libelleDemandePlanning;
	}

	public Constante.EtatDemandePlanningConge getEtatDemandePlanning() {
		return this.etatDemandePlanning;
	}

	public void setEtatDemandePlanning(Constante.EtatDemandePlanningConge etatDemandePlanning) {
		this.etatDemandePlanning = etatDemandePlanning;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getLibelleSorteConge() {
		return this.libelleSorteConge;
	}

	public void setLibelleSorteConge(String libelleSorteConge) {
		this.libelleSorteConge = libelleSorteConge;
	}

	public Constante.SorteConge getSorteConge() {
		return this.sorteConge;
	}

	public void setSorteConge(Constante.SorteConge sorteConge) {
		this.sorteConge = sorteConge;
	}

	public String getMotifRejet() {
		return this.motifRejet;
	}

	public void setMotifRejet(String motifRejet) {
		this.motifRejet = motifRejet;
	}
}
