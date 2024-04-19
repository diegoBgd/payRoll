/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.CategoriePersonnelC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.GradePersonnelC;
/*     */ import classesPaie.GradePersonnelDetailC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
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
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class GradePersonnelB
/*     */   extends GradePersonnelC
/*     */ {
/*     */   private static final long serialVersionUID = 5655626376938669692L;
/*     */   private int idCategorie;
/*     */   private int idNiveau;
/*     */   private int idPersonnel;
/*     */   private int ageFormation;
			private int idGrdInf;
/*  39 */   private int index = 1;
/*     */   private GradePersonnelC grade;
/*     */   private GradePersonnelDetailC detailNiveauF;
/*     */   private Base niveauFormation;
/*     */   private Base personnel;
/*     */   private List<GradePersonnelC> grades;
/*  45 */   private List<SelectItem> categoriePersonnels = new ArrayList<SelectItem>();
/*  46 */   private List<SelectItem> listPersonnels = new ArrayList<SelectItem>();
/*     */  private List<SelectItem> listGrdInf;
/*  48 */   private List<SelectItem> niveaux = new ArrayList<SelectItem>();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  51 */   private HttpSession session = HelperC.getSession();
/*     */ 
/*     */   private List<EmployeB> listRetraite;
/*     */   private double bonus;
/*     */   private boolean disableMsg;
/*     */   private String bonusS;
/*     */ 
/*     */   
/*     */   public int getIdCategorie() {
/*  60 */     return this.idCategorie;
/*     */   }
/*     */   
/*     */   public void setIdCategorie(int idCategorie) {
/*  64 */     this.idCategorie = idCategorie;
/*     */   }
/*     */   
/*     */   public int getIdNiveau() {
/*  68 */     return this.idNiveau;
/*     */   }
/*     */   
/*     */   public void setIdNiveau(int idNiveau) {
/*  72 */     this.idNiveau = idNiveau;
/*     */   }
/*     */   
/*     */   public int getIndex() {
/*  76 */     return this.index;
/*     */   }
/*     */   
/*     */   public void setIndex(int index) {
/*  80 */     this.index = index;
/*     */   }
/*     */   
/*     */   public GradePersonnelC getGrade() {
/*  84 */     return this.grade;
/*     */   }
/*     */   
/*     */   public void setGrade(GradePersonnelC grade) {
/*  88 */     this.grade = grade;
/*     */   }
/*     */   
/*     */   public GradePersonnelDetailC getDetailNiveauF() {
/*  92 */     return this.detailNiveauF;
/*     */   }
/*     */   
/*     */   public void setDetailNiveauF(GradePersonnelDetailC detailNiveauF) {
/*  96 */     this.detailNiveauF = detailNiveauF;
/*     */   }
/*     */   
/*     */   public List<GradePersonnelC> getGrades() {
/* 100 */     return this.grades;
/*     */   }
/*     */   
/*     */   public void setGrades(List<GradePersonnelC> grades) {
/* 104 */     this.grades = grades;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getCategoriePersonnels() {
/* 108 */     return this.categoriePersonnels;
/*     */   }
/*     */   
/*     */   public void setCategoriePersonnels(List<SelectItem> categoriePersonnels) {
/* 112 */     this.categoriePersonnels = categoriePersonnels;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getNiveaux() {
/* 116 */     return this.niveaux;
/*     */   }
/*     */   
/*     */   public void setNiveaux(List<SelectItem> niveaux) {
/* 120 */     this.niveaux = niveaux;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/* 124 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 128 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/* 132 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 136 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/* 140 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 144 */     this.session = session;
/*     */   }
/*     */   
/*     */   public int getIdPersonnel() {
/* 148 */     return this.idPersonnel;
/*     */   }
/*     */   
/*     */   public void setIdPersonnel(int idPersonnel) {
/* 152 */     this.idPersonnel = idPersonnel;
/*     */   }
/*     */   
/*     */   public Base getPersonnel() {
/* 156 */     return this.personnel;
/*     */   }
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/* 160 */     this.personnel = personnel;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListPersonnels() {
/* 164 */     return this.listPersonnels;
/*     */   }
/*     */   
/*     */   public void setListPersonnels(List<SelectItem> listPersonnels) {
/* 168 */     this.listPersonnels = listPersonnels;
/*     */   }
/*     */   
/*     */   public double getBonus() {
/* 172 */     return this.bonus;
/*     */   }
/*     */   
/*     */   public void setBonus(double bonus) {
/* 176 */     this.bonus = bonus;
/*     */   }
/*     */   
/*     */   public String getBonusS() {
/* 180 */     return this.bonusS;
/*     */   }
/*     */   
/*     */   public void setBonusS(String bonusS) {
/* 184 */     this.bonusS = bonusS;
/*     */   }
/*     */   
/*     */   public int getAgeFormation() {
/* 188 */     return this.ageFormation;
/*     */   }
/*     */   
/*     */   public void setAgeFormation(int ageFormation) {
/* 192 */     this.ageFormation = ageFormation;
/*     */   }


			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
			public List<EmployeB> getListRetraite() {
				return listRetraite;
			}
			public void setListRetraite(List<EmployeB> listRetraite) {
			this.listRetraite = listRetraite;
			}
			public List<SelectItem> getListGrdInf() {
				return listGrdInf;
			}
			public void setListGrdInf(List<SelectItem> listGrdInf) {
				this.listGrdInf = listGrdInf;
			}
			public int getIdGrdInf() {
				return idGrdInf;
			}
			public void setIdGrdInf(int idGrdInf) {
				this.idGrdInf = idGrdInf;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/* 197 */     this.operateur = new OperateurC();
/* 198 */     this.exercice = new ExerciceC();
/*     */     disableMsg=true;
/* 200 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 201 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 202 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 203 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/* 205 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/* 208 */         FacesContext context = FacesContext.getCurrentInstance();
/* 209 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 210 */       } catch (IOException e) {
/*     */         
/* 212 */         e.printStackTrace();
/*     */       } 
/*     */     }
			  listGrdInf=new ArrayList<SelectItem>();
			
/* 215 */     this.listPersonnels.add(new SelectItem(Integer.valueOf(0), ""));
/* 216 */     for (Base person : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel))) {
/* 217 */       this.listPersonnels.add(new SelectItem(Integer.valueOf(person.getId()), person.getDesignation()));
/*     */     }
/*     */     
/* 220 */     this.niveaux.add(new SelectItem(Integer.valueOf(0), ""));
/* 221 */     for (Base niveau : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.niveauFormation))) {
				this.niveaux.add(new SelectItem(Integer.valueOf(niveau.getId()), niveau.getDesignation()));
/*     */     }
				chargementGrade();
				chargerGradeInf();
/*     */   }
			
			private void chargerGradeInf(){
				listGrdInf.add(new SelectItem(Integer.valueOf(0), ""));
				for (Base grd : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.gradePersonnel))) {
					listGrdInf.add(new SelectItem(grd.getId(), grd.getDesignation()));
				}
			}
			public void changeGrdInf(ValueChangeEvent event)
			{
				idGrdInf= ((Integer)event.getNewValue()).intValue();
				this.setIdGradeInferieur(idGrdInf);
			}

/*     */   private void chargementGrade() {
/* 227 */     this.grades = FichierBaseDAO.getInstance().getListGradeParCategoriePersonnel(this.idCategorie);
/*     */   }
/*     */   
/*     */   private void clear(boolean b) {
/* 231 */     if (b)
/* 232 */       setId(0); 
              setCode("");
/* 233 */     setDesignation("");
/* 234 */     setTraitementMensuel(0.0D);
/* 235 */     setTraitementMensuelS("");
/* 236 */     this.idCategorie = 0;
			  disableMsg=true;
/* 237 */     this.idNiveau = 0;
/* 238 */     this.idPersonnel = 0;
/* 239 */     this.ageFormation = 0;
/* 240 */     this.bonus = 0.0D;
/* 241 */     this.bonusS = "";
/* 242 */     setPersonnel(null);
/* 243 */     getListNiveau().clear();
/* 244 */     this.grade = null;
			  idGrdInf=0;
			  
/* 245 */     getCategoriePersonnels().clear();

/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changePersonnel(ValueChangeEvent event) {
/* 251 */     this.categoriePersonnels.clear();

/* 252 */     this.categoriePersonnels.add(new SelectItem(Integer.valueOf(0), ""));
/* 253 */     this.idCategorie = 0;
/*     */    

/* 255 */     this.idPersonnel = ((Integer)event.getNewValue()).intValue();
/*     */     
/* 257 */     if (this.idPersonnel != 0) {
/* 258 */       setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.personnel)));
/*     */       
/* 260 */       if (getPersonnel() != null) {
/* 261 */         for (CategoriePersonnelC categ : FichierBaseDAO.getInstance().getListeCategoriePersonnel(getPersonnel())) {
/* 262 */           this.categoriePersonnels.add(new SelectItem(Integer.valueOf(categ.getId()), categ.getDesignation()));
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCategorie(ValueChangeEvent event) {
/* 270 */     this.idCategorie = ((Integer)event.getNewValue()).intValue();
/* 271 */     if (this.idCategorie != 0) {
/* 272 */       setCategoriePersonnel(FichierBaseDAO.getInstance().getCategoriePersonnel(this.idCategorie));
/* 273 */       chargementGrade();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setObject() {
				disableMsg=true;
/* 280 */     if (this.grade != null) {
/*     */       
/* 282 */       setId(this.grade.getId());
/*     */       disableMsg=false;
/* 284 */       if (this.grade.getCategoriePersonnel().getPersonnel() != null) {
/*     */         
/* 286 */         setPersonnel(this.grade.getCategoriePersonnel().getPersonnel());
/* 287 */         if (getPersonnel() != null) {
/* 288 */           this.idPersonnel = getPersonnel().getId();
/* 289 */           this.categoriePersonnels.clear();
/* 290 */           for (CategoriePersonnelC categ : FichierBaseDAO.getInstance().getListeCategoriePersonnel(getPersonnel())) {
/* 291 */             this.categoriePersonnels.add(new SelectItem(Integer.valueOf(categ.getId()), categ.getDesignation()));
/*     */           }
/*     */           idGrdInf=grade.getIdGradeInferieur();
/* 294 */           setCategoriePersonnel(this.grade.getCategoriePersonnel());
/* 295 */           if (getCategoriePersonnel() != null) {
/* 296 */             this.idCategorie = getCategoriePersonnel().getId();
/*     */           }
/*     */         } 
                  setCode(grade.getCode());
/* 299 */         setDesignation(this.grade.getDesignation());
/* 300 */         setTraitementMensuel(this.grade.getTraitementMensuel());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 308 */     this.grade = (GradePersonnelC)event.getObject();
/* 309 */     if (this.grade != null) {
/* 310 */       setObject();
/* 311 */       setListNiveau(FichierBaseDAO.getInstance().getListeDetailNiveauFormation(this.grade));
/* 312 */       PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargerNiveau() {
/* 318 */     if (this.niveauFormation == null) {
/* 319 */       HelperC.afficherMessage("Information", "veillez s�l�ctionner le niveau de formation");
/* 320 */     } else if (this.bonus > 100.0D) {
/* 321 */       HelperC.afficherMessage("Information", "Le bonus ne peut pas etre sup�rieur � 100");
/*     */     } else {
/*     */       
/* 324 */       for (GradePersonnelDetailC det : getListNiveau()) {
/* 325 */         if (this.detailNiveauF != null && 
/* 326 */           det.getNiveau() == this.detailNiveauF.getNiveau()) {
/* 327 */           this.detailNiveauF = det;
/* 328 */           this.detailNiveauF.setExiste(true);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 334 */       if (this.detailNiveauF == null) {
/* 335 */         this.detailNiveauF = new GradePersonnelDetailC();
/*     */       }
/* 337 */       this.detailNiveauF.setNiveau(this.niveauFormation);
/* 338 */       this.detailNiveauF.setTauxBonusSalaire(getBonus());
/* 339 */       this.detailNiveauF.setTauxBonusSalaireS(getBonusS());
/* 340 */       this.detailNiveauF.setAge(getAgeFormation());
/*     */       
/* 342 */       if (!this.detailNiveauF.isExiste())
/* 343 */         getListNiveau().add(this.detailNiveauF); 
/* 344 */       clearDetailNiveau();
/* 345 */       this.detailNiveauF = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearDetailNiveau() {
/* 351 */     this.idNiveau = 0;
/* 352 */     this.detailNiveauF = null;
/* 353 */     this.bonus = 0.0D;
/* 354 */     this.bonusS = "";
/* 355 */     this.niveauFormation = null;
/* 356 */     this.ageFormation = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enleverNiveau() {
/* 363 */     if (this.detailNiveauF == null) {
/* 364 */       HelperC.afficherMessage("Information", "Pr�cisez l'�l�ment � supprimer");
/*     */     
/*     */     }
/* 367 */     else if (this.detailNiveauF.getNiveau() != null) {
/*     */       
/* 369 */       getListNiveauDeleted().add(this.detailNiveauF);
/* 370 */       getListNiveau().remove(this.detailNiveauF);
/*     */     } 
/*     */     
/* 373 */     clearDetailNiveau();
/* 374 */     this.detailNiveauF = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void affecterDetailNiveau() {
/* 380 */     if (this.detailNiveauF != null) {
/*     */       
/* 382 */       this.idNiveau = this.detailNiveauF.getNiveau().getId();
/* 383 */       this.niveauFormation = this.detailNiveauF.getNiveau();
/* 384 */       this.bonus = this.detailNiveauF.getTauxBonusSalaire();
/* 385 */       this.bonusS = this.detailNiveauF.getTauxBonusSalaireS();
/* 386 */       this.ageFormation = this.detailNiveauF.getAge();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelectedDetailNiveau(SelectEvent event) {
/* 392 */     this.detailNiveauF = (GradePersonnelDetailC)event.getObject();
/* 393 */     if (this.detailNiveauF != null) {
/* 394 */       affecterDetailNiveau();
/* 395 */       PrimeFaces.current().executeScript("PF('dlg').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeNiveau(ValueChangeEvent event) {
/* 402 */     this.idNiveau = ((Integer)event.getNewValue()).intValue();
/* 403 */     this.niveauFormation = FichierBaseDAO.getInstance().getBaseById(this.idNiveau, Tables.getTableName(Tables.TableName.niveauFormation));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeTraitementMensuel() {
/*     */     try {
/* 409 */       setTraitementMensuel(Double.valueOf(getTraitementMensuelS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     }
/* 411 */     catch (Exception e) {
/* 412 */       setTraitementMensuel(0.0D);
/*     */     } finally {
/*     */       
/* 415 */       setTraitementMensuelS(HelperC.TraitementMontant.getMontantFormate(getTraitementMensuel(), 2));
/* 416 */       setTraitementMensuel(Double.valueOf(getTraitementMensuelS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeBonus() {
/*     */     try {
/* 423 */       setBonus(Double.valueOf(getBonusS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     }
/* 425 */     catch (Exception e) {
/* 426 */       setBonus(0.0D);
/*     */     } finally {
/*     */       
/* 429 */       setBonusS(HelperC.TraitementMontant.getMontantFormate(getBonus(), 2));
/* 430 */       setBonus(Double.valueOf(getBonusS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 436 */     if (getCategoriePersonnel() == null) {
/* 437 */       HelperC.afficherMessage("Information", "Pr�cisez la cat�gorie");
/* 438 */     } else if (getDesignation().trim().equals("")) {
/* 439 */       HelperC.afficherMessage("Information", " Pr�cisez la d�signation");
/* 440 */     }  else {
/*     */       
/* 444 */       Historique hist = new Historique();
/* 445 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 446 */       hist.setOperateur(this.operateur);
/* 447 */       if (getId() == 0) {
/* 448 */         hist.setOperation("Cr�ation du grade " + getDesignation());
/*     */       } else {
/* 450 */         hist.setOperation("Modification du grade " + getDesignation());
/* 451 */       }  hist.setTable(Tables.getTableName(Tables.TableName.gradePersonnel));
/* 452 */       setHistorique(hist);
/* 453 */       if (FichierBaseDAO.getInstance().insertUpdateGradePersonnel(this)) {
/* 454 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/*     */         chargementGrade();
/* 456 */         clear(true);
/*     */       } else {
/*     */         
/* 459 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void supprimer() {
/* 464 */     if (getId() == 0) {
/* 465 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 467 */     else if (FichierBaseDAO.getInstance().deleteGradePersonnel(this)) {
/* 468 */       clear(true);
				chargementGrade();
/* 469 */       HelperC.afficherMessage("Information", "Succes de l'op�ration");
/*     */     }
/*     */     else {
/*     */       
/* 473 */       HelperC.afficherMessage("D�sol�", "Echec de suppression ");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 479 */     clear(true);
/*     */   }
/*     */ }

