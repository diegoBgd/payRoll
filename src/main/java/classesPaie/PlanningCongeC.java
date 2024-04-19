/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlanningCongeC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1877413862387418376L;
/*    */   private int id;
/*    */   private Date dateDebut;
/*    */   private Date dateFin;
/*    */   private String dateDebS;
/*    */   private String dateFiS;
/*    */   private EmployeC employe;
/*    */   private Base typeConge;
/*    */   private OperateurC operateurCreation;
/*    */   private Historique historic;
/*    */   
/*    */   public int getId() {
/* 24 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 28 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Date getDateDebut() {
/* 32 */     return this.dateDebut;
/*    */   }
/*    */   
/*    */   public void setDateDebut(Date dateDebut) {
/* 36 */     this.dateDebut = dateDebut;
/*    */   }
/*    */   
/*    */   public Date getDateFin() {
/* 40 */     return this.dateFin;
/*    */   }
/*    */   
/*    */   public void setDateFin(Date dateFin) {
/* 44 */     this.dateFin = dateFin;
/*    */   }
/*    */   
/*    */   public EmployeC getEmploye() {
/* 48 */     return this.employe;
/*    */   }
/*    */   
/*    */   public void setEmploye(EmployeC employe) {
/* 52 */     this.employe = employe;
/*    */   }
/*    */   
/*    */   public Base getTypeConge() {
/* 56 */     return this.typeConge;
/*    */   }
/*    */   
/*    */   public void setTypeConge(Base typeConge) {
/* 60 */     this.typeConge = typeConge;
/*    */   }
/*    */   
/*    */   public OperateurC getOperateurCreation() {
/* 64 */     return this.operateurCreation;
/*    */   }
/*    */   
/*    */   public void setOperateurCreation(OperateurC operateurCreation) {
/* 68 */     this.operateurCreation = operateurCreation;
/*    */   }
/*    */   
/*    */   public Historique getHistoric() {
/* 72 */     return this.historic;
/*    */   }
/*    */   
/*    */   public void setHistoric(Historique historic) {
/* 76 */     this.historic = historic;
/*    */   }
/*    */   
/*    */   public String getDateDebS() {
/* 80 */     return this.dateDebS;
/*    */   }
/*    */   
/*    */   public void setDateDebS(String dateDebS) {
/* 84 */     this.dateDebS = dateDebS;
/*    */   }
/*    */   
/*    */   public String getDateFiS() {
/* 88 */     return this.dateFiS;
/*    */   }
/*    */   
/*    */   public void setDateFiS(String dateFiS) {
/* 92 */     this.dateFiS = dateFiS;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\PlanningCongeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */