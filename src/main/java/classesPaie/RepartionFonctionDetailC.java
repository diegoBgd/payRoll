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
/*    */ public class RepartionFonctionDetailC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7935871343828768491L;
/*    */   private int id;
/*    */   private RepartitionFonctionC entete;
/*    */   private Base fonction;
/*    */   private boolean existe;
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
/*    */   public RepartitionFonctionC getEntete() {
/* 37 */     return this.entete;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEntete(RepartitionFonctionC entete) {
/* 42 */     this.entete = entete;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getFonction() {
/* 47 */     return this.fonction;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFonction(Base fonction) {
/* 52 */     this.fonction = fonction;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isExiste() {
/* 57 */     return this.existe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExiste(boolean existe) {
/* 62 */     this.existe = existe;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\RepartionFonctionDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */