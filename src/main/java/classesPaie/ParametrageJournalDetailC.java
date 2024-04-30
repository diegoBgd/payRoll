package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametrageJournalDetailC implements Serializable {
	private static final long serialVersionUID = 3203711879871211729L;
	private int id;
	private int typeElement;
	private int idEntete;
	private int indexNum;
	private int nombrElement;
	private String titrElement;
	private String libelle;
	private List<ParametrageJournalElementC> listDetailElement = new ArrayList<ParametrageJournalElementC>();
	private List<ParametrageJournalElementC> liteDeletedElement = new ArrayList<ParametrageJournalElementC>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeElement() {
		return this.typeElement;
	}

	public void setTypeElement(int typeElement) {
		this.typeElement = typeElement;
	}

	public String getTitrElement() {
		return this.titrElement;
	}

	public void setTitrElement(String titrElement) {
		this.titrElement = titrElement;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getIdEntete() {
		return this.idEntete;
	}

	public void setIdEntete(int idEntete) {
		this.idEntete = idEntete;
	}

	public int getIndexNum() {
		return this.indexNum;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}

	public List<ParametrageJournalElementC> getListDetailElement() {
		return this.listDetailElement;
	}

	public void setListDetailElement(List<ParametrageJournalElementC> listDetailElement) {
		this.listDetailElement = listDetailElement;
	}

	public List<ParametrageJournalElementC> getLiteDeletedElement() {
		return this.liteDeletedElement;
	}

	public void setLiteDeletedElement(List<ParametrageJournalElementC> liteDeletedElement) {
		this.liteDeletedElement = liteDeletedElement;
	}

	public int getNombrElement() {
		this.nombrElement = getListDetailElement().size();
		return this.nombrElement;
	}

	public void setNombrElement(int nombrElement) {
		this.nombrElement = nombrElement;
	}
}
