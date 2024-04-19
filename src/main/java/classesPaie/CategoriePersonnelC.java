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
/*    */ public class CategoriePersonnelC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5280309449245943005L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Base personnel;
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
/*    */   public String getCode() {
/* 34 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 39 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 44 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 49 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getPersonnel() {
/* 54 */     return this.personnel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPersonnel(Base personnel) {
/* 59 */     this.personnel = personnel;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\CategoriePersonnelC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */