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
/*     */ public class CreditRembourseC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -370758307614513909L;
/*     */   private int id;
/*     */   private int index;
/*     */   private int idCredit;
/*     */   private int idBulletin;
/*     */   private double montantCapital;
/*     */   private double montantRembourse;
/*     */   private double montantInteret;
/*     */   private double montantTaxe;
/*     */   private Date dateRemboursement;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private String dateRemboursementPrint,
						   printMontant,libelle;
/*     */   private Historique historique;

/*     */   public int getIdBulletin() {
/*  33 */     return this.idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdBulletin(int idBulletin) {
/*  38 */     this.idBulletin = idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateRemboursementPrint() {
			  dateRemboursementPrint=HelperC.convertDate(getDateRemboursement());
/*  43 */     return this.dateRemboursementPrint;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateRemboursementPrint(String dateRemboursementPrint) {
/*  48 */     this.dateRemboursementPrint = dateRemboursementPrint;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantRembourse() {
/*  53 */     return this.montantRembourse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantRembourse(double montantRembourse) {
/*  58 */     this.montantRembourse = montantRembourse;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  63 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  68 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  73 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  78 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdCredit() {
/*  83 */     return this.idCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdCredit(int idCredit) {
/*  88 */     this.idCredit = idCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateRemboursement() {
/*  93 */     return this.dateRemboursement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateRemboursement(Date dateRemboursement) {
/*  98 */     this.dateRemboursement = dateRemboursement;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/* 103 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/* 108 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantCapital() {
/* 113 */     return this.montantCapital;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantCapital(double montantCapital) {
/* 118 */     this.montantCapital = montantCapital;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantInteret() {
/* 123 */     return this.montantInteret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantInteret(double montantInteret) {
/* 128 */     this.montantInteret = montantInteret;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantTaxe() {
/* 133 */     return this.montantTaxe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantTaxe(double montantTaxe) {
/* 138 */     this.montantTaxe = montantTaxe;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 143 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 148 */     this.index = index;
/*     */   }
			public Historique getHistorique() {
				return historique;
			}
			public void setHistorique(Historique historique) {
				this.historique = historique;
			}
			public String getPrintMontant() {
				printMontant=HelperC.decimalNumber(getMontantRembourse(), 0, true);
				return printMontant;
			}
			public void setPrintMontant(String printMontant) {
				this.printMontant = printMontant;
			}
			public String getLibelle() {
				return libelle;
			}
			public void setLibelle(String libelle) {
				this.libelle = libelle;
			}


/*     */ }


/* Location:              G:\PAIE\!\classesPaie\CreditRembourseC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */