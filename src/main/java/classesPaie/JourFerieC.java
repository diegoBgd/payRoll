package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class JourFerieC implements Serializable {
	private static final long serialVersionUID = 4287274764602240392L;
	private int id;
	private ExerciceC exercice;
	private Date dateFerie;
	private String designation;
	private String code;
	private String printDate;
	private Constante.TypeJourFerie typeJour;
	private String libelleJourFerie;
	private int typeJourFerie;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExerciceC getExercice() {
		return this.exercice;
	}

	public void setExercice(ExerciceC exercice) {
		this.exercice = exercice;
	}

	public Date getDateFerie() {
		return this.dateFerie;
	}

	public void setDateFerie(Date dateFerie) {
		this.dateFerie = dateFerie;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Constante.TypeJourFerie getTypeJour() {
		return this.typeJour;
	}

	public void setTypeJour(Constante.TypeJourFerie typeJour) {
		this.typeJour = typeJour;
	}

	public String getLibelleJourFerie() {
		return this.libelleJourFerie;
	}

	public void setLibelleJourFerie(String libelleJourFerie) {
		this.libelleJourFerie = libelleJourFerie;
	}

	public int getTypeJourFerie() {
		return this.typeJourFerie;
	}

	public void setTypeJourFerie(int typeJourFerie) {
		this.typeJourFerie = typeJourFerie;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public String getPrintDate() {
		return this.printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
}
