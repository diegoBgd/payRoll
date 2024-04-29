 package vuesPaie;
 
 import classesPaie.ConditionPositionC;
 import classesPaie.Constante;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class ConditionPositionB
   extends ConditionPositionC
   implements Serializable
 {
   private static final long serialVersionUID = 749760639710887039L;
   private ConditionPositionC selected;
   private int idPosition;
   private List<ConditionPositionC> listConditionsPostition = new ArrayList<ConditionPositionC>();
   private OperateurC operateur;
   private HttpSession session = HelperC.getSession();
   private ExerciceC exercice;
   
   public List<ConditionPositionC> getListConditionsPostition() {
     return this.listConditionsPostition;
   }
 
   
   public void setListConditionsPostition(List<ConditionPositionC> listConditionsPostition) {
     this.listConditionsPostition = listConditionsPostition;
   }
 
   
   public ConditionPositionC getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(ConditionPositionC selected) {
     this.selected = selected;
   }
 
   
   public int getIdPosition() {
     return this.idPosition;
   }
 
   
   public void setIdPosition(int idPosition) {
     this.idPosition = idPosition;
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
 
   
   public static long getSerialversionuid() {
     return 749760639710887039L;
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
     this.listConditionsPostition = FichierBaseDAO.getInstance().getListeConditionPosition();
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setId(0);
     }
     setCondition("");
     setPosition(null);
     setLibellePosition("");
     this.selected = null;
   }
 
   
   private void setObject() {
     if (this.selected != null) {
       
       setId(this.selected.getId());
       setCondition(this.selected.getCondition());
       setLibellePosition(this.selected.getLibellePosition());
       setPosition(this.selected.getPosition());
       if (getPosition() != null)
       {
         this.idPosition = Constante.getPosition(getPosition());
       }
     } 
   }
 
   
   public void changePosition(ValueChangeEvent event) {
     this.idPosition = ((Integer)event.getNewValue()).intValue();
     if (this.idPosition != 0)
     {
       setPosition(Constante.getPosition(this.idPosition));
     }
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (ConditionPositionC)event.getObject();
     if (this.selected != null)
     {
       setObject();
     }
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
   
   public void save() {
     if (getPosition() == null) {
       
       HelperC.afficherMessage("Information", "Veillez selectionner la position!");
     }
     else if (getCondition().equalsIgnoreCase("")) {
       
       HelperC.afficherMessage("Information", "Veillez saisir la condition");
     } else {
       
       this.selected = FichierBaseDAO.getInstance().getConditionPosition(getCondition(), getPosition(), getId());
       if (this.selected == null) {
         
         if (FichierBaseDAO.getInstance().insertUpdateConditionPosition(this)) {
           
           HelperC.afficherMessage("Information", "Succès d'enregistrement");
           this.listConditionsPostition = FichierBaseDAO.getInstance().getListeConditionPosition();
           clear(true);
         } else {
           
           HelperC.afficherMessage("information", "Echec d'enregistrement");
         } 
       } else {
         
         HelperC.afficherMessage("information", "Cet employé est déjé dans ce grade");
       } 
     } 
   }
 
 
   
   public void supprimer() {
     try {
       if (getId() == 0) {
         
         HelperC.afficherDeleteMessage();
       }
       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.conditionPosition))) {
         
         HelperC.afficherMessage("Félicitation", "Succès de l'Opération");
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'Opération");
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void fermer() {
     try {
       FacesContext.getCurrentInstance().getExternalContext().redirect("/asystPaie/masterPage.jsf");
     }
     catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 }


