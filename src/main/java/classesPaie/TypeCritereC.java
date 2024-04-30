package classesPaie;

import java.io.Serializable;

public class TypeCritereC implements Serializable {
	private static final long serialVersionUID = -991021918637711843L;
	private int id;
	private String code;
	private String designation;
	private String noteAppreciationGlobaleS;
	private double noteAppreciationGlobale;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getNoteAppreciationGlobale() {
		return this.noteAppreciationGlobale;
	}

	public void setNoteAppreciationGlobale(double noteAppreciationGlobale) {
		this.noteAppreciationGlobale = noteAppreciationGlobale;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public String getNoteAppreciationGlobaleS() {
		return this.noteAppreciationGlobaleS;
	}

	public void setNoteAppreciationGlobaleS(String noteAppreciationGlobaleS) {
		this.noteAppreciationGlobaleS = noteAppreciationGlobaleS;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
