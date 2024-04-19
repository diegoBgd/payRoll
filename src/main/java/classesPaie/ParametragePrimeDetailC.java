/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ParametragePrimeDetailC
/*     */   implements Serializable {
/*     */   private static final long serialVersionUID = 4948335288203549633L;
/*     */   private int id;
/*     */   private int idEntete;
/*     */   private boolean added;
/*     */   private boolean disable = true;
/*     */   private double taux;
/*     */   private double forfait;
/*     */   private double plafon;
/*     */   private double plancher;
/*     */   private String codeElement;
/*     */   private String libelle;
/*     */   
/*     */   public int getId() {
/*  20 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  23 */     this.id = id;
/*     */   }
/*     */   public double getTaux() {
/*  26 */     return this.taux;
/*     */   }
/*     */   public void setTaux(double taux) {
/*  29 */     this.taux = taux;
/*     */   }
/*     */   public double getForfait() {
/*  32 */     return this.forfait;
/*     */   }
/*     */   public void setForfait(double forfait) {
/*  35 */     this.forfait = forfait;
/*     */   }
/*     */   public double getPlafon() {
/*  38 */     return this.plafon;
/*     */   }
/*     */   public void setPlafon(double plafon) {
/*  41 */     this.plafon = plafon;
/*     */   }
/*     */   public double getPlancher() {
/*  44 */     return this.plancher;
/*     */   }
/*     */   public void setPlancher(double plancher) {
/*  47 */     this.plancher = plancher;
/*     */   }
/*     */   public boolean isAdded() {
/*  50 */     return this.added;
/*     */   }
/*     */   public void setAdded(boolean added) {
/*  53 */     this.added = added;
/*     */   }
/*     */   public String getCodeElement() {
/*  56 */     return this.codeElement;
/*     */   }
/*     */   public void setCodeElement(String codeElement) {
/*  59 */     this.codeElement = codeElement;
/*     */   }
/*     */   public int getIdEntete() {
/*  62 */     return this.idEntete;
/*     */   }
/*     */   public void setIdEntete(int idEntete) {
/*  65 */     this.idEntete = idEntete;
/*     */   }
/*     */   public String getLibelle() {
/*  68 */     return this.libelle;
/*     */   }
/*     */   public void setLibelle(String libelle) {
/*  71 */     this.libelle = libelle;
/*     */   }
/*     */   public boolean isDisable() {
/*  74 */     return this.disable;
/*     */   }
/*     */   public void setDisable(boolean disable) {
/*  77 */     this.disable = disable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkDetail() {
/*  82 */     if (this.added) {
/*  83 */       this.disable = false;
/*     */     
/*     */     }
/*  86 */     else if (getId() == 0) {
/*  87 */       this.disable = true;
/*  88 */       this.plancher = 0.0D;
/*  89 */       this.plafon = 0.0D;
/*  90 */       this.taux = 0.0D;
/*  91 */       this.forfait = 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeTaux() {
/*  98 */     if (this.taux > 0.0D) {
/*  99 */       this.forfait = 0.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public void changeForfait() {
/* 104 */     if (this.forfait > 0.0D) {
/*     */       
/* 106 */       this.plancher = 0.0D;
/* 107 */       this.plafon = 0.0D;
/* 108 */       this.taux = 0.0D;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\ParametragePrimeDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */