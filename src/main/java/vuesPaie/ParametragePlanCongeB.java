/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DirectionC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametragePlanCongeC;
/*     */ import classesPaie.ServicesC;
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
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametragePlanCongeB
/*     */   extends ParametragePlanCongeC
/*     */ {
/*     */   private static final long serialVersionUID = -3319971197056022743L;
/*     */  
/*     */   private int idDirection;
/*     */   private int idService;
/*     */   
/*     */   private ParametragePlanCongeC selected;
/*  43 */   private List<ParametragePlanCongeC> listParametrage = new ArrayList<ParametragePlanCongeC>();
/*  44 */  
/*  45 */   private List<SelectItem> listeDirection = new ArrayList<SelectItem>();
/*  46 */   private List<SelectItem> listeService = new ArrayList<SelectItem>();
/*  47 */   
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  55 */   private HttpSession session = HelperC.getSession();
/*     */ 
/*     */   
/*     */   private DroitC droit;
  
/*     */   public int getIdDirection() {
/*  71 */     return this.idDirection;
/*     */   }
/*     */   
/*     */   public void setIdDirection(int idDirection) {
/*  75 */     this.idDirection = idDirection;
/*     */   }
/*     */   
/*     */   public int getIdService() {
/*  79 */     return this.idService;
/*     */   }
/*     */   
/*     */   public void setIdService(int idService) {
/*  83 */     this.idService = idService;
/*     */   }
/*     */   

/*     */   public ParametragePlanCongeC getSelected() {
/* 127 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(ParametragePlanCongeC selected) {
/* 131 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public List<ParametragePlanCongeC> getListParametrage() {
/* 135 */     return this.listParametrage;
/*     */   }
/*     */   
/*     */   public void setListParametrage(List<ParametragePlanCongeC> listParametrage) {
/* 139 */     this.listParametrage = listParametrage;
/*     */   }
/*     */   
/*     */   
/*     */   public List<SelectItem> getListeDirection() {
/* 152 */     return this.listeDirection;
/*     */   }
/*     */   
/*     */   public void setListeDirection(List<SelectItem> listeDirection) {
/* 156 */     this.listeDirection = listeDirection;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListeService() {
/* 160 */     return this.listeService;
/*     */   }
/*     */   
/*     */   public void setListeService(List<SelectItem> listeService) {
/* 164 */     this.listeService = listeService;
/*     */   }
/*     */   
/*     */   
/*     */   public OperateurC getOperateur() {
/* 208 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 212 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/* 216 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 220 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/* 224 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 228 */     this.session = session;
/*     */   }
/*     */   
/*     */   public DroitC getDroit() {
/* 232 */     return this.droit;
/*     */   }
/*     */   
/*     */   public void setDroit(DroitC droit) {
/* 236 */     this.droit = droit;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/* 241 */     this.operateur = new OperateurC();
/* 242 */     this.exercice = new ExerciceC();
/* 243 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 244 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*     */     
/* 246 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 247 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/* 249 */     if (this.operateur == null || this.exercice == null) {
/*     */       try {
/* 251 */         FacesContext context = FacesContext.getCurrentInstance();
/* 252 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 253 */       } catch (IOException e) {
/*     */         
/* 255 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/* 258 */       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 259 */           this.operateur.getIdEmploye());
/* 260 */       if (userFonction != null)
/* 261 */         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), 
/* 262 */             Constante.Role.gestionConge); 

/* 263 */      
/* 273 */       this.listeDirection.add(new SelectItem(Integer.valueOf(0), " "));
/* 274 */     
				for (Base d : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.direction))) {
/* 305 */         this.listeDirection.add(new SelectItem(Integer.valueOf( d.getId()), String.valueOf( d.getCode()) + " - " +  d.getDesignation()));
/*     */       }
/* 275 */       chargerPlan();
/*     */     } 
/*     */   }
/*     */ 
/*     */  private void chargerPlan(){
			 listParametrage=FichierBaseDAO.getInstance().getListParametragePlanConge(exercice.getId());
			}
/*     */ 
/*     */   
/*     */  
/*     */   
/*     */   public void changeDirection(ValueChangeEvent event) {
/* 299 */     this.idDirection = ((Integer)event.getNewValue()).intValue();
/* 300 */     this.idService = 0;
/* 301 */     this.listeService.clear();
/* 302 */     setDirection(FichierBaseDAO.getInstance().getDirection(this.idDirection));
/* 303 */     if (getDirection() != null) {
	  			this.listeService.add(new SelectItem(Integer.valueOf(0), " "));
/* 304 */       for (ServicesC servi : FichierBaseDAO.getInstance().getListeServices(getDirection().getId())) {
/* 305 */         this.listeService.add(new SelectItem(Integer.valueOf(servi.getId()), String.valueOf(servi.getCode()) + " || " + servi.getDesignation()));
/*     */       }
/* 307 */       
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeService(ValueChangeEvent event) {
/* 312 */     this.idService = ((Integer)event.getNewValue()).intValue();
/* 313 */     setService(FichierBaseDAO.getInstance().getServices(this.idService));
/* 314 */     if (getService() != null) {
/* 315 */       setDirection(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setObject() {
/* 320 */     if (this.selected != null) {
/* 321 */       setId(this.selected.getId());
/* 322 */      
/*     */ 	   setNombreEmploye(selected.getNombreEmploye());
/*     */       
/* 334 */       setDirection(this.selected.getDirection());
/* 335 */       if (getDirection() != null) {
/* 336 */         this.idDirection = getDirection().getId();
/* 337 */         for (ServicesC servi : FichierBaseDAO.getInstance().getListeServices(this.idDirection)) {
/* 338 */           this.listeService.add(new SelectItem(Integer.valueOf(servi.getId()), String.valueOf(servi.getCode()) + " || " + servi.getDesignation()));
/*     */         }
/* 340 */          setService(this.selected.getService());
					this.idService = getService().getId();
/*     */         }
					
/*     */       } 
/*     */       
/* 354 */     
/* 355 */      
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear() {
/* 387 */     setId(0);
/* 388 */    
/* 389 */     
/* 390 */   
/* 391 */     this.idDirection = 0;
/* 392 */     this.listeService.clear();
/* 393 */     this.idService = 0;
/* 394 */     setDirection(null);
/* 395 */     setService(null);
/* 396 */     setNombreEmploye(0);
/* 397 */     
/* 413 */     this.selected = null;
/*     */   }
/*     */   
/*     */   public void initialiser() {
/* 417 */     clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 424 */     if (getId() == 0 && !this.droit.isCreer()) {
/* 425 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/* 426 */     } else if (getId() != 0 && !this.droit.isModifier()) {
/* 427 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/* 428 */     
/* 432 */     } else if (getNombreEmploye() == 0) {
/* 433 */       HelperC.afficherMessage("Information", "Le nombre d'employ� ne peut pas �tre z�ro");
/* 434 */     }  else {
/*     */       if (getService() == null) {
	/* 433 */       HelperC.afficherMessage("Information", "Il faut pr�ciser le service");
	/* 434 */     }  else {
/* 446 */       Historique hist = new Historique();
/* 447 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 448 */       hist.setOperateur(this.operateur);
/* 449 */       if (getId() == 0) {
/* 450 */         hist.setOperation("Saisie Parametrage cong� ");
/*     */       } else {
/* 452 */         hist.setOperation("Modification Parametrage cong� ");
/* 453 */       }  hist.setTable(Tables.getTableName(Tables.TableName.parametragePlanConge));
/* 454 */       setHistorique(hist);
/*     */        setIdExercice(exercice.getId());
/* 456 */       if (FichierBaseDAO.getInstance().insertUpdateParametragePlanConge(this)) {
/* 457 */         clear();
				  chargerPlan();
/* 458 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'Op�ration");
/*     */       } else {
/*     */         
/* 461 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Op�ration");
/*     */       } 
/*     */     }
/*     */   }
/*     */ }
/*     */   
/*     */   public void supprimer() {
/* 468 */     if (!this.droit.isSupprimer()) {
/* 469 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/* 470 */     } else if (getId() == 0) {
/* 471 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 474 */       Historique hist = new Historique();
/* 475 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 476 */       hist.setOperateur(this.operateur);
/* 477 */       hist.setOperation("Suppression Parametrage cong� ");
/* 478 */       hist.setTable(Tables.getTableName(Tables.TableName.parametragePlanConge));
/* 479 */       hist.setIdLigne(getId());
/* 480 */       setHistorique(hist);
/* 481 */       if (FichierBaseDAO.getInstance().deleteParametragePlanConge(this)) {
/* 482 */         clear();
			      chargerPlan();
/* 483 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'Op�ration");
/*     */       } else {
/*     */         
/* 486 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   

/*     */   public void onRowSelected(SelectEvent event) {
/* 501 */     this.selected = (ParametragePlanCongeC)event.getObject();
/* 502 */     if (this.selected != null)
/* 503 */       setObject(); 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\ParametragePlanCongeB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */