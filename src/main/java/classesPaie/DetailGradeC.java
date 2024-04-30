package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class DetailGradeC implements Serializable {
	private static final long serialVersionUID = 9171738236007346077L;
	private int id, idEmpl, idNvGrd, idAncGrd, idRef, typeAvancement;

	private String comment;

	private Date dateDebut;
	private Date dateFin;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRef() {
		return idRef;
	}

	public void setIdRef(int idRef) {
		this.idRef = idRef;
	}

	public int getIdEmpl() {
		return idEmpl;
	}

	public void setIdEmpl(int idEmpl) {
		this.idEmpl = idEmpl;
	}

	public int getIdNvGrd() {
		return idNvGrd;
	}

	public void setIdNvGrd(int idNvGrd) {
		this.idNvGrd = idNvGrd;
	}

	public int getIdAncGrd() {
		return idAncGrd;
	}

	public void setIdAncGrd(int idAncGrd) {
		this.idAncGrd = idAncGrd;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getTypeAvancement() {
		return typeAvancement;
	}

	public void setTypeAvancement(int typeAvancement) {
		this.typeAvancement = typeAvancement;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
}
