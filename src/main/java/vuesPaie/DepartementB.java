 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
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
 public class DepartementB
   extends Base
 {
   private static final long serialVersionUID = 5726012767319035278L;
   private Base departement;
   private List<Base> departements;
   private FacesMessage msg = null;
   private HttpSession session = HelperC.getSession();
   private OperateurC operateur;
   private ExerciceC exercice;
   
   public Base getDepartement() {
     return this.departement;
   }
 
   
   public void setDepartement(Base departement) {
     this.departement = departement;
   }
   
   public List<Base> getDepartements() {
     return this.departements;
   }
   
   public void setDepartements(List<Base> departements) {
     this.departements = departements;
   }
 
   
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
 
   
   @PostConstruct
   private void init() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     this.operateur = (OperateurC)this.session.getAttribute("operateur");
     this.exercice = (ExerciceC)this.session.getAttribute("exercice");
     if (this.operateur == null || this.exercice == null) {
       
       try {
         
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/identification.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     }
     charger();
   }
 
   
   private void charger() {
     this.departements = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.departement));
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.departement = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.departement));
       if (this.departement == null) {
         
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
     this.departement = null;
     setDesignation("");
   }
 
   
   public void onRowSelectedDepartement(SelectEvent event) {
     this.departement = (Base)event.getObject();
     if (this.departement != null)
     {
       affecter();
     }
   }
 
   
   private void affecter() {
     if (this.departement != null) {
       
       setId(this.departement.getId());
       setCode(this.departement.getCode());
       setDesignation(this.departement.getDesignation());
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
   public void enregistrer() {
     try {
       if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
         
         this.msg = new FacesMessage("Information", "Code et Désignation obligatoires");
         FacesContext.getCurrentInstance().addMessage(null, this.msg);
       } else {
         
         this.departement = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.departement));
         if (this.departement == null) {
           
           Historique hist = new Historique();
           hist.setDateOperation(Calendar.getInstance().getTime());
           hist.setOperateur(this.operateur);
           if (getId() == 0) {
             
             hist.setOperation("Création du département " + getCode());
           } else {
             
             hist.setOperation("Modification du département " + getCode());
           } 
           hist.setTable(Tables.getTableName(Tables.TableName.departement));
           setHistorique(hist);
           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.departement))) {
             
             clear(true);
             this.msg = new FacesMessage("Félicitaions", "Succès de l'Opération");
             FacesContext.getCurrentInstance().addMessage(null, this.msg);
             charger();
           } else {
             
             this.msg = new FacesMessage("Désolé", "Echec de l'Opération");
             FacesContext.getCurrentInstance().addMessage(null, this.msg);
           } 
         } else {
           
           this.msg = new FacesMessage("Information", "Ce departement vient d'être créee par un autre opérateur");
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
         
         this.msg = new FacesMessage("Information", "Précisez le département à  supprimer");
         FacesContext.getCurrentInstance().addMessage(null, this.msg);
       }
       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.departement))) {
         
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


