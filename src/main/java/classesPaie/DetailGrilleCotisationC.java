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
/*     */ public class DetailGrilleCotisationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5058006541711219529L;
/*     */   private int id;
/*     */   private int idGrille;
/*     */   private String categorie;
/*     */   private double trancheMin;
/*     */   private double trancheMax;
/*     */   private double pointAchete;
/*     */   private double valeurAchat;
/*     */   private double totalCotisation;
/*     */   
/*     */   public double getTotalCotisation() {
/*  25 */     this.totalCotisation = this.pointAchete * this.valeurAchat;
/*  26 */     return this.totalCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTotalCotisation(double totalCotisation) {
/*  31 */     this.totalCotisation = totalCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  36 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  41 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdGrille() {
/*  46 */     return this.idGrille;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdGrille(int idGrille) {
/*  51 */     this.idGrille = idGrille;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCategorie() {
/*  56 */     return this.categorie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCategorie(String categorie) {
/*  61 */     this.categorie = categorie;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTrancheMin() {
/*  66 */     return this.trancheMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTrancheMin(double trancheMin) {
/*  71 */     this.trancheMin = trancheMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTrancheMax() {
/*  76 */     return this.trancheMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTrancheMax(double trancheMax) {
/*  81 */     this.trancheMax = trancheMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPointAchete() {
/*  86 */     return this.pointAchete;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPointAchete(double pointAchete) {
/*  91 */     this.pointAchete = pointAchete;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getValeurAchat() {
/*  96 */     return this.valeurAchat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValeurAchat(double valeurAchat) {
/* 101 */     this.valeurAchat = valeurAchat;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailGrilleCotisationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */