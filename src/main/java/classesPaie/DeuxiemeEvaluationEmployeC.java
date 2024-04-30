package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import persistencePaie.FichierBaseDAO;

public class DeuxiemeEvaluationEmployeC implements Serializable {
	private static final long serialVersionUID = 7354580136041066257L;
	private int id;
	private int idTypeNotation;
	private String noteGeneraleS;
	private String dateEvaluationS;
	private String pourcentageS;
	private String noteEvaluationS;
	private String justificationDiscordance;
	private double noteGenerale;
	private double pourcentage;
	private double noteEvaluation;
	private Date dateEvaluation;
	private Historique historique;
	private EvaluationEmployeC premiereEvaluation;
	private TypeNotationC typeNotation;
	private Base typeAppreciation;
	private List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluation = new ArrayList<DeuxiemeEvaluationEmployeDetailCritereC>();
	private List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluationDeleted = new ArrayList<DeuxiemeEvaluationEmployeDetailCritereC>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJustificationDiscordance() {
		return this.justificationDiscordance;
	}

	public void setJustificationDiscordance(String justificationDiscordance) {
		this.justificationDiscordance = justificationDiscordance;
	}

	public String getNoteEvaluationS() {
		return this.noteEvaluationS;
	}

	public void setNoteEvaluationS(String noteEvaluationS) {
		this.noteEvaluationS = noteEvaluationS;
	}

	public String getDateEvaluationS() {
		return this.dateEvaluationS;
	}

	public void setDateEvaluationS(String dateEvaluationS) {
		this.dateEvaluationS = dateEvaluationS;
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

	public EvaluationEmployeC getPremiereEvaluation() {
		return this.premiereEvaluation;
	}

	public void setPremiereEvaluation(EvaluationEmployeC premiereEvaluation) {
		this.premiereEvaluation = premiereEvaluation;
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

	public List<DeuxiemeEvaluationEmployeDetailCritereC> getListDetailDeuxiemeEvaluation() {
		return this.listDetailDeuxiemeEvaluation;
	}

	public void setListDetailDeuxiemeEvaluation(
			List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluation) {
		this.listDetailDeuxiemeEvaluation = listDetailDeuxiemeEvaluation;
	}

	public List<DeuxiemeEvaluationEmployeDetailCritereC> getListDetailDeuxiemeEvaluationDeleted() {
		return this.listDetailDeuxiemeEvaluationDeleted;
	}

	public void setListDetailDeuxiemeEvaluationDeleted(
			List<DeuxiemeEvaluationEmployeDetailCritereC> listDetailDeuxiemeEvaluationDeleted) {
		this.listDetailDeuxiemeEvaluationDeleted = listDetailDeuxiemeEvaluationDeleted;
	}

	public String getNoteGeneraleS() {
		return this.noteGeneraleS;
	}

	public void setNoteGeneraleS(String noteGeneraleS) {
		this.noteGeneraleS = noteGeneraleS;
	}

	public String getPourcentageS() {
		return this.pourcentageS;
	}

	public void setPourcentageS(String pourcentageS) {
		this.pourcentageS = pourcentageS;
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

	public int getIdTypeNotation() {
		return this.idTypeNotation;
	}

	public void setIdTypeNotation(int idTypeNotation) {
		this.idTypeNotation = idTypeNotation;
	}

	public void calculResultatEvaluation() {
		this.pourcentage = 0.0D;
		this.pourcentageS = "";
		this.noteEvaluation = 0.0D;
		this.noteEvaluationS = "";
		this.noteGenerale = 0.0D;
		this.noteGeneraleS = "";
		for (DeuxiemeEvaluationEmployeDetailCritereC eval : getListDetailDeuxiemeEvaluation()) {

			if (eval.getNoteObtenue() > eval.getDetailCritere().getDetailCritere().getNote()) {
				HelperC.afficherMessage("Information",
						"Les points obtenus doivent être inférieur ou egale aux points de références");
				return;
			} else if (eval.isApplicable()) {

				this.noteEvaluation += eval.getNoteObtenue();
				this.noteEvaluationS = HelperC.TraitementMontant.getMontantFormate(this.noteEvaluation, 2);
				this.noteGenerale += eval.getDetailCritere().getDetailCritere().getNote();
				this.noteGeneraleS = HelperC.TraitementMontant.getMontantFormate(this.noteGenerale, 0);
				this.pourcentage = this.noteEvaluation * 100.0D / this.noteGenerale;
				this.pourcentageS = HelperC.TraitementMontant.getMontantFormate(this.pourcentage, 2);
			}
			for (TypeNotationC typeNotation : FichierBaseDAO.getInstance().getAllTypeNotation()) {

				if (typeNotation.getPourcentageMin() <= this.pourcentage
						&& typeNotation.getPourcentageMax() > this.pourcentage) {

					setTypeNotation(typeNotation);
					this.idTypeNotation = getTypeNotation().getId();
				}
			}
		}
	}
}
