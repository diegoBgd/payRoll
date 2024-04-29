 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.PlanningCongeC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.application.FacesMessage;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.PrimeFaces;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;
 
 
 @ManagedBean
 @ViewScoped
 public class PlanningCongeB
   extends PlanningCongeC
 {
   private static final long serialVersionUID = -2277577768915294339L;
   private String code;
   private String codeEmployeRecherche;
   private String nomEmployeRecherche;
   private String prenomEmployeRecherche;
   private String dateDebutS;
   private String dateFinS;
   private EmployeC employeSelected;
   private List<PlanningCongeC> listProgrammationConge;
   private PlanningCongeC planningSelected;
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
   
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private DroitC droit;
   
   public PlanningCongeB() {
     this.listProgrammationConge = new ArrayList<PlanningCongeC>();
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
     } 
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
   
   public String getCode() {
     return this.code;
   }
   
   public void setCode(String code) {
     this.code = code;
   }
   
   public String getCodeEmployeRecherche() {
     return this.codeEmployeRecherche;
   }
   
   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
     this.codeEmployeRecherche = codeEmployeRecherche;
   }
   
   public String getNomEmployeRecherche() {
     return this.nomEmployeRecherche;
   }
   
   public void setNomEmployeRecherche(String nomEmployeRecherche) {
     this.nomEmployeRecherche = nomEmployeRecherche;
   }
   
   public String getPrenomEmployeRecherche() {
     return this.prenomEmployeRecherche;
   }
   
   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
     this.prenomEmployeRecherche = prenomEmployeRecherche;
   }
   
   public EmployeC getEmployeSelected() {
     return this.employeSelected;
   }
   
   public void setEmployeSelected(EmployeC employeSelected) {
     this.employeSelected = employeSelected;
   }
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
   
   public List<PlanningCongeC> getListProgrammationConge() {
     return this.listProgrammationConge;
   }
 
   
   public void setListProgrammationConge(List<PlanningCongeC> listProgrammationConge) {
     this.listProgrammationConge = listProgrammationConge;
   }
   
   public PlanningCongeC getPlanningSelected() {
     return this.planningSelected;
   }
   
   public void setPlanningSelected(PlanningCongeC planningSelected) {
     this.planningSelected = planningSelected;
   }
   
   public String getDateDebutS() {
     return this.dateDebutS;
   }
   
   public void setDateDebutS(String dateDebutS) {
     this.dateDebutS = dateDebutS;
   }
   
   public String getDateFinS() {
     return this.dateFinS;
   }
   
   public void setDateFinS(String dateFinS) {
     this.dateFinS = dateFinS;
   }
 
 
   
   public void findByCode() {
     this.employeSelected = FactoryDAO.getInstance().getEmploye(getCode(), false);
     
     if (this.employeSelected != null) {
       this.code = this.employeSelected.getCode();
       setEmploye(this.employeSelected);
       chargerConges();
     } else {
       clearInfo(true);
     } 
   }
   
   public void chargerEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, 
         this.nomEmployeRecherche,true);
   }
 
   
   public void onRowEmployeSelected() {
     if (this.employeSelected != null) {
       setEmploye(this.employeSelected);
       setCode(this.employeSelected.getCode());
       chargerConges();
       
       PrimeFaces.current().executeScript("PF('dlg').hide();");
     } 
   }
 
 
   
   public void changeDateDebut() {
     if (getDateDebutS().replace("/", "").replace("_", "").trim()
       .equals("")) {
       setDateDebut(null);
     } else {
       setDateDebut(HelperC.validerDate(getDateDebutS()));
       
       if (getDateDebut() == null) {
         setDateDebutS("");
         HelperC.afficherMessage("Information", "Date Invalide!");
       } else {
         setDateDebutS(HelperC.convertDate(getDateDebut()));
       } 
     } 
   }
 
   
   public void changeDateFin() {
     if (getDateFinS().replace("/", "").replace("_", "").trim()
       .equals("")) {
       setDateFin(null);
     } else {
       setDateFin(HelperC.validerDate(getDateFinS()));
       
       if (getDateFin() == null) {
         setDateFinS("");
         HelperC.afficherMessage("Information", "Date Invalide!");
       } else {
         setDateDebutS(HelperC.convertDate(getDateFin()));
       } 
     } 
   }
   
   public void onRowPlanningSelected() {
     if (this.planningSelected != null) {
       setId(this.planningSelected.getId());
       
       if (this.planningSelected.getEmploye() != null) {
         setEmploye(this.planningSelected.getEmploye());
       } else {
         setEmploye(null);
       } 
       if (this.planningSelected.getDateDebut() != null) {
         setDateDebut(this.planningSelected.getDateDebut());
         setDateDebutS(HelperC.convertDate(this.planningSelected.getDateDebut()));
       } else {
         setDateDebut(null);
         setDateDebutS("");
       } 
       
       if (this.planningSelected.getDateFin() != null) {
         setDateFin(this.planningSelected.getDateFin());
         setDateFinS(HelperC.convertDate(this.planningSelected.getDateFin()));
       } else {
         setDateFin(null);
         setDateFinS("");
       } 
     } 
   }
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     } else if (getId() > 0 && !this.droit.isModifier()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     } else if (getEmploye() == null) {
       HelperC.afficherMessage("Information", 
           "Veuillez d'abord préciser l'employé SVP!", 
           FacesMessage.SEVERITY_ERROR);
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(getOperateurCreation());
       if (getId() == 0) {
         hist.setOperation("Enregistrement du planning de congé pour l'employé:" + getEmploye().getMatricule());
       } else {
         hist.setOperation("Modification du planning de congé pour l'employé:" + getEmploye().getMatricule());
       }  hist.setTable(Tables.getTableName(Tables.TableName.planningConge));
       setHistoric(hist);
     } 
   }
 
 
 
 
 
 
 
 
 
   
   public void supprimer() {
     if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     } else if (getId() > 0 && !this.droit.isSupprimer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }  if (getId() > 0) {
       if (FactoryDAO.getInstance().delete(getId(), 
           Tables.getTableName(Tables.TableName.planningConge))) {
         HelperC.afficherMessage("Information", "Succès de l'Opération!", 
             FacesMessage.SEVERITY_INFO);
         chargerConges();
         clearInfo(false);
       } else {
         HelperC.afficherMessage("Information", "Echec de l'opération", 
             FacesMessage.SEVERITY_ERROR);
       } 
     }
   }
 
 
 
 
   
   private void chargerConges() {
     if (getListProgrammationConge().size() > 0) {
       for (PlanningCongeC p : getListProgrammationConge()) {
         
         if (p.getDateDebut() != null) {
           p.setDateDebS(HelperC.convertDate(p.getDateDebut()));
         } else {
           p.setDateDebS("");
         } 
         if (p.getDateFin() != null) {
           p.setDateFiS(HelperC.convertDate(p.getDateFin())); continue;
         } 
         p.setDateFiS("");
       } 
     }
   }
 
   
   private void clearInfo(boolean b) {
     if (b) {
       setCode("");
       this.employeSelected = null;
       setEmploye(null);
       getListProgrammationConge().clear();
     } 
     
     setId(0);
     setDateDebut(null);
     setDateDebutS("");
     setDateFin(null);
     setDateFinS("");
   }
   
   public void initialiser() {
     clearInfo(true);
   }
 }


