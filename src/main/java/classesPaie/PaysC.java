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
/*    */ public class PaysC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6277739791403647026L;
/*    */   private int id;
/*    */   private String designation;
/*    */   private String continent;
/*    */   private String nationalite;
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
/*    */   public String getDesignation() {
/* 35 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 40 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getContinent() {
/* 45 */     return this.continent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContinent(String continent) {
/* 50 */     this.continent = continent;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNationalite() {
/* 55 */     return this.nationalite;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNationalite(String nationalite) {
/* 60 */     this.nationalite = nationalite;
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


/* Location:              G:\PAIE\!\classesPaie\PaysC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */