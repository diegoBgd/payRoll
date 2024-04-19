package editionPaie;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
import classesPaie.EmployeC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
@ManagedBean
@ViewScoped
public class FicheEmployeB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845488903188875450L;
	EmployeC empl;
	HttpSession session = HelperC.getSession();
	OperateurC oper;
	private String code,nom;
	
	public FicheEmployeB() {
		
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
	@PostConstruct
	 private void init() {
	    
	    String codeOperateur = (String)this.session.getAttribute("operateur");
	    String codeExercice = (String)this.session.getAttribute("exercice");
	     if (codeOperateur == null || codeExercice == null) {
	 
	      
	      try {
	         FacesContext context = FacesContext.getCurrentInstance();
	        context.getExternalContext().redirect("/payRoll/login.xhtml");
	     }
	      catch (IOException e) {
	         
	        e.printStackTrace();
	       } 
	     } else {
	    	
	    	 oper=FichierBaseDAO.getInstance().getOperateur(codeOperateur);
	    	
	    	 searchEmploye();
	     }
	    	 
	     }
		private void searchEmploye(){
		empl=FactoryDAO.getInstance().getEmploye(oper.getIdEmploye(), false);
		if(empl!=null)
		{
			code=empl.getCode();
			nom=empl.getNomPrenom();
		}
		
		
	}
		public void visualiserFiche() {
		    EditionFicheEmmployeB fiche = new EditionFicheEmmployeB();
		     fiche.setEmploye(empl);
		    fiche.visualiser();
	  }

}
