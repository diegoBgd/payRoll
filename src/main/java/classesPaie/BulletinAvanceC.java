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
/*    */ public class BulletinAvanceC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7608011581455899457L;
/*    */   private int id;
/*    */   private int idBulletin;
/*    */   private int idAvance;
/*    */   private Date dateAvance;
/*    */   private double montantAvance;
/*    */   private String datePrint;
/*    */   private String printMotant;
/*    */   
/*    */   public String getDatePrint() {
/* 27 */     if (this.dateAvance != null)
/*    */     {
/* 29 */       this.datePrint = HelperC.convertDate(this.dateAvance);
/*    */     }
/* 31 */     return this.datePrint;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDatePrint(String datePrint) {
/* 36 */     this.datePrint = datePrint;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 41 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 46 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdBulletin() {
/* 51 */     return this.idBulletin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIdBulletin(int idBulletin) {
/* 56 */     this.idBulletin = idBulletin;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateAvance() {
/* 61 */     return this.dateAvance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateAvance(Date dateAvance) {
/* 66 */     this.dateAvance = dateAvance;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getMontantAvance() {
/* 71 */     return this.montantAvance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMontantAvance(double montantAvance) {
/* 76 */     this.montantAvance = montantAvance;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdAvance() {
/* 81 */     return this.idAvance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIdAvance(int idAvance) {
/* 86 */     this.idAvance = idAvance;
/*    */   }
/*    */   
/*    */   public String getPrintMotant() {
/* 90 */     this.printMotant = HelperC.decimalNumber(getMontantAvance(), 0, true);
/* 91 */     return this.printMotant;
/*    */   }
/*    */   
/*    */   public void setPrintMotant(String printMotant) {
/* 95 */     this.printMotant = printMotant;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\BulletinAvanceC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */