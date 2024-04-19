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
/*     */ public class DetailPrimeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5338145590315134394L;
/*     */   private int id;
/*     */   private int anciennetteMin;
/*     */   private int anciennetteMax;
/*     */   private int index;
/*     */   private double plancher;
/*     */   private double plafond;
/*     */   private double taux;
/*     */   private double nombreMoisSalaire;
/*     */   private String plancherS;
/*     */   private String plafondS;
/*     */   private String tauxS;
/*     */   private String nombreMoisSalaireS;
/*     */   private PrimeIndemniteC prime;
/*     */   private boolean existe;
/*     */   
/*     */   public int getId() {
/*  34 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  39 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnciennetteMin() {
/*  44 */     return this.anciennetteMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnciennetteMin(int anciennetteMin) {
/*  49 */     this.anciennetteMin = anciennetteMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnciennetteMax() {
/*  54 */     return this.anciennetteMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnciennetteMax(int anciennetteMax) {
/*  59 */     this.anciennetteMax = anciennetteMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPlancher() {
/*  64 */     return this.plancher;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlancher(double plancher) {
/*  69 */     this.plancher = plancher;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPlafond() {
/*  74 */     return this.plafond;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlafond(double plafond) {
/*  79 */     this.plafond = plafond;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTaux() {
/*  84 */     return this.taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTaux(double taux) {
/*  89 */     this.taux = taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPlancherS() {
/*  94 */     return this.plancherS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlancherS(String plancherS) {
/*  99 */     this.plancherS = plancherS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPlafondS() {
/* 104 */     return this.plafondS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlafondS(String plafondS) {
/* 109 */     this.plafondS = plafondS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxS() {
/* 114 */     return this.tauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxS(String tauxS) {
/* 119 */     this.tauxS = tauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getPrime() {
/* 124 */     return this.prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/* 129 */     this.prime = prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNombreMoisSalaire() {
/* 134 */     return this.nombreMoisSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreMoisSalaire(double nombreMoisSalaire) {
/* 139 */     this.nombreMoisSalaire = nombreMoisSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNombreMoisSalaireS() {
/* 144 */     return this.nombreMoisSalaireS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreMoisSalaireS(String nombreMoisSalaireS) {
/* 149 */     this.nombreMoisSalaireS = nombreMoisSalaireS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/* 154 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/* 159 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 164 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 169 */     this.index = index;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailPrimeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */