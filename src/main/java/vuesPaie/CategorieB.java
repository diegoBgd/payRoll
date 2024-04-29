 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.io.Serializable;
 import java.util.Calendar;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.application.FacesMessage;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class CategorieB
   extends Base
   implements Serializable
 {
   private static final long serialVersionUID = 2150297787964447291L;
   private Base categorie;
   private List<Base> categories;
   private FacesMessage msg = null;
   private HttpSession session = HelperC.getSession();
   private OperateurC operateur;
   private ExerciceC exercice;
   
   public FacesMessage getMsg() {
     return this.msg;
   }
 
   
   public void setMsg(FacesMessage msg) {
     this.msg = msg;
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
 
   
   public Base getCategorie() {
     return this.categorie;
   }
 
   
   public void setCategorie(Base categorie) {
     this.categorie = categorie;
   }
 
   
   public List<Base> getCategories() {
     return this.categories;
   }
   
   public void setCategories(List<Base> categories) {
     this.categories = categories;
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
     }
     charger();
   }
 
   
   private void charger() {
     this.categories = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.categoriePersonnel));
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.categorie = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.categoriePersonnel));
       if (this.categorie == null) {
         
         clear(false);
       } else {
         
         affecter();
       } 
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     this.categorie = null;
     setDesignation("");
   }
 
   
   public void onRowSelectedCategorie(SelectEvent event) {
     this.categorie = (Base)event.getObject();
     if (this.categorie != null)
     {
       affecter();
     }
   }
 
   
   private void affecter() {
     if (this.categorie != null) {
       
       setId(this.categorie.getId());
       setCode(this.categorie.getCode());
       setDesignation(this.categorie.getDesignation());
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
   public void enregistrer() {
     try {
       if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
         
         this.msg = new FacesMessage("Information", "Référence et libellé obligatoires");
         FacesContext.getCurrentInstance().addMessage(null, this.msg);
       } else {
         
         this.categorie = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.categoriePersonnel));
         if (this.categorie == null) {
           
           Historique hist = new Historique();
           hist.setDateOperation(Calendar.getInstance().getTime());
           hist.setOperateur(this.operateur);
           if (getId() == 0) {
             
             hist.setOperation("Création de la catégorie " + getCode());
           } else {
             
             hist.setOperation("Modification de la catégorie " + getCode());
           } 
           hist.setTable(Tables.getTableName(Tables.TableName.categoriePersonnel));
           setHistorique(hist);
           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.categoriePersonnel))) {
             
             clear(true);
             this.msg = new FacesMessage("Félicitaions", "Succé de l'Opération");
             FacesContext.getCurrentInstance().addMessage(null, this.msg);
             charger();
           } else {
             
             this.msg = new FacesMessage("Désolé", "Echec de l'Opération");
             FacesContext.getCurrentInstance().addMessage(null, this.msg);
           } 
         } else {
           
           this.msg = new FacesMessage("Information", "Cette catégorie vient d'étre créée par un autre opérateur");
           FacesContext.getCurrentInstance().addMessage(null, this.msg);
         }
       
       } 
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void supprimer() {
     try {
       if (getId() == 0) {
         
        
        HelperC.afficherDeleteMessage();
       }
       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.categoriePersonnel))) {
         
         clear(true);
         this.msg = new FacesMessage("Félicitation", "Succès de l'Opération");
         FacesContext.getCurrentInstance().addMessage(null, this.msg);
         charger();
       } else {
         
         this.msg = new FacesMessage("Désolé", "Echec de l'Opération");
         FacesContext.getCurrentInstance().addMessage(null, this.msg);
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
  
 }


