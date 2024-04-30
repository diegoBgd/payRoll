package classesPaie;

import java.io.Serializable;

public class DeuxiemeEvaluationEmployeDetailCritereC implements Serializable {
	private static final long serialVersionUID = -1669661618648326912L;
	private int id;
	private int indexe;
	private double noteObtenue;
	private boolean applicable = true;
	private String noteObtenueS;
	private Historique historique;
	private DeuxiemeEvaluationEmployeC entete;
	private EvaluationEmployeDetailCritereC detailCritere;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public DeuxiemeEvaluationEmployeC getEntete() {
		return this.entete;
	}

	public void setEntete(DeuxiemeEvaluationEmployeC entete) {
		this.entete = entete;
	}

	public EvaluationEmployeDetailCritereC getDetailCritere() {
		return this.detailCritere;
	}

	public void setDetailCritere(EvaluationEmployeDetailCritereC detailCritere) {
		this.detailCritere = detailCritere;
	}

	public boolean isApplicable() {
		return this.applicable;
	}

	public void setApplicable(boolean applicable) {
		this.applicable = applicable;
	}

	public void changeApplicable() {
		setApplicable(isApplicable());
		this.entete.calculResultatEvaluation();
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
		this.entete.calculResultatEvaluation();

//		     if (getNoteObtenue() < 0.0D)
//		     {
//		       setNoteObtenue(0.0D);
//		     }
//		     setNoteObtenueS(Helper.TraitementMontant.getMontantFormate(getNoteObtenue(), 2));
//		     setNoteObtenue(Double.valueOf(getNoteObtenueS().replace(" ", "").replace(",", ".").trim()).doubleValue());
//		     this.entete.calculResultatEvaluation();
//		    
//		     if (getNoteObtenue() < 0.0D)
//		     {
//			   setNoteObtenue(0.0D);
//		     }
//		   		setNoteObtenueS(Helper.TraitementMontant.getMontantFormate(getNoteObtenue(), 2));
//		   		setNoteObtenue(Double.valueOf(getNoteObtenueS().replace(" ", "").replace(",", ".").trim()).doubleValue());
//		   		this.entete.calculResultatEvaluation();
	}
}
