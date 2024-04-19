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
/*    */ public class ParametrageDateNotationC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6964197087581883937L;
/*    */   private int id;
/*    */   private int jourDebut;
/*    */   private int moisDebut;
/*    */   private int jourFin;
/*    */   private int moisFin;
/*    */   private int dureeRecoursNotation;
/*    */   private Historique historique;
/*    */   
/*    */   public int getId() {
/* 27 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 32 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getJourDebut() {
/* 37 */     return this.jourDebut;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setJourDebut(int jourDebut) {
/* 42 */     this.jourDebut = jourDebut;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMoisDebut() {
/* 47 */     return this.moisDebut;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMoisDebut(int moisDebut) {
/* 52 */     this.moisDebut = moisDebut;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getJourFin() {
/* 57 */     return this.jourFin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setJourFin(int jourFin) {
/* 62 */     this.jourFin = jourFin;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMoisFin() {
/* 67 */     return this.moisFin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMoisFin(int moisFin) {
/* 72 */     this.moisFin = moisFin;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDureeRecoursNotation() {
/* 77 */     return this.dureeRecoursNotation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDureeRecoursNotation(int dureeRecoursNotation) {
/* 82 */     this.dureeRecoursNotation = dureeRecoursNotation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 87 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 92 */     this.historique = historique;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageDateNotationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */