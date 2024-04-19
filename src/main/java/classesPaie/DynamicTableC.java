/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class DynamicTableC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4315108032780326600L;
/*    */   private String nomColone;
/*    */   private String valeur;
/*    */   private String[] colones;
/*    */   
/*    */   public String getNomColone() {
/* 15 */     return this.nomColone;
/*    */   }
/*    */   public void setNomColone(String nomColone) {
/* 18 */     this.nomColone = nomColone;
/*    */   }
/*    */   public String[] getColones() {
/* 21 */     return this.colones;
/*    */   }
/*    */   
/*    */   public void setColones(String[] colones) {
/* 25 */     this.colones = colones;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getValeur() {
/* 37 */     return this.valeur;
/*    */   }
/*    */   
/*    */   public void setValeur(String valeur) {
/* 41 */     this.valeur = valeur;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\DynamicTableC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */