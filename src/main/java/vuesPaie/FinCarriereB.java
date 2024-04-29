 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DemandeProlongationRetraiteC;
 import classesPaie.DemandeRetraiteAnticipeC;
 import classesPaie.DetailPrimeEmployeC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.FinCarriereC;
 import classesPaie.FinCarriereDetailIndemniteC;
 import classesPaie.FinCarriereDetailPrimeC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageFinCarriereC;
 import classesPaie.Tables;
 import classesPaie.TraitementSalarialC;

 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
import java.util.Date;
 import java.util.List;

 import javax.annotation.PostConstruct;
 import javax.faces.application.FacesMessage;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;

 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;

 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;

 @ManagedBean
 @ViewScoped
 public class FinCarriereB
   extends FinCarriereC
 {
   private static final long serialVersionUID = -2571783497005994158L;
   private int idMotif;
   private int idPrime;
   private int idIndemnite;
   private String montantPrimeS = "0.0"; 
            private String montantIndemniteS = "0.0"; 
            private double montantPrime;
   private double montantIndemnite;
   private FinCarriereC selected;
   private EmployeC selection;
   private List<FinCarriereC> allFinCarriere = new ArrayList<FinCarriereC>();
   private List<SelectItem> listPrime = new ArrayList<SelectItem>();
   private List<SelectItem> listIndemnite = new ArrayList<SelectItem>(); 
            ParametrageFinCarriereC parametre;
   private String code;
   private String codeRecherche;
   private String nomRecherche;
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>(); 
            private String nom;
            private String codeEmployeRecherche; 
            private String nomEmployeRecherche; 
            private String prenomEmployeRecherche;
   private Constante.TypeAvancement typeAvancement;
   private FinCarriereDetailPrimeC selectedDetailPrime;
   private FinCarriereDetailIndemniteC selectedDetailIndemnite;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DemandeRetraiteAnticipeC anticipe;
   private List<EmployeC> listEmployeRetraite,listRetraite;
   List<DemandeRetraiteAnticipeC> listRetraiteAnticipe;
   List<ParametrageFinCarriereC> listParametre;
   private HttpSession session = HelperC.getSession();
 	private String personel,categorie,grade,niveauFrm,fonction;
 	private String printDate,printDateSalaire;
   private int typeR;
   private Base personnel;
 	private List<FinCarriereC>listeFinCarriere;
   TraitementSalarialC traitementAv,traitementAp;
   double tauxSalaire = 0.0D, salaireBase = 0.0D, ancienSalaire = 0.0D;
   
   public DemandeRetraiteAnticipeC getAnticipe() {
     return this.anticipe;
   }
   
   public void setAnticipe(DemandeRetraiteAnticipeC anticipe) {
     this.anticipe = anticipe;
   }
   
   public List<ParametrageFinCarriereC> getListParametre() {
     return this.listParametre;
   }
   
   public void setListParametre(List<ParametrageFinCarriereC> listParametre) {
     this.listParametre = listParametre;
   }
   
   public List<EmployeC> getListEmployeRetraite() {
     return this.listEmployeRetraite;
   }
   
   public void setListEmployeRetraite(List<EmployeC> listEmployeRetraite) {
     this.listEmployeRetraite = listEmployeRetraite;
   }
   
   public List<DemandeRetraiteAnticipeC> getListRetraiteAnticipe() {
     return this.listRetraiteAnticipe;
   }
 
   
   public void setListRetraiteAnticipe(List<DemandeRetraiteAnticipeC> listRetraiteAnticipe) {
     this.listRetraiteAnticipe = listRetraiteAnticipe;
   }
   
   public FinCarriereC getSelected() {
     return this.selected;
   }
   
   public void setSelected(FinCarriereC selected) {
     this.selected = selected;
   }
   
   public EmployeC getSelection() {
     return this.selection;
   }
   
   public void setSelection(EmployeC selection) {
     this.selection = selection;
   }
   
   public List<FinCarriereC> getAllFinCarriere() {
     return this.allFinCarriere;
   }
   
   public void setAllFinCarriere(List<FinCarriereC> allFinCarriere) {
     this.allFinCarriere = allFinCarriere;
   }
   
   public String getCode() {
     return this.code;
   }
   
   public void setCode(String code) {
     this.code = code;
   }
   
   public String getCodeRecherche() {
     return this.codeRecherche;
   }
   
   public void setCodeRecherche(String codeRecherche) {
     this.codeRecherche = codeRecherche;
   }
   
   public String getNomRecherche() {
     return this.nomRecherche;
   }
   
   public void setNomRecherche(String nomRecherche) {
     this.nomRecherche = nomRecherche;
   }
   
   public String getNom() {
     return this.nom;
   }
   
   public void setNom(String nom) {
     this.nom = nom;
   }
   
   public String getCodeEmployeRecherche() {
     return this.codeEmployeRecherche;
   }
   
   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
     this.codeEmployeRecherche = codeEmployeRecherche;
   }
   
   public String getNomEmployeRecherche() {
     return this.nomEmployeRecherche;
   }
   
   public void setNomEmployeRecherche(String nomEmployeRecherche) {
     this.nomEmployeRecherche = nomEmployeRecherche;
   }
   
   public String getPrenomEmployeRecherche() {
     return this.prenomEmployeRecherche;
   }
   
   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
     this.prenomEmployeRecherche = prenomEmployeRecherche;
   }
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
   
   public OperateurC getOperateur() {
     return this.operateur;
   }
   
   public void setOperateur(OperateurC operateur) {
     this.operateur = operateur;
   }
   
   public ExerciceC getExercice() {
     return this.exercice;
   }
   
   public void setExercice(ExerciceC exercice) {
     this.exercice = exercice;
   }
   
   public HttpSession getSession() {
     return this.session;
   }
   
   public void setSession(HttpSession session) {
     this.session = session;
   }
   
   public int getIdMotif() {
     return this.idMotif;
   }
   
   public void setIdMotif(int idMotif) {
     this.idMotif = idMotif;
   }
   
   public int getIdPrime() {
     return this.idPrime;
   }
   
   public void setIdPrime(int idPrime) {
     this.idPrime = idPrime;
   }
   
   public int getIdIndemnite() {
     return this.idIndemnite;
   }
   
   public void setIdIndemnite(int idIndemnite) {
     this.idIndemnite = idIndemnite;
   }
   
   public List<SelectItem> getListPrime() {
     return this.listPrime;
   }
   
   public void setListPrime(List<SelectItem> listPrime) {
     this.listPrime = listPrime;
   }
   
   public List<SelectItem> getListIndemnite() {
     return this.listIndemnite;
   }
   
   public void setListIndemnite(List<SelectItem> listIndemnite) {
     this.listIndemnite = listIndemnite;
   }
   

   public Constante.TypeAvancement getTypeAvancement() {
     return this.typeAvancement;
   }
   
   public void setTypeAvancement(Constante.TypeAvancement typeAvancement) {
     this.typeAvancement = typeAvancement;
   }
   
   public FinCarriereDetailPrimeC getSelectedDetailPrime() {
     return this.selectedDetailPrime;
   }
   
   public void setSelectedDetailPrime(FinCarriereDetailPrimeC selectedDetailPrime) {
     this.selectedDetailPrime = selectedDetailPrime;
   }
   
   public FinCarriereDetailIndemniteC getSelectedDetailIndemnite() {
     return this.selectedDetailIndemnite;
   }
 
   
   public void setSelectedDetailIndemnite(FinCarriereDetailIndemniteC selectedDetailIndemnite) {
     this.selectedDetailIndemnite = selectedDetailIndemnite;
   }
   
   public void setMontantPrime(double montantPrime) {
     this.montantPrime = montantPrime;
   }
   
   public void setMontantIndemnite(double montantIndemnite) {
     this.montantIndemnite = montantIndemnite;
   }
   
   public String getMontantPrimeS() {
     return this.montantPrimeS;
   }
   
   public void setMontantPrimeS(String montantPrimeS) {
     this.montantPrimeS = montantPrimeS;
   }
   
   public String getMontantIndemniteS() {
     return this.montantIndemniteS;
   }
   
   public void setMontantIndemniteS(String montantIndemniteS) {
     this.montantIndemniteS = montantIndemniteS;
   }
   
   public Double getMontantPrime() {
     return Double.valueOf(this.montantPrime);
   }
   
   public void setMontantPrime(Double montantPrime) {
     this.montantPrime = montantPrime.doubleValue();
   }
   
   public Double getMontantIndemnite() {
     return Double.valueOf(this.montantIndemnite);
   }
   
   public void setMontantIndemnite(Double montantIndemnite) {
     this.montantIndemnite = montantIndemnite.doubleValue();
   }
   
   public Base getPersonnel() {
     return this.personnel;
   }
   
   public void setPersonnel(Base personnel) {
     this.personnel = personnel;
   }

  
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
   @PostConstruct
   private void charger() {
     String codeExercice = (String)this.session.getAttribute("exercice");
     String codeOperateur = (String)this.session.getAttribute("operateur");
     
     if (codeOperateur == null || codeExercice == null) {
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       } catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
       setDateRetraite(new Date());
				listRetraite=new ArrayList<EmployeC>();
			    printDate=HelperC.convertDate(getDateRetraite());
       listParametre=FichierBaseDAO.getInstance().getListParametrageFinCarriere();
     } 
   }
 
 
   
   private void chargementListEmployeAretraite() {
     if (this.listParametre != null && this.listParametre.size() > 0)
     {
       for (ParametrageFinCarriereC prm : this.listParametre) {
         
         List<EmployeC> list = FactoryDAO.getInstance().getListEmployeARetraite(getDateRetraite(), prm.getAgeRetraite(),prm.getPersonnel().getId());
         for (EmployeC empl : list) {
           
           if (!prolongation(empl.getId())) {
            listRetraite.add(empl);
           }
         } 
       } 
     }
   }
   
   private void chargementRetraiteAntcp() {
     List<DemandeRetraiteAnticipeC> listAnt = FactoryDAO.getInstance().getListeRetraiteAnticipeValide(Constante.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.traitementRetraite),1);
     for (DemandeRetraiteAnticipeC dmd : listAnt) {
        listRetraite.add(dmd.getEmploye());
     } 
   }
 
 
   
   private boolean prolongation(int idEmp) {
     boolean prolong = false;
              int dif=0;
     DemandeProlongationRetraiteC prol = FactoryDAO.getInstance().getDemandeProlongationParEmploye(idEmp, 2, 2);
     if (prol != null ) 
			  {
				dif=HelperC.getYearFromDate(getDateRetraite())-HelperC.getYearFromDate(prol.getDateDemande());
				if(dif<=prol.getAgeRetraiteDemande())
       prolong = true;
     }
     return prolong;
   }
   
   public void findByCode() {
     if (!this.code.equals(null) && !this.code.equals("")) {
       this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
       
       if (this.selection != null) {
         setObject1();
					
       }
     } 
   }
   
   public void findEmployeRecherche() {
     this.selection = FactoryDAO.getInstance().getEmploye(this.codeRecherche, false);
     
     if (this.selection != null) {
       setObject1();
     }
   }

 
   public void chargerEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,true);
   }
 
   
   public void chargementRetraiteEncours() {}
   
   private void setObject() {
     if (this.selected != null) {
       setId(this.selected.getId());
       setEmploye(this.selected.getEmploye());
       this.selection = getEmploye();
       if (this.selection != null)
         this.nom = selection.getNomPrenom();    
       setAge(this.selected.getAge());
       setDateRetraite(this.selected.getDateRetraite());
       setDateEvenementS(this.selected.getDateEvenementS());
       setDateFin(this.selected.getDateFin());
       setDateFinS(this.selected.getDateFinS());   
       setTypeRetraite(this.selected.getTypeRetraite());
       setObject1();
      
       
       
     } 
   }

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
   private void setObject1() {
     if (this.selection != null) {
       
       setEmploye(this.selection);
       if (getEmploye() != null) {
         this.code = getEmploye().getCode();
         this.codeRecherche = getEmploye().getCode();
         setAge(this.selection.getAge());
                  setTypeRetraite(typeR);
                  completeEmploye();   
         calculMontantTraitement();
				 
       } else {
         this.code = "";
         this.codeRecherche = "";
       } 
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
       setId(0); 
     setEmploye(null);
     this.selected = null;
     this.selection = null;
     this.code = "";
     this.nom = "";
    
     setAge(0);
     setMontant(0.0D);
     setMontantApres(0);
     setDateRetraite(null);
     setDateEvenementS("");
     setDateFin(null);
     setDateFinS("");
     code="";
     setTypeRetraite(0);
     setTraitementApres(null);
     setTraitementAvant(null);
     getListeDetailPrime().clear();
      personel="";
               categorie="";
               grade="";
               niveauFrm="";
               fonction="";
   }
 
   
   public void initialiser() {
     clear(true);
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
 
   
  
   public void changeMotifFinCarriere(ValueChangeEvent event) {
     this.idMotif = ((Integer)event.getNewValue()).intValue();
    
   }
   public void changeTyepRetraite(ValueChangeEvent event) {
     setTypeRetraite(((Integer)event.getNewValue()).intValue());
   }
   
   public void clear2() {
     this.listEmploye.clear();
     this.codeEmployeRecherche = "";
     this.nomEmployeRecherche = "";
     this.prenomEmployeRecherche = "";
   }
 
   
   
   
 
   

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
			
   public void ajouterDetailPrime() {
     if (getPrime() == null) {
       HelperC.afficherMessage("Information", 
           "Veuillez selectionner la prime");
     } else if (getMontantPrime().doubleValue() == 0.0D) {
       HelperC.afficherMessage("Information", 
           "Veuillez saisir le montant de la prime");
     } else {
       
       for (FinCarriereDetailPrimeC det : getListeDetailPrime()) {
         if (det.getPrime().getId() == getPrime().getId()) {
           this.selectedDetailPrime = det;
           this.selectedDetailPrime.setExiste(true);
           
           break;
         } 
       } 
       if (this.selectedDetailPrime == null) {
         this.selectedDetailPrime = new FinCarriereDetailPrimeC();
       }
       this.selectedDetailPrime.setPrime(getPrime());
       this.selectedDetailPrime.setMontant(getMontantPrime().doubleValue());
     
       
       if (!this.selectedDetailPrime.isExiste()) {
         getListeDetailPrime().add(this.selectedDetailPrime);
       }
       clearDetailPrime();
     } 
   }
 
   
   public void enleverDetailPrime() {
    
     if (this.selectedDetailPrime != null) {
       getListeDetailPrimeDeleted().add(this.selectedDetailPrime);
       getListeDetailPrime().remove(this.selectedDetailPrime);
       
       clearDetailPrime();
     } else {
       HelperC.afficherMessage("Information", "Il n'y a aucun élément é enlever!", FacesMessage.SEVERITY_ERROR);
     } 
   }
   
   private void clearDetailPrime() {
     this.idPrime = 0;
     setPrime(null);
     setMontantPrime(0.0D);
     setMontantPrimeS("");
     this.selectedDetailPrime = null;
   }
 
   
   public void onRowselectedDetailPrime() {
     setDetailPrimeEmploye();
   }
   
   private void setDetailPrimeEmploye() {
     if (this.selectedDetailPrime != null) {
       setPrime(this.selectedDetailPrime.getPrime());
       
       if (getPrime() != null) {
         this.idPrime = this.selectedDetailPrime.getPrime().getId();
       }
       setMontantPrime(this.selectedDetailPrime.getMontant());
       setMontantPrimeS(HelperC.TraitementMontant.getMontantFormate(this.selectedDetailPrime.getMontant(), 3));
     } 
   }
   
  
   
 
   public void chargerIdPrime(ValueChangeEvent ev) {
     this.idPrime = Integer.valueOf(ev.getNewValue().toString()).intValue();
     setPrime(FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrime));
   }

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
   
   private void calculMontantTraitement() {
     if (selection != null) {
       tauxSalaire = 0.0D; salaireBase = 0.0D; ancienSalaire = 0.0D;
                TraitementSalarialC trtAct=FactoryDAO.getInstance().getSalaireActuel(selection, new Date());
                if(trtAct!=null)
       	ancienSalaire=trtAct.getSalaireBase();
                else 
                	ancienSalaire=selection.getSalaireBase();
                
                  setMontant(ancienSalaire);
                  parametre = FichierBaseDAO.getInstance().getParametrageFinCarriere(selection.getIdPersnl());
                  if(parametre!=null)
                  {
                	  salaireBase=parametre.getPourcentageSalaire()*ancienSalaire/100;
                	  setMontantApres(salaireBase);
                	  
                  }
                
       createTraiementAv();
                createTraiementAp();
     } 
   }
 
   
   public void save() {
     if (getEmploye() == null) {
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création de la fin de carrière pour l'employé" + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } else {
         hist.setOperation("Modification de la fin de carriére pour l'employé" + getEmploye().getNom() + " " + getEmploye().getPrenom());
       }  hist.setTable(Tables.getTableName(Tables.TableName.finCarriere));
       setHistorique(hist);
 
       if(getDateSalaire()!=null) {
	                getTraitementAvant().setDateDebut(getDateSalaire());
	                if(parametre!=null)
						getTraitementApres().setDateDebut(HelperC.addMonth(getDateSalaire(), parametre.getPeriodeSalire()));
       if (FactoryDAO.getInstance().insertUpdateFinCarriere(this)) {
         HelperC.afficherMessage("Félicitation", "Enregistrement avec Succès");
         
         clear(true);
       } else {
         
         HelperC.afficherAttention("Désolé", "Echec d'enregistrement");
       } 
				}
				else
					 HelperC.afficherAttention("Attention", "Il faut préciser la date du premier salaire");
     } 
   }
   
   public void delete() {
     if (this.selected != null) {
					Historique hist = new Historique();
	       hist.setDateOperation(Calendar.getInstance().getTime());
	       hist.setOperateur(this.operateur);
	      
	         hist.setOperation("Annulation fin de carriére pour l'employé" + getEmploye().getNom() + " " + getEmploye().getPrenom());
	         hist.setTable(Tables.getTableName(Tables.TableName.finCarriere));
	        setHistorique(hist);
       if(FactoryDAO.getInstance().annulerFinCarriere(this))
				{
					clear(true);
					HelperC.afficherMessage("Félicitation", "Suppression avec Succès");
				}
				else
					HelperC.afficherMessage("Désolé", "Echec de l'opération");
			}
			}
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (FinCarriereC)event.getObject();
     setObject();
			  PrimeFaces.current().executeScript("PF('dlgRetr').hide();");
   }
 
   
   public void onEmplselected(SelectEvent event) {
     this.selection = (EmployeC)event.getObject();
     setObject1();
     PrimeFaces.current().executeScript("PF('dlgEmpRt').hide();");
   }
   
   
   
 }


