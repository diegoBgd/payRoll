package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class BulletinHeureSupplementaireC implements Serializable {
	private static final long serialVersionUID = -6014821056875434144L;
	private int id;
	private int idBulletin;
	private int heures;
	private int minutes;
	private int secondes;
	private double pourcentage;
	private double montant;
	private Date dateTravail;
	private String HeurePreste;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBulletin() {
		return this.idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}

	public int getHeures() {
		return this.heures;
	}

	public void setHeures(int heures) {
		this.heures = heures;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSecondes() {
		return this.secondes;
	}

	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}

	public double getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Date getDateTravail() {
		return this.dateTravail;
	}

	public void setDateTravail(Date dateTravail) {
		this.dateTravail = dateTravail;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getHeurePreste() {
		this.HeurePreste = String.valueOf(this.heures) + " H " + this.minutes + " Min " + this.secondes + " Sec ";
		return this.HeurePreste;
	}

	public void setHeurePreste(String heurePreste) {
		this.HeurePreste = heurePreste;
	}
}
