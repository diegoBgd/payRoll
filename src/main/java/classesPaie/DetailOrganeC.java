package classesPaie;

import java.io.Serializable;

public class DetailOrganeC implements Serializable {
	private static final long serialVersionUID = -3869581595451326928L;
	private int id;
	private int idDirection;

	private int idSrvice;
	private int idDepartmt;
	private int idEmpl;
	private String libelleDirection;
	private String libelleDepartement;
	private String libelleService;

	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmpl() {
		return this.idEmpl;
	}

	public void setIdEmpl(int idEmpl) {
		this.idEmpl = idEmpl;
	}

	public int getIdDirection() {
		return idDirection;
	}

	public void setIdDirection(int idDirection) {
		this.idDirection = idDirection;
	}

	public int getIdSrvice() {
		return idSrvice;
	}

	public void setIdSrvice(int idSrvice) {
		this.idSrvice = idSrvice;
	}

	public int getIdDepartmt() {
		return idDepartmt;
	}

	public void setIdDepartmt(int idDepartmt) {
		this.idDepartmt = idDepartmt;
	}

	public String getLibelleDirection() {
		return libelleDirection;
	}

	public void setLibelleDirection(String libelleDirection) {
		this.libelleDirection = libelleDirection;
	}

	public String getLibelleDepartement() {
		return libelleDepartement;
	}

	public void setLibelleDepartement(String libelleDepartement) {
		this.libelleDepartement = libelleDepartement;
	}

	public String getLibelleService() {
		return libelleService;
	}

	public void setLibelleService(String libelleService) {
		this.libelleService = libelleService;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

}
