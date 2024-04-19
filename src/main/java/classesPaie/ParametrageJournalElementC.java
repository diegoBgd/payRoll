/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.faces.event.ValueChangeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParametrageJournalElementC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1958116012983966909L;
/*    */   private int id;
/*    */   private int idElement;
/*    */   private int idEntete;
/*    */   private int signe;
/*    */   private String libelleElment;
/*    */   private boolean added;
/*    */   int sign;
/*    */   
/*    */   public int getId() {
/* 22 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 26 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getIdElement() {
/* 30 */     return this.idElement;
/*    */   }
/*    */   
/*    */   public void setIdElement(int idElement) {
/* 34 */     this.idElement = idElement;
/*    */   }
/*    */   
/*    */   public int getIdEntete() {
/* 38 */     return this.idEntete;
/*    */   }
/*    */   
/*    */   public void setIdEntete(int idEntete) {
/* 42 */     this.idEntete = idEntete;
/*    */   }
/*    */   
/*    */   public String getLibelleElment() {
/* 46 */     return this.libelleElment;
/*    */   }
/*    */   
/*    */   public void setLibelleElment(String libelleElment) {
/* 50 */     this.libelleElment = libelleElment;
/*    */   }
/*    */   
/*    */   public boolean isAdded() {
/* 54 */     return this.added;
/*    */   }
/*    */   
/*    */   public void setAdded(boolean added) {
/* 58 */     this.added = added;
/*    */   }
/*    */   
/*    */   public int getSigne() {
/* 62 */     return this.signe;
/*    */   }
/*    */   
/*    */   public void setSigne(int signe) {
/* 66 */     this.signe = signe;
/*    */   }
/*    */ 
/*    */   
/*    */   public void changeSigne(ValueChangeEvent event) {
/* 71 */     this.signe = ((Integer)event.getNewValue()).intValue();
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageJournalElementC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */