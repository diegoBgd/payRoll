/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BulletinCreditC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1191699993152399825L;
/*    */   private int id;
/*    */   private int idBulletin;
/*    */   private String noDossier,libelle;
/*    */   private String printMontant;
/*    */   private double montantTranche;
/*    */   private double tranchAdded;
/*    */   private double montant;
/*    */   
/*    */   public int getId() {
/* 23 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 28 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdBulletin() {
/* 33 */     return this.idBulletin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIdBulletin(int idBulletin) {
/* 38 */     this.idBulletin = idBulletin;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNoDossier() {
/* 43 */     return this.noDossier;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNoDossier(String noDossier) {
/* 48 */     this.noDossier = noDossier;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getMontantTranche() {
/* 53 */     return this.montantTranche;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMontantTranche(double montantTranche) {
/* 58 */     this.montantTranche = montantTranche;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getTranchAdded() {
/* 63 */     return this.tranchAdded;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTranchAdded(double tranchAdded) {
/* 68 */     this.tranchAdded = tranchAdded;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getMontant() {
/* 73 */     this.montant = this.tranchAdded + this.montantTranche;
/* 74 */     return this.montant;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMontant(double montant) {
/* 79 */     this.montant = montant;
/*    */   }
/*    */   
/*    */   public String getPrintMontant() {
/* 83 */     this.printMontant = HelperC.decimalNumber(getMontant(), 0, true);
/* 84 */     return this.printMontant;
/*    */   }
/*    */   
/*    */   public void setPrintMontant(String printMontant) {
/* 88 */     this.printMontant = printMontant;
/*    */   }
			public String getLibelle() {
				return libelle;
			}
			public void setLibelle(String libelle) {
				this.libelle = libelle;
			}
         
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\BulletinCreditC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */