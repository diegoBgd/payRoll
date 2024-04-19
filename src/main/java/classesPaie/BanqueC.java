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
/*     */ public class BanqueC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4344885561487133962L;
/*     */   private int id;
/*     */   private String code;
/*     */   private String designation;
/*     */   private String telephone1;
/*     */   private String telephone2;
/*     */   private String adresse;
/*     */   private String compte;
/*     */   private String banqueVirement;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  29 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  34 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  39 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  44 */     if (code != null)
/*     */     {
/*  46 */       this.code = code.toUpperCase();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/*  52 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/*  57 */     this.designation = designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTelephone1() {
/*  62 */     return this.telephone1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTelephone1(String telephone1) {
/*  67 */     this.telephone1 = telephone1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTelephone2() {
/*  72 */     return this.telephone2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTelephone2(String telephone2) {
/*  77 */     this.telephone2 = telephone2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAdresse() {
/*  82 */     return this.adresse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAdresse(String adresse) {
/*  87 */     this.adresse = adresse;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCompte() {
/*  92 */     return this.compte;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCompte(String compte) {
/*  97 */     this.compte = compte;
/*     */   }
/*     */   
/*     */   public String getBanqueVirement() {
/* 101 */     return this.banqueVirement;
/*     */   }
/*     */   
/*     */   public void setBanqueVirement(String banqueVirement) {
/* 105 */     this.banqueVirement = banqueVirement;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 110 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 115 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\BanqueC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */