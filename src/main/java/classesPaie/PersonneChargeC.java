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
/*     */ public class PersonneChargeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5815287299923124570L;
/*     */   private int id;
/*     */   private int numero;
/*     */   private int relation;
/*     */   private double montant;
/*     */   private String nomPersonneCharge;
/*     */   private String photo;
/*     */   private String dateNaissanceS;
/*     */   private String sexe;
/*     */   private String sexeS;
/*     */   private String dateSaisieS;
			private int nombre;
/*     */   private String relationS;
/*     */   private String libelleRelation;
/*     */   private Date dateNaissance;
/*     */   private Date dateSaisie;
/*     */   private EmployeC employe;
/*  38 */ 
/*     */   private Historique historic;
/*     */   private OperateurC operator;
/*     */   
/*     */   public int getId() {
/*  44 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  49 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   
/*     */   public EmployeC getEmploye() {
/*  64 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  69 */     this.employe = employe;
/*     */   }
/*     */   
/*     */  
/*     */   
/*     */   public void setHistoric(Historique historic) {
/*  95 */     this.historic = historic;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperator() {
/* 100 */     return this.operator;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperator(OperateurC operator) {
/* 105 */     this.operator = operator;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumero() {
/* 110 */     return this.numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumero(int numero) {
/* 115 */     this.numero = numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRelation() {
/* 120 */     return this.relation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRelation(int relation) {
/* 125 */     this.relation = relation;
/*     */   }
/*     */ 
/*     */   

/*     */   public String getNomPersonneCharge() {
/* 140 */     return this.nomPersonneCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomPersonneCharge(String nomPersonneCharge) {
/* 145 */     this.nomPersonneCharge = nomPersonneCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPhoto() {
/* 150 */     return this.photo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhoto(String photo) {
/* 155 */     this.photo = photo;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateNaissanceS() {
/* 160 */     return this.dateNaissanceS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateNaissanceS(String dateNaissanceS) {
/* 165 */     this.dateNaissanceS = dateNaissanceS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSexeS() {
/* 170 */     return this.sexeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSexeS(String sexeS) {
/* 175 */     this.sexeS = sexeS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateSaisieS() {
/* 180 */     return this.dateSaisieS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateSaisieS(String dateSaisieS) {
/* 185 */     this.dateSaisieS = dateSaisieS;
/*     */   }
/*     */ 
/*     */   

/*     */   public String getRelationS() {
/* 200 */     return this.relationS;
/*     */   }
			public double getMontant() {
				return montant;
			}
			public void setMontant(double montant) {
				this.montant = montant;
			}
/*     */ 
/*     */   
/*     */   public void setRelationS(String relationS) {
/* 205 */     this.relationS = relationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateNaissance() {
/* 210 */     return this.dateNaissance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateNaissance(Date dateNaissance) {
/* 215 */     this.dateNaissance = dateNaissance;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateSaisie() {
/* 220 */     return this.dateSaisie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateSaisie(Date dateSaisie) {
/* 225 */     this.dateSaisie = dateSaisie;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleRelation() {
/* 230 */     return this.libelleRelation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelleRelation(String libelleRelation) {
/* 235 */     this.libelleRelation = libelleRelation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSexe() {
/* 240 */     return this.sexe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSexe(String sexe) {
/* 245 */     this.sexe = sexe;
/*     */   }
			public int getNombre() {
				return nombre;
			}
			public void setNombre(int nombre) {
				this.nombre = nombre;
			}
			public Historique getHistoric() {
				return historic;
			}

/*     */ }


