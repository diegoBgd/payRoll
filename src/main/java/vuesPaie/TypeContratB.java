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
 public class TypeContratB
   extends Base
 {
   private static final long serialVersionUID = -3891763416929479090L;
   private Base typeContrat;
   private List<Base> typeContrats;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   public Base getTypeContrat() {
     return this.typeContrat;
   }
 
   
   public void setTypeContrat(Base typeContrat) {
     this.typeContrat = typeContrat;
   }
 
   
   public List<Base> getTypeContrats() {
     return this.typeContrats;
   }
   
   public void setTypeContrats(List<Base> typeContrats) {
     this.typeContrats = typeContrats;
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
     this.typeContrats = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeContrat));
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     this.typeContrat = null;
     setDesignation("");
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.typeContrat = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeContrat));
     } 
     if (this.typeContrat == null) {
       
       clear(true);
     } else {
       
       setObject();
     } 
   }
 
   
   private void setObject() {
     if (this.typeContrat != null) {
       
       setId(this.typeContrat.getId());
       setCode(this.typeContrat.getCode());
       setDesignation(this.typeContrat.getDesignation());
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.typeContrat = (Base)event.getObject();
     if (this.typeContrat != null)
     {
       setObject();
     }
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() == 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Code et Désignation obligatoires");
     }
     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeContrat)) != null) {
       
       HelperC.afficherMessage("Information", "Ce code est déjé utilisé");
     }
     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.typeContrat)) != null) {
       
       HelperC.afficherMessage("information", "Cette désignation est déjé utilisée");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du type contrat " + getCode());
       } else {
         
         hist.setOperation("Modification du type contrat " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeContrat));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeContrat))) {
         
         clear(true);
         HelperC.afficherMessage("Félicitaions", "Succès de l'Opération");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'Opération");
       } 
     } 
   }
 
 
   
   public void supprimer() {
     try {
       if (getId() == 0) {
         
         HelperC.afficherDeleteMessage();
       }
       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.typeContrat))) {
         
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
 
 
   
   
 }

