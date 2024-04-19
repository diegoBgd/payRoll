/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
 
/*     */ 
/*     */ public class AffectationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3339259287458640254L;
/*     */   private int id;
/*     */   private int idFonction;
/*     */   private String reference;
/*     */   private String motPasse;
/*     */   private String confirmPwd;
/*     */   private String nomPrenom;
/*     */   private String matricule;
/*     */   private String comment;
/*     */   private Date dateDebut;
/*     */   private Date dateFin;
/*     */   private int idEmploye;
/*     */   private Base fonction;
/*     */   private Historique historique;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*     */   private boolean createEmploye;
/*     */   
/*     */   public int getId() {
/*  35 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  40 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReference() {
/*  45 */     return this.reference;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReference(String reference) {
/*  50 */     this.reference = reference;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/*  55 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  60 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/*  65 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/*  70 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */  
/*     */   public Base getFonction() {
/*  85 */     return this.fonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFonction(Base fonction) {
/*  90 */     this.fonction = fonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  95 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 100 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/* 105 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/* 110 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/* 115 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/* 120 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMotPasse() {
/* 125 */     return this.motPasse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMotPasse(String motPasse) {
/* 130 */     this.motPasse = motPasse;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getConfirmPwd() {
/* 135 */     return this.confirmPwd;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConfirmPwd(String confirmPwd) {
/* 140 */     this.confirmPwd = confirmPwd;
/*     */   }
/*     */   
/*     */   public int getIdFonction() {
/* 144 */     return this.idFonction;
/*     */   }
/*     */   
/*     */   public void setIdFonction(int idFonction) {
/* 148 */     this.idFonction = idFonction;
/*     */   }
/*     */ 
/*     */   

/*     */   public void setNomPrenom(String nomPrenom) {
/* 161 */     this.nomPrenom = nomPrenom;
/*     */   }
/*     */   
/*     */   public String getComment() {
/* 165 */     return this.comment;
/*     */   }
/*     */   
/*     */   public void setComment(String comment) {
/* 169 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   

/*     */   public void setMatricule(String matricule) {
/* 183 */     this.matricule = matricule;
/*     */   }
			public int getIdEmploye() {
				return idEmploye;
			}
			public void setIdEmploye(int idEmploye) {
				this.idEmploye = idEmploye;
			}
/*     */ 
/*     */   
/*     */   public boolean isCreateEmploye() {
/* 188 */     return this.createEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreateEmploye(boolean createEmploye) {
/* 193 */     this.createEmploye = createEmploye;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\AffectationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */