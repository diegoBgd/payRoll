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
/*    */ public class EmployeDetailConditionsRecrutementC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2739627766095516065L;
/*    */   private int id;
/*    */   private boolean remplie;
/*    */   private EmployeC entete;
/*    */   private ConditionRecrutementDetailC condition;
/*    */   
/*    */   public int getId() {
/* 24 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 29 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemplie() {
/* 34 */     return this.remplie;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRemplie(boolean remplie) {
/* 39 */     this.remplie = remplie;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConditionRecrutementDetailC getCondition() {
/* 44 */     return this.condition;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCondition(ConditionRecrutementDetailC condition) {
/* 49 */     this.condition = condition;
/*    */   }
/*    */ 
/*    */   
/*    */   public EmployeC getEntete() {
/* 54 */     return this.entete;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEntete(EmployeC entete) {
/* 59 */     this.entete = entete;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\EmployeDetailConditionsRecrutementC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */