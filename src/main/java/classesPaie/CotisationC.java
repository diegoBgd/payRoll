/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CotisationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3310070463687717018L;
/*     */   private int id;
/*     */   private int idOrganisme;
/*     */   private String code;
/*     */   private String designation;
/*     */   private String prefixeSalarial;
/*     */   private String prefixePatronal;
/*     */   private String chargeSalarial;
/*     */   private String chargePtronal;
/*     */   private int typeBaremme,typeElement;
/*     */   private boolean hide,obligatoire,
							retraite;
/*     */   
/*     */   public int getId() {
/*  25 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  29 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  33 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  37 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getDesignation() {
/*  41 */     return this.designation;
/*     */   }
/*     */   
/*     */   public void setDesignation(String designation) {
/*  45 */     this.designation = designation;
/*     */   }
/*     */   
/*     */   public int getTypeBaremme() {
/*  49 */     return this.typeBaremme;
/*     */   }
/*     */   
/*     */   public void setTypeBaremme(int typeBaremme) {
/*  53 */     this.typeBaremme = typeBaremme;
/*     */   }
/*     */   
/*     */   public int getIdOrganisme() {
/*  57 */     return this.idOrganisme;
/*     */   }
/*     */   
/*     */   public void setIdOrganisme(int idOrganisme) {
/*  61 */     this.idOrganisme = idOrganisme;
/*     */   }
/*     */   
/*     */   public String getPrefixeSalarial() {
/*  65 */     return this.prefixeSalarial;
/*     */   }
/*     */   
/*     */   public void setPrefixeSalarial(String prefixeSalarial) {
/*  69 */     this.prefixeSalarial = prefixeSalarial;
/*     */   }
/*     */   
/*     */   public String getPrefixePatronal() {
/*  73 */     return this.prefixePatronal;
/*     */   }
/*     */   
/*     */   public void setPrefixePatronal(String prefixePatronal) {
/*  77 */     this.prefixePatronal = prefixePatronal;
/*     */   }
/*     */   
/*     */   public String getChargeSalarial() {
/*  81 */     return this.chargeSalarial;
/*     */   }
/*     */   
/*     */   public void setChargeSalarial(String chargeSalarial) {
/*  85 */     this.chargeSalarial = chargeSalarial;
/*     */   }
/*     */   
/*     */   public String getChargePtronal() {
/*  89 */     return this.chargePtronal;
/*     */   }
/*     */   
/*     */   public void setChargePtronal(String chargePtronal) {
/*  93 */     this.chargePtronal = chargePtronal;
/*     */   }
/*     */   
/*     */   public boolean isHide() {
/*  97 */     return this.hide;
/*     */   }
/*     */   
/*     */   public void setHide(boolean hide) {
/* 101 */     this.hide = hide;
/*     */   }
			public boolean isObligatoire() {
				return obligatoire;
			}
			public void setObligatoire(boolean obligatoire) {
				this.obligatoire = obligatoire;
			}
			public boolean isRetraite() {
				return retraite;
			}
			public void setRetraite(boolean retraite) {
				this.retraite = retraite;
			}
			public int getTypeElement() {
				return typeElement;
			}
			public void setTypeElement(int typeElement) {
				this.typeElement = typeElement;
			}


/*     */ }


/* Location:              G:\PAIE\!\classesPaie\CotisationC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */