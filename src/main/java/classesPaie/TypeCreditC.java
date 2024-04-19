/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class TypeCreditC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 265763872270618326L;
/*     */   private int id;
/*     */   private String code;
/*     */   private String libelle;
/*     */   private String terme;
/*     */   private String tauxMinimumString;
/*     */   private String tauxMaximumString;
/*     */   private double tauxMinimum;
/*     */   private double tauxMaximum;
/*     */   private boolean centraliserEcritureEnComptabilite;
/*     */   private boolean affecterDeuxiemeCompteCredit;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  31 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  36 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  41 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  46 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelle() {
/*  51 */     return this.libelle;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelle(String libelle) {
/*  56 */     this.libelle = libelle;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTerme() {
/*  61 */     return this.terme;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTerme(String terme) {
/*  66 */     this.terme = terme;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxMinimum() {
/*  71 */     return this.tauxMinimum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMinimum(double tauxMinimum) {
/*  76 */     this.tauxMinimum = tauxMinimum;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxMaximum() {
/*  81 */     return this.tauxMaximum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMaximum(double tauxMaximum) {
/*  86 */     this.tauxMaximum = tauxMaximum;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxMinimumString() {
/*  91 */     return this.tauxMinimumString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMinimumString(String tauxMinimumString) {
/*  96 */     this.tauxMinimumString = tauxMinimumString;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxMaximumString() {
/* 101 */     return this.tauxMaximumString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMaximumString(String tauxMaximumString) {
/* 106 */     this.tauxMaximumString = tauxMaximumString;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCentraliserEcritureEnComptabilite() {
/* 111 */     return this.centraliserEcritureEnComptabilite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCentraliserEcritureEnComptabilite(boolean centraliserEcritureEnComptabilite) {
/* 116 */     this.centraliserEcritureEnComptabilite = centraliserEcritureEnComptabilite;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAffecterDeuxiemeCompteCredit() {
/* 121 */     return this.affecterDeuxiemeCompteCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAffecterDeuxiemeCompteCredit(boolean affecterDeuxiemeCompteCredit) {
/* 126 */     this.affecterDeuxiemeCompteCredit = affecterDeuxiemeCompteCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 131 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 136 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\TypeCreditC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */