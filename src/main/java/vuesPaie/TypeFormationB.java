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
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class TypeFormationB
   extends Base
 {
   private static final long serialVersionUID = 7415527835658069410L;
   private Base typeFormation;
   private List<Base> typeFormations;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   public Base getTypeFormation() {
     return this.typeFormation;
   }
 
   
   public void setTypeFormation(Base typeFormation) {
     this.typeFormation = typeFormation;
   }
   
   public List<Base> getTypeFormations() {
     return this.typeFormations;
   }
   
   public void setTypeFormations(List<Base> typeFormations) {
     this.typeFormations = typeFormations;
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
     String codeExercice = (String)this.session.getAttribute("exercice");
     String codeOperateur = (String)this.session.getAttribute("operateur");
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     if (this.exercice == null || this.operateur == null) {
 
       
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
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
       }
       charger();
     } 
   }
 
   
   private void charger() {
     this.typeFormations = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeFormation));
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setDesignation("");
   }
 
   
   private void setObject() {
     if (this.typeFormation != null) {
       
       setId(this.typeFormation.getId());
       setCode(this.typeFormation.getCode());
       setDesignation(this.typeFormation.getDesignation());
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.typeFormation = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeFormation));
       if (this.typeFormation == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.typeFormation = (Base)event.getObject();
     if (this.typeFormation != null)
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
     else if (getCode().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Code et Désignation sont obligatoires");
     }
     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeFormation)) != null) {
       
       HelperC.afficherMessage("Information", "Ce type de formation existe déjé ");
     }
     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.typeFormation)) != null) {
       
       HelperC.afficherMessage("Information", "Cette désignation existe déjé ");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du type formation " + getCode());
       } else {
         
         hist.setOperation("Modification de l'exercice " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeFormation));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeFormation))) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherMessage("Information", "Précisez la formation é supprimer");
     }
     else if (getId() > 0 && !this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeFormation), getId())) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succes de l'opération ");
       charger();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de l'opération ");
     } 
   }
 
 
   

 }


