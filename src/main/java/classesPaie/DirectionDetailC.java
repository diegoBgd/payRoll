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
/*    */ public class DirectionDetailC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8759523713201701173L;
/*    */   private int id;
/*    */   private DirectionC direction;
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
/*    */   public DirectionC getDirection() {
/* 34 */     return this.direction;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDirection(DirectionC direction) {
/* 39 */     this.direction = direction;
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


/* Location:              G:\PAIE\!\classesPaie\DirectionDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */