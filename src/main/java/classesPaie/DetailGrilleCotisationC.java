package classesPaie;

import java.io.Serializable;

public class DetailGrilleCotisationC implements Serializable {
	private static final long serialVersionUID = -5058006541711219529L;
	private int id;
	private int idGrille;
	private String categorie;
	private double trancheMin;
	private double trancheMax;
	private double pointAchete;
	private double valeurAchat;
	private double totalCotisation;

	public double getTotalCotisation() {
		this.totalCotisation = this.pointAchete * this.valeurAchat;
		return this.totalCotisation;
	}

	public void setTotalCotisation(double totalCotisation) {
		this.totalCotisation = totalCotisation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdGrille() {
		return this.idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public double getTrancheMin() {
		return this.trancheMin;
	}

	public void setTrancheMin(double trancheMin) {
		this.trancheMin = trancheMin;
	}

	public double getTrancheMax() {
		return this.trancheMax;
	}

	public void setTrancheMax(double trancheMax) {
		this.trancheMax = trancheMax;
	}

	public double getPointAchete() {
		return this.pointAchete;
	}

	public void setPointAchete(double pointAchete) {
		this.pointAchete = pointAchete;
	}

	public double getValeurAchat() {
		return this.valeurAchat;
	}

	public void setValeurAchat(double valeurAchat) {
		this.valeurAchat = valeurAchat;
	}
}
