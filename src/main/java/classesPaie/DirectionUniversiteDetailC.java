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
/*    */ public class DirectionUniversiteDetailC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2966483665858264559L;
/*    */   private int id;
/*    */   private DirectionUniversiteC entete;
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
/*    */   public DirectionUniversiteC getEntete() {
/* 37 */     return this.entete;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEntete(DirectionUniversiteC entete) {
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


/* Location:              G:\PAIE\!\classesPaie\DirectionUniversiteDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */