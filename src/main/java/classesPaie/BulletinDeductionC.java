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
/*     */ public class BulletinDeductionC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7216623808137583037L;
/*     */   private int id;
/*     */   private int idRetenu;
/*     */   private int idBulletin;
/*     */   private int indexNum;
/*     */   private double montantRetenu;
/*     */   private double taux;
/*     */   private double base;
/*     */   private String codeDeduction;
/*     */   private String libelleDeduction;
/*     */   private DeductionC deduction;
/*     */   private String printMontant;
/*     */   
/*     */   public int getIndexNum() {
/*  31 */     return this.indexNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexNum(int indexNum) {
/*  36 */     this.indexNum = indexNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTaux() {
/*  41 */     return this.taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTaux(double taux) {
/*  46 */     this.taux = taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBase() {
/*  51 */     return this.base;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBase(double base) {
/*  56 */     this.base = base;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeductionC getDeduction() {
/*  61 */     return this.deduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeduction(DeductionC deduction) {
/*  66 */     this.deduction = deduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdBulletin() {
/*  71 */     return this.idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdBulletin(int idBulletin) {
/*  76 */     this.idBulletin = idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  81 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  86 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdRetenu() {
/*  91 */     return this.idRetenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdRetenu(int idRetenu) {
/*  96 */     this.idRetenu = idRetenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantRetenu() {
/* 101 */     return this.montantRetenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantRetenu(double montantRetenu) {
/* 106 */     this.montantRetenu = montantRetenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeDeduction() {
/* 111 */     return this.codeDeduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeDeduction(String codeDeduction) {
/* 116 */     this.codeDeduction = codeDeduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleDeduction() {
/* 121 */     if (this.deduction != null)
/*     */     {
/* 123 */       this.libelleDeduction = this.deduction.getDesignation();
/*     */     }
/* 125 */     return this.libelleDeduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelleDeduction(String libelleDeduction) {
/* 130 */     this.libelleDeduction = libelleDeduction;
/*     */   }
/*     */   
/*     */   public String getPrintMontant() {
/* 134 */     this.printMontant = HelperC.decimalNumber(getMontantRetenu(), 0, true);
/* 135 */     return this.printMontant;
/*     */   }
/*     */   
/*     */   public void setPrintMontant(String printMontant) {
/* 139 */     this.printMontant = printMontant;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\BulletinDeductionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */