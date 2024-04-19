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
/*     */ public class DroitC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5088192229343968205L;
/*     */   private int id;
/*     */   private int numero;
/*     */   private String libelle;
/*     */   private boolean creer;
/*     */   private boolean modifier;
/*     */   private boolean supprimer;
/*     */   private boolean afficher;
/*     */   private boolean desactiveModifier;
/*     */   private boolean desactiveSupprimer;
/*     */   private RoleC role;
/*     */   private Constante.Role rol;
/*     */   private Base fonction;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  38 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelle() {
/*  43 */     return this.libelle;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelle(String libelle) {
/*  48 */     this.libelle = libelle;
/*     */   }
/*     */ 
/*     */   
/*     */   public RoleC getRole() {
/*  53 */     return this.role;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRole(RoleC role) {
/*  58 */     this.role = role;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCreer() {
/*  63 */     return this.creer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreer(boolean creer) {
/*  68 */     this.creer = creer;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isModifier() {
/*  73 */     return this.modifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModifier(boolean modifier) {
/*  78 */     this.modifier = modifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSupprimer() {
/*  83 */     return this.supprimer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSupprimer(boolean supprimer) {
/*  88 */     this.supprimer = supprimer;
/*     */   }
/*     */ 
/*     */   
/*     */   public Constante.Role getRol() {
/*  93 */     return this.rol;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRol(Constante.Role rol) {
/*  98 */     this.rol = rol;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 103 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 108 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumero() {
/* 113 */     return this.numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumero(int numero) {
/* 118 */     this.numero = numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDesactiveModifier() {
/* 123 */     return this.desactiveModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesactiveModifier(boolean desactiveModifier) {
/* 128 */     this.desactiveModifier = desactiveModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDesactiveSupprimer() {
/* 133 */     return this.desactiveSupprimer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesactiveSupprimer(boolean desactiveSupprimer) {
/* 138 */     this.desactiveSupprimer = desactiveSupprimer;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getFonction() {
/* 143 */     return this.fonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFonction(Base fonction) {
/* 148 */     this.fonction = fonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAfficher() {
/* 153 */     return this.afficher;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAfficher(boolean afficher) {
/* 158 */     this.afficher = afficher;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\DroitC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */