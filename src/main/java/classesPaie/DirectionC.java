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
/*    */ public class DirectionC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -5571513694333872045L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Base organe;
/*    */   private Historique historique;
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
/*    */   public String getCode() {
/* 35 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 40 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 45 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 50 */     this.designation = designation;
/*    */   }
/*    */   
/*    */   public Base getOrgane() {
/* 54 */     return this.organe;
/*    */   }
/*    */   
/*    */   public void setOrgane(Base organe) {
/* 58 */     this.organe = organe;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 63 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 68 */     this.historique = historique;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\DirectionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */