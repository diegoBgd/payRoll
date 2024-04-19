package vuesPaie;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import classesPaie.Base;
import classesPaie.BulletinPaieC;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

public class PaimentB {
	HttpSession session = HelperC.getSession(); 
	OperateurC operateur;
	ExerciceC exercice;
	Base userFonction;
	DroitC droit;
	private String printDatePaie;
	Date datePaie;
	
	private List<BulletinPaieC>listPaie;
	
	public PaimentB() {
		
	}

	
	public List<BulletinPaieC> getListPaie() {
		return listPaie;
	}
	public void setListPaie(List<BulletinPaieC> listPaie) {
		this.listPaie = listPaie;
	}
	public String getPrintDatePaie() {
		return printDatePaie;
	}
	public void setPrintDatePaie(String printDatePaie) {
		this.printDatePaie = printDatePaie;
	}

	@PostConstruct
	public void init() {
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);

		if (this.operateur == null || this.exercice == null) {

			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {

			userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (userFonction != null) {
				droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.bulletinPaie);
			}

		}
	}

	public void chargerPaie(){
		if(droit==null) {
			return;
		}
		if(datePaie==null) {
			return;
		}
		listPaie=FactoryDAO.getInstance().getListBulletinPaie(0, datePaie, datePaie, exercice.getId());
	}

}
