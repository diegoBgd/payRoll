/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;

/*     */ public class SaisieSanctionC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7740330472394813414L;
/*     */   private int id,idSanction,etat,idOperateur,moisPaie,idExercice,decision;
			private double montantRetenu,tauxRetenu,salaireBase;
			private Date dateDebutSanction,dateFinSanction,dateRecours,dateDecision,dateSaisie;
			private String motifSanction,motifRecours,motifDecision,printDateSaisie,
						   libelleSanction,moisLettre,libelleDecision;
/*     */   private EmployeC employe;
/*     */   private Historique historique;
			private boolean cloture,inLine;
			private  ParametrageSanctionC prm;
  public int getId() {
    return this.id;
   }
  
   public void setId(int id) {
     this.id = id;
   }

  public EmployeC getEmploye() {
    return this.employe;
  }

public int getEtat() {
	return etat;
}
public void setEtat(int etat) {
	this.etat = etat;
}
public int getIdOperateur() {
	return idOperateur;
}
public void setIdOperateur(int idOperateur) {
	this.idOperateur = idOperateur;
}
public int getMoisPaie() {
	return moisPaie;
}
public void setMoisPaie(int moisPaie) {
	this.moisPaie = moisPaie;
}
public int getIdExercice() {
	return idExercice;
}
public void setIdExercice(int idExercice) {
	this.idExercice = idExercice;
}
public int getDecision() {
	return decision;
}
public void setDecision(int decision) {
	this.decision = decision;
}
public double getMontantRetenu() {
	return montantRetenu;
}
public void setMontantRetenu(double montantRetenu) {
	this.montantRetenu = montantRetenu;
}
public double getTauxRetenu() {
	return tauxRetenu;
}
public void setTauxRetenu(double tauxRetenu) {
	this.tauxRetenu = tauxRetenu;
}
public double getSalaireBase() {
	return salaireBase;
}
public void setSalaireBase(double salaireBase) {
	this.salaireBase = salaireBase;
}
public Date getDateDebutSanction() {
	return dateDebutSanction;
}
public void setDateDebutSanction(Date dateDebutSanction) {
	this.dateDebutSanction = dateDebutSanction;
}
public Date getDateFinSanction() {
	return dateFinSanction;
}
public void setDateFinSanction(Date dateFinSanction) {
	this.dateFinSanction = dateFinSanction;
}
public Date getDateRecours() {
	return dateRecours;
}
public void setDateRecours(Date dateRecours) {
	this.dateRecours = dateRecours;
}
public Date getDateDecision() {
	return dateDecision;
}
public void setDateDecision(Date dateDecision) {
	this.dateDecision = dateDecision;
}
public Date getDateSaisie() {
	return dateSaisie;
}
public void setDateSaisie(Date dateSaisie) {
	this.dateSaisie = dateSaisie;
}
public String getMotifSanction() {
	return motifSanction;
}
public void setMotifSanction(String motifSanction) {
	this.motifSanction = motifSanction;
}
public String getMotifRecours() {
	return motifRecours;
}
public void setMotifRecours(String motifRecours) {
	this.motifRecours = motifRecours;
}
public String getMotifDecision() {
	return motifDecision;
}
public void setMotifDecision(String motifDecision) {
	this.motifDecision = motifDecision;
}
public boolean isCloture() {
	return cloture;
}
public void setCloture(boolean cloture) {
	this.cloture = cloture;
}
public void setEmploye(EmployeC employe) {
    this.employe = employe;
 }
public Historique getHistorique() {
    return this.historique;
  }
public void setHistorique(Historique historique) {
    this.historique = historique;
}

public String getPrintDateSaisie() {
	printDateSaisie=HelperC.convertDate(getDateSaisie());
	return printDateSaisie;
}

public void setPrintDateSaisie(String printDateSaisie) {
	this.printDateSaisie = printDateSaisie;
}

public void setLibelleSanction(String libelleSanction) {
	this.libelleSanction = libelleSanction;
}

public String getLibelleSanction() {
	return libelleSanction;
}

public String getLibelleDecision() {
	return libelleDecision;
}

public String getMoisLettre() {
	return moisLettre;
}

public void setMoisLettre(String moisLettre) {
	this.moisLettre = moisLettre;
}

public boolean isInLine() {
	return inLine;
}

public void setInLine(boolean inLine) {
	this.inLine = inLine;
}
public void setLibelleDecision(String libelleDecision) {
	this.libelleDecision = libelleDecision;
}

public int getIdSanction() {
	return idSanction;
}

public void setIdSanction(int idSanction) {
	this.idSanction = idSanction;
}

public ParametrageSanctionC getPrm() {
	return prm;
}

public void setPrm(ParametrageSanctionC prm) {
	this.prm = prm;
}

 }

