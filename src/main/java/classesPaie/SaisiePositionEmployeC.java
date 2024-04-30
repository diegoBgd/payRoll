package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaisiePositionEmployeC implements Serializable {
	private String montantPrimeS = "0.0";
	private String montantIndemniteS = "0.0";

	private List<SaisiePositionDetailPrimeC> listeDetailPrime = new ArrayList<SaisiePositionDetailPrimeC>();
	private List<SaisiePositionDetailPrimeC> listeDetailPrimeDeleted = new ArrayList<SaisiePositionDetailPrimeC>();

	private List<PrimeIndemniteC> listePrime = new ArrayList<PrimeIndemniteC>();
	private static final long serialVersionUID = 5170371432832041954L;
	private int id;
	private int etat;
	private int duree;
	private Date dateDemandePosition, dateDecision, dateRetour, dateMisApplication;
	private Date dateDebut;
	private Date dateFin;
	private String dateDemandePositionS, libelleDecision;;
	private String dateDebutS, printDteDecision, dateRetourS, dateMisApplicationS;
	private String dateFinS;
	private String motifDemande;
	private String motifRefus;
	private Historique historique;
	private double taux, tauxApres, ancienSalaire, salaireApres, nouveauSalaire;
	private boolean ajoutAllocationFamiliale, avancementGrade;
	private boolean ajoutIndemniteLogement;
	private boolean ajoutSoinsSante, inLine;
	private boolean avancementTraitement;
	private int decision, idPrs, idCtg, idGrd, idFx;
	private double montantPrime;
	private double montantIndemnite;
	private TraitementSalarialC traitement;
	private PrimeIndemniteC prime;
	private PrimeIndemniteC indemnite;
	private EmployeC employe;
	private ConditionPositionC conditionPosition;

	public String getMontantPrimeS() {
		return this.montantPrimeS;
	}

	public void setMontantPrimeS(String montantPrimeS) {
		this.montantPrimeS = montantPrimeS;
	}

	public String getMontantIndemniteS() {
		return this.montantIndemniteS;
	}

	public void setMontantIndemniteS(String montantIndemniteS) {
		this.montantIndemniteS = montantIndemniteS;
	}

	public double getMontantPrime() {
		return this.montantPrime;
	}

	public void setMontantPrime(double montantPrime) {
		this.montantPrime = montantPrime;
	}

	public double getMontantIndemnite() {
		return this.montantIndemnite;
	}

	public void setMontantIndemnite(double montantIndemnite) {
		this.montantIndemnite = montantIndemnite;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuree() {
		if (getDateDebut() != null && getDateFin() != null)
			duree = (int) HelperC.daysBetween(getDateDebut(), getDateFin()) / 30;
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Date getDateDemandePosition() {
		return this.dateDemandePosition;
	}

	public void setDateDemandePosition(Date dateDemandePosition) {
		this.dateDemandePosition = dateDemandePosition;
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

	public String getDateDemandePositionS() {
		return this.dateDemandePositionS;
	}

	public void setDateDemandePositionS(String dateDemandePositionS) {
		this.dateDemandePositionS = dateDemandePositionS;
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

	public ConditionPositionC getConditionPosition() {
		return this.conditionPosition;
	}

	public void setConditionPosition(ConditionPositionC conditionPosition) {
		this.conditionPosition = conditionPosition;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public TraitementSalarialC getTraitement() {
		return this.traitement;
	}

	public void setTraitement(TraitementSalarialC traitement) {
		this.traitement = traitement;
	}

	public boolean isAjoutAllocationFamiliale() {
		return this.ajoutAllocationFamiliale;
	}

	public void setAjoutAllocationFamiliale(boolean ajoutAllocationFamiliale) {
		this.ajoutAllocationFamiliale = ajoutAllocationFamiliale;
	}

	public boolean isAjoutIndemniteLogement() {
		return this.ajoutIndemniteLogement;
	}

	public void setAjoutIndemniteLogement(boolean ajoutIndemniteLogement) {
		this.ajoutIndemniteLogement = ajoutIndemniteLogement;
	}

	public boolean isAjoutSoinsSante() {
		return this.ajoutSoinsSante;
	}

	public void setAjoutSoinsSante(boolean ajoutSoinsSante) {
		this.ajoutSoinsSante = ajoutSoinsSante;
	}

	public List<PrimeIndemniteC> getListePrime() {
		return this.listePrime;
	}

	public void setListePrime(List<PrimeIndemniteC> listePrime) {
		this.listePrime = listePrime;
	}

	public List<SaisiePositionDetailPrimeC> getListeDetailPrime() {
		return this.listeDetailPrime;
	}

	public void setListeDetailPrime(List<SaisiePositionDetailPrimeC> listeDetailPrime) {
		this.listeDetailPrime = listeDetailPrime;
	}

	public List<SaisiePositionDetailPrimeC> getListeDetailPrimeDeleted() {
		return this.listeDetailPrimeDeleted;
	}

	public void setListeDetailPrimeDeleted(List<SaisiePositionDetailPrimeC> listeDetailPrimeDeleted) {
		this.listeDetailPrimeDeleted = listeDetailPrimeDeleted;
	}

	public PrimeIndemniteC getPrime() {
		return this.prime;
	}

	public void setPrime(PrimeIndemniteC prime) {
		this.prime = prime;
	}

	public PrimeIndemniteC getIndemnite() {
		return this.indemnite;
	}

	public void setIndemnite(PrimeIndemniteC indemnite) {
		this.indemnite = indemnite;
	}

	public String getMotifDemande() {
		return this.motifDemande;
	}

	public void setMotifDemande(String motifDemande) {
		this.motifDemande = motifDemande;
	}

	public String getMotifRefus() {
		return this.motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	public boolean isAvancementTraitement() {
		return this.avancementTraitement;
	}

	public void setAvancementTraitement(boolean avancementTraitement) {
		this.avancementTraitement = avancementTraitement;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getTauxApres() {
		return tauxApres;
	}

	public void setTauxApres(double tauxApres) {
		this.tauxApres = tauxApres;
	}

	public double getAncienSalaire() {
		return ancienSalaire;
	}

	public void setAncienSalaire(double ancienSalaire) {
		this.ancienSalaire = ancienSalaire;
	}

	public double getSalaireApres() {
		return salaireApres;
	}

	public void setSalaireApres(double salaireApres) {
		this.salaireApres = salaireApres;
	}

	public double getNouveauSalaire() {
		return nouveauSalaire;
	}

	public void setNouveauSalaire(double nouveauSalaire) {
		this.nouveauSalaire = nouveauSalaire;
	}

	public boolean isAvancementGrade() {
		return avancementGrade;
	}

	public void setAvancementGrade(boolean avancementGrade) {
		this.avancementGrade = avancementGrade;
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

	public boolean isInLine() {
		return inLine;
	}

	public void setInLine(boolean inLine) {
		this.inLine = inLine;
	}

	public String getLibelleDecision() {
		switch (decision) {
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

	public String getPrintDteDecision() {
		if (getDateDecision() != null)
			printDteDecision = HelperC.convertDate(getDateDebut());
		return printDteDecision;
	}

	public void setPrintDteDecision(String printDteDecision) {
		this.printDteDecision = printDteDecision;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Date getDateMisApplication() {
		return dateMisApplication;
	}

	public void setDateMisApplication(Date dateMisApplication) {
		this.dateMisApplication = dateMisApplication;
	}

	public String getDateRetourS() {
		return dateRetourS;
	}

	public void setDateRetourS(String dateRetourS) {
		this.dateRetourS = dateRetourS;
	}

	public String getDateMisApplicationS() {
		return dateMisApplicationS;
	}

	public void setDateMisApplicationS(String dateMisApplicationS) {
		this.dateMisApplicationS = dateMisApplicationS;
	}

	public int getIdPrs() {
		return idPrs;
	}

	public void setIdPrs(int idPrs) {
		this.idPrs = idPrs;
	}

	public int getIdCtg() {
		return idCtg;
	}

	public void setIdCtg(int idCtg) {
		this.idCtg = idCtg;
	}

	public int getIdGrd() {
		return idGrd;
	}

	public void setIdGrd(int idGrd) {
		this.idGrd = idGrd;
	}

	public int getIdFx() {
		return idFx;
	}

	public void setIdFx(int idFx) {
		this.idFx = idFx;
	}

}
