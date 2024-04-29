package vuesPaie;
import classesPaie.Constante;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
import classesPaie.ParametrageSanctionC;
import classesPaie.SaisieSanctionC;
import classesPaie.Tables;
import classesPaie.TraitementSalarialC;
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;

 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;

 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;

 
 @ManagedBean
 @ViewScoped
 public class SaisieSanctionB
   extends SaisieSanctionC
 {
   private static final long serialVersionUID = -7482694101907046862L;
   private SaisieSanctionC selected;
   private EmployeC selection;
   private List<SaisieSanctionC> listSaisie,listRecours,
								          listDecision,listRetard;
   private List<SelectItem> listTypeSanction;
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
   private OperateurC operateur;
   private ExerciceC exercice;
   
   private HttpSession session = HelperC.getSession();
   private boolean disableSave,disableElt,disableMsg;
   private String code;
   private String dateSaisieS,dateDebutSanctionS,
						   dateFinSanctionS,dateRecoursS,
						   dateDecisionS;
   private String codeEmployeRecherche;
   private String nomEmployeRecherche;
   private String prenomEmployeRecherche;
   private int decision,dureeSanction,typeDossier,typeDecision;
   ParametrageSanctionC parm;
   double salaireActuel=0;
   TraitementSalarialC traitement;
   ParametrageGeneralC parametre;
            List<ParametrageSanctionC> listParam;
   public EmployeC getSelection() {
     return this.selection;
   }
   
   public void setSelection(EmployeC selection) {
     this.selection = selection;
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
   
   public int getDecision() {
     return this.decision;
   }
   
   public void setDecision(int decision) {
     this.decision = decision;
   }
   
			public SaisieSanctionC getSelected() {
				return selected;
			}
			public void setSelected(SaisieSanctionC selected) {
				this.selected = selected;
			}
			public List<SaisieSanctionC> getListSaisie() {
				return listSaisie;
			}
			public void setListSaisie(List<SaisieSanctionC> listSaisie) {
				this.listSaisie = listSaisie;
			}
			
			public boolean isDisableSave() {
				return disableSave;
			}
			public void setDisableSave(boolean disableSave) {
				this.disableSave = disableSave;
			}
			
			public String getDateSaisieS() {
				return dateSaisieS;
			}
			public void setDateSaisieS(String dateSaisieS) {
				this.dateSaisieS = dateSaisieS;
			}
			public String getDateDebutSanctionS() {
				return dateDebutSanctionS;
			}
			public void setDateDebutSanctionS(String dateDebutSanctionS) {
				this.dateDebutSanctionS = dateDebutSanctionS;
			}
			public String getDateFinSanctionS() {
				return dateFinSanctionS;
			}
			public void setDateFinSanctionS(String dateFinSanctionS) {
				this.dateFinSanctionS = dateFinSanctionS;
			}
			
			public int getDureeSanction() {
				return dureeSanction;
			}
			public void setDureeSanction(int dureeSanction) {
				this.dureeSanction = dureeSanction;
			}
			
			public String getDateRecoursS() {
				return dateRecoursS;
			}
			public void setDateRecoursS(String dateRecoursS) {
				this.dateRecoursS = dateRecoursS;
			}
			
			public String getDateDecisionS() {
				return dateDecisionS;
			}
			public void setDateDecisionS(String dateDecisionS) {
				this.dateDecisionS = dateDecisionS;
			}
			
			public boolean isDisableElt() {
				return disableElt;
			}
			public void setDisableElt(boolean disableElt) {
				this.disableElt = disableElt;
			}
			public int getTypeDossier() {
				return typeDossier;
			}
			public void setTypeDossier(int typeDossier) {
				this.typeDossier = typeDossier;
			}
			
			public List<SaisieSanctionC> getListRecours() {
				return listRecours;
			}
			public void setListRecours(List<SaisieSanctionC> listRecours) {
				this.listRecours = listRecours;
			}
			public List<SaisieSanctionC> getListDecision() {
				return listDecision;
			}
			public void setListDecision(List<SaisieSanctionC> listDecision) {
				this.listDecision = listDecision;
			}
			public int getTypeDecision() {
				return typeDecision;
			}
			public void setTypeDecision(int typeDecision) {
				this.typeDecision = typeDecision;
			}
			
			public List<SelectItem> getListTypeSanction() {
				return listTypeSanction;
			}
			public void setListTypeSanction(List<SelectItem> listTypeSanction) {
				this.listTypeSanction = listTypeSanction;
			}
			
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   @PostConstruct
   private void init() {
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
     }
				
			else{
				disableMsg=true;
				searchForm();
				parametre=FichierBaseDAO.getInstance().getParametrageGeneral();
				chargerSanction();
			}

 			}

			private void chargerSanction()
			{
				listParam=FichierBaseDAO.getInstance().getListeParametrageSanction();
				listTypeSanction=new ArrayList<SelectItem>();
				listTypeSanction.add(new SelectItem(0, ""));
				for (ParametrageSanctionC prm :listParam) {
					listTypeSanction.add(new SelectItem(prm.getId(),prm.getLibelleSanction()));
				}
			}
			private void searchForm()
			{
				 UIComponent frm = null;
				 FacesContext context = FacesContext.getCurrentInstance();
				  frm = context.getViewRoot().findComponent("frmSaisie");
				    if (frm != null)
				    {
				     this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.saisie));
				    }
				    frm = context.getViewRoot().findComponent("frmRec");
				     if (frm != null)
				     {
				    	 this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.recours));
				     }
				     frm = context.getViewRoot().findComponent("frmDecRec");
				     if (frm != null)
				     {
				    	 this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.decision));
				     }
				     frm = context.getViewRoot().findComponent("frmRecLine");
				     if (frm != null)
				     {
				    	 this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.recours));
				    	 employeInLine();
				    	 setInLine(true);
				    	 setDateRecours(new Date());
				    	 setDateRecoursS(HelperC.changeDateFormate(getDateRecours()));
				    	
				     }
			}

			public void chargerSaiaie()
			{
				listSaisie=FactoryDAO.getInstance().getListeSanction(Constante.getEtatSanction(Constante.EtatSanction.saisie));
				for (SaisieSanctionC saisie : listSaisie) {
					completerLibelle(saisie);
				}
			}
			private void employeInLine(){
				selection = FactoryDAO.getInstance().getEmploye(operateur.getIdEmploye(), false);
				if (this.selection != null) {
					affeterEmploye();
					 
				}
			}
			
			public void chargerSanctionEmploye() 
			{
				listSaisie= FactoryDAO.getInstance().getListeSanctionTraite(selection.getId());
				
			}
			public void chargerRecours(){
				switch (typeDossier) {
				case 1:
					listRecours= FactoryDAO.getInstance().getListeSaisieTraite(Constante.getEtatSanction(Constante.EtatSanction.recours), 0);
					break;

				case 2:
					chargerRetard();
					break;
				}
				if(listRecours!=null && listRecours.size()>0)
				for (SaisieSanctionC saisie : listRecours) {
					completerLibelle(saisie);
				}
			}
			public void chargerDecisions(){
				listDecision= FactoryDAO.getInstance().getListeSaisieTraite(Constante.getEtatSanction(Constante.EtatSanction.decision), typeDecision);
				for (SaisieSanctionC saisie : listDecision) {
					completerLibelle(saisie);
				}
			}
			
			private void chargerRetard()
			{
				int days=0;
				listRecours=new ArrayList<SaisieSanctionC>();
				listParam=FichierBaseDAO.getInstance().getListeParametrageSanction();
				for (ParametrageSanctionC prm : listParam) {
					
					//listRetard=FactoryDAO.getInstance().getListeSanctionRetard();
					for (SaisieSanctionC saisieSanction : listRetard) 
					{
						days=(int)HelperC.daysBetween(saisieSanction.getDateSaisie(), new Date());
						
						if(days>prm.getDureeCloture())
						{
							listRecours.add(saisieSanction);
						}
					}
				}
				
			}
			private void completerLibelle(SaisieSanctionC saisie) {
				
			}
			
			
			
	public void findByCode() {
		this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
		if (this.selection != null) {
			affeterEmploye();

		}
	}

	private void affeterEmploye() {
		setCode(this.selection.getCode());
		setEmploye(this.selection);
		chercherSalaire();
	}

	private void chercherSalaire() {
		traitement = FactoryDAO.getInstance().getSalaireActuel(selection,
				new Date());
		if (traitement != null)
			salaireActuel = traitement.getSalaireBase();
		else
			salaireActuel = selection.getSalaireBase();
		setSalaireBase(salaireActuel);
	}

			private void checkDays(){
				
				if(getDateDebutSanction()!=null && getDateFinSanction()!=null)
				{
					dureeSanction=(int)HelperC.daysBetween(getDateDebutSanction(), getDateFinSanction());
				}
			}
   private void clear1(boolean b) {
     if (b)
       this.selection = null; 
				code="";
   }
   
   private void setObject() {
				disableMsg=true;
     if (this.selected != null) {
       setId(this.selected.getId());
       setEmploye(this.selected.getEmploye());
       if (getEmploye() != null)
         setCode(getEmploye().getCode()); 
       setDateDebutSanction(this.selected.getDateDebutSanction());   
       setMotifDecision(this.selected.getMotifDecision());
       setDateSaisie(this.selected.getDateSaisie());
       setIdSanction(selected.getIdSanction());
				searchSanction();
				dateSaisieS=HelperC.convertDate(selected.getDateSaisie());
				setDateSaisie(selected.getDateSaisie());
				dateDebutSanctionS=HelperC.convertDate(selected.getDateDebutSanction());
				setDateDebutSanction(selected.getDateDebutSanction());
				dateFinSanctionS=HelperC.convertDate(selected.getDateFinSanction());
				setDateFinSanction(selected.getDateFinSanction());
				dateDecisionS=HelperC.convertDate(selected.getDateDecision());
				setDateDecision(selected.getDateDecision());
				dateRecoursS=HelperC.convertDate(selected.getDateRecours());
				setDateRecours(selected.getDateRecours());
				checkDays();
				setTauxRetenu(selected.getTauxRetenu());
				setMontantRetenu(selected.getMontantRetenu());
				setMoisPaie(selected.getMoisPaie());
				setMotifDecision(selected.getMotifDecision());
				setMotifRecours(selected.getMotifRecours());
				setMotifSanction(selected.getMotifSanction());
				setSalaireBase(selected.getSalaireBase());
				setMoisLettre(HelperC.moisEnLettres(selected.getMoisPaie()));			
				setCloture(selected.isCloture());
				disableMsg=false;
     } 
   }
 
 
   
   private void clear(boolean b) {
     this.code = "";
     setId(0);
     setEmploye(null);
     setDateDebutSanction(null);
     setDateDebutSanctionS("");
     setDateFinSanction(null);
     setDateFinSanctionS("");
     setDureeSanction(0);
     setIdSanction(0);
			  setTauxRetenu(0);
			  setSalaireBase(0);
			  setMontantRetenu(0);
			  setMoisPaie(0);
			  setMotifSanction("");
     disableSave=false;
     setDateRecours(null);
     setLibelleSanction("");
     setDateDecision(null);
     setMoisLettre("");
     setDateSaisie(null);
     setDateSaisieS("");
     setMotifRecours("");
     this.nomEmployeRecherche = "";
     this.prenomEmployeRecherche = "";
     this.selected = null;
			  setDecision(0);
			  dateDecisionS="";
			  setCloture(false);
			  setMotifDecision("");
			  disableMsg=true;
   }
 
   
   public void changeDateDebutSanction() {
     if (getDateDebutSanctionS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateDebutSanction(null);
     } else {
       
       setDateDebutSanction(HelperC.validerDate(getDateDebutSanctionS()));
       if (getDateDebutSanction() == null) {
         setDateDebutSanctionS("");
         
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         checkDays();
				  calculRetenu();
         setDateDebutSanctionS(HelperC.convertDate(getDateDebutSanction()));
        
       } 
     } 
   }
 
 
   
   public void changeDateSaisie() {
     if (getDateSaisieS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateSaisie(null);
     } else {
       
       setDateSaisie(HelperC.validerDate(getDateSaisieS()));
       if (getDateSaisie() == null) {
         setDateSaisieS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         setDateSaisieS(HelperC.convertDate(getDateSaisie()));
       } 
     } 
   }
   
   public void changeDateRecours() {
     if (getDateRecoursS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateRecours(null);
     } else {
       
       setDateRecours(HelperC.validerDate(getDateRecoursS()));
       if (getDateRecours() == null) {
         setDateRecoursS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         setDateRecoursS(HelperC.convertDate(getDateRecours()));
				  checkRecours();
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
   
   public void changeDateFinSanction() {
     if (getDateFinSanctionS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateDebutSanction(null);
     } else {
       
       setDateFinSanction(HelperC.validerDate(getDateFinSanctionS()));
       if (getDateFinSanction() == null) {
         setDateFinSanctionS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         checkDays();
				  calculRetenu();
         setDateFinSanctionS(HelperC.convertDate(getDateFinSanction()));
       } 
     } 
   }
   
   public void changeSanction(ValueChangeEvent event) {
			setIdSanction((Integer)event.getNewValue());
			searchSanction();
			}

			private void searchSanction() {
				parm=FichierBaseDAO.getInstance().getParametrageSanction(getIdSanction());
				if(parm!=null)
				{
					if(parm.getTauxRetenue()>0 && parm.getIdRetenu()>0)
					{
						setTauxRetenu(parm.getTauxRetenue());					
					}
				}
			}


			private void checkRecours()
			{
				int nbJr=0;
				disableSave=false;
				if(parm==null)
					parm=FichierBaseDAO.getInstance().getParametrageSanctionParType(getIdSanction());
				if(parm!=null)
				{
					if(getDateDebutSanction()!=null && getDateRecours()!=null)
					{
						if(getDateRecours().after(getDateDebutSanction()) && getDateRecours().before(getDateFinSanction()))
						{
						nbJr=(int)HelperC.daysBetween(getDateDebutSanction(), getDateRecours());
						if(nbJr>parm.getDureeRecours())
						{
							HelperC.afficherAttention("Information", "La durée de recours doit étre inférieures ou égale é "+parm.getDureeRecours()+" jours");
							disableSave=true;
							return;
						}
						}
						else {
							HelperC.afficherAttention("Information", "La date recours doit étre entre la date début sanction et la date fin sanction");
							disableSave=true;
							return;
						}
						
					}
				}
			}
			private void calculRetenu(){
				disableSave=false;
				double montantJr=0,montantPeriod=0;
				if(parm!=null)
				{
					if(dureeSanction>0)
					{
						if(dureeSanction<parm.getDureeMin() && parm.getDureeMin()>0)
						{
							HelperC.afficherAttention("Information", "La durée doit étre supérieure ou égale é "+parm.getDureeMin()+" jours");
							disableSave=true;
							return;
						}
						
						if(dureeSanction>parm.getDureeMax() && parm.getDureeMax()>0)
						{
							HelperC.afficherAttention("Information", "La durée doit étre inférieure é ou égale "+parm.getDureeMax()+" jours");
							disableSave=true;
							return;
						}
					
						if(dureeSanction>=parm.getDureeMin() && dureeSanction<=parm.getDureeMax()){
							if(parametre!=null)
							{
								montantJr=salaireActuel/parametre.getNbreJourMois();
								montantPeriod=montantJr*dureeSanction;
								setMontantRetenu(montantPeriod*parm.getTauxRetenue()/100);
							
							}
					}
				  }
				}
			}
				
   public void onRowSelected(SelectEvent event) {
     this.selected = (SaisieSanctionC)event.getObject();
     if (this.selected != null) {
       setObject();
       PrimeFaces.current().executeScript("PF('dlgSct').hide();");
     } 
   }
 

		public void onRowRecoursSelected(SelectEvent event) {
		    this.selected = (SaisieSanctionC)event.getObject();
		  if (this.selected != null) {
		      setObject();
		       PrimeFaces.current().executeScript("PF('dlgRec').hide();");
		     } 
		   }
		
	public void onRecoursLineSelected(SelectEvent event) {
		this.selected = (SaisieSanctionC) event.getObject();
		if (this.selected != null) {
			setObject();
			setInLine(true);
			if (getDateRecours() == null) {

				setDateRecours(new Date());
				setDateRecoursS(HelperC.changeDateFormate(getDateRecours()));
				checkRecours();
			}
			PrimeFaces.current().executeScript("PF('dlgSct').hide();");
		}
	}
		
		public void onRowDecisionSelected(SelectEvent event) {
		    this.selected = (SaisieSanctionC)event.getObject();
		  if (this.selected != null) {
		      setObject();
		       PrimeFaces.current().executeScript("PF('dlgDec').hide();");
		     } 
		   }
 
   
   public void onRowselected1(SelectEvent event) {
     this.selection = (EmployeC)event.getObject();
      affeterEmploye();
     PrimeFaces.current().executeScript("PF('dlg').hide();");
   }
 
 
 
 
   
   public void chargerEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, 
         this.nomEmployeRecherche,true);
   }


   public void enregistrerRecours() {
     if (getDateRecours() == null) {
       HelperC.afficherMessage("Information", "Veillez saisir la date du recours");
     } 
      else {
      
 
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Recours des mesures disciplinaires de l'employé " + getEmploye().getNomPrenom());
       } else {
         hist.setOperation("Modification Recours mesures disciplinaires de l'employé " + getEmploye().getNomPrenom());
       }  hist.setTable(Tables.getTableName(Tables.TableName.saisiesanction));
       setHistorique(hist);
				this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.recours));
				
       if (FactoryDAO.getInstance().insertUpdateSaisieSanction(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }

	public void enregistrerRecoursLine() {
		if (getDateRecours() == null) {
			HelperC.afficherMessage("Information", "Veillez saisir la date du recours");
		} else {

			if (!getMotifRecours().trim().equals("")) {
				Historique hist = new Historique();
				hist.setDateOperation(Calendar.getInstance().getTime());
				hist.setOperateur(this.operateur);
				if (getId() == 0) {
					hist.setOperation("Recours sur la sanction de l'employé " + getEmploye().getNomPrenom());
				} else {
					hist.setOperation("Modification sur la sanction de l'employé " + getEmploye().getNomPrenom());
				}
				hist.setTable(Tables.getTableName(Tables.TableName.saisiesanction));
				setHistorique(hist);
				this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.recours));
				this.setInLine(true);
				if (FactoryDAO.getInstance().insertUpdateSaisieSanction(this)) {
					HelperC.afficherMessage("Information", "Succès de l'opération");
					clear(true);
				} else {

					HelperC.afficherMessage("Désolé", "Echec de l'opération ");
				}
			}
			else
				HelperC.afficherAttention("Désolé", "Il faut préciser le motif ");
		}
	}
		
   public void enregistrerDecision() {
     if (getEmploye() == null) {
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     } 
     else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Décision sur la sanction de l'employé " + getEmploye().getNomPrenom());
       } else {
         hist.setOperation("Modification de la décision sur la sanction de l'employé " + getEmploye().getNomPrenom());

       }  hist.setTable(Tables.getTableName(Tables.TableName.saisiesanction));
       setHistorique(hist);
				this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.decision));
				this.setIdOperateur(operateur.getId());
				
       if (FactoryDAO.getInstance().insertUpdateSaisieSanction(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }

	public void enregistrerSaisie() {
		if (getEmploye() == null) {
			HelperC.afficherMessage("Information", "Veillez saisir l'employé");
			return;
		}
		if (getMotifSanction() == "") {
			HelperC.afficherMessage("Information", "Veillez décrire la raison de la sanction");
			return;
		}
		if (getDateDebutSanction() == null) {
			HelperC.afficherAttention("Information", "Veillez préciser la date début de la sanction");
			return;
		}
		if (getDateDebutSanction().after(getDateFinSanction())) {
			HelperC.afficherAttention("Information",
					"Veillez la date de début ne peut pas etre supérieur é la date fin");
			return;
		}

		if (parm.getIdRetenu() > 0 && getMoisPaie() == 0) {
			HelperC.afficherAttention("Information", "Veillez préciser le mois de paie pour la retenu");
			return;
		}
		
		Historique hist = new Historique();
		hist.setDateOperation(Calendar.getInstance().getTime());
		hist.setOperateur(this.operateur);
		if (getId() == 0) {
			hist.setOperation(
					"Saisie de la sanction de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
		} else {
			hist.setOperation("Modification sur la sanction de l'employé " + getEmploye().getNom() + " "
					+ getEmploye().getPrenom());
		}
		hist.setTable(Tables.getTableName(Tables.TableName.saisiesanction));
		setHistorique(hist);
		this.setIdOperateur(operateur.getId());
		this.setIdExercice(exercice.getId());
		this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.saisie));
		
		if (FactoryDAO.getInstance().insertUpdateSaisieSanction(this)) {

			HelperC.afficherMessage("Information", "Succès de l'opération");
			clear(true);
		} else {

			HelperC.afficherMessage("Désolé", "Echec de l'opération ");
		}
	}
   
 
   public void supprimer() {
     if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     }
			else {
				if(selected.getEtat()!=Constante.getEtatSanction(Constante.EtatSanction.saisie))
					 HelperC.afficherAttention("Information", "On ne peut pas supprimer cette sanction car il est déjé utilisé ailleur!");
				else 
				{
      if (FactoryDAO.getInstance().deleteSaisieSanction(this)) {
       clear(true);
       HelperC.afficherMessage("Information", "Succès de l'opération ");
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
				}
   }
 }
   
   public void initialiser() {
     clear(true);
   }
 
   
  
			public void annulerRecours(){
				if(selected.getDecision()==0)
				{
				if(FactoryDAO.getInstance().annulerRecourSanction(selected))
				{
					 clear(true);
				     HelperC.afficherMessage("Information", "Succès de l'opération ");
				}
				else
					 HelperC.afficherMessage("Désolé", "Echec de suppression");
				}
				else
					 HelperC.afficherAttention("Attention", "Impossible d'annuler le recours car la décision est déjé prise !");
			}
			
			public void annulerDecision(){
				if(FactoryDAO.getInstance().annulerDecisionSanction(selected))
				{
					 clear(true);
				     HelperC.afficherMessage("Information", "Succès de l'opération ");
				}
				else
					 HelperC.afficherMessage("Désolé", "Echec de suppression");
			}
 }

