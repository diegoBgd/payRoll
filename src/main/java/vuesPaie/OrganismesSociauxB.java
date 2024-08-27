package vuesPaie;

import classesPaie.Base;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.OrganismeSupportantBaseSalarialC;
import classesPaie.Tables;
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
public class OrganismesSociauxB extends Base {
	private static final long serialVersionUID = 7480784560012895665L;
	private OrganismeSupportantBaseSalarialC organisme;
	private List<OrganismeSupportantBaseSalarialC> listOrganismes;
	private OperateurC operateur;
	private ExerciceC exercice;
	private HttpSession session = HelperC.getSession();
	private String compteCpb, libelleCpte;
	private Base selectedCpt;
	private List<Base> listCpte;
	private boolean disableMsg;
	
	public OrganismeSupportantBaseSalarialC getOrganisme() {
		return organisme;
	}

	public void setOrganisme(OrganismeSupportantBaseSalarialC organisme) {
		this.organisme = organisme;
	}

	public List<OrganismeSupportantBaseSalarialC> getListOrganismes() {
		return listOrganismes;
	}

	public void setListOrganismes(List<OrganismeSupportantBaseSalarialC> listOrganismes) {
		this.listOrganismes = listOrganismes;
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

	public String getCompteCpb() {
		return compteCpb;
	}

	public void setCompteCpb(String compteCpb) {
		this.compteCpb = compteCpb;
	}

	public String getLibelleCpte() {
		return libelleCpte;
	}

	public void setLibelleCpte(String libelleCpte) {
		this.libelleCpte = libelleCpte;
	}

	public Base getSelectedCpt() {
		return selectedCpt;
	}

	public void setSelectedCpt(Base selectedCpt) {
		this.selectedCpt = selectedCpt;
	}

	public List<Base> getListCpte() {
		return listCpte;
	}

	public void setListCpte(List<Base> listCpte) {
		this.listCpte = listCpte;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	@PostConstruct
	private void init() {
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
			disableMsg=true;
			chargement();
		}

	}

	private void chargement() {
		listOrganismes = FichierBaseDAO.getInstance().getAllOrganismeSupportantBaseSalarial();
	}

	public void changeCode() {
		if (getCode() != null && !getCode().equals("")) {

			organisme = FichierBaseDAO.getInstance().getOrganismeSupportantBaseSalariale(getCode());
			affecter();
		}

	}

	private void clear(boolean b) {
		if (b) {
			setCode("");
		}
		setId(0);
		this.organisme = null;
		setDesignation("");
		compteCpb="";
		selectedCpt=null;
		libelleCpte="";
	}

	public void takeSelectedOrganisme() {
		affecter();
	}

	private void affecter() {
		disableMsg=true;
		if (this.organisme != null) {
			disableMsg=false;
			setId(this.organisme.getId());
			setCode(this.organisme.getCode());
			setDesignation(this.organisme.getDesignation());
			compteCpb=organisme.getCompteCptbl();
			searchCompte();
		}
	}

	public void initialiser() {
		clear(true);
		disableMsg=true;
	}

	public void enregistrer() {
		if (getCode().trim().equals("") || getDesignation().trim().equals("")) {

			HelperC.afficherMessage("Information", "Code et libellé obligatoires");
			return;
		}
		if (organisme == null)
			organisme = new OrganismeSupportantBaseSalarialC();
		organisme.setId(getId());
		organisme.setCode(getCode());
		organisme.setDesignation(getDesignation());
		organisme.setCompteCptbl(compteCpb);

		if (FichierBaseDAO.getInstance().insertUpdateOrganismeSupportantBaseSalariale(organisme)) {

			 initialiser();
			chargement();
		} else {

			HelperC.afficherMessage("Désolé", "Echec de l'Opération");
		}

	}

	public void supprimer() {
		if (organisme == null || organisme.getId() == 0) {

			HelperC.afficherMessage("Information", "Précisez l'élément à  supprimer");
			return;
		}
		FichierBaseDAO.getInstance().delete(organisme.getId(), Tables.getTableName(Tables.TableName.organismesSociaux));
		chargement();
		 initialiser();
	}

	public void chargerCompte() {
		listCpte = LiaisonComptaDAO.getConnection().getPlanComptable("", "");
	}

	public void takeSelectedCompte() {
		setCompteValue();
		PrimeFaces.current().executeScript("PF('dlgCpt').hide();");
	}

	public void searchCompte() {
		if (compteCpb != null && !compteCpb.equals("")) {
			selectedCpt = LiaisonComptaDAO.getConnection().getCompte(compteCpb);
			setCompteValue();
		}
	}

	private void setCompteValue() {
		if (selectedCpt != null) {
			compteCpb = selectedCpt.getCode();
			libelleCpte = selectedCpt.getDesignation();

		}
	}

}
