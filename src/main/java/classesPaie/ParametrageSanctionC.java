package classesPaie;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

public class ParametrageSanctionC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2802891347257988960L;
	private int id,idRetenu,dureeMin,dureeMax,dureeRecours,dureeCloture;
	private double tauxRetenue;
	private String libelleSanction;

	public ParametrageSanctionC() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getIdRetenu() {
		return idRetenu;
	}
	public void setIdRetenu(int idRetenu) {
		this.idRetenu = idRetenu;
	}
	public int getDureeMin() {
		return dureeMin;
	}
	public void setDureeMin(int dureeMin) {
		this.dureeMin = dureeMin;
	}
	public int getDureeMax() {
		return dureeMax;
	}
	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}
	public int getDureeRecours() {
		return dureeRecours;
	}
	public void setDureeRecours(int dureeRecours) {
		this.dureeRecours = dureeRecours;
	}
	public int getDureeCloture() {
		return dureeCloture;
	}
	public void setDureeCloture(int dureeCloture) {
		this.dureeCloture = dureeCloture;
	}
	public double getTauxRetenue() {
		return tauxRetenue;
	}
	public void setTauxRetenue(double tauxRetenue) {
		this.tauxRetenue = tauxRetenue;
	}
	public String getLibelleSanction() {
		return libelleSanction;
	}
	public void setLibelleSanction(String libelleSanction) {
		this.libelleSanction = libelleSanction;
	}
	
}
