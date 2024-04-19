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
/*     */ public class DetailPrimeEmployeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8727764517753702677L;
/*     */   private int id;
/*     */   private int idParametre;
/*     */   private int index;
/*     */   private double montant;
/*     */   private double taux;
/*     */   private String montantS;
/*     */   private EmployeC employe;
/*     */   private PrimeIndemniteC prime;
/*     */   private PrimeIndemniteC indemnite;
/*     */   private Historique historique;
/*     */   private boolean existe,bloque;
/*     */   private boolean calculated;
/*     */   
/*     */   public int getId() {
/*  29 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  34 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/*  39 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/*  44 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantS() {
/*  49 */     return this.montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantS(String montantS) {
/*  54 */     this.montantS = montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  59 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  64 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getPrime() {
/*  69 */     return this.prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/*  74 */     this.prime = prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getIndemnite() {
/*  79 */     return this.indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndemnite(PrimeIndemniteC indemnite) {
/*  84 */     this.indemnite = indemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  89 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  94 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/*  99 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 104 */     this.index = index;
/*     */   }
/*     */   
/*     */   public double getTaux() {
/* 108 */     return this.taux;
/*     */   }
/*     */   
/*     */   public void setTaux(double taux) {
/* 112 */     this.taux = taux;
/*     */   }
/*     */   
/*     */   public boolean isCalculated() {
/* 116 */     return this.calculated;
/*     */   }
/*     */   
/*     */   public void setCalculated(boolean calculated) {
/* 120 */     this.calculated = calculated;
/*     */   }
/*     */   
/*     */   public int getIdParametre() {
/* 124 */     return this.idParametre;
/*     */   }
/*     */   
/*     */   public void setIdParametre(int idParametre) {
/* 128 */     this.idParametre = idParametre;
/*     */   }

			
			public boolean isBloque() {
				return bloque;
			}
			public void setBloque(boolean bloque) {
				this.bloque = bloque;
			}
/*     */   public void changeMontant() {
/*     */     try {
/* 135 */       setMontant(Double.valueOf(this.montantS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     }
/* 137 */     catch (Exception e) {
/*     */       
/* 139 */       setMontant(0.0D);
/*     */     } 
/* 141 */     this.montantS = HelperC.TraitementMontant.getMontantFormate(getMontant(), 0);
/* 142 */     setMontant(Integer.valueOf(this.montantS.replace("-", "").replace(" ", "").replace(",", ".").trim()).intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/* 147 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/* 152 */     this.existe = existe;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailPrimeEmployeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */