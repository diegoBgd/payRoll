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
/*    */ public class DepartementSectionC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7284002736162675473L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Historique historique;
/*    */   private FaculteInstitutC faculteInstitut;
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
/*    */   public FaculteInstitutC getFaculteInstitut() {
/* 68 */     return this.faculteInstitut;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFaculteInstitut(FaculteInstitutC faculteInstitut) {
/* 73 */     this.faculteInstitut = faculteInstitut;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\DepartementSectionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */