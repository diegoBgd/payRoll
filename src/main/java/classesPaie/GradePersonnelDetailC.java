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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GradePersonnelDetailC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8499043735773534770L;
/*     */   private int id;
/*     */   private int age;
/*     */   private int index;
/*     */   private int mentionUniversite;
/*     */   private double tauxBonusSalaire;
/*     */   private double salaireMensuel;
/*     */   private Historique historique;
/*     */   private String tauxBonusSalaireS;
/*     */   private boolean derogationConseilAdministration;
/*     */   private boolean propositionConseilFaculte;
/*     */   private GradePersonnelC grade;
/*     */   private Base niveau;
/*     */   private boolean existe;
/*     */   
/*     */   public int getId() {
/*  36 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  41 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxBonusSalaire() {
/*  46 */     return this.tauxBonusSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxBonusSalaire(double tauxBonusSalaire) {
/*  51 */     this.tauxBonusSalaire = tauxBonusSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  56 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  61 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxBonusSalaireS() {
/*  66 */     return this.tauxBonusSalaireS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxBonusSalaireS(String tauxBonusSalaireS) {
/*  71 */     this.tauxBonusSalaireS = tauxBonusSalaireS;
/*     */   }
/*     */ 
/*     */   
/*     */   public GradePersonnelC getGrade() {
/*  76 */     return this.grade;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGrade(GradePersonnelC grade) {
/*  81 */     this.grade = grade;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getNiveau() {
/*  86 */     return this.niveau;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNiveau(Base niveau) {
/*  91 */     this.niveau = niveau;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExiste() {
/*  96 */     return this.existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExiste(boolean existe) {
/* 101 */     this.existe = existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 106 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 111 */     this.index = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMentionUniversite() {
/* 116 */     return this.mentionUniversite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMentionUniversite(int mentionUniversite) {
/* 121 */     this.mentionUniversite = mentionUniversite;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDerogationConseilAdministration() {
/* 126 */     return this.derogationConseilAdministration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDerogationConseilAdministration(boolean derogationConseilAdministration) {
/* 131 */     this.derogationConseilAdministration = derogationConseilAdministration;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPropositionConseilFaculte() {
/* 136 */     return this.propositionConseilFaculte;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPropositionConseilFaculte(boolean propositionConseilFaculte) {
/* 141 */     this.propositionConseilFaculte = propositionConseilFaculte;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAge() {
/* 146 */     return this.age;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAge(int age) {
/* 151 */     this.age = age;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSalaireMensuel() {
/* 156 */     return this.salaireMensuel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSalaireMensuel(double salaireMensuel) {
/* 161 */     this.salaireMensuel = salaireMensuel;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\GradePersonnelDetailC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */