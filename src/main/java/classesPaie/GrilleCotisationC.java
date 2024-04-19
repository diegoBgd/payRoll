/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GrilleCotisationC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4133351757643013496L;
/*    */   private int id;
/*    */   private double coefficient;
/*    */   private Date dateDebut;
/*    */   private Date dateFin;
/* 20 */   private List<DetailGrilleCotisationC> listDetail = new ArrayList<DetailGrilleCotisationC>();
/* 21 */   private List<DetailGrilleCotisationC> listDeleted = new ArrayList<DetailGrilleCotisationC>();
/*    */ 
/*    */ 
/*    */   
/*    */   public int getId() {
/* 26 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 31 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getCoefficient() {
/* 36 */     return this.coefficient;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCoefficient(double coefficient) {
/* 41 */     this.coefficient = coefficient;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateDebut() {
/* 46 */     return this.dateDebut;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateDebut(Date dateDebut) {
/* 51 */     this.dateDebut = dateDebut;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateFin() {
/* 56 */     return this.dateFin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateFin(Date dateFin) {
/* 61 */     this.dateFin = dateFin;
/*    */   }
/*    */   
/*    */   public List<DetailGrilleCotisationC> getListDetail() {
/* 65 */     return this.listDetail;
/*    */   }
/*    */   
/*    */   public void setListDetail(List<DetailGrilleCotisationC> listDetail) {
/* 69 */     this.listDetail = listDetail;
/*    */   }
/*    */   
/*    */   public List<DetailGrilleCotisationC> getListDeleted() {
/* 73 */     return this.listDeleted;
/*    */   }
/*    */   
/*    */   public void setListDeleted(List<DetailGrilleCotisationC> listDeleted) {
/* 77 */     this.listDeleted = listDeleted;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\GrilleCotisationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */