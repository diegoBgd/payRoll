package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.JourFerieC;
import classesPaie.OperateurC;
import classesPaie.Tables;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class JourFerieB extends JourFerieC {
	private static final long serialVersionUID = 8890950578865507200L;
	private JourFerieC selected;
	private OperateurC operateur;
	private ExerciceC exercice;
	public List<JourFerieC> listeHolyDays = new ArrayList<JourFerieC>();
	private String codeExercice;
	private HttpSession session = HelperC.getSession();
	private String designationExercice;
	private String dateFerieS;
	private boolean disableMsg;

	public String getDateFerieS() {
		return this.dateFerieS;
	}

	private DroitC droit;
	Base userFonction;

	public void setDateFerieS(String dateFerieS) {
		this.dateFerieS = dateFerieS;
	}

	public String getDesignationExercice() {
		return this.designationExercice;
	}

	public void setDesignationExercice(String designationExercice) {
		this.designationExercice = designationExercice;
	}

	public String getCodeExercice() {
		return this.codeExercice;
	}

	public void setCodeExercice(String codeExercice) {
		this.codeExercice = codeExercice;
	}

	public JourFerieC getSelected() {
		return this.selected;
	}

	public void setSelected(JourFerieC selected) {
		this.selected = selected;
	}

	public OperateurC getOperateur() {
		return this.operateur;
	}

	public void setOperateur(OperateurC operateur) {
		this.operateur = operateur;
	}

	public HttpSession getSession() {
		return this.session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<JourFerieC> getListeHolyDays() {
		return this.listeHolyDays;
	}

	public void setListeHolyDays(List<JourFerieC> listeHolyDays) {
		this.listeHolyDays = listeHolyDays;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	@PostConstruct
	private void init() {
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
		this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
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
			this.listeHolyDays = FichierBaseDAO.getInstance().getAllJourFerier();
			clear(true);
		}
	}

	public void findByCode() {
		this.selected = FichierBaseDAO.getInstance().getCurrentJourFerier(getCode());
		if (this.selected != null) {

			setObject();
		} else {

			clear(false);
		}
	}

	public void findExerciceByCode() {
		setExercice(FichierBaseDAO.getInstance().getExercice(this.codeExercice));
		if (getExercice() != null) {
			this.designationExercice = getExercice().getDesignation();
		}
	}

	public void changeDateFerie() {
		if (getDateFerieS().replace("/", "").replace("_", "").trim().equals("")) {

			setDateFerie(null);
		} else {

			setDateFerie(HelperC.validerDate(getDateFerieS()));
			if (getDateFerie() == null) {

				setDateFerieS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {

				setDateFerieS(HelperC.convertDate(getDateFerie()));
			}
		}
	}

	private void clear(boolean b) {
		if (b) {
			setCode("");
		}
		setId(0);
		setDesignation("");
		setDateFerie(null);
		setDateFerieS("");
		setTypeJourFerie(1);
		setExercice(null);
		setLibelleJourFerie(Constante.getLibelleBase(getTypeJourFerie()));
		this.codeExercice = "";
		this.designationExercice = "";
		this.selected = null;
	}

	private void setObject() {
		if (this.selected != null) {
			disableMsg = false;
			setId(this.selected.getId());
			setCode(this.selected.getCode());
			setDateFerie(this.selected.getDateFerie());
			this.dateFerieS = HelperC.changeDateFormate(getDateFerie());
			setDesignation(this.selected.getDesignation());
			setLibelleJourFerie(this.selected.getLibelleJourFerie());
			setTypeJourFerie(this.selected.getTypeJourFerie());
			setExercice(this.selected.getExercice());
			if (getExercice() != null) {

				this.codeExercice = getExercice().getCode();
				this.designationExercice = getExercice().getDesignation();
			}
		}
	}

	public void save() {
		setExercice(this.exercice);
		if (getId() == 0 && !this.droit.isCreer()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
		} else if (getId() > 0 && !this.droit.isModifier()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
		} else if (getCode().trim().equals("")) {

			HelperC.afficherMessage("Information", "Précisez le code");
		} else if (getDesignation().trim().equals("")) {

			HelperC.afficherMessage("Information", "Précisez la désignation");
		} else if (getExercice() == null) {

			HelperC.afficherMessage("Information", "Précisez l'exercice");
		} else if (FichierBaseDAO.getInstance().getCurrentJourFerier(getCode(), getId()) != null) {

			HelperC.afficherMessage("Information", "Ce code est déjà  utilisé");
		} else if (FichierBaseDAO.getInstance().getCurrentJourFeriers(getDesignation(), getId()) != null) {

			HelperC.afficherMessage("Information", "Cette désignation est déjà  utilisé");
		} else if (getDateFerie() == null) {

			HelperC.afficherMessage("Information", "Il faut préciser la date du jour férié!");
		} else {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {

				hist.setOperation("Création du jour férier " + getCode());
			} else {

				hist.setOperation("Modification du du jour férier " + getCode());
			}
			hist.setTable(Tables.getTableName(Tables.TableName.jourFerie));
			setHistorique(hist);
			if (FichierBaseDAO.getInstance().insertUpdateJourFerier(this)) {

				HelperC.afficherMessage("Information", "Succè de l'opération");
				this.listeHolyDays = FichierBaseDAO.getInstance().getAllJourFerier();
				clear(true);
			} else {

				HelperC.afficherMessage("Désolé", "Echec de l'opération ");
			}
		}
	}

	public void onRowSelected(SelectEvent event) {
		this.selected = (JourFerieC) event.getObject();
		setObject();
	}

	public void supprimer() {
		if (getId() == 0) {

			HelperC.afficherDeleteMessage();
		} else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.jourFerie), getId())) {

			HelperC.afficherMessage("Information", "Succès de la suppression");
			this.listeHolyDays = FichierBaseDAO.getInstance().getAllJourFerier();
			clear(true);
		} else {

			HelperC.afficherMessage("Désolé", "Echec de suppression");
		}
	}

	public void initialiser() {
		clear(true);
	}

	public void fermer() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/masterPage.jsf");
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}
}
