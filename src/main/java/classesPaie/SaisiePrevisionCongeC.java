package classesPaie;

import java.io.Serializable;

public class SaisiePrevisionCongeC implements Serializable {
	private static final long serialVersionUID = -7675248847449367779L;
	private int id;
	private int joursConges;
	private int joursReportes;
	private EmployeC employe;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJoursConges() {
		return this.joursConges;
	}

	public void setJoursConges(int joursConges) {
		this.joursConges = joursConges;
	}

	public int getJoursReportes() {
		return this.joursReportes;
	}

	public void setJoursReportes(int joursReportes) {
		this.joursReportes = joursReportes;
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
}
