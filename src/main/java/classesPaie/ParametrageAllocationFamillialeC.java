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
/*    */ public class ParametrageAllocationFamillialeC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 124794847895351487L;
/*    */   private int id;
/*    */   private int ageMaximal;
/*    */   private int ageReportMaximal;
/*    */   private int statutPersonnel;
/*    */   private double montantAllocation;
/*    */   private String statutPersonnelS;
/*    */   
/*    */   public int getId() {
/* 23 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 28 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAgeMaximal() {
/* 33 */     return this.ageMaximal;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAgeMaximal(int ageMaximal) {
/* 38 */     this.ageMaximal = ageMaximal;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAgeReportMaximal() {
/* 43 */     return this.ageReportMaximal;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAgeReportMaximal(int ageReportMaximal) {
/* 48 */     this.ageReportMaximal = ageReportMaximal;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getMontantAllocation() {
/* 53 */     return this.montantAllocation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMontantAllocation(double montantAllocation) {
/* 58 */     this.montantAllocation = montantAllocation;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStatutPersonnel() {
/* 63 */     return this.statutPersonnel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStatutPersonnel(int statutPersonnel) {
/* 68 */     this.statutPersonnel = statutPersonnel;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getStatutPersonnelS() {
/* 73 */     return this.statutPersonnelS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStatutPersonnelS(String statutPersonnelS) {
/* 78 */     this.statutPersonnelS = statutPersonnelS;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageAllocationFamillialeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */