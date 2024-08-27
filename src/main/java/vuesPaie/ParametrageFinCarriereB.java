package vuesPaie;

import classesPaie.Base;
import classesPaie.CategoriePersonnelC;
import classesPaie.ExerciceC;
import classesPaie.GradePersonnelC;
import classesPaie.HelperC;
import classesPaie.OperateurC;
import classesPaie.ParametrageFinCarriereC;
import classesPaie.Tables;
import classesPaie.TypeNotationC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class ParametrageFinCarriereB extends ParametrageFinCarriereC {
	private static final long serialVersionUID = 5203706953535440034L;
	private int idPersonnel;
	private int idGrade;
	private int idTypeNotation, idCategorie;
	private ParametrageFinCarriereC selected;
	private List<ParametrageFinCarriereC> listParametrage = new ArrayList<ParametrageFinCarriereC>();
	private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>();
	private List<SelectItem> listPersonnel = new ArrayList<SelectItem>();
	private List<SelectItem> listGrade = new ArrayList<SelectItem>();
	private List<SelectItem> listCagtorie = new ArrayList<SelectItem>();
	private OperateurC operateur;
	private ExerciceC exercice;
	private HttpSession session = HelperC.getSession();

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getIdPersonnel() {
		return this.idPersonnel;
	}

	public void setIdPersonnel(int idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public int getIdGrade() {
		return this.idGrade;
	}

	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}

	public ParametrageFinCarriereC getSelected() {
		return this.selected;
	}

	public void setSelected(ParametrageFinCarriereC selected) {
		this.selected = selected;
	}

	public List<ParametrageFinCarriereC> getListParametrage() {
		return this.listParametrage;
	}

	public void setListParametrage(List<ParametrageFinCarriereC> listParametrage) {
		this.listParametrage = listParametrage;
	}

	public List<SelectItem> getListPersonnel() {
		return this.listPersonnel;
	}

	public void setListPersonnel(List<SelectItem> listPersonnel) {
		this.listPersonnel = listPersonnel;
	}

	public OperateurC getOperateur() {
		return this.operateur;
	}

	public List<SelectItem> getListGrade() {
		return listGrade;
	}

	public void setListGrade(List<SelectItem> listGrade) {
		this.listGrade = listGrade;
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

	public int getIdTypeNotation() {
		return this.idTypeNotation;
	}

	public void setIdTypeNotation(int idTypeNotation) {
		this.idTypeNotation = idTypeNotation;
	}

	public List<SelectItem> getListTypeNotation() {
		return this.listTypeNotation;
	}

	public void setListTypeNotation(List<SelectItem> listTypeNotation) {
		this.listTypeNotation = listTypeNotation;
	}

	public List<SelectItem> getListCagtorie() {
		return listCagtorie;
	}

	public void setListCagtorie(List<SelectItem> listCagtorie) {
		this.listCagtorie = listCagtorie;
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
		}
		//this.listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));
		this.listTypeNotation.add(new SelectItem(Integer.valueOf(0), ""));
		//this.listGrade.add(new SelectItem(Integer.valueOf(0), ""));
		for (Base personnel : FichierBaseDAO.getInstance()
				.getAllBase(Tables.getTableName(Tables.TableName.personnel))) {
			this.listPersonnel.add(new SelectItem(Integer.valueOf(personnel.getId()), personnel.getDesignation()));
		}
		for (TypeNotationC typeNotation : FichierBaseDAO.getInstance().getAllTypeNotation()) {
			this.listTypeNotation
					.add(new SelectItem(Integer.valueOf(typeNotation.getId()), typeNotation.getDesignation()));
		}
	}

	private void setObject() {
		if (this.selected != null) {
			setId(this.selected.getId());
			setPersonnel(this.selected.getPersonnel());
			setCategorie(this.selected.getCategorie());
			if (getPersonnel() != null) {
				this.idPersonnel = getPersonnel().getId();

				chargerCategorie();
				if (getCategorie() != null)
					idCategorie = getCategorie().getId();
			}
			setAgeMaxRetraite(this.selected.getAgeMaxRetraite());
			setAgeRetraite(this.selected.getAgeRetraite());

			chargerGrade();
			setDernierGrade(this.selected.getDernierGrade());
			if (getDernierGrade() != null) {
				this.idGrade = getDernierGrade().getId();

			}
			setTypeNotation(this.selected.getTypeNotation());
			if (getTypeNotation() != null) {
				this.idTypeNotation = getTypeNotation().getId();
			}
			setPeriodeProlongation(selected.getPeriodeProlongation());
			setPeriodeSalire(selected.getPeriodeSalire());
			setPourcentageSalaire(selected.getPourcentageSalaire());
			setAnneesProlongationRetraite(selected.getAnneesProlongationRetraite());
			setPeriodeAnticipe(selected.getPeriodeAnticipe());
		}
	}

	private void clear() {
		setId(0);
		setPersonnel(null);
		this.idPersonnel = 0;
		setPeriodeProlongation(0);
		setAgeMaxRetraite(0);
		setAgeRetraite(0);
		setPourcentageSalaire(0);
		setAnneesProlongationRetraite(0);
		setPeriodeAnticipe(0);
		setDernierGrade(null);
		this.idGrade = 0;

		setTypeNotation(null);
		this.idTypeNotation = 0;
		this.selected = null;
	}

	public void onRowSelect(SelectEvent event) {
		this.selected = (ParametrageFinCarriereC) event.getObject();
		if (this.selected != null) {
			setObject();
			PrimeFaces.current().executeScript("PF('dlgParm').hide();");
		}
	}

	public void initialiser() {
		clear();
	}

	public void chargement() {
		listParametrage = FichierBaseDAO.getInstance().getListParametrageFinCarriere();
	}

	public void changePersonnel(ValueChangeEvent event) {
		this.idPersonnel = ((Integer) event.getNewValue()).intValue();
		if (this.idPersonnel != 0) {
			setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel,
					Tables.getTableName(Tables.TableName.personnel)));
			if (getPersonnel() != null) {

				chargerCategorie();
			}
		}
	}

	public void changeGrade(ValueChangeEvent event) {
		this.idGrade = ((Integer) event.getNewValue()).intValue();
		if (this.idGrade >0) {
			setDernierGrade(FichierBaseDAO.getInstance().getGradePersonnel(this.idGrade));

		}
	}

	public void changeCategorie(ValueChangeEvent event) {
		idCategorie = ((Integer) event.getNewValue()).intValue();
		if(idCategorie>0)
		{
		setCategorie(FichierBaseDAO.getInstance().getCategoriePersonnel(idCategorie));
		chargerGrade();
		}
	}

	private void chargerGrade() {
		listGrade.add(new SelectItem(0, ""));
		for (GradePersonnelC grd : FichierBaseDAO.getInstance().getListGradeParCategoriePersonnel(idCategorie)) {
			listGrade.add(new SelectItem(grd.getId(), grd.getDesignation()));
		}
	}

	public void changeTypeNotation(ValueChangeEvent event) {
		this.idTypeNotation = ((Integer) event.getNewValue()).intValue();
		if (this.idTypeNotation != 0) {
			setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(this.idTypeNotation));
		}
	}

	private void chargerCategorie() {
		listCagtorie.add(new SelectItem(0, ""));
		for (CategoriePersonnelC cat : FichierBaseDAO.getInstance()
				.getListCategoriePersonnelParIdPersonnel(idPersonnel)) {
			listCagtorie.add(new SelectItem(Integer.valueOf(cat.getId()), cat.getDesignation()));
		}
	}

	public void save() {
		 if (FichierBaseDAO.getInstance().insertUpdateParametrageFinCarriere(this)) {
			HelperC.afficherMessage("Information", "Succès d'enregistrement");
		} else {
			HelperC.afficherMessage("information", "Echec d'enregistrement");
		}
	}

	public void supprimer() {
		try {
			if (getId() == 0) {

				HelperC.afficherDeleteMessage();

			} else if (FichierBaseDAO.getInstance().delete(getId(),
					Tables.getTableName(Tables.TableName.parametrageFinCarriere))) {

				HelperC.afficherMessage("Félicitation", "Succès de l'Opération");
				clear();
			} else {

				HelperC.afficherMessage("Désolé", "Echec de l'Opération");

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
