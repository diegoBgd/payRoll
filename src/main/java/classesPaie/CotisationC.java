package classesPaie;

import java.io.Serializable;

public class CotisationC implements Serializable {
	private static final long serialVersionUID = 3310070463687717018L;
	private int id;
	private int idOrganisme;
	private String code;
	private String designation;
	private String prefixeSalarial;
	private String prefixePatronal;
	private String chargeSalarial;
	private String chargePtronal;
	private int typeBaremme, typeElement;
	private boolean hide, obligatoire, retraite;
	private OrganismeSupportantBaseSalarialC organisme;
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

	public int getTypeBaremme() {
		return this.typeBaremme;
	}

	public void setTypeBaremme(int typeBaremme) {
		this.typeBaremme = typeBaremme;
	}

	public int getIdOrganisme() {
		return this.idOrganisme;
	}

	public void setIdOrganisme(int idOrganisme) {
		this.idOrganisme = idOrganisme;
	}

	public String getPrefixeSalarial() {
		return this.prefixeSalarial;
	}

	public void setPrefixeSalarial(String prefixeSalarial) {
		this.prefixeSalarial = prefixeSalarial;
	}

	public String getPrefixePatronal() {
		return this.prefixePatronal;
	}

	public void setPrefixePatronal(String prefixePatronal) {
		this.prefixePatronal = prefixePatronal;
	}

	public String getChargeSalarial() {
		return this.chargeSalarial;
	}

	public void setChargeSalarial(String chargeSalarial) {
		this.chargeSalarial = chargeSalarial;
	}

	public String getChargePtronal() {
		return this.chargePtronal;
	}

	public void setChargePtronal(String chargePtronal) {
		this.chargePtronal = chargePtronal;
	}

	public boolean isHide() {
		return this.hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public boolean isObligatoire() {
		return obligatoire;
	}

	public void setObligatoire(boolean obligatoire) {
		this.obligatoire = obligatoire;
	}

	public boolean isRetraite() {
		return retraite;
	}

	public void setRetraite(boolean retraite) {
		this.retraite = retraite;
	}

	public int getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(int typeElement) {
		this.typeElement = typeElement;
	}

	public OrganismeSupportantBaseSalarialC getOrganisme() {
		return organisme;
	}

	public void setOrganisme(OrganismeSupportantBaseSalarialC organisme) {
		this.organisme = organisme;
	}

}
