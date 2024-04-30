package classesPaie;

import java.io.Serializable;

public class MarcheProgrammeC implements Serializable {
	private static final long serialVersionUID = 1058098683753291434L;
	private int id;
	private int heureNormaleEmploye;
	private int heureNormaleJour;
	private int nombreJoursConge;
	private int prochainNumeroDossier;
	private int nombreJoursCommercial;
	private int tauxCourtTerme;
	private int tauxMoyenTerme;
	private int joursSupplementaire;
	private int nombreAnneeJourSuppl;
	private double tauxInteretCredit;
	private double tauxTaxeInteret;
	private double tauxImpotForfaitaire;
	private double tauxSuperieurImpotForfaitaire;
	private double trancheMontantImpotForfaitaire;
	private double allocationMensuelleConjoint;
	private double allocationMensuelleEnfant;
	private double pourcentageLogementRajout;
	private double pourcentageForfaitLogement;
	private double valeurAchat;
	private double tauxIndice;
	private double coefficientMultiplicateur;
	private double baseTAInferieur;
	private double baseTASuperieur;
	private double tauxCongeMaladie;
	private double tauxCongeMaternite;
	private double tauxMinIndemniteLogement;
	private double fraisMedicauxEmploye;
	private double fraisMedicauxCharge;
	private double salaireBaseMaxHeureSuppl;
	private double heurePause;
	private boolean congeMensuel;
	private boolean penaliteConge;
	private boolean presenceJournalier;
	private boolean heureNuitSamedi;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTauxInteretCredit() {
		return this.tauxInteretCredit;
	}

	public void setTauxInteretCredit(double tauxInteretCredit) {
		this.tauxInteretCredit = tauxInteretCredit;
	}

	public double getTauxTaxeInteret() {
		return this.tauxTaxeInteret;
	}

	public void setTauxTaxeInteret(double tauxTaxeInteret) {
		this.tauxTaxeInteret = tauxTaxeInteret;
	}

	public double getTauxImpotForfaitaire() {
		return this.tauxImpotForfaitaire;
	}

	public void setTauxImpotForfaitaire(double tauxImpotForfaitaire) {
		this.tauxImpotForfaitaire = tauxImpotForfaitaire;
	}

	public double getTauxSuperieurImpotForfaitaire() {
		return this.tauxSuperieurImpotForfaitaire;
	}

	public void setTauxSuperieurImpotForfaitaire(double tauxSuperieurImpotForfaitaire) {
		this.tauxSuperieurImpotForfaitaire = tauxSuperieurImpotForfaitaire;
	}

	public double getTrancheMontantImpotForfaitaire() {
		return this.trancheMontantImpotForfaitaire;
	}

	public void setTrancheMontantImpotForfaitaire(double trancheMontantImpotForfaitaire) {
		this.trancheMontantImpotForfaitaire = trancheMontantImpotForfaitaire;
	}

	public int getHeureNormaleEmploye() {
		return this.heureNormaleEmploye;
	}

	public void setHeureNormaleEmploye(int heureNormaleEmploye) {
		this.heureNormaleEmploye = heureNormaleEmploye;
	}

	public int getHeureNormaleJour() {
		return this.heureNormaleJour;
	}

	public void setHeureNormaleJour(int heureNormaleJour) {
		this.heureNormaleJour = heureNormaleJour;
	}

	public int getNombreJoursConge() {
		return this.nombreJoursConge;
	}

	public void setNombreJoursConge(int nombreJoursConge) {
		this.nombreJoursConge = nombreJoursConge;
	}

	public double getAllocationMensuelleConjoint() {
		return this.allocationMensuelleConjoint;
	}

	public void setAllocationMensuelleConjoint(double allocationMensuelleConjoint) {
		this.allocationMensuelleConjoint = allocationMensuelleConjoint;
	}

	public double getAllocationMensuelleEnfant() {
		return this.allocationMensuelleEnfant;
	}

	public void setAllocationMensuelleEnfant(double allocationMensuelleEnfant) {
		this.allocationMensuelleEnfant = allocationMensuelleEnfant;
	}

	public double getPourcentageLogementRajout() {
		return this.pourcentageLogementRajout;
	}

	public void setPourcentageLogementRajout(double pourcentageLogementRajout) {
		this.pourcentageLogementRajout = pourcentageLogementRajout;
	}

	public double getPourcentageForfaitLogement() {
		return this.pourcentageForfaitLogement;
	}

	public void setPourcentageForfaitLogement(double pourcentageForfaitLogement) {
		this.pourcentageForfaitLogement = pourcentageForfaitLogement;
	}

	public double getValeurAchat() {
		return this.valeurAchat;
	}

	public void setValeurAchat(double valeurAchat) {
		this.valeurAchat = valeurAchat;
	}

	public double getTauxIndice() {
		return this.tauxIndice;
	}

	public void setTauxIndice(double tauxIndice) {
		this.tauxIndice = tauxIndice;
	}

	public double getCoefficientMultiplicateur() {
		return this.coefficientMultiplicateur;
	}

	public void setCoefficientMultiplicateur(double coefficientMultiplicateur) {
		this.coefficientMultiplicateur = coefficientMultiplicateur;
	}

	public double getBaseTAInferieur() {
		return this.baseTAInferieur;
	}

	public void setBaseTAInferieur(double baseTAInferieur) {
		this.baseTAInferieur = baseTAInferieur;
	}

	public double getBaseTASuperieur() {
		return this.baseTASuperieur;
	}

	public void setBaseTASuperieur(double baseTASuperieur) {
		this.baseTASuperieur = baseTASuperieur;
	}

	public double getTauxCongeMaladie() {
		return this.tauxCongeMaladie;
	}

	public void setTauxCongeMaladie(double tauxCongeMaladie) {
		this.tauxCongeMaladie = tauxCongeMaladie;
	}

	public double getTauxCongeMaternite() {
		return this.tauxCongeMaternite;
	}

	public void setTauxCongeMaternite(double tauxCongeMaternite) {
		this.tauxCongeMaternite = tauxCongeMaternite;
	}

	public double getTauxMinIndemniteLogement() {
		return this.tauxMinIndemniteLogement;
	}

	public void setTauxMinIndemniteLogement(double tauxMinIndemniteLogement) {
		this.tauxMinIndemniteLogement = tauxMinIndemniteLogement;
	}

	public double getFraisMedicauxEmploye() {
		return this.fraisMedicauxEmploye;
	}

	public void setFraisMedicauxEmploye(double fraisMedicauxEmploye) {
		this.fraisMedicauxEmploye = fraisMedicauxEmploye;
	}

	public double getFraisMedicauxCharge() {
		return this.fraisMedicauxCharge;
	}

	public void setFraisMedicauxCharge(double fraisMedicauxCharge) {
		this.fraisMedicauxCharge = fraisMedicauxCharge;
	}

	public double getSalaireBaseMaxHeureSuppl() {
		return this.salaireBaseMaxHeureSuppl;
	}

	public void setSalaireBaseMaxHeureSuppl(double salaireBaseMaxHeureSuppl) {
		this.salaireBaseMaxHeureSuppl = salaireBaseMaxHeureSuppl;
	}

	public boolean isCongeMensuel() {
		return this.congeMensuel;
	}

	public void setCongeMensuel(boolean congeMensuel) {
		this.congeMensuel = congeMensuel;
	}

	public boolean isPenaliteConge() {
		return this.penaliteConge;
	}

	public void setPenaliteConge(boolean penaliteConge) {
		this.penaliteConge = penaliteConge;
	}

	public boolean isPresenceJournalier() {
		return this.presenceJournalier;
	}

	public void setPresenceJournalier(boolean presenceJournalier) {
		this.presenceJournalier = presenceJournalier;
	}

	public boolean isHeureNuitSamedi() {
		return this.heureNuitSamedi;
	}

	public void setHeureNuitSamedi(boolean heureNuitSamedi) {
		this.heureNuitSamedi = heureNuitSamedi;
	}

	public int getProchainNumeroDossier() {
		return this.prochainNumeroDossier;
	}

	public void setProchainNumeroDossier(int prochainNumeroDossier) {
		this.prochainNumeroDossier = prochainNumeroDossier;
	}

	public int getNombreJoursCommercial() {
		return this.nombreJoursCommercial;
	}

	public void setNombreJoursCommercial(int nombreJoursCommercial) {
		this.nombreJoursCommercial = nombreJoursCommercial;
	}

	public int getTauxCourtTerme() {
		return this.tauxCourtTerme;
	}

	public void setTauxCourtTerme(int tauxCourtTerme) {
		this.tauxCourtTerme = tauxCourtTerme;
	}

	public int getTauxMoyenTerme() {
		return this.tauxMoyenTerme;
	}

	public void setTauxMoyenTerme(int tauxMoyenTerme) {
		this.tauxMoyenTerme = tauxMoyenTerme;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public int getJoursSupplementaire() {
		return this.joursSupplementaire;
	}

	public void setJoursSupplementaire(int joursSupplementaire) {
		this.joursSupplementaire = joursSupplementaire;
	}

	public int getNombreAnneeJourSuppl() {
		return this.nombreAnneeJourSuppl;
	}

	public void setNombreAnneeJourSuppl(int nombreAnneeJourSuppl) {
		this.nombreAnneeJourSuppl = nombreAnneeJourSuppl;
	}

	public double getHeurePause() {
		return this.heurePause;
	}

	public void setHeurePause(double heurePause) {
		this.heurePause = heurePause;
	}
}
