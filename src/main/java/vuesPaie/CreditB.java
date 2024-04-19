package vuesPaie;

import classesPaie.AvaliseurC;
import classesPaie.BanqueC;
import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.CreditC;
import classesPaie.CreditDetailC;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
import classesPaie.Tables;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class CreditB extends CreditC implements Serializable {
	private static final long serialVersionUID = 8294855538634640156L;

	private int idBanque;
	private int index;
	private double montantTranche;
	private List<CreditC> listCredit;
	private CreditDetailC detail;
	private String codeEmploye, nomEmploye;
	private boolean selected, disableMsg;
	private boolean desable, desableAdd;
	private List<SelectItem> banques;
	private List<EmployeC> employes;
	private List<AvaliseurC> avaliseurs;
	private String printDateDb, printDateFn, printDate, printTotTranch, printTotMn;
	private Date dateDb, dateFn;
	private EmployeC selectedEmploye;
	private CreditC selectedCredit;
	private OperateurC operateur;
	private ExerciceC exercice;
	private HttpSession session = HelperC.getSession();
	private DroitC droit;
	private String codeRechEmp;
	private String nomRechEmp;
	private String prenomRechEmp;
	Base userFonction;
	ParametrageGeneralC prm;
	ExerciceC dossier;
	String num;
	BanqueC bk;
	int months = 0;
	double totalRemb, reste, totalTranche, totalMensul;

	public List<SelectItem> getBanques() {
		return this.banques;
	}

	public void setBanques(List<SelectItem> banques) {
		this.banques = banques;
	}

	public List<CreditC> getListCredit() {
		return this.listCredit;
	}

	public void setListCredit(List<CreditC> listCredit) {
		this.listCredit = listCredit;
	}

	public List<EmployeC> getEmployes() {
		return this.employes;
	}

	public void setEmployes(List<EmployeC> employes) {
		this.employes = employes;
	}

	public List<AvaliseurC> getAvaliseurs() {
		return this.avaliseurs;
	}

	public void setAvaliseurs(List<AvaliseurC> avaliseurs) {
		this.avaliseurs = avaliseurs;
	}

	public DroitC getDroit() {
		return this.droit;
	}

	public void setDroit(DroitC droit) {
		this.droit = droit;
	}

	public CreditDetailC getDetail() {
		return this.detail;
	}

	public void setDetail(CreditDetailC detail) {
		this.detail = detail;
	}

	public int getIdBanque() {
		return this.idBanque;
	}

	public void setIdBanque(int idBanque) {
		this.idBanque = idBanque;
	}

	public CreditC getSelectedCredit() {
		return this.selectedCredit;
	}

	public void setSelectedCredit(CreditC selectedCredit) {
		this.selectedCredit = selectedCredit;
	}

	public OperateurC getOperateur() {
		return this.operateur;
	}

	public void setOperateur(OperateurC operateur) {
		this.operateur = operateur;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ExerciceC getExercice() {
		return this.exercice;
	}

	public void setExercice(ExerciceC exercice) {
		this.exercice = exercice;
	}

	public boolean isDesable() {
		return this.desable;
	}

	public void setDesable(boolean desable) {
		this.desable = desable;
	}

	public boolean isSelected() {
		return selected;
	}

	public String getCodeEmploye() {
		return codeEmploye;
	}

	public void setCodeEmploye(String codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public EmployeC getSelectedEmploye() {
		return selectedEmploye;
	}

	public void setSelectedEmploye(EmployeC selectedEmploye) {
		this.selectedEmploye = selectedEmploye;
	}

	public String getPrintDateDb() {
		return printDateDb;
	}

	public void setPrintDateDb(String printDateDb) {
		this.printDateDb = printDateDb;
	}

	public String getPrintDateFn() {
		return printDateFn;
	}

	public void setPrintDateFn(String printDateFn) {
		this.printDateFn = printDateFn;
	}

	public Date getDateDb() {
		return dateDb;
	}

	public void setDateDb(Date dateDb) {
		this.dateDb = dateDb;
	}

	public Date getDateFn() {
		return dateFn;
	}

	public void setDateFn(Date dateFn) {
		this.dateFn = dateFn;
	}

	public double getMontantTranche() {
		return montantTranche;
	}

	public void setMontantTranche(double montantTranche) {
		this.montantTranche = montantTranche;
	}

	public String getCodeRechEmp() {
		return codeRechEmp;
	}

	public void setCodeRechEmp(String codeRechEmp) {
		this.codeRechEmp = codeRechEmp;
	}

	public String getNomRechEmp() {
		return nomRechEmp;
	}

	public void setNomRechEmp(String nomRechEmp) {
		this.nomRechEmp = nomRechEmp;
	}

	public String getPrenomRechEmp() {
		return prenomRechEmp;
	}

	public void setPrenomRechEmp(String prenomRechEmp) {
		this.prenomRechEmp = prenomRechEmp;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public boolean isDesableAdd() {
		return desableAdd;
	}

	public void setDesableAdd(boolean desableAdd) {
		this.desableAdd = desableAdd;
	}

	public String getPrintTotTranch() {
		return printTotTranch;
	}

	public void setPrintTotTranch(String printTotTranch) {
		this.printTotTranch = printTotTranch;
	}

	public String getPrintTotMn() {
		return printTotMn;
	}

	public void setPrintTotMn(String printTotMn) {
		this.printTotMn = printTotMn;
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
			banques = new ArrayList<SelectItem>();
			this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());

			disableMsg = true;
			if (this.userFonction != null) {
				this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
						Constante.Role.creditAvance);
			}

			chargerBanque();
			chargerNumero();
		}
	}

	private void chargerNumero() {
		num = FactoryDAO.getInstance().getNumeroDossier(exercice.getId());
		setNumeroDossier(num);
	}

	public void chargerCredit() {
		listCredit = FactoryDAO.getInstance().getListCredit(0);

	}

	public void chargementEmploye() {
		employes = FactoryDAO.getInstance().getListEmploye(codeRechEmp, nomRechEmp, true);
	}

	public void searchEmploye() {
		selectedEmploye = FactoryDAO.getInstance().getEmployeActif(codeEmploye, false);
		if (selectedEmploye != null) {
			codeEmploye = selectedEmploye.getCode();
			nomEmploye = selectedEmploye.getNomPrenom();
			setIdEmploye(selectedEmploye.getId());
		}
	}

	public void changeBanque(ValueChangeEvent event) {
		idBanque = ((Integer) event.getNewValue()).intValue();
		searchBank();
	}

	private void searchBank() {
		if (idBanque > 0) {
			bk = FichierBaseDAO.getInstance().getBanque(idBanque);
			setBanque(bk);
		}
	}

	private void chargerBanque() {
		banques.add(new SelectItem(0, ""));
		for (BanqueC bq : FichierBaseDAO.getInstance().getAllBanque()) {
			banques.add(new SelectItem(Integer.valueOf(bq.getId()), bq.getDesignation()));
		}
	}

	public void onRowSelectedCredit(SelectEvent event) {
		selectedCredit = (CreditC) event.getObject();
		if (selectedCredit != null)
			setValues();
		PrimeFaces.current().executeScript("PF('dlgCrd').hide();");
	}

	private void setValues() {
		selectedEmploye = selectedCredit.getEmploye();
		if (selectedEmploye != null) {
			codeEmploye = selectedEmploye.getCode();
			nomEmploye = selectedEmploye.getNomPrenom();
			setIdEmploye(selectedEmploye.getId());
		}
		disableMsg = false;
		setId(selectedCredit.getId());
		if (selectedCredit.getBanque() != null) {
			idBanque = selectedCredit.getBanque().getId();
			searchBank();
		}
		setCapital(selectedCredit.getCapital());
		setMontantCredit(selectedCredit.getMontantCredit());
		totalRemb = FactoryDAO.getInstance().getTotalRembourse(selectedCredit.getId());
		setMontantRembourse(totalRemb);
		setReste(getCapital() - totalRemb);
		setDuree(selectedCredit.getDuree());
		setListDetail(selectedCredit.getListDetail());
		setNumeroCompte(selectedCredit.getNumeroCompte());
		setNumeroDossier(selectedCredit.getNumeroDossier());
		setDatePret(selectedCredit.getDatePret());
		printDate = HelperC.convertDate(selectedCredit.getDatePret());
		calculTotal(this.getListDetail());
	}

	public void addDetail() {
		if (montantTranche > 0 && dateDb != null && dateFn != null) {

			if (detail == null)
				detail = new CreditDetailC();
			detail.setDateDeb(dateDb);
			detail.setDateFin(dateFn);
			detail.setTranche(montantTranche);

			if ((detail.getTotalTranch() + totalMensul) > getCapital()) {
				HelperC.afficherAttention("ATTENTION", "On ne peut pas épasser le montant é rembourser !");
				return;

			}

			if (months > getDuree()) {
				HelperC.afficherAttention("ATTENTION", "On ne peut pas dépasser la durée de rembourssement !");
				return;

			}
			if (!selected)
				this.getListDetail().add(detail);
			else {
				this.getListDetail().remove(index);
				this.getListDetail().add(index, detail);
			}
			clearDetail();

		} else {
			HelperC.afficherAttention("ATTENTION", "La date début, la date fin et le montant doivent étre corrects");
		}
		calculTotal(this.getListDetail());
	}

	private void calculTotal(List<CreditDetailC> list) {

		totalMensul = 0;
		totalTranche = 0;
		for (CreditDetailC det : list) {
			totalMensul += det.getMensualite();
			totalTranche += det.getTotalTranch();
		}
		printTotMn = HelperC.decimalNumber(totalMensul, 0, true);
		printTotTranch = HelperC.decimalNumber(totalTranche, 0, true);
	}

	public void removeDetail() {
		if (detail != null) {
			if (detail.getId() > 0)
				this.getListDeleted().add(detail);
			this.getListDetail().remove(detail);
			clearDetail();
			calculTotal(this.getListDetail());
		}
	}

	public void clearDetail() {
		montantTranche = 0;
		dateDb = null;
		dateFn = null;
		printDateDb = "";
		printDateFn = "";
		selected = false;
		index = 0;
		detail = null;
		desableAdd = false;
	}

	private void checkDate() {
		if (getDateDb() != null && getDateFn() != null) {
			if (dateFn.before(dateDb)) {
				HelperC.afficherAttention("ATTENTION", "La date début doit étre inférieure é la date fin ");
				desableAdd = true;
			} else {
				months = (int) HelperC.daysBetween(dateDb, dateFn) / 30;
			}
		}
	}

	public void onDetailSelected(SelectEvent event) {
		detail = (CreditDetailC) event.getObject();
		if (detail != null) {
			montantTranche = detail.getTranche();
			dateDb = detail.getDateDeb();
			dateFn = detail.getDateFin();
			printDateDb = HelperC.convertDate(dateDb);
			printDateFn = HelperC.convertDate(dateFn);
			selected = true;
			index = this.getListDetail().indexOf(detail);
		}
	}

	public void changeDateDeb() {
		desableAdd = false;
		if (this.printDateDb.replace("/", "").replace("_", "").trim().equals("")) {

			setDateDb(null);
		} else {

			dateDb = HelperC.validerDate(printDateDb);

			if (dateDb != null) {

				printDateDb = HelperC.convertDate(dateDb);
				desableAdd = false;
				checkDate();
				calculDateFin();
			} else {
				HelperC.afficherAttention("ATTENTION", "La date saisie n'est pas correct");
				desableAdd = true;
				;
			}
		}
	}

	public void changeDateFin() {
		desableAdd = false;
		if (printDateFn.replace("/", "").replace("_", "").trim().equals("")) {

			dateFn = null;

		} else {
			dateFn = HelperC.validerDate(printDateFn);

			if (dateFn != null) {
				desableAdd = false;
				printDateFn = HelperC.convertDate(dateFn);
				checkDate();

			} else {
				HelperC.afficherAttention("ATTENTION", "La date saisie n'est pas correct");
				desableAdd = true;
			}
		}
	}

	public void changeDateCred() {
		desable = false;
		if (printDate.replace("/", "").replace("_", "").trim().equals("")) {

			setDatePret(null);

		} else {
			setDatePret(HelperC.validerDate(printDate));

			if (getDatePret() != null) {
				printDate = HelperC.convertDate(getDatePret());

			} else {
				HelperC.afficherAttention("ATTENTION", "La date saisie n'est pas correct");
				desable = true;
			}
		}
	}

	public void onEmployeSelected(SelectEvent event) {
		selectedEmploye = (EmployeC) event.getObject();
		if (selectedEmploye != null) {
			codeEmploye = selectedEmploye.getCode();
			nomEmploye = selectedEmploye.getNomPrenom();
			setIdEmploye(selectedEmploye.getId());
		}
		PrimeFaces.current().executeScript("PF('dlgEmploye').hide();");
	}

	private void calculDateFin() {

		if (getDateDb() != null && getDuree() > 0) {
			dateFn = HelperC.addMonth(getDateDb(), getDuree());
			printDateFn = HelperC.convertDate(dateFn);
		}
	}

	private void createHistoric(boolean delete) {
		Historique hist = new Historique();
		hist.setDateOperation(Calendar.getInstance().getTime());
		hist.setOperateur(this.operateur);
		if (getId() == 0) {

			hist.setOperation("Enregistrement Crédit :Employe=" + codeEmploye + " " + nomEmploye);
		} else {
			if (delete)
				hist.setOperation("Suppression Crédit :Employe=" + codeEmploye + " " + nomEmploye);
			else
				hist.setOperation("Modification Crédit :Employe=" + codeEmploye + " " + nomEmploye);
		}
		hist.setTable(Tables.getTableName(Tables.TableName.credit));
		setHistorique(hist);
	}

	public void enregistrer() {
		this.setExercice(exercice);
		if (getId() == 0 && !this.droit.isCreer()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit d'enregistrer les crédits ");
			return;
		}
		if (getId() > 0 && !this.droit.isModifier()) {

			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier les crédits ");
			return;
		}
		if (getDatePret() == null) {

			HelperC.afficherAttention("ATTENTION", "Il faut préciser la date de prét ! ");
			return;
		}
		if (selectedEmploye == null) {
			HelperC.afficherAttention("ATTENTION", "Il faut préciser l'employé ! ");
			return;
		}

		createHistoric(false);

		if (FactoryDAO.getInstance().insertUpdateCredit(this)) {
			HelperC.afficherMessage("Information", "Succès de l'opération ");
			initialiser();
		} else {

			HelperC.afficherAttention("Désolé", "Echec de l'opération ");
		}

	}

	public void supprimer() {
		if (selectedCredit != null) {
			createHistoric(true);
			if (FactoryDAO.getInstance().deleteCreditEmploye(this)) {
				HelperC.afficherMessage("Information", "Succès de l'opération ");
				initialiser();
			} else
				HelperC.afficherAttention("Désolé", "Echec de l'opération ");
		} else
			HelperC.afficherDeleteMessage();
	}

	public void initialiser() {
		setId(0);
		chargerNumero();
		codeEmploye = "";
		nomEmploye = "";
		idBanque = 0;
		printDate = "";
		setCapital(0);
		setMontantCredit(0);
		setMontantRembourse(0);
		setReste(0);
		setDuree(0);
		getListDetail().clear();
		getListDeleted().clear();
		desable = false;
		printTotMn = "";
		printTotTranch = "";
		totalMensul = 0;
		totalRemb = 0;
		totalTranche = 0;
		disableMsg = true;
		clearDetail();

	}
}
