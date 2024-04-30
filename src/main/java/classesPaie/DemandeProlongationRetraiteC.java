package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class DemandeProlongationRetraiteC implements Serializable {
	private static final long serialVersionUID = -4201050548222125830L;
	private int id;
	private int age, ageMin, ageMax;
	private int ageRetraiteDemande;
	private int avisConseilAdministration;
	private int etat;
	private String motifDemande;
	private String dateDemandeS;
	private String dateDecisionS;
	private String libelleProlongation;
	private String libelleDecision;
	private Constante.EtatDemandeProlongationRetraite etatProlongation;
	private Date dateDemande;
	private Date dateDecision;
	private int decision;
	private Historique historique;
	private EmployeC employe;
	private boolean inLine;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAgeRetraiteDemande() {
		return this.ageRetraiteDemande;
	}

	public void setAgeRetraiteDemande(int ageRetraiteDemande) {
		this.ageRetraiteDemande = ageRetraiteDemande;
	}

	public int getAvisConseilAdministration() {
		return this.avisConseilAdministration;
	}

	public void setAvisConseilAdministration(int avisConseilAdministration) {
		this.avisConseilAdministration = avisConseilAdministration;
	}

	public String getMotifDemande() {
		return this.motifDemande;
	}

	public void setMotifDemande(String motifDemande) {
		this.motifDemande = motifDemande;
	}

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public int getDecision() {
		return this.decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
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

	public String getDateDemandeS() {
		return this.dateDemandeS;
	}

	public void setDateDemandeS(String dateDemandeS) {
		this.dateDemandeS = dateDemandeS;
	}

	public String getDateDecisionS() {

		return this.dateDecisionS;
	}

	public void setDateDecisionS(String dateDecisionS) {
		this.dateDecisionS = dateDecisionS;
	}

	public Date getDateDecision() {
		return this.dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getLibelleProlongation() {
		return this.libelleProlongation;
	}

	public void setLibelleProlongation(String libelleProlongation) {
		this.libelleProlongation = libelleProlongation;
	}

	public Constante.EtatDemandeProlongationRetraite getEtatProlongation() {
		return this.etatProlongation;
	}

	public void setEtatProlongation(Constante.EtatDemandeProlongationRetraite etatProlongation) {
		this.etatProlongation = etatProlongation;
	}

	public String getLibelleDecision() {
		switch (getDecision()) {
		case 1:
			libelleDecision = "Accepté";
			break;

		case 2:
			libelleDecision = "Refusé";
			break;
		}
		return libelleDecision;
	}

	public void setLibelleDecision(String libelleDecision) {
		this.libelleDecision = libelleDecision;
	}

	public int getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}

	public int getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(int ageMax) {
		this.ageMax = ageMax;
	}

	public boolean isInLine() {
		return inLine;
	}

	public void setInLine(boolean inLine) {
		this.inLine = inLine;
	}

}
