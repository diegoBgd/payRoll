package editionPaie;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
import classesPaie.BulletinPaieC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.OperateurC;

@ManagedBean
@ViewScoped
public class BulletinLineB implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 2037921454831121322L;
private String code,nom,datePrint;
private List<String> listDate;
HttpSession session = HelperC.getSession();
ExerciceC exercice;
OperateurC oper;
Date datePaie;
EmployeC empl;
BulletinPaieC bulletin;

	public BulletinLineB() 
	{
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDatePrint() {
		return datePrint;
	}
	public void setDatePrint(String datePrint) {
		this.datePrint = datePrint;
	}
	public List<String> getListDate() {
		return listDate;
	}
	public void setListDate(List<String> listDate) {
		this.listDate = listDate;
	}
	@PostConstruct
	 private void init() {
	    
	    String codeOperateur = (String)this.session.getAttribute("operateur");
	    String codeExercice = (String)this.session.getAttribute("exercice");
	     if (codeOperateur == null || codeExercice == null) {
	 
	      
	      try {
	         FacesContext context = FacesContext.getCurrentInstance();
	        context.getExternalContext().redirect("/payRoll/login.xhtmlf");
	     }
	      catch (IOException e) {
	         
	        e.printStackTrace();
	       } 
	     } else {
	    	 exercice=FichierBaseDAO.getInstance().getExercice(codeExercice);
	    	 oper=FichierBaseDAO.getInstance().getOperateur(codeOperateur);
	    	 listDate=FactoryDAO.getInstance().getListDatePaie(exercice.getId(), 1);
	    	 searchEmploye();
	     }
	    	 
	     }

	public void changeDatePaie(ValueChangeEvent event)
	{
		datePrint=(String)event.getNewValue().toString();
	       if(datePrint!=null)
	    	   datePaie=HelperC.validerDate(datePrint);
	}
	
	private void searchEmploye(){
		empl=FactoryDAO.getInstance().getEmploye(oper.getIdEmploye(), false);
		if(empl!=null)
		{
			code=empl.getCode();
			nom=empl.getNomPrenom();
		}
	}

	   public void visualiser() {
		   bulletin=FactoryDAO.getInstance().getBulletinPaie(empl.getId(), datePaie, exercice.getId());
	     if (bulletin!=null) {
	      
	     EditionBulletinB edit = new EditionBulletinB();
	       edit.setBulletin(bulletin);
	       edit.setEmploye(empl);
	     edit.printBulletin();
	   } else {
	      
	    HelperC.afficherAttention("ATTENTION", "Il n'y a pas de bulletin � cette date");
	    } 
	  }
}
