package classesPaie;

import java.io.Serializable;

public class PrimeIndemniteC implements Serializable {
	private static final long serialVersionUID = 79075396261986231L;
	private int id;
	private String code;
	private String designation;
	private String typePrime;
	private String prefixeComptable;
	private boolean soumisCotisation;
	private boolean imposable;
	private boolean hide;
	private boolean retraite;
	private Historique historique;
	private double baseCalcul;
	private double tauxPrime;
	private double montantMax;
	private double montantMin;

	public String getPrefixeComptable() {
		return this.prefixeComptable;
	}

	public void setPrefixeComptable(String prefixeComptable) {
		this.prefixeComptable = prefixeComptable;
	}

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

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTypePrime() {
		return this.typePrime;
	}

	public void setTypePrime(String typePrime) {
		this.typePrime = typePrime;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public double getBaseCalcul() {
		return this.baseCalcul;
	}

	public void setBaseCalcul(double baseCalcul) {
		this.baseCalcul = baseCalcul;
	}

	public double getTauxPrime() {
		return this.tauxPrime;
	}

	public void setTauxPrime(double tauxPrime) {
		this.tauxPrime = tauxPrime;
	}

	public double getMontantMax() {
		return this.montantMax;
	}

	public void setMontantMax(double montantMax) {
		this.montantMax = montantMax;
	}

	public double getMontantMin() {
		return this.montantMin;
	}

	public void setMontantMin(double montantMin) {
		this.montantMin = montantMin;
	}

	public boolean isSoumisCotisation() {
		return this.soumisCotisation;
	}

	public void setSoumisCotisation(boolean soumisCotisation) {
		this.soumisCotisation = soumisCotisation;
	}

	public boolean isImposable() {
		return this.imposable;
	}

	public void setImposable(boolean imposable) {
		this.imposable = imposable;
	}

	public boolean isHide() {
		return this.hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public boolean isRetraite() {
		return retraite;
	}

	public void setRetraite(boolean retraite) {
		this.retraite = retraite;
	}

}
