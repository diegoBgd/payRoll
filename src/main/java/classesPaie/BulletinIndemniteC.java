package classesPaie;

import java.io.Serializable;

public class BulletinIndemniteC implements Serializable {
	private static final long serialVersionUID = 4330186674282708063L;
	private int idIndemnite;
	private int id;
	private int idBulletin;
	private double montantIndemnite;
	private double baseCalcul;
	private double taux;
	private String codeIndemnite;
	private String libelleIndemnite;
	private PrimeIndemniteC indemnite;

	public PrimeIndemniteC getIndemnite() {
		return this.indemnite;
	}

	public void setIndemnite(PrimeIndemniteC indemnite) {
		this.indemnite = indemnite;
	}

	public int getIdBulletin() {
		return this.idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}

	public int getIdIndemnite() {
		return this.idIndemnite;
	}

	public void setIdIndemnite(int idIndemnite) {
		this.idIndemnite = idIndemnite;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMontantIndemnite() {
		return this.montantIndemnite;
	}

	public void setMontantIndemnite(double montantIndemnite) {
		this.montantIndemnite = montantIndemnite;
	}

	public String getCodeIndemnite() {
		return this.codeIndemnite;
	}

	public void setCodeIndemnite(String codeIndemnite) {
		this.codeIndemnite = codeIndemnite;
	}

	public String getLibelleIndemnite() {
		if (this.indemnite != null) {
			this.libelleIndemnite = this.indemnite.getDesignation();
		}
		return this.libelleIndemnite;
	}

	public void setLibelleIndemnite(String libelleIndemnite) {
		this.libelleIndemnite = libelleIndemnite;
	}

	public double getBaseCalcul() {
		return this.baseCalcul;
	}

	public void setBaseCalcul(double baseCalcul) {
		this.baseCalcul = baseCalcul;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
}
