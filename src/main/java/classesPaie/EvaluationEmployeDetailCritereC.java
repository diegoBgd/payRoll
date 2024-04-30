package classesPaie;

import java.io.Serializable;

public class EvaluationEmployeDetailCritereC implements Serializable {
	private static final long serialVersionUID = 1226050377455000093L;
	private int id;
	private int indexe;
	private double noteObtenue;
	private boolean applicable = true;
	private boolean disable;
	private String noteObtenueS;
	private Historique historique;
	private EvaluationEmployeDetailCritereC selected;
	private EvaluationEmployeC entete;
	private DetailCritereEvaluationC detailCritere;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public EvaluationEmployeC getEntete() {
		return this.entete;
	}

	public void setEntete(EvaluationEmployeC entete) {
		this.entete = entete;
	}

	public DetailCritereEvaluationC getDetailCritere() {
		return this.detailCritere;
	}

	public void setDetailCritere(DetailCritereEvaluationC detailCritere) {
		this.detailCritere = detailCritere;
	}

	public int getIndexe() {
		return this.indexe;
	}

	public void setIndexe(int indexe) {
		this.indexe = indexe;
	}

	public double getNoteObtenue() {
		return this.noteObtenue;
	}

	public void setNoteObtenue(double noteObtenue) {
		this.noteObtenue = noteObtenue;
	}

	public String getNoteObtenueS() {
		return this.noteObtenueS;
	}

	public void setNoteObtenueS(String noteObtenueS) {
		this.noteObtenueS = noteObtenueS;
	}

	public boolean isApplicable() {
		return this.applicable;
	}

	public void setApplicable(boolean applicable) {
		this.applicable = applicable;
	}

	public void changeApplicable() {
		setApplicable(isApplicable());
		if (!applicable)
			setDisable(true);
		else
			setDisable(false);

		this.entete.calculResultatEvaluation();
	}

	public EvaluationEmployeDetailCritereC getSelected() {
		return this.selected;
	}

	public void setSelected(EvaluationEmployeDetailCritereC selected) {
		this.selected = selected;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public void changeNote() {
		try {
			setNoteObtenue(Double.valueOf(getNoteObtenueS().replace("-", "").replace(" ", "").replace(",", ".").trim())
					.doubleValue());

		} catch (Exception e) {

			setNoteObtenue(0.0D);
		}
		if (getNoteObtenue() < 0.0D) {
			setNoteObtenue(0.0D);
		}
		setNoteObtenueS(HelperC.TraitementMontant.getMontantFormate(getNoteObtenue(), 2));
		setNoteObtenue(Double.valueOf(getNoteObtenueS().replace(" ", "").replace(",", ".").trim()).doubleValue());
		this.entete.setTypeAppreciation(null);
		this.entete.calculResultatEvaluation();

	}

}
