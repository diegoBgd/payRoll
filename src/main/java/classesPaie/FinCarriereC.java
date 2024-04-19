/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class FinCarriereC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8522503876419276120L;
/*     */   private int id,dureSalaireTotal;
/*     */   private int age;
/*     */   private int typeRetraite;
/*     */   private Date dateRetraite,dateSalaire;
/*     */   private Date dateFin;
/*     */   private Historique historique;
/*     */   private String dateEvenementS;
/*     */   private String dateFinS,printDateRetraite;
/*     */   private double montant,montantApres;
/*     */   private TraitementSalarialC traitementAvant,traitementApres;
/*     */   private PrimeIndemniteC prime;
			private EmployeC employe; 



/*  34 */   private List<FinCarriereDetailPrimeC> listeDetailPrime;
/*     */   
/*     */   private List<FinCarriereDetailPrimeC> listeDetailPrimeDeleted;
/*     */   
/*     */   
/*     */   
			public FinCarriereC(){
				listeDetailPrime=new ArrayList<FinCarriereDetailPrimeC>();
				listeDetailPrimeDeleted=new ArrayList<FinCarriereDetailPrimeC>();
			}
			
			public int getId() {
		     return this.id;
			}
/*     */   public void setId(int id) {
/*  43 */     this.id = id;
/*     */   }
/*     */   

/*     */   public int getAge() {
/*  55 */     return this.age;
/*     */   }
/*     */   
/*     */   public void setAge(int age) {
/*  59 */     this.age = age;
/*     */   }
/*     */   

/*     */   public Date getDateFin() {
/*  71 */     return this.dateFin;
/*     */   }
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/*  75 */     this.dateFin = dateFin;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/*  79 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  83 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public String getDateEvenementS() {
/*  87 */     return this.dateEvenementS;
/*     */   }
/*     */   
/*     */   public void setDateEvenementS(String dateEvenementS) {
/*  91 */     this.dateEvenementS = dateEvenementS;
/*     */   }
/*     */   
/*     */   public String getDateFinS() {
/*  95 */     return this.dateFinS;
/*     */   }
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/*  99 */     this.dateFinS = dateFinS;
/*     */   }
/*     */   
/*     */   public EmployeC getEmploye() {
/* 103 */     return this.employe;
/*     */   }
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 107 */     this.employe = employe;
/*     */   }
/*     */   
 
/*     */   public int getTypeRetraite() {
/* 119 */     return this.typeRetraite;
/*     */   }
/*     */   
/*     */   public void setTypeRetraite(int typeRetraite) {
/* 123 */     this.typeRetraite = typeRetraite;
/*     */   }



/*     */   public PrimeIndemniteC getPrime() {
/* 177 */     return this.prime;
/*     */   }
/*     */   
/*     */   public void setPrime(PrimeIndemniteC prime) {
/* 181 */     this.prime = prime;
/*     */   }
/*     */   

/*     */   public List<FinCarriereDetailPrimeC> getListeDetailPrime() {
/* 227 */     return this.listeDetailPrime;
/*     */   }
/*     */   
/*     */   public void setListeDetailPrime(List<FinCarriereDetailPrimeC> listeDetailPrime) {
/* 231 */     this.listeDetailPrime = listeDetailPrime;
/*     */   }
/*     */   
/*     */   public List<FinCarriereDetailPrimeC> getListeDetailPrimeDeleted() {
/* 235 */     return this.listeDetailPrimeDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListeDetailPrimeDeleted(List<FinCarriereDetailPrimeC> listeDetailPrimeDeleted) {
/* 240 */     this.listeDetailPrimeDeleted = listeDetailPrimeDeleted;
/*     */   }
public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}
public double getMontantApres() {
	return montantApres;
}
public void setMontantApres(double montantApres) {
	this.montantApres = montantApres;
}

public int getDureSalaireTotal() {
	return dureSalaireTotal;
}

public void setDureSalaireTotal(int dureSalaireTotal) {
	this.dureSalaireTotal = dureSalaireTotal;
}

public String getPrintDateRetraite() {
	return printDateRetraite;
}

public void setPrintDateRetraite(String printDateRetraite) {
	this.printDateRetraite = printDateRetraite;
}

public Date getDateRetraite() {
	return dateRetraite;
}

public void setDateRetraite(Date dateRetraite) {
	this.dateRetraite = dateRetraite;
}

public TraitementSalarialC getTraitementAvant() {
	return traitementAvant;
}

public void setTraitementAvant(TraitementSalarialC traitementAvant) {
	this.traitementAvant = traitementAvant;
}

public TraitementSalarialC getTraitementApres() {
	return traitementApres;
}

public void setTraitementApres(TraitementSalarialC traitementApres) {
	this.traitementApres = traitementApres;
}

public Date getDateSalaire() {
	return dateSalaire;
}

public void setDateSalaire(Date dateSalaire) {
	this.dateSalaire = dateSalaire;
}


}


