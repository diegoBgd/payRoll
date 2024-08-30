package vuesPaie;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.LiaisonComptaC;
import classesPaie.OperateurC;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class LiaisonComptaB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5713831546812118723L;
	ExerciceC exercice; 
	Base userFonction;
	HttpSession session;
	OperateurC operateur;
	DroitC droit;
	LiaisonComptaC link;
	int idL;
	
	private String utilisateur,motPasse,nomServeur,baseDonne;
	private boolean disableMsg;
	
	
	public LiaisonComptaB() {
		 this.session = HelperC.getSession();
	}
	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getNomServeur() {
		return nomServeur;
	}

	public void setNomServeur(String nomServeur) {
		this.nomServeur = nomServeur;
	}

	public String getBaseDonne() {
		return baseDonne;
	}

	public void setBaseDonne(String baseDonne) {
		this.baseDonne = baseDonne;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	@PostConstruct
	  public void init() {
		disableMsg=true;
	    String codeOperateur = (String)this.session.getAttribute("operateur");
	    String codeExercice = (String)this.session.getAttribute("exercice");
	    this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
	    this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
	    if (this.operateur == null || this.exercice == null) {

	      
	      try {
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.getExternalContext().redirect("/payRoll/login.jsf");
	      }
	      catch (IOException e) {
	        
	        e.printStackTrace();
	      } 
	    } else {
	      
	      this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
	      if (this.userFonction != null)
	      {
	        this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
	      }
	      chargerLiaison();
	    } 
	  }
	
	private void chargerLiaison() {
		link=FichierBaseDAO.getInstance().getLiaisonCompta();
		if(link!=null)
		{
			idL=link.getId();
			baseDonne=link.getDataBase();
			motPasse=link.getPassWord();
			nomServeur=link.getServerName();
			utilisateur=link.getUserCode();
			disableMsg=false;
		}
		else {
			initialiser();
			
		}
	}

	public void initialiser() {
		idL=0;
		baseDonne="";
		motPasse="";
		nomServeur="";
		utilisateur="";
		disableMsg=true;
		link=null;
	}
	
	public void save() {
		if (idL== 0 && !this.droit.isCreer()) {
		      
		      HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
		      return;
		    }
		if (idL> 0 && !this.droit.isModifier()) {
		      
			 HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
		      return;
		    }
		if(link==null)
			link=new LiaisonComptaC();
		link.setId(idL);
		link.setDataBase(baseDonne);
		link.setPassWord(motPasse);
		link.setServerName(nomServeur);
		link.setUserCode(utilisateur);
		
		if(FichierBaseDAO.getInstance().insertUpdateLiaisonCompta(link))
		{
			 HelperC.afficherInformation("Info", "succès de l'Opération");
			 chargerLiaison();
		}
		else
			HelperC.afficherErreur("Info!", "Echec de l'Opération");
	}
	public void delete() {
		if(link!=null && link.getId()>0)
		{
			if(FichierBaseDAO.getInstance().deleteLiaisonCompta(link))
			{
				HelperC.afficherInformation("Info", "succès de l'Opération");
				 chargerLiaison();
			}
			else
				HelperC.afficherErreur("Info!", "Echec de l'Opération");
		}
	}
}
