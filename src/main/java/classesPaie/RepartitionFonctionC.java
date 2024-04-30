package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepartitionFonctionC implements Serializable {
	private static final long serialVersionUID = -2559516413284084236L;
	private int id;
	private String code;
	private String designation;
	private Historique historique;
	private List<RepartionFonctionDetailC> listeDetail = new ArrayList<RepartionFonctionDetailC>();
	private List<RepartionFonctionDetailC> listeDetailDeleted = new ArrayList<RepartionFonctionDetailC>();

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

	public List<RepartionFonctionDetailC> getListeDetail() {
		return this.listeDetail;
	}

	public void setListeDetail(List<RepartionFonctionDetailC> listeDetail) {
		this.listeDetail = listeDetail;
	}

	public List<RepartionFonctionDetailC> getListeDetailDeleted() {
		return this.listeDetailDeleted;
	}

	public void setListeDetailDeleted(List<RepartionFonctionDetailC> listeDetailDeleted) {
		this.listeDetailDeleted = listeDetailDeleted;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
