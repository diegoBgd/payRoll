/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ public class SaisiePositionEmployeC
/*     */   implements Serializable
/*     */ {
/*  53 */   private String montantPrimeS = "0.0";
/*  54 */   private String montantIndemniteS = "0.0";
/*  55 */   
/*  57 */   private List<SaisiePositionDetailPrimeC> listeDetailPrime = new ArrayList<SaisiePositionDetailPrimeC>();
/*  58 */   private List<SaisiePositionDetailPrimeC> listeDetailPrimeDeleted = new ArrayList<SaisiePositionDetailPrimeC>();
/*  59 */  
/*  60 */   private List<PrimeIndemniteC> listePrime = new ArrayList<PrimeIndemniteC>();
			private static final long serialVersionUID = 5170371432832041954L; 
			private int id; private int etat; 
			private int duree; 
			private Date dateDemandePosition,dateDecision,
						 dateRetour,dateMisApplication; 
			private Date dateDebut; 
			private Date dateFin; 
			private String dateDemandePositionS,libelleDecision;; 
			private String dateDebutS,printDteDecision,dateRetourS,
							dateMisApplicationS; 
			private String dateFinS; 
			private String motifDemande;
/*     */   private String motifRefus;
/*     */   private Historique historique;
/*     */   private double taux,tauxApres,ancienSalaire,salaireApres,nouveauSalaire;
			private boolean ajoutAllocationFamiliale,avancementGrade; 
			private boolean ajoutIndemniteLogement; 
			private boolean ajoutSoinsSante,inLine; 
			private boolean avancementTraitement; 
			private int decision,idPrs,idCtg,idGrd,idFx; 
			private double montantPrime; 
			private double montantIndemnite; 
			private TraitementSalarialC traitement; 
			private PrimeIndemniteC prime; 
			private PrimeIndemniteC indemnite; 
			private EmployeC employe; 
			private ConditionPositionC conditionPosition;
			
			
/*     */   public String getMontantPrimeS() {
/*  65 */     return this.montantPrimeS;
/*     */   }
/*     */   
/*     */   
/*     */   public void setMontantPrimeS(String montantPrimeS) {
/*  70 */     this.montantPrimeS = montantPrimeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantIndemniteS() {
/*  75 */     return this.montantIndemniteS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantIndemniteS(String montantIndemniteS) {
/*  80 */     this.montantIndemniteS = montantIndemniteS;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantPrime() {
/*  85 */     return this.montantPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantPrime(double montantPrime) {
/*  90 */     this.montantPrime = montantPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantIndemnite() {
/*  95 */     return this.montantIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantIndemnite(double montantIndemnite) {
/* 100 */     this.montantIndemnite = montantIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/* 105 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/* 110 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDuree() {
	           if(getDateDebut()!=null && getDateFin()!=null)
	        	   duree=(int)HelperC.daysBetween(getDateDebut(), getDateFin())/30;
/* 115 */     return this.duree;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDuree(int duree) {
/* 120 */     this.duree = duree;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDemandePosition() {
/* 125 */     return this.dateDemandePosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDemandePosition(Date dateDemandePosition) {
/* 130 */     this.dateDemandePosition = dateDemandePosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/* 135 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/* 140 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/* 145 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/* 150 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDemandePositionS() {
/* 155 */     return this.dateDemandePositionS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDemandePositionS(String dateDemandePositionS) {
/* 160 */     this.dateDemandePositionS = dateDemandePositionS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/* 165 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/* 170 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/* 175 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/* 180 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 185 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 190 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/* 195 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 200 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConditionPositionC getConditionPosition() {
/* 205 */     return this.conditionPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConditionPosition(ConditionPositionC conditionPosition) {
/* 210 */     this.conditionPosition = conditionPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEtat() {
/* 215 */     return this.etat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEtat(int etat) {
/* 220 */     this.etat = etat;
/*     */   }
/*     */ 
/*     */   
/*     */   public TraitementSalarialC getTraitement() {
/* 235 */     return this.traitement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTraitement(TraitementSalarialC traitement) {
/* 240 */     this.traitement = traitement;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAjoutAllocationFamiliale() {
/* 245 */     return this.ajoutAllocationFamiliale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAjoutAllocationFamiliale(boolean ajoutAllocationFamiliale) {
/* 250 */     this.ajoutAllocationFamiliale = ajoutAllocationFamiliale;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAjoutIndemniteLogement() {
/* 255 */     return this.ajoutIndemniteLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAjoutIndemniteLogement(boolean ajoutIndemniteLogement) {
/* 260 */     this.ajoutIndemniteLogement = ajoutIndemniteLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAjoutSoinsSante() {
/* 265 */     return this.ajoutSoinsSante;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAjoutSoinsSante(boolean ajoutSoinsSante) {
/* 270 */     this.ajoutSoinsSante = ajoutSoinsSante;
/*     */   }
/*     */   
/*     */   
/*     */   
/*     */   public List<PrimeIndemniteC> getListePrime() {
/* 283 */     return this.listePrime;
/*     */   }
/*     */   
/*     */   public void setListePrime(List<PrimeIndemniteC> listePrime) {
/* 287 */     this.listePrime = listePrime;
/*     */   }
/*     */    
/*     */   public List<SaisiePositionDetailPrimeC> getListeDetailPrime() {
/* 299 */     return this.listeDetailPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListeDetailPrime(List<SaisiePositionDetailPrimeC> listeDetailPrime) {
/* 304 */     this.listeDetailPrime = listeDetailPrime;
/*     */   }
/*     */   
/*     */   public List<SaisiePositionDetailPrimeC> getListeDetailPrimeDeleted() {
/* 308 */     return this.listeDetailPrimeDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListeDetailPrimeDeleted(List<SaisiePositionDetailPrimeC> listeDetailPrimeDeleted) {
/* 313 */     this.listeDetailPrimeDeleted = listeDetailPrimeDeleted;
/*     */   }
/*     */   

/*     */   
/*     */   public PrimeIndemniteC getPrime() {
/* 336 */     return this.prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/* 341 */     this.prime = prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getIndemnite() {
/* 346 */     return this.indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndemnite(PrimeIndemniteC indemnite) {
/* 351 */     this.indemnite = indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMotifDemande() {
/* 356 */     return this.motifDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMotifDemande(String motifDemande) {
/* 361 */     this.motifDemande = motifDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMotifRefus() {
/* 366 */     return this.motifRefus;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMotifRefus(String motifRefus) {
/* 371 */     this.motifRefus = motifRefus;
/*     */   }
/*     */   
/*     */   public boolean isAvancementTraitement() {
/* 375 */     return this.avancementTraitement;
/*     */   }
/*     */   
/*     */   public void setAvancementTraitement(boolean avancementTraitement) {
/* 379 */     this.avancementTraitement = avancementTraitement;
/*     */   }
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
		libelleDecision="Accepté";
		break;

	case 2:
		libelleDecision="Refusé";
		break;
	}
	return libelleDecision;
}
public void setLibelleDecision(String libelleDecision) {
	this.libelleDecision = libelleDecision;
}
public String getPrintDteDecision() {
	if(getDateDecision()!=null)
		printDteDecision=HelperC.convertDate(getDateDebut());
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


/* Location:              G:\PAIE\!\classesPaie\SaisiePositionEmployeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */