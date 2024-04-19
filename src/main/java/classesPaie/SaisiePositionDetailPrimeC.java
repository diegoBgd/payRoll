/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;

/*     */ 
/*     */ public class SaisiePositionDetailPrimeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7495182057595167337L;
/*     */   private int id,idParm;
/*     */   private int indexe;
/*     */   private double montant;
/*     */   private String montantS;
/*     */   private Historique historique;
/*     */   private boolean existe,kept;
/*     */   private SaisiePositionEmployeC saisie;
/*     */   private PrimeIndemniteC prime;
/*     */   
/*     */   public int getId() {
/*  31 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  36 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/*  41 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/*  46 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  51 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  56 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public SaisiePositionEmployeC getSaisie() {
/*  61 */     return this.saisie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSaisie(SaisiePositionEmployeC saisie) {
/*  66 */     this.saisie = saisie;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getPrime() {
/*  71 */     return this.prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/*  76 */     this.prime = prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantS() {
/*  81 */     return this.montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantS(String montantS) {
/*  86 */     this.montantS = montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/*  91 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/*  96 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexe() {
/* 101 */     return this.indexe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexe(int indexe) {
/* 106 */     this.indexe = indexe;
/*     */   }

			public int getIdParm() {
				return idParm;
			}
			public void setIdParm(int idParm) {
				this.idParm = idParm;
			}
			public boolean isKept() {
				return kept;
			}
			public void setKept(boolean kept) {
				this.kept = kept;
			} 
					
			public void checkPrime() {
				
			}
}


