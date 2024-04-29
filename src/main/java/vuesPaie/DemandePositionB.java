 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.CategoriePersonnelC;
 import classesPaie.ConditionPositionC;
 import classesPaie.Constante;
import classesPaie.DetailPrimeEmployeC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
import classesPaie.GradePersonnelC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
import classesPaie.ParametragePositionC;
import classesPaie.ParametragePositionDetailC;
import classesPaie.SaisiePositionDetailPrimeC;
 import classesPaie.SaisiePositionEmployeC;
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
 public class DemandePositionB
   extends SaisiePositionEmployeC
 {
   private static final long serialVersionUID = -6034101436213977045L;
   private int idPosition,idPersonel,idCateg,idGrad,idFonct;
   private int idCondition,typeDecision;
   private Constante.Position position;
   private SaisiePositionEmployeC selected;
   private EmployeC selection;
   private CategoriePersonnelC categoriePersonnel;
   private Base personnel;
   private List<SaisiePositionEmployeC> listDemande,listPosition;
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
   
			private OperateurC operateur;
   private List<SelectItem> listConditions = new ArrayList<SelectItem>(); 
			private ExerciceC exercice;
   private HttpSession session = HelperC.getSession(); 
			private String code,libelleDecision; 
			private String codeEmployeRecherche;
   private String nomEmployeRecherche;
   private String prenomEmployeRecherche;
   private boolean disableSave;
            private  SaisiePositionDetailPrimeC selectedDetailPrime;
            double ancienSal=0,nouveauSal=0;
            private List<SelectItem> listPrsnl,listFonct,listGrd,listCateg;
            TraitementSalarialC traitementSal;
            List<DetailPrimeEmployeC> listPrimeEmpl;
            ParametrageGeneralC parm;
			ParametragePositionC parmPos;
			boolean onLine;
			
   public int getIdCondition() {
     return this.idCondition;
   }
 
   
   public void setIdCondition(int idCondition) {
     this.idCondition = idCondition;
   }
 
   
   public Constante.Position getPosition() {
     return this.position;
   }
 
   
   public void setPosition(Constante.Position position) {
     this.position = position;
   }
 
   
   public int getIdPosition() {
     return this.idPosition;
   }
 
   
   public void setIdPosition(int idPosition) {
     this.idPosition = idPosition;
   }
 
   
   public SaisiePositionEmployeC getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(SaisiePositionEmployeC selected) {
     this.selected = selected;
   }
 
   
   public EmployeC getSelection() {
     return this.selection;
   }
 
   
   public void setSelection(EmployeC selection) {
     this.selection = selection;
   }
 
   
   public CategoriePersonnelC getCategoriePersonnel() {
     return this.categoriePersonnel;
   }
 
   
   public void setCategoriePersonnel(CategoriePersonnelC categoriePersonnel) {
     this.categoriePersonnel = categoriePersonnel;
   }
 
   
   public Base getPersonnel() {
     return this.personnel;
   }
 
   
   public void setPersonnel(Base personnel) {
     this.personnel = personnel;
   }
   
   public List<SaisiePositionEmployeC> getListDemande() {
     return this.listDemande;
   }
   
   public void setListDemande(List<SaisiePositionEmployeC> listDemande) {
     this.listDemande = listDemande;
   }
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
   

   public List<SelectItem> getListConditions() {
     return this.listConditions;
   }
   
   public void setListConditions(List<SelectItem> listConditions) {
     this.listConditions = listConditions;
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

		public boolean isDisableSave() {
			return disableSave;
		}
	
		public void setDisableSave(boolean disableSave) {
			this.disableSave = disableSave;
		}
		
		public int getTypeDecision() {
			return typeDecision;
		}
		public void setTypeDecision(int typeDecision) {
			this.typeDecision = typeDecision;
		}
		public SaisiePositionDetailPrimeC getSelectedDetailPrime() {
			return selectedDetailPrime;
		}
		public void setSelectedDetailPrime(
				SaisiePositionDetailPrimeC selectedDetailPrime) {
			this.selectedDetailPrime = selectedDetailPrime;
		}
		public List<SaisiePositionEmployeC> getListPosition() {
			return listPosition;
		}
		public void setListPosition(List<SaisiePositionEmployeC> listPosition) {
			this.listPosition = listPosition;
		}
		
		public List<SelectItem> getListPrsnl() {
			return listPrsnl;
		}
		public void setListPrsnl(List<SelectItem> listPrsnl) {
			this.listPrsnl = listPrsnl;
		}
		public List<SelectItem> getListFonct() {
			return listFonct;
		}
		public void setListFonct(List<SelectItem> listFonct) {
			this.listFonct = listFonct;
		}
		public List<SelectItem> getListGrd() {
			return listGrd;
		}
		public void setListGrd(List<SelectItem> listGrd) {
			this.listGrd = listGrd;
		}
		public List<SelectItem> getListCateg() {
			return listCateg;
		}
		public void setListCateg(List<SelectItem> listCateg) {
			this.listCateg = listCateg;
		}
		public int getIdPersonel() {
			return idPersonel;
		}
		public void setIdPersonel(int idPersonel) {
			this.idPersonel = idPersonel;
		}
		public int getIdCateg() {
			return idCateg;
		}
		public void setIdCateg(int idCateg) {
			this.idCateg = idCateg;
		}
		public int getIdGrad() {
			return idGrad;
		}
		public void setIdGrad(int idGrad) {
			this.idGrad = idGrad;
		}
		public int getIdFonct() {
			return idFonct;
		}
		public void setIdFonct(int idFonct) {
			this.idFonct = idFonct;
		}
		
		public String getLibelleDecision() {
			return libelleDecision;
		}
		public void setLibelleDecision(String libelleDecision) {
			this.libelleDecision = libelleDecision;
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
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
				listPrsnl=  new ArrayList<SelectItem>();
				listCateg=new ArrayList<SelectItem>();
				listGrd=new ArrayList<SelectItem>();
				listFonct=new ArrayList<SelectItem>();
			
				listGrd.add(new SelectItem(Integer.valueOf(0), ""));
				listCateg.add(new SelectItem(Integer.valueOf(0), ""));
				listPrsnl.add(new SelectItem(Integer.valueOf(0), ""));
				listFonct.add(new SelectItem(Integer.valueOf(0), ""));
				
       this.listConditions.add(new SelectItem(Integer.valueOf(0), ""));
				chargerPersonnel();
				chargerFonction();
				parm=FichierBaseDAO.getInstance().getParametrageGeneral();
				
       setDateDemandePosition(new Date());
       setDateDemandePositionS(HelperC.changeDateFormate(getDateDemandePosition()));
				UIComponent frm = null;
				FacesContext context = FacesContext.getCurrentInstance();
				frm = context.getViewRoot().findComponent("frmDm");
				if (frm != null)
				{
					setEtat(Constante.getEtatPosition(Constante.EtatPosition.demandePosition));
				}
				     
				frm = context.getViewRoot().findComponent("frmPos");
				if (frm != null)
				{
					setEtat(Constante.getEtatPosition(Constante.EtatPosition.decide));
				}
				
				frm = context.getViewRoot().findComponent("frmPosLine");
				if (frm != null)
				{
					setEtat(Constante.getEtatPosition(Constante.EtatPosition.demandePosition));
					onLine=true;
					employeInLine();
				}
				
				
     } 
   }

			private void employeInLine(){
				selection = FactoryDAO.getInstance().getEmploye(operateur.getIdEmploye(), false);
				if (this.selection != null) {
				      setCode(this.selection.getCode());
					  setEmploye(this.selection);
					  idPersonel=selection.getIdPersnl();
					  idCateg=selection.getIdCatgrie();
					  idGrad=selection.getIdGrd();
					  idFonct=selection.getIdFnctn();
				}
			}

			private void chargerPersonnel() {
				listPrsnl.clear();
				listPrsnl.add(new SelectItem(Integer.valueOf(0), ""));
			       
			     for (Base personnel : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel)))
			      {
			         listPrsnl.add(new SelectItem(Integer.valueOf(personnel.getId()), personnel.getDesignation()));
			      }
			}
			
			private void chargerCategorie() {
				listCateg.clear();
				listCateg.add(new SelectItem(Integer.valueOf(0), ""));
			       
			     for (CategoriePersonnelC cat : FichierBaseDAO.getInstance().getListCategoriePersonnelParIdPersonnel(idPersonel))
			      {
			    	 listCateg.add(new SelectItem(Integer.valueOf(cat.getId()), cat.getDesignation()));
			      }
			}
			
			private void chargerGrade() {
				listGrd.clear();
				listGrd.add(new SelectItem(Integer.valueOf(0), ""));
			       
			     for (GradePersonnelC grd : FichierBaseDAO.getInstance().getListGradeParCategoriePersonnel(idCateg))
			      {
			    	 listGrd.add(new SelectItem(Integer.valueOf(grd.getId()), grd.getDesignation()));
			      }
			}
			
			private void chargerFonction() {
				listFonct.clear();
				listFonct.add(new SelectItem(Integer.valueOf(0), ""));
			       
			     for (Base fx : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction)))
			      {
			    	 listFonct.add(new SelectItem(Integer.valueOf(fx.getId()), fx.getDesignation()));
			      }
			}

		public void changePersonnel(ValueChangeEvent event) {
			idPersonel = ((Integer) event.getNewValue()).intValue();
			if (idPersonel != 0) {
				chargerCategorie();
				setIdPrs(idPersonel);
			}
		}
		
		public void changeCategorie(ValueChangeEvent event) {
			idCateg = ((Integer) event.getNewValue()).intValue();
			if (idCateg != 0) {
				setIdCtg(idCateg);
				chargerGrade();
			}
		}
		
		public void changeGrade(ValueChangeEvent event) {
			idGrad = ((Integer) event.getNewValue()).intValue();
			if (idGrad != 0) {
				setIdGrd(idGrad);
			}
		}
		public void changeFonction(ValueChangeEvent event) {
			idFonct = ((Integer) event.getNewValue()).intValue();
			if (idFonct != 0) {
				setIdFx(idFonct);
			}
		}
		

   
   public void findByCode() {
     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
     if (this.selection != null) {
       
       setCode(this.selection.getCode());
       setEmploye(this.selection);
				idPersonel=selection.getIdPersnl();
				chargerCategorie();
				idCateg=selection.getIdCatgrie();
				chargerGrade();
				idGrad=selection.getIdGrd();
				idFonct=selection.getIdFnctn();
				setIdCtg(idCateg);
				setIdPrs(idPersonel);
				setIdFx(idFonct);
				setIdGrd(idGrad);
				
     } else {
       
       clear1(true);
     } 
   }
 
   
   private void clear1(boolean b) {
     if (b)
     {
       this.selection = null;
     }
   }
 
   
   public void changePosition(ValueChangeEvent event) {
     this.listConditions.clear();
     this.idPosition = ((Integer)event.getNewValue()).intValue();
     if (this.idPosition != 0) {
       
       setPosition(Constante.getPosition(this.idPosition));     
     	chargerCondition();
     } 
   }
 
  private void chargerCondition() {
			listConditions.clear();
			listConditions.add(new SelectItem(Integer.valueOf(0), ""));
     for (ConditionPositionC condi : FichierBaseDAO.getInstance().getListeConditionPosition(idPosition))
       listConditions.add(new SelectItem(Integer.valueOf(condi.getId()), condi.getCondition())); 
   }
   
   public void changeCondition(ValueChangeEvent event) {
     this.idCondition = ((Integer)event.getNewValue()).intValue();
     if (this.idCondition != 0)
     {
       setConditionPosition(FichierBaseDAO.getInstance().getConditionPosition(this.idCondition));
				chargerPosition();
     }
   }
 
   
   private void setObject() {
     if (this.selected != null) {
       
       setId(this.selected.getId());
               
       setEmploye(this.selected.getEmploye());
       if (getEmploye() != null)
       {
         setCode(getEmploye().getCode());
				  selection=selected.getEmploye();
       }
       setPosition(this.selected.getConditionPosition().getPosition());
       if (getPosition() != null) {
         
         this.idPosition = Constante.getPosition(getPosition());
         chargerCondition();
         
       } 
       
       setConditionPosition(this.selected.getConditionPosition());
       if (getConditionPosition() != null)
       {
         this.idCondition = getConditionPosition().getId();
       }
				
       setDateDemandePosition(this.selected.getDateDemandePosition());
       setDateDemandePositionS(this.selected.getDateDemandePositionS());
				setDateDebut(selected.getDateDebut());
				setDateFin(selected.getDateFin());
				setDateDebutS(selected.getDateDebutS());
				setDateFinS(selected.getDateFinS());
				setDecision(selected.getDecision()); 
       setDuree(this.selected.getDuree());
       setMotifDemande(this.selected.getMotifDemande());		
				setMotifRefus(selected.getMotifRefus());
				
				switch (getDecision()) {
				case 1:
					libelleDecision="Demande accéptée ";
					break;
				case 2:
					libelleDecision="Demande refusée ";
					break;
				}
			
				if(selected.getEtat()==Constante.getEtatPosition(Constante.EtatPosition.demandePosition) && selected.getDecision()==0)
				{
					idPersonel=selected.getEmploye().getIdPersnl();
					chargerCategorie();
					idCateg=selected.getEmploye().getIdCatgrie();
					chargerGrade();
					idGrad=selected.getEmploye().getIdGrd();
					idFonct=selected.getEmploye().getIdFnctn();
					
					listPrimeEmpl=FactoryDAO.getInstance().getListeDetailPrime(selection);
					chargerPosition();
					getParmInfo();
				}
				else{
					
					idPersonel=selected.getIdPrs();
					chargerCategorie();
					idCateg=selected.getIdCtg();
					chargerGrade();
					idGrad=selected.getIdGrd();
					idFonct=selected.getIdFx();
					
					setAjoutAllocationFamiliale(selected.isAjoutAllocationFamiliale());
        			setAjoutIndemniteLogement(selected.isAjoutIndemniteLogement());
        			setAvancementGrade(selected.isAvancementGrade());
        			setAvancementTraitement(selected.isAvancementTraitement());
        			setNouveauSalaire(selected.getNouveauSalaire());
        			setAncienSalaire(selected.getAncienSalaire());
        			setTaux(selected.getTaux());
           			traitementSal=FactoryDAO.getInstance().getTraitementSalarial(selection.getId(), Constante.getTypeAvancement(Constante.TypeAvancement.changementPosition), selected.getId());
           			if(traitementSal!=null)
           	    	 setTraitement(traitementSal);
           			setListeDetailPrime(selected.getListeDetailPrime());
				}
				setIdCtg(idCateg);
				setIdPrs(idPersonel);
				setIdFx(idFonct);
				setIdGrd(idGrad);

     } 
   }
 
   
   private void clear() {
     setId(0);
     setEmploye(null);
     setConditionPosition(null);
     setDateDemandePosition(null);
     setEtat(0);
     setDuree(0);
     setMotifDemande("");
			  setDateDebutS("");
			  setDateFinS("");
			  setDateDemandePositionS("");
			  setDuree(0);
			  listConditions.clear();
			  listConditions.add(new SelectItem(Integer.valueOf(0), ""));
     this.listConditions.clear();
     this.idCondition = 0;
     this.idPosition = 0;
     this.nomEmployeRecherche = "";
     this.code = "";
     this.prenomEmployeRecherche = "";
     this.selected = null;
			  idPersonel=0;
				idCateg=0;
				idGrad=0;
				idFonct=0;
				setIdCtg(0);
				setIdPrs(0);
				setIdFx(0);
				setIdGrd(0);
			   setAjoutAllocationFamiliale(false);
			   setAjoutIndemniteLogement(false);
			   setAvancementGrade(false);
			   setAvancementTraitement(false);
			   setNouveauSalaire(0);
   			   setAncienSalaire(0);
   			   setTaux(0);
   			   traitementSal=null;
   			   nouveauSal=0;ancienSal=0;parmPos=null;
   			   this.getListeDetailPrime().clear();
   }
 


	private void clearInline() {
		setId(0);
		setConditionPosition(null);
		setDateDemandePosition(null);
		setEtat(0);
		setDuree(0);
		setMotifDemande("");
		setDateDebutS("");
		setDateFinS("");
		setDateDemandePositionS("");
		setDuree(0);
		listConditions.clear();
		listConditions.add(new SelectItem(Integer.valueOf(0), ""));
		this.listConditions.clear();
		this.idCondition = 0;
		this.idPosition = 0;		
		this.selected = null;
		setAjoutAllocationFamiliale(false);
		setAjoutIndemniteLogement(false);
		setAvancementGrade(false);
		setAvancementTraitement(false);
		setNouveauSalaire(0);
		setAncienSalaire(0);
		setTaux(0);
		traitementSal = null;
		nouveauSal = 0;
		ancienSal = 0;
		parmPos = null;
		idPersonel=0;
		idCateg=0;
		idGrad=0;
		idFonct=0;
		setIdCtg(0);
		setIdPrs(0);
		setIdFx(0);
		setIdGrd(0);
		
	}
	
	
	
   public void changeDateDemandePosition() {
     if (getDateDemandePositionS().replace("/", "").replace("_", "").trim().equals("")) {
       
       setDateDemandePosition(null);
     } else {
       
       setDateDemandePosition(HelperC.validerDate(getDateDemandePositionS()));
       if (getDateDemandePosition() == null) {
         
         setDateDemandePositionS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         setDateDemandePositionS(HelperC.convertDate(getDateDemandePosition()));
       } 
     } 
   }
 
   
   public void changeDateDebut() {
     if (getDateDebutS().replace("/", "").replace("_", "").trim().equals("")) {
       
       setDateDebut(null);
     } else {
       
       setDateDebut(HelperC.validerDate(getDateDebutS()));
       if (getDateDebut() == null) {
         
         setDateDebutS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         setDateDebutS(HelperC.convertDate(getDateDebut()));
					//searchPeriod();
       } 
     } 
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
				  //searchPeriod();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (SaisiePositionEmployeC)event.getObject();
     if (this.selected != null)
     {
       setObject();
				PrimeFaces.current().executeScript("PF('dlgDemande').hide();");
     }
   }
 
			public void onRowDmdSelected(SelectEvent event) {
     this.selected = (SaisiePositionEmployeC)event.getObject();
     if (this.selected != null)
     {
       setObject();
				PrimeFaces.current().executeScript("PF('dlgDem').hide();");
     }
   }
   public void onRowDecidSelected(SelectEvent event) {
	     this.selected = (SaisiePositionEmployeC)event.getObject();
	     if (this.selected != null)
	     {
	       setObject();
					PrimeFaces.current().executeScript("PF('dlgDec').hide();");
	     }
	   }
   private void setObject1() {
     if (this.selection != null) {
       
       setEmploye(this.selection);
			if (getEmploye() != null) {
			
			    setCode(getEmploye().getCode());
				idPersonel = selection.getIdPersnl();
				chargerCategorie();
				idCateg = selection.getIdCatgrie();
				chargerGrade();
				idGrad = selection.getIdGrd();
				idFonct = selection.getIdFnctn();
				setIdCtg(idCateg);
				setIdPrs(idPersonel);
				setIdFx(idFonct);
				setIdGrd(idGrad);
				
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
   
            private void chargerPosition(){
            	disableSave=false;
            	if(selection!=null)
            	{
            		parmPos=FichierBaseDAO.getInstance().getParametrePosition(idCondition, selection.getIdPersnl());
            		
            		if(parmPos==null)
            		{
            			disableSave=true;
            			HelperC.afficherAttention("Information", "Il faut paramétrer les informations en rapport avec cette condition pour ce personnel !");
            		}
            	}
            }
            
            
  
            private void getParmInfo(){
            	int id=0;
            	if(parmPos!=null)
        		{
        			setAjoutAllocationFamiliale(parmPos.isAjoutAllocationFamiliale());
        			setAjoutIndemniteLogement(parmPos.isAjoutAllocationLogement());
        			setAvancementGrade(parmPos.isAvancementGrade());
        			setAvancementTraitement(parmPos.isAvancementTraitement());
        			TraitementSalarialC salAc=FactoryDAO.getInstance().getSalaireActuel(selection, new Date());
        			
        			 if(salAc!=null)
        				 ancienSal=salAc.getSalaireBase();
        			 else
        				ancienSal=selection.getSalaireBase();
        			nouveauSal=(ancienSal*parmPos.getTauxSalaire()/100);
        			setNouveauSalaire(nouveauSal);
        			setAncienSalaire(ancienSal);
        			setTaux(parmPos.getTauxSalaire());
        			
        			for (ParametragePositionDetailC det : parmPos.getListDetailPrime()) {
        				
						selectedDetailPrime=new SaisiePositionDetailPrimeC();
						selectedDetailPrime.setIdParm(det.getIdParm());
						selectedDetailPrime.setPrime(FichierBaseDAO.getInstance().getPrimeIndemnite(det.getIdPrime()));
						id++;
						selectedDetailPrime.setIndexe(id);
						selectedDetailPrime.setKept(true);
						this.getListeDetailPrime().add(selectedDetailPrime);
					
					}
        			if(listPrimeEmpl!=null && listPrimeEmpl.size()>0)
        			for (DetailPrimeEmployeC detPm : listPrimeEmpl) 
        			{
        				if(!isExistPrime(detPm.getPrime().getId()))
        				{
        					id++;
        				selectedDetailPrime=new SaisiePositionDetailPrimeC();
						selectedDetailPrime.setIdParm(detPm.getIdParametre());
						selectedDetailPrime.setPrime(detPm.getPrime());
						selectedDetailPrime.setIndexe(id);
						selectedDetailPrime.setKept(true);
						this.getListeDetailPrime().add(selectedDetailPrime);
						
        				}
					}
        			traitementSal=FactoryDAO.getInstance().getTraitementSalarial(selection.getId(), Constante.getTypeAvancement(Constante.TypeAvancement.changementPosition), selected.getId());
       		    	if(traitementSal==null)
        				traitementSal=new TraitementSalarialC();
        			   traitementSal.setAncienSalaire(ancienSal);
        			   traitementSal.setSalaireBase(nouveauSal);
        			   traitementSal.setDateDebut(getDateDebut());
        			   traitementSal.setEmploye(selection);
        			   traitementSal.setTypeAvancement(Constante.getTypeAvancement(Constante.TypeAvancement.changementPosition));
        			   traitementSal.setComment("CHANGEMENT POSITION : "+getConditionPosition().getLibellePosition());
        			   if(traitementSal.getAncienSalaire()>0 && traitementSal.getSalaireBase()>0)
        			   setTraitement(traitementSal);
        		}
            }
            
            private boolean isExistPrime(int idPrm) {
				boolean b=false;
				for (SaisiePositionDetailPrimeC det : getListeDetailPrime()) {
					if(det.getPrime().getId()==idPrm)
					{
						b=true;
						break;
					}
				}
				return b;
			}
            public void chargerEmploye() {
            	this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,true);
            }
 
			public void chargement(){
				listDemande=FactoryDAO.getInstance().getListeSaisiePositionEmploye(Constante.getEtatPosition(Constante.EtatPosition.demandePosition));
			}
			
			public void chargerPositionDecide()
			{
				listPosition=FactoryDAO.getInstance().getListePositionEmploye(typeDecision, Constante.getEtatPosition(Constante.EtatPosition.decide));
			}
			
			public void chargerPositionLine()
			{
				if(getEmploye()!=null)
					listPosition=FactoryDAO.getInstance().getListeSaisiePositionLine(getEmploye().getId());
			}
   
   public void enregistrer() {
     if (getEmploye() == null) {
       
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     }
     else if (getDateDemandePosition() == null) {
       
       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
     }
     else if (getConditionPosition() == null) {
       
       HelperC.afficherMessage("Information", "Veillez séléctionner la condition de cette position");
     }
     else if (getMotifDemande().equalsIgnoreCase("")) {
       
       HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création de la demande de position de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } else {
         
         hist.setOperation("Modification de la demande de position de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.saisiePositionEmploye));
       setHistorique(hist);
				setEtat(Constante.getEtatPosition(Constante.EtatPosition.demandePosition));
       if (FactoryDAO.getInstance().insertUpdateSaisiePositionEmploye(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
        
         clear();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
			public void saveDecision() {
     if (getEmploye() == null) {
       
       HelperC.afficherMessage("Information", "Veillez saisir l'employé");
     }
     else if (getDateDemandePosition() == null) {
       
       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
     }
     else if (getConditionPosition() == null) {
       
       HelperC.afficherMessage("Information", "Veillez séléctionner la condition de cette position");
     }
     else if (getDecision()==0) {
       
       HelperC.afficherAttention("Information", "Veillez préciser la décision prise !");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Décision demande de position de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } else {
         
         hist.setOperation("Modification de la Décision de la demande de position de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.saisiePositionEmploye));
       setHistorique(hist);
			
				if(getDateDecision()==null)
				 setDateDecision(new Date());
				 setEtat(Constante.getEtatPosition(Constante.EtatPosition.decide));
				 
       if (FactoryDAO.getInstance().insertUpdateSaisiePositionEmploye(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
        if(parm!=null) 
					{
					if(getDecision()==1)
					     HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(), parm.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de position a été acceptée ", "Demande position");
					if(getDecision()==2)
					     HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(), parm.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de position a été refusée\nMotif : "+getMotifRefus(), "Demande position");
					}
         clear();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }

	public void saveInLine() {
		if (getEmploye() == null) {

			HelperC.afficherMessage("Information", "Veillez saisir l'employé");
		} else if (getDateDemandePosition() == null) {

			HelperC.afficherMessage("Information",
					"Veillez d'abord saisir la date de demande");
		} else if (getConditionPosition() == null) {

			HelperC.afficherMessage("Information",
					"Veillez séléctionner la condition de cette position");
		}  else {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {

				hist.setOperation("Décision demande de position de l'employé "
						+ getEmploye().getNomPrenom());
			} else {

				hist.setOperation("Modification de la Décision de la demande de position de l'employé "
						+ getEmploye().getNomPrenom());
			}
			hist.setTable(Tables
					.getTableName(Tables.TableName.saisiePositionEmploye));
			setHistorique(hist);

			if (getDateDecision() == null && getDecision()==0) {
				setEtat(Constante
						.getEtatPosition(Constante.EtatPosition.demandePosition));
                 setInLine(true);
                
				if (FactoryDAO.getInstance().insertUpdateSaisiePositionEmploye(
						this)) {

					HelperC.afficherMessage("Information",
							"Succès de l'opération");
					
					clearInline();
				} else {
					HelperC.afficherMessage("Désolé", "Echec de l'opération ");
				}
			} else {
				
				HelperC.afficherAttention("Information",
						"La demande a été déjé traitée !");
			}
		}
	}
			public void annuler() {
		    if(selected!=null)
		    {
		    	 Historique hist = new Historique();
		    	 hist.setDateOperation(Calendar.getInstance().getTime());
		    	 hist.setOperateur(this.operateur);
		    	
		    	
		          hist.setOperation("Annulation de la Décision de la demande de position de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
		    	    
		    	    hist.setTable(Tables.getTableName(Tables.TableName.saisiePositionEmploye));
		    	    selected.setHistorique(hist);
		    	  		
		    	if(FactoryDAO.getInstance().annulerPositionEmploye(selected))
				{
		    		HelperC.afficherMessage("Félicitation", "Annulation avec Succès");
		    		clear();
				}
		    	else
		    		 HelperC.afficherMessage("Désolé", "Echec de l'opération ");
		    
		    }
   }
 
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création de la demande de position de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } else {
         
         hist.setOperation("Modification de la demande de position de l'employé " + getEmploye().getNom() + " " + getEmploye().getPrenom());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.saisiePositionEmploye));
       setHistorique(hist);
				if (getDecision()==0) {
       if (FactoryDAO.getInstance().deleteSaisiePositionEmploye(this)) {
         
         clear();
         HelperC.afficherMessage("Information", "Succès de l'opération ");
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de suppression");
       } 
				}
				HelperC.afficherAttention("Information",
						"Impossible de supprimer car la décision a été prise !");
     } 
   }
 
   
   public void initialiser() {
     clear();
   }
  public void initialiserLine() {
           clearInline();
	       }
 
   

 }


