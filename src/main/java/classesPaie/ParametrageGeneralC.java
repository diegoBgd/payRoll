package classesPaie;

import java.io.Serializable;

public class ParametrageGeneralC implements Serializable {
	private static final long serialVersionUID = 3623199434878924818L;
	private int id;
	private int nbreDecimal;
	private int dureCourTerme;
	private int dureLongTerme;
	private int dureMoyenTerme;
	private int nbreHeureMois;
	private int nbreHeureJour;
	private int nbreJourMois;
	private double tauxMaxLogement;
	private double montantNetMin;
	private double tauxJrFerie;
	private boolean allocationBaseHsup;
	private boolean logementBaseHsup;
	private String mailOrigine, pwdOrigine, smtpServer, port;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbreDecimal() {
		return this.nbreDecimal;
	}

	public void setNbreDecimal(int nbreDecimal) {
		this.nbreDecimal = nbreDecimal;
	}

	public double getTauxMaxLogement() {
		return this.tauxMaxLogement;
	}

	public void setTauxMaxLogement(double tauxMaxLogement) {
		this.tauxMaxLogement = tauxMaxLogement;
	}

	public boolean isAllocationBaseHsup() {
		return this.allocationBaseHsup;
	}

	public void setAllocationBaseHsup(boolean allocationBaseHsup) {
		this.allocationBaseHsup = allocationBaseHsup;
	}

	public boolean isLogementBaseHsup() {
		return this.logementBaseHsup;
	}

	public void setLogementBaseHsup(boolean logementBaseHsup) {
		this.logementBaseHsup = logementBaseHsup;
	}

	public int getDureCourTerme() {
		return this.dureCourTerme;
	}

	public void setDureCourTerme(int dureCourTerme) {
		this.dureCourTerme = dureCourTerme;
	}

	public int getDureLongTerme() {
		return this.dureLongTerme;
	}

	public void setDureLongTerme(int dureLongTerme) {
		this.dureLongTerme = dureLongTerme;
	}

	public int getDureMoyenTerme() {
		return this.dureMoyenTerme;
	}

	public void setDureMoyenTerme(int dureMoyenTerme) {
		this.dureMoyenTerme = dureMoyenTerme;
	}

	public double getMontantNetMin() {
		return this.montantNetMin;
	}

	public void setMontantNetMin(double montantNetMin) {
		this.montantNetMin = montantNetMin;
	}

	public int getNbreHeureMois() {
		return this.nbreHeureMois;
	}

	public void setNbreHeureMois(int nbreHeureMois) {
		this.nbreHeureMois = nbreHeureMois;
	}

	public int getNbreHeureJour() {
		return this.nbreHeureJour;
	}

	public void setNbreHeureJour(int nbreHeureJour) {
		this.nbreHeureJour = nbreHeureJour;
	}

	public int getNbreJourMois() {
		return this.nbreJourMois;
	}

	public void setNbreJourMois(int nbreJourMois) {
		this.nbreJourMois = nbreJourMois;
	}

	public double getTauxJrFerie() {
		return this.tauxJrFerie;
	}

	public void setTauxJrFerie(double tauxJrFerie) {
		this.tauxJrFerie = tauxJrFerie;
	}

	public String getMailOrigine() {
		return mailOrigine;
	}

	public void setMailOrigine(String mailOrigine) {
		this.mailOrigine = mailOrigine;
	}

	public String getPwdOrigine() {
		return pwdOrigine;
	}

	public void setPwdOrigine(String pwdOrigine) {
		this.pwdOrigine = pwdOrigine;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
