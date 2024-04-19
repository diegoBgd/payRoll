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
/*    */ public class CompteEmployeC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3599248323083752852L;
/*    */   private int id;
/*    */   private EmployeC employe;
/*    */   private String pseudo;
/*    */   private String motPasse;
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
/*    */   public EmployeC getEmploye() {
/* 35 */     return this.employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEmploye(EmployeC employe) {
/* 40 */     this.employe = employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPseudo() {
/* 45 */     return this.pseudo;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPseudo(String pseudo) {
/* 50 */     this.pseudo = pseudo;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMotPasse() {
/* 55 */     return this.motPasse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMotPasse(String motPasse) {
/* 60 */     this.motPasse = motPasse;
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


/* Location:              G:\PAIE\!\classesPaie\CompteEmployeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */