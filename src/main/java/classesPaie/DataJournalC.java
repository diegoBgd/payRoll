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
/*    */ public class DataJournalC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3866906857652128606L;
/*    */   private EmployeC employe;
/*    */   private String valeur;
/*    */   private String colonne;
/*    */   
/*    */   public EmployeC getEmploye() {
/* 21 */     return this.employe;
/*    */   }
/*    */   
/*    */   public void setEmploye(EmployeC employe) {
/* 25 */     this.employe = employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValeur() {
/* 30 */     return this.valeur;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValeur(String valeur) {
/* 35 */     this.valeur = valeur;
/*    */   }
/*    */   
/*    */   public String getColonne() {
/* 39 */     return this.colonne;
/*    */   }
/*    */   
/*    */   public void setColonne(String colonne) {
/* 43 */     this.colonne = colonne;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\DataJournalC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */