package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DetailPrimeC;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import classesPaie.PrimeIndemniteC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import persistencePaie.FichierBaseDAO;
import persistencePaie.LiaisonComptaDAO;

@ManagedBean
@ViewScoped
public class PrimeIndemniteB extends PrimeIndemniteC {
	private static final long serialVersionUID = 8575093108194785390L;
	private int idPrime;
	private double tauxPrime;
	private double plafondPrime;
	private double plancherPrime;
	private String type;
	private PrimeIndemniteC selected;
	private Base selectedCpt;
	private DetailPrimeC detail;
	private List<SelectItem> primes;
	private List<PrimeIndemniteC> listePrime;
	private List<Base> listCpte;
	private OperateurC operateur;
	private DroitC droit;
	private boolean disable, disableMsg;
	private String compteCpb,libelleCpte;
	
	private ExerciceC exercice;
	private HttpSession session;
	HttpServletRequest request;
	Base userFonction;

	public List<PrimeIndemniteC> getListePrime() {
		return this.listePrime;
	}

	public void setListePrime(List<PrimeIndemniteC> listePrime) {
		this.listePrime = listePrime;
	}

	public int getIdPrime() {
		return this.idPrime;
	}

	public void setIdPrime(int idPrime) {
		this.idPrime = idPrime;
	}

	public PrimeIndemniteB() {
		this.session = HelperC.getSession();
	}

	public double getTauxPrime() {
		return this.tauxPrime;
	}

	public void setTauxPrime(double tauxPrime) {
		this.tauxPrime = tauxPrime;
	}

	public double getPlafondPrime() {
		return this.plafondPrime;
	}

	public void setPlafondPrime(double plafondPrime) {
		this.plafondPrime = plafondPrime;
	}

	public double getPlancherPrime() {
		return this.plancherPrime;
	}

	public void setPlancherPrime(double plancherPrime) {
		this.plancherPrime = plancherPrime;
	}

	public PrimeIndemniteC getSelected() {
		return this.selected;
	}

	public void setSelected(PrimeIndemniteC selected) {
		this.selected = selected;
	}

	public DetailPrimeC getDetail() {
		return this.detail;
	}

	public void setDetail(DetailPrimeC detail) {
		this.detail = detail;
	}

	public List<SelectItem> getPrimes() {
		return this.primes;
	}

	public void setPrimes(List<SelectItem> primes) {
		this.primes = primes;
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

	public boolean isDisable() {
		return this.disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
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
			disableMsg = true;
			this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (this.userFonction != null) {
				this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
						Constante.Role.parametrage);
			}

			this.request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

			chargementPrime();
		}
	}

	private void chargement() {
		this.primes = new ArrayList<SelectItem>();
		this.primes.add(new SelectItem(Integer.valueOf(0), ""));

		for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemnite()) {
			this.primes.add(new SelectItem(Integer.valueOf(prm.getId()), prm.getDesignation()));
		}
	}

	public void chargementPrime() {
		this.listePrime = FichierBaseDAO.getInstance().getAllPrimeIndemnite();
	}

	private void clear(boolean b) {
		if (b) {
			setCode("");
		}
		disableMsg = true;
		setId(0);
		setDesignation("");
		setTauxPrime(0.0D);
		setType("");
		setPrefixeComptable("");
		setImposable(false);
		setSoumisCotisation(false);
		setHide(false);
		setRetraite(false);
		this.selected = null;
		compteCpb="";
		libelleCpte="";
		selectedCpt=null;
	}

	public void changeCodeIndemnite() {
		if (!getCode().trim().equals("")) {

			this.selected = FichierBaseDAO.getInstance().getPrimeIndemnite(getCode());
			if (this.selected != null) {

				setObject();
			} else {

				clear(false);
			}
		}
	}

	public void changeCodePrime() {
		if (!getCode().trim().equals("")) {

			this.selected = FichierBaseDAO.getInstance().getPrimeIndemnite(getCode());
			if (this.selected != null) {

				setObject();
			} else {

				clear(false);
			}
		}
	}

	private void setObject() {
		disableMsg = true;
		if (this.selected != null) {
			disableMsg = false;
			compteCpb=selected.getPrefixeComptable();
			setCode(this.selected.getCode());
			setId(this.selected.getId());
			setDesignation(this.selected.getDesignation());
			setPrefixeComptable(compteCpb);
			setType(this.selected.getTypePrime());
			setImposable(this.selected.isImposable());
			setSoumisCotisation(this.selected.isSoumisCotisation());
			setHide(this.selected.isHide());
			setRetraite(this.selected.isRetraite());
			
			searchCompte();
		}
	}

	public void onRowSelected(SelectEvent event) {
		this.selected = (PrimeIndemniteC) event.getObject();
		if (this.selected != null) {

			setObject();
			PrimeFaces.current().executeScript("PF('dlgPrm').hide();");
		}
	}

	public void changeElement(ValueChangeEvent event) {
		this.idPrime = ((Integer) event.getNewValue()).intValue();
		this.selected = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrime);
	}

	public void enregistrerPrime() {
		if (getCode().trim().equals("")) {

			HelperC.afficherMessage("Information", "Précisez le code");
		} else if (getDesignation().trim().equals("")) {

			HelperC.afficherMessage("Information", "Précisez la désignation");
		} else {

			setTauxPrime(this.tauxPrime);
			setTypePrime(this.type);
			setPrefixeComptable(compteCpb);
			
			if (getId() == 0) {
				if (this.droit.isCreer()) {

					if (FichierBaseDAO.getInstance().insertUpdatePrimeIndemnite(this)) {

						HelperC.afficherMessage("Information", "Succès de l'opération ");
						chargementPrime();
						clear(true);
					} else {

						HelperC.afficherMessage("Désolé", "Echec de l'opération ");
					}
				} else {

					HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer ");
				}
			}
			if (getId() > 0) {
				if (this.droit.isModifier()) {

					if (FichierBaseDAO.getInstance().insertUpdatePrimeIndemnite(this)) {

						HelperC.afficherMessage("Information", "Succès de l'opération ");
						chargementPrime();
						clear(true);
					} else {

						HelperC.afficherMessage("Désolé", "Echec de l'opération ");
					}
				} else {

					HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier ");
				}
			}
		}
	}

	public void supprimer() {
		if (getId() == 0) {

			HelperC.afficherDeleteMessage();
		} else if (this.droit.isSupprimer()) {

			if (FichierBaseDAO.getInstance().deletePrimeIndemnite(this)) {

				clear(true);
				HelperC.afficherMessage("Information", "Succes de l'opération");
				chargementPrime();
			} else {

				HelperC.afficherMessage("Désolé", "Echec de suppression ");
			}
		} else {

			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
		}
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
			this.setPrefixeComptable(compteCpb);
		}
	}
	
	public void initialiser() {
		clear(true);
	}
}
