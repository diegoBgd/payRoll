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
/*    */ public class ServicesC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1274221364609373764L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Historique historique;
/*    */   private DirectionC direction;
/*    */   
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
/*    */   public String getCode() {
/* 36 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 41 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 46 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 51 */     this.designation = designation;
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
/*    */   public DirectionC getDirection() {
/* 66 */     return this.direction;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDirection(DirectionC direction) {
/* 71 */     this.direction = direction;
/*    */   }
/*    */   
/*    */  
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ServicesC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */