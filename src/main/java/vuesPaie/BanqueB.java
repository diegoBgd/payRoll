/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.BanqueC;
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class BanqueB
/*     */   extends BanqueC implements Serializable {
/*     */   private static final long serialVersionUID = 7945184805910695243L;
/*     */   private BanqueC banque;
/*     */   private BanqueC banqueVirment;
/*     */   private List<BanqueC> banques;
/*     */   private List<BanqueC> banqueVirementS;
/*     */   private String codeBanqueVirement;
/*     */   private String designationBanqueVirement;
/*     */   private String compteBanqueVirement;
/*     */   private String codeBanqueRecherche;
/*     */   private String designationBanqueRecherche;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
			private boolean disableMsg;
/*  42 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */ 
/*     */   
/*     */   public BanqueC getBanque() {
/*  48 */     return this.banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBanque(BanqueC banque) {
/*  53 */     this.banque = banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeBanqueVirement() {
/*  58 */     return this.codeBanqueVirement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeBanqueVirement(String codeBanqueVirement) {
/*  63 */     this.codeBanqueVirement = codeBanqueVirement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignationBanqueVirement() {
/*  68 */     return this.designationBanqueVirement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignationBanqueVirement(String designationBanqueVirement) {
/*  73 */     this.designationBanqueVirement = designationBanqueVirement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCompteBanqueVirement() {
/*  78 */     return this.compteBanqueVirement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCompteBanqueVirement(String compteBanqueVirement) {
/*  83 */     this.compteBanqueVirement = compteBanqueVirement;
/*     */   }
/*     */   
/*     */   public List<BanqueC> getBanques() {
/*  87 */     return this.banques;
/*     */   }
/*     */   
/*     */   public void setBanques(List<BanqueC> banques) {
/*  91 */     this.banques = banques;
/*     */   }
/*     */   
/*     */   public List<BanqueC> getBanqueVirementS() {
/*  95 */     return this.banqueVirementS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBanqueVirementS(List<BanqueC> banqueVirementS) {
/* 100 */     this.banqueVirementS = banqueVirementS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCodeBanqueRecherche() {
/* 106 */     return this.codeBanqueRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeBanqueRecherche(String codeBanqueRecherche) {
/* 111 */     this.codeBanqueRecherche = codeBanqueRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDesignationBanqueRecherche() {
/* 116 */     return this.designationBanqueRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesignationBanqueRecherche(String designationBanqueRecherche) {
/* 121 */     this.designationBanqueRecherche = designationBanqueRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/* 126 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 131 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 136 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 141 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 146 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 151 */     this.session = session;
/*     */   }
/*     */   
/*     */   public BanqueC getBanqueVirment() {
/* 155 */     return this.banqueVirment;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBanqueVirment(BanqueC banqueVirment) {
/* 160 */     this.banqueVirment = banqueVirment;
}
public boolean isDisableMsg() {
	return disableMsg;
}
public void setDisableMsg(boolean disableMsg) {
	this.disableMsg = disableMsg;
}
/*     */   @PostConstruct
/*     */   private void init() {
/* 167 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 168 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 169 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 170 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 171 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 175 */         FacesContext context = FacesContext.getCurrentInstance();
/* 176 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 178 */       catch (IOException e) {
/*     */         
/* 180 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/* 184 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 185 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 186 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 187 */       if (this.userFonction != null)
/*     */       {
/* 189 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
				chargerBanque();
/*     */     } 
/*     */   }

/*     */ 
/*     */ 
/*     */   
/*     */   public void chargerBanque() {
/* 197 */     this.banques = FichierBaseDAO.getInstance().getBanques();
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 202 */     if (b)
/*     */     {
/* 204 */       setCode("");
/*     */     }
			  disableMsg=true;
/* 206 */     setId(0);
/* 207 */     setDesignation("");
/* 208 */     setTelephone1("");
/* 209 */     setTelephone2("");
/* 210 */     setAdresse("");
/* 211 */     setCompte("");
/* 212 */     setCodeBanqueVirement("");
/* 213 */     setDesignationBanqueVirement("");
/* 214 */     setCompteBanqueVirement("");
/* 215 */     setBanqueVirement("");
/* 216 */    
/* 218 */     if (this.banqueVirementS != null) {
/* 219 */       this.banqueVirementS.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setObject() {
			 disableMsg=true;
/* 224 */     if (this.banque != null) {
/*     */       
/* 226 */       setId(this.banque.getId());
/* 227 */       setCode(this.banque.getCode());
/* 228 */       setDesignation(this.banque.getDesignation());
/* 229 */       setTelephone1(this.banque.getTelephone1());
/* 230 */       setTelephone2(this.banque.getTelephone2());
/* 231 */       setAdresse(this.banque.getAdresse());
/* 232 */       setCompte(this.banque.getCompte());
/*     */       disableMsg=false;
/* 234 */       if (this.banque.getBanqueVirement() != null && !this.banque.getBanqueVirement().equals("")) {
/*     */         
/* 236 */         this.banqueVirment = FichierBaseDAO.getInstance().getBanque(this.banque.getBanqueVirement());
/* 237 */         this.codeBanqueVirement = this.banqueVirment.getCode();
/* 238 */         this.designationBanqueVirement = this.banqueVirment.getDesignation();
/* 239 */         this.compteBanqueVirement = this.banqueVirment.getCompte();
/* 240 */         setBanqueVirement(this.codeBanqueVirement);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 247 */     if (getCode().trim().equals("")) {
/*     */       
/* 249 */       clear(true);
/*     */     } else {
/*     */       
/* 252 */       this.banque = FichierBaseDAO.getInstance().getBanque(getCode());
/* 253 */       if (this.banque == null) {
/*     */         
/* 255 */         clear(false);
/*     */       } else {
/*     */         
/* 258 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected() {
/* 265 */     if (this.banque != null) {
/*     */       
/* 267 */       setObject();
/* 268 */       PrimeFaces.current().executeScript("PF('dlgBanque').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void focusBanque() {
/* 276 */     this.codeBanqueVirement = "";
/* 277 */     this.designationBanqueVirement = "";
/* 278 */     if (this.banqueVirementS != null)
/*     */     {
/* 280 */       this.banqueVirementS.clear();
/*     */     }
/* 282 */     HelperC.afficherMessage("Information", "Double-cliquer pour lance une recherche");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObjectBanque() {
/* 287 */     setObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeBanque() {
/* 293 */     if (getBanqueVirement() != null)
/*     */     {
/* 295 */       setObjectBanque();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */ 
/*     */   
/*     */  
/*     */   
/*     */   public void enregistrer() {
/* 322 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 324 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 326 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 328 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 330 */     else if (getCode().trim().equals("")) {
/*     */       
/* 332 */       HelperC.afficherMessage("Information", "Completez le code");
/*     */     }
/* 334 */     else if (getDesignation().trim().equals("")) {
/*     */       
/* 336 */       HelperC.afficherMessage("Information", "Completez la d�signation");
/*     */     } else {
/*     */       
/* 339 */       Historique hist = new Historique();
/* 340 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 341 */       hist.setOperateur(this.operateur);
/* 342 */       if (getId() == 0) {
/*     */         
/* 344 */         hist.setOperation("Cr�ation de la banque " + getCode());
/*     */       } else {
/*     */         
/* 347 */         hist.setOperation("Modification de la banque " + getCode());
/*     */       } 
/* 349 */       hist.setTable(Tables.getTableName(Tables.TableName.banque));
/* 350 */       setHistorique(hist);
/* 351 */       if (FichierBaseDAO.getInstance().insertUpdateBanque(this)) {
/*     */         
/* 353 */         chargerBanque();
/* 354 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 355 */         clear(true);
/*     */       } else {
/*     */         
/* 358 */         HelperC.afficherMessage("Information", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 365 */     if (getId() != 0) {
/*     */       
/* 367 */       if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.banque), getId())) {
/*     */         
/* 369 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 370 */         clear(true);
/* 371 */         chargerBanque();
/*     */       } else {
/*     */         
/* 374 */         HelperC.afficherMessage("D�sole", "Echec de suppression");
/*     */       } 
/*     */     } else {
/*     */       
/* 378 */       HelperC.afficherDeleteMessage();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 384 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\BanqueB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */