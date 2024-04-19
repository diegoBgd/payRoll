/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.CategoriePersonnelC;
/*     */ import classesPaie.ConditionPositionC;
/*     */ import classesPaie.Constante;
import classesPaie.DetailPrimeEmployeC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
import classesPaie.GradePersonnelC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
import classesPaie.ParametragePositionC;
import classesPaie.ParametragePositionDetailC;
import classesPaie.SaisiePositionDetailPrimeC;
/*     */ import classesPaie.SaisiePositionEmployeC;
/*     */ import classesPaie.Tables;
import classesPaie.TraitementSalarialC;

/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;

/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;

/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class DemandePositionB
/*     */   extends SaisiePositionEmployeC
/*     */ {
/*     */   private static final long serialVersionUID = -6034101436213977045L;
/*     */   private int idPosition,idPersonel,idCateg,idGrad,idFonct;
/*     */   private int idCondition,typeDecision;
/*     */   private Constante.Position position;
/*     */   private SaisiePositionEmployeC selected;
/*     */   private EmployeC selection;
/*     */   private CategoriePersonnelC categoriePersonnel;
/*     */   private Base personnel;
/*  54 */   private List<SaisiePositionEmployeC> listDemande,listPosition;
/*  55 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
/*  56 */   
			private OperateurC operateur;
/*  57 */   private List<SelectItem> listConditions = new ArrayList<SelectItem>(); 
			private ExerciceC exercice;
/*  58 */   private HttpSession session = HelperC.getSession(); 
			private String code,libelleDecision; 
			private String codeEmployeRecherche;
/*     */   private String nomEmployeRecherche;
/*     */   private String prenomEmployeRecherche;
/*     */   private boolean disableSave;
            private  SaisiePositionDetailPrimeC selectedDetailPrime;
            double ancienSal=0,nouveauSal=0;
            private List<SelectItem> listPrsnl,listFonct,listGrd,listCateg;
            TraitementSalarialC traitementSal;
            List<DetailPrimeEmployeC> listPrimeEmpl;
            ParametrageGeneralC parm;
			ParametragePositionC parmPos;
			boolean onLine;
			
/*     */   public int getIdCondition() {
/*  63 */     return this.idCondition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdCondition(int idCondition) {
/*  68 */     this.idCondition = idCondition;
/*     */   }
/*     */ 
/*     */   
/*     */   public Constante.Position getPosition() {
/*  73 */     return this.position;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPosition(Constante.Position position) {
/*  78 */     this.position = position;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdPosition() {
/*  83 */     return this.idPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdPosition(int idPosition) {
/*  88 */     this.idPosition = idPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public SaisiePositionEmployeC getSelected() {
/*  93 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(SaisiePositionEmployeC selected) {
/*  98 */     this.selected = selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getSelection() {
/* 103 */     return this.selection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/* 108 */     this.selection = selection;
/*     */   }
/*     */ 
/*     */   
/*     */   public CategoriePersonnelC getCategoriePersonnel() {
/* 113 */     return this.categoriePersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCategoriePersonnel(CategoriePersonnelC categoriePersonnel) {
/* 118 */     this.categoriePersonnel = categoriePersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getPersonnel() {
/* 123 */     return this.personnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/* 128 */     this.personnel = personnel;
/*     */   }
/*     */   
/*     */   public List<SaisiePositionEmployeC> getListDemande() {
/* 132 */     return this.listDemande;
/*     */   }
/*     */   
/*     */   public void setListDemande(List<SaisiePositionEmployeC> listDemande) {
/* 136 */     this.listDemande = listDemande;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/* 140 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/* 144 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   

/*     */   public List<SelectItem> getListConditions() {
/* 156 */     return this.listConditions;
/*     */   }
/*     */   
/*     */   public void setListConditions(List<SelectItem> listConditions) {
/* 160 */     this.listConditions = listConditions;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/* 165 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 170 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 175 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 180 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 185 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 190 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 195 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/* 200 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 205 */     return this.codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 210 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 215 */     return this.nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 220 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 225 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 230 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }

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
/*     */   
/*     */   @PostConstruct
/*     */   private void charger() {
/* 236 */     this.operateur = new OperateurC();
/* 237 */     this.exercice = new ExerciceC();
/* 238 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 239 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 240 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 241 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 242 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 246 */         FacesContext context = FacesContext.getCurrentInstance();
/* 247 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 249 */       catch (IOException e) {
/*     */         
/* 251 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
				listPrsnl=  new ArrayList<SelectItem>();
				listCateg=new ArrayList<SelectItem>();
				listGrd=new ArrayList<SelectItem>();
				listFonct=new ArrayList<SelectItem>();
			
				listGrd.add(new SelectItem(Integer.valueOf(0), ""));
				listCateg.add(new SelectItem(Integer.valueOf(0), ""));
				listPrsnl.add(new SelectItem(Integer.valueOf(0), ""));
				listFonct.add(new SelectItem(Integer.valueOf(0), ""));
				
/* 255 */       this.listConditions.add(new SelectItem(Integer.valueOf(0), ""));
				chargerPersonnel();
				chargerFonction();
				parm=FichierBaseDAO.getInstance().getParametrageGeneral();
				
/* 265 */       setDateDemandePosition(new Date());
/* 266 */       setDateDemandePositionS(HelperC.changeDateFormate(getDateDemandePosition()));
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
				
				
/*     */     } 
/*     */   }

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
		

/*     */   
/*     */   public void findByCode() {
/* 272 */     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
/* 273 */     if (this.selection != null) {
/*     */       
/* 275 */       setCode(this.selection.getCode());
/* 276 */       setEmploye(this.selection);
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
				
/*     */     } else {
/*     */       
/* 279 */       clear1(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear1(boolean b) {
/* 285 */     if (b)
/*     */     {
/* 287 */       this.selection = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changePosition(ValueChangeEvent event) {
/* 293 */     this.listConditions.clear();
/* 294 */     this.idPosition = ((Integer)event.getNewValue()).intValue();
/* 295 */     if (this.idPosition != 0) {
/*     */       
/* 297 */       setPosition(Constante.getPosition(this.idPosition));     
/* 299 */     	chargerCondition();
/*     */     } 
/*     */   }
/*     */ 
/*     */  private void chargerCondition() {
			listConditions.clear();
			listConditions.add(new SelectItem(Integer.valueOf(0), ""));
/* 306 */     for (ConditionPositionC condi : FichierBaseDAO.getInstance().getListeConditionPosition(idPosition))
/* 307 */       listConditions.add(new SelectItem(Integer.valueOf(condi.getId()), condi.getCondition())); 
/*     */   }
/*     */   
/*     */   public void changeCondition(ValueChangeEvent event) {
/* 309 */     this.idCondition = ((Integer)event.getNewValue()).intValue();
/* 310 */     if (this.idCondition != 0)
/*     */     {
/* 312 */       setConditionPosition(FichierBaseDAO.getInstance().getConditionPosition(this.idCondition));
				chargerPosition();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 318 */     if (this.selected != null) {
/*     */       
/* 320 */       setId(this.selected.getId());
               
/* 321 */       setEmploye(this.selected.getEmploye());
/* 322 */       if (getEmploye() != null)
/*     */       {
/* 324 */         setCode(getEmploye().getCode());
				  selection=selected.getEmploye();
/*     */       }
/* 326 */       setPosition(this.selected.getConditionPosition().getPosition());
/* 327 */       if (getPosition() != null) {
/*     */         
/* 329 */         this.idPosition = Constante.getPosition(getPosition());
/*     */         chargerCondition();
/* 331 */         
/*     */       } 
/*     */       
/* 337 */       setConditionPosition(this.selected.getConditionPosition());
/* 338 */       if (getConditionPosition() != null)
/*     */       {
/* 340 */         this.idCondition = getConditionPosition().getId();
/*     */       }
				
/* 342 */       setDateDemandePosition(this.selected.getDateDemandePosition());
/* 343 */       setDateDemandePositionS(this.selected.getDateDemandePositionS());
				setDateDebut(selected.getDateDebut());
				setDateFin(selected.getDateFin());
				setDateDebutS(selected.getDateDebutS());
				setDateFinS(selected.getDateFinS());
				setDecision(selected.getDecision()); 
/* 345 */       setDuree(this.selected.getDuree());
/* 347 */       setMotifDemande(this.selected.getMotifDemande());		
				setMotifRefus(selected.getMotifRefus());
				
				switch (getDecision()) {
				case 1:
					libelleDecision="Demande acc�pt�e ";
					break;
				case 2:
					libelleDecision="Demande refus�e ";
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

/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear() {
/* 353 */     setId(0);
/* 354 */     setEmploye(null);
/* 355 */     setConditionPosition(null);
/* 356 */     setDateDemandePosition(null);
/* 357 */     setEtat(0);
/* 358 */     setDuree(0);
/* 360 */     setMotifDemande("");
			  setDateDebutS("");
			  setDateFinS("");
			  setDateDemandePositionS("");
			  setDuree(0);
			  listConditions.clear();
			  listConditions.add(new SelectItem(Integer.valueOf(0), ""));
/* 361 */     this.listConditions.clear();
/* 362 */     this.idCondition = 0;
/* 363 */     this.idPosition = 0;
/* 364 */     this.nomEmployeRecherche = "";
/* 365 */     this.code = "";
/* 366 */     this.prenomEmployeRecherche = "";
/* 367 */     this.selected = null;
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
/*     */   }
/*     */ 


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
	
	
	
/*     */   public void changeDateDemandePosition() {
/* 372 */     if (getDateDemandePositionS().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 374 */       setDateDemandePosition(null);
/*     */     } else {
/*     */       
/* 377 */       setDateDemandePosition(HelperC.validerDate(getDateDemandePositionS()));
/* 378 */       if (getDateDemandePosition() == null) {
/*     */         
/* 380 */         setDateDemandePositionS("");
/* 381 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 384 */         setDateDemandePositionS(HelperC.convertDate(getDateDemandePosition()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateDebut() {
/* 391 */     if (getDateDebutS().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 393 */       setDateDebut(null);
/*     */     } else {
/*     */       
/* 396 */       setDateDebut(HelperC.validerDate(getDateDebutS()));
/* 397 */       if (getDateDebut() == null) {
/*     */         
/* 399 */         setDateDebutS("");
/* 400 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 403 */         setDateDebutS(HelperC.convertDate(getDateDebut()));
					//searchPeriod();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateFin() {
/* 410 */     if (getDateFinS().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 412 */       setDateFin(null);
/*     */     } else {
/*     */       
/* 415 */       setDateFin(HelperC.validerDate(getDateFinS()));
/* 416 */       if (getDateFin() == null) {
/*     */         
/* 418 */         setDateFinS("");
/* 419 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 422 */         setDateFinS(HelperC.convertDate(getDateFin()));
				  //searchPeriod();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 429 */     this.selected = (SaisiePositionEmployeC)event.getObject();
/* 430 */     if (this.selected != null)
/*     */     {
/* 432 */       setObject();
				PrimeFaces.current().executeScript("PF('dlgDemande').hide();");
/*     */     }
/*     */   }
/*     */ 
			public void onRowDmdSelected(SelectEvent event) {
/* 429 */     this.selected = (SaisiePositionEmployeC)event.getObject();
/* 430 */     if (this.selected != null)
/*     */     {
/* 432 */       setObject();
				PrimeFaces.current().executeScript("PF('dlgDem').hide();");
/*     */     }
/*     */   }
/*     */   public void onRowDecidSelected(SelectEvent event) {
	/* 429 */     this.selected = (SaisiePositionEmployeC)event.getObject();
	/* 430 */     if (this.selected != null)
	/*     */     {
	/* 432 */       setObject();
					PrimeFaces.current().executeScript("PF('dlgDec').hide();");
	/*     */     }
	/*     */   }
/*     */   private void setObject1() {
/* 438 */     if (this.selection != null) {
/*     */       
/* 440 */       setEmploye(this.selection);
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
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowselected1(SelectEvent event) {
/* 453 */     this.selection = (EmployeC)event.getObject();
/* 454 */     setObject1();
/* 455 */     PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */   }
/*     */   
            private void chargerPosition(){
            	disableSave=false;
            	if(selection!=null)
            	{
            		parmPos=FichierBaseDAO.getInstance().getParametrePosition(idCondition, selection.getIdPersnl());
            		
            		if(parmPos==null)
            		{
            			disableSave=true;
            			HelperC.afficherAttention("Information", "Il faut param�trer les informations en rapport avec cette condition pour ce personnel !");
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
/*     */   
/*     */   public void enregistrer() {
/* 466 */     if (getEmploye() == null) {
/*     */       
/* 468 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/*     */     }
/* 470 */     else if (getDateDemandePosition() == null) {
/*     */       
/* 472 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
/*     */     }
/* 474 */     else if (getConditionPosition() == null) {
/*     */       
/* 476 */       HelperC.afficherMessage("Information", "Veillez s�l�ctionner la condition de cette position");
/*     */     }
/* 478 */     else if (getMotifDemande().equalsIgnoreCase("")) {
/*     */       
/* 480 */       HelperC.afficherAttention("Information", "Veillez decrire le motif de votre demande");
/*     */     } else {
/*     */       
/* 483 */       Historique hist = new Historique();
/* 484 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 485 */       hist.setOperateur(this.operateur);
/* 486 */       if (getId() == 0) {
/*     */         
/* 488 */         hist.setOperation("Cr�ation de la demande de position de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/*     */         
/* 491 */         hist.setOperation("Modification de la demande de position de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } 
/* 493 */       hist.setTable(Tables.getTableName(Tables.TableName.saisiePositionEmploye));
/* 494 */       setHistorique(hist);
				setEtat(Constante.getEtatPosition(Constante.EtatPosition.demandePosition));
/* 495 */       if (FactoryDAO.getInstance().insertUpdateSaisiePositionEmploye(this)) {
/*     */         
/* 497 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 498 */        
/* 499 */         clear();
/*     */       } else {
/*     */         
/* 502 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
			public void saveDecision() {
/* 466 */     if (getEmploye() == null) {
/*     */       
/* 468 */       HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
/*     */     }
/* 470 */     else if (getDateDemandePosition() == null) {
/*     */       
/* 472 */       HelperC.afficherMessage("Information", "Veillez d'abord saisir la date de demande");
/*     */     }
/* 474 */     else if (getConditionPosition() == null) {
/*     */       
/* 476 */       HelperC.afficherMessage("Information", "Veillez s�l�ctionner la condition de cette position");
/*     */     }
/* 478 */     else if (getDecision()==0) {
/*     */       
/* 480 */       HelperC.afficherAttention("Information", "Veillez pr�ciser la d�cision prise !");
/*     */     } else {
/*     */       
/* 483 */       Historique hist = new Historique();
/* 484 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 485 */       hist.setOperateur(this.operateur);
/* 486 */       if (getId() == 0) {
/*     */         
/* 488 */         hist.setOperation("D�cision demande de position de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/*     */         
/* 491 */         hist.setOperation("Modification de la D�cision de la demande de position de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } 
/* 493 */       hist.setTable(Tables.getTableName(Tables.TableName.saisiePositionEmploye));
/* 494 */       setHistorique(hist);
			
				if(getDateDecision()==null)
				 setDateDecision(new Date());
				 setEtat(Constante.getEtatPosition(Constante.EtatPosition.decide));
				 
/* 495 */       if (FactoryDAO.getInstance().insertUpdateSaisiePositionEmploye(this)) {
/*     */         
/* 497 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 498 */        if(parm!=null) 
					{
					if(getDecision()==1)
					     HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(), parm.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de position a �t� accept�e ", "Demande position");
					if(getDecision()==2)
					     HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(), parm.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de position a �t� refus�e\nMotif : "+getMotifRefus(), "Demande position");
					}
/* 499 */         clear();
/*     */       } else {
/*     */         
/* 502 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }

	public void saveInLine() {
		if (getEmploye() == null) {

			HelperC.afficherMessage("Information", "Veillez saisir l'employ�");
		} else if (getDateDemandePosition() == null) {

			HelperC.afficherMessage("Information",
					"Veillez d'abord saisir la date de demande");
		} else if (getConditionPosition() == null) {

			HelperC.afficherMessage("Information",
					"Veillez s�l�ctionner la condition de cette position");
		}  else {

			Historique hist = new Historique();
			hist.setDateOperation(Calendar.getInstance().getTime());
			hist.setOperateur(this.operateur);
			if (getId() == 0) {

				hist.setOperation("D�cision demande de position de l'employ� "
						+ getEmploye().getNomPrenom());
			} else {

				hist.setOperation("Modification de la D�cision de la demande de position de l'employ� "
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
							"Succ�s de l'op�ration");
					
					clearInline();
				} else {
					HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
				}
			} else {
				
				HelperC.afficherAttention("Information",
						"La demande a �t� d�j� trait�e !");
			}
		}
	}
			public void annuler() {
		    if(selected!=null)
		    {
		    	 Historique hist = new Historique();
		    	 hist.setDateOperation(Calendar.getInstance().getTime());
		    	 hist.setOperateur(this.operateur);
		    	
		    	
		          hist.setOperation("Annulation de la D�cision de la demande de position de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
		    	    
		    	    hist.setTable(Tables.getTableName(Tables.TableName.saisiePositionEmploye));
		    	    selected.setHistorique(hist);
		    	  		
		    	if(FactoryDAO.getInstance().annulerPositionEmploye(selected))
				{
		    		HelperC.afficherMessage("F�licitation", "Annulation avec succ�s");
		    		clear();
				}
		    	else
		    		 HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
		    
		    }
/*     */   }
/*     */ 
/*     */   public void supprimer() {
/* 509 */     if (getId() == 0) {
/*     */       
/* 511 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 514 */       Historique hist = new Historique();
/* 515 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 516 */       hist.setOperateur(this.operateur);
/* 517 */       if (getId() == 0) {
/*     */         
/* 519 */         hist.setOperation("Cr�ation de la demande de position de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } else {
/*     */         
/* 522 */         hist.setOperation("Modification de la demande de position de l'employ� " + getEmploye().getNom() + " " + getEmploye().getPrenom());
/*     */       } 
/* 524 */       hist.setTable(Tables.getTableName(Tables.TableName.saisiePositionEmploye));
/* 525 */       setHistorique(hist);
				if (getDecision()==0) {
/* 526 */       if (FactoryDAO.getInstance().deleteSaisiePositionEmploye(this)) {
/*     */         
/* 529 */         clear();
/* 530 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/*     */       } else {
/*     */         
/* 533 */         HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */       } 
				}
				HelperC.afficherAttention("Information",
						"Impossible de supprimer car la d�cision a �t� prise !");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 540 */     clear();
/*     */   }
/*     */  public void initialiserLine() {
           clearInline();
	       }
/*     */ 
/*     */   

/*     */ }


