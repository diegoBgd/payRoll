/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.CotisationC;
/*     */ import classesPaie.DeductionC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageJournalC;
/*     */ import classesPaie.ParametrageJournalDetailC;
/*     */ import classesPaie.ParametrageJournalElementC;
/*     */ import classesPaie.PrimeIndemniteC;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametreJournalPaieB
/*     */   extends ParametrageJournalC
/*     */ {
/*     */   private static final long serialVersionUID = 6195269262643867390L;
/*     */   private ParametrageJournalC selectedParametre;
/*     */   private ParametrageJournalDetailC selectedDetail;
/*     */   private List<CotisationC> listCotisaton;
/*     */   private CotisationC cotisation;
/*     */   private List<PrimeIndemniteC> listPrime;
/*     */   private PrimeIndemniteC prime;
/*     */   private List<DeductionC> listDeduction;
/*     */   private DeductionC deduction;
/*     */   private List<SelectItem> listElement;
/*     */   private List<ParametrageJournalC> listJournal;
/*  53 */   private HttpSession session = HelperC.getSession(); 
			private List<ParametrageJournalElementC> listDetElement; 
			private ParametrageJournalElementC selectedElement; 
			private int itemeId; 
			private int sign; 
			private boolean disableMsg;
			private int typeElmt; 
			private String codeElement; 
			private String titreElement; 
			private String libelleElement; 
			private OperateurC operateur;
			private ExerciceC exercice; 
			private DroitC droit; 
			Base userFonction;
/*     */   int index;
/*     */   boolean selected;
/*     */   
/*     */   public CotisationC getCotisation() {
/*  58 */     return this.cotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCotisation(CotisationC cotisation) {
/*  63 */     this.cotisation = cotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getPrime() {
/*  68 */     return this.prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/*  73 */     this.prime = prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTypeElmt() {
/*  78 */     return this.typeElmt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeElmt(int typeElmt) {
/*  83 */     this.typeElmt = typeElmt;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemeId() {
/*  88 */     return this.itemeId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemeId(int itemeId) {
/*  93 */     this.itemeId = itemeId;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParametrageJournalC getSelectedParametre() {
/*  98 */     return this.selectedParametre;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedParametre(ParametrageJournalC selectedParametre) {
/* 103 */     this.selectedParametre = selectedParametre;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParametrageJournalDetailC getSelectedDetail() {
/* 108 */     return this.selectedDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedDetail(ParametrageJournalDetailC selectedDetail) {
/* 113 */     this.selectedDetail = selectedDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeductionC getDeduction() {
/* 118 */     return this.deduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeduction(DeductionC deduction) {
/* 123 */     this.deduction = deduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeElement() {
/* 128 */     return this.codeElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeElement(String codeElement) {
/* 133 */     this.codeElement = codeElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitreElement() {
/* 138 */     return this.titreElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitreElement(String titreElement) {
/* 143 */     this.titreElement = titreElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleElement() {
/* 148 */     return this.libelleElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelleElement(String libelleElement) {
/* 153 */     this.libelleElement = libelleElement;
/*     */   }
/*     */   
/*     */   public List<CotisationC> getListCotisaton() {
/* 157 */     return this.listCotisaton;
/*     */   }
/*     */   
/*     */   public void setListCotisaton(List<CotisationC> listCotisaton) {
/* 161 */     this.listCotisaton = listCotisaton;
/*     */   }
/*     */   
/*     */   public List<PrimeIndemniteC> getListPrime() {
/* 165 */     return this.listPrime;
/*     */   }
/*     */   
/*     */   public void setListPrime(List<PrimeIndemniteC> listPrime) {
/* 169 */     this.listPrime = listPrime;
/*     */   }
/*     */   
/*     */   public List<DeductionC> getListDeduction() {
/* 173 */     return this.listDeduction;
/*     */   }
/*     */   
/*     */   public void setListDeduction(List<DeductionC> listDeduction) {
/* 177 */     this.listDeduction = listDeduction;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListElement() {
/* 181 */     return this.listElement;
/*     */   }
/*     */   
/*     */   public void setListElement(List<SelectItem> listElement) {
/* 185 */     this.listElement = listElement;
/*     */   }
/*     */   
/*     */   public List<ParametrageJournalElementC> getListDetElement() {
/* 189 */     return this.listDetElement;
/*     */   }
/*     */   
/*     */   public void setListDetElement(List<ParametrageJournalElementC> listDetElement) {
/* 193 */     this.listDetElement = listDetElement;
/*     */   }
/*     */   
/*     */   public ParametrageJournalElementC getSelectedElement() {
/* 197 */     return this.selectedElement;
/*     */   }
/*     */   
/*     */   public void setSelectedElement(ParametrageJournalElementC selectedElement) {
/* 201 */     this.selectedElement = selectedElement;
/*     */   }
/*     */   
/*     */   public int getSign() {
/* 205 */     return this.sign;
/*     */   }
/*     */   
/*     */   public void setSign(int sign) {
/* 209 */     this.sign = sign;
/*     */   }
/*     */   
/*     */   public List<ParametrageJournalC> getListJournal() {
/* 213 */     return this.listJournal;
/*     */   }
/*     */   
/*     */   public void setListJournal(List<ParametrageJournalC> listJournal) {
/* 217 */     this.listJournal = listJournal;
/*     */   }

public boolean isDisableMsg() {
	return disableMsg;
}
public void setDisableMsg(boolean disableMsg) {
	this.disableMsg = disableMsg;
}
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/* 223 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 224 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 225 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 226 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 227 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 231 */         FacesContext context = FacesContext.getCurrentInstance();
/* 232 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 234 */       catch (IOException e) {
/*     */         
/* 236 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/* 240 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 241 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 242 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 243 */       if (this.userFonction != null)
/*     */       {
/* 245 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
/*     */       }
/* 247 */       this.listElement = new ArrayList<SelectItem>();
/* 248 */       this.listDetElement = new ArrayList<ParametrageJournalElementC>();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargementElement() {
/* 255 */     this.listElement.clear();
/* 256 */     switch (this.typeElmt) {
/*     */       
/*     */       case 1:
/* 259 */         chargementAutreElement();
/*     */         break;
/*     */       
/*     */       case 2:
/* 263 */         chargementPrime();
/*     */         break;
/*     */       
/*     */       case 3:
/*     */       case 4:
/* 268 */         chargementCotisation();
/*     */         break;
/*     */       
/*     */       case 5:
/* 272 */         chargementDeduction();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargementCotisation() {
/* 280 */     this.listDetElement.clear();
/* 281 */     this.listCotisaton = FichierBaseDAO.getInstance().getAllCotisation();
/*     */     
/* 283 */     if (this.listCotisaton != null && this.listCotisaton.size() > 0)
/*     */     {
/*     */       
/* 286 */       for (CotisationC cot : this.listCotisaton) {
/*     */         
/* 288 */         if (this.selectedElement == null) {
/* 289 */           this.selectedElement = new ParametrageJournalElementC();
/*     */         }
/* 291 */         this.selectedElement.setIdElement(cot.getId());
/* 292 */         this.selectedElement.setLibelleElment(cot.getDesignation());
/* 293 */         this.listDetElement.add(this.selectedElement);
/* 294 */         this.selectedElement = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargementPrime() {
/* 302 */     this.listPrime = FichierBaseDAO.getInstance().getAllPrimeIndemnite();
/* 303 */     this.listDetElement.clear();
/*     */     
/* 305 */     if (this.listPrime != null && this.listPrime.size() > 0)
/*     */     {
/*     */       
/* 308 */       for (PrimeIndemniteC prm : this.listPrime) {
/* 309 */         this.selectedElement = new ParametrageJournalElementC();
/* 310 */         this.selectedElement.setIdElement(prm.getId());
/* 311 */         this.selectedElement.setLibelleElment(prm.getDesignation());
/* 312 */         this.listDetElement.add(this.selectedElement);
/* 313 */         this.selectedElement = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargementDeduction() {
/* 322 */     this.listDetElement.clear();
/* 323 */     this.listDeduction = FichierBaseDAO.getInstance().getAllDeduction();
/* 324 */     if (this.listDeduction != null && this.listDeduction.size() > 0)
/*     */     {
/*     */       
/* 327 */       for (DeductionC ded : this.listDeduction) {
/* 328 */         if (this.selectedElement == null)
/* 329 */           this.selectedElement = new ParametrageJournalElementC(); 
/* 330 */         this.selectedElement.setIdElement(ded.getId());
/* 331 */         this.selectedElement.setLibelleElment(ded.getDesignation());
/* 332 */         this.listDetElement.add(this.selectedElement);
/* 333 */         this.selectedElement = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void searchParametre() {
/* 341 */     if (getCode() != null && !getCode().equals("")) {
/*     */       
/* 343 */       this.selectedParametre = FichierBaseDAO.getInstance().getParametrageJournal(getCode());
/* 344 */       setObject();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setObject() {
				disableMsg=true;
/* 349 */     if (this.selectedParametre != null) {
/*     */       disableMsg=false;
/* 351 */       setId(this.selectedParametre.getId());
/* 352 */       setCode(this.selectedParametre.getCode());
/* 353 */       setLibelle(this.selectedParametre.getLibelle());
/* 354 */       setListeDetail(this.selectedParametre.getListeDetail());
/* 355 */       setNumIndex();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargementAutreElement() {
/* 362 */     this.listElement.add(new SelectItem(Integer.valueOf(1), "Salaire de base"));
/* 363 */     this.listElement.add(new SelectItem(Integer.valueOf(2), "Montant heures suppl�mentaires"));
/* 364 */     this.listElement.add(new SelectItem(Integer.valueOf(3), "Indemnit�  de logement"));
/* 365 */     this.listElement.add(new SelectItem(Integer.valueOf(4), "Allocation familiale"));
/* 366 */     this.listElement.add(new SelectItem(Integer.valueOf(5), "Total primes soumis � cotisation imposable"));
/* 367 */     this.listElement.add(new SelectItem(Integer.valueOf(6), "Base imposable"));
/* 368 */     this.listElement.add(new SelectItem(Integer.valueOf(7), "Total cotisation salariale"));
/* 369 */     this.listElement.add(new SelectItem(Integer.valueOf(8), "Total cotisations employeur"));
/* 370 */     this.listElement.add(new SelectItem(Integer.valueOf(9), "Total prime et indemnit� hors cotisations"));
/* 371 */     this.listElement.add(new SelectItem(Integer.valueOf(10), "Total d�duction"));
/* 372 */     this.listElement.add(new SelectItem(Integer.valueOf(11), "Total remboursement cr�dits"));
/* 373 */     this.listElement.add(new SelectItem(Integer.valueOf(12), "Net � payer"));
/* 374 */     this.listElement.add(new SelectItem(Integer.valueOf(13), "Nombre de jours additionnels"));
/* 375 */     this.listElement.add(new SelectItem(Integer.valueOf(14), "Total pour les jours additionnels"));
/* 376 */     this.listElement.add(new SelectItem(Integer.valueOf(15), "Nombre de personnes pris en charge"));
/* 377 */     this.listElement.add(new SelectItem(Integer.valueOf(16), "Nombre d'heures normales"));
/* 378 */     this.listElement.add(new SelectItem(Integer.valueOf(17), "Nombre d'heures suppl�mentaires"));
/* 379 */     this.listElement.add(new SelectItem(Integer.valueOf(18), "Nombre d'heures non travaill�es"));
/* 380 */     this.listElement.add(new SelectItem(Integer.valueOf(19), "Nombre d'heures travaill�es"));
/* 381 */     this.listElement.add(new SelectItem(Integer.valueOf(20), "Montant cong�maladie"));
/* 382 */     this.listElement.add(new SelectItem(Integer.valueOf(21), "Monatnt cong� maternit�"));
			  this.listElement.add(new SelectItem(Integer.valueOf(22), "Monatnt comission"));
/* 383 */     this.listElement.add(new SelectItem(Integer.valueOf(23), "P�nalit� heures non travaill�es"));
/* 384 */     this.listElement.add(new SelectItem(Integer.valueOf(24), "Commentaire bulletin"));
/* 385 */     this.listElement.add(new SelectItem(Integer.valueOf(25), "Colonne vierge"));
/* 386 */     this.listElement.add(new SelectItem(Integer.valueOf(26), "Date entr�e fonction"));
/*     */     
/* 388 */     this.listDetElement.clear();
/*     */     
/* 390 */     for (SelectItem it : this.listElement) {
/*     */       
/* 392 */       if (this.selectedElement == null)
/* 393 */         this.selectedElement = new ParametrageJournalElementC(); 
/* 394 */         this.selectedElement.setIdElement(Integer.valueOf(it.getValue().toString()).intValue());
/* 395 */         this.selectedElement.setLibelleElment(it.getLabel().toString());
/*     */       
/* 397 */         this.listDetElement.add(this.selectedElement);
/* 398 */         this.selectedElement = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargerParametrage() {
/* 404 */     this.listJournal = FichierBaseDAO.getInstance().getListJournalParametre();
/*     */   }
/*     */   
/*     */   public void takeSelectedJournal() {
/* 408 */     if (this.selectedParametre != null) {
/*     */       
/* 410 */       setObject();
/* 411 */       PrimeFaces.current().executeScript("PF('dlgJrnl').hide();");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeType(ValueChangeEvent event) {
/* 416 */     this.typeElmt = ((Integer)event.getNewValue()).intValue();
/* 417 */     chargementElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void searchElement(ParametrageJournalElementC elt) {
/* 424 */     for (ParametrageJournalElementC elmt : this.listDetElement) {
/*     */       
/* 426 */       if (elmt.getIdElement() == elt.getIdElement()) {
/*     */         
/* 428 */         elmt.setAdded(true);
/* 429 */         elmt.setId(elt.getId());
/* 430 */         elmt.setIdElement(elt.getIdElement());
/* 431 */         elmt.setIdEntete(elt.getIdEntete());
/* 432 */         elmt.setSigne(elt.getSigne());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDetail() {
/* 442 */     if (this.typeElmt > 0) {
/* 443 */       if (this.selectedDetail == null) {
/* 444 */         this.selectedDetail = new ParametrageJournalDetailC();
/*     */       }
/* 446 */       this.selectedDetail.setTitrElement(this.titreElement);
/* 447 */       this.selectedDetail.setTypeElement(this.typeElmt);
/* 448 */       this.selectedDetail.setLibelle(this.libelleElement);
/* 449 */       addElement();
/* 450 */       if (!this.selected) {
/* 451 */         getListeDetail().add(this.selectedDetail);
/*     */       } else {
/* 453 */         getListeDetail().remove(this.index);
/* 454 */         getListeDetail().add(this.index, this.selectedDetail);
/*     */       } 
/*     */       
/* 457 */       setNumIndex();
/* 458 */       clearDetail();
/*     */     }
/*     */     else {
/*     */       
/* 462 */       HelperC.afficherAttention("ATTENTION", "Il faut pr�ciser le type des l�l�ments");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addElement() {
/* 468 */     if (this.selectedDetail != null) {
/*     */       
/* 470 */       int index = 0;
/*     */ 
/*     */       
/* 473 */       for (ParametrageJournalElementC elmt : this.listDetElement) {
/*     */         
/* 475 */         if (elmt.isAdded()) {
/*     */           
/* 477 */           ParametrageJournalElementC selectedElt = new ParametrageJournalElementC();
/* 478 */           selectedElt.setId(elmt.getId());
/* 479 */           selectedElt.setAdded(true);
/* 480 */           selectedElt.setIdElement(elmt.getIdElement());
/* 481 */           selectedElt.setSigne(elmt.getSigne());
/* 482 */           selectedElt.setIdEntete(elmt.getIdEntete());
/* 483 */           selectedElt.setLibelleElment(elmt.getLibelleElment());
/* 484 */           if (!checkElement(selectedElt)) {
/* 485 */             this.selectedDetail.getListDetailElement().add(selectedElt);
/*     */             continue;
/*     */           } 
/* 488 */           index = checkElementIndex(elmt);
/*     */           
/* 490 */           this.selectedDetail.getListDetailElement().remove(index);
/* 491 */           this.selectedDetail.getListDetailElement().add(selectedElt);
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 497 */         if (elmt.getId() > 0) {
/* 498 */           this.selectedDetail.getLiteDeletedElement().add(elmt);
/*     */         }
/*     */       } 
/* 501 */       clearElements();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 506 */       HelperC.afficherAttention("ATTENTION", "Il faut pr�ciser le titre d'une colonne");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkElement(ParametrageJournalElementC elt) {
/* 512 */     boolean bl = false;
/* 513 */     for (ParametrageJournalElementC e : this.selectedDetail.getListDetailElement()) {
/* 514 */       if (e.getIdElement() == elt.getIdElement()) {
/*     */         
/* 516 */         bl = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 520 */     return bl;
/*     */   }
/*     */   
/*     */   private int checkElementIndex(ParametrageJournalElementC elt) {
/* 524 */     int index = 0;
/* 525 */     for (ParametrageJournalElementC e : this.selectedDetail.getListDetailElement()) {
/* 526 */       if (e.getIdElement() == elt.getIdElement()) {
/*     */         
/* 528 */         index = this.selectedDetail.getListDetailElement().indexOf(e);
/*     */         break;
/*     */       } 
/*     */     } 
/* 532 */     return index;
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearElements() {
/* 537 */     for (ParametrageJournalElementC elmt : this.listDetElement) {
/*     */       
/* 539 */       elmt.setAdded(false);
/* 540 */       elmt.setSigne(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeDetail() {
/* 545 */     if (this.selectedDetail != null) {
/*     */       
/* 547 */       if (this.selectedDetail.getId() > 0)
/*     */       {
/* 549 */         getListDeleted().add(this.selectedDetail);
/*     */       }
/* 551 */       getListeDetail().remove(this.index);
/* 552 */       setNumIndex();
/* 553 */       clearDetail();
/* 554 */       clearElements();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setNumIndex() {
/* 560 */     int i = 0;
/* 561 */     if (getListeDetail().size() > 0)
/*     */     {
/*     */       
/* 564 */       for (ParametrageJournalDetailC det : getListeDetail()) {
/*     */ 
/*     */         
/* 567 */         i++;
/* 568 */         det.setIndexNum(i);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearDetail() {
/* 576 */     this.selectedDetail = null;
/* 577 */     this.titreElement = "";
/* 578 */     this.codeElement = "";
/* 579 */     this.typeElmt = 0;
/* 580 */     this.libelleElement = "";
/* 581 */     this.typeElmt = 0;
/* 582 */     this.itemeId = 0;
/* 583 */     this.selected = false;
/* 584 */     this.index = 0;
/* 585 */     this.listDetElement.clear();
			  disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 590 */     this.selectedDetail = (ParametrageJournalDetailC)event.getObject();
/* 591 */     if (this.selectedDetail != null) {
/*     */       
/* 593 */       this.titreElement = this.selectedDetail.getTitrElement();
/* 594 */       this.typeElmt = this.selectedDetail.getTypeElement();
/* 595 */       this.selected = true;
/* 596 */       if (this.typeElmt > 0) {
/*     */         
/* 598 */         chargementElement();
/*     */         
/* 600 */         for (ParametrageJournalElementC elt : this.selectedDetail.getListDetailElement())
/*     */         {
/* 602 */           searchElement(elt);
/*     */         }
/*     */       } 
/*     */       
/* 606 */       this.index = getListeDetail().indexOf(this.selectedDetail);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 612 */     if (getId() == 0 && getListeDetail().size() > 0 && !this.droit.isCreer()) {
/*     */       
/* 614 */       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de cr�er le bulletin ");
/*     */       return;
/*     */     } 
/* 617 */     if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 619 */       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier le bulletin ");
/*     */       return;
/*     */     } 
              if(getCode()!=null && !getCode().equals(""))
/* 622 */     if (FichierBaseDAO.getInstance().insertUpdateParametrageJournal(this)) {
/*     */       
/* 624 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 625 */       clearDetail();
/* 626 */       initialize();
/*     */     } else {
/*     */       
/* 629 */       HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 635 */     setCode("");
/* 636 */     setId(0);
/* 637 */     setLibelle("");
/* 638 */     setTypeElmt(0);
/* 639 */     getListeDetail().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 644 */     if (getId() != 0) {

/* 648 */       FichierBaseDAO.getInstance().deleteParametrageJournal(this);
/* 649 */       clearDetail();
/* 650 */       initialize();
/*     */ 
/*     */     
/*     */     }
/*     */     else {
    
/* 657 */       HelperC.afficherDeleteMessage();
/*     */     } 
/*     */   }
/*     */ }


