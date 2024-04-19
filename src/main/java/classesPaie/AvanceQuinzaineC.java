/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
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
/*     */ public class AvanceQuinzaineC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4426935757480190419L;
/*     */   private int id;
/*     */   private int modePaiement;
/*     */   private int moisConcerne;
/*     */   private int typeAvance;
/*     */   private double montant;
/*     */   private String montantString;
/*     */   private String numeroCompte;
/*     */   private String dateString;
/*     */   private String nomEmploye;
/*     */   private Date date;
/*     */   private EmployeC employe;
/*     */   private BanqueC banque;
/*     */   private Historique historique;
/*     */   private ExerciceC exercice;
/*     */   private OperateurC operateur;
/*  36 */   private List<AvanceDetailC> listDetail = new ArrayList<AvanceDetailC>();
/*  37 */   private List<AvanceDetailC> listDeleted = new ArrayList<AvanceDetailC>();
/*     */ 	private int idPaie;
/*     */ 
/*     */   
/*     */   public String getNomEmploye() {
/*  42 */     return this.nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmploye(String nomEmploye) {
/*  47 */     this.nomEmploye = nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  52 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  57 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDate() {
/*  62 */     return this.date;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Date date) {
/*  67 */     this.date = date;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  72 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  77 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  82 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  87 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  92 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  97 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/* 102 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 107 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/* 112 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/* 117 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantString() {
/* 122 */     return this.montantString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantString(String montantString) {
/* 127 */     this.montantString = montantString;
/*     */   }
/*     */ 
/*     */   
/*     */   public BanqueC getBanque() {
/* 132 */     return this.banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBanque(BanqueC banque) {
/* 137 */     this.banque = banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getModePaiement() {
/* 142 */     return this.modePaiement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModePaiement(int modePaiement) {
/* 147 */     this.modePaiement = modePaiement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNumeroCompte() {
/* 152 */     return this.numeroCompte;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumeroCompte(String numeroCompte) {
/* 157 */     this.numeroCompte = numeroCompte;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTypeAvance() {
/* 162 */     return this.typeAvance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeAvance(int typeAvance) {
/* 167 */     this.typeAvance = typeAvance;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateString() {
/* 172 */     return this.dateString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateString(String dateString) {
/* 177 */     this.dateString = dateString;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMoisConcerne() {
/* 182 */     return this.moisConcerne;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMoisConcerne(int moisConcerne) {
/* 187 */     this.moisConcerne = moisConcerne;
/*     */   }
/*     */   
/*     */   public List<AvanceDetailC> getListDetail() {
/* 191 */     return this.listDetail;
/*     */   }
/*     */   
/*     */   public void setListDetail(List<AvanceDetailC> listDetail) {
/* 195 */     this.listDetail = listDetail;
/*     */   }
/*     */   
/*     */   public List<AvanceDetailC> getListDeleted() {
/* 199 */     return this.listDeleted;
/*     */   }
/*     */   
/*     */   public void setListDeleted(List<AvanceDetailC> listDeleted) {
/* 203 */     this.listDeleted = listDeleted;
/*     */   }
			public int getIdPaie() {
				return idPaie;
			}
			public void setIdPaie(int idPaie) {
				this.idPaie = idPaie;
			}

/*     */ }


/* Location:              G:\PAIE\!\classesPaie\AvanceQuinzaineC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */