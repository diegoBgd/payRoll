package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class SaisieRegimeDisciplinaireC implements Serializable {
	private static final long serialVersionUID = 5527309594808320175L;
	private int id;
	private int sanctionsDisciplinaires;
	private int dureeSanction;
	private int etatSanction;
	private Date dateDebutSanction;
	private Date dateFinSanction;
	private Date dateDecision;
	private Date dateRecours;
	private Date dateSaisie;
	private String dateDebutSanctionS;
	private String dateFinSanctionS;
	private String raisonSanction;
	private String justificationRecours;
	private String libelleSanction;
	private String dateDecisionS;
	private String dateRecoursS;
	private String motifDecision;
	private String dateSaisieS;
	private Historique historique;
	private Constante.SanctionsDisciplinaires sanction;
	private TraitementSalarialC traitement;
	private EmployeC employe;
	private Constante.EtatSanction etatS;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSanctionsDisciplinaires() {
		return this.sanctionsDisciplinaires;
	}

	public void setSanctionsDisciplinaires(int sanctionsDisciplinaires) {
		this.sanctionsDisciplinaires = sanctionsDisciplinaires;
	}

	public int getDureeSanction() {
		return this.dureeSanction;
	}

	public void setDureeSanction(int dureeSanction) {
		this.dureeSanction = dureeSanction;
	}

	public Date getDateDebutSanction() {
		return this.dateDebutSanction;
	}

	public void setDateDebutSanction(Date dateDebutSanction) {
		this.dateDebutSanction = dateDebutSanction;
	}

	public Date getDateFinSanction() {
		return this.dateFinSanction;
	}

	public void setDateFinSanction(Date dateFinSanction) {
		this.dateFinSanction = dateFinSanction;
	}

	public String getDateDebutSanctionS() {
		return this.dateDebutSanctionS;
	}

	public void setDateDebutSanctionS(String dateDebutSanctionS) {
		this.dateDebutSanctionS = dateDebutSanctionS;
	}

	public String getDateFinSanctionS() {
		return this.dateFinSanctionS;
	}

	public void setDateFinSanctionS(String dateFinSanctionS) {
		this.dateFinSanctionS = dateFinSanctionS;
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

	public Constante.SanctionsDisciplinaires getSanction() {
		return this.sanction;
	}

	public void setSanction(Constante.SanctionsDisciplinaires sanction) {
		this.sanction = sanction;
	}

	public String getLibelleSanction() {
		return this.libelleSanction;
	}

	public void setLibelleSanction(String libelleSanction) {
		this.libelleSanction = libelleSanction;
	}

	public String getRaisonSanction() {
		return this.raisonSanction;
	}

	public void setRaisonSanction(String raisonSanction) {
		this.raisonSanction = raisonSanction;
	}

	public int getEtatSanction() {
		return this.etatSanction;
	}

	public void setEtatSanction(int etatSanction) {
		this.etatSanction = etatSanction;
	}

	public Date getDateDecision() {
		return this.dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	public Date getDateRecours() {
		return this.dateRecours;
	}

	public void setDateRecours(Date dateRecours) {
		this.dateRecours = dateRecours;
	}

	public String getDateDecisionS() {
		return this.dateDecisionS;
	}

	public void setDateDecisionS(String dateDecisionS) {
		this.dateDecisionS = dateDecisionS;
	}

	public String getDateRecoursS() {
		return this.dateRecoursS;
	}

	public void setDateRecoursS(String dateRecoursS) {
		this.dateRecoursS = dateRecoursS;
	}

	public String getJustificationRecours() {
		return this.justificationRecours;
	}

	public void setJustificationRecours(String justificationRecours) {
		this.justificationRecours = justificationRecours;
	}

	public String getMotifDecision() {
		return this.motifDecision;
	}

	public void setMotifDecision(String motifDecision) {
		this.motifDecision = motifDecision;
	}

	public Constante.EtatSanction getEtatS() {
		return this.etatS;
	}

	public void setEtatS(Constante.EtatSanction etatS) {
		this.etatS = etatS;
	}

	public Date getDateSaisie() {
		return this.dateSaisie;
	}

	public void setDateSaisie(Date dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public String getDateSaisieS() {
		return this.dateSaisieS;
	}

	public void setDateSaisieS(String dateSaisieS) {
		this.dateSaisieS = dateSaisieS;
	}

	public TraitementSalarialC getTraitement() {
		return this.traitement;
	}

	public void setTraitement(TraitementSalarialC traitement) {
		this.traitement = traitement;
	}
}
