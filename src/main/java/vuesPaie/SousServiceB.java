/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ServicesC;
/*     */ import classesPaie.SousServiceC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class SousServiceB
/*     */   extends SousServiceC
/*     */ {
/*     */   private static final long serialVersionUID = 86856177370856233L;
/*     */   private SousServiceC selected;
/*     */   private List<SelectItem> listService;
/*     */   private List<SousServiceC> listSousService;
/*     */   private int idService;
/*     */   OperateurC operateur;
/*     */   DroitC droit;
/*     */   ExerciceC exercice;
/*     */   HttpServletRequest request;
/*     */   Base userFonction;
/*     */   ServicesC service;
/*  50 */   HttpSession session = HelperC.getSession();
/*     */   
/*     */   public SousServiceC getSelected() {
/*  53 */     return this.selected;
/*     */   }
/*     */   public void setSelected(SousServiceC selected) {
/*  56 */     this.selected = selected;
/*     */   }
/*     */   public List<SelectItem> getListService() {
/*  59 */     return this.listService;
/*     */   }
/*     */   public void setListService(List<SelectItem> listService) {
/*  62 */     this.listService = listService;
/*     */   }
/*     */   public List<SousServiceC> getListSousService() {
/*  65 */     return this.listSousService;
/*     */   }
/*     */   public void setListSousService(List<SousServiceC> listSousService) {
/*  68 */     this.listSousService = listSousService;
/*     */   }
/*     */   public int getIdService() {
/*  71 */     return this.idService;
/*     */   }
/*     */   public void setIdService(int idService) {
/*  74 */     this.idService = idService;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/*  80 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  81 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  82 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  83 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  84 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/*  88 */         FacesContext context = FacesContext.getCurrentInstance();
/*  89 */         context.getExternalContext().redirect("/gestionPaie/login.xhtml");
/*     */       }
/*  91 */       catch (IOException e) {
/*     */         
/*  93 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/*  97 */       this.request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
/*  98 */       setTypeSubService(this.request.getParameter("type"));
/*     */       
/* 100 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 101 */       if (this.userFonction != null)
/*     */       {
/* 103 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.structureAdministrative);
/*     */       }
/* 105 */       chargementService();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargementService() {
/* 112 */     this.listService = new ArrayList<SelectItem>();
/* 113 */     this.listService.add(new SelectItem(Integer.valueOf(0), ""));
/* 114 */     for (ServicesC srv : FichierBaseDAO.getInstance().getListeServices(0))
/*     */     {
/* 116 */       this.listService.add(new SelectItem(Integer.valueOf(srv.getId()), String.valueOf(srv.getCode()) + "||" + srv.getDesignation()));
/*     */     }
/*     */   }
/*     */   
/*     */   private void chargement() {
/* 121 */     if (getService() != null)
/*     */     {
/* 123 */       this.listSousService = FichierBaseDAO.getInstance().getListSouServices(this.idService, getTypeSubService());
/*     */     }
/*     */   }
/*     */   
/*     */   public void changeService(ValueChangeEvent event) {
/* 128 */     this.idService = ((Integer)event.getNewValue()).intValue();
/* 129 */     this.service = FichierBaseDAO.getInstance().getServices(this.idService);
/* 130 */     setService(this.service);
/* 131 */     chargement();
/*     */   }
/*     */   
/*     */   public void takeSelection() {
/* 135 */     if (this.selected != null)
/*     */     {
/* 137 */       setObject();
/*     */     }
/*     */   }
/*     */   
/*     */   public void searchSousService() {
/* 142 */     if (!getCode().equals("")) {
/*     */       
/* 144 */       this.selected = FichierBaseDAO.getInstance().getSouServices(getCode());
/* 145 */       if (this.selected != null)
/*     */       {
/* 147 */         setObject(); } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setObject() {
/* 152 */     setId(this.selected.getId());
/* 153 */     setCode(this.selected.getCode());
/* 154 */     setDesignation(this.selected.getDesignation());
/* 155 */     setTypeSubService(this.selected.getTypeSubService());
/* 156 */     this.service = this.selected.getService();
/* 157 */     this.idService = this.service.getId();
/*     */   }
/*     */ 
/*     */   
/*     */   public void searchSouService() {
/* 162 */     if (getCode() != null && !getCode().equals(""))
/*     */     {
/* 164 */       if (this.idService > 0) {
/*     */         
/* 166 */         this.selected = FichierBaseDAO.getInstance().getSouServices(getCode(), this.idService);
/* 167 */         if (this.selected != null) {
/* 168 */           setObject();
/*     */         }
/*     */       } else {
/* 171 */         HelperC.afficherAttention("ATTENTION", "Il faut préciser le service");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 180 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 182 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
/*     */     }
/* 184 */     else if (getId() != 0 && !this.droit.isModifier()) {
/*     */       
/* 186 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 188 */     else if (getService() == null) {
/*     */       
/* 190 */       HelperC.afficherInformation("Information", "Précisez le service");
/*     */     }
/* 192 */     else if (getCode().trim().equals("")) {
/*     */       
/* 194 */       HelperC.afficherInformation("Information", "Précisez le code");
/*     */     
/*     */     }
/* 197 */     else if (getDesignation().trim().equals("")) {
/*     */       
/* 199 */       HelperC.afficherInformation("Information", "Précisez la Désignation");
/*     */     }
/*     */     else {
/*     */       
/* 203 */       Historique hist = new Historique();
/* 204 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 205 */       hist.setOperateur(this.operateur);
/* 206 */       if (getId() == 0) {
/*     */         
/* 208 */         hist.setOperation("Saisie Service code: " + getCode());
/*     */       } else {
/*     */         
/* 211 */         hist.setOperation("Modification Service code: " + getCode());
/*     */       } 
/* 213 */       hist.setTable(Tables.getTableName(Tables.TableName.services));
/* 214 */       setHistorique(hist);
/* 215 */       if (FichierBaseDAO.getInstance().insertUpdateSousServices(this)) {
/*     */ 
/*     */         
/* 218 */         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
/* 219 */         chargement();
/* 220 */         clear();
/*     */       } else {
/*     */         
/* 223 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 230 */     if (!this.droit.isSupprimer()) {
/*     */       
/* 232 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 234 */     else if (getId() == 0) {
/*     */       
/* 236 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 239 */       Historique hist = new Historique();
/* 240 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 241 */       hist.setOperateur(this.operateur);
/* 242 */       hist.setOperation("Suppression Service code: " + getCode());
/* 243 */       hist.setTable(Tables.getTableName(Tables.TableName.services));
/* 244 */       hist.setIdLigne(getId());
/* 245 */       setHistorique(hist);
/* 246 */       if (FichierBaseDAO.getInstance().deleteSousServices(this)) {
/*     */         
/* 248 */         clear();
/* 249 */         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
/* 250 */         chargement();
/*     */       } else {
/*     */         
/* 253 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clear() {
/* 261 */     setId(0);
/* 262 */     setCode("");
/* 263 */     setDesignation("");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 269 */     clear();
/* 270 */     this.service = null;
/* 271 */     this.idService = 0;
/* 272 */     this.listSousService.clear();
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\SousServiceB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */