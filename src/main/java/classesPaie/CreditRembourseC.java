package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class CreditRembourseC implements Serializable {
	private static final long serialVersionUID = -370758307614513909L;
	private int id;
	private int index;
	private int idCredit;
	private int idBulletin;
	private double montantCapital;
	private double montantRembourse;
	private double montantInteret;
	private double montantTaxe;
	private Date dateRemboursement;
	private OperateurC operateur;
	private ExerciceC exercice;
	private String dateRemboursementPrint, printMontant, libelle;
	private Historique historique;

	public int getIdBulletin() {
		return this.idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}

	public String getDateRemboursementPrint() {
		dateRemboursementPrint = HelperC.convertDate(getDateRemboursement());
		return this.dateRemboursementPrint;
	}

	public void setDateRemboursementPrint(String dateRemboursementPrint) {
		this.dateRemboursementPrint = dateRemboursementPrint;
	}

	public double getMontantRembourse() {
		return this.montantRembourse;
	}

	public void setMontantRembourse(double montantRembourse) {
		this.montantRembourse = montantRembourse;
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

	public int getIdCredit() {
		return this.idCredit;
	}

	public void setIdCredit(int idCredit) {
		this.idCredit = idCredit;
	}

	public Date getDateRemboursement() {
		return this.dateRemboursement;
	}

	public void setDateRemboursement(Date dateRemboursement) {
		this.dateRemboursement = dateRemboursement;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMontantCapital() {
		return this.montantCapital;
	}

	public void setMontantCapital(double montantCapital) {
		this.montantCapital = montantCapital;
	}

	public double getMontantInteret() {
		return this.montantInteret;
	}

	public void setMontantInteret(double montantInteret) {
		this.montantInteret = montantInteret;
	}

	public double getMontantTaxe() {
		return this.montantTaxe;
	}

	public void setMontantTaxe(double montantTaxe) {
		this.montantTaxe = montantTaxe;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public String getPrintMontant() {
		printMontant = HelperC.decimalNumber(getMontantRembourse(), 0, true);
		return printMontant;
	}

	public void setPrintMontant(String printMontant) {
		this.printMontant = printMontant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
