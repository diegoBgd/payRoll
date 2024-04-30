package classesPaie;

import java.io.Serializable;

public class CoordonneesSocieteC implements Serializable {
	private static final long serialVersionUID = 3273607654552380175L;
	private int id;
	private String code;
	private String representant;
	private String nomSociete;
	private String adresse;
	private String telephoneMobile;
	private String telephoneFixe;
	private String fax;
	private String email;
	private String descriptif;
	private String noEmployeurInss;
	private String logo;
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

	public String getRepresentant() {
		return this.representant;
	}

	public void setRepresentant(String representant) {
		this.representant = representant;
	}

	public String getNomSociete() {
		return this.nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephoneMobile() {
		return this.telephoneMobile;
	}

	public void setTelephoneMobile(String telephoneMobile) {
		this.telephoneMobile = telephoneMobile;
	}

	public String getTelephoneFixe() {
		return this.telephoneFixe;
	}

	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescriptif() {
		return this.descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getNoEmployeurInss() {
		return this.noEmployeurInss;
	}

	public void setNoEmployeurInss(String noEmployeurInss) {
		this.noEmployeurInss = noEmployeurInss;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
