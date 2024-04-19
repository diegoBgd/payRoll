/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
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
/*     */ public class NatureCongeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4192532924603418202L;
/*     */   private int id;
/*     */   private int nombreJoursAnnuel;
/*     */   private int nombreJoursAjoutes;
/*     */   private int nombreAnneesAjoutJour;
/*     */   private int dureeMax;
/*     */   private int joursConge;
/*     */   private String code;
/*     */   private String designation;
/*     */   private String libelleJoursConge;
/*     */   private String numReferenceDecision;
/*     */   private Date dateDecision;
/*     */   private Date dateFinDecision;
/*     */   private Historique historique;
/*     */   private TypeCongeC type;
/*     */   private Base personnel;
/*     */   
/*     */   public int getId() {
/*  36 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  41 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNombreJoursAnnuel() {
/*  46 */     return this.nombreJoursAnnuel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreJoursAnnuel(int nombreJoursAnnuel) {
/*  51 */     this.nombreJoursAnnuel = nombreJoursAnnuel;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNombreJoursAjoutes() {
/*  56 */     return this.nombreJoursAjoutes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreJoursAjoutes(int nombreJoursAjoutes) {
/*  61 */     this.nombreJoursAjoutes = nombreJoursAjoutes;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNombreAnneesAjoutJour() {
/*  66 */     return this.nombreAnneesAjoutJour;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreAnneesAjoutJour(int nombreAnneesAjoutJour) {
/*  71 */     this.nombreAnneesAjoutJour = nombreAnneesAjoutJour;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  76 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  81 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/*  86 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/*  91 */     this.designation = designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  96 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 101 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDureeMax() {
/* 106 */     return this.dureeMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDureeMax(int dureeMax) {
/* 111 */     this.dureeMax = dureeMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getJoursConge() {
/* 116 */     return this.joursConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJoursConge(int joursConge) {
/* 121 */     this.joursConge = joursConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeCongeC getType() {
/* 126 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(TypeCongeC type) {
/* 131 */     this.type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleJoursConge() {
/* 136 */     return this.libelleJoursConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelleJoursConge(String libelleJoursConge) {
/* 141 */     this.libelleJoursConge = libelleJoursConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getPersonnel() {
/* 146 */     return this.personnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/* 151 */     this.personnel = personnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNumReferenceDecision() {
/* 156 */     return this.numReferenceDecision;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumReferenceDecision(String numReferenceDecision) {
/* 161 */     this.numReferenceDecision = numReferenceDecision;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDecision() {
/* 166 */     return this.dateDecision;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDecision(Date dateDecision) {
/* 171 */     this.dateDecision = dateDecision;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFinDecision() {
/* 176 */     return this.dateFinDecision;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinDecision(Date dateFinDecision) {
/* 181 */     this.dateFinDecision = dateFinDecision;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\NatureCongeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */