/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

/*     */ public class DeductionRangesC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2146872480492911851L;
/*     */   private int id;
/*  31 */   private int no = 1; private int index; private double borneDebut;
/*  32 */   private List<DeductionRangesC> listeDetailDeductionRanges = new ArrayList<DeductionRangesC>(); 
			private double borneFin; 
			private double montant;
/*  33 */   private List<DeductionRangesC> listeDetailDeductionRangesdeleted = new ArrayList<DeductionRangesC>(); 
			private String borneDebutS; 
			private String borneFinS; 
			private String montantS;
/*     */   private boolean existe;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  38 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  43 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNo() {
/*  48 */     return this.no;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNo(int no) {
/*  53 */     this.no = no;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/*  58 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/*  63 */     this.index = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBorneDebut() {
/*  68 */     return this.borneDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorneDebut(double borneDebut) {
/*  73 */     this.borneDebut = borneDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBorneFin() {
/*  78 */     return this.borneFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorneFin(double borneFin) {
/*  83 */     this.borneFin = borneFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/*  88 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/*  93 */     this.montant = montant;
/*     */   }
/*     */   
/*     */   public List<DeductionRangesC> getListeDetailDeductionRanges() {
/*  97 */     return this.listeDetailDeductionRanges;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListeDetailDeductionRanges(List<DeductionRangesC> listeDetailDeductionRanges) {
/* 102 */     this.listeDetailDeductionRanges = listeDetailDeductionRanges;
/*     */   }
/*     */   
/*     */   public List<DeductionRangesC> getListeDetailDeductionRangesdeleted() {
/* 106 */     return this.listeDetailDeductionRangesdeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListeDetailDeductionRangesdeleted(List<DeductionRangesC> listeDetailDeductionRangesdeleted) {
/* 111 */     this.listeDetailDeductionRangesdeleted = listeDetailDeductionRangesdeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBorneDebutS() {
/* 116 */     return this.borneDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorneDebutS(String borneDebutS) {
/* 121 */     this.borneDebutS = borneDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBorneFinS() {
/* 126 */     return this.borneFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorneFinS(String borneFinS) {
/* 131 */     this.borneFinS = borneFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantS() {
/* 136 */     return this.montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantS(String montantS) {
/* 141 */     this.montantS = montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/* 146 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/* 151 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 156 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 161 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DeductionRangesC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */