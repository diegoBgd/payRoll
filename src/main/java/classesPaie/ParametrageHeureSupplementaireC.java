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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParametrageHeureSupplementaireC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -140091060844344844L;
/*    */   private int id;
/*    */   private String numero;
/*    */   private boolean actif;
/*    */   private Historique historique;
/* 24 */   private List<DetailParametrageHeureSupplementaireC> listDetailHeureSupplementaire = new ArrayList<DetailParametrageHeureSupplementaireC>();
/* 25 */   private List<DetailParametrageHeureSupplementaireC> listDetailHeureSupplementaireDeleted = new ArrayList<DetailParametrageHeureSupplementaireC>();
/*    */ 
/*    */ 
/*    */   
/*    */   public int getId() {
/* 30 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 35 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   
 
		public boolean isActif() {
			return actif;
		}
		public String getNumero() {
			return numero;
		}
		public void setNumero(String numero) {
			this.numero = numero;
		}
		public void setActif(boolean actif) {
			this.actif = actif;
		}
/*    */   public Historique getHistorique() {
/* 70 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 75 */     this.historique = historique;
/*    */   }
/*    */   
/*    */   public List<DetailParametrageHeureSupplementaireC> getListDetailHeureSupplementaire() {
/* 79 */     return this.listDetailHeureSupplementaire;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListDetailHeureSupplementaire(List<DetailParametrageHeureSupplementaireC> listDetailHeureSupplementaire) {
/* 84 */     this.listDetailHeureSupplementaire = listDetailHeureSupplementaire;
/*    */   }
/*    */   
/*    */   public List<DetailParametrageHeureSupplementaireC> getListDetailHeureSupplementaireDeleted() {
/* 88 */     return this.listDetailHeureSupplementaireDeleted;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListDetailHeureSupplementaireDeleted(List<DetailParametrageHeureSupplementaireC> listDetailHeureSupplementaireDeleted) {
/* 93 */     this.listDetailHeureSupplementaireDeleted = listDetailHeureSupplementaireDeleted;
/*    */   }
/*    */ }

