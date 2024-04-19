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
/*     */ public class SaisieAbsenceC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2123801105427480157L;
/*     */   private int id;
/*     */   private int idExercice;
/*     */   private int duree;
/*     */   private int moisPaie;
/*     */   private String observation;
/*     */   private String dateS;
/*  27 */   private Date date = new Date();
/*     */   private boolean retenuPaie;
/*     */   private Base absence;
/*     */   
/*     */   public int getId() {
/*  32 */     return this.id;
/*     */   }
/*     */   private EmployeC employe; private Historique historique;
/*     */   
/*     */   public void setId(int id) {
/*  37 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDuree() {
/*  42 */     return this.duree;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDuree(int duree) {
/*  47 */     this.duree = duree;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getObservation() {
/*  52 */     return this.observation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObservation(String observation) {
/*  57 */     this.observation = observation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateS() {
/*  62 */     return this.dateS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateS(String dateS) {
/*  67 */     this.dateS = dateS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDate() {
/*  72 */     return this.date;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Date date) {
/*  77 */     this.date = date;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getAbsence() {
/*  82 */     return this.absence;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAbsence(Base absence) {
/*  87 */     this.absence = absence;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  92 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  97 */     this.employe = employe;
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

			public boolean isRetenuPaie() {
				return retenuPaie;
			}
			public void setRetenuPaie(boolean retenuPaie) {
				this.retenuPaie = retenuPaie;
			}
/*     */   public int getMoisPaie() {
/* 122 */     return this.moisPaie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMoisPaie(int moisPaie) {
/* 127 */     this.moisPaie = moisPaie;
/*     */   }
/*     */   
/*     */   public int getIdExercice() {
/* 131 */     return this.idExercice;
/*     */   }
/*     */   
/*     */   public void setIdExercice(int idExercice) {
/* 135 */     this.idExercice = idExercice;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\SaisieAbsenceC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */