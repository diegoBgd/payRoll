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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SaisiePresenceC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2877475969362762521L;
/*    */   private int id;
/*    */   private int duree;
/*    */   private Date date;
/*    */   private EmployeC employe;
/*    */   private Base departement;
/*    */   private String dateS;
/*    */   private Historique historique;
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
/*    */   public int getDuree() {
/* 38 */     return this.duree;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDuree(int duree) {
/* 43 */     this.duree = duree;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDate() {
/* 48 */     return this.date;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDate(Date date) {
/* 53 */     this.date = date;
/*    */   }
/*    */ 
/*    */   
/*    */   public EmployeC getEmploye() {
/* 58 */     return this.employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEmploye(EmployeC employe) {
/* 63 */     this.employe = employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getDepartement() {
/* 68 */     return this.departement;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDepartement(Base departement) {
/* 73 */     this.departement = departement;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDateS() {
/* 78 */     return this.dateS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateS(String dateS) {
/* 83 */     this.dateS = dateS;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 88 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 93 */     this.historique = historique;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\SaisiePresenceC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */