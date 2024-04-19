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
/*     */ import classesPaie.ParametrageGeneralC;
/*     */ import classesPaie.SaisieAbsenceC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class SaisieAbsenceB
/*     */   extends SaisieAbsenceC
/*     */ {
/*     */   private static final long serialVersionUID = 5659574157386814683L;
/*  44 */   private List<SaisieAbsenceC> allSaisieAbsence = new ArrayList<SaisieAbsenceC>();
/*  45 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>(); 
			private SaisieAbsenceC selected;
			private EmployeC selection; 
			private String code; 
			private String nomEmploye; 
			private String codeEmployeRecherche;
/*  46 */   private HttpSession session = HelperC.getSession(); 
		    private String nomEmployeRecherche; 
		    private String prenomEmployeRecherche; 
		    private int idEmploye; 
		    private boolean disableMsg;
		    private OperateurC operateur; 
		    private ExerciceC exercice; 
		    private DroitC droit;
/*     */   Base userFonction;
/*     */   ParametrageGeneralC prm;
/*     */   
/*     */   @PostConstruct
/*     */   private void charger() {
/*  52 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  53 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  54 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  55 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  56 */     if (this.exercice == null || this.operateur == null) {
/*     */ 
/*     */       
/*     */       try {
/*  60 */         FacesContext context = FacesContext.getCurrentInstance();
/*  61 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/*  63 */       catch (IOException e) {
/*     */         
/*  65 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/*  69 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/*  70 */       if (this.userFonction != null)
/*     */       {
/*  72 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge);
/*     */       }
/*  74 */       setDateS(HelperC.changeDateFormate(getDate()));
/*  75 */       this.prm = FichierBaseDAO.getInstance().getParametrageGeneral();
/*  76 */       chargerAbscence();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  84 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  89 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  94 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  99 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 104 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 109 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 114 */     return this.codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 119 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 124 */     return this.nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 129 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 134 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 139 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public List<SaisieAbsenceC> getAllSaisieAbsence() {
/* 143 */     return this.allSaisieAbsence;
/*     */   }
/*     */   
/*     */   public void setAllSaisieAbsence(List<SaisieAbsenceC> allSaisieAbsence) {
/* 147 */     this.allSaisieAbsence = allSaisieAbsence;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/* 151 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/* 155 */     this.listEmploye = listEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public SaisieAbsenceC getSelected() {
/* 160 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(SaisieAbsenceC selected) {
/* 165 */     this.selected = selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getSelection() {
/* 170 */     return this.selection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/* 175 */     this.selection = selection;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 180 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/* 185 */     this.code = code;
/*     */   }
  
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   public int getIdEmploye() {
/* 195 */     return this.idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEmploye(int idEmploye) {
/* 200 */     this.idEmploye = idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmploye() {
/* 205 */     return this.nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmploye(String nomEmploye) {
/* 210 */     this.nomEmploye = nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDate() {
/* 215 */     if (getDateS().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 217 */       setDate(null);
/*     */     } else {
/*     */       
/* 220 */       setDate(HelperC.validerDate(getDateS()));
/* 221 */       if (getDate() == null) {
/*     */         
/* 223 */         setDateS("");
/* 224 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 227 */         setDateS(HelperC.convertDate(getDate()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerAbscence() {
/* 234 */     this.allSaisieAbsence = FactoryDAO.getInstance().getAllSaisieAbsence();
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 239 */     if (b)
/*     */     {
/* 241 */       setObservation("");
/*     */     }
              disableMsg=true;
/* 243 */     setEmploye(null);
/* 244 */     this.code = "";
/* 245 */     this.nomEmploye = "";
/* 246 */     setId(0);
/* 247 */     setDate(Calendar.getInstance().getTime());
/* 248 */     setDateS(HelperC.convertDate(Calendar.getInstance().getTime()));
/* 249 */     setDuree(0);
/* 250 */     setMoisPaie(0);
/* 251 */     setObservation("");
/* 252 */     setRetenuPaie(false);;
/* 253 */     this.selected = null;
/* 254 */     chargerAbscence();
/*     */   }
/*     */ 
/*     */   
/*     */   public void compareHour() {
/* 259 */     if (this.prm != null && getDuree() > this.prm.getNbreHeureJour()) {
/*     */       
/* 261 */       HelperC.afficherAttention("ATTENTION", "Vous ne depassez pas le nombre d'heures travaill� par jour !");
/* 262 */       setDuree(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void findByCode() {
/* 268 */     if (this.code != null && !this.code.equals("")) {
/*     */       
/* 270 */       this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
/* 271 */       if (this.selection != null) {
/*     */         
/* 273 */         setEmploye(this.selection);
/* 274 */         this.nomEmploye = this.selection.getNomPrenom();
/* 275 */         searchAbscence();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargerEmploye() {
/* 282 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 287 */     setIdExercice(this.exercice.getId());
/* 288 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 290 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 292 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 294 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 296 */     else if (getEmploye().getId() == 0 || getDate() == null || getDuree() == 0) {
/*     */       
/* 298 */       HelperC.afficherMessage("Information", "Veuillez remplir tous les champs obligatoires");
/*     */     } else {
/*     */       
/* 301 */       Historique hist = new Historique();
/* 302 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 303 */       hist.setOperateur(this.operateur);
/* 304 */       if (getId() == 0) {
/*     */         
/* 306 */         hist.setOperation("Cr�ation de la saisie d'absence ");
/*     */       } else {
/*     */         
/* 309 */         hist.setOperation("Modification de la saisie d'absence ");
/*     */       } 
/* 311 */       hist.setTable(Tables.getTableName(Tables.TableName.saisieabsence));
/* 312 */       setHistorique(hist);
/* 313 */      
/* 319 */       if (FactoryDAO.getInstance().insertUpdateSaisieAbsence(this)) {
/*     */         
/* 321 */         HelperC.afficherMessage("F�licitation", "Enregistrement avec succ�s");
/* 322 */         clear(true);
/*     */       } else {
/*     */         
/* 325 */         HelperC.afficherMessage("D�sol�!!", "Echec d'enregistrement");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete() {
/* 332 */     if (this.selected == null) {
/*     */       
/* 334 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 336 */     else if (this.selected != null && !this.droit.isSupprimer()) {
/*     */       
/* 338 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     } 
/* 340 */     if (this.selected != null) {
/*     */       
/* 342 */       FactoryDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.saisieabsence));
/* 343 */       HelperC.afficherMessage("F�licitation", "Suppression avec succ�s");
/* 344 */       clear(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 350 */     if (this.selected != null) {
/*     */       disableMsg=false;
/* 352 */       setId(this.selected.getId());
/* 353 */       if (this.selected.getDate() != null) {
/*     */         
/* 355 */         setDate(this.selected.getDate());
/* 356 */         setDateS(HelperC.convertDate(this.selected.getDate()));
/*     */       } else {
/*     */         
/* 359 */         setDate(Calendar.getInstance().getTime());
/* 360 */         setDateS(HelperC.convertDate(Calendar.getInstance().getTime()));
/*     */       } 
/* 362 */       setMoisPaie(this.selected.getMoisPaie());
/* 363 */       setDuree(this.selected.getDuree());
/* 364 */       setObservation(this.selected.getObservation());
/* 365 */       setAbsence(this.selected.getAbsence());
/* 366 */       setRetenuPaie(this.selected.isRetenuPaie());
/* 367 */       setMoisPaie(this.selected.getMoisPaie());
/* 368 */       this.selection = this.selected.getEmploye();
/* 369 */       setEmploye(this.selected.getEmploye());
/* 370 */       setObject1();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void searchAbscence() {
/* 376 */     if (getEmploye() != null && getDate() != null) {
/*     */       
/* 378 */       this.selected = FactoryDAO.getInstance().getSaisieAbsenceParEmployeEtDate(getEmploye().getId(), getDate());
/* 379 */       if (this.selected != null)
/*     */       {
/* 381 */         setObject();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject1() {
/* 388 */     if (this.selection != null) {
/*     */       
/* 390 */       setEmploye(this.selection);
/* 391 */       if (getEmploye() != null) {
/*     */         
/* 393 */         this.code = getEmploye().getCode();
/* 394 */         this.nomEmploye = this.selection.getNomPrenom();
/* 395 */         setEmploye(this.selection);
/*     */       } else {
/*     */         
/* 398 */         this.code = "";
/* 399 */         this.nomEmploye = "";
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowselected1(SelectEvent event) {
/* 406 */     this.selection = (EmployeC)event.getObject();
/* 407 */     setObject1();
/* 408 */     PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */   }
/*     */ 
/*     */ public void initialiser()
			{
				clear(true);
			}
/*     */   
/*     */   public void onRowselected() {
/* 414 */     setObject();
/*     */   }
/*     */ }

