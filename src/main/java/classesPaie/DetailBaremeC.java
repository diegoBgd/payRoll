package classesPaie;

import java.io.Serializable;

public class DetailBaremeC implements Serializable {
	private static final long serialVersionUID = -2207440842855727449L;
	private int id;
	private int index;
	private int numero;
	private double borneDebut;
	private double borneFin;
	private double pourcentage;
	private double sommeRetranche;
	private double cotisation;
	private String borneDebutString;
	private String borneFinString;
	private String pourcentageString;
	private String sommeRetrancheString;
	private BaremeIPRC bareme;
	private boolean existe;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getBorneDebut() {
		return this.borneDebut;
	}

	public void setBorneDebut(double borneDebut) {
		this.borneDebut = borneDebut;
	}

	public double getBorneFin() {
		return this.borneFin;
	}

	public void setBorneFin(double borneFin) {
		this.borneFin = borneFin;
	}

	public double getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public double getSommeRetranche() {
		return this.sommeRetranche;
	}

	public void setSommeRetranche(double sommeRetranche) {
		this.sommeRetranche = sommeRetranche;
	}

	public BaremeIPRC getBareme() {
		return this.bareme;
	}

	public void setBareme(BaremeIPRC bareme) {
		this.bareme = bareme;
	}

	public String getBorneDebutString() {
		return this.borneDebutString;
	}

	public void setBorneDebutString(String borneDebutString) {
		this.borneDebutString = borneDebutString;
	}

	public String getBorneFinString() {
		return this.borneFinString;
	}

	public void setBorneFinString(String borneFinString) {
		this.borneFinString = borneFinString;
	}

	public String getPourcentageString() {
		return this.pourcentageString;
	}

	public void setPourcentageString(String pourcentageString) {
		this.pourcentageString = pourcentageString;
	}

	public String getSommeRetrancheString() {
		return this.sommeRetrancheString;
	}

	public void setSommeRetrancheString(String sommeRetrancheString) {
		this.sommeRetrancheString = sommeRetrancheString;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public double getCotisation() {
		return this.cotisation;
	}

	public void setCotisation(double cotisation) {
		this.cotisation = cotisation;
	}
}
