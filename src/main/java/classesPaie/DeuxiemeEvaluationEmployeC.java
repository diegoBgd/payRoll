/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ import persistencePaie.FichierBaseDAO;
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
/*     */ public class DeuxiemeEvaluationEmployeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7354580136041066257L;
/*     */   private int id;
/*     */   private int idTypeNotation;
/*     */   private String noteGeneraleS;
/*     */   private String dateEvaluationS;
/*     */   private String pourcentageS;
/*     */   private String noteEvaluationS;
/*     */   private String justificationDiscordance;
/*     */   private double noteGenerale;
/*     */   private double pourcentage;
/*     */   private double noteEvaluation;
/*     */   private Date dateEvaluation;
/*     */   private Historique historique;
/*     */   private EvaluationEmployeC premiereEvaluation;
/*     */   private TypeNotationC typeNotation;
/*     */   private Base typeAppreciation;
/*  39 */   private List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluation = new ArrayList<DeuxiemeEvaluationEmployeDetailCritereC>();
/*  40 */   private List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluationDeleted = new ArrayList<DeuxiemeEvaluationEmployeDetailCritereC>();
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  45 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  50 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJustificationDiscordance() {
/*  55 */     return this.justificationDiscordance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJustificationDiscordance(String justificationDiscordance) {
/*  60 */     this.justificationDiscordance = justificationDiscordance;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteEvaluationS() {
/*  65 */     return this.noteEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteEvaluationS(String noteEvaluationS) {
/*  70 */     this.noteEvaluationS = noteEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateEvaluationS() {
/*  75 */     return this.dateEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateEvaluationS(String dateEvaluationS) {
/*  80 */     this.dateEvaluationS = dateEvaluationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNoteEvaluation() {
/*  85 */     return this.noteEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteEvaluation(double noteEvaluation) {
/*  90 */     this.noteEvaluation = noteEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateEvaluation() {
/*  95 */     return this.dateEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateEvaluation(Date dateEvaluation) {
/* 100 */     this.dateEvaluation = dateEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 105 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 110 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public EvaluationEmployeC getPremiereEvaluation() {
/* 115 */     return this.premiereEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPremiereEvaluation(EvaluationEmployeC premiereEvaluation) {
/* 120 */     this.premiereEvaluation = premiereEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeNotationC getTypeNotation() {
/* 125 */     return this.typeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeNotation(TypeNotationC typeNotation) {
/* 130 */     this.typeNotation = typeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getTypeAppreciation() {
/* 135 */     return this.typeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeAppreciation(Base typeAppreciation) {
/* 140 */     this.typeAppreciation = typeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DeuxiemeEvaluationEmployeDetailCritereC> getListDetailDeuxiemeEvaluation() {
/* 145 */     return this.listDetailDeuxiemeEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailDeuxiemeEvaluation(List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluation) {
/* 150 */     this.listDetailDeuxiemeEvaluation = listDetailDeuxiemeEvaluation;
/*     */   }
/*     */   
/*     */   public List<DeuxiemeEvaluationEmployeDetailCritereC> getListDetailDeuxiemeEvaluationDeleted() {
/* 154 */     return this.listDetailDeuxiemeEvaluationDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailDeuxiemeEvaluationDeleted(List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluationDeleted) {
/* 159 */     this.listDetailDeuxiemeEvaluationDeleted = listDetailDeuxiemeEvaluationDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteGeneraleS() {
/* 164 */     return this.noteGeneraleS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteGeneraleS(String noteGeneraleS) {
/* 169 */     this.noteGeneraleS = noteGeneraleS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPourcentageS() {
/* 174 */     return this.pourcentageS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageS(String pourcentageS) {
/* 179 */     this.pourcentageS = pourcentageS;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNoteGenerale() {
/* 184 */     return this.noteGenerale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteGenerale(double noteGenerale) {
/* 189 */     this.noteGenerale = noteGenerale;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentage() {
/* 194 */     return this.pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentage(double pourcentage) {
/* 199 */     this.pourcentage = pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdTypeNotation() {
/* 204 */     return this.idTypeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdTypeNotation(int idTypeNotation) {
/* 209 */     this.idTypeNotation = idTypeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void calculResultatEvaluation() {
/* 214 */     this.pourcentage = 0.0D;
/* 215 */     this.pourcentageS = "";
/* 216 */     this.noteEvaluation = 0.0D;
/* 217 */     this.noteEvaluationS = "";
/* 218 */     this.noteGenerale = 0.0D;
/* 219 */     this.noteGeneraleS = "";
/* 220 */     for (DeuxiemeEvaluationEmployeDetailCritereC eval : getListDetailDeuxiemeEvaluation()) {
/*     */ 
/*     */       if(eval.getNoteObtenue()>eval.getDetailCritere().getDetailCritere().getNote())
				{
					HelperC.afficherMessage("Information", "Les points obtenus doivent être inférieur ou egale aux points de références");
					return;
				}
				else
/* 223 */       if (eval.isApplicable()) {
/*     */         
/* 225 */         this.noteEvaluation += eval.getNoteObtenue();
/* 226 */         this.noteEvaluationS = HelperC.TraitementMontant.getMontantFormate(this.noteEvaluation, 2);
/* 227 */         this.noteGenerale += eval.getDetailCritere().getDetailCritere().getNote();
/* 228 */         this.noteGeneraleS = HelperC.TraitementMontant.getMontantFormate(this.noteGenerale, 0);
/* 229 */         this.pourcentage = this.noteEvaluation * 100.0D / this.noteGenerale;
/* 230 */         this.pourcentageS = HelperC.TraitementMontant.getMontantFormate(this.pourcentage, 2);
/*     */       } 
/* 232 */       for (TypeNotationC typeNotation : FichierBaseDAO.getInstance().getAllTypeNotation()) {
/*     */ 
/*     */         
/* 235 */         if (typeNotation.getPourcentageMin() <= this.pourcentage && typeNotation.getPourcentageMax() > this.pourcentage) {
/*     */           
/* 237 */           setTypeNotation(typeNotation);
/* 238 */           this.idTypeNotation = getTypeNotation().getId();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DeuxiemeEvaluationEmployeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */