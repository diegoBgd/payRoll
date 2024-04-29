 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DetailCritereEvaluationC;
import classesPaie.DetailGradeC;
 import classesPaie.DeuxiemeEvaluationEmployeC;
 import classesPaie.DeuxiemeEvaluationEmployeDetailCritereC;
 import classesPaie.EmployeC;
 import classesPaie.EvaluationEmployeC;
 import classesPaie.EvaluationEmployeDetailCritereC;
 import classesPaie.ExerciceC;
import classesPaie.GradePersonnelC;
import classesPaie.GradePersonnelDetailC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
import classesPaie.ParametrageAvancementGradeC;
import classesPaie.ParametreAvancementSalaireC;
 import classesPaie.Tables;
 import classesPaie.TraitementSalarialC;
 import classesPaie.TroisiemeEvaluationEmployeC;
 import classesPaie.TypeNotationC;

 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;

 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;

 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;

 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;

 @ManagedBean
 @ViewScoped
 public class TroisiemeEvaluationEmployeB
   extends TroisiemeEvaluationEmployeC
 {
   private static final long serialVersionUID = -2740359771688092287L;
   private int idTypeNotation;
   private int idAppreciation;
   private int anneeValidite;
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
   private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>(); 
		    private TroisiemeEvaluationEmployeC selected; 
		    private EvaluationEmployeC selectedEvaluationEmploye; 
		    private DeuxiemeEvaluationEmployeC selectedDeuxiemeEvaluationEmploye; 
		    private EmployeC selection;
   private List<SelectItem> listAppreciationAvancement = new ArrayList<SelectItem>(); 
			private Constante.TypeAvancement typeAvancement; 
			private double ancienSalaire; 
			private Date dateApplication; 
			private String code;
   private HttpSession session = HelperC.getSession(); 
			private String codeEmployeRecherche; 
			private String nomEmployeRecherche,nomEmploye; 
			private String prenomEmployeRecherche; 
			private String noteCritereS;
			private String ancienGrade; 
			private String dateApplicationS; private int index;
   private OperateurC operateur;
   private ExerciceC exercice;
   private String personel,categorie,grade,niveauFrm,fonction,
						   nouveauGrad;
			private double nvSalaire,salaireGrad,taux,tauxNvGrd;
			private List<TroisiemeEvaluationEmployeC>listCotation;
			ParametreAvancementSalaireC avancementSalaire;
			ParametrageAvancementGradeC avancementGrade;
			GradePersonnelC gradeActuel;
			GradePersonnelDetailC detailGrd;
			TraitementSalarialC traitementSal;
			DetailGradeC nouvGrd;
   public Date getDateApplication() {
     return this.dateApplication;
   }
 
   
   public void setDateApplication(Date dateApplication) {
     this.dateApplication = dateApplication;
   }
 
   
   public String getDateApplicationS() {
     return this.dateApplicationS;
   }
 
   
   public void setDateApplicationS(String dateApplicationS) {
     this.dateApplicationS = dateApplicationS;
   }
 
   
   public Constante.TypeAvancement getTypeAvancement() {
     return this.typeAvancement;
   }
 
   
   public void setTypeAvancement(Constante.TypeAvancement typeAvancement) {
     this.typeAvancement = typeAvancement;
   }
 
   
   public int getIdTypeNotation() {
     return this.idTypeNotation;
   }
 
   
   public void setIdTypeNotation(int idTypeNotation) {
     this.idTypeNotation = idTypeNotation;
   }
 
   
   public int getIdAppreciation() {
     return this.idAppreciation;
   }
 
   
   public void setIdAppreciation(int idAppreciation) {
     this.idAppreciation = idAppreciation;
   }
 
   
   public int getAnneeValidite() {
     return this.anneeValidite;
   }
 
   
   public void setAnneeValidite(int anneeValidite) {
     this.anneeValidite = anneeValidite;
   }
 
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
   
   public List<SelectItem> getListTypeNotation() {
     return this.listTypeNotation;
   }
   
   public void setListTypeNotation(List<SelectItem> listTypeNotation) {
     this.listTypeNotation = listTypeNotation;
   }
   
   public List<SelectItem> getListAppreciationAvancement() {
     return this.listAppreciationAvancement;
   }
 
   
   public void setListAppreciationAvancement(List<SelectItem> listAppreciationAvancement) {
     this.listAppreciationAvancement = listAppreciationAvancement;
   }
 
   
   public TroisiemeEvaluationEmployeC getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(TroisiemeEvaluationEmployeC selected) {
     this.selected = selected;
   }
 
   
   public EvaluationEmployeC getSelectedEvaluationEmploye() {
     return this.selectedEvaluationEmploye;
   }
 
   
   public void setSelectedEvaluationEmploye(EvaluationEmployeC selectedEvaluationEmploye) {
     this.selectedEvaluationEmploye = selectedEvaluationEmploye;
   }
 
   
   public DeuxiemeEvaluationEmployeC getSelectedDeuxiemeEvaluationEmploye() {
     return this.selectedDeuxiemeEvaluationEmploye;
   }
 
   
   public void setSelectedDeuxiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC selectedDeuxiemeEvaluationEmploye) {
     this.selectedDeuxiemeEvaluationEmploye = selectedDeuxiemeEvaluationEmploye;
   }
 
   
   public EmployeC getSelection() {
     return this.selection;
   }
 
   
   public void setSelection(EmployeC selection) {
     this.selection = selection;
   }
 
   
   public String getCode() {
     return this.code;
   }
 
   
   public void setCode(String code) {
     this.code = code;
   }
 
   
   public String getCodeEmployeRecherche() {
     return this.codeEmployeRecherche;
   }
 
   
   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
     this.codeEmployeRecherche = codeEmployeRecherche;
   }
 
   
   public String getNomEmployeRecherche() {
     return this.nomEmployeRecherche;
   }
 
   
   public void setNomEmployeRecherche(String nomEmployeRecherche) {
     this.nomEmployeRecherche = nomEmployeRecherche;
   }
 
   
   public String getPrenomEmployeRecherche() {
     return this.prenomEmployeRecherche;
   }
 
   
   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
     this.prenomEmployeRecherche = prenomEmployeRecherche;
   }
 
   
   public String getNoteCritereS() {
     return this.noteCritereS;
   }
 
   
   public void setNoteCritereS(String noteCritereS) {
     this.noteCritereS = noteCritereS;
   }
 
   
   public int getIndex() {
     return this.index;
   }
 
   
   public void setIndex(int index) {
     this.index = index;
   }
 
   
   public OperateurC getOperateur() {
     return this.operateur;
   }
 
   
   public void setOperateur(OperateurC operateur) {
     this.operateur = operateur;
   }
 
   
   public ExerciceC getExercice() {
     return this.exercice;
   }
 
   
   public void setExercice(ExerciceC exercice) {
     this.exercice = exercice;
   }
 
   
   public HttpSession getSession() {
     return this.session;
   }
 
   
   public void setSession(HttpSession session) {
     this.session = session;
   }
 
   
   public double getAncienSalaire() {
     return this.ancienSalaire;
   }
 
   
   public void setAncienSalaire(double ancienSalaire) {
     this.ancienSalaire = ancienSalaire;
   }
 
   
   


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
   @PostConstruct
   private void charger() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     if (this.operateur == null || this.exercice == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       findCritereEvaluation();
       
				listTypeNotation.add(new SelectItem(0, ""));
       for (TypeNotationC type : FichierBaseDAO.getInstance().getAllTypeNotation())
       {
        	listTypeNotation.add(new SelectItem(Integer.valueOf(type.getId()), type.getDesignation()));
       }

 		 listAppreciationAvancement.add(new SelectItem(0,""));
       
       for (Base typeAppreciation : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAppreciationAvancement)))
       {
         listAppreciationAvancement.add(new SelectItem(Integer.valueOf(typeAppreciation.getId()), typeAppreciation.getDesignation()));
       }
       
       setAnneeValidite(HelperC.getYearFromDate(new Date()));
       setDateEvaluation(new Date());
       setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
     } 
   }
 
   
   public void findByCode() {
     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
     if (this.selection != null) {
       completeEmploye();
				traitementSal=FactoryDAO.getInstance().getSalaireActuel(selection, new Date());
				if(traitementSal!=null)
					ancienSalaire=traitementSal.getSalaireBase();
				else
					ancienSalaire=selection.getSalaireBase();
       setCode(this.selection.getCode());
				setNomEmploye(selection.getNomPrenom());
       changeEvaluation();
     } else {
       
       clear1(true);
     } 
   }

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
   private void setObject() {
     if (this.selected != null) {
       
       setId(this.selected.getId());
       setDeuxiemeEvaluation(this.selected.getDeuxiemeEvaluation());
       setDateEvaluation(this.selected.getDateEvaluation());
       setDateEvaluationS(this.selected.getDateEvaluationS());
       setTypeAppreciation(this.selected.getTypeAppreciation());
       setTypeNotation(this.selected.getTypeNotation());
				
       if (getTypeNotation() != null) {
         
         this.idTypeNotation = getTypeNotation().getId();
         setIdTypeNotation(this.idTypeNotation);
       } 
       if (getTypeAppreciation() != null)
       {
         this.idAppreciation = getTypeAppreciation().getId();
       }
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
   }
 
   
   private void setObject1() {
     if (this.selection != null) {
       
       setCode(this.selection.getCode());
				completeEmploye();
       changeEvaluation();

     } else {
       
       setCode("");
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setId(0);
     }
     setAnneeValidite(HelperC.getYearFromDate(new Date()));
     setDateEvaluation(new Date());
     setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
     setTypeNotation(null);
     setIdTypeNotation(0);
     this.idTypeNotation = 0;
     setTypeAppreciation(null);
     this.idAppreciation = 0;
     this.selected = null;
     this.code = "";
     this.dateApplication = null;
     this.dateApplicationS = "";
     setEmploye(null);
     setTraitement(null);
     setDeuxiemeEvaluation(null);
     findCritereEvaluation();
   }
 
   
   public void onRowselected1(SelectEvent event) {
     this.selection = (EmployeC)event.getObject();
     setObject1();
     changeEvaluation();
     PrimeFaces.current().executeScript("PF('dlg').hide();");
   }
 
 
   
   private void clear1(boolean b) {
     if (b)
     {
       this.selection = null;
     }
   }
 
   
   public void chargerEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,true);
   }
 
   
   public void changeNotation(ValueChangeEvent event) {
     this.idTypeNotation = ((Integer)event.getNewValue()).intValue();
				double augm=0;
     if (this.idTypeNotation != 0)
     {
       setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(this.idTypeNotation));
     }
     if (getTypeNotation() != null)
     {
				taux=getTypeNotation().getTauxAugmentation();
				augm=ancienSalaire*taux/100;
				nvSalaire=ancienSalaire+augm;
				setTauxCot(taux);
       chargerParametrageAvancement();
     }
   }
 
   
   
   
   public void changeAppreciation(ValueChangeEvent event) {
     this.idAppreciation = ((Integer)event.getNewValue()).intValue();
     if (this.idAppreciation != 0)
     {
       setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(this.idAppreciation, Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
     }
   }
 
   
   public void changeAnneeValidite(ValueChangeEvent event) {
     setAnneeValidite(((Integer)event.getNewValue()).intValue());
     if (getAnneeValidite() != 0)
     {
       changeEvaluation();
     }
   }
 
   
   public void changeEvaluation() {
     if (this.selection != null && getAnneeValidite() != 0) {
       
       this.selectedEvaluationEmploye = FactoryDAO.getInstance().getEvaluationEmploye(this.selection, getAnneeValidite());
       if (this.selectedEvaluationEmploye != null) {
         
         this.selectedDeuxiemeEvaluationEmploye = FactoryDAO.getInstance().getDeuxiemeEvaluationEmploye(this.selectedEvaluationEmploye);
         if (this.selectedDeuxiemeEvaluationEmploye != null) {
           
           setDeuxiemeEvaluation(this.selectedDeuxiemeEvaluationEmploye);
           findDeuxiemeEvaluation();
           this.selected = FactoryDAO.getInstance().getTroisiemeEvaluationEmploye(getDeuxiemeEvaluation());
           if (this.selected != null) {
             
             setObject();
             this.typeAvancement = Constante.TypeAvancement.avancementTraitement;
             this.dateApplicationS = HelperC.convertDate(this.dateApplication);
           } 
         } 
       } 
     } 
   }


			private void getLastEvaluation() {
				
			}

   public void findDeuxiemeEvaluation() {
     int i = 0;
     getListDetailDeuxiemeEvaluation().clear();
     
     for (DeuxiemeEvaluationEmployeDetailCritereC detailCritere : FactoryDAO.getInstance().getListeDeuxiemeEvaluationEmployeDetailCritere(getDeuxiemeEvaluation())) {
       
       i++;
       detailCritere.setIndexe(i);
       getListDetailDeuxiemeEvaluation().add(detailCritere);
     } 
   }
 
 
   
   public void findCritereEvaluation() {
     int i = 0;
     DeuxiemeEvaluationEmployeDetailCritereC detailDeuxiemeEvaluation = null;
     EvaluationEmployeDetailCritereC detailEvaluation = null;
     getListDetailDeuxiemeEvaluation().clear();
     for (DetailCritereEvaluationC detailCritere : FichierBaseDAO.getInstance().getAllDetailCritereEvaluation()) {
 
       
       i++;
       detailEvaluation = new EvaluationEmployeDetailCritereC();
       detailDeuxiemeEvaluation = new DeuxiemeEvaluationEmployeDetailCritereC();
       detailEvaluation.setDetailCritere(detailCritere);
       detailDeuxiemeEvaluation.setDetailCritere(detailEvaluation);
       detailDeuxiemeEvaluation.setIndexe(i);
       getListDetailDeuxiemeEvaluation().add(detailDeuxiemeEvaluation);
     } 
   }
 
 
   
   public void changeDateEvaluation() {
     if (getDateEvaluationS().replace("/", "").replace("_", "").trim().equals("")) {
       
       setDateEvaluation(null);
     } else {
       
       setDateEvaluation(HelperC.validerDate(getDateEvaluationS()));
       if (getDateEvaluation() == null) {
         
         setDateEvaluationS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         setDateEvaluationS(HelperC.convertDate(getDateEvaluation()));
       } 
     } 
   }
 
   
   public void changeDateApplication() {
     if (this.dateApplicationS.replace("/", "").replace("_", "").trim().equals("")) {
       
       setDateApplication((Date)null);
     } else {
       
       setDateApplication(HelperC.validerDate(this.dateApplicationS));
       if (getDateApplication() == null) {
         
         this.dateApplicationS = "";
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         this.dateApplicationS = HelperC.convertDate(getDateApplication());

       } 
     } 
   }
 
   
   public void enregistrer() {
     if (getDeuxiemeEvaluation() == null) {
       
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     }
     else if (getTypeAppreciation() == null) {
       
       HelperC.afficherMessage("Information", "Veillez séléctionner l'appréciation");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création de la troisieme évaluation de l'employé " + nomEmploye);
       } else {
         
         hist.setOperation("Modification de la troisieme évaluation de l'employé " + nomEmploye);
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.evaluationEmploye));
       setHistorique(hist);
				if(nvSalaire>0)
				{
					if(getDateApplication()!=null)
						createTraiement();
					else 
					{
						   HelperC.afficherInformation("Information", "Il faut préciser la date d'application du nouveau salaire");
						   return;
					}
				}
       if (FactoryDAO.getInstance().insertUpdateTroisiemeEvaluationEmploye(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   public void supprimer() {
     if (selected==null) {
       
       HelperC.afficherDeleteMessage();
     }
     else {
					Historique hist = new Historique();
	       hist.setDateOperation(Calendar.getInstance().getTime());
	       hist.setOperateur(this.operateur);
					hist.setOperation("Suppression de la troisieme évaluation de l'employé " + nomEmploye);
					hist.setTable(Tables.getTableName(Tables.TableName.evaluationEmploye));
					selected.setHistorique(hist);
				if (FactoryDAO.getInstance().deleteTroisiemeEvaluationEmploye(selected)) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succès de l'opération ");
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
			}
   }
 
   
   public void initialiser() {
     clear(true);
   }
 }


