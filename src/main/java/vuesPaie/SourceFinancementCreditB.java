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
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class SourceFinancementCreditB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = -6016896800008532256L;
/*     */   private Base sourceFinancementCredit;
/*     */   private List<Base> sourceFinancementCredits;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  36 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   public Base getSourceFinancementCredit() {
/*  41 */     return this.sourceFinancementCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSourceFinancementCredit(Base sourceFinancementCredit) {
/*  46 */     this.sourceFinancementCredit = sourceFinancementCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Base> getSourceFinancementCredits() {
/*  51 */     return this.sourceFinancementCredits;
/*     */   }
/*     */   
/*     */   public void setSourceFinancementCredits(List<Base> sourceFinancementCredits) {
/*  55 */     this.sourceFinancementCredits = sourceFinancementCredits;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  60 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  65 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  70 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  75 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  80 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  85 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  91 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  92 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  93 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  94 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  95 */     if (this.exercice == null || this.operateur == null) {
/*     */ 
/*     */       
/*     */       try {
/*  99 */         FacesContext context = FacesContext.getCurrentInstance();
/* 100 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 102 */       catch (IOException e) {
/*     */         
/* 104 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 108 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 109 */       if (this.userFonction != null)
/*     */       {
/* 111 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.creditAvance);
/*     */       }
/* 113 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 119 */     this.sourceFinancementCredits = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.sourceFinancementCredit));
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 124 */     if (this.sourceFinancementCredit != null) {
/*     */       
/* 126 */       setId(this.sourceFinancementCredit.getId());
/* 127 */       setCode(this.sourceFinancementCredit.getCode());
/* 128 */       setDesignation(this.sourceFinancementCredit.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 134 */     if (b)
/*     */     {
/* 136 */       setCode("");
/*     */     }
/* 138 */     setId(0);
/* 139 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 144 */     if (getCode().trim().equals("")) {
/*     */       
/* 146 */       clear(true);
/*     */     } else {
/*     */       
/* 149 */       this.sourceFinancementCredit = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.sourceFinancementCredit));
/* 150 */       if (this.sourceFinancementCredit == null) {
/*     */         
/* 152 */         clear(false);
/*     */       } else {
/*     */         
/* 155 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 162 */     this.sourceFinancementCredit = (Base)event.getObject();
/* 163 */     if (this.sourceFinancementCredit != null)
/*     */     {
/* 165 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 171 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 173 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 175 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 177 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 179 */     else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */       
/* 181 */       HelperC.afficherMessage("Information", "Completez tous les champs");
/*     */     }
/* 183 */     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.sourceFinancementCredit)) != null) {
/*     */       
/* 185 */       HelperC.afficherMessage("Information", "Ce code de source de financement existe d�j� ");
/*     */     }
/* 187 */     else if (FichierBaseDAO.getInstance().getBases(getDesignation().replace("'", "\\'").trim(), getId(), Tables.getTableName(Tables.TableName.sourceFinancementCredit)) != null) {
/*     */       
/* 189 */       HelperC.afficherMessage("Information", "Cette d�signation de source de financement est d�j� saisi");
/*     */     } else {
/*     */       
/* 192 */       Historique hist = new Historique();
/* 193 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 194 */       hist.setOperateur(this.operateur);
/* 195 */       if (getId() == 0) {
/*     */         
/* 197 */         hist.setOperation("Cr�ation du source de financement " + getCode());
/*     */       } else {
/*     */         
/* 200 */         hist.setOperation("Modification du source de financement " + getCode());
/*     */       } 
/* 202 */       hist.setTable(Tables.getTableName(Tables.TableName.sourceFinancementCredit));
/* 203 */       setHistorique(hist);
/* 204 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.sourceFinancementCredit))) {
/*     */         
/* 206 */         clear(true);
/* 207 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 208 */         charger();
/*     */       } else {
/*     */         
/* 211 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 218 */     if (getId() == 0) {
/*     */       
/* 220 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 222 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.sourceFinancementCredit), getId())) {
/*     */       
/* 224 */       clear(true);
/* 225 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 226 */       charger();
/*     */     } else {
/*     */       
/* 229 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 235 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fermer() {
/*     */     try {
/* 242 */       FacesContext.getCurrentInstance().getExternalContext().redirect("/asystPaie/masterPage.jsf");
/*     */     }
/* 244 */     catch (IOException e) {
/*     */       
/* 246 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\SourceFinancementCreditB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */