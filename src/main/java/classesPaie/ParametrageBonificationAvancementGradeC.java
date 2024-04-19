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
/*    */ public class ParametrageBonificationAvancementGradeC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4426951655302157259L;
/*    */   private int id;
/*    */   private double tauxBonification;
/*    */   private String tauxBonificationS;
/*    */   private Historique historique;
/*    */   private GradePersonnelC grade;
/*    */   
/*    */   public int getId() {
/* 25 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 30 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getTauxBonification() {
/* 35 */     return this.tauxBonification;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTauxBonification(double tauxBonification) {
/* 40 */     this.tauxBonification = tauxBonification;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTauxBonificationS() {
/* 45 */     return this.tauxBonificationS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTauxBonificationS(String tauxBonificationS) {
/* 50 */     this.tauxBonificationS = tauxBonificationS;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 55 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 60 */     this.historique = historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public GradePersonnelC getGrade() {
/* 65 */     return this.grade;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGrade(GradePersonnelC grade) {
/* 70 */     this.grade = grade;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageBonificationAvancementGradeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */