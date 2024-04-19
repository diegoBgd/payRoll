/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CotisationOrderC
/*    */   implements Serializable, Comparator<BulletinCotisationC>
/*    */ {
/*    */   private static final long serialVersionUID = -1284898470829870007L;
/*    */   
/*    */   public int compare(BulletinCotisationC o1, BulletinCotisationC o2) {
/* 19 */     if (o1.getPriority() < o2.getPriority()) {
/* 20 */       return -1;
/*    */     }
/* 22 */     return 1;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\CotisationOrderC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */