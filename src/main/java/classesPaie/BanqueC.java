package classesPaie;

import java.io.Serializable;

public class BanqueC implements Serializable {
	private static final long serialVersionUID = 4344885561487133962L;
	private int id;
	private String code;
	private String designation;
	private String telephone1;
	private String telephone2;
	private String adresse;
	private String compte;
	private String banqueVirement;
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
		if (code != null) {
			this.code = code.toUpperCase();
		}
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTelephone1() {
		return this.telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return this.telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCompte() {
		return this.compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getBanqueVirement() {
		return this.banqueVirement;
	}

	public void setBanqueVirement(String banqueVirement) {
		this.banqueVirement = banqueVirement;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
