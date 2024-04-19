/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class AvanceDetailC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1730582595801636760L;
/*    */   private int id;
/*    */   private int mois;
/*    */   private int idEntete;
/*    */   private double montant;
/*    */   private String printMois;
/*    */   private String printMontant;
/*    */   private boolean seleceted;
/*    */   private boolean disable = true;
/*    */   
/*    */   public int getId() {
/* 20 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 23 */     this.id = id;
/*    */   }
/*    */   public int getMois() {
/* 26 */     return this.mois;
/*    */   }
/*    */   public void setMois(int mois) {
/* 29 */     this.mois = mois;
/*    */   }
/*    */   public double getMontant() {
/* 32 */     return this.montant;
/*    */   }
/*    */   public void setMontant(double montant) {
/* 35 */     this.montant = montant;
/*    */   }
/*    */   public int getIdEntete() {
/* 38 */     return this.idEntete;
/*    */   }
/*    */   public void setIdEntete(int idEntete) {
/* 41 */     this.idEntete = idEntete;
/*    */   }
/*    */   public String getPrintMois() {
/* 44 */     this.printMois = HelperC.moisEnLettres(getMois());
/* 45 */     return this.printMois;
/*    */   }
/*    */   public void setPrintMois(String printMois) {
/* 48 */     this.printMois = printMois;
/*    */   }
/*    */   public String getPrintMontant() {
/* 51 */     this.printMontant = HelperC.decimalNumber(getMontant(), 0, true);
/* 52 */     return this.printMontant;
/*    */   }
/*    */   public void setPrintMontant(String printMontant) {
/* 55 */     this.printMontant = printMontant;
/*    */   }
/*    */   public boolean isSeleceted() {
/* 58 */     return this.seleceted;
/*    */   }
/*    */   public void setSeleceted(boolean seleceted) {
/* 61 */     this.seleceted = seleceted;
/*    */   }
/*    */   public boolean isDisable() {
/* 64 */     return this.disable;
/*    */   }
/*    */   public void setDisable(boolean disable) {
/* 67 */     this.disable = disable;
/*    */   }
/*    */   
/*    */   public void checkDetail() {
/* 71 */     if (this.seleceted) {
/* 72 */       this.disable = false;
/*    */     } else {
/*    */       
/* 75 */       this.disable = true;
/* 76 */       this.montant = 0.0D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\AvanceDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */