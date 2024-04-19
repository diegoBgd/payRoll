/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

import javax.faces.application.FacesMessage;

/*     */ import persistencePaie.FichierBaseDAO;

/*     */ public class EvaluationEmployeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5158561641818467968L;
/*     */   private int id,idNvGrade,idAnGrade;
/*     */   private int idTypeNotation;
/*     */   private int anneeValidite;
/*     */   private String noteGeneraleS;
/*     */   private String dateEvaluationS;
/*     */   private String pourcentageS;
/*     */   private String noteEvaluationS;
/*     */   private String justificationDiscordance;
/*     */   private double noteGenerale,nouveauSalaire;
/*     */   private double pourcentage,ancienSalary;
/*     */   private double noteEvaluation,tauxAvSal;
/*     */   private Date dateEvaluation;
/*     */   private Historique historique;
/*     */   private EmployeC employe;
/*     */   private TypeNotationC typeNotation;
/*     */   private Base typeAppreciation;
			private TraitementSalarialC traitement;
			private DetailGradeC nouvelgrd;
/*     */   private EvaluationEmployeDetailCritereC detail;
/*  41 */   private List<EvaluationEmployeDetailCritereC> listDetailEvaluation = new ArrayList<EvaluationEmployeDetailCritereC>();
/*  42 */   private List<EvaluationEmployeDetailCritereC> listDetailEvaluationDeleted = new ArrayList<EvaluationEmployeDetailCritereC>();
/*     */ 
/*     */ 
/*     */   
/*     */   public EvaluationEmployeDetailCritereC getDetail() {
/*  47 */     return this.detail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetail(EvaluationEmployeDetailCritereC detail) {
/*  52 */     this.detail = detail;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  57 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  62 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdTypeNotation() {
/*  67 */     return this.idTypeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdTypeNotation(int idTypeNotation) {
/*  72 */     this.idTypeNotation = idTypeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteGeneraleS() {
			 
/*  77 */     return this.noteGeneraleS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteGeneraleS(String noteGeneraleS) {
/*  82 */     this.noteGeneraleS = noteGeneraleS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateEvaluationS() {
/*  87 */     return this.dateEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateEvaluationS(String dateEvaluationS) {
/*  92 */     this.dateEvaluationS = dateEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPourcentageS() {
			  
/*  97 */     return this.pourcentageS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageS(String pourcentageS) {
/* 102 */     this.pourcentageS = pourcentageS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteEvaluationS() {
				
/* 107 */     return this.noteEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteEvaluationS(String noteEvaluationS) {
/* 112 */     this.noteEvaluationS = noteEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJustificationDiscordance() {
/* 117 */     return this.justificationDiscordance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJustificationDiscordance(String justificationDiscordance) {
/* 122 */     this.justificationDiscordance = justificationDiscordance;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNoteGenerale() {
/* 127 */     return this.noteGenerale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteGenerale(double noteGenerale) {
/* 132 */     this.noteGenerale = noteGenerale;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentage() {
/* 137 */     return this.pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentage(double pourcentage) {
/* 142 */     this.pourcentage = pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNoteEvaluation() {
/* 147 */     return this.noteEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteEvaluation(double noteEvaluation) {
/* 152 */     this.noteEvaluation = noteEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateEvaluation() {
/* 157 */     return this.dateEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateEvaluation(Date dateEvaluation) {
/* 162 */     this.dateEvaluation = dateEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 167 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 172 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/* 177 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 182 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeNotationC getTypeNotation() {
/* 187 */     return this.typeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeNotation(TypeNotationC typeNotation) {
/* 192 */     this.typeNotation = typeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getTypeAppreciation() {
/* 197 */     return this.typeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeAppreciation(Base typeAppreciation) {
/* 202 */     this.typeAppreciation = typeAppreciation;
/*     */   }
/*     */   
/*     */   public List<EvaluationEmployeDetailCritereC> getListDetailEvaluation() {
/* 206 */     return this.listDetailEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailEvaluation(List<EvaluationEmployeDetailCritereC> listDetailEvaluation) {
/* 211 */     this.listDetailEvaluation = listDetailEvaluation;
/*     */   }
/*     */   
/*     */   public List<EvaluationEmployeDetailCritereC> getListDetailEvaluationDeleted() {
/* 215 */     return this.listDetailEvaluationDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailEvaluationDeleted(List<EvaluationEmployeDetailCritereC> listDetailEvaluationDeleted) {
/* 220 */     this.listDetailEvaluationDeleted = listDetailEvaluationDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnneeValidite() {
/* 225 */     return this.anneeValidite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnneeValidite(int anneeValidite) {
/* 230 */     this.anneeValidite = anneeValidite;
/*     */   }
			
			public double getTauxAvSal() {
				return tauxAvSal;
			}
		
			public void setTauxAvSal(double tauxAvSal) {
				this.tauxAvSal = tauxAvSal;
			}
			public DetailGradeC getNouvelgrd() {
				return nouvelgrd;
			}
			public void setNouvelgrd(DetailGradeC nouvelgrd) {
				this.nouvelgrd = nouvelgrd;
			}
			
			public double getAncienSalary() {
				return ancienSalary;
			}
			public void setAncienSalary(double ancienSalary) {
				this.ancienSalary = ancienSalary;
			}
			
			public double getNouveauSalaire() {
				return nouveauSalaire;
			}
			public void setNouveauSalaire(double nouveauSalaire) {
				this.nouveauSalaire = nouveauSalaire;
			}
			
			public TraitementSalarialC getTraitement() {
				return traitement;
			}
			public void setTraitement(TraitementSalarialC traitement) {
				this.traitement = traitement;
			}			
			public int getIdNvGrade() {
				return idNvGrade;
			}
			public void setIdNvGrade(int idNvGrade) {
				this.idNvGrade = idNvGrade;
			}
			public int getIdAnGrade() {
				return idAnGrade;
			}
			public void setIdAnGrade(int idAnGrade) {
				this.idAnGrade = idAnGrade;
			}
/*     */   public void calculResultatEvaluation() {
/* 235 */     setPourcentage(0.0D);
/* 236 */     setPourcentageS("");
/* 237 */     setNoteEvaluation(0.0D);
/* 238 */     setNoteEvaluationS("");
/* 239 */     setNoteGenerale(0.0D);
/* 240 */     setNoteGeneraleS("");
/* 241 */     for (EvaluationEmployeDetailCritereC eval : getListDetailEvaluation()) {
/*     */       
/* 243 */       //System.out.println(eval);
/* 244 */       if (eval.getNoteObtenue() > eval.getDetailCritere().getNote()) {
/*     */         
/* 246 */         //System.out.println("Les points obtenus doivent être inférieur ou egale aux points de références");
/*     */ 
/*     */         
/* 249 */         HelperC.afficherMessage("Information", "Les points obtenus doivent être inférieur ou egale aux points de références",FacesMessage.SEVERITY_WARN);
/*     */ 		  return;
/*     */       
/*     */       }
/* 253 */       else if (eval.getNoteObtenue() <= eval.getDetailCritere().getNote() && eval.isApplicable()) {
/*     */         
/* 255 */        // System.out.println("++++++++++++");
/* 256 */         setNoteEvaluation(getNoteEvaluation() + eval.getNoteObtenue());
/* 257 */         //System.out.println(getNoteEvaluation());
/* 258 */         setNoteEvaluationS(HelperC.TraitementMontant.getMontantFormate(getNoteEvaluation(), 2));
/* 259 */         setNoteGenerale(getNoteGenerale() + eval.getDetailCritere().getNote());
/* 260 */         setNoteGeneraleS(HelperC.TraitementMontant.getMontantFormate(getNoteGenerale(), 0));
/* 261 */         setPourcentage(getNoteEvaluation() * 100.0D / getNoteGenerale());
/* 262 */         setPourcentageS(HelperC.TraitementMontant.getMontantFormate(getPourcentage(), 2));
/*     */       } 
/* 264 */       for (TypeNotationC typeNotation : FichierBaseDAO.getInstance().getAllTypeNotation()) {
/*     */ 
/*     */         
/* 267 */         if (typeNotation.getPourcentageMin() <= getPourcentage() && typeNotation.getPourcentageMax() > getPourcentage()) {
/*     */           
/* 269 */           setTypeNotation(typeNotation);
/* 270 */           this.idTypeNotation = getTypeNotation().getId();

/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


