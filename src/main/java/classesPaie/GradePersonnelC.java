/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 

/*     */ public class GradePersonnelC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3487909321953269344L;
/*     */   private int id,idGradeInferieur,numOrdre;
/*     */   private double traitementMensuel;
/*     */   private String traitementMensuelS;
/*     */   private String designation;
/*     */   private String code;
/*     */   private String codeCateg;
/*     */   private Historique historique;
/*  28 */   private List<GradePersonnelDetailC> listNiveau = new ArrayList<GradePersonnelDetailC>();
/*  29 */   private List<GradePersonnelDetailC> listNiveauDeleted = new ArrayList<GradePersonnelDetailC>();
/*     */   
/*     */   private CategoriePersonnelC categoriePersonnel;
/*     */   
/*     */   public int getId() {
/*  34 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  39 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTraitementMensuel() {
/*  44 */     return this.traitementMensuel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTraitementMensuel(double traitementMensuel) {
/*  49 */     this.traitementMensuel = traitementMensuel;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  54 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  59 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public CategoriePersonnelC getCategoriePersonnel() {
/*  64 */     return this.categoriePersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCategoriePersonnel(CategoriePersonnelC categoriePersonnel) {
/*  69 */     this.categoriePersonnel = categoriePersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTraitementMensuelS() {
/*  74 */     return this.traitementMensuelS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTraitementMensuelS(String traitementMensuelS) {
/*  79 */     this.traitementMensuelS = traitementMensuelS;
/*     */   }
/*     */   
/*     */   public List<GradePersonnelDetailC> getListNiveau() {
/*  83 */     return this.listNiveau;
/*     */   }
/*     */   
/*     */   public void setListNiveau(List<GradePersonnelDetailC> listNiveau) {
/*  87 */     this.listNiveau = listNiveau;
/*     */   }
/*     */   
/*     */   public List<GradePersonnelDetailC> getListNiveauDeleted() {
/*  91 */     return this.listNiveauDeleted;
/*     */   }
/*     */   
/*     */   public void setListNiveauDeleted(List<GradePersonnelDetailC> listNiveauDeleted) {
/*  95 */     this.listNiveauDeleted = listNiveauDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/* 100 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/* 105 */     this.designation = designation;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 109 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 113 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getCodeCateg() {
/* 117 */     return this.codeCateg;
/*     */   }
/*     */   
/*     */   public void setCodeCateg(String codeCateg) {
/* 121 */     this.codeCateg = codeCateg;
/*     */   }
			public int getIdGradeInferieur() {
				return idGradeInferieur;
			}
			public void setIdGradeInferieur(int idGradeInferieur) {
				this.idGradeInferieur = idGradeInferieur;
			}
					

/*     */ }


/* Location:              G:\PAIE\!\classesPaie\GradePersonnelC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */