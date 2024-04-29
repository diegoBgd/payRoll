 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import classesPaie.TypeCongeC;
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
 public class TypeCongeB
   extends TypeCongeC
 {
   private static final long serialVersionUID = -3785327091564332422L;
   private TypeCongeC selected;
   private List<TypeCongeC> typeConges;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
 			private boolean disableMsg;
   private HttpSession session = HelperC.getSession();
   Base userFonction;
   
   public List<TypeCongeC> getTypeConges() {
     return this.typeConges;
   }
   public void setTypeConges(List<TypeCongeC> typeConges) {
     this.typeConges = typeConges;
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
   
   public TypeCongeC getSelected() {
     return this.selected;
   }
   public void setSelected(TypeCongeC selected) {
     this.selected = selected;
   }
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   @PostConstruct
   private void init() {
     String codeExercice = (String)this.session.getAttribute("exercice");
     String codeOperateur = (String)this.session.getAttribute("operateur");
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     if (this.exercice == null || this.operateur == null) {
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       } catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       disableMsg=true;
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge);
       }
       this.typeConges = FichierBaseDAO.getInstance().getAllTypeConge();
     } 
   }
 
 
 
   
   private void clear(boolean b) {
     if (b)
       setCode(""); 
     setId(0);
     setDesignation("");
     setSorteConge(0);
     setAnnuel(false);
     this.selected = null;
			  disableMsg=true;
			  setNombreJour(0);
   }
 
   
   private void setObject() {
     if (this.selected != null) {
        disableMsg=false;
       setId(this.selected.getId());
       setCode(this.selected.getCode());
       setDesignation(this.selected.getDesignation());
       setSorteConge(this.selected.getSorteConge());
       setAnnuel(this.selected.isAnnuel());
				setNombreJour(this.selected.getNombreJour());
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       clear(true);
     } else {
       this.selected = FichierBaseDAO.getInstance().getTypeConge(getCode());
       if (this.selected == null) {
         clear(false);
       } else {
         setObject();
       } 
     } 
   }
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (TypeCongeC)event.getObject();
     if (this.selected != null) {
       setObject();
     }
   }
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     } else if (getId() > 0 && !this.droit.isModifier()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     } else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
       HelperC.afficherMessage("Information", "Code et Désignation sont obligatoires");
     } else if (getSorteConge() == 0) {
       HelperC.afficherMessage("Information", "Le sorte de congé est obligatoire");
     }
     else if (FichierBaseDAO.getInstance().getTypeConge(getCode(), getId()) != null) {
       HelperC.afficherMessage("Information", "Ce code existe déjé ");
     } else if (FichierBaseDAO.getInstance().getTypeConges(getDesignation(), getId()) != null) {
       HelperC.afficherMessage("Information", "Cette désignation existe déjé");
     }
     else {
       
      
       if (FichierBaseDAO.getInstance().insertUpdateTypeConge(this)) {
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         this.typeConges = FichierBaseDAO.getInstance().getAllTypeConge();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
   
   public void supprimer() {
     if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     } else if (getId() > 0 && !this.droit.isSupprimer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeConge), getId())) {
       clear(true);
       HelperC.afficherMessage("Information", "Succès de l'opération ");
       this.typeConges = FichierBaseDAO.getInstance().getAllTypeConge();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 }

