/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.CategoriePersonnelC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageAvancementGradeC;
/*     */ import classesPaie.Tables;
/*     */ import classesPaie.TypeNotationC;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametrageAvancementGradeB
/*     */   extends ParametrageAvancementGradeC
/*     */ {
/*     */   private static final long serialVersionUID = -8088736129546101L;
/*     */   private int idType;
/*     */   private int idTypeAppreciation;
/*     */   private int idPersonnel;
/*     */   private int idCategorie;
/*     */   private int idAncienGrade;
/*     */   private int idGradeActuel;
/*     */   private ParametrageAvancementGradeC avancement;
/*     */   private CategoriePersonnelC categoriePersonnel;
/*     */   private Base personnel;
/*  51 */   private List<ParametrageAvancementGradeC> listParametreAvancement = new ArrayList<ParametrageAvancementGradeC>();
/*  52 */   private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>();
/*  53 */   private List<SelectItem> listTypeAppreciation = new ArrayList<SelectItem>();
/*  54 */   private List<SelectItem> listPersonnel = new ArrayList<SelectItem>();
/*  55 */   private List<SelectItem> categoriePersonnels = new ArrayList<SelectItem>();
/*  56 */   private boolean disableMsg;
/*  58 */   private HttpSession session = HelperC.getSession();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   
/*     */   public int getIdType() {
/*  63 */     return this.idType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdType(int idType) {
/*  68 */     this.idType = idType;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParametrageAvancementGradeC getAvancement() {
/*  73 */     return this.avancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAvancement(ParametrageAvancementGradeC avancement) {
/*  78 */     this.avancement = avancement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  85 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  90 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  95 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 100 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 105 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 110 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdTypeAppreciation() {
/* 115 */     return this.idTypeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdTypeAppreciation(int idTypeAppreciation) {
/* 120 */     this.idTypeAppreciation = idTypeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdPersonnel() {
/* 125 */     return this.idPersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdPersonnel(int idPersonnel) {
/* 130 */     this.idPersonnel = idPersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdCategorie() {
/* 135 */     return this.idCategorie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdCategorie(int idCategorie) {
/* 140 */     this.idCategorie = idCategorie;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdAncienGrade() {
/* 145 */     return this.idAncienGrade;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdAncienGrade(int idAncienGrade) {
/* 150 */     this.idAncienGrade = idAncienGrade;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdGradeActuel() {
/* 155 */     return this.idGradeActuel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdGradeActuel(int idGradeActuel) {
/* 160 */     this.idGradeActuel = idGradeActuel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CategoriePersonnelC getCategoriePersonnel() {
/* 166 */     return this.categoriePersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCategoriePersonnel(CategoriePersonnelC categoriePersonnel) {
/* 171 */     this.categoriePersonnel = categoriePersonnel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Base getPersonnel() {
/* 177 */     return this.personnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/* 182 */     this.personnel = personnel;
/*     */   }
/*     */   
/*     */   public List<ParametrageAvancementGradeC> getListParametreAvancement() {
/* 186 */     return this.listParametreAvancement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListParametreAvancement(List<ParametrageAvancementGradeC> listParametreAvancement) {
/* 191 */     this.listParametreAvancement = listParametreAvancement;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListTypeNotation() {
/* 195 */     return this.listTypeNotation;
/*     */   }
/*     */   
/*     */   public void setListTypeNotation(List<SelectItem> listTypeNotation) {
/* 199 */     this.listTypeNotation = listTypeNotation;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListTypeAppreciation() {
/* 203 */     return this.listTypeAppreciation;
/*     */   }
/*     */   
/*     */   public void setListTypeAppreciation(List<SelectItem> listTypeAppreciation) {
/* 207 */     this.listTypeAppreciation = listTypeAppreciation;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListPersonnel() {
/* 211 */     return this.listPersonnel;
/*     */   }
/*     */   
/*     */   public void setListPersonnel(List<SelectItem> listPersonnel) {
/* 215 */     this.listPersonnel = listPersonnel;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getCategoriePersonnels() {
/* 219 */     return this.categoriePersonnels;
/*     */   }
/*     */   
/*     */   public void setCategoriePersonnels(List<SelectItem> categoriePersonnels) {
/* 223 */     this.categoriePersonnels = categoriePersonnels;
/*     */   }
/*     */   

			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/* 245 */     this.operateur = new OperateurC();
/* 246 */     this.exercice = new ExerciceC();
/* 247 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 248 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 249 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 250 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 251 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 255 */         FacesContext context = FacesContext.getCurrentInstance();
/* 256 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 258 */       catch (IOException e) {
/*     */         
/* 260 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
				chargerNotation();       

				chargerAppreciation();
       
				chargerPersonnel();
				chargement();
     		} 

  			}
			
			private void chargerPersonnel() {
			this.listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));
	       
		       for (Base personnel : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel)))
		       {
		       this.listPersonnel.add(new SelectItem(Integer.valueOf(personnel.getId()), personnel.getDesignation()));
		       }
			}
			private void chargerAppreciation() {
			this.listTypeAppreciation.add(new SelectItem(Integer.valueOf(0), ""));
	      
		       for (Base appreciation : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAppreciationAvancement)))
		       {
		    	   this.listTypeAppreciation.add(new SelectItem(Integer.valueOf(appreciation.getId()), appreciation.getDesignation()));
		       }
		   }
			private void chargerNotation() {
				this.listTypeNotation.add(new SelectItem(Integer.valueOf(0), ""));
	      
		      for (TypeNotationC notation : FichierBaseDAO.getInstance().getAllTypeNotation())
		      {
		         this.listTypeNotation.add(new SelectItem(Integer.valueOf(notation.getId()), notation.getDesignation()));
		      }
	
			}
			private void chargerCategorie() {
				this.categoriePersonnels.add(new SelectItem(Integer.valueOf(0), ""));
	        
		        for (CategoriePersonnelC categ : FichierBaseDAO.getInstance().getListeCategoriePersonnel(getPersonnel()))
		        {
		           this.categoriePersonnels.add(new SelectItem(Integer.valueOf(categ.getId()), categ.getDesignation()));
		        }
				}
/*     */ 
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 290 */     if (this.avancement != null) {
/*     */        disableMsg=false;
/* 292 */       setId(this.avancement.getId());
/* 293 */       setNombreDeFoisNotation(this.avancement.getNombreDeFoisNotation());
/* 294 */       
/* 295 */       setTypeAppreciation(this.avancement.getTypeAppreciation());
/* 296 */       
/* 298 */       setPersonnel(this.avancement.getPersonnel());
/* 299 */       setTypeNotation(this.avancement.getTypeNotation());
/* 300 */       if (getTypeNotation() != null)
/*     */       {
/* 302 */         this.idType = getTypeNotation().getId();
/*     */       }
/* 304 */       if (getTypeAppreciation() != null)
/*     */       {
/* 306 */         this.idTypeAppreciation = getTypeAppreciation().getId();
/*     */       }
/* 308 */       
/* 317 */         setPersonnel(this.avancement.getPersonnel());
/* 318 */         if (getPersonnel() != null) {
/*     */           
/* 320 */           this.idPersonnel = getPersonnel().getId();
/* 321 */           this.categoriePersonnels.clear();
					chargerCategorie();
/*     */           
/* 329 */           setCategoriePersonnel(this.avancement.getCategorie());
/* 330 */           if (getCategoriePersonnel() != null) {
/*     */             
/* 332 */             this.idCategorie = getCategoriePersonnel().getId();
/* 333 */            
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 347 */      
/*     */     
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear() {
/* 357 */     setId(0);
/* 358 */     setTypeNotation(null);
/* 359 */     setNombreDeFoisNotation(0);
/* 360 */     setTypeAppreciation(null);
/* 361 */     setPersonnel(null);
			  disableMsg=true;
/* 368 */     setPersonnel(null);
/* 369 */     setCategoriePersonnel(null);

/* 376 */     setPersonnel((Base)null);
/* 377 */     this.avancement = null;
/* 378 */     this.idPersonnel = 0;
/* 379 */     this.idCategorie = 0;
/* 380 */     this.idAncienGrade = 0;
/* 381 */     this.idGradeActuel = 0;
/* 382 */     this.idTypeAppreciation = 0;
/* 383 */     this.idType = 0;
/* 384 */     this.categoriePersonnels.clear();
/* 385 */     
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelect(SelectEvent event) {
/* 391 */     this.avancement = (ParametrageAvancementGradeC)event.getObject();
/* 392 */     if (this.avancement != null) {
/*     */        disableMsg=true;
/* 394 */       setObject();
/* 395 */       PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeTypeNotation(ValueChangeEvent event) {
/* 402 */     this.idType = ((Integer)event.getNewValue()).intValue();
/* 403 */     if (this.idType != 0)
/*     */     {
/* 405 */       setTypeNotation(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.typeNotation)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changePersonnel(ValueChangeEvent event) {
/* 411 */     this.categoriePersonnels.clear();
/* 412 */     this.idPersonnel = ((Integer)event.getNewValue()).intValue();
/* 413 */     if (this.idPersonnel != 0) {
/*     */       
/* 415 */       setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.personnel)));
/* 416 */       if (getPersonnel() != null) {
/*     */         
/* 418 */         chargerCategorie();
/*     */       } 
/*     */     } 
/*     */   }
			
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeCategorie(ValueChangeEvent event) {
/* 431 */     if (event != null) {
/*     */       
/* 433 */       this.idCategorie = ((Integer)event.getNewValue()).intValue();
/* 434 */       if (this.idCategorie != 0) {
/*     */         
/* 436 */         setCategoriePersonnel(FichierBaseDAO.getInstance().getCategoriePersonnel(this.idCategorie));
/* 437 */         if (getCategoriePersonnel() != null) {
/*     */           
/* 439 */          
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   


/*     */   
/*     */   public void changeTypeAppreciation(ValueChangeEvent event) {
/* 473 */     this.idTypeAppreciation = ((Integer)event.getNewValue()).intValue();
/* 474 */     if (this.idTypeAppreciation != 0)
/*     */     {
/* 476 */       setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(this.idTypeAppreciation, Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargement() {
/* 482 */     listParametreAvancement = FichierBaseDAO.getInstance().getListeParametrageAvancement();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 487 */     clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 492 */     if (getPersonnel() == null) {
/*     */       
/* 494 */       HelperC.afficherMessage("Information", "Veillez selectionner le personnel!");
/*     */     }
/* 496 */     else if (getTypeNotation() == null) {
/*     */       
/* 498 */       HelperC.afficherMessage("Information", "Veillez selectionner le type de notation!");
/*     */     } else {

	       	if (FichierBaseDAO.getInstance().insertUpdateParametrageAvancementGrade(this)) {
/*     */           
/* 506 */           HelperC.afficherMessage("Information", "Succ�s d'enregistrement");
/* 507 */           clear();
					chargement();
/*     */         } else {
/*     */           
/* 510 */           HelperC.afficherMessage("information", "Echec d'enregistrement");
/*     */         
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 523 */       if (getId() == 0) {
/*     */         
/* 525 */         HelperC.afficherDeleteMessage();
/*     */       }
/* 527 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.parametrageAvancementGrade))) {
/*     */         
/* 529 */         HelperC.afficherMessage("F�licitation", "Succ�s de l'Op�ration");
/* 530 */         clear();
				  chargement();
/*     */       } else {
/*     */         
/* 533 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */       }
/*     */     
/* 536 */     } catch (Exception e) {
/*     */       
/* 538 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }

