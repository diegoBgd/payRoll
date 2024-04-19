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
/*    */ public class OrganismeSupportantBaseSalarialC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7015928892045967341L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private String compteBase;
/*    */   private Historique historique;
/*    */   
/*    */   public int getId() {
/* 25 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 30 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 35 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 40 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 45 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 50 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCompteBase() {
/* 55 */     return this.compteBase;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCompteBase(String compteBase) {
/* 60 */     this.compteBase = compteBase;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 65 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 70 */     this.historique = historique;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\OrganismeSupportantBaseSalarialC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */