package vuesPaie;

import classesPaie.BanqueC;
import classesPaie.Base;
import classesPaie.CategoriePersonnelC;
import classesPaie.Constante;
import classesPaie.CotisationC;
import classesPaie.DeductionC;
import classesPaie.DetailBanqueEmployeC;
import classesPaie.DetailComissionEmployeC;
import classesPaie.DetailCotisationEmployeC;
import classesPaie.DetailDeductionC;
import classesPaie.DetailIndemniteEmployeC;
import classesPaie.DetailPrimeEmployeC;
import classesPaie.DirectionC;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.GradePersonnelC;
import classesPaie.GradePersonnelDetailC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.MarcheProgrammeC;
import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
import classesPaie.ParametragePrimeC;
import classesPaie.PrimeIndemniteC;
import classesPaie.ServicesC;
import classesPaie.Tables;
import editionPaie.EditionFicheEmmployeB;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

@ManagedBean
@ViewScoped
public class EmployeB extends EmployeC implements Serializable {
	private static final long serialVersionUID = 5659574157386814683L;
	private int idPrime;
	private int idIndemnite;
	private int idCotisation;
	private int idDeduction;
	private int idBanqueDeduction;
	private int idCategorie;
	private int idGrade;
	private int idContrat;

	private int idPrimeIndemnite;
	private int idBanquePaiement;
	private int idPersonnel;
	private int idLieuTravail;
	private int idNiveau;
	private int idNiveauFormation;
	private int idDirection;
	private int idDepartement;
	private int idService;
	private int niveauCategorie;
	private int typeNiveau;

	private int idFonction;
	private int imgId;
	private int idCom;
	private double montantVirement;
	private double montantPrime;
	private double montantIndemnite;
	private double montantSalarial;
	private double montantPatronal;
	private double montantDeduction;
	private double tauxInd;
	private double tauxPrm;
	private double montantCom, tauxCom;

	private List<SelectItem> listPersonnels = new ArrayList<SelectItem>();
	private String codeEmployeRecherche;
	private String nomEmployeRecherche;
	private String prenomEmployeRecherche;
	private String nomRecherche;
	private String codeGrade;
	private String codeCategorieEmploye;
	private String designationGrade;
	private String numeroBankDeduc;
	private String numeroBanquePaiement;
	private String urlImg;
	private boolean desactiver2emeEmployeur = true;
	private boolean bnkPrincipal;
	private MarcheProgrammeC marche;
	private OperateurC operateur;
	private ExerciceC exercice;
	private PrimeIndemniteC selectedPrimeIndemnite;
	private DetailPrimeEmployeC selectedDetailPrime;
	private DetailIndemniteEmployeC selectedDetailIndemnite;
	private DetailCotisationEmployeC selectedDetailCotisation;
	private DetailComissionEmployeC selectedDetailComission;
	private DeductionC selectedDeduction;
	private BanqueC banqueDeduction;
	private BanqueC banquePaiement;
	private DetailBanqueEmployeC banquePaiementSelected;
	private DetailDeductionC selectedDetailDeduction;
	private DeductionC deduction;
	private PrimeIndemniteC prime;
	private PrimeIndemniteC indemnite;
	private CotisationC cotisation;
	private CotisationC comission;
	private EmployeC selected;
	private DroitC droit;
	private StreamedContent image;
	private List<SelectItem> listCategoriePersonnel = new ArrayList<SelectItem>();
	private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
	private List<SelectItem> listcategorie = new ArrayList<SelectItem>();
	private List<SelectItem> listgrade = new ArrayList<SelectItem>();
	private List<SelectItem> listNiveauFormation = new ArrayList<SelectItem>();
	private List<SelectItem> listNiveau = new ArrayList<SelectItem>();
	private List<SelectItem> listDirection = new ArrayList<SelectItem>();
	private List<SelectItem> listcontrat = new ArrayList<SelectItem>();
	private List<SelectItem> listdeduction = new ArrayList<SelectItem>();
	private List<SelectItem> listBankDeductions = new ArrayList<SelectItem>();
	private List<SelectItem> listBankPaiement = new ArrayList<SelectItem>();
	private List<SelectItem> listbasepaie = new ArrayList<SelectItem>();
	private List<SelectItem> listProfession = new ArrayList<SelectItem>();
	private List<SelectItem> listModeReglement = new ArrayList<SelectItem>();
	private List<SelectItem> listPrime = new ArrayList<SelectItem>();
	private List<SelectItem> listIndemnite = new ArrayList<SelectItem>();
	private List<SelectItem> listCotisation = new ArrayList<SelectItem>();
	private List<SelectItem> listDeduction = new ArrayList<SelectItem>();
	private List<SelectItem> listDeductions = new ArrayList<SelectItem>();
	private List<SelectItem> listDepartement = new ArrayList<SelectItem>();
	private List<SelectItem> listServices = new ArrayList<SelectItem>();
	private List<SelectItem> listFonctions = new ArrayList<SelectItem>();

	private List<SelectItem> listLieuTravail = new ArrayList<SelectItem>();
	private List<SelectItem> listComission = new ArrayList<SelectItem>();
	private HttpSession session = HelperC.getSession();
	double traitementSalarial = 0.0D;
	List<ParametragePrimeC> listParametragePrime;
	Base userFonction;
	ParametrageGeneralC parm;
	int indexPrm;
	int indexCot;
	int indexDed;
	int idParmPrm;
	int indexCom;
	boolean selectedDetailPrm;
	boolean selectedDetailCot;
	boolean selectedDetailDed;
	boolean selectedDetailCom;

	@PostConstruct
	private void charger() {
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

			this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (this.userFonction != null)
				this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(),
						Constante.Role.profilEmploye);
			setEtat(true);
			setUrlPhoto("avatar.jpg");

			this.parm = FichierBaseDAO.getInstance().getParametrageGeneral();

			this.listcategorie.clear();
			this.listcategorie.add(new SelectItem(Integer.valueOf(0), ""));

			listDirection.clear();
			listDirection.add(new SelectItem(Integer.valueOf(0), ""));

			listDepartement.clear();
			listDepartement.add(new SelectItem(Integer.valueOf(0), ""));

			this.listServices.clear();
			this.listServices.add(new SelectItem(Integer.valueOf(0), ""));

			this.listNiveauFormation.clear();
			this.listNiveauFormation.add(new SelectItem(Integer.valueOf(0), ""));

			this.listgrade.clear();
			this.listgrade.add(new SelectItem(Integer.valueOf(0), ""));

			this.listLieuTravail.clear();
			this.listLieuTravail.add(new SelectItem(Integer.valueOf(0), ""));

			this.listModeReglement.clear();
			this.listModeReglement.add(new SelectItem(Integer.valueOf(0), ""));

			for (int i = 1; i < 5; i++) {
				this.listModeReglement.add(new SelectItem(Integer.valueOf(i), Constante.getLibelleModeReglement(i)));
			}

			for (Base b : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel))) {
				this.listPersonnels.add(new SelectItem(Integer.valueOf(b.getId()), b.getDesignation()));
			}

			for (Base b : FichierBaseDAO.getInstance()
					.getAllBase(Tables.getTableName(Tables.TableName.niveauFormation))) {
				this.listNiveauFormation.add(new SelectItem(Integer.valueOf(b.getId()), b.getDesignation()));
			}

			for (Base contrat : FichierBaseDAO.getInstance()
					.getAllBase(Tables.getTableName(Tables.TableName.typeContrat))) {
				this.listcontrat.add(new SelectItem(Integer.valueOf(contrat.getId()), contrat.getDesignation()));
			}

			this.listBankDeductions.add(new SelectItem(Integer.valueOf(0), ""));
			for (BanqueC bank : FichierBaseDAO.getInstance().getAllBanque()) {
				this.listBankDeductions.add(new SelectItem(Integer.valueOf(bank.getId()), bank.getDesignation()));
			}

			this.listBankPaiement.add(new SelectItem(Integer.valueOf(0), ""));
			for (BanqueC bank : FichierBaseDAO.getInstance().getAllBanque()) {
				this.listBankPaiement.add(new SelectItem(Integer.valueOf(bank.getId()), bank.getDesignation()));
			}

			this.listDeductions.add(new SelectItem(Integer.valueOf(0), ""));

			for (DeductionC deduction : FichierBaseDAO.getInstance().getAllDeduction()) {
				this.listDeductions.add(new SelectItem(Integer.valueOf(deduction.getId()),
						deduction.getCode() + "|| " + deduction.getDesignation()));
			}

			for (PrimeIndemniteC prime : FichierBaseDAO.getInstance().getAllPrimeIndemnite()) {
				this.listPrime.add(new SelectItem(Integer.valueOf(prime.getId()),
						prime.getCode() + "|| " + prime.getDesignation()));
			}

			for (CotisationC cotisation : FichierBaseDAO.getInstance().getListCotisation(0)) {
				this.listCotisation.add(new SelectItem(Integer.valueOf(cotisation.getId()),
						cotisation.getCode() + "|| " + cotisation.getDesignation()));
			}
			this.listComission.add(new SelectItem(Integer.valueOf(0), ""));
			for (CotisationC cotisation : FichierBaseDAO.getInstance().getListCotisation(1)) {
				this.listComission.add(new SelectItem(Integer.valueOf(cotisation.getId()),
						cotisation.getCode() + "|| " + cotisation.getDesignation()));
			}

			for (Base organ : FichierBaseDAO.getInstance()
					.getAllBase(Tables.getTableName(Tables.TableName.directionGnle))) {
				listDirection.add(new SelectItem(Integer.valueOf(organ.getId()), organ.getDesignation()));
			}

			this.listFonctions.add(new SelectItem(Integer.valueOf(0), ""));
			for (Base f : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction))) {
				this.listFonctions.add(new SelectItem(Integer.valueOf(f.getId()), f.getDesignation()));
			}

			for (Base lieu : FichierBaseDAO.getInstance()
					.getAllBase(Tables.getTableName(Tables.TableName.lieuxTravail))) {
				this.listLieuTravail.add(new SelectItem(Integer.valueOf(lieu.getId()),
						String.valueOf(lieu.getCode()) + "||" + lieu.getDesignation()));
			}
		}
	}

	public StreamedContent getImage() {
		return this.image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public String getUrlImg() {
		return this.urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public int getImgId() {
		return this.imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public double getTauxInd() {
		return this.tauxInd;
	}

	public void setTauxInd(double tauxInd) {
		this.tauxInd = tauxInd;
	}

	public double getTauxPrm() {
		return this.tauxPrm;
	}

	public void setTauxPrm(double tauxPrm) {
		this.tauxPrm = tauxPrm;
	}

	public int getIdLieuTravail() {
		return this.idLieuTravail;
	}

	public void setIdLieuTravail(int idLieuTravail) {
		this.idLieuTravail = idLieuTravail;
	}

	public List<SelectItem> getListLieuTravail() {
		return this.listLieuTravail;
	}

	public void setListLieuTravail(List<SelectItem> listLieuTravail) {
		this.listLieuTravail = listLieuTravail;
	}

	public boolean isBnkPrincipal() {
		return this.bnkPrincipal;
	}

	public void setBnkPrincipal(boolean bnkPrincipal) {
		this.bnkPrincipal = bnkPrincipal;
	}

	public int getIdBanqueDeduction() {
		return this.idBanqueDeduction;
	}

	public void setIdBanqueDeduction(int idBanqueDeduction) {
		this.idBanqueDeduction = idBanqueDeduction;
	}

	public int getIdBanquePaiement() {
		return this.idBanquePaiement;
	}

	public void setIdBanquePaiement(int idBanquePaiement) {
		this.idBanquePaiement = idBanquePaiement;
	}

	public List<SelectItem> getListBankDeductions() {
		return this.listBankDeductions;
	}

	public void setListBankDeductions(List<SelectItem> listBankDeductions) {
		this.listBankDeductions = listBankDeductions;
	}

	public List<SelectItem> getListBankPaiement() {
		return this.listBankPaiement;
	}

	public void setListBankPaiement(List<SelectItem> listBankPaiement) {
		this.listBankPaiement = listBankPaiement;
	}

	public int getIdNiveauFormation() {
		return this.idNiveauFormation;
	}

	public void setIdNiveauFormation(int idNiveauFormation) {
		this.idNiveauFormation = idNiveauFormation;
	}

	public List<SelectItem> getListNiveauFormation() {
		return this.listNiveauFormation;
	}

	public void setListNiveauFormation(List<SelectItem> listNiveauFormation) {
		this.listNiveauFormation = listNiveauFormation;
	}

	public int getIdNiveau() {
		return this.idNiveau;
	}

	public void setIdNiveau(int idNiveau) {
		this.idNiveau = idNiveau;
	}

	public DetailIndemniteEmployeC getSelectedDetailIndemnite() {
		return this.selectedDetailIndemnite;
	}

	public void setSelectedDetailIndemnite(DetailIndemniteEmployeC selectedDetailIndemnite) {
		this.selectedDetailIndemnite = selectedDetailIndemnite;
	}

	public String getCodeEmployeRecherche() {
		return this.codeEmployeRecherche;
	}

	public void setCodeEmployeRecherche(String codeEmployeRecherche) {
		this.codeEmployeRecherche = codeEmployeRecherche;
	}

	public String getNomEmployeRecherche() {
		return this.nomEmployeRecherche;
	}

	public void setNomEmployeRecherche(String nomEmployeRecherche) {
		this.nomEmployeRecherche = nomEmployeRecherche;
	}

	public String getPrenomEmployeRecherche() {
		return this.prenomEmployeRecherche;
	}

	public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
		this.prenomEmployeRecherche = prenomEmployeRecherche;
	}

	public DetailBanqueEmployeC getBanquePaiementSelected() {
		return this.banquePaiementSelected;
	}

	public void setBanquePaiementSelected(DetailBanqueEmployeC banquePaiementSelected) {
		this.banquePaiementSelected = banquePaiementSelected;
	}

	public DetailPrimeEmployeC getSelectedDetailPrime() {
		return this.selectedDetailPrime;
	}

	public void setSelectedDetailPrime(DetailPrimeEmployeC selectedDetailPrime) {
		this.selectedDetailPrime = selectedDetailPrime;
	}

	public List<SelectItem> getListProfession() {
		return this.listProfession;
	}

	public void setListProfession(List<SelectItem> listProfession) {
		this.listProfession = listProfession;
	}

	public List<SelectItem> getListcategorie() {
		return this.listcategorie;
	}

	public void setListcategorie(List<SelectItem> listcategorie) {
		this.listcategorie = listcategorie;
	}

	public List<SelectItem> getListgrade() {
		return this.listgrade;
	}

	public void setListgrade(List<SelectItem> listgrade) {
		this.listgrade = listgrade;
	}

	public List<SelectItem> getListcontrat() {
		return this.listcontrat;
	}

	public void setListcontrat(List<SelectItem> listcontrat) {
		this.listcontrat = listcontrat;
	}

	public List<SelectItem> getListdeduction() {
		return this.listdeduction;
	}

	public void setListdeduction(List<SelectItem> listdeduction) {
		this.listdeduction = listdeduction;
	}

	public List<SelectItem> getListNiveau() {
		return this.listNiveau;
	}

	public void setListNiveau(List<SelectItem> listNiveau) {
		this.listNiveau = listNiveau;
	}

	public EmployeC getSelected() {
		return this.selected;
	}

	public void setSelected(EmployeC selected) {
		this.selected = selected;
	}

	public DeductionC getSelectedDeduction() {
		return this.selectedDeduction;
	}

	public void setSelectedDeduction(DeductionC selectedDeduction) {
		this.selectedDeduction = selectedDeduction;
	}

	public DetailDeductionC getSelectedDetailDeduction() {
		return this.selectedDetailDeduction;
	}

	public void setSelectedDetailDeduction(DetailDeductionC selectedDetailDeduction) {
		this.selectedDetailDeduction = selectedDetailDeduction;
	}

	public int getIdPrime() {
		return this.idPrime;
	}

	public void setIdPrime(int idPrime) {
		this.idPrime = idPrime;
	}

	public int getIdDeduction() {
		return this.idDeduction;
	}

	public void setIdDeduction(int idDeduction) {
		this.idDeduction = idDeduction;
	}

	public int getIdCategorie() {
		return this.idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getIdGrade() {
		return this.idGrade;
	}

	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}

	public int getIdContrat() {
		return this.idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public int getIdPrimeIndemnite() {
		return this.idPrimeIndemnite;
	}

	public void setIdPrimeIndemnite(int idPrimeIndemnite) {
		this.idPrimeIndemnite = idPrimeIndemnite;
	}

	public MarcheProgrammeC getMarche() {
		return this.marche;
	}

	public void setMarche(MarcheProgrammeC marche) {
		this.marche = marche;
	}

	public List<EmployeC> getListEmploye() {
		return this.listEmploye;
	}

	public void setListEmploye(List<EmployeC> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public DetailCotisationEmployeC getSelectedDetailCotisation() {
		return this.selectedDetailCotisation;
	}

	public void setSelectedDetailCotisation(DetailCotisationEmployeC selectedDetailCotisation) {
		this.selectedDetailCotisation = selectedDetailCotisation;
	}

	public List<SelectItem> getListDirection() {
		return listDirection;
	}

	public void setListDirection(List<SelectItem> listDirection) {
		this.listDirection = listDirection;
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

	public List<SelectItem> getListPrime() {
		return this.listPrime;
	}

	public void setListPrime(List<SelectItem> listPrime) {
		this.listPrime = listPrime;
	}

	public List<SelectItem> getListIndemnite() {
		return this.listIndemnite;
	}

	public void setListIndemnite(List<SelectItem> listIndemnite) {
		this.listIndemnite = listIndemnite;
	}

	public int getIdIndemnite() {
		return this.idIndemnite;
	}

	public void setIdIndemnite(int idIndemnite) {
		this.idIndemnite = idIndemnite;
	}

	public int getIdCotisation() {
		return this.idCotisation;
	}

	public void setIdCotisation(int idCotisation) {
		this.idCotisation = idCotisation;
	}

	public List<SelectItem> getListCotisation() {
		return this.listCotisation;
	}

	public void setListCotisation(List<SelectItem> listCotisation) {
		this.listCotisation = listCotisation;
	}

	public List<SelectItem> getListDeduction() {
		return this.listDeduction;
	}

	public void setListDeduction(List<SelectItem> listDeduction) {
		this.listDeduction = listDeduction;
	}

	public PrimeIndemniteC getSelectedPrimeIndemnite() {
		return this.selectedPrimeIndemnite;
	}

	public void setSelectedPrimeIndemnite(PrimeIndemniteC selectedPrimeIndemnite) {
		this.selectedPrimeIndemnite = selectedPrimeIndemnite;
	}

	public String getNomRecherche() {
		return this.nomRecherche;
	}

	public void setNomRecherche(String nomRecherche) {
		this.nomRecherche = nomRecherche;
	}

	public int getNiveauCategorie() {
		return this.niveauCategorie;
	}

	public void setNiveauCategorie(int niveauCategorie) {
		this.niveauCategorie = niveauCategorie;
	}

	public String getCodeGrade() {
		return this.codeGrade;
	}

	public void setCodeGrade(String codeGrade) {
		this.codeGrade = codeGrade;
	}

	public String getCodeCategorieEmploye() {
		return this.codeCategorieEmploye;
	}

	public void setCodeCategorieEmploye(String codeCategorieEmploye) {
		this.codeCategorieEmploye = codeCategorieEmploye;
	}

	public String getDesignationGrade() {
		return this.designationGrade;
	}

	public void setDesignationGrade(String designationGrade) {
		this.designationGrade = designationGrade;
	}

	public int getTypeNiveau() {
		return this.typeNiveau;
	}

	public void setTypeNiveau(int typeNiveau) {
		this.typeNiveau = typeNiveau;
	}

	public int getIdPersonnel() {
		return this.idPersonnel;
	}

	public void setIdPersonnel(int idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public List<SelectItem> getListPersonnels() {
		return this.listPersonnels;
	}

	public void setListPersonnels(List<SelectItem> listPersonnels) {
		this.listPersonnels = listPersonnels;
	}

	public List<SelectItem> getListCategoriePersonnel() {
		return this.listCategoriePersonnel;
	}

	public void setListCategoriePersonnel(List<SelectItem> listCategoriePersonnel) {
		this.listCategoriePersonnel = listCategoriePersonnel;
	}

	public List<SelectItem> getListDeductions() {
		return this.listDeductions;
	}

	public void setListDeductions(List<SelectItem> listDeductions) {
		this.listDeductions = listDeductions;
	}

	public double getMontantVirement() {
		return this.montantVirement;
	}

	public void setMontantVirement(double montantVirement) {
		this.montantVirement = montantVirement;
	}

	public int getIdService() {
		return this.idService;
	}

	public List<SelectItem> getListDepartement() {
		return listDepartement;
	}

	public void setListDepartement(List<SelectItem> listDepartement) {
		this.listDepartement = listDepartement;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public List<SelectItem> getListServices() {
		return this.listServices;
	}

	public void setListServices(List<SelectItem> listServices) {
		this.listServices = listServices;
	}

	public double getMontantPrime() {
		return this.montantPrime;
	}

	public void setMontantPrime(double montantPrime) {
		this.montantPrime = montantPrime;
	}

	public double getMontantIndemnite() {
		return this.montantIndemnite;
	}

	public void setMontantIndemnite(double montantIndemnite) {
		this.montantIndemnite = montantIndemnite;
	}

	public double getMontantSalarial() {
		return this.montantSalarial;
	}

	public void setMontantSalarial(double montantSalarial) {
		this.montantSalarial = montantSalarial;
	}

	public double getMontantPatronal() {
		return this.montantPatronal;
	}

	public void setMontantPatronal(double montantPatronal) {
		this.montantPatronal = montantPatronal;
	}

	public double getMontantDeduction() {
		return this.montantDeduction;
	}

	public void setMontantDeduction(double montantDeduction) {
		this.montantDeduction = montantDeduction;
	}

	public boolean isDesactiver2emeEmployeur() {
		return this.desactiver2emeEmployeur;
	}

	public void setDesactiver2emeEmployeur(boolean desactiver2emeEmployeur) {
		this.desactiver2emeEmployeur = desactiver2emeEmployeur;
	}

	public int getIdFonction() {
		return this.idFonction;
	}

	public void setIdFonction(int idFonction) {
		this.idFonction = idFonction;
	}

	public List<SelectItem> getListFonctions() {
		return this.listFonctions;
	}

	public void setListFonctions(List<SelectItem> listFonctions) {
		this.listFonctions = listFonctions;
	}

	public String getNumeroBankDeduc() {
		return this.numeroBankDeduc;
	}

	public void setNumeroBankDeduc(String numeroBankDeduc) {
		this.numeroBankDeduc = numeroBankDeduc;
	}

	public String getNumeroBanquePaiement() {
		return this.numeroBanquePaiement;
	}

	public void setNumeroBanquePaiement(String numeroBanquePaiement) {
		this.numeroBanquePaiement = numeroBanquePaiement;
	}

	public BanqueC getBanqueDeduction() {
		return this.banqueDeduction;
	}

	public void setBanqueDeduction(BanqueC banqueDeduction) {
		this.banqueDeduction = banqueDeduction;
	}

	public BanqueC getBanquePaiement() {
		return this.banquePaiement;
	}

	public void setBanquePaiement(BanqueC banquePaiement) {
		this.banquePaiement = banquePaiement;
	}

	public DeductionC getDeduction() {
		return this.deduction;
	}

	public void setDeduction(DeductionC deduction) {
		this.deduction = deduction;
	}

	public PrimeIndemniteC getIndemnite() {
		return this.indemnite;
	}

	public void setIndemnite(PrimeIndemniteC indemnite) {
		this.indemnite = indemnite;
	}

	public DroitC getDroit() {
		return this.droit;
	}

	public void setDroit(DroitC droit) {
		this.droit = droit;
	}

	public CotisationC getCotisation() {
		return this.cotisation;
	}

	public void setCotisation(CotisationC cotisation) {
		this.cotisation = cotisation;
	}

	public CotisationC getComission() {
		return comission;
	}

	public void setComission(CotisationC comission) {
		this.comission = comission;
	}

	public List<SelectItem> getListComission() {
		return listComission;
	}

	public void setListComission(List<SelectItem> listComission) {
		this.listComission = listComission;
	}

	public int getIdCom() {
		return idCom;
	}

	public void setIdCom(int idCom) {
		this.idCom = idCom;
	}

	public DetailComissionEmployeC getSelectedDetailComission() {
		return selectedDetailComission;
	}

	public void setSelectedDetailComission(DetailComissionEmployeC selectedDetailComission) {
		this.selectedDetailComission = selectedDetailComission;
	}

	public double getMontantCom() {
		return montantCom;
	}

	public void setMontantCom(double montantCom) {
		this.montantCom = montantCom;
	}

	public double getTauxCom() {
		return tauxCom;
	}

	public void setTauxCom(double tauxCom) {
		this.tauxCom = tauxCom;
	}

	public int getIdDirection() {
		return idDirection;
	}

	public void setIdDirection(int idDirection) {
		this.idDirection = idDirection;
	}

	public int getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}

	public void changementHeure() {
		if (this.parm != null && getNombreHeureNormal() > this.parm.getNbreHeureMois()) {
			setNombreHeureNormal(0);
			HelperC.afficherMessage("Information",
					"Vous ne pouvez pas dépasser " + this.parm.getNbreHeureMois() + " heures");
		}
	}

	public void changementTauxLogement() {
		if (getPourcentageLogement() > 0.0D)
			setMontantLogement(0.0D);
	}

	public void changementMTLogement() {
		if (getMontantLogement() > 0.0D)
			setPourcentageLogement(0.0D);
	}

	public List<SelectItem> getListbasepaie() {
		if (this.listbasepaie == null) {
			this.listbasepaie = new ArrayList<SelectItem>();
		} else {
			this.listbasepaie.clear();
		}
		this.listbasepaie.add(new SelectItem(""));
		this.listbasepaie.add(new SelectItem("Horaire"));
		this.listbasepaie.add(new SelectItem("Journalier"));
		this.listbasepaie.add(new SelectItem("Mensuel"));
		return this.listbasepaie;
	}

	public void setListbasepaie(List<SelectItem> listbasepaie) {
		this.listbasepaie = listbasepaie;
	}

	public void findEmployeByCode() {
		this.selected = FactoryDAO.getInstance().getEmploye(getCode(), true);
		if (this.selected != null) {
			setObject();
		} else {
			clear(false);
		}
	}

	public void changeEmployeOccasionnel() {
		if (isDeuxiemeEmployeur())
			setDeuxiemeEmployeur(false);
	}

	public void changeDeuxiemeEmploi() {
		if (isEmployeOccasionnel())
			setEmployeOccasionnel(false);
	}

	public void focusCode() {
		this.nomRecherche = "";
		if (this.listEmploye != null)
			this.listEmploye.clear();
		HelperC.afficherMessage("Information", "Double-cliquer pour lance une recherche");
	}

	public void clearListEmployes() {
		this.listEmploye.clear();
	}

	public void chargerEmployes() {
		this.listEmploye = FactoryDAO.getInstance().getListEmployeSimple(this.nomRecherche);
		HelperC.afficherMessage("Information", String.valueOf(this.listEmploye.size()) + " trouvé(s)",
				FacesMessage.SEVERITY_INFO);
	}

	public void changePersonnel(ValueChangeEvent event) {
		this.idPersonnel = ((Integer) event.getNewValue()).intValue();
		setStaff(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel,
				Tables.getTableName(Tables.TableName.personnel)));

		setIdPersnl(this.idPersonnel);

		if (this.idPersonnel > 0) {
			this.idCategorie = 0;
			this.listCategoriePersonnel.clear();

			Iterator<CategoriePersonnelC> iterator = FichierBaseDAO.getInstance()
					.getListCategoriePersonnelParIdPersonnel(this.idPersonnel).iterator();
			while (iterator.hasNext()) {
				CategoriePersonnelC cat = iterator.next();
				this.listCategoriePersonnel.add(new SelectItem(Integer.valueOf(cat.getId()), cat.getDesignation()));
			}

		}
	}

	public void changeCategorie(ValueChangeEvent event) {
		this.idCategorie = ((Integer) event.getNewValue()).intValue();
		if (this.idCategorie != 0)
			setCategoriePersonnel(FichierBaseDAO.getInstance().getCategoriePersonnel(this.idCategorie));
		setIdCatgrie(this.idCategorie);
		if (this.idCategorie > 0) {
			this.listgrade.clear();
			this.listgrade.add(new SelectItem(Integer.valueOf(0), ""));

			Iterator<GradePersonnelC> iterator = FichierBaseDAO.getInstance()
					.getGradesPersonnelParIdCategorie(this.idCategorie).iterator();
			while (iterator.hasNext()) {
				GradePersonnelC grade = iterator.next();
				this.listgrade.add(new SelectItem(Integer.valueOf(grade.getId()), grade.getDesignation()));
			}

		} else {
			this.listgrade.clear();
			this.idCategorie = 0;
			this.idGrade = 0;
			this.idNiveauFormation = 0;
		}
	}

	public void changeGrade(ValueChangeEvent event) {
		if (event != null) {
			this.idGrade = ((Integer) event.getNewValue()).intValue();
			if (this.idGrade > 0) {
				setIdGrd(this.idGrade);
				setGradePersonnel(FichierBaseDAO.getInstance().getGradePersonnel(this.idGrade));
				if (getGradePersonnel() != null) {
					this.traitementSalarial = getGradePersonnel().getTraitementMensuel();
				}
			} else {
				this.idGrade = 0;
				setIdGrd(this.idGrade);
				setCodeGrade("");
			}
		}
	}

	public void chargerNiveauFormation(ValueChangeEvent event) {
		this.idNiveauFormation = ((Integer) event.getNewValue()).intValue();
		GradePersonnelDetailC detail = null;
		setIdNvFormt(this.idNiveauFormation);

		if (this.idNiveauFormation > 0) {
			if (this.idGrade > 0)
				detail = FichierBaseDAO.getInstance().getGradePersonnelDetailNiveauFormation(this.idGrade,
						this.idNiveauFormation);
			if (detail != null && getId() == 0) {
				setSalaireBase(this.traitementSalarial * detail.getTauxBonusSalaire());
			}
			setNiveauFormation(FichierBaseDAO.getInstance().getBaseById(this.idNiveauFormation,
					Tables.getTableName(Tables.TableName.niveauFormation)));
		}
	}

	public void changeFonction(ValueChangeEvent event) {
		this.idFonction = ((Integer) event.getNewValue()).intValue();
		setFonction(FichierBaseDAO.getInstance().getBaseById(this.idFonction,
				Tables.getTableName(Tables.TableName.fonction)));
		setIdFnctn(this.idFonction);
	}

	public void changeDirection(ValueChangeEvent event) {
		idDirection = ((Integer) event.getNewValue()).intValue();

		if (this.idDirection > 0) {
			listDepartement.clear();
			listDepartement.add(new SelectItem(Integer.valueOf(0), ""));

			for (DirectionC d : FichierBaseDAO.getInstance().getListDirectionParOrgane(idDirection)) {
				listDepartement.add(new SelectItem(d.getId(), d.getDesignation()));
			}

		}
	}

	public void changeDepartement(ValueChangeEvent event) {
		idDepartement = ((Integer) event.getNewValue()).intValue();

		if (idDepartement > 0) {
			this.listServices.clear();
			this.listServices.add(new SelectItem(Integer.valueOf(0), ""));

			this.idService = 0;
			for (ServicesC serv : FichierBaseDAO.getInstance().getListeServices(idDepartement)) {

				listServices.add(new SelectItem(Integer.valueOf(serv.getId()), serv.getDesignation()));
			}

		}
	}

	public void changeService(ValueChangeEvent event) {
		this.idService = ((Integer) event.getNewValue()).intValue();

		setIdServce(this.idService);

	}

	public void changeLieuTravail(ValueChangeEvent event) {
		this.idLieuTravail = ((Integer) event.getNewValue()).intValue();
		if (this.idLieuTravail > 0) {
			setLieuTravail(FichierBaseDAO.getInstance().getBaseById(this.idLieuTravail,
					Tables.getTableName(Tables.TableName.lieuxTravail)));
		} else {
			setLieuTravail(null);
		}
	}

	public void changeContrat(ValueChangeEvent event) {
		this.idContrat = ((Integer) event.getNewValue()).intValue();
		if (this.idContrat > 0) {
			setContrat(FichierBaseDAO.getInstance().getBaseById(this.idContrat,
					Tables.getTableName(Tables.TableName.typeContrat)));
		} else {
			setContrat(null);
		}
	}

	public void chargerIdPrime(ValueChangeEvent ev) {
		this.idPrime = Integer.valueOf(ev.getNewValue().toString()).intValue();
		this.montantPrime = 0.0D;
		if (this.idPrime > 0) {
			this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrime);
		}
	}

	public void changerIdComission(ValueChangeEvent ev) {
		this.idCom = Integer.valueOf(ev.getNewValue().toString()).intValue();
		comission = FichierBaseDAO.getInstance().getCotisation(this.idCom);
	}

	public void chargerIdCotisation(ValueChangeEvent ev) {
		this.idCotisation = Integer.valueOf(ev.getNewValue().toString()).intValue();
		this.cotisation = FichierBaseDAO.getInstance().getCotisation(this.idCotisation);
	}

	public void chargerIdDeduction(ValueChangeEvent ev) {
		this.idDeduction = ((Integer) ev.getNewValue()).intValue();
		this.montantDeduction = 0.0D;
		if (this.idDeduction > 0) {
			this.deduction = FichierBaseDAO.getInstance().getDeduction(this.idDeduction);
		} else {

			this.deduction = null;
		}
	}

	public void ajouterDetailPrime() {
		if (this.prime == null) {
			HelperC.afficherAttention("Information", "Veuillez selectionner la prime");

		} else {

			for (DetailPrimeEmployeC det : getListeDetailPrime()) {
				if (det.getPrime().getId() == this.prime.getId()) {
					this.selectedDetailPrime = det;
					this.selectedDetailPrime.setExiste(true);

					break;
				}
			}
			if (this.selectedDetailPrime == null) {
				this.selectedDetailPrime = new DetailPrimeEmployeC();
			}
			this.selectedDetailPrime.setPrime(this.prime);
			this.selectedDetailPrime.setMontant(getMontantPrime());
			this.selectedDetailPrime.setMontantS(HelperC.TraitementMontant.getMontantFormate(getMontantPrime(), 0));
			this.selectedDetailPrime.setTaux(this.tauxPrm);
			this.selectedDetailPrime.setIdParametre(this.idParmPrm);

			if (!this.selectedDetailPrime.isExiste()) {
				if (!this.selectedDetailPrm) {
					getListeDetailPrime().add(this.selectedDetailPrime);
				} else {

					getListeDetailPrime().remove(this.indexPrm);
					getListeDetailPrime().add(this.indexPrm, this.selectedDetailPrime);
				}
			}
			clearDetailPrime();
		}
	}

	public void enleverDetailPrime() {
		if (getListeDetailPrime() == null) {
			setListeDetailPrime(new ArrayList<DetailPrimeEmployeC>());
		}
		if (this.selectedDetailPrime != null) {
			getListeDetailPrimeDeleted().add(this.selectedDetailPrime);
			getListeDetailPrime().remove(this.selectedDetailPrime);

			clearDetailPrime();
		} else {
			HelperC.afficherAttention("Information", "Il n'y a aucun élément Ã  enlever!");
		}
	}

	private void clearDetailPrime() {
		this.idPrime = 0;
		this.prime = null;
		setMontantPrime(0.0D);
		this.selectedDetailPrime = null;
		this.tauxPrm = 0.0D;
		this.indexPrm = 0;
		this.idParmPrm = 0;
		this.selectedDetailPrm = false;
	}

	public void onRowselectedDetailPrime() {
		this.indexPrm = getListeDetailPrime().indexOf(this.selectedDetailPrime);
		setDetailPrimeEmploye();
	}

	private void setDetailPrimeEmploye() {
		if (this.selectedDetailPrime != null) {
			this.prime = this.selectedDetailPrime.getPrime();
			this.selectedDetailPrm = true;
			if (this.prime != null)
				this.idPrime = this.selectedDetailPrime.getPrime().getId();
			this.idParmPrm = this.selectedDetailPrime.getIdParametre();
			this.montantPrime = this.selectedDetailPrime.getMontant();
			this.tauxPrm = this.selectedDetailPrime.getTaux();
		}
	}

	public void chargerIdIndemnite(ValueChangeEvent ev) {
		this.idIndemnite = ((Integer) ev.getNewValue()).intValue();
		this.indemnite = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idIndemnite);
	}

	public void ajouterDetailIndemnite() {
		if (this.indemnite == null) {
			HelperC.afficherMessage("Information", "Veuillez selectionner l'indemnité");
		} else if (getMontantIndemnite() == 0.0D && this.tauxInd == 0.0D) {
			HelperC.afficherMessage("Information", "Veuillez saisir le montant de l'indemnitéou le taux");
		} else {

			for (DetailIndemniteEmployeC det : getListeDetailIndemnite()) {
				if (det.getIndemnite().getId() == this.indemnite.getId()) {
					this.selectedDetailIndemnite = det;
					this.selectedDetailIndemnite.setExiste(true);

					break;
				}
			}
			if (this.selectedDetailIndemnite == null)
				this.selectedDetailIndemnite = new DetailIndemniteEmployeC();
			this.selectedDetailIndemnite.setIndemnite(this.indemnite);
			this.selectedDetailIndemnite.setMontant(getMontantIndemnite());
			this.selectedDetailIndemnite
					.setMontantS(HelperC.TraitementMontant.getMontantFormate(getMontantIndemnite(), 0));
			this.selectedDetailIndemnite.setTaux(this.tauxInd);

			if (!this.selectedDetailIndemnite.isExiste()) {
				getListeDetailIndemnite().add(this.selectedDetailIndemnite);
			}
			clearDetailIndemnite();
		}
	}

	public void enleverDetailIndemnite() {
		if (getListeDetailIndemnite() != null) {
			setListeDetailIndemnite(new ArrayList<DetailIndemniteEmployeC>());
		}
		if (this.selectedDetailIndemnite != null) {
			getListeDetailIndemniteDeleted().add(this.selectedDetailIndemnite);
			getListeDetailIndemnite().remove(this.selectedDetailIndemnite);
			clearDetailIndemnite();
		} else {
			HelperC.afficherMessage("Information", "Il n'y a aucun élément Ã  enlever!", FacesMessage.SEVERITY_ERROR);
		}
	}

	private void clearDetailIndemnite() {
		this.idIndemnite = 0;
		this.indemnite = null;
		setMontantIndemnite(0.0D);
		this.selectedDetailIndemnite = null;
		this.tauxInd = 0.0D;
	}

	public void onRowselectedDetailIndemnite() {
		setDetailIndemniteEmploye();
	}

	private void setDetailIndemniteEmploye() {
		if (this.selectedDetailIndemnite != null) {
			this.indemnite = this.selectedDetailIndemnite.getIndemnite();

			if (this.indemnite != null) {
				this.idIndemnite = this.selectedDetailIndemnite.getIndemnite().getId();
			}
			setMontantIndemnite(this.selectedDetailIndemnite.getMontant());
		}
	}

	public void ajouterDetailCotisation() {
		if (this.cotisation == null) {
			HelperC.afficherMessage("Information", "Veillez selectionner la cotisation ou l'impét");
		} else {

			for (DetailCotisationEmployeC det : getListeDetailCotisation()) {
				if (det.getCotisation().getId() == this.idCotisation) {
					this.selectedDetailCotisation = det;
					this.selectedDetailCotisation.setExiste(true);

					break;
				}
			}
			if (this.selectedDetailCotisation == null) {
				this.selectedDetailCotisation = new DetailCotisationEmployeC();
			}
			this.selectedDetailCotisation.setCotisation(this.cotisation);
			this.selectedDetailCotisation.setMontantPatronal(this.montantPatronal);
			this.selectedDetailCotisation.setMontantSalarial(this.montantSalarial);

			if (!this.selectedDetailCotisation.isExiste()) {
				if (!this.selectedDetailCot) {
					getListeDetailCotisation().add(this.selectedDetailCotisation);
				} else {

					getListeDetailCotisation().remove(this.indexCot);
					getListeDetailCotisation().add(this.indexCot, this.selectedDetailCotisation);
				}
			}

			clearDetailCotisation();
		}
	}

	public void enleverDetailCotisation() {
		if (getListeDetailCotisation() == null) {
			setListeDetailCotisation(new ArrayList<DetailCotisationEmployeC>());
		}
		if (this.selectedDetailCotisation != null) {
			getListeDetailCotisationDeleted().add(this.selectedDetailCotisation);
			getListeDetailCotisation().remove(this.selectedDetailCotisation);
			clearDetailCotisation();
			this.selectedDetailCotisation = null;
		} else {
			HelperC.afficherMessage("Information", "Il n'y a aucun élément Ã  enlever!", FacesMessage.SEVERITY_ERROR);
		}
	}

	private void clearDetailCotisation() {
		this.idCotisation = 0;
		setCotisation((CotisationC) null);
		setMontantSalarial(0.0D);
		this.montantPatronal = 0.0D;
		this.selectedDetailCotisation = null;
		this.indexCot = 0;
		this.selectedDetailCot = false;
	}

	public void onRowselectedDetailCotisation() {
		this.indexCot = getListeDetailCotisation().indexOf(this.selectedDetailCotisation);
		setDetailCotisationEmploye();
	}

	private void setDetailCotisationEmploye() {
		if (this.selectedDetailCotisation != null) {
			setCotisation(this.selectedDetailCotisation.getCotisation());
			this.selectedDetailCot = true;
			if (getCotisation() != null)
				this.idCotisation = this.selectedDetailCotisation.getCotisation().getId();
			setMontantSalarial(this.selectedDetailCotisation.getMontantSalarial());
			setMontantPatronal(this.selectedDetailCotisation.getMontantPatronal());
		}
	}

	public void ajouterDetailComission() {
		if (this.comission == null) {
			HelperC.afficherMessage("Information", "Veillez selectionner la commission ou l'impét");
		} else {

			for (DetailComissionEmployeC det : getListeDetailComission()) {
				if (det.getComission().getId() == this.idCom) {
					this.selectedDetailComission = det;
					this.selectedDetailComission.setExist(true);

					break;
				}
			}
			if (this.selectedDetailComission == null) {
				this.selectedDetailComission = new DetailComissionEmployeC();
			}
			this.selectedDetailComission.setComission(this.comission);
			this.selectedDetailComission.setMontant(montantCom);
			this.selectedDetailComission.setTaux(tauxCom);

			if (!this.selectedDetailComission.isExist()) {
				if (!this.selectedDetailCom) {
					getListeDetailComission().add(this.selectedDetailComission);
				} else {

					getListeDetailComission().remove(this.indexCom);
					getListeDetailComission().add(this.indexCom, this.selectedDetailComission);
				}
			}

			clearComission();
		}
	}

	public void onRowDetailComissionSelected() {
		indexCom = getListeDetailComission().indexOf(selectedDetailComission);
		setDetailComission();
	}

	private void setDetailComission() {
		if (selectedDetailComission != null) {
			comission = selectedDetailComission.getComission();
			idCom = comission.getId();
			montantCom = selectedDetailComission.getMontant();
			selectedDetailCom = true;
			tauxCom = selectedDetailComission.getTaux();

		}
	}

	public void removeComission() {
		if (selectedDetailComission != null) {
			if (getListeDetailComission().size() > 0) {
				if (selectedDetailComission.getId() > 0)
					getListeDetailComissionDeleted().add(selectedDetailComission);
				getListeDetailComission().remove(selectedDetailComission);
				clearComission();
			}
		}
	}

	private void clearComission() {
		idCom = 0;
		indexCom = 0;
		selectedDetailComission = null;
		montantCom = 0;
		selectedDetailCom = false;
		tauxCom = 0;
		comission = null;

	}

	public void ajouterDetailDeduction() {
		if (this.deduction == null) {
			HelperC.afficherMessage("Information", "Veillez selectionner la déduction ou l'impét");
		} else {

			for (DetailDeductionC det : getListeDetailDeduction()) {
				if (det.getDeduction().getId() == this.idDeduction) {
					this.selectedDetailDeduction = det;
					this.selectedDetailDeduction.setExiste(true);

					break;
				}
			}

			if (this.selectedDetailDeduction == null) {
				this.selectedDetailDeduction = new DetailDeductionC();
			}
			this.selectedDetailDeduction.setDeduction(this.deduction);
			this.selectedDetailDeduction.setBanque(this.banqueDeduction);
			this.selectedDetailDeduction.setNumeroCpte(this.numeroBankDeduc);
			this.selectedDetailDeduction.setMontant(getMontantDeduction());
			this.selectedDetailDeduction.setMontantS(HelperC.decimalNumber(getMontantDeduction(), 0, true));

			if (!this.selectedDetailDeduction.isExiste()) {
				if (!this.selectedDetailDed) {
					getListeDetailDeduction().add(this.selectedDetailDeduction);
				} else {
					getListeDetailDeduction().remove(this.indexDed);
					getListeDetailDeduction().add(this.indexDed, this.selectedDetailDeduction);
				}
			}

			if (getListeDetailDeduction().size() > 0) {
				for (DetailDeductionC det : getListeDetailDeduction()) {
					det.setBanque(det.getBanque());
				}
			}

			clearDetailDeduction();
		}
	}

	public void enleverDetailDeduction() {
		if (this.selectedDetailDeduction != null) {
			if (getListeDetailDeductionDeleted() == null) {
				setListeDetailDeductionDeleted(new ArrayList<DetailDeductionC>());
			}
			if (this.selectedDetailDeduction.getId() > 0) {
				getListeDetailDeductionDeleted().add(this.selectedDetailDeduction);
				getListeDetailDeduction().remove(this.selectedDetailDeduction);

				clearDetailDeduction();
			}
		}
	}

	private void clearDetailDeduction() {
		this.idDeduction = 0;
		this.deduction = null;
		setMontantDeduction(0.0D);
		this.numeroBankDeduc = "";
		this.idBanqueDeduction = 0;
		this.banqueDeduction = null;
		this.selectedDetailDeduction = null;
		this.indexDed = 0;
		this.selectedDetailDed = false;
	}

	public void onRowselectedDetailDeduction() {
		setDetailDeductionEmploye();
	}

	private void setDetailDeductionEmploye() {
		if (this.selectedDetailDeduction != null) {
			this.deduction = this.selectedDetailDeduction.getDeduction();
			this.numeroBankDeduc = this.selectedDetailDeduction.getNumeroCpte();
			if (this.deduction != null)
				this.idDeduction = this.selectedDetailDeduction.getDeduction().getId();
			this.indexDed = getListeDetailDeduction().indexOf(this.selectedDetailDeduction);
			this.selectedDetailDed = true;
			if (this.selectedDetailDeduction.getBanque() != null) {
				this.idBanqueDeduction = this.selectedDetailDeduction.getBanque().getId();
				this.banqueDeduction = this.selectedDetailDeduction.getBanque();
			} else {
				this.idBanqueDeduction = 0;
			}

			setMontantDeduction(this.selectedDetailDeduction.getMontant());
		}
	}

	public void changeDateEntre() {
		if (getDateEntreS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateEntre(null);
		} else {
			setDateEntre(HelperC.validerDate(getDateEntreS()));
			if (getDateEntre() == null) {
				setDateEntreS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {
				setDateEntreS(HelperC.convertDate(getDateEntre()));
			}
		}
	}

	public void changeDateDelivrance() {
		if (getDateDelivranceS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateDelivrance(null);
		} else {
			setDateDelivrance(HelperC.validerDate(getDateDelivranceS()));
			if (getDateDelivrance() == null) {
				setDateDelivranceS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {
				setDateDelivranceS(HelperC.convertDate(getDateDelivrance()));
			}
		}
	}

	public void changeDateSortie() {
		if (getDateSortieS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateSortie(null);
		} else {
			setDateSortie(HelperC.validerDate(getDateSortieS()));
			if (getDateSortie() == null) {
				setDateSortieS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {
				setDateSortieS(HelperC.convertDate(getDateSortie()));
			}
		}
	}

	public void changeDateNaissance() {
		if (getDateNaissanceS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateNaissance(null);
		} else {
			setDateNaissance(HelperC.validerDate(getDateNaissanceS()));
			if (getDateNaissance() == null) {
				setDateNaissanceS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {
				setDateNaissanceS(HelperC.convertDate(getDateNaissance()));
			}
		}
	}

	public void changeDateDebutContrat() {
		if (getDateDebutContratS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateDebutContrat(null);
		} else {
			setDateDebutContrat(HelperC.validerDate(getDateDebutContratS()));
			if (getDateDebutContrat() == null) {
				setDateDebutContratS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {
				setDateDebutContratS(HelperC.convertDate(getDateDebutContrat()));
			}
		}
	}

	public void changeDateFinContrat() {
		if (getDateFinContratS().replace("/", "").replace("_", "").trim().equals("")) {
			setDateFinContrat(null);
		} else {
			setDateFinContrat(HelperC.validerDate(getDateFinContratS()));
			if (getDateFinContrat() == null) {
				setDateFinContratS("");
				HelperC.afficherMessage("Information", "Date invalide");
			} else {
				setDateFinContratS(HelperC.convertDate(getDateFinContrat()));
			}
		}
	}

	public void chargerBanqueDeduction(ValueChangeEvent ev) {
		this.idBanqueDeduction = ((Integer) ev.getNewValue()).intValue();
		this.banqueDeduction = FichierBaseDAO.getInstance().getBanque(this.idBanqueDeduction);
	}

	public void chargerBanquePaiement(ValueChangeEvent ev) {
		this.idBanquePaiement = ((Integer) ev.getNewValue()).intValue();
		this.banquePaiement = FichierBaseDAO.getInstance().getBanque(this.idBanquePaiement);
	}

	public void ajouterBanquePaiement() {
		if (this.banquePaiement == null) {
			HelperC.afficherMessage("Information", "Veuillez préciser la banque de paiement SVP!");
		} else {

			for (DetailBanqueEmployeC det : getListBanquePaiement()) {

				if (det.getBanque().getId() == this.idBanquePaiement) {
					this.banquePaiementSelected = det;
					this.banquePaiementSelected.setExiste(true);

					break;
				}
			}
			if (this.banquePaiementSelected == null) {
				this.banquePaiementSelected = new DetailBanqueEmployeC();
			}
			this.banquePaiementSelected.setBanque(this.banquePaiement);
			this.banquePaiementSelected.setNumeroCompte(this.numeroBanquePaiement);
			this.banquePaiementSelected.setPourcentageSalaire(getPourcentageSalaire());
			this.banquePaiementSelected.setMontant(this.montantVirement);
			this.banquePaiementSelected
					.setMontantS(HelperC.TraitementMontant.getMontantFormate(this.montantVirement, 0));
			this.banquePaiementSelected.setPrincipal(this.bnkPrincipal);
			if (!this.banquePaiementSelected.isExiste()) {
				getListBanquePaiement().add(this.banquePaiementSelected);
			}
			clearDetailBanque();
		}
	}

	private void clearDetailBanque() {
		this.idBanquePaiement = 0;
		this.banquePaiement = null;
		setPourcentageSalaire(0.0D);
		this.montantVirement = 0.0D;
		this.numeroBanquePaiement = "";
		this.banquePaiementSelected = null;
		this.bnkPrincipal = false;
	}

	public void enleverDetailBanque() {
		if (getListBanquePaiement().size() > 0 && this.banquePaiementSelected != null) {
			if (this.banquePaiementSelected.getId() > 0)
				getListBanquePaiementDeleted().add(this.banquePaiementSelected);
			getListBanquePaiement().remove(this.banquePaiementSelected);
		}
	}

	public void onRowselectedDetailBanque() {
		setDetailBanqueEmploye();
	}

	private void setDetailBanqueEmploye() {
		if (this.banquePaiementSelected != null) {
			if (this.banquePaiementSelected.getBanque() != null) {
				this.idBanquePaiement = this.banquePaiementSelected.getBanque().getId();
				this.banquePaiement = this.banquePaiementSelected.getBanque();
				this.bnkPrincipal = this.banquePaiementSelected.isPrincipal();
				this.numeroBanquePaiement = this.banquePaiementSelected.getNumeroCompte();
			} else {
				this.idBanquePaiement = 0;
				this.banquePaiement = null;
			}

			setPourcentageSalaire(this.banquePaiementSelected.getPourcentageSalaire());
			this.montantVirement = this.banquePaiementSelected.getMontant();
		}
	}

	public void telecharger(FileUploadEvent event) {
		try {
			InputStream in = event.getFile().getInputstream();

			if (getCode() != null && !getCode().equals("")) {

				setUrlPhoto(String.valueOf(getCode()) + ".jpg");

				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
						.getContext();
				OutputStream out = new FileOutputStream(new File(
						String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + getUrlPhoto()));
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = in.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				in.close();
				out.flush();
				out.close();
			} else {

				HelperC.afficherAttention("ATTENTION", "Il faut préciser la matricul de l'employé!");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void save() {
		if (getId() == 0 && !this.droit.isCreer()) {
			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
			return;
		}
		if (getId() > 0 && !this.droit.isModifier()) {
			HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
			return;
		}

		if (getCode().trim().equals("")) {
			HelperC.afficherMessage("Information", "Veuillez saisir le code!", FacesMessage.SEVERITY_ERROR);
		}
		if (getNomPrenom().trim().equals("")) {
			HelperC.afficherMessage("Information", "Veuillez saisir le nom!", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (getDateEntre() == null) {
			HelperC.afficherMessage("Information", "Veuillez saisir la date d'Entrée en Fonction!",
					FacesMessage.SEVERITY_ERROR);
			return;
		}

		this.selected = FactoryDAO.getInstance().getEmploye(getCode(), getId());

		if (this.selected != null) {
			HelperC.afficherMessage("information",
					"Ce nom et prénom de l'employéest déja enregistré par quelqu'un d'autre");
			return;
		}

		setEtatGrade(true);
		setEtatOrgane(true);
		setEtatProfession(true);
		setEtat(true);

		Historique hist = new Historique();
		hist.setDateOperation(Calendar.getInstance().getTime());
		hist.setOperateur(this.operateur);
		if (getId() == 0) {
			hist.setOperation("Création de l'employé" + getCode());
		} else {
			hist.setOperation("Modification de l'employé" + getCode());
		}
		hist.setTable(Tables.getTableName(Tables.TableName.employe));
		setHistorique(hist);

		if (FactoryDAO.getInstance().insertUpdateEmploye(this)) {
			HelperC.afficherMessage("information", "L'employéest enregistréavec succès");
			clear(true);
		} else {

			HelperC.afficherMessage("Désolé", "Echec d'enregistrement");
		}
	}

	public void afficherEmployeSelectionne() {
		if (this.selected != null) {

			setCode(this.selected.getCode());
			findEmployeByCode();
			PrimeFaces.current().executeScript("PF('dlgEmployes').hide();");
		}
	}

	private void setObject() {
		if (this.selected != null) {

			setId(this.selected.getId());
			setCode(this.selected.getCode());
			setNom(this.selected.getNom());
			setPrenom(this.selected.getPrenom());
			setNomPrenom(this.selected.getNomPrenom());
			setAdresse(this.selected.getAdresse());
			setCni(this.selected.getCni());
			setEmail(this.selected.getEmail());
			setTelMobile(this.selected.getTelMobile());
			setDateDelivrance(this.selected.getDateDelivrance());
			setDateDelivranceS(this.selected.getDateDelivranceS());
			setTelHabitat(this.selected.getTelHabitat());
			setTelService(this.selected.getTelService());
			setMatricule(this.selected.getMatricule());
			setLibelleNationalite(this.selected.getLibelleNationalite());
			setDateNaissance(this.selected.getDateNaissance());
			setDateNaissanceS(this.selected.getDateNaissanceS());
			setLieuNaissance(this.selected.getLieuNaissance());
			setDateEntre(this.selected.getDateEntre());
			setDateEntreS(this.selected.getDateEntreS());
			setDateSortie(this.selected.getDateSortie());
			setDateSortieS(this.selected.getDateSortieS());
			setSection(this.selected.getSection());
			setSexe(this.selected.getSexe());
			setEtatCivil(this.selected.getEtatCivil());
			setNumCaisseSociale(this.selected.getNumCaisseSociale());
			setSuffixeComptable(this.selected.getSuffixeComptable());
			setNumCAMMut(this.selected.getNumCAMMut());
			setNombreHeureNormal(this.selected.getNombreHeureNormal());
			setEmployeurConjoint(this.selected.getEmployeurConjoint());
			setComplement(this.selected.getComplement());
			setBasePaiement(this.selected.getBasePaiement());
			setPourcentageLogement(this.selected.getPourcentageLogement());
			setEmployeOccasionnel(this.selected.isEmployeOccasionnel());
			setDeuxiemeEmployeur(this.selected.isDeuxiemeEmployeur());
			setModeReglement(this.selected.getModeReglement());
			setListeDetailPrime(this.selected.getListeDetailPrime());
			setListeDetailIndemnite(this.selected.getListeDetailIndemnite());
			setListeDetailCotisation(this.selected.getListeDetailCotisation());
			setListeDetailDeduction(this.selected.getListeDetailDeduction());
			setListBanquePaiement(this.selected.getListBanquePaiement());
			setLieuTravail(this.selected.getLieuTravail());
			setListeDetailComission(this.selected.getListeDetailComission());
			setUrlPhoto(this.selected.getUrlPhoto());
			setPourcentageLogement(this.selected.getPourcentageLogement());
			setMontantLogement(this.selected.getMontantLogement());
			setTypeEmploye(this.selected.getTypeEmploye());
			setSalaireBase(this.selected.getSalaireBase());
			setJourConge(selected.getJourConge());
			if (this.selected.getIdFnctn() > 0) {

				this.idFonction = this.selected.getIdFnctn();
				setIdFnctn(this.idFonction);
			} else {
				this.idFonction = 0;
				setIdFnctn(0);
				setFonction(null);
			}

			if (this.selected.getUrlPhoto() == null || this.selected.getUrlPhoto().equals("")) {
				setUrlPhoto("avatar.jpg");
			}
			if (this.selected.getIdPersnl() > 0) {

				this.idPersonnel = this.selected.getIdPersnl();
				setIdPersnl(this.idPersonnel);
			}

			if (this.selected.getIdCatgrie() > 0) {

				this.idCategorie = this.selected.getIdCatgrie();
				setIdCatgrie(this.idCategorie);

				Iterator<CategoriePersonnelC> iterator = FichierBaseDAO.getInstance()
						.getListCategoriePersonnelParIdPersonnel(this.idPersonnel).iterator();
				while (iterator.hasNext()) {
					CategoriePersonnelC cat = iterator.next();
					this.listCategoriePersonnel.add(new SelectItem(Integer.valueOf(cat.getId()), cat.getDesignation()));
				}

			}

			if (this.selected.getIdGrd() > 0) {

				this.idGrade = this.selected.getIdGrd();
				setIdGrd(this.idGrade);

				Iterator<GradePersonnelC> iterator = FichierBaseDAO.getInstance()
						.getListGradeParCategoriePersonnel(this.idCategorie).iterator();
				while (iterator.hasNext()) {
					GradePersonnelC grade = iterator.next();
					this.listgrade.add(new SelectItem(Integer.valueOf(grade.getId()), grade.getDesignation()));
				}
			}

			if (this.selected.getIdNvFormt() > 0) {
				this.idNiveauFormation = this.selected.getIdNvFormt();
				setIdNvFormt(this.idNiveauFormation);
			}

			if (this.selected.getLieuTravail() != null) {
				setLieuTravail(this.selected.getLieuTravail());
				this.idLieuTravail = this.selected.getLieuTravail().getId();
			} else {
				this.idLieuTravail = 0;
			}
			setContrat(this.selected.getContrat());

			if (getContrat() != null) {
				this.idContrat = getContrat().getId();
			} else {
				this.idContrat = 0;
			}
			setDateDebutContrat(this.selected.getDateDebutContrat());
			setDateDebutContratS(this.selected.getDateDebutContratS());
			setDateFinContrat(this.selected.getDateFinContrat());
			setDateFinContratS(this.selected.getDateFinContratS());
			setEtat(this.selected.isEtat());

			if (this.selected.getListeDetailPrime().size() > 0) {
				setListeDetailPrime(this.selected.getListeDetailPrime());
			} else {
				getListeDetailPrime().clear();
			}

			if (this.selected.getListeDetailIndemnite().size() > 0) {
				setListeDetailIndemnite(this.selected.getListeDetailIndemnite());
			} else {
				getListeDetailIndemnite().clear();
			}
			if (this.selected.getListeDetailCotisation().size() > 0) {
				setListeDetailCotisation(this.selected.getListeDetailCotisation());
			} else {
				getListeDetailCotisation().clear();
			}
			if (this.selected.getListeDetailDeduction().size() > 0) {
				setListeDetailDeduction(this.selected.getListeDetailDeduction());
			} else {
				getListeDetailDeduction().clear();
			}
			if (this.selected.getListBanquePaiement().size() > 0) {
				setListBanquePaiement(this.selected.getListBanquePaiement());

				for (DetailBanqueEmployeC det : getListBanquePaiement()) {
					det.setMontantS(HelperC.TraitementMontant.getMontantFormate(det.getMontant(), 0));
				}
			} else {

				getListBanquePaiement().clear();
			}
			if (this.selected.getDetailOrgane() != null) {

				setDetailOrgane(this.selected.getDetailOrgane());

				idDirection = this.selected.getDetailOrgane().getIdDirection();

				if (idDirection > 0) {

					for (DirectionC dire : FichierBaseDAO.getInstance().getListDirectionParOrgane(idDirection)) {
						listDepartement.add(new SelectItem(Integer.valueOf(dire.getId()), dire.getDesignation()));
					}
					idDepartement = this.selected.getDetailOrgane().getIdDepartmt();
				}
				if (idDepartement > 0) {

					for (ServicesC servi : FichierBaseDAO.getInstance().getListeServices(idDepartement)) {
						listServices.add(new SelectItem(servi.getId(), servi.getDesignation()));
					}
					idService = this.selected.getDetailOrgane().getIdSrvice();
				}
			}
		}
	}

	private void clear(boolean b) {
		if (b) {
			setCode("");
		}
		this.idCategorie = 0;
		this.idDirection = 0;
		this.idContrat = 0;
		this.idNiveau = 0;
		this.idPersonnel = 0;
		this.idNiveauFormation = 0;
		this.idDepartement = 0;
		this.idFonction = 0;
		this.idService = 0;

		this.idLieuTravail = 0;

		this.listgrade.clear();
		this.selected = null;
		setId(0);
		setTypeEmploye(0);
		setCni("");
		setMatricule("");
		setNom("");
		setPrenom("");
		setNomPrenom("");
		setDateDelivranceS("");
		setLibelleNationalite("");
		setAdresse("");
		setLieuNaissance("");
		setTelHabitat("");
		setDateNaissance(null);
		setDateNaissanceS("");
		setTelMobile("");
		setEmail("");
		setTelService("");
		setNombreHeureNormal(0);
		setSalaireBase(0.0D);
		setPourcentageLogement(0.0D);
		setEmployeOccasionnel(false);
		setDeuxiemeEmployeur(false);
		setModeReglement(0);
		setContrat(null);
		setDateDebutContrat(null);
		setDateDebutContratS("");
		setDateFinContrat(null);
		setDateFinContratS("");
		setDateEntre(null);
		setDateEntreS("");
		setDateSortie(null);
		setDateSortieS("");
		setEtat(true);
		setSection("");
		setNumCaisseSociale("");
		setSuffixeComptable("");
		setSexe(0);
		setEtatCivil(0);
		setEmployeurConjoint("");
		setComplement(0);
		setNumCAMMut("");
		setStaff(null);
		setNiveauFormation(null);
		setDetailGrade(null);
		setDirectionUb(null);
		setDirection(null);
		setService(null);
		setDetailOrgane(null);
		setBasePaiement(0);
		setPourcentageLogement(0.0D);
		setMontantLogement(0.0D);
		setFonction(null);
		setJourConge(0);
		setUrlPhoto("avatar.jpg");
		clearDetailIndemnite();
		clearDetailPrime();
		clearDetailCotisation();
		clearDetailDeduction();
		clearDetailBanque();
		getListeDetailPrime().clear();
		getListeDetailIndemnite().clear();
		getListeDetailCotisation().clear();
		getListeDetailDeduction().clear();
		getListBanquePaiement().clear();
		this.listgrade.add(new SelectItem(Integer.valueOf(0), ""));
		this.listServices.add(new SelectItem(Integer.valueOf(0), ""));
		this.listDepartement.add(new SelectItem(Integer.valueOf(0), ""));
	}

	public void chargerEmploye() {
		this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche,
				this.prenomEmployeRecherche, false);
	}

	public void delete() {
		if (getId() > 0) {
			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			hist.setOperation("Suppression de l'employé" + getCode());
			hist.setTable(Tables.getTableName(Tables.TableName.employe));
			setHistorique(hist);
			if (FactoryDAO.getInstance().deleteEmploye(this)) {
				HelperC.afficherMessage("information", "La suppression a réussie!");
				clear(true);
			} else {
				HelperC.afficherMessage("Désolé", "Echec de suppression");
			}
		} else
			HelperC.afficherDeleteMessage();
	}

	public void initialise() {
		clear(true);
	}

	public void visualiserFiche() {
		EditionFicheEmmployeB fiche = new EditionFicheEmmployeB();
		fiche.setEmploye(this.selected);
		fiche.visualiser();
	}

	public void changeValueTaux() {
		if (this.tauxPrm > 0.0D)
			setMontantPrime(0.0D);
	}

	public void changeValueForfait() {
		if (this.montantPrime > 0.0D) {
			this.tauxPrm = 0.0D;
		}
	}

	public void addAllIndemnite() {
		if (!getCode().equals("") && !getNomPrenom().equals("")) {

			chargerPrimePersonnel();
			chargerPrimeFonctionPersonnel();
			chargerPrimePersonnelCategorie();
			chargerPrimePeronnelGrade();
			chargerPrimePeronnelCatGrade();
			chargerPrimePeronnelGradeFx();
			chargerPrimePeronnelCatFx();
			chargerPrimeForAll();
			chargerAllPrime();
		}
	}

	private void chargerPrimeFonctionPersonnel() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(this.idPersonnel, 0, 0,
				this.idFonction);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
			if (ind != null) {
				this.idParmPrm = prm.getId();
				this.prime = ind;
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}

	private void chargerPrimePersonnel() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(this.idPersonnel, 0, 0, 0);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
			if (ind != null) {

				this.prime = ind;
				this.idParmPrm = prm.getId();
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}

	private void chargerAllPrime() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(this.idPersonnel,
				this.idCategorie, this.idGrade, this.idFonction);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
			if (ind != null) {

				this.prime = ind;
				this.idParmPrm = prm.getId();
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}

	private void chargerPrimePersonnelCategorie() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(this.idPersonnel,
				this.idCategorie, 0, 0);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
			if (ind != null) {

				this.prime = ind;
				this.idParmPrm = prm.getId();
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}

	private void chargerPrimePeronnelGrade() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(this.idPersonnel, 0,
				this.idGrade, 0);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
			if (ind != null) {

				this.prime = ind;
				this.idParmPrm = prm.getId();
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}

	private void chargerPrimePeronnelCatGrade() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(this.idPersonnel,
				this.idCategorie, this.idGrade, 0);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
			if (ind != null) {

				this.prime = ind;
				this.idParmPrm = prm.getId();
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}

	private void chargerPrimePeronnelGradeFx() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(this.idPersonnel, 0,
				this.idGrade, this.idFonction);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
			if (ind != null) {

				this.prime = ind;
				this.idParmPrm = prm.getId();
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}

	private void chargerPrimePeronnelCatFx() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(this.idPersonnel,
				this.idCategorie, 0, this.idFonction);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
			if (ind != null) {

				this.prime = ind;
				this.idParmPrm = prm.getId();
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}

	private void chargerPrimeForAll() {
		PrimeIndemniteC ind = null;
		this.listParametragePrime = FichierBaseDAO.getInstance().getListParametragePrime(0, 0, 0, 0);
		for (ParametragePrimeC prm : this.listParametragePrime) {

			ind = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());

			if (ind != null) {

				this.prime = ind;
				this.idParmPrm = prm.getId();
				this.montantPrime = prm.getForfait();
				this.tauxPrm = prm.getTaux();
				ajouterDetailPrime();
			}
		}
	}
	public void getAccountPlan() {
		String urlFile="";
		if (urlFile == null || urlFile.equals("")) {

			HelperC.afficherAttention("Attention", "Il faut préciser le fichier excel !");
			return;

		}
		File f = new File(urlFile);
		try {
			
			FileInputStream file = new FileInputStream(f);
			XSSFWorkbook xSSFWorkbook = new XSSFWorkbook(file);
			Sheet s = xSSFWorkbook.getSheetAt(0);
			int rownumber = s.getLastRowNum() + 1;

			EmployeC emp = null;
			String code = null, libelle = null;
			int tmp = 0, i = 0;

			Iterator<Row> rowIterator = s.iterator();

			while (rowIterator.hasNext()) {

				Row row = s.getRow(i);
				if (row != null) {
					Cell cellCode = row.getCell(0);
					Cell cellNom = row.getCell(1);

					code = cellCode.getStringCellValue();
					libelle = cellNom.getStringCellValue();

					emp = FactoryDAO.getInstance().getEmployeSimple("");
			
					if (emp != null) {

						
					}

					i++;
				}
			}
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
