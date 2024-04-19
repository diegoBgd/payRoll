package vuesPaie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import persistencePaie.FichierBaseDAO;
import classesPaie.Base;
import classesPaie.DeductionC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import classesPaie.ParametrageSanctionC;
@ManagedBean
@ViewScoped
public class ParametrageSanctionB extends ParametrageSanctionC{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3443203397652347718L;
	OperateurC operateur; ExerciceC exercice;
	HttpSession session = HelperC.getSession();
	Base userFonction;
	private List<ParametrageSanctionC> listParametre;
	private ParametrageSanctionC selected;
	private List<SelectItem> listRetenu;
	private boolean disableMsg;
	
	public ParametrageSanctionB() {
		
	}
	
	public List<ParametrageSanctionC> getListParametre() {
		return listParametre;
	}

	public void setListParametre(List<ParametrageSanctionC> listParametre) {
		this.listParametre = listParametre;
	}

	public ParametrageSanctionC getSelected() {
		return selected;
	}

	public void setSelected(ParametrageSanctionC selected) {
		this.selected = selected;
	}

	public List<SelectItem> getListRetenu() {
		return listRetenu;
	}

	public void setListRetenu(List<SelectItem> listRetenu) {
		this.listRetenu = listRetenu;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	@PostConstruct
	   public void init() {
	     String codeOperateur = (String)this.session.getAttribute("operateur");
	     String codeExercice = (String)this.session.getAttribute("exercice");
	     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
	    this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
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
	    	charger();
	    	chargerRetenu();
	   }
	}

	private void chargerRetenu()
	{
		listRetenu=new ArrayList<SelectItem>();
		listRetenu.add(new SelectItem(0, ""));
		for (DeductionC ret : FichierBaseDAO.getInstance().getAllDeduction()) {
			listRetenu.add(new SelectItem(ret.getId(),ret.getDesignation()));
		}
	}
	public void charger(){
		listParametre=FichierBaseDAO.getInstance().getListeParametrageSanction();
		
	}
	
	 public void changeReetenu(ValueChangeEvent event) {
		   setIdRetenu(((Integer)event.getNewValue()).intValue());
		 }
	 
	public void onRowSelect(SelectEvent event) {
		selected= (ParametrageSanctionC)event.getObject();
		disableMsg=true;
	    if (this.selected != null)
	    {
	    	setObject();
	    	 PrimeFaces.current().executeScript("PF('dlgPrm').hide();");
	     }
	   }
	
	private void setObject(){
		disableMsg=false;
		setId(selected.getId());
		setDureeCloture(selected.getDureeCloture());
		setDureeMax(selected.getDureeMax());
		setDureeMin(selected.getDureeMin());
		setDureeRecours(selected.getDureeRecours());
		setTauxRetenue(selected.getTauxRetenue());
		setLibelleSanction(selected.getLibelleSanction());
		setIdRetenu(selected.getIdRetenu());
	}
	
	public void save(){
		if(getLibelleSanction()!=null && !getLibelleSanction().equals(""))
		{
			if(FichierBaseDAO.getInstance().insertUpdateParametrageSanction(this))
			{
				 HelperC.afficherMessage("Information", "Succès de l'opération!", FacesMessage.SEVERITY_INFO);
				 charger();
				 initialiser();
			}
			else
				HelperC.afficherMessage("Information", "Echec de l'opération!", FacesMessage.SEVERITY_INFO);
		}
	}
	
	public void supprimer(){
		if(selected!=null)
		{
			if(FichierBaseDAO.getInstance().deleteParametrageSanction(selected))
			{
				 HelperC.afficherMessage("Information", "Succès de l'opération!", FacesMessage.SEVERITY_INFO);
				 charger();
				 initialiser();
			}
			else
				HelperC.afficherMessage("Information", "Echec de l'opération!", FacesMessage.SEVERITY_INFO);
		}
		else
			HelperC.afficherDeleteMessage();
	}
	
	public void initialiser(){
		setId(0);
		setDureeCloture(0);
		setDureeMax(0);
		setDureeMin(0);
		setDureeRecours(0);
		setTauxRetenue(0);
		setIdRetenu(0);
		setLibelleSanction("");
		selected=null;
		disableMsg=true;
	}
	
}
