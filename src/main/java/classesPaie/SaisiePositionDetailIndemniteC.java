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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SaisiePositionDetailIndemniteC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5683316363021446290L;
/*     */   private int id;
/*     */   private int indexe;
/*     */   private double montant;
/*     */   private Historique historique;
/*     */   private String montantS;
/*     */   private SaisiePositionEmployeC saisie;
/*     */   private PrimeIndemniteC indemnite;
/*     */   private boolean existe;
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
/*     */   public double getMontant() {
/*  41 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/*  46 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  51 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  56 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public SaisiePositionEmployeC getSaisie() {
/*  61 */     return this.saisie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSaisie(SaisiePositionEmployeC saisie) {
/*  66 */     this.saisie = saisie;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getIndemnite() {
/*  71 */     return this.indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndemnite(PrimeIndemniteC indemnite) {
/*  76 */     this.indemnite = indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantS() {
/*  81 */     return this.montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantS(String montantS) {
/*  86 */     this.montantS = montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/*  91 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/*  96 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexe() {
/* 101 */     return this.indexe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexe(int indexe) {
/* 106 */     this.indexe = indexe;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeMontant() {
/*     */     try {
/* 113 */       setMontant(Double.valueOf(this.montantS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     }
/* 115 */     catch (Exception e) {
/*     */       
/* 117 */       setMontant(0.0D);
/*     */     } 
/* 119 */     this.montantS = HelperC.TraitementMontant.getMontantFormate(getMontant(), 0);
/* 120 */     setMontant(Integer.valueOf(this.montantS.replace("-", "").replace(" ", "").replace(",", ".").trim()).intValue());
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\SaisiePositionDetailIndemniteC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */