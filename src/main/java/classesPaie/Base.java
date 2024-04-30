package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class Base implements Serializable {
	private static final long serialVersionUID = -3951661310585326273L;
	private int id;
	private String code;
	private String designation;
	private String symbole;
	private Date dateCreation;
	private Date dateModif;
	private OperateurC operCreation;
	private OperateurC operModif;
	private Historique historique;

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

	public String getSymbole() {
		return this.symbole;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	public OperateurC getOperCreation() {
		return this.operCreation;
	}

	public void setOperCreation(OperateurC operCreation) {
		this.operCreation = operCreation;
	}

	public OperateurC getOperModif() {
		return this.operModif;
	}

	public void setOperModif(OperateurC operModif) {
		this.operModif = operModif;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
