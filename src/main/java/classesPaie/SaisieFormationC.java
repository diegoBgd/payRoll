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
/*     */ 
/*     */ public class SaisieFormationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7431517431793550065L;
/*     */   private int id;
/*     */   private int idEmploye;
/*     */   private int idFormation;
/*     */   private String observation;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*  28 */   private Date dateDebut = new Date();
/*  29 */   private Date dateFin = new Date();
/*     */   
/*     */   private Base formation;
/*     */   
/*     */   public int getId() {
/*  34 */     return this.id;
/*     */   }
/*     */   private EmployeC employe; private Historique historique;
/*     */   
/*     */   public void setId(int id) {
/*  39 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdEmploye() {
/*  44 */     return this.idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEmploye(int idEmploye) {
/*  49 */     this.idEmploye = idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdFormation() {
/*  54 */     return this.idFormation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdFormation(int idFormation) {
/*  59 */     this.idFormation = idFormation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getObservation() {
/*  64 */     return this.observation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObservation(String observation) {
/*  69 */     this.observation = observation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/*  74 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/*  79 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/*  84 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/*  89 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/*  94 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  99 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/* 104 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/* 109 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getFormation() {
/* 114 */     return this.formation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormation(Base formation) {
/* 119 */     this.formation = formation;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/* 124 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 129 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 134 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 139 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\SaisieFormationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */