package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParametrageHeureSupplementaireC implements Serializable {
	private static final long serialVersionUID = -140091060844344844L;
	private int id;
	private String numero;
	private boolean actif;
	private Historique historique;
	private List<DetailParametrageHeureSupplementaireC> listDetailHeureSupplementaire = new ArrayList<DetailParametrageHeureSupplementaireC>();
	private List<DetailParametrageHeureSupplementaireC> listDetailHeureSupplementaireDeleted = new ArrayList<DetailParametrageHeureSupplementaireC>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActif() {
		return actif;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public List<DetailParametrageHeureSupplementaireC> getListDetailHeureSupplementaire() {
		return this.listDetailHeureSupplementaire;
	}

	public void setListDetailHeureSupplementaire(
			List<DetailParametrageHeureSupplementaireC> listDetailHeureSupplementaire) {
		this.listDetailHeureSupplementaire = listDetailHeureSupplementaire;
	}

	public List<DetailParametrageHeureSupplementaireC> getListDetailHeureSupplementaireDeleted() {
		return this.listDetailHeureSupplementaireDeleted;
	}

	public void setListDetailHeureSupplementaireDeleted(
			List<DetailParametrageHeureSupplementaireC> listDetailHeureSupplementaireDeleted) {
		this.listDetailHeureSupplementaireDeleted = listDetailHeureSupplementaireDeleted;
	}
}
