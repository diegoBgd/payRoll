/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import persistencePaie.FichierBaseDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CalculCotisationParBaremeC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3514853604400549563L;
/* 13 */   public static double montantSalarial = 0.0D, montantPatronal = 0.0D, taux = 0.0D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void valeurCotisationONPR(double montantBase) {
/* 21 */     double onpr = 0.0D;
/*    */     
/* 23 */     GrilleCotisationC grille = null;
/* 24 */     DetailGrilleCotisationC detail = null;
/*    */     
/* 26 */     montantSalarial = 0.0D;
/* 27 */     montantPatronal = 0.0D;
/* 28 */     taux = 0.0D;
/*    */     
/* 30 */     grille = FichierBaseDAO.getInstance().getGrilleCotisation();
/* 31 */     if (grille != null) {
/* 32 */       onpr = montantBase / grille.getCoefficient();
/*    */       
/* 34 */       detail = FichierBaseDAO.getInstance().getDetail(onpr);
/*    */       
/* 36 */       if (detail != null) {
/* 37 */         montantSalarial = detail.getTotalCotisation();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void valeurCotisationImpot(double montantBase, CotisationC cotisation, int typeEmploye) {
/* 45 */     double ire = 0.0D;
/* 46 */     BaremeIPRC bareme = null;
/* 47 */     montantSalarial = 0.0D; montantPatronal = 0.0D; taux = 0.0D;
/*    */     //System.out.println(HelperC.decimalNumber(montantBase, 0, true));
/* 49 */     if (cotisation != null)
/*    */     {
/*    */     
/* 52 */       if (cotisation.getTypeBaremme() == 1) {
/*    */         
/* 54 */         double tranche = 0.0D;
/* 55 */         double montantA = 0.0D;
/* 56 */         double borneFinA = 0.0D;
/* 57 */         bareme = FichierBaseDAO.getInstance().getBaremeIRE(typeEmploye);

/* 58 */         if (bareme != null) {
/*    */ 
/*    */           
/* 61 */           tranche = montantBase;
/*    */           
/* 63 */           int i = 0;
/* 64 */           for (DetailBaremeC det : bareme.getListDetailBareme()) {
/*    */ 
/*    */             
/* 67 */             if (bareme.getType() == 2) {
/*    */               
/* 69 */               det.setBorneDebut(det.getBorneDebut() / 12.0D);
/* 70 */               det.setBorneFin(det.getBorneFin() / 12.0D);
/*    */             } 

/* 72 */             if (det.getBorneFin() == 0.0D)
/*    */             {
/* 74 */               det.setBorneFin(Integer.MAX_VALUE);
/*    */             }
/*    */             
/* 77 */             if (montantBase >= det.getBorneDebut() && montantBase <= det.getBorneFin()) {
/*    */               
/* 79 */               tranche = montantBase - borneFinA;
/*    */               
/* 81 */               if (tranche > 0.0D) {
/*    */                 
/* 83 */                 ire += tranche * det.getPourcentage() / 100.0D;
/* 84 */                 if (i > 0)
/* 85 */                   montantA = (bareme.getListDetailBareme().get(i - 1)).getBorneDebut() * (bareme.getListDetailBareme().get(i - 1)).getPourcentage() / 100.0D; 
/* 86 */                 ire += montantA;
/* 87 */                 det.setCotisation(ire);
/*    */               } 
/* 89 */               taux = det.getPourcentage();
/*    */             } 
/* 91 */             i++;
/* 92 */             borneFinA = det.getBorneFin();
/* 93 */             montantA = 0.0D;
/*    */           } 
/*    */ 
/*    */           
/* 97 */           montantSalarial = Math.rint(ire);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


