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
/*     */ public class DetailProfessionC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8364515305030206476L;
/*     */   private int id;
/*     */   private Base profession;
/*     */   private EmployeC employe;
/*     */   private Date dateDebut;
/*     */   private Date dateFin;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*     */   private Historique historique;
/*     */   private boolean existe;
/*     */   private boolean etat;
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
/*     */   public Base getProfession() {
/*  41 */     return this.profession;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProfession(Base profession) {
/*  46 */     this.profession = profession;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  51 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  56 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/*  61 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/*  66 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/*  71 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  76 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/*  81 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/*  86 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/*  91 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/*  96 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/* 101 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/* 106 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEtat() {
/* 111 */     return this.etat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEtat(boolean etat) {
/* 116 */     this.etat = etat;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 121 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 126 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailProfessionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */