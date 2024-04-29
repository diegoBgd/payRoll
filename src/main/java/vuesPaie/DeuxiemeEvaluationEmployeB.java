 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.DetailCritereEvaluationC;
 import classesPaie.DetailOrganeC;
 import classesPaie.DeuxiemeEvaluationEmployeC;
 import classesPaie.DeuxiemeEvaluationEmployeDetailCritereC;
 import classesPaie.EmployeC;
 import classesPaie.EvaluationEmployeC;
 import classesPaie.EvaluationEmployeDetailCritereC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
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
 public class DeuxiemeEvaluationEmployeB
   extends DeuxiemeEvaluationEmployeC
 {
   private static final long serialVersionUID = 7585413436307591895L;
   private int idTypeNotation;
   private int idAppreciation;
   private int anneeValidite;
   private List<DetailCritereEvaluationC> allDetailCritere = new ArrayList<DetailCritereEvaluationC>();
   
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
   private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>(); 
		    private DeuxiemeEvaluationEmployeC selected; 
		    private EvaluationEmployeC selectedEvaluationEmploye; 
		    private EmployeC selection; private String code;
   private List<SelectItem> listAppreciationAvancement = new ArrayList<SelectItem>(); 
			private String codeEmployeRecherche; private String nomEmployeRecherche; 
			private String prenomEmployeRecherche;
   private HttpSession session = HelperC.getSession(); 
			private String noteCritereS; private String nomEmploye; 
			private String nomOrgane;private int index; 
			private DeuxiemeEvaluationEmployeDetailCritereC selectedDetail; 
			private OperateurC operateur;
   private ExerciceC exercice;
   DetailOrganeC organeDetail;
   private String personel,categorie,grade,niveauFrm,fonction;
   public String getFonction() {
     return this.fonction;
   }
 
   
   public void setFonction(String fonction) {
     this.fonction = fonction;
   }
 
   
   public String getNomOrgane() {
     return this.nomOrgane;
   }
 
   
   public void setNomOrgane(String nomOrgane) {
     this.nomOrgane = nomOrgane;
   }
 
   
   public String getNomEmploye() {
     return this.nomEmploye;
   }
 
   
   public void setNomEmploye(String nomEmploye) {
     this.nomEmploye = nomEmploye;
   }
 
   
   public int getIdAppreciation() {
     return this.idAppreciation;
   }
 
   
   public void setIdAppreciation(int idAppreciation) {
     this.idAppreciation = idAppreciation;
   }
 
 
   
   public DeuxiemeEvaluationEmployeC getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(DeuxiemeEvaluationEmployeC selected) {
     this.selected = selected;
   }
 
   
   public int getIndex() {
     return this.index;
   }
 
   
   public void setIndex(int index) {
     this.index = index;
   }
 
   
   public DeuxiemeEvaluationEmployeDetailCritereC getSelectedDetail() {
     return this.selectedDetail;
   }
 
   
   public void setSelectedDetail(DeuxiemeEvaluationEmployeDetailCritereC selectedDetail) {
     this.selectedDetail = selectedDetail;
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
 
   
   public int getAnneeValidite() {
     return this.anneeValidite;
   }
 
   
   public void setAnneeValidite(int anneeValidite) {
     this.anneeValidite = anneeValidite;
   }
 
   
   public EvaluationEmployeC getSelectedEvaluationEmploye() {
     return this.selectedEvaluationEmploye;
   }
 
   
   public void setSelectedEvaluationEmploye(EvaluationEmployeC selectedEvaluationEmploye) {
     this.selectedEvaluationEmploye = selectedEvaluationEmploye;
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
   
   public List<DetailCritereEvaluationC> getAllDetailCritere() {
     return this.allDetailCritere;
   }
   
   public void setAllDetailCritere(List<DetailCritereEvaluationC> allDetailCritere) {
     this.allDetailCritere = allDetailCritere;
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
       
       for (TypeNotationC type : FichierBaseDAO.getInstance().getAllTypeNotation())
       {
         this.listTypeNotation.add(new SelectItem(Integer.valueOf(type.getId()), type.getDesignation()));
       }
 
       
       for (Base typeAppreciation : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAppreciationAvancement)))
       {
         this.listAppreciationAvancement.add(new SelectItem(Integer.valueOf(typeAppreciation.getId()), typeAppreciation.getDesignation()));
       }
       
       setAnneeValidite(HelperC.getYearFromDate(new Date()));
       setDateEvaluation(new Date());
       setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
     } 
   }
 
   
   public void findByCode() {
     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
     if (this.selection != null) {
       
       setCode(this.selection.getCode());
       this.nomEmploye = this.selection.getNomPrenom();
       completeEmploye();

       if (getAnneeValidite() != 0)
       {
         changeEvaluation();
       }
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
			grade=grd.getDesignation();
		else
			grade="";
	}
}

   private void setObject() {
     if (this.selected != null) {
       
       setId(this.selected.getId());
       setPremiereEvaluation(this.selected.getPremiereEvaluation());
       setPourcentage(this.selected.getPourcentage());
       setPourcentageS(this.selected.getPourcentageS());
       setDateEvaluation(this.selected.getDateEvaluation());
       setDateEvaluationS(this.selected.getDateEvaluationS());
       setNoteEvaluation(this.selected.getNoteEvaluation());
       setNoteEvaluationS(this.selected.getNoteEvaluationS());
       setNoteGenerale(this.selected.getNoteGenerale());
       setNoteGeneraleS(this.selected.getNoteGeneraleS());
       setTypeAppreciation(this.selected.getTypeAppreciation());
       setTypeNotation(this.selected.getTypeNotation());
       setPourcentage(this.selected.getPourcentage());
       setPourcentageS(this.selected.getPourcentageS());
       setNoteGenerale(this.selected.getNoteGenerale());
       setNoteGeneraleS(this.selected.getNoteGeneraleS());
       if (getTypeNotation() != null) {
         
         this.idTypeNotation = getTypeNotation().getId();
         setIdTypeNotation(this.idTypeNotation);
       } 
       if (getTypeAppreciation() != null)
       {
         this.idAppreciation = getTypeAppreciation().getId();
       }
     } 
   }
 
   
   private void setObject1() {
     if (this.selection != null) {
       
       setCode(this.selection.getCode());
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
     setAnneeValidite(HelperC.getYearFromDate(new Date()) + 1);
     setPourcentage(0.0D);
     setPourcentageS("");
     setDateEvaluation(new Date());
     setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
     setNoteEvaluation(0.0D);
     setNoteEvaluationS("");
     setNoteGenerale(0.0D);
     setNoteGeneraleS("");
     setTypeNotation(null);
     setJustificationDiscordance("");
     setPourcentage(0.0D);
     setPourcentageS("");
     setNoteGenerale(0.0D);
     setNoteGeneraleS("");
     setIdTypeNotation(0);
     this.idTypeNotation = 0;
     setTypeAppreciation(null);
     this.idAppreciation = 0;
     this.selected = null;
     this.code = "";
     getListDetailDeuxiemeEvaluation().clear();
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
     if (this.idTypeNotation != 0)
     {
       setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(this.idTypeNotation));
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
         
         setPremiereEvaluation(this.selectedEvaluationEmploye);
         findPremiereEvaluation();
         this.selected = FactoryDAO.getInstance().getDeuxiemeEvaluationEmploye(getPremiereEvaluation());
         if (this.selected != null) {
           
           setObject();
           findDeuxiemeEvaluationEmploye(this.selected);
         } 
       } 
     } 
   }
 
   
   public void findPremiereEvaluation() {
     int i = 0;
     DeuxiemeEvaluationEmployeDetailCritereC detail = null;
     getListDetailDeuxiemeEvaluation().clear();
     for (EvaluationEmployeDetailCritereC detailCritere : FactoryDAO.getInstance().getListeEvaluationEmployeDetailCritere(getPremiereEvaluation())) {
       
       i++;
       detail = new DeuxiemeEvaluationEmployeDetailCritereC();
       detail.setIndexe(i);
       detail.setDetailCritere(detailCritere);
       detail.setEntete(this);
       getListDetailDeuxiemeEvaluation().add(detail);
     } 
   }
 
 
   
   public void findCritereEvaluation() {
     int i = 0;
     DeuxiemeEvaluationEmployeDetailCritereC detail = null;
     EvaluationEmployeDetailCritereC detailEvaluation = null;
     getListDetailDeuxiemeEvaluation().clear();
     for (DetailCritereEvaluationC detailCritere : FichierBaseDAO.getInstance().getAllDetailCritereEvaluation()) {
 
       
       i++;
       detail = new DeuxiemeEvaluationEmployeDetailCritereC();
       detailEvaluation = new EvaluationEmployeDetailCritereC();
       detailEvaluation.setDetailCritere(detailCritere);
       detail.setIndexe(i);
       detail.setDetailCritere(detailEvaluation);
       detail.setEntete(this);
       getListDetailDeuxiemeEvaluation().add(detail);
     } 
   }
 
 
   
   public void findDeuxiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC evaluation) {
     if (getId() != 0) {
       
       if (getListDetailDeuxiemeEvaluation().size() > 0)
       {
         getListDetailDeuxiemeEvaluation().clear();
       }
       int i = 0;
       
       for (DeuxiemeEvaluationEmployeDetailCritereC details : FactoryDAO.getInstance().getListeDeuxiemeEvaluationEmployeDetailCritere(evaluation)) {
 
         
         i++;
         details.setIndexe(i);
         details.getDetailCritere();
         details.getEntete();
         details.getNoteObtenue();
         details.getNoteObtenueS();
         details.isApplicable();
         getListDetailDeuxiemeEvaluation().add(details);
       } 
       
       if (i == 0) {
         
         DeuxiemeEvaluationEmployeDetailCritereC detail = null;
         getListDetailDeuxiemeEvaluation().clear();
         for (EvaluationEmployeDetailCritereC detailCritere : FactoryDAO.getInstance().getListeEvaluationEmployeDetailCritere(getPremiereEvaluation())) {
           
           i++;
           detail = new DeuxiemeEvaluationEmployeDetailCritereC();
           detail.setIndexe(i);
           detail.setDetailCritere(detailCritere);
           detail.setEntete(this);
           getListDetailDeuxiemeEvaluation().add(detail);
         } 
       } 
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
 
   
   public void enregistrer() {
     if (getPremiereEvaluation() == null) {
       
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     }
     else if (getNoteEvaluation() == 0.0D || getPourcentage() == 0.0D) {
       
       HelperC.afficherMessage("Information", "Veillez d'abord evaluer l'employé");
     }
     else if (getTypeAppreciation() == null) {
       
       HelperC.afficherMessage("Information", "Veillez séléctionner l'appréciation");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création de la deuxiéme évaluation de l'employé " + getPremiereEvaluation().getEmploye().getNom() + " " + getPremiereEvaluation().getEmploye().getPrenom());
       } else {
         
         hist.setOperation("Modification de la deuxiéme évaluation de l'employé " + getPremiereEvaluation().getEmploye().getNom() + " " + getPremiereEvaluation().getEmploye().getPrenom());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.evaluationEmploye));
       setHistorique(hist);
       if (FactoryDAO.getInstance().insertUpdateDeuxiemeEvaluationEmploye(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (FactoryDAO.getInstance().deleteDeuxiemeEvaluationEmploye(this)) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succès de l'opération ");
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
   public void fermer() {
     try {
       FacesContext.getCurrentInstance().getExternalContext().redirect("/asystPaie/masterPage.jsf");
     }
     catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 }


