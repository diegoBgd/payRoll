package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class BulletinAvanceC implements Serializable {
	private static final long serialVersionUID = 7608011581455899457L;
	private int id;
	private int idBulletin;
	private int idAvance;
	private Date dateAvance;
	private double montantAvance;
	private String datePrint;
	private String printMotant;

	public String getDatePrint() {
		if (this.dateAvance != null) {
			this.datePrint = HelperC.convertDate(this.dateAvance);
		}
		return this.datePrint;
	}

	public void setDatePrint(String datePrint) {
		this.datePrint = datePrint;
	}

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

	public Date getDateAvance() {
		return this.dateAvance;
	}

	public void setDateAvance(Date dateAvance) {
		this.dateAvance = dateAvance;
	}

	public double getMontantAvance() {
		return this.montantAvance;
	}

	public void setMontantAvance(double montantAvance) {
		this.montantAvance = montantAvance;
	}

	public int getIdAvance() {
		return this.idAvance;
	}

	public void setIdAvance(int idAvance) {
		this.idAvance = idAvance;
	}

	public String getPrintMotant() {
		this.printMotant = HelperC.decimalNumber(getMontantAvance(), 0, true);
		return this.printMotant;
	}

	public void setPrintMotant(String printMotant) {
		this.printMotant = printMotant;
	}
}
