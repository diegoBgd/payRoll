package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisieDemandeCongeC implements Serializable {
	private static final long serialVersionUID = -4453434594007144519L;
	private int id;
	private int etat, decision;
	private EmployeC employe;
	private TypeCongeC typeConge;
	private String motif;
	private String dateDebutS;
	private String dateFinS;
	private String dateAttributionS;
	private String motifRejet;
	private String dateDemandeS;
	private String libelleSorteConge;
	private String dateDecisionS;
	private boolean inLine;
	private String libelleEtatDemandeConge;
	private String libelleDecision;
	private String dateRetourCongeS;
	private String dateDecisionNonRetourS;
	private int duree;
	private Date dateDebut;
	private Date dateFin;
	private Date dateDemande;
	private Date dateRetourConge;
	private Date dateDecision;
	private Historique historique;
	private OperateurC operator;
	private ExerciceC exercise;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public TypeCongeC getTypeConge() {
		return this.typeConge;
	}

	public void setTypeConge(TypeCongeC typeConge) {
		this.typeConge = typeConge;
	}

	public String getMotif() {
		return this.motif;
	}

	public int getDecision() {
		return decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
	}

	public Date getDateDecision() {
		return dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
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

	public String getDateDemandeS() {
		return this.dateDemandeS;
	}

	public void setDateDemandeS(String dateDemandeS) {
		this.dateDemandeS = dateDemandeS;
	}

	public String getLibelleSorteConge() {
		return this.libelleSorteConge;
	}

	public void setLibelleSorteConge(String libelleSorteConge) {
		this.libelleSorteConge = libelleSorteConge;
	}

	public int getDuree() {
		if (getDateDebut() != null && getDateFin() != null)
			duree = (int) HelperC.daysBetween(getDateDebut(), getDateFin());
		return this.duree;
	}

	public void setDuree(int duree) {
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

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public boolean isInLine() {
		return inLine;
	}

	public void setInLine(boolean inLine) {
		this.inLine = inLine;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public OperateurC getOperator() {
		return this.operator;
	}

	public void setOperator(OperateurC operator) {
		this.operator = operator;
	}

	public ExerciceC getExercise() {
		return this.exercise;
	}

	public void setExercise(ExerciceC exercise) {
		this.exercise = exercise;
	}

	public String getMotifRejet() {
		return this.motifRejet;
	}

	public void setMotifRejet(String motifRejet) {
		this.motifRejet = motifRejet;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getLibelleEtatDemandeConge() {
		return this.libelleEtatDemandeConge;
	}

	public String getDateDecisionS() {
		if (getDateDecision() != null)
			dateDecisionS = HelperC.convertDate(getDateDecision());
		return dateDecisionS;
	}

	public void setDateDecisionS(String dateDecisionS) {
		this.dateDecisionS = dateDecisionS;
	}

	public void setLibelleEtatDemandeConge(String libelleEtatDemandeConge) {
		this.libelleEtatDemandeConge = libelleEtatDemandeConge;
	}

	public String getDateRetourCongeS() {
		return this.dateRetourCongeS;
	}

	public String getLibelleDecision() {
		switch (getDecision()) {
		case 1:
			libelleDecision = "Accept�";
			break;

		case 2:
			libelleDecision = "Refus�";
			break;
		}
		return libelleDecision;
	}

	public void setLibelleDecision(String libelleDecision) {
		this.libelleDecision = libelleDecision;
	}

	public void setDateRetourCongeS(String dateRetourCongeS) {
		this.dateRetourCongeS = dateRetourCongeS;
	}

	public Date getDateRetourConge() {
		return this.dateRetourConge;
	}

	public void setDateRetourConge(Date dateRetourConge) {
		this.dateRetourConge = dateRetourConge;
	}

	public String getDateDecisionNonRetourS() {
		return this.dateDecisionNonRetourS;
	}

	public void setDateDecisionNonRetourS(String dateDecisionNonRetourS) {
		this.dateDecisionNonRetourS = dateDecisionNonRetourS;
	}

}
