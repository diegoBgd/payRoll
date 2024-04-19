/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DemandeProlongationRetraiteC;
/*     */ import classesPaie.DemandeRetraiteAnticipeC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
import classesPaie.ParametrageFinCarriereC;
import classesPaie.ParametrageGeneralC;
/*     */ import classesPaie.Tables;
import classesPaie.Tables.TableName;

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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;

/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;

/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class DemandeRetraiteAnticipeB
/*     */   extends DemandeRetraiteAnticipeC
/*     */ {
/*     */   private static final long serialVersionUID = 5352140340946268540L;
/*     */   private DemandeRetraiteAnticipeC selected;
/*     */   private EmployeC selection;
/*  43 */   private List<DemandeRetraiteAnticipeC> listDemande = new ArrayList<DemandeRetraiteAnticipeC>();
/*  44 */   private List<DemandeRetraiteAnticipeC> listDecisionDemande = new ArrayList<DemandeRetraiteAnticipeC>();
/*  45 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  48 */   private HttpSession session = HelperC.getSession();
/*     */   private String personel,categorie,grade,niveauFrm,fonction;
/*     */   private String code,msgInfo;
/*     */   private String codeEmployeRecherche;
/*     */   private String nomEmployeRecherche;
/*     */   private String prenomEmployeRecherche;
/*     */   private int age,typeDec;
			private List<SelectItem>listMotif;
			private boolean disableSave;
			ParametrageFinCarriereC parm;
			ParametrageGeneralC parmGn;
			
/*     */   public DemandeRetraiteAnticipeC getSelected() {
/*  56 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(DemandeRetraiteAnticipeC selected) {
/*  60 */     this.selected = selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getSelection() {
/*  65 */     return this.selection;
/*     */   }
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/*  69 */     this.selection = selection;
/*     */   }
/*     */   
/*     */   public List<DemandeRetraiteAnticipeC> getListDemande() {
/*  73 */     return this.listDemande;
/*     */   }
/*     */   
/*     */   public void setListDemande(List<DemandeRetraiteAnticipeC> listDemande) {
/*  77 */     this.listDemande = listDemande;
/*     */   }
/*     */   
/*     */   public List<DemandeRetraiteAnticipeC> getListDecisionDemande() {
/*  81 */     return this.listDecisionDemande;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDecisionDemande(List<DemandeRetraiteAnticipeC> listDecisionDemande) {
/*  86 */     this.listDecisionDemande = listDecisionDemande;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/*  90 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/*  94 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/*  98 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 102 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/* 106 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 110 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/* 114 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 118 */     this.session = session;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 122 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 126 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 130 */     return this.codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 134 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 138 */     return this.nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 142 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 146 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 150 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public static long getSerialversionuid() {
/* 154 */     return 5352140340946268540L;
/*     */   }

			
public String getFonction() {
	return fonction;
}
public void setFonction(String fonction) {
	this.fonction = fonction;
}
public int getTypeDec() {
	return typeDec;
}
public void setTypeDec(int typeDec) {
	this.typeDec = typeDec;
}
public boolean isDisableSave() {
	return disableSave;
}
public void setDisableSave(boolean disableSave) {
	this.disableSave = disableSave;
}
public List<SelectItem> getListMotif() {
	return listMotif;
}
public void setListMotif(List<SelectItem> listMotif) {
	this.listMotif = listMotif;
}

public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
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
public String getMsgInfo() {
	return msgInfo;
}
public void setMsgInfo(String msgInfo) {
	this.msgInfo = msgInfo;
}
/*     */   @PostConstruct
/*     */   private void charger() {
/* 159 */     this.operateur = new OperateurC();
/* 160 */     this.exercice = new ExerciceC();
/*     */     
/* 162 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 163 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 164 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 165 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 166 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/* 169 */         FacesContext context = FacesContext.getCurrentInstance();
/* 170 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 171 */       } catch (IOException e) {
/*     */         
/* 173 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
	 			parmGn=FichierBaseDAO.getInstance().getParametrageGeneral();
/* 176 */       UIComponent frm = null;
/* 177 */       FacesContext context = FacesContext.getCurrentInstance();
/* 178 */       frm = context.getViewRoot().findComponent("frmRtr");
/* 179 */       if (frm != null) {
/*     */         
/* 181 */        
/* 182 */         setEtat(Constante.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.demandeRetraiteAnticipe));
/* 184 */        
/* 185 */         setDateDemande(new Date());
/* 186 */         setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
/*     */       } 
/* 188 */       frm = context.getViewRoot().findComponent("frmVld");
/* 189 */       if (frm != null) {
/*     */         
/* 191 */         
/* 192 */         setEtat(Constante.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.traitementRetraite));
/* 194 */         setDateDecision(new Date());
/* 195 */         setDateDecisionS(HelperC.changeDateFormate(getDateDecision()));
/*     */       } 
				
				frm = context.getViewRoot().findComponent("frmRtrLn");
/* 189 */       if (frm != null) {
/*     */         
/* 191 */         
/* 192 */         setEtat(Constante.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.demandeRetraiteAnticipe));
				  setDateDemande(new Date());
/* 186 */         setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
				  employeInLine();
				  
/*     */       } 
/*     */     } 
				chargementMotif();
/*     */   }

		private void employeInLine(){
			selection = FactoryDAO.getInstance().getEmploye(operateur.getIdEmploye(), false);
			if (this.selection != null) {
				setObject1();
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
		age=getEmploye().getAge();
		searchParametre();
	}
}

	private void chargementMotif(){
		listMotif=new ArrayList<SelectItem>();
		listMotif.add(new SelectItem(0, ""));
		for (Base b : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.motifRetraite))) {
			listMotif.add(new SelectItem(b.getId(), b.getDesignation()));
		}
	}
	private void searchParametre(){
		disableSave=false;
		parm=FichierBaseDAO.getInstance().getParametrageFinCarriere(selection.getIdPersnl());
		if(parm!=null)
		{
			if(getAnciennette()<parm.getPeriodeAnticipe())
			{
				disableSave=true;
        		HelperC.afficherAttention("Information", "L'employ� doit avoir "+parm.getPeriodeAnticipe()+" ans d'anciennet� !");
        		msgInfo="Il faut avoir l'ancienn�t� de "+parm.getPeriodeAnticipe()+" ans pour demander la retraite anticip�e ";
        		return;
			}
		}
	}
	
 
	   public void onRowVldSelected(SelectEvent event){
		   this.selected = (DemandeRetraiteAnticipeC)event.getObject();
		   if (this.selected != null)
		         setObject(); 
		     PrimeFaces.current().executeScript("PF('dlgVld').hide();");		
	   }
	   public void chargementTraite(){
		   listDecisionDemande=FactoryDAO.getInstance().getListeRetraiteAnticipeValide(2,typeDec);
	   }
   
/*     */   public void findByCode() {
/* 212 */     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
/* 213 */     if (this.selection != null) {
/* 214 */       
				setObject1();
/* 216 */       
/*     */     } else {
/* 218 */       clear1(true);
/*     */     } 
/*     */   }

/*     */   private void clear1(boolean b) {
/* 230 */     if (b) {
/* 231 */       this.selection = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 237 */     if (this.selected != null) {
/* 238 */       setId(this.selected.getId());
/* 239 */       setEmploye(this.selected.getEmploye());
				selection=selected.getEmploye();
/* 240 */       if (getEmploye() != null)
				{
/* 241 */         setObject1();
				}
/* 242 */       setDateDemande(this.selected.getDateDemande());
/* 243 */       setDateDemandeS(this.selected.getDateDemandeS());
/* 244 */       
/* 245 */       setIdMotifDemande(this.selected.getIdMotifDemande());
/* 246 */       setAnciennette(this.selected.getAnciennette());
/* 247 */       setDateDebutRetraite(this.selected.getDateDebutRetraite());
/* 248 */       setDateDebutRetraiteS(this.selected.getDateDebutRetraiteS());
/* 249 */       setDecision(this.selected.getDecision());
/* 250 */       if (this.selected.getDateDecision() != null) 
				{
/* 251 */         setDateDecision(this.selected.getDateDecision());
/*     */       } else {
/* 253 */         setDateDecision(null);
/*     */       } 
/* 255 */       setDateDecisionS(HelperC.changeDateFormate(getDateDecision()));
/* 256 */       setLibelleDemandeRetraite(this.selected.getLibelleDemandeRetraite());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear() {
/* 262 */     setId(0);
/* 263 */     setEmploye(null);
/* 264 */     setDateDemande(null);
/* 265 */     setDateDemandeS("");
/* 266 */     setEtat(0);
/* 267 */     setDateDemande(null);
/* 268 */     setDateDemandeS("");
/* 269 */     setEtat(0);
/* 270 */     personel="";
			  categorie="";
			  grade="";
			  niveauFrm="";
			  fonction="";
/* 271 */     setIdMotifDemande(0);
/* 272 */     setAnciennette(0);
/* 273 */     setDateDebutRetraite(null);
/* 274 */     setDateDebutRetraiteS("");
/* 275 */     setDecision(0);
/* 276 */     setDateDecision(null);
/* 277 */     setDateDecisionS("");
/* 278 */     setLibelleDemandeRetraite("");
/* 279 */     this.nomEmployeRecherche = "";
/* 280 */     this.code = "";
/* 281 */     this.prenomEmployeRecherche = "";
/* 282 */     this.selected = null;
/*     */   }

			 public void clearLine() {
/* 262 */     setId(0);
/* 263 */    
/* 264 */    
/* 266 */     setEtat(0);
/* 271 */     setIdMotifDemande(0);
/* 272 */    
/* 273 */     setDateDebutRetraite(null);
/* 274 */     setDateDebutRetraiteS("");
/* 275 */     setDecision(0);
/* 276 */     setDateDecision(null);
/* 277 */     setDateDecisionS("");
/* 278 */     setLibelleDemandeRetraite("");
/* 279 */     this.selected = null;
/*     */   }

/*     */   public void changeDateDemande() {
/* 287 */     if (getDateDemandeS().replace("/", "").replace("_", "").trim().equals("")) {
/* 288 */       setDateDemande(null);
/*     */     } else {
/*     */       
/* 291 */       setDateDemande(HelperC.validerDate(getDateDemandeS()));
/* 292 */       if (getDateDemande() == null) {
/* 293 */         setDateDemandeS("");
/* 294 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 297 */         setDateDemandeS(HelperC.convertDate(getDateDemande()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDateDebutRetraite() {
/* 305 */     if (getDateDebutRetraiteS().replace("/", "").replace("_", "").trim().equals("")) {
/* 306 */       setDateDebutRetraite(null);
/*     */     } else {
/*     */       
/* 309 */       setDateDebutRetraite(HelperC.validerDate(getDateDebutRetraiteS()));
/* 310 */       if (getDateDebutRetraite() == null) {
/* 311 */         setDateDebutRetraiteS("");
/* 312 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 315 */         setDateDebutRetraiteS(HelperC.convertDate(getDateDebutRetraite()));
                  if(selection!=null)
				 prolongation(selection.getId());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDateDecision() {
/* 323 */     if (getDateDecisionS().replace("/", "").replace("_", "").trim().equals("")) {
/* 324 */       setDateDecision(null);
/*     */     } else {
/*     */       
/* 327 */       setDateDecision(HelperC.validerDate(getDateDecisionS()));
/* 328 */       if (getDateDecision() == null) {
/* 329 */         setDateDecisionS("");
/* 330 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 333 */         setDateDecisionS(HelperC.convertDate(getDateDecision()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 342 */     this.selected = (DemandeRetraiteAnticipeC)event.getObject();
/* 343 */     if (this.selected != null)
/* 344 */       setObject(); 
/* 345 */     PrimeFaces.current().executeScript("PF('dlgDmd').hide();");
/*     */   }

			public void onRowDmdSelected(SelectEvent event) {
/* 342 */     this.selected = (DemandeRetraiteAnticipeC)event.getObject();
/* 343 */     if (this.selected != null)
/* 344 */       setObject(); 
/* 345 */     PrimeFaces.current().executeScript("PF('dlgDem').hide();");
/*     */   }

/*     */   private void setObject1() {
/* 350 */     if (this.selection != null) {
/* 351 */       setEmploye(this.selection);
/*     */       
/* 353 */       if (getEmploye() != null) {
/* 354 */         setCode(this.selection.getCode());
/* 215 */      	  setEmploye(this.selection);
				  setAnciennette(selection.getAnciennete());
				  
				  completeEmploye();
/*     */       } else {
/* 356 */         setCode("");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 

         private void prolongation(int idEmp) {
        	 	  disableSave=false;
	              int dif=0;
	              DemandeProlongationRetraiteC prol = FactoryDAO.getInstance().getDemandeProlongationParEmploye(idEmp, 2, 2);
	              if (prol != null ) 
	              {
	            	  dif=HelperC.getYearFromDate(getDateDebutRetraite())-HelperC.getYearFromDate(prol.getDateDemande());
	            	  if(dif<=prol.getAgeRetraiteDemande())
	            	  {
	            		  disableSave = true;
	            		  HelperC.afficherAttention("Information", "L'employ� est  en prolongation de travail !");
	              		return;
	            	  }
	              }
	             
         }
			public void chargement()
			{
				listDemande = FactoryDAO.getInstance().getListeDemandeRetraiteAnticipe(1);
			}
			
			public void chargementLine()
			{
				listDemande = FactoryDAO.getInstance().getListeDemandeRetraiteAnticipeLine(selection.getId());
			}
			
			public void changeMotif(ValueChangeEvent event)
			{
				setIdMotifDemande(((Integer)event.getNewValue()).intValue());
			}
/*     */   
/*     */   public void onRowselected1(SelectEvent event) {
/* 363 */     this.selection = (EmployeC)event.getObject();
/* 364 */     setObject1();
/* 365 */     PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void chargerEmploye() {
/* 372 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, 
/* 373 */         this.nomEmployeRecherche,true);
/*     */   }
/*     */   
/*     */   public void enregistrerDemande() {
/* 377 */     if (getEmploye() == null) {
/* 378 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/* 379 */     } else if (getDateDemande() == null) {
/* 380 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
/* 381 */     } else if (getDateDemande() == null) {
/* 382 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de debut de la retraite");
/* 383 */     } else if (getIdMotifDemande()==0) {
/* 384 */       HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
/* 385 */     } else if (getAnciennette() == 0) {
/* 386 */       HelperC.afficherAttention("Information", "Veillez indiquer l'anciennet� de l'employ�");
/*     */     }
/*     */     else {
/*     */       
/* 390 */       Historique hist = new Historique();
/* 391 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 392 */       hist.setOperateur(this.operateur);
/* 393 */       if (getId() == 0) {
/* 394 */         hist.setOperation("Cr�ation de la demande de la retraite anticip�e de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/* 396 */         hist.setOperation("Modification de la demande de la retraite anticip�e  de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 397 */       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
/* 398 */       setHistorique(hist);
				setEtat(Constante.getEtatDemandeRetraiteAnticipe(Constante.EtatDemandeRetraiteAnticipe.demandeRetraiteAnticipe));
/* 399 */       if (FactoryDAO.getInstance().insertUpdateDemandeRetraiteAnticipe(this)) {
/*     */         
/* 401 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 402 */         chargement();
/* 403 */         clear();
/*     */       } else {
/*     */         
/* 406 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }

/*     */   public void enregistrerDemandeLine() {
	        if(getDecision()==0 && getDateDecision()==null)
	        {
/* 377 */     if (getEmploye() == null) {
/* 378 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/* 379 */     } else if (getDateDemande() == null) {
/* 380 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
/* 381 */     } else if (getDateDemande() == null) {
/* 382 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de debut de la retraite");
/* 383 */     } else if (getIdMotifDemande()==0) {
/* 384 */       HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
/* 385 */     } 
/*     */     else {
/*     */       
/* 390 */       Historique hist = new Historique();
/* 391 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 392 */       hist.setOperateur(this.operateur);
/* 393 */       if (getId() == 0) {
/* 394 */         hist.setOperation("Cr�ation de la demande de la retraite anticip�e de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/* 396 */         hist.setOperation("Modification de la demande de la retraite anticip�e  de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 397 */       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
/* 398 */       setHistorique(hist);
				setInLine(true);
/* 399 */       if (FactoryDAO.getInstance().insertUpdateDemandeRetraiteAnticipe(this)) {
/*     */         
/* 401 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 402 */         chargementLine();
/* 403 */         clearLine();
/*     */       } else {
/*     */         
/* 406 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
	        }
	        else{
	        	 HelperC.afficherAttention("Information", "Impossible de modifier car votre demande �st d�j� trait�e ");
	         }
/*     */   }


/*     */   public void enregistrerDecision() {
/* 411 */     if (getEmploye() == null) {
/* 412 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/* 413 */     } else if (getDateDemande() == null) {
/* 414 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
/* 415 */     } else if (getDateDemande() == null) {
/* 416 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de debut de la retraite");
/* 417 */     } else if (getDateDecision() == null) {
/* 418 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de decision");
/* 419 */     } else if (getIdMotifDemande()==0) {
/* 420 */       HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
/* 421 */     } else if (getAnciennette() == 0) {
/* 422 */       HelperC.afficherAttention("Information", "Veillez indiquer l'anciennet� de l'employ�");
/*     */     } 


/* 427 */     Historique hist = new Historique();
/* 428 */     hist.setDateOperation(Calendar.getInstance().getTime());
/* 429 */     hist.setOperateur(this.operateur);
/* 430 */     if (getId() == 0) {
/* 431 */       hist.setOperation("Cr�ation de la demande de la retraite anticip�e de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */     } else {
/* 433 */       hist.setOperation("Modification de la demande de la retraite anticip�e  de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 434 */     }  hist.setTable(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
/* 435 */     setHistorique(hist);
/* 436 */     if (FactoryDAO.getInstance().insertUpdateDemandeRetraiteAnticipe(this)) {
/*     */       
/* 438 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
				if(parmGn!=null) 
				{
				if(getDecision()==1)
				    HelperC.sendEmail(parmGn.getSmtpServer(), parmGn.getMailOrigine(), parmGn.getMailOrigine(), parmGn.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de retraite anticip�e a �t� accpt�e ", "Demande retraite anticip�e");
				if(getDecision()==2)
				     HelperC.sendEmail(parmGn.getSmtpServer(), parmGn.getMailOrigine(), parmGn.getMailOrigine(), parmGn.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de retraite anticip�e a �t� refus�e", "Demande retraite anticip�e");
				}
/* 439 */      chargementTraite();
/* 440 */       clear();
/*     */     } else {
/*     */       
/* 443 */       HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */     } 
/*     */   }

			public void annuler(){
				if(selected!=null)
				if(FactoryDAO.getInstance().annulerRetraiteAnticipe(selected))
				{
					 HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
					 clear();
				}
				else
					 HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
			}

/*     */   public void supprimer() {
/* 449 */     if (getId() == 0) {
/* 450 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 453 */       Historique hist = new Historique();
/* 454 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 455 */       hist.setOperateur(this.operateur);
/* 456 */       if (getId() == 0) {
/* 457 */         hist.setOperation("Cr�ation de la demande de la retraite anticip�e de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/* 459 */         hist.setOperation("Modification de la demande de la retraite anticip�e  de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/* 460 */       }  hist.setTable(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
/* 461 */       setHistorique(hist);
/* 462 */       if (FactoryDAO.getInstance().deleteDemandeRetraiteAnticipe(this)) {
/* 463 */       chargement();
/* 464 */         clear();
/* 465 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/*     */       } else {
/*     */         
/* 468 */         HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialiser() {
/* 474 */     clear();
/*     */   }
/*     */ }

