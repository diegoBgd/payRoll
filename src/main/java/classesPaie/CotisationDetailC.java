/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CotisationDetailC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8977522981951262940L;
/*     */   private int id;
/*     */   private int idEntete;
/*     */   private int typeBase;
/*     */   private String codeElement;
/*     */   private String typeElement;
/*     */   private String libelleElement;
/*     */   private String symbol;
/*     */   
/*     */   public int getId() {
/*  21 */     return this.id;
/*     */   }
/*     */   private String typePrm; private double taux; private double plafon; private double plancher; private double forfait; private double tauxMax; private boolean seleceted; private boolean disable = true;
/*     */   public void setId(int id) {
/*  25 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getIdEntete() {
/*  29 */     return this.idEntete;
/*     */   }
/*     */   
/*     */   public void setIdEntete(int idEntete) {
/*  33 */     this.idEntete = idEntete;
/*     */   }
/*     */   
/*     */   public String getCodeElement() {
/*  37 */     return this.codeElement;
/*     */   }
/*     */   
/*     */   public void setCodeElement(String codeElement) {
/*  41 */     this.codeElement = codeElement;
/*     */   }
/*     */   
/*     */   public String getTypeElement() {
/*  45 */     return this.typeElement;
/*     */   }
/*     */   
/*     */   public void setTypeElement(String typeElement) {
/*  49 */     this.typeElement = typeElement;
/*     */   }
/*     */   
/*     */   public double getTaux() {
/*  53 */     return this.taux;
/*     */   }
/*     */   
/*     */   public void setTaux(double taux) {
/*  57 */     this.taux = taux;
/*     */   }
/*     */   
/*     */   public double getPlafon() {
/*  61 */     return this.plafon;
/*     */   }
/*     */   
/*     */   public void setPlafon(double plafon) {
/*  65 */     this.plafon = plafon;
/*     */   }
/*     */   
/*     */   public double getPlancher() {
/*  69 */     return this.plancher;
/*     */   }
/*     */   
/*     */   public void setPlancher(double plancher) {
/*  73 */     this.plancher = plancher;
/*     */   }
/*     */   
/*     */   public double getForfait() {
/*  77 */     return this.forfait;
/*     */   }
/*     */   
/*     */   public void setForfait(double forfait) {
/*  81 */     this.forfait = forfait;
/*     */   }
/*     */   
/*     */   public String getLibelleElement() {
/*  85 */     return this.libelleElement;
/*     */   }
/*     */   
/*     */   public void setLibelleElement(String libelleElement) {
/*  89 */     this.libelleElement = libelleElement;
/*     */   }
/*     */   
/*     */   public boolean isSeleceted() {
/*  93 */     return this.seleceted;
/*     */   }
/*     */   
/*     */   public void setSeleceted(boolean seleceted) {
/*  97 */     this.seleceted = seleceted;
/*     */   }
/*     */   
/*     */   public String getSymbol() {
/* 101 */     return this.symbol;
/*     */   }
/*     */   
/*     */   public void setSymbol(String symbol) {
/* 105 */     this.symbol = symbol;
/*     */   }
/*     */   
/*     */   public boolean isDisable() {
/* 109 */     return this.disable;
/*     */   }
/*     */   
/*     */   public void setDisable(boolean disable) {
/* 113 */     this.disable = disable;
/*     */   }
/*     */   public double getTauxMax() {
/* 116 */     return this.tauxMax;
/*     */   }
/*     */   
/*     */   public void setTauxMax(double tauxMax) {
/* 120 */     this.tauxMax = tauxMax;
/*     */   }
/*     */   
/*     */   public int getTypeBase() {
/* 124 */     return this.typeBase;
/*     */   }
/*     */   
/*     */   public void setTypeBase(int typeBase) {
/* 128 */     this.typeBase = typeBase;
/*     */   }
/*     */   
/*     */   public String getTypePrm() {
/* 132 */     return this.typePrm;
/*     */   }
/*     */   
/*     */   public void setTypePrm(String typePrm) {
/* 136 */     this.typePrm = typePrm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkDetail() {
/* 141 */     if (this.seleceted) {
/* 142 */       this.disable = false;
/*     */     
/*     */     }
/* 145 */     else if (getId() == 0) {
/* 146 */       this.disable = true;
/* 147 */       this.plancher = 0.0D;
/* 148 */       this.plafon = 0.0D;
/* 149 */       this.taux = 0.0D;
/* 150 */       this.tauxMax = 0.0D;
/* 151 */       this.forfait = 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeTauxMax() {
/* 158 */     if (this.tauxMax > 0.0D) {
/*     */       
/* 160 */       this.forfait = 0.0D;
/* 161 */       this.taux = 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeTaux() {
/* 167 */     if (this.taux > 0.0D) {
/*     */       
/* 169 */       this.forfait = 0.0D;
/* 170 */       this.tauxMax = 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeForfait() {
/* 176 */     if (this.forfait > 0.0D) {
/*     */       
/* 178 */       this.plancher = 0.0D;
/* 179 */       this.plafon = 0.0D;
/* 180 */       this.taux = 0.0D;
/* 181 */       this.tauxMax = 0.0D;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\CotisationDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */