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
/*    */ public class ParametrageBonificationTitreComplementaireC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4340476378254564536L;
/*    */   private int id;
/*    */   private int dureeFormation;
/*    */   private double pourcentage;
/*    */   private String pourcentageS;
/*    */   private Historique historique;
/*    */   private GradePersonnelC grade;
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
/*    */   public int getDureeFormation() {
/* 36 */     return this.dureeFormation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDureeFormation(int dureeFormation) {
/* 41 */     this.dureeFormation = dureeFormation;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getPourcentage() {
/* 46 */     return this.pourcentage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPourcentage(double pourcentage) {
/* 51 */     this.pourcentage = pourcentage;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPourcentageS() {
/* 56 */     return this.pourcentageS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPourcentageS(String pourcentageS) {
/* 61 */     this.pourcentageS = pourcentageS;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 66 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 71 */     this.historique = historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public GradePersonnelC getGrade() {
/* 76 */     return this.grade;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGrade(GradePersonnelC grade) {
/* 81 */     this.grade = grade;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageBonificationTitreComplementaireC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */