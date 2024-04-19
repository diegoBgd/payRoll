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
/*    */ public class FilieresOptionsC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -224980853991299994L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Historique historique;
/*    */   private DepartementSectionC departementSection;
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
/*    */   public DepartementSectionC getDepartementSection() {
/* 68 */     return this.departementSection;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDepartementSection(DepartementSectionC departementSection) {
/* 73 */     this.departementSection = departementSection;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\FilieresOptionsC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */