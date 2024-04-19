/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ public class CreditC
/*     */   implements Serializable
/*     */ {
/*     */   private Historique historique;
/*     */   private ExerciceC exercice;
/*     */   private OperateurC operateur;
/*     */   private MarcheProgrammeC marche;
/*     */   private BanqueC banque;
			private EmployeC employe;

/*  44 */   private List<CreditDetailC> listDeleted = new ArrayList<CreditDetailC>();
/*  45 */   private List<CreditDetailC> listDetail = new ArrayList<CreditDetailC>(); 
			
			private double montantCredit,capital; 
			private double montantRembourse,reste; 
			private Date datePret;
			private String numeroDossier,printDateCrd; 
			private String numeroCompte; 
			private int frequence; 		 
			private int duree; 			
			private int id; 
			private int idEmploye;
			
			private static final long serialVersionUID = -8105196239575754642L;
			
			
 
			public EmployeC getEmploye() {
				return employe;
			}
			public void setEmploye(EmployeC employe) {
				this.employe = employe;
			}
			public double getMontantCredit() {
				return montantCredit;
			}
			public void setMontantCredit(double montantCredit) {
				this.montantCredit = montantCredit;
			}
			public double getMontantRembourse() {
				return montantRembourse;
			}
			public void setMontantRembourse(double montantRembourse) {
				this.montantRembourse = montantRembourse;
			}
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
/*     */   
/*     */   public BanqueC getBanque() {
/*  80 */     return this.banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBanque(BanqueC banque) {
/*  85 */     this.banque = banque;
/*     */   }
/*     */   
/*     */   public String getNumeroCompte() {
/* 110 */     return this.numeroCompte;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumeroCompte(String numeroCompte) {
/* 115 */     this.numeroCompte = numeroCompte;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDatePret() {
/* 120 */     return this.datePret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDatePret(Date datePret) {
/* 125 */     this.datePret = datePret;
/*     */   }
/*     */ 
 
/*     */   public String getNumeroDossier() {
/* 170 */     return this.numeroDossier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumeroDossier(String numeroDossier) {
/* 175 */     this.numeroDossier = numeroDossier;
/*     */   }
/*     */ 
/*     */   
/*     */   public MarcheProgrammeC getMarche() {
/* 180 */     return this.marche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMarche(MarcheProgrammeC marche) {
/* 185 */     this.marche = marche;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDuree() {
/* 190 */     return this.duree;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDuree(int duree) {
/* 195 */     this.duree = duree;
/*     */   }
/*     */ 
/*     */   

/*     */   public OperateurC getOperateur() {
/* 210 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 215 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 220 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 225 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 230 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 235 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
 
/*     */   public int getFrequence() {
/* 250 */     return this.frequence;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFrequence(int frequence) {
/* 255 */     this.frequence = frequence;
/*     */   }
   
/*     */   public List<CreditDetailC> getListDetail() {
/* 289 */     return this.listDetail;
/*     */   }
/*     */   
/*     */   public void setListDetail(List<CreditDetailC> listDetail) {
/* 293 */     this.listDetail = listDetail;
/*     */   }
/*     */   
/*     */   public List<CreditDetailC> getListDeleted() {
/* 297 */     return this.listDeleted;
/*     */   }
/*     */   
/*     */   public void setListDeleted(List<CreditDetailC> listDeleted) {
/* 301 */     this.listDeleted = listDeleted;
/*     */   }
			public double getCapital() {
				return capital;
			}
			public void setCapital(double capital) {
				this.capital = capital;
			}
			public double getReste() {
				return reste;
			}
			public void setReste(double reste) {
				this.reste = reste;
			}
			public int getIdEmploye() {
				return idEmploye;
			}
			public void setIdEmploye(int idEmploye) {
				this.idEmploye = idEmploye;
			}
			public String getPrintDateCrd() {
				printDateCrd=HelperC.convertDate(getDatePret());
				return printDateCrd;
			}
			public void setPrintDateCrd(String printDateCrd) {
				this.printDateCrd = printDateCrd;
			}
			public void calculRemboursement()
			{
				reste=getCapital()-getMontantRembourse();
				
			}
/*     */ }

