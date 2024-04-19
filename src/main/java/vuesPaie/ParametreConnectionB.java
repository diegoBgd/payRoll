/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.ParametreConnectionC;
/*     */ import java.io.IOException;
/*     */ import java.sql.Connection;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import persistencePaie.Connexion;
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametreConnectionB
/*     */   extends ParametreConnectionC
/*     */ {
/*     */   private static final long serialVersionUID = -6756194751191247346L;
/*     */   ParametreConnectionC parametre;
/*     */   private String user;
/*     */   private String pwd;
/*     */   private String serveur;
/*     */   private String dataBase;
/*     */   private String infoMsg;
/*     */   private boolean showMsg;
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  30 */     this.parametre = new ParametreConnectionC();
/*  31 */     chargerParameter();
/*     */   }
/*     */   
/*     */   public String getUser() {
/*  35 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(String user) {
/*  39 */     this.user = user;
/*     */   }
/*     */   
/*     */   public String getPwd() {
/*  43 */     return this.pwd;
/*     */   }
/*     */   
/*     */   public void setPwd(String pwd) {
/*  47 */     this.pwd = pwd;
/*     */   }
/*     */   
/*     */   public String getServeur() {
/*  51 */     return this.serveur;
/*     */   }
/*     */   
/*     */   public void setServeur(String serveur) {
/*  55 */     this.serveur = serveur;
/*     */   }
/*     */   
/*     */   public String getDataBase() {
/*  59 */     return this.dataBase;
/*     */   }
/*     */   
/*     */   public void setDataBase(String dataBase) {
/*  63 */     this.dataBase = dataBase;
/*     */   }
/*     */   
/*     */   public String getInfoMsg() {
/*  67 */     return this.infoMsg;
/*     */   }
/*     */   
/*     */   public void setInfoMsg(String infoMsg) {
/*  71 */     this.infoMsg = infoMsg;
/*     */   }
/*     */   
/*     */   public boolean isShowMsg() {
/*  75 */     return this.showMsg;
/*     */   }
/*     */   
/*     */   public void setShowMsg(boolean showMsg) {
/*  79 */     this.showMsg = showMsg;
/*     */   }
/*     */ 
/*     */   
/*     */   public void returnLogin() {
/*  84 */     Connexion.initializeConnection();
/*  85 */     Connexion.getConnection();
/*  86 */     FacesContext context = FacesContext.getCurrentInstance();
/*     */     try {
/*  88 */       context.getExternalContext().redirect("/payRoll/login.jsf");
/*  89 */     } catch (IOException e) {
/*     */       
/*  91 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void testConnection() {
/*  97 */     Connection con = null;
/*  98 */     Connexion.initializeConnection();
/*  99 */     con = Connexion.getConnection();
/* 100 */     if (con != null) {
/* 101 */       this.infoMsg = "Connexion réussie!";
/*     */     } else {
/* 103 */       this.infoMsg = "Connexion échouée !";
/*     */     } 
/* 105 */     this.showMsg = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerParameter() {
/* 110 */     this.parametre.readChaine();
/* 111 */     this.serveur = this.parametre.getNomHost();
/* 112 */     this.dataBase = this.parametre.getNomBaseDeDonnee();
/* 113 */     this.user = this.parametre.getNomUtilisateur();
/* 114 */     this.pwd = this.parametre.getMotDePasse();
/*     */   }
/*     */   
/*     */   public void save() {
/* 118 */     this.infoMsg = "";
/* 119 */     this.showMsg = false;
/* 120 */     this.parametre.setMotDePasse(this.pwd);
/* 121 */     this.parametre.setNomBaseDeDonnee(this.dataBase);
/* 122 */     this.parametre.setNomHost(this.serveur);
/* 123 */     this.parametre.setNomUtilisateur(this.user);
/* 124 */     this.infoMsg = this.parametre.enregistrer();
/* 125 */     this.showMsg = true;
/*     */   }
/*     */ }

