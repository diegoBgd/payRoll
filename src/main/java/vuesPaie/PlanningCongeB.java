/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.PlanningCongeC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class PlanningCongeB
/*     */   extends PlanningCongeC
/*     */ {
/*     */   private static final long serialVersionUID = -2277577768915294339L;
/*     */   private String code;
/*     */   private String codeEmployeRecherche;
/*     */   private String nomEmployeRecherche;
/*     */   private String prenomEmployeRecherche;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*     */   private EmployeC employeSelected;
/*     */   private List<PlanningCongeC> listProgrammationConge;
/*     */   private PlanningCongeC planningSelected;
/*  43 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
/*     */   
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  47 */   private HttpSession session = HelperC.getSession();
/*     */   private DroitC droit;
/*     */   
/*     */   public PlanningCongeB() {
/*  51 */     this.listProgrammationConge = new ArrayList<PlanningCongeC>();
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/*  56 */     this.operateur = new OperateurC();
/*  57 */     this.exercice = new ExerciceC();
/*  58 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  59 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*     */     
/*  61 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  62 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/*  64 */     if (this.operateur == null || this.exercice == null) {
/*     */       try {
/*  66 */         FacesContext context = FacesContext.getCurrentInstance();
/*  67 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*  68 */       } catch (IOException e) {
/*     */         
/*  70 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*  73 */       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(
/*  74 */           this.operateur.getIdEmploye());
/*  75 */       if (userFonction != null)
/*  76 */         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), 
/*  77 */             Constante.Role.gestionConge); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/*  82 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  86 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/*  90 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  94 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/*  98 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 102 */     this.session = session;
/*     */   }
/*     */   
/*     */   public DroitC getDroit() {
/* 106 */     return this.droit;
/*     */   }
/*     */   
/*     */   public void setDroit(DroitC droit) {
/* 110 */     this.droit = droit;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 114 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 118 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 122 */     return this.codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 126 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 130 */     return this.nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 134 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 138 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 142 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public EmployeC getEmployeSelected() {
/* 146 */     return this.employeSelected;
/*     */   }
/*     */   
/*     */   public void setEmployeSelected(EmployeC employeSelected) {
/* 150 */     this.employeSelected = employeSelected;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/* 154 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/* 158 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public List<PlanningCongeC> getListProgrammationConge() {
/* 162 */     return this.listProgrammationConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListProgrammationConge(List<PlanningCongeC> listProgrammationConge) {
/* 167 */     this.listProgrammationConge = listProgrammationConge;
/*     */   }
/*     */   
/*     */   public PlanningCongeC getPlanningSelected() {
/* 171 */     return this.planningSelected;
/*     */   }
/*     */   
/*     */   public void setPlanningSelected(PlanningCongeC planningSelected) {
/* 175 */     this.planningSelected = planningSelected;
/*     */   }
/*     */   
/*     */   public String getDateDebutS() {
/* 179 */     return this.dateDebutS;
/*     */   }
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/* 183 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */   
/*     */   public String getDateFinS() {
/* 187 */     return this.dateFinS;
/*     */   }
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/* 191 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void findByCode() {
/* 197 */     this.employeSelected = FactoryDAO.getInstance().getEmploye(getCode(), false);
/*     */     
/* 199 */     if (this.employeSelected != null) {
/* 200 */       this.code = this.employeSelected.getCode();
/* 201 */       setEmploye(this.employeSelected);
/* 202 */       chargerConges();
/*     */     } else {
/* 204 */       clearInfo(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void chargerEmploye() {
/* 209 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, 
/* 210 */         this.nomEmployeRecherche,true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowEmployeSelected() {
/* 215 */     if (this.employeSelected != null) {
/* 216 */       setEmploye(this.employeSelected);
/* 217 */       setCode(this.employeSelected.getCode());
/* 218 */       chargerConges();
/*     */       
/* 220 */       PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDateDebut() {
/* 227 */     if (getDateDebutS().replace("/", "").replace("_", "").trim()
/* 228 */       .equals("")) {
/* 229 */       setDateDebut(null);
/*     */     } else {
/* 231 */       setDateDebut(HelperC.validerDate(getDateDebutS()));
/*     */       
/* 233 */       if (getDateDebut() == null) {
/* 234 */         setDateDebutS("");
/* 235 */         HelperC.afficherMessage("Information", "Date Invalide!");
/*     */       } else {
/* 237 */         setDateDebutS(HelperC.convertDate(getDateDebut()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateFin() {
/* 244 */     if (getDateFinS().replace("/", "").replace("_", "").trim()
/* 245 */       .equals("")) {
/* 246 */       setDateFin(null);
/*     */     } else {
/* 248 */       setDateFin(HelperC.validerDate(getDateFinS()));
/*     */       
/* 250 */       if (getDateFin() == null) {
/* 251 */         setDateFinS("");
/* 252 */         HelperC.afficherMessage("Information", "Date Invalide!");
/*     */       } else {
/* 254 */         setDateDebutS(HelperC.convertDate(getDateFin()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onRowPlanningSelected() {
/* 260 */     if (this.planningSelected != null) {
/* 261 */       setId(this.planningSelected.getId());
/*     */       
/* 263 */       if (this.planningSelected.getEmploye() != null) {
/* 264 */         setEmploye(this.planningSelected.getEmploye());
/*     */       } else {
/* 266 */         setEmploye(null);
/*     */       } 
/* 268 */       if (this.planningSelected.getDateDebut() != null) {
/* 269 */         setDateDebut(this.planningSelected.getDateDebut());
/* 270 */         setDateDebutS(HelperC.convertDate(this.planningSelected.getDateDebut()));
/*     */       } else {
/* 272 */         setDateDebut(null);
/* 273 */         setDateDebutS("");
/*     */       } 
/*     */       
/* 276 */       if (this.planningSelected.getDateFin() != null) {
/* 277 */         setDateFin(this.planningSelected.getDateFin());
/* 278 */         setDateFinS(HelperC.convertDate(this.planningSelected.getDateFin()));
/*     */       } else {
/* 280 */         setDateFin(null);
/* 281 */         setDateFinS("");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 288 */     if (getId() == 0 && !this.droit.isCreer()) {
/* 289 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/* 290 */     } else if (getId() > 0 && !this.droit.isModifier()) {
/* 291 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/* 292 */     } else if (getEmploye() == null) {
/* 293 */       HelperC.afficherMessage("Information", 
/* 294 */           "Veuillez d'abord pr�ciser l'employ� SVP!", 
/* 295 */           FacesMessage.SEVERITY_ERROR);
/*     */     } else {
/*     */       
/* 298 */       Historique hist = new Historique();
/* 299 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 300 */       hist.setOperateur(getOperateurCreation());
/* 301 */       if (getId() == 0) {
/* 302 */         hist.setOperation("Enregistrement du planning de cong� pour l'employ�:" + getEmploye().getMatricule());
/*     */       } else {
/* 304 */         hist.setOperation("Modification du planning de cong� pour l'employ�:" + getEmploye().getMatricule());
/* 305 */       }  hist.setTable(Tables.getTableName(Tables.TableName.planningConge));
/* 306 */       setHistoric(hist);
/*     */     } 
/*     */   }
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
/*     */   public void supprimer() {
/* 320 */     if (getId() == 0) {
/* 321 */       HelperC.afficherDeleteMessage();
/* 322 */     } else if (getId() > 0 && !this.droit.isSupprimer()) {
/* 323 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/* 324 */     }  if (getId() > 0) {
/* 325 */       if (FactoryDAO.getInstance().delete(getId(), 
/* 326 */           Tables.getTableName(Tables.TableName.planningConge))) {
/* 327 */         HelperC.afficherMessage("Information", "Succ� de l'Op�ration!", 
/* 328 */             FacesMessage.SEVERITY_INFO);
/* 329 */         chargerConges();
/* 330 */         clearInfo(false);
/*     */       } else {
/* 332 */         HelperC.afficherMessage("Information", "Echec de l'op�ration", 
/* 333 */             FacesMessage.SEVERITY_ERROR);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargerConges() {
/* 343 */     if (getListProgrammationConge().size() > 0) {
/* 344 */       for (PlanningCongeC p : getListProgrammationConge()) {
/*     */         
/* 346 */         if (p.getDateDebut() != null) {
/* 347 */           p.setDateDebS(HelperC.convertDate(p.getDateDebut()));
/*     */         } else {
/* 349 */           p.setDateDebS("");
/*     */         } 
/* 351 */         if (p.getDateFin() != null) {
/* 352 */           p.setDateFiS(HelperC.convertDate(p.getDateFin())); continue;
/*     */         } 
/* 354 */         p.setDateFiS("");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearInfo(boolean b) {
/* 361 */     if (b) {
/* 362 */       setCode("");
/* 363 */       this.employeSelected = null;
/* 364 */       setEmploye(null);
/* 365 */       getListProgrammationConge().clear();
/*     */     } 
/*     */     
/* 368 */     setId(0);
/* 369 */     setDateDebut(null);
/* 370 */     setDateDebutS("");
/* 371 */     setDateFin(null);
/* 372 */     setDateFinS("");
/*     */   }
/*     */   
/*     */   public void initialiser() {
/* 376 */     clearInfo(true);
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\PlanningCongeB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */