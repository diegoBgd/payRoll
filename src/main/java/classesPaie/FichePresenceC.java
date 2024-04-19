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
/*     */ public class FichePresenceC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3071418887960181690L;
/*     */   private int id;
/*     */   private int idTypeHeurePreste;
/*     */   private double totalheureTravail;
/*     */   private String heureEntreee;
/*     */   private String heureSortie;
/*     */   private Date datePointage;
/*     */   private ExerciceC exercise;
/*     */   private EmployeC employe;
/*     */   private Historique historic;
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
/*     */   public int getIdTypeHeurePreste() {
/*  40 */     return this.idTypeHeurePreste;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdTypeHeurePreste(int idTypeHeurePreste) {
/*  45 */     this.idTypeHeurePreste = idTypeHeurePreste;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTotalheureTravail() {
/*  50 */     return this.totalheureTravail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTotalheureTravail(double totalheureTravail) {
/*  55 */     this.totalheureTravail = totalheureTravail;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeureEntreee() {
/*  60 */     return this.heureEntreee;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureEntreee(String heureEntreee) {
/*  65 */     this.heureEntreee = heureEntreee;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeureSortie() {
/*  70 */     return this.heureSortie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureSortie(String heureSortie) {
/*  75 */     this.heureSortie = heureSortie;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercise() {
/*  80 */     return this.exercise;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercise(ExerciceC exercise) {
/*  85 */     this.exercise = exercise;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  90 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  95 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistoric() {
/* 100 */     return this.historic;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistoric(Historique historic) {
/* 105 */     this.historic = historic;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDatePointage() {
/* 110 */     return this.datePointage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDatePointage(Date datePointage) {
/* 115 */     this.datePointage = datePointage;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\FichePresenceC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */