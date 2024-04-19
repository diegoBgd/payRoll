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
/*    */ public class ConditionRecrutementDetailC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6642149213942091301L;
/*    */   private int id;
/*    */   private boolean rempli;
/*    */   private Base personnel;
/*    */   private Base critereRecrutement;
/*    */   private ConditionRecrutementC entete;
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
/*    */   public boolean isRempli() {
/* 35 */     return this.rempli;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRempli(boolean rempli) {
/* 40 */     this.rempli = rempli;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getPersonnel() {
/* 45 */     return this.personnel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPersonnel(Base personnel) {
/* 50 */     this.personnel = personnel;
/*    */   }
/*    */ 
/*    */   
/*    */   public Base getCritereRecrutement() {
/* 55 */     return this.critereRecrutement;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCritereRecrutement(Base critereRecrutement) {
/* 60 */     this.critereRecrutement = critereRecrutement;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConditionRecrutementC getEntete() {
/* 65 */     return this.entete;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEntete(ConditionRecrutementC entete) {
/* 70 */     this.entete = entete;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ConditionRecrutementDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */