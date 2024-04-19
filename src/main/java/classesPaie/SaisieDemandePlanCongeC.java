/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ public class SaisieDemandePlanCongeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6328431253063915857L;
/*     */   private int id;
/*     */   private int etat;
/*     */   private String motif;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*     */   private String dateAttributionS;
/*     */   private String dureeS;
/*     */   private String motifRejet;
/*     */   private String libelleDemandePlanning;
/*     */   private String libelleSorteConge;
/*     */   private double duree;
/*     */   private Date dateDebut;
/*     */   private Date dateFin;
/*     */   private Date dateAttribution;
/*     */   private Historique historique;
/*     */   private Constante.EtatDemandePlanningConge etatDemandePlanning;
/*     */   private Constante.SorteConge sorteConge;
/*     */   private EmployeC employe;
/*     */   private TypeCongeC typeConge;
/*     */   private ParametrageDureeCongeC natureConge;
/*     */   
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  37 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getMotif() {
/*  41 */     return this.motif;
/*     */   }
/*     */   
/*     */   public void setMotif(String motif) {
/*  45 */     this.motif = motif;
/*     */   }
/*     */   
/*     */   public String getDateDebutS() {
/*  49 */     return this.dateDebutS;
/*     */   }
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/*  53 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */   
/*     */   public String getDateFinS() {
/*  57 */     return this.dateFinS;
/*     */   }
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/*  61 */     this.dateFinS = dateFinS;
/*     */   }
/*     */   
/*     */   public String getDateAttributionS() {
/*  65 */     return this.dateAttributionS;
/*     */   }
/*     */   
/*     */   public void setDateAttributionS(String dateAttributionS) {
/*  69 */     this.dateAttributionS = dateAttributionS;
/*     */   }
/*     */   
/*     */   public double getDuree() {
/*  73 */     return this.duree;
/*     */   }
/*     */   
/*     */   public void setDuree(double duree) {
/*  77 */     this.duree = duree;
/*     */   }
/*     */   
/*     */   public Date getDateDebut() {
/*  81 */     return this.dateDebut;
/*     */   }
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  85 */     this.dateDebut = dateDebut;
/*     */   }
/*     */   
/*     */   public Date getDateFin() {
/*  89 */     return this.dateFin;
/*     */   }
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/*  93 */     this.dateFin = dateFin;
/*     */   }
/*     */   
/*     */   public Date getDateAttribution() {
/*  97 */     return this.dateAttribution;
/*     */   }
/*     */   
/*     */   public void setDateAttribution(Date dateAttribution) {
/* 101 */     this.dateAttribution = dateAttribution;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/* 105 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 109 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public EmployeC getEmploye() {
/* 113 */     return this.employe;
/*     */   }
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 117 */     this.employe = employe;
/*     */   }
/*     */   
/*     */   public ParametrageDureeCongeC getNatureConge() {
/* 121 */     return this.natureConge;
/*     */   }
/*     */   
/*     */   public void setNatureConge(ParametrageDureeCongeC natureConge) {
/* 125 */     this.natureConge = natureConge;
/*     */   }
/*     */   
/*     */   public TypeCongeC getTypeConge() {
/* 129 */     return this.typeConge;
/*     */   }
/*     */   
/*     */   public void setTypeConge(TypeCongeC typeConge) {
/* 133 */     this.typeConge = typeConge;
/*     */   }
/*     */   
/*     */   public String getDureeS() {
/* 137 */     return this.dureeS;
/*     */   }
/*     */   
/*     */   public void setDureeS(String dureeS) {
/* 141 */     this.dureeS = dureeS;
/*     */   }
/*     */   
/*     */   public String getLibelleDemandePlanning() {
/* 145 */     return this.libelleDemandePlanning;
/*     */   }
/*     */   
/*     */   public void setLibelleDemandePlanning(String libelleDemandePlanning) {
/* 149 */     this.libelleDemandePlanning = libelleDemandePlanning;
/*     */   }
/*     */   
/*     */   public Constante.EtatDemandePlanningConge getEtatDemandePlanning() {
/* 153 */     return this.etatDemandePlanning;
/*     */   }
/*     */   
/*     */   public void setEtatDemandePlanning(Constante.EtatDemandePlanningConge etatDemandePlanning) {
/* 157 */     this.etatDemandePlanning = etatDemandePlanning;
/*     */   }
/*     */   
/*     */   public int getEtat() {
/* 161 */     return this.etat;
/*     */   }
/*     */   
/*     */   public void setEtat(int etat) {
/* 165 */     this.etat = etat;
/*     */   }
/*     */   
/*     */   public String getLibelleSorteConge() {
/* 169 */     return this.libelleSorteConge;
/*     */   }
/*     */   
/*     */   public void setLibelleSorteConge(String libelleSorteConge) {
/* 173 */     this.libelleSorteConge = libelleSorteConge;
/*     */   }
/*     */   
/*     */   public Constante.SorteConge getSorteConge() {
/* 177 */     return this.sorteConge;
/*     */   }
/*     */   
/*     */   public void setSorteConge(Constante.SorteConge sorteConge) {
/* 181 */     this.sorteConge = sorteConge;
/*     */   }
/*     */   
/*     */   public String getMotifRejet() {
/* 185 */     return this.motifRejet;
/*     */   }
/*     */   
/*     */   public void setMotifRejet(String motifRejet) {
/* 189 */     this.motifRejet = motifRejet;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\SaisieDemandePlanCongeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */