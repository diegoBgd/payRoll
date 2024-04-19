/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class ParametrageDureeEtTraitementPositionC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3003227177233022188L;
/*     */   private int id;
/*     */   private int dureeDemande;
/*     */   private int dureePosition;
/*     */   private int positionDepassementDuree;
/*     */   private int cause;
/*     */   private double tauxTraitementAvantFin;
/*     */   private double tauxTraitementApresFin;
/*     */   private boolean ajoutAllocationFamiliale;
/*     */   private boolean ajoutIndemniteLogement;
/*     */   private boolean ajoutSoinsSante;
/*     */   private boolean avancementTraitement;
/*     */   private Historique historique;
/*  35 */   private List<DetailParametrageTraitementPositionC> listDuree = new ArrayList<DetailParametrageTraitementPositionC>();
/*  36 */   private List<DetailParametrageTraitementPositionC> listDureeDeleted = new ArrayList<DetailParametrageTraitementPositionC>();
/*     */   
/*     */   private ConditionPositionC conditionPosition;
/*     */   
/*     */   public int getId() {
/*  41 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  46 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDureeDemande() {
/*  51 */     return this.dureeDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDureeDemande(int dureeDemande) {
/*  56 */     this.dureeDemande = dureeDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDureePosition() {
/*  61 */     return this.dureePosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDureePosition(int dureePosition) {
/*  66 */     this.dureePosition = dureePosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPositionDepassementDuree() {
/*  71 */     return this.positionDepassementDuree;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPositionDepassementDuree(int positionDepassementDuree) {
/*  76 */     this.positionDepassementDuree = positionDepassementDuree;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCause() {
/*  81 */     return this.cause;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCause(int cause) {
/*  86 */     this.cause = cause;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxTraitementAvantFin() {
/*  91 */     return this.tauxTraitementAvantFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxTraitementAvantFin(double tauxTraitementAvantFin) {
/*  96 */     this.tauxTraitementAvantFin = tauxTraitementAvantFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxTraitementApresFin() {
/* 101 */     return this.tauxTraitementApresFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxTraitementApresFin(double tauxTraitementApresFin) {
/* 106 */     this.tauxTraitementApresFin = tauxTraitementApresFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAjoutAllocationFamiliale() {
/* 111 */     return this.ajoutAllocationFamiliale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAjoutAllocationFamiliale(boolean ajoutAllocationFamiliale) {
/* 116 */     this.ajoutAllocationFamiliale = ajoutAllocationFamiliale;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAjoutIndemniteLogement() {
/* 121 */     return this.ajoutIndemniteLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAjoutIndemniteLogement(boolean ajoutIndemniteLogement) {
/* 126 */     this.ajoutIndemniteLogement = ajoutIndemniteLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAjoutSoinsSante() {
/* 131 */     return this.ajoutSoinsSante;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAjoutSoinsSante(boolean ajoutSoinsSante) {
/* 136 */     this.ajoutSoinsSante = ajoutSoinsSante;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAvancementTraitement() {
/* 141 */     return this.avancementTraitement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAvancementTraitement(boolean avancementTraitement) {
/* 146 */     this.avancementTraitement = avancementTraitement;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 151 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 156 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConditionPositionC getConditionPosition() {
/* 161 */     return this.conditionPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConditionPosition(ConditionPositionC conditionPosition) {
/* 166 */     this.conditionPosition = conditionPosition;
/*     */   }
/*     */   
/*     */   public List<DetailParametrageTraitementPositionC> getListDuree() {
/* 170 */     return this.listDuree;
/*     */   }
/*     */   
/*     */   public void setListDuree(List<DetailParametrageTraitementPositionC> listDuree) {
/* 174 */     this.listDuree = listDuree;
/*     */   }
/*     */   
/*     */   public List<DetailParametrageTraitementPositionC> getListDureeDeleted() {
/* 178 */     return this.listDureeDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDureeDeleted(List<DetailParametrageTraitementPositionC> listDureeDeleted) {
/* 183 */     this.listDureeDeleted = listDureeDeleted;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageDureeEtTraitementPositionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */