package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.CotisationC;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import classesPaie.Tables;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import persistencePaie.FichierBaseDAO;
import persistencePaie.LiaisonComptaDAO;

@ManagedBean
@ViewScoped
public class CotisationB extends CotisationC {
	private static final long serialVersionUID = 1296717958515546530L;
	private CotisationC cotisation;
	private int idBarm, typElement;
	private int idOrganismeSocial;
	private List<CotisationC> listCotisation;
	private OperateurC operateur;
	private ExerciceC exercice;
	private Base selectedOrganisme,selectedCpt;
	private List<SelectItem> listOrganismesSociaux;
	private List<SelectItem> listBareme;
	private List<Base> listeOrganisme,listCpte;
	private String codeOrganisme;
	private String libelleOrganisme,compteCpb,libelleCpte;
	private HttpSession session = HelperC.getSession();
	private boolean disableMsg;

	private DroitC droit;

	Base userFonction;

	public CotisationC getCotisation() {
		return this.cotisation;
	}

	public void setCotisation(CotisationC cotisation) {
		this.cotisation = cotisation;
	}

	public int getIdBarm() {
		return this.idBarm;
	}

	public void setIdBarm(int idBarm) {
		this.idBarm = idBarm;
	}

	public Base getSelectedOrganisme() {
		return this.selectedOrganisme;
	}

	public void setSelectedOrganisme(Base selectedOrganisme) {
		this.selectedOrganisme = selectedOrganisme;
	}

	public List<SelectItem> getListOrganismesSociaux() {
		return this.listOrganismesSociaux;
	}

	public void setListOrganismesSociaux(List<SelectItem> listOrganismesSociaux) {
		this.listOrganismesSociaux = listOrganismesSociaux;
	}

	public List<Base> getListeOrganisme() {
		return this.listeOrganisme;
	}

	public void setListeOrganisme(List<Base> listeOrganisme) {
		this.listeOrganisme = listeOrganisme;
	}

	public int getIdOrganismeSocial() {
		return this.idOrganismeSocial;
	}

	public void setIdOrganismeSocial(int idOrganismeSocial) {
		this.idOrganismeSocial = idOrganismeSocial;
	}

	public String getCodeOrganisme() {
		return this.codeOrganisme;
	}

	public void setCodeOrganisme(String codeOrganisme) {
		this.codeOrganisme = codeOrganisme;
	}

	public String getLibelleOrganisme() {
		return this.libelleOrganisme;
	}

	public void setLibelleOrganisme(String libelleOrganisme) {
		this.libelleOrganisme = libelleOrganisme;
	}

	public List<CotisationC> getListCotisation() {
		return this.listCotisation;
	}

	public void setListCotisation(List<CotisationC> listCotisation) {
		this.listCotisation = listCotisation;
	}

	public List<SelectItem> getListBareme() {
		return this.listBareme;
	}

	public void setListBareme(List<SelectItem> listBareme) {
		this.listBareme = listBareme;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	public int getTypElement() {
		return typElement;
	}

	public void setTypElement(int typElement) {
		this.typElement = typElement;
	}

	public String getCompteCpb() {
		return compteCpb;
	}

	public void setCompteCpb(String compteCpb) {
		this.compteCpb = compteCpb;
	}

	public List<Base> getListCpte() {
		return listCpte;
	}

	public void setListCpte(List<Base> listCpte) {
		this.listCpte = listCpte;
	}

	public Base getSelectedCpt() {
		return selectedCpt;
	}

	public void setSelectedCpt(Base selectedCpt) {
		this.selectedCpt = selectedCpt;
	}

	public String getLibelleCpte() {
		return libelleCpte;
	}

	public void setLibelleCpte(String libelleCpte) {
		this.libelleCpte = libelleCpte;
	}

	@PostConstruct
	private void charger() {
		this.operateur = new OperateurC();
		this.exercice = new ExerciceC();

		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
		disableMsg = true;
		if (this.operateur == null || this.exercice == null) {
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (this.userFonction != null)
				this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
						Constante.Role.fichierBase);
			UIComponent frm = null;
			FacesContext context = FacesContext.getCurrentInstance();
			frm = context.getViewRoot().findComponent("frmCot");
			if (frm != null) {
				typElement = 0;
			}
			frm = context.getViewRoot().findComponent("frmCom");
			if (frm != null) {
				typElement = 1;
			}
			setTypeElement(typElement);
			chargementOrganisme();
			chargementCotisation();
		}
	}

	public void changeCode() {
		disableMsg = true;
		if (getCode().trim().equals("")) {

			clear();
		} else {

			this.cotisation = FichierBaseDAO.getInstance().getCotisation(getCode());
			if (this.cotisation == null) {

				clear();
			} else {

				setCotisationValues();
			}
		}
	}

	private void chargementOrganisme() {
		this.listOrganismesSociaux = new ArrayList<SelectItem>();
		this.listeOrganisme = FichierBaseDAO.getInstance()
				.getAllBase(Tables.getTableName(Tables.TableName.organismesSociaux));
		this.listOrganismesSociaux.add(new SelectItem(Integer.valueOf(0), ""));
		for (Base organisme : this.listeOrganisme) {
			if (organisme != null)
				this.listOrganismesSociaux
						.add(new SelectItem(Integer.valueOf(organisme.getId()), organisme.getDesignation()));
		}
	}

	public void changeOrganismeSocial(ValueChangeEvent event) {
		this.idOrganismeSocial = ((Integer) event.getNewValue()).intValue();
		setIdOrganisme(this.idOrganismeSocial);
	}

	private void setCotisationValues() {
		setId(this.cotisation.getId());
		setCode(this.cotisation.getCode());
		setDesignation(this.cotisation.getDesignation());
		setObligatoire(this.cotisation.isObligatoire());
		setRetraite(cotisation.isRetraite());
		setChargePtronal(compteCpb);
		this.idOrganismeSocial = this.cotisation.getIdOrganisme();
		setTypeBaremme(this.cotisation.getTypeBaremme());
		setIdOrganisme(this.idOrganismeSocial);
		setChargePtronal(cotisation.getChargePtronal());
		setChargeSalarial(cotisation.getChargeSalarial());
		setPrefixePatronal(cotisation.getPrefixePatronal());
		setPrefixeSalarial(cotisation.getPrefixeSalarial());
		compteCpb=cotisation.getChargePtronal();
		searchCompte();
		disableMsg = false;
	}

	public void chargementCotisation() {
		this.listCotisation = FichierBaseDAO.getInstance().getListCotisation(typElement);

	}

	public void onCotisationSelected(SelectEvent event) {
		this.cotisation = (CotisationC) event.getObject();
		disableMsg = true;
		setCotisationValues();
		PrimeFaces.current().executeScript("PF('dlgCotisation').hide();");
	}

	private void clear() {
		setId(0);
		setDesignation("");
		setTypeBaremme(0);
		setChargePtronal("");
		setChargeSalarial("");
		setPrefixePatronal("");
		setPrefixeSalarial("");
		this.idOrganismeSocial = 0;
		this.idBarm = 0;
		this.cotisation = null;
		setHide(false);
		setObligatoire(false);
		setRetraite(false);
		disableMsg = true;
		compteCpb="";
		selectedCpt=null;
		libelleCpte="";
	}

	public void initialize() {
		setCode("");
		clear();
		clearOrganisme();
	}

	public void enregistrer() {
		if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
			HelperC.afficherMessage("Information", "Completez tous les champs nécessaires");

		} else {

			if (getId() == 0)
				if (this.droit.isCreer()) {
					if (FichierBaseDAO.getInstance().insertUpdateCotisation(this)) {
						initialize();
						chargementCotisation();

						HelperC.afficherMessage("Information", "Succès de l'opération");
						charger();
					} else {
						HelperC.afficherMessage("Désolé", "Echec de l'opération");
					}
				} else {
					HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer ");
				}
			if (getId() > 0) {
				if (this.droit.isModifier()) {
					if (FichierBaseDAO.getInstance().insertUpdateCotisation(this)) {
						initialize();
						HelperC.afficherMessage("Information", "Succès de l'opération");
						charger();
						chargementCotisation();
					} else {
						HelperC.afficherMessage("Désolé", "Echec de l'opération");
					}
				} else {
					HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier ");
				}
			}
		}
	}

	public void supprimer() {
		if (this.cotisation == null) {
			HelperC.afficherDeleteMessage();
		} else if (this.droit.isSupprimer()) {
			if (FichierBaseDAO.getInstance().delete(this.cotisation.getId(),
					Tables.getTableName(Tables.TableName.cotisation))) {
				initialize();
				chargementCotisation();

				HelperC.afficherMessage("Information", "Succès de l'opération ");
			} else {
				HelperC.afficherMessage("Désolé", "Echec de suppression");
			}
		} else {
			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
		}
	}

	public void searchOrgenisme() {
		if (!this.codeOrganisme.equals("")) {
			this.selectedOrganisme = FichierBaseDAO.getInstance().getBaseByCode(this.codeOrganisme,
					Tables.getTableName(Tables.TableName.organismesSociaux));
			if (this.selectedOrganisme != null) {
				this.libelleOrganisme = this.selectedOrganisme.getDesignation();
			} else {
				this.libelleOrganisme = "";
			}
		}
	}

	public void onRowOrganSelected(SelectEvent event) {
		this.selectedOrganisme = (Base) event.getObject();
		if (this.selectedOrganisme != null) {
			this.codeOrganisme = this.selectedOrganisme.getCode();
			this.libelleOrganisme = this.selectedOrganisme.getDesignation();
		}
	}

	public void saveOrganisme() {
		if (!this.codeOrganisme.equals("")) {
			if (this.selectedOrganisme == null)
				this.selectedOrganisme = new Base();
			this.selectedOrganisme.setCode(this.codeOrganisme);
			this.selectedOrganisme.setDesignation(this.libelleOrganisme);

			FichierBaseDAO.getInstance().insertUpdateBase(this.selectedOrganisme,
					Tables.getTableName(Tables.TableName.organismesSociaux));
			chargementOrganisme();
			clearOrganisme();
		}
	}

	public void deleteOrganisme() {
		if (this.selectedOrganisme != null && this.selectedOrganisme.getId() > 0) {
			FichierBaseDAO.getInstance().delete(this.selectedOrganisme.getId(),
					Tables.getTableName(Tables.TableName.organismesSociaux));
			chargementOrganisme();
			clearOrganisme();
		}
	}

	public void clearOrganisme() {
		this.codeOrganisme = "";
		this.libelleOrganisme = "";
		this.selectedOrganisme = null;
	}
	
	public void chargerCompte() {
		listCpte=LiaisonComptaDAO.getConnection().getPlanComptable("", "");
	}
	public void takeSelectedCompte() {
		setCompteValue();
		PrimeFaces.current().executeScript("PF('dlgCpt').hide();");
	}
	public void searchCompte() {
		if(compteCpb!=null && !compteCpb.equals(""))
		{
			selectedCpt=LiaisonComptaDAO.getConnection().getCompte(compteCpb);
			setCompteValue();
		}
	}
	private void setCompteValue() {
		if(selectedCpt!=null)
		{
			compteCpb=selectedCpt.getCode();		
			libelleCpte=selectedCpt.getDesignation();
			this.setChargePtronal(compteCpb);
		}
	}
	
}
