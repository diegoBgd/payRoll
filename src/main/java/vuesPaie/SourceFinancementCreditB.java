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
 public class SourceFinancementCreditB
   extends Base
 {
   private static final long serialVersionUID = -6016896800008532256L;
   private Base sourceFinancementCredit;
   private List<Base> sourceFinancementCredits;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   public Base getSourceFinancementCredit() {
     return this.sourceFinancementCredit;
   }
 
   
   public void setSourceFinancementCredit(Base sourceFinancementCredit) {
     this.sourceFinancementCredit = sourceFinancementCredit;
   }
 
   
   public List<Base> getSourceFinancementCredits() {
     return this.sourceFinancementCredits;
   }
   
   public void setSourceFinancementCredits(List<Base> sourceFinancementCredits) {
     this.sourceFinancementCredits = sourceFinancementCredits;
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
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
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
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.creditAvance);
       }
       charger();
     } 
   }
 
   
   private void charger() {
     this.sourceFinancementCredits = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.sourceFinancementCredit));
   }
 
   
   private void setObject() {
     if (this.sourceFinancementCredit != null) {
       
       setId(this.sourceFinancementCredit.getId());
       setCode(this.sourceFinancementCredit.getCode());
       setDesignation(this.sourceFinancementCredit.getDesignation());
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setDesignation("");
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.sourceFinancementCredit = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.sourceFinancementCredit));
       if (this.sourceFinancementCredit == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.sourceFinancementCredit = (Base)event.getObject();
     if (this.sourceFinancementCredit != null)
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
       
       HelperC.afficherMessage("Information", "Completez tous les champs");
     }
     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.sourceFinancementCredit)) != null) {
       
       HelperC.afficherMessage("Information", "Ce code de source de financement existe déjé ");
     }
     else if (FichierBaseDAO.getInstance().getBases(getDesignation().replace("'", "\\'").trim(), getId(), Tables.getTableName(Tables.TableName.sourceFinancementCredit)) != null) {
       
       HelperC.afficherMessage("Information", "Cette désignation de source de financement est déjé saisi");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du source de financement " + getCode());
       } else {
         
         hist.setOperation("Modification du source de financement " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.sourceFinancementCredit));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.sourceFinancementCredit))) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.sourceFinancementCredit), getId())) {
       
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


