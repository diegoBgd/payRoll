/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import classesPaie.TypeCreditC;
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeCreditB
/*     */   extends TypeCreditC
/*     */ {
/*     */   private static final long serialVersionUID = 7663214010747562208L;
/*     */   private String tauxMinimumS;
/*     */   private String tauxMaximumS;
/*     */   private TypeCreditC typeCredit;
/*     */   private List<TypeCreditC> typeCredits;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  39 */   private HttpSession session = HelperC.getSession();
/*     */   private DroitC droit;
/*     */   Base userFonction;
/*     */   
/*     */   public TypeCreditC getTypeCredit() {
/*  44 */     return this.typeCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeCredit(TypeCreditC typeCredit) {
/*  49 */     this.typeCredit = typeCredit;
/*     */   }
/*     */   
/*     */   public List<TypeCreditC> getTypeCredits() {
/*  53 */     return this.typeCredits;
/*     */   }
/*     */   
/*     */   public void setTypeCredits(List<TypeCreditC> typeCredits) {
/*  57 */     this.typeCredits = typeCredits;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxMinimumS() {
/*  62 */     return this.tauxMinimumS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMinimumS(String tauxMinimumS) {
/*  67 */     this.tauxMinimumS = tauxMinimumS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxMaximumS() {
/*  72 */     return this.tauxMaximumS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMaximumS(String tauxMaximumS) {
/*  77 */     this.tauxMaximumS = tauxMaximumS;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  82 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  87 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  92 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  97 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 102 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 107 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public DroitC getDroit() {
/* 112 */     return this.droit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDroit(DroitC droit) {
/* 117 */     this.droit = droit;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/* 123 */     this.operateur = new OperateurC();
/* 124 */     this.exercice = new ExerciceC();
/* 125 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 126 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 127 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 128 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 129 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 133 */         FacesContext context = FacesContext.getCurrentInstance();
/* 134 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 136 */       catch (IOException e) {
/*     */         
/* 138 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 142 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 143 */       if (this.userFonction != null)
/*     */       {
/* 145 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.creditAvance);
/*     */       }
/* 147 */       this.typeCredits = FichierBaseDAO.getInstance().getAllTypeCredit();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeTauxMinimum() {
/*     */     try {
/* 154 */       setTauxMinimum(Double.valueOf(this.tauxMinimumS.replace("-", "")
/* 155 */             .replace(" ", "").replace(",", ".").trim()).doubleValue());
/* 156 */     } catch (Exception e) {
/* 157 */       setTauxMinimum(0.0D);
/*     */     } finally {
/*     */       
/* 160 */       this.tauxMinimumS = HelperC.TraitementMontant.getMontantFormate(
/* 161 */           getTauxMinimum(), 0);
/* 162 */       setTauxMinimum(Double.valueOf(this.tauxMinimumS.replace(" ", "")
/* 163 */             .replace(",", ".").trim()).doubleValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeTauxMaximun() {
/*     */     try {
/* 169 */       setTauxMaximum(Double.valueOf(this.tauxMaximumS.replace("-", "")
/* 170 */             .replace(" ", "").replace(",", ".").trim()).doubleValue());
/* 171 */     } catch (Exception e) {
/* 172 */       setTauxMaximum(0.0D);
/*     */     } finally {
/*     */       
/* 175 */       this.tauxMaximumS = HelperC.TraitementMontant.getMontantFormate(
/* 176 */           getTauxMaximum(), 0);
/* 177 */       setTauxMaximum(Double.valueOf(this.tauxMaximumS.replace(" ", "")
/* 178 */             .replace(",", ".").trim()).doubleValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObjet() {
/* 184 */     if (this.typeCredit != null) {
/*     */       
/* 186 */       setId(this.typeCredit.getId());
/* 187 */       setCode(this.typeCredit.getCode());
/* 188 */       setLibelle(this.typeCredit.getLibelle());
/* 189 */       setTerme(this.typeCredit.getTerme());
/* 190 */       setTauxMinimum(this.typeCredit.getTauxMinimum());
/* 191 */       this.tauxMinimumS = HelperC.TraitementMontant.getMontantFormate(getTauxMinimum(), 0);
/* 192 */       setTauxMaximum(this.typeCredit.getTauxMaximum());
/* 193 */       this.tauxMaximumS = HelperC.TraitementMontant.getMontantFormate(getTauxMaximum(), 0);
/* 194 */       setCentraliserEcritureEnComptabilite(this.typeCredit.isCentraliserEcritureEnComptabilite());
/* 195 */       setAffecterDeuxiemeCompteCredit(this.typeCredit.isAffecterDeuxiemeCompteCredit());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 201 */     if (b)
/*     */     {
/* 203 */       setCode("");
/*     */     }
/* 205 */     setId(0);
/* 206 */     setLibelle("");
/* 207 */     setTerme("");
/* 208 */     setTauxMinimum(0.0D);
/* 209 */     setTauxMaximum(0.0D);
/* 210 */     this.tauxMinimumS = "";
/* 211 */     this.tauxMaximumS = "";
/* 212 */     setCentraliserEcritureEnComptabilite(false);
/* 213 */     setAffecterDeuxiemeCompteCredit(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 218 */     if (getCode().trim().equals("")) {
/*     */       
/* 220 */       clear(false);
/*     */     } else {
/*     */       
/* 223 */       this.typeCredit = FichierBaseDAO.getInstance().getTypeCredit(getCode());
/*     */     } 
/* 225 */     if (this.typeCredit == null) {
/*     */       
/* 227 */       clear(false);
/*     */     } else {
/*     */       
/* 230 */       setObjet();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelect(SelectEvent event) {
/* 236 */     this.typeCredit = (TypeCreditC)event.getObject();
/* 237 */     if (this.typeCredit != null)
/*     */     {
/* 239 */       setObjet();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 245 */     if (getCode().trim().equals("") || getLibelle().trim().equals("") || getTerme().trim().equals("")) {
/*     */       
/* 247 */       HelperC.afficherMessage("Information", "Completez tous les champs");
/*     */     }
/* 249 */     else if (FichierBaseDAO.getInstance().getTypeCreditNotCurrent(getCode(), getId()) != null) {
/*     */       
/* 251 */       HelperC.afficherMessage("Information", "Ce code existe d�j�");
/*     */     }
/* 253 */     else if (FichierBaseDAO.getInstance().getTypeCreditByLibelle(getLibelle(), getId()) != null) {
/*     */       
/* 255 */       HelperC.afficherMessage("Information", "Ce libell� existe d�j�");
/*     */     } else {
/*     */       
/* 258 */       if (getId() == 0 && !this.droit.isCreer()) {
/*     */         
/* 260 */         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de cr�er ");
/*     */         return;
/*     */       } 
/* 263 */       if (getId() > 0 && !this.droit.isModifier()) {
/*     */         
/* 265 */         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier ");
/*     */         return;
/*     */       } 
/* 268 */       Historique hist = new Historique();
/* 269 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 270 */       hist.setOperateur(this.operateur);
/* 271 */       if (getId() == 0) {
/*     */         
/* 273 */         hist.setOperation("Cr�ation du type cr�dit " + getCode());
/*     */       } else {
/*     */         
/* 276 */         hist.setOperation("Modification du type cr�dit  " + getCode());
/*     */       } 
/* 278 */       hist.setTable(Tables.getTableName(Tables.TableName.typeCredit));
/* 279 */       setHistorique(hist);
/* 280 */       if (FichierBaseDAO.getInstance().insertUpdateTypeCredit(this)) {
/*     */         
/* 282 */         HelperC.afficherMessage("Information", "Suc�s de l'op�ration");
/* 283 */         clear(true);
/* 284 */         this.typeCredits = FichierBaseDAO.getInstance().getAllTypeCredit();
/*     */       } else {
/*     */         
/* 287 */         HelperC.afficherMessage("Information", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 294 */     if (getId() == 0) {
/*     */       
/*     */     } else {
/*     */       
/* 299 */       if (!this.droit.isSupprimer()) {
/*     */         
/* 301 */         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
/*     */         return;
/*     */       } 
/* 304 */       if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeCredit), getId())) {
/*     */         
/* 306 */         clear(true);
/* 307 */         HelperC.afficherMessage("Information", "Suc�s de l'op�ration");
/* 308 */         this.typeCredits = FichierBaseDAO.getInstance().getAllTypeCredit();
/*     */       } else {
/*     */         
/* 311 */         HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 318 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fermer() {
/*     */     try {
/* 325 */       FacesContext.getCurrentInstance().getExternalContext().redirect("/asystPaie/masterPage.jsf");
/*     */     }
/* 327 */     catch (IOException e) {
/*     */       
/* 329 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\TypeCreditB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */