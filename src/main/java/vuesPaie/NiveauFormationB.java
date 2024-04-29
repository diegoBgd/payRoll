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
 public class NiveauFormationB
   extends Base
 {
   private static final long serialVersionUID = -1864592058311941323L;
   private Base selected;
   private List<Base> niveauxFormations;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
 	private boolean disableMsg;
 
   
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
 
   
   public Base getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(Base selected) {
     this.selected = selected;
   }
   
   public List<Base> getNiveauxFormations() {
     return this.niveauxFormations;
   }
   
   public void setNiveauxFormations(List<Base> niveauxFormations) {
     this.niveauxFormations = niveauxFormations;
   }
			
			
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
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
       disableMsg=true;
       charger();
     } 
   }
 
   
   private void charger() {
     this.niveauxFormations = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.niveauFormation));
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
				disableMsg=true;
     if (this.selected != null) {
       disableMsg=false;
       setId(this.selected.getId());
       setCode(this.selected.getCode());
       setDesignation(this.selected.getDesignation());
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.selected = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.niveauFormation));
       if (this.selected == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (Base)event.getObject();
     if (this.selected != null)
     {
       setObject();
     }
   }
 
   
   public void enregistrer() {
     if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Code et Désignation sont obligatoires");
     }
     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.niveauFormation)) != null) {
       
       HelperC.afficherMessage("Information", "Ce code existe déjà ");
     }
     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.niveauFormation)) != null) {
       
       HelperC.afficherMessage("Information", "Cette désignation existe déjà ");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du niveau de formation " + getCode());
       } else {
         
         hist.setOperation("Modification du niveau de formation " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.niveauFormation));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.niveauFormation))) {
         
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
       
       HelperC.afficherDeleteMessage();
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.niveauFormation), getId())) {
        HelperC.afficherMessage("Information", "Succès de l'opération ");
       clear(true);
      
       charger();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
   
 }


