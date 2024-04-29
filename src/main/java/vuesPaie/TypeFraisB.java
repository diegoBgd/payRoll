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
 public class TypeFraisB
   extends Base
 {
   private static final long serialVersionUID = -365714400725990680L;
   private Base typeFrais;
   private List<Base> listTypeFrais;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   public Base getTypeFrais() {
     return this.typeFrais;
   }
 
   
   public void setTypeFrais(Base typeFrais) {
     this.typeFrais = typeFrais;
   }
   
   public List<Base> getListTypeFrais() {
     return this.listTypeFrais;
   }
   
   public void setListTypeFrais(List<Base> listTypeFrais) {
     this.listTypeFrais = listTypeFrais;
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
     this.listTypeFrais = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeFrais));
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
     if (this.typeFrais != null) {
       
       setId(this.typeFrais.getId());
       setCode(this.typeFrais.getCode());
       setDesignation(this.typeFrais.getDesignation());
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.typeFrais = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeFrais));
       if (this.typeFrais == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelect(SelectEvent event) {
     this.typeFrais = (Base)event.getObject();
     if (this.typeFrais != null)
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
     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeFrais)) != null) {
       
       HelperC.afficherMessage("Information", "Ce code du type de frais existe déja");
     }
     else if (FichierBaseDAO.getInstance().getBases(getDesignation().replace("'", "\\'").trim(), getId(), Tables.getTableName(Tables.TableName.typeFrais)) != null) {
       
       HelperC.afficherMessage("Information", "ce type de frais est deja saisi");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du type de frais " + getCode());
       } else {
         
         hist.setOperation("Modification du type de frais " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeFrais));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeFrais))) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherMessage("Information", "Précisez le type de frais é supprimer");
     }
     else if (getId() > 0 && !this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeFrais), getId())) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succès de l'opération");
       charger();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   

 }

