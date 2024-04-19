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
/*     */ public class CoordonneesSocieteC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3273607654552380175L;
/*     */   private int id;
/*     */   private String code;
/*     */   private String representant;
/*     */   private String nomSociete;
/*     */   private String adresse;
/*     */   private String telephoneMobile;
/*     */   private String telephoneFixe;
/*     */   private String fax;
/*     */   private String email;
/*     */   private String descriptif;
/*     */   private String noEmployeurInss;
/*     */   private String logo;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  38 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  43 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  48 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRepresentant() {
/*  53 */     return this.representant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRepresentant(String representant) {
/*  58 */     this.representant = representant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomSociete() {
/*  63 */     return this.nomSociete;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomSociete(String nomSociete) {
/*  68 */     this.nomSociete = nomSociete;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAdresse() {
/*  73 */     return this.adresse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAdresse(String adresse) {
/*  78 */     this.adresse = adresse;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTelephoneMobile() {
/*  83 */     return this.telephoneMobile;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTelephoneMobile(String telephoneMobile) {
/*  88 */     this.telephoneMobile = telephoneMobile;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTelephoneFixe() {
/*  93 */     return this.telephoneFixe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTelephoneFixe(String telephoneFixe) {
/*  98 */     this.telephoneFixe = telephoneFixe;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFax() {
/* 103 */     return this.fax;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFax(String fax) {
/* 108 */     this.fax = fax;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEmail() {
/* 113 */     return this.email;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmail(String email) {
/* 118 */     this.email = email;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescriptif() {
/* 123 */     return this.descriptif;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescriptif(String descriptif) {
/* 128 */     this.descriptif = descriptif;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoEmployeurInss() {
/* 133 */     return this.noEmployeurInss;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoEmployeurInss(String noEmployeurInss) {
/* 138 */     this.noEmployeurInss = noEmployeurInss;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLogo() {
/* 143 */     return this.logo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogo(String logo) {
/* 148 */     this.logo = logo;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 153 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 158 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\CoordonneesSocieteC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */