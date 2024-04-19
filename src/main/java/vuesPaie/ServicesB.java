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
/*     */ import classesPaie.ServiceDetailC;
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
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ServicesB
/*     */   extends ServicesC
/*     */ {
/*  49 */   private HttpSession session = HelperC.getSession();
/*  50 */   private List<ServicesC> listeService = new ArrayList<ServicesC>();
/*  51 */   private ServicesC service = null; 
			private static final long serialVersionUID = -5406685991306351422L; 
			private List<SelectItem> listeDirection;
			private boolean disableMsg;
			private int idDirection;
/*     */   private ServiceDetailC detail;
/*     */   
/*     */  
/*     */   private OperateurC operateur; 
			private ExerciceC exercice; 
			private DroitC droit; 
			private DirectionC direction; 
			private Base directionUb; 
			Base userFonction; 
			HttpServletRequest request;
			
/*     */   
/*     */   
/*     */   public Base getDirectionUb() {
/*  63 */     return this.directionUb;
/*     */   }
/*     */   
/*     */   public void setDirectionUb(Base directionUb) {
/*  67 */     this.directionUb = directionUb;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListeDirection() {
/*  71 */     return this.listeDirection;
/*     */   }
/*     */   public void setListeDirection(List<SelectItem> listeDirection) {
/*  74 */     this.listeDirection = listeDirection;
/*     */   }
/*     */ 
/*     */   
/*     */   public DirectionC getDirection() {
/*  79 */     return this.direction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDirection(DirectionC direction) {
/*  84 */     this.direction = direction;
/*     */   }
/*     */   
/*     */ 
/*     */   
/*     */   public int getIdDirection() {
/*  96 */     return this.idDirection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdDirection(int idDirection) {
/* 101 */     this.idDirection = idDirection;
/*     */   }
/*     */ 
/*     */   
/*     */   public ServiceDetailC getDetail() {
/* 106 */     return this.detail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetail(ServiceDetailC detail) {
/* 111 */     this.detail = detail;
/*     */   }
/*     */ 
/*     */   
/*     */   public ServicesC getService() {
/* 116 */     return this.service;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setService(ServicesC service) {
/* 121 */     this.service = service;
/*     */   }
/*     */   
/*     */   public List<ServicesC> getListeService() {
/* 125 */     return this.listeService;
/*     */   }
/*     */   
/*     */   public void setListeService(List<ServicesC> listeService) {
/* 129 */     this.listeService = listeService;
/*     */   }
/*     */ 
/*     */ public boolean isDisableMsg() {
			return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/* 136 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 137 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 138 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 139 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 140 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 144 */         FacesContext context = FacesContext.getCurrentInstance();
/* 145 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 147 */       catch (IOException e) {
/*     */         
/* 149 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 153 */       this.request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
/* 154 */      
/*     */       
/* 156 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 157 */       if (this.userFonction != null)
/*     */       {
/* 159 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.structureAdministrative);
/*     */       }
/*     */       disableMsg=true;
/* 162 */       chargement();
/* 163 */       chargementDirection();
/* 164 */     
/*     */     } 
/*     */   }

/*     */   
/*     */  
/*     */   
/*     */   private void chargementDirection() {
/* 178 */     this.listeDirection = new ArrayList<SelectItem>();
/* 179 */     this.listeDirection.add(new SelectItem(Integer.valueOf(0), " "));
/*     */     
/* 181 */     for (DirectionC dir : FichierBaseDAO.getInstance().getListeDirection()) {
/* 182 */       this.listeDirection.add(new SelectItem(Integer.valueOf(dir.getId()), String.valueOf(dir.getCode()) + "||" + dir.getDesignation()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeOrgane(ValueChangeEvent event) {
/* 188 */     this.idDirection = ((Integer)event.getNewValue()).intValue();
/* 189 */     this.direction = FichierBaseDAO.getInstance().getDirection(this.idDirection);
/* 190 */     if (this.direction != null)
/* 191 */       setDirection(this.direction); 
/* 192 */     chargement();
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeSelection() {
/* 197 */     if (this.service != null) {
/*     */     disableMsg=false;
/* 199 */       setId(this.service.getId());
/* 200 */       setCode(this.service.getCode());
/* 201 */       setDesignation(this.service.getDesignation());
/* 202 */       if (this.service.getDirection() != null) {
/*     */         
/* 204 */         this.idDirection = this.service.getDirection().getId();
/* 205 */         this.direction = this.service.getDirection();
/* 206 */         setDirection(this.direction);
/* 207 */        
/*     */       } 
/* 209 */       
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDirection(ValueChangeEvent event) {
/* 220 */     this.idDirection = ((Integer)event.getNewValue()).intValue();
/* 221 */     this.direction = FichierBaseDAO.getInstance().getDirection(this.idDirection);
/* 222 */     setDirection(this.direction);
/* 223 */    
/* 224 */     setDirectionUb((Base)null);
/* 225 */     chargement();
/*     */   }
/*     */ 
/*     */   
/*     */   
/*     */   public void searchService() {
/* 238 */     if (getCode() != null && !getCode().equals(""))
/*     */     {
/* 240 */      
/*     */         
/* 242 */         this.service = FichierBaseDAO.getInstance().getService(getCode(), this.idDirection);
/* 243 */         if (this.service != null)
/* 244 */           affecter(); 
/*     */       } 
/*     */     }
/*     */   
/*     */ 
/*     */   
/*     */   private void chargement() {
/* 253 */     this.listeService.clear();
/* 254 */     this.listeService = FichierBaseDAO.getInstance().getListeServices(this.idDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 259 */     if (b) {
/*     */       
/* 261 */       setDirection((DirectionC)null);
/* 262 */       this.listeService.clear();
/* 263 */       this.idDirection = 0;
/*     */     } 
/*     */     
/* 266 */     setId(0);
/* 267 */     setCode("");
/* 268 */     setDesignation("");
/* 269 */     this.service = null;
			  disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 274 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 279 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 281 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 283 */     else if (getId() != 0 && !this.droit.isModifier()) {
/*     */       
/* 285 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 287 */    
/* 291 */     else if (getCode().trim().equals("")) {
/*     */       
/* 293 */       HelperC.afficherAttention("Information", "Pr�cisez la r�ference");
/*     */     
/*     */     }
/* 296 */     else if (getDesignation().trim().equals("")) {
/*     */       
/* 298 */       HelperC.afficherAttention("Information", "Pr�cisez la D�signation");
/*     */     
/*     */     }
				if(idDirection==0)
					 HelperC.afficherAttention("Information", "Pr�cisez la direction");
/* 301 */     else {
/*     */       
/* 306 */       Historique hist = new Historique();
/* 307 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 308 */       hist.setOperateur(this.operateur);
/* 309 */       if (getId() == 0) {
/*     */         
/* 311 */         hist.setOperation("Saisie Service code: " + getCode());
/*     */       } else {
/*     */         
/* 314 */         hist.setOperation("Modification Service code: " + getCode());
/*     */       } 
/* 316 */       hist.setTable(Tables.getTableName(Tables.TableName.services));
/* 317 */       setHistorique(hist);
/* 318 */       if (FichierBaseDAO.getInstance().insertUpdateServices(this)) {
/*     */         
/* 320 */         clear(false);
/* 321 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'Op�ration");
/* 322 */         chargement();
/*     */       } else {
/*     */         
/* 325 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 332 */     if (!this.droit.isSupprimer()) {
/*     */       
/* 334 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 336 */     else if (getId() == 0) {
/*     */       
/* 338 */       HelperC.afficherInformation("Information", "Pr�cisez l'�l�ment � supprimer");
/*     */     } else {
/*     */       
/* 341 */       Historique hist = new Historique();
/* 342 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 343 */       hist.setOperateur(this.operateur);
/* 344 */       hist.setOperation("Suppression Service code: " + getCode());
/* 345 */       hist.setTable(Tables.getTableName(Tables.TableName.services));
/* 346 */       hist.setIdLigne(getId());
/* 347 */       setHistorique(hist);
/* 348 */       if (FichierBaseDAO.getInstance().deleteServices(this)) {
/*     */         
/* 350 */         clear(false);
/* 351 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'Op�ration");
/* 352 */         chargement();
/*     */       } else {
/*     */         
/* 355 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void dbClickCode() {
/* 362 */     if (getDirection() == null) {
/*     */       
/* 364 */       HelperC.afficherInformation("Information", "Pr�cisez la Direction d'abord");
/*     */     } else {
/*     */       
/* 367 */       this.listeService = FichierBaseDAO.getInstance().getListeServices(0);
/* 368 */       PrimeFaces.current().executeScript("PF('recherche').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void affecter() {
				disableMsg=true;
/* 375 */     if (this.service != null) {
/*     */       disableMsg=false;
/* 377 */       setId(this.service.getId());
/* 378 */       setCode(this.service.getCode());
/* 379 */       setDesignation(this.service.getDesignation());
/* 380 */       PrimeFaces.current().executeScript("PF('recherche').hide();");
/*     */     } 
/*     */   }
/*     */ }


