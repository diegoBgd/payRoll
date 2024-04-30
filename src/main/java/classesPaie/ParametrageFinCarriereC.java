package classesPaie;

import java.io.Serializable;

public class ParametrageFinCarriereC implements Serializable {
	private static final long serialVersionUID = -5282532046410041304L;
	private int id, periodeProlongation, periodeSalire;

	private int ageRetraite;
	private int periodeAnticipe;
	private int anneesProlongationRetraite;
	private int ageMaxRetraite;

	private double pourcentageSalaire;

	private Historique historique;
	private Base personnel;
	private TypeNotationC typeNotation;
	private CategoriePersonnelC categorie;
	private GradePersonnelC dernierGrade;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAgeRetraite() {
		return this.ageRetraite;
	}

	public void setAgeRetraite(int ageRetraite) {
		this.ageRetraite = ageRetraite;
	}

	public int getAnneesProlongationRetraite() {
		return this.anneesProlongationRetraite;
	}

	public void setAnneesProlongationRetraite(int anneesProlongationRetraite) {
		this.anneesProlongationRetraite = anneesProlongationRetraite;
	}

	public int getAgeMaxRetraite() {
		return this.ageMaxRetraite;
	}

	public void setAgeMaxRetraite(int ageMaxRetraite) {
		this.ageMaxRetraite = ageMaxRetraite;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public Base getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Base personnel) {
		this.personnel = personnel;
	}

	public TypeNotationC getTypeNotation() {
		return this.typeNotation;
	}

	public void setTypeNotation(TypeNotationC typeNotation) {
		this.typeNotation = typeNotation;
	}

	public GradePersonnelC getDernierGrade() {
		return this.dernierGrade;
	}

	public void setDernierGrade(GradePersonnelC dernierGrade) {
		this.dernierGrade = dernierGrade;
	}

	public CategoriePersonnelC getCategorie() {
		return this.categorie;
	}

	public void setCategorie(CategoriePersonnelC categorie) {
		this.categorie = categorie;

	}

	public int getPeriodeProlongation() {
		return periodeProlongation;
	}

	public void setPeriodeProlongation(int periodeProlongation) {
		this.periodeProlongation = periodeProlongation;
	}

	public int getPeriodeSalire() {
		return periodeSalire;
	}

	public void setPeriodeSalire(int periodeSalire) {
		this.periodeSalire = periodeSalire;
	}

	public double getPourcentageSalaire() {
		return pourcentageSalaire;
	}

	public void setPourcentageSalaire(double pourcentageSalaire) {
		this.pourcentageSalaire = pourcentageSalaire;

	}

	public int getPeriodeAnticipe() {
		return periodeAnticipe;
	}

	public void setPeriodeAnticipe(int periodeAnticipe) {
		this.periodeAnticipe = periodeAnticipe;
	}

}
