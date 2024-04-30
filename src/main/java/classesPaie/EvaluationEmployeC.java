package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import persistencePaie.FichierBaseDAO;

public class EvaluationEmployeC implements Serializable {
	private static final long serialVersionUID = 5158561641818467968L;
	private int id, idNvGrade, idAnGrade;
	private int idTypeNotation;
	private int anneeValidite;
	private String noteGeneraleS;
	private String dateEvaluationS;
	private String pourcentageS;
	private String noteEvaluationS;
	private String justificationDiscordance;
	private double noteGenerale, nouveauSalaire;
	private double pourcentage, ancienSalary;
	private double noteEvaluation, tauxAvSal;
	private Date dateEvaluation;
	private Historique historique;
	private EmployeC employe;
	private TypeNotationC typeNotation;
	private Base typeAppreciation;
	private TraitementSalarialC traitement;
	private DetailGradeC nouvelgrd;
	private EvaluationEmployeDetailCritereC detail;
	private List<EvaluationEmployeDetailCritereC> listDetailEvaluation = new ArrayList<EvaluationEmployeDetailCritereC>();
	private List<EvaluationEmployeDetailCritereC> listDetailEvaluationDeleted = new ArrayList<EvaluationEmployeDetailCritereC>();

	public EvaluationEmployeDetailCritereC getDetail() {
		return this.detail;
	}

	public void setDetail(EvaluationEmployeDetailCritereC detail) {
		this.detail = detail;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTypeNotation() {
		return this.idTypeNotation;
	}

	public void setIdTypeNotation(int idTypeNotation) {
		this.idTypeNotation = idTypeNotation;
	}

	public String getNoteGeneraleS() {

		return this.noteGeneraleS;
	}

	public void setNoteGeneraleS(String noteGeneraleS) {
		this.noteGeneraleS = noteGeneraleS;
	}

	public String getDateEvaluationS() {
		return this.dateEvaluationS;
	}

	public void setDateEvaluationS(String dateEvaluationS) {
		this.dateEvaluationS = dateEvaluationS;
	}

	public String getPourcentageS() {

		return this.pourcentageS;
	}

	public void setPourcentageS(String pourcentageS) {
		this.pourcentageS = pourcentageS;
	}

	public String getNoteEvaluationS() {

		return this.noteEvaluationS;
	}

	public void setNoteEvaluationS(String noteEvaluationS) {
		this.noteEvaluationS = noteEvaluationS;
	}

	public String getJustificationDiscordance() {
		return this.justificationDiscordance;
	}

	public void setJustificationDiscordance(String justificationDiscordance) {
		this.justificationDiscordance = justificationDiscordance;
	}

	public double getNoteGenerale() {
		return this.noteGenerale;
	}

	public void setNoteGenerale(double noteGenerale) {
		this.noteGenerale = noteGenerale;
	}

	public double getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public double getNoteEvaluation() {
		return this.noteEvaluation;
	}

	public void setNoteEvaluation(double noteEvaluation) {
		this.noteEvaluation = noteEvaluation;
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

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
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

	public List<EvaluationEmployeDetailCritereC> getListDetailEvaluation() {
		return this.listDetailEvaluation;
	}

	public void setListDetailEvaluation(List<EvaluationEmployeDetailCritereC> listDetailEvaluation) {
		this.listDetailEvaluation = listDetailEvaluation;
	}

	public List<EvaluationEmployeDetailCritereC> getListDetailEvaluationDeleted() {
		return this.listDetailEvaluationDeleted;
	}

	public void setListDetailEvaluationDeleted(List<EvaluationEmployeDetailCritereC> listDetailEvaluationDeleted) {
		this.listDetailEvaluationDeleted = listDetailEvaluationDeleted;
	}

	public int getAnneeValidite() {
		return this.anneeValidite;
	}

	public void setAnneeValidite(int anneeValidite) {
		this.anneeValidite = anneeValidite;
	}

	public double getTauxAvSal() {
		return tauxAvSal;
	}

	public void setTauxAvSal(double tauxAvSal) {
		this.tauxAvSal = tauxAvSal;
	}

	public DetailGradeC getNouvelgrd() {
		return nouvelgrd;
	}

	public void setNouvelgrd(DetailGradeC nouvelgrd) {
		this.nouvelgrd = nouvelgrd;
	}

	public double getAncienSalary() {
		return ancienSalary;
	}

	public void setAncienSalary(double ancienSalary) {
		this.ancienSalary = ancienSalary;
	}

	public double getNouveauSalaire() {
		return nouveauSalaire;
	}

	public void setNouveauSalaire(double nouveauSalaire) {
		this.nouveauSalaire = nouveauSalaire;
	}

	public TraitementSalarialC getTraitement() {
		return traitement;
	}

	public void setTraitement(TraitementSalarialC traitement) {
		this.traitement = traitement;
	}

	public int getIdNvGrade() {
		return idNvGrade;
	}

	public void setIdNvGrade(int idNvGrade) {
		this.idNvGrade = idNvGrade;
	}

	public int getIdAnGrade() {
		return idAnGrade;
	}

	public void setIdAnGrade(int idAnGrade) {
		this.idAnGrade = idAnGrade;
	}

	public void calculResultatEvaluation() {
		setPourcentage(0.0D);
		setPourcentageS("");
		setNoteEvaluation(0.0D);
		setNoteEvaluationS("");
		setNoteGenerale(0.0D);
		setNoteGeneraleS("");
		for (EvaluationEmployeDetailCritereC eval : getListDetailEvaluation()) {

			// System.out.println(eval);
			if (eval.getNoteObtenue() > eval.getDetailCritere().getNote()) {

				// System.out.println("Les points obtenus doivent être inférieur ou egale aux
				// points de références");

				HelperC.afficherMessage("Information",
						"Les points obtenus doivent être inférieur ou egale aux points de références",
						FacesMessage.SEVERITY_WARN);
				return;

			} else if (eval.getNoteObtenue() <= eval.getDetailCritere().getNote() && eval.isApplicable()) {

				// System.out.println("++++++++++++");
				setNoteEvaluation(getNoteEvaluation() + eval.getNoteObtenue());
				// System.out.println(getNoteEvaluation());
				setNoteEvaluationS(HelperC.TraitementMontant.getMontantFormate(getNoteEvaluation(), 2));
				setNoteGenerale(getNoteGenerale() + eval.getDetailCritere().getNote());
				setNoteGeneraleS(HelperC.TraitementMontant.getMontantFormate(getNoteGenerale(), 0));
				setPourcentage(getNoteEvaluation() * 100.0D / getNoteGenerale());
				setPourcentageS(HelperC.TraitementMontant.getMontantFormate(getPourcentage(), 2));
			}
			for (TypeNotationC typeNotation : FichierBaseDAO.getInstance().getAllTypeNotation()) {

				if (typeNotation.getPourcentageMin() <= getPourcentage()
						&& typeNotation.getPourcentageMax() > getPourcentage()) {

					setTypeNotation(typeNotation);
					this.idTypeNotation = getTypeNotation().getId();

				}
			}
		}
	}
}
