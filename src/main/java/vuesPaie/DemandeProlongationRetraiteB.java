/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
import classesPaie.Constante;
/*     */ import classesPaie.DemandeProlongationRetraiteC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
import classesPaie.ParametrageFinCarriereC;
import classesPaie.ParametrageGeneralC;
/*     */ import classesPaie.Tables;

/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.component.UIComponent;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;

/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;

/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class DemandeProlongationRetraiteB
/*     */   extends DemandeProlongationRetraiteC
/*     */ {
/*     */   private static final long serialVersionUID = 7234059774225821051L;
/*     */   private DemandeProlongationRetraiteC selected;
/*     */   private EmployeC selection;
/*  42 */   private List<DemandeProlongationRetraiteC> listDemande = new ArrayList<DemandeProlongationRetraiteC>();
/*  43 */   private List<DemandeProlongationRetraiteC> listDecisionDemande = new ArrayList<DemandeProlongationRetraiteC>();
/*  44 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  47 */   private HttpSession session = HelperC.getSession();
/*     */   private String personel,categorie,grade,niveauFrm,fonction;
/*     */   private String code,msgInfo;
/*     */   private String codeEmployeRecherche;
/*     */   private String nomEmployeRecherche;
/*     */   private String prenomEmployeRecherche;
/*     */   private boolean disableSave;
			private int typeDec;
			ParametrageFinCarriereC parm;
			ParametrageGeneralC parmGn;
			
/*     */   public DemandeProlongationRetraiteC getSelected() {
/*  55 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(DemandeProlongationRetraiteC selected) {
/*  59 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public EmployeC getSelection() {
/*  63 */     return this.selection;
/*     */   }
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/*  67 */     this.selection = selection;
/*     */   }
/*     */   
/*     */   public List<DemandeProlongationRetraiteC> getListDemande() {
/*  71 */     return this.listDemande;
/*     */   }
/*     */   
/*     */   public void setListDemande(List<DemandeProlongationRetraiteC> listDemande) {
/*  75 */     this.listDemande = listDemande;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/*  79 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/*  83 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/*  87 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  91 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/*  95 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  99 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/* 103 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 107 */     this.session = session;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 111 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 115 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 119 */     return this.codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 123 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 127 */     return this.nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 131 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 135 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 139 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public List<DemandeProlongationRetraiteC> getListDecisionDemande() {
/* 143 */     return this.listDecisionDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDecisionDemande(List<DemandeProlongationRetraiteC> listDecisionDemande) {
/* 148 */     this.listDecisionDemande = listDecisionDemande;
/*     */   }
/*     */   
/*     */   public static long getSerialversionuid() {
/* 152 */     return 7234059774225821051L;
/*     */   }
/*     */   public String getPersonel() {
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
/*     */   @PostConstruct
/*     */   private void charger() {
/* 157 */     this.operateur = new OperateurC();
/* 158 */     this.exercice = new ExerciceC();
/*     */     
/* 160 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 161 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 162 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 163 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 164 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/* 167 */         FacesContext context = FacesContext.getCurrentInstance();
/* 168 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 169 */       } catch (IOException e) {
/*     */         
/* 171 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       parmGn=FichierBaseDAO.getInstance().getParametrageGeneral();
/* 175 */       UIComponent frm = null;
/* 176 */       FacesContext context = FacesContext.getCurrentInstance();
/* 177 */       frm = context.getViewRoot().findComponent("frmPrl");
/* 178 */       if (frm != null) {
/*     */         
/* 180 */        setEtatProlongation(Constante.EtatDemandeProlongationRetraite.demandeProlongation);
/* 181 */        setDateDemande(new Date());
				 setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
/*     */       } 

				frm = context.getViewRoot().findComponent("frmVldPrl");
				if (frm != null) {
/*     */         
/* 180 */        setEtatProlongation(Constante.EtatDemandeProlongationRetraite.decide);
/* 181 */        setDateDecision(new Date());
				 setDateDecisionS(HelperC.changeDateFormate(getDateDecision()));
/*     */       } 

				frm = context.getViewRoot().findComponent("frmPrLine");
				if (frm != null) {
/*     */         setDateDemande(new Date());
				  setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
/* 180 */         setEtatProlongation(Constante.EtatDemandeProlongationRetraite.demandeProlongation);
/* 181 */         employeInLine();
				  completeEmploye();
/*     */       } 
/*     */       
/* 184 */       
				
/*     */     } 
/*     */   }


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
			
/*     */   public void chargementDemande() {
/* 193 */     this.listDemande = FactoryDAO.getInstance().getListeDemandeProlongationRetraite(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargementTraitement() {
	
/* 198 */    listDecisionDemande = FactoryDAO.getInstance().getListeDemandeProlongationRetraiteTraite(2,typeDec);
/* 199 */     
/*     */   }
/*     */   
/*     */   public void findByCode() {
/* 205 */     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
/* 206 */     if (this.selection != null) {
/* 207 */       setCode(this.selection.getCode());
/* 208 */       setEmploye(this.selection);				
				completeEmploye();
/*     */     } else {
/*     */       
/* 211 */       clear1(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void clear1(boolean b) {
/* 216 */     if (b) {
/* 217 */       this.selection = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 223 */     if (this.selected != null) {
/* 224 */       setId(this.selected.getId());
/* 225 */       setEmploye(this.selected.getEmploye());
				selection=this.selected.getEmploye();
/* 226 */       if (getEmploye() != null)
/* 227 */         setCode(getEmploye().getCode()); 
				completeEmploye();
/* 228 */       setDateDemande(this.selected.getDateDemande());
/* 229 */       setDateDemandeS(this.selected.getDateDemandeS());
/* 230 */       setEtat(this.selected.getEtat());
/* 231 */       setEtatProlongation(this.selected.getEtatProlongation());
/* 232 */       setMotifDemande(this.selected.getMotifDemande());
/* 233 */       setAge(this.selected.getAge());
/* 234 */       setAgeRetraiteDemande(this.selected.getAgeRetraiteDemande());
/* 235 */       setInLine(selected.isInLine());
/* 236 */       setDecision(this.selected.getDecision());
/* 237 */       setDateDecision(this.selected.getDateDecision());
/* 238 */       setDateDecisionS(this.selected.getDateDecisionS());
/* 239 */       setEtat(this.selected.getEtat());
/* 240 */       setEtatProlongation(this.selected.getEtatProlongation());
/* 241 */       setLibelleProlongation(this.selected.getLibelleProlongation());
                
/*     */     } 
/*     */   }
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
				msgInfo="Il faut avoir atteint l'�ge de retaraite ("+parm.getAgeRetraite()+" ans) pour demander la prolongation !";
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
	
/*     */   private void clear() {
/* 247 */     setId(0);
/* 248 */     setEmploye(null);
/* 249 */     setDateDemande(null);
/* 250 */     setDateDemandeS("");
/* 251 */     setEtat(0);
/* 252 */     setEtatProlongation(null);
/* 253 */     setMotifDemande("");
/* 254 */     setAge(0);
/* 255 */     setAgeRetraiteDemande(0);
/* 256 */     setAvisConseilAdministration(0);
/* 257 */     setDecision(0);
/* 258 */     setDateDecision(null);
/* 259 */     setDateDecisionS("");
/* 260 */     setEtat(0);
/* 261 */     setEtatProlongation(null);
/* 262 */     setLibelleProlongation("");
/* 263 */     this.nomEmployeRecherche = "";
/* 264 */     this.code = "";
/* 265 */     this.prenomEmployeRecherche = "";
/* 266 */     this.selected = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateDemande() {
/* 271 */     if (getDateDemandeS().replace("/", "").replace("_", "").trim().equals("")) {
/* 272 */       setDateDemande(null);
/*     */     } else {
/*     */       
/* 275 */       setDateDemande(HelperC.validerDate(getDateDemandeS()));
/* 276 */       if (getDateDemande() == null) {
/* 277 */         setDateDemandeS("");
/* 278 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 281 */         setDateDemandeS(HelperC.convertDate(getDateDemande()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDateDecision() {
/* 289 */     if (getDateDecisionS().replace("/", "").replace("_", "").trim().equals("")) {
/* 290 */       setDateDecision(null);
/*     */     } else {
/*     */       
/* 293 */       setDateDecision(HelperC.validerDate(getDateDecisionS()));
/* 294 */       if (getDateDecision() == null) {
/* 295 */         setDateDecisionS("");
/* 296 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 299 */         setDateDecisionS(HelperC.convertDate(getDateDecision()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 308 */     this.selected = (DemandeProlongationRetraiteC)event.getObject();
/* 309 */     if (this.selected != null)
/* 310 */       setObject(); 
/* 311 */     PrimeFaces.current().executeScript("PF('dlgDmd').hide();");
/*     */   }


/*     */   public void onRowDecisionSelected(SelectEvent event) {
/* 308 */     this.selected = (DemandeProlongationRetraiteC)event.getObject();
/* 309 */     if (this.selected != null)
/* 310 */       setObject(); 
/* 311 */     PrimeFaces.current().executeScript("PF('dlgTrt').hide();");
/*     */   }

/*     */   private void setObject1() {
/* 316 */     if (this.selection != null) {
/* 317 */       setEmploye(this.selection);
/*     */       
/* 319 */       if (getEmploye() != null) {
/* 320 */         setCode(getEmploye().getCode());
/*     */       } else {
/* 322 */         setCode("");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowselected1(SelectEvent event) {
/* 329 */     this.selection = (EmployeC)event.getObject();
/* 330 */     setObject1();
/* 331 */     PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */   }
/*     */    
            public void checkPeriod(){
            	disableSave=false;
            	if(parm!=null){
            	if(getAge()<parm.getAgeRetraite())
            	{
            		disableSave=true;
            		HelperC.afficherAttention("Information", " L'Employ� n'a pas encore l'�ge  de retraite ");
            		return;
            	}
            	if(getAgeRetraiteDemande()>parm.getPeriodeProlongation())
            	{
            		disableSave=true;
            		HelperC.afficherAttention("Information", "On ne peut pas d�passer une prolongation de "+parm.getPeriodeProlongation()+" ans");
            		return;
            	}
            	if(getAge()+getAgeRetraiteDemande()>parm.getAgeMaxRetraite())
            	{
            		disableSave=true;
            		HelperC.afficherAttention("Information", "On ne peut pas d�passer  "+parm.getAgeMaxRetraite()+" ans ");
            		return;
            	}
            	}
            	else
            	{
            		disableSave=true;
            		HelperC.afficherAttention("Information", "Il faut param�trer tous �l�ments en rapport la prologantion du travail et le personnel");
            		return;
            	}
            }
/*     */ 
/*     */ 
/*     */   
/*     */   public void chargerEmploye() {
/* 338 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, 
/* 339 */         this.nomEmployeRecherche,true);
/*     */   }
/*     */   
/*     */   public void enregistrerDemande() {
/* 343 */     if (getEmploye() == null) {
/* 344 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/* 345 */     } else if (getDateDemande() == null) {
/* 346 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
/* 347 */     } else if (getMotifDemande().equalsIgnoreCase("")) {
/* 348 */       HelperC.afficherAttention("Information", "Veillez d�crire le motif de votre demande");
/* 349 */     } else if (getAgeRetraiteDemande() == 0) {
/* 350 */       HelperC.afficherAttention("Information", "Veillez indiquer l'age de la retraite souhait�");
/*     */     } else {
/* 352 */       
/* 353 */       setEtat(Constante.getEtatDemandeProlongationRetraite(Constante.EtatDemandeProlongationRetraite.demandeProlongation));
/*     */       setInLine(true);
/* 355 */       Historique hist = new Historique();
/* 356 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 357 */       hist.setOperateur(this.operateur);
/* 358 */       if (getId() == 0) {
/* 359 */         hist.setOperation("Cr�ation de la demande de prolongation de l'age de la retraite de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/* 361 */         hist.setOperation("Modification de la demande de prolongation de l'age de la retraite de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 362 */       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
/* 363 */       setHistorique(hist);

/* 364 */       if (FactoryDAO.getInstance().insertUpdateDemandeProlongationRetraite(this)) {
/*     */         
/* 366 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/*     */         chargementDemande();
/* 368 */         clear();
/*     */       } else {
/*     */         
/* 371 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }

/*     */   public void enregistrerDemandeLine() {
	         if(getDecision()==0 && getDateDecision()==null)
	         {
/* 343 */     if (getEmploye() == null) {
/* 344 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/* 345 */     } else if (getDateDemande() == null) {
/* 346 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
/* 347 */     } else if (getMotifDemande().equalsIgnoreCase("")) {
/* 348 */       HelperC.afficherAttention("Information", "Veillez d�crire le motif de votre demande");
/* 349 */     } else if (getAgeRetraiteDemande() == 0) {
/* 350 */       HelperC.afficherAttention("Information", "Veillez indiquer l'age de la retraite souhait�");
/*     */     } else {
/* 352 */       setEtatProlongation(Constante.EtatDemandeProlongationRetraite.demandeProlongation);
/* 353 */       setEtat(Constante.getEtatDemandeProlongationRetraite(Constante.EtatDemandeProlongationRetraite.demandeProlongation));
/*     */       
/* 355 */       Historique hist = new Historique();
/* 356 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 357 */       hist.setOperateur(this.operateur);
/* 358 */       if (getId() == 0) {
/* 359 */         hist.setOperation("Cr�ation de la demande de prolongation de l'age de la retraite de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/* 361 */         hist.setOperation("Modification de la demande de prolongation de l'age de la retraite de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 362 */       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
/* 363 */        setHistorique(hist);

/* 364 */       if (FactoryDAO.getInstance().insertUpdateDemandeProlongationRetraite(this)) {
/*     */         
/* 366 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/*     */         chargementDemande();
/* 368 */         clearLine();
/*     */       } else {
/*     */         
/* 371 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
	         }
	         else{
	        	 HelperC.afficherAttention("Information", "Impossible de modifier car votre demande �st d�j� trait�e ");
	         }
	         
/*     */   }

/*     */   public void enregistrerDecision() {
/* 376 */     if (getEmploye() == null) {
/* 377 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/* 378 */     } else if (getDateDemande() == null) {
/* 379 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
/* 380 */     } else if (getMotifDemande().equalsIgnoreCase("")) {
/* 381 */       HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
/* 382 */     } else if (getAgeRetraiteDemande() == 0) {
/* 383 */       HelperC.afficherAttention("Information", "Veillez indiquer l'age de la retraite souhait�");
/* 384 */     } else if (getDateDecision() == null) {
/* 385 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de d�cision");
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 391 */       Historique hist = new Historique();
/* 392 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 393 */       hist.setOperateur(this.operateur);
/* 394 */       if (getId() == 0) {
/* 395 */         hist.setOperation("Cr�ation de la demande de prolongation de l'age de la retraite de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/* 397 */         hist.setOperation("Modification de la demande de prolongation de l'age de la retraite de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 398 */       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
/*     */       
/* 400 */       setHistorique(hist);
				setEtat(Constante.getEtatDemandeProlongationRetraite(Constante.EtatDemandeProlongationRetraite.decide));
/*     */     
/* 401 */       if (FactoryDAO.getInstance().insertUpdateDemandeProlongationRetraite(this)) {
/*     */         
/* 403 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
				if(parmGn!=null) 
				{
				if(getDecision()==1)
				    HelperC.sendEmail(parmGn.getSmtpServer(), parmGn.getMailOrigine(), parmGn.getMailOrigine(), parmGn.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de prolongation de travail a �t� accept�e ", "Demande prolongation de travail");
				if(getDecision()==2)
				     HelperC.sendEmail(parmGn.getSmtpServer(), parmGn.getMailOrigine(), parmGn.getMailOrigine(), parmGn.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de prolongation de travail a �t� refus�e ", "Demande prolongation de travail.");
				}
/* 404 */         chargementDemande();
/* 405 */         clear();
/*     */       } else {
/*     */         
/* 408 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   public void annulerDecision()
			{
				if(selected!=null)
				if(FactoryDAO.getInstance().annulerProlongagtionRetraite(selected))
					 HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
				else
					  HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
			}
/*     */   public void supprimer() {
/* 415 */     if (getId() == 0) {
/* 416 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 419 */       Historique hist = new Historique();
/* 420 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 421 */       hist.setOperateur(this.operateur);
/* 422 */       if (getId() == 0) {
/* 423 */         hist.setOperation("Cr�ation de la demande de prolongation de l'age de la retraite de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/* 425 */         hist.setOperation("Modification de la demande de prolongation de l'age de la retraite de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 426 */       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
/* 427 */       setHistorique(hist);
/* 428 */       if (FactoryDAO.getInstance().deleteDemandeProlongationRetraite(selected)) {
/*     */           chargementDemande();
/* 430 */         clear();
/* 431 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/*     */       } else {
/*     */         
/* 434 */         HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialiser() {
/* 440 */     clear();
/*     */   }
/*     */ }

