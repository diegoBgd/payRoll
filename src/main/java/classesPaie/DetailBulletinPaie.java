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
/*    */ public class DetailBulletinPaie
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1353693407426445312L;
/*    */   private int id;
/*    */   private BulletinPaieC bulletinPaie;
/*    */   private CreditC creditAvance;
/*    */   private double capital;
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
/*    */   public BulletinPaieC getBulletinPaie() {
/* 35 */     return this.bulletinPaie;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBulletinPaie(BulletinPaieC bulletinPaie) {
/* 40 */     this.bulletinPaie = bulletinPaie;
/*    */   }
/*    */ 
/*    */   
/*    */   public CreditC getCreditAvance() {
/* 45 */     return this.creditAvance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCreditAvance(CreditC creditAvance) {
/* 50 */     this.creditAvance = creditAvance;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getCapital() {
/* 55 */     return this.capital;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCapital(double capital) {
/* 60 */     this.capital = capital;
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


/* Location:              G:\PAIE\!\classesPaie\DetailBulletinPaie.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */