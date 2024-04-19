/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TypeCritereC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -991021918637711843L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private String noteAppreciationGlobaleS;
/*    */   private double noteAppreciationGlobale;
/*    */   private Historique historique;
/*    */   
/*    */   public int getId() {
/* 26 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 31 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 36 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 41 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getNoteAppreciationGlobale() {
/* 46 */     return this.noteAppreciationGlobale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNoteAppreciationGlobale(double noteAppreciationGlobale) {
/* 51 */     this.noteAppreciationGlobale = noteAppreciationGlobale;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 56 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 61 */     this.historique = historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNoteAppreciationGlobaleS() {
/* 66 */     return this.noteAppreciationGlobaleS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNoteAppreciationGlobaleS(String noteAppreciationGlobaleS) {
/* 71 */     this.noteAppreciationGlobaleS = noteAppreciationGlobaleS;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 76 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 81 */     this.code = code;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\TypeCritereC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */