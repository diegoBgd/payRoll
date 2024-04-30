package classesPaie;

import java.io.Serializable;

public class ParametragePrimeDetailC implements Serializable {
	private static final long serialVersionUID = 4948335288203549633L;
	private int id;
	private int idEntete;
	private boolean added;
	private boolean disable = true;
	private double taux;
	private double forfait;
	private double plafon;
	private double plancher;
	private String codeElement;
	private String libelle;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getForfait() {
		return this.forfait;
	}

	public void setForfait(double forfait) {
		this.forfait = forfait;
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

	public boolean isAdded() {
		return this.added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}

	public String getCodeElement() {
		return this.codeElement;
	}

	public void setCodeElement(String codeElement) {
		this.codeElement = codeElement;
	}

	public int getIdEntete() {
		return this.idEntete;
	}

	public void setIdEntete(int idEntete) {
		this.idEntete = idEntete;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public boolean isDisable() {
		return this.disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public void checkDetail() {
		if (this.added) {
			this.disable = false;

		} else if (getId() == 0) {
			this.disable = true;
			this.plancher = 0.0D;
			this.plafon = 0.0D;
			this.taux = 0.0D;
			this.forfait = 0.0D;
		}
	}

	public void changeTaux() {
		if (this.taux > 0.0D) {
			this.forfait = 0.0D;
		}
	}

	public void changeForfait() {
		if (this.forfait > 0.0D) {

			this.plancher = 0.0D;
			this.plafon = 0.0D;
			this.taux = 0.0D;
		}
	}
}
