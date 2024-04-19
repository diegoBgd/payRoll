/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrimeIndemniteC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 79075396261986231L;
/*     */   private int id;
/*     */   private String code;
/*     */   private String designation;
/*     */   private String typePrime;
/*     */   private String prefixeComptable;
/*     */   private boolean soumisCotisation;
/*     */   private boolean imposable;
/*     */   private boolean hide;
			private boolean retraite;
/*     */   private Historique historique;
/*     */   private double baseCalcul;
/*     */   private double tauxPrime;
/*     */   private double montantMax;
/*     */   private double montantMin;
/*     */   
/*     */   public String getPrefixeComptable() {
/*  34 */     return this.prefixeComptable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrefixeComptable(String prefixeComptable) {
/*  39 */     this.prefixeComptable = prefixeComptable;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  43 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  48 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  53 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  58 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/*  63 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/*  68 */     this.designation = designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTypePrime() {
/*  73 */     return this.typePrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypePrime(String typePrime) {
/*  78 */     this.typePrime = typePrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  83 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  88 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public double getBaseCalcul() {
/*  92 */     return this.baseCalcul;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseCalcul(double baseCalcul) {
/*  97 */     this.baseCalcul = baseCalcul;
/*     */   }
/*     */   public double getTauxPrime() {
/* 100 */     return this.tauxPrime;
/*     */   }
/*     */   public void setTauxPrime(double tauxPrime) {
/* 103 */     this.tauxPrime = tauxPrime;
/*     */   }
/*     */   
/*     */   public double getMontantMax() {
/* 107 */     return this.montantMax;
/*     */   }
/*     */   
/*     */   public void setMontantMax(double montantMax) {
/* 111 */     this.montantMax = montantMax;
/*     */   }
/*     */   
/*     */   public double getMontantMin() {
/* 115 */     return this.montantMin;
/*     */   }
/*     */   
/*     */   public void setMontantMin(double montantMin) {
/* 119 */     this.montantMin = montantMin;
/*     */   }
/*     */   
/*     */   public boolean isSoumisCotisation() {
/* 123 */     return this.soumisCotisation;
/*     */   }
/*     */   
/*     */   public void setSoumisCotisation(boolean soumisCotisation) {
/* 127 */     this.soumisCotisation = soumisCotisation;
/*     */   }
/*     */   
/*     */   public boolean isImposable() {
/* 131 */     return this.imposable;
/*     */   }
/*     */   
/*     */   public void setImposable(boolean imposable) {
/* 135 */     this.imposable = imposable;
/*     */   }
/*     */   
/*     */   public boolean isHide() {
/* 139 */     return this.hide;
/*     */   }
/*     */   
/*     */   public void setHide(boolean hide) {
/* 143 */     this.hide = hide;
/*     */   }
			public boolean isRetraite() {
				return retraite;
			}
			public void setRetraite(boolean retraite) {
				this.retraite = retraite;
			}

/*     */ }


/* Location:              G:\PAIE\!\classesPaie\PrimeIndemniteC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */