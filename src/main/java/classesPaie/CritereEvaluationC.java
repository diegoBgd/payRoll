/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

/*     */ public class CritereEvaluationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 334264713110927822L;
/*     */   private int id;
/*     */   private int index;
/*     */   private String code;
/*     */   private String designation;
/*     */   private String noteGeneralS;
/*     */   private double noteGeneral;
/*     */   private Historique historique;
/*  30 */   private List<DetailCritereEvaluationC> listDetailCritere = new ArrayList<DetailCritereEvaluationC>();
/*  31 */   private List<DetailCritereEvaluationC> listDetailCritereDeleted = new ArrayList<DetailCritereEvaluationC>();
/*     */   private TypeCritereC typeCritere;
/*     */   
/*     */   public List<DetailCritereEvaluationC> getListDetailCritere() {
/*  35 */     return this.listDetailCritere;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailCritere(List<DetailCritereEvaluationC> listDetailCritere) {
/*  40 */     this.listDetailCritere = listDetailCritere;
/*     */   }
/*     */   
/*     */   public List<DetailCritereEvaluationC> getListDetailCritereDeleted() {
/*  44 */     return this.listDetailCritereDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailCritereDeleted(List<DetailCritereEvaluationC> listDetailCritereDeleted) {
/*  49 */     this.listDetailCritereDeleted = listDetailCritereDeleted;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  55 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  60 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  65 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  70 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/*  75 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/*  80 */     this.designation = designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNoteGeneral() {
/*  85 */     return this.noteGeneral;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteGeneral(double noteGeneral) {
/*  90 */     this.noteGeneral = noteGeneral;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  95 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 100 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeCritereC getTypeCritere() {
/* 105 */     return this.typeCritere;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeCritere(TypeCritereC typeCritere) {
/* 110 */     this.typeCritere = typeCritere;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteGeneralS() {
/* 115 */     return this.noteGeneralS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteGeneralS(String noteGeneralS) {
/* 120 */     this.noteGeneralS = noteGeneralS;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 125 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 130 */     this.index = index;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\CritereEvaluationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */