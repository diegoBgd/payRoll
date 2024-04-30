package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CritereEvaluationC implements Serializable {
	private static final long serialVersionUID = 334264713110927822L;
	private int id;
	private int index;
	private String code;
	private String designation;
	private String noteGeneralS;
	private double noteGeneral;
	private Historique historique;
	private List<DetailCritereEvaluationC> listDetailCritere = new ArrayList<DetailCritereEvaluationC>();
	private List<DetailCritereEvaluationC> listDetailCritereDeleted = new ArrayList<DetailCritereEvaluationC>();
	private TypeCritereC typeCritere;

	public List<DetailCritereEvaluationC> getListDetailCritere() {
		return this.listDetailCritere;
	}

	public void setListDetailCritere(List<DetailCritereEvaluationC> listDetailCritere) {
		this.listDetailCritere = listDetailCritere;
	}

	public List<DetailCritereEvaluationC> getListDetailCritereDeleted() {
		return this.listDetailCritereDeleted;
	}

	public void setListDetailCritereDeleted(List<DetailCritereEvaluationC> listDetailCritereDeleted) {
		this.listDetailCritereDeleted = listDetailCritereDeleted;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getNoteGeneral() {
		return this.noteGeneral;
	}

	public void setNoteGeneral(double noteGeneral) {
		this.noteGeneral = noteGeneral;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public TypeCritereC getTypeCritere() {
		return this.typeCritere;
	}

	public void setTypeCritere(TypeCritereC typeCritere) {
		this.typeCritere = typeCritere;
	}

	public String getNoteGeneralS() {
		return this.noteGeneralS;
	}

	public void setNoteGeneralS(String noteGeneralS) {
		this.noteGeneralS = noteGeneralS;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
