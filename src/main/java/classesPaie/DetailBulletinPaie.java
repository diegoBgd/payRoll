package classesPaie;

import java.io.Serializable;

public class DetailBulletinPaie implements Serializable {
	private static final long serialVersionUID = -1353693407426445312L;
	private int id;
	private BulletinPaieC bulletinPaie;
	private CreditC creditAvance;
	private double capital;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BulletinPaieC getBulletinPaie() {
		return this.bulletinPaie;
	}

	public void setBulletinPaie(BulletinPaieC bulletinPaie) {
		this.bulletinPaie = bulletinPaie;
	}

	public CreditC getCreditAvance() {
		return this.creditAvance;
	}

	public void setCreditAvance(CreditC creditAvance) {
		this.creditAvance = creditAvance;
	}

	public double getCapital() {
		return this.capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
