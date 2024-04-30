package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeductionRangesC implements Serializable {
	private static final long serialVersionUID = 2146872480492911851L;
	private int id;
	private int no = 1;
	private int index;
	private double borneDebut;
	private List<DeductionRangesC> listeDetailDeductionRanges = new ArrayList<DeductionRangesC>();
	private double borneFin;
	private double montant;
	private List<DeductionRangesC> listeDetailDeductionRangesdeleted = new ArrayList<DeductionRangesC>();
	private String borneDebutS;
	private String borneFinS;
	private String montantS;
	private boolean existe;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNo() {
		return this.no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getBorneDebut() {
		return this.borneDebut;
	}

	public void setBorneDebut(double borneDebut) {
		this.borneDebut = borneDebut;
	}

	public double getBorneFin() {
		return this.borneFin;
	}

	public void setBorneFin(double borneFin) {
		this.borneFin = borneFin;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public List<DeductionRangesC> getListeDetailDeductionRanges() {
		return this.listeDetailDeductionRanges;
	}

	public void setListeDetailDeductionRanges(List<DeductionRangesC> listeDetailDeductionRanges) {
		this.listeDetailDeductionRanges = listeDetailDeductionRanges;
	}

	public List<DeductionRangesC> getListeDetailDeductionRangesdeleted() {
		return this.listeDetailDeductionRangesdeleted;
	}

	public void setListeDetailDeductionRangesdeleted(List<DeductionRangesC> listeDetailDeductionRangesdeleted) {
		this.listeDetailDeductionRangesdeleted = listeDetailDeductionRangesdeleted;
	}

	public String getBorneDebutS() {
		return this.borneDebutS;
	}

	public void setBorneDebutS(String borneDebutS) {
		this.borneDebutS = borneDebutS;
	}

	public String getBorneFinS() {
		return this.borneFinS;
	}

	public void setBorneFinS(String borneFinS) {
		this.borneFinS = borneFinS;
	}

	public String getMontantS() {
		return this.montantS;
	}

	public void setMontantS(String montantS) {
		this.montantS = montantS;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
