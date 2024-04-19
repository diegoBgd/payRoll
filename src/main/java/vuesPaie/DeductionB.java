package vuesPaie;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DeductionC;
import classesPaie.DroitC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.Tables;
import java.io.IOException;
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
public class DeductionB extends DeductionC {
	private static final long serialVersionUID = 3496540449322392036L;
	private DeductionC selected;
	private List<DeductionC> listDeduction;
	private OperateurC operateur;
	private ExerciceC exercice;
	private HttpSession session = HelperC.getSession();
	private DroitC droit;
	private boolean disableSave, disableMsg;
	Base userFonction;

	public DroitC getDroit() {
		return this.droit;
	}

	public void setDroit(DroitC droit) {
		this.droit = droit;
	}

	public DeductionC getSelected() {
		return this.selected;
	}

	public void setSelected(DeductionC selected) {
		this.selected = selected;
	}

	public List<DeductionC> getListDeduction() {
		return this.listDeduction;
	}

	public void setListDeduction(List<DeductionC> listDeduction) {
		this.listDeduction = listDeduction;
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

	public boolean isDisableSave() {
		return disableSave;
	}

	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
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
			if (this.userFonction != null) {
				this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
						Constante.Role.parametrage);
			}
			charger();
		}
	}

	private void charger() {
		this.listDeduction = FichierBaseDAO.getInstance().getAllDeduction();
	}

	public void changeTauxValue() {
		if (getTaux() > 0.0D) {
			setMontant(0.0D);
		}
	}

	public void changeForfaitValue() {
		if (getMontant() > 0.0D) {
			setTaux(0.0D);
		}
	}

	private void setObject() {
		if (this.selected != null) {
			disableMsg = false;
			setId(this.selected.getId());
			setCode(this.selected.getCode());
			setDesignation(this.selected.getDesignation());
			setCentralisable(this.selected.isCentralisable());
			setPrefixeComptable(this.selected.getPrefixeComptable());
			setTaux(this.selected.getTaux());
			setTauxS(this.selected.getTauxS());
			setMontant(this.selected.getMontant());
			setMontantS(this.selected.getMontantS());
			setHide(this.selected.isHide());
			setTypeSanction(this.selected.isTypeSanction());
			setSoustract(selected.isSoustract());
		}
	}

	private void clear(boolean b) {
		if (b) {
			setCode("");
		}
		disableMsg = true;
		setId(0);
		setDesignation("");
		setCentralisable(false);
		setPrefixeComptable("");
		setTaux(0.0D);
		setMontant(0.0D);
		setTauxS("");
		setHide(false);
		setTypeSanction(false);
		setMontantS("");
		setSoustract(false);
	}

	public void changeCode() {
		if (getCode().trim().equals("")) {

			clear(true);
		} else {

			this.selected = FichierBaseDAO.getInstance().getDeduction(getCode());
			if (this.selected == null) {

				clear(false);
			} else {

				setObject();
			}
		}
	}

	public void checkDeductionSanction() {
		disableSave = false;
		if (isTypeSanction()) {
			if (FichierBaseDAO.getInstance().getDeductionSanction() != null) {
				HelperC.afficherAttention("Attention", "Une reténu lié aux sanction est déjà paramétré !");
				disableSave = true;
			}
		}
	}

	public void onRowSelected(SelectEvent event) {
		this.selected = (DeductionC) event.getObject();
		disableMsg = true;
		if (this.selected != null) {
			setObject();
		}
	}

	public void enregistrer() {
		if (getCode().trim().equals("") || getDesignation().trim().equals("")) {

			HelperC.afficherMessage("Information", "Completez tous les champs nécessaires");
		} else {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {

				hist.setOperation("Création de la déduction " + getCode());
			} else {

				hist.setOperation("Modification de la déduction " + getCode());
			}
			hist.setTable(Tables.getTableName(Tables.TableName.deduction));
			setHistorique(hist);
			if (getId() == 0) {
				if (this.droit.isCreer()) {

					if (FichierBaseDAO.getInstance().insertUpdateDeduction(this)) {

						clear(true);
						HelperC.afficherMessage("Information", "Succès de l'opération");
						charger();
					} else {

						HelperC.afficherMessage("Désolé", "Echec de l'opération");
					}
				} else {

					HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer ");
				}
			}
			if (getId() > 0) {
				if (this.droit.isModifier()) {

					if (FichierBaseDAO.getInstance().insertUpdateDeduction(this)) {

						clear(true);
						HelperC.afficherMessage("Information", "Succès de l'opération");
						charger();
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
		if (getId() == 0) {

			HelperC.afficherDeleteMessage();
		} else if (this.droit.isSupprimer()) {

			if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.deduction), getId())) {

				clear(true);
				HelperC.afficherMessage("Information", "Succès de l'opération ");
				init();
			} else {

				HelperC.afficherMessage("Désolé", "Echec de suppression");
			}
		} else {

			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
		}
	}

	public void initialiser() {
		clear(true);
	}
}
