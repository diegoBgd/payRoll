/*    */ package classesPaie;
/*    */ 
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
/*    */ public class DirectionUniversiteC
/*    */   extends Base
/*    */ {
/*    */   private static final long serialVersionUID = 4662812143241752185L;
/*    */   private int id;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Historique historique;
/* 25 */   private List<DirectionUniversiteDetailC> listeDetail = new ArrayList<DirectionUniversiteDetailC>();
/* 26 */   private List<DirectionUniversiteDetailC> listeDetailDeleted = new ArrayList<DirectionUniversiteDetailC>();
/*    */ 
/*    */ 
/*    */   
/*    */   public int getId() {
/* 31 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 36 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 41 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 46 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDesignation() {
/* 51 */     return this.designation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDesignation(String designation) {
/* 56 */     this.designation = designation;
/*    */   }
/*    */   
/*    */   public List<DirectionUniversiteDetailC> getListeDetail() {
/* 60 */     return this.listeDetail;
/*    */   }
/*    */   
/*    */   public void setListeDetail(List<DirectionUniversiteDetailC> listeDetail) {
/* 64 */     this.listeDetail = listeDetail;
/*    */   }
/*    */   
/*    */   public List<DirectionUniversiteDetailC> getListeDetailDeleted() {
/* 68 */     return this.listeDetailDeleted;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListeDetailDeleted(List<DirectionUniversiteDetailC> listeDetailDeleted) {
/* 73 */     this.listeDetailDeleted = listeDetailDeleted;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 78 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 83 */     this.historique = historique;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\DirectionUniversiteC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */