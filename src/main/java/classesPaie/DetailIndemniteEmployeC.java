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
/*     */ public class DetailIndemniteEmployeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4005857446114462398L;
/*     */   private int id;
/*     */   private double montant;
/*     */   private double taux;
/*     */   private String montantS;
/*     */   private EmployeC employe;
/*     */   private PrimeIndemniteC indemnite;
/*     */   private Historique historique;
/*     */   private boolean existe;
/*     */   
/*     */   public int getId() {
/*  27 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  32 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/*  37 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/*  42 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantS() {
/*  47 */     return this.montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantS(String montantS) {
/*  52 */     this.montantS = montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  57 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  62 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getIndemnite() {
/*  67 */     return this.indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndemnite(PrimeIndemniteC indemnite) {
/*  72 */     this.indemnite = indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  77 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  82 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/*  87 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/*  92 */     this.existe = existe;
/*     */   }
/*     */   
/*     */   public double getTaux() {
/*  96 */     return this.taux;
/*     */   }
/*     */   
/*     */   public void setTaux(double taux) {
/* 100 */     this.taux = taux;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeMontant() {
/*     */     try {
/* 107 */       setMontant(Double.valueOf(this.montantS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     }
/* 109 */     catch (Exception e) {
/*     */       
/* 111 */       setMontant(0.0D);
/*     */     } 
/* 113 */     this.montantS = HelperC.TraitementMontant.getMontantFormate(getMontant(), 0);
/* 114 */     setMontant(Integer.valueOf(this.montantS.replace("-", "").replace(" ", "").replace(",", ".").trim()).intValue());
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailIndemniteEmployeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */