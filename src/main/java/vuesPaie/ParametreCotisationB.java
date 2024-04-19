/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.BaremeIPRC;
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.CotisationC;
/*     */ import classesPaie.CotisationDetailC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametreCotisationC;
/*     */ import classesPaie.PrimeIndemniteC;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametreCotisationB
/*     */   extends ParametreCotisationC
/*     */ {
/*     */   private static final long serialVersionUID = -5163107367751544507L;
/*     */   private List<CotisationC> listCotisation;
/*     */   private List<ParametreCotisationC> listeParametre;
/*  42 */   private ParametreCotisationC selected = null;
/*     */   
/*     */   int indexPrmElt;
/*     */   
/*     */   int indexCotElt;
/*     */   
/*     */   private List<PrimeIndemniteC> listPrimes;
/*     */   private List<SelectItem> cotisations;
/*     */   private String codeElement;
/*     */   private String libelleElement;
/*     */   private List<CotisationDetailC> listElementPrm;
/*     */   private List<CotisationDetailC> listElementCot;
/*     */   private List<CotisationDetailC> listCotAdded;
/*     */   private CotisationDetailC detail;
/*  59 */   private HttpSession session = HelperC.getSession(); 
            private int idPrime; 
            private int idCot; 
            private boolean detailSelected; 
            private boolean disableSelectPrm; 
            private List<BaremeIPRC> listeBareme; 
            private PrimeIndemniteC prime; 
            private OperateurC operateur; 
            private ExerciceC exercice; 
            private Base selectedOrganisme; 
            private List<Base> listeOrganisme;
/*     */   private DroitC droit;
/*     */   Base userFonction;
/*  62 */   int priority = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDisableSelectPrm() {
/*  69 */     return this.disableSelectPrm;
/*     */   }
/*     */   
/*     */   public void setDisableSelectPrm(boolean disableSelectPrm) {
/*  73 */     this.disableSelectPrm = disableSelectPrm;
/*     */   }
/*     */   
/*     */   public List<PrimeIndemniteC> getListPrimes() {
/*  77 */     return this.listPrimes;
/*     */   }
/*     */   
/*     */   public void setListPrimes(List<PrimeIndemniteC> listPrimes) {
/*  81 */     this.listPrimes = listPrimes;
/*     */   }
/*     */   
/*     */   public CotisationDetailC getDetail() {
/*  85 */     return this.detail;
/*     */   }
/*     */   
/*     */   public void setDetail(CotisationDetailC detail) {
/*  89 */     this.detail = detail;
/*     */   }
/*     */   
/*     */   public List<BaremeIPRC> getListeBareme() {
/*  93 */     return this.listeBareme;
/*     */   }
/*     */   
/*     */   public void setListeBareme(List<BaremeIPRC> listeBareme) {
/*  97 */     this.listeBareme = listeBareme;
/*     */   }
/*     */   
/*     */   public DroitC getDroit() {
/* 101 */     return this.droit;
/*     */   }
/*     */   
/*     */   public void setDroit(DroitC droit) {
/* 105 */     this.droit = droit;
/*     */   }
/*     */   
/*     */   public List<Base> getListeOrganisme() {
/* 109 */     return this.listeOrganisme;
/*     */   }
/*     */   
/*     */   public void setListeOrganisme(List<Base> listeOrganisme) {
/* 113 */     this.listeOrganisme = listeOrganisme;
/*     */   }
/*     */   
/*     */   public String getCodeElement() {
/* 117 */     return this.codeElement;
/*     */   }
/*     */   
/*     */   public void setCodeElement(String codeElement) {
/* 121 */     this.codeElement = codeElement;
/*     */   }
/*     */   
/*     */   public List<CotisationC> getListCotisation() {
/* 125 */     return this.listCotisation;
/*     */   }
/*     */   
/*     */   public void setListCotisation(List<CotisationC> listCotisation) {
/* 129 */     this.listCotisation = listCotisation;
/*     */   }
/*     */   
/*     */   public ParametreCotisationC getSelected() {
/* 133 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(ParametreCotisationC selected) {
/* 137 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public int getIdPrime() {
/* 141 */     return this.idPrime;
/*     */   }
/*     */   
/*     */   public void setIdPrime(int idPrime) {
/* 145 */     this.idPrime = idPrime;
/*     */   }
/*     */   
/*     */   public boolean isDetailSelected() {
/* 149 */     return this.detailSelected;
/*     */   }
/*     */   
/*     */   public void setDetailSelected(boolean detailSelected) {
/* 153 */     this.detailSelected = detailSelected;
/*     */   }
/*     */   
/*     */   public PrimeIndemniteC getPrime() {
/* 157 */     return this.prime;
/*     */   }
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/* 161 */     this.prime = prime;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/* 165 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 169 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/* 173 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 177 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/* 181 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 185 */     this.session = session;
/*     */   }
/*     */   
/*     */   public Base getSelectedOrganisme() {
/* 189 */     return this.selectedOrganisme;
/*     */   }
/*     */   
/*     */   public void setSelectedOrganisme(Base selectedOrganisme) {
/* 193 */     this.selectedOrganisme = selectedOrganisme;
/*     */   }
/*     */   
/*     */   public String getLibelleElement() {
/* 197 */     return this.libelleElement;
/*     */   }
/*     */   
/*     */   public void setLibelleElement(String libelleElement) {
/* 201 */     this.libelleElement = libelleElement;
/*     */   }
/*     */   
/*     */   public List<CotisationDetailC> getListElementPrm() {
/* 205 */     return this.listElementPrm;
/*     */   }
/*     */   
/*     */   public void setListElementPrm(List<CotisationDetailC> listElementPrm) {
/* 209 */     this.listElementPrm = listElementPrm;
/*     */   }
/*     */   
/*     */   public List<CotisationDetailC> getListElementCot() {
/* 213 */     return this.listElementCot;
/*     */   }
/*     */   
/*     */   public void setListElementCot(List<CotisationDetailC> listElementCot) {
/* 217 */     this.listElementCot = listElementCot;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getCotisations() {
/* 221 */     return this.cotisations;
/*     */   }
/*     */   
/*     */   public void setCotisations(List<SelectItem> cotisations) {
/* 225 */     this.cotisations = cotisations;
/*     */   }
/*     */   
/*     */   public int getIdCot() {
/* 229 */     return this.idCot;
/*     */   }
/*     */   
/*     */   public void setIdCot(int idCot) {
/* 233 */     this.idCot = idCot;
/*     */   }
/*     */   
/*     */   public List<ParametreCotisationC> getListeParametre() {
/* 237 */     return this.listeParametre;
/*     */   }
/*     */   
/*     */   public void setListeParametre(List<ParametreCotisationC> listeParametre) {
/* 241 */     this.listeParametre = listeParametre;
/*     */   }
/*     */   public List<CotisationDetailC> getListCotAdded() {
/* 244 */     return this.listCotAdded;
/*     */   }
/*     */   
/*     */   public void setListCotAdded(List<CotisationDetailC> listCotAdded) {
/* 248 */     this.listCotAdded = listCotAdded;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   private void charger() {
/* 253 */     this.operateur = new OperateurC();
/* 254 */     this.exercice = new ExerciceC();
/*     */     
/* 256 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 257 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 258 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 259 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/* 261 */     if (this.operateur == null || this.exercice == null) {
/*     */       try {
/* 263 */         FacesContext context = FacesContext.getCurrentInstance();
/* 264 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 265 */       } catch (IOException e) {
/*     */         
/* 267 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/* 270 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 271 */       if (this.userFonction != null) {
/* 272 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), 
/* 273 */             Constante.Role.parametrage);
/*     */       }
/* 275 */       this.listElementPrm = new ArrayList<CotisationDetailC>();
/* 276 */       this.listElementCot = new ArrayList<CotisationDetailC>();
/*     */       
/* 278 */       chargerDetailPrmtr();
/* 279 */       chargerCotisation();
/*     */       
/* 281 */       this.disableSelectPrm = true;
/* 282 */       this.selected = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeValueTauxSalarial() {
/* 289 */     if (getTauxSalarial() > 0.0D)
/* 290 */       setForfaitSalarial(0.0D); 
/*     */   }
/*     */   
/*     */   public void changeValueForfaitSalarial() {
/* 294 */     if (getForfaitSalarial() > 0.0D)
/* 295 */       setTauxSalarial(0.0D); 
/*     */   }
/*     */   
/*     */   public void changeValueTauxPatronal() {
/* 299 */     if (getTauxPatronal() > 0.0D)
/* 300 */       setForfaitPatronal(0.0D); 
/*     */   }
/*     */   
/*     */   public void changeValueForfaitPatronal() {
/* 304 */     if (getForfaitPatronal() > 0.0D)
/* 305 */       setTauxPatronal(0.0D); 
/*     */   }
/*     */   public void changePlafond() {
/* 308 */     if (getForfaitPatronal() > 0.0D) {
/* 309 */       setPlafonPatronal(0.0D);
/*     */     }
/* 311 */     if (getForfaitSalarial() > 0.0D)
/* 312 */       setPlafonSalarial(0.0D); 
/*     */   }
/*     */   
/*     */   public void changePlancher() {
/* 316 */     if (getForfaitPatronal() > 0.0D) {
/* 317 */       setPlancherPatronal(0.0D);
/*     */     }
/* 319 */     if (getForfaitSalarial() > 0.0D) {
/* 320 */       setPlancherSalarial(0.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerDetailPrmtr() {
/* 326 */     addBaseAddition();
/*     */ 
/*     */     
/* 329 */    
/* 330 */      
				for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemniteImposable()) {
					
				
				if (this.detail == null)
/* 331 */         this.detail = new CotisationDetailC(); 
/* 332 */       this.detail.setCodeElement(prm.getCode());
/* 333 */       this.detail.setLibelleElement(prm.getDesignation());
/* 334 */       this.detail.setTypeElement("A");
/* 335 */       this.detail.setTypePrm("P");
/* 336 */       this.listElementPrm.add(this.detail);
/* 337 */       this.detail = null; }
/*     */ 
/*     */     
/* 340 */     addBaseRemovable();
/*     */ 
/*     */     
/* 343 */    for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemniteImposable()) {
/* 344 */       if (this.detail == null)
/* 345 */         this.detail = new CotisationDetailC(); 
/* 346 */       this.detail.setCodeElement(prm.getCode());
/* 347 */       this.detail.setLibelleElement(prm.getDesignation());
/* 348 */       this.detail.setTypeElement("M");
/* 349 */       this.detail.setTypePrm("P");
/* 350 */       this.listElementCot.add(this.detail);
/* 351 */       this.detail = null; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private void addBaseAddition() {
/* 357 */     if (this.detail == null)
/* 358 */       this.detail = new CotisationDetailC(); 
/* 359 */     this.detail.setCodeElement("SB");
/* 360 */     this.detail.setLibelleElement("SALAIRE DE BASE");
/* 361 */     this.detail.setTypeElement("A");
/* 362 */     this.detail.setTypePrm("P");
/* 363 */     this.detail.setTypeBase(1);
/* 364 */     this.listElementPrm.add(this.detail);
/* 365 */     this.detail = null;
/*     */     
/* 367 */     if (this.detail == null)
/* 368 */       this.detail = new CotisationDetailC(); 
/* 369 */     this.detail.setCodeElement("HS");
/* 370 */     this.detail.setLibelleElement("HEURES SUPPLEMENTAIRES");
/* 371 */     this.detail.setTypeElement("A");
/* 372 */     this.detail.setTypePrm("P");
/* 373 */     this.detail.setTypeBase(2);
/* 374 */     this.listElementPrm.add(this.detail);
/* 375 */     this.detail = null;
/*     */     
/* 377 */     if (this.detail == null)
/* 378 */       this.detail = new CotisationDetailC(); 
/* 379 */     this.detail.setCodeElement("LG");
/* 380 */     this.detail.setLibelleElement("LOGEMENT");
/* 381 */     this.detail.setTypeElement("A");
/* 382 */     this.detail.setTypePrm("P");
/* 383 */     this.detail.setTypeBase(3);
/* 384 */     this.listElementPrm.add(this.detail);
/* 385 */     this.detail = null;
/*     */     
/* 387 */     if (this.detail == null)
/* 388 */       this.detail = new CotisationDetailC(); 
/* 389 */     this.detail.setCodeElement("AL");
/* 390 */     this.detail.setLibelleElement("ALLOCATIONS FAMILIALES");
/* 391 */     this.detail.setTypeElement("A");
/* 392 */     this.detail.setTypePrm("P");
/* 393 */     this.detail.setTypeBase(4);
/* 394 */     this.listElementPrm.add(this.detail);
/* 395 */     this.detail = null;
			
			if (this.detail == null)
/* 388 */       this.detail = new CotisationDetailC(); 
/* 389 */     this.detail.setCodeElement("BR");
/* 390 */     this.detail.setLibelleElement("SALAIRES BRUTES");
/* 391 */     this.detail.setTypeElement("A");
/* 392 */     this.detail.setTypePrm("P");
/* 393 */     this.detail.setTypeBase(5);
/* 394 */     this.listElementPrm.add(this.detail);
/* 395 */     this.detail = null;
              
/*     */   }
/*     */ 
/*     */   
/*     */   private void addBaseRemovable() {
/* 400 */     if (this.detail == null)
/* 401 */       this.detail = new CotisationDetailC(); 
/* 402 */     this.detail.setCodeElement("SB");
/* 403 */     this.detail.setLibelleElement("SALAIRE DE BASE");
/* 404 */     this.detail.setTypeElement("M");
/* 405 */     this.detail.setTypePrm("P");
/* 406 */     this.detail.setTypeBase(1);
/* 407 */     this.listElementCot.add(this.detail);
/* 408 */     this.detail = null;
/*     */     
/* 410 */     if (this.detail == null)
/* 411 */       this.detail = new CotisationDetailC(); 
/* 412 */     this.detail.setCodeElement("HS");
/* 413 */     this.detail.setLibelleElement("HEURES SUPPLEMENTAIRES");
/* 414 */     this.detail.setTypeElement("M");
/* 415 */     this.detail.setTypePrm("P");
/* 416 */     this.detail.setTypeBase(2);
/* 417 */     this.listElementCot.add(this.detail);
/* 418 */     this.detail = null;
/*     */     
/* 420 */     if (this.detail == null)
/* 421 */       this.detail = new CotisationDetailC(); 
/* 422 */     this.detail.setCodeElement("LG");
/* 423 */     this.detail.setLibelleElement("LOGEMENT");
/* 424 */     this.detail.setTypeElement("M");
/* 425 */     this.detail.setTypePrm("P");
/* 426 */     this.detail.setTypeBase(3);
/* 427 */     this.listElementCot.add(this.detail);
/* 428 */     this.detail = null;
/*     */     
/* 430 */     if (this.detail == null)
/* 431 */       this.detail = new CotisationDetailC(); 
/* 432 */     this.detail.setCodeElement("AL");
/* 433 */     this.detail.setLibelleElement("ALLOCATIONS FAMILIALES");
/* 434 */     this.detail.setTypeElement("M");
/* 435 */     this.detail.setTypePrm("P");
/* 436 */     this.detail.setTypeBase(4);
/* 437 */     this.listElementCot.add(this.detail);
/* 438 */     this.detail = null;
/*     */   }
/*     */   
/*     */   private void chargerCotisationDetail() {
/* 442 */     CotisationC cot = null;
/*     */ 
/*     */     
/* 445 */     int i = 0;
/*     */     
/* 447 */    
/* 448 */      for (ParametreCotisationC pm : FichierBaseDAO.getInstance().getAllParametreCotisation(this.idCot)) {
	

                cot = FichierBaseDAO.getInstance().getCotisation(pm.getIdCotisation());
/* 449 */       if (this.detail == null)
/* 450 */         this.detail = new CotisationDetailC(); 
/* 451 */       this.detail.setCodeElement(cot.getCode());
/* 452 */       this.detail.setLibelleElement(cot.getDesignation());
/* 453 */       this.detail.setTypeElement("M");
/* 454 */       this.detail.setTypePrm("C");
/* 455 */       this.listElementCot.add(i, this.detail);
/* 456 */       this.detail = null;
/* 457 */       i++; 
/*     */   }
/*     */   }
/*     */   
/*     */   public void changeCotisation(ValueChangeEvent event) {
/* 462 */     this.idCot = ((Integer)event.getNewValue()).intValue();
/* 463 */     if (this.idCot > 0) {
/*     */       
/* 465 */       this.selected = FichierBaseDAO.getInstance().getParameterCotisation(this.idCot, false);
/* 466 */       if (this.selected != null) {
/*     */         
/* 468 */         setObject();
/*     */       }
/*     */       else {
/*     */         
/* 472 */         clear(false);
/* 473 */         chargerCotisationDetail();
/* 474 */         setIdCotisation(this.idCot);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void searchValue() {
/* 481 */     if (this.idCot > 0) {
/*     */       
/* 483 */       this.selected = FichierBaseDAO.getInstance().getParameterCotisation(this.idCot, false);
/* 484 */       if (this.selected != null) {
/*     */         
/* 486 */         setObject();
/*     */       }
/*     */       else {
/*     */         
/* 490 */         clear(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void chargementParametre() {
/* 496 */     CotisationC cotis = null;
/* 497 */     this.listeParametre = FichierBaseDAO.getInstance().getAllParametreCotisation();
			  int num=0;
/* 498 */     for (ParametreCotisationC cot : this.listeParametre) {
/*     */       num++;
/* 500 */       cotis = FichierBaseDAO.getInstance().getCotisation(cot.getIdCotisation());
/* 501 */       cot.setCode(cotis.getCode());
/* 502 */       cot.setLibelle(cotis.getDesignation());
				cot.setPriorite(num);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerCotisation() {
/* 508 */     this.cotisations = new ArrayList<SelectItem>();
/* 509 */     this.cotisations.add(new SelectItem(Integer.valueOf(0), ""));
/*     */     
/* 511 */     for (CotisationC cot : FichierBaseDAO.getInstance().getAllCotisation())
/*     */     {
/* 513 */       this.cotisations.add(new SelectItem(Integer.valueOf(cot.getId()), String.valueOf(cot.getCode()) + "||" + cot.getDesignation()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeCalculationValue() {
/* 520 */     setForfaitPatronal(0.0D);
/* 521 */     setForfaitSalarial(0.0D);
/* 522 */     setTauxPatronal(0.0D);
/* 523 */     setTauxSalarial(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onSelected(SelectEvent event) {
/* 528 */     this.selected = (ParametreCotisationC)event.getObject();
/* 529 */     setObject();
/* 530 */     PrimeFaces.current().executeScript("PF('dlgCotisation').hide();");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeElement(ValueChangeEvent event) {
/* 536 */     this.idPrime = ((Integer)event.getNewValue()).intValue();
/* 537 */     this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrime);
/*     */     
/* 539 */     if (this.prime != null) {
/* 540 */       this.codeElement = String.valueOf(this.prime.getTypePrime()) + this.prime.getCode();
/*     */     } else {
/* 542 */       this.codeElement = "";
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeBaseSal(ValueChangeEvent event) {
/* 547 */     setTypeBaseSalarial(((Integer)event.getNewValue()).intValue());
/* 548 */     switch (getTypeBaseSalarial()) {
/*     */       case 0:
/*     */       case 1:
/* 551 */         setBaseFixe(0.0D);
/*     */         break;
/*     */       case 2:
/* 554 */         setForfaitPatronal(0.0D);
/* 555 */         setForfaitSalarial(0.0D);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   public void changeBasePatr(ValueChangeEvent event) {
/* 560 */     setTypeBasePatronal(((Integer)event.getNewValue()).intValue());
/* 561 */     switch (getTypeBasePatronal()) {
/*     */       case 0:
/*     */       case 1:
/* 564 */         setBaseFixe(0.0D);
/*     */         break;
/*     */       case 2:
/* 567 */         setForfaitPatronal(0.0D);
/* 568 */         setForfaitSalarial(0.0D);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   private void clear(boolean b) {
/* 573 */     if (b) {
/*     */       
/* 575 */       setId(0);
/* 576 */       this.idCot = 0;
/*     */     } 
/* 578 */     setForfaitPatronal(0.0D);
/* 579 */     setForfaitSalarial(0.0D);
/* 580 */     setTauxPatronal(0.0D);
/* 581 */     setTauxSalarial(0.0D);
/* 582 */     setPlancherPatronal(0.0D);
/* 583 */     setPlancherSalarial(0.0D);
/* 584 */     setPlafonPatronal(0.0D);
/* 585 */     setPlafonSalarial(0.0D);
/* 586 */     setTypeBasePatronal(0);
/* 587 */     setTypeBaseSalarial(0);
			  setTauxPlafonPatronal(0);
			  setTauxPlafonSalarial(0);
/* 588 */     setBaseFixe(0.0D);
/* 589 */     this.idPrime = 0;
/* 590 */     this.selected = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 597 */     if (this.selected != null) {
/* 598 */       this.idCot = this.selected.getIdCotisation();
/* 599 */       setId(this.selected.getId());
/* 600 */       setForfaitPatronal(this.selected.getForfaitPatronal());
/* 601 */       setForfaitSalarial(this.selected.getForfaitSalarial());
/* 602 */       setTauxPatronal(this.selected.getTauxPatronal());
/* 603 */       setTauxSalarial(this.selected.getTauxSalarial());
/* 604 */       setIdCotisation(this.selected.getIdCotisation());
/* 605 */       setPlafonPatronal(this.selected.getPlafonPatronal());
/* 606 */       setPlafonSalarial(this.selected.getPlafonSalarial());
/* 607 */       setPlancherPatronal(this.selected.getPlancherPatronal());
/* 608 */       setPlancherSalarial(this.selected.getPlancherSalarial());
/* 609 */       setTypeBasePatronal(this.selected.getTypeBasePatronal());
/* 610 */       setTypeBaseSalarial(this.selected.getTypeBaseSalarial());
/* 611 */       setPlafondBase(this.selected.getPlafondBase());
/* 612 */       setPlancherBase(this.selected.getPlancherBase());
/* 613 */       setBaseFixe(this.selected.getBaseFixe());
                setTauxPlafonPatronal(selected.getTauxPlafonPatronal());
                setTauxPlafonSalarial(selected.getTauxPlafonSalarial());
/* 633 */       chargerCotisationDetail();
/* 634 */       setDetailElementValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDetailElementValue() {
/* 640 */     for (CotisationDetailC det : this.listElementPrm) {
/*     */       
/* 642 */       this.detail = FichierBaseDAO.getInstance().getCotisationDetail(det.getCodeElement(), this.selected.getId(), "A");
/* 643 */       if (this.detail != null) {
/*     */         
/* 645 */         if (this.detail.getTypeElement().equals("A")) {
/*     */           
/* 647 */           det.setSeleceted(true);
/* 648 */           det.setDisable(false);
/* 649 */           det.setId(this.detail.getId());
/* 650 */           det.setForfait(this.detail.getForfait());
/* 651 */           det.setTaux(this.detail.getTaux());
/* 652 */           det.setTauxMax(this.detail.getTauxMax());
/* 653 */           det.setIdEntete(this.detail.getIdEntete());
/* 654 */           det.setPlafon(this.detail.getPlafon());
/* 655 */           det.setPlancher(this.detail.getPlancher());
/*     */         } 
/*     */ 
/*     */         
/* 659 */         this.detail = null;
/*     */       } 
/*     */     } 
/*     */     
/* 663 */     for (CotisationDetailC det : this.listElementCot) {
/*     */       
/* 665 */       this.detail = FichierBaseDAO.getInstance().getCotisationDetail(det.getCodeElement(), this.selected.getId(), "M");
/* 666 */       if (this.detail != null) {
/*     */         
/* 668 */         if (this.detail.getTypeElement().equals("M")) {
/*     */           
/* 670 */           det.setSeleceted(true);
/* 671 */           det.setDisable(false);
/* 672 */           det.setId(this.detail.getId());
/* 673 */           det.setForfait(this.detail.getForfait());
/* 674 */           det.setTaux(this.detail.getTaux());
/* 675 */           det.setTauxMax(this.detail.getTauxMax());
/* 676 */           det.setIdEntete(this.detail.getIdEntete());
/* 677 */           det.setPlafon(this.detail.getPlafon());
/* 678 */           det.setPlancher(this.detail.getPlancher());
/*     */         } 
/*     */         
/* 681 */         this.detail = null;
/*     */       } 
/*     */     } 
/*     */   }
 

/*     */   public void changePrime(ValueChangeEvent ev) {
/* 688 */     this.idPrime = ((Integer)ev.getNewValue()).intValue();
/* 689 */     if (this.idPrime != 0) {
/* 690 */       setPrime(FichierBaseDAO.getInstance().getPrimeIndemnite(
/* 691 */             this.idPrime));
/*     */     }
/*     */   }
/*     */   
/*     */   public void completeDetail() {
/* 696 */     if (this.detail != null) {
/*     */       
/* 698 */       this.codeElement = this.detail.getCodeElement();
/* 699 */       this.libelleElement = this.detail.getLibelleElement();
/*     */     } 
/*     */   }
/*     */   public void enregistrer() {
/* 703 */     if (getIdCotisation() == 0) {
/* 704 */       HelperC.afficherMessage("Information", 
/* 705 */           "Pr�ciser la cotisation � parametrer");
/*     */     }
/*     */     else {
/*     */       
/* 709 */       for (CotisationDetailC det : this.listElementPrm) {
/*     */         
/* 711 */         if (det.isSeleceted()) {
/* 712 */           getListDetail().add(det); continue;
/*     */         } 
/* 714 */         if (det.getId() > 0) {
/* 715 */           getListDeleted().add(det);
/*     */         }
/*     */       } 
/* 718 */       if (this.listElementCot.size() > 0) {
/* 719 */         for (CotisationDetailC det : this.listElementCot) {
/* 720 */           if (det.isSeleceted()) {
/* 721 */             getListDetail().add(det);
/*     */           
/*     */           }
/* 724 */           else if (det.getId() > 0) {
/* 725 */             getListDeleted().add(det);
/*     */           } 
/*     */ 
/*     */           
/* 729 */           this.priority = addPriority(det);
/* 730 */           this.priority++;
/* 731 */           if (this.priority > 0 && 
/* 732 */             this.priority > getPriorite()) {
/* 733 */             setPriorite(this.priority);
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/* 738 */         setPriorite(1);
/*     */       } 
/* 740 */       if (getId() == 0)
/* 741 */         if (this.droit.isCreer()) {
/* 742 */           if (FichierBaseDAO.getInstance().insertUpdateParametreCotisation(
/* 743 */               this)) {
/* 744 */             clear(true);
/* 745 */             HelperC.afficherMessage("Information", 
/* 746 */                 "Succ�s de l'op�ration");
/* 747 */             charger();
/*     */           } else {
/* 749 */             HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
/*     */           } 
/*     */         } else {
/* 752 */           HelperC.afficherMessage("ATTENTION", 
/* 753 */               "Vous n'avez pas le droit de cr�er ");
/*     */         }  
/* 755 */       if (getId() > 0)
/* 756 */         if (this.droit.isModifier()) {
/* 757 */           if (FichierBaseDAO.getInstance().insertUpdateParametreCotisation(
/* 758 */               this)) {
/* 759 */             clear(true);
/* 760 */             HelperC.afficherMessage("Information", 
/* 761 */                 "Succ�s de l'op�ration");
/* 762 */             charger();
/*     */           } else {
/* 764 */             HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
/*     */           } 
/*     */         } else {
/* 767 */           HelperC.afficherMessage("ATTENTION", 
/* 768 */               "Vous n'avez pas le droit de modifier ");
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void supprimer() {
/* 774 */     if (getId() == 0) {
/* 775 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 778 */     else if (this.droit.isSupprimer()) {
/* 779 */       if (FichierBaseDAO.getInstance()
/* 780 */         .deleteParametreCotisation(this.selected)) {
/* 781 */         clear(true);
/* 782 */         charger();
/* 783 */         HelperC.afficherMessage("Information", 
/* 784 */             "Succ�s de l'op�ration ");
/*     */       } else {
/* 786 */         HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */       } 
/*     */     } else {
/* 789 */       HelperC.afficherMessage("ATTENTION", 
/* 790 */           "Vous n'avez pas le droit de supprimer ");
/*     */     } 
/*     */   }

			public void updatePriority(){
				if(listeParametre!=null && listeParametre.size()>0)
					
					if(FichierBaseDAO.getInstance().updateCotisationPriority(listeParametre))
						 HelperC.afficherMessage("Information", 
								           "Succ�s de l'op�ration");
					else
						HelperC.afficherAttention("D�sol�", "Echec de l'op�ration");
						
			}

/*     */   public void initialiser() {
/* 797 */     clear(true);
/* 798 */     for (CotisationDetailC det : this.listElementCot) {
/* 799 */       det.setSeleceted(false);
/* 800 */       det.setPlafon(0.0D);
/* 801 */       det.setPlancher(0.0D);
/* 802 */       det.setTaux(0.0D);
/* 803 */       det.setForfait(0.0D);
/* 804 */       det.setDisable(true);
/*     */     } 
/* 806 */     for (CotisationDetailC det : this.listElementPrm) {
/* 807 */       det.setSeleceted(false);
/* 808 */       det.setPlafon(0.0D);
/* 809 */       det.setPlancher(0.0D);
/* 810 */       det.setTaux(0.0D);
/* 811 */       det.setForfait(0.0D);
/* 812 */       det.setDisable(true);
/*     */     } 
/* 814 */     this.detail = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private int addPriority(CotisationDetailC det) {
/* 819 */     CotisationC cot = null;
/* 820 */     cot = FichierBaseDAO.getInstance().getCotisation(det.getCodeElement());
/* 821 */     if (cot != null) {
/* 822 */       this.priority = FichierBaseDAO.getInstance().getPrioriteCot(cot.getId());
/*     */     }
/* 824 */     return this.priority;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\ParametreCotisationB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */