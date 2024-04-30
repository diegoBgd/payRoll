 package classesPaie;
 
 import java.io.Serializable;
 
 
 public class CalculHeureSupC
   implements Serializable
 {
   private static final long serialVersionUID = -539540476092723208L;
   
   public static double montantHeurePreste(double montantBase, int nbreHeures, double prcent, int heures, int min, int sec) {
     double montant = 0.0D;
     double montantHeure = 0.0D;
     if (nbreHeures > 0)
     {
       montantHeure = montantBase / nbreHeures;
     }
     montantHeure = montantHeure * prcent / 100.0D;
     montant = montantHeure * heures + montantHeure * min / 60.0D + montantHeure * sec / 3600.0D;
     return Math.round(montant);
   }
 }


