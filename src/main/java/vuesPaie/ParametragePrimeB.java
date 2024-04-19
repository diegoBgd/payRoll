/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.CategoriePersonnelC;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.GradePersonnelC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametragePrimeC;
/*     */ import classesPaie.ParametragePrimeDetailC;
/*     */ import classesPaie.PrimeIndemniteC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametragePrimeB
/*     */   extends ParametragePrimeC {
/*     */   private static final long serialVersionUID = 1759124516649373735L;
/*     */   private int idPrm;
/*     */   private int idGrade;
/*     */   private int idCategorie;
/*     */   private int idFonction;
/*     */   private int idPersonnel;
/*     */   private PrimeIndemniteC prime;
/*     */   private GradePersonnelC grade;
/*     */   private CategoriePersonnelC categorie;
/*     */   private Base fonction;
/*     */   private Base personnel;
/*     */   private ParametragePrimeC selected;
/*     */   private List<SelectItem> listPrime;
/*     */   private List<SelectItem> listGrade;
/*     */   private List<SelectItem> listCategorie;
/*     */   private List<SelectItem> listFonction;
/*     */   private List<SelectItem> listPersonnel;
/*     */   private List<ParametragePrimeC> listParametre;
/*     */   private List<ParametragePrimeDetailC> listElementPrm;
/*     */   private ParametragePrimeDetailC detail;
/*     */   private boolean prmSelected,disableMsg;
/*     */   private boolean disableDetail;
/*     */   ExerciceC exercice;
/*     */   DroitC droit;
/*  60 */   HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   OperateurC operateur;
/*     */   
/*     */   List<ParametragePrimeC> listParmAdded;
/*     */   
/*     */   int priority;
/*     */   
/*     */   public boolean isPrmSelected() {
/*  71 */     return this.prmSelected;
/*     */   }
/*     */   
/*     */   public void setPrmSelected(boolean prmSelected) {
/*  75 */     this.prmSelected = prmSelected;
/*     */   }
/*     */   
/*     */   public int getIdPrm() {
/*  79 */     return this.idPrm;
/*     */   }
/*     */   
/*     */   public void setIdPrm(int idPrm) {
/*  83 */     this.idPrm = idPrm;
/*     */   }
/*     */   
/*     */   public int getIdGrade() {
/*  87 */     return this.idGrade;
/*     */   }
/*     */   
/*     */   public void setIdGrade(int idGrade) {
/*  91 */     this.idGrade = idGrade;
/*     */   }
/*     */   
/*     */   public int getIdCategorie() {
/*  95 */     return this.idCategorie;
/*     */   }
/*     */   
/*     */   public void setIdCategorie(int idCategorie) {
/*  99 */     this.idCategorie = idCategorie;
/*     */   }
/*     */   
/*     */   public int getIdFonction() {
/* 103 */     return this.idFonction;
/*     */   }
/*     */   
/*     */   public void setIdFonction(int idFonction) {
/* 107 */     this.idFonction = idFonction;
/*     */   }
/*     */   
/*     */   public int getIdPersonnel() {
/* 111 */     return this.idPersonnel;
/*     */   }
/*     */   
/*     */   public void setIdPersonnel(int idPersonnel) {
/* 115 */     this.idPersonnel = idPersonnel;
/*     */   }
/*     */   
/*     */   public PrimeIndemniteC getPrime() {
/* 119 */     return this.prime;
/*     */   }
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/* 123 */     this.prime = prime;
/*     */   }
/*     */   
/*     */   public GradePersonnelC getGrade() {
/* 127 */     return this.grade;
/*     */   }
/*     */   
/*     */   public void setGrade(GradePersonnelC grade) {
/* 131 */     this.grade = grade;
/*     */   }
/*     */   
/*     */   public CategoriePersonnelC getCategorie() {
/* 135 */     return this.categorie;
/*     */   }
/*     */   
/*     */   public void setCategorie(CategoriePersonnelC categorie) {
/* 139 */     this.categorie = categorie;
/*     */   }
/*     */   
/*     */   public Base getFonction() {
/* 143 */     return this.fonction;
/*     */   }
/*     */   
/*     */   public void setFonction(Base fonction) {
/* 147 */     this.fonction = fonction;
/*     */   }
/*     */   
/*     */   public Base getPersonnel() {
/* 151 */     return this.personnel;
/*     */   }
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/* 155 */     this.personnel = personnel;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListPrime() {
/* 159 */     return this.listPrime;
/*     */   }
/*     */   
/*     */   public void setListPrime(List<SelectItem> listPrime) {
/* 163 */     this.listPrime = listPrime;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListGrade() {
/* 167 */     return this.listGrade;
/*     */   }
/*     */   
/*     */   public void setListGrade(List<SelectItem> listGrade) {
/* 171 */     this.listGrade = listGrade;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListCategorie() {
/* 175 */     return this.listCategorie;
/*     */   }
/*     */   
/*     */   public void setListCategorie(List<SelectItem> listCategorie) {
/* 179 */     this.listCategorie = listCategorie;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListFonction() {
/* 183 */     return this.listFonction;
/*     */   }
/*     */   
/*     */   public void setListFonction(List<SelectItem> listFonction) {
/* 187 */     this.listFonction = listFonction;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListPersonnel() {
/* 191 */     return this.listPersonnel;
/*     */   }
/*     */   
/*     */   public void setListPersonnel(List<SelectItem> listPersonnel) {
/* 195 */     this.listPersonnel = listPersonnel;
/*     */   }
/*     */   
/*     */   public List<ParametragePrimeC> getListParametre() {
/* 199 */     return this.listParametre;
/*     */   }
/*     */   
/*     */   public void setListParametre(List<ParametragePrimeC> listParametre) {
/* 203 */     this.listParametre = listParametre;
/*     */   }
/*     */   
/*     */   public ParametragePrimeC getSelected() {
/* 207 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(ParametragePrimeC selected) {
/* 211 */     this.selected = selected;
/*     */   }
/*     */   public List<ParametragePrimeDetailC> getListElementPrm() {
/* 214 */     return this.listElementPrm;
/*     */   }
/*     */   public void setListElementPrm(List<ParametragePrimeDetailC> listElementPrm) {
/* 217 */     this.listElementPrm = listElementPrm;
/*     */   }
/*     */   
/*     */   public ParametragePrimeDetailC getDetail() {
/* 221 */     return this.detail;
/*     */   }
/*     */   
/*     */   public void setDetail(ParametragePrimeDetailC detail) {
/* 225 */     this.detail = detail;
/*     */   }
/*     */   
/*     */   public boolean isDisableDetail() {
/* 229 */     return this.disableDetail;
/*     */   }
/*     */   
/*     */   public void setDisableDetail(boolean disableDetail) {
/* 233 */     this.disableDetail = disableDetail;
/*     */   }
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/* 239 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 240 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 241 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 242 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 243 */     if (this.exercice == null || this.operateur == null) {
/*     */       
/*     */       try {
/* 246 */         FacesContext context = FacesContext.getCurrentInstance();
/* 247 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 248 */       } catch (IOException e) {
/*     */         
/* 250 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/* 254 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 255 */       if (this.userFonction != null)
/* 256 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.elementPaie); 
/* 257 */       this.disableDetail = true;
/* 258 */       this.listCategorie = new ArrayList<SelectItem>();
/* 259 */       this.listFonction = new ArrayList<SelectItem>();
/* 260 */       this.listGrade = new ArrayList<SelectItem>();
/* 261 */       this.listPersonnel = new ArrayList<SelectItem>();
/* 262 */       this.listPrime = new ArrayList<SelectItem>();
/* 263 */       this.listElementPrm = new ArrayList<ParametragePrimeDetailC>();
/* 264 */       this.listParmAdded = new ArrayList<ParametragePrimeC>();
/*     */       
/* 266 */       chargementPersonnel();
/* 267 */       chargementFonction();
/* 268 */       chargementPrime();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargementParametre() {
/* 274 */     this.listParametre = FichierBaseDAO.getInstance().getAllParametragePrime(0, 0, 0, 0);
/* 275 */     for (ParametragePrimeC parm : this.listParametre) {
/* 276 */       if (parm.getIdCategorie() > 0)
/* 277 */         parm.setLibelleCategorie(FichierBaseDAO.getInstance().getCategoriePersonnel(parm.getIdCategorie()).getDesignation()); 
/* 278 */       if (parm.getIdFonction() > 0)
/* 279 */         parm.setLibelleFonction(FichierBaseDAO.getInstance().getBaseById(parm.getIdFonction(), Tables.getTableName(Tables.TableName.fonction)).getDesignation()); 
/* 280 */       if (parm.getIdGrade() > 0)
/* 281 */         parm.setLibelleGrade(FichierBaseDAO.getInstance().getGradePersonnel(parm.getIdGrade()).getDesignation()); 
/* 282 */       if (parm.getIdPersonnel() > 0)
/* 283 */         parm.setLibellePersonnel(FichierBaseDAO.getInstance().getBaseById(parm.getIdPersonnel(), Tables.getTableName(Tables.TableName.personnel)).getDesignation()); 
/* 284 */       if (parm.getIdPrime() > 0) {
/* 285 */         parm.setLibellePrime(FichierBaseDAO.getInstance().getPrimeIndemnite(parm.getIdPrime()).getDesignation());
/*     */       }
/* 287 */       parm.setListDetail(FichierBaseDAO.getInstance().getAllParametragePrimeDetail(parm.getId()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 292 */     this.selected = (ParametragePrimeC)event.getObject();
/* 293 */     if (this.selected != null) {
/*     */       disableMsg=true;
/* 295 */       parameterValues();
/* 296 */       PrimeFaces.current().executeScript("PF('dlgPrm').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void parameterValues() {
/* 303 */     setId(this.selected.getId());
/* 304 */     this.idPersonnel = this.selected.getIdPersonnel();
/* 305 */     this.idCategorie = this.selected.getIdCategorie();
/* 306 */     this.idGrade = this.selected.getIdGrade();
/*     */     
/* 308 */     this.idFonction = this.selected.getIdFonction();
/* 309 */     this.idPrm = this.selected.getIdPrime();
/* 310 */     this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrm);
/*     */     
/* 312 */     chargementCategorie();
/* 313 */     chargementGrade();
/*     */     disableMsg=false;
/* 315 */     this.listParmAdded = FichierBaseDAO.getInstance().getDistictParametragePrime(this.idPersonnel, this.idCategorie, this.idGrade, this.idFonction);
/* 316 */     if (this.listParmAdded.size() > 0) {
/* 317 */       primeAjoute();
/*     */     } else {
/* 319 */       chargerPrime();
/* 320 */     }  setTypeBase(this.selected.getTypeBase());
/* 321 */     setTaux(this.selected.getTaux());
/* 322 */     setForfait(this.selected.getForfait());
/* 323 */     setPlafond(this.selected.getPlafond());
/* 324 */     setPlancher(this.selected.getPlancher());
/* 325 */     setCalculHeurSup(this.selected.isCalculHeurSup());
/* 326 */     if (this.selected.getTaux() > 0.0D) {
/* 327 */       this.disableDetail = false;
/*     */     } else {
/* 329 */       this.disableDetail = true;
/*     */     } 
/* 331 */     if (this.selected.getListDetail().size() > 0) {
/* 332 */       for (ParametragePrimeDetailC det : this.selected.getListDetail()) {
/* 333 */         for (ParametragePrimeDetailC dt : this.listElementPrm) {
/* 334 */           if (det.getCodeElement().equals(dt.getCodeElement())) {
/* 335 */             dt.setId(det.getId());
/* 336 */             dt.setAdded(true);
/* 337 */             dt.setTaux(det.getTaux());
/* 338 */             dt.setForfait(det.getForfait());
/* 339 */             dt.setPlafon(det.getPlafon());
/* 340 */             dt.setPlancher(det.getPlancher());
/* 341 */             dt.setDisable(false);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void changePersonnel(ValueChangeEvent event) {
/* 349 */     if (event != null && event.getNewValue() != null) {
/* 350 */       this.idPersonnel = ((Integer)event.getNewValue()).intValue();
/* 351 */       chargementCategorie();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeFonction(ValueChangeEvent event) {
/* 357 */     if (event != null && event.getNewValue() != null) {
/* 358 */       this.idFonction = ((Integer)event.getNewValue()).intValue();
/*     */     }
/*     */   }
/*     */   
/*     */   public void changeCategorie(ValueChangeEvent event) {
/* 363 */     if (event != null && event.getNewValue() != null) {
/*     */       
/* 365 */       this.idCategorie = ((Integer)event.getNewValue()).intValue();
/* 366 */       chargementGrade();
/*     */     } 
/*     */   }
/*     */   public void changeGrade(ValueChangeEvent event) {
/* 370 */     if (event != null && event.getNewValue() != null)
/* 371 */       this.idGrade = ((Integer)event.getNewValue()).intValue(); 
/*     */   }
/*     */   
/*     */   public void changePrime(ValueChangeEvent event) {
/* 375 */     if (event != null && event.getNewValue() != null) {
/* 376 */       this.idPrm = ((Integer)event.getNewValue()).intValue();
/* 377 */       primeValues();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void primeValues() {
/* 383 */     this.listParmAdded.clear();
/* 384 */     this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrm);
/* 385 */     if (this.prime != null)
/*     */     {
/* 387 */       checkElement();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkElement() {
				disableMsg=true;
/* 394 */     this.selected = FichierBaseDAO.getInstance().getParametragePrime(this.idPersonnel, this.idCategorie, this.idGrade, this.idFonction, this.idPrm, true);
/* 395 */     if (this.selected != null) {
/* 396 */       parameterValues();
/*     */     } else {
/*     */       
/* 399 */       this.listParmAdded = FichierBaseDAO.getInstance().getDistictParametragePrime(this.idPersonnel, this.idCategorie, this.idGrade, this.idFonction);
/* 400 */       if (this.listParmAdded.size() > 0) {
/* 401 */         primeAjoute();
/*     */       } else {
/* 403 */         chargerPrime();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void chargementPersonnel() {
/* 408 */     this.listPersonnel.clear();
/* 409 */     this.listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));
/* 410 */     for (Base b : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel)))
/*     */     {
/* 412 */       this.listPersonnel.add(new SelectItem(Integer.valueOf(b.getId()), String.valueOf(b.getCode()) + "||" + b.getDesignation()));
/*     */     }
/*     */   }
/*     */   
/*     */   private void chargementFonction() {
/* 417 */     this.listFonction.add(new SelectItem(Integer.valueOf(0), ""));
/* 418 */     for (Base b : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction))) {
/* 419 */       this.listFonction.add(new SelectItem(Integer.valueOf(b.getId()), String.valueOf(b.getCode()) + "||" + b.getDesignation()));
/*     */     }
/*     */   }
/*     */   
/*     */   private void chargementCategorie() {
/* 424 */     this.listCategorie.clear();
/* 425 */     this.listCategorie.add(new SelectItem(Integer.valueOf(0), ""));
/* 426 */     for (CategoriePersonnelC cat : FichierBaseDAO.getInstance().getListCategoriePersonnelParIdPersonnel(this.idPersonnel))
/*     */     {
/* 428 */       this.listCategorie.add(new SelectItem(Integer.valueOf(cat.getId()), String.valueOf(cat.getCode()) + "||" + cat.getDesignation()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargementGrade() {
/* 434 */     this.listGrade.clear();
/* 435 */     this.listGrade.add(new SelectItem(Integer.valueOf(0), ""));
/* 436 */     for (GradePersonnelC grade : FichierBaseDAO.getInstance().getListGradeParCategoriePersonnel(this.idCategorie))
/*     */     {
/* 438 */       this.listGrade.add(new SelectItem(Integer.valueOf(grade.getId()), String.valueOf(grade.getCode()) + "||" + grade.getDesignation()));
/*     */     }
/*     */   }
/*     */   
/*     */   private void chargementPrime() {
/* 443 */     this.listPrime.clear();
/* 444 */     this.listPrime.add(new SelectItem(Integer.valueOf(0), ""));
/* 445 */     for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemnite())
/*     */     {
/* 447 */       this.listPrime.add(new SelectItem(Integer.valueOf(prm.getId()), String.valueOf(prm.getCode()) + "||" + prm.getDesignation()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerPrime() {
/* 453 */     this.listElementPrm.clear();
/* 454 */     if (this.prime != null) {
/*     */       
/* 456 */      
/*     */         for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemnite())
					{
/* 459 */         if (this.prime.getId() != prm.getId()) {
/* 460 */           if (this.detail == null)
/* 461 */             this.detail = new ParametragePrimeDetailC(); 
/* 462 */           this.detail.setCodeElement(prm.getCode());
/* 463 */           this.detail.setLibelle(prm.getDesignation());
/* 464 */           this.listElementPrm.add(this.detail);
/* 465 */           this.detail = null;
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   private void primeAjoute() {
/* 472 */     this.listElementPrm.clear();
/* 473 */     PrimeIndemniteC pm = null;
/*     */     
/* 475 */     for (ParametragePrimeC prm : this.listParmAdded) {
/*     */       
/* 477 */       pm = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
/* 478 */       if (pm != null)
/*     */       {
/*     */ 
/*     */         
/* 482 */         if (this.prime.getId() != pm.getId()) {
/* 483 */           if (this.detail == null)
/* 484 */             this.detail = new ParametragePrimeDetailC(); 
/* 485 */           this.detail.setCodeElement(pm.getCode());
/* 486 */           this.detail.setLibelle(pm.getDesignation());
/* 487 */           this.listElementPrm.add(this.detail);
/* 488 */           this.detail = null;
/*     */         }  
				} 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeTaux() {
/* 494 */     if (getTaux() > 0.0D) {
/* 495 */       setForfait(0.0D);
/* 496 */       this.disableDetail = false;
/*     */     } else {
/*     */       
/* 499 */       this.disableDetail = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeForfait() {
/* 504 */     if (getForfait() > 0.0D) {
/* 505 */       setTaux(0.0D);
/* 506 */       setPlafond(0.0D);
/* 507 */       setPlancher(0.0D);
/* 508 */       this.disableDetail = true;
/*     */     } else {
/*     */       
/* 511 */       this.disableDetail = false;
/*     */     } 
/*     */   }
/*     */   private int addPriority(ParametragePrimeDetailC det) {
/* 515 */     this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(det.getCodeElement());
/* 516 */     this.priority = FichierBaseDAO.getInstance().getPrioritePrime(this.idPersonnel, this.idCategorie, this.idGrade, this.idFonction, this.prime.getId());
/*     */     
/* 518 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void affecterDetail() {
/* 522 */     for (ParametragePrimeDetailC det : this.listElementPrm) {
/* 523 */       if (det.isAdded()) {
/*     */         
/* 525 */         getListDetail().add(det);
/*     */         
/*     */         continue;
/*     */       } 
/* 529 */       if (det.getId() > 0) {
/* 530 */         getListDeleted().add(det);
/*     */       }
/*     */     } 
/*     */     
/* 534 */     PrimeFaces.current().executeScript("PF('dlgPrmElt').hide();");
/*     */   }
/*     */   
/*     */   public void enregistrer() {
/* 538 */     if (getId() == 0 && !this.droit.isCreer()) {
/* 539 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 544 */     else if (this.idPrm == 0) {
/* 545 */       HelperC.afficherAttention("ATTENTION", "Il faut pr�ciser la prime ou l'indemnit�");
/*     */     } else {
/*     */       
/* 548 */       if (getListDetail().size() > 0) {
/*     */         
/* 550 */         for (ParametragePrimeDetailC det : getListDetail()) {
/* 551 */           this.priority = addPriority(det);
/* 552 */           this.priority++;
/*     */           
/* 554 */           if (this.priority > getPriorite()) {
/* 555 */             setPriorite(this.priority);
/*     */           }
/*     */         } 
/*     */       } else {
/* 559 */         setPriorite(1);
/*     */       } 
/*     */       
/* 562 */       Historique hist = new Historique();
/* 563 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 564 */       hist.setOperateur(this.operateur);
/* 565 */       if (getId() == 0) {
/* 566 */         hist.setOperation("Cr�ation du param�tre prime ");
/*     */       } else {
/* 568 */         hist.setOperation("Modification du param�tre prime ");
/* 569 */       }  setHist(hist);
/* 570 */       setIdPrime(this.idPrm);
/* 571 */       setIdGrade(this.idGrade);
/* 572 */       setIdCategorie(this.idCategorie);
/* 573 */       setIdFonction(this.idFonction);
/* 574 */       setIdPersonnel(this.idPersonnel);
/*     */       
/* 576 */       if (FichierBaseDAO.getInstance().insertUpdateParametrePrime(this)) {
/* 577 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 578 */         initialiser();
/*     */       } else {
/*     */         
/* 581 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void supprimer() {
/* 587 */     if (getId() == 0) {
/*     */       
/* 589 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 591 */     else if (this.droit.isSupprimer()) {
/*     */       
/* 593 */       Historique hist = new Historique();
/* 594 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 595 */       hist.setOperateur(this.operateur);
/* 596 */       if (getId() == 0) {
/* 597 */         hist.setOperation("Suppression du param�tre prime ");
/*     */       }
/* 599 */       if (FichierBaseDAO.getInstance().deleteParametragePrime(this.selected)) {
/*     */ 
/*     */         
/* 602 */         HelperC.afficherMessage("Information", "Succes de l'op�ration");
/* 603 */         initialiser();
/*     */       } else {
/*     */         
/* 606 */         HelperC.afficherMessage("D�sol�", "Echec de suppression ");
/*     */       } 
/*     */     } else {
/*     */       
/* 610 */       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 616 */     setId(0);
/* 617 */     this.idCategorie = 0;
/* 618 */     this.idFonction = 0;
/* 619 */     this.idGrade = 0;
/* 620 */     this.idPersonnel = 0;
/* 621 */     this.idPrm = 0;
/* 622 */     setTypeBase(0);
/* 623 */     setPlafond(0.0D);
/* 624 */     setPlancher(0.0D);
/* 625 */     setForfait(0.0D);
/* 626 */     setTaux(0.0D);
/* 627 */     setCalculHeurSup(false);
/* 628 */     this.listElementPrm.clear();
/* 629 */     chargerPrime();
/*     */   }
/*     */ }

