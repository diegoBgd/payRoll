package classesPaie;

import java.io.Serializable;

public class BulletinCotisationC implements Serializable {
	private static final long serialVersionUID = 6042503218530329798L;
	private int id;
	private int priority;
	private int typeCotisation;
	private int idCotisation;
	private int idBulletin;
	private int indexNum;
	private double montantCotisation;
	private double montantPatronal;
	private double montantBase;
	private double tauxSalarial;
	private double tauxPatronal;
	private String codeCotisation;
	private String printSalarial;
	private String printBase;
	private String printPatronal;
	private String libelleCotisation;
	private CotisationC cotisation;
	private boolean calculated;

	public int getIndexNum() {
		return this.indexNum;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}

	public CotisationC getCotisation() {
		return this.cotisation;
	}

	public void setCotisation(CotisationC cotisation) {
		this.cotisation = cotisation;
	}

	public int getIdBulletin() {
		return this.idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCotisation() {
		return this.idCotisation;
	}

	public void setIdCotisation(int idCotisation) {
		this.idCotisation = idCotisation;
	}

	public double getMontantCotisation() {
		return this.montantCotisation;
	}

	public void setMontantCotisation(double montantCotisation) {
		this.montantCotisation = montantCotisation;
	}

	public String getCodeCotisation() {
		return this.codeCotisation;
	}

	public void setCodeCotisation(String codeCotisation) {
		this.codeCotisation = codeCotisation;
	}

	public String getLibelleCotisation() {
		if (this.cotisation != null) {
			this.libelleCotisation = this.cotisation.getDesignation();
		}
		return this.libelleCotisation;
	}

	public void setLibelleCotisation(String libelleCotisation) {
		this.libelleCotisation = libelleCotisation;
	}

	public double getMontantPatronal() {
		return this.montantPatronal;
	}

	public void setMontantPatronal(double montantPatronal) {
		this.montantPatronal = montantPatronal;
	}

	public double getMontantBase() {
		return this.montantBase;
	}

	public void setMontantBase(double montantBase) {
		this.montantBase = montantBase;
	}

	public double getTauxSalarial() {
		return this.tauxSalarial;
	}

	public void setTauxSalarial(double tauxSalarial) {
		this.tauxSalarial = tauxSalarial;
	}

	public double getTauxPatronal() {
		return this.tauxPatronal;
	}

	public void setTauxPatronal(double tauxPatronal) {
		this.tauxPatronal = tauxPatronal;
	}

	public boolean isCalculated() {
		return this.calculated;
	}

	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getPrintSalarial() {
		this.printSalarial = HelperC.decimalNumber(getMontantCotisation(), 0, true);
		return this.printSalarial;
	}

	public void setPrintSalarial(String printSalarial) {
		this.printSalarial = printSalarial;
	}

	public String getPrintPatronal() {
		this.printPatronal = HelperC.decimalNumber(getMontantPatronal(), 0, true);
		return this.printPatronal;
	}

	public void setPrintPatronal(String printPatronal) {
		this.printPatronal = printPatronal;
	}

	public int getTypeCotisation() {
		return this.typeCotisation;
	}

	public void setTypeCotisation(int typeCotisation) {
		this.typeCotisation = typeCotisation;
	}

	public String getPrintBase() {
		
		printBase= HelperC.decimalNumber(getMontantBase(), 0, true);
		return printBase;
	}

	public void setPrintBase(String printBase) {
		this.printBase = printBase;
	}
	
}
