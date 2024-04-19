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
/*     */ public class JoursCongeEmployeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7359882432160989093L;
/*     */   private int id;
/*     */   private int numero;
/*     */   private double joursDu;
/*     */   private double joursPris;
/*     */   private double joursReportes;
/*     */   private String joursDuS;
/*     */   private String joursPrisS;
/*     */   private String joursReportesS;
/*     */   private ExerciceC exercice;
/*     */   private EmployeC employe;
/*     */   private Historique historique;
/*     */   private boolean existe;
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
/*     */   public void setJoursReportes(int joursReportes) {
/*  42 */     this.joursReportes = joursReportes;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  47 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  52 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  57 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  62 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  67 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  72 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/*  77 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/*  82 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getJoursDu() {
/*  87 */     return this.joursDu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJoursDu(double joursDu) {
/*  92 */     this.joursDu = joursDu;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getJoursPris() {
/*  97 */     return this.joursPris;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJoursPris(double joursPris) {
/* 102 */     this.joursPris = joursPris;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getJoursReportes() {
/* 107 */     return this.joursReportes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJoursReportes(double joursReportes) {
/* 112 */     this.joursReportes = joursReportes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJoursDuS() {
/* 117 */     return this.joursDuS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJoursDuS(String joursDuS) {
/* 122 */     this.joursDuS = joursDuS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJoursPrisS() {
/* 127 */     return this.joursPrisS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJoursPrisS(String joursPrisS) {
/* 132 */     this.joursPrisS = joursPrisS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJoursReportesS() {
/* 137 */     return this.joursReportesS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJoursReportesS(String joursReportesS) {
/* 142 */     this.joursReportesS = joursReportesS;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumero() {
/* 147 */     return this.numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumero(int numero) {
/* 152 */     this.numero = numero;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\JoursCongeEmployeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */