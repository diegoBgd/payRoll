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
/*     */ public class BaremeIPRC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2757212180809734906L;
/*     */   private int id;
/*     */   private int typeEmploye;
/*     */   private int type;
/*     */   private String numero;
/*     */   private String description;
/*     */   private String formule;
/*     */   private Date date;
/*     */   private double pensionComplementaire;
/*     */   private double transportBrut;
/*     */   private double montantPersonneCharge;
/*     */   private double pourcentagePersonneCharge;
/*     */   private double logementNonImposable;
/*     */   private boolean actif;
/*     */   private MarcheProgrammeC marche;
/*     */   private Historique historique;
/*  34 */   private List<DetailBaremeC> listDetailBareme = new ArrayList<DetailBaremeC>();
/*  35 */   private List<DetailBaremeC> listDetailBaremeDeleted = new ArrayList<DetailBaremeC>();
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  40 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  45 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getTypeEmploye() {
/*  49 */     return this.typeEmploye;
/*     */   }
/*     */   
/*     */   public void setTypeEmploye(int typeEmploye) {
/*  53 */     this.typeEmploye = typeEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNumero() {
/*  58 */     return this.numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumero(String numero) {
/*  63 */     this.numero = numero;
/*     */   }
/*     */   
/*     */   public int getType() {
/*  67 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/*  71 */     this.type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDate() {
/*  76 */     return this.date;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Date date) {
/*  81 */     this.date = date;
/*     */   }
/*     */ 
/*     */   
/*     */   public MarcheProgrammeC getMarche() {
/*  96 */     return this.marche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMarche(MarcheProgrammeC marche) {
/* 101 */     this.marche = marche;
/*     */   }
/*     */   
/*     */   public List<DetailBaremeC> getListDetailBareme() {
/* 105 */     return this.listDetailBareme;
/*     */   }
/*     */   
/*     */   public void setListDetailBareme(List<DetailBaremeC> listDetailBareme) {
/* 109 */     this.listDetailBareme = listDetailBareme;
/*     */   }
/*     */   
/*     */   public List<DetailBaremeC> getListDetailBaremeDeleted() {
/* 113 */     return this.listDetailBaremeDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailBaremeDeleted(List<DetailBaremeC> listDetailBaremeDeleted) {
/* 118 */     this.listDetailBaremeDeleted = listDetailBaremeDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 123 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 128 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPensionComplementaire() {
/* 133 */     return this.pensionComplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPensionComplementaire(double pensionComplementaire) {
/* 138 */     this.pensionComplementaire = pensionComplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTransportBrut() {
/* 143 */     return this.transportBrut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransportBrut(double transportBrut) {
/* 148 */     this.transportBrut = transportBrut;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentagePersonneCharge() {
/* 153 */     return this.pourcentagePersonneCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentagePersonneCharge(double pourcentagePersonneCharge) {
/* 158 */     this.pourcentagePersonneCharge = pourcentagePersonneCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLogementNonImposable() {
/* 163 */     return this.logementNonImposable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogementNonImposable(double logementNonImposable) {
/* 168 */     this.logementNonImposable = logementNonImposable;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantPersonneCharge() {
/* 173 */     return this.montantPersonneCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantPersonneCharge(double montantPersonneCharge) {
/* 178 */     this.montantPersonneCharge = montantPersonneCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFormule() {
/* 183 */     return this.formule;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormule(String formule) {
/* 188 */     this.formule = formule;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 192 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 196 */     this.description = description;
/*     */   }
			public boolean isActif() {
				return actif;
			}
			public void setActif(boolean actif) {
				this.actif = actif;
			}

/*     */ }


/* Location:              G:\PAIE\!\classesPaie\BaremeIPRC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */