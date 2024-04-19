package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BulletinPaieC implements Serializable {
	private List<BulletinAllocationC> listAllocation = new ArrayList<BulletinAllocationC>();
	private List<BulletinCotisationC> listeCotisation = new ArrayList<BulletinCotisationC>();
	private List<BulletinDeductionC> listDeduction = new ArrayList<BulletinDeductionC>();
	private List<BulletinDeductionC> listDeletedDeduction = new ArrayList<BulletinDeductionC>();
	private List<BulletinHeureSupplementaireC> listHeureSup = new ArrayList<BulletinHeureSupplementaireC>();
	private List<BulletinIndemniteC> listeIndemnite = new ArrayList<BulletinIndemniteC>();
	private List<BulletinPrimeC> listePrime = new ArrayList<BulletinPrimeC>();
	private List<BulletinPrimeC> listDeletedPrime = new ArrayList<BulletinPrimeC>();
	private List<BulletinCreditC> listCredit = new ArrayList<BulletinCreditC>();
	private List<BulletinAvanceC> listAvance = new ArrayList<BulletinAvanceC>();
	private List<BulletinCotisationC> listDeletedCotisation = new ArrayList<BulletinCotisationC>();
	private List<CreditRembourseC> listRemboursement = new ArrayList<CreditRembourseC>();
	private List<BulletinComissionC> listComission = new ArrayList<BulletinComissionC>();
	private static final long serialVersionUID = -4837743312838499752L;
	private int id;
	private int idEmploye;
	private int mois, idBkVrt;
	private int modeReglement;
	private int nombreHeureNormal;
	private int nombreHeurJr;
	private int nbreJourNormal;
	private int nombreHeurePreste;
	private int idExercice;
	private int nombreJourPreste;
	private int nombreJourAbsent;
	private int nbrJourFerie;
	private int basePaiement;
	private int nbreJourAdd;
	private String compteVirement;
	private String nomEmploye;
	private String lieuTravail;
	private String contrat;
	private String commentaire;
	private String datePaieString;
	private String codeEmploye;
	private String moisPrint;
	private String printTotalSalarial;
	private String printNet;
	private String printTotalDed;
	private String printTotalPatronal;
	private String printTotalPrime;
	private String printTotAlloc;
	private String printTotalAvc;
	private String printTotalCrd;
	private double salaireBase;
	private double totalAllocation;
	private double totalLogement;
	private double totaltAvance;
	private double totalCreditRembourse;
	private double montantHeure;
	private double totalHrsPreste;
	private double totalmontantBase;
	private double totalPrime;
	private double totalCotisation;
	private double totalDeduction;
	private double totalIndemnite;
	private double totalHS;
	private double montantJrn;
	private double totalCredit;
	private double totalAvance;
	private double totalNetPay;
	private double totalComplementAllocation;
	private double totalBrute;
	private double totalPatronal;
	private double montantBaseHSup;
	private double totalPrimeScotisation;
	private double totalBaseImposable;
	private double totalPrimeHrCot;
	private double tauxJourAdd;
	private double tauxJrsFerie;
	private double totalJourAdd;
	private double totalJourFerie;
	private double totalCotObl;
	private double totalComission;
	private String printTotalComission;

	private boolean modify;
	private Date datePaie;
	private Historique history;

	public Historique getHistory() {
		return history;
	}

	public void setHistory(Historique history) {
		this.history = history;
	}

	public List<BulletinComissionC> getListComission() {
		return listComission;
	}

	public void setListComission(List<BulletinComissionC> listComission) {
		this.listComission = listComission;
	}

	public int getIdBkVrt() {
		return idBkVrt;
	}

	public void setIdBkVrt(int idBkVrt) {
		this.idBkVrt = idBkVrt;
	}

	public String getCompteVirement() {
		return compteVirement;
	}

	public void setCompteVirement(String compteVirement) {
		this.compteVirement = compteVirement;
	}

	public boolean isModify() {
		return this.modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}

	public int getNombreJourAbsent() {
		return this.nombreJourAbsent;
	}

	public void setNombreJourAbsent(int nombreJourAbsent) {
		this.nombreJourAbsent = nombreJourAbsent;
	}

	public int getNombreHeurJr() {
		return this.nombreHeurJr;
	}

	public void setNombreHeurJr(int nombreHeurJr) {
		this.nombreHeurJr = nombreHeurJr;
	}

	public int getNbreJourNormal() {
		return this.nbreJourNormal;
	}

	public void setNbreJourNormal(int nbreJourNormal) {
		this.nbreJourNormal = nbreJourNormal;
	}

	public double getTauxJourAdd() {
		return this.tauxJourAdd;
	}

	public void setTauxJourAdd(double tauxJourAdd) {
		this.tauxJourAdd = tauxJourAdd;
	}

	public void setMontantJrn(double montantJrn) {
		this.montantJrn = montantJrn;
	}

	public void setTotalJourAdd(double totalJourAdd) {
		this.totalJourAdd = totalJourAdd;
	}

	public int getNbreJourAdd() {
		return this.nbreJourAdd;
	}

	public void setNbreJourAdd(int nbreJourAdd) {
		this.nbreJourAdd = nbreJourAdd;
	}

	public void setTotalPrimeHrCot(double totalPrimeHrCot) {
		this.totalPrimeHrCot = totalPrimeHrCot;
	}

	public void setTotalBaseImposable(double totalBaseImposable) {
		this.totalBaseImposable = totalBaseImposable;
	}

	public void setTotalPrimeScotisation(double totalPrimeScotisation) {
		this.totalPrimeScotisation = totalPrimeScotisation;
	}

	public int getNbrJourFerie() {
		return this.nbrJourFerie;
	}

	public void setNbrJourFerie(int nbrJourFerie) {
		this.nbrJourFerie = nbrJourFerie;
	}

	public String getPrintTotalPrime() {
		this.printTotalPrime = HelperC.decimalNumber(getTotalPrime(), 0, true);
		return this.printTotalPrime;
	}

	public void setPrintTotalPrime(String printTotalPrime) {
		this.printTotalPrime = printTotalPrime;
	}

	public int getNombreJourPreste() {
		return this.nombreJourPreste;
	}

	public void setNombreJourPreste(int nombreJourPreste) {
		this.nombreJourPreste = nombreJourPreste;
	}

	public int getBasePaiement() {
		return this.basePaiement;
	}

	public void setBasePaiement(int basePaiement) {
		this.basePaiement = basePaiement;
	}

	public void setTotalBrute(double totalBrute) {
		this.totalBrute = totalBrute;
	}

	public String getPrintTotalSalarial() {
		this.printTotalSalarial = HelperC.decimalNumber(getTotalCotisation(), 0, true);
		return this.printTotalSalarial;
	}

	public void setPrintTotalSalarial(String printTotalSalarial) {
		this.printTotalSalarial = printTotalSalarial;
	}

	public String getPrintTotalPatronal() {
		this.printTotalPatronal = HelperC.decimalNumber(getTotalPatronal(), 0, true);
		return this.printTotalPatronal;
	}

	public void setPrintTotalPatronal(String printTotalPatronal) {
		this.printTotalPatronal = printTotalPatronal;
	}

	public String getPrintNet() {
		this.printNet = HelperC.decimalNumber(getTotalNetPay(), 0, true);
		return this.printNet;
	}

	public void setPrintNet(String printNet) {
		this.printNet = printNet;
	}

	public String getPrintTotalDed() {
		this.printTotalDed = HelperC.decimalNumber(getTotalDeduction(), 0, true);
		return this.printTotalDed;
	}

	public String getPrintTotalAvc() {
		HelperC.decimalNumber(getTotalAvance(), 0, true);
		return this.printTotalAvc;
	}

	public void setPrintTotalAvc(String printTotalAvc) {
		this.printTotalAvc = printTotalAvc;
	}

	public String getPrintTotalCrd() {
		this.printTotalCrd = HelperC.decimalNumber(getTotalCredit(), 0, true);
		return this.printTotalCrd;
	}

	public void setPrintTotalCrd(String printTotalCrd) {
		this.printTotalCrd = printTotalCrd;
	}

	public void setPrintTotalDed(String printTotalDed) {
		this.printTotalDed = printTotalDed;
	}

	public String getMoisPrint() {
		return this.moisPrint;
	}

	public void setMoisPrint(String moisPrint) {
		this.moisPrint = moisPrint;
	}

	public String getCodeEmploye() {
		return this.codeEmploye;
	}

	public void setCodeEmploye(String codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public void setTotalHS(double totalHS) {
		this.totalHS = totalHS;
	}

	public void setTotalmontantBase(double totolmontantBase) {
		this.totalmontantBase = totolmontantBase;
	}

	public int getNombreHeureNormal() {
		return this.nombreHeureNormal;
	}

	public void setNombreHeureNormal(int nombreHeureNormal) {
		this.nombreHeureNormal = nombreHeureNormal;
	}

	public int getNombreHeurePreste() {
		return this.nombreHeurePreste;
	}

	public void setNombreHeurePreste(int nombreHeurePreste) {
		this.nombreHeurePreste = nombreHeurePreste;
	}

	public int getIdExercice() {
		return this.idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public int getMois() {
		return this.mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getModeReglement() {
		return this.modeReglement;
	}

	public void setModeReglement(int modeReglement) {
		this.modeReglement = modeReglement;
	}

	public String getNomEmploye() {
		return this.nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getLieuTravail() {
		return this.lieuTravail;
	}

	public void setLieuTravail(String lieuTravail) {
		this.lieuTravail = lieuTravail;
	}

	public String getContrat() {
		return this.contrat;
	}

	public void setContrat(String contrat) {
		this.contrat = contrat;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public double getSalaireBase() {
		return this.salaireBase;
	}

	public void setSalaireBase(double salaireBase) {
		this.salaireBase = salaireBase;
	}

	public void setTotalAllocation(double totalAllocation) {
		this.totalAllocation = totalAllocation;
	}

	public double getTotalLogement() {
		return Math.round(totalLogement);
	}

	public void setTotalLogement(double totalLogement) {
		this.totalLogement = totalLogement;
	}

	public double getTotaltAvance() {
		return this.totaltAvance;
	}

	public void setTotaltAvance(double totaltAvance) {
		this.totaltAvance = totaltAvance;
	}

	public double getTotalComplementAllocation() {
		return this.totalComplementAllocation;
	}

	public void setTotalComplementAllocation(double totalComplementAllocation) {
		this.totalComplementAllocation = totalComplementAllocation;
	}

	public double getTotalCreditRembourse() {
		return this.totalCreditRembourse;
	}

	public void setTotalCreditRembourse(double totalCreditRembourse) {
		this.totalCreditRembourse = totalCreditRembourse;
	}

	public String getDatePaieString() {
		return this.datePaieString;
	}

	public void setDatePaieString(String datePaieString) {
		this.datePaieString = datePaieString;
	}

	public Date getDatePaie() {
		return this.datePaie;
	}

	public void setDatePaie(Date datePaie) {
		this.datePaie = datePaie;
	}

	public List<BulletinPrimeC> getListePrime() {
		return this.listePrime;
	}

	public void setListePrime(List<BulletinPrimeC> listePrime) {
		this.listePrime = listePrime;
	}

	public List<BulletinPrimeC> getListDeletedPrime() {
		return this.listDeletedPrime;
	}

	public void setListDeletedPrime(List<BulletinPrimeC> listDeletedPrime) {
		this.listDeletedPrime = listDeletedPrime;
	}

	public List<BulletinIndemniteC> getListeIndemnite() {
		return this.listeIndemnite;
	}

	public void setListeIndemnite(List<BulletinIndemniteC> listeIndemnite) {
		this.listeIndemnite = listeIndemnite;
	}

	public List<BulletinCotisationC> getListeCotisation() {
		return this.listeCotisation;
	}

	public void setListeCotisation(List<BulletinCotisationC> listeCotisation) {
		this.listeCotisation = listeCotisation;
	}

	public List<BulletinCotisationC> getListDeletedCotisation() {
		return this.listDeletedCotisation;
	}

	public void setListDeletedCotisation(List<BulletinCotisationC> listDeletedCotisation) {
		this.listDeletedCotisation = listDeletedCotisation;
	}

	public List<BulletinDeductionC> getListDeduction() {
		return this.listDeduction;
	}

	public void setListDeduction(List<BulletinDeductionC> listDeduction) {
		this.listDeduction = listDeduction;
	}

	public List<BulletinDeductionC> getListDeletedDeduction() {
		return this.listDeletedDeduction;
	}

	public void setListDeletedDeduction(List<BulletinDeductionC> listDeletedDeduction) {
		this.listDeletedDeduction = listDeletedDeduction;
	}

	public List<BulletinAllocationC> getListAllocation() {
		return this.listAllocation;
	}

	public void setListAllocation(List<BulletinAllocationC> listAllocation) {
		this.listAllocation = listAllocation;
	}

	public List<BulletinHeureSupplementaireC> getListHeureSup() {
		return this.listHeureSup;
	}

	public void setListHeureSup(List<BulletinHeureSupplementaireC> listHeureSup) {
		this.listHeureSup = listHeureSup;
	}

	public List<BulletinCreditC> getListCredit() {
		return this.listCredit;
	}

	public void setListCredit(List<BulletinCreditC> listCredit) {
		this.listCredit = listCredit;
	}

	public List<BulletinAvanceC> getListAvance() {
		return this.listAvance;
	}

	public void setListAvance(List<BulletinAvanceC> listAvance) {
		this.listAvance = listAvance;
	}

	public List<CreditRembourseC> getListRemboursement() {
		return this.listRemboursement;
	}

	public void setListRemboursement(List<CreditRembourseC> listRemboursement) {
		this.listRemboursement = listRemboursement;
	}

	public void setTotalCredit(double totalCredit) {
		this.totalCredit = totalCredit;
	}

	public void setTotalAvance(double totalAvance) {
		this.totalAvance = totalAvance;
	}

	public void setMontantHeure(double montantHeure) {
		this.montantHeure = montantHeure;
	}

	public void setTotalPrime(double totalPrime) {
		this.totalPrime = totalPrime;
	}

	public void setTotalCotisation(double totalCotisation) {
		this.totalCotisation = totalCotisation;
	}

	public void setTotalDeduction(double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public void setTotalIndemnite(double totalIndemnite) {
		this.totalIndemnite = totalIndemnite;
	}

	public double getMontantBaseHSup() {
		return this.montantBaseHSup;
	}

	public void setMontantBaseHSup(double montantBaseHSup) {
		this.montantBaseHSup = montantBaseHSup;
	}

	public void setTotalPatronal(double totalPatronal) {
		this.totalPatronal = totalPatronal;
	}

	public void setTotalNetPay(double totalNetPay) {
		this.totalNetPay = totalNetPay;
	}

	public double getTauxJrsFerie() {
		return this.tauxJrsFerie;
	}

	public void setTauxJrsFerie(double tauxJrsFerie) {
		this.tauxJrsFerie = tauxJrsFerie;
	}

	public void setTotalJourFerie(double totalJourFerie) {
		this.totalJourFerie = totalJourFerie;
	}

	public void setTotalHrsPreste(double totalHrsPreste) {
		this.totalHrsPreste = totalHrsPreste;
	}

	public double getTotalJourFerie() {
		this.totalJourFerie = Math.rint(getMontantJrn() * getNbrJourFerie() * getTauxJrsFerie() / 100.0D);
		return this.totalJourFerie;
	}

	public String getPrintTotAlloc() {
		this.printTotAlloc = HelperC.decimalNumber(getTotalAllocation(), 0, true);
		return this.printTotAlloc;
	}

	public void setPrintTotAlloc(String printTotAlloc) {
		this.printTotAlloc = printTotAlloc;
	}

	public void setTotalCotObl(double totalCotObl) {
		this.totalCotObl = totalCotObl;
	}

	public double getMontantJrn() {
		if (getNbreJourNormal() > 0)
			this.montantJrn = Math.rint(getSalaireBase() / getNbreJourNormal());
		return this.montantJrn;
	}

	public double getTotalHrsPreste() {
		this.totalHrsPreste = getMontantHeure() * getNombreHeurePreste();
		return this.totalHrsPreste;
	}

	public double getTotalJourAdd() {
		this.totalJourAdd = getMontantJrn() * getNbreJourAdd();

		return Math.round(totalJourAdd);
	}

	public double getTotalmontantBase() {
		return this.totalmontantBase;
	}

	public double getMontantHeure() {
		if (this.nombreHeureNormal > 0) {
			this.montantHeure = Math.rint(getSalaireBase() / this.nombreHeureNormal);
		}
		return this.montantHeure;
	}

	public double getTotalHS() {
		this.totalHS = 0.0D;
		if (getListHeureSup().size() > 0) {
			for (BulletinHeureSupplementaireC hs : getListHeureSup()) {

				this.totalHS += Math.round(hs.getMontant());
			}
		}

		return this.totalHS;
	}

	public double getTotalAllocation() {
		this.totalAllocation = 0.0D;
		if (getListAllocation().size() > 0) {
			for (BulletinAllocationC alc : getListAllocation()) {
				this.totalAllocation += Math.round(alc.getMontant() * alc.getNombre());
			}
		}

		return this.totalAllocation;
	}

	public double getTotalCredit() {
		this.totalCredit = 0.0D;
		if (this.listCredit.size() > 0) {
			for (BulletinCreditC bcrd : this.listCredit) {
				this.totalCredit += Math.round(bcrd.getMontant());
			}
		}

		return this.totalCredit;
	}

	public double getTotalAvance() {
		this.totalAvance = 0.0D;
		if (this.listAvance.size() > 0) {
			for (BulletinAvanceC bav : this.listAvance) {

				this.totalAvance += Math.round(bav.getMontantAvance());
			}
		}

		return this.totalAvance;
	}

	public double getTotalPrimeHrCot() {
		this.totalPrimeHrCot = 0.0D;
		if (getListePrime().size() > 0) {

			for (BulletinPrimeC bPrim : getListePrime()) {

				this.totalPrime += Math.round(bPrim.getMontantPrime());
				if (!bPrim.getPrimeBulletin().isSoumisCotisation()) {
					this.totalPrimeHrCot += Math.round(bPrim.getMontantPrime());
				}
			}
		}

		return this.totalPrimeHrCot;
	}

	public double getTotalPrimeScotisation() {
		this.totalPrimeScotisation = 0.0D;
		if (getListePrime().size() > 0) {

			for (BulletinPrimeC bPrim : getListePrime()) {

				if (bPrim.getPrimeBulletin().isSoumisCotisation()) {
					this.totalPrimeScotisation += Math.round(bPrim.getMontantPrime());
				}
			}
		}

		return this.totalPrimeScotisation;
	}

	public double getTotalPrime() {
		this.totalPrime = 0.0D;

		int indexPrm = 0;
		if (getListePrime().size() > 0) {

			for (BulletinPrimeC bPrim : getListePrime()) {

				indexPrm++;
				this.totalPrime += Math.round(bPrim.getMontantPrime());
			}
		}

		return this.totalPrime;
	}

	public double getTotalBaseImposable() {
		this.totalBaseImposable = 0.0D;
		if (getListeCotisation().size() > 0) {

			for (BulletinCotisationC cot : getListeCotisation()) {

				if (cot.getCotisation().getTypeBaremme() == 1) {
					this.totalBaseImposable += cot.getMontantBase();
				}
			}
		}

		return this.totalBaseImposable;
	}

	public double getTotalCotisation() {
		this.totalCotisation = 0.0D;
		int indexCot = 0;
		if (getListeCotisation().size() > 0) {

			for (BulletinCotisationC cot : getListeCotisation()) {

				indexCot++;
				this.totalCotisation += Math.round(cot.getMontantCotisation());
			}
		}

		return this.totalCotisation;
	}

	public double getTotalCotObl() {
		this.totalCotObl = 0;

		if (getListeCotisation().size() > 0) {

			for (BulletinCotisationC cot : getListeCotisation()) {
				if (cot.getCotisation().isObligatoire()) {

					this.totalCotObl += Math.round(cot.getMontantCotisation());
				}
			}
		}
		return totalCotObl;
	}

	public double getTotalComission() {
		totalComission = 0;
		for (BulletinComissionC com : listComission) {
			totalComission += Math.round(com.getMontant());
		}
		return totalComission;
	}

	public void setTotalComission(double totalComission) {
		this.totalComission = totalComission;
	}

	public String getPrintTotalComission() {
		return printTotalComission;
	}

	public void setPrintTotalComission(String printTotalComission) {
		this.printTotalComission = printTotalComission;
	}

	public double getTotalCotisationObligatoire() {
		this.totalCotisation = 0.0D;
		int indexCot = 0;
		if (getListeCotisation().size() > 0) {

			for (BulletinCotisationC cot : getListeCotisation()) {
				if (cot.getCotisation().isObligatoire()) {
					indexCot++;
					this.totalCotisation += Math.round(cot.getMontantCotisation());
				}
			}
		}

		return this.totalCotisation;
	}

	public double getTotalDeduction() {
		this.totalDeduction = 0.0D;
		int indexDed = 0;
		if (getListDeduction().size() > 0) {

			for (BulletinDeductionC ded : getListDeduction()) {

				indexDed++;
				if (ded.getDeduction().isSoustract())
					this.totalDeduction += Math.round(ded.getMontantRetenu());
			}
		}

		return this.totalDeduction;
	}

	public double getTotalIndemnite() {
		this.totalIndemnite = 0.0D;
		if (getListeIndemnite().size() > 0) {
			for (BulletinIndemniteC ind : getListeIndemnite()) {

				this.totalIndemnite += ind.getMontantIndemnite();
			}
		}

		return this.totalIndemnite;
	}

	public double getTotalBrute() {
		this.totalBrute = getSalaireBase() + getTotalAllocation() + getTotalHS() + getTotalPrime() + getTotalIndemnite()
				+ getTotalLogement() + getTotalComplementAllocation() + getTotalJourAdd();

		return this.totalBrute;
	}

	public double getTotalPatronal() {
		this.totalPatronal = 0.0D;
		if (getListeCotisation().size() > 0) {
			for (BulletinCotisationC cot : getListeCotisation()) {
				this.totalPatronal += Math.round(cot.getMontantPatronal());
			}
		}
		return this.totalPatronal;
	}

	public double getTotalNetPay() {
		this.totalNetPay = getTotalBrute()
				- (getTotalAvance() + getTotalCotisation() + getTotalCredit() + getTotalDeduction());

		return this.totalNetPay;
	}

	public void calcuNet() {
		getTotalNetPay();
	}
}
