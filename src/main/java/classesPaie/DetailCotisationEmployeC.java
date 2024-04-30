package classesPaie;

import java.io.Serializable;

public class DetailCotisationEmployeC implements Serializable {
	private static final long serialVersionUID = -8082631340804262748L;
	private int id;
	private EmployeC employe;
	private CotisationC cotisation;
	private Historique historique;
	private double montantSalarial;
	private double montantPatronal;
	private String montantS;
	private boolean existe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public CotisationC getCotisation() {
		return this.cotisation;
	}

	public void setCotisation(CotisationC cotisation) {
		this.cotisation = cotisation;
	}

	public double getMontantSalarial() {
		return this.montantSalarial;
	}

	public void setMontantSalarial(double montantSalarial) {
		this.montantSalarial = montantSalarial;
	}

	public double getMontantPatronal() {
		return this.montantPatronal;
	}

	public void setMontantPatronal(double montantPatronal) {
		this.montantPatronal = montantPatronal;
	}

	public String getMontantS() {
		return this.montantS;
	}

	public void setMontantS(String montantS) {
		this.montantS = montantS;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}
}
