package classesPaie;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class DetailParametrageHeureSupplementaireC implements Serializable {
	private static final long serialVersionUID = -4528206862627401251L;
	private int id;
	private int index;
	private int numero;
	private Date heureDebut;
	private Date heureFin;
	private String heureDebutS;
	private String heureFinS;
	private String tauxS;
	private double taux;
	private ParametrageHeureSupplementaireC heureSupplementaire;
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

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getHeureDebut() {
		return this.heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getHeureFin() {
		return this.heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}

	public String getHeureDebutS() {
		return this.heureDebutS;
	}

	public void setHeureDebutS(String heureDebutS) {
		this.heureDebutS = heureDebutS;
	}

	public String getHeureFinS() {
		return this.heureFinS;
	}

	public void setHeureFinS(String heureFinS) {
		this.heureFinS = heureFinS;
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

	public ParametrageHeureSupplementaireC getHeureSupplementaire() {
		return this.heureSupplementaire;
	}

	public void setHeureSupplementaire(ParametrageHeureSupplementaireC heureSupplementaire) {
		this.heureSupplementaire = heureSupplementaire;
	}

	public String getTauxS() {
		return this.tauxS;
	}

	public void setTauxS(String tauxS) {
		this.tauxS = tauxS;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
}
