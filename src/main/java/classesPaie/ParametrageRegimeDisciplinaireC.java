/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParametrageRegimeDisciplinaireC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2011267525550691555L;
/*     */   private int id;
/*     */   private int sanctionsDisciplinaires;
/*     */   private int dureeMin;
/*     */   private int dureeMax;
/*     */   private int dureeRecours;
/*     */   private int dureeCloture;
/*     */   private int actionDepassementDureeCloture;
/*     */   private double tauxTraitementRetenue;
/*     */   private String tauxTraitementRetenueS;
/*     */   private String libelleSanction;
/*     */   private Historique historique;
/*     */   private Constante.SanctionsDisciplinaires sanction;
/*     */   
/*     */   public int getId() {
/*  25 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  29 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getSanctionsDisciplinaires() {
/*  33 */     return this.sanctionsDisciplinaires;
/*     */   }
/*     */   
/*     */   public void setSanctionsDisciplinaires(int sanctionsDisciplinaires) {
/*  37 */     this.sanctionsDisciplinaires = sanctionsDisciplinaires;
/*     */   }
/*     */   
/*     */   public int getDureeMin() {
/*  41 */     return this.dureeMin;
/*     */   }
/*     */   
/*     */   public void setDureeMin(int dureeMin) {
/*  45 */     this.dureeMin = dureeMin;
/*     */   }
/*     */   
/*     */   public int getDureeMax() {
/*  49 */     return this.dureeMax;
/*     */   }
/*     */   
/*     */   public void setDureeMax(int dureeMax) {
/*  53 */     this.dureeMax = dureeMax;
/*     */   }
/*     */   
/*     */   public int getDureeRecours() {
/*  57 */     return this.dureeRecours;
/*     */   }
/*     */   
/*     */   public void setDureeRecours(int dureeRecours) {
/*  61 */     this.dureeRecours = dureeRecours;
/*     */   }
/*     */   
/*     */   public int getDureeCloture() {
/*  65 */     return this.dureeCloture;
/*     */   }
/*     */   
/*     */   public void setDureeCloture(int dureeCloture) {
/*  69 */     this.dureeCloture = dureeCloture;
/*     */   }
/*     */   
/*     */   public int getActionDepassementDureeCloture() {
/*  73 */     return this.actionDepassementDureeCloture;
/*     */   }
/*     */   
/*     */   public void setActionDepassementDureeCloture(int actionDepassementDureeCloture) {
/*  77 */     this.actionDepassementDureeCloture = actionDepassementDureeCloture;
/*     */   }
/*     */   
/*     */   public double getTauxTraitementRetenue() {
/*  81 */     return this.tauxTraitementRetenue;
/*     */   }
/*     */   
/*     */   public void setTauxTraitementRetenue(double tauxTraitementRetenue) {
/*  85 */     this.tauxTraitementRetenue = tauxTraitementRetenue;
/*     */   }
/*     */   
/*     */   public String getTauxTraitementRetenueS() {
/*  89 */     return this.tauxTraitementRetenueS;
/*     */   }
/*     */   
/*     */   public void setTauxTraitementRetenueS(String tauxTraitementRetenueS) {
/*  93 */     this.tauxTraitementRetenueS = tauxTraitementRetenueS;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/*  97 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 101 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public Constante.SanctionsDisciplinaires getSanction() {
/* 105 */     return this.sanction;
/*     */   }
/*     */   
/*     */   public void setSanction(Constante.SanctionsDisciplinaires sanction) {
/* 109 */     this.sanction = sanction;
/*     */   }
/*     */   
/*     */   public String getLibelleSanction() {
/* 113 */     return this.libelleSanction;
/*     */   }
/*     */   
/*     */   public void setLibelleSanction(String libelleSanction) {
/* 117 */     this.libelleSanction = libelleSanction;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageRegimeDisciplinaireC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */