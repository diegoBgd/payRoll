package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TroisiemeEvaluationEmployeC implements Serializable {
	private static final long serialVersionUID = -8604562845308507941L;
	private int id, idAncGrd, idNvGrd;
	private double ancienSalary, nouveauSalaire, tauxCot, tauxAv;
	private String dateEvaluationS;
	private Date dateEvaluation;
	private Historique historique;
	private DeuxiemeEvaluationEmployeC deuxiemeEvaluation;
	private TypeNotationC typeNotation;
	private Base typeAppreciation;
	private TraitementSalarialC traitement;
	private DetailGradeC nouvelgrd;

	private EmployeC employe;
	private List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluation = new ArrayList<DeuxiemeEvaluationEmployeDetailCritereC>();

	public TraitementSalarialC getTraitement() {
		return this.traitement;
	}

	public void setTraitement(TraitementSalarialC traitement) {
		this.traitement = traitement;
	}

	public List<DeuxiemeEvaluationEmployeDetailCritereC> getListDetailDeuxiemeEvaluation() {
		return this.listDetailDeuxiemeEvaluation;
	}

	public void setListDetailDeuxiemeEvaluation(
			List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluation) {
		this.listDetailDeuxiemeEvaluation = listDetailDeuxiemeEvaluation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateEvaluationS() {
		return this.dateEvaluationS;
	}

	public void setDateEvaluationS(String dateEvaluationS) {
		this.dateEvaluationS = dateEvaluationS;
	}

	public Date getDateEvaluation() {
		return this.dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public DeuxiemeEvaluationEmployeC getDeuxiemeEvaluation() {
		return this.deuxiemeEvaluation;
	}

	public void setDeuxiemeEvaluation(DeuxiemeEvaluationEmployeC deuxiemeEvaluation) {
		this.deuxiemeEvaluation = deuxiemeEvaluation;
	}

	public TypeNotationC getTypeNotation() {
		return this.typeNotation;
	}

	public void setTypeNotation(TypeNotationC typeNotation) {
		this.typeNotation = typeNotation;
	}

	public Base getTypeAppreciation() {
		return this.typeAppreciation;
	}

	public void setTypeAppreciation(Base typeAppreciation) {
		this.typeAppreciation = typeAppreciation;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public int getIdAncGrd() {
		return idAncGrd;
	}

	public void setIdAncGrd(int idAncGrd) {
		this.idAncGrd = idAncGrd;
	}

	public int getIdNvGrd() {
		return idNvGrd;
	}

	public void setIdNvGrd(int idNvGrd) {
		this.idNvGrd = idNvGrd;
	}

	public double getNouveauSalaire() {
		return nouveauSalaire;
	}

	public void setNouveauSalaire(double nouveauSalaire) {
		this.nouveauSalaire = nouveauSalaire;
	}

	public double getAncienSalary() {
		return ancienSalary;
	}

	public void setAncienSalary(double ancienSalary) {
		this.ancienSalary = ancienSalary;
	}

	public DetailGradeC getNouvelgrd() {
		return nouvelgrd;
	}

	public void setNouvelgrd(DetailGradeC nouvelgrd) {
		this.nouvelgrd = nouvelgrd;
	}

	public double getTauxCot() {
		return tauxCot;
	}

	public void setTauxCot(double tauxCot) {
		this.tauxCot = tauxCot;
	}

	public double getTauxAv() {
		return tauxAv;
	}

	public void setTauxAv(double tauxAv) {
		this.tauxAv = tauxAv;
	}

}
