package classesPaie;

import java.io.Serializable;

public class DetailPrimeC implements Serializable {
	private static final long serialVersionUID = 5338145590315134394L;
	private int id;
	private int anciennetteMin;
	private int anciennetteMax;
	private int index;
	private double plancher;
	private double plafond;
	private double taux;
	private double nombreMoisSalaire;
	private String plancherS;
	private String plafondS;
	private String tauxS;
	private String nombreMoisSalaireS;
	private PrimeIndemniteC prime;
	private boolean existe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnciennetteMin() {
		return this.anciennetteMin;
	}

	public void setAnciennetteMin(int anciennetteMin) {
		this.anciennetteMin = anciennetteMin;
	}

	public int getAnciennetteMax() {
		return this.anciennetteMax;
	}

	public void setAnciennetteMax(int anciennetteMax) {
		this.anciennetteMax = anciennetteMax;
	}

	public double getPlancher() {
		return this.plancher;
	}

	public void setPlancher(double plancher) {
		this.plancher = plancher;
	}

	public double getPlafond() {
		return this.plafond;
	}

	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public String getPlancherS() {
		return this.plancherS;
	}

	public void setPlancherS(String plancherS) {
		this.plancherS = plancherS;
	}

	public String getPlafondS() {
		return this.plafondS;
	}

	public void setPlafondS(String plafondS) {
		this.plafondS = plafondS;
	}

	public String getTauxS() {
		return this.tauxS;
	}

	public void setTauxS(String tauxS) {
		this.tauxS = tauxS;
	}

	public PrimeIndemniteC getPrime() {
		return this.prime;
	}

	public void setPrime(PrimeIndemniteC prime) {
		this.prime = prime;
	}

	public double getNombreMoisSalaire() {
		return this.nombreMoisSalaire;
	}

	public void setNombreMoisSalaire(double nombreMoisSalaire) {
		this.nombreMoisSalaire = nombreMoisSalaire;
	}

	public String getNombreMoisSalaireS() {
		return this.nombreMoisSalaireS;
	}

	public void setNombreMoisSalaireS(String nombreMoisSalaireS) {
		this.nombreMoisSalaireS = nombreMoisSalaireS;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
