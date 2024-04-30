package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisieDemandeSortieC implements Serializable {
	private static final long serialVersionUID = 2877639710624920386L;
	private int id, idExercice;
	private int etatSortie, decision;
	private long nombreMin;
	private String heureDepart;
	private String heureRetour;
	private String motifSortie;
	private String dateDemandeS;
	private String dateValidationS;
	private String dateSortieS;
	private String motifRefusSortie;
	private String libelleEtatSortie, libelleDecision;
	private Date dateDemande;
	private Date dateValidation;
	private Date dateSortie;
	private boolean imputableAuxPresences, inLine;
	private Historique historique;
	private Constante.EtatDemandeSortie etatDemandeSortie;
	private EmployeC employe;
	private OperateurC decideur;

	public int getId() {
		return this.id;
	}

	public int getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

	public int getDecision() {
		return decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getNombreMin() {
		return this.nombreMin;
	}

	public void setNombreMin(long nombreMin) {
		this.nombreMin = nombreMin;
	}

	public String getHeureDepart() {
		return this.heureDepart;
	}

	public void setHeureDepart(String heureDepart) {
		this.heureDepart = heureDepart;
	}

	public String getHeureRetour() {
		return this.heureRetour;
	}

	public void setHeureRetour(String heureRetour) {
		this.heureRetour = heureRetour;
	}

	public String getMotifSortie() {
		return this.motifSortie;
	}

	public void setMotifSortie(String motifSortie) {
		this.motifSortie = motifSortie;
	}

	public String getDateDemandeS() {
		return this.dateDemandeS;
	}

	public void setDateDemandeS(String dateDemandeS) {
		this.dateDemandeS = dateDemandeS;
	}

	public String getDateValidationS() {
		return this.dateValidationS;
	}

	public void setDateValidationS(String dateValidationS) {
		this.dateValidationS = dateValidationS;
	}

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}

	public boolean isImputableAuxPresences() {
		return this.imputableAuxPresences;
	}

	public void setImputableAuxPresences(boolean imputableAuxPresences) {
		this.imputableAuxPresences = imputableAuxPresences;
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

	public OperateurC getDecideur() {
		return this.decideur;
	}

	public void setDecideur(OperateurC decideur) {
		this.decideur = decideur;
	}

	public int getEtatSortie() {
		return this.etatSortie;
	}

	public void setEtatSortie(int etatSortie) {
		this.etatSortie = etatSortie;
	}

	public Constante.EtatDemandeSortie getEtatDemandeSortie() {
		return this.etatDemandeSortie;
	}

	public void setEtatDemandeSortie(Constante.EtatDemandeSortie etatDemandeSortie) {
		this.etatDemandeSortie = etatDemandeSortie;
	}

	public String getDateSortieS() {
		return this.dateSortieS;
	}

	public void setDateSortieS(String dateSortieS) {
		this.dateSortieS = dateSortieS;
	}

	public Date getDateSortie() {
		return this.dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public String getMotifRefusSortie() {
		return this.motifRefusSortie;
	}

	public void setMotifRefusSortie(String motifRefusSortie) {
		this.motifRefusSortie = motifRefusSortie;
	}

	public String getLibelleEtatSortie() {
		return this.libelleEtatSortie;
	}

	public void setLibelleEtatSortie(String libelleEtatSortie) {
		this.libelleEtatSortie = libelleEtatSortie;
	}

	public boolean isInLine() {
		return inLine;
	}

	public void setInLine(boolean inLine) {
		this.inLine = inLine;
	}

	public String getLibelleDecision() {
		switch (getDecision()) {
		case 1:
			setLibelleDecision("Accpté");
			break;

		case 2:
			setLibelleDecision("Refusé");
			break;
		}
		return libelleDecision;
	}

	public void setLibelleDecision(String libelleDecision) {
		this.libelleDecision = libelleDecision;
	}

}
