package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class ExerciceC extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	private String exercicePrecedent, printDeb, printFin;
	private Date dateDebut;
	private Date dateFin;
	private Historique historique;

	public String getExercicePrecedent() {
		return this.exercicePrecedent;
	}

	public void setExercicePrecedent(String exercicePrecedent) {
		this.exercicePrecedent = exercicePrecedent;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public String getPrintDeb() {
		printDeb = HelperC.convertDate(getDateDebut());
		return printDeb;
	}

	public void setPrintDeb(String printDeb) {
		this.printDeb = printDeb;
	}

	public String getPrintFin() {
		printFin = HelperC.convertDate(getDateFin());
		return printFin;
	}

	public void setPrintFin(String printFin) {
		this.printFin = printFin;
	}

}
