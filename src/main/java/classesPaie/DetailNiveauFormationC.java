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
/*     */ 
/*     */ 
/*     */ public class DetailNiveauFormationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5555752774471306048L;
/*     */   private int id;
/*     */   private EmployeC employe;
/*     */   private Base niveau;
/*     */   private Date dateDebut;
/*     */   private Date dateFin;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*     */   private boolean etat;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  32 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  37 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  42 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  47 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/*  52 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  57 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/*  62 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/*  67 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/*  72 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/*  77 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/*  82 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/*  87 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEtat() {
/*  92 */     return this.etat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEtat(boolean etat) {
/*  97 */     this.etat = etat;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 102 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 107 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getNiveau() {
/* 112 */     return this.niveau;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNiveau(Base niveau) {
/* 117 */     this.niveau = niveau;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailNiveauFormationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */