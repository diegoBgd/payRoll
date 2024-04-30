package classesPaie;

import java.io.Serializable;

public class PrevisionFraisMedicauxC implements Serializable {
	private static final long serialVersionUID = 1573497623030045574L;
	private int id;
	private int nombrePersonnesAcharge;
	private EmployeC employe;
	private double previsionParEmploye;
	private double previsionParPersonneACharge;
	private double referenceConge = 12.0D;
	private double referenceFraisMedicaux = 12.0D;
	private double montantANouveau;
	private double montantPeriode;
	private String designation;
	private String previsionParEmployeS;
	private String previsionParPersonneAChargeS;

	public int getId() {
		return this.id;
	}

	private String referenceCongeS;
	private String referenceFraisMedicauxS;
	private String montantANouveauS;
	private String montantPeriodeS;
	private Historique historique;

	public void setId(int id) {
		this.id = id;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public double getPrevisionParEmploye() {
		return this.previsionParEmploye;
	}

	public void setPrevisionParEmploye(double previsionParEmploye) {
		this.previsionParEmploye = previsionParEmploye;
	}

	public double getPrevisionParPersonneACharge() {
		return this.previsionParPersonneACharge;
	}

	public void setPrevisionParPersonneACharge(double previsionParPersonneACharge) {
		this.previsionParPersonneACharge = previsionParPersonneACharge;
	}

	public double getReferenceConge() {
		return this.referenceConge;
	}

	public void setReferenceConge(double referenceConge) {
		this.referenceConge = referenceConge;
	}

	public double getReferenceFraisMedicaux() {
		return this.referenceFraisMedicaux;
	}

	public void setReferenceFraisMedicaux(double referenceFraisMedicaux) {
		this.referenceFraisMedicaux = referenceFraisMedicaux;
	}

	public double getMontantANouveau() {
		return this.montantANouveau;
	}

	public void setMontantANouveau(double montantANouveau) {
		this.montantANouveau = montantANouveau;
	}

	public double getMontantPeriode() {
		return this.montantPeriode;
	}

	public void setMontantPeriode(double montantPeriode) {
		this.montantPeriode = montantPeriode;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPrevisionParEmployeS() {
		return this.previsionParEmployeS;
	}

	public void setPrevisionParEmployeS(String previsionParEmployeS) {
		this.previsionParEmployeS = previsionParEmployeS;
	}

	public String getPrevisionParPersonneAChargeS() {
		return this.previsionParPersonneAChargeS;
	}

	public void setPrevisionParPersonneAChargeS(String previsionParPersonneAChargeS) {
		this.previsionParPersonneAChargeS = previsionParPersonneAChargeS;
	}

	public String getReferenceCongeS() {
		return this.referenceCongeS;
	}

	public void setReferenceCongeS(String referenceCongeS) {
		this.referenceCongeS = referenceCongeS;
	}

	public String getReferenceFraisMedicauxS() {
		return this.referenceFraisMedicauxS;
	}

	public void setReferenceFraisMedicauxS(String referenceFraisMedicauxS) {
		this.referenceFraisMedicauxS = referenceFraisMedicauxS;
	}

	public String getMontantANouveauS() {
		return this.montantANouveauS;
	}

	public void setMontantANouveauS(String montantANouveauS) {
		this.montantANouveauS = montantANouveauS;
	}

	public String getMontantPeriodeS() {
		return this.montantPeriodeS;
	}

	public void setMontantPeriodeS(String montantPeriodeS) {
		this.montantPeriodeS = montantPeriodeS;
	}

	public int getNombrePersonnesAcharge() {
		return this.nombrePersonnesAcharge;
	}

	public void setNombrePersonnesAcharge(int nombrePersonnesAcharge) {
		this.nombrePersonnesAcharge = nombrePersonnesAcharge;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
