package classesPaie;

import java.io.Serializable;

public class JoursCongeEmployeC implements Serializable {
	private static final long serialVersionUID = 7359882432160989093L;
	private int id;
	private int numero;
	private double joursDu;
	private double joursPris;
	private double joursReportes;
	private String joursDuS;
	private String joursPrisS;
	private String joursReportesS;
	private ExerciceC exercice;
	private EmployeC employe;
	private Historique historique;
	private boolean existe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setJoursReportes(int joursReportes) {
		this.joursReportes = joursReportes;
	}

	public ExerciceC getExercice() {
		return this.exercice;
	}

	public void setExercice(ExerciceC exercice) {
		this.exercice = exercice;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public double getJoursDu() {
		return this.joursDu;
	}

	public void setJoursDu(double joursDu) {
		this.joursDu = joursDu;
	}

	public double getJoursPris() {
		return this.joursPris;
	}

	public void setJoursPris(double joursPris) {
		this.joursPris = joursPris;
	}

	public double getJoursReportes() {
		return this.joursReportes;
	}

	public void setJoursReportes(double joursReportes) {
		this.joursReportes = joursReportes;
	}

	public String getJoursDuS() {
		return this.joursDuS;
	}

	public void setJoursDuS(String joursDuS) {
		this.joursDuS = joursDuS;
	}

	public String getJoursPrisS() {
		return this.joursPrisS;
	}

	public void setJoursPrisS(String joursPrisS) {
		this.joursPrisS = joursPrisS;
	}

	public String getJoursReportesS() {
		return this.joursReportesS;
	}

	public void setJoursReportesS(String joursReportesS) {
		this.joursReportesS = joursReportesS;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
