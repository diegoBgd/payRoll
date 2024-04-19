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
/*    */ public class AmortissementCreditC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4134389965197973655L;
/*    */   private String trancheRembourse;
/*    */   private String interet;
/*    */   private String capital;
/*    */   private String montantRestant;
/*    */   private String montantTaxe;
/*    */   private String dateRemboursement;
/*    */   private int numero;
/*    */   
/*    */   public String getTrancheRembourse() {
/* 24 */     return this.trancheRembourse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTrancheRembourse(String trancheRembourse) {
/* 29 */     this.trancheRembourse = trancheRembourse;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInteret() {
/* 34 */     return this.interet;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInteret(String interet) {
/* 39 */     this.interet = interet;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCapital() {
/* 44 */     return this.capital;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCapital(String capital) {
/* 49 */     this.capital = capital;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMontantRestant() {
/* 54 */     return this.montantRestant;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMontantRestant(String montantRestant) {
/* 59 */     this.montantRestant = montantRestant;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMontantTaxe() {
/* 64 */     return this.montantTaxe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMontantTaxe(String montantTaxe) {
/* 69 */     this.montantTaxe = montantTaxe;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDateRemboursement() {
/* 74 */     return this.dateRemboursement;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateRemboursement(String dateRemboursement) {
/* 79 */     this.dateRemboursement = dateRemboursement;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumero() {
/* 84 */     return this.numero;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNumero(int numero) {
/* 89 */     this.numero = numero;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\AmortissementCreditC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */