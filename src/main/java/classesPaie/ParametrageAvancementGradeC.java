package classesPaie;

import java.io.Serializable;

public class ParametrageAvancementGradeC implements Serializable {
	private static final long serialVersionUID = 5955828157042261264L;
	private int id;
	private int nombreDeFoisNotation;
	private CategoriePersonnelC categorie;
	private Base typeAppreciation;
	private Base personnel;
	private Base typeNotation;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNombreDeFoisNotation() {
		return this.nombreDeFoisNotation;
	}

	public void setNombreDeFoisNotation(int nombreDeFoisNotation) {
		this.nombreDeFoisNotation = nombreDeFoisNotation;
	}

	public Base getTypeAppreciation() {
		return this.typeAppreciation;
	}

	public void setTypeAppreciation(Base typeAppreciation) {
		this.typeAppreciation = typeAppreciation;
	}

	public Base getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Base personnel) {
		this.personnel = personnel;
	}

	public CategoriePersonnelC getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePersonnelC categorie) {
		this.categorie = categorie;
	}

	public Base getTypeNotation() {
		return typeNotation;
	}

	public void setTypeNotation(Base typeNotation) {
		this.typeNotation = typeNotation;
	}

}
