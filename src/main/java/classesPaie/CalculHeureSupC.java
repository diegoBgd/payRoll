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
/*    */ public class CalculHeureSupC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -539540476092723208L;
/*    */   
/*    */   public static double montantHeurePreste(double montantBase, int nbreHeures, double prcent, int heures, int min, int sec) {
/* 20 */     double montant = 0.0D;
/* 21 */     double montantHeure = 0.0D;
/* 22 */     if (nbreHeures > 0)
/*    */     {
/* 24 */       montantHeure = montantBase / nbreHeures;
/*    */     }
/* 26 */     montantHeure = montantHeure * prcent / 100.0D;
/* 27 */     montant = montantHeure * heures + montantHeure * min / 60.0D + montantHeure * sec / 3600.0D;
/* 28 */     return Math.round(montant);
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\CalculHeureSupC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */