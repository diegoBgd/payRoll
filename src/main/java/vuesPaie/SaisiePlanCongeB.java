package vuesPaie;

import java.io.IOException;
import java.util.ArrayList;
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
import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import classesPaie.ParametrageDureeCongeC;
import classesPaie.SaisiePlanCongeC;
import classesPaie.Tables;

@ManagedBean
@ViewScoped
public class SaisiePlanCongeB extends SaisiePlanCongeC{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8719017081536286269L;
	private String code;
	private String nomEmployeRecherche; 
	private String codeEmployeRecherche;
	private EmployeC selection;
	private List<EmployeC> listEmploye = new ArrayList<EmployeC>(); 
	private List<SelectItem> listTypeConge;
	private List<SaisiePlanCongeC> listPlan;
	private SaisiePlanCongeC selected;
	private int duree,jourCongeAnnuel;
	DroitC droit;
	private boolean decision,disableSave;
	private boolean afficherMotif;
	 HttpSession session = HelperC.getSession();
	 OperateurC operateur;
	 ExerciceC exercice;
	 ParametrageDureeCongeC prmDure;
	 private Base userFonction;
	 
	public SaisiePlanCongeB() {
		
	}
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	public String getNomEmployeRecherche() {
		return nomEmployeRecherche;
	}


	public void setNomEmployeRecherche(String nomEmployeRecherche) {
		this.nomEmployeRecherche = nomEmployeRecherche;
	}


	public String getCodeEmployeRecherche() {
		return codeEmployeRecherche;
	}


	public void setCodeEmployeRecherche(String codeEmployeRecherche) {
		this.codeEmployeRecherche = codeEmployeRecherche;
	}


	public List<EmployeC> getListEmploye() {
		return listEmploye;
	}


	public void setListEmploye(List<EmployeC> listEmploye) {
		this.listEmploye = listEmploye;
	}


	public List<SelectItem> getListTypeConge() {
		return listTypeConge;
	}


	public void setListTypeConge(List<SelectItem> listTypeConge) {
		this.listTypeConge = listTypeConge;
	}


	public boolean isDisableSave() {
		return disableSave;
	}


	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
	}


	public boolean isDecision() {
		return decision;
	}


	public void setDecision(boolean decision) {
		this.decision = decision;
	}


	public boolean isAfficherMotif() {
		return afficherMotif;
	}


	public void setAfficherMotif(boolean afficherMotif) {
		this.afficherMotif = afficherMotif;
	}


	public EmployeC getSelection() {
		return selection;
	}


	public void setSelection(EmployeC selection) {
		this.selection = selection;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public int getJourCongeAnnuel() {
		return jourCongeAnnuel;
	}

	public void setJourCongeAnnuel(int jourCongeAnnuel) {
		this.jourCongeAnnuel = jourCongeAnnuel;
	}

	public List<SaisiePlanCongeC> getListPlan() {
		return listPlan;
	}

	public void setListPlan(List<SaisiePlanCongeC> listPlan) {
		this.listPlan = listPlan;
	}


	public SaisiePlanCongeC getSelected() {
		return selected;
	}


	public void setSelected(SaisiePlanCongeC selected) {
		this.selected = selected;
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
	      } catch (IOException e) {
	        
	       e.printStackTrace();
	    } 
	   } else {
		   
		   listTypeConge=new ArrayList<SelectItem>();
		   this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
		    if (this.userFonction != null) {
		        this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), 
		            Constante.Role.saisiePlanConge);
		       }
		   listTypeConge.add(new SelectItem(0, ""));
			for (Base typ : FichierBaseDAO.getInstance().getAllBase(
					        Tables.getTableName(Tables.TableName.typeConge))) {
				listTypeConge.add(new SelectItem(Integer.valueOf(typ.getId()), typ.getDesignation()));
					}
	    } 
	   }
	
	public void chargerEmploye()
	{
		listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,true);
	}
	public void onRowselected1(SelectEvent event) {
		   this.selection = (EmployeC)event.getObject();
		   setObject1();
		 
		    PrimeFaces.current().executeScript("PF('dlg').hide();");
		  }
	public void findByCode() {
	    this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
	   
	    if (this.selection != null) {
	      setObject1();
	     
	    } 
	  }
	private void searchSaisiePlan()
	{
		disableSave=false;
		if(selection!=null)
		{
			selected=FactoryDAO.getInstance().getSaisiePlanConge(selection, exercice.getId(), getIdTypeConge());
			if(selected!=null)
				setObject();
			else
			{
				prmDure=FichierBaseDAO.getInstance().getParametrageDureeConge(selection.getIdPersnl(), getIdTypeConge());
				
				if(prmDure==null)
					FichierBaseDAO.getInstance().getParametrageDureeConge(0, getIdTypeConge());
				
				if(prmDure==null || prmDure.getNombreJoursAnnuel()==0)
				{
					disableSave=true;
					 HelperC.afficherAttention("ATTENTION", "Il faut paramétrer la durée pour ce type de congé");
					 return;
				}
				else
					setJourCongeAnnuel(prmDure.getNombreJoursAnnuel());
			}
		}
	}
	private void checkDays()
	{
		disableSave=false;
		
		if(getDateDebut()!=null && getDateFin()!=null)
		{
			duree=(int)HelperC.daysBetween(getDateDebut(), getDateFin());
			if(prmDure!=null)
			{
				if(duree>prmDure.getNombreJoursAnnuel())
				{
					disableSave=true;
					 HelperC.afficherAttention("ATTENTION", "Vous ne puvez pas dépasser la durée pour ce type de congé !");
					 return;
				}
			}
		}
	}
	private void setObject1() {
		   if (this.selection != null) {		     
			   this.code = selection.getCode();
			   setEmploye(selection);
			   setIdEmploye(selection.getId());
		  }
	}
	public void changeDateFin() {
	    if (getDateFinS().replace("/", "").replace("_", "").trim().equals("")) {
	      setDateFin(null);
	     } else {
	      
	      setDateFin(HelperC.validerDate(getDateFinS()));
	     if (getDateFin() == null) {
	         setDateFinS("");
	      HelperC.afficherMessage("Information", "Date invalide");
	     } else {
	    	 checkDays();
	        setDateFinS(HelperC.convertDate(getDateFin()));
	        
	     } 
	    } 
	  }
	public void changeDateDebut() {
	     if (getDateDebutS().replace("/", "").replace("_", "").trim().equals("")) {
	      setDateDebut(null);
	   } else {
	       
	      setDateDebut(HelperC.validerDate(getDateDebutS()));
	      if (getDateDebut() == null) {
	         HelperC.afficherMessage("Information", "Date invalide");
	     } else {
	        
	        setDateDebutS(HelperC.convertDate(getDateDebut()));
	        checkDays();
	      } 
	     } 
	  }
	
	public void changeTypeConge(ValueChangeEvent event){
		setIdTypeConge(((Integer)event.getNewValue()).intValue());
		searchSaisiePlan();
	}
	
	private void setObject(){
		this.setId(selected.getId());
		this.setDateDebut(selected.getDateDebut());
		this.setDateFin(selected.getDateFin());
		this.setDateDebutS(selected.getDateDebutS());
		this.setDateFinS(selected.getDateFinS());
		this.setIdEmploye(selected.getIdEmploye());
		this.setIdTypeConge(selected.getIdTypeConge());
		selection=selected.getEmploye();
		if(prmDure==null)
		{
			prmDure=FichierBaseDAO.getInstance().getParametrageDureeConge(selection.getIdPersnl(), getIdTypeConge());
			this.setJourCongeAnnuel(prmDure.getNombreJoursAnnuel());
		}
		duree=selected.getDureeConge();
		
		disableSave=false;
		setObject1();
	}
	
	public void onRowselected(SelectEvent event)
	{
		selected= (SaisiePlanCongeC)event.getObject();
		if(selected!=null)
			setObject();
		  PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
		
	}
	
	public void chargement(){
		listPlan=FactoryDAO.getInstance().getListSaisiePlanConge(exercice.getId());
	}
	
	public void saveSaisie(){
		if(droit!=null)
		{
			if(getId()==0)
			{
				if(!droit.isCreer())
				{
					 HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
					 return;
				}
			}
			else{
				if(!droit.isModifier())
				{
					 HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
					 return;
				}
			}
			setIdExercice(exercice.getId());
			if(FactoryDAO.getInstance().insertUpdateSaisiePlanConge(this))
			{
				 HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
				 initialiser();
			}
			else
				  HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
		}
	}
	
	public void delete(){
		if(selected!=null)
		{
			if(droit!=null)
			{
				if(!droit.isSupprimer())
				{
					 HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
					return;
				}
				if(FactoryDAO.getInstance().deleteSaisiePlanConge(selected))
				{
					 HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
					 initialiser();
				}
				else
					 HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
			}
		}
	}
	
	public void initialiser(){
		this.setId(0);
		this.setDateDebut(null);
		this.setDateFin(null);
		this.setDateDebutS("");
		this.setDateFinS("");
		this.setIdEmploye(0);
		this.setIdTypeConge(0);
		duree=0;
		setDureeConge(0);
		jourCongeAnnuel=0;
		selection=null;
		code="";
		
	}
}
