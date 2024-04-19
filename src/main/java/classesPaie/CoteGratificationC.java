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
/*    */ public class CoteGratificationC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7458489972039532189L;
/*    */   private int id;
/*    */   private double cote;
/*    */   private EmployeC employe;
/*    */   private String coteS;
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
/*    */   public double getCote() {
/* 35 */     return this.cote;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCote(double cote) {
/* 40 */     this.cote = cote;
/*    */   }
/*    */ 
/*    */   
/*    */   public EmployeC getEmploye() {
/* 45 */     return this.employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEmploye(EmployeC employe) {
/* 50 */     this.employe = employe;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCoteS() {
/* 55 */     return this.coteS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCoteS(String coteS) {
/* 60 */     this.coteS = coteS;
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


/* Location:              G:\PAIE\!\classesPaie\CoteGratificationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */