 package vuesPaie;
 
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import classesPaie.TypeCritereC;
 import java.io.IOException;
 import java.util.ArrayList;
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
 public class TypeCritereB
   extends TypeCritereC
 {
   private static final long serialVersionUID = 2862485259253372895L;
   private List<TypeCritereC> allCriteres = new ArrayList<TypeCritereC>();
   private TypeCritereC selected = null;
   private HttpSession session = HelperC.getSession();
   private OperateurC operateur;
   private ExerciceC exercice;
   
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
 
   
   public List<TypeCritereC> getAllCriteres() {
     return this.allCriteres;
   }
   
   public void setAllCriteres(List<TypeCritereC> allCriteres) {
     this.allCriteres = allCriteres;
   }
 
   
   public TypeCritereC getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(TypeCritereC selected) {
     this.selected = selected;
   }
 
   
   @PostConstruct
   private void charger() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     this.operateur = (OperateurC)this.session.getAttribute("operateur");
     this.exercice = (ExerciceC)this.session.getAttribute("exercice");
     if (this.operateur == null || this.exercice == null) {
       
       try {
         
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/identification.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     }
     this.allCriteres = FichierBaseDAO.getInstance().getAllTypeCritere();
   }
 
   
   public void findByCode() {
     if (getCode().equalsIgnoreCase("")) {
       
       clear(true);
     } else {
       
       this.selected = FichierBaseDAO.getInstance().getTypeCritere(getCode());
       if (this.selected == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setDesignation("");
     setNoteAppreciationGlobale(0.0D);
     setNoteAppreciationGlobaleS("");
     this.selected = null;
   }
 
   
   private void setObject() {
     if (this.selected != null) {
       
       setId(this.selected.getId());
       setCode(this.selected.getCode());
       setDesignation(this.selected.getDesignation());
       setNoteAppreciationGlobale(this.selected.getNoteAppreciationGlobale());
       setNoteAppreciationGlobaleS(this.selected.getNoteAppreciationGlobaleS());
     } 
   }
 
   
   public void changeNoteAppreciation() {
     try {
       setNoteAppreciationGlobale(Double.valueOf(getNoteAppreciationGlobaleS().replace(" ", "").replace(",", ".").trim()).doubleValue());
     } catch (Exception e) {
       setNoteAppreciationGlobale(0.0D);
     } finally {
       if (getNoteAppreciationGlobale() < 0.0D) {
         setNoteAppreciationGlobale(0.0D);
       }
       if (getNoteAppreciationGlobale() > 0.0D) {
         
         setNoteAppreciationGlobaleS(HelperC.TraitementMontant.getMontantFormate(getNoteAppreciationGlobale(), 2));
         setNoteAppreciationGlobale(Double.valueOf(getNoteAppreciationGlobaleS().replace(" ", "").replace(",", ".").trim()).doubleValue());
       } else {
         
         setNoteAppreciationGlobaleS("");
         setNoteAppreciationGlobale(0.0D);
       } 
     } 
   }
 
 
 
 
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (TypeCritereC)event.getObject();
     if (this.selected != null)
     {
       setObject();
     }
   }
 
   
   public void enregistrer() {
     if (getDesignation().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Completez tous les champs nécessaires");
     }
     else if (FichierBaseDAO.getInstance().getTypeCritere(getCode(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Ce code de type critere existe déjé ");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création d'un type critére d'évaluation " + getCode());
       } else {
         
         hist.setOperation("Modification d'un type critére d'évaluation " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeCritere));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateTypeCritere(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
         this.allCriteres = FichierBaseDAO.getInstance().getAllTypeCritere();
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherMessage("Information", "Précisez le type de critere é supprimer");
     }
     else if (FichierBaseDAO.getInstance().deleteTypeCritere(this)) {
       
       HelperC.afficherMessage("Information", "Succès de l'opération ");
       this.allCriteres = FichierBaseDAO.getInstance().getAllTypeCritere();
       clear(true);
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   

 }


