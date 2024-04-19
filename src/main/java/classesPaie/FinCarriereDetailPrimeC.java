/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;

/*    */ public class FinCarriereDetailPrimeC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2898858119167601453L;
/*    */   private int id,idParm;
/*    */   private int indexe;
/*    */   private double montant;
/*    */   private Historique historique;
/*    */   private String libellePrime;
/*    */   private boolean existe;
/*    */   private FinCarriereC entete;
/*    */   private PrimeIndemniteC prime;
/*    */   
/*    */   public int getId() {
/* 26 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 30 */     this.id = id;
/*    */   }
/*    */   
/*    */   public double getMontant() {
/* 34 */     return this.montant;
/*    */   }
/*    */   
/*    */   public void setMontant(double montant) {
/* 38 */     this.montant = montant;
/*    */   }
/*    */   
/*    */   public Historique getHistorique() {
/* 42 */     return this.historique;
/*    */   }
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 46 */     this.historique = historique;
/*    */   }
/*    */   
/*    */   public FinCarriereC getEntete() {
/* 50 */     return this.entete;
/*    */   }
/*    */   
/*    */   public void setEntete(FinCarriereC entete) {
/* 54 */     this.entete = entete;
/*    */   }
/*    */   
/*    */   public PrimeIndemniteC getPrime() {
/* 58 */     return this.prime;
/*    */   }
/*    */   
/*    */   public void setPrime(PrimeIndemniteC prime) {
/* 62 */     this.prime = prime;
/*    */   }
/*    */   
/*    */   public int getIndexe() {
/* 66 */     return this.indexe;
/*    */   }
/*    */   
/*    */   public void setIndexe(int indexe) {
/* 70 */     this.indexe = indexe;
/*    */   }
/*    */   
/*    */  
/*    */   public boolean isExiste() {
/* 82 */     return this.existe;
/*    */   }
/*    */   
/*    */   public void setExiste(boolean existe) {
/* 86 */     this.existe = existe;
/*    */   }
public String getLibellePrime() {
	return libellePrime;
}
public void setLibellePrime(String libellePrime) {
	this.libellePrime = libellePrime;
}
public int getIdParm() {
	return idParm;
}
public void setIdParm(int idParm) {
	this.idParm = idParm;
}


/*    */ }


/* Location:              G:\PAIE\!\classesPaie\FinCarriereDetailPrimeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */