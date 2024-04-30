package classesPaie;

import java.io.Serializable;

public class CoteGratificationC implements Serializable {
	private static final long serialVersionUID = -7458489972039532189L;
	private int id;
	private double cote;
	private EmployeC employe;
	private String coteS;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCote() {
		return this.cote;
	}

	public void setCote(double cote) {
		this.cote = cote;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public String getCoteS() {
		return this.coteS;
	}

	public void setCoteS(String coteS) {
		this.coteS = coteS;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
