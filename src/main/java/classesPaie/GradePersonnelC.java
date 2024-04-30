package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GradePersonnelC implements Serializable {
	private static final long serialVersionUID = -3487909321953269344L;
	private int id, idGradeInferieur, numOrdre;
	private double traitementMensuel;
	private String traitementMensuelS;
	private String designation;
	private String code;
	private String codeCateg;
	private Historique historique;
	private List<GradePersonnelDetailC> listNiveau = new ArrayList<GradePersonnelDetailC>();
	private List<GradePersonnelDetailC> listNiveauDeleted = new ArrayList<GradePersonnelDetailC>();

	private CategoriePersonnelC categoriePersonnel;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTraitementMensuel() {
		return this.traitementMensuel;
	}

	public void setTraitementMensuel(double traitementMensuel) {
		this.traitementMensuel = traitementMensuel;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public CategoriePersonnelC getCategoriePersonnel() {
		return this.categoriePersonnel;
	}

	public void setCategoriePersonnel(CategoriePersonnelC categoriePersonnel) {
		this.categoriePersonnel = categoriePersonnel;
	}

	public String getTraitementMensuelS() {
		return this.traitementMensuelS;
	}

	public void setTraitementMensuelS(String traitementMensuelS) {
		this.traitementMensuelS = traitementMensuelS;
	}

	public List<GradePersonnelDetailC> getListNiveau() {
		return this.listNiveau;
	}

	public void setListNiveau(List<GradePersonnelDetailC> listNiveau) {
		this.listNiveau = listNiveau;
	}

	public List<GradePersonnelDetailC> getListNiveauDeleted() {
		return this.listNiveauDeleted;
	}

	public void setListNiveauDeleted(List<GradePersonnelDetailC> listNiveauDeleted) {
		this.listNiveauDeleted = listNiveauDeleted;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeCateg() {
		return this.codeCateg;
	}

	public void setCodeCateg(String codeCateg) {
		this.codeCateg = codeCateg;
	}

	public int getIdGradeInferieur() {
		return idGradeInferieur;
	}

	public void setIdGradeInferieur(int idGradeInferieur) {
		this.idGradeInferieur = idGradeInferieur;
	}

}
