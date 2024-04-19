/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParametrageJournalC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 9059266199618346320L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String libelle;
/* 20 */   private List<ParametrageJournalDetailC> listeDetail = new ArrayList<ParametrageJournalDetailC>();
/* 21 */   private List<ParametrageJournalDetailC> listDeleted = new ArrayList<ParametrageJournalDetailC>();
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
/*    */   public String getCode() {
/* 36 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 41 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLibelle() {
/* 46 */     return this.libelle;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLibelle(String libelle) {
/* 51 */     this.libelle = libelle;
/*    */   }
/*    */   
/*    */   public List<ParametrageJournalDetailC> getListeDetail() {
/* 55 */     return this.listeDetail;
/*    */   }
/*    */   
/*    */   public void setListeDetail(List<ParametrageJournalDetailC> listeDetail) {
/* 59 */     this.listeDetail = listeDetail;
/*    */   }
/*    */   
/*    */   public List<ParametrageJournalDetailC> getListDeleted() {
/* 63 */     return this.listDeleted;
/*    */   }
/*    */   
/*    */   public void setListDeleted(List<ParametrageJournalDetailC> listDeleted) {
/* 67 */     this.listDeleted = listDeleted;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageJournalC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */