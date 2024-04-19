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
/*     */ 
/*     */ public class DeuxiemeEvaluationEmployeDetailCritereC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1669661618648326912L;
/*     */   private int id;
/*     */   private int indexe;
/*     */   private double noteObtenue;
/*     */   private boolean applicable = true;
/*     */   private String noteObtenueS;
/*     */   private Historique historique;
/*     */   private DeuxiemeEvaluationEmployeC entete;
/*     */   private EvaluationEmployeDetailCritereC detailCritere;
/*     */   
/*     */   public int getId() {
/*  32 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  37 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexe() {
/*  42 */     return this.indexe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexe(int indexe) {
/*  47 */     this.indexe = indexe;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNoteObtenue() {
/*  52 */     return this.noteObtenue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteObtenue(double noteObtenue) {
/*  57 */     this.noteObtenue = noteObtenue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteObtenueS() {
/*  62 */     return this.noteObtenueS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteObtenueS(String noteObtenueS) {
/*  67 */     this.noteObtenueS = noteObtenueS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  72 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  77 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeuxiemeEvaluationEmployeC getEntete() {
/*  82 */     return this.entete;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntete(DeuxiemeEvaluationEmployeC entete) {
/*  87 */     this.entete = entete;
/*     */   }
/*     */ 
/*     */   
/*     */   public EvaluationEmployeDetailCritereC getDetailCritere() {
/*  92 */     return this.detailCritere;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetailCritere(EvaluationEmployeDetailCritereC detailCritere) {
/*  97 */     this.detailCritere = detailCritere;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isApplicable() {
/* 102 */     return this.applicable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setApplicable(boolean applicable) {
/* 107 */     this.applicable = applicable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeApplicable() {
/* 112 */     setApplicable(isApplicable());
/* 113 */     this.entete.calculResultatEvaluation();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeNote() {
/*     */     try {
/* 120 */       setNoteObtenue(Double.valueOf(getNoteObtenueS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     
/*     */     }
/* 123 */     catch (Exception e) {
/*     */       
/* 125 */       setNoteObtenue(0.0D);
/*     */     } 
/* 127 */     if (getNoteObtenue() < 0.0D)
/*     */     {
/* 129 */       setNoteObtenue(0.0D);
/*     */     }
/* 131 */     setNoteObtenueS(HelperC.TraitementMontant.getMontantFormate(getNoteObtenue(), 2));
/* 132 */     setNoteObtenue(Double.valueOf(getNoteObtenueS().replace(" ", "").replace(",", ".").trim()).doubleValue());
/* 133 */     this.entete.calculResultatEvaluation();
/*     */     
//		     if (getNoteObtenue() < 0.0D)
//		     {
//		       setNoteObtenue(0.0D);
//		     }
//		     setNoteObtenueS(Helper.TraitementMontant.getMontantFormate(getNoteObtenue(), 2));
//		     setNoteObtenue(Double.valueOf(getNoteObtenueS().replace(" ", "").replace(",", ".").trim()).doubleValue());
//		     this.entete.calculResultatEvaluation();
//		    
//		     if (getNoteObtenue() < 0.0D)
//		     {
//			   setNoteObtenue(0.0D);
//		     }
//		   		setNoteObtenueS(Helper.TraitementMontant.getMontantFormate(getNoteObtenue(), 2));
//		   		setNoteObtenue(Double.valueOf(getNoteObtenueS().replace(" ", "").replace(",", ".").trim()).doubleValue());
//		   		this.entete.calculResultatEvaluation();
   }
 }


/* Location:              G:\PAIE\!\classesPaie\DeuxiemeEvaluationEmployeDetailCritereC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */