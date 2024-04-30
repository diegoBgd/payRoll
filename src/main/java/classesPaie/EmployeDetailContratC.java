package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class EmployeDetailContratC implements Serializable {
	private static final long serialVersionUID = 7331261073067736421L;
	private int id;
	private Date dateDebutContrat;
	private Date dateFinContrat;
	private Historique historique;
	private EmployeC employe;
	private Base contrat;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebutContrat() {
		return this.dateDebutContrat;
	}

	public void setDateDebutContrat(Date dateDebutContrat) {
		this.dateDebutContrat = dateDebutContrat;
	}

	public Date getDateFinContrat() {
		return this.dateFinContrat;
	}

	public void setDateFinContrat(Date dateFinContrat) {
		this.dateFinContrat = dateFinContrat;
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

	public Base getContrat() {
		return this.contrat;
	}

	public void setContrat(Base contrat) {
		this.contrat = contrat;
	}
}
