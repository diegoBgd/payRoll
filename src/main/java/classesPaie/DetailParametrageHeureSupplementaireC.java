/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Time;
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
/*     */ public class DetailParametrageHeureSupplementaireC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4528206862627401251L;
/*     */   private int id;
/*     */   private int index;
/*     */   private int numero;
/*     */   private Date heureDebut;
/*     */   private Date heureFin;
/*     */   private String heureDebutS;
/*     */   private String heureFinS;
/*     */   private String tauxS;
/*     */   private double taux;
/*     */   private ParametrageHeureSupplementaireC heureSupplementaire;
/*     */   private boolean existe;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  34 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  39 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/*  44 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/*  49 */     this.index = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumero() {
/*  54 */     return this.numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumero(int numero) {
/*  59 */     this.numero = numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getHeureDebut() {
/*  64 */     return this.heureDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureDebut(Date heureDebut) {
/*  69 */     this.heureDebut = heureDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getHeureFin() {
/*  74 */     return this.heureFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureFin(Date heureFin) {
/*  79 */     this.heureFin = heureFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureFin(Time heureFin) {
/*  84 */     this.heureFin = heureFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeureDebutS() {
/*  89 */     return this.heureDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureDebutS(String heureDebutS) {
/*  94 */     this.heureDebutS = heureDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeureFinS() {
/*  99 */     return this.heureFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureFinS(String heureFinS) {
/* 104 */     this.heureFinS = heureFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/* 109 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/* 114 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 119 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 124 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParametrageHeureSupplementaireC getHeureSupplementaire() {
/* 129 */     return this.heureSupplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureSupplementaire(ParametrageHeureSupplementaireC heureSupplementaire) {
/* 134 */     this.heureSupplementaire = heureSupplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxS() {
/* 139 */     return this.tauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxS(String tauxS) {
/* 144 */     this.tauxS = tauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTaux() {
/* 149 */     return this.taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTaux(double taux) {
/* 154 */     this.taux = taux;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DetailParametrageHeureSupplementaireC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */