package classesPaie;

import java.io.Serializable;

public class HistoriqueCongeC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 923170307411327451L;
	private EmployeC employe;
	private String matricule,nomEploye;
	private String printDateDeb,printDateFin;
	private int congeAnnuel,congePris,solde;
	
	public HistoriqueCongeC() {
		
	}

	public EmployeC getEmploye() {
		return employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public String getMatricule() {
		if(employe!=null)
			matricule=employe.getCode();
		return matricule;
	}

	public void setMatricule(String matricule) {
		
		this.matricule = matricule;
	}

	public String getNomEploye() {
		if(employe!=null)
			nomEploye=employe.getNomPrenom();
		return nomEploye;
	}

	public void setNomEploye(String nomEploye) {
		this.nomEploye = nomEploye;
	}

	public String getPrintDateDeb() {
		return printDateDeb;
	}

	public void setPrintDateDeb(String printDateDeb) {
		this.printDateDeb = printDateDeb;
	}

	public String getPrintDateFin() {
		return printDateFin;
	}

	public void setPrintDateFin(String printDateFin) {
		this.printDateFin = printDateFin;
	}

	public int getCongeAnnuel() {
		return congeAnnuel;
	}

	public void setCongeAnnuel(int congeAnnuel) {
		if(employe!=null)
			congeAnnuel=employe.getJourConge();
		this.congeAnnuel = congeAnnuel;
	}

	public int getCongePris() {
		return congePris;
	}

	public void setCongePris(int congePris) {
		this.congePris = congePris;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}
	
}
