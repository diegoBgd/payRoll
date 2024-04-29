 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DirectionC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ParametragePlanCongeC;
 import classesPaie.ServicesC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 @ManagedBean
 @ViewScoped
 public class ParametragePlanCongeB
   extends ParametragePlanCongeC
 {
   private static final long serialVersionUID = -3319971197056022743L;
  
   private int idDirection;
   private int idService;
   
   private ParametragePlanCongeC selected;
   private List<ParametragePlanCongeC> listParametrage = new ArrayList<ParametragePlanCongeC>();
  
   private List<SelectItem> listeDirection = new ArrayList<SelectItem>();
   private List<SelectItem> listeService = new ArrayList<SelectItem>();
   
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
 
   
   private DroitC droit;
  
   public int getIdDirection() {
     return this.idDirection;
   }
   
   public void setIdDirection(int idDirection) {
     this.idDirection = idDirection;
   }
   
   public int getIdService() {
     return this.idService;
   }
   
   public void setIdService(int idService) {
     this.idService = idService;
   }
   

   public ParametragePlanCongeC getSelected() {
     return this.selected;
   }
   
   public void setSelected(ParametragePlanCongeC selected) {
     this.selected = selected;
   }
   
   public List<ParametragePlanCongeC> getListParametrage() {
     return this.listParametrage;
   }
   
   public void setListParametrage(List<ParametragePlanCongeC> listParametrage) {
     this.listParametrage = listParametrage;
   }
   
   
   public List<SelectItem> getListeDirection() {
     return this.listeDirection;
   }
   
   public void setListeDirection(List<SelectItem> listeDirection) {
     this.listeDirection = listeDirection;
   }
   
   public List<SelectItem> getListeService() {
     return this.listeService;
   }
   
   public void setListeService(List<SelectItem> listeService) {
     this.listeService = listeService;
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
   
   public DroitC getDroit() {
     return this.droit;
   }
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }
   
   @PostConstruct
   public void init() {
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
       } catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(
           this.operateur.getIdEmploye());
       if (userFonction != null)
         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), 
             Constante.Role.gestionConge); 

      
       this.listeDirection.add(new SelectItem(Integer.valueOf(0), " "));
     
				for (Base d : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.direction))) {
         this.listeDirection.add(new SelectItem(Integer.valueOf( d.getId()), String.valueOf( d.getCode()) + " - " +  d.getDesignation()));
       }
       chargerPlan();
     } 
   }
 
  private void chargerPlan(){
			 listParametrage=FichierBaseDAO.getInstance().getListParametragePlanConge(exercice.getId());
			}
 
   
  
   
   public void changeDirection(ValueChangeEvent event) {
     this.idDirection = ((Integer)event.getNewValue()).intValue();
     this.idService = 0;
     this.listeService.clear();
     setDirection(FichierBaseDAO.getInstance().getDirection(this.idDirection));
     if (getDirection() != null) {
	  			this.listeService.add(new SelectItem(Integer.valueOf(0), " "));
       for (ServicesC servi : FichierBaseDAO.getInstance().getListeServices(getDirection().getId())) {
         this.listeService.add(new SelectItem(Integer.valueOf(servi.getId()), String.valueOf(servi.getCode()) + " || " + servi.getDesignation()));
       }
       
     } 
   }
   
   public void changeService(ValueChangeEvent event) {
     this.idService = ((Integer)event.getNewValue()).intValue();
     setService(FichierBaseDAO.getInstance().getServices(this.idService));
     if (getService() != null) {
       setDirection(null);
     }
   }
   
   public void setObject() {
     if (this.selected != null) {
       setId(this.selected.getId());
      
 	   setNombreEmploye(selected.getNombreEmploye());
       
       setDirection(this.selected.getDirection());
       if (getDirection() != null) {
         this.idDirection = getDirection().getId();
         for (ServicesC servi : FichierBaseDAO.getInstance().getListeServices(this.idDirection)) {
           this.listeService.add(new SelectItem(Integer.valueOf(servi.getId()), String.valueOf(servi.getCode()) + " || " + servi.getDesignation()));
         }
          setService(this.selected.getService());
					this.idService = getService().getId();
         }
					
       } 
       
     
      
   }
 
   
   private void clear() {
     setId(0);
    
     
   
     this.idDirection = 0;
     this.listeService.clear();
     this.idService = 0;
     setDirection(null);
     setService(null);
     setNombreEmploye(0);
     
     this.selected = null;
   }
   
   public void initialiser() {
     clear();
   }
 
 
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     } else if (getId() != 0 && !this.droit.isModifier()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     
     } else if (getNombreEmploye() == 0) {
       HelperC.afficherMessage("Information", "Le nombre d'employé ne peut pas étre zéro");
     }  else {
       if (getService() == null) {
	       HelperC.afficherMessage("Information", "Il faut préciser le service");
	     }  else {
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Saisie Parametrage congé ");
       } else {
         hist.setOperation("Modification Parametrage congé ");
       }  hist.setTable(Tables.getTableName(Tables.TableName.parametragePlanConge));
       setHistorique(hist);
        setIdExercice(exercice.getId());
       if (FichierBaseDAO.getInstance().insertUpdateParametragePlanConge(this)) {
         clear();
				  chargerPlan();
         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
       } 
     }
   }
 }
   
   public void supprimer() {
     if (!this.droit.isSupprimer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     } else if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       hist.setOperation("Suppression Parametrage congé ");
       hist.setTable(Tables.getTableName(Tables.TableName.parametragePlanConge));
       hist.setIdLigne(getId());
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().deleteParametragePlanConge(this)) {
         clear();
			      chargerPlan();
         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
       } 
     } 
   }
   

   public void onRowSelected(SelectEvent event) {
     this.selected = (ParametragePlanCongeC)event.getObject();
     if (this.selected != null)
       setObject(); 
   }
 }


