/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BulletinAllocationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5588953129834874886L;
/*     */   private int id;
/*     */   private int idPerson;
/*     */   private int idBulletin;
/*     */   private double montant;
/*     */   private String nomPersonne;
/*     */   private String satut;
/*     */   private String printMontant;
/*     */   private PersonneChargeC personneCharge;
/*     */   private int nombre;
/*     */   public PersonneChargeC getPersonneCharge() {
/*  27 */     return this.personneCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPersonneCharge(PersonneChargeC personneCharge) {
/*  32 */     this.personneCharge = personneCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdBulletin() {
/*  37 */     return this.idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdBulletin(int idBulletin) {
/*  42 */     this.idBulletin = idBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  47 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  52 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdPerson() {
/*  57 */     return this.idPerson;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdPerson(int idPerson) {
/*  62 */     this.idPerson = idPerson;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/*  67 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/*  72 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomPersonne() {
/*  77 */     if (this.personneCharge != null)
/*     */     {
/*  79 */       this.nomPersonne = this.personneCharge.getNomPersonneCharge();
/*     */     }
/*  81 */     return this.nomPersonne;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomPersonne(String nomPersonne) {
/*  86 */     this.nomPersonne = nomPersonne;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSatut() {
/*  91 */     if (this.personneCharge != null)
/*     */     {
/*  93 */       this.satut = Constante.getLibelleStatutPersonneACharge(this.personneCharge.getRelation());
/*     */     }
/*  95 */     return this.satut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSatut(String satut) {
/* 100 */     this.satut = satut;
/*     */   }
/*     */   
/*     */   public String getPrintMontant() {
/* 104 */     this.printMontant = HelperC.decimalNumber(getMontant(), 0, true);
/* 105 */     return this.printMontant;
/*     */   }
/*     */   
/*     */   public void setPrintMontant(String printMontant) {
/* 109 */     this.printMontant = printMontant;
/*     */   }
			public int getNombre() {
				return nombre;
			}
			public void setNombre(int nombre) {
				this.nombre = nombre;
			}

/*     */ }


