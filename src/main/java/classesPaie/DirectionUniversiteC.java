package classesPaie;

import java.util.ArrayList;
import java.util.List;

public class DirectionUniversiteC extends Base {
	private static final long serialVersionUID = 4662812143241752185L;
	private int id;
	private String code;
	private String designation;
	private Historique historique;
	private List<DirectionUniversiteDetailC> listeDetail = new ArrayList<DirectionUniversiteDetailC>();
	private List<DirectionUniversiteDetailC> listeDetailDeleted = new ArrayList<DirectionUniversiteDetailC>();

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

	public List<DirectionUniversiteDetailC> getListeDetail() {
		return this.listeDetail;
	}

	public void setListeDetail(List<DirectionUniversiteDetailC> listeDetail) {
		this.listeDetail = listeDetail;
	}

	public List<DirectionUniversiteDetailC> getListeDetailDeleted() {
		return this.listeDetailDeleted;
	}

	public void setListeDetailDeleted(List<DirectionUniversiteDetailC> listeDetailDeleted) {
		this.listeDetailDeleted = listeDetailDeleted;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
