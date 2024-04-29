 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DirectionC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ServiceDetailC;
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
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSession;
 import org.primefaces.PrimeFaces;
 import persistencePaie.FichierBaseDAO;

 
 @ManagedBean
 @ViewScoped
 public class ServicesB
   extends ServicesC
 {
   private HttpSession session = HelperC.getSession();
   private List<ServicesC> listeService = new ArrayList<ServicesC>();
   private ServicesC service = null; 
			private static final long serialVersionUID = -5406685991306351422L; 
			private List<SelectItem> listeDirection;
			private boolean disableMsg;
			private int idDirection;
   private ServiceDetailC detail;
   
  
   private OperateurC operateur; 
			private ExerciceC exercice; 
			private DroitC droit; 
			private DirectionC direction; 
			private Base directionUb; 
			Base userFonction; 
			HttpServletRequest request;
			
   
   
   public Base getDirectionUb() {
     return this.directionUb;
   }
   
   public void setDirectionUb(Base directionUb) {
     this.directionUb = directionUb;
   }
   
   public List<SelectItem> getListeDirection() {
     return this.listeDirection;
   }
   public void setListeDirection(List<SelectItem> listeDirection) {
     this.listeDirection = listeDirection;
   }
 
   
   public DirectionC getDirection() {
     return this.direction;
   }
 
   
   public void setDirection(DirectionC direction) {
     this.direction = direction;
   }
   
 
   
   public int getIdDirection() {
     return this.idDirection;
   }
 
   
   public void setIdDirection(int idDirection) {
     this.idDirection = idDirection;
   }
 
   
   public ServiceDetailC getDetail() {
     return this.detail;
   }
 
   
   public void setDetail(ServiceDetailC detail) {
     this.detail = detail;
   }
 
   
   public ServicesC getService() {
     return this.service;
   }
 
   
   public void setService(ServicesC service) {
     this.service = service;
   }
   
   public List<ServicesC> getListeService() {
     return this.listeService;
   }
   
   public void setListeService(List<ServicesC> listeService) {
     this.listeService = listeService;
   }
 
 public boolean isDisableMsg() {
			return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   
   @PostConstruct
   public void init() {
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
       
       this.request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
      
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.structureAdministrative);
       }
       disableMsg=true;
       chargement();
       chargementDirection();
     
     } 
   }

   
  
   
   private void chargementDirection() {
     this.listeDirection = new ArrayList<SelectItem>();
     this.listeDirection.add(new SelectItem(Integer.valueOf(0), " "));
     
     for (DirectionC dir : FichierBaseDAO.getInstance().getListeDirection()) {
       this.listeDirection.add(new SelectItem(Integer.valueOf(dir.getId()), String.valueOf(dir.getCode()) + "||" + dir.getDesignation()));
     }
   }
 
   
   public void changeOrgane(ValueChangeEvent event) {
     this.idDirection = ((Integer)event.getNewValue()).intValue();
     this.direction = FichierBaseDAO.getInstance().getDirection(this.idDirection);
     if (this.direction != null)
       setDirection(this.direction); 
     chargement();
   }
 
   
   public void takeSelection() {
     if (this.service != null) {
     disableMsg=false;
       setId(this.service.getId());
       setCode(this.service.getCode());
       setDesignation(this.service.getDesignation());
       if (this.service.getDirection() != null) {
         
         this.idDirection = this.service.getDirection().getId();
         this.direction = this.service.getDirection();
         setDirection(this.direction);
        
       } 
       
     } 
   }
 
   
   public void changeDirection(ValueChangeEvent event) {
     this.idDirection = ((Integer)event.getNewValue()).intValue();
     this.direction = FichierBaseDAO.getInstance().getDirection(this.idDirection);
     setDirection(this.direction);
    
     setDirectionUb((Base)null);
     chargement();
   }
 
   
   
   public void searchService() {
     if (getCode() != null && !getCode().equals(""))
     {
      
         
         this.service = FichierBaseDAO.getInstance().getService(getCode(), this.idDirection);
         if (this.service != null)
           affecter(); 
       } 
     }
   
 
   
   private void chargement() {
     this.listeService.clear();
     this.listeService = FichierBaseDAO.getInstance().getListeServices(this.idDirection);
   }
 
   
   private void clear(boolean b) {
     if (b) {
       
       setDirection((DirectionC)null);
       this.listeService.clear();
       this.idDirection = 0;
     } 
     
     setId(0);
     setCode("");
     setDesignation("");
     this.service = null;
			  disableMsg=true;
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() != 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
    
     else if (getCode().trim().equals("")) {
       
       HelperC.afficherAttention("Information", "Précisez la réference");
     
     }
     else if (getDesignation().trim().equals("")) {
       
       HelperC.afficherAttention("Information", "Précisez la Désignation");
     
     }
				if(idDirection==0)
					 HelperC.afficherAttention("Information", "Précisez la direction");
     else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Saisie Service code: " + getCode());
       } else {
         
         hist.setOperation("Modification Service code: " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.services));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateServices(this)) {
         
         clear(false);
         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
         chargement();
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (!this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }
     else if (getId() == 0) {
       
       HelperC.afficherInformation("Information", "Précisez l'élément à supprimer");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       hist.setOperation("Suppression Service code: " + getCode());
       hist.setTable(Tables.getTableName(Tables.TableName.services));
       hist.setIdLigne(getId());
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().deleteServices(this)) {
         
         clear(false);
         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
         chargement();
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
       } 
     } 
   }
 
   
   public void dbClickCode() {
     if (getDirection() == null) {
       
       HelperC.afficherInformation("Information", "Précisez la Direction d'abord");
     } else {
       
       this.listeService = FichierBaseDAO.getInstance().getListeServices(0);
       PrimeFaces.current().executeScript("PF('recherche').hide();");
     } 
   }
 
 
   
   public void affecter() {
				disableMsg=true;
     if (this.service != null) {
       disableMsg=false;
       setId(this.service.getId());
       setCode(this.service.getCode());
       setDesignation(this.service.getDesignation());
       PrimeFaces.current().executeScript("PF('recherche').hide();");
     } 
   }
 }


