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
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class TypeSanctionB
   extends Base
 {
   private static final long serialVersionUID = 5176764009000635033L;
   private Base typeSanction;
   private List<Base> typeSanctions;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
 
 
   
   public Base getTypeSanction() {
     return this.typeSanction;
   }
 
   
   public void setTypeSanction(Base typeSanction) {
     this.typeSanction = typeSanction;
   }
 
   
   public List<Base> getTypeSanctions() {
     return this.typeSanctions;
   }
   
   public void setTypeSanctions(List<Base> typeSanctions) {
     this.typeSanctions = typeSanctions;
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
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       charger();
     } 
   }
 
   
   private void charger() {
     this.typeSanctions = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeSanction));
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
     if (this.typeSanction != null) {
       
       setId(this.typeSanction.getId());
       setCode(this.typeSanction.getCode());
       setDesignation(this.typeSanction.getDesignation());
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.typeSanction = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeSanction));
       if (this.typeSanction == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.typeSanction = (Base)event.getObject();
     if (this.typeSanction != null)
     {
       setObject();
     }
   }
 
   
   public void enregistrer() {
     if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Code et Désignation sont obligatoires");
     }
     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeSanction)) != null) {
       
       HelperC.afficherMessage("Information", "Ce code existe déjé ");
     }
     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.typeSanction)) != null) {
       
       HelperC.afficherMessage("Information", "Cette désignation existe déjé ");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du type sanction " + getCode());
       } else {
         
         hist.setOperation("Modification du type sanction " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeSanction));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeSanction))) {
         
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
       
       HelperC.afficherMessage("Information", "Précisez le type de sanction é supprimer");
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeSanction), getId())) {
       
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


