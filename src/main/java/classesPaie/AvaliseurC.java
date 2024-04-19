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
/*    */ public class AvaliseurC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8091634780255622680L;
/*    */   private int id;
/*    */   private int idEmploye;
/*    */   private int idCredit;
/*    */   private String nomAvaliseur;
/*    */   private String codeAvaliseur;
/*    */   private EmployeC employe;
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
/*    */   public int getIdEmploye() {
/* 39 */     return this.idEmploye;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIdEmploye(int idEmploye) {
/* 44 */     this.idEmploye = idEmploye;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomAvaliseur() {
/* 49 */     return this.nomAvaliseur;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNomAvaliseur(String nomAvaliseur) {
/* 54 */     this.nomAvaliseur = nomAvaliseur;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCodeAvaliseur() {
/* 59 */     return this.codeAvaliseur;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCodeAvaliseur(String codeAvaliseur) {
/* 64 */     this.codeAvaliseur = codeAvaliseur;
/*    */   }
/*    */ 
/*    */   
/*    */   public EmployeC getEmploye() {
/* 69 */     return this.employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEmploye(EmployeC employe) {
/* 74 */     this.employe = employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdCredit() {
/* 79 */     return this.idCredit;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIdCredit(int idCredit) {
/* 84 */     this.idCredit = idCredit;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\AvaliseurC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */