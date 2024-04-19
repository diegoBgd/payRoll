/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DemandeProlongationRetraiteC;
/*     */ import classesPaie.DemandeRetraiteAnticipeC;
/*     */ import classesPaie.DetailPrimeEmployeC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.FinCarriereC;
/*     */ import classesPaie.FinCarriereDetailIndemniteC;
/*     */ import classesPaie.FinCarriereDetailPrimeC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageFinCarriereC;
/*     */ import classesPaie.Tables;
/*     */ import classesPaie.TraitementSalarialC;

/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
import java.util.Date;
/*     */ import java.util.List;

/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;

/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;

/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class FinCarriereB
/*     */   extends FinCarriereC
/*     */ {
/*     */   private static final long serialVersionUID = -2571783497005994158L;
/*     */   private int idMotif;
/*     */   private int idPrime;
/*     */   private int idIndemnite;
/*  54 */   private String montantPrimeS = "0.0"; 
            private String montantIndemniteS = "0.0"; 
            private double montantPrime;
/*     */   private double montantIndemnite;
/*     */   private FinCarriereC selected;
/*     */   private EmployeC selection;
/*  58 */   private List<FinCarriereC> allFinCarriere = new ArrayList<FinCarriereC>();
/*  59 */   private List<SelectItem> listPrime = new ArrayList<SelectItem>();
/*  60 */   private List<SelectItem> listIndemnite = new ArrayList<SelectItem>(); 
            ParametrageFinCarriereC parametre;
/*     */   private String code;
/*     */   private String codeRecherche;
/*     */   private String nomRecherche;
/*  64 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>(); 
            private String nom;
            private String codeEmployeRecherche; 
            private String nomEmployeRecherche; 
            private String prenomEmployeRecherche;
/*     */   private Constante.TypeAvancement typeAvancement;
/*     */   private FinCarriereDetailPrimeC selectedDetailPrime;
/*     */   private FinCarriereDetailIndemniteC selectedDetailIndemnite;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DemandeRetraiteAnticipeC anticipe;
/*     */   private List<EmployeC> listEmployeRetraite,listRetraite;
/*     */   List<DemandeRetraiteAnticipeC> listRetraiteAnticipe;
/*     */   List<ParametrageFinCarriereC> listParametre;
/*  74 */   private HttpSession session = HelperC.getSession();
/*     */ 	private String personel,categorie,grade,niveauFrm,fonction;
/*     */ 	private String printDate,printDateSalaire;
/*     */   private int typeR;
/*     */   private Base personnel;
/*     */ 	private List<FinCarriereC>listeFinCarriere;
/*     */   TraitementSalarialC traitementAv,traitementAp;
/*     */   double tauxSalaire = 0.0D, salaireBase = 0.0D, ancienSalaire = 0.0D;
/*     */   
/*     */   public DemandeRetraiteAnticipeC getAnticipe() {
/*  84 */     return this.anticipe;
/*     */   }
/*     */   
/*     */   public void setAnticipe(DemandeRetraiteAnticipeC anticipe) {
/*  88 */     this.anticipe = anticipe;
/*     */   }
/*     */   
/*     */   public List<ParametrageFinCarriereC> getListParametre() {
/*  92 */     return this.listParametre;
/*     */   }
/*     */   
/*     */   public void setListParametre(List<ParametrageFinCarriereC> listParametre) {
/*  96 */     this.listParametre = listParametre;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmployeRetraite() {
/* 100 */     return this.listEmployeRetraite;
/*     */   }
/*     */   
/*     */   public void setListEmployeRetraite(List<EmployeC> listEmployeRetraite) {
/* 104 */     this.listEmployeRetraite = listEmployeRetraite;
/*     */   }
/*     */   
/*     */   public List<DemandeRetraiteAnticipeC> getListRetraiteAnticipe() {
/* 108 */     return this.listRetraiteAnticipe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListRetraiteAnticipe(List<DemandeRetraiteAnticipeC> listRetraiteAnticipe) {
/* 113 */     this.listRetraiteAnticipe = listRetraiteAnticipe;
/*     */   }
/*     */   
/*     */   public FinCarriereC getSelected() {
/* 117 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(FinCarriereC selected) {
/* 121 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public EmployeC getSelection() {
/* 125 */     return this.selection;
/*     */   }
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/* 129 */     this.selection = selection;
/*     */   }
/*     */   
/*     */   public List<FinCarriereC> getAllFinCarriere() {
/* 133 */     return this.allFinCarriere;
/*     */   }
/*     */   
/*     */   public void setAllFinCarriere(List<FinCarriereC> allFinCarriere) {
/* 137 */     this.allFinCarriere = allFinCarriere;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 141 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 145 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getCodeRecherche() {
/* 149 */     return this.codeRecherche;
/*     */   }
/*     */   
/*     */   public void setCodeRecherche(String codeRecherche) {
/* 153 */     this.codeRecherche = codeRecherche;
/*     */   }
/*     */   
/*     */   public String getNomRecherche() {
/* 157 */     return this.nomRecherche;
/*     */   }
/*     */   
/*     */   public void setNomRecherche(String nomRecherche) {
/* 161 */     this.nomRecherche = nomRecherche;
/*     */   }
/*     */   
/*     */   public String getNom() {
/* 165 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/* 169 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 173 */     return this.codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 177 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 181 */     return this.nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 185 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 189 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 193 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/* 197 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/* 201 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/* 205 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 209 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/* 213 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 217 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/* 221 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 225 */     this.session = session;
/*     */   }
/*     */   
/*     */   public int getIdMotif() {
/* 229 */     return this.idMotif;
/*     */   }
/*     */   
/*     */   public void setIdMotif(int idMotif) {
/* 233 */     this.idMotif = idMotif;
/*     */   }
/*     */   
/*     */   public int getIdPrime() {
/* 237 */     return this.idPrime;
/*     */   }
/*     */   
/*     */   public void setIdPrime(int idPrime) {
/* 241 */     this.idPrime = idPrime;
/*     */   }
/*     */   
/*     */   public int getIdIndemnite() {
/* 245 */     return this.idIndemnite;
/*     */   }
/*     */   
/*     */   public void setIdIndemnite(int idIndemnite) {
/* 249 */     this.idIndemnite = idIndemnite;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListPrime() {
/* 253 */     return this.listPrime;
/*     */   }
/*     */   
/*     */   public void setListPrime(List<SelectItem> listPrime) {
/* 257 */     this.listPrime = listPrime;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListIndemnite() {
/* 261 */     return this.listIndemnite;
/*     */   }
/*     */   
/*     */   public void setListIndemnite(List<SelectItem> listIndemnite) {
/* 265 */     this.listIndemnite = listIndemnite;
/*     */   }
/*     */   

/*     */   public Constante.TypeAvancement getTypeAvancement() {
/* 277 */     return this.typeAvancement;
/*     */   }
/*     */   
/*     */   public void setTypeAvancement(Constante.TypeAvancement typeAvancement) {
/* 281 */     this.typeAvancement = typeAvancement;
/*     */   }
/*     */   
/*     */   public FinCarriereDetailPrimeC getSelectedDetailPrime() {
/* 285 */     return this.selectedDetailPrime;
/*     */   }
/*     */   
/*     */   public void setSelectedDetailPrime(FinCarriereDetailPrimeC selectedDetailPrime) {
/* 289 */     this.selectedDetailPrime = selectedDetailPrime;
/*     */   }
/*     */   
/*     */   public FinCarriereDetailIndemniteC getSelectedDetailIndemnite() {
/* 293 */     return this.selectedDetailIndemnite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedDetailIndemnite(FinCarriereDetailIndemniteC selectedDetailIndemnite) {
/* 298 */     this.selectedDetailIndemnite = selectedDetailIndemnite;
/*     */   }
/*     */   
/*     */   public void setMontantPrime(double montantPrime) {
/* 302 */     this.montantPrime = montantPrime;
/*     */   }
/*     */   
/*     */   public void setMontantIndemnite(double montantIndemnite) {
/* 306 */     this.montantIndemnite = montantIndemnite;
/*     */   }
/*     */   
/*     */   public String getMontantPrimeS() {
/* 310 */     return this.montantPrimeS;
/*     */   }
/*     */   
/*     */   public void setMontantPrimeS(String montantPrimeS) {
/* 314 */     this.montantPrimeS = montantPrimeS;
/*     */   }
/*     */   
/*     */   public String getMontantIndemniteS() {
/* 318 */     return this.montantIndemniteS;
/*     */   }
/*     */   
/*     */   public void setMontantIndemniteS(String montantIndemniteS) {
/* 322 */     this.montantIndemniteS = montantIndemniteS;
/*     */   }
/*     */   
/*     */   public Double getMontantPrime() {
/* 326 */     return Double.valueOf(this.montantPrime);
/*     */   }
/*     */   
/*     */   public void setMontantPrime(Double montantPrime) {
/* 330 */     this.montantPrime = montantPrime.doubleValue();
/*     */   }
/*     */   
/*     */   public Double getMontantIndemnite() {
/* 334 */     return Double.valueOf(this.montantIndemnite);
/*     */   }
/*     */   
/*     */   public void setMontantIndemnite(Double montantIndemnite) {
/* 338 */     this.montantIndemnite = montantIndemnite.doubleValue();
/*     */   }
/*     */   
/*     */   public Base getPersonnel() {
/* 342 */     return this.personnel;
/*     */   }
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/* 346 */     this.personnel = personnel;
/*     */   }

  
public String getFonction() {
	return fonction;
}
public void setFonction(String fonction) {
	this.fonction = fonction;
}
public List<EmployeC> getListRetraite() {
	return listRetraite;
}
public void setListRetraite(List<EmployeC> listRetraite) {
	this.listRetraite = listRetraite;
}

public List<FinCarriereC> getListeFinCarriere() {
	return listeFinCarriere;
}
public void setListeFinCarriere(List<FinCarriereC> listeFinCarriere) {
	this.listeFinCarriere = listeFinCarriere;
}
public int getTypeR() {
	return typeR;
}
public void setTypeR(int typeR) {
	this.typeR = typeR;
}
public String getPrintDate() {
	return printDate;
}
public void setPrintDate(String printDate) {
	this.printDate = printDate;
}
public String getPersonel() {
	return personel;
}
public void setPersonel(String personel) {
	this.personel = personel;
}
public String getCategorie() {
	return categorie;
}
public void setCategorie(String categorie) {
	this.categorie = categorie;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getNiveauFrm() {
	return niveauFrm;
}
public void setNiveauFrm(String niveauFrm) {
	this.niveauFrm = niveauFrm;
}

public String getPrintDateSalaire() {
	return printDateSalaire;
}
public void setPrintDateSalaire(String printDateSalaire) {
	this.printDateSalaire = printDateSalaire;
}
/*     */   @PostConstruct
/*     */   private void charger() {
/* 351 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 352 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*     */     
/* 354 */     if (codeOperateur == null || codeExercice == null) {
/*     */       try {
/* 356 */         FacesContext context = FacesContext.getCurrentInstance();
/* 357 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 358 */       } catch (IOException e) {
/*     */         
/* 360 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/* 363 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 364 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*     */       setDateRetraite(new Date());
				listRetraite=new ArrayList<EmployeC>();
			    printDate=HelperC.convertDate(getDateRetraite());
/* 366 */       listParametre=FichierBaseDAO.getInstance().getListParametrageFinCarriere();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargementListEmployeAretraite() {
/* 388 */     if (this.listParametre != null && this.listParametre.size() > 0)
/*     */     {
/* 390 */       for (ParametrageFinCarriereC prm : this.listParametre) {
/*     */         
/* 392 */         List<EmployeC> list = FactoryDAO.getInstance().getListEmployeARetraite(getDateRetraite(), prm.getAgeRetraite(),prm.getPersonnel().getId());
/* 393 */         for (EmployeC empl : list) {
/*     */           
/* 395 */           if (!prolongation(empl.getId())) {
/* 396 */            listRetraite.add(empl);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void chargementRetraiteAntcp() {
/* 404 */     List<DemandeRetraiteAnticipeC> listAnt = FactoryDAO.getInstance().getListeRetraiteAnticipeValide(Constante.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.traitementRetraite),1);
/* 405 */     for (DemandeRetraiteAnticipeC dmd : listAnt) {
/*     */        listRetraite.add(dmd.getEmploye());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean prolongation(int idEmp) {
/* 416 */     boolean prolong = false;
              int dif=0;
/* 417 */     DemandeProlongationRetraiteC prol = FactoryDAO.getInstance().getDemandeProlongationParEmploye(idEmp, 2, 2);
/* 418 */     if (prol != null ) 
			  {
				dif=HelperC.getYearFromDate(getDateRetraite())-HelperC.getYearFromDate(prol.getDateDemande());
				if(dif<=prol.getAgeRetraiteDemande())
/* 419 */       prolong = true;
/*     */     }
/* 421 */     return prolong;
/*     */   }
/*     */   
/*     */   public void findByCode() {
/* 425 */     if (!this.code.equals(null) && !this.code.equals("")) {
/* 426 */       this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
/*     */       
/* 428 */       if (this.selection != null) {
/* 429 */         setObject1();
					
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void findEmployeRecherche() {
/* 435 */     this.selection = FactoryDAO.getInstance().getEmploye(this.codeRecherche, false);
/*     */     
/* 437 */     if (this.selection != null) {
/* 438 */       setObject1();
/*     */     }
/*     */   }

 
/*     */   public void chargerEmploye() {
/* 446 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargementRetraiteEncours() {}
/*     */   
/*     */   private void setObject() {
/* 453 */     if (this.selected != null) {
/* 454 */       setId(this.selected.getId());
/* 455 */       setEmploye(this.selected.getEmploye());
/* 456 */       this.selection = getEmploye();
/* 457 */       if (this.selection != null)
/* 458 */         this.nom = selection.getNomPrenom();    
/* 460 */       setAge(this.selected.getAge());
/* 463 */       setDateRetraite(this.selected.getDateRetraite());
/* 464 */       setDateEvenementS(this.selected.getDateEvenementS());
/* 465 */       setDateFin(this.selected.getDateFin());
/* 466 */       setDateFinS(this.selected.getDateFinS());   
/* 470 */       setTypeRetraite(this.selected.getTypeRetraite());
/* 471 */       setObject1();
/* 472 */      
/*     */       
/* 474 */       
/*     */     } 
/*     */   }

			public void chargerRetraiteTraite(){
				listeFinCarriere=FactoryDAO.getInstance().getListeFinCarriere();
			}
			public void chargerRetraite(){
				listRetraite.clear();
				switch (typeR) {
				case 0:
					chargementRetraiteAntcp();
					break;

				case 1:
					chargementListEmployeAretraite();
					break;
				}
			}
			public void changerIdPrime(){
				
			}
/*     */   private void setObject1() {
/* 480 */     if (this.selection != null) {
/*     */       
/* 482 */       setEmploye(this.selection);
/* 483 */       if (getEmploye() != null) {
/* 484 */         this.code = getEmploye().getCode();
/* 485 */         this.codeRecherche = getEmploye().getCode();
/* 486 */         setAge(this.selection.getAge());
                  setTypeRetraite(typeR);
                  completeEmploye();   
/* 487 */         calculMontantTraitement();
				 
/*     */       } else {
/* 489 */         this.code = "";
/* 490 */         this.codeRecherche = "";
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 497 */     if (b)
/* 498 */       setId(0); 
/* 499 */     setEmploye(null);
/* 500 */     this.selected = null;
/* 501 */     this.selection = null;
/* 502 */     this.code = "";
/* 503 */     this.nom = "";
/* 504 */    
/* 505 */     setAge(0);
/* 506 */     setMontant(0.0D);
/* 507 */     setMontantApres(0);
/* 508 */     setDateRetraite(null);
/* 509 */     setDateEvenementS("");
/* 510 */     setDateFin(null);
/* 511 */     setDateFinS("");
/* 512 */     code="";
/* 515 */     setTypeRetraite(0);
/* 516 */     setTraitementApres(null);
/* 517 */     setTraitementAvant(null);
/* 518 */     getListeDetailPrime().clear();
/* 519 */      personel="";
               categorie="";
               grade="";
               niveauFrm="";
               fonction="";
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 524 */     clear(true);
			}

   public void changeDateFin() {
     if (getDateFinS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateFin(null);
     } else {
      
      setDateFin(HelperC.validerDate(getDateFinS()));
       if (getDateFin() == null) {
        setDateFinS("");
        HelperC.afficherMessage("Information", "Date invalide");
     } else {
         
        setDateFinS(HelperC.convertDate(getDateFin()));
       } 
    } 
   }
/*     */ 
/*     */   
/*     */  
/*     */   public void changeMotifFinCarriere(ValueChangeEvent event) {
/* 593 */     this.idMotif = ((Integer)event.getNewValue()).intValue();
/* 594 */    
/*     */   }
/*     */   public void changeTyepRetraite(ValueChangeEvent event) {
/* 598 */     setTypeRetraite(((Integer)event.getNewValue()).intValue());
/*     */   }
/*     */   
/*     */   public void clear2() {
/* 602 */     this.listEmploye.clear();
/* 603 */     this.codeEmployeRecherche = "";
/* 604 */     this.nomEmployeRecherche = "";
/* 605 */     this.prenomEmployeRecherche = "";
/*     */   }
/*     */ 
/*     */   
/*     */   
/*     */   
/*     */ 
/*     */   

private void completeEmploye(){
	if(selection!=null)
	{
		Base fx = FichierBaseDAO.getInstance().getBaseById(selection.getIdFnctn(), Tables.getTableName(Tables.TableName.fonction));
		Base nv=FichierBaseDAO.getInstance().getBaseById(selection.getIdNvFormt(), Tables.getTableName(Tables.TableName.niveauFormation));
		Base catg=FichierBaseDAO.getInstance().getBaseById(selection.getIdCatgrie(), Tables.getTableName(Tables.TableName.categoriePersonnel));
		Base pers=FichierBaseDAO.getInstance().getBaseById(selection.getIdPersnl(), Tables.getTableName(Tables.TableName.personnel));
		Base grd=FichierBaseDAO.getInstance().getBaseById(selection.getIdGrd(), Tables.getTableName(Tables.TableName.gradePersonnel));
		if(fx!=null)
			fonction=fx.getDesignation();
		else
			fonction="";
		
		if(nv!=null)
			niveauFrm=nv.getDesignation();
		else
			niveauFrm="";
		
		if(catg!=null)
			categorie=catg.getDesignation();
		else
			categorie="";
		if(pers!=null)
			personel=pers.getDesignation();
		else
			personel="";
		if(grd!=null)
		{
			grade=grd.getDesignation();
			
		}
		else
			grade="";
	}
}

			public void changeDateRetraite(){
				 if (printDate.replace("/", "").replace("_", "").trim().equals("")) {
				    
				       setDateRetraite(null);
				     } else {
				      
				    	 setDateRetraite(HelperC.validerDate(printDate));
				       if (getDateRetraite() == null) {
				        printDate="";
				        HelperC.afficherMessage("Information", "Date invalide");
				     } else {
				         
				    	 printDate=HelperC.convertDate(getDateRetraite());
				       } 
				    } 
			}
			
			public void changeDateSalaire(){
				 if (printDateSalaire.replace("/", "").replace("_", "").trim().equals("")) {
				    
				       setDateSalaire(null);
				     } else {
				      
				    	 setDateSalaire(HelperC.validerDate(printDateSalaire));
				       if (getDateSalaire() == null) {
				        printDateSalaire="";
				        HelperC.afficherMessage("Information", "Date invalide");
				     } else {
				         
				    	 printDateSalaire=HelperC.convertDate(getDateSalaire());
				       } 
				    } 
			}
			
/*     */   public void ajouterDetailPrime() {
/* 713 */     if (getPrime() == null) {
/* 714 */       HelperC.afficherMessage("Information", 
/* 715 */           "Veuillez selectionner la prime");
/* 716 */     } else if (getMontantPrime().doubleValue() == 0.0D) {
/* 717 */       HelperC.afficherMessage("Information", 
/* 718 */           "Veuillez saisir le montant de la prime");
/*     */     } else {
/*     */       
/* 721 */       for (FinCarriereDetailPrimeC det : getListeDetailPrime()) {
/* 722 */         if (det.getPrime().getId() == getPrime().getId()) {
/* 723 */           this.selectedDetailPrime = det;
/* 724 */           this.selectedDetailPrime.setExiste(true);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 729 */       if (this.selectedDetailPrime == null) {
/* 730 */         this.selectedDetailPrime = new FinCarriereDetailPrimeC();
/*     */       }
/* 732 */       this.selectedDetailPrime.setPrime(getPrime());
/* 733 */       this.selectedDetailPrime.setMontant(getMontantPrime().doubleValue());
/* 734 */     
/*     */       
/* 736 */       if (!this.selectedDetailPrime.isExiste()) {
/* 737 */         getListeDetailPrime().add(this.selectedDetailPrime);
/*     */       }
/* 739 */       clearDetailPrime();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enleverDetailPrime() {
/* 745 */    
/* 748 */     if (this.selectedDetailPrime != null) {
/* 749 */       getListeDetailPrimeDeleted().add(this.selectedDetailPrime);
/* 750 */       getListeDetailPrime().remove(this.selectedDetailPrime);
/*     */       
/* 752 */       clearDetailPrime();
/*     */     } else {
/* 754 */       HelperC.afficherMessage("Information", "Il n'y a aucun �l�ment � enlever!", FacesMessage.SEVERITY_ERROR);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void clearDetailPrime() {
/* 759 */     this.idPrime = 0;
/* 760 */     setPrime(null);
/* 761 */     setMontantPrime(0.0D);
/* 762 */     setMontantPrimeS("");
/* 763 */     this.selectedDetailPrime = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowselectedDetailPrime() {
/* 768 */     setDetailPrimeEmploye();
/*     */   }
/*     */   
/*     */   private void setDetailPrimeEmploye() {
/* 772 */     if (this.selectedDetailPrime != null) {
/* 773 */       setPrime(this.selectedDetailPrime.getPrime());
/*     */       
/* 775 */       if (getPrime() != null) {
/* 776 */         this.idPrime = this.selectedDetailPrime.getPrime().getId();
/*     */       }
/* 778 */       setMontantPrime(this.selectedDetailPrime.getMontant());
/* 779 */       setMontantPrimeS(HelperC.TraitementMontant.getMontantFormate(this.selectedDetailPrime.getMontant(), 3));
/*     */     } 
/*     */   }
/*     */   
/*     */  
/*     */   
/*     */ 
/*     */   public void chargerIdPrime(ValueChangeEvent ev) {
/* 857 */     this.idPrime = Integer.valueOf(ev.getNewValue().toString()).intValue();
/* 858 */     setPrime(FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrime));
/*     */   }

			private void createTraiementAv(){
				traitementAv=FactoryDAO.getInstance().getTraitementSalarial(selection.getId(), Constante.getTypeAvancement(Constante.TypeAvancement.finCarriereAv), getId());
				if(traitementAv==null)
					traitementAv=new TraitementSalarialC();
				
				traitementAv.setAncienSalaire(ancienSalaire);
				traitementAv.setSalaireBase(ancienSalaire);
				traitementAv.setEmploye(selection);
			
				traitementAv.setTypeAvancement(Constante.getTypeAvancement(Constante.TypeAvancement.finCarriereAv));
				traitementAv.setComment("INDEMNITE DE RETRAITE ");
				
				this.setTraitementAvant(traitementAv);
			}
			private void createTraiementAp(){
				traitementAp=FactoryDAO.getInstance().getTraitementSalarial(selection.getId(), Constante.getTypeAvancement(Constante.TypeAvancement.finCarriereAp), getId());
				if(traitementAp==null)
					traitementAp=new TraitementSalarialC();
				
				traitementAp.setAncienSalaire(ancienSalaire);
				traitementAp.setSalaireBase(salaireBase);
				traitementAp.setEmploye(selection);
				
				traitementAp.setTypeAvancement(Constante.getTypeAvancement(Constante.TypeAvancement.finCarriereAp));
				traitementAp.setComment("INDEMNITE DE RETRAITE ");
				this.setTraitementApres(traitementAp);
			}
/*     */   
/*     */   private void calculMontantTraitement() {
/* 890 */     if (selection != null) {
/* 891 */       tauxSalaire = 0.0D; salaireBase = 0.0D; ancienSalaire = 0.0D;
                TraitementSalarialC trtAct=FactoryDAO.getInstance().getSalaireActuel(selection, new Date());
                if(trtAct!=null)
/* 892 */       	ancienSalaire=trtAct.getSalaireBase();
                else 
                	ancienSalaire=selection.getSalaireBase();
                
                  setMontant(ancienSalaire);
                  parametre = FichierBaseDAO.getInstance().getParametrageFinCarriere(selection.getIdPersnl());
                  if(parametre!=null)
                  {
                	  salaireBase=parametre.getPourcentageSalaire()*ancienSalaire/100;
                	  setMontantApres(salaireBase);
                	  
                  }
                
/* 893 */       createTraiementAv();
                createTraiementAp();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 934 */     if (getEmploye() == null) {
/* 935 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/* 936 */     } else {
/*     */       
/* 940 */       Historique hist = new Historique();
/* 941 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 942 */       hist.setOperateur(this.operateur);
/* 943 */       if (getId() == 0) {
/* 944 */         hist.setOperation("Cr�ation de la fin de carri�re pour l'employ�" + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/* 946 */         hist.setOperation("Modification de la fin de carri�re pour l'employ�" + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 947 */       }  hist.setTable(Tables.getTableName(Tables.TableName.finCarriere));
/* 948 */       setHistorique(hist);
/*     */ 
/*     */       if(getDateSalaire()!=null) {
	                getTraitementAvant().setDateDebut(getDateSalaire());
	                if(parametre!=null)
						getTraitementApres().setDateDebut(HelperC.addMonth(getDateSalaire(), parametre.getPeriodeSalire()));
/* 951 */       if (FactoryDAO.getInstance().insertUpdateFinCarriere(this)) {
/* 952 */         HelperC.afficherMessage("F�licitation", "Enregistrement avec succ�s");
/*     */         
/* 954 */         clear(true);
/*     */       } else {
/*     */         
/* 957 */         HelperC.afficherAttention("D�sol�", "Echec d'enregistrement");
/*     */       } 
				}
				else
					 HelperC.afficherAttention("Attention", "Il faut pr�ciser la date du premier salaire");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void delete() {
/* 963 */     if (this.selected != null) {
					Historique hist = new Historique();
	/* 941 */       hist.setDateOperation(Calendar.getInstance().getTime());
	/* 942 */       hist.setOperateur(this.operateur);
	/* 943 */      
	/* 946 */         hist.setOperation("Annulation fin de carri�re pour l'employ�" + getEmploye().getNom() + " " + getEmploye().getPrenom());
	/* 947 */         hist.setTable(Tables.getTableName(Tables.TableName.finCarriere));
	/* 948 */        setHistorique(hist);
/* 964 */       if(FactoryDAO.getInstance().annulerFinCarriere(this))
				{
					clear(true);
					HelperC.afficherMessage("F�licitation", "Suppression avec succ�s");
				}
				else
					HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
			}
			}
   
/*     */   public void onRowSelected(SelectEvent event) {
/* 972 */     this.selected = (FinCarriereC)event.getObject();
/* 973 */     setObject();
			  PrimeFaces.current().executeScript("PF('dlgRetr').hide();");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEmplselected(SelectEvent event) {
/* 978 */     this.selection = (EmployeC)event.getObject();
/* 979 */     setObject1();
/* 980 */     PrimeFaces.current().executeScript("PF('dlgEmpRt').hide();");
/*     */   }
/*     */   
/*     */   
/*     */   
/*     */ }


