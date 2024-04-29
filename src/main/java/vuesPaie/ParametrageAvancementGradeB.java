 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.CategoriePersonnelC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageAvancementGradeC;
 import classesPaie.Tables;
 import classesPaie.TypeNotationC;
 import java.io.IOException;
 import java.util.ArrayList;
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
 public class ParametrageAvancementGradeB
   extends ParametrageAvancementGradeC
 {
   private static final long serialVersionUID = -8088736129546101L;
   private int idType;
   private int idTypeAppreciation;
   private int idPersonnel;
   private int idCategorie;
   private int idAncienGrade;
   private int idGradeActuel;
   private ParametrageAvancementGradeC avancement;
   private CategoriePersonnelC categoriePersonnel;
   private Base personnel;
   private List<ParametrageAvancementGradeC> listParametreAvancement = new ArrayList<ParametrageAvancementGradeC>();
   private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>();
   private List<SelectItem> listTypeAppreciation = new ArrayList<SelectItem>();
   private List<SelectItem> listPersonnel = new ArrayList<SelectItem>();
   private List<SelectItem> categoriePersonnels = new ArrayList<SelectItem>();
   private boolean disableMsg;
   private HttpSession session = HelperC.getSession();
   private OperateurC operateur;
   private ExerciceC exercice;
   
   public int getIdType() {
     return this.idType;
   }
 
   
   public void setIdType(int idType) {
     this.idType = idType;
   }
 
   
   public ParametrageAvancementGradeC getAvancement() {
     return this.avancement;
   }
 
   
   public void setAvancement(ParametrageAvancementGradeC avancement) {
     this.avancement = avancement;
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
 
   
   public int getIdTypeAppreciation() {
     return this.idTypeAppreciation;
   }
 
   
   public void setIdTypeAppreciation(int idTypeAppreciation) {
     this.idTypeAppreciation = idTypeAppreciation;
   }
 
   
   public int getIdPersonnel() {
     return this.idPersonnel;
   }
 
   
   public void setIdPersonnel(int idPersonnel) {
     this.idPersonnel = idPersonnel;
   }
 
   
   public int getIdCategorie() {
     return this.idCategorie;
   }
 
   
   public void setIdCategorie(int idCategorie) {
     this.idCategorie = idCategorie;
   }
 
   
   public int getIdAncienGrade() {
     return this.idAncienGrade;
   }
 
   
   public void setIdAncienGrade(int idAncienGrade) {
     this.idAncienGrade = idAncienGrade;
   }
 
   
   public int getIdGradeActuel() {
     return this.idGradeActuel;
   }
 
   
   public void setIdGradeActuel(int idGradeActuel) {
     this.idGradeActuel = idGradeActuel;
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
   
   public List<ParametrageAvancementGradeC> getListParametreAvancement() {
     return this.listParametreAvancement;
   }
 
   
   public void setListParametreAvancement(List<ParametrageAvancementGradeC> listParametreAvancement) {
     this.listParametreAvancement = listParametreAvancement;
   }
   
   public List<SelectItem> getListTypeNotation() {
     return this.listTypeNotation;
   }
   
   public void setListTypeNotation(List<SelectItem> listTypeNotation) {
     this.listTypeNotation = listTypeNotation;
   }
   
   public List<SelectItem> getListTypeAppreciation() {
     return this.listTypeAppreciation;
   }
   
   public void setListTypeAppreciation(List<SelectItem> listTypeAppreciation) {
     this.listTypeAppreciation = listTypeAppreciation;
   }
   
   public List<SelectItem> getListPersonnel() {
     return this.listPersonnel;
   }
   
   public void setListPersonnel(List<SelectItem> listPersonnel) {
     this.listPersonnel = listPersonnel;
   }
   
   public List<SelectItem> getCategoriePersonnels() {
     return this.categoriePersonnels;
   }
   
   public void setCategoriePersonnels(List<SelectItem> categoriePersonnels) {
     this.categoriePersonnels = categoriePersonnels;
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
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       disableMsg=true;
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
 
 
   
   private void setObject() {
     if (this.avancement != null) {
        disableMsg=false;
       setId(this.avancement.getId());
       setNombreDeFoisNotation(this.avancement.getNombreDeFoisNotation());
       
       setTypeAppreciation(this.avancement.getTypeAppreciation());
       
       setPersonnel(this.avancement.getPersonnel());
       setTypeNotation(this.avancement.getTypeNotation());
       if (getTypeNotation() != null)
       {
         this.idType = getTypeNotation().getId();
       }
       if (getTypeAppreciation() != null)
       {
         this.idTypeAppreciation = getTypeAppreciation().getId();
       }
       
         setPersonnel(this.avancement.getPersonnel());
         if (getPersonnel() != null) {
           
           this.idPersonnel = getPersonnel().getId();
           this.categoriePersonnels.clear();
					chargerCategorie();
           
           setCategoriePersonnel(this.avancement.getCategorie());
           if (getCategoriePersonnel() != null) {
             
             this.idCategorie = getCategoriePersonnel().getId();
            
           } 
         } 
       } 
       
      
     
   }
 
   
   private void clear() {
     setId(0);
     setTypeNotation(null);
     setNombreDeFoisNotation(0);
     setTypeAppreciation(null);
     setPersonnel(null);
			  disableMsg=true;
     setPersonnel(null);
     setCategoriePersonnel(null);

     setPersonnel((Base)null);
     this.avancement = null;
     this.idPersonnel = 0;
     this.idCategorie = 0;
     this.idAncienGrade = 0;
     this.idGradeActuel = 0;
     this.idTypeAppreciation = 0;
     this.idType = 0;
     this.categoriePersonnels.clear();
     
   }
 
   
   public void onRowSelect(SelectEvent event) {
     this.avancement = (ParametrageAvancementGradeC)event.getObject();
     if (this.avancement != null) {
        disableMsg=true;
       setObject();
       PrimeFaces.current().executeScript("PF('dlg').hide();");
     } 
   }
 
 
   
   public void changeTypeNotation(ValueChangeEvent event) {
     this.idType = ((Integer)event.getNewValue()).intValue();
     if (this.idType != 0)
     {
       setTypeNotation(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.typeNotation)));
     }
   }
 
   
   public void changePersonnel(ValueChangeEvent event) {
     this.categoriePersonnels.clear();
     this.idPersonnel = ((Integer)event.getNewValue()).intValue();
     if (this.idPersonnel != 0) {
       
       setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.personnel)));
       if (getPersonnel() != null) {
         
         chargerCategorie();
       } 
     } 
   }
			
 
 
   
   public void changeCategorie(ValueChangeEvent event) {
     if (event != null) {
       
       this.idCategorie = ((Integer)event.getNewValue()).intValue();
       if (this.idCategorie != 0) {
         
         setCategoriePersonnel(FichierBaseDAO.getInstance().getCategoriePersonnel(this.idCategorie));
         if (getCategoriePersonnel() != null) {
           
          
         } 
       } 
     } 
   }
 
 
   


   
   public void changeTypeAppreciation(ValueChangeEvent event) {
     this.idTypeAppreciation = ((Integer)event.getNewValue()).intValue();
     if (this.idTypeAppreciation != 0)
     {
       setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(this.idTypeAppreciation, Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
     }
   }
 
   
   public void chargement() {
     listParametreAvancement = FichierBaseDAO.getInstance().getListeParametrageAvancement();
   }
 
   
   public void initialiser() {
     clear();
   }
 
   
   public void save() {
     if (getPersonnel() == null) {
       
       HelperC.afficherMessage("Information", "Veillez selectionner le personnel!");
     }
     else if (getTypeNotation() == null) {
       
       HelperC.afficherMessage("Information", "Veillez selectionner le type de notation!");
     } else {

	       	if (FichierBaseDAO.getInstance().insertUpdateParametrageAvancementGrade(this)) {
           
           HelperC.afficherMessage("Information", "Succès d'enregistrement");
           clear();
					chargement();
         } else {
           
           HelperC.afficherMessage("information", "Echec d'enregistrement");
         
       } 
     } 
   }
 
 
   
   public void supprimer() {
     try {
       if (getId() == 0) {
         
         HelperC.afficherDeleteMessage();
       }
       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.parametrageAvancementGrade))) {
         
         HelperC.afficherMessage("Félicitation", "Succès de l'Opération");
         clear();
				  chargement();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'Opération");
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 }

