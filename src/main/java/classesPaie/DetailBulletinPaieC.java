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
/*    */ public class DetailBulletinPaieC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 34340511368315678L;
/*    */   private int id;
/*    */   private Historique historique;
/*    */   
/*    */   public int getId() {
/* 22 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 27 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 32 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 37 */     this.historique = historique;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\DetailBulletinPaieC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */