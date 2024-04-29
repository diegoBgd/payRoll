 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
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
 public class PersonnelB
   extends Base
 {
   private static final long serialVersionUID = 5731144679340826201L;
   private Base personnel;
   private List<Base> personnels;
   private OperateurC operateur;
   private FacesMessage msg = null;
   private HttpSession session = HelperC.getSession();
   
   private ExerciceC exercice;
   
   public FacesMessage getMsg() {
     return this.msg;
   }
   private DroitC droit; Base userFonction;
   
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
 
   
   public Base getPersonnel() {
     return this.personnel;
   }
 
   
   public void setPersonnel(Base personnel) {
     this.personnel = personnel;
   }
   
   public List<Base> getPersonnels() {
     return this.personnels;
   }
   public void setPersonnels(List<Base> personnels) {
     this.personnels = personnels;
   }
 
   
   @PostConstruct
   private void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     if (this.operateur == null || this.exercice == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge);
       }
       charger();
     } 
   }
 
   
   private void charger() {
     this.personnels = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel));
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.personnel = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.personnel));
       if (this.personnel == null) {
         
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
     this.personnel = null;
     setDesignation("");
   }
 
   
   public void onRowSelectedPersonnel(SelectEvent event) {
     this.personnel = (Base)event.getObject();
     if (this.personnel != null)
     {
       affecter();
     }
   }
 
   
   private void affecter() {
     if (this.personnel != null) {
       
       setId(this.personnel.getId());
       setCode(this.personnel.getCode());
       setDesignation(this.personnel.getDesignation());
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
   public void enregistrer() {
     try {
       if (getId() == 0 && !this.droit.isCreer()) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
       }
       else if (getId() > 0 && !this.droit.isModifier()) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
       }
       else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
         
         this.msg = new FacesMessage("Information", "Code et Désignation obligatoires");
         FacesContext.getCurrentInstance().addMessage(null, this.msg);
       } else {
         
         this.personnel = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.personnel));
         if (this.personnel == null) {
           
           Historique hist = new Historique();
           hist.setDateOperation(Calendar.getInstance().getTime());
           hist.setOperateur(this.operateur);
           if (getId() == 0) {
             
             hist.setOperation("Création du personnel " + getCode());
           } else {
             
             hist.setOperation("Modification du personnel " + getCode());
           } 
           hist.setTable(Tables.getTableName(Tables.TableName.personnel));
           setHistorique(hist);
           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.personnel))) {
             
             clear(true);
             this.msg = new FacesMessage("Félicitaions", "Succès de l'Opération");
             FacesContext.getCurrentInstance().addMessage(null, this.msg);
             charger();
           } else {
             
             this.msg = new FacesMessage("Désolé", "Echec de l'Opération");
             FacesContext.getCurrentInstance().addMessage(null, this.msg);
           } 
         } else {
           
           this.msg = new FacesMessage("Information", "Ce personnel vient d'étre créee par un autre opérateur");
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
         
         this.msg = new FacesMessage("Information", "Précisez le departement é supprimer");
         FacesContext.getCurrentInstance().addMessage(null, this.msg);
       }
       else if (getId() > 0 && !this.droit.isSupprimer()) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
       }
       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.personnel))) {
         
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


