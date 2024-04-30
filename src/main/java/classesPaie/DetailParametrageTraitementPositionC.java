package classesPaie;

import java.io.Serializable;

public class DetailParametrageTraitementPositionC implements Serializable {
	private static final long serialVersionUID = 7341820912609894467L;
	private int id;
	private int ancienneteMin;
	private int ancienneteMax;
	private int duree;
	private int index;
	private Historique historique;
	private boolean existe;
	private ParametrageDureeEtTraitementPositionC parametrage;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAncienneteMin() {
		return this.ancienneteMin;
	}

	public void setAncienneteMin(int ancienneteMin) {
		this.ancienneteMin = ancienneteMin;
	}

	public int getAncienneteMax() {
		return this.ancienneteMax;
	}

	public void setAncienneteMax(int ancienneteMax) {
		this.ancienneteMax = ancienneteMax;
	}

	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public ParametrageDureeEtTraitementPositionC getParametrage() {
		return this.parametrage;
	}

	public void setParametrage(ParametrageDureeEtTraitementPositionC parametrage) {
		this.parametrage = parametrage;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
