package vuesPaie;

import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import persistencePaie.Connexion;
import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class IdentificationB implements Serializable {
	private static final long serialVersionUID = -2564179843157457755L;
	private String code;
	private String message;
	private String motPasse;
	private static String codeExercice;
	private static String nomOperateur;
	private ExerciceC exercice;
	private OperateurC operateur;
	HttpSession session;
	private List<ExerciceC> listExercice;
	private int idExercice;
	private static int idEmpl;
	private List<SelectItem> listExerc;
	private boolean operateurExist;
	private boolean disable;
	private boolean desableLogin;
	static EmployeC empl;

	public int getIdExercice() {
		return this.idExercice;
	}

	public boolean isOperateurExist() {
		return this.operateurExist;
	}

	public void setOperateurExist(boolean operateurExist) {
		this.operateurExist = operateurExist;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMotPasse() {
		return this.motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getCodeExercice() {
		return codeExercice;
	}

	public void setCodeExercice(String codeExercice) {
		IdentificationB.codeExercice = codeExercice;
	}

	public ExerciceC getExercice() {
		return this.exercice;
	}

	public void setExercice(ExerciceC exercice) {
		this.exercice = exercice;
	}

	public OperateurC getOperateur() {
		return this.operateur;
	}

	public void setOperateur(OperateurC operateur) {
		this.operateur = operateur;
	}

	public String getNomOperateur() {
		return nomOperateur;
	}

	public boolean isDisable() {
		return this.disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public List<ExerciceC> getListExercice() {
		return this.listExercice;
	}

	public void setListExercice(List<ExerciceC> listExercice) {
		this.listExercice = listExercice;
	}

	public List<SelectItem> getListExerc() {
		return this.listExerc;
	}

	public void setListExerc(List<SelectItem> listExerc) {
		this.listExerc = listExerc;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isDesableLogin() {
		return this.desableLogin;
	}

	public void setDesableLogin(boolean desableLogin) {
		this.desableLogin = desableLogin;
	}

	@PostConstruct
	private void init() {
		Connection con = Connexion.getConnection();
		if (con != null) {

			this.operateurExist = FichierBaseDAO.getInstance().searchUserExist();
			if (this.operateurExist) {
				chargerExercice();
				this.desableLogin = false;
			} else {
				this.disable = true;
			}

		} else {

			this.desableLogin = true;
			this.disable = true;
			this.message = "Il y a un problème de connexion à  la base de données! Vèrifiez les paramètres de connexion!";
		}
	}

	public void redirectionBdd() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("/payRoll/parametreConnexion.jsf");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void login() throws IOException {
		if (this.operateurExist) {

			if (getCode().trim().equals("") || getMotPasse().trim().equals("")) {

				HelperC.afficherMessage("Information", "Veuillez préciser votre code utilisateur et mot de passe !",
						FacesMessage.SEVERITY_ERROR);
			} else {

				this.operateur = FichierBaseDAO.getInstance().getOperateur(this.code, this.motPasse);
				if (this.operateur != null && !this.operateur.isLineUser()) {

					if (this.operateur.getId() > 0) {

						idEmpl = this.operateur.getIdEmploye();
						empl = FactoryDAO.getInstance().getEmployeSimple(idEmpl);
						FacesContext context = FacesContext.getCurrentInstance();
						HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
						session.setAttribute("operateur", this.code);
						session.setAttribute("exercice", codeExercice);
						session.setAttribute("existUser", Boolean.valueOf(this.operateurExist));
						context.getExternalContext().redirect("/payRoll/masterPage.jsf");

					} else {

						HelperC.afficherMessage("Avertissement", "Echec d'authentification",
								FacesMessage.SEVERITY_ERROR);
					}
				} else {

					HelperC.afficherMessage("Information", "L'utilisateur n'est pas reconnu par le système!",
							FacesMessage.SEVERITY_ERROR);
				}
			}
		} else {

			FacesContext context = FacesContext.getCurrentInstance();
			this.session = (HttpSession) context.getExternalContext().getSession(true);
			this.session.setAttribute("operateur", " ");
			this.session.setAttribute("exercice", " ");
			this.session.setAttribute("existUser", Boolean.valueOf(this.operateurExist));
			context.getExternalContext().redirect("/payRoll/masterPage.jsf");
		}
	}

	public void logOut() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.invalidate();
		this.operateur = new OperateurC();

		try {
			context.getExternalContext().redirect("/payRoll/login.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void redirectionNouveauPwd() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("/payRoll/nouveauLogin.jsf");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void chargerExercice() {
		this.listExercice = FichierBaseDAO.getInstance().getListExercice();
		if (this.listExercice.size() == 0) {

			codeExercice = HelperC.changeDateFormate(new Date()).split("/")[2];
			this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
			if (this.exercice == null) {

				this.exercice = new ExerciceC();
				this.exercice.setCode(codeExercice);
				FichierBaseDAO.getInstance().insertUpdateExercice(this.exercice);
			}
		} else {

			this.listExerc = new ArrayList<SelectItem>();
			this.listExerc.add(new SelectItem(0, ""));
			for (ExerciceC ex : this.listExercice) {
				this.listExerc.add(new SelectItem(Integer.valueOf(ex.getId()), ex.getCode()));
			}
		}
	}

	public void changeExercice(ValueChangeEvent event) {
		if (operateurExist & (event != null)) {
			idExercice = ((Integer) event.getNewValue()).intValue();
			exercice = FichierBaseDAO.getInstance().getExercice(idExercice);
			codeExercice = exercice.getCode();
		}
	}

	public static EmployeC GetProperties() {
		// EmployeC u = null;

		return empl;
	}

	public static ExerciceC getNumero() {
		ExerciceC exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);

		return exercice;
	}

}
