package classesPaie;

import java.io.Serializable;

public class BulletinAllocationC implements Serializable {
	private static final long serialVersionUID = -5588953129834874886L;
	private int id;
	private int idPerson;
	private int idBulletin;
	private double montant;
	private String nomPersonne;
	private String satut;
	private String printMontant;
	private PersonneChargeC personneCharge;
	private int nombre;

	public PersonneChargeC getPersonneCharge() {
		return this.personneCharge;
	}

	public void setPersonneCharge(PersonneChargeC personneCharge) {
		this.personneCharge = personneCharge;
	}

	public int getIdBulletin() {
		return this.idBulletin;
	}

	public void setIdBulletin(int idBulletin) {
		this.idBulletin = idBulletin;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getNomPersonne() {
		if (this.personneCharge != null) {
			this.nomPersonne = this.personneCharge.getNomPersonneCharge();
		}
		return this.nomPersonne;
	}

	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}

	public String getSatut() {
		if (this.personneCharge != null) {
			this.satut = Constante.getLibelleStatutPersonneACharge(this.personneCharge.getRelation());
		}
		return this.satut;
	}

	public void setSatut(String satut) {
		this.satut = satut;
	}

	public String getPrintMontant() {
		this.printMontant = HelperC.decimalNumber(getMontant(), 0, true);
		return this.printMontant;
	}

	public void setPrintMontant(String printMontant) {
		this.printMontant = printMontant;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

}
