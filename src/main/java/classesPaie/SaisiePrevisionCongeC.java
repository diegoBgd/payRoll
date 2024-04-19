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
/*    */ public class SaisiePrevisionCongeC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7675248847449367779L;
/*    */   private int id;
/*    */   private int joursConges;
/*    */   private int joursReportes;
/*    */   private EmployeC employe;
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
/*    */   public int getJoursConges() {
/* 35 */     return this.joursConges;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setJoursConges(int joursConges) {
/* 40 */     this.joursConges = joursConges;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getJoursReportes() {
/* 45 */     return this.joursReportes;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setJoursReportes(int joursReportes) {
/* 50 */     this.joursReportes = joursReportes;
/*    */   }
/*    */ 
/*    */   
/*    */   public EmployeC getEmploye() {
/* 55 */     return this.employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEmploye(EmployeC employe) {
/* 60 */     this.employe = employe;
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


/* Location:              G:\PAIE\!\classesPaie\SaisiePrevisionCongeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */