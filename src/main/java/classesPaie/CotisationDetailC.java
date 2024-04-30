package classesPaie;

import java.io.Serializable;

public class CotisationDetailC implements Serializable {
	private static final long serialVersionUID = 8977522981951262940L;
	private int id;
	private int idEntete;
	private int typeBase;
	private String codeElement;
	private String typeElement;
	private String libelleElement;
	private String symbol;

	public int getId() {
		return this.id;
	}

	private String typePrm;
	private double taux;
	private double plafon;
	private double plancher;
	private double forfait;
	private double tauxMax;
	private boolean seleceted;
	private boolean disable = true;

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEntete() {
		return this.idEntete;
	}

	public void setIdEntete(int idEntete) {
		this.idEntete = idEntete;
	}

	public String getCodeElement() {
		return this.codeElement;
	}

	public void setCodeElement(String codeElement) {
		this.codeElement = codeElement;
	}

	public String getTypeElement() {
		return this.typeElement;
	}

	public void setTypeElement(String typeElement) {
		this.typeElement = typeElement;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getPlafon() {
		return this.plafon;
	}

	public void setPlafon(double plafon) {
		this.plafon = plafon;
	}

	public double getPlancher() {
		return this.plancher;
	}

	public void setPlancher(double plancher) {
		this.plancher = plancher;
	}

	public double getForfait() {
		return this.forfait;
	}

	public void setForfait(double forfait) {
		this.forfait = forfait;
	}

	public String getLibelleElement() {
		return this.libelleElement;
	}

	public void setLibelleElement(String libelleElement) {
		this.libelleElement = libelleElement;
	}

	public boolean isSeleceted() {
		return this.seleceted;
	}

	public void setSeleceted(boolean seleceted) {
		this.seleceted = seleceted;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean isDisable() {
		return this.disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public double getTauxMax() {
		return this.tauxMax;
	}

	public void setTauxMax(double tauxMax) {
		this.tauxMax = tauxMax;
	}

	public int getTypeBase() {
		return this.typeBase;
	}

	public void setTypeBase(int typeBase) {
		this.typeBase = typeBase;
	}

	public String getTypePrm() {
		return this.typePrm;
	}

	public void setTypePrm(String typePrm) {
		this.typePrm = typePrm;
	}

	public void checkDetail() {
		if (this.seleceted) {
			this.disable = false;

		} else if (getId() == 0) {
			this.disable = true;
			this.plancher = 0.0D;
			this.plafon = 0.0D;
			this.taux = 0.0D;
			this.tauxMax = 0.0D;
			this.forfait = 0.0D;
		}
	}

	public void changeTauxMax() {
		if (this.tauxMax > 0.0D) {

			this.forfait = 0.0D;
			this.taux = 0.0D;
		}
	}

	public void changeTaux() {
		if (this.taux > 0.0D) {

			this.forfait = 0.0D;
			this.tauxMax = 0.0D;
		}
	}

	public void changeForfait() {
		if (this.forfait > 0.0D) {

			this.plancher = 0.0D;
			this.plafon = 0.0D;
			this.taux = 0.0D;
			this.tauxMax = 0.0D;
		}
	}
}
