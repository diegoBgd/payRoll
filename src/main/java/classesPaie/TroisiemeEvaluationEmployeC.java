/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ public class TroisiemeEvaluationEmployeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8604562845308507941L;
/*     */   private int id,idAncGrd,idNvGrd;
			private double ancienSalary,nouveauSalaire,tauxCot,tauxAv;
/*     */   private String dateEvaluationS;
/*     */   private Date dateEvaluation;
/*     */   private Historique historique;
/*     */   private DeuxiemeEvaluationEmployeC deuxiemeEvaluation;
/*     */   private TypeNotationC typeNotation;
/*     */   private Base typeAppreciation;
/*     */   private TraitementSalarialC traitement;
			private DetailGradeC nouvelgrd;
			
/*     */   private EmployeC employe;
/*  30 */   private List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluation = new ArrayList<DeuxiemeEvaluationEmployeDetailCritereC>();
/*     */ 
/*     */ 
/*     */   
/*     */   public TraitementSalarialC getTraitement() {
/*  35 */     return this.traitement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTraitement(TraitementSalarialC traitement) {
/*  40 */     this.traitement = traitement;
/*     */   }
/*     */   
/*     */   public List<DeuxiemeEvaluationEmployeDetailCritereC> getListDetailDeuxiemeEvaluation() {
/*  44 */     return this.listDetailDeuxiemeEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailDeuxiemeEvaluation(List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluation) {
/*  49 */     this.listDetailDeuxiemeEvaluation = listDetailDeuxiemeEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  54 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  59 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateEvaluationS() {
/*  64 */     return this.dateEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateEvaluationS(String dateEvaluationS) {
/*  69 */     this.dateEvaluationS = dateEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateEvaluation() {
/*  74 */     return this.dateEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateEvaluation(Date dateEvaluation) {
/*  79 */     this.dateEvaluation = dateEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  84 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  89 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeuxiemeEvaluationEmployeC getDeuxiemeEvaluation() {
/*  94 */     return this.deuxiemeEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeuxiemeEvaluation(DeuxiemeEvaluationEmployeC deuxiemeEvaluation) {
/*  99 */     this.deuxiemeEvaluation = deuxiemeEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeNotationC getTypeNotation() {
/* 104 */     return this.typeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeNotation(TypeNotationC typeNotation) {
/* 109 */     this.typeNotation = typeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getTypeAppreciation() {
/* 114 */     return this.typeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeAppreciation(Base typeAppreciation) {
/* 119 */     this.typeAppreciation = typeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/* 124 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 129 */     this.employe = employe;
/*     */   }
public int getIdAncGrd() {
	return idAncGrd;
}
public void setIdAncGrd(int idAncGrd) {
	this.idAncGrd = idAncGrd;
}
public int getIdNvGrd() {
	return idNvGrd;
}
public void setIdNvGrd(int idNvGrd) {
	this.idNvGrd = idNvGrd;
}
public double getNouveauSalaire() {
	return nouveauSalaire;
}
public void setNouveauSalaire(double nouveauSalaire) {
	this.nouveauSalaire = nouveauSalaire;
}
public double getAncienSalary() {
	return ancienSalary;
}
public void setAncienSalary(double ancienSalary) {
	this.ancienSalary = ancienSalary;
}
public DetailGradeC getNouvelgrd() {
	return nouvelgrd;
}
public void setNouvelgrd(DetailGradeC nouvelgrd) {
	this.nouvelgrd = nouvelgrd;
}
public double getTauxCot() {
	return tauxCot;
}
public void setTauxCot(double tauxCot) {
	this.tauxCot = tauxCot;
}
public double getTauxAv() {
	return tauxAv;
}
public void setTauxAv(double tauxAv) {
	this.tauxAv = tauxAv;
}


			
/*     */ }


