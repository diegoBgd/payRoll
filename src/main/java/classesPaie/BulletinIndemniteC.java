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
/*     */ public class BulletinIndemniteC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4330186674282708063L;
/*     */   private int idIndemnite;
/*     */   private int id;
/*     */   private int idBulletin;
/*     */   private double montantIndemnite;
/*     */   private double baseCalcul;
/*     */   private double taux;
/*     */   private String codeIndemnite;
/*     */   private String libelleIndemnite;
/*     */   private PrimeIndemniteC indemnite;
/*     */   
/*     */   public PrimeIndemniteC getIndemnite() {
/*  29 */     return this.indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndemnite(PrimeIndemniteC indemnite) {
/*  34 */     this.indemnite = indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdBulletin() {
/*  39 */     return this.idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdBulletin(int idBulletin) {
/*  44 */     this.idBulletin = idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdIndemnite() {
/*  49 */     return this.idIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdIndemnite(int idIndemnite) {
/*  54 */     this.idIndemnite = idIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  59 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  64 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantIndemnite() {
/*  69 */     return this.montantIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantIndemnite(double montantIndemnite) {
/*  74 */     this.montantIndemnite = montantIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeIndemnite() {
/*  79 */     return this.codeIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeIndemnite(String codeIndemnite) {
/*  84 */     this.codeIndemnite = codeIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleIndemnite() {
/*  89 */     if (this.indemnite != null)
/*     */     {
/*  91 */       this.libelleIndemnite = this.indemnite.getDesignation();
/*     */     }
/*  93 */     return this.libelleIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelleIndemnite(String libelleIndemnite) {
/*  98 */     this.libelleIndemnite = libelleIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBaseCalcul() {
/* 103 */     return this.baseCalcul;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseCalcul(double baseCalcul) {
/* 108 */     this.baseCalcul = baseCalcul;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTaux() {
/* 113 */     return this.taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTaux(double taux) {
/* 118 */     this.taux = taux;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\BulletinIndemniteC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */