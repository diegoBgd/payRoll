package classesPaie;

import java.io.Serializable;

public class TypeCreditC implements Serializable {
	private static final long serialVersionUID = 265763872270618326L;
	private int id;
	private String code;
	private String libelle;
	private String terme;
	private String tauxMinimumString;
	private String tauxMaximumString;
	private double tauxMinimum;
	private double tauxMaximum;
	private boolean centraliserEcritureEnComptabilite;
	private boolean affecterDeuxiemeCompteCredit;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getTerme() {
		return this.terme;
	}

	public void setTerme(String terme) {
		this.terme = terme;
	}

	public double getTauxMinimum() {
		return this.tauxMinimum;
	}

	public void setTauxMinimum(double tauxMinimum) {
		this.tauxMinimum = tauxMinimum;
	}

	public double getTauxMaximum() {
		return this.tauxMaximum;
	}

	public void setTauxMaximum(double tauxMaximum) {
		this.tauxMaximum = tauxMaximum;
	}

	public String getTauxMinimumString() {
		return this.tauxMinimumString;
	}

	public void setTauxMinimumString(String tauxMinimumString) {
		this.tauxMinimumString = tauxMinimumString;
	}

	public String getTauxMaximumString() {
		return this.tauxMaximumString;
	}

	public void setTauxMaximumString(String tauxMaximumString) {
		this.tauxMaximumString = tauxMaximumString;
	}

	public boolean isCentraliserEcritureEnComptabilite() {
		return this.centraliserEcritureEnComptabilite;
	}

	public void setCentraliserEcritureEnComptabilite(boolean centraliserEcritureEnComptabilite) {
		this.centraliserEcritureEnComptabilite = centraliserEcritureEnComptabilite;
	}

	public boolean isAffecterDeuxiemeCompteCredit() {
		return this.affecterDeuxiemeCompteCredit;
	}

	public void setAffecterDeuxiemeCompteCredit(boolean affecterDeuxiemeCompteCredit) {
		this.affecterDeuxiemeCompteCredit = affecterDeuxiemeCompteCredit;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
