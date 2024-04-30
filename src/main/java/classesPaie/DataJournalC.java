package classesPaie;

import java.io.Serializable;

public class DataJournalC implements Serializable {
	private static final long serialVersionUID = 3866906857652128606L;
	private EmployeC employe;
	private String valeur;
	private String colonne;

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public String getValeur() {
		return this.valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getColonne() {
		return this.colonne;
	}

	public void setColonne(String colonne) {
		this.colonne = colonne;
	}
}
