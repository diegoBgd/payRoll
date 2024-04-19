package classesPaie;

import java.io.Serializable;

public class ParametreAvancementSalaireC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4240213976491760203L;
	private int idPersonnel,idCategorie,id;
	private double tauxAvancement;
	private boolean avancementInconditionnel,ancienSalaireInferieur,ancienSalaireSuperieur;
	private String libellePersonl,libelleCat;
	private TypeNotationC typeNotation;
	 
	public ParametreAvancementSalaireC() {
		
	}

	public int getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(int idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTauxAvancement() {
		return tauxAvancement;
	}

	public void setTauxAvancement(double tauxAvancement) {
		this.tauxAvancement = tauxAvancement;
	}

	public boolean isAvancementInconditionnel() {
		return avancementInconditionnel;
	}

	public void setAvancementInconditionnel(boolean avancementInconditionnel) {
		this.avancementInconditionnel = avancementInconditionnel;
	}

	public boolean isAncienSalaireInferieur() {
		return ancienSalaireInferieur;
	}

	public void setAncienSalaireInferieur(boolean ancienSalaireInferieur) {
		this.ancienSalaireInferieur = ancienSalaireInferieur;
	}

	public boolean isAncienSalaireSuperieur() {
		return ancienSalaireSuperieur;
	}

	public void setAncienSalaireSuperieur(boolean ancienSalaireSuperieur) {
		this.ancienSalaireSuperieur = ancienSalaireSuperieur;
	}

	public String getLibellePersonl() {
		return libellePersonl;
	}

	public void setLibellePersonl(String libellePersonl) {
		this.libellePersonl = libellePersonl;
	}

	public String getLibelleCat() {
		return libelleCat;
	}

	public void setLibelleCat(String libelleCat) {
		this.libelleCat = libelleCat;
	}

	public TypeNotationC getTypeNotation() {
		return typeNotation;
	}

	public void setTypeNotation(TypeNotationC typeNotation) {
		this.typeNotation = typeNotation;
	}

	

}
