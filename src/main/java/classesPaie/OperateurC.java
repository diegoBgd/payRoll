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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OperateurC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7819695991073962372L;
/*     */   private int id;
/*     */   private int idEmploye,idFonction;
/*     */   private boolean actif = true,lineUser;
/*     */   private Date dateModif;
/*     */   private String codeSecret;
/*     */   private String login;
/*     */   private Historique historique;
/*     */   private EmployeC employe;
/*     */   
/*     */   public boolean isActif() {
/*  30 */     return this.actif;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActif(boolean actif) {
/*  35 */     this.actif = actif;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeSecret() {
/*  40 */     return this.codeSecret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeSecret(String codeSecret) {
/*  45 */     this.codeSecret = codeSecret;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/*  50 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  55 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  60 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  65 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateModif() {
/*  70 */     return this.dateModif;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateModif(Date dateModif) {
/*  75 */     this.dateModif = dateModif;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLogin() {
/*  80 */     return this.login;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogin(String login) {
/*  85 */     this.login = login;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/*  90 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/*  95 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdEmploye() {
/* 100 */     return this.idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEmploye(int idEmploye) {
/* 105 */     this.idEmploye = idEmploye;
/*     */   }

			public boolean isLineUser() {
				return lineUser;
			}
			public void setLineUser(boolean lineUser) {
				this.lineUser = lineUser;
			}
			public int getIdFonction() {
				return idFonction;
			}
			public void setIdFonction(int idFonction) {
				this.idFonction = idFonction;
			}
		


/*     */ }


/* Location:              G:\PAIE\!\classesPaie\OperateurC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */