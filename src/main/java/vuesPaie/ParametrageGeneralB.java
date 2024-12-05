package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.LiaisonComptaC;
import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import persistencePaie.FichierBaseDAO;
import persistencePaie.LiaisonComptaDAO;

@ManagedBean
@ViewScoped
public class ParametrageGeneralB extends ParametrageGeneralC {
	private static final long serialVersionUID = -8346467593125395780L;
	private ParametrageGeneralC parametre;
	private String cpteChrg, cpteSal, cpteAvc, libelleChrg, libelleSal, libelleAvc;
	private DroitC droit;
	private boolean disableMsg;
	private List<Base> listCpteChrg,listCptePaie,listCpteAvc;
	private Base selectedCpteCharge, selectedCpteSalaire, selectedCpteAvance;
	
	OperateurC operateur;
	ExerciceC exercice;
	HttpSession session = HelperC.getSession();
	
	LiaisonComptaC liaison;
	
	public ParametrageGeneralC getParametre() {
		return this.parametre;
	}

	public void setParametre(ParametrageGeneralC parametre) {
		this.parametre = parametre;
	}

	public DroitC getDroit() {
		return this.droit;
	}

	public void setDroit(DroitC droit) {
		this.droit = droit;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	public String getCpteChrg() {
		return cpteChrg;
	}

	public void setCpteChrg(String cpteChrg) {
		this.cpteChrg = cpteChrg;
	}

	public String getCpteSal() {
		return cpteSal;
	}

	public void setCpteSal(String cpteSal) {
		this.cpteSal = cpteSal;
	}

	public String getCpteAvc() {
		return cpteAvc;
	}

	public void setCpteAvc(String cpteAvc) {
		this.cpteAvc = cpteAvc;
	}

	public String getLibelleChrg() {
		return libelleChrg;
	}

	public void setLibelleChrg(String libelleChrg) {
		this.libelleChrg = libelleChrg;
	}

	public String getLibelleSal() {
		return libelleSal;
	}

	public void setLibelleSal(String libelleSal) {
		this.libelleSal = libelleSal;
	}

	public String getLibelleAvc() {
		return libelleAvc;
	}

	public void setLibelleAvc(String libelleAvc) {
		this.libelleAvc = libelleAvc;
	}

	public List<Base> getListCpteChrg() {
		return listCpteChrg;
	}

	public void setListCpteChrg(List<Base> listCpteChrg) {
		this.listCpteChrg = listCpteChrg;
	}

	public List<Base> getListCptePaie() {
		return listCptePaie;
	}

	public void setListCptePaie(List<Base> listCptePaie) {
		this.listCptePaie = listCptePaie;
	}

	public List<Base> getListCpteAvc() {
		return listCpteAvc;
	}

	public void setListCpteAvc(List<Base> listCpteAvc) {
		this.listCpteAvc = listCpteAvc;
	}

	public Base getSelectedCpteCharge() {
		return selectedCpteCharge;
	}

	public void setSelectedCpteCharge(Base selectedCpteCharge) {
		this.selectedCpteCharge = selectedCpteCharge;
	}

	public Base getSelectedCpteSalaire() {
		return selectedCpteSalaire;
	}

	public void setSelectedCpteSalaire(Base selectedCpteSalaire) {
		this.selectedCpteSalaire = selectedCpteSalaire;
	}

	public Base getSelectedCpteAvance() {
		return selectedCpteAvance;
	}

	public void setSelectedCpteAvance(Base selectedCpteAvance) {
		this.selectedCpteAvance = selectedCpteAvance;
	}

	@PostConstruct
	private void init() {
		disableMsg = true;
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

			Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (userFonction != null) {
				this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.parametrage);
			}
			liaison=FichierBaseDAO.getInstance().getLiaisonCompta();
			chargement();
		}
	}

	private void chargement() {
		this.parametre = FichierBaseDAO.getInstance().getParametrageGeneral();
		if (this.parametre != null) {
			disableMsg = false;
			setId(this.parametre.getId());
			setNbreDecimal(this.parametre.getNbreDecimal());
			setTauxMaxLogement(this.parametre.getTauxMaxLogement());
			setAllocationBaseHsup(this.parametre.isAllocationBaseHsup());
			setLogementBaseHsup(this.parametre.isLogementBaseHsup());
			setDureCourTerme(this.parametre.getDureCourTerme());
			setDureLongTerme(this.parametre.getDureLongTerme());
			setDureMoyenTerme(this.parametre.getDureMoyenTerme());
			setMontantNetMin(this.parametre.getMontantNetMin());
			setNbreHeureJour(this.parametre.getNbreHeureJour());
			setNbreHeureMois(this.parametre.getNbreHeureJour() * this.parametre.getNbreHeureJour());
			setNbreJourMois(this.parametre.getNbreJourMois());
			setTauxJrFerie(this.parametre.getTauxJrFerie());
			setMailOrigine(this.parametre.getMailOrigine());
			setPwdOrigine(this.parametre.getPwdOrigine());
			setPort(this.parametre.getPort());
			setSmtpServer(this.parametre.getSmtpServer());
			cpteAvc=parametre.getCompteAvance();
			cpteChrg=parametre.getCompteCharge();
			cpteSal=parametre.getCompteSalaire();
			searchCompteAvc();
			searchCompteChrg();
			searchComptePaie();
		}
	}

	
	public void chargerCompteChrg() {
		if(liaison!=null)
		listCpteChrg=LiaisonComptaDAO.getConnection(liaison).getPlanComptable("", "");
	}
	public void chargerComptePaie() {
		if(liaison!=null)
		listCptePaie=LiaisonComptaDAO.getConnection(liaison).getPlanComptable("", "");
	}
	public void chargerCompteAvc() {
		if(liaison!=null)
		listCpteAvc=LiaisonComptaDAO.getConnection(liaison).getPlanComptable("", "");
	}
	
	public void takeSelectedCompteAvc() {
		setCompteAvcValue();
		PrimeFaces.current().executeScript("PF('dlgCptAv').hide();");
	}
	
	public void takeSelectedCompteSal() {
		setCompteSalValue();
		PrimeFaces.current().executeScript("PF('dlgCptPy').hide();");
	}
	public void takeSelectedCompteChrg() {
		setCompteChrgValue();
		PrimeFaces.current().executeScript("PF('dlgCptChrg').hide();");
	}
	public void searchCompteAvc() {
		if(cpteAvc!=null && !cpteAvc.equals(""))
		{
			if(liaison!=null)
			{
			selectedCpteAvance=LiaisonComptaDAO.getConnection(liaison).getCompte(cpteAvc);
			setCompteAvcValue();
			}
		}
	}
	public void searchCompteChrg() {
		if(cpteChrg!=null && !cpteChrg.equals(""))
		{
			if(liaison!=null)
			{
			selectedCpteCharge=LiaisonComptaDAO.getConnection(liaison).getCompte(cpteChrg);
			setCompteChrgValue();
			}
		}
	}
	public void searchComptePaie() {
		if(cpteSal!=null && !cpteSal.equals(""))
		{
			if(liaison!=null)
			{
			selectedCpteSalaire=LiaisonComptaDAO.getConnection(liaison).getCompte(cpteSal);
			setCompteSalValue();
			}
		}
	}
	private void setCompteChrgValue() {
		if(selectedCpteCharge!=null)
		{
			cpteChrg=selectedCpteCharge.getCode();		
			libelleChrg=selectedCpteCharge.getDesignation();
			
		}
	}
	private void setCompteSalValue() {
		if(selectedCpteSalaire!=null)
		{
			cpteSal=selectedCpteSalaire.getCode();		
			libelleSal=selectedCpteSalaire.getDesignation();
			
		}
	}
	private void setCompteAvcValue() {
		if(selectedCpteAvance!=null)
		{
			cpteAvc=selectedCpteAvance.getCode();		
			libelleAvc=selectedCpteAvance.getDesignation();
			
		}
	}
	public void save() {
		setCompteAvance(cpteAvc);
		setCompteSalaire(cpteSal);
		setCompteCharge(cpteChrg);
		
		if (getId() == 0 && (this.droit == null || !this.droit.isCreer())) {

			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer le bulletin ");
			return;
		}
		if (getId() > 0 && (this.droit == null || !this.droit.isModifier())) {

			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier le bulletin ");
			return;
		}
		if (FichierBaseDAO.getInstance().insertUpdateParametrageGeneral(this)) {

			HelperC.afficherMessage("Information", "Succès de l'opération ");
			initialise();
			chargement();
		} else {

			HelperC.afficherMessage("Désolé", "Echec de l'opération ");
		}
	}

	public void delete() {
		if (getId() > 0) {

			if (this.droit == null || !this.droit.isSupprimer()) {

				HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer le paramétrage ! ");
				return;
			}
			if (FichierBaseDAO.getInstance().deleteParametrage(this)) {

				HelperC.afficherMessage("Information", "Succès de l'opération ");
				initialise();
			}
		}
	}

	public void initialise() {
		setId(0);
		setNbreDecimal(0);
		setTauxMaxLogement(0.0D);
		setDureCourTerme(0);
		setDureLongTerme(0);
		setDureMoyenTerme(0);
		setMontantNetMin(0.0D);
		setNbreHeureJour(0);
		setNbreHeureMois(0);
		setNbreJourMois(0);
		setTauxJrFerie(0.0D);
		setAllocationBaseHsup(false);
		setLogementBaseHsup(false);
		setMailOrigine("");
		setPwdOrigine("");
		setPort("");
		setSmtpServer("");

		disableMsg = true;
	}
}
