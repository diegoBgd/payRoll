
package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class DemandeAvancementGradeC implements Serializable {
	private static final long serialVersionUID = 6020445107811640258L;
	private int id;
	private int etat;
	private Date dateDemande;
	private Historique historique;
	private String dateDemandeS;
	private String libelleEtat;
	private String motif;
	private Constante.EtatDemandeAvancementGrade etatDemande;
	private EmployeC employe;
	private GradePersonnelC gradeDemande;
	private Base niveauFormation;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
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

	public GradePersonnelC getGradeDemande() {
		return this.gradeDemande;
	}

	public void setGradeDemande(GradePersonnelC gradeDemande) {
		this.gradeDemande = gradeDemande;
	}

	public String getDateDemandeS() {
		return this.dateDemandeS;
	}

	public void setDateDemandeS(String dateDemandeS) {
		this.dateDemandeS = dateDemandeS;
	}

	public String getLibelleEtat() {
		return this.libelleEtat;
	}

	public void setLibelleEtat(String libelleEtat) {
		this.libelleEtat = libelleEtat;
	}

	public Constante.EtatDemandeAvancementGrade getEtatDemande() {
		return this.etatDemande;
	}

	public void setEtatDemande(Constante.EtatDemandeAvancementGrade etatDemande) {
		this.etatDemande = etatDemande;
	}

	public Base getNiveauFormation() {
		return this.niveauFormation;
	}

	public void setNiveauFormation(Base niveauFormation) {
		this.niveauFormation = niveauFormation;
	}

	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
}
