/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DemandeAvancementGradeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6020445107811640258L;
/*     */   private int id;
/*     */   private int etat;
/*     */   private Date dateDemande;
/*     */   private Historique historique;
/*     */   private String dateDemandeS;
/*     */   private String libelleEtat;
/*     */   private String motif;
/*     */   private Constante.EtatDemandeAvancementGrade etatDemande;
/*     */   private EmployeC employe;
/*     */   private GradePersonnelC gradeDemande;
/*     */   private Base niveauFormation;
/*     */   
/*     */   public int getId() {
/*  35 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  40 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEtat() {
/*  45 */     return this.etat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEtat(int etat) {
/*  50 */     this.etat = etat;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDemande() {
/*  55 */     return this.dateDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDemande(Date dateDemande) {
/*  60 */     this.dateDemande = dateDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  65 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  70 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  75 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  80 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public GradePersonnelC getGradeDemande() {
/*  85 */     return this.gradeDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGradeDemande(GradePersonnelC gradeDemande) {
/*  90 */     this.gradeDemande = gradeDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDemandeS() {
/*  95 */     return this.dateDemandeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDemandeS(String dateDemandeS) {
/* 100 */     this.dateDemandeS = dateDemandeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleEtat() {
/* 105 */     return this.libelleEtat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelleEtat(String libelleEtat) {
/* 110 */     this.libelleEtat = libelleEtat;
/*     */   }
/*     */ 
/*     */   
/*     */   public Constante.EtatDemandeAvancementGrade getEtatDemande() {
/* 115 */     return this.etatDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEtatDemande(Constante.EtatDemandeAvancementGrade etatDemande) {
/* 120 */     this.etatDemande = etatDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getNiveauFormation() {
/* 125 */     return this.niveauFormation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNiveauFormation(Base niveauFormation) {
/* 130 */     this.niveauFormation = niveauFormation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMotif() {
/* 135 */     return this.motif;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMotif(String motif) {
/* 140 */     this.motif = motif;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DemandeAvancementGradeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */