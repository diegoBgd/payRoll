 package vuesPaie;
 
 import classesPaie.Base;
import classesPaie.Constante;
 import classesPaie.DemandeProlongationRetraiteC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
import classesPaie.ParametrageFinCarriereC;
import classesPaie.ParametrageGeneralC;
 import classesPaie.Tables;

 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;

 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.component.UIComponent;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;

 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;

 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;

 @ManagedBean
 @ViewScoped
 public class DemandeProlongationRetraiteB
   extends DemandeProlongationRetraiteC
 {
   private static final long serialVersionUID = 7234059774225821051L;
   private DemandeProlongationRetraiteC selected;
   private EmployeC selection;
   private List<DemandeProlongationRetraiteC> listDemande = new ArrayList<DemandeProlongationRetraiteC>();
   private List<DemandeProlongationRetraiteC> listDecisionDemande = new ArrayList<DemandeProlongationRetraiteC>();
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private String personel,categorie,grade,niveauFrm,fonction;
   private String code,msgInfo;
   private String codeEmployeRecherche;
   private String nomEmployeRecherche;
   private String prenomEmployeRecherche;
   private boolean disableSave;
			private int typeDec;
			ParametrageFinCarriereC parm;
			ParametrageGeneralC parmGn;
			
   public DemandeProlongationRetraiteC getSelected() {
     return this.selected;
   }
   
   public void setSelected(DemandeProlongationRetraiteC selected) {
     this.selected = selected;
   }
   
   public EmployeC getSelection() {
     return this.selection;
   }
   
   public void setSelection(EmployeC selection) {
     this.selection = selection;
   }
   
   public List<DemandeProlongationRetraiteC> getListDemande() {
     return this.listDemande;
   }
   
   public void setListDemande(List<DemandeProlongationRetraiteC> listDemande) {
     this.listDemande = listDemande;
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
   
   public String getCode() {
     return this.code;
   }
   
   public void setCode(String code) {
     this.code = code;
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
   
   public List<DemandeProlongationRetraiteC> getListDecisionDemande() {
     return this.listDecisionDemande;
   }
 
   
   public void setListDecisionDemande(List<DemandeProlongationRetraiteC> listDecisionDemande) {
     this.listDecisionDemande = listDecisionDemande;
   }
   
   public static long getSerialversionuid() {
     return 7234059774225821051L;
   }
   public String getPersonel() {
			return personel;
			}

			public int getTypeDec() {
				return typeDec;
			}
			public void setTypeDec(int typeDec) {
				this.typeDec = typeDec;
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
			
			public boolean isDisableSave() {
				return disableSave;
			}
			public void setDisableSave(boolean disableSave) {
				this.disableSave = disableSave;
			}
			
			public String getFonction() {
				return fonction;
			}
			public void setFonction(String fonction) {
				this.fonction = fonction;
			}
			
			public String getMsgInfo() {
				return msgInfo;
			}
			public void setMsgInfo(String msgInfo) {
				this.msgInfo = msgInfo;
			}
   @PostConstruct
   private void charger() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     if (this.operateur == null || this.exercice == null) {
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       } catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       parmGn=FichierBaseDAO.getInstance().getParametrageGeneral();
       UIComponent frm = null;
       FacesContext context = FacesContext.getCurrentInstance();
       frm = context.getViewRoot().findComponent("frmPrl");
       if (frm != null) {
         
        setEtatProlongation(Constante.EtatDemandeProlongationRetraite.demandeProlongation);
        setDateDemande(new Date());
				 setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
       } 

				frm = context.getViewRoot().findComponent("frmVldPrl");
				if (frm != null) {
         
        setEtatProlongation(Constante.EtatDemandeProlongationRetraite.decide);
        setDateDecision(new Date());
				 setDateDecisionS(HelperC.changeDateFormate(getDateDecision()));
       } 

				frm = context.getViewRoot().findComponent("frmPrLine");
				if (frm != null) {
         setDateDemande(new Date());
				  setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
         setEtatProlongation(Constante.EtatDemandeProlongationRetraite.demandeProlongation);
         employeInLine();
				  completeEmploye();
       } 
       
       
				
     } 
   }


			private void employeInLine(){
				selection = FactoryDAO.getInstance().getEmploye(operateur.getIdEmploye(), false);
				if (this.selection != null) {
				      setCode(this.selection.getCode());
					  setEmploye(this.selection);
				}
			}
			
			public void chargementDemandeLine() {
			    this.listDemande = FactoryDAO.getInstance().getListeDemandeProlongationRetraiteLine(selection.getId());
			 }
			
   public void chargementDemande() {
     this.listDemande = FactoryDAO.getInstance().getListeDemandeProlongationRetraite(1);
   }
 
   
   public void chargementTraitement() {
	
    listDecisionDemande = FactoryDAO.getInstance().getListeDemandeProlongationRetraiteTraite(2,typeDec);
     
   }
   
   public void findByCode() {
     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
     if (this.selection != null) {
       setCode(this.selection.getCode());
       setEmploye(this.selection);				
				completeEmploye();
     } else {
       
       clear1(true);
     } 
   }
   
   private void clear1(boolean b) {
     if (b) {
       this.selection = null;
     }
   }
 
   
   private void setObject() {
     if (this.selected != null) {
       setId(this.selected.getId());
       setEmploye(this.selected.getEmploye());
				selection=this.selected.getEmploye();
       if (getEmploye() != null)
         setCode(getEmploye().getCode()); 
				completeEmploye();
       setDateDemande(this.selected.getDateDemande());
       setDateDemandeS(this.selected.getDateDemandeS());
       setEtat(this.selected.getEtat());
       setEtatProlongation(this.selected.getEtatProlongation());
       setMotifDemande(this.selected.getMotifDemande());
       setAge(this.selected.getAge());
       setAgeRetraiteDemande(this.selected.getAgeRetraiteDemande());
       setInLine(selected.isInLine());
       setDecision(this.selected.getDecision());
       setDateDecision(this.selected.getDateDecision());
       setDateDecisionS(this.selected.getDateDecisionS());
       setEtat(this.selected.getEtat());
       setEtatProlongation(this.selected.getEtatProlongation());
       setLibelleProlongation(this.selected.getLibelleProlongation());
                
     } 
   }
	private void completeEmploye(){
	if(getEmploye()!=null)
	{
		Base fx = FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdFnctn(), Tables.getTableName(Tables.TableName.fonction));
		Base nv=FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdNvFormt(), Tables.getTableName(Tables.TableName.niveauFormation));
		Base catg=FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdCatgrie(), Tables.getTableName(Tables.TableName.categoriePersonnel));
		Base pers=FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdPersnl(), Tables.getTableName(Tables.TableName.personnel));
		Base grd=FichierBaseDAO.getInstance().getBaseById(getEmploye().getIdGrd(), Tables.getTableName(Tables.TableName.gradePersonnel));
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
			grade=grd.getDesignation();
		else
			grade="";
		setAge(selection.getAge());
		searchParametre();
	}
}

	private void searchParametre(){
		disableSave=false;
		msgInfo="";
		parm=FichierBaseDAO.getInstance().getParametrageFinCarriere(selection.getIdPersnl());
		if(parm!=null)
		{
			setAgeMin(parm.getAgeRetraite());
			setAgeMax(parm.getAgeMaxRetraite());
			if(getAge()<getAgeMin())
			{
				msgInfo="Il faut avoir atteint l'ége de retaraite ("+parm.getAgeRetraite()+" ans) pour demander la prolongation !";
				disableSave=true;
			}
		}
	}
	
	public void clearLine() {
		setId(0);
		setEtat(0);	
		setMotifDemande("");
		setAgeRetraiteDemande(0);
		setDecision(0);
		setDateDecision(null);
		setDateDecisionS("");
		setEtat(0);
		setEtatProlongation(null);
		setLibelleProlongation("");
		
		
	}
	
   private void clear() {
     setId(0);
     setEmploye(null);
     setDateDemande(null);
     setDateDemandeS("");
     setEtat(0);
     setEtatProlongation(null);
     setMotifDemande("");
     setAge(0);
     setAgeRetraiteDemande(0);
     setAvisConseilAdministration(0);
     setDecision(0);
     setDateDecision(null);
     setDateDecisionS("");
     setEtat(0);
     setEtatProlongation(null);
     setLibelleProlongation("");
     this.nomEmployeRecherche = "";
     this.code = "";
     this.prenomEmployeRecherche = "";
     this.selected = null;
   }
 
   
   public void changeDateDemande() {
     if (getDateDemandeS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateDemande(null);
     } else {
       
       setDateDemande(HelperC.validerDate(getDateDemandeS()));
       if (getDateDemande() == null) {
         setDateDemandeS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         setDateDemandeS(HelperC.convertDate(getDateDemande()));
       } 
     } 
   }
 
 
   
   public void changeDateDecision() {
     if (getDateDecisionS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateDecision(null);
     } else {
       
       setDateDecision(HelperC.validerDate(getDateDecisionS()));
       if (getDateDecision() == null) {
         setDateDecisionS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         setDateDecisionS(HelperC.convertDate(getDateDecision()));
       } 
     } 
   }
 
 
 
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (DemandeProlongationRetraiteC)event.getObject();
     if (this.selected != null)
       setObject(); 
     PrimeFaces.current().executeScript("PF('dlgDmd').hide();");
   }


   public void onRowDecisionSelected(SelectEvent event) {
     this.selected = (DemandeProlongationRetraiteC)event.getObject();
     if (this.selected != null)
       setObject(); 
     PrimeFaces.current().executeScript("PF('dlgTrt').hide();");
   }

   private void setObject1() {
     if (this.selection != null) {
       setEmploye(this.selection);
       
       if (getEmploye() != null) {
         setCode(getEmploye().getCode());
       } else {
         setCode("");
       } 
     } 
   }
 
   
   public void onRowselected1(SelectEvent event) {
     this.selection = (EmployeC)event.getObject();
     setObject1();
     PrimeFaces.current().executeScript("PF('dlg').hide();");
   }
    
            public void checkPeriod(){
            	disableSave=false;
            	if(parm!=null){
            	if(getAge()<parm.getAgeRetraite())
            	{
            		disableSave=true;
            		HelperC.afficherAttention("Information", " L'Employé n'a pas encore l'ége  de retraite ");
            		return;
            	}
            	if(getAgeRetraiteDemande()>parm.getPeriodeProlongation())
            	{
            		disableSave=true;
            		HelperC.afficherAttention("Information", "On ne peut pas dépasser une prolongation de "+parm.getPeriodeProlongation()+" ans");
            		return;
            	}
            	if(getAge()+getAgeRetraiteDemande()>parm.getAgeMaxRetraite())
            	{
            		disableSave=true;
            		HelperC.afficherAttention("Information", "On ne peut pas dépasser  "+parm.getAgeMaxRetraite()+" ans ");
            		return;
            	}
            	}
            	else
            	{
            		disableSave=true;
            		HelperC.afficherAttention("Information", "Il faut paramétrer tous éléments en rapport la prologantion du travail et le personnel");
            		return;
            	}
            }
 
 
   
   public void chargerEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, 
         this.nomEmployeRecherche,true);
   }
   
   public void enregistrerDemande() {
     if (getEmploye() == null) {
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     } else if (getDateDemande() == null) {
       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
     } else if (getMotifDemande().equalsIgnoreCase("")) {
       HelperC.afficherAttention("Information", "Veillez décrire le motif de votre demande");
     } else if (getAgeRetraiteDemande() == 0) {
       HelperC.afficherAttention("Information", "Veillez indiquer l'age de la retraite souhaité");
     } else {
       
       setEtat(Constante.getEtatDemandeProlongationRetraite(Constante.EtatDemandeProlongationRetraite.demandeProlongation));
       setInLine(true);
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création de la demande de prolongation de l'age de la retraite de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } else {
         hist.setOperation("Modification de la demande de prolongation de l'age de la retraite de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
       setHistorique(hist);

       if (FactoryDAO.getInstance().insertUpdateDemandeProlongationRetraite(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
         chargementDemande();
         clear();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }

   public void enregistrerDemandeLine() {
	         if(getDecision()==0 && getDateDecision()==null)
	         {
     if (getEmploye() == null) {
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     } else if (getDateDemande() == null) {
       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
     } else if (getMotifDemande().equalsIgnoreCase("")) {
       HelperC.afficherAttention("Information", "Veillez décrire le motif de votre demande");
     } else if (getAgeRetraiteDemande() == 0) {
       HelperC.afficherAttention("Information", "Veillez indiquer l'age de la retraite souhaité");
     } else {
       setEtatProlongation(Constante.EtatDemandeProlongationRetraite.demandeProlongation);
       setEtat(Constante.getEtatDemandeProlongationRetraite(Constante.EtatDemandeProlongationRetraite.demandeProlongation));
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création de la demande de prolongation de l'age de la retraite de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } else {
         hist.setOperation("Modification de la demande de prolongation de l'age de la retraite de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
        setHistorique(hist);

       if (FactoryDAO.getInstance().insertUpdateDemandeProlongationRetraite(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
         chargementDemande();
         clearLine();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
	         }
	         else{
	        	 HelperC.afficherAttention("Information", "Impossible de modifier car votre demande ést déjé traitée ");
	         }
	         
   }

   public void enregistrerDecision() {
     if (getEmploye() == null) {
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     } else if (getDateDemande() == null) {
       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
     } else if (getMotifDemande().equalsIgnoreCase("")) {
       HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
     } else if (getAgeRetraiteDemande() == 0) {
       HelperC.afficherAttention("Information", "Veillez indiquer l'age de la retraite souhaité");
     } else if (getDateDecision() == null) {
       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de décision");
     
     }
     else {
 
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création de la demande de prolongation de l'age de la retraite de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } else {
         hist.setOperation("Modification de la demande de prolongation de l'age de la retraite de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
       
       setHistorique(hist);
				setEtat(Constante.getEtatDemandeProlongationRetraite(Constante.EtatDemandeProlongationRetraite.decide));
     
       if (FactoryDAO.getInstance().insertUpdateDemandeProlongationRetraite(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
				if(parmGn!=null) 
				{
				if(getDecision()==1)
				    HelperC.sendEmail(parmGn.getSmtpServer(), parmGn.getMailOrigine(), parmGn.getMailOrigine(), parmGn.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de prolongation de travail a été acceptée ", "Demande prolongation de travail");
				if(getDecision()==2)
				     HelperC.sendEmail(parmGn.getSmtpServer(), parmGn.getMailOrigine(), parmGn.getMailOrigine(), parmGn.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de prolongation de travail a été refusée ", "Demande prolongation de travail.");
				}
         chargementDemande();
         clear();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   public void annulerDecision()
			{
				if(selected!=null)
				if(FactoryDAO.getInstance().annulerProlongagtionRetraite(selected))
					 HelperC.afficherMessage("Information", "Succès de l'opération");
				else
					  HelperC.afficherMessage("Désolé", "Echec de l'opération ");
			}
   public void supprimer() {
     if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création de la demande de prolongation de l'âge de la retraite de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } else {
         hist.setOperation("Modification de la demande de prolongation de l'age de la retraite de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
       setHistorique(hist);
       if (FactoryDAO.getInstance().deleteDemandeProlongationRetraite(selected)) {
           chargementDemande();
         clear();
         HelperC.afficherMessage("Information", "Succès de l'opération ");
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de suppression");
       } 
     } 
   }
   
   public void initialiser() {
     clear();
   }
 }

