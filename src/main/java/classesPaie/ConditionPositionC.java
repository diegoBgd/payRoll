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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConditionPositionC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4143318173055503397L;
/*    */   private int id;
/*    */   private String condition;
/*    */   private String libellePosition;
/*    */   private Constante.Position position;
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
/*    */   public String getCondition() {
/* 37 */     return this.condition;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCondition(String condition) {
/* 42 */     this.condition = condition;
/*    */   }
/*    */ 
/*    */   
/*    */   public Constante.Position getPosition() {
/* 47 */     return this.position;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPosition(Constante.Position position) {
/* 52 */     this.position = position;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLibellePosition() {
/* 57 */     return this.libellePosition;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLibellePosition(String libellePosition) {
/* 62 */     this.libellePosition = libellePosition;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ConditionPositionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */