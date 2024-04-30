package classesPaie;

import java.io.Serializable;

public class ParametrageDecideurDetailC implements Serializable {
	private static final long serialVersionUID = -3910350309685521609L;
	private int id;
	private int position;
	private int idEmploye;
	private int idEntete;
	private String nomEmploye;
	private String codeEmploye;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public int getIdEntete() {
		return this.idEntete;
	}

	public void setIdEntete(int idEntete) {
		this.idEntete = idEntete;
	}

	public String getNomEmploye() {
		return this.nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getCodeEmploye() {
		return this.codeEmploye;
	}

	public void setCodeEmploye(String codeEmploye) {
		this.codeEmploye = codeEmploye;
	}
}
