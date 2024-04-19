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
/*    */ public class FinCarriereDetailIndemniteC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8098122230538362578L;
/*    */   private int id;
/*    */   private int indexe;
/*    */   private double montant;
/*    */   private Historique historique;
/*    */   private String montantS;
/*    */   private boolean existe;
/*    */   private FinCarriereC entete;
/*    */   private PrimeIndemniteC indemnite;
/*    */   
/*    */   public int getId() {
/* 27 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 31 */     this.id = id;
/*    */   }
/*    */   
/*    */   public double getMontant() {
/* 35 */     return this.montant;
/*    */   }
/*    */   
/*    */   public void setMontant(double montant) {
/* 39 */     this.montant = montant;
/*    */   }
/*    */   
/*    */   public Historique getHistorique() {
/* 43 */     return this.historique;
/*    */   }
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 47 */     this.historique = historique;
/*    */   }
/*    */   
/*    */   public FinCarriereC getEntete() {
/* 51 */     return this.entete;
/*    */   }
/*    */   
/*    */   public void setEntete(FinCarriereC entete) {
/* 55 */     this.entete = entete;
/*    */   }
/*    */   
/*    */   public PrimeIndemniteC getIndemnite() {
/* 59 */     return this.indemnite;
/*    */   }
/*    */   
/*    */   public void setIndemnite(PrimeIndemniteC indemnite) {
/* 63 */     this.indemnite = indemnite;
/*    */   }
/*    */   
/*    */   public int getIndexe() {
/* 67 */     return this.indexe;
/*    */   }
/*    */   
/*    */   public void setIndexe(int indexe) {
/* 71 */     this.indexe = indexe;
/*    */   }
/*    */   
/*    */   public String getMontantS() {
/* 75 */     return this.montantS;
/*    */   }
/*    */   
/*    */   public void setMontantS(String montantS) {
/* 79 */     this.montantS = montantS;
/*    */   }
/*    */   
/*    */   public boolean isExiste() {
/* 83 */     return this.existe;
/*    */   }
/*    */   
/*    */   public void setExiste(boolean existe) {
/* 87 */     this.existe = existe;
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\FinCarriereDetailIndemniteC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */