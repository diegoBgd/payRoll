package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParametreCotisationC implements Serializable {
	private List<CotisationDetailC> listDeleted = new ArrayList<CotisationDetailC>();
	private List<CotisationDetailC> listDetail = new ArrayList<CotisationDetailC>();
	private Date dateFinUtilisation;
	private double baseFixe;
	private double plancherBase;
	private double plafondBase;
	private double forfaitPatronal;
	private double forfaitSalarial;
	private double tauxPatronal;
	private double tauxSalarial;
	private double plafonPatronal;
	private double plafonSalarial;
	private double tauxPlafonSalarial, tauxPlafonPatronal;
	private double plancherPatronal;
	private double plancherSalarial;
	private String libelle;
	private String code;
	private int priorite;
	private int typeBasePatronal;
	private int typeBaseSalarial;
	private int idCotisation;
	private int id;
	private static final long serialVersionUID = 2721587510844306357L;

	public List<CotisationDetailC> getListDetail() {
		return this.listDetail;
	}

	public void setListDetail(List<CotisationDetailC> listDetail) {
		this.listDetail = listDetail;
	}

	public List<CotisationDetailC> getListDeleted() {
		return this.listDeleted;
	}

	public void setListDeleted(List<CotisationDetailC> listDeleted) {
		this.listDeleted = listDeleted;
	}

	public Date getDateFinUtilisation() {
		return this.dateFinUtilisation;
	}

	public void setDateFinUtilisation(Date dateFinUtilisation) {
		this.dateFinUtilisation = dateFinUtilisation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCotisation() {
		return this.idCotisation;
	}

	public void setIdCotisation(int idCotisation) {
		this.idCotisation = idCotisation;
	}

	public double getTauxSalarial() {
		return this.tauxSalarial;
	}

	public void setTauxSalarial(double tauxSalarial) {
		this.tauxSalarial = tauxSalarial;
	}

	public double getTauxPatronal() {
		return this.tauxPatronal;
	}

	public void setTauxPatronal(double tauxPatronal) {
		this.tauxPatronal = tauxPatronal;
	}

	public double getForfaitSalarial() {
		return this.forfaitSalarial;
	}

	public void setForfaitSalarial(double forfaitSalarial) {
		this.forfaitSalarial = forfaitSalarial;
	}

	public double getForfaitPatronal() {
		return this.forfaitPatronal;
	}

	public void setForfaitPatronal(double forfaitPatronal) {
		this.forfaitPatronal = forfaitPatronal;
	}

	public double getPlancherSalarial() {
		return this.plancherSalarial;
	}

	public void setPlancherSalarial(double plancherSalarial) {
		this.plancherSalarial = plancherSalarial;
	}

	public double getPlancherPatronal() {
		return this.plancherPatronal;
	}

	public void setPlancherPatronal(double plancherPatronal) {
		this.plancherPatronal = plancherPatronal;
	}

	public double getPlafonSalarial() {
		return this.plafonSalarial;
	}

	public void setPlafonSalarial(double plafonSalarial) {
		this.plafonSalarial = plafonSalarial;
	}

	public double getPlafonPatronal() {
		return this.plafonPatronal;
	}

	public void setPlafonPatronal(double plafonPatronal) {
		this.plafonPatronal = plafonPatronal;
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

	public int getTypeBaseSalarial() {
		return this.typeBaseSalarial;
	}

	public void setTypeBaseSalarial(int typeBaseSalarial) {
		this.typeBaseSalarial = typeBaseSalarial;
	}

	public int getTypeBasePatronal() {
		return this.typeBasePatronal;
	}

	public void setTypeBasePatronal(int typeBasePatronal) {
		this.typeBasePatronal = typeBasePatronal;
	}

	public int getPriorite() {
		return this.priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	public double getPlafondBase() {
		return this.plafondBase;
	}

	public void setPlafondBase(double plafondBase) {
		this.plafondBase = plafondBase;
	}

	public double getPlancherBase() {
		return this.plancherBase;
	}

	public void setPlancherBase(double plancherBase) {
		this.plancherBase = plancherBase;
	}

	public double getBaseFixe() {
		return this.baseFixe;
	}

	public void setBaseFixe(double baseFixe) {
		this.baseFixe = baseFixe;
	}

	public double getTauxPlafonSalarial() {
		return tauxPlafonSalarial;
	}

	public void setTauxPlafonSalarial(double tauxPlafonSalarial) {
		this.tauxPlafonSalarial = tauxPlafonSalarial;
	}

	public double getTauxPlafonPatronal() {
		return tauxPlafonPatronal;
	}

	public void setTauxPlafonPatronal(double tauxPlafonPatronal) {
		this.tauxPlafonPatronal = tauxPlafonPatronal;
	}

	public void changePriority() {
		if (getPriorite() > 0)
			setPriorite(priorite);
		System.out.println(getPriorite());
	}

}
