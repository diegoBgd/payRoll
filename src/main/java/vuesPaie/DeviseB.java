 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DeviseC;
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
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class DeviseB
   extends DeviseC
 {
   private static final long serialVersionUID = 4055882882261088023L;
   private DeviseC devise;
   private List<DeviseC> devises;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   public DeviseC getDevise() {
     return this.devise;
   }
 
   
   public void setDevise(DeviseC devise) {
     this.devise = devise;
   }
   
   public List<DeviseC> getDevises() {
     return this.devises;
   }
   
   public void setDevises(List<DeviseC> devises) {
     this.devises = devises;
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
       
       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
       }
       charger();
     } 
   }
 
   
   private void charger() {
     this.devises = FichierBaseDAO.getInstance().getAllDevise();
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setDesignation("");
     setSymbole("");
   }
 
   
   private void setObject() {
     if (this.devise != null) {
       
       setId(this.devise.getId());
       setCode(this.devise.getCode());
       setDesignation(this.devise.getDesignation());
       setSymbole(this.devise.getSymbole());
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.devise = FichierBaseDAO.getInstance().getDevise(getCode());
       if (this.devise == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.devise = (DeviseC)event.getObject();
     if (this.devise != null)
     {
       setObject();
     }
   }
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Code et Désignation sont obligatoires");
     }
     else if (FichierBaseDAO.getInstance().getDeviseNotCurrent(getCode(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Ce code existe déjà  dans devise");
     }
     else if (FichierBaseDAO.getInstance().getDeviseByDesignation(getDesignation(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "cette designation est deja saisie");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création des devises " + getCode());
       } else {
         
         hist.setOperation("Modification des devises " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.devise));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateDevise(this)) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherMessage("Information", "Précisez la devise à  supprimer ");
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.devise), getId())) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succès de l'opération ");
       charger();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
  
 }


