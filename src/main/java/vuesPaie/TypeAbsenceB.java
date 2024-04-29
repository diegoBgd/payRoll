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
 public class TypeAbsenceB
   extends Base
 {
   private static final long serialVersionUID = -1453927867592142501L;
   private Base typeAbsence;
   private List<Base> typeAbsences;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   public Base getTypeAbsence() {
     return this.typeAbsence;
   }
 
   
   public void setTypeAbsence(Base typeAbsence) {
     this.typeAbsence = typeAbsence;
   }
   
   public List<Base> getTypeAbsences() {
     return this.typeAbsences;
   }
   
   public void setTypeAbsences(List<Base> typeAbsences) {
     this.typeAbsences = typeAbsences;
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
     if (codeOperateur == null || codeExercice == null) {
 
       
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
     this.typeAbsences = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAbsence));
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
     if (this.typeAbsence != null) {
       
       setId(this.typeAbsence.getId());
       setCode(this.typeAbsence.getCode());
       setDesignation(this.typeAbsence.getDesignation());
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.typeAbsence = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeAbsence));
       if (this.typeAbsence == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.typeAbsence = (Base)event.getObject();
     if (this.typeAbsence != null)
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
     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeAbsence)) != null) {
       
       HelperC.afficherMessage("Information", "Ce code existe déjé ");
     }
     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.typeAbsence)) != null) {
       
       HelperC.afficherMessage("Information", "Cette désignation existe déjé ");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du type d'absence " + getCode());
       } else {
         
         hist.setOperation("Modification du type d'absence " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeAbsence));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeAbsence))) {
         
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
       
       HelperC.afficherMessage("Information", "Précisez le type d'absence é supprimer");
     }
     else if (getId() > 0 && !this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeAbsence), getId())) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succes de l'opération ");
       charger();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de l'opération ");
     } 
   }
 
 
   
  
 }

