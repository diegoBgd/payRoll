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
/*     */ public class DetailParametrageTraitementPositionC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7341820912609894467L;
/*     */   private int id;
/*     */   private int ancienneteMin;
/*     */   private int ancienneteMax;
/*     */   private int duree;
/*     */   private int index;
/*     */   private Historique historique;
/*     */   private boolean existe;
/*     */   private ParametrageDureeEtTraitementPositionC parametrage;
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
/*     */   public int getAncienneteMin() {
/*  41 */     return this.ancienneteMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAncienneteMin(int ancienneteMin) {
/*  46 */     this.ancienneteMin = ancienneteMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAncienneteMax() {
/*  51 */     return this.ancienneteMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAncienneteMax(int ancienneteMax) {
/*  56 */     this.ancienneteMax = ancienneteMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDuree() {
/*  61 */     return this.duree;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDuree(int duree) {
/*  66 */     this.duree = duree;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  71 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  76 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParametrageDureeEtTraitementPositionC getParametrage() {
/*  81 */     return this.parametrage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParametrage(ParametrageDureeEtTraitementPositionC parametrage) {
/*  86 */     this.parametrage = parametrage;
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
/*     */   public int getIndex() {
/* 101 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 106 */     this.index = index;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailParametrageTraitementPositionC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */