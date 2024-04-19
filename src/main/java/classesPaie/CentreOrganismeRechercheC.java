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
/*    */ public class CentreOrganismeRechercheC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1139667665648814076L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Historique historique;
/*    */   private Base campus;
/*    */   private FaculteInstitutC faculteInstitut;
/*    */   
/*    */   public int getId() {
/* 29 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 34 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 39 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 44 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 49 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 54 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 59 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 64 */     this.historique = historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getCampus() {
/* 69 */     return this.campus;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCampus(Base campus) {
/* 74 */     this.campus = campus;
/*    */   }
/*    */ 
/*    */   
/*    */   public FaculteInstitutC getFaculteInstitut() {
/* 79 */     return this.faculteInstitut;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFaculteInstitut(FaculteInstitutC faculteInstitut) {
/* 84 */     this.faculteInstitut = faculteInstitut;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\CentreOrganismeRechercheC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */