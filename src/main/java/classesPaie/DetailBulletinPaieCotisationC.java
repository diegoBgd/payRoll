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
/*     */ public class DetailBulletinPaieCotisationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4089084913068900460L;
/*     */   private int id;
/*     */   private BulletinPaieC bulletinPaie;
/*     */   private ParametreCotisationC retenueCotisation;
/*     */   private double tauxSalarial;
/*     */   private double tauxPatronal;
/*     */   private double partSalarial;
/*     */   private double partPatronal;
/*     */   private String tauxSalarialString;
/*     */   private String tauxPatronalString;
/*     */   private String partSalarialString;
/*     */   private String partPatronalString;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  32 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  37 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public BulletinPaieC getBulletinPaie() {
/*  42 */     return this.bulletinPaie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBulletinPaie(BulletinPaieC bulletinPaie) {
/*  47 */     this.bulletinPaie = bulletinPaie;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParametreCotisationC getRetenueCotisation() {
/*  52 */     return this.retenueCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRetenueCotisation(ParametreCotisationC retenueCotisation) {
/*  57 */     this.retenueCotisation = retenueCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxSalarial() {
/*  62 */     return this.tauxSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxSalarial(double tauxSalarial) {
/*  67 */     this.tauxSalarial = tauxSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxPatronal() {
/*  72 */     return this.tauxPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxPatronal(double tauxPatronal) {
/*  77 */     this.tauxPatronal = tauxPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxSalarialString() {
/*  82 */     return this.tauxSalarialString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxSalarialString(String tauxSalarialString) {
/*  87 */     this.tauxSalarialString = tauxSalarialString;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxPatronalString() {
/*  92 */     return this.tauxPatronalString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxPatronalString(String tauxPatronalString) {
/*  97 */     this.tauxPatronalString = tauxPatronalString;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPartSalarial() {
/* 102 */     return this.partSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartSalarial(double partSalarial) {
/* 107 */     this.partSalarial = partSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPartPatronal() {
/* 112 */     return this.partPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartPatronal(double partPatronal) {
/* 117 */     this.partPatronal = partPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPartSalarialString() {
/* 122 */     return this.partSalarialString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartSalarialString(String partSalarialString) {
/* 127 */     this.partSalarialString = partSalarialString;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPartPatronalString() {
/* 132 */     return this.partPatronalString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartPatronalString(String partPatronalString) {
/* 137 */     this.partPatronalString = partPatronalString;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 142 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 147 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeTauxSalarial() {
/*     */     try {
/* 154 */       this.tauxSalarial = Double.valueOf(this.tauxSalarialString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/*     */     }
/* 157 */     catch (Exception e) {
/*     */       
/* 159 */       this.tauxSalarial = 0.0D;
/*     */     } 
/* 161 */     this.tauxSalarialString = HelperC.TraitementMontant.getMontantFormate(this.tauxSalarial, 0);
/* 162 */     this.tauxSalarial = Double.valueOf(this.tauxSalarialString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 164 */     this.tauxSalarialString = HelperC.TraitementMontant.getMontantFormate(this.tauxSalarial, 0);
/* 165 */     this.tauxSalarial = Double.valueOf(this.tauxSalarialString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 167 */     this.tauxSalarialString = HelperC.TraitementMontant.getMontantFormate(this.tauxSalarial, 0);
/* 168 */     this.tauxSalarial = Double.valueOf(this.tauxSalarialString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeTauxPatronal() {
/*     */     try {
/* 175 */       this.tauxPatronal = Double.valueOf(this.tauxPatronalString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/*     */     }
/* 178 */     catch (Exception e) {
/*     */       
/* 180 */       this.tauxPatronal = 0.0D;
/*     */     } 
/* 182 */     this.tauxPatronalString = HelperC.TraitementMontant.getMontantFormate(this.tauxPatronal, 0);
/* 183 */     this.tauxPatronal = Double.valueOf(this.tauxPatronalString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 185 */     this.tauxPatronalString = HelperC.TraitementMontant.getMontantFormate(this.tauxPatronal, 0);
/* 186 */     this.tauxPatronal = Double.valueOf(this.tauxPatronalString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 188 */     this.tauxPatronalString = HelperC.TraitementMontant.getMontantFormate(this.tauxPatronal, 0);
/* 189 */     this.tauxPatronal = Double.valueOf(this.tauxPatronalString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changePartSalarial() {
/*     */     try {
/* 196 */       this.partSalarial = Double.valueOf(this.partSalarialString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/*     */     }
/* 199 */     catch (Exception e) {
/*     */       
/* 201 */       this.partSalarial = 0.0D;
/*     */     } 
/* 203 */     this.partSalarialString = HelperC.TraitementMontant.getMontantFormate(this.partSalarial, 0);
/* 204 */     this.partSalarial = Double.valueOf(this.partSalarialString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 206 */     this.partSalarialString = HelperC.TraitementMontant.getMontantFormate(this.partSalarial, 0);
/* 207 */     this.partSalarial = Double.valueOf(this.partSalarialString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 209 */     this.partSalarialString = HelperC.TraitementMontant.getMontantFormate(this.partSalarial, 0);
/* 210 */     this.partSalarial = Double.valueOf(this.partSalarialString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changePartPatronal() {
/*     */     try {
/* 217 */       this.partPatronal = Double.valueOf(this.partPatronalString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/*     */     }
/* 220 */     catch (Exception e) {
/*     */       
/* 222 */       this.partPatronal = 0.0D;
/*     */     } 
/* 224 */     this.partPatronalString = HelperC.TraitementMontant.getMontantFormate(this.partPatronal, 0);
/* 225 */     this.partPatronal = Double.valueOf(this.partPatronalString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 227 */     this.partPatronalString = HelperC.TraitementMontant.getMontantFormate(this.partPatronal, 0);
/* 228 */     this.partPatronal = Double.valueOf(this.partPatronalString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 230 */     this.partPatronalString = HelperC.TraitementMontant.getMontantFormate(this.partPatronal, 0);
/* 231 */     this.partPatronal = Double.valueOf(this.partPatronalString.replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailBulletinPaieCotisationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */