package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditDetailC implements Serializable {
	private static final long serialVersionUID = -7939063315653994763L;
	private int id;
	private int idEmploye;
	private int idEntete;
	private int mensualite;
	private String printDateDeb, printTotTrch, printDateFin, printTranche;
	private Date dateDeb, dateFin;
	private double tranche, totalTranch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public int getIdEntete() {
		return idEntete;
	}

	public void setIdEntete(int idEntete) {
		this.idEntete = idEntete;
	}

	public String getPrintDateDeb() {
		printDateDeb = HelperC.convertDate(getDateDeb());
		return printDateDeb;
	}

	public void setPrintDateDeb(String printDateDeb) {
		this.printDateDeb = printDateDeb;
	}

	public String getPrintDateFin() {
		printDateFin = HelperC.convertDate(getDateFin());
		return printDateFin;
	}

	public void setPrintDateFin(String printDateFin) {
		this.printDateFin = printDateFin;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public double getTranche() {
		return tranche;
	}

	public void setTranche(double tranche) {
		this.tranche = tranche;
	}

	public String getPrintTranche() {
		printTranche = HelperC.decimalNumber(getTranche(), 0, true);
		return printTranche;
	}

	public void setPrintTranche(String printTranche) {
		this.printTranche = printTranche;
	}

	public int getMensualite() {
		if (getDateDeb() != null && getDateFin() != null)
			mensualite = (int) HelperC.daysBetween(getDateDeb(), getDateFin()) / 30;
		return mensualite;
	}

	public void setMensualite(int mensualite) {
		this.mensualite = mensualite;
	}

	public double getTotalTranch() {
		totalTranch = getTranche() * getMensualite();
		return totalTranch;
	}

	public void setTotalTranch(double totalTranch) {
		this.totalTranch = totalTranch;
	}

	public String getPrintTotTrch() {
		printTotTrch = HelperC.decimalNumber(getTotalTranch(), 0, true);
		return printTotTrch;
	}

	public void setPrintTotTrch(String printTotTrch) {
		this.printTotTrch = printTotTrch;
	}

}
