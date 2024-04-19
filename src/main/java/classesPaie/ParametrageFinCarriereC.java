/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ParametrageFinCarriereC implements Serializable {
/*     */   private static final long serialVersionUID = -5282532046410041304L;
/*     */   private int id,periodeProlongation,periodeSalire;
/*     */   
/*     */   private int ageRetraite;
			private int periodeAnticipe;
/*     */   private int anneesProlongationRetraite;
/*     */   private int ageMaxRetraite;
/*     */  
/*     */  
/*     */   private double pourcentageSalaire;

/*     */   
/*     */   private Historique historique;
/*     */   private Base personnel;
/*     */   private TypeNotationC typeNotation;
/*     */   private CategoriePersonnelC categorie;
/*     */   private GradePersonnelC dernierGrade;
/*     */   
/*     */   public int getId() {
/*  32 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  36 */     this.id = id;
/*     */   }
/*     */   
/*     */   
/*     */   public int getAgeRetraite() {
/*  82 */     return this.ageRetraite;
/*     */   }
/*     */   
/*     */   public void setAgeRetraite(int ageRetraite) {
/*  86 */     this.ageRetraite = ageRetraite;
/*     */   }
/*     */   
/*     */   public int getAnneesProlongationRetraite() {
/*  90 */     return this.anneesProlongationRetraite;
/*     */   }
/*     */   
/*     */   public void setAnneesProlongationRetraite(int anneesProlongationRetraite) {
/*  94 */     this.anneesProlongationRetraite = anneesProlongationRetraite;
/*     */   }
/*     */   
/*     */   public int getAgeMaxRetraite() {
/*  98 */     return this.ageMaxRetraite;
/*     */   }
/*     */   
/*     */   public void setAgeMaxRetraite(int ageMaxRetraite) {
/* 102 */     this.ageMaxRetraite = ageMaxRetraite;
/*     */   }
/*     */   
/*     */  


/*     */   public Historique getHistorique() {
/* 176 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 180 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public Base getPersonnel() {
/* 184 */     return this.personnel;
/*     */   }
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/* 188 */     this.personnel = personnel;
/*     */   }
/*     */   
/*     */   public TypeNotationC getTypeNotation() {
/* 192 */     return this.typeNotation;
/*     */   }
/*     */   
/*     */   public void setTypeNotation(TypeNotationC typeNotation) {
/* 196 */     this.typeNotation = typeNotation;
/*     */   }
/*     */   
/*     */   public GradePersonnelC getDernierGrade() {
/* 200 */     return this.dernierGrade;
/*     */   }
/*     */   
/*     */   public void setDernierGrade(GradePersonnelC dernierGrade) {
/* 204 */     this.dernierGrade = dernierGrade;
/*     */   }
/*     */   
/*     */  
/*     */   public CategoriePersonnelC getCategorie() {
/* 217 */     return this.categorie;
/*     */   }
/*     */   
/*     */   public void setCategorie(CategoriePersonnelC categorie) {
/* 221 */     this.categorie = categorie;


/*     */   }
/*     */
			public int getPeriodeProlongation() {
				return periodeProlongation;
			}
			public void setPeriodeProlongation(int periodeProlongation) {
				this.periodeProlongation = periodeProlongation;
			}
			
			public int getPeriodeSalire() {
				return periodeSalire;
			}
			public void setPeriodeSalire(int periodeSalire) {
				this.periodeSalire = periodeSalire;
			}
			public double getPourcentageSalaire() {
				return pourcentageSalaire;
			}
			public void setPourcentageSalaire(double pourcentageSalaire) {
				this.pourcentageSalaire = pourcentageSalaire;
				
			}
			public int getPeriodeAnticipe() {
				return periodeAnticipe;
			}
			public void setPeriodeAnticipe(int periodeAnticipe) {
				this.periodeAnticipe = periodeAnticipe;
			} 
			
}


/* Location:              G:\PAIE\!\classesPaie\ParametrageFinCarriereC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */