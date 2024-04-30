package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametrageJournalC implements Serializable {
	private static final long serialVersionUID = 9059266199618346320L;
	private int id;
	private String code;
	private String libelle;
	private List<ParametrageJournalDetailC> listeDetail = new ArrayList<ParametrageJournalDetailC>();
	private List<ParametrageJournalDetailC> listDeleted = new ArrayList<ParametrageJournalDetailC>();

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

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ParametrageJournalDetailC> getListeDetail() {
		return this.listeDetail;
	}

	public void setListeDetail(List<ParametrageJournalDetailC> listeDetail) {
		this.listeDetail = listeDetail;
	}

	public List<ParametrageJournalDetailC> getListDeleted() {
		return this.listDeleted;
	}

	public void setListDeleted(List<ParametrageJournalDetailC> listDeleted) {
		this.listDeleted = listDeleted;
	}
}
