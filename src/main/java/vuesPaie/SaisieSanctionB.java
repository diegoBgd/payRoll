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
/*     */ import javax.servlet.http.HttpSession;

/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;

/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class SaisieSanctionB
/*     */   extends SaisieSanctionC
/*     */ {
/*     */   private static final long serialVersionUID = -7482694101907046862L;
/*     */   private SaisieSanctionC selected;
/*     */   private EmployeC selection;
/*  45 */   private List<SaisieSanctionC> listSaisie,listRecours,
								          listDecision,listRetard;
/*  46 */   private List<SelectItem> listTypeSanction;
/*  47 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   
/*  51 */   private HttpSession session = HelperC.getSession();
/*     */   private boolean disableSave,disableElt,disableMsg;
/*     */   private String code;
/*     */   private String dateSaisieS,dateDebutSanctionS,
						   dateFinSanctionS,dateRecoursS,
						   dateDecisionS;
/*     */   private String codeEmployeRecherche;
/*     */   private String nomEmployeRecherche;
/*     */   private String prenomEmployeRecherche;
/*     */   private int decision,dureeSanction,typeDossier,typeDecision;
/*     */   ParametrageSanctionC parm;
/*     */   double salaireActuel=0;
/*     */   TraitementSalarialC traitement;
/*     */   ParametrageGeneralC parametre;
            List<ParametrageSanctionC> listParam;
/*     */   public EmployeC getSelection() {
/*  70 */     return this.selection;
/*     */   }
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/*  74 */     this.selection = selection;
/*     */   }
/*     */   

/*     */   public List<EmployeC> getListEmploye() {
/*  87 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/*  91 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/*  95 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  99 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/* 103 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 107 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   
/*     */   public HttpSession getSession() {
/* 119 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 123 */     this.session = session;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 127 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 131 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 135 */     return this.codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 139 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 143 */     return this.nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 147 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 151 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 155 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public int getDecision() {
/* 159 */     return this.decision;
/*     */   }
/*     */   
/*     */   public void setDecision(int decision) {
/* 163 */     this.decision = decision;
/*     */   }
/*     */   
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
/*     */   @PostConstruct
/*     */   private void init() {
/* 185 */     this.operateur = new OperateurC();
/* 186 */     this.exercice = new ExerciceC();
/*     */     
/* 188 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 189 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 190 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 191 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/* 193 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/* 196 */         FacesContext context = FacesContext.getCurrentInstance();
/* 197 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 198 */       } catch (IOException e) {
/*     */         
/* 200 */         e.printStackTrace();
/*     */       } 
/*     */     }
				
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
/*     */   private void clear1(boolean b) {
/* 302 */     if (b)
/* 303 */       this.selection = null; 
				code="";
/*     */   }
/*     */   
/*     */   private void setObject() {
				disableMsg=true;
/* 307 */     if (this.selected != null) {
/* 308 */       setId(this.selected.getId());
/* 309 */       setEmploye(this.selected.getEmploye());
/* 310 */       if (getEmploye() != null)
/* 311 */         setCode(getEmploye().getCode()); 
/* 312 */       setDateDebutSanction(this.selected.getDateDebutSanction());   
/* 327 */       setMotifDecision(this.selected.getMotifDecision());
/* 328 */       setDateSaisie(this.selected.getDateSaisie());
/* 329 */       setIdSanction(selected.getIdSanction());
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
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 338 */     this.code = "";
/* 339 */     setId(0);
/* 340 */     setEmploye(null);
/* 341 */     setDateDebutSanction(null);
/* 342 */     setDateDebutSanctionS("");
/* 343 */     setDateFinSanction(null);
/* 344 */     setDateFinSanctionS("");
/* 345 */     setDureeSanction(0);
/* 346 */     setIdSanction(0);
			  setTauxRetenu(0);
			  setSalaireBase(0);
			  setMontantRetenu(0);
			  setMoisPaie(0);
			  setMotifSanction("");
/* 347 */     disableSave=false;
/* 349 */     setDateRecours(null);
/* 350 */     setLibelleSanction("");
/* 351 */     setDateDecision(null);
/* 352 */     setMoisLettre("");
/* 354 */     setDateSaisie(null);
/* 355 */     setDateSaisieS("");
/* 356 */     setMotifRecours("");
/* 359 */     this.nomEmployeRecherche = "";
/* 360 */     this.prenomEmployeRecherche = "";
/* 361 */     this.selected = null;
			  setDecision(0);
			  dateDecisionS="";
			  setCloture(false);
			  setMotifDecision("");
			  disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateDebutSanction() {
/* 366 */     if (getDateDebutSanctionS().replace("/", "").replace("_", "").trim().equals("")) {
/* 367 */       setDateDebutSanction(null);
/*     */     } else {
/*     */       
/* 370 */       setDateDebutSanction(HelperC.validerDate(getDateDebutSanctionS()));
/* 371 */       if (getDateDebutSanction() == null) {
/* 372 */         setDateDebutSanctionS("");
/*     */         
/* 374 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         checkDays();
				  calculRetenu();
/* 377 */         setDateDebutSanctionS(HelperC.convertDate(getDateDebutSanction()));
/* 378 */        
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDateSaisie() {
/* 389 */     if (getDateSaisieS().replace("/", "").replace("_", "").trim().equals("")) {
/* 390 */       setDateSaisie(null);
/*     */     } else {
/*     */       
/* 393 */       setDateSaisie(HelperC.validerDate(getDateSaisieS()));
/* 394 */       if (getDateSaisie() == null) {
/* 395 */         setDateSaisieS("");
/* 396 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 399 */         setDateSaisieS(HelperC.convertDate(getDateSaisie()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeDateRecours() {
/* 405 */     if (getDateRecoursS().replace("/", "").replace("_", "").trim().equals("")) {
/* 406 */       setDateRecours(null);
/*     */     } else {
/*     */       
/* 409 */       setDateRecours(HelperC.validerDate(getDateRecoursS()));
/* 410 */       if (getDateRecours() == null) {
/* 411 */         setDateRecoursS("");
/* 412 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 415 */         setDateRecoursS(HelperC.convertDate(getDateRecours()));
				  checkRecours();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateDecision() {
/* 422 */     if (getDateDecisionS().replace("/", "").replace("_", "").trim().equals("")) {
/* 423 */       setDateDecision(null);
/*     */     } else {
/*     */       
/* 426 */       setDateDecision(HelperC.validerDate(getDateDecisionS()));
/* 427 */       if (getDateDecision() == null) {
/* 428 */         setDateDecisionS("");
/* 429 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 432 */         setDateDecisionS(HelperC.convertDate(getDateDecision()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeDateFinSanction() {
/* 438 */     if (getDateFinSanctionS().replace("/", "").replace("_", "").trim().equals("")) {
/* 439 */       setDateDebutSanction(null);
/*     */     } else {
/*     */       
/* 442 */       setDateFinSanction(HelperC.validerDate(getDateFinSanctionS()));
/* 443 */       if (getDateFinSanction() == null) {
/* 444 */         setDateFinSanctionS("");
/* 445 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         checkDays();
				  calculRetenu();
/* 448 */         setDateFinSanctionS(HelperC.convertDate(getDateFinSanction()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeSanction(ValueChangeEvent event) {
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
							HelperC.afficherAttention("Information", "La dur�e de recours doit �tre inf�rieures ou �gale � "+parm.getDureeRecours()+" jours");
							disableSave=true;
							return;
						}
						}
						else {
							HelperC.afficherAttention("Information", "La date recours doit �tre entre la date d�but sanction et la date fin sanction");
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
							HelperC.afficherAttention("Information", "La dur�e doit �tre sup�rieure ou �gale � "+parm.getDureeMin()+" jours");
							disableSave=true;
							return;
						}
						
						if(dureeSanction>parm.getDureeMax() && parm.getDureeMax()>0)
						{
							HelperC.afficherAttention("Information", "La dur�e doit �tre inf�rieure � ou �gale "+parm.getDureeMax()+" jours");
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
				
/*     */   public void onRowSelected(SelectEvent event) {
/* 464 */     this.selected = (SaisieSanctionC)event.getObject();
/* 465 */     if (this.selected != null) {
/* 466 */       setObject();
/* 467 */       PrimeFaces.current().executeScript("PF('dlgSct').hide();");
/*     */     } 
/*     */   }
/*     */ 

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
 
/*     */   
/*     */   public void onRowselected1(SelectEvent event) {
/* 488 */     this.selection = (EmployeC)event.getObject();
/* 489 */      affeterEmploye();
/* 490 */     PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void chargerEmploye() {
/* 498 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, 
/* 499 */         this.nomEmployeRecherche,true);
/*     */   }


/*     */   public void enregistrerRecours() {
/* 577 */     if (getDateRecours() == null) {
/* 578 */       HelperC.afficherMessage("Information", "Veillez saisir la date du recours");
/* 579 */     } 
/* 581 */      else {
/* 594 */      
/*     */ 
/*     */       
/* 597 */       Historique hist = new Historique();
/* 598 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 599 */       hist.setOperateur(this.operateur);
/* 600 */       if (getId() == 0) {
/* 601 */         hist.setOperation("Recours des mesures disciplinaires de l'employ� " + getEmploye().getNomPrenom());
/*     */       } else {
/* 603 */         hist.setOperation("Modification Recours mesures disciplinaires de l'employ� " + getEmploye().getNomPrenom());
/* 604 */       }  hist.setTable(Tables.getTableName(Tables.TableName.saisiesanction));
/* 605 */       setHistorique(hist);
				this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.recours));
				
/* 606 */       if (FactoryDAO.getInstance().insertUpdateSaisieSanction(this)) {
/*     */         
/* 608 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 609 */         clear(true);
/*     */       } else {
/*     */         
/* 612 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }

	public void enregistrerRecoursLine() {
		if (getDateRecours() == null) {
			HelperC.afficherMessage("Information", "Veillez saisir la date du recours");
		} else {

			if (!getMotifRecours().trim().equals("")) {
				Historique hist = new Historique();
				hist.setDateOperation(Calendar.getInstance().getTime());
				hist.setOperateur(this.operateur);
				if (getId() == 0) {
					hist.setOperation("Recours sur la sanction de l'employ� " + getEmploye().getNomPrenom());
				} else {
					hist.setOperation("Modification sur la sanction de l'employ� " + getEmploye().getNomPrenom());
				}
				hist.setTable(Tables.getTableName(Tables.TableName.saisiesanction));
				setHistorique(hist);
				this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.recours));
				this.setInLine(true);
				if (FactoryDAO.getInstance().insertUpdateSaisieSanction(this)) {
					HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
					clear(true);
				} else {

					HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
				}
			}
			else
				HelperC.afficherAttention("D�sol�", "Il faut pr�ciser le motif ");
		}
	}
		
/*     */   public void enregistrerDecision() {
/* 617 */     if (getEmploye() == null) {
/* 618 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/* 619 */     } 
/*     */     else {
/*     */       
/* 640 */       Historique hist = new Historique();
/* 641 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 642 */       hist.setOperateur(this.operateur);
/* 643 */       if (getId() == 0) {
/* 644 */         hist.setOperation("D�cision sur la sanction de l'employ� " + getEmploye().getNomPrenom());
/*     */       } else {
/* 646 */         hist.setOperation("Modification de la d�cision sur la sanction de l'employ� " + getEmploye().getNomPrenom());

/* 647 */       }  hist.setTable(Tables.getTableName(Tables.TableName.saisiesanction));
/* 648 */       setHistorique(hist);
				this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.decision));
				this.setIdOperateur(operateur.getId());
				
/* 649 */       if (FactoryDAO.getInstance().insertUpdateSaisieSanction(this)) {
/*     */         
/* 651 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 652 */         clear(true);
/*     */       } else {
/*     */         
/* 655 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }

	public void enregistrerSaisie() {
		if (getEmploye() == null) {
			HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
			return;
		}
		if (getMotifSanction() == "") {
			HelperC.afficherMessage("Information", "Veillez d�crire la raison de la sanction");
			return;
		}
		if (getDateDebutSanction() == null) {
			HelperC.afficherAttention("Information", "Veillez pr�ciser la date d�but de la sanction");
			return;
		}
		if (getDateDebutSanction().after(getDateFinSanction())) {
			HelperC.afficherAttention("Information",
					"Veillez la date de d�but ne peut pas etre sup�rieur � la date fin");
			return;
		}

		if (parm.getIdRetenu() > 0 && getMoisPaie() == 0) {
			HelperC.afficherAttention("Information", "Veillez pr�ciser le mois de paie pour la retenu");
			return;
		}
		
		Historique hist = new Historique();
		hist.setDateOperation(Calendar.getInstance().getTime());
		hist.setOperateur(this.operateur);
		if (getId() == 0) {
			hist.setOperation(
					"Saisie de la sanction de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
		} else {
			hist.setOperation("Modification sur la sanction de l'employ� " + getEmploye().getNom() + " "
					+ getEmploye().getPrenom());
		}
		hist.setTable(Tables.getTableName(Tables.TableName.saisiesanction));
		setHistorique(hist);
		this.setIdOperateur(operateur.getId());
		this.setIdExercice(exercice.getId());
		this.setEtat(Constante.getEtatSanction(Constante.EtatSanction.saisie));
		
		if (FactoryDAO.getInstance().insertUpdateSaisieSanction(this)) {

			HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
			clear(true);
		} else {

			HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
		}
	}
   
 
/*     */   public void supprimer() {
/* 696 */     if (getId() == 0) {
/* 697 */       HelperC.afficherDeleteMessage();
/*     */     }
			else {
				if(selected.getEtat()!=Constante.getEtatSanction(Constante.EtatSanction.saisie))
					 HelperC.afficherAttention("Information", "On ne peut pas supprimer cette sanction car il est d�j� utilis� ailleur!");
				else 
				{
/* 699 */      if (FactoryDAO.getInstance().deleteSaisieSanction(this)) {
/* 700 */       clear(true);
/* 701 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/*     */     } else {
/*     */       
/* 704 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
				}
/*     */   }
/*     */ }
/*     */   
/*     */   public void initialiser() {
/* 710 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */  
			public void annulerRecours(){
				if(selected.getDecision()==0)
				{
				if(FactoryDAO.getInstance().annulerRecourSanction(selected))
				{
					 clear(true);
				     HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
				}
				else
					 HelperC.afficherMessage("D�sol�", "Echec de suppression");
				}
				else
					 HelperC.afficherAttention("Attention", "Impossible d'annuler le recours car la d�cision est d�j� prise !");
			}
			
			public void annulerDecision(){
				if(FactoryDAO.getInstance().annulerDecisionSanction(selected))
				{
					 clear(true);
				     HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
				}
				else
					 HelperC.afficherMessage("D�sol�", "Echec de suppression");
			}
/*     */ }

