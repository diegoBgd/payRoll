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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RepartitionFonctionC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2559516413284084236L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Historique historique;
/* 26 */   private List<RepartionFonctionDetailC> listeDetail = new ArrayList<RepartionFonctionDetailC>();
/* 27 */   private List<RepartionFonctionDetailC> listeDetailDeleted = new ArrayList<RepartionFonctionDetailC>();
/*    */ 
/*    */ 
/*    */   
/*    */   public int getId() {
/* 32 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 37 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 42 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 47 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 52 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 57 */     this.designation = designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<RepartionFonctionDetailC> getListeDetail() {
/* 62 */     return this.listeDetail;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListeDetail(List<RepartionFonctionDetailC> listeDetail) {
/* 67 */     this.listeDetail = listeDetail;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<RepartionFonctionDetailC> getListeDetailDeleted() {
/* 72 */     return this.listeDetailDeleted;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListeDetailDeleted(List<RepartionFonctionDetailC> listeDetailDeleted) {
/* 77 */     this.listeDetailDeleted = listeDetailDeleted;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 82 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 87 */     this.historique = historique;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\RepartitionFonctionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */