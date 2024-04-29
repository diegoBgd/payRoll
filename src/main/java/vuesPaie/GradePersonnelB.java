 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.CategoriePersonnelC;
 import classesPaie.ExerciceC;
 import classesPaie.GradePersonnelC;
 import classesPaie.GradePersonnelDetailC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;
 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 
 
 @ManagedBean
 @ViewScoped
 public class GradePersonnelB
   extends GradePersonnelC
 {
   private static final long serialVersionUID = 5655626376938669692L;
   private int idCategorie;
   private int idNiveau;
   private int idPersonnel;
   private int ageFormation;
			private int idGrdInf;
   private int index = 1;
   private GradePersonnelC grade;
   private GradePersonnelDetailC detailNiveauF;
   private Base niveauFormation;
   private Base personnel;
   private List<GradePersonnelC> grades;
   private List<SelectItem> categoriePersonnels = new ArrayList<SelectItem>();
   private List<SelectItem> listPersonnels = new ArrayList<SelectItem>();
  private List<SelectItem> listGrdInf;
   private List<SelectItem> niveaux = new ArrayList<SelectItem>();
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
 
   private List<EmployeB> listRetraite;
   private double bonus;
   private boolean disableMsg;
   private String bonusS;
 
   
   public int getIdCategorie() {
     return this.idCategorie;
   }
   
   public void setIdCategorie(int idCategorie) {
     this.idCategorie = idCategorie;
   }
   
   public int getIdNiveau() {
     return this.idNiveau;
   }
   
   public void setIdNiveau(int idNiveau) {
     this.idNiveau = idNiveau;
   }
   
   public int getIndex() {
     return this.index;
   }
   
   public void setIndex(int index) {
     this.index = index;
   }
   
   public GradePersonnelC getGrade() {
     return this.grade;
   }
   
   public void setGrade(GradePersonnelC grade) {
     this.grade = grade;
   }
   
   public GradePersonnelDetailC getDetailNiveauF() {
     return this.detailNiveauF;
   }
   
   public void setDetailNiveauF(GradePersonnelDetailC detailNiveauF) {
     this.detailNiveauF = detailNiveauF;
   }
   
   public List<GradePersonnelC> getGrades() {
     return this.grades;
   }
   
   public void setGrades(List<GradePersonnelC> grades) {
     this.grades = grades;
   }
   
   public List<SelectItem> getCategoriePersonnels() {
     return this.categoriePersonnels;
   }
   
   public void setCategoriePersonnels(List<SelectItem> categoriePersonnels) {
     this.categoriePersonnels = categoriePersonnels;
   }
   
   public List<SelectItem> getNiveaux() {
     return this.niveaux;
   }
   
   public void setNiveaux(List<SelectItem> niveaux) {
     this.niveaux = niveaux;
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
   
   public int getIdPersonnel() {
     return this.idPersonnel;
   }
   
   public void setIdPersonnel(int idPersonnel) {
     this.idPersonnel = idPersonnel;
   }
   
   public Base getPersonnel() {
     return this.personnel;
   }
   
   public void setPersonnel(Base personnel) {
     this.personnel = personnel;
   }
   
   public List<SelectItem> getListPersonnels() {
     return this.listPersonnels;
   }
   
   public void setListPersonnels(List<SelectItem> listPersonnels) {
     this.listPersonnels = listPersonnels;
   }
   
   public double getBonus() {
     return this.bonus;
   }
   
   public void setBonus(double bonus) {
     this.bonus = bonus;
   }
   
   public String getBonusS() {
     return this.bonusS;
   }
   
   public void setBonusS(String bonusS) {
     this.bonusS = bonusS;
   }
   
   public int getAgeFormation() {
     return this.ageFormation;
   }
   
   public void setAgeFormation(int ageFormation) {
     this.ageFormation = ageFormation;
   }


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
   @PostConstruct
   private void init() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     disableMsg=true;
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
			  listGrdInf=new ArrayList<SelectItem>();
			
     this.listPersonnels.add(new SelectItem(Integer.valueOf(0), ""));
     for (Base person : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel))) {
       this.listPersonnels.add(new SelectItem(Integer.valueOf(person.getId()), person.getDesignation()));
     }
     
     this.niveaux.add(new SelectItem(Integer.valueOf(0), ""));
     for (Base niveau : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.niveauFormation))) {
				this.niveaux.add(new SelectItem(Integer.valueOf(niveau.getId()), niveau.getDesignation()));
     }
				chargementGrade();
				chargerGradeInf();
   }
			
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

   private void chargementGrade() {
     this.grades = FichierBaseDAO.getInstance().getListGradeParCategoriePersonnel(this.idCategorie);
   }
   
   private void clear(boolean b) {
     if (b)
       setId(0); 
              setCode("");
     setDesignation("");
     setTraitementMensuel(0.0D);
     setTraitementMensuelS("");
     this.idCategorie = 0;
			  disableMsg=true;
     this.idNiveau = 0;
     this.idPersonnel = 0;
     this.ageFormation = 0;
     this.bonus = 0.0D;
     this.bonusS = "";
     setPersonnel(null);
     getListNiveau().clear();
     this.grade = null;
			  idGrdInf=0;
			  
     getCategoriePersonnels().clear();

   }
 
 
   
   public void changePersonnel(ValueChangeEvent event) {
     this.categoriePersonnels.clear();

     this.categoriePersonnels.add(new SelectItem(Integer.valueOf(0), ""));
     this.idCategorie = 0;
    

     this.idPersonnel = ((Integer)event.getNewValue()).intValue();
     
     if (this.idPersonnel != 0) {
       setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.personnel)));
       
       if (getPersonnel() != null) {
         for (CategoriePersonnelC categ : FichierBaseDAO.getInstance().getListeCategoriePersonnel(getPersonnel())) {
           this.categoriePersonnels.add(new SelectItem(Integer.valueOf(categ.getId()), categ.getDesignation()));
         }
       }
     } 
   }
 
   
   public void changeCategorie(ValueChangeEvent event) {
     this.idCategorie = ((Integer)event.getNewValue()).intValue();
     if (this.idCategorie != 0) {
       setCategoriePersonnel(FichierBaseDAO.getInstance().getCategoriePersonnel(this.idCategorie));
       chargementGrade();
     } 
   }
 
 
   
   private void setObject() {
				disableMsg=true;
     if (this.grade != null) {
       
       setId(this.grade.getId());
       disableMsg=false;
       if (this.grade.getCategoriePersonnel().getPersonnel() != null) {
         
         setPersonnel(this.grade.getCategoriePersonnel().getPersonnel());
         if (getPersonnel() != null) {
           this.idPersonnel = getPersonnel().getId();
           this.categoriePersonnels.clear();
           for (CategoriePersonnelC categ : FichierBaseDAO.getInstance().getListeCategoriePersonnel(getPersonnel())) {
             this.categoriePersonnels.add(new SelectItem(Integer.valueOf(categ.getId()), categ.getDesignation()));
           }
           idGrdInf=grade.getIdGradeInferieur();
           setCategoriePersonnel(this.grade.getCategoriePersonnel());
           if (getCategoriePersonnel() != null) {
             this.idCategorie = getCategoriePersonnel().getId();
           }
         } 
                  setCode(grade.getCode());
         setDesignation(this.grade.getDesignation());
         setTraitementMensuel(this.grade.getTraitementMensuel());
       } 
     } 
   }
 
 
   
   public void onRowSelected(SelectEvent event) {
     this.grade = (GradePersonnelC)event.getObject();
     if (this.grade != null) {
       setObject();
       setListNiveau(FichierBaseDAO.getInstance().getListeDetailNiveauFormation(this.grade));
       PrimeFaces.current().executeScript("PF('dlg').hide();");
     } 
   }
 
   
   public void chargerNiveau() {
     if (this.niveauFormation == null) {
       HelperC.afficherMessage("Information", "veillez séléctionner le niveau de formation");
     } else if (this.bonus > 100.0D) {
       HelperC.afficherMessage("Information", "Le bonus ne peut pas etre supérieur é 100");
     } else {
       
       for (GradePersonnelDetailC det : getListNiveau()) {
         if (this.detailNiveauF != null && 
           det.getNiveau() == this.detailNiveauF.getNiveau()) {
           this.detailNiveauF = det;
           this.detailNiveauF.setExiste(true);
           
           break;
         } 
       } 
       
       if (this.detailNiveauF == null) {
         this.detailNiveauF = new GradePersonnelDetailC();
       }
       this.detailNiveauF.setNiveau(this.niveauFormation);
       this.detailNiveauF.setTauxBonusSalaire(getBonus());
       this.detailNiveauF.setTauxBonusSalaireS(getBonusS());
       this.detailNiveauF.setAge(getAgeFormation());
       
       if (!this.detailNiveauF.isExiste())
         getListNiveau().add(this.detailNiveauF); 
       clearDetailNiveau();
       this.detailNiveauF = null;
     } 
   }
 
   
   private void clearDetailNiveau() {
     this.idNiveau = 0;
     this.detailNiveauF = null;
     this.bonus = 0.0D;
     this.bonusS = "";
     this.niveauFormation = null;
     this.ageFormation = 0;
   }
 
 
 
   
   public void enleverNiveau() {
     if (this.detailNiveauF == null) {
       HelperC.afficherMessage("Information", "Précisez l'élément é supprimer");
     
     }
     else if (this.detailNiveauF.getNiveau() != null) {
       
       getListNiveauDeleted().add(this.detailNiveauF);
       getListNiveau().remove(this.detailNiveauF);
     } 
     
     clearDetailNiveau();
     this.detailNiveauF = null;
   }
 
 
   
   private void affecterDetailNiveau() {
     if (this.detailNiveauF != null) {
       
       this.idNiveau = this.detailNiveauF.getNiveau().getId();
       this.niveauFormation = this.detailNiveauF.getNiveau();
       this.bonus = this.detailNiveauF.getTauxBonusSalaire();
       this.bonusS = this.detailNiveauF.getTauxBonusSalaireS();
       this.ageFormation = this.detailNiveauF.getAge();
     } 
   }
 
   
   public void onRowSelectedDetailNiveau(SelectEvent event) {
     this.detailNiveauF = (GradePersonnelDetailC)event.getObject();
     if (this.detailNiveauF != null) {
       affecterDetailNiveau();
       PrimeFaces.current().executeScript("PF('dlg').hide();");
     } 
   }
 
 
   
   public void changeNiveau(ValueChangeEvent event) {
     this.idNiveau = ((Integer)event.getNewValue()).intValue();
     this.niveauFormation = FichierBaseDAO.getInstance().getBaseById(this.idNiveau, Tables.getTableName(Tables.TableName.niveauFormation));
   }
 
   
   public void changeTraitementMensuel() {
     try {
       setTraitementMensuel(Double.valueOf(getTraitementMensuelS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
     }
     catch (Exception e) {
       setTraitementMensuel(0.0D);
     } finally {
       
       setTraitementMensuelS(HelperC.TraitementMontant.getMontantFormate(getTraitementMensuel(), 2));
       setTraitementMensuel(Double.valueOf(getTraitementMensuelS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
     } 
   }
 
   
   public void changeBonus() {
     try {
       setBonus(Double.valueOf(getBonusS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
     }
     catch (Exception e) {
       setBonus(0.0D);
     } finally {
       
       setBonusS(HelperC.TraitementMontant.getMontantFormate(getBonus(), 2));
       setBonus(Double.valueOf(getBonusS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
     } 
   }
 
   
   public void enregistrer() {
     if (getCategoriePersonnel() == null) {
       HelperC.afficherMessage("Information", "Précisez la catégorie");
     } else if (getDesignation().trim().equals("")) {
       HelperC.afficherMessage("Information", " Précisez la désignation");
     }  else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création du grade " + getDesignation());
       } else {
         hist.setOperation("Modification du grade " + getDesignation());
       }  hist.setTable(Tables.getTableName(Tables.TableName.gradePersonnel));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateGradePersonnel(this)) {
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         chargementGrade();
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
   public void supprimer() {
     if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     }
     else if (FichierBaseDAO.getInstance().deleteGradePersonnel(this)) {
       clear(true);
				chargementGrade();
       HelperC.afficherMessage("Information", "Succes de l'opération");
     }
     else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression ");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 }

