package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametrageDureeEtTraitementPositionC implements Serializable {
	private static final long serialVersionUID = 3003227177233022188L;
	private int id;
	private int dureeDemande;
	private int dureePosition;
	private int positionDepassementDuree;
	private int cause;
	private double tauxTraitementAvantFin;
	private double tauxTraitementApresFin;
	private boolean ajoutAllocationFamiliale;
	private boolean ajoutIndemniteLogement;
	private boolean ajoutSoinsSante;
	private boolean avancementTraitement;
	private Historique historique;
	private List<DetailParametrageTraitementPositionC> listDuree = new ArrayList<DetailParametrageTraitementPositionC>();
	private List<DetailParametrageTraitementPositionC> listDureeDeleted = new ArrayList<DetailParametrageTraitementPositionC>();

	private ConditionPositionC conditionPosition;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDureeDemande() {
		return this.dureeDemande;
	}

	public void setDureeDemande(int dureeDemande) {
		this.dureeDemande = dureeDemande;
	}

	public int getDureePosition() {
		return this.dureePosition;
	}

	public void setDureePosition(int dureePosition) {
		this.dureePosition = dureePosition;
	}

	public int getPositionDepassementDuree() {
		return this.positionDepassementDuree;
	}

	public void setPositionDepassementDuree(int positionDepassementDuree) {
		this.positionDepassementDuree = positionDepassementDuree;
	}

	public int getCause() {
		return this.cause;
	}

	public void setCause(int cause) {
		this.cause = cause;
	}

	public double getTauxTraitementAvantFin() {
		return this.tauxTraitementAvantFin;
	}

	public void setTauxTraitementAvantFin(double tauxTraitementAvantFin) {
		this.tauxTraitementAvantFin = tauxTraitementAvantFin;
	}

	public double getTauxTraitementApresFin() {
		return this.tauxTraitementApresFin;
	}

	public void setTauxTraitementApresFin(double tauxTraitementApresFin) {
		this.tauxTraitementApresFin = tauxTraitementApresFin;
	}

	public boolean isAjoutAllocationFamiliale() {
		return this.ajoutAllocationFamiliale;
	}

	public void setAjoutAllocationFamiliale(boolean ajoutAllocationFamiliale) {
		this.ajoutAllocationFamiliale = ajoutAllocationFamiliale;
	}

	public boolean isAjoutIndemniteLogement() {
		return this.ajoutIndemniteLogement;
	}

	public void setAjoutIndemniteLogement(boolean ajoutIndemniteLogement) {
		this.ajoutIndemniteLogement = ajoutIndemniteLogement;
	}

	public boolean isAjoutSoinsSante() {
		return this.ajoutSoinsSante;
	}

	public void setAjoutSoinsSante(boolean ajoutSoinsSante) {
		this.ajoutSoinsSante = ajoutSoinsSante;
	}

	public boolean isAvancementTraitement() {
		return this.avancementTraitement;
	}

	public void setAvancementTraitement(boolean avancementTraitement) {
		this.avancementTraitement = avancementTraitement;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public ConditionPositionC getConditionPosition() {
		return this.conditionPosition;
	}

	public void setConditionPosition(ConditionPositionC conditionPosition) {
		this.conditionPosition = conditionPosition;
	}

	public List<DetailParametrageTraitementPositionC> getListDuree() {
		return this.listDuree;
	}

	public void setListDuree(List<DetailParametrageTraitementPositionC> listDuree) {
		this.listDuree = listDuree;
	}

	public List<DetailParametrageTraitementPositionC> getListDureeDeleted() {
		return this.listDureeDeleted;
	}

	public void setListDureeDeleted(List<DetailParametrageTraitementPositionC> listDureeDeleted) {
		this.listDureeDeleted = listDureeDeleted;
	}
}
