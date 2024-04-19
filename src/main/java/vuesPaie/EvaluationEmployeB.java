package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DetailCritereEvaluationC;
import classesPaie.DetailGradeC;
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
public class EvaluationEmployeB extends EvaluationEmployeC {
	private static final long serialVersionUID = 2173666650383459525L;
	private int idTypeNotation;
	private int idAppreciation;
	private List<EvaluationEmployeC>listCotation;
	private List<DetailCritereEvaluationC> allDetailCritere = new ArrayList<DetailCritereEvaluationC>();
	private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
	private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>();
	private EvaluationEmployeC selected;
	private EmployeC selection;
	private String code;
	private List<SelectItem> listAppreciationAvancement = new ArrayList<SelectItem>();
	private String codeEmployeRecherche;
	private String nomEmployeRecherche;
	private HttpSession session = HelperC.getSession();
	private String prenomEmployeRecherche;
	private String noteCritereS,nouveauGrad,dateApplicationS;
	private int index;
	private EvaluationEmployeDetailCritereC selectedDetail;
	private OperateurC operateur;
	private ExerciceC exercice;
	private Date dateApplication;
	private String personel, categorie, grade, niveauFrm, fonction;
	private boolean disableSave,disableMsg;
	private double nvSalaire,salaireGrad,tauxNvGrd,ancienSalaire;
	 
	ParametreAvancementSalaireC avancementSalaire;
	ParametrageAvancementGradeC avancementGrade;
	GradePersonnelC ancienGrade,nouvelGrd;
	GradePersonnelDetailC detailGrd;
	TraitementSalarialC traitementSal;
	DetailGradeC nouvGrd;
	double taux;
	public EvaluationEmployeC getSelected() {
		return this.selected;
	}

	public void setSelected(EvaluationEmployeC selected) {
		this.selected = selected;
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

	public EvaluationEmployeDetailCritereC getSelectedDetail() {
		return this.selectedDetail;
	}

	public void setSelectedDetail(EvaluationEmployeDetailCritereC selectedDetail) {
		this.selectedDetail = selectedDetail;
	}

	public int getIdAppreciation() {
		return this.idAppreciation;
	}

	public void setIdAppreciation(int idAppreciation) {
		this.idAppreciation = idAppreciation;
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

	public boolean isDisableSave() {
		return disableSave;
	}

	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
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

	public double getSalaireGrad() {
		return salaireGrad;
	}

	public void setSalaireGrad(double salaireGrad) {
		this.salaireGrad = salaireGrad;
	}

	public double getTauxNvGrd() {
		return tauxNvGrd;
	}

	public void setTauxNvGrd(double tauxNvGrd) {
		this.tauxNvGrd = tauxNvGrd;
	}

	public double getAncienSalaire() {
		return ancienSalaire;
	}

	public void setAncienSalaire(double ancienSalaire) {
		this.ancienSalaire = ancienSalaire;
	}

	public Date getDateApplication() {
		return dateApplication;
	}

	public void setDateApplication(Date dateApplication) {
		this.dateApplication = dateApplication;
	}

	public List<EvaluationEmployeC> getListCotation() {
		return listCotation;
	}

	public void setListCotation(List<EvaluationEmployeC> listCotation) {
		this.listCotation = listCotation;
	}

	public String getDateApplicationS() {
		return dateApplicationS;
	}

	public void setDateApplicationS(String dateApplicationS) {
		this.dateApplicationS = dateApplicationS;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	@PostConstruct
	private void charger() {
		this.operateur = new OperateurC();
		this.exercice = new ExerciceC();
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
		if (this.operateur == null || this.exercice == null) {

			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {

			findCritereEvaluation();
			disableMsg=true;
			for (TypeNotationC type : FichierBaseDAO.getInstance().getAllTypeNotation()) {
				this.listTypeNotation.add(new SelectItem(Integer.valueOf(type.getId()), type.getDesignation()));
			}

			for (Base typeAppreciation : FichierBaseDAO.getInstance()
					.getAllBase(Tables.getTableName(Tables.TableName.typeAppreciationAvancement))) {
				this.listAppreciationAvancement.add(
						new SelectItem(Integer.valueOf(typeAppreciation.getId()), typeAppreciation.getDesignation()));
			}

			setAnneeValidite(HelperC.getYearFromDate(new Date()));
			setDateEvaluation(new Date());
			setDateEvaluationS(HelperC.changeDateFormate(getDateEvaluation()));
		}
	}

	private void completeEmploye() {
		if (getEmploye() != null) {
			Base fx = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdFnctn(),
					Tables.getTableName(Tables.TableName.fonction));
			Base nv = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdNvFormt(),
					Tables.getTableName(Tables.TableName.niveauFormation));
			Base catg = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdCatgrie(),
					Tables.getTableName(Tables.TableName.categoriePersonnel));
			Base pers = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdPersnl(),
					Tables.getTableName(Tables.TableName.personnel));
			Base grd = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdGrd(),
					Tables.getTableName(Tables.TableName.gradePersonnel));
			if (fx != null)
				fonction = fx.getDesignation();
			else
				fonction = "";

			if (nv != null)
				niveauFrm = nv.getDesignation();
			else
				niveauFrm = "";

			if (catg != null)
				categorie = catg.getDesignation();
			else
				categorie = "";
			if (pers != null)
				personel = pers.getDesignation();
			else
				personel = "";
			if (grd != null)
				grade = grd.getDesignation();
			else
				grade = "";
		}
	}

	public void findByCode() {
		this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
		if (this.selection != null) {

			setObject1();
			changeEvaluation();
		} else {

			clear1(true);
		}
	}

	private void setObject() {
		if (this.selected != null) {
			disableMsg=false;
			setId(this.selected.getId());
			setEmploye(this.selected.getEmploye());
			setAnneeValidite(this.selected.getAnneeValidite());
			setPourcentage(this.selected.getPourcentage());
			setDateEvaluation(this.selected.getDateEvaluation());
			setDateEvaluationS(this.selected.getDateEvaluationS());
			setNoteEvaluation(this.selected.getNoteEvaluation());		
			setNoteGenerale(this.selected.getNoteGenerale());		
			setTypeNotation(this.selected.getTypeNotation());
			setJustificationDiscordance(this.selected.getJustificationDiscordance());			
			setNoteGenerale(this.selected.getNoteGenerale());
			setNoteGeneraleS(this.selected.getNoteGeneraleS());			
			setAncienSalary(selected.getAncienSalary());
			setNouveauSalaire(selected.getNouveauSalaire());
			setTauxAvSal(selected.getTauxAvSal());
			if (getTypeNotation() != null) {

				this.idTypeNotation = getTypeNotation().getId();
				setIdTypeNotation(this.idTypeNotation);
				
			}
			setTypeAppreciation(this.selected.getTypeAppreciation());
			if (getTypeAppreciation() != null) {
				this.idAppreciation = getTypeAppreciation().getId();
			}
				ancienGrade=FichierBaseDAO.getInstance().getGradePersonnel(selected.getIdAnGrade());
				nouvelGrd=FichierBaseDAO.getInstance().getGradePersonnel(selected.getIdNvGrade());
				
					
				traitementSal=FactoryDAO.getInstance().getTraitementSalarial(selection.getId(), Constante.getTypeAvancement(Constante.TypeAvancement.avancementTraitement), selected.getId());
				nouvGrd=FactoryDAO.getInstance().getDetailGradeParEmploye(selection.getId(),0,selected.getId());
				ancienSalaire=selected.getAncienSalary();
				nvSalaire=selected.getNouveauSalaire();
				if(nouvelGrd!=null)
					nouveauGrad=nouvelGrd.getDesignation();
				if(ancienGrade!=null)
					grade=ancienGrade.getDesignation();
			
				} 
		}
	

	private void setObject1() {
		if (this.selection != null) {

			setEmploye(this.selection);
			if (getEmploye() != null) {

				setCode(getEmploye().getCode());
				completeEmploye();
			} else {

				setCode("");
			}
		}
	}

	private void clear(boolean b) {
		if (b) {
			setId(0);
		}
		disableMsg=true;
		setEmploye(null);
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
		setTauxAvSal(0);
		setAncienSalary(0);
		setNouveauSalaire(0);
		nouveauGrad="";
		this.idTypeNotation = 0;
		setTypeAppreciation(null);
		this.idAppreciation = 0;
		this.selected = null;
		this.code = "";
		ancienSalaire=0;
		nvSalaire=0;
		getListDetailEvaluation().clear();
		findCritereEvaluation();
	}

	public void onRowselected1(SelectEvent event) {
		this.selection = (EmployeC) event.getObject();
		setObject1();
		changeEvaluation();
		PrimeFaces.current().executeScript("PF('dlg').hide();");
	}

	private void clear1(boolean b) {
		if (b) {
			this.selection = null;
		}
		setEmploye(this.selection);
	}

	public void chargerEmploye() {
		this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,
				true);
	}

	public void changeNotation(ValueChangeEvent event) {
		this.idTypeNotation = ((Integer) event.getNewValue()).intValue();
		if (this.idTypeNotation != 0) {
			setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(this.idTypeNotation));
		}
	}

	public void changeAppreciation(ValueChangeEvent event) {
		this.idAppreciation = ((Integer) event.getNewValue()).intValue();
		if (this.idAppreciation != 0) {
			setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(this.idAppreciation,
					Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
			
			chargerParametrageAvancement();
		}
	}

	public void changeAnneeValidite(ValueChangeEvent event) {
		setAnneeValidite(((Integer) event.getNewValue()).intValue());
		if (getAnneeValidite() != 0) {
			changeEvaluation();
		}
	}

	public void changeEvaluation() {
		if (getEmploye() != null && getAnneeValidite() != 0) {

			this.selected = FactoryDAO.getInstance().getEvaluationEmploye(getEmploye(), getAnneeValidite());
			if (this.selected != null) {

				setObject();
				findPremiereEvaluationEmploye(this.selected);
				calculResultatEvaluation();
			}
		}
	}

	public void findCritereEvaluation() {
		int i = 0;
		EvaluationEmployeDetailCritereC detail = null;
		for (DetailCritereEvaluationC detailCritere : FichierBaseDAO.getInstance().getAllDetailCritereEvaluation()) {

			i++;
			detail = new EvaluationEmployeDetailCritereC();
			detail.setIndexe(i);
			detail.setDetailCritere(detailCritere);
			detail.setEntete(this);
			getListDetailEvaluation().add(detail);
		}
	}

	public void findPremiereEvaluationEmploye(EvaluationEmployeC evaluation) {
		if (getId() != 0) {

			if (getListDetailEvaluation().size() > 0) {
				getListDetailEvaluation().clear();
			}
			int i = 0;

			for (EvaluationEmployeDetailCritereC details : FactoryDAO.getInstance()
					.getListeEvaluationEmployeDetailCritere(evaluation)) {

				i++;
				details.setIndexe(i);
				details.getDetailCritere();
				details.getEntete();
				details.getNoteObtenue();
				details.getNoteObtenueS();
				details.isApplicable();
				details.setEntete(this);
				getListDetailEvaluation().add(details);
			}

			if (i == 0) {

				EvaluationEmployeDetailCritereC detail = null;
				for (DetailCritereEvaluationC detailCritere : FichierBaseDAO.getInstance()
						.getAllDetailCritereEvaluation()) {

					i++;
					detail = new EvaluationEmployeDetailCritereC();
					detail.setIndexe(i);
					detail.setEntete(this);
					detail.setDetailCritere(detailCritere);
					getListDetailEvaluation().add(detail);
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

	private void chargerParametrageAvancement(){
		avancementGrade=FichierBaseDAO.getInstance().getParametreAvancementGrade(selection.getIdPersnl(), selection.getIdCatgrie(), idTypeNotation);
		searchChanges();
		chargerCotation();
	}
	
	private void searchChanges(){
		double augm=0;
		int nbrCot=0;
		int avGrdTypApr=0,avGrdTypNt=0;
		if(avancementGrade!=null)
		{
			if(avancementGrade.getTypeNotation()!=null)
				avGrdTypNt=avancementGrade.getTypeNotation().getId();
			if( avancementGrade.getTypeAppreciation()!=null)
				avGrdTypApr=avancementGrade.getTypeAppreciation().getId();
			
			nbrCot=FactoryDAO.getInstance().getNombreCotation(selection.getId(),avGrdTypNt, avGrdTypApr);
			
			if(nbrCot==avancementGrade.getNombreDeFoisNotation())
			{
				nouvelGrd=FichierBaseDAO.getInstance().getGradeSuperieur(selection.getIdGrd());
			if(nouvelGrd!=null)
			{				
				nouveauGrad=nouvelGrd.getDesignation();
				createGrade();
			}
			}
		}
		
			if(traitementSal==null)
				ancienSalaire=selection.getSalaireBase();
			else
				ancienSalaire=traitementSal.getSalaireBase();
			if(this.getTypeNotation()!=null)
				taux=this.getTypeNotation().getTauxAugmentation();
			augm=ancienSalaire*taux/100;
			setTauxAvSal(taux);
			nvSalaire=augm+ancienSalaire;		
		
		
	}
	private void chargerCotation(){
		listCotation=FactoryDAO.getInstance().getListEvaluationEmploye(selection.getId());
	}
	private void createGrade(){
		if(nouvGrd==null)
			nouvGrd=new DetailGradeC();
			nouvGrd.setComment("AVANCEMENT GRADE PAR COTATION");
			nouvGrd.setIdNvGrd(nouvelGrd.getId());
			nouvGrd.setIdAncGrd(ancienGrade.getId());
			nouvGrd.setIdEmpl(selection.getId());
			nouvGrd.setDateDebut(getDateApplication());
			nouvGrd.setTypeAvancement(Constante.getTypeAvancement(Constante.TypeAvancement.avancementGrade));
			this.setIdNvGrade(nouvelGrd.getId());
			this.setIdAnGrade(ancienGrade.getId());
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
	
	public void enregistrer() {
		if (getEmploye() == null) {

			HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
		} else if (getNoteEvaluation() == 0.0D || getPourcentage() == 0.0D) {

			HelperC.afficherMessage("Information", "Veillez d'abord evaluer l'employ�");
		} else if (getTypeAppreciation() == null) {

			HelperC.afficherMessage("Information", "Veillez s�l�ctionner l'appr�ciation");
			idAppreciation=0;
		} else 
			if(getDateApplication()==null)
				HelperC.afficherMessage("Information", "Il faut pr�iser la date � la quelle le nouveau salaire sera consid�r�");
			
			else {
			createTraiement();
			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {

				hist.setOperation("Cr�ation de l'�valuation de l'employ� " + getEmploye().getNom() + " "
						+ getEmploye().getPrenom());
			} else {

				hist.setOperation("Modificationde l'�valuation de l'employ� " + getEmploye().getNom() + " "
						+ getEmploye().getPrenom());
			}
			hist.setTable(Tables.getTableName(Tables.TableName.evaluationEmploye));
			setHistorique(hist);
			if (FactoryDAO.getInstance().insertUpdateEvaluationEmploye(this)) {
				HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
				clear(true);
			} else {

				HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
			}
		}
		}

	
	public void supprimer() {
		if (getId() == 0) {

			HelperC.afficherDeleteMessage();
		} else 
		{
			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			hist.setOperation("Suppression de l' �valuation de l'employ� " + getEmploye().getNom() + " "
						+ getEmploye().getPrenom());
				hist.setTable(Tables.getTableName(Tables.TableName.evaluationEmploye));
			setHistorique(hist);
			if (FactoryDAO.getInstance().deleteEvaluationEmploye(this)) {

			clear(true);
			HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
			
		
		}
			else {

			HelperC.afficherMessage("D�sol�", "Echec de suppression");
		}
		}
	}

	public void initialiser() {
		clear(true);
	}

}
