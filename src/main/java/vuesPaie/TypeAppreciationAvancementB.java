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
 public class TypeAppreciationAvancementB
   extends Base
 {
   private static final long serialVersionUID = -4488191200197067424L;
   private Base typeAppreciation;
   private List<Base> listeTypeAppreciations;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   private boolean disableMsg;
 
   
   public Base getTypeAppreciation() {
     return this.typeAppreciation;
   }
 
   
   public void setTypeAppreciation(Base typeAppreciation) {
     this.typeAppreciation = typeAppreciation;
   }
 
   
   public List<Base> getListeTypeAppreciations() {
     return this.listeTypeAppreciations;
   }
   
   public void setListeTypeAppreciations(List<Base> listeTypeAppreciations) {
     this.listeTypeAppreciations = listeTypeAppreciations;
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
 
public boolean isDisableMsg() {
	return disableMsg;
}
public void setDisableMsg(boolean disableMsg) {
	this.disableMsg = disableMsg;
}
   @PostConstruct
   private void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
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
       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.premiereEvaluation);
       }
       charger();
				disableMsg=true;
     } 
   }

 
   
   private void charger() {
     this.listeTypeAppreciations = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAppreciationAvancement));
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.typeAppreciation = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeAppreciationAvancement));
       if (this.typeAppreciation == null) {
         
         clear(false);
       } else {
         
         affecter();
       } 
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     this.typeAppreciation = null;
     setDesignation("");
				disableMsg=true;
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.typeAppreciation = (Base)event.getObject();
     if (this.typeAppreciation != null)
     {
       affecter();
     }
   }
 
   
   private void affecter() {
				disableMsg=true;
     if (this.typeAppreciation != null) {
        disableMsg=false;
       setId(this.typeAppreciation.getId());
       setCode(this.typeAppreciation.getCode());
       setDesignation(this.typeAppreciation.getDesignation());
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
   public void enregistrer() {
     try {
       if (getId() == 0 && !this.droit.isCreer()) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
       }
       else if (getId() > 0 && !this.droit.isModifier()) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
       }
       else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
         
         HelperC.afficherMessage("Information", "Code et Désignation obligatoires");
       } else {
         
         this.typeAppreciation = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeAppreciationAvancement));
         if (this.typeAppreciation == null) {
           
           Historique hist = new Historique();
           hist.setDateOperation(Calendar.getInstance().getTime());
           hist.setOperateur(this.operateur);
           if (getId() == 0) {
             
             hist.setOperation("Création du type d'appreciation d'avancement " + getCode());
           } else {
             
             hist.setOperation("Modification du type d'appreciation d'avancement " + getCode());
           } 
           hist.setTable(Tables.getTableName(Tables.TableName.typeAppreciationAvancement));
           setHistorique(hist);
           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeAppreciationAvancement))) {
             
             clear(true);
             HelperC.afficherMessage("Félicitaions", "Succès de l'Opération");
             charger();
           } else {
             
             HelperC.afficherMessage("Désolé", "Echec de l'Opération");
           } 
         } else {
           
           HelperC.afficherMessage("Information", "Ce type d'appréciation vient d'étre créee par un autre opérateur");
         }
       
       } 
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void supprimer() {
     try {
       if (getId() == 0) {
         
         HelperC.afficherDeleteMessage();
       }
       else if (getId() > 0 && !this.droit.isSupprimer()) {
         
         HelperC.afficherDeleteMessage();
       }
       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.typeAppreciationAvancement))) {
         
         clear(true);
         HelperC.afficherMessage("Félicitation", "Succès de l'Opération");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'Opération");
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
  
 }

