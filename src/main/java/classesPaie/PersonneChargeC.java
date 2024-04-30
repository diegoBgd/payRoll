package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonneChargeC implements Serializable {
	private static final long serialVersionUID = 5815287299923124570L;
	private int id;
	private int numero;
	private int relation;
	private double montant;
	private String nomPersonneCharge;
	private String photo;
	private String dateNaissanceS;
	private String sexe;
	private String sexeS;
	private String dateSaisieS;
	private int nombre;
	private String relationS;
	private String libelleRelation;
	private Date dateNaissance;
	private Date dateSaisie;
	private EmployeC employe;

	private Historique historic;
	private OperateurC operator;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public void setHistoric(Historique historic) {
		this.historic = historic;
	}

	public OperateurC getOperator() {
		return this.operator;
	}

	public void setOperator(OperateurC operator) {
		this.operator = operator;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getRelation() {
		return this.relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}

	public String getNomPersonneCharge() {
		return this.nomPersonneCharge;
	}

	public void setNomPersonneCharge(String nomPersonneCharge) {
		this.nomPersonneCharge = nomPersonneCharge;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDateNaissanceS() {
		return this.dateNaissanceS;
	}

	public void setDateNaissanceS(String dateNaissanceS) {
		this.dateNaissanceS = dateNaissanceS;
	}

	public String getSexeS() {
		return this.sexeS;
	}

	public void setSexeS(String sexeS) {
		this.sexeS = sexeS;
	}

	public String getDateSaisieS() {
		return this.dateSaisieS;
	}

	public void setDateSaisieS(String dateSaisieS) {
		this.dateSaisieS = dateSaisieS;
	}

	public String getRelationS() {
		return this.relationS;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public void setRelationS(String relationS) {
		this.relationS = relationS;
	}

	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateSaisie() {
		return this.dateSaisie;
	}

	public void setDateSaisie(Date dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public String getLibelleRelation() {
		return this.libelleRelation;
	}

	public void setLibelleRelation(String libelleRelation) {
		this.libelleRelation = libelleRelation;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public Historique getHistoric() {
		return historic;
	}

}
