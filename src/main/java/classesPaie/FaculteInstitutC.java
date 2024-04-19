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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FaculteInstitutC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1069093849975401360L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Historique historique;
/*    */   private Base campus;
/*    */   
/*    */   public int getId() {
/* 28 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 33 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 38 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 43 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 48 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 53 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 58 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 63 */     this.historique = historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getCampus() {
/* 68 */     return this.campus;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCampus(Base campus) {
/* 73 */     this.campus = campus;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\FaculteInstitutC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */