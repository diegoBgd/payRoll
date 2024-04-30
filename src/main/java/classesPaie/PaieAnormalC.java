package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class PaieAnormalC implements Serializable {
	private static final long serialVersionUID = 4595058223947218476L;
	private int id;
	private int idEmploye;
	private int idExercice;
	private int indexNum;
	private Date datePaie;
	private String nomPrenom, printMontant, printBase;
	private String code;
	private double montantPaie;

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

	public Date getDatePaie() {
		return this.datePaie;
	}

	public void setDatePaie(Date datePaie) {
		this.datePaie = datePaie;
	}

	public String getNomPrenom() {
		return this.nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public double getMontantPaie() {
		return this.montantPaie;
	}

	public void setMontantPaie(double montantPaie) {
		this.montantPaie = montantPaie;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIndexNum() {
		return this.indexNum;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}

	public String getPrintMontant() {
		printMontant = HelperC.decimalNumber(Math.round(getMontantPaie()), 0, true);
		return printMontant;
	}

	public void setPrintMontant(String printMontant) {

		this.printMontant = printMontant;
	}

	public String getPrintBase() {
		return printBase;
	}

	public void setPrintBase(String printBase) {
		this.printBase = printBase;
	}

}
