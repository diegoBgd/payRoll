/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;

/*     */ 
/*     */ public class EvaluationEmployeDetailCritereC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1226050377455000093L;
/*     */   private int id;
/*     */   private int indexe;
/*     */   private double noteObtenue;
/*     */   private boolean applicable = true;
			private boolean disable;
/*     */   private String noteObtenueS;
/*     */   private Historique historique;
/*     */   private EvaluationEmployeDetailCritereC selected;
/*     */   private EvaluationEmployeC entete;
/*     */   private DetailCritereEvaluationC detailCritere;
/*     */   
/*     */   public int getId() {
/*  30 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  35 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  40 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  45 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public EvaluationEmployeC getEntete() {
/*  50 */     return this.entete;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntete(EvaluationEmployeC entete) {
/*  55 */     this.entete = entete;
/*     */   }
/*     */ 
/*     */   
/*     */   public DetailCritereEvaluationC getDetailCritere() {
/*  60 */     return this.detailCritere;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetailCritere(DetailCritereEvaluationC detailCritere) {
/*  65 */     this.detailCritere = detailCritere;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexe() {
/*  70 */     return this.indexe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexe(int indexe) {
/*  75 */     this.indexe = indexe;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNoteObtenue() {
/*  80 */     return this.noteObtenue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteObtenue(double noteObtenue) {
/*  85 */     this.noteObtenue = noteObtenue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteObtenueS() {
/*  90 */     return this.noteObtenueS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteObtenueS(String noteObtenueS) {
/*  95 */     this.noteObtenueS = noteObtenueS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isApplicable() {
/* 100 */     return this.applicable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setApplicable(boolean applicable) {
/* 105 */     this.applicable = applicable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeApplicable() {
/* 110 */     setApplicable(isApplicable());
				if(!applicable)
					setDisable(true);
				else
					setDisable(false);
				
/* 111 */     this.entete.calculResultatEvaluation();
/*     */   }
/*     */ 
/*     */   
/*     */   public EvaluationEmployeDetailCritereC getSelected() {
/* 116 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(EvaluationEmployeDetailCritereC selected) {
/* 121 */     this.selected = selected;
/*     */   }

			public boolean isDisable() {
				return disable;
			}
			public void setDisable(boolean disable) {
				this.disable = disable;
			} 
/*     */   public void changeNote() {
/*     */     try {
/* 128 */       setNoteObtenue(Double.valueOf(getNoteObtenueS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     
/*     */     }
/* 131 */     catch (Exception e) {
/*     */       
/* 133 */       setNoteObtenue(0.0D);
/*     */     } 
/* 135 */     if (getNoteObtenue() < 0.0D)
/*     */     {
/* 137 */       setNoteObtenue(0.0D);
/*     */     }
/* 139 */     setNoteObtenueS(HelperC.TraitementMontant.getMontantFormate(getNoteObtenue(), 2));
/* 140 */     setNoteObtenue(Double.valueOf(getNoteObtenueS().replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     this.entete.setTypeAppreciation(null);
/* 142 */     this.entete.calculResultatEvaluation();

 }

}


/* Location:              G:\PAIE\!\classesPaie\EvaluationEmployeDetailCritereC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */