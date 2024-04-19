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
/*     */ 
/*     */ public class TraitementSalarialC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1665864970233221058L;
/*     */   private int id,idRef;
/*     */   private int typeAvancement;
/*     */   private double salaireBase;
/*     */   private double pourcentage;
/*     */   private double ancienSalaire;
/*     */   private Date dateDebut;
/*     */   private Date dateFin;
/*     */   private String dateDebutS;
/*     */   private String salaireBaseS;
/*     */   private String pourcentageS;
/*     */   private String ancienSalaireS,comment;
/*     */   private Historique historique;
/*     */   private EmployeC employe;
/*     */   
/*     */   public int getId() {
/*  35 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  40 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTypeAvancement() {
/*  45 */     return this.typeAvancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeAvancement(int typeAvancement) {
/*  50 */     this.typeAvancement = typeAvancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSalaireBase() {
/*  55 */     return this.salaireBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSalaireBase(double salaireBase) {
/*  60 */     this.salaireBase = salaireBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentage() {
/*  65 */     return this.pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentage(double pourcentage) {
/*  70 */     this.pourcentage = pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/*  75 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  80 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/*  85 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/*  90 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSalaireBaseS() {
/*  95 */     return this.salaireBaseS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSalaireBaseS(String salaireBaseS) {
/* 100 */     this.salaireBaseS = salaireBaseS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPourcentageS() {
/* 105 */     return this.pourcentageS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageS(String pourcentageS) {
/* 110 */     this.pourcentageS = pourcentageS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 115 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 120 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/* 125 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 130 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAncienSalaire() {
/* 135 */     return this.ancienSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAncienSalaire(double ancienSalaire) {
/* 140 */     this.ancienSalaire = ancienSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAncienSalaireS() {
/* 145 */     return this.ancienSalaireS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAncienSalaireS(String ancienSalaireS) {
/* 150 */     this.ancienSalaireS = ancienSalaireS;
/*     */   }
/*     */   
/*     */   public Date getDateFin() {
/* 154 */     return this.dateFin;
/*     */   }
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/* 158 */     this.dateFin = dateFin;
/*     */   }
			public int getIdRef() {
				return idRef;
			}
			public void setIdRef(int idRef) {
				this.idRef = idRef;
			}
			public String getComment() {
				return comment;
			}
			public void setComment(String comment) {
				this.comment = comment;
			}

			
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\TraitementSalarialC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */