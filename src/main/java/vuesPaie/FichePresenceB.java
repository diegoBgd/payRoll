 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.FichePresenceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.List;
 import javax.faces.application.FacesMessage;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class FichePresenceB
   extends FichePresenceC
 {
   private static final long serialVersionUID = -2310124807870544746L;
   private int idEmploye;
   private String datePointageS;
   private List<SelectItem> listEmploye = new ArrayList<SelectItem>();
   private OperateurC operateur;
   private HttpSession session = HelperC.getSession(); private FichePresenceC presenceSelected; private DroitC droit;
   ExerciceC exercice;
   Base userFonction;
   
   public void init() {
     String codeExercice = (String)this.session.getAttribute("exercice");
     String codeOperateur = (String)this.session.getAttribute("operateur");
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     if (this.exercice == null || this.operateur == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionAbsencePresence);
       }
       setExercise(this.exercice);
       setDatePointage(Calendar.getInstance().getTime());
       this.datePointageS = HelperC.convertDate(getDatePointage());
       this.listEmploye.add(new SelectItem(Integer.valueOf(0), ""));
       
       for (EmployeC emp : FactoryDAO.getInstance().getAllEmploye(false, 0))
       {
         this.listEmploye.add(new SelectItem(Integer.valueOf(emp.getId()), String.valueOf(emp.getNom()) + " " + emp.getPrenom()));
       }
     } 
   }
 
 
   
   public int getIdEmploye() {
     return this.idEmploye;
   }
 
   
   public void setIdEmploye(int idEmploye) {
     this.idEmploye = idEmploye;
   }
 
   
   public String getDatePointageS() {
     return this.datePointageS;
   }
 
   
   public void setDatePointageS(String datePointageS) {
     this.datePointageS = datePointageS;
   }
 
   
   public FichePresenceC getPresenceSelected() {
     return this.presenceSelected;
   }
 
   
   public void setPresenceSelected(FichePresenceC presenceSelected) {
     this.presenceSelected = presenceSelected;
   }
   
   public List<SelectItem> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<SelectItem> listEmploye) {
     this.listEmploye = listEmploye;
   }
 
   
   public void changeEmploye(ValueChangeEvent event) {
     this.idEmploye = ((Integer)event.getNewValue()).intValue();
     setEmploye(FactoryDAO.getInstance().getEmploye(this.idEmploye, false));
   }
 
   
   public void changeDatePointage() {
     if (this.datePointageS.replace("/", "").replace("_", "").trim().equals("")) {
       
       setDatePointage(null);
     } else {
       
       setDatePointage(HelperC.validerDate(this.datePointageS));
       if (getDatePointage() == null) {
         
         this.datePointageS = "";
         HelperC.afficherMessage("Information", "Date Invalide!");
       }
       else if (getDatePointage().after(Calendar.getInstance().getTime())) {
         
         this.datePointageS = "";
         HelperC.afficherMessage("Information", "Date Invalide!");
       } else {
         
         this.datePointageS = HelperC.convertDate(getDatePointage());
       } 
     } 
     showPresenceSelected();
   }
 
   
   public void changeHeureEntree() {
     if ((getHeureEntreee() != "" || getHeureEntreee() != null) && getHeureEntreee() != "" && !getHeureEntreee().replace("_", "").trim().replace(":", "").equals(""))
     {
       if (Integer.parseInt(getHeureEntreee().split(":")[0]) > 24 || Integer.parseInt(getHeureEntreee().split(":")[1]) > 59) {
         
         HelperC.afficherMessage("Information", "Heure Invalide!");
       } else {
         
         setHeureEntreee(getHeureEntreee());
       } 
     }
   }
 
   
   public void changeHeureSortie() {
     if ((getHeureSortie() != "" || getHeureSortie() != null) && getHeureSortie() != "" && !getHeureSortie().replace("_", "").trim().replace(":", "").equals(""))
     {
       if (Integer.parseInt(getHeureSortie().split(":")[0]) > 24 || Integer.parseInt(getHeureSortie().split(":")[1]) > 59) {
         
         HelperC.afficherMessage("Information", "Heure Invalide!");
       } else {
         
         setHeureSortie(getHeureSortie());
       } 
     }
   }
 
   
   public void changeTypeHeuresPrestees() {
     showPresenceSelected();
   }
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getEmploye() == null) {
       
       HelperC.afficherMessage("Information", "Veuillez d'abord préciser l'employé!", FacesMessage.SEVERITY_ERROR);
     }
     else if (getDatePointage() == null) {
       
       HelperC.afficherMessage("Information", "Veuillez saisir au moins la date de pointage SVP!", FacesMessage.SEVERITY_ERROR);
     }
     else if (getIdTypeHeurePreste() == 0) {
       
       HelperC.afficherMessage("Information", "Veuillez préciser le type d'heures prestées SVP!", FacesMessage.SEVERITY_ERROR);
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Pointage de l'employé " + getEmploye().getCode());
       } else {
         
         hist.setOperation("Modification du pointage de l'employé " + getEmploye().getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.pointagePresence));
       setHistoric(hist);
       if (FactoryDAO.getInstance().insertUpdatePointagePresence(this)) {
         
         initialiser();
         HelperC.afficherMessage("Information", "Succès de l'Opération!", FacesMessage.SEVERITY_INFO);
       } else {
         
         HelperC.afficherMessage("Information", "Echec de l'Opération!", FacesMessage.SEVERITY_ERROR);
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (getId() > 0 && !this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }
     else if (getId() > 0) {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Pointage de l'employé " + getEmploye().getCode());
       } else {
         
         hist.setOperation("Modification du pointage de l'employé " + getEmploye().getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.pointagePresence));
       setHistoric(hist);
       if (getIdTypeHeurePreste() > 0) {
         
         if (FactoryDAO.getInstance().deletePointagePresence(this)) {
           
           initialiser();
           HelperC.afficherMessage("Information", "Succès de l'Opération!", FacesMessage.SEVERITY_INFO);
         } else {
           
           HelperC.afficherMessage("Information", "Echec de l'Opération!", FacesMessage.SEVERITY_ERROR);
         } 
       } else {
         
         HelperC.afficherMessage("Information", "Il faut préciser le type d'heures prestées!", FacesMessage.SEVERITY_ERROR);
       } 
     } 
   }
 
   
   private void showPresenceSelected() {
     if (getEmploye() != null && getDatePointage() != null) {
       
       this.presenceSelected = FactoryDAO.getInstance().getFichePresenceParEmployeEtDatePointage(getEmploye().getId(), HelperC.convertDat(getDatePointage()), getIdTypeHeurePreste());
       if (this.presenceSelected != null) {
         
         setId(this.presenceSelected.getId());
         setIdTypeHeurePreste(this.presenceSelected.getIdTypeHeurePreste());
         if (this.presenceSelected.getEmploye() != null) {
           
           this.idEmploye = this.presenceSelected.getEmploye().getId();
           setEmploye(this.presenceSelected.getEmploye());
         } else {
           
           this.idEmploye = 0;
           setEmploye(null);
         } 
         if (this.presenceSelected.getDatePointage() != null) {
           
           setDatePointage(this.presenceSelected.getDatePointage());
           this.datePointageS = HelperC.convertDate(this.presenceSelected.getDatePointage());
         } else {
           
           setDatePointage(null);
           this.datePointageS = "";
         } 
         setHeureEntreee(this.presenceSelected.getHeureEntreee());
         setHeureSortie(this.presenceSelected.getHeureSortie());
       } else {
         
         clearInfo(false);
       } 
     } 
   }
 
   
   private void clearInfo(boolean b) {
     setId(0);
     if (b) {
       
       this.idEmploye = 0;
       setEmploye(null);
       setDatePointage(Calendar.getInstance().getTime());
       this.datePointageS = HelperC.convertDate(getDatePointage());
     } 
     setHeureEntreee("");
     setHeureSortie("");
     setIdTypeHeurePreste(0);
   }
 
   
   public void initialiser() {
     clearInfo(true);
   }
 }


