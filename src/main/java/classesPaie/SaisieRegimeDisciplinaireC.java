/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class SaisieRegimeDisciplinaireC implements Serializable {
/*     */   private static final long serialVersionUID = 5527309594808320175L;
/*     */   private int id;
/*     */   private int sanctionsDisciplinaires;
/*     */   private int dureeSanction;
/*     */   private int etatSanction;
/*     */   private Date dateDebutSanction;
/*     */   private Date dateFinSanction;
/*     */   private Date dateDecision;
/*     */   private Date dateRecours;
/*     */   private Date dateSaisie;
/*     */   private String dateDebutSanctionS;
/*     */   private String dateFinSanctionS;
/*     */   private String raisonSanction;
/*     */   private String justificationRecours;
/*     */   private String libelleSanction;
/*     */   private String dateDecisionS;
/*     */   private String dateRecoursS;
/*     */   private String motifDecision;
/*     */   private String dateSaisieS;
/*     */   private Historique historique;
/*     */   private Constante.SanctionsDisciplinaires sanction;
/*     */   private TraitementSalarialC traitement;
/*     */   private EmployeC employe;
/*     */   private Constante.EtatSanction etatS;
/*     */   
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  37 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getSanctionsDisciplinaires() {
/*  41 */     return this.sanctionsDisciplinaires;
/*     */   }
/*     */   
/*     */   public void setSanctionsDisciplinaires(int sanctionsDisciplinaires) {
/*  45 */     this.sanctionsDisciplinaires = sanctionsDisciplinaires;
/*     */   }
/*     */   
/*     */   public int getDureeSanction() {
/*  49 */     return this.dureeSanction;
/*     */   }
/*     */   
/*     */   public void setDureeSanction(int dureeSanction) {
/*  53 */     this.dureeSanction = dureeSanction;
/*     */   }
/*     */   
/*     */   public Date getDateDebutSanction() {
/*  57 */     return this.dateDebutSanction;
/*     */   }
/*     */   
/*     */   public void setDateDebutSanction(Date dateDebutSanction) {
/*  61 */     this.dateDebutSanction = dateDebutSanction;
/*     */   }
/*     */   
/*     */   public Date getDateFinSanction() {
/*  65 */     return this.dateFinSanction;
/*     */   }
/*     */   
/*     */   public void setDateFinSanction(Date dateFinSanction) {
/*  69 */     this.dateFinSanction = dateFinSanction;
/*     */   }
/*     */   
/*     */   public String getDateDebutSanctionS() {
/*  73 */     return this.dateDebutSanctionS;
/*     */   }
/*     */   
/*     */   public void setDateDebutSanctionS(String dateDebutSanctionS) {
/*  77 */     this.dateDebutSanctionS = dateDebutSanctionS;
/*     */   }
/*     */   
/*     */   public String getDateFinSanctionS() {
/*  81 */     return this.dateFinSanctionS;
/*     */   }
/*     */   
/*     */   public void setDateFinSanctionS(String dateFinSanctionS) {
/*  85 */     this.dateFinSanctionS = dateFinSanctionS;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/*  89 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  93 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public EmployeC getEmploye() {
/*  97 */     return this.employe;
/*     */   }
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 101 */     this.employe = employe;
/*     */   }
/*     */   
/*     */   public Constante.SanctionsDisciplinaires getSanction() {
/* 105 */     return this.sanction;
/*     */   }
/*     */   
/*     */   public void setSanction(Constante.SanctionsDisciplinaires sanction) {
/* 109 */     this.sanction = sanction;
/*     */   }
/*     */   
/*     */   public String getLibelleSanction() {
/* 113 */     return this.libelleSanction;
/*     */   }
/*     */   
/*     */   public void setLibelleSanction(String libelleSanction) {
/* 117 */     this.libelleSanction = libelleSanction;
/*     */   }
/*     */   
/*     */   public String getRaisonSanction() {
/* 121 */     return this.raisonSanction;
/*     */   }
/*     */   
/*     */   public void setRaisonSanction(String raisonSanction) {
/* 125 */     this.raisonSanction = raisonSanction;
/*     */   }
/*     */   
/*     */   public int getEtatSanction() {
/* 129 */     return this.etatSanction;
/*     */   }
/*     */   
/*     */   public void setEtatSanction(int etatSanction) {
/* 133 */     this.etatSanction = etatSanction;
/*     */   }
/*     */   
/*     */   public Date getDateDecision() {
/* 137 */     return this.dateDecision;
/*     */   }
/*     */   
/*     */   public void setDateDecision(Date dateDecision) {
/* 141 */     this.dateDecision = dateDecision;
/*     */   }
/*     */   
/*     */   public Date getDateRecours() {
/* 145 */     return this.dateRecours;
/*     */   }
/*     */   
/*     */   public void setDateRecours(Date dateRecours) {
/* 149 */     this.dateRecours = dateRecours;
/*     */   }
/*     */   
/*     */   public String getDateDecisionS() {
/* 153 */     return this.dateDecisionS;
/*     */   }
/*     */   
/*     */   public void setDateDecisionS(String dateDecisionS) {
/* 157 */     this.dateDecisionS = dateDecisionS;
/*     */   }
/*     */   
/*     */   public String getDateRecoursS() {
/* 161 */     return this.dateRecoursS;
/*     */   }
/*     */   
/*     */   public void setDateRecoursS(String dateRecoursS) {
/* 165 */     this.dateRecoursS = dateRecoursS;
/*     */   }
/*     */   
/*     */   public String getJustificationRecours() {
/* 169 */     return this.justificationRecours;
/*     */   }
/*     */   
/*     */   public void setJustificationRecours(String justificationRecours) {
/* 173 */     this.justificationRecours = justificationRecours;
/*     */   }
/*     */   
/*     */   public String getMotifDecision() {
/* 177 */     return this.motifDecision;
/*     */   }
/*     */   
/*     */   public void setMotifDecision(String motifDecision) {
/* 181 */     this.motifDecision = motifDecision;
/*     */   }
/*     */   
/*     */   public Constante.EtatSanction getEtatS() {
/* 185 */     return this.etatS;
/*     */   }
/*     */   
/*     */   public void setEtatS(Constante.EtatSanction etatS) {
/* 189 */     this.etatS = etatS;
/*     */   }
/*     */   
/*     */   public Date getDateSaisie() {
/* 193 */     return this.dateSaisie;
/*     */   }
/*     */   
/*     */   public void setDateSaisie(Date dateSaisie) {
/* 197 */     this.dateSaisie = dateSaisie;
/*     */   }
/*     */   
/*     */   public String getDateSaisieS() {
/* 201 */     return this.dateSaisieS;
/*     */   }
/*     */   
/*     */   public void setDateSaisieS(String dateSaisieS) {
/* 205 */     this.dateSaisieS = dateSaisieS;
/*     */   }
/*     */   
/*     */   public TraitementSalarialC getTraitement() {
/* 209 */     return this.traitement;
/*     */   }
/*     */   
/*     */   public void setTraitement(TraitementSalarialC traitement) {
/* 213 */     this.traitement = traitement;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\SaisieRegimeDisciplinaireC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */