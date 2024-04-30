package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaremeIPRC implements Serializable {
	private static final long serialVersionUID = -2757212180809734906L;
	private int id;
	private int typeEmploye;
	private int type;
	private String numero;
	private String description;
	private String formule;
	private Date date;
	private double pensionComplementaire;
	private double transportBrut;
	private double montantPersonneCharge;
	private double pourcentagePersonneCharge;
	private double logementNonImposable;
	private boolean actif;
	private MarcheProgrammeC marche;
	private Historique historique;
	private List<DetailBaremeC> listDetailBareme = new ArrayList<DetailBaremeC>();
	private List<DetailBaremeC> listDetailBaremeDeleted = new ArrayList<DetailBaremeC>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeEmploye() {
		return this.typeEmploye;
	}

	public void setTypeEmploye(int typeEmploye) {
		this.typeEmploye = typeEmploye;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MarcheProgrammeC getMarche() {
		return this.marche;
	}

	public void setMarche(MarcheProgrammeC marche) {
		this.marche = marche;
	}

	public List<DetailBaremeC> getListDetailBareme() {
		return this.listDetailBareme;
	}

	public void setListDetailBareme(List<DetailBaremeC> listDetailBareme) {
		this.listDetailBareme = listDetailBareme;
	}

	public List<DetailBaremeC> getListDetailBaremeDeleted() {
		return this.listDetailBaremeDeleted;
	}

	public void setListDetailBaremeDeleted(List<DetailBaremeC> listDetailBaremeDeleted) {
		this.listDetailBaremeDeleted = listDetailBaremeDeleted;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public double getPensionComplementaire() {
		return this.pensionComplementaire;
	}

	public void setPensionComplementaire(double pensionComplementaire) {
		this.pensionComplementaire = pensionComplementaire;
	}

	public double getTransportBrut() {
		return this.transportBrut;
	}

	public void setTransportBrut(double transportBrut) {
		this.transportBrut = transportBrut;
	}

	public double getPourcentagePersonneCharge() {
		return this.pourcentagePersonneCharge;
	}

	public void setPourcentagePersonneCharge(double pourcentagePersonneCharge) {
		this.pourcentagePersonneCharge = pourcentagePersonneCharge;
	}

	public double getLogementNonImposable() {
		return this.logementNonImposable;
	}

	public void setLogementNonImposable(double logementNonImposable) {
		this.logementNonImposable = logementNonImposable;
	}

	public double getMontantPersonneCharge() {
		return this.montantPersonneCharge;
	}

	public void setMontantPersonneCharge(double montantPersonneCharge) {
		this.montantPersonneCharge = montantPersonneCharge;
	}

	public String getFormule() {
		return this.formule;
	}

	public void setFormule(String formule) {
		this.formule = formule;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

}
