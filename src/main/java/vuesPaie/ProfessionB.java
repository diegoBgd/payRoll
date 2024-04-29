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
 public class ProfessionB
   extends Base
 {
   private static final long serialVersionUID = -5289226743168992229L;
   private Base profession;
   private List<Base> professions;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private DroitC droit;
   Base userFonction;
   
   public Base getProfession() {
     return this.profession;
   }
 
   
   public void setProfession(Base profession) {
     this.profession = profession;
   }
   
   public List<Base> getProfessions() {
     return this.professions;
   }
   
   public void setProfessions(List<Base> professions) {
     this.professions = professions;
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
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
       }
       charger();
     } 
   }
 
   
   private void charger() {
     this.professions = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.profession));
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     this.profession = null;
     setDesignation("");
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.profession = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.profession));
     } 
     if (this.profession == null) {
       
       clear(true);
     } else {
       
       setObject();
     } 
   }
 
   
   private void setObject() {
     if (this.profession != null) {
       
       setId(this.profession.getId());
       setCode(this.profession.getCode());
       setDesignation(this.profession.getDesignation());
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.profession = (Base)event.getObject();
     if (this.profession != null)
     {
       setObject();
     }
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
   
   public void enregistrer() {
     if (getCode().trim().equals("") || getDesignation().trim().equals(""))
     {
       HelperC.afficherMessage("Information", "Code et Désignation obligatoires");
     }
     
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
       return;
     } 
     if (getId() != 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
       
       return;
     } 
     try {
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création de la profession " + getCode());
       } else {
         
         hist.setOperation("Modification de la profession " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.profession));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.profession))) {
         
         clear(true);
         HelperC.afficherMessage("Félicitaions", "Succès de l'Opération");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'Opération");
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void supprimer() {
     try {
       if (getId() == 0) {
         
         HelperC.afficherMessage("Information", "Précisez la profession é supprimer");
       }
       else if (this.droit.isSupprimer()) {
         
         if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.profession))) {
           
           clear(true);
           HelperC.afficherMessage("Félicitaions", "Succès de l'Opération");
           charger();
         } else {
           
           HelperC.afficherMessage("Désolé", "Echec de l'Opération");
         } 
       } else {
         
         HelperC.afficherMessage("Désolé", "Vous n'avez pas le droit de supprimer");
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 }


