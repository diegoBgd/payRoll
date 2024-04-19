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
/*     */ public class BulletinCotisationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6042503218530329798L;
/*     */   private int id;
/*     */   private int priority;
/*     */   private int typeCotisation;
/*     */   private int idCotisation;
/*     */   private int idBulletin;
/*     */   private int indexNum;
/*     */   private double montantCotisation;
/*     */   private double montantPatronal;
/*     */   private double montantBase;
/*     */   private double tauxSalarial;
/*     */   private double tauxPatronal;
/*     */   private String codeCotisation;
/*     */   private String printSalarial;
/*     */   private String printPatronal;
/*     */   private String libelleCotisation;
/*     */   private CotisationC cotisation;
/*     */   private boolean calculated;
/*     */   
/*     */   public int getIndexNum() {
/*  34 */     return this.indexNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexNum(int indexNum) {
/*  39 */     this.indexNum = indexNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public CotisationC getCotisation() {
/*  44 */     return this.cotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCotisation(CotisationC cotisation) {
/*  49 */     this.cotisation = cotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdBulletin() {
/*  54 */     return this.idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdBulletin(int idBulletin) {
/*  59 */     this.idBulletin = idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  64 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  69 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdCotisation() {
/*  74 */     return this.idCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdCotisation(int idCotisation) {
/*  79 */     this.idCotisation = idCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantCotisation() {
/*  84 */     return this.montantCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantCotisation(double montantCotisation) {
/*  89 */     this.montantCotisation = montantCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeCotisation() {
/*  94 */     return this.codeCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeCotisation(String codeCotisation) {
/*  99 */     this.codeCotisation = codeCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleCotisation() {
/* 104 */     if (this.cotisation != null)
/*     */     {
/* 106 */       this.libelleCotisation = this.cotisation.getDesignation();
/*     */     }
/* 108 */     return this.libelleCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelleCotisation(String libelleCotisation) {
/* 113 */     this.libelleCotisation = libelleCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantPatronal() {
/* 118 */     return this.montantPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantPatronal(double montantPatronal) {
/* 123 */     this.montantPatronal = montantPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantBase() {
/* 128 */     return this.montantBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantBase(double montantBase) {
/* 133 */     this.montantBase = montantBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxSalarial() {
/* 138 */     return this.tauxSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxSalarial(double tauxSalarial) {
/* 143 */     this.tauxSalarial = tauxSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxPatronal() {
/* 148 */     return this.tauxPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxPatronal(double tauxPatronal) {
/* 153 */     this.tauxPatronal = tauxPatronal;
/*     */   }
/*     */   
/*     */   public boolean isCalculated() {
/* 157 */     return this.calculated;
/*     */   }
/*     */   
/*     */   public void setCalculated(boolean calculated) {
/* 161 */     this.calculated = calculated;
/*     */   }
/*     */   
/*     */   public int getPriority() {
/* 165 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setPriority(int priority) {
/* 169 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrintSalarial() {
/* 174 */     this.printSalarial = HelperC.decimalNumber(getMontantCotisation(), 0, true);
/* 175 */     return this.printSalarial;
/*     */   }
/*     */   
/*     */   public void setPrintSalarial(String printSalarial) {
/* 179 */     this.printSalarial = printSalarial;
/*     */   }
/*     */   
/*     */   public String getPrintPatronal() {
/* 183 */     this.printPatronal = HelperC.decimalNumber(getMontantPatronal(), 0, true);
/* 184 */     return this.printPatronal;
/*     */   }
/*     */   
/*     */   public void setPrintPatronal(String printPatronal) {
/* 188 */     this.printPatronal = printPatronal;
/*     */   }
/*     */   
/*     */   public int getTypeCotisation() {
/* 192 */     return this.typeCotisation;
/*     */   }
/*     */   
/*     */   public void setTypeCotisation(int typeCotisation) {
/* 196 */     this.typeCotisation = typeCotisation;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\BulletinCotisationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */