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
/*     */ public class BulletinPrimeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3908431611077935721L;
/*     */   private int id;
/*     */   private int priority;
/*     */   private int idPrime;
/*     */   private int idParametre;
/*     */   private int idBulletin;
/*     */   private int indexNum;
/*     */   private double montantPrime;
/*     */   private double montantSalaireBase;
/*     */   private double taux;
/*     */   private double base;
/*     */   private String codePrime;
/*     */   private String libellePrime;
/*     */   private String printMontant;
/*     */   private PrimeIndemniteC primeBulletin;
/*     */   private Date datePaie;
/*     */   private boolean calculated;
/*     */   
/*     */   public double getTaux() {
/*  35 */     return this.taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTaux(double taux) {
/*  40 */     this.taux = taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBase() {
/*  45 */     return this.base;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBase(double base) {
/*  50 */     this.base = base;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantSalaireBase() {
/*  55 */     return this.montantSalaireBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantSalaireBase(double montantSalaireBase) {
/*  60 */     this.montantSalaireBase = montantSalaireBase;
/*     */   }
/*     */   
/*     */   public String getPrintMontant() {
/*  64 */     this.printMontant = HelperC.decimalNumber(getMontantPrime(), 0, true);
/*  65 */     return this.printMontant;
/*     */   }
/*     */   
/*     */   public void setPrintMontant(String printMontant) {
/*  69 */     this.printMontant = printMontant;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  74 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  79 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdPrime() {
/*  84 */     return this.idPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdPrime(int idPrime) {
/*  89 */     this.idPrime = idPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantPrime() {
/*  94 */     return this.montantPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantPrime(double montantPrime) {
/*  99 */     this.montantPrime = montantPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodePrime() {
/* 104 */     return this.codePrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodePrime(String codePrime) {
/* 109 */     this.codePrime = codePrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibellePrime() {
/* 114 */     if (this.primeBulletin != null)
/*     */     {
/* 116 */       this.libellePrime = this.primeBulletin.getDesignation();
/*     */     }
/* 118 */     return this.libellePrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibellePrime(String libellePrime) {
/* 123 */     this.libellePrime = libellePrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdBulletin() {
/* 128 */     return this.idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdBulletin(int idBulletin) {
/* 133 */     this.idBulletin = idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getPrimeBulletin() {
/* 138 */     return this.primeBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrimeBulletin(PrimeIndemniteC primeBulletin) {
/* 143 */     this.primeBulletin = primeBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexNum() {
/* 148 */     return this.indexNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexNum(int indexNum) {
/* 153 */     this.indexNum = indexNum;
/*     */   }
/*     */   
/*     */   public Date getDatePaie() {
/* 157 */     return this.datePaie;
/*     */   }
/*     */   
/*     */   public void setDatePaie(Date datePaie) {
/* 161 */     this.datePaie = datePaie;
/*     */   }
/*     */   
/*     */   public boolean isCalculated() {
/* 165 */     return this.calculated;
/*     */   }
/*     */   
/*     */   public void setCalculated(boolean calculated) {
/* 169 */     this.calculated = calculated;
/*     */   }
/*     */   
/*     */   public int getPriority() {
/* 173 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setPriority(int priority) {
/* 177 */     this.priority = priority;
/*     */   }
/*     */   
/*     */   public int getIdParametre() {
/* 181 */     return this.idParametre;
/*     */   }
/*     */   
/*     */   public void setIdParametre(int idParametre) {
/* 185 */     this.idParametre = idParametre;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\BulletinPrimeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */