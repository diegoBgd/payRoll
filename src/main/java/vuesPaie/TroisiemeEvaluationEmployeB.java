/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DetailCritereEvaluationC;
import classesPaie.DetailGradeC;
/*     */ import classesPaie.DeuxiemeEvaluationEmployeC;
/*     */ import classesPaie.DeuxiemeEvaluationEmployeDetailCritereC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.EvaluationEmployeC;
/*     */ import classesPaie.EvaluationEmployeDetailCritereC;
/*     */ import classesPaie.ExerciceC;
import classesPaie.GradePersonnelC;
import classesPaie.GradePersonnelDetailC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
import classesPaie.ParametrageAvancementGradeC;
import classesPaie.ParametreAvancementSalaireC;
/*     */ import classesPaie.Tables;
/*     */ import classesPaie.TraitementSalarialC;
/*     */ import classesPaie.TroisiemeEvaluationEmployeC;
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
/*     */ public class TroisiemeEvaluationEmployeB
/*     */   extends TroisiemeEvaluationEmployeC
/*     */ {
/*     */   private static final long serialVersionUID = -2740359771688092287L;
/*     */   private int idTypeNotation;
/*     */   private int idAppreciation;
/*     */   private int anneeValidite;
/*  58 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
/*  59 */   private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>(); 
		    private TroisiemeEvaluationEmployeC selected; 
		    private EvaluationEmployeC selectedEvaluationEmploye; 
		    private DeuxiemeEvaluationEmployeC selectedDeuxiemeEvaluationEmploye; 
		    private EmployeC selection;
/*  60 */   private List<SelectItem> listAppreciationAvancement = new ArrayList<SelectItem>(); 
			private Constante.TypeAvancement typeAvancement; 
			private double ancienSalaire; 
			private Date dateApplication; 
			private String code;
/*  61 */   private HttpSession session = HelperC.getSession(); 
			private String codeEmployeRecherche; 
			private String nomEmployeRecherche,nomEmploye; 
			private String prenomEmployeRecherche; 
			private String noteCritereS;
			private String ancienGrade; 
			private String dateApplicationS; private int index;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private String personel,categorie,grade,niveauFrm,fonction,
						   nouveauGrad;
			private double nvSalaire,salaireGrad,taux,tauxNvGrd;
			private List<TroisiemeEvaluationEmployeC>listCotation;
			ParametreAvancementSalaireC avancementSalaire;
			ParametrageAvancementGradeC avancementGrade;
			GradePersonnelC gradeActuel;
			GradePersonnelDetailC detailGrd;
			TraitementSalarialC traitementSal;
			DetailGradeC nouvGrd;
/*     */   public Date getDateApplication() {
/*  66 */     return this.dateApplication;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateApplication(Date dateApplication) {
/*  71 */     this.dateApplication = dateApplication;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateApplicationS() {
/*  76 */     return this.dateApplicationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateApplicationS(String dateApplicationS) {
/*  81 */     this.dateApplicationS = dateApplicationS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Constante.TypeAvancement getTypeAvancement() {
/*  86 */     return this.typeAvancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeAvancement(Constante.TypeAvancement typeAvancement) {
/*  91 */     this.typeAvancement = typeAvancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdTypeNotation() {
/*  96 */     return this.idTypeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdTypeNotation(int idTypeNotation) {
/* 101 */     this.idTypeNotation = idTypeNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdAppreciation() {
/* 106 */     return this.idAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdAppreciation(int idAppreciation) {
/* 111 */     this.idAppreciation = idAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnneeValidite() {
/* 116 */     return this.anneeValidite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnneeValidite(int anneeValidite) {
/* 121 */     this.anneeValidite = anneeValidite;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/* 126 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/* 130 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListTypeNotation() {
/* 134 */     return this.listTypeNotation;
/*     */   }
/*     */   
/*     */   public void setListTypeNotation(List<SelectItem> listTypeNotation) {
/* 138 */     this.listTypeNotation = listTypeNotation;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListAppreciationAvancement() {
/* 142 */     return this.listAppreciationAvancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListAppreciationAvancement(List<SelectItem> listAppreciationAvancement) {
/* 147 */     this.listAppreciationAvancement = listAppreciationAvancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public TroisiemeEvaluationEmployeC getSelected() {
/* 152 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(TroisiemeEvaluationEmployeC selected) {
/* 157 */     this.selected = selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public EvaluationEmployeC getSelectedEvaluationEmploye() {
/* 162 */     return this.selectedEvaluationEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedEvaluationEmploye(EvaluationEmployeC selectedEvaluationEmploye) {
/* 167 */     this.selectedEvaluationEmploye = selectedEvaluationEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeuxiemeEvaluationEmployeC getSelectedDeuxiemeEvaluationEmploye() {
/* 172 */     return this.selectedDeuxiemeEvaluationEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedDeuxiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC selectedDeuxiemeEvaluationEmploye) {
/* 177 */     this.selectedDeuxiemeEvaluationEmploye = selectedDeuxiemeEvaluationEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getSelection() {
/* 182 */     return this.selection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/* 187 */     this.selection = selection;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 192 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/* 197 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 202 */     return this.codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 207 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 212 */     return this.nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 217 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 222 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 227 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoteCritereS() {
/* 232 */     return this.noteCritereS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoteCritereS(String noteCritereS) {
/* 237 */     this.noteCritereS = noteCritereS;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 242 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 247 */     this.index = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/* 252 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 257 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 262 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 267 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 272 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 277 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAncienSalaire() {
/* 282 */     return this.ancienSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAncienSalaire(double ancienSalaire) {
/* 287 */     this.ancienSalaire = ancienSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   


public String getFonction() {
	return fonction;
}
public String getNomEmploye() {
	return nomEmploye;
}
public void setNomEmploye(String nomEmploye) {
	this.nomEmploye = nomEmploye;
}
public double getSalaireGrad() {
	return salaireGrad;
}
public void setSalaireGrad(double salaireGrad) {
	this.salaireGrad = salaireGrad;
}
public double getTaux() {
	return taux;
}
public void setTaux(double taux) {
	this.taux = taux;
}
public String getAncienGrade() {
	return ancienGrade;
}
public void setAncienGrade(String ancienGrade) {
	this.ancienGrade = ancienGrade;
}
public void setFonction(String fonction) {
	this.fonction = fonction;
}
public List<TroisiemeEvaluationEmployeC> getListCotation() {
	return listCotation;
}
public void setListCotation(List<TroisiemeEvaluationEmployeC> listCotation) {
	this.listCotation = listCotation;
}
public String getNouveauGrad() {
	return nouveauGrad;
}
public void setNouveauGrad(String nouveauGrad) {
	this.nouveauGrad = nouveauGrad;
}
public double getNvSalaire() {
	return nvSalaire;
}
public void setNvSalaire(double nvSalaire) {
	this.nvSalaire = nvSalaire;
}
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

public double getTauxNvGrd() {
	return tauxNvGrd;
}
public void setTauxNvGrd(double tauxNvGrd) {
	this.tauxNvGrd = tauxNvGrd;
}
/*     */   @PostConstruct
/*     */   private void charger() {
/* 303 */     this.operateur = new OperateurC();
/* 304 */     this.exercice = new ExerciceC();
/* 305 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 306 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 307 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 308 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 309 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 313 */         FacesContext context = FacesContext.getCurrentInstance();
/* 314 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 316 */       catch (IOException e) {
/*     */         
/* 318 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 322 */       findCritereEvaluation();
/*     */       
				listTypeNotation.add(new SelectItem(0, ""));
/* 324 */       for (TypeNotationC type : FichierBaseDAO.getInstance().getAllTypeNotation())
/*     */       {
/* 326 */        	listTypeNotation.add(new SelectItem(Integer.valueOf(type.getId()), type.getDesignation()));
/*     */       }

/*     */ 		 listAppreciationAvancement.add(new SelectItem(0,""));
/*     */       
/* 330 */       for (Base typeAppreciation : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAppreciationAvancement)))
/*     */       {
/* 332 */         listAppreciationAvancement.add(new SelectItem(Integer.valueOf(typeAppreciation.getId()), typeAppreciation.getDesignation()));
/*     */       }
/*     */       
/* 335 */       setAnneeValidite(HelperC.getYearFromDate(new Date()));
/* 336 */       setDateEvaluation(new Date());
/* 337 */       setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void findByCode() {
/* 343 */     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
/* 344 */     if (this.selection != null) {
/*     */       completeEmploye();
				traitementSal=FactoryDAO.getInstance().getSalaireActuel(selection, new Date());
				if(traitementSal!=null)
					ancienSalaire=traitementSal.getSalaireBase();
				else
					ancienSalaire=selection.getSalaireBase();
/* 346 */       setCode(this.selection.getCode());
				setNomEmploye(selection.getNomPrenom());
/* 347 */       changeEvaluation();
/*     */     } else {
/*     */       
/* 350 */       clear1(true);
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
		{
			grade=grd.getDesignation();
			this.setIdAncGrd(grd.getId());
		}
		else
			grade="";
	}
}

	private void chargerParametrageAvancement(){
			avancementGrade=FichierBaseDAO.getInstance().getParametreAvancementGrade(selection.getIdPersnl(), selection.getIdCatgrie(), idTypeNotation);
			avancementSalaire=FichierBaseDAO.getInstance().getParametrageAvancementSalaire(selection.getIdPersnl(), selection.getIdCatgrie(),idTypeNotation);
			searchChanges();
			chargerCotation();
	}
	
	private void searchChanges(){
		double augm=0;
		int nbrCot=0;
		if(avancementGrade!=null)
		{
			nbrCot=FactoryDAO.getInstance().getNombreCotation(selection.getId(), avancementGrade.getTypeNotation().getId(), avancementGrade.getTypeAppreciation().getId());
			if(nbrCot==avancementGrade.getNombreDeFoisNotation())
			{
			gradeActuel=FichierBaseDAO.getInstance().getGradeSuperieur(selection.getIdGrd());
			if(gradeActuel!=null)
			{				
				nouveauGrad=gradeActuel.getDesignation();
				this.setIdNvGrd(gradeActuel.getId());
				
				detailGrd=FichierBaseDAO.getInstance().getGradePersonnelDetailNiveauFormation(selection.getIdGrd(), selection.getIdNvFormt());
				if(detailGrd!=null)
				{
					salaireGrad=gradeActuel.getTraitementMensuel()+(gradeActuel.getTraitementMensuel()*detailGrd.getTauxBonusSalaire()/100);
				}
				createGrade();
			}
			}
		}
		if(avancementSalaire!=null)
		{
			if(traitementSal==null)
				ancienSalaire=selection.getSalaireBase();
			else
				ancienSalaire=traitementSal.getSalaireBase();
			tauxNvGrd=avancementSalaire.getTauxAvancement();
			augm=ancienSalaire*taux/100;
			setTauxAv(tauxNvGrd);
			nvSalaire+=augm;		
		}
		
	}
	
	private void chargerCotation(){
		listCotation=FactoryDAO.getInstance().getListTroisiemeEvaluationEmploye(selection.getId());
	}
	
	
	private void createTraiement(){
		
		if(traitementSal==null)
			traitementSal=new TraitementSalarialC();
		
		traitementSal.setAncienSalaire(ancienSalaire);
		traitementSal.setSalaireBase(nvSalaire);
		traitementSal.setEmploye(selection);
		traitementSal.setDateDebut(getDateApplication());
		traitementSal.setTypeAvancement(Constante.getTypeAvancement(Constante.TypeAvancement.avancementTraitement));
		traitementSal.setComment("AVANCEMENT SALAIRE PAR COTATION");
		
		this.setAncienSalary(ancienSalaire);
		this.setNouveauSalaire(nvSalaire);
		this.setTraitement(traitementSal);
	}
	
	private void createGrade(){
		if(nouvGrd==null)
			nouvGrd=new DetailGradeC();
			nouvGrd.setComment("AVANCEMENT GRADE PAR COTATION");
			nouvGrd.setIdNvGrd(gradeActuel.getId());
			nouvGrd.setIdAncGrd(selection.getIdGrd());
			nouvGrd.setIdEmpl(selection.getId());
			nouvGrd.setDateDebut(getDateApplication());
			nouvGrd.setTypeAvancement(Constante.getTypeAvancement(Constante.TypeAvancement.avancementGrade));
			this.setNouvelgrd(nouvGrd);
	}
/*     */   private void setObject() {
/* 356 */     if (this.selected != null) {
/*     */       
/* 358 */       setId(this.selected.getId());
/* 359 */       setDeuxiemeEvaluation(this.selected.getDeuxiemeEvaluation());
/* 360 */       setDateEvaluation(this.selected.getDateEvaluation());
/* 361 */       setDateEvaluationS(this.selected.getDateEvaluationS());
/* 362 */       setTypeAppreciation(this.selected.getTypeAppreciation());
/* 363 */       setTypeNotation(this.selected.getTypeNotation());
				
/* 364 */       if (getTypeNotation() != null) {
/*     */         
/* 366 */         this.idTypeNotation = getTypeNotation().getId();
/* 367 */         setIdTypeNotation(this.idTypeNotation);
/*     */       } 
/* 369 */       if (getTypeAppreciation() != null)
/*     */       {
/* 371 */         this.idAppreciation = getTypeAppreciation().getId();
/*     */       }
			    gradeActuel=FichierBaseDAO.getInstance().getGradePersonnel(selected.getIdAncGrd());
				traitementSal=FactoryDAO.getInstance().getTraitementSalarial(selection.getId(), Constante.getTypeAvancement(Constante.TypeAvancement.avancementTraitement), selected.getId());
				nouvGrd=FactoryDAO.getInstance().getDetailGradeParEmploye(selection.getId(),0,selected.getId());
				ancienSalaire=selected.getAncienSalary();
				nvSalaire=selected.getNouveauSalaire();
				taux=selected.getTauxCot();
				tauxNvGrd=selected.getTauxAv();
				if(gradeActuel!=null)
					grade=gradeActuel.getDesignation();
			
				} 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject1() {
/* 378 */     if (this.selection != null) {
/*     */       
/* 380 */       setCode(this.selection.getCode());
				completeEmploye();
/* 381 */       changeEvaluation();

/*     */     } else {
/*     */       
/* 384 */       setCode("");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 390 */     if (b)
/*     */     {
/* 392 */       setId(0);
/*     */     }
/* 394 */     setAnneeValidite(HelperC.getYearFromDate(new Date()));
/* 395 */     setDateEvaluation(new Date());
/* 396 */     setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
/* 397 */     setTypeNotation(null);
/* 398 */     setIdTypeNotation(0);
/* 399 */     this.idTypeNotation = 0;
/* 400 */     setTypeAppreciation(null);
/* 401 */     this.idAppreciation = 0;
/* 402 */     this.selected = null;
/* 403 */     this.code = "";
/* 404 */     this.dateApplication = null;
/* 405 */     this.dateApplicationS = "";
/* 406 */     setEmploye(null);
/* 407 */     setTraitement(null);
/* 408 */     setDeuxiemeEvaluation(null);
/* 409 */     findCritereEvaluation();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowselected1(SelectEvent event) {
/* 414 */     this.selection = (EmployeC)event.getObject();
/* 415 */     setObject1();
/* 416 */     changeEvaluation();
/* 417 */     PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clear1(boolean b) {
/* 423 */     if (b)
/*     */     {
/* 425 */       this.selection = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargerEmploye() {
/* 431 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeNotation(ValueChangeEvent event) {
/* 436 */     this.idTypeNotation = ((Integer)event.getNewValue()).intValue();
				double augm=0;
/* 437 */     if (this.idTypeNotation != 0)
/*     */     {
/* 439 */       setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(this.idTypeNotation));
/*     */     }
/* 441 */     if (getTypeNotation() != null)
/*     */     {
				taux=getTypeNotation().getTauxAugmentation();
				augm=ancienSalaire*taux/100;
				nvSalaire=ancienSalaire+augm;
				setTauxCot(taux);
/* 443 */       chargerParametrageAvancement();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   
/*     */   
/*     */   public void changeAppreciation(ValueChangeEvent event) {
/* 501 */     this.idAppreciation = ((Integer)event.getNewValue()).intValue();
/* 502 */     if (this.idAppreciation != 0)
/*     */     {
/* 504 */       setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(this.idAppreciation, Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeAnneeValidite(ValueChangeEvent event) {
/* 510 */     setAnneeValidite(((Integer)event.getNewValue()).intValue());
/* 511 */     if (getAnneeValidite() != 0)
/*     */     {
/* 513 */       changeEvaluation();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeEvaluation() {
/* 519 */     if (this.selection != null && getAnneeValidite() != 0) {
/*     */       
/* 521 */       this.selectedEvaluationEmploye = FactoryDAO.getInstance().getEvaluationEmploye(this.selection, getAnneeValidite());
/* 522 */       if (this.selectedEvaluationEmploye != null) {
/*     */         
/* 524 */         this.selectedDeuxiemeEvaluationEmploye = FactoryDAO.getInstance().getDeuxiemeEvaluationEmploye(this.selectedEvaluationEmploye);
/* 525 */         if (this.selectedDeuxiemeEvaluationEmploye != null) {
/*     */           
/* 527 */           setDeuxiemeEvaluation(this.selectedDeuxiemeEvaluationEmploye);
/* 528 */           findDeuxiemeEvaluation();
/* 529 */           this.selected = FactoryDAO.getInstance().getTroisiemeEvaluationEmploye(getDeuxiemeEvaluation());
/* 530 */           if (this.selected != null) {
/*     */             
/* 532 */             setObject();
/* 533 */             this.typeAvancement = Constante.TypeAvancement.avancementTraitement;
/* 534 */             this.dateApplicationS = HelperC.convertDate(this.dateApplication);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }


			private void getLastEvaluation() {
				
			}

/*     */   public void findDeuxiemeEvaluation() {
/* 545 */     int i = 0;
/* 546 */     getListDetailDeuxiemeEvaluation().clear();
/*     */     
/* 548 */     for (DeuxiemeEvaluationEmployeDetailCritereC detailCritere : FactoryDAO.getInstance().getListeDeuxiemeEvaluationEmployeDetailCritere(getDeuxiemeEvaluation())) {
/*     */       
/* 551 */       i++;
/* 552 */       detailCritere.setIndexe(i);
/* 553 */       getListDetailDeuxiemeEvaluation().add(detailCritere);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void findCritereEvaluation() {
/* 560 */     int i = 0;
/* 561 */     DeuxiemeEvaluationEmployeDetailCritereC detailDeuxiemeEvaluation = null;
/* 562 */     EvaluationEmployeDetailCritereC detailEvaluation = null;
/* 563 */     getListDetailDeuxiemeEvaluation().clear();
/* 564 */     for (DetailCritereEvaluationC detailCritere : FichierBaseDAO.getInstance().getAllDetailCritereEvaluation()) {
/*     */ 
/*     */       
/* 567 */       i++;
/* 568 */       detailEvaluation = new EvaluationEmployeDetailCritereC();
/* 569 */       detailDeuxiemeEvaluation = new DeuxiemeEvaluationEmployeDetailCritereC();
/* 570 */       detailEvaluation.setDetailCritere(detailCritere);
/* 571 */       detailDeuxiemeEvaluation.setDetailCritere(detailEvaluation);
/* 572 */       detailDeuxiemeEvaluation.setIndexe(i);
/* 573 */       getListDetailDeuxiemeEvaluation().add(detailDeuxiemeEvaluation);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDateEvaluation() {
/* 580 */     if (getDateEvaluationS().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 582 */       setDateEvaluation(null);
/*     */     } else {
/*     */       
/* 585 */       setDateEvaluation(HelperC.validerDate(getDateEvaluationS()));
/* 586 */       if (getDateEvaluation() == null) {
/*     */         
/* 588 */         setDateEvaluationS("");
/* 589 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 592 */         setDateEvaluationS(HelperC.convertDate(getDateEvaluation()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateApplication() {
/* 599 */     if (this.dateApplicationS.replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 601 */       setDateApplication((Date)null);
/*     */     } else {
/*     */       
/* 604 */       setDateApplication(HelperC.validerDate(this.dateApplicationS));
/* 605 */       if (getDateApplication() == null) {
/*     */         
/* 607 */         this.dateApplicationS = "";
/* 608 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 611 */         this.dateApplicationS = HelperC.convertDate(getDateApplication());

/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 619 */     if (getDeuxiemeEvaluation() == null) {
/*     */       
/* 621 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/*     */     }
/* 623 */     else if (getTypeAppreciation() == null) {
/*     */       
/* 625 */       HelperC.afficherMessage("Information", "Veillez s�l�ctionner l'appr�ciation");
/*     */     } else {
/*     */       
/* 628 */       Historique hist = new Historique();
/* 629 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 630 */       hist.setOperateur(this.operateur);
/* 631 */       if (getId() == 0) {
/*     */         
/* 633 */         hist.setOperation("Cr�ation de la troisieme �valuation de l'employ� " + nomEmploye);
/*     */       } else {
/*     */         
/* 636 */         hist.setOperation("Modification de la troisieme �valuation de l'employ� " + nomEmploye);
/*     */       } 
/* 638 */       hist.setTable(Tables.getTableName(Tables.TableName.evaluationEmploye));
/* 639 */       setHistorique(hist);
				if(nvSalaire>0)
				{
					if(getDateApplication()!=null)
						createTraiement();
					else 
					{
						   HelperC.afficherInformation("Information", "Il faut pr�ciser la date d'application du nouveau salaire");
						   return;
					}
				}
/* 640 */       if (FactoryDAO.getInstance().insertUpdateTroisiemeEvaluationEmploye(this)) {
/*     */         
/* 642 */         HelperC.afficherMessage("Information", "Succ� de l'op�ration");
/* 643 */         clear(true);
/*     */       } else {
/*     */         
/* 646 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
 
/*     */   public void supprimer() {
/* 653 */     if (selected==null) {
/*     */       
/* 655 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 657 */     else {
					Historique hist = new Historique();
	/* 629 */       hist.setDateOperation(Calendar.getInstance().getTime());
	/* 630 */       hist.setOperateur(this.operateur);
					hist.setOperation("Suppression de la troisieme �valuation de l'employ� " + nomEmploye);
					hist.setTable(Tables.getTableName(Tables.TableName.evaluationEmploye));
					selected.setHistorique(hist);
				if (FactoryDAO.getInstance().deleteTroisiemeEvaluationEmploye(selected)) {
/*     */       
/* 659 */       clear(true);
/* 660 */       HelperC.afficherMessage("Information", "Succ� de l'op�ration ");
/*     */     } else {
/*     */       
/* 663 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
			}
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 669 */     clear(true);
/*     */   }
/*     */ }


