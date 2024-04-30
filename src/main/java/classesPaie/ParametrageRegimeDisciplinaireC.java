package classesPaie;

import java.io.Serializable;

public class ParametrageRegimeDisciplinaireC implements Serializable {
	private static final long serialVersionUID = 2011267525550691555L;
	private int id;
	private int sanctionsDisciplinaires;
	private int dureeMin;
	private int dureeMax;
	private int dureeRecours;
	private int dureeCloture;
	private int actionDepassementDureeCloture;
	private double tauxTraitementRetenue;
	private String tauxTraitementRetenueS;
	private String libelleSanction;
	private Historique historique;
	private Constante.SanctionsDisciplinaires sanction;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSanctionsDisciplinaires() {
		return this.sanctionsDisciplinaires;
	}

	public void setSanctionsDisciplinaires(int sanctionsDisciplinaires) {
		this.sanctionsDisciplinaires = sanctionsDisciplinaires;
	}

	public int getDureeMin() {
		return this.dureeMin;
	}

	public void setDureeMin(int dureeMin) {
		this.dureeMin = dureeMin;
	}

	public int getDureeMax() {
		return this.dureeMax;
	}

	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}

	public int getDureeRecours() {
		return this.dureeRecours;
	}

	public void setDureeRecours(int dureeRecours) {
		this.dureeRecours = dureeRecours;
	}

	public int getDureeCloture() {
		return this.dureeCloture;
	}

	public void setDureeCloture(int dureeCloture) {
		this.dureeCloture = dureeCloture;
	}

	public int getActionDepassementDureeCloture() {
		return this.actionDepassementDureeCloture;
	}

	public void setActionDepassementDureeCloture(int actionDepassementDureeCloture) {
		this.actionDepassementDureeCloture = actionDepassementDureeCloture;
	}

	public double getTauxTraitementRetenue() {
		return this.tauxTraitementRetenue;
	}

	public void setTauxTraitementRetenue(double tauxTraitementRetenue) {
		this.tauxTraitementRetenue = tauxTraitementRetenue;
	}

	public String getTauxTraitementRetenueS() {
		return this.tauxTraitementRetenueS;
	}

	public void setTauxTraitementRetenueS(String tauxTraitementRetenueS) {
		this.tauxTraitementRetenueS = tauxTraitementRetenueS;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public Constante.SanctionsDisciplinaires getSanction() {
		return this.sanction;
	}

	public void setSanction(Constante.SanctionsDisciplinaires sanction) {
		this.sanction = sanction;
	}

	public String getLibelleSanction() {
		return this.libelleSanction;
	}

	public void setLibelleSanction(String libelleSanction) {
		this.libelleSanction = libelleSanction;
	}
}
