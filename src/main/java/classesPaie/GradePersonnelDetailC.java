package classesPaie;

import java.io.Serializable;

public class GradePersonnelDetailC implements Serializable {
	private static final long serialVersionUID = -8499043735773534770L;
	private int id;
	private int age;
	private int index;
	private int mentionUniversite;
	private double tauxBonusSalaire;
	private double salaireMensuel;
	private Historique historique;
	private String tauxBonusSalaireS;
	private boolean derogationConseilAdministration;
	private boolean propositionConseilFaculte;
	private GradePersonnelC grade;
	private Base niveau;
	private boolean existe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTauxBonusSalaire() {
		return this.tauxBonusSalaire;
	}

	public void setTauxBonusSalaire(double tauxBonusSalaire) {
		this.tauxBonusSalaire = tauxBonusSalaire;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public String getTauxBonusSalaireS() {
		return this.tauxBonusSalaireS;
	}

	public void setTauxBonusSalaireS(String tauxBonusSalaireS) {
		this.tauxBonusSalaireS = tauxBonusSalaireS;
	}

	public GradePersonnelC getGrade() {
		return this.grade;
	}

	public void setGrade(GradePersonnelC grade) {
		this.grade = grade;
	}

	public Base getNiveau() {
		return this.niveau;
	}

	public void setNiveau(Base niveau) {
		this.niveau = niveau;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getMentionUniversite() {
		return this.mentionUniversite;
	}

	public void setMentionUniversite(int mentionUniversite) {
		this.mentionUniversite = mentionUniversite;
	}

	public boolean isDerogationConseilAdministration() {
		return this.derogationConseilAdministration;
	}

	public void setDerogationConseilAdministration(boolean derogationConseilAdministration) {
		this.derogationConseilAdministration = derogationConseilAdministration;
	}

	public boolean isPropositionConseilFaculte() {
		return this.propositionConseilFaculte;
	}

	public void setPropositionConseilFaculte(boolean propositionConseilFaculte) {
		this.propositionConseilFaculte = propositionConseilFaculte;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalaireMensuel() {
		return this.salaireMensuel;
	}

	public void setSalaireMensuel(double salaireMensuel) {
		this.salaireMensuel = salaireMensuel;
	}
}
