 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ServicesC;
 import classesPaie.SousServiceC;
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
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class SousServiceB
   extends SousServiceC
 {
   private static final long serialVersionUID = 86856177370856233L;
   private SousServiceC selected;
   private List<SelectItem> listService;
   private List<SousServiceC> listSousService;
   private int idService;
   OperateurC operateur;
   DroitC droit;
   ExerciceC exercice;
   HttpServletRequest request;
   Base userFonction;
   ServicesC service;
   HttpSession session = HelperC.getSession();
   
   public SousServiceC getSelected() {
     return this.selected;
   }
   public void setSelected(SousServiceC selected) {
     this.selected = selected;
   }
   public List<SelectItem> getListService() {
     return this.listService;
   }
   public void setListService(List<SelectItem> listService) {
     this.listService = listService;
   }
   public List<SousServiceC> getListSousService() {
     return this.listSousService;
   }
   public void setListSousService(List<SousServiceC> listSousService) {
     this.listSousService = listSousService;
   }
   public int getIdService() {
     return this.idService;
   }
   public void setIdService(int idService) {
     this.idService = idService;
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
         context.getExternalContext().redirect("/gestionPaie/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       this.request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       setTypeSubService(this.request.getParameter("type"));
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.structureAdministrative);
       }
       chargementService();
     } 
   }
 
 
   
   private void chargementService() {
     this.listService = new ArrayList<SelectItem>();
     this.listService.add(new SelectItem(Integer.valueOf(0), ""));
     for (ServicesC srv : FichierBaseDAO.getInstance().getListeServices(0))
     {
       this.listService.add(new SelectItem(Integer.valueOf(srv.getId()), String.valueOf(srv.getCode()) + "||" + srv.getDesignation()));
     }
   }
   
   private void chargement() {
     if (getService() != null)
     {
       this.listSousService = FichierBaseDAO.getInstance().getListSouServices(this.idService, getTypeSubService());
     }
   }
   
   public void changeService(ValueChangeEvent event) {
     this.idService = ((Integer)event.getNewValue()).intValue();
     this.service = FichierBaseDAO.getInstance().getServices(this.idService);
     setService(this.service);
     chargement();
   }
   
   public void takeSelection() {
     if (this.selected != null)
     {
       setObject();
     }
   }
   
   public void searchSousService() {
     if (!getCode().equals("")) {
       
       this.selected = FichierBaseDAO.getInstance().getSouServices(getCode());
       if (this.selected != null)
       {
         setObject(); } 
     } 
   }
   
   private void setObject() {
     setId(this.selected.getId());
     setCode(this.selected.getCode());
     setDesignation(this.selected.getDesignation());
     setTypeSubService(this.selected.getTypeSubService());
     this.service = this.selected.getService();
     this.idService = this.service.getId();
   }
 
   
   public void searchSouService() {
     if (getCode() != null && !getCode().equals(""))
     {
       if (this.idService > 0) {
         
         this.selected = FichierBaseDAO.getInstance().getSouServices(getCode(), this.idService);
         if (this.selected != null) {
           setObject();
         }
       } else {
         HelperC.afficherAttention("ATTENTION", "Il faut préciser le service");
       } 
     }
   }
 
 
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() != 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getService() == null) {
       
       HelperC.afficherInformation("Information", "Précisez le service");
     }
     else if (getCode().trim().equals("")) {
       
       HelperC.afficherInformation("Information", "Précisez le code");
     
     }
     else if (getDesignation().trim().equals("")) {
       
       HelperC.afficherInformation("Information", "Précisez la Désignation");
     }
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
       if (FichierBaseDAO.getInstance().insertUpdateSousServices(this)) {
 
         
         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
         chargement();
         clear();
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
       
       HelperC.afficherDeleteMessage();
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       hist.setOperation("Suppression Service code: " + getCode());
       hist.setTable(Tables.getTableName(Tables.TableName.services));
       hist.setIdLigne(getId());
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().deleteSousServices(this)) {
         
         clear();
         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
         chargement();
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
       } 
     } 
   }
 
 
   
   private void clear() {
     setId(0);
     setCode("");
     setDesignation("");
   }
 
 
   
   public void initialiser() {
     clear();
     this.service = null;
     this.idService = 0;
     this.listSousService.clear();
   }
 }


