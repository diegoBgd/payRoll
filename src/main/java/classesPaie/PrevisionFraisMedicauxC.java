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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrevisionFraisMedicauxC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1573497623030045574L;
/*     */   private int id;
/*     */   private int nombrePersonnesAcharge;
/*     */   private EmployeC employe;
/*     */   private double previsionParEmploye;
/*     */   private double previsionParPersonneACharge;
/*  33 */   private double referenceConge = 12.0D;
/*  34 */   private double referenceFraisMedicaux = 12.0D; private double montantANouveau; private double montantPeriode; private String designation;
/*     */   private String previsionParEmployeS;
/*     */   private String previsionParPersonneAChargeS;
/*     */   
/*     */   public int getId() {
/*  39 */     return this.id;
/*     */   }
/*     */   private String referenceCongeS; private String referenceFraisMedicauxS; private String montantANouveauS; private String montantPeriodeS; private Historique historique;
/*     */   
/*     */   public void setId(int id) {
/*  44 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  49 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  54 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPrevisionParEmploye() {
/*  59 */     return this.previsionParEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrevisionParEmploye(double previsionParEmploye) {
/*  64 */     this.previsionParEmploye = previsionParEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPrevisionParPersonneACharge() {
/*  69 */     return this.previsionParPersonneACharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrevisionParPersonneACharge(double previsionParPersonneACharge) {
/*  74 */     this.previsionParPersonneACharge = previsionParPersonneACharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getReferenceConge() {
/*  79 */     return this.referenceConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReferenceConge(double referenceConge) {
/*  84 */     this.referenceConge = referenceConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getReferenceFraisMedicaux() {
/*  89 */     return this.referenceFraisMedicaux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReferenceFraisMedicaux(double referenceFraisMedicaux) {
/*  94 */     this.referenceFraisMedicaux = referenceFraisMedicaux;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantANouveau() {
/*  99 */     return this.montantANouveau;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantANouveau(double montantANouveau) {
/* 104 */     this.montantANouveau = montantANouveau;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantPeriode() {
/* 109 */     return this.montantPeriode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantPeriode(double montantPeriode) {
/* 114 */     this.montantPeriode = montantPeriode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/* 119 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/* 124 */     this.designation = designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrevisionParEmployeS() {
/* 129 */     return this.previsionParEmployeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrevisionParEmployeS(String previsionParEmployeS) {
/* 134 */     this.previsionParEmployeS = previsionParEmployeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrevisionParPersonneAChargeS() {
/* 139 */     return this.previsionParPersonneAChargeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrevisionParPersonneAChargeS(String previsionParPersonneAChargeS) {
/* 144 */     this.previsionParPersonneAChargeS = previsionParPersonneAChargeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReferenceCongeS() {
/* 149 */     return this.referenceCongeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReferenceCongeS(String referenceCongeS) {
/* 154 */     this.referenceCongeS = referenceCongeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReferenceFraisMedicauxS() {
/* 159 */     return this.referenceFraisMedicauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReferenceFraisMedicauxS(String referenceFraisMedicauxS) {
/* 164 */     this.referenceFraisMedicauxS = referenceFraisMedicauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantANouveauS() {
/* 169 */     return this.montantANouveauS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantANouveauS(String montantANouveauS) {
/* 174 */     this.montantANouveauS = montantANouveauS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantPeriodeS() {
/* 179 */     return this.montantPeriodeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantPeriodeS(String montantPeriodeS) {
/* 184 */     this.montantPeriodeS = montantPeriodeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNombrePersonnesAcharge() {
/* 189 */     return this.nombrePersonnesAcharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombrePersonnesAcharge(int nombrePersonnesAcharge) {
/* 194 */     this.nombrePersonnesAcharge = nombrePersonnesAcharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 199 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 204 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\PrevisionFraisMedicauxC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */