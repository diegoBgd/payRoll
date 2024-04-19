/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;

		public class DetailCritereEvaluationC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1961113854282081175L;
/*    */   private int id;
/*    */   private int index;
/*    */   private double note;
/*    */   private String noteS;
/*    */   private String designation;
/*    */   private CritereEvaluationC critere;
/*    */   private Historique historique;
/*    */   
/*    */   public int getId() {
/* 30 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 35 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getNote() {
/* 40 */     return this.note;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNote(double note) {
/* 45 */     this.note = note;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNoteS() {
/* 50 */     return this.noteS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNoteS(String noteS) {
/* 55 */     this.noteS = noteS;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 60 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 65 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 70 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 75 */     this.historique = historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public CritereEvaluationC getCritere() {
/* 80 */     return this.critere;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCritere(CritereEvaluationC critere) {
/* 85 */     this.critere = critere;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIndex() {
/* 90 */     return this.index;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIndex(int index) {
/* 95 */     this.index = index;
/*    */   }
/*    */ }


