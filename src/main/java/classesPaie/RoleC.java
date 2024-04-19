/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ public class RoleC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8989238819289599136L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Base fonction;
/* 26 */   private List<DroitC> details = new ArrayList<DroitC>();
/*    */   
/*    */   private Historique historique;
/*    */   
/*    */   public int getId() {
/* 31 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 36 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 41 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 46 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 51 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 56 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getFonction() {
/* 61 */     return this.fonction;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFonction(Base fonction) {
/* 66 */     this.fonction = fonction;
/*    */   }
/*    */   
/*    */   public List<DroitC> getDetails() {
/* 70 */     return this.details;
/*    */   }
/*    */   
/*    */   public void setDetails(List<DroitC> details) {
/* 74 */     this.details = details;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 79 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 84 */     this.historique = historique;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\RoleC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */