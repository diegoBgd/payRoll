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
/*     */ public class Base
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3951661310585326273L;
/*     */   private int id;
/*     */   private String code;
/*     */   private String designation;
/*     */   private String symbole;
/*     */   private Date dateCreation;
/*     */   private Date dateModif;
/*     */   private OperateurC operCreation;
/*     */   private OperateurC operModif;
/*     */   private Historique historique;
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
/*     */   public String getCode() {
/*  37 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  42 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignation() {
/*  47 */     return this.designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignation(String designation) {
/*  52 */     this.designation = designation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSymbole() {
/*  57 */     return this.symbole;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSymbole(String symbole) {
/*  62 */     this.symbole = symbole;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateCreation() {
/*  67 */     return this.dateCreation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateCreation(Date dateCreation) {
/*  72 */     this.dateCreation = dateCreation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateModif() {
/*  77 */     return this.dateModif;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateModif(Date dateModif) {
/*  82 */     this.dateModif = dateModif;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperCreation() {
/*  87 */     return this.operCreation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperCreation(OperateurC operCreation) {
/*  92 */     this.operCreation = operCreation;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperModif() {
/*  97 */     return this.operModif;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperModif(OperateurC operModif) {
/* 102 */     this.operModif = operModif;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 107 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 112 */     this.historique = historique;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\Base.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */