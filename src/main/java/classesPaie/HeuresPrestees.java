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
/*     */ public class HeuresPrestees
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -961611621728285318L;
/*     */   private double pourcent;
/*     */   private Date datePrestation;
/*     */   private String temps;
/*     */   private String numeroHs;
/*     */   private int id;
/*     */   private int heure;
/*     */   private int minute;
/*     */   private int sec;
/*     */   private int idEmploye;
/*     */   private int mois;
/*     */   private int idExercice;
/*     */   
/*     */   public int getIdExercice() {
/*  28 */     return this.idExercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdExercice(int idExercice) {
/*  33 */     this.idExercice = idExercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcent() {
/*  38 */     return this.pourcent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcent(double pourcent) {
/*  43 */     this.pourcent = pourcent;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTemps() {
/*  48 */     return this.temps;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTemps(String temps) {
/*  53 */     this.temps = temps;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDatePrestation() {
/*  58 */     return this.datePrestation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDatePrestation(Date datePrestation) {
/*  63 */     this.datePrestation = datePrestation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  68 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  73 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeure() {
/*  78 */     return this.heure;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeure(int heure) {
/*  83 */     this.heure = heure;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinute() {
/*  88 */     return this.minute;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinute(int minute) {
/*  93 */     this.minute = minute;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSec() {
/*  98 */     return this.sec;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSec(int sec) {
/* 103 */     this.sec = sec;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdEmploye() {
/* 108 */     return this.idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEmploye(int idEmploye) {
/* 113 */     this.idEmploye = idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMois() {
/* 118 */     return this.mois;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMois(int mois) {
/* 123 */     this.mois = mois;
/*     */   }
/*     */   
/*     */   public String getNumeroHs() {
/* 127 */     return this.numeroHs;
/*     */   }
/*     */   
/*     */   public void setNumeroHs(String numeroHs) {
/* 131 */     this.numeroHs = numeroHs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onHourChange() {}
/*     */ 
/*     */   
/*     */   public void onMinuteChange() {
/* 140 */     if (this.minute >= 60) {
/*     */       
/* 142 */       this.heure++;
/* 143 */       this.minute -= 60;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onSecondchange() {
/* 149 */     if (this.sec >= 60) {
/*     */       
/* 151 */       this.minute++;
/* 152 */       this.sec -= 60;
/* 153 */       onMinuteChange();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\HeuresPrestees.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */