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
/*    */ public class Historique
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8241995439527735882L;
/*    */   private int id;
/*    */   private int idLigne;
/*    */   private Date dateOperation;
/*    */   private OperateurC operateur;
/*    */   private String table;
/*    */   private String operation;
/*    */   
/*    */   public int getId() {
/* 27 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 32 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdLigne() {
/* 37 */     return this.idLigne;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIdLigne(int idLigne) {
/* 42 */     this.idLigne = idLigne;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateOperation() {
/* 47 */     return this.dateOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateOperation(Date dateOperation) {
/* 52 */     this.dateOperation = dateOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   public OperateurC getOperateur() {
/* 57 */     return this.operateur;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOperateur(OperateurC operateur) {
/* 62 */     this.operateur = operateur;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTable() {
/* 67 */     return this.table;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTable(String table) {
/* 72 */     this.table = table;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOperation() {
/* 77 */     return this.operation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOperation(String operation) {
/* 82 */     this.operation = operation;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\Historique.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */