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
/*     */ 
/*     */ public class DetailCotisationEmployeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8082631340804262748L;
/*     */   private int id;
/*     */   private EmployeC employe;
/*     */   private CotisationC cotisation;
/*     */   private Historique historique;
/*     */   private double montantSalarial;
/*     */   private double montantPatronal;
/*     */   private String montantS;
/*     */   private boolean existe;
/*     */   
/*     */   public int getId() {
/*  28 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  33 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  38 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  43 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  48 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  53 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public CotisationC getCotisation() {
/*  58 */     return this.cotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCotisation(CotisationC cotisation) {
/*  63 */     this.cotisation = cotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantSalarial() {
/*  68 */     return this.montantSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantSalarial(double montantSalarial) {
/*  73 */     this.montantSalarial = montantSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantPatronal() {
/*  78 */     return this.montantPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantPatronal(double montantPatronal) {
/*  83 */     this.montantPatronal = montantPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMontantS() {
/*  88 */     return this.montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantS(String montantS) {
/*  93 */     this.montantS = montantS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/*  98 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/* 103 */     this.existe = existe;
/*     */   }
/*     */ }


