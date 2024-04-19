package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametragePositionC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2061140649646577246L;
	private int id,idPersonnel,idposition,dureePosition,dureDemande,idCondition,idCategorie;
	private double tauxSalaire;
	private boolean ajoutAllocationFamiliale,ajoutAllocationLogement,
					avancementGrade,avancementTraitement,
					bloquerPaie;
	private String libellePersonnel,libelleCondition;
	
	private List<ParametragePositionDetailC>listDetailPrime,listRemoved;
	
	public ParametragePositionC() {
		listDetailPrime=new ArrayList<ParametragePositionDetailC>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(int idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public int getIdposition() {
		return idposition;
	}

	public void setIdposition(int idposition) {
		this.idposition = idposition;
	}

	public int getDureePosition() {
		return dureePosition;
	}

	public void setDureePosition(int dureePosition) {
		this.dureePosition = dureePosition;
	}

	public boolean isAjoutAllocationFamiliale() {
		return ajoutAllocationFamiliale;
	}

	public void setAjoutAllocationFamiliale(boolean ajoutAllocationFamiliale) {
		this.ajoutAllocationFamiliale = ajoutAllocationFamiliale;
	}

	public boolean isAjoutAllocationLogement() {
		return ajoutAllocationLogement;
	}

	public void setAjoutAllocationLogement(boolean ajoutAllocationLogement) {
		this.ajoutAllocationLogement = ajoutAllocationLogement;
	}

	public boolean isAvancementGrade() {
		return avancementGrade;
	}

	public void setAvancementGrade(boolean avancementGrade) {
		this.avancementGrade = avancementGrade;
	}

	public boolean isAvancementTraitement() {
		return avancementTraitement;
	}

	public void setAvancementTraitement(boolean avancementTraitement) {
		this.avancementTraitement = avancementTraitement;
	}


	public List<ParametragePositionDetailC> getListDetailPrime() {
		return listDetailPrime;
	}

	public void setListDetailPrime(List<ParametragePositionDetailC> listDetailPrime) {
		this.listDetailPrime = listDetailPrime;
	}

	public int getDureDemande() {
		return dureDemande;
	}

	public void setDureDemande(int dureDemande) {
		this.dureDemande = dureDemande;
	}

	
	public double getTauxSalaire() {
		return tauxSalaire;
	}

	public void setTauxSalaire(double tauxSalaire) {
		this.tauxSalaire = tauxSalaire;
	}

	public boolean isBloquerPaie() {
		return bloquerPaie;
	}

	public void setBloquerPaie(boolean bloquerPaie) {
		this.bloquerPaie = bloquerPaie;
	}

	public int getIdCondition() {
		return idCondition;
	}

	public void setIdCondition(int idCondition) {
		this.idCondition = idCondition;
	}

	public List<ParametragePositionDetailC> getListRemoved() {
		return listRemoved;
	}

	public void setListRemoved(List<ParametragePositionDetailC> listRemoved) {
		this.listRemoved = listRemoved;
	}

	public String getLibellePersonnel() {
		return libellePersonnel;
	}

	public void setLibellePersonnel(String libellePersonnel) {
		this.libellePersonnel = libellePersonnel;
	}

	public String getLibelleCondition() {
		return libelleCondition;
	}

	public void setLibelleCondition(String libelleCondition) {
		this.libelleCondition = libelleCondition;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	
}
