package vuesPaie;

import java.io.IOException;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import classesPaie.EmployeC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
import persistencePaie.UtilitaireDAO;

@ManagedBean
@ViewScoped
public class ModifPasseWordB {
	HttpSession session = HelperC.getSession();
    private EmployeC selectedEmploye;
    private String infoMsg,codeLine,matricule,mail,nom;
    ParametrageGeneralC parm;
    OperateurC user;
    
    public ModifPasseWordB()
	{
		
	}

	

	public EmployeC getSelectedEmploye() {
		return selectedEmploye;
	}

	public void setSelectedEmploye(EmployeC selectedEmploye) {
		this.selectedEmploye = selectedEmploye;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public String getCodeLine() {
		return codeLine;
	}

	public void setCodeLine(String codeLine) {
		this.codeLine = codeLine;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	   @PostConstruct
	   public void init() {
	   
	    parm=FichierBaseDAO.getInstance().getParametrageGeneral();
	  }


	/***
	    * ------------------------------------ GENERER UN CODE POUR LES SERVICES EN LIGNE ---------------------
	    */
	  
	  public void chercherEmploye() {
		  nom="";mail="";
		  selectedEmploye=FactoryDAO.getInstance().getEmployeSimple(matricule);
		  if(selectedEmploye!=null)
		  {
			  nom=selectedEmploye.getNomPrenom();
			  mail=selectedEmploye.getEmail();
			  user=FichierBaseDAO.getInstance().getOperateur(selectedEmploye);
			
			  if(user==null)
			  {
				  HelperC.afficherAttention("ATTENTION", "L' employ� de matricule "+matricule +" n'est pas utlisateur du logiciel!");
			  }
			 // getCodeServiceLigne();
		  }
		  else
		  {
			  HelperC.afficherAttention("ATTENTION", "L' employ� de matricule "+matricule +" n'existe pas !");
		  }
		  
	  }
	  private void getCodeServiceLigne() {
		  if(selectedEmploye!=null)
			  codeLine=UtilitaireDAO.getInstance().getCodeLine(selectedEmploye.getId());
	  }
	  public void redirectionLogin() {
		    FacesContext context = FacesContext.getCurrentInstance();
		     try {
		       context.getExternalContext().redirect("/payRoll/login.jsf");
		    } catch (IOException e) {
		      
		       e.printStackTrace();
		    } 
		  }
	  
	  public void sendPwdToMail()
	  {
		  if(parm!=null)
		  {
		  if(selectedEmploye!=null)
		  {
			 if(selectedEmploye.getEmail()!=null && !selectedEmploye.getEmail().equals(""))
			 {
				 if(user!=null)
				 {
					 HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(), parm.getPwdOrigine(), mail, user.getCodeSecret(), "Votre code secret");
				 }
				 else
					  HelperC.afficherAttention("ATTENTION", "Il faut d'abord �tre utilisateur !");
			 }
			 HelperC.afficherAttention("ATTENTION", "Il faut avoir une adresse e-mail sur la fiche employ� !");
		  }
		  else
			   HelperC.afficherAttention("ATTENTION", "Il faut pr�ciser l'employ�!");
		  }
		  else
			  HelperC.afficherAttention("ATTENTION", "Il faut v�rifier le parametrage des mails !");
	  }
	  public void generateCode() {
		  if(selectedEmploye!=null)
		  {
			 String mot="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			 int longMot=5;
			  if(user!=null)
			  {
			 if(codeLine==null || codeLine.equals("") )
				 codeLine= generateRandomChars(mot, longMot);
			 
			 if(codeLine!=null && !codeLine.equals(""))
			 {
				 
				 if(UtilitaireDAO.getInstance().updateCodeLine(selectedEmploye.getId(),codeLine))
					 getCodeServiceLigne();
				 if(selectedEmploye.getEmail()!=null && !selectedEmploye.getEmail().equals("") && parm!=null)
					 HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(), parm.getPwdOrigine(), mail, codeLine, "Nouveau code secret");
			 }
			  }
			  else
				  HelperC.afficherAttention("ATTENTION", "Il faut d'abord �tre utilisateur !");
		  }
		  else
			   HelperC.afficherAttention("ATTENTION", "Il faut pr�ciser l'employ�!");
	  }
	  private  String generateRandomChars(String candidateChars, int length) {
		    StringBuilder sb = new StringBuilder();
		    Random random = new Random();
		    for (int i = 0; i < length; i++) {
		        sb.append(candidateChars.charAt(random.nextInt(candidateChars
		                .length())));
		    }

		    return sb.toString();
		}
}
