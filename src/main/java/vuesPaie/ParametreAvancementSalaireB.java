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

import org.primefaces.event.SelectEvent;

import persistencePaie.FichierBaseDAO;
import classesPaie.Base;
import classesPaie.CategoriePersonnelC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import classesPaie.ParametreAvancementSalaireC;
import classesPaie.Tables;
import classesPaie.TypeNotationC;
import classesPaie.Tables.TableName;


@ManagedBean
@ViewScoped
public class ParametreAvancementSalaireB extends ParametreAvancementSalaireC{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3808908829384848319L;
	private List<SelectItem> listPersonnel = new ArrayList<SelectItem>();
	private List<SelectItem> listCategorie = new ArrayList<SelectItem>();
	private int idPrsnl,idCatgr,choix;
	private List<ParametreAvancementSalaireC> listParametreAvancement;
	private HttpSession session = HelperC.getSession();
	private OperateurC operateur;
	private ExerciceC exercice;
	private ParametreAvancementSalaireC selectedavancement;
	private boolean disableMsg;
	private int idType;
	 private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>();
	 
	public ParametreAvancementSalaireB() {
		
	}
	
	public List<SelectItem> getListPersonnel() {
		return listPersonnel;
	}
	public void setListPersonnel(List<SelectItem> listPersonnel) {
		this.listPersonnel = listPersonnel;
	}
	public List<SelectItem> getListCategorie() {
		return listCategorie;
	}
	public void setListCategorie(List<SelectItem> listCategorie) {
		this.listCategorie = listCategorie;
	}
	public int getIdPrsnl() {
		return idPrsnl;
	}
	public void setIdPrsnl(int idPrsnl) {
		this.idPrsnl = idPrsnl;
	}
	public int getIdCatgr() {
		return idCatgr;
	}
	public void setIdCatgr(int idCatgr) {
		this.idCatgr = idCatgr;
	}
	
	public int getChoix() {
		return choix;
	}
	public void setChoix(int choix) {
		this.choix = choix;
	}
	
	public List<ParametreAvancementSalaireC> getListParametreAvancement() {
		return listParametreAvancement;
	}
	public void setListParametreAvancement(
			List<ParametreAvancementSalaireC> listParametreAvancement) {
		this.listParametreAvancement = listParametreAvancement;
	}
	
	public ParametreAvancementSalaireC getSelectedavancement() {
		return selectedavancement;
	}

	public void setSelectedavancement(ParametreAvancementSalaireC selectedavancement) {
		this.selectedavancement = selectedavancement;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public List<SelectItem> getListTypeNotation() {
		return listTypeNotation;
	}

	public void setListTypeNotation(List<SelectItem> listTypeNotation) {
		this.listTypeNotation = listTypeNotation;
	}

	@PostConstruct
	   private void init() {
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
	    	disableMsg=true;
	    	chargerPersonnel();
	    	chargerParametrage();
	    	chargerNotation();
	    }
	}
	private void chargerNotation() {
		this.listTypeNotation.add(new SelectItem(Integer.valueOf(0), ""));
  
      for (TypeNotationC notation : FichierBaseDAO.getInstance().getAllTypeNotation())
      {
         this.listTypeNotation.add(new SelectItem(Integer.valueOf(notation.getId()), notation.getDesignation()));
      }

	}
	private void chargerParametrage(){
		listParametreAvancement=FichierBaseDAO.getInstance().getListParametrageAvancementSalaire(0, 0);
		for (ParametreAvancementSalaireC prm : listParametreAvancement) {
			if(prm.getIdCategorie()>0)
				prm.setLibelleCat(FichierBaseDAO.getInstance().getBaseById(prm.getIdCategorie(), Tables.getTableName(TableName.categoriePersonnel)).getDesignation());
			if(prm.getIdPersonnel()>0)
				prm.setLibellePersonl(FichierBaseDAO.getInstance().getBaseById(prm.getIdPersonnel(), Tables.getTableName(TableName.personnel)).getDesignation());
	
		}
	}
	private void chargerPersonnel()
	{
		listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));
		
		for (Base personnel : FichierBaseDAO.getInstance().getAllBase(
				Tables.getTableName(Tables.TableName.personnel))) {
			listPersonnel.add(new SelectItem(
					Integer.valueOf(personnel.getId()), personnel
							.getDesignation()));
		}
	}
	  public void changeTypeNotation(ValueChangeEvent event) {
		   this.idType = ((Integer)event.getNewValue()).intValue();
		   if (this.idType != 0)
		  {
		    setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(idType));
		   }
		  }

	public void changePersonnel(ValueChangeEvent event) {
		    if (event != null && event.getNewValue() != null) {
		    	idPrsnl = ((Integer)event.getNewValue()).intValue();
		    	this.setIdPersonnel(idPrsnl);
		      chargementCategorie();
		    } 
		 }
	
	 private void chargementCategorie() {
	     this.listCategorie.clear();
	     this.listCategorie.add(new SelectItem(Integer.valueOf(0), ""));
	    for (CategoriePersonnelC cat : FichierBaseDAO.getInstance().getListCategoriePersonnelParIdPersonnel(idPrsnl))
	    {
	      this.listCategorie.add(new SelectItem(Integer.valueOf(cat.getId()), String.valueOf(cat.getCode()) + "||" + cat.getDesignation()));
	     }
	   }
	 public void changeCategorie(ValueChangeEvent event) {
	     if (event != null && event.getNewValue() != null) {
	      
	      idCatgr = ((Integer)event.getNewValue()).intValue();
	      this.setIdCategorie(idCatgr);
	     } 
	  }
	 
	  public void onRowSelected(SelectEvent event) {
		  selectedavancement = (ParametreAvancementSalaireC)event.getObject();
		  disableMsg=true;
		    if (selectedavancement != null) {
		    	this.setId(selectedavancement.getId());
		    	this.setAncienSalaireInferieur(selectedavancement.isAncienSalaireInferieur());
		    	this.setAvancementInconditionnel(selectedavancement.isAvancementInconditionnel());
		    	this.setAncienSalaireSuperieur(selectedavancement.isAncienSalaireSuperieur());		    
		    	this.setIdCategorie(selectedavancement.getIdCategorie());
		    	this.setIdPersonnel(selectedavancement.getIdPersonnel());
		    	this.setTauxAvancement(selectedavancement.getTauxAvancement());
		    	this.setTypeNotation(selectedavancement.getTypeNotation());
		    	if(selectedavancement.getTypeNotation()!=null)
		    		setIdType(selectedavancement.getTypeNotation().getId());
		    	disableMsg=false;
		    	idPrsnl=selectedavancement.getIdPersonnel();
		    	chargementCategorie();
		    	idCatgr=selectedavancement.getIdCategorie();
		    	
		     } 
		   }
	
	 
	public void save(){
		if(idPrsnl==0)
		{
			 HelperC.afficherAttention("Information", "Veillez préciser le personnel!");
			 return;
		}
		
		FichierBaseDAO.getInstance().insertUpdateParametrageAvancementSalaire(this);
		chargerParametrage();
		initialiser();
	}
	
	public void supprimer(){
		if(selectedavancement!=null)
		{
		FichierBaseDAO.getInstance().delete(selectedavancement.getId(), Tables.getTableName(TableName.parametrageAvancementSalaire));
		chargerParametrage();
		initialiser();
		}else
			HelperC.afficherDeleteMessage();
		
	}
	
	public void initialiser(){
		this.setId(0);
    	this.setAncienSalaireInferieur(false);
    	this.setAvancementInconditionnel(false);
    	this.setAncienSalaireSuperieur(false);
    	this.setTauxAvancement(0);
    	this.setIdCategorie(0);
    	this.setIdPersonnel(0);
    	this.setIdType(0);
    	this.setTypeNotation(null);
    	disableMsg=true;
    	idCatgr=0;
    	idPrsnl=0;
    	choix=0;
	}
}
