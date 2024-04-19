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
/*    */ public class ConditionRecrutementC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6104212712347946217L;
/*    */   private int id;
/*    */   private String numeroDecision;
/*    */   private Date dateDebApplication;
/*    */   private Date dateFinApplication;
/* 20 */   private List<ConditionRecrutementDetailC> listDetailCondition = new ArrayList<ConditionRecrutementDetailC>();
/* 21 */   private List<ConditionRecrutementDetailC> listDetailConditionDeleted = new ArrayList<ConditionRecrutementDetailC>();
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
/*    */   public String getNumeroDecision() {
/* 36 */     return this.numeroDecision;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNumeroDecision(String numeroDecision) {
/* 41 */     this.numeroDecision = numeroDecision;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateDebApplication() {
/* 46 */     return this.dateDebApplication;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateDebApplication(Date dateDebApplication) {
/* 51 */     this.dateDebApplication = dateDebApplication;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateFinApplication() {
/* 56 */     return this.dateFinApplication;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateFinApplication(Date dateFinApplication) {
/* 61 */     this.dateFinApplication = dateFinApplication;
/*    */   }
/*    */   
/*    */   public List<ConditionRecrutementDetailC> getListDetailCondition() {
/* 65 */     return this.listDetailCondition;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListDetailCondition(List<ConditionRecrutementDetailC> listDetailCondition) {
/* 70 */     this.listDetailCondition = listDetailCondition;
/*    */   }
/*    */   
/*    */   public List<ConditionRecrutementDetailC> getListDetailConditionDeleted() {
/* 74 */     return this.listDetailConditionDeleted;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListDetailConditionDeleted(List<ConditionRecrutementDetailC> listDetailConditionDeleted) {
/* 79 */     this.listDetailConditionDeleted = listDetailConditionDeleted;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ConditionRecrutementC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */