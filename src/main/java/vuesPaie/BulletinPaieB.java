package vuesPaie;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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

import classesPaie.*;
import editionPaie.EditionBulletinB;
import persistencePaie.*;

 
@ManagedBean
@ViewScoped
public class BulletinPaieB extends BulletinPaieC implements Serializable {
	private static final long serialVersionUID = 1L;
	private EmployeC employe;
	private List<EmployeC> listEmploye;
	private String codeEmploye;
	private String nomEmploye;
	private String matriculeEmploye;
	private String codeRechEmp;
	private String nomRechEmp;
	private String prenomRechEmp;
	private String moisLettre;
	private String infoMsg;
	private BulletinPaieC bulletinEntete;
	private BulletinAllocationC detailAllocation;
	private BulletinHeureSupplementaireC detailHeurSup;
	private BulletinIndemniteC detailIndemnite;
	private BulletinPrimeC detailPrime;
	private BulletinCotisationC detailCotisation;
	private BulletinDeductionC detailDeduction;
	private BulletinCreditC detailCredit;
	private BulletinAvanceC detailAvance;
	private BulletinComissionC detailComission;
	private List<BulletinPaieC> listBulletin;
	private List<SelectItem> listePrimes;
	private List<SelectItem> listeIndemnites;
	private List<SelectItem> listeCotisations;
	private List<SelectItem> listeDeductions;
	private List<SelectItem> listeComission;
	private CotisationC cotisation;
	private CotisationC cmission;
	private PrimeIndemniteC prime;
	private PrimeIndemniteC indemnite;
	private DeductionC deduction;
	private int idCotisation;
	private int idCom;
	private int idPrime;
	private int idEmploye;
	private int idIndmnite;
	private int idDeduction;
	private int nbrDecimaux;
	private boolean disableSave,disableMsg;
	private double montantCotisation;
	private double montantPrime;
	private double montantIndemnite;
	private double montantDeduction;
	private double montantAvanceQz;
	private double baseCalcule;
	private double partPatronal;
	private double montantCredit;
	private double monatntAvance;
	private double monatntCmssion;
	private double tauxCom;
	private OperateurC operateur;
	private ExerciceC exercice;
	private HttpSession session = HelperC.getSession();
	int indexCot;
	int indexCom;
	int indexPrm;
	int indexDed;
	int indexInd;
	int indexCrd;
	int indexAvc;
	int typeCotisation;
	boolean isComSelected;
	boolean isCotSelected;
	boolean isPrmSelected;
	boolean isDedSelected;
	boolean isIndSelected;
	boolean isCotExist;
	boolean isComExist;
	boolean isPrmExist;
	boolean isDedExist;
	boolean isCalculPrm;
	boolean isCalculCotisation;
	String libelleDeduction;
	CreditRembourseC detailCredRembourse;
	private DroitC droit;
    DetailBanqueEmployeC bkVrt;
	// private CreditRembourseC remboursement;
	ParametrageGeneralC parametre;
	TraitementSalarialC traitement;
	SaisiePositionEmployeC position;
	FinCarriereC finCarriere;
	List<DetailPrimeEmployeC> listPrimeEmploye;
	List<DetailCotisationEmployeC> listCotEmploye;
	double montantBaseHs;
	double montantNetMin;
	double montantPrimeHs;
	double tauxSal;
	double tauxPatr;
	ParametragePrimeC parametrePrime;
	ParametragePrimeDetailC parametrePrmDet;
	ParametreCotisationC parametreCot,parametreCom;
	List<CotisationDetailC> listParmCotDetail;
	List<CotisationDetailC> listCotPrm;
	int typeEmploye;
	int idParmPrim;
	DetailCotisationEmployeC detCotEmp;

	Historique hist;

	public CotisationC getCmission() {
		return cmission;
	}

	public void setCmission(CotisationC cmission) {
		this.cmission = cmission;
	}

	public int getIdCom() {
		return idCom;
	}

	public void setIdCom(int idCom) {
		this.idCom = idCom;
	}

	public double getMonatntAvance() {
		return this.monatntAvance;
	}

	public void setMonatntAvance(double monatntAvance) {
		this.monatntAvance = monatntAvance;
	}

	public double getMontantCredit() {
		return this.montantCredit;
	}

	public void setMontantCredit(double montantCredit) {
		this.montantCredit = montantCredit;
	}

	public String getMoisLettre() {
		return this.moisLettre;
	}

	public void setMoisLettre(String moisLettre) {
		this.moisLettre = moisLettre;
	}

	public DroitC getDroit() {
		return this.droit;
	}

	public void setDroit(DroitC droit) {
		this.droit = droit;
	}

	public double getBaseCalcule() {
		return this.baseCalcule;
	}

	public void setBaseCalcule(double baseCalcule) {
		this.baseCalcule = baseCalcule;
	}

	public double getPartPatronal() {
		return this.partPatronal;
	}

	public void setPartPatronal(double partPatronal) {
		this.partPatronal = partPatronal;
	}

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public BulletinAvanceC getDetailAvance() {
		return this.detailAvance;
	}

	public void setDetailAvance(BulletinAvanceC detailAvance) {
		this.detailAvance = detailAvance;
	}

	public BulletinCreditC getDetailCredit() {
		return this.detailCredit;
	}

	public void setDetailCredit(BulletinCreditC detailCredit) {
		this.detailCredit = detailCredit;
	}

	public double getMontantAvanceQz() {
		return this.montantAvanceQz;
	}

	public void setMontantAvanceQz(double montantAvanceQz) {
		this.montantAvanceQz = montantAvanceQz;
	}

	public String getCodeRechEmp() {
		return this.codeRechEmp;
	}

	public void setCodeRechEmp(String codeRechEmp) {
		this.codeRechEmp = codeRechEmp;
	}

	public String getNomRechEmp() {
		return this.nomRechEmp;
	}

	public void setNomRechEmp(String nomRechEmp) {
		this.nomRechEmp = nomRechEmp;
	}

	public String getPrenomRechEmp() {
		return this.prenomRechEmp;
	}

	public void setPrenomRechEmp(String prenomRechEmp) {
		this.prenomRechEmp = prenomRechEmp;
	}

	public List<EmployeC> getListEmploye() {
		return this.listEmploye;
	}

	public void setListEmploye(List<EmployeC> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public double getMontantCotisation() {
		return this.montantCotisation;
	}

	public void setMontantCotisation(double montantCotisation) {
		this.montantCotisation = montantCotisation;
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

	public double getMontantDeduction() {
		return this.montantDeduction;
	}

	public void setMontantDeduction(double montantDeduction) {
		this.montantDeduction = montantDeduction;
	}

	public CotisationC getCotisation() {
		return this.cotisation;
	}

	public void setCotisation(CotisationC cotisation) {
		this.cotisation = cotisation;
	}

	public PrimeIndemniteC getPrime() {
		return this.prime;
	}

	public void setPrime(PrimeIndemniteC prime) {
		this.prime = prime;
	}

	public PrimeIndemniteC getIndemnite() {
		return this.indemnite;
	}

	public void setIndemnite(PrimeIndemniteC indemnite) {
		this.indemnite = indemnite;
	}

	public DeductionC getDeduction() {
		return this.deduction;
	}

	public void setDeduction(DeductionC deduction) {
		this.deduction = deduction;
	}

	public int getIdCotisation() {
		return this.idCotisation;
	}

	public void setIdCotisation(int idCotisation) {
		this.idCotisation = idCotisation;
	}

	public int getIdPrime() {
		return this.idPrime;
	}

	public void setIdPrime(int idPrime) {
		this.idPrime = idPrime;
	}

	public int getIdIndmnite() {
		return this.idIndmnite;
	}

	public void setIdIndmnite(int idIndmnite) {
		this.idIndmnite = idIndmnite;
	}

	public int getIdDeduction() {
		return this.idDeduction;
	}

	public void setIdDeduction(int idDeduction) {
		this.idDeduction = idDeduction;
	}

	public List<SelectItem> getListePrimes() {
		return this.listePrimes;
	}

	public void setListePrimes(List<SelectItem> listePrimes) {
		this.listePrimes = listePrimes;
	}

	public List<SelectItem> getListeIndemnites() {
		return this.listeIndemnites;
	}

	public void setListeIndemnites(List<SelectItem> listeIndemnites) {
		this.listeIndemnites = listeIndemnites;
	}

	public List<SelectItem> getListeCotisations() {
		return this.listeCotisations;
	}

	public void setListeCotisations(List<SelectItem> listeCotisations) {
		this.listeCotisations = listeCotisations;
	}

	public List<SelectItem> getListeDeductions() {
		return this.listeDeductions;
	}

	public void setListeDeductions(List<SelectItem> listeDeductions) {
		this.listeDeductions = listeDeductions;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public String getCodeEmploye() {
		return this.codeEmploye;
	}

	public void setCodeEmploye(String codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public String getNomEmploye() {
		return this.nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getMatriculeEmploye() {
		return this.matriculeEmploye;
	}

	public void setMatriculeEmploye(String matriculeEmploye) {
		this.matriculeEmploye = matriculeEmploye;
	}

	public BulletinPaieC getBulletinEntete() {
		return this.bulletinEntete;
	}

	public void setBulletinEntete(BulletinPaieC bulletinEntete) {
		this.bulletinEntete = bulletinEntete;
	}

	public BulletinAllocationC getDetailAllocation() {
		return this.detailAllocation;
	}

	public void setDetailAllocation(BulletinAllocationC detailAllocation) {
		this.detailAllocation = detailAllocation;
	}

	public BulletinHeureSupplementaireC getDetailHeurSup() {
		return this.detailHeurSup;
	}

	public void setDetailHeurSup(BulletinHeureSupplementaireC detailHeurSup) {
		this.detailHeurSup = detailHeurSup;
	}

	public BulletinIndemniteC getDetailIndemnite() {
		return this.detailIndemnite;
	}

	public void setDetailIndemnite(BulletinIndemniteC detailIndemnite) {
		this.detailIndemnite = detailIndemnite;
	}

	public BulletinPrimeC getDetailPrime() {
		return this.detailPrime;
	}

	public void setDetailPrime(BulletinPrimeC detailPrime) {
		this.detailPrime = detailPrime;
	}

	public BulletinCotisationC getDetailCotisation() {
		return this.detailCotisation;
	}

	public void setDetailCotisation(BulletinCotisationC detailCotisation) {
		this.detailCotisation = detailCotisation;
	}

	public BulletinDeductionC getDetailDeduction() {
		return this.detailDeduction;
	}

	public void setDetailDeduction(BulletinDeductionC detailDeduction) {
		this.detailDeduction = detailDeduction;
	}

	public boolean isDisableSave() {
		return this.disableSave;
	}

	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
	}

	public List<BulletinPaieC> getListBulletin() {
		return this.listBulletin;
	}

	public void setListBulletin(List<BulletinPaieC> listBulletin) {
		this.listBulletin = listBulletin;
	}

	public int getNbrDecimaux() {
		return this.nbrDecimaux;
	}

	public void setNbrDecimaux(int nbrDecimaux) {
		this.nbrDecimaux = nbrDecimaux;
	}

	public String getInfoMsg() {
		return this.infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}

	public BulletinComissionC getDetailComission() {
		return detailComission;
	}

	public void setDetailComission(BulletinComissionC detailComission) {
		this.detailComission = detailComission;
	}

	public List<SelectItem> getListeComission() {
		return listeComission;
	}

	public void setListeComission(List<SelectItem> listeComission) {
		this.listeComission = listeComission;
	}

	public double getMonatntCmssion() {
		return monatntCmssion;
	}

	public void setMonatntCmssion(double monatntCmssion) {
		this.monatntCmssion = monatntCmssion;
	}

	public double getTauxCom() {
		return tauxCom;
	}

	public void setTauxCom(double tauxCom) {
		this.tauxCom = tauxCom;
	}

	@PostConstruct
	private void init() {
		this.operateur = new OperateurC();
		this.exercice = new ExerciceC();
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");
		if (codeOperateur == null || codeExercice == null) {

			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {

			this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
			this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
			Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
			if (userFonction != null) {
				this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.bulletinPaie);
			}
			chargementCotisation();
			chargementPrime();
			disableMsg=true;
			chargementDeduction();
			chargementComission();
			this.listPrimeEmploye = new ArrayList<DetailPrimeEmployeC>();
			this.listCotEmploye = new ArrayList<DetailCotisationEmployeC>();
			this.parametre = FichierBaseDAO.getInstance().getParametrageGeneral();
			if (this.parametre != null) {

				setTauxJourAdd(this.parametre.getTauxJrFerie());
				setNbreJourNormal(this.parametre.getNbreJourMois());
				setNombreHeureNormal(this.parametre.getNbreJourMois() * this.parametre.getNbreHeureJour());
				setNombreHeurePreste(this.parametre.getNbreJourMois() * this.parametre.getNbreHeureJour());
				this.montantNetMin = this.parametre.getMontantNetMin();
				this.nbrDecimaux = this.parametre.getNbreDecimal();
			}
			this.listCotPrm = new ArrayList<CotisationDetailC>();
		}
	}

	public void chargementBulletin() {
		this.listBulletin = FactoryDAO.getInstance().getListBulletinPaie(this.idEmploye, getMois(),
				this.exercice.getId());

		for (BulletinPaieC btn : this.listBulletin) {

			this.employe = FactoryDAO.getInstance().getEmploye(btn.getIdEmploye(), false);
			btn.setMoisPrint(HelperC.getMoisEnTouteLettre(btn.getMois()));
			btn.setNomEmploye(this.employe.getNomPrenom());
			if (this.employe.getMatricule().equals("")) {

				btn.setCodeEmploye(this.employe.getCode());
				continue;
			}
			btn.setCodeEmploye(this.employe.getMatricule());
		}
	}

	public void onBulletinSelected(SelectEvent event) {
		this.bulletinEntete = (BulletinPaieC) event.getObject();
		if (this.bulletinEntete != null) {

			this.employe = FactoryDAO.getInstance().getEmploye(this.bulletinEntete.getIdEmploye(), false);
			this.typeEmploye = this.employe.getTypeEmploye();
			this.codeEmploye = this.employe.getCode();
			this.nomEmploye = this.employe.getNomPrenom();
			this.idEmploye = this.employe.getId();
			setBulletinValues();
		}
		PrimeFaces.current().executeScript("PF('dlgBlt').hide();");
	}

	private void searchBulletin() {
		this.bulletinEntete = FactoryDAO.getInstance().getBulletinPaie(this.employe.getId(), getDatePaie(),
				this.exercice.getId());
		setBulletinValues();
	}

	private void setBulletinValues() {
		disableMsg=true;
		if (this.bulletinEntete != null) {
			disableMsg=false;
			setId(this.bulletinEntete.getId());
			setCommentaire(this.bulletinEntete.getCommentaire());
			setDatePaie(this.bulletinEntete.getDatePaie());
			setDatePaieString(HelperC.convertDate(getDatePaie()));
			setNombreHeureNormal(this.bulletinEntete.getNombreHeureNormal());
			setNombreHeurePreste(this.bulletinEntete.getNombreHeurePreste());
			setTotalLogement(this.bulletinEntete.getTotalLogement());
			setMois(this.bulletinEntete.getMois());
			setMoisLettre(HelperC.moisEnLettres(getMois()));
			setBasePaiement(this.bulletinEntete.getBasePaiement());
			setModeReglement(this.bulletinEntete.getModeReglement());
			setSalaireBase(this.bulletinEntete.getSalaireBase());
			setTotalmontantBase(this.bulletinEntete.getTotalmontantBase());
			setMontantBaseHSup(this.bulletinEntete.getMontantBaseHSup());
			setTauxJourAdd(this.bulletinEntete.getTauxJourAdd());
			setTotalJourAdd(this.bulletinEntete.getTotalJourAdd());
			setNbreJourAdd(this.bulletinEntete.getNbreJourAdd());
			setListAllocation(this.bulletinEntete.getListAllocation());
			setListAvance(this.bulletinEntete.getListAvance());
			setListCredit(this.bulletinEntete.getListCredit());
			setListRemboursement(this.getListRemboursement());
			setListDeduction(this.bulletinEntete.getListDeduction());
			setListeCotisation(this.bulletinEntete.getListeCotisation());
			setListeIndemnite(this.bulletinEntete.getListeIndemnite());
			setListePrime(this.bulletinEntete.getListePrime());
			setListHeureSup(this.bulletinEntete.getListHeureSup());
			setListComission(this.bulletinEntete.getListComission());
			setTotalNetPay(this.bulletinEntete.getTotalNetPay());
			setNbreJourNormal(this.bulletinEntete.getNbreJourNormal());
			setNbrJourFerie(this.bulletinEntete.getNbrJourFerie());
			setTauxJrsFerie(this.bulletinEntete.getTauxJrsFerie());
			setCompteVirement(bulletinEntete.getCompteVirement());
			setIdBkVrt(bulletinEntete.getIdBkVrt());
		}
	}

	public void onFreeDaysLeave() {
		chargementEmplCotisation();
		calcuNet();
	}

	public void onHourLeave() {
		chargementEmplCotisation();
		calcuNet();
	}

	public void onAddDaysLeave() {
		chargementEmplCotisation();
		calcuNet();
	}

	public void onComplementLeave() {
		calcuNet();
	}

	private void chargementCotisation() {
		this.listeCotisations = new ArrayList<SelectItem>();
		this.listeCotisations.add(new SelectItem(Integer.valueOf(0), ""));

		for (CotisationC cot : FichierBaseDAO.getInstance().getListCotisation(0)) {
			this.listeCotisations.add(new SelectItem(Integer.valueOf(cot.getId()), cot.getDesignation()));
		}
	}

	private void chargementComission() {
		this.listeComission = new ArrayList<SelectItem>();
		this.listeComission.add(new SelectItem(Integer.valueOf(0), ""));

		for (CotisationC cot : FichierBaseDAO.getInstance().getListCotisation(0)) {
			this.listeComission.add(new SelectItem(Integer.valueOf(cot.getId()), cot.getDesignation()));
		}
	}
	
	public void changeCotisation(ValueChangeEvent event) {
		this.idCotisation = ((Integer) event.getNewValue()).intValue();
		this.cotisation = FichierBaseDAO.getInstance().getCotisation(this.idCotisation);
		this.isCalculCotisation = true;
		for (BulletinCotisationC det : getListeCotisation()) {

			if (det.getIdCotisation() == this.idCotisation) {

				this.isCotExist = true;
				return;
			}
		}
	}

	public void changeCmission(ValueChangeEvent event) {
		this.idCom = ((Integer) event.getNewValue()).intValue();
		this.cmission = FichierBaseDAO.getInstance().getCotisation(this.idCotisation);
		this.isCalculCotisation = true;
		for (BulletinComissionC det : getListComission()) {

			if (det.getIdComission() == this.idCom) {

				this.isComExist = true;
				return;
			}
		}
	}
	public void addCotisation() {
		if (this.cotisation != null) {

			if (this.detailCotisation == null) {
				this.detailCotisation = new BulletinCotisationC();
			}
			this.detailCotisation.setIdCotisation(this.idCotisation);
			this.detailCotisation.setLibelleCotisation(this.cotisation.getDesignation());
			this.detailCotisation.setMontantCotisation(this.montantCotisation);
			this.detailCotisation.setCodeCotisation(this.cotisation.getCode());
			this.detailCotisation.setMontantPatronal(this.partPatronal);
			this.detailCotisation.setCotisation(this.cotisation);
			this.detailCotisation.setTauxPatronal(this.tauxPatr);
			this.detailCotisation.setTauxSalarial(this.tauxSal);
			if (!this.isCotSelected) {

				if (!this.isCotExist) {

					getListeCotisation().add(this.detailCotisation);
				} else {

					HelperC.afficherAttention("ATTENTION", "Cette cotisation est déjÃ  ajoutée!");
				}
			} else {

				getListeCotisation().remove(this.indexCot);
				getListeCotisation().add(this.indexCot, this.detailCotisation);
			}

			// for (BulletinCotisationC cot : getListeCotisation())
			// {
			// if(cot.getCotisation().getTypeBaremme()==1)
			// calculValeurCotisation(cot);
			// }

			calcuNet();
			clearCotisation();
		}
	}
	
	public void addComission() {
		if (this.cmission != null) {

			if (this.detailComission == null) {
				this.detailComission = new BulletinComissionC();
			}
			this.detailComission.setIdComission(idCom);
			
			this.detailComission.setMontant(this.monatntCmssion);
			this.detailComission.setComission(cmission);
			this.detailComission.setTaux(tauxCom);
			
			if (!this.isComSelected) {

				if (!this.isComExist) {

					getListComission().add(this.detailComission);
				} else {

					HelperC.afficherAttention("ATTENTION", "Cette commission est déjÃ  ajoutée!");
				}
			} else {

				getListComission().remove(this.indexCom);
				getListComission().add(this.indexCom, this.detailComission);
			}

			// for (BulletinCotisationC cot : getListeCotisation())
			// {
			// if(cot.getCotisation().getTypeBaremme()==1)
			// calculValeurCotisation(cot);
			// }
			clearComission();
			
		}
	}

	public void onCotisationSelected(SelectEvent event) {
		this.detailCotisation = (BulletinCotisationC) event.getObject();
		if (this.detailCotisation != null) {

			this.cotisation = FichierBaseDAO.getInstance().getCotisation(this.detailCotisation.getIdCotisation());
			this.detailCotisation.setLibelleCotisation(this.cotisation.getDesignation());
			this.montantCotisation = this.detailCotisation.getMontantCotisation();
			this.partPatronal = this.detailCotisation.getMontantPatronal();
			this.tauxPatr = this.detailCotisation.getTauxPatronal();
			this.tauxSal = this.detailCotisation.getTauxSalarial();
			this.idCotisation = this.detailCotisation.getIdCotisation();
			this.isCotSelected = true;
			this.indexCot = getListeCotisation().indexOf(this.detailCotisation);
		}
	}

	public void onComissionSelected(SelectEvent event) {
		this.detailComission = (BulletinComissionC) event.getObject();
		if (this.detailComission != null) {

			cmission = FichierBaseDAO.getInstance().getCotisation(this.detailComission.getIdComission());
			this.monatntCmssion = this.detailComission.getMontant();	
			tauxCom=detailComission.getTaux();
			idCom=detailComission.getIdComission();
			this.isComSelected = true;
			this.indexCot = getListComission().indexOf(this.detailComission);
		}
	}
	private void clearCotisation() {
		this.idCotisation = 0;
		this.montantCotisation = 0.0D;
		this.detailCotisation = null;
		this.cotisation = null;
		this.isCotSelected = false;
		this.indexCot = 0;
		this.baseCalcule = 0.0D;
		this.partPatronal = 0.0D;
		this.isCotExist = false;
		this.isCalculCotisation = false;
		this.parametreCot = null;
		this.tauxPatr = 0.0D;
		this.tauxSal = 0.0D;
	}
	
	private void clearComission() {
		cmission = null;
		this.monatntCmssion = 0;	
		idCom=0;
		this.isComSelected = false;
		this.indexCot = 0;
		tauxCom=0;
	}

	private void chargementEmplCotisation() {
		boolean bl = false;
		if (this.employe != null && this.employe.getListeDetailCotisation() != null
				&& this.employe.getListeDetailCotisation().size() > 0) {

			getListeCotisation().clear();

			for (DetailCotisationEmployeC cot : this.employe.getListeDetailCotisation()) {

				this.cotisation = cot.getCotisation();
				this.idCotisation = this.cotisation.getId();

				if (this.montantCotisation == 0.0D && this.partPatronal == 0.0D) {

					this.parametreCot = FichierBaseDAO.getInstance().getParameterCotisation(this.idCotisation, true);

					/// **************************************************LORSQUE PARAMETRE
					/// COTISATION N'A PAS DE DETAIL************************
					if (this.parametreCot != null && this.parametreCot.getListDetail().size() == 0) {

						/// -----------------------------------LORSQUE PARAMETRE COTISATION EST UN
						/// MONTANT FORFAITAIRE-----------------------------------------------
						if (this.parametreCot.getForfaitPatronal() > 0.0D)
							this.partPatronal = this.parametreCot.getForfaitPatronal();

						if (this.parametreCot.getForfaitSalarial() > 0.0D) {
							this.montantCotisation = this.parametreCot.getForfaitSalarial();
						}

						/// --------------------------------------LORSQUE PARAMETRE COTISATION EST UN
						/// POURCENTAGE----------------------------------------------------

						if (this.parametreCot.getTauxPatronal() > 0.0D) {

							switch (this.parametreCot.getTypeBasePatronal()) {
							case 0:
								this.baseCalcule = getSalaireBase();
								break;

							case 1:
								this.baseCalcule = getTotalmontantBase();
								break;
							case 2:
								this.baseCalcule = this.parametreCot.getBaseFixe();
								break;
							}
							if (this.parametreCot.getPlafondBase() > 0.0D) {
								if (this.baseCalcule > this.parametreCot.getPlafondBase())
									this.baseCalcule = this.parametreCot.getPlafondBase();
							}
							if (this.parametreCot.getPlancherBase() > 0.0D) {
								if (this.baseCalcule < this.parametreCot.getPlancherBase()) {
									this.baseCalcule = this.parametreCot.getPlancherBase();
								}
							}
							this.partPatronal = this.baseCalcule * this.parametreCot.getTauxPatronal() / 100.0D;
							this.tauxPatr = this.parametreCot.getTauxPatronal();

							if (this.parametreCot.getPlafonPatronal() > 0.0D
									&& this.partPatronal > this.parametreCot.getPlafonPatronal())
								this.partPatronal = this.parametreCot.getPlafonPatronal();
							if (this.parametreCot.getPlancherPatronal() > 0.0D
									&& this.partPatronal < this.parametreCot.getPlancherPatronal()) {
								this.partPatronal = this.parametreCot.getPlancherPatronal();
							}
						}

						if (this.parametreCot.getTauxSalarial() > 0.0D) {

							this.baseCalcule = 0.0D;
							this.tauxSal = this.parametreCot.getTauxSalarial();

							switch (this.parametreCot.getTypeBaseSalarial()) {
							case 0:
								this.baseCalcule = getSalaireBase();
								break;

							case 1:
								this.baseCalcule = getTotalmontantBase();
								break;
							case 2:
								this.baseCalcule = this.parametreCot.getBaseFixe();
								break;
							}
							if (this.parametreCot.getPlafondBase() > 0.0D) {
								if (this.baseCalcule > this.parametreCot.getPlafondBase())
									this.baseCalcule = this.parametreCot.getPlafondBase();
							}
							if (this.parametreCot.getPlancherBase() > 0.0D) {
								if (this.baseCalcule < this.parametreCot.getPlancherBase()) {
									this.baseCalcule = this.parametreCot.getPlancherBase();
								}
							}
							this.montantCotisation = this.parametreCot.getTauxSalarial() * this.baseCalcule / 100.0D;

							if (this.parametreCot.getPlafonSalarial() > 0.0D
									&& this.montantCotisation > this.parametreCot.getPlafonSalarial()) {
								this.montantCotisation = this.parametreCot.getPlafonSalarial();
							}
							if (this.parametreCot.getPlancherSalarial() > 0.0D
									&& this.montantCotisation < this.parametreCot.getPlancherSalarial()) {
								this.montantCotisation = this.parametreCot.getPlancherSalarial();
							}
						}
						if (cot.getMontantSalarial() > 0.0D) {
							this.montantCotisation = cot.getMontantSalarial();
							bl = true;
						}
						if (cot.getMontantPatronal() > 0.0D) {
							this.partPatronal = cot.getMontantPatronal();
						}
						bl = true;
					} else {

						if (cot.getMontantSalarial() > 0.0D) {
							this.montantCotisation = cot.getMontantSalarial();
							bl = true;
						}
						if (cot.getMontantPatronal() > 0.0D) {
							this.partPatronal = cot.getMontantPatronal();
						}
					}
				}
				if (this.cotisation != null) {
					createNewCotisation(bl);
				}
				bl = false;
			}

			/// ***********************************************************LORSQUE PARAMETRE
			/// COTISATION A DE DETAIL**************************************

			Collections.sort(getListeCotisation(), new CotisationOrderC());
			for (BulletinCotisationC cot : getListeCotisation()) {

				calculValeurCotisation(cot);
				cot.setMontantCotisation(Math.round(cot.getMontantCotisation()));

			}
		}
	}

	private void createNewCotisation(boolean calculated) {
		if (this.detailCotisation == null) {
			this.detailCotisation = new BulletinCotisationC();
		}

		this.detailCotisation.setIdCotisation(this.idCotisation);
		this.detailCotisation.setLibelleCotisation(this.cotisation.getDesignation());
		this.detailCotisation.setCodeCotisation(this.cotisation.getCode());
		this.detailCotisation.setMontantCotisation(this.montantCotisation);
		this.detailCotisation.setMontantPatronal(this.partPatronal);
		this.detailCotisation.setCotisation(this.cotisation);
		this.detailCotisation.setCalculated(calculated);
		this.detailCotisation.setMontantBase(this.baseCalcule);
		this.detailCotisation.setTauxPatronal(this.tauxPatr);
		this.detailCotisation.setTauxSalarial(this.tauxSal);
		this.detailCotisation.setPriority(FichierBaseDAO.getInstance().getPrioriteCot(this.idCotisation));
		getListeCotisation().add(this.detailCotisation);
		clearCotisation();
	}

	private void calculValeurCotisation(BulletinCotisationC cot) {
		this.parametreCot = FichierBaseDAO.getInstance().getParameterCotisation(cot.getIdCotisation(), true);
		double totPrm = 0.0D, totCot = 0.0D, tmpMontant = 0.0D, taux = 0.0D, diff = 0.0D, montantPrm = 0.0D,

				montantSal = 0.0D, montantPatr = 0.0D, montantBase = 0.0D;
		CotisationC cotisation = FichierBaseDAO.getInstance().getCotisation(cot.getIdCotisation());

		if (this.parametreCot != null && this.parametreCot.getListDetail().size() > 0) {

			onlyEmployePrime(this.parametreCot.getId());

			switch (this.parametreCot.getTypeBaseSalarial()) {
			case 0:
				this.baseCalcule = getSalaireBase();
				break;

			case 1:
				this.baseCalcule = getTotalmontantBase();
				break;
			case 2:
				this.baseCalcule = this.parametreCot.getBaseFixe();
				break;
			}
			if (this.listCotPrm.size() > 0) {
				for (CotisationDetailC det : this.listCotPrm) {

					if (det != null) {
						double aloc;
						if (det.getForfait() > 0.0D) {
							totPrm += det.getForfait();
						}
						switch (det.getTypeBase()) {

						case 1:
							if (det.getTaux() > 0.0D) {

								tmpMontant = this.baseCalcule * det.getTaux() / 100.0D;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;

							} else {

								tmpMontant = this.baseCalcule;
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;
							continue;

						case 2:
							if (det.getTauxMax() > 0.0D) {

								double HS = 0.0D;
								HS = this.baseCalcule * det.getTauxMax() / 100.0D;

								if (HS > getTotalHS()) {

									tmpMontant = HS - getTotalHS();

									if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
										tmpMontant = det.getPlafon();
									}
									if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
										tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}

							if (det.getTaux() > 0.0D) {

								tmpMontant = getTotalHS() * det.getTaux();

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}

							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

								tmpMontant = getTotalHS();
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;
							continue;

						case 3:
							if (det.getTauxMax() > 0.0D) {

								double logement = 0.0D;

								logement = this.baseCalcule * det.getTauxMax() / 100.0D;
								if (getTotalLogement() > logement) {

									tmpMontant = getTotalLogement() - logement;

									if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
										tmpMontant = det.getPlafon();
									}
									if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
										tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getTaux() > 0.0D) {

								tmpMontant = getTotalLogement() * det.getTaux() / 100.0D;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

								tmpMontant = getTotalLogement();
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;
							continue;

						case 4:
							aloc = 0.0D;
							if (det.getTauxMax() > 0.0D) {
								aloc = this.baseCalcule * det.getTauxMax() / 100.0D;

								if (aloc > getTotalAllocation())
									tmpMontant = aloc - getTotalAllocation();
								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getTaux() > 0.0D) {

								tmpMontant = getTotalAllocation() * det.getTaux() / 100.0D;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

								tmpMontant = getTotalAllocation();
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;
							continue;
						case 5:

							if (det.getTaux() > 0) {
								tmpMontant = this.getTotalBrute() * det.getTaux() / 100;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}

								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {

									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getForfait() > 0) {
								totPrm += det.getForfait();
							}
							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

								tmpMontant = getTotalBrute();
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;

							break;

						default:
							montantPrm = getValeurPrime(det.getCodeElement(), false);

							if (det.getTauxMax() > 0.0D) {

								double montant = 0.0D;
								montant = this.baseCalcule * det.getTauxMax() / 100.0D;

								if (montantPrm > montant) {

									tmpMontant = montantPrm - montant;
								}
								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
							}
							if (det.getTaux() > 0.0D) {

								tmpMontant = montantPrm * det.getTaux() / 100.0D;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
									tmpMontant = det.getPlancher();
							}
							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {
								tmpMontant = montantPrm;
							}

							totPrm += tmpMontant;
							tmpMontant = 0.0D;
							break;
						}

					}
				}
			}

			this.listParmCotDetail = FichierBaseDAO.getInstance().getAllCotisationDetail(this.parametreCot.getId(), "M",
					"C");

			double montantDet = 0.0D;
			for (CotisationDetailC det : this.listParmCotDetail) {

				montantDet = getValeurCotisation(det.getCodeElement());
				if (det.getTaux() > 0.0D) {
					montantDet = montantDet * det.getTaux() / 100.0D;
				}

				totCot += montantDet;
				montantDet = 0.0D;
			}

			this.listParmCotDetail = FichierBaseDAO.getInstance().getAllCotisationDetail(this.parametreCot.getId(), "M",
					"P");
			for (CotisationDetailC det : this.listParmCotDetail) {

				montantDet = getValeurPrime(det.getCodeElement(), true);
				if (det.getTaux() > 0.0D) {
					montantDet = montantDet * det.getTaux() / 100.0D;
				}
				totCot += montantDet;
				montantDet = 0.0D;
			}

			if (this.parametreCot.getTauxSalarial() > 0.0D) {

				montantBase = totPrm - totCot;

				if (this.parametreCot.getPlafondBase() > 0.0D) {
					if (montantBase > this.parametreCot.getPlafondBase())
						montantBase = this.parametreCot.getPlafondBase();
				}
				if (this.parametreCot.getPlancherBase() > 0.0D) {
					if (montantBase < this.parametreCot.getPlancherBase()) {
						montantBase = this.parametreCot.getPlancherBase();
					}
				}
				if (this.parametreCot.getPlafonSalarial() > 0.0D
						&& montantBase > this.parametreCot.getPlafonSalarial()) {
					montantBase = this.parametreCot.getPlafonSalarial();
				}
				if (this.parametreCot.getPlancherSalarial() > 0.0D
						&& montantBase < this.parametreCot.getPlancherSalarial()) {
					montantBase = this.parametreCot.getPlancherSalarial();
				}

				montantSal = montantBase * this.parametreCot.getTauxSalarial() / 100.0D;

				cot.setTauxSalarial(this.parametreCot.getTauxSalarial());
				cot.setMontantCotisation(montantSal);
				cot.setMontantBase(montantBase);
				cot.setCalculated(true);
			}

			if (this.parametreCot.getForfaitSalarial() > 0.0D) {

				montantSal = this.parametreCot.getForfaitPatronal();
				cot.setMontantCotisation(montantSal);
				cot.setCalculated(true);
			}

			if (this.parametreCot.getForfaitPatronal() > 0.0D) {

				montantPatr = this.parametreCot.getForfaitPatronal();
				cot.setMontantPatronal(montantPatr);
				cot.setCalculated(true);
			}

			if (this.parametreCot.getTauxPatronal() > 0.0D) {

				montantBase = totPrm - totCot;

				if (this.parametreCot.getPlafondBase() > 0.0D) {
					if (montantBase > this.parametreCot.getPlafondBase())
						montantBase = this.parametreCot.getPlafondBase();
				}
				if (this.parametreCot.getPlancherBase() > 0.0D) {
					if (montantBase < this.parametreCot.getPlancherBase()) {
						montantBase = this.parametreCot.getPlancherBase();
					}
				}
				if (this.parametreCot.getPlafonPatronal() > 0.0D
						&& montantBase > this.parametreCot.getPlafonPatronal()) {
					montantBase = this.parametreCot.getPlafonPatronal();
				}
				if (this.parametreCot.getPlancherPatronal() > 0.0D
						&& montantBase < this.parametreCot.getPlancherPatronal()) {
					montantBase = this.parametreCot.getPlancherPatronal();
				}
				montantPatr = montantBase * this.parametreCot.getTauxPatronal() / 100.0D;

				cot.setMontantPatronal(montantPatr);
				cot.setCalculated(true);
				cot.setTauxPatronal(this.parametreCot.getTauxPatronal());
			}

			if (parametreCot.getTauxPlafonSalarial() > 0) {
				double montCot = 0;
				montantBase = totPrm - totCot;
				montCot = montantBase * parametreCot.getTauxPlafonSalarial() / 100;
				if (cot.getMontantCotisation() > montCot)
					montantSal = montCot;
				else
					montantSal = cot.getMontantCotisation();

				cot.setMontantCotisation(montantSal);
				cot.setCalculated(true);
				cot.setTauxSalarial(this.parametreCot.getTauxSalarial());
			}

			if (parametreCot.getTauxPlafonPatronal() > 0) {
				double montCot = 0;

				montantBase = totPrm - totCot;
				montCot = montantBase * parametreCot.getTauxPlafonPatronal() / 100;
				if (cot.getMontantCotisation() > montCot)
					montantPatr = montCot;
				else
					montantPatr = cot.getMontantCotisation();

				cot.setMontantPatronal(montantPatr);
				cot.setCalculated(true);
				cot.setTauxPatronal(this.parametreCot.getTauxPatronal());
			}

			if (cotisation.getTypeBaremme() == 1) {

				montantBase = totPrm - totCot;

				if (this.parametreCot.getPlafondBase() > 0.0D) {
					if (montantBase > this.parametreCot.getPlafondBase())
						montantBase = this.parametreCot.getPlafondBase();
				}
				if (this.parametreCot.getPlancherBase() > 0.0D) {
					if (montantBase < this.parametreCot.getPlancherBase()) {
						montantBase = this.parametreCot.getPlancherBase();
					}
				}
				CalculCotisationParBaremeC.valeurCotisationImpot(montantBase, cotisation, this.typeEmploye);
				cot.setMontantCotisation(CalculCotisationParBaremeC.montantSalarial);
				cot.setMontantPatronal(CalculCotisationParBaremeC.montantPatronal);
				cot.setTauxSalarial(CalculCotisationParBaremeC.taux);
				cot.setMontantBase(montantBase);
				cot.setTypeCotisation(this.typeEmploye);
			}

			if (cotisation.getTypeBaremme() == 2) {
				montantBase = totPrm - totCot;

				if (this.parametreCot.getPlafondBase() > 0.0D) {
					if (montantBase > this.parametreCot.getPlafondBase())
						montantBase = this.parametreCot.getPlafondBase();
				}
				if (this.parametreCot.getPlancherBase() > 0.0D) {
					if (montantBase < this.parametreCot.getPlancherBase()) {
						montantBase = this.parametreCot.getPlancherBase();
					}
				}
				CalculCotisationParBaremeC.valeurCotisationONPR(montantBase);
				montantSal = CalculCotisationParBaremeC.montantSalarial;
				cot.setMontantCotisation(montantSal);
				if (cot.getTauxPatronal() > 0.0D) {
					cot.setMontantPatronal(getSalaireBase() * cot.getTauxPatronal() / 100.0D);
				}

				cot.setCalculated(true);
			}
		}
	}
	private void chargementEmplComission() {
		boolean bl = false;
		if (this.employe != null && this.employe.getListeDetailComission() != null
				&& this.employe.getListeDetailComission().size() > 0) {

			getListComission().clear();

			for (DetailComissionEmployeC com : this.employe.getListeDetailComission()) {

				this.cmission = com.getComission();
				this.idCom = this.cmission.getId();

				if (this.monatntCmssion == 0.0) {

					this.parametreCom = FichierBaseDAO.getInstance().getParameterCotisation(this.idCom, true);

					/// **************************************************LORSQUE PARAMETRE
					/// COTISATION N'A PAS DE DETAIL************************
					if (this.parametreCom != null && this.parametreCom.getListDetail().size() == 0) {

						

						
						tauxCom=com.getTaux();
						
						if (com.getMontant() > 0.0D) {
							this.monatntCmssion= com.getMontant();
							bl = true;
						}
						
					} 
				}
				if (this.cmission != null) {
					createNewComission(bl);
				}
				bl = false;
			}

			/// ***********************************************************LORSQUE PARAMETRE
			/// COTISATION A DE DETAIL**************************************

		
			for (BulletinComissionC com : getListComission()) {

				calculValeurComission(com);
				com.setMontant(Math.round(com.getMontant()));

			}
		}
	}
	private void createNewComission(boolean calculated) {
		if (this.detailComission == null) {
			this.detailComission = new BulletinComissionC();
		}

		this.detailComission.setIdComission(this.idCom);
		
		this.detailComission.setMontant(monatntCmssion);
		
		this.detailComission.setTaux(this.tauxCom);
	
		getListComission().add(this.detailComission);
		clearComission();
	}
	private void calculValeurComission(BulletinComissionC com) {
		parametreCom = FichierBaseDAO.getInstance().getParameterCotisation(com.getIdComission(), true);
		
		double totPrm = 0.0D, totCot = 0.0D, tmpMontant = 0.0D, taux = 0.0D, diff = 0.0D, montantPrm = 0.0D,

				montantCom = 0.0D, montantPatr = 0.0D, montantBase = 0.0D;
		
		CotisationC cotisation = FichierBaseDAO.getInstance().getCotisation(com.getIdComission());

		if (parametreCom != null && parametreCom.getListDetail().size() > 0) {

			onlyEmployePrime(parametreCom.getId());

			switch (this.parametreCom.getTypeBaseSalarial()) {
			case 0:
				this.baseCalcule = getSalaireBase();
				break;

			case 1:
				this.baseCalcule = getTotalmontantBase();
				break;
			case 2:
				this.baseCalcule = this.parametreCom.getBaseFixe();
				break;
			}
			if (this.listCotPrm.size() > 0) {
				for (CotisationDetailC det : this.listCotPrm) {

					if (det != null) {
						double aloc;
						if (det.getForfait() > 0.0D) {
							totPrm += det.getForfait();
						}
						taux=det.getTaux();
						switch (det.getTypeBase()) {

						case 1:
							if (det.getTaux() > 0.0D) {

								tmpMontant = this.baseCalcule * det.getTaux() / 100.0D;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;

							} else {

								tmpMontant = this.baseCalcule;
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;
							continue;

						case 2:
							if (det.getTauxMax() > 0.0D) {

								double HS = 0.0D;
								HS = this.baseCalcule * det.getTauxMax() / 100.0D;

								if (HS > getTotalHS()) {

									tmpMontant = HS - getTotalHS();

									if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
										tmpMontant = det.getPlafon();
									}
									if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
										tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}

							if (det.getTaux() > 0.0D) {

								tmpMontant = getTotalHS() * det.getTaux();

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}

							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

								tmpMontant = getTotalHS();
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;
							continue;

						case 3:
							if (det.getTauxMax() > 0.0D) {

								double logement = 0.0D;

								logement = this.baseCalcule * det.getTauxMax() / 100.0D;
								if (getTotalLogement() > logement) {

									tmpMontant = getTotalLogement() - logement;

									if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
										tmpMontant = det.getPlafon();
									}
									if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
										tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getTaux() > 0.0D) {

								tmpMontant = getTotalLogement() * det.getTaux() / 100.0D;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

								tmpMontant = getTotalLogement();
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;
							continue;

						case 4:
							aloc = 0.0D;
							if (det.getTauxMax() > 0.0D) {
								aloc = this.baseCalcule * det.getTauxMax() / 100.0D;

								if (aloc > getTotalAllocation())
									tmpMontant = aloc - getTotalAllocation();
								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getTaux() > 0.0D) {

								tmpMontant = getTotalAllocation() * det.getTaux() / 100.0D;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

								tmpMontant = getTotalAllocation();
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;
							continue;
						case 5:

							if (det.getTaux() > 0) {
								tmpMontant = this.getTotalBrute() * det.getTaux() / 100;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}

								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {

									tmpMontant = det.getPlancher();
								}
								totPrm += tmpMontant;
							}
							if (det.getForfait() > 0) {
								totPrm += det.getForfait();
							}
							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

								tmpMontant = getTotalBrute();
								totPrm += tmpMontant;
							}
							tmpMontant = 0.0D;

							break;

						default:
							montantPrm = getValeurPrime(det.getCodeElement(), false);

							if (det.getTauxMax() > 0.0D) {

								double montant = 0.0D;
								montant = this.baseCalcule * det.getTauxMax() / 100.0D;

								if (montantPrm > montant) {

									tmpMontant = montantPrm - montant;
								}
								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
							}
							if (det.getTaux() > 0.0D) {

								tmpMontant = montantPrm * det.getTaux() / 100.0D;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
									tmpMontant = det.getPlancher();
							}
							if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {
								tmpMontant = montantPrm;
							}

							totPrm += tmpMontant;
							tmpMontant = 0.0D;
							break;
						}

					}
				}
			}

			this.listParmCotDetail = FichierBaseDAO.getInstance().getAllCotisationDetail(this.parametreCom.getId(), "M",
					"C");

			double montantDet = 0.0D;
			for (CotisationDetailC det : this.listParmCotDetail) {

				montantDet = getValeurCotisation(det.getCodeElement());
				if (det.getTaux() > 0.0D) {
					montantDet = montantDet * det.getTaux() / 100.0D;
				}

				totCot += montantDet;
				montantDet = 0.0D;
			}

			this.listParmCotDetail = FichierBaseDAO.getInstance().getAllCotisationDetail(this.parametreCom.getId(), "M",
					"P");
			for (CotisationDetailC det : this.listParmCotDetail) {

				montantDet = getValeurPrime(det.getCodeElement(), true);
				if (det.getTaux() > 0.0D) {
					montantDet = montantDet * det.getTaux() / 100.0D;
				}
				totCot += montantDet;
				montantDet = 0.0D;
			}
			montantBase = totPrm - totCot;
			//montantCom=montantBase*parametreCom..gettgetTaux()/100;
			com.setComission(cotisation);
			com.setIdComission(cotisation.getId());
			com.setTaux(taux);
			com.setMontant(montantBase);
			com.setCalculated(true);
			
		}
	}

	private void onlyEmployePrime(int idEntete) {
		this.listCotPrm.clear();

		for (BulletinPrimeC bPm : getListePrime()) {

			CotisationDetailC cotisationDetailC = FichierBaseDAO.getInstance().getCotisationDetail(bPm.getCodePrime(),
					idEntete);
			if (cotisationDetailC != null)
				this.listCotPrm.add(cotisationDetailC);
		}
		CotisationDetailC det = FichierBaseDAO.getInstance().getCotisationDetailSB("SB", idEntete);
		if (det != null)
			this.listCotPrm.add(det);
		det = FichierBaseDAO.getInstance().getCotisationDetailHS("HS", idEntete);
		if (det != null)
			this.listCotPrm.add(det);
		det = FichierBaseDAO.getInstance().getCotisationDetailLOG("LG", idEntete);
		if (det != null)
			this.listCotPrm.add(det);
		det = FichierBaseDAO.getInstance().getCotisationDetailAL("AL", idEntete);
		if (det != null)
			this.listCotPrm.add(det);
		det = FichierBaseDAO.getInstance().getCotisationDetailBRUT("BR", idEntete);
		if (det != null)
			this.listCotPrm.add(det);
	}

	private double getValeurPrime(String code, boolean removable) {
		double montant = 0.0D;
		PrimeIndemniteC prm = FichierBaseDAO.getInstance().getPrimeIndemnite(code);
		if (prm != null) {

			for (BulletinPrimeC bPm : getListePrime()) {
				if (bPm.getIdPrime() == prm.getId()) {

					montant = bPm.getMontantPrime();

					break;
				}
			}
		} else if (removable) {

			if (code.equals("SB"))
				montant = getSalaireBase();
			if (code.equals("LG"))
				montant = getTotalLogement();
			if (code.equals("HS"))
				montant = getTotalHS();
			if (code.equals("AL")) {
				montant = getTotalAllocation();
			}
			if (code.equals("BR")) {
				montant = getTotalBrute();
			}
		}

		return montant;
	}

	private double getValeurCotisation(String code) {
		double montant = 0.0D;
		ParametreCotisationC pCot = null;
		CotisationC cot = FichierBaseDAO.getInstance().getCotisation(code);
		if (cot != null) {

			for (BulletinCotisationC bCot : getListeCotisation()) {
				if (bCot.getIdCotisation() == cot.getId()) {

					montant = bCot.getMontantCotisation();

					break;
				}
			}
		}
		return montant;
	}

	public void removeCotisation() {
		if (this.detailCotisation != null) {

			if (this.detailCotisation.getId() > 0) {
				getListDeletedCotisation().add(this.detailCotisation);
			}
			getListeCotisation().remove(this.detailCotisation);
			for (BulletinCotisationC cot : getListeCotisation()) {
				calculValeurCotisation(cot);
			}
			calcuNet();
			clearCotisation();
		}
	}

	public void onCotisationLeave() {
		this.baseCalcule = 0.0D;
		this.montantCotisation = 0.0D;
		this.partPatronal = 0.0D;

		if (this.cotisation == null || this.montantCotisation == 0.0D)
			;
	}

	private void chargementPrime() {
		this.listePrimes = new ArrayList<SelectItem>();
		this.listePrimes.add(new SelectItem(Integer.valueOf(0), ""));

		for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemnite()) {
			this.listePrimes.add(new SelectItem(Integer.valueOf(prm.getId()), prm.getDesignation()));
		}
	}

	public void changePrime(ValueChangeEvent event) {
		this.idPrime = ((Integer) event.getNewValue()).intValue();
		this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrime);

		for (BulletinPrimeC det : getListePrime()) {

			if (det.getIdPrime() == this.idPrime) {

				this.isPrmExist = true;
				return;
			}
		}
	}

	public void addPrime() {
		this.montantPrimeHs = 0.0D;
		if (this.prime != null) {

			if (this.detailPrime == null) {
				this.detailPrime = new BulletinPrimeC();
			}
			this.detailPrime.setIdPrime(this.idPrime);
			this.detailPrime.setLibellePrime(this.prime.getDesignation());
			this.detailPrime.setMontantPrime(this.montantPrime);
			this.detailPrime.setPrimeBulletin(this.prime);
			this.detailPrime.setCodePrime(this.prime.getCode());

			if (!this.isPrmSelected) {

				if (!this.isPrmExist) {

					getListePrime().add(this.detailPrime);
				} else {

					HelperC.afficherAttention("ATTENTION", "Cette prime est déjÃ  ajoutée!");
				}
			} else {

				getListePrime().remove(this.indexPrm);
				getListePrime().add(this.indexPrm, this.detailPrime);
			}

			for (BulletinCotisationC cot : getListeCotisation()) {
				calculValeurCotisation(cot);
			}
			chargerHeuresSup();
			calcuNet();
			clearPrime();
		}
	}

	private void createNewPrime(boolean calculate) {
		if (this.prime != null) {

			if (this.detailPrime == null) {
				this.detailPrime = new BulletinPrimeC();
			}
			this.detailPrime.setIdParametre(this.idParmPrim);
			this.detailPrime.setIdPrime(this.idPrime);
			this.detailPrime.setLibellePrime(this.prime.getDesignation());
			this.detailPrime.setMontantPrime(this.montantPrime);
			this.detailPrime.setPrimeBulletin(this.prime);
			this.detailPrime.setCalculated(calculate);
			this.detailPrime.setCodePrime(this.prime.getCode());
			this.detailPrime.setPriority(FichierBaseDAO.getInstance().getPrioritePrime(this.employe.getIdPersnl(),
					this.employe.getIdCatgrie(), this.employe.getIdGrd(), this.employe.getIdFnctn(), this.idPrime));
			getListePrime().add(this.detailPrime);
			clearPrime();
		}
	}

	public void onPrimeSelected(SelectEvent event) {
		this.detailPrime = (BulletinPrimeC) event.getObject();
		if (this.detailPrime != null) {

			this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.detailPrime.getIdPrime());
			this.detailPrime.setLibellePrime(this.prime.getDesignation());
			this.montantPrime = this.detailPrime.getMontantPrime();
			this.idPrime = this.detailPrime.getIdPrime();
			this.isPrmSelected = true;
			this.idParmPrim = this.detailPrime.getIdParametre();
			this.indexPrm = getListePrime().indexOf(this.detailPrime);
		}
	}

	public void removePrime() {
		if (this.detailPrime != null) {

			if (this.detailPrime.getId() > 0) {
				getListDeletedPrime().add(this.detailPrime);
			}
			getListePrime().remove(this.detailPrime);
			removeEmplPrime(this.detailPrime.getPrimeBulletin());

			for (BulletinCotisationC cot : getListeCotisation()) {
				calculValeurCotisation(cot);
			}
			chargerHeuresSup();
			calcuNet();
			clearPrime();

		}
	}

	private void removeEmplPrime(PrimeIndemniteC prm) {
		for (DetailPrimeEmployeC detEmplPrm : this.employe.getListeDetailPrime()) {

			if (prm.getCode().equals(detEmplPrm.getPrime().getCode())) {

				this.employe.getListeDetailPrime().remove(detEmplPrm);
				return;
			}
		}
	}

	private void clearPrime() {
		this.idPrime = 0;
		this.montantPrime = 0.0D;
		this.isPrmSelected = false;
		this.indexPrm = 0;
		this.detailPrime = null;
		this.prime = null;
		this.isPrmExist = false;
		this.isCalculPrm = false;
		this.idParmPrim = 0;
	}

	private void chargementPrimeEmploye() {
		if (this.employe != null && this.employe.getListeDetailPrime() != null
				&& this.employe.getListeDetailPrime().size() > 0) {

			getListePrime().clear();

			for (DetailPrimeEmployeC pm : this.employe.getListeDetailPrime()) {

				this.prime = pm.getPrime();
				this.idPrime = this.prime.getId();
				this.idParmPrim = pm.getIdParametre();
				if (pm.getMontant() > 0.0D) {

					this.montantPrime = pm.getMontant();
					pm.setCalculated(true);
				}
				if (pm.getIdParametre() == 0 && pm.getTaux() > 0.0D) {
					this.montantPrime = pm.getTaux() / 100.0D * getSalaireBase();
					pm.setCalculated(true);
				}
				if (this.prime != null) {
					createNewPrime(pm.isCalculated());
				}
			}

		}
		Collections.sort(getListePrime(), new PrimeOrderC());
		valeurPrimeSansDetail(getListePrime());

		valeurPrimeAvecDetail(getListePrime());

	}

	private void valeurPrimeSansDetail(List<BulletinPrimeC> listPrm) {
		for (BulletinPrimeC prmB : listPrm) {

			if (!prmB.isCalculated()) {

				this.parametrePrime = FichierBaseDAO.getInstance().getParametragePrime(prmB.getIdParametre(), true);

				if (this.parametrePrime != null && this.parametrePrime.getListDetail().size() == 0) {

					if (this.parametrePrime.getForfait() > 0.0D) {

						this.montantPrime = this.parametrePrime.getForfait();
						prmB.setCalculated(true);
					}

					if (this.parametrePrime.getTaux() > 0.0D) {

						switch (this.parametrePrime.getTypeBase()) {
						case 0:
							this.baseCalcule = getSalaireBase();
							break;
						case 1:
							this.baseCalcule = getTotalmontantBase();
							break;
						}

						this.montantPrime = this.baseCalcule * this.parametrePrime.getTaux() / 100.0D;

						if (this.parametrePrime.getPlafond() > 0.0D
								&& this.montantPrime > this.parametrePrime.getPlafond())
							this.montantPrime = this.parametrePrime.getPlafond();
						if (this.parametrePrime.getPlancher() > 0.0D
								&& this.montantPrime < this.parametrePrime.getPlancher()) {
							this.montantPrime = this.parametrePrime.getPlancher();
						}
						prmB.setCalculated(true);
					}

					prmB.setMontantPrime(this.montantPrime);

				} else if (!FichierBaseDAO.getInstance().isParametragePrimeDetailExist(prmB.getCodePrime())) {

					prmB.setMontantPrime(0.0D);
					prmB.setCalculated(true);
				}

				this.parametrePrime = null;
			}
			this.montantPrime = 0.0D;
		}
	}

	private boolean allPrimeCalculated(List<BulletinPrimeC> listPrm) {
		boolean bl = false;
		for (BulletinPrimeC prm : listPrm) {

			if (!prm.isCalculated()) {

				bl = true;
				break;
			}
		}
		return bl;
	}

	private void valeurPrimeAvecDetail(List<BulletinPrimeC> listPrm) {
		this.montantPrime = 0.0D;
		for (BulletinPrimeC prm : listPrm) {

			if (!prm.isCalculated()) {

				this.parametrePrime = FichierBaseDAO.getInstance().getParametragePrime(prm.getIdParametre(), true);
				if (this.parametrePrime != null && this.parametrePrime.getListDetail().size() > 0) {

					this.montantPrime = searchInCalculateValues(this.parametrePrime, prm);
					prm.setMontantPrime(this.montantPrime);
					prm.setCalculated(true);
				}
				this.parametrePrime = null;
			}
			this.montantPrime = 0.0D;
		}
	}

	private double searchInCalculateValues(ParametragePrimeC paPrm, BulletinPrimeC bPm) {
		PrimeIndemniteC prim = null;
		double montant = 0.0D, totPrm = 0.0D;
		for (ParametragePrimeDetailC det : paPrm.getListDetail()) {

			prim = FichierBaseDAO.getInstance().getPrimeIndemnite(det.getCodeElement());

			if (prim != null) {
				for (BulletinPrimeC bPrm : getListePrime()) {
					if (bPrm.isCalculated() && prim.getId() == bPrm.getIdPrime()) {

						montant = det.getTaux() * bPrm.getMontantPrime() / 100.0D;

						if (det.getPlafon() > 0.0D && montant > det.getPlafon())
							montant = det.getPlafon();
						if (det.getPlancher() < 0.0D && montant < det.getPlancher())
							montant = det.getPlancher();
						totPrm += montant;
						bPm.setCalculated(true);

						montant = 0.0D;
					}
				}
			}
		}

		return totPrm;
	}

	public void changeIndemnite(ValueChangeEvent event) {
		this.idIndmnite = ((Integer) event.getNewValue()).intValue();
		this.indemnite = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idIndmnite);
	}

	public void addIndemnite() {
		if (this.indemnite != null) {

			if (this.detailIndemnite == null) {
				this.detailIndemnite = new BulletinIndemniteC();
			}
			this.detailIndemnite.setIdIndemnite(this.idIndmnite);
			this.detailIndemnite.setLibelleIndemnite(this.indemnite.getDesignation());
			this.detailIndemnite.setMontantIndemnite(this.montantIndemnite);
			if (!this.isIndSelected) {

				getListeIndemnite().add(this.detailIndemnite);
			} else {

				getListeIndemnite().remove(this.indexInd);
				getListeIndemnite().add(this.indexInd, this.detailIndemnite);
			}
			calcuNet();
			clearIndemnite();
		}
	}

	public void onIndemniteSelected(SelectEvent event) {
		this.detailIndemnite = (BulletinIndemniteC) event.getObject();
		if (this.detailIndemnite != null) {

			this.indemnite = FichierBaseDAO.getInstance().getPrimeIndemnite(this.detailPrime.getIdPrime());
			this.detailIndemnite.setLibelleIndemnite(this.indemnite.getDesignation());
			this.montantIndemnite = this.detailIndemnite.getMontantIndemnite();
			this.idIndmnite = this.detailPrime.getIdPrime();
			this.isIndSelected = true;
			this.indexInd = getListeIndemnite().indexOf(this.detailIndemnite);
		}
	}

	private void clearIndemnite() {
		this.idIndmnite = 0;
		this.montantIndemnite = 0.0D;
		this.isIndSelected = false;
		this.indexInd = 0;
		this.indemnite = null;
		this.detailIndemnite = null;
	}

	private void chargementIndmEmploye() {
		if (this.employe != null && this.employe.getListeDetailIndemnite() != null
				&& this.employe.getListeDetailIndemnite().size() > 0) {

			getListeIndemnite().clear();
			if (this.position == null)
				this.position = FactoryDAO.getInstance().getSaisiePositionEmploye(this.employe, getDatePaie());
			if (this.position != null) {

			} else {

				if (this.finCarriere == null)
					this.finCarriere = FactoryDAO.getInstance().getFinCarriereEmploye(this.employe.getId());
				if (this.finCarriere != null) {
					
				}

				for (DetailIndemniteEmployeC ind : this.employe.getListeDetailIndemnite()) {

					this.indemnite = ind.getIndemnite();
					this.idIndmnite = this.indemnite.getId();
					if (ind.getMontant() > 0.0D)
						this.montantIndemnite = ind.getMontant();
					if (ind.getTaux() > 0.0D)
						this.montantIndemnite = ind.getTaux() * getSalaireBase() / 100.0D;
					if (this.montantIndemnite == 0.0D) {
						onIndemniteLeave();
					}
					if (this.indemnite != null) {
						addIndemnite();
					}
				}
			}
		}
	}

	public void onIndemniteLeave() {
	}

	private void chargementDeduction() {
		this.listeDeductions = new ArrayList<SelectItem>();
		this.listeDeductions.add(new SelectItem(Integer.valueOf(0), ""));

		for (DeductionC ded : FichierBaseDAO.getInstance().getAllDeduction()) {
			this.listeDeductions.add(new SelectItem(Integer.valueOf(ded.getId()), ded.getDesignation()));
		}
	}

	public void changeDeduction(ValueChangeEvent event) {
		this.idDeduction = ((Integer) event.getNewValue()).intValue();
		this.deduction = FichierBaseDAO.getInstance().getDeduction(this.idDeduction);
		for (BulletinDeductionC det : getListDeduction()) {

			if (det.getIdRetenu() == this.idDeduction) {

				this.isDedExist = true;
				return;
			}
		}
	}

	public void addDeduction() {
		if (this.deduction != null) {

			if (this.detailDeduction == null) {
				this.detailDeduction = new BulletinDeductionC();
			}
			this.detailDeduction.setIdRetenu(this.idDeduction);
			this.detailDeduction.setLibelleDeduction(this.deduction.getDesignation());
			this.detailDeduction.setDeduction(deduction);
			this.detailDeduction.setMontantRetenu(this.montantDeduction);
			if (!this.isDedSelected) {

				if (!this.isDedExist) {

					getListDeduction().add(this.detailDeduction);
				} else {

					HelperC.afficherAttention("ATTENTION", "Cette retenu est déjÃ  ajoutée!");
				}
			} else {

				getListDeduction().remove(this.indexDed);
				getListDeduction().add(this.indexDed, this.detailDeduction);
			}
			calcuNet();
			clearDeduction();
		}
	}

	public void onDeductionSelected(SelectEvent event) {
		this.detailDeduction = (BulletinDeductionC) event.getObject();
		if (this.detailDeduction != null) {

			this.deduction = FichierBaseDAO.getInstance().getDeduction(this.detailDeduction.getIdRetenu());
			this.detailDeduction.setLibelleDeduction(this.deduction.getDesignation());
			this.montantDeduction = this.detailDeduction.getMontantRetenu();
			this.idDeduction = this.detailDeduction.getIdRetenu();
			this.isDedSelected = true;
			this.indexDed = getListDeduction().indexOf(this.detailDeduction);
		}
	}

	private void clearDeduction() {
		this.idDeduction = 0;
		this.montantDeduction = 0.0D;
		this.isDedSelected = false;
		this.isDedExist = false;
		this.indexDed = 0;
		this.deduction = null;
		this.detailDeduction = null;
	}

	private void chargementDeductEmploye() {
		if (this.employe != null && this.employe.getListeDetailDeduction() != null
				&& this.employe.getListeDetailDeduction().size() > 0) {

			getListDeduction().clear();
			for (DetailDeductionC ded : this.employe.getListeDetailDeduction()) {

				this.deduction = ded.getDeduction();
				this.idDeduction = this.deduction.getId();
				this.montantDeduction = ded.getMontant();
				if (this.deduction != null) {
					addDeduction();
				}
			}

		}
		deductionSanction();// LORSQU'IL Y A EU SANCTION QUI IMPLIQUE UNE DEDUCTION SUR SALAIRE
	}

	private void deductionSanction() {
		List<SaisieSanctionC> listSanction=FactoryDAO.getInstance().getListeSanctionRetenu(employe, getMois(), exercice.getId());
		for (SaisieSanctionC saisie : listSanction) {
			
			montantDeduction=saisie.getMontantRetenu();
		if (montantDeduction > 0) {
			deduction =FichierBaseDAO.getInstance().getDeduction( saisie.getPrm().getIdRetenu());
			if (deduction != null) {
				idDeduction = deduction.getId();
				addDeduction();
			}
		}
		}
	}

	public void onDeductionLeave() {
	}

	public void removeDeduction() {
		if (this.detailDeduction != null) {

			if (this.detailDeduction.getId() > 0) {
				getListDeletedDeduction().add(this.detailDeduction);
			}
			getListDeduction().remove(this.detailDeduction);
			clearDeduction();
		}
	}

	public void chargementEmploye() {
		this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.nomRechEmp, false);
	}

	public void onEmployeSelected(SelectEvent event) {
		this.employe = (EmployeC) event.getObject();
		if (this.employe != null) {

			this.idEmploye = this.employe.getId();
			setIdEmploye(this.employe.getId());
			this.nomEmploye = this.employe.getNomPrenom();
			this.codeEmploye = this.employe.getCode();
			this.typeEmploye = this.employe.getTypeEmploye();

			setBasePaiement(this.employe.getBasePaiement());
			PrimeFaces.current().executeScript("PF('dlgEmploye').hide();");
		}
	}

	public void chargementElements() {
		int nbreHeur = 0;
		this.disableSave = false;
		this.infoMsg = "";

		if(employe.getDateSortie()!=null)
		{
			HelperC.afficherAttention("ATTENTION",
					"Paie impossible!");
			this.infoMsg = "Cet employé ne travaille plus depuis "+employe.getDateSortieS();
			this.disableSave = true;
			return;
		}
		if (getDatePaie().before(this.exercice.getDateDebut()) || getDatePaie().after(this.exercice.getDateFin())) {

			HelperC.afficherAttention("ATTENTION",
					"La date de la paie ne doit pas étre en déhors de la période de l'exercice !");
			this.infoMsg = "La date de paie ne doit pas étre en déhors de la période de l'exercice !";
			this.disableSave = true;

		} else if (this.employe.getDateEntre() != null) {

			if (this.employe.getDateEntre().before(getDatePaie())) {

				this.disableSave = false;
				this.montantBaseHs = 0.0D;
				if (getMois() > 0) {

					this.bulletinEntete = FactoryDAO.getInstance().getBulletinPaie(this.employe.getId(), getDatePaie(),
							this.exercice.getId());
					if (this.bulletinEntete == null) {
						setCommentaire(
								"SALAIRE DU MOIS  DE " + HelperC.getMoisEnTouteLettre(this.getMois()).toUpperCase());
						this.traitement = FactoryDAO.getInstance().getSalaireActuel(employe, getDatePaie());
						position = FactoryDAO.getInstance().getSaisiePositionEmploye(this.employe, getDatePaie());
						
						if (employe.getDateRetraite() == null) {
							if (this.traitement != null)
								setSalaireBase(this.traitement.getSalaireBase());
							else
								setSalaireBase(this.employe.getSalaireBase());

							setBasePaiement(this.employe.getBasePaiement());
							setTotalComplementAllocation(this.employe.getComplement());
							setModeReglement(this.employe.getModeReglement());
						
							if (this.employe.getPourcentageLogement() > 0.0D)
								setTotalLogement(
										this.employe.getSalaireBase() * this.employe.getPourcentageLogement() / 100.0D);
							else
								setTotalLogement(this.employe.getMontantLogement());
							
							if(position!=null && !position.isAjoutIndemniteLogement())
								setTotalLogement(0);

						}

						createBulletin();
					} else {

						setBulletinValues();
					}
				}
			} else {

				HelperC.afficherAttention("ATTENTION",
						"La date début du contrat de cet employé est supérieur à la date du paiement !");

				this.infoMsg = "La date début du contrat de cet employé est supérieur à la date du paiement !";

				this.disableSave = true;
			}
		} else {

			HelperC.afficherAttention("ATTENTION", "Il faut préciser la date d'entrée en fonction pour cet employé !");
			this.infoMsg = "Il faut préciser la date d'entrée en fonction pour cet employé  !";
			this.disableSave = true;
		}
	}

	public void modifyBulletin() {
		setModify(true);
		setBulletinValues();
	}

	public void nonModify() {
		setBulletinValues();
	}

	private void createBulletin() {
		int nbreHeur = FactoryDAO.getInstance().getNombreHeureAbsence(this.idEmploye, getMois(), this.exercice.getId());

		setNombreHeurePreste((this.parametre.getNbreHeureJour()*parametre.getNbreJourMois()) - nbreHeur);
		chargementAvance();
		if (employe.getDateRetraite() == null)
			chargerPersonneCharge();
		if(position!=null && !position.isAjoutAllocationFamiliale())
			getListAllocation().clear();
		if (employe.getDateRetraite() == null)
			chargementPrimeEmploye();
		else {
			prime = FichierBaseDAO.getInstance().getPrimeRetraite();
			if (prime != null) {
				idPrime = prime.getId();
				getListePrime().clear();
				if (traitement != null)
					montantPrime = traitement.getSalaireBase();
				addPrime();
			}
		}
		chargerHeuresSup();

		if (employe.getDateRetraite() != null) {
			List<CotisationC> listCt = FichierBaseDAO.getInstance().getAllCotisationRetraite();
			if (listCt != null && listCt.size() > 0) {
				for (CotisationC c : listCt) {
					detCotEmp = new DetailCotisationEmployeC();
					detCotEmp.setCotisation(c);
					employe.getListeDetailCotisation().add(detCotEmp);
				}

			}
		}
		chargementEmplCotisation();
		chargementDeductEmploye();
		chargementCredit();
		chargementEmplComission();
		calcuNet();
	}

	public void searchEmploye() {
		if (!this.codeEmploye.equals("")) {

			this.employe = FactoryDAO.getInstance().getEmploye(this.codeEmploye, true);
			if (this.employe != null) {

				this.idEmploye = this.employe.getId();
				setIdEmploye(this.employe.getId());
				this.nomEmploye = this.employe.getNomPrenom();

				this.typeEmploye = this.employe.getTypeEmploye();
				bkVrt=FactoryDAO.getInstance().getBanquePrincipal(idEmploye);
				if(bkVrt!=null)
				{
					setCompteVirement(bkVrt.getNumeroCompte());
					if(bkVrt.getBanque()!=null)
						setIdBkVrt(bkVrt.getBanque().getId());
				}
				setNombreHeurePreste(getNombreHeurePreste());
				setTotalComplementAllocation(this.employe.getComplement());
				setModeReglement(this.employe.getModeReglement());
				setBasePaiement(this.employe.getBasePaiement());
			}
		} else {

			clearEmploye();
		}
	}

	private void clearEmploye() {
		this.employe = null;
		this.nomEmploye = "";
		this.codeEmploye = "";
		setSalaireBase(0.0D);
		setCompteVirement("");
		setIdBkVrt(0);
	}

	private void chargementCredit() {
		List<CreditDetailC> listCrt = null;
		CreditC crdi = null;

		if (this.employe != null) {

			getListCredit().clear();
			getListRemboursement().clear();
			if (FactoryDAO.getInstance().getListCreditRestant(this.employe.getId()))
			listCrt = FactoryDAO.getInstance().getListCreditEmploye(this.employe.getId(), getDatePaie());
			
			if (listCrt != null && listCrt.size() > 0) {

				for (CreditDetailC crd : listCrt) {
					
					crdi = FactoryDAO.getInstance().getCreditEmploye(crd.getIdEntete());
					detailCredit = new BulletinCreditC();
					montantCredit = crd.getTranche();
					detailCredit.setMontantTranche(montantCredit);
					detailCredit.setNoDossier(crdi.getNumeroDossier());
					detailCredit.setLibelle("Crédit : " + crdi.getBanque().getCode());
					getListCredit().add(detailCredit);

					detailCredRembourse = new CreditRembourseC();
					detailCredRembourse.setExercice(exercice);
					detailCredRembourse.setMontantRembourse(montantCredit);
					detailCredRembourse.setIdCredit(crdi.getId());
					detailCredRembourse.setDateRemboursement(getDatePaie());
					getListRemboursement().add(detailCredRembourse);
				}
			}
		}
	}

	public void onCreditSelected(SelectEvent event) {
		this.detailCredit = (BulletinCreditC) event.getObject();
		if (this.detailCredit != null) {

			this.montantCredit = this.detailCredit.getMontantTranche() + this.detailCredit.getTranchAdded();
			this.indexCrd = getListCredit().indexOf(this.detailCredit);
		}
	}

	public void addCredit() {
		if (this.detailCredit != null) {

			this.detailCredit.setMontant(this.montantCredit);
			this.detailCredit.setMontantTranche(this.montantCredit);
			getListCredit().remove(this.indexCrd);
			getListCredit().add(this.detailCredit);
			calcuNet();
			clearCredit();
		}
	}

	private void clearCredit() {
		this.detailCredit = null;
		this.montantCredit = 0.0D;
		this.indexCrd = 0;
	}

	public void onAvanceSelected(SelectEvent event) {
		this.detailAvance = (BulletinAvanceC) event.getObject();
		if (this.detailAvance != null) {

			this.montantAvanceQz = this.detailAvance.getMontantAvance();
			this.indexAvc = getListAvance().indexOf(this.detailAvance);
		}
	}

	public void addAvance() {
		if (this.detailAvance != null) {

			this.detailAvance.setMontantAvance(this.montantAvanceQz);
			getListAvance().remove(this.indexAvc);
			getListAvance().add(this.indexAvc, this.detailAvance);
			calcuNet();
			clearAvance();
		}
	}

	private void clearAvance() {
		this.montantAvanceQz = 0.0D;
		this.detailAvance = null;
		this.indexAvc = 0;
	}

	private void chargementAvance() {
		double totalRembourse = 0.0D;
		if (this.employe != null && getMois() > 0) {

			searchBulletin();
			getListAvance().clear();
			List<AvanceQuinzaineC> listAvance = FactoryDAO.getInstance().getListAvanceMoisEncours(this.employe.getId(), getMois());
			for (AvanceQuinzaineC avance : listAvance) {

				
				if (avance.getMontant() >0) {

					if (this.detailAvance == null) {
						this.detailAvance = new BulletinAvanceC();
					}
					this.detailAvance.setDateAvance(avance.getDate());
					this.detailAvance.setMontantAvance(avance.getMontant());
					this.detailAvance.setIdAvance(avance.getId());
					getListAvance().add(this.detailAvance);
					this.detailAvance = null;
				}
			}
		}
	}

	private void chargerHeuresSup() {
		getListHeureSup().clear();
		double montantHeur = 0.0D;
		this.montantBaseHs = 0.0D;
		List<HeuresPrestees> listeHeureSup;
		if(this.employe!=null)
		{
		listeHeureSup = FactoryDAO.getInstance().getListHeurePrestees(this.employe.getId(),
				this.exercice.getId(), getMois());

		this.montantBaseHs += getSalaireBase();

		if (this.parametre != null) {

			if (this.parametre.isAllocationBaseHsup()) {
				this.montantBaseHs += getTotalAllocation();
			}
			if (this.parametre.isLogementBaseHsup()) {
				this.montantBaseHs += getTotalLogement();
			}
			setNombreHeurJr(this.parametre.getNbreHeureJour());
			setNbreJourNormal(this.parametre.getNbreJourMois());
			setTauxJrsFerie(this.parametre.getTauxJrFerie());
		}

		for (BulletinPrimeC bPm : getListePrime()) {

			this.parametrePrime = FichierBaseDAO.getInstance().getParametragePrime(bPm.getIdParametre(), false);
			if (this.parametrePrime != null && this.parametrePrime.isCalculHeurSup()) {
				this.montantBaseHs += bPm.getMontantPrime();
			}
		}
		setMontantBaseHSup(this.montantBaseHs);

		if (listeHeureSup != null && listeHeureSup.size() > 0) {

			for (HeuresPrestees hs : listeHeureSup) {

				hs.setTemps(HelperC.heuresPreste(hs.getHeure(), hs.getMinute(), hs.getSec()));
				if (this.detailHeurSup == null) {
					this.detailHeurSup = new BulletinHeureSupplementaireC();
				}
				this.detailHeurSup.setPourcentage(hs.getPourcent());
				montantHeur = CalculHeureSupC.montantHeurePreste(getMontantBaseHSup(), getNombreHeureNormal(),
						hs.getPourcent(), hs.getHeure(), hs.getMinute(), hs.getSec());
				this.detailHeurSup.setHeures(hs.getHeure());
				this.detailHeurSup.setMinutes(hs.getMinute());
				this.detailHeurSup.setSecondes(hs.getSec());
				this.detailHeurSup.setMontant(montantHeur);
				this.detailHeurSup.setDateTravail(hs.getDatePrestation());
				getListHeureSup().add(this.detailHeurSup);
				montantHeur = 0.0D;
				this.detailHeurSup = null;
			}
		}
		}
	}

	public void chargerPersonneCharge() {
		if (this.employe != null) {

			getListAllocation().clear();
			List<PersonneChargeC> listePersonneCharge = FactoryDAO.getInstance()
					.getAllPersonnesChargeByEmploye(this.employe.getId());
			
			if (listePersonneCharge != null && listePersonneCharge.size() > 0) {
				for (PersonneChargeC pch : listePersonneCharge) {

					if (this.detailAllocation == null) {
						this.detailAllocation = new BulletinAllocationC();
					}
					this.detailAllocation.setIdPerson(pch.getId());
					this.detailAllocation.setMontant(pch.getMontant());
					this.detailAllocation.setNomPersonne(pch.getNomPersonneCharge());
					this.detailAllocation.setSatut(Constante.getLibelleStatutPersonneACharge(pch.getRelation()));
					this.detailAllocation.setNombre(pch.getNombre());
					getListAllocation().add(this.detailAllocation);
					this.detailAllocation = null;
				}
			}
		}
	}

	public void changeDatePaie() {
		if (getDatePaieString().replace("/", "").replace("_", "").trim().equals("")) {

			setDatePaie(null);
		} else {

			setDatePaie(HelperC.validerDate(getDatePaieString()));
			if (getDatePaie() == null) {

				setDatePaieString("");
			} else {

				setDatePaieString(HelperC.convertDate(getDatePaie()));
				setMois(HelperC.getMonthFromDate(getDatePaie()));
				this.moisLettre = HelperC.moisEnLettres(getMois());
				chargementElements();
			}
		}
	}

	public void visualiser() {
		if (this.droit.isAfficher()) {

			EditionBulletinB edit = new EditionBulletinB();
			edit.setBulletin(this.bulletinEntete);
			edit.setEmploye(this.employe);
			edit.printBulletin();
		} else {

			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit d'éditer le bulletin ");
		}
	}

	public void save() {
		setIdExercice(this.exercice.getId());
		if (getId() == 0 && !this.droit.isCreer()) {

			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer le bulletin ");
			return;
		}
		if (getId() > 0 && !this.droit.isModifier()) {

			HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier le bulletin ");
			return;
		}
		if (getDatePaie() == null) {

			HelperC.afficherMessage("ATTENTION", "Il faut préciser la date de paie ! ");
			return;
		}
		if (this.employe == null) {

			HelperC.afficherMessage("ATTENTION", "Il faut préciser l'employé ! ");
			return;
		}
		if (getTotalNetPay() >= this.montantNetMin) {
			
			hist=new Historique();
			hist.setDateOperation(new Date());
			hist.setOperateur(operateur);
			hist.setTable(Tables.getTableName(Tables.TableName.bulletinPaie));
			
			if(getId()==0)
				hist.setOperation("Enregistrement de la paie de "+employe.getNomPrenom()+" pour le mois de "+getMoisLettre()+" : Net ="+HelperC.decimalNumber(getTotalNetPay(), 0, true));
			else
				hist.setOperation("Modification de la paie de "+employe.getNomPrenom()+" pour le mois de "+getMoisLettre()+" : Net ="+HelperC.decimalNumber(getTotalNetPay(), 0, true));
			
			this.setHistory(hist);
			
			if (FactoryDAO.getInstance().insertUpdateBulletinPaie(this)) {

				HelperC.afficherMessage("Information", "Succè de l'opération ");
				initialize();
			} else {

				HelperC.afficherMessage("Désolé", "Echec de l'opération ");
			}
		} else {

			HelperC.afficherAttention("ATTENTION", "La paie ne peut pas étre inférieure à "
					+ HelperC.decimalNumber(this.parametre.getMontantNetMin(), 0, true));
		}
	}

	public void deleteBulletin() {
		if (getId() > 0) {

			hist=new Historique();
			hist.setDateOperation(new Date());
			hist.setOperateur(operateur);
			hist.setTable(Tables.getTableName(Tables.TableName.bulletinPaie));
			hist.setOperation("Suppression de la paie de "+employe.getNomPrenom()+" pour le mois de "+getMoisLettre()+" : Net ="+HelperC.decimalNumber(getTotalNetPay(), 0, true));
			this.setHistory(hist);
			
			FactoryDAO.getInstance().deleteBulletinPaie(this);
			initialize();
		}
		else
			HelperC.afficherDeleteMessage();
	}

	public void initialize() {
		setId(0);
		this.montantAvanceQz = 0.0D;
		this.montantDeduction = 0.0D;
		this.montantIndemnite = 0.0D;
		this.montantPrime = 0.0D;
		setTotalAllocation(0.0D);
		setTotalAvance(0.0D);
		setTotalCotisation(0.0D);
		setTotalCredit(0.0D);
		setTotalDeduction(0.0D);
		setTotalHS(0.0D);
		setTotalIndemnite(0.0D);
		setTotalLogement(0.0D);
		setTotalPrime(0.0D);
		setTotaltAvance(0.0D);
		setTotalmontantBase(0.0D);
		setTotalComplementAllocation(0.0D);
		setCodeEmploye("");
		setCommentaire("");
		setSalaireBase(0.0D);
		setNombreHeureNormal(0);
		setNombreHeurePreste(0);
		setModeReglement(0);
		setMois(0);
		setTauxJourAdd(0.0D);
		setTotalJourAdd(0.0D);
		setNbreJourAdd(0);
		setDatePaieString("");
		this.employe = null;
		this.nomEmploye = "";
		this.codeEmploye = "";
		this.disableSave = false;
		this.codeRechEmp = "";
		this.nomRechEmp = "";
		this.typeEmploye = 0;
		getListAllocation().clear();
		getListAvance().clear();
		getListCredit().clear();
		getListDeduction().clear();
		getListeCotisation().clear();
		getListeIndemnite().clear();
		getListePrime().clear();
		getListHeureSup().clear();
		getListComission().clear();
		disableMsg=true;
	}
}
