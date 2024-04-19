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
/*     */ public class PaieAnormalC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4595058223947218476L;
/*     */   private int id;
/*     */   private int idEmploye;
/*     */   private int idExercice;
/*     */   private int indexNum;
/*     */   private Date datePaie;
/*     */   private String nomPrenom,printMontant,printBase;
/*     */   private String code;
/*     */   private double montantPaie;
/*     */   
/*     */   public int getIdExercice() {
/*  26 */     return this.idExercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdExercice(int idExercice) {
/*  31 */     this.idExercice = idExercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  36 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  41 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdEmploye() {
/*  46 */     return this.idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEmploye(int idEmploye) {
/*  51 */     this.idEmploye = idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDatePaie() {
/*  56 */     return this.datePaie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDatePaie(Date datePaie) {
/*  61 */     this.datePaie = datePaie;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomPrenom() {
/*  66 */     return this.nomPrenom;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomPrenom(String nomPrenom) {
/*  71 */     this.nomPrenom = nomPrenom;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantPaie() {
/*  76 */     return this.montantPaie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantPaie(double montantPaie) {
/*  81 */     this.montantPaie = montantPaie;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  86 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  91 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexNum() {
/*  96 */     return this.indexNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexNum(int indexNum) {
/* 101 */     this.indexNum = indexNum;
/*     */   }
			public String getPrintMontant() {
				printMontant=HelperC.decimalNumber(Math.round(getMontantPaie()), 0, true);
				return printMontant;
			}
			public void setPrintMontant(String printMontant) {
				
				this.printMontant = printMontant;
			}
			public String getPrintBase() {
				return printBase;
			}
			public void setPrintBase(String printBase) {
				this.printBase = printBase;
			}


/*     */ }


/* Location:              G:\PAIE\!\classesPaie\PaieAnormalC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */