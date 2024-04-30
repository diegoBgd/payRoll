package classesPaie;

import java.io.Serializable;

public class DroitC implements Serializable {
	private static final long serialVersionUID = -5088192229343968205L;
	private int id;
	private int numero;
	private String libelle;
	private boolean creer;
	private boolean modifier;
	private boolean supprimer;
	private boolean afficher;
	private boolean desactiveModifier;
	private boolean desactiveSupprimer;
	private RoleC role;
	private Constante.Role rol;
	private Base fonction;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public RoleC getRole() {
		return this.role;
	}

	public void setRole(RoleC role) {
		this.role = role;
	}

	public boolean isCreer() {
		return this.creer;
	}

	public void setCreer(boolean creer) {
		this.creer = creer;
	}

	public boolean isModifier() {
		return this.modifier;
	}

	public void setModifier(boolean modifier) {
		this.modifier = modifier;
	}

	public boolean isSupprimer() {
		return this.supprimer;
	}

	public void setSupprimer(boolean supprimer) {
		this.supprimer = supprimer;
	}

	public Constante.Role getRol() {
		return this.rol;
	}

	public void setRol(Constante.Role rol) {
		this.rol = rol;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isDesactiveModifier() {
		return this.desactiveModifier;
	}

	public void setDesactiveModifier(boolean desactiveModifier) {
		this.desactiveModifier = desactiveModifier;
	}

	public boolean isDesactiveSupprimer() {
		return this.desactiveSupprimer;
	}

	public void setDesactiveSupprimer(boolean desactiveSupprimer) {
		this.desactiveSupprimer = desactiveSupprimer;
	}

	public Base getFonction() {
		return this.fonction;
	}

	public void setFonction(Base fonction) {
		this.fonction = fonction;
	}

	public boolean isAfficher() {
		return this.afficher;
	}

	public void setAfficher(boolean afficher) {
		this.afficher = afficher;
	}
}
