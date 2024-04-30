package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class OperateurC implements Serializable {
	private static final long serialVersionUID = -7819695991073962372L;
	private int id;
	private int idEmploye, idFonction;
	private boolean actif = true, lineUser;
	private Date dateModif;
	private String codeSecret;
	private String login;
	private Historique historique;
	private EmployeC employe;

	public boolean isActif() {
		return this.actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getCodeSecret() {
		return this.codeSecret;
	}

	public void setCodeSecret(String codeSecret) {
		this.codeSecret = codeSecret;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public boolean isLineUser() {
		return lineUser;
	}

	public void setLineUser(boolean lineUser) {
		this.lineUser = lineUser;
	}

	public int getIdFonction() {
		return idFonction;
	}

	public void setIdFonction(int idFonction) {
		this.idFonction = idFonction;
	}

}
