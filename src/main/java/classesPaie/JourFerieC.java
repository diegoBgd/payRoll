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
/*     */ public class JourFerieC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4287274764602240392L;
/*     */   private int id;
/*     */   private ExerciceC exercice;
/*     */   private Date dateFerie;
/*     */   private String designation;
/*     */   private String code;
/*     */   private String printDate;
/*     */   private Constante.TypeJourFerie typeJour;
/*     */   private String libelleJourFerie;
/*     */   private int typeJourFerie;
/*     */   private Historique historique;
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
/*     */   public ExerciceC getExercice() {
/*  41 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  46 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFerie() {
/*  51 */     return this.dateFerie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFerie(Date dateFerie) {
/*  56 */     this.dateFerie = dateFerie;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/*  61 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/*  66 */     this.designation = designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  71 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  76 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public Constante.TypeJourFerie getTypeJour() {
/*  81 */     return this.typeJour;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeJour(Constante.TypeJourFerie typeJour) {
/*  86 */     this.typeJour = typeJour;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleJourFerie() {
/*  91 */     return this.libelleJourFerie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelleJourFerie(String libelleJourFerie) {
/*  96 */     this.libelleJourFerie = libelleJourFerie;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTypeJourFerie() {
/* 101 */     return this.typeJourFerie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeJourFerie(int typeJourFerie) {
/* 106 */     this.typeJourFerie = typeJourFerie;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 111 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 116 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrintDate() {
/* 121 */     return this.printDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrintDate(String printDate) {
/* 126 */     this.printDate = printDate;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\JourFerieC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */