/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DemandeProlongationRetraiteC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4201050548222125830L;
/*     */   private int id;
/*     */   private int age,ageMin,ageMax;
/*     */   private int ageRetraiteDemande;
/*     */   private int avisConseilAdministration;
/*     */   private int etat;
/*     */   private String motifDemande;
/*     */   private String dateDemandeS;
/*     */   private String dateDecisionS;
/*     */   private String libelleProlongation;
/*     */   private String libelleDecision;
/*     */   private Constante.EtatDemandeProlongationRetraite etatProlongation;
/*     */   private Date dateDemande;
/*     */   private Date dateDecision;
/*     */   private int decision;
/*     */   private Historique historique;
/*     */   private EmployeC employe;
/*     */   private boolean inLine;
/*     */   public int getId() {
/*  32 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  36 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getAge() {
/*  40 */     return this.age;
/*     */   }
/*     */   
/*     */   public void setAge(int age) {
/*  44 */     this.age = age;
/*     */   }
/*     */   
/*     */   public int getAgeRetraiteDemande() {
/*  48 */     return this.ageRetraiteDemande;
/*     */   }
/*     */   
/*     */   public void setAgeRetraiteDemande(int ageRetraiteDemande) {
/*  52 */     this.ageRetraiteDemande = ageRetraiteDemande;
/*     */   }
/*     */   
/*     */   public int getAvisConseilAdministration() {
/*  56 */     return this.avisConseilAdministration;
/*     */   }
/*     */   
/*     */   public void setAvisConseilAdministration(int avisConseilAdministration) {
/*  60 */     this.avisConseilAdministration = avisConseilAdministration;
/*     */   }
/*     */   
/*     */   public String getMotifDemande() {
/*  64 */     return this.motifDemande;
/*     */   }
/*     */   
/*     */   public void setMotifDemande(String motifDemande) {
/*  68 */     this.motifDemande = motifDemande;
/*     */   }
/*     */   
/*     */   public Date getDateDemande() {
/*  72 */     return this.dateDemande;
/*     */   }
/*     */   
/*     */   public void setDateDemande(Date dateDemande) {
/*  76 */     this.dateDemande = dateDemande;
/*     */   }
/*     */   
/*     */   public int getDecision() {
/*  80 */     return this.decision;
/*     */   }
/*     */   
/*     */   public void setDecision(int decision) {
/*  84 */     this.decision = decision;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/*  88 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  92 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public EmployeC getEmploye() {
/*  96 */     return this.employe;
/*     */   }
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 100 */     this.employe = employe;
/*     */   }
/*     */   
/*     */   public String getDateDemandeS() {
/* 104 */     return this.dateDemandeS;
/*     */   }
/*     */   
/*     */   public void setDateDemandeS(String dateDemandeS) {
/* 108 */     this.dateDemandeS = dateDemandeS;
/*     */   }
/*     */   
/*     */   public String getDateDecisionS() {
	           
/* 112 */     return this.dateDecisionS;
/*     */   }
/*     */   
/*     */   public void setDateDecisionS(String dateDecisionS) {
/* 116 */     this.dateDecisionS = dateDecisionS;
/*     */   }
/*     */   
/*     */   public Date getDateDecision() {
/* 120 */     return this.dateDecision;
/*     */   }
/*     */   
/*     */   public void setDateDecision(Date dateDecision) {
/* 124 */     this.dateDecision = dateDecision;
/*     */   }
/*     */   
/*     */   public int getEtat() {
/* 128 */     return this.etat;
/*     */   }
/*     */   
/*     */   public void setEtat(int etat) {
/* 132 */     this.etat = etat;
/*     */   }
/*     */   public String getLibelleProlongation() {
/* 135 */     return this.libelleProlongation;
/*     */   }
/*     */   
/*     */   public void setLibelleProlongation(String libelleProlongation) {
/* 139 */     this.libelleProlongation = libelleProlongation;
/*     */   }
/*     */   
/*     */   public Constante.EtatDemandeProlongationRetraite getEtatProlongation() {
/* 143 */     return this.etatProlongation;
/*     */   }
/*     */   
/*     */   public void setEtatProlongation(Constante.EtatDemandeProlongationRetraite etatProlongation) {
/* 147 */     this.etatProlongation = etatProlongation;
/*     */   }
/*     */   
/*     */   public String getLibelleDecision() {
	          switch (getDecision()) {
			case 1:
				libelleDecision="Accepté";
				break;

			case 2:
				libelleDecision="Refusé";
				break;
			}
/* 151 */     return  libelleDecision;
/*     */   }
/*     */   
/*     */   public void setLibelleDecision(String libelleDecision) {
/* 155 */     this.libelleDecision = libelleDecision;
/*     */   }
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

