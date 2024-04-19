package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class DeductionC implements Serializable {
	private static final long serialVersionUID = 7674288598372492360L;
	private int id;
	private String code;
	private String designation;
	private String montantS;
	private String tauxS;
	private String prefixeComptable;
	private double taux;
	private double montant;
	private boolean centralisable;
	private boolean hide, typeSanction,soustract;
	private Historique historique;
	private Date dateFinUtilisation;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrefixeComptable() {
		return this.prefixeComptable;
	}

	public void setPrefixeComptable(String prefixeComptable) {
		this.prefixeComptable = prefixeComptable;
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

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public boolean isCentralisable() {
		return this.centralisable;
	}

	public void setCentralisable(boolean centralisable) {
		this.centralisable = centralisable;
	}

	public Date getDateFinUtilisation() {
		return this.dateFinUtilisation;
	}

	public void setDateFinUtilisation(Date dateFinUtilisation) {
		this.dateFinUtilisation = dateFinUtilisation;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public String getMontantS() {
		return this.montantS;
	}

	public void setMontantS(String montantS) {
		this.montantS = montantS;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getTauxS() {
		return this.tauxS;
	}

	public void setTauxS(String tauxS) {
		this.tauxS = tauxS;
	}

	public boolean isHide() {
		return this.hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public boolean isTypeSanction() {
		return typeSanction;
	}

	public void setTypeSanction(boolean typeSanction) {
		this.typeSanction = typeSanction;
	}

	public boolean isSoustract() {
		return soustract;
	}

	public void setSoustract(boolean soustract) {
		this.soustract = soustract;
	}

}
