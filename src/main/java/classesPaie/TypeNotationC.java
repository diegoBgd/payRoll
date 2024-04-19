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
/*     */ public class TypeNotationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6759490662542323253L;
/*     */   private int id;
/*     */   private String code;
/*     */   private String designation;
/*     */   private String pourcentageMinS;
/*     */   private String pourcentageMaxS;
/*     */   private String tauxAugmentationS;
/*     */   private double pourcentageMin;
/*     */   private double pourcentageMax;
/*     */   private double tauxAugmentation;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  38 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  43 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  48 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/*  53 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/*  58 */     this.designation = designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  63 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  68 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPourcentageMinS() {
/*  73 */     return this.pourcentageMinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageMinS(String pourcentageMinS) {
/*  78 */     this.pourcentageMinS = pourcentageMinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPourcentageMaxS() {
/*  83 */     return this.pourcentageMaxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageMaxS(String pourcentageMaxS) {
/*  88 */     this.pourcentageMaxS = pourcentageMaxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxAugmentationS() {
/*  93 */     return this.tauxAugmentationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxAugmentationS(String tauxAugmentationS) {
/*  98 */     this.tauxAugmentationS = tauxAugmentationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentageMin() {
/* 103 */     return this.pourcentageMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageMin(double pourcentageMin) {
/* 108 */     this.pourcentageMin = pourcentageMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentageMax() {
/* 113 */     return this.pourcentageMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageMax(double pourcentageMax) {
/* 118 */     this.pourcentageMax = pourcentageMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxAugmentation() {
/* 123 */     return this.tauxAugmentation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxAugmentation(double tauxAugmentation) {
/* 128 */     this.tauxAugmentation = tauxAugmentation;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\TypeNotationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */