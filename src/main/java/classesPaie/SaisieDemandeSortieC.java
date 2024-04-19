/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ public class SaisieDemandeSortieC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2877639710624920386L;
/*     */   private int id,idExercice;
/*     */   private int etatSortie,decision;
/*     */   private long nombreMin;
/*     */   private String heureDepart;
/*     */   private String heureRetour;
/*     */   private String motifSortie;
/*     */   private String dateDemandeS;
/*     */   private String dateValidationS;
/*     */   private String dateSortieS;
/*     */   private String motifRefusSortie;
/*     */   private String libelleEtatSortie,
					libelleDecision;
/*     */   private Date dateDemande;
/*     */   private Date dateValidation;
/*     */   private Date dateSortie;
/*     */   private boolean imputableAuxPresences,inLine;
/*     */   private Historique historique;
/*     */   private Constante.EtatDemandeSortie etatDemandeSortie;
/*     */   private EmployeC employe;
/*     */   private OperateurC decideur;
/*     */   
			
/*     */   public int getId() {
/*  32 */     return this.id;
/*     */   }
			public int getIdExercice() {
				return idExercice;
			}
			public void setIdExercice(int idExercice) {
				this.idExercice = idExercice;
			}
			
			public int getDecision() {
				return decision;
			}
			public void setDecision(int decision) {
				this.decision = decision;
			}
/*     */   
/*     */   public void setId(int id) {
/*  36 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getNombreMin() {
/*  40 */     return this.nombreMin;
/*     */   }
/*     */   
/*     */   public void setNombreMin(long nombreMin) {
/*  44 */     this.nombreMin = nombreMin;
/*     */   }
/*     */   
/*     */   public String getHeureDepart() {
/*  48 */     return this.heureDepart;
/*     */   }
/*     */   
/*     */   public void setHeureDepart(String heureDepart) {
/*  52 */     this.heureDepart = heureDepart;
/*     */   }
/*     */   
/*     */   public String getHeureRetour() {
/*  56 */     return this.heureRetour;
/*     */   }
/*     */   
/*     */   public void setHeureRetour(String heureRetour) {
/*  60 */     this.heureRetour = heureRetour;
/*     */   }
/*     */   
/*     */   public String getMotifSortie() {
/*  64 */     return this.motifSortie;
/*     */   }
/*     */   
/*     */   public void setMotifSortie(String motifSortie) {
/*  68 */     this.motifSortie = motifSortie;
/*     */   }
/*     */   
/*     */   public String getDateDemandeS() {
/*  72 */     return this.dateDemandeS;
/*     */   }
/*     */   
/*     */   public void setDateDemandeS(String dateDemandeS) {
/*  76 */     this.dateDemandeS = dateDemandeS;
/*     */   }
/*     */   
/*     */   public String getDateValidationS() {
/*  80 */     return this.dateValidationS;
/*     */   }
/*     */   
/*     */   public void setDateValidationS(String dateValidationS) {
/*  84 */     this.dateValidationS = dateValidationS;
/*     */   }
/*     */   
/*     */   public Date getDateDemande() {
/*  88 */     return this.dateDemande;
/*     */   }
/*     */   
/*     */   public void setDateDemande(Date dateDemande) {
/*  92 */     this.dateDemande = dateDemande;
/*     */   }
/*     */   
/*     */   public Date getDateValidation() {
/*  96 */     return this.dateValidation;
/*     */   }
/*     */   
/*     */   public void setDateValidation(Date dateValidation) {
/* 100 */     this.dateValidation = dateValidation;
/*     */   }
/*     */   
/*     */   public boolean isImputableAuxPresences() {
/* 104 */     return this.imputableAuxPresences;
/*     */   }
/*     */   
/*     */   public void setImputableAuxPresences(boolean imputableAuxPresences) {
/* 108 */     this.imputableAuxPresences = imputableAuxPresences;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/* 112 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 116 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public EmployeC getEmploye() {
/* 120 */     return this.employe;
/*     */   }
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 124 */     this.employe = employe;
/*     */   }
/*     */   
/*     */   public OperateurC getDecideur() {
/* 128 */     return this.decideur;
/*     */   }
/*     */   
/*     */   public void setDecideur(OperateurC decideur) {
/* 132 */     this.decideur = decideur;
/*     */   }
/*     */   
/*     */   public int getEtatSortie() {
/* 136 */     return this.etatSortie;
/*     */   }
/*     */   
/*     */   public void setEtatSortie(int etatSortie) {
/* 140 */     this.etatSortie = etatSortie;
/*     */   }
/*     */   
/*     */   public Constante.EtatDemandeSortie getEtatDemandeSortie() {
/* 144 */     return this.etatDemandeSortie;
/*     */   }
/*     */   
/*     */   public void setEtatDemandeSortie(Constante.EtatDemandeSortie etatDemandeSortie) {
/* 148 */     this.etatDemandeSortie = etatDemandeSortie;
/*     */   }
/*     */   
/*     */   public String getDateSortieS() {
/* 152 */     return this.dateSortieS;
/*     */   }
/*     */   
/*     */   public void setDateSortieS(String dateSortieS) {
/* 156 */     this.dateSortieS = dateSortieS;
/*     */   }
/*     */   
/*     */   public Date getDateSortie() {
/* 160 */     return this.dateSortie;
/*     */   }
/*     */   
/*     */   public void setDateSortie(Date dateSortie) {
/* 164 */     this.dateSortie = dateSortie;
/*     */   }
/*     */   
/*     */   public String getMotifRefusSortie() {
/* 168 */     return this.motifRefusSortie;
/*     */   }
/*     */   
/*     */   public void setMotifRefusSortie(String motifRefusSortie) {
/* 172 */     this.motifRefusSortie = motifRefusSortie;
/*     */   }
/*     */   
/*     */   public String getLibelleEtatSortie() {
/* 176 */     return this.libelleEtatSortie;
/*     */   }
/*     */   
/*     */   public void setLibelleEtatSortie(String libelleEtatSortie) {
/* 180 */     this.libelleEtatSortie = libelleEtatSortie;
/*     */   }
			public boolean isInLine() {
				return inLine;
			}
			public void setInLine(boolean inLine) {
				this.inLine = inLine;
			}
			public String getLibelleDecision() {
				switch (getDecision()) {
				case 1:
					setLibelleDecision("Accpté");
					break;

				case 2:
					setLibelleDecision("Refusé");
					break;
				}
				return libelleDecision;
			}
			public void setLibelleDecision(String libelleDecision) {
				this.libelleDecision = libelleDecision;
			}

/*     */ }


/* Location:              G:\PAIE\!\classesPaie\SaisieDemandeSortieC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */