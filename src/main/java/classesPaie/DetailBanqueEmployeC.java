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
/*     */ public class DetailBanqueEmployeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1749676701332269245L;
/*     */   private int id;
/*     */   private int idEmploye;
/*     */   private EmployeC employe;
/*     */   private BanqueC banque;
/*     */   private double pourcentageSalaire;
/*     */   private double montant;
/*     */   private String numeroCompte;
/*     */   private Historique historique;
/*     */   private String montantS;
/*     */   private boolean existe;
/*     */   private boolean principal;
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
/*     */   public EmployeC getEmploye() {
/*  41 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  46 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public BanqueC getBanque() {
/*  51 */     return this.banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBanque(BanqueC banque) {
/*  56 */     this.banque = banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentageSalaire() {
/*  61 */     return this.pourcentageSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageSalaire(double pourcentageSalaire) {
/*  66 */     this.pourcentageSalaire = pourcentageSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNumeroCompte() {
/*  71 */     return this.numeroCompte;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumeroCompte(String numeroCompte) {
/*  76 */     this.numeroCompte = numeroCompte;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  81 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  86 */     this.historique = historique;
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
/*     */   public int getIdEmploye() {
/* 101 */     return this.idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEmploye(int idEmploye) {
/* 106 */     this.idEmploye = idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/* 111 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/* 116 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantS() {
/* 121 */     return this.montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantS(String montantS) {
/* 126 */     this.montantS = montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPrincipal() {
/* 131 */     return this.principal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrincipal(boolean principal) {
/* 136 */     this.principal = principal;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailBanqueEmployeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */