/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.AvanceDetailC;
/*     */ import classesPaie.AvanceQuinzaineC;
/*     */ import classesPaie.BanqueC;
/*     */ import classesPaie.Base;
import classesPaie.BulletinPaieC;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DetailBanqueEmployeC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.TypeCreditC;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class AvanceQuinzaineB
/*     */   extends AvanceQuinzaineC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7301676475596039486L;
/*     */   private int index;
/*     */   private int idEpmloye;
/*     */   private int mois;
/*     */   private int typeReglrment;
/*     */   private String dateS;
/*     */   private String codeEmploye;
/*     */   private String nomEmploye;
/*     */   private String codeEmployeRecherche;
/*     */   private String nomEmployeRecherche;
/*     */   private String prenomEmployeRecherche;
/*     */   private String codeBanque;
/*     */   private String nomBanque;
/*     */   private String codeBankRech;
/*     */   private String nomBankRech;
/*     */   private String printMois;
/*  58 */   double total = 0.0D; 
			private AvanceQuinzaineC avanceQuinzaine; 
			private List<AvanceQuinzaineC> listAvance; 
			private List<AvanceDetailC> listDet; 
			private EmployeC employe; 
			private List<EmployeC> employes; 
			private TypeCreditC typeCredit; 
			private List<BanqueC> listBank; 
			private BanqueC banque; 
			private OperateurC operateur; 
			private ExerciceC exercice; 
			private HttpSession session; 
			private Date dateDeb; 
			private Date dateFin; 
			private DroitC droit; 
			private AvanceDetailC selectedDetail;
			private boolean disableMsg;
			private double salaireNet;
			Base userFonction; 
			int month;
			BulletinPaieC bulletin;
/*     */   
/*     */   public AvanceQuinzaineB() {
/*  61 */     this.session = HelperC.getSession();
/*     */   }
/*     */ 
/*     */   
/*     */   public DroitC getDroit() {
/*  66 */     return this.droit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDroit(DroitC droit) {
/*  71 */     this.droit = droit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdEpmloye() {
/*  76 */     return this.idEpmloye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEpmloye(int idEpmloye) {
/*  81 */     this.idEpmloye = idEpmloye;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMois() {
/*  86 */     return this.mois;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMois(int mois) {
/*  91 */     this.mois = mois;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDeb() {
/*  96 */     return this.dateDeb;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDeb(Date dateDeb) {
/* 101 */     this.dateDeb = dateDeb;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/* 106 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/* 111 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCodeBankRech() {
/* 117 */     return this.codeBankRech;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeBankRech(String codeBankRech) {
/* 122 */     this.codeBankRech = codeBankRech;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomBankRech() {
/* 127 */     return this.nomBankRech;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomBankRech(String nomBankRech) {
/* 132 */     this.nomBankRech = nomBankRech;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BanqueC> getListBank() {
/* 137 */     return this.listBank;
/*     */   }
/*     */   
/*     */   public void setListBank(List<BanqueC> listBank) {
/* 141 */     this.listBank = listBank;
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
/*     */   public String getCodeEmploye() {
/* 156 */     return this.codeEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeEmploye(String codeEmploye) {
/* 161 */     this.codeEmploye = codeEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmploye() {
/* 166 */     return this.nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmploye(String nomEmploye) {
/* 171 */     this.nomEmploye = nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public AvanceQuinzaineC getAvanceQuinzaine() {
/* 176 */     return this.avanceQuinzaine;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAvanceQuinzaine(AvanceQuinzaineC avanceQuinzaine) {
/* 181 */     this.avanceQuinzaine = avanceQuinzaine;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/* 186 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 191 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 196 */     return this.codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 201 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 206 */     return this.nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 211 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 216 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 221 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeBanque() {
/* 226 */     return this.codeBanque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeBanque(String codeBanque) {
/* 231 */     this.codeBanque = codeBanque;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomBanque() {
/* 236 */     return this.nomBanque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomBanque(String nomBanque) {
/* 241 */     this.nomBanque = nomBanque;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeCreditC getTypeCredit() {
/* 246 */     return this.typeCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeCredit(TypeCreditC typeCredit) {
/* 251 */     this.typeCredit = typeCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public BanqueC getBanque() {
/* 256 */     return this.banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBanque(BanqueC banque) {
/* 261 */     this.banque = banque;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 266 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 271 */     this.index = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/* 276 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 281 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 286 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 291 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public List<AvanceQuinzaineC> getListAvance() {
/* 295 */     return this.listAvance;
/*     */   }
/*     */   
/*     */   public void setListAvance(List<AvanceQuinzaineC> listAvance) {
/* 299 */     this.listAvance = listAvance;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getEmployes() {
/* 303 */     return this.employes;
/*     */   }
/*     */   
/*     */   public void setEmployes(List<EmployeC> employes) {
/* 307 */     this.employes = employes;
/*     */   }
/*     */   
/*     */   public int getTypeReglrment() {
/* 311 */     return this.typeReglrment;
/*     */   }
/*     */   
/*     */   public void setTypeReglrment(int typeReglrment) {
/* 315 */     this.typeReglrment = typeReglrment;
/*     */   }
/*     */   
/*     */   public String getPrintMois() {
/* 319 */     return this.printMois;
/*     */   }
/*     */   
/*     */   public void setPrintMois(String printMois) {
/* 323 */     this.printMois = printMois;
/*     */   }
/*     */   
/*     */   public List<AvanceDetailC> getListDet() {
/* 327 */     return this.listDet;
/*     */   }
/*     */   
/*     */   public void setListDet(List<AvanceDetailC> listDet) {
/* 331 */     this.listDet = listDet;
/*     */   }
/*     */   
/*     */   public AvanceDetailC getSelectedDetail() {
/* 335 */     return this.selectedDetail;
/*     */   }
/*     */   
/*     */   public void setSelectedDetail(AvanceDetailC selectedDetail) {
/* 339 */     this.selectedDetail = selectedDetail;
/*     */   }

			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   
			public double getSalaireNet() {
				return salaireNet;
			}
			public void setSalaireNet(double salaireNet) {
				this.salaireNet = salaireNet;
			}
			
/*     */   @PostConstruct
/*     */   private void init() {
/* 345 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 346 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 347 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 348 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 349 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 353 */         FacesContext context = FacesContext.getCurrentInstance();
/* 354 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 356 */       catch (IOException e) {
/*     */         
/* 358 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/* 362 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 363 */       if (this.userFonction != null)
/*     */       {
/* 365 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.creditAvance);
/*     */       }
/* 367 */       setDate(Calendar.getInstance().getTime());
/* 368 */       this.dateS = HelperC.convertDate(getDate());
/* 369 */       this.codeBankRech = "";
/* 370 */       this.nomBankRech = "";
/* 371 */       this.listAvance = new ArrayList<AvanceQuinzaineC>();
/* 372 */       this.month = HelperC.getMonthFromDate(getDate());
/* 373 */       chargerDetail();
/*     */     } 
/*     */   }

/*     */ 
/*     */   
/*     */   private void chargerDetail() {
/* 379 */     this.listDet = new ArrayList<AvanceDetailC>();
/*     */ 
/*     */     
/* 382 */     for (int i = this.month; i <= 12; i++) {
/* 383 */       this.selectedDetail = new AvanceDetailC();
/* 384 */       this.selectedDetail.setMois(i);
/* 385 */       this.selectedDetail.setMontant(0.0D);
/* 386 */       setDetailValue(this.selectedDetail);
/* 387 */       this.listDet.add(this.selectedDetail);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDetailValue(AvanceDetailC det) {
/* 393 */     if (getListDetail().size() > 0)
/* 394 */       for (AvanceDetailC avDet : getListDetail()) {
/*     */         
/* 396 */         if (avDet.getMois() == det.getMois()) {
/*     */           
/* 398 */           det.setId(avDet.getId());
/* 399 */           det.setSeleceted(true);
/* 400 */           det.setDisable(false);
/* 401 */           det.setMontant(avDet.getMontant());
/*     */           break;
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   public void changeDate() {
/* 408 */     if (this.dateS.replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 410 */       setDate(null);
/*     */     } else {
/*     */       
/* 413 */       setDate(HelperC.validerDate(this.dateS));
/* 414 */       if (getDate() == null) {
/*     */         
/* 416 */         this.dateS = "";
/*     */       } else {
/*     */         
/* 419 */         this.dateS = HelperC.convertDate(getDate());
/* 420 */         this.month = HelperC.getMonthFromDate(getDate());
/* 421 */         this.printMois = HelperC.moisEnLettres(this.month);
/* 422 */         setMoisConcerne(this.month);
/* 423 */         if (this.employe != null) {
/*     */           
/* 425 */           this.avanceQuinzaine = FactoryDAO.getInstance().getAvanceQuinzaine(getDate(), this.employe.getId(), this.exercice.getId());
/* 426 */           if (this.avanceQuinzaine != null) {
/* 427 */             takeSelectedAvance();
/*     */           } else {
/* 429 */             
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } public void changeModereglement(ValueChangeEvent event) {
/* 435 */     this.typeReglrment = ((Integer)event.getNewValue()).intValue();
/* 436 */     setModePaiement(this.typeReglrment);
/* 437 */     searchBank();
/*     */   }
/*     */   
/*     */   public void chargerAvance() {
/* 441 */     this.listAvance.clear();
/* 442 */     this.listAvance = FactoryDAO.getInstance().getAllAvanceQuinzaine(this.dateDeb, this.dateFin, 0, this.mois);
/* 443 */     if (this.listAvance != null && this.listAvance.size() > 0) {
/* 444 */       for (AvanceQuinzaineC av : this.listAvance) {
/*     */         
/* 446 */         av.setDateString(HelperC.changeDateFormate(av.getDate()));
/* 447 */         av.setNomEmploye(av.getEmploye().getNomPrenom());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowAvanceSelect(SelectEvent event) {
/* 454 */     this.avanceQuinzaine = (AvanceQuinzaineC)event.getObject();
/* 455 */     if (this.avanceQuinzaine != null) {
/*     */       
/* 457 */       takeSelectedAvance();
/* 458 */       PrimeFaces.current().executeScript("PF('dlgAvnc').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void takeSelectedAvance() {
/* 464 */     setId(this.avanceQuinzaine.getId());
/* 465 */     setDate(this.avanceQuinzaine.getDate());
/* 466 */     setModePaiement(this.avanceQuinzaine.getModePaiement());
/* 467 */     setMontant(this.avanceQuinzaine.getMontant());
/* 468 */     setNumeroCompte(this.avanceQuinzaine.getNumeroCompte());
/* 469 */     setModePaiement(this.avanceQuinzaine.getModePaiement());
/*     */      disableMsg=false;
/* 471 */     this.typeReglrment = this.avanceQuinzaine.getModePaiement();
/* 472 */     this.month = this.avanceQuinzaine.getMoisConcerne();
/* 473 */     this.printMois = HelperC.moisEnLettres(this.month);
/* 474 */     setMoisConcerne(this.month);
/* 475 */     this.dateS = HelperC.changeDateFormate(getDate());
/* 476 */     setListDetail(this.avanceQuinzaine.getListDetail());
/* 477 */     if (this.avanceQuinzaine.getEmploye() != null) {
/*     */       
/* 479 */       this.employe = this.avanceQuinzaine.getEmploye();
/* 480 */       setObjectEmploye();
/* 481 */       searchBank();
/*     */     } else {
/*     */       
/* 484 */       clearEmploye();
/*     */     } 
/* 486 */     if (this.avanceQuinzaine.getBanque() != null) {
/*     */       
/* 488 */       this.banque = this.avanceQuinzaine.getBanque();
/* 489 */       if (this.banque != null) {
/*     */         
/* 491 */         this.nomBanque = this.banque.getDesignation();
/* 492 */         setBanque(this.banque);
/*     */       } else {
/*     */         
/* 495 */         this.nomBanque = "";
/*     */       } 
/*     */     } else {
/*     */       
/* 499 */       this.codeBanque = "";
/* 500 */       this.nomBanque = "";
/*     */     } 
/* 502 */     chargerDetail();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObjectEmploye() {
/* 507 */     if (this.employe != null) {
/*     */       
/* 509 */       this.codeEmploye = this.employe.getCode();
/* 510 */       this.nomEmploye = this.employe.getNomPrenom();
/* 511 */       this.idEpmloye = this.employe.getId();
/* 512 */       setEmploye(this.employe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRowSelectEmploye(SelectEvent event) {
/* 519 */     this.employe = (EmployeC)event.getObject();
/* 520 */     if (this.employe != null) {
/*     */       
/* 522 */       setObjectEmploye();
/* 523 */       PrimeFaces.current().executeScript("PF('dlgEmploye').hide();");
/*     */     }
/*     */     else {
/*     */       
/* 527 */       clearEmploye();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargerEmploye() {
/* 533 */     this.employes = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,false);
/* 534 */     HelperC.afficherMessage("Information", String.valueOf(this.employes.size()) + " �l�ment(s) trouv�(s) ");
/*     */   }
/*     */ 
/*     */   
/*     */   public void searchEmployee() {
/* 539 */     if (!this.codeEmploye.equals("")) {
/*     */       
/* 541 */       this.employe = FactoryDAO.getInstance().getEmploye(this.codeEmploye, false);
/* 542 */       if (this.employe != null) {
/*     */         
/* 544 */         setObjectEmploye();
/* 545 */         if (getDate() != null) {
/*     */           
/* 547 */           this.avanceQuinzaine = FactoryDAO.getInstance().getAvanceQuinzaine(getDate(), this.employe.getId(), this.exercice.getId());
/* 548 */           if (this.avanceQuinzaine != null) {
/* 549 */             takeSelectedAvance();
/*     */           } else {
/* 551 */            
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 556 */         clearEmploye();
/*     */       } 
/*     */     } else {
/*     */       
/* 560 */       clearEmploye();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargerBanque() {
/* 566 */     this.listBank = FichierBaseDAO.getInstance().getBanque(this.codeBankRech, this.nomBankRech);
/*     */   }
/*     */ 
/*     */   
/*     */   private void searchBank() {
/* 571 */     DetailBanqueEmployeC detail = null;
/*     */     
/* 573 */     if (this.employe != null) {
/* 574 */       if (this.typeReglrment == 4) {
/* 575 */         detail = FactoryDAO.getInstance().getBanquePrincipal(
/* 576 */             this.employe.getId());
/*     */         
/* 578 */         if (detail != null) {
/* 579 */           this.banque = detail.getBanque();
/* 580 */           this.nomBanque = this.banque.getDesignation();
/* 581 */           setNumeroCompte(detail.getNumeroCompte());
/* 582 */           setBanque(this.banque);
/*     */         } else {
/* 584 */           this.codeBanque = "";
/* 585 */           this.nomBanque = "";
/* 586 */           setNumeroCompte("");
/*     */         } 
/*     */       } else {
/* 589 */         this.codeBanque = "";
/* 590 */         this.nomBanque = "";
/*     */       } 
/*     */     } else {
/*     */       
/* 594 */       this.banque = null;
/* 595 */       this.nomBanque = "";
/* 596 */       setNumeroCompte("");
/* 597 */       setBanque(this.banque);
/*     */     } 
/*     */   }
/*     */ 

	public void chargerPaie() {
		if (employe != null && mois > 0) {
			
			bulletin = FactoryDAO.getInstance().getPaieMois(idEpmloye, mois, exercice.getId());
			if (bulletin != null) {
				salaireNet = bulletin.getTotalNetPay();
				setMontant(Math.round(salaireNet / 2));
				setIdPaie(bulletin.getId());
				
				if (listDet.size() > 0)
					for (AvanceDetailC avDet : listDet) {

						if (avDet.getMois() == mois) {

							avDet.setSeleceted(true);
							avDet.setDisable(true);
							avDet.setMontant(getMontant());
							break;
						}
					}
			}
		}
	}
			
/*     */   
/*     */   public void enregistrer() {
/* 603 */     if (getDate() == null) {
/*     */       
/* 605 */       HelperC.afficherMessage("Information", "Pr�cisez la date de l'avance ");
/*     */     } else {
/*     */       
/* 608 */       setOperateur(this.operateur);
/* 609 */       setExercice(this.exercice);
/* 610 */       setMoisConcerne(this.month);
/* 611 */       addDetail();
/* 612 */       removeDetail();
/*     */       
/* 614 */       if (getId() == 0 && !this.droit.isCreer()) {
/*     */         
/* 616 */         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de cr�er ");
/*     */         return;
/*     */       } 
/* 619 */       if (getId() > 0 && !this.droit.isModifier()) {
/*     */         
/* 621 */         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier ");
/*     */         return;
/*     */       } 
/* 624 */       if (this.total > getMontant() || this.total < getMontant()) {
/*     */         
/* 626 */         HelperC.afficherMessage("ATTENTION", "Le montant total � rembourser doit �tre �gal au montant de l'avance ");
/*     */         return;
/*     */       } 
/* 629 */       if (FactoryDAO.getInstance().insertUpdateAvanceQuinzaine(this)) {
/*     */         
/* 631 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 632 */         initialiser();
/*     */       } else {
/*     */         
/* 635 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addDetail() {
/* 642 */     this.total = 0.0D;
/* 643 */     getListDetail().clear();
/* 644 */     if (this.listDet.size() > 0)
/*     */     {
/* 646 */       for (AvanceDetailC det : this.listDet) {
/* 647 */         if (det.isSeleceted() && det.getMontant() > 0.0D) {
/*     */           
/* 649 */           getListDetail().add(det);
/* 650 */           this.total += det.getMontant();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void removeDetail() {
/* 657 */     for (AvanceDetailC det : this.listDet) {
/* 658 */       if (det.getId() > 0 && !det.isSeleceted())
/* 659 */         getListDeleted().add(det); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void supprimer() {
/* 664 */     if (getId() == 0) {
/*     */       
/* 666 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 669 */       if (!this.droit.isSupprimer()) {
/*     */         
/* 671 */         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
/*     */         return;
/*     */       } 
/* 674 */       if (FactoryDAO.getInstance().deleteAvanceQuinzaine(this.avanceQuinzaine)) {
/*     */         
/* 676 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 677 */         initialiser();
/*     */       } else {
/*     */         
/* 680 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearEmploye() {
/* 687 */     this.employe = null;
/* 688 */     this.banque = null;
/* 689 */     this.codeEmploye = "";
/* 690 */     this.nomEmploye = "";
/* 691 */     this.codeBanque = "";
/* 692 */     this.nomBanque = "";
/* 693 */     this.idEpmloye = 0;
/* 694 */     setMontant(0.0D);
/* 695 */     setNumeroCompte("");
/* 696 */     setMontantString("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 701 */     this.codeEmploye = "";
/* 702 */     this.nomEmploye = "";
/* 703 */     this.codeBanque = "";
/* 704 */     this.nomBanque = "";
/* 705 */     this.printMois = "";
				bulletin=null;
				salaireNet=0;
				setIdPaie(0);
/*     */      disableMsg=true;
/* 707 */     setDate(null);
/* 708 */     setId(0);
/* 709 */     setBanque((BanqueC)null);
/* 710 */     setNumeroCompte("");
/* 711 */     setMontant(0.0D);
/* 712 */     setMontantString("");
/* 713 */     setModePaiement(1);
/* 714 */     getListDetail().clear();
/* 715 */     getListDeleted().clear();
/* 716 */     chargerDetail();
/*     */   }
/*     */ }

