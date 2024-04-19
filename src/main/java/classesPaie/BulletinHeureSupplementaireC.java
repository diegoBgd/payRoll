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
/*     */ public class BulletinHeureSupplementaireC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6014821056875434144L;
/*     */   private int id;
/*     */   private int idBulletin;
/*     */   private int heures;
/*     */   private int minutes;
/*     */   private int secondes;
/*     */   private double pourcentage;
/*     */   private double montant;
/*     */   private Date dateTravail;
/*     */   private String HeurePreste;
/*     */   
/*     */   public int getId() {
/*  27 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  32 */     this.id = id;
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
/*     */   public int getHeures() {
/*  47 */     return this.heures;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeures(int heures) {
/*  52 */     this.heures = heures;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinutes() {
/*  57 */     return this.minutes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinutes(int minutes) {
/*  62 */     this.minutes = minutes;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSecondes() {
/*  67 */     return this.secondes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSecondes(int secondes) {
/*  72 */     this.secondes = secondes;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentage() {
/*  77 */     return this.pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentage(double pourcentage) {
/*  82 */     this.pourcentage = pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateTravail() {
/*  87 */     return this.dateTravail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateTravail(Date dateTravail) {
/*  92 */     this.dateTravail = dateTravail;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontant() {
/*  97 */     return this.montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontant(double montant) {
/* 102 */     this.montant = montant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeurePreste() {
/* 107 */     this.HeurePreste = String.valueOf(this.heures) + " H " + this.minutes + " Min " + this.secondes + " Sec ";
/* 108 */     return this.HeurePreste;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeurePreste(String heurePreste) {
/* 113 */     this.HeurePreste = heurePreste;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\BulletinHeureSupplementaireC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */