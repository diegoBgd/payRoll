/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmployeDetailContratC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7331261073067736421L;
/*    */   private int id;
/*    */   private Date dateDebutContrat;
/*    */   private Date dateFinContrat;
/*    */   private Historique historique;
/*    */   private EmployeC employe;
/*    */   private Base contrat;
/*    */   
/*    */   public int getId() {
/* 24 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 28 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Date getDateDebutContrat() {
/* 32 */     return this.dateDebutContrat;
/*    */   }
/*    */   
/*    */   public void setDateDebutContrat(Date dateDebutContrat) {
/* 36 */     this.dateDebutContrat = dateDebutContrat;
/*    */   }
/*    */   
/*    */   public Date getDateFinContrat() {
/* 40 */     return this.dateFinContrat;
/*    */   }
/*    */   
/*    */   public void setDateFinContrat(Date dateFinContrat) {
/* 44 */     this.dateFinContrat = dateFinContrat;
/*    */   }
/*    */   
/*    */   public Historique getHistorique() {
/* 48 */     return this.historique;
/*    */   }
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 52 */     this.historique = historique;
/*    */   }
/*    */   
/*    */   public EmployeC getEmploye() {
/* 56 */     return this.employe;
/*    */   }
/*    */   
/*    */   public void setEmploye(EmployeC employe) {
/* 60 */     this.employe = employe;
/*    */   }
/*    */   
/*    */   public Base getContrat() {
/* 64 */     return this.contrat;
/*    */   }
/*    */   
/*    */   public void setContrat(Base contrat) {
/* 68 */     this.contrat = contrat;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\EmployeDetailContratC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */