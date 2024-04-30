package classesPaie;

import java.io.Serializable;

public class DynamicTableC implements Serializable {
	private static final long serialVersionUID = -4315108032780326600L;
	private String nomColone;
	private String valeur;
	private String[] colones;

	public String getNomColone() {
		return this.nomColone;
	}

	public void setNomColone(String nomColone) {
		this.nomColone = nomColone;
	}

	public String[] getColones() {
		return this.colones;
	}

	public void setColones(String[] colones) {
		this.colones = colones;
	}

	public String getValeur() {
		return this.valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
}
