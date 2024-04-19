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
/*     */ public class DetailDeductionC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6256376370150051926L;
/*     */   private int id;
/*     */   private int idBanque;
/*     */   private DeductionC deduction;
/*     */   private BanqueC banque;
/*     */   private EmployeC employe;
/*     */   private String montantS;
/*     */   private String numeroCpte;
/*     */   private double montant;
/*     */   private double taux;
/*     */   private double base;
/*     */   private Historique historique;
/*     */   private boolean existe;
/*     */   
/*     */   public int getIdBanque() {
/*  32 */     return this.idBanque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdBanque(int idBanque) {
/*  37 */     this.idBanque = idBanque;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  42 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  47 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeductionC getDeduction() {
/*  52 */     return this.deduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeduction(DeductionC deduction) {
/*  57 */     this.deduction = deduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public BanqueC getBanque() {
/*  62 */     return this.banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBanque(BanqueC banque) {
/*  67 */     this.banque = banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  72 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  77 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantS() {
/*  82 */     return this.montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantS(String montantS) {
/*  87 */     this.montantS = montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/*  92 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/*  97 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNumeroCpte() {
/* 102 */     return this.numeroCpte;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumeroCpte(String numeroCpte) {
/* 107 */     this.numeroCpte = numeroCpte;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 112 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 117 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/* 122 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/* 127 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTaux() {
/* 132 */     return this.taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTaux(double taux) {
/* 137 */     this.taux = taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBase() {
/* 142 */     return this.base;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBase(double base) {
/* 147 */     this.base = base;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailDeductionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */