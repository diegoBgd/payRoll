/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ public class ParametrageDureeCongeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4088278207128196381L;
/*     */   private int id;
/*     */   private int nombreJoursAnnuel;
/*     */   private int nombreJoursAjoutes;
/*     */   private int nombreAnneesAjoutJour;

/*     */   private int joursConge;
/*     */   
/*     */   private String libelleJoursConge;
/*     */   private String libelleSorteConge;
/*     */   private Historique historique;
/*     */   private TypeCongeC type;
/*     */   private Base personnel;
/*     */   private Constante.SorteConge sorteConge;
/*     */   
/*     */   public int getId() {
/*  26 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  30 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getNombreJoursAnnuel() {
/*  34 */     return this.nombreJoursAnnuel;
/*     */   }
/*     */   
/*     */   public void setNombreJoursAnnuel(int nombreJoursAnnuel) {
/*  38 */     this.nombreJoursAnnuel = nombreJoursAnnuel;
/*     */   }
/*     */   
/*     */   public int getNombreJoursAjoutes() {
/*  42 */     return this.nombreJoursAjoutes;
/*     */   }
/*     */   
/*     */   public void setNombreJoursAjoutes(int nombreJoursAjoutes) {
/*  46 */     this.nombreJoursAjoutes = nombreJoursAjoutes;
/*     */   }
/*     */   
/*     */   public int getNombreAnneesAjoutJour() {
/*  50 */     return this.nombreAnneesAjoutJour;
/*     */   }
/*     */   
/*     */   public void setNombreAnneesAjoutJour(int nombreAnneesAjoutJour) {
/*  54 */     this.nombreAnneesAjoutJour = nombreAnneesAjoutJour;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/*  58 */     return this.historique;
/*     */   }
/*     */   public void setHistorique(Historique historique) {
/*  61 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public int getJoursConge() {
/*  71 */     return this.joursConge;
/*     */   }
/*     */   public void setJoursConge(int joursConge) {
/*  74 */     this.joursConge = joursConge;
/*     */   }
/*     */   public TypeCongeC getType() {
/*  77 */     return this.type;
/*     */   }
/*     */   public void setType(TypeCongeC type) {
/*  80 */     this.type = type;
/*     */   }
/*     */   public String getLibelleJoursConge() {
/*  83 */     return this.libelleJoursConge;
/*     */   }
/*     */   public void setLibelleJoursConge(String libelleJoursConge) {
/*  86 */     this.libelleJoursConge = libelleJoursConge;
/*     */   }
/*     */   
/*     */   public Base getPersonnel() {
/*  90 */     return this.personnel;
/*     */   }
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/*  94 */     this.personnel = personnel;
/*     */   }
/*     */   
/*     */   public Constante.SorteConge getSorteConge() {
/*  98 */     return this.sorteConge;
/*     */   }
/*     */   
/*     */   public void setSorteConge(Constante.SorteConge sorteConge) {
/* 102 */     this.sorteConge = sorteConge;
/*     */   }
/*     */   
/*     */   public String getLibelleSorteConge() {
/* 106 */     return this.libelleSorteConge;
/*     */   }
/*     */   
/*     */   public void setLibelleSorteConge(String libelleSorteConge) {
/* 110 */     this.libelleSorteConge = libelleSorteConge;
/*     */   }
/*     */   
/*     */  
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageDureeCongeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */