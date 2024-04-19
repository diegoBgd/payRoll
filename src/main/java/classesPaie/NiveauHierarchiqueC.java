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
/*    */ public class NiveauHierarchiqueC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7021278099806345456L;
/*    */   private int id;
/*    */   private int niveau;
/*    */   private String code;
/*    */   private String designation;
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
/*    */   public int getNiveau() {
/* 35 */     return this.niveau;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNiveau(int niveau) {
/* 40 */     this.niveau = niveau;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 45 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 50 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 55 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 60 */     this.designation = designation;
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


/* Location:              G:\PAIE\!\classesPaie\NiveauHierarchiqueC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */