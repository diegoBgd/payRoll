/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ public class ParametragePrimeC
/*     */   implements Serializable
/*     */ {
/*  25 */   private List<ParametragePrimeDetailC> listDeleted = new ArrayList<ParametragePrimeDetailC>();
/*  26 */   private List<ParametragePrimeDetailC> listDetail = new ArrayList<ParametragePrimeDetailC>(); 
			private String libelleCategorie; private String libelleFonction; private String libelleGrade; private String libellePrime; private String libellePersonnel; private Historique hist; private boolean calculHeurSup; private double plafond; private double plancher;
/*     */   private double forfait;
/*     */   
/*     */   public List<ParametragePrimeDetailC> getListDetail() {
/*  30 */     return this.listDetail;
/*     */   }
/*     */   private double taux; private int priorite; private int typeBase; private int idPrime; private int idFonction; private int idGrade; private int idCategorie; private int idPersonnel; private int id; private static final long serialVersionUID = 5809956522017435354L;
/*     */   public void setListDetail(List<ParametragePrimeDetailC> listDetail) {
/*  34 */     this.listDetail = listDetail;
/*     */   }
/*     */   
/*     */   public List<ParametragePrimeDetailC> getListDeleted() {
/*  38 */     return this.listDeleted;
/*     */   }
/*     */   
/*     */   public void setListDeleted(List<ParametragePrimeDetailC> listDeleted) {
/*  42 */     this.listDeleted = listDeleted;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  46 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  49 */     this.id = id;
/*     */   }
/*     */   public int getIdPersonnel() {
/*  52 */     return this.idPersonnel;
/*     */   }
/*     */   public void setIdPersonnel(int idPersonnel) {
/*  55 */     this.idPersonnel = idPersonnel;
/*     */   }
/*     */   public int getIdCategorie() {
/*  58 */     return this.idCategorie;
/*     */   }
/*     */   public void setIdCategorie(int idCategorie) {
/*  61 */     this.idCategorie = idCategorie;
/*     */   }
/*     */   public int getIdGrade() {
/*  64 */     return this.idGrade;
/*     */   }
/*     */   public void setIdGrade(int idGrade) {
/*  67 */     this.idGrade = idGrade;
/*     */   }
/*     */   public int getIdFonction() {
/*  70 */     return this.idFonction;
/*     */   }
/*     */   public void setIdFonction(int idFonction) {
/*  73 */     this.idFonction = idFonction;
/*     */   }
/*     */   public double getTaux() {
/*  76 */     return this.taux;
/*     */   }
/*     */   public void setTaux(double taux) {
/*  79 */     this.taux = taux;
/*     */   }
/*     */   public double getForfait() {
/*  82 */     return this.forfait;
/*     */   }
/*     */   public void setForfait(double forfait) {
/*  85 */     this.forfait = forfait;
/*     */   }
/*     */   public double getPlancher() {
/*  88 */     return this.plancher;
/*     */   }
/*     */   public void setPlancher(double plancher) {
/*  91 */     this.plancher = plancher;
/*     */   }
/*     */   public double getPlafond() {
/*  94 */     return this.plafond;
/*     */   }
/*     */   public void setPlafond(double plafond) {
/*  97 */     this.plafond = plafond;
/*     */   }
/*     */   public boolean isCalculHeurSup() {
/* 100 */     return this.calculHeurSup;
/*     */   }
/*     */   public void setCalculHeurSup(boolean calculHeurSup) {
/* 103 */     this.calculHeurSup = calculHeurSup;
/*     */   }
/*     */   public Historique getHist() {
/* 106 */     return this.hist;
/*     */   }
/*     */   public void setHist(Historique hist) {
/* 109 */     this.hist = hist;
/*     */   }
/*     */   public int getIdPrime() {
/* 112 */     return this.idPrime;
/*     */   }
/*     */   public void setIdPrime(int idPrime) {
/* 115 */     this.idPrime = idPrime;
/*     */   }
/*     */   public String getLibellePersonnel() {
/* 118 */     return this.libellePersonnel;
/*     */   }
/*     */   public void setLibellePersonnel(String libellePersonnel) {
/* 121 */     this.libellePersonnel = libellePersonnel;
/*     */   }
/*     */   public String getLibellePrime() {
/* 124 */     return this.libellePrime;
/*     */   }
/*     */   public void setLibellePrime(String libellePrime) {
/* 127 */     this.libellePrime = libellePrime;
/*     */   }
/*     */   public String getLibelleGrade() {
/* 130 */     return this.libelleGrade;
/*     */   }
/*     */   public void setLibelleGrade(String libelleGrade) {
/* 133 */     this.libelleGrade = libelleGrade;
/*     */   }
/*     */   public String getLibelleFonction() {
/* 136 */     return this.libelleFonction;
/*     */   }
/*     */   public void setLibelleFonction(String libelleFonction) {
/* 139 */     this.libelleFonction = libelleFonction;
/*     */   }
/*     */   public String getLibelleCategorie() {
/* 142 */     return this.libelleCategorie;
/*     */   }
/*     */   public void setLibelleCategorie(String libelleCategorie) {
/* 145 */     this.libelleCategorie = libelleCategorie;
/*     */   }
/*     */   public int getTypeBase() {
/* 148 */     return this.typeBase;
/*     */   }
/*     */   public void setTypeBase(int typeBase) {
/* 151 */     this.typeBase = typeBase;
/*     */   }
/*     */   
/*     */   public int getPriorite() {
/* 155 */     return this.priorite;
/*     */   }
/*     */   
/*     */   public void setPriorite(int priorite) {
/* 159 */     this.priorite = priorite;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\ParametragePrimeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */