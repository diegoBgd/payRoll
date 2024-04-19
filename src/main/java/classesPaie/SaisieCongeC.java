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
/*     */ public class SaisieCongeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 18058660863343923L;
/*     */   private int id;
/*     */   private int idEmploye;
/*     */   private int idConge;
/*     */   private double nombreJours;
/*     */   private String observation;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*  29 */   private Date dateDebut = new Date();
/*  30 */   private Date dateFin = new Date();
/*     */   
/*     */   private Base conge;
/*     */   
/*     */   public Base getConge() {
/*  35 */     return this.conge;
/*     */   }
/*     */   private EmployeC employe; private Historique historique;
/*     */   
/*     */   public void setConge(Base conge) {
/*  40 */     this.conge = conge;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/*  45 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/*  50 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/*  55 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/*  60 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  65 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  70 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdEmploye() {
/*  75 */     return this.idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEmploye(int idEmploye) {
/*  80 */     this.idEmploye = idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdConge() {
/*  85 */     return this.idConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdConge(int idConge) {
/*  90 */     this.idConge = idConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getObservation() {
/*  95 */     return this.observation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObservation(String observation) {
/* 100 */     this.observation = observation;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/* 105 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 110 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/* 115 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/* 120 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/* 125 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/* 130 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNombreJours() {
/* 135 */     return this.nombreJours;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreJours(double nombreJours) {
/* 140 */     this.nombreJours = nombreJours;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 145 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 150 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\SaisieCongeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */