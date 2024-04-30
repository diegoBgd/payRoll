package classesPaie;

import java.io.Serializable;
import java.util.Date;

import classesPaie.Tables.TableName;
import persistencePaie.FichierBaseDAO;

public class DemandeRetraiteAnticipeC implements Serializable {
	private static final long serialVersionUID = -5276985280251846906L;
	private int id;
	private int anciennette;
	private int etat;
	private int idMotifDemande;
	private String dateDemandeS;
	private String dateDebutRetraiteS;
	private String dateDecisionS;
	private String libelleDemandeRetraite;
	private String libelleDecision;

	private Date dateDemande;
	private Date dateDebutRetraite;
	private Date dateDecision;
	private int decision;
	private Historique historique;
	private EmployeC employe;
	private boolean inLine;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnciennette() {
		return this.anciennette;
	}

	public void setAnciennette(int anciennette) {
		this.anciennette = anciennette;
	}

	public String getDateDemandeS() {
		return this.dateDemandeS;
	}

	public int getIdMotifDemande() {
		return idMotifDemande;
	}

	public void setIdMotifDemande(int idMotifDemande) {
		this.idMotifDemande = idMotifDemande;
	}

	public void setDateDemandeS(String dateDemandeS) {
		this.dateDemandeS = dateDemandeS;
	}

	public String getDateDebutRetraiteS() {
		return this.dateDebutRetraiteS;
	}

	public void setDateDebutRetraiteS(String dateDebutRetraiteS) {
		this.dateDebutRetraiteS = dateDebutRetraiteS;
	}

	public String getDateDecisionS() {
		return this.dateDecisionS;
	}

	public void setDateDecisionS(String dateDecisionS) {
		this.dateDecisionS = dateDecisionS;
	}

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateDebutRetraite() {
		return this.dateDebutRetraite;
	}

	public void setDateDebutRetraite(Date dateDebutRetraite) {
		this.dateDebutRetraite = dateDebutRetraite;
	}

	public Date getDateDecision() {
		return this.dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	public int getDecision() {
		return this.decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public EmployeC getEmploye() {
		return this.employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public String getLibelleDemandeRetraite() {
		libelleDemandeRetraite = "";
		if (getIdMotifDemande() > 0)
			libelleDemandeRetraite = FichierBaseDAO.getInstance()
					.getBaseById(getIdMotifDemande(), Tables.getTableName(TableName.motifRetraite)).getDesignation();
		return this.libelleDemandeRetraite;
	}

	public void setLibelleDemandeRetraite(String libelleDemandeRetraite) {
		this.libelleDemandeRetraite = libelleDemandeRetraite;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getLibelleDecision() {
		switch (decision) {
		case 1:
			libelleDecision = "Accepté";
			break;

		case 2:
			libelleDecision = "Refusé";
			break;
		}
		return this.libelleDecision;
	}

	public void setLibelleDecision(String libelleDecision) {
		this.libelleDecision = libelleDecision;
	}

	public boolean isInLine() {
		return inLine;
	}

	public void setInLine(boolean inLine) {
		this.inLine = inLine;
	}

}
