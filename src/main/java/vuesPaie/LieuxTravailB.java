 package vuesPaie;
 
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.LieuxTravailC;
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
 public class LieuxTravailB
   extends LieuxTravailC
 {
   private static final long serialVersionUID = 300722569362046868L;
   private LieuxTravailC lieuTravail;
   private List<LieuxTravailC> lieuTravails;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
 
 
   
   public LieuxTravailC getLieuTravail() {
     return this.lieuTravail;
   }
 
   
   public void setLieuTravail(LieuxTravailC lieuTravail) {
     this.lieuTravail = lieuTravail;
   }
 
   
   public List<LieuxTravailC> getLieuTravails() {
     return this.lieuTravails;
   }
   
   public void setLieuTravails(List<LieuxTravailC> lieuTravails) {
     this.lieuTravails = lieuTravails;
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
     }
     charger();
   }
 
   
   private void charger() {
     this.lieuTravails = FichierBaseDAO.getInstance().getAllLieuxTravail();
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setDesignation("");
     setAvenue("");
   }
 
   
   private void setObject() {
     if (this.lieuTravail != null) {
       
       setId(this.lieuTravail.getId());
       setCode(this.lieuTravail.getCode());
       setDesignation(this.lieuTravail.getDesignation());
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(false);
     } else {
       
       this.lieuTravail = FichierBaseDAO.getInstance().getLieuxTravail(getCode());
       if (this.lieuTravail == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.lieuTravail = (LieuxTravailC)event.getObject();
     if (this.lieuTravail != null)
     {
       setObject();
     }
   }
 
   
   public void enregistrer() {
     if (getCode().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Précisez le code");
     }
     else if (getDesignation().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Précisez la désignation");
     }
     else if (FichierBaseDAO.getInstance().getLieuxTravail(getCode(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Ce code est déjà  utilisé");
     }
     else if (FichierBaseDAO.getInstance().getLieuxTravailByDesignation(getDesignation(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Cette désignation est déjà  utilisé");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du lieu de travail " + getCode());
       } else {
         
         hist.setOperation("Modification du lieu de travail " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.lieuxTravail));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateLieuxTravail(this)) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération");
         this.lieuTravails = FichierBaseDAO.getInstance().getAllLieuxTravail();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.lieuxTravail), getId())) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succès de la suppression");
       this.lieuTravails = FichierBaseDAO.getInstance().getAllLieuxTravail();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   

 }


