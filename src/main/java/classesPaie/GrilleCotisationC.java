package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GrilleCotisationC implements Serializable {
	private static final long serialVersionUID = -4133351757643013496L;
	private int id;
	private double coefficient;
	private Date dateDebut;
	private Date dateFin;
	private List<DetailGrilleCotisationC> listDetail = new ArrayList<DetailGrilleCotisationC>();
	private List<DetailGrilleCotisationC> listDeleted = new ArrayList<DetailGrilleCotisationC>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCoefficient() {
		return this.coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
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

	public List<DetailGrilleCotisationC> getListDetail() {
		return this.listDetail;
	}

	public void setListDetail(List<DetailGrilleCotisationC> listDetail) {
		this.listDetail = listDetail;
	}

	public List<DetailGrilleCotisationC> getListDeleted() {
		return this.listDeleted;
	}

	public void setListDeleted(List<DetailGrilleCotisationC> listDeleted) {
		this.listDeleted = listDeleted;
	}
}
