/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.DetailCritereEvaluationC;
/*     */ import classesPaie.DetailOrganeC;
/*     */ import classesPaie.DeuxiemeEvaluationEmployeC;
/*     */ import classesPaie.DeuxiemeEvaluationEmployeDetailCritereC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.EvaluationEmployeC;
/*     */ import classesPaie.EvaluationEmployeDetailCritereC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import classesPaie.TypeNotationC;

/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
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

/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class DeuxiemeEvaluationEmployeB
/*     */   extends DeuxiemeEvaluationEmployeC
/*     */ {
/*     */   private static final long serialVersionUID = 7585413436307591895L;
/*     */   private int idTypeNotation;
/*     */   private int idAppreciation;
/*     */   private int anneeValidite;
/*  58 */   private List<DetailCritereEvaluationC> allDetailCritere = new ArrayList<DetailCritereEvaluationC>();
/*     */   
/*  60 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
/*  61 */   private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>(); 
		    private DeuxiemeEvaluationEmployeC selected; 
		    private EvaluationEmployeC selectedEvaluationEmploye; 
		    private EmployeC selection; private String code;
/*  62 */   private List<SelectItem> listAppreciationAvancement = new ArrayList<SelectItem>(); 
			private String codeEmployeRecherche; private String nomEmployeRecherche; 
			private String prenomEmployeRecherche;
/*  63 */   private HttpSession session = HelperC.getSession(); 
			private String noteCritereS; private String nomEmploye; 
			private String nomOrgane;private int index; 
			private DeuxiemeEvaluationEmployeDetailCritereC selectedDetail; 
			private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   DetailOrganeC organeDetail;
/*     */   private String personel,categorie,grade,niveauFrm,fonction;
/*     */   public String getFonction() {
/*  68 */     return this.fonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFonction(String fonction) {
/*  73 */     this.fonction = fonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomOrgane() {
/*  78 */     return this.nomOrgane;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomOrgane(String nomOrgane) {
/*  83 */     this.nomOrgane = nomOrgane;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmploye() {
/*  88 */     return this.nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmploye(String nomEmploye) {
/*  93 */     this.nomEmploye = nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdAppreciation() {
/*  98 */     return this.idAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdAppreciation(int idAppreciation) {
/* 103 */     this.idAppreciation = idAppreciation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DeuxiemeEvaluationEmployeC getSelected() {
/* 109 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(DeuxiemeEvaluationEmployeC selected) {
/* 114 */     this.selected = selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 119 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 124 */     this.index = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeuxiemeEvaluationEmployeDetailCritereC getSelectedDetail() {
/* 129 */     return this.selectedDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedDetail(DeuxiemeEvaluationEmployeDetailCritereC selectedDetail) {
/* 134 */     this.selectedDetail = selectedDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/* 139 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 144 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 149 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 154 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 159 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 164 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnneeValidite() {
/* 169 */     return this.anneeValidite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnneeValidite(int anneeValidite) {
/* 174 */     this.anneeValidite = anneeValidite;
/*     */   }
/*     */ 
/*     */   
/*     */   public EvaluationEmployeC getSelectedEvaluationEmploye() {
/* 179 */     return this.selectedEvaluationEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedEvaluationEmploye(EvaluationEmployeC selectedEvaluationEmploye) {
/* 184 */     this.selectedEvaluationEmploye = selectedEvaluationEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getSelection() {
/* 189 */     return this.selection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/* 194 */     this.selection = selection;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 199 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/* 204 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 209 */     return this.codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 214 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 219 */     return this.nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 224 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 229 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 234 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteCritereS() {
/* 239 */     return this.noteCritereS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteCritereS(String noteCritereS) {
/* 244 */     this.noteCritereS = noteCritereS;
/*     */   }
/*     */   
/*     */   public List<DetailCritereEvaluationC> getAllDetailCritere() {
/* 248 */     return this.allDetailCritere;
/*     */   }
/*     */   
/*     */   public void setAllDetailCritere(List<DetailCritereEvaluationC> allDetailCritere) {
/* 252 */     this.allDetailCritere = allDetailCritere;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/* 256 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/* 260 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListTypeNotation() {
/* 264 */     return this.listTypeNotation;
/*     */   }
/*     */   
/*     */   public void setListTypeNotation(List<SelectItem> listTypeNotation) {
/* 268 */     this.listTypeNotation = listTypeNotation;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListAppreciationAvancement() {
/* 272 */     return this.listAppreciationAvancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListAppreciationAvancement(List<SelectItem> listAppreciationAvancement) {
/* 277 */     this.listAppreciationAvancement = listAppreciationAvancement;
/*     */   }



public String getPersonel() {
	return personel;
}
public void setPersonel(String personel) {
	this.personel = personel;
}
public String getCategorie() {
	return categorie;
}
public void setCategorie(String categorie) {
	this.categorie = categorie;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getNiveauFrm() {
	return niveauFrm;
}
public void setNiveauFrm(String niveauFrm) {
	this.niveauFrm = niveauFrm;
}

/*     */   
/*     */   @PostConstruct
/*     */   private void charger() {
/* 283 */     this.operateur = new OperateurC();
/* 284 */     this.exercice = new ExerciceC();
/* 285 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 286 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 287 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 288 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 289 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 293 */         FacesContext context = FacesContext.getCurrentInstance();
/* 294 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 296 */       catch (IOException e) {
/*     */         
/* 298 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 302 */       findCritereEvaluation();
/*     */       
/* 304 */       for (TypeNotationC type : FichierBaseDAO.getInstance().getAllTypeNotation())
/*     */       {
/* 306 */         this.listTypeNotation.add(new SelectItem(Integer.valueOf(type.getId()), type.getDesignation()));
/*     */       }
/*     */ 
/*     */       
/* 310 */       for (Base typeAppreciation : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAppreciationAvancement)))
/*     */       {
/* 312 */         this.listAppreciationAvancement.add(new SelectItem(Integer.valueOf(typeAppreciation.getId()), typeAppreciation.getDesignation()));
/*     */       }
/*     */       
/* 315 */       setAnneeValidite(HelperC.getYearFromDate(new Date()));
/* 316 */       setDateEvaluation(new Date());
/* 317 */       setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void findByCode() {
/* 323 */     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
/* 324 */     if (this.selection != null) {
/*     */       
/* 326 */       setCode(this.selection.getCode());
/* 327 */       this.nomEmploye = this.selection.getNomPrenom();
/* 328 */       completeEmploye();

/* 337 */       if (getAnneeValidite() != 0)
/*     */       {
/* 339 */         changeEvaluation();
/*     */       }
/*     */     } else {
/*     */       
/* 343 */       clear1(true);
/*     */     } 
/*     */   }

	private void completeEmploye(){
	if(selection!=null)
	{
		Base fx = FichierBaseDAO.getInstance().getBaseById(selection.getIdFnctn(), Tables.getTableName(Tables.TableName.fonction));
		Base nv=FichierBaseDAO.getInstance().getBaseById(selection.getIdNvFormt(), Tables.getTableName(Tables.TableName.niveauFormation));
		Base catg=FichierBaseDAO.getInstance().getBaseById(selection.getIdCatgrie(), Tables.getTableName(Tables.TableName.categoriePersonnel));
		Base pers=FichierBaseDAO.getInstance().getBaseById(selection.getIdPersnl(), Tables.getTableName(Tables.TableName.personnel));
		Base grd=FichierBaseDAO.getInstance().getBaseById(selection.getIdGrd(), Tables.getTableName(Tables.TableName.gradePersonnel));
		if(fx!=null)
			fonction=fx.getDesignation();
		else
			fonction="";
		
		if(nv!=null)
			niveauFrm=nv.getDesignation();
		else
			niveauFrm="";
		
		if(catg!=null)
			categorie=catg.getDesignation();
		else
			categorie="";
		if(pers!=null)
			personel=pers.getDesignation();
		else
			personel="";
		if(grd!=null)
			grade=grd.getDesignation();
		else
			grade="";
	}
}

/*     */   private void setObject() {
/* 349 */     if (this.selected != null) {
/*     */       
/* 351 */       setId(this.selected.getId());
/* 352 */       setPremiereEvaluation(this.selected.getPremiereEvaluation());
/* 353 */       setPourcentage(this.selected.getPourcentage());
/* 354 */       setPourcentageS(this.selected.getPourcentageS());
/* 355 */       setDateEvaluation(this.selected.getDateEvaluation());
/* 356 */       setDateEvaluationS(this.selected.getDateEvaluationS());
/* 357 */       setNoteEvaluation(this.selected.getNoteEvaluation());
/* 358 */       setNoteEvaluationS(this.selected.getNoteEvaluationS());
/* 359 */       setNoteGenerale(this.selected.getNoteGenerale());
/* 360 */       setNoteGeneraleS(this.selected.getNoteGeneraleS());
/* 361 */       setTypeAppreciation(this.selected.getTypeAppreciation());
/* 362 */       setTypeNotation(this.selected.getTypeNotation());
/* 363 */       setPourcentage(this.selected.getPourcentage());
/* 364 */       setPourcentageS(this.selected.getPourcentageS());
/* 365 */       setNoteGenerale(this.selected.getNoteGenerale());
/* 366 */       setNoteGeneraleS(this.selected.getNoteGeneraleS());
/* 367 */       if (getTypeNotation() != null) {
/*     */         
/* 369 */         this.idTypeNotation = getTypeNotation().getId();
/* 370 */         setIdTypeNotation(this.idTypeNotation);
/*     */       } 
/* 372 */       if (getTypeAppreciation() != null)
/*     */       {
/* 374 */         this.idAppreciation = getTypeAppreciation().getId();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject1() {
/* 381 */     if (this.selection != null) {
/*     */       
/* 383 */       setCode(this.selection.getCode());
/* 384 */       changeEvaluation();
/*     */     } else {
/*     */       
/* 387 */       setCode("");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 393 */     if (b)
/*     */     {
/* 395 */       setId(0);
/*     */     }
/* 397 */     setAnneeValidite(HelperC.getYearFromDate(new Date()) + 1);
/* 398 */     setPourcentage(0.0D);
/* 399 */     setPourcentageS("");
/* 400 */     setDateEvaluation(new Date());
/* 401 */     setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
/* 402 */     setNoteEvaluation(0.0D);
/* 403 */     setNoteEvaluationS("");
/* 404 */     setNoteGenerale(0.0D);
/* 405 */     setNoteGeneraleS("");
/* 406 */     setTypeNotation(null);
/* 407 */     setJustificationDiscordance("");
/* 408 */     setPourcentage(0.0D);
/* 409 */     setPourcentageS("");
/* 410 */     setNoteGenerale(0.0D);
/* 411 */     setNoteGeneraleS("");
/* 412 */     setIdTypeNotation(0);
/* 413 */     this.idTypeNotation = 0;
/* 414 */     setTypeAppreciation(null);
/* 415 */     this.idAppreciation = 0;
/* 416 */     this.selected = null;
/* 417 */     this.code = "";
/* 418 */     getListDetailDeuxiemeEvaluation().clear();
/* 419 */     findCritereEvaluation();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowselected1(SelectEvent event) {
/* 424 */     this.selection = (EmployeC)event.getObject();
/* 425 */     setObject1();
/* 426 */     changeEvaluation();
/* 427 */     PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clear1(boolean b) {
/* 433 */     if (b)
/*     */     {
/* 435 */       this.selection = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargerEmploye() {
/* 441 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeNotation(ValueChangeEvent event) {
/* 446 */     this.idTypeNotation = ((Integer)event.getNewValue()).intValue();
/* 447 */     if (this.idTypeNotation != 0)
/*     */     {
/* 449 */       setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(this.idTypeNotation));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeAppreciation(ValueChangeEvent event) {
/* 455 */     this.idAppreciation = ((Integer)event.getNewValue()).intValue();
/* 456 */     if (this.idAppreciation != 0)
/*     */     {
/* 458 */       setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(this.idAppreciation, Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeAnneeValidite(ValueChangeEvent event) {
/* 464 */     setAnneeValidite(((Integer)event.getNewValue()).intValue());
/* 465 */     if (getAnneeValidite() != 0)
/*     */     {
/* 467 */       changeEvaluation();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeEvaluation() {
/* 473 */     if (this.selection != null && getAnneeValidite() != 0) {
/*     */       
/* 475 */       this.selectedEvaluationEmploye = FactoryDAO.getInstance().getEvaluationEmploye(this.selection, getAnneeValidite());
/* 476 */       if (this.selectedEvaluationEmploye != null) {
/*     */         
/* 478 */         setPremiereEvaluation(this.selectedEvaluationEmploye);
/* 479 */         findPremiereEvaluation();
/* 480 */         this.selected = FactoryDAO.getInstance().getDeuxiemeEvaluationEmploye(getPremiereEvaluation());
/* 481 */         if (this.selected != null) {
/*     */           
/* 483 */           setObject();
/* 484 */           findDeuxiemeEvaluationEmploye(this.selected);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void findPremiereEvaluation() {
/* 492 */     int i = 0;
/* 493 */     DeuxiemeEvaluationEmployeDetailCritereC detail = null;
/* 494 */     getListDetailDeuxiemeEvaluation().clear();
/* 495 */     for (EvaluationEmployeDetailCritereC detailCritere : FactoryDAO.getInstance().getListeEvaluationEmployeDetailCritere(getPremiereEvaluation())) {
/*     */       
/* 497 */       i++;
/* 498 */       detail = new DeuxiemeEvaluationEmployeDetailCritereC();
/* 499 */       detail.setIndexe(i);
/* 500 */       detail.setDetailCritere(detailCritere);
/* 501 */       detail.setEntete(this);
/* 502 */       getListDetailDeuxiemeEvaluation().add(detail);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void findCritereEvaluation() {
/* 509 */     int i = 0;
/* 510 */     DeuxiemeEvaluationEmployeDetailCritereC detail = null;
/* 511 */     EvaluationEmployeDetailCritereC detailEvaluation = null;
/* 512 */     getListDetailDeuxiemeEvaluation().clear();
/* 513 */     for (DetailCritereEvaluationC detailCritere : FichierBaseDAO.getInstance().getAllDetailCritereEvaluation()) {
/*     */ 
/*     */       
/* 516 */       i++;
/* 517 */       detail = new DeuxiemeEvaluationEmployeDetailCritereC();
/* 518 */       detailEvaluation = new EvaluationEmployeDetailCritereC();
/* 519 */       detailEvaluation.setDetailCritere(detailCritere);
/* 520 */       detail.setIndexe(i);
/* 521 */       detail.setDetailCritere(detailEvaluation);
/* 522 */       detail.setEntete(this);
/* 523 */       getListDetailDeuxiemeEvaluation().add(detail);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void findDeuxiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC evaluation) {
/* 530 */     if (getId() != 0) {
/*     */       
/* 532 */       if (getListDetailDeuxiemeEvaluation().size() > 0)
/*     */       {
/* 534 */         getListDetailDeuxiemeEvaluation().clear();
/*     */       }
/* 536 */       int i = 0;
/*     */       
/* 538 */       for (DeuxiemeEvaluationEmployeDetailCritereC details : FactoryDAO.getInstance().getListeDeuxiemeEvaluationEmployeDetailCritere(evaluation)) {
/*     */ 
/*     */         
/* 541 */         i++;
/* 542 */         details.setIndexe(i);
/* 543 */         details.getDetailCritere();
/* 544 */         details.getEntete();
/* 545 */         details.getNoteObtenue();
/* 546 */         details.getNoteObtenueS();
/* 547 */         details.isApplicable();
/* 548 */         getListDetailDeuxiemeEvaluation().add(details);
/*     */       } 
/*     */       
/* 551 */       if (i == 0) {
/*     */         
/* 553 */         DeuxiemeEvaluationEmployeDetailCritereC detail = null;
/* 554 */         getListDetailDeuxiemeEvaluation().clear();
/* 555 */         for (EvaluationEmployeDetailCritereC detailCritere : FactoryDAO.getInstance().getListeEvaluationEmployeDetailCritere(getPremiereEvaluation())) {
/*     */           
/* 557 */           i++;
/* 558 */           detail = new DeuxiemeEvaluationEmployeDetailCritereC();
/* 559 */           detail.setIndexe(i);
/* 560 */           detail.setDetailCritere(detailCritere);
/* 561 */           detail.setEntete(this);
/* 562 */           getListDetailDeuxiemeEvaluation().add(detail);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDateEvaluation() {
/* 571 */     if (getDateEvaluationS().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 573 */       setDateEvaluation(null);
/*     */     } else {
/*     */       
/* 576 */       setDateEvaluation(HelperC.validerDate(getDateEvaluationS()));
/* 577 */       if (getDateEvaluation() == null) {
/*     */         
/* 579 */         setDateEvaluationS("");
/* 580 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 583 */         setDateEvaluationS(HelperC.convertDate(getDateEvaluation()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 590 */     if (getPremiereEvaluation() == null) {
/*     */       
/* 592 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/*     */     }
/* 594 */     else if (getNoteEvaluation() == 0.0D || getPourcentage() == 0.0D) {
/*     */       
/* 596 */       HelperC.afficherMessage("Information", "Veillez d'abord evaluer l'employ�");
/*     */     }
/* 598 */     else if (getTypeAppreciation() == null) {
/*     */       
/* 600 */       HelperC.afficherMessage("Information", "Veillez s�l�ctionner l'appr�ciation");
/*     */     } else {
/*     */       
/* 603 */       Historique hist = new Historique();
/* 604 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 605 */       hist.setOperateur(this.operateur);
/* 606 */       if (getId() == 0) {
/*     */         
/* 608 */         hist.setOperation("Cr�ation de la deuxi�me �valuation de l'employ� " + getPremiereEvaluation().getEmploye().getNom() + " " + getPremiereEvaluation().getEmploye().getPrenom());
/*     */       } else {
/*     */         
/* 611 */         hist.setOperation("Modification de la deuxi�me �valuation de l'employ� " + getPremiereEvaluation().getEmploye().getNom() + " " + getPremiereEvaluation().getEmploye().getPrenom());
/*     */       } 
/* 613 */       hist.setTable(Tables.getTableName(Tables.TableName.evaluationEmploye));
/* 614 */       setHistorique(hist);
/* 615 */       if (FactoryDAO.getInstance().insertUpdateDeuxiemeEvaluationEmploye(this)) {
/*     */         
/* 617 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 618 */         clear(true);
/*     */       } else {
/*     */         
/* 621 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 628 */     if (getId() == 0) {
/*     */       
/* 630 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 632 */     else if (FactoryDAO.getInstance().deleteDeuxiemeEvaluationEmploye(this)) {
/*     */       
/* 634 */       clear(true);
/* 635 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/*     */     } else {
/*     */       
/* 638 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 644 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fermer() {
/*     */     try {
/* 651 */       FacesContext.getCurrentInstance().getExternalContext().redirect("/asystPaie/masterPage.jsf");
/*     */     }
/* 653 */     catch (IOException e) {
/*     */       
/* 655 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\DeuxiemeEvaluationEmployeB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */