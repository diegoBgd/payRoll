/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.BaremeIPRC;
/*     */ import classesPaie.Base;
import classesPaie.BulletinPrimeC;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.CotisationC;
/*     */ import classesPaie.DeductionC;
/*     */ import classesPaie.DetailBaremeC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.PrimeIndemniteC;
/*     */ import classesPaie.Tables;

/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;

/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class BaremeIPRB
/*     */   extends BaremeIPRC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5659574157386814683L;
/*     */   private int idPrime;
/*     */   private int idCotisation;
/*     */   private int idRetenu;
/*     */   private double borneDebut;
/*     */   private double borneFin;
/*     */   private double pourcentage;
/*     */   private double sommeRetranche;
/*     */   private String dateS;
/*     */   private String separator;
/*     */   private String borneDebutS;
/*     */   private String borneFinS;
/*     */   private String sommeRetrancheS;
/*     */   private String elementBase;
/*     */   private BaremeIPRC bareme;
/*  58 */   private int index = 1; 
			private DetailBaremeC detailBareme; 
			private OperateurC operateur; 
			private ExerciceC exercice; 
			private DroitC droit;
/*  59 */   private HttpSession session = HelperC.getSession(); 
			private List<SelectItem> listPrimesIndemnites; 
			private List<SelectItem> listCotisations; 
			private List<SelectItem> listRetenu;
/*     */   private List<BaremeIPRC> listBareme;
/*     */   private PrimeIndemniteC prime; 
			private CotisationC cotisation; 
			private DeductionC deduction; 
			private boolean disableMsg;
			Base userFonction;
			
/*     */   public CotisationC getCotisation() {
/*  63 */     return this.cotisation;
/*     */   }
/*     */  
/*     */   public void setCotisation(CotisationC cotisation) {
/*  67 */     this.cotisation = cotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeductionC getDeduction() {
/*  72 */     return this.deduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeduction(DeductionC deduction) {
/*  77 */     this.deduction = deduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdPrime() {
/*  82 */     return this.idPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdPrime(int idPrime) {
/*  87 */     this.idPrime = idPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdCotisation() {
/*  92 */     return this.idCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdCotisation(int idCotisation) {
/*  97 */     this.idCotisation = idCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdRetenu() {
/* 102 */     return this.idRetenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdRetenu(int idRetenu) {
/* 107 */     this.idRetenu = idRetenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getElementBase() {
/* 112 */     return this.elementBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setElementBase(String elementBase) {
/* 117 */     this.elementBase = elementBase;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListPrimesIndemnites() {
/* 121 */     return this.listPrimesIndemnites;
/*     */   }
/*     */   
/*     */   public void setListPrimesIndemnites(List<SelectItem> listPrimesIndemnites) {
/* 125 */     this.listPrimesIndemnites = listPrimesIndemnites;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListCotisations() {
/* 129 */     return this.listCotisations;
/*     */   }
/*     */   
/*     */   public void setListCotisations(List<SelectItem> listCotisations) {
/* 133 */     this.listCotisations = listCotisations;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListRetenu() {
/* 137 */     return this.listRetenu;
/*     */   }
/*     */   
/*     */   public void setListRetenu(List<SelectItem> listRetenu) {
/* 141 */     this.listRetenu = listRetenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateS() {
/* 146 */     return this.dateS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateS(String dateS) {
/* 151 */     this.dateS = dateS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSeparator() {
/* 156 */     return this.separator;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSeparator(String separator) {
/* 161 */     this.separator = separator;
/*     */   }
/*     */ 
/*     */   
/*     */   public BaremeIPRC getBareme() {
/* 166 */     return this.bareme;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBareme(BaremeIPRC bareme) {
/* 171 */     this.bareme = bareme;
/*     */   }
/*     */ 
/*     */   
/*     */   public DetailBaremeC getDetailBareme() {
/* 176 */     return this.detailBareme;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetailBareme(DetailBaremeC detailBareme) {
/* 181 */     this.detailBareme = detailBareme;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBorneDebut() {
/* 186 */     return this.borneDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorneDebut(double borneDebut) {
/* 191 */     this.borneDebut = borneDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBorneFin() {
/* 196 */     return this.borneFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorneFin(double borneFin) {
/* 201 */     this.borneFin = borneFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentage() {
/* 206 */     return this.pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentage(double pourcentage) {
/* 211 */     this.pourcentage = pourcentage;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSommeRetranche() {
/* 216 */     return this.sommeRetranche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSommeRetranche(double sommeRetranche) {
/* 221 */     this.sommeRetranche = sommeRetranche;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 226 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 231 */     this.index = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBorneDebutS() {
/* 236 */     return this.borneDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorneDebutS(String borneDebutS) {
/* 241 */     this.borneDebutS = borneDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBorneFinS() {
/* 246 */     return this.borneFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorneFinS(String borneFinS) {
/* 251 */     this.borneFinS = borneFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSommeRetrancheS() {
/* 256 */     return this.sommeRetrancheS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSommeRetrancheS(String sommeRetrancheS) {
/* 261 */     this.sommeRetrancheS = sommeRetrancheS;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/* 266 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 271 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 276 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 281 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 286 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 291 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public PrimeIndemniteC getPrime() {
/* 296 */     return this.prime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/* 301 */     this.prime = prime;
/*     */   }
/*     */   
/*     */   public List<BaremeIPRC> getListBareme() {
/* 305 */     return this.listBareme;
/*     */   }
/*     */   
/*     */   public void setListBareme(List<BaremeIPRC> listBareme) {
/* 309 */     this.listBareme = listBareme;
/*     */   }

			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/* 315 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 316 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 317 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 318 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 319 */     this.separator = " ";
/* 320 */     if (this.exercice == null || this.operateur == null) {
/*     */       
/*     */       try
/*     */       {
/* 324 */         FacesContext context = FacesContext.getCurrentInstance();
/* 325 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 327 */       catch (IOException e)
/*     */       {
/* 329 */         e.printStackTrace();
/*     */       }
/*     */     
/*     */     } else {
/*     */       disableMsg=true;
/* 334 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 335 */       if (this.userFonction != null)
/*     */       {
/* 337 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
/*     */       }
/* 339 */       setDate(Calendar.getInstance().getTime());
/* 340 */       this.dateS = HelperC.convertDate(getDate());
/*     */       setTypeEmploye(1);
/* 342 */       this.elementBase = "";
				chargerBaremeActif();
/*     */     } 
/*     */   }
/*     */ 
			public void changePrime(ValueChangeEvent event) {
/* 1510 */     setTypeEmploye(((Integer)event.getNewValue()).intValue());
			   chargerBaremeActif();
/* 1511 */     
/*      */   }
/*     */   private void setDetailBareme() {
/* 350 */     setListDetailBareme(FichierBaseDAO.getInstance().getListeDetailBareme(this.bareme));
/* 351 */     if (getListDetailBareme().size() > 0) {
/*     */       
/* 353 */       for (DetailBaremeC deta : getListDetailBareme())
/*     */       {
/*     */         
/* 356 */         deta.setIndex(getListDetailBareme().indexOf(deta) + 1);
/* 357 */         this.borneDebut = deta.getBorneFin() + 1.0D;
/* 358 */         this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
/* 359 */         this.index = getListDetailBareme().indexOf(deta) + 2;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 364 */       this.index = 1;
/* 365 */       this.borneDebut = 0.0D;
/* 366 */       this.borneDebutS = "0";
/* 367 */       this.borneFin = 0.0D;
/* 368 */       this.borneFinS = "";
/* 369 */       this.pourcentage = 0.0D;
/* 370 */       this.sommeRetranche = 0.0D;
/* 371 */       this.sommeRetrancheS = "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeNumero() {
/* 378 */     if (this.bareme != null) {
/*     */       bareme=FichierBaseDAO.getInstance().getBareme(getNumero());
/* 380 */       setObject();
/* 381 */       setDetailBareme();
/*     */     } else {
/*     */       
/* 384 */       clear(false);
/* 385 */       clearDetail();
/*     */     } 
/*     */   }

		  private void chargerBaremeActif(){
			  disableMsg=true;
			  bareme=FichierBaseDAO.getInstance().getBaremeIRE(getTypeEmploye());
			  if(bareme!=null){
				 
				  setObject();
				  setDetailBareme();
			  }
		  }
  
/*     */   public void changeDate() {
/* 391 */     if (this.dateS.replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 393 */       setDate(null);
/*     */     } else {
/*     */       
/* 396 */       setDate(HelperC.validerDate(this.dateS));
/* 397 */       if (getDate() == null) {
/*     */         
/* 399 */         this.dateS = "";
/* 400 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 403 */         this.dateS = HelperC.convertDate(getDate());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeBorneDebut() {
/*     */     try {
/* 412 */       this.borneDebut = Double.valueOf(this.borneDebutS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/*     */     }
/* 415 */     catch (Exception e) {
/*     */       
/* 417 */       this.borneDebut = 0.0D;
/*     */     } 
/* 419 */     this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
/* 420 */     this.borneDebut = Double.valueOf(this.borneDebutS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 422 */     this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
/* 423 */     this.borneDebut = Double.valueOf(this.borneDebutS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 425 */     this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
/* 426 */     this.borneDebut = Double.valueOf(this.borneDebutS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeBorneFin() {
/*     */     try {
/* 433 */       this.borneFin = Double.valueOf(this.borneFinS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/*     */     }
/* 436 */     catch (Exception e) {
/*     */       
/* 438 */       this.borneFin = 0.0D;
/*     */     } 
/* 440 */     this.borneFinS = HelperC.TraitementMontant.getMontantFormate(this.borneFin, 0);
/* 441 */     this.borneFin = Double.valueOf(this.borneFinS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 443 */     this.borneFinS = HelperC.TraitementMontant.getMontantFormate(this.borneFin, 0);
/* 444 */     this.borneFin = Double.valueOf(this.borneFinS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 446 */     this.borneFinS = HelperC.TraitementMontant.getMontantFormate(this.borneFin, 0);
/* 447 */     this.borneFin = Double.valueOf(this.borneFinS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeSommeRetranche() {
/*     */     try {
/* 454 */       this.sommeRetranche = Double.valueOf(this.sommeRetrancheS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/*     */     }
/* 457 */     catch (Exception e) {
/*     */       
/* 459 */       this.sommeRetranche = 0.0D;
/*     */     } 
/* 461 */     this.sommeRetrancheS = HelperC.TraitementMontant.getMontantFormate(this.sommeRetranche, 0);
/* 462 */     this.sommeRetranche = Double.valueOf(this.sommeRetrancheS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 464 */     this.sommeRetrancheS = HelperC.TraitementMontant.getMontantFormate(this.sommeRetranche, 0);
/* 465 */     this.sommeRetranche = Double.valueOf(this.sommeRetrancheS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */     
/* 467 */     this.sommeRetrancheS = HelperC.TraitementMontant.getMontantFormate(this.sommeRetranche, 0);
/* 468 */     this.sommeRetranche = Double.valueOf(this.sommeRetrancheS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void cloturerBaremeIRE() {
/* 473 */     chargerBareme();
/*     */   }
/*     */   
/*     */   public void chargement() {
/* 477 */     this.listBareme = FichierBaseDAO.getInstance().getListBareme(false);
/*     */   }
/*     */   
/*     */   public void NoncloturerBaremeIRE() {
/* 481 */     this.borneFin = 0.0D;
/* 482 */     this.borneFinS = "";
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerBareme() {
/* 487 */     double pource = 0.0D;
/* 488 */     double somme = 0.0D;
/* 489 */     double petitBorne = 0.0D;
/* 490 */     double grandBorne = 0.0D;
/*     */     
/* 492 */     for (DetailBaremeC det : getListDetailBareme()) {
/*     */ 
/*     */       
/* 495 */       if (this.detailBareme == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 499 */       if (det.getIndex() == this.detailBareme.getIndex()) {
/*     */         
/* 501 */         this.detailBareme = det;
/* 502 */         this.detailBareme.setExiste(true);
/*     */         break;
/*     */       } 
/* 505 */       pource = det.getPourcentage();
/* 506 */       somme = det.getSommeRetranche();
/* 507 */       petitBorne = det.getBorneDebut();
/* 508 */       grandBorne = det.getBorneFin();
/*     */     } 
/* 510 */     if ((this.pourcentage != 0.0D && pource > this.pourcentage) || (this.sommeRetranche != 0.0D && somme > this.sommeRetranche)) {
/*     */       
/* 512 */       HelperC.afficherMessage("Information", "le pourcentage ou la somme � retrancher doit �tre sup�rieur(ere)  � celui ou celle d�j� ins�r�(e) dans le tableau");
/*     */ 
/*     */     
/*     */     }
/* 516 */     else if (petitBorne > 0.0D && grandBorne == 1.0D) {
/*     */       
/* 518 */       HelperC.afficherMessage("Information", "Le bar�me est d�j� clotur�");
/*     */     } 
/* 520 */     if (this.detailBareme == null)
/*     */     {
/* 522 */       this.detailBareme = new DetailBaremeC();
/*     */     }
/* 524 */     this.detailBareme.setBorneDebut(this.borneDebut);
/* 525 */     this.detailBareme.setBorneDebutString(this.borneDebutS);
/* 526 */     this.detailBareme.setBorneFin(this.borneFin);
/* 527 */     this.detailBareme.setBorneFinString(this.borneFinS);
/* 528 */     this.detailBareme.setPourcentage(this.pourcentage);
/* 529 */     this.detailBareme.setSommeRetranche(this.sommeRetranche);
/* 530 */     this.detailBareme.setSommeRetrancheString(this.sommeRetrancheS);
/* 531 */     this.detailBareme.setIndex(this.index);
/* 532 */     clearDetailBareme();
/* 533 */     if (!this.detailBareme.isExiste())
/*     */     {
/* 535 */       getListDetailBareme().add(this.detailBareme);
/*     */     }
/* 537 */     this.detailBareme = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void charger() {
/* 542 */     if (this.borneFin == 0.0D && this.borneDebut == 0.0D) {
/*     */       
/* 544 */       HelperC.afficherMessage("Information", "Pr�cisez la borne Fin");
/*     */     }
/* 546 */     else if (this.borneFin < this.borneDebut && this.borneFin != 0.0D) {
/*     */       
/* 548 */       HelperC.afficherMessage("Information", "La borne D�but  ne peut pas �tre sup�rieure � la borne Fin ");
/*     */     }
/* 550 */     else if (this.pourcentage != 0.0D && this.sommeRetranche != 0.0D) {
/*     */       
/* 552 */       HelperC.afficherMessage("Information", "La somme � retrancher et le pourcentage ne peuvent pas �tre ajouter en m�me temps");
/*     */ 
/*     */     
/*     */     }
/* 556 */     else if (this.index > 1 && this.sommeRetranche == 0.0D && this.pourcentage == 0.0D) {
/*     */       
/* 558 */       HelperC.afficherMessage("Information", "le pourcentage ou la somme � retrancher doit �tre sup�rieur  � 0");
/*     */     }
/* 560 */     else if (this.borneDebut > 0.0D && this.borneFin == 0.0D) {
/*     */       
/* 562 */       PrimeFaces.current().executeScript("PF('confirmDialog').show();");
/*     */     }
/*     */     else {
/*     */       
/* 566 */       chargerBareme();

/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearDetailBareme() {
/* 572 */     this.borneDebut = 0.0D;
/* 573 */     this.borneDebutS = "";
/* 574 */     this.pourcentage = 0.0D;
/* 575 */     this.sommeRetranche = 0.0D;
/* 576 */     this.sommeRetrancheS = "";
/* 577 */     this.index = this.detailBareme.getIndex() + 1;
/* 578 */     this.borneDebut = this.detailBareme.getBorneFin() + 1.0D;
/* 579 */     this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
/* 580 */     this.borneFin = 0.0D;
/* 581 */     this.borneFinS = "";
/*     */   }
/*     */ 
/*     */   
/*     */   public void enlever() {
/* 586 */     if (this.detailBareme != null) {
/*     */       
/* 588 */       if (this.detailBareme.getId() > 0)
/*     */       {
/* 590 */         getListDetailBaremeDeleted().add(this.detailBareme);
/*     */       }
/* 592 */       getListDetailBareme().remove(this.detailBareme);
/* 593 */       clearDetail();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearDetail() {
/* 599 */     this.detailBareme = null;
/* 600 */     this.borneDebut = 0.0D;
/* 601 */     this.borneDebutS = "";
/* 602 */     this.borneFin = 0.0D;
/* 603 */     this.borneFinS = "";
/* 604 */     this.pourcentage = 0.0D;
/* 605 */     this.sommeRetranche = 0.0D;
/* 606 */     this.sommeRetrancheS = "";
/* 607 */     this.index = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
	 			disableMsg=true;
/* 612 */     if (this.bareme != null) {
/*     */        disableMsg=false;
/* 614 */       setId(this.bareme.getId());
/* 615 */       setNumero(this.bareme.getNumero());
/* 616 */       setDate(this.bareme.getDate());
/* 617 */       this.dateS = HelperC.convertDate(getDate());
/* 618 */       setPensionComplementaire(this.bareme.getPensionComplementaire());
/* 619 */       setFormule(this.bareme.getFormule());
/* 620 */       setTransportBrut(this.bareme.getTransportBrut());
/* 621 */       setMontantPersonneCharge(this.bareme.getMontantPersonneCharge());
/* 622 */       setLogementNonImposable(this.bareme.getLogementNonImposable());
/* 623 */       setPourcentagePersonneCharge(this.bareme.getPourcentagePersonneCharge());
/* 624 */       setDescription(this.bareme.getDescription());
/* 625 */       setActif(this.bareme.isActif());
/* 626 */       setTypeEmploye(this.bareme.getTypeEmploye());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void affecterDetail() {
/* 633 */     if (this.detailBareme != null) {
/*     */       
/* 635 */       this.borneDebut = this.detailBareme.getBorneDebut();
/* 636 */       this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
/* 637 */       this.borneFin = this.detailBareme.getBorneFin();
/* 638 */       this.borneFinS = HelperC.TraitementMontant.getMontantFormate(this.borneFin, 0);
/* 639 */       this.pourcentage = this.detailBareme.getPourcentage();
/* 640 */       this.sommeRetranche = this.detailBareme.getSommeRetranche();
/* 641 */       this.sommeRetrancheS = HelperC.TraitementMontant.getMontantFormate(this.sommeRetranche, 0);
/* 642 */       this.index = this.detailBareme.getIndex();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelectedDetail(SelectEvent event) {
/* 648 */     this.detailBareme = (DetailBaremeC)event.getObject();
/* 649 */     if (this.detailBareme != null)
/*     */     {
/* 651 */       affecterDetail();
/*     */     }
/*     */   }
/*     */   
/*     */   public void onRowSelect(SelectEvent event) {
/* 656 */     this.bareme = (BaremeIPRC)event.getObject();
/* 657 */     setObject();
/* 658 */     setDetailBareme();
/* 659 */     PrimeFaces.current().executeScript("PF('dlgBrm').hide();");
/*     */   }
/*     */   
/*     */   private void clear(boolean b) {
/* 663 */     if (b)
/*     */     {
/* 665 */       setNumero("");
/*     */     }
/* 667 */     this.bareme = null;
/* 668 */     setId(0);
/* 669 */     setDate(Calendar.getInstance().getTime());
/* 670 */     this.dateS = HelperC.convertDate(getDate());
/* 671 */     setTransportBrut(0.0D);
/* 672 */     setPensionComplementaire(0.0D);
/* 673 */     setPourcentagePersonneCharge(0.0D);
/* 674 */     setLogementNonImposable(0.0D);
/* 675 */     setType(1);
/* 676 */     setTypeEmploye(0);
/* 677 */     setFormule("");
/*     */      disableMsg=true;
/* 679 */     setDescription("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 684 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 689 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 691 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 693 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 695 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 697 */     else if (getType() == 0) {
/*     */       
/* 699 */       HelperC.afficherMessage("Information", "Pr�cisez le type de bar�me");
/*     */     }
/* 701 */     else if (getListDetailBareme() == null) {
/*     */       
/* 703 */       HelperC.afficherMessage("Information", "Pr�cisez les d�tails de Bar�me ");
/*     */     } 
/* 705 */     Historique hist = new Historique();
/* 706 */     hist.setDateOperation(Calendar.getInstance().getTime());
/* 707 */     hist.setOperateur(this.operateur);
/* 708 */     if (getId() == 0) {
/*     */       
/* 710 */       hist.setOperation("Cr�ation du bar�me " + getNumero());
/*     */     } else {
/*     */      
/* 713 */       hist.setOperation("Modification du bar�me " + getNumero());
/*     */     } 
/* 715 */     hist.setTable(Tables.getTableName(Tables.TableName.bareme));
/* 716 */     setHistorique(hist);
/*     */     
				if(isActif())
				{
					BaremeIPRC brm= bareme=FichierBaseDAO.getInstance().getBaremeIRE(getTypeEmploye());
					if(getId()!=brm.getId())
					{
						HelperC.afficherAttention("Attetion", "Un autre bar�me active pour ce type d'employ� existe !");
						return;
					}
				}
/* 718 */     if (FichierBaseDAO.getInstance().insertUpdateBareme(this)) {
/*     */       
/* 720 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
				chargerBaremeActif();
/*     */     } else {
/*     */       
/* 723 */       HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 729 */     if (getId() == 0) {
/*     */       
/* 731 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 733 */     else if (this.droit.isSupprimer()) {
/*     */       
/* 735 */       if (FichierBaseDAO.getInstance().deleteBareme(this.bareme)) {
/*     */         
/* 737 */         clear(true);
/* 738 */         HelperC.afficherMessage("Information", "Succes de l'op�ration");
/* 739 */         setListDetailBareme(null);
/*     */       } else {
/*     */         
/* 742 */         HelperC.afficherMessage("D�sol�", "Echec de suppression ");
/*     */       } 
/*     */     } else {
/*     */       
/* 746 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     } 
/*     */   }
/*     */ }

