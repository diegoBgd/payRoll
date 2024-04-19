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
/*     */ public class FonctionnaireC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5262309402831106100L;
/*     */   private int id;
/*     */   private String matricule;
/*     */   private String nom;
/*     */   private String prenom;
/*     */   private Date dateDebut;
/*     */   private Date dateFin;
/*     */   private Historique historique;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*     */   
/*     */   public int getId() {
/*  30 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  35 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMatricule() {
/*  40 */     return this.matricule;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMatricule(String matricule) {
/*  45 */     this.matricule = matricule;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNom() {
/*  50 */     return this.nom;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNom(String nom) {
/*  55 */     this.nom = nom;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrenom() {
/*  60 */     return this.prenom;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrenom(String prenom) {
/*  65 */     this.prenom = prenom;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/*  70 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  75 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/*  80 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/*  85 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  90 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  95 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/* 100 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/* 105 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/* 110 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/* 115 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomPrenom() {
/* 120 */     return String.valueOf(this.nom) + " " + this.prenom;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\FonctionnaireC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */