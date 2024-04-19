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
/*    */ public class ServiceDetailC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1444496860828944730L;
/*    */   private int id;
/*    */   private ServicesC service;
/*    */   private Base fonction;
/*    */   private boolean existe;
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
/*    */   public ServicesC getService() {
/* 34 */     return this.service;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setService(ServicesC service) {
/* 39 */     this.service = service;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getFonction() {
/* 44 */     return this.fonction;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFonction(Base fonction) {
/* 49 */     this.fonction = fonction;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isExiste() {
/* 54 */     return this.existe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExiste(boolean existe) {
/* 59 */     this.existe = existe;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ServiceDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */