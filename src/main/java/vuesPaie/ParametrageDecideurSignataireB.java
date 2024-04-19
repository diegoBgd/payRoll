 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageDecideurDetailC;
 import classesPaie.ParametrageDecideurSignataireC;
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
 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;
 
 @ManagedBean
 @ViewScoped
 public class ParametrageDecideurSignataireB extends ParametrageDecideurSignataireC {
   private static final long serialVersionUID = -7312582735996409528L;
   private int idDirectionUniversite;
   private int idDirection;
   private int idService;
   private int idPersonnel;
   private int idfonction;
   private int idConge;
   private int idPosition;
   private int idSanction;
   private int idMofitFinCarrie;
   private int idTypeOperation;
   private int position;
   private boolean disableMsg;
   private String codeEmpl;
   private String nomEmpl;
   private ParametrageDecideurSignataireC selected;
   private ParametrageDecideurDetailC detail;
   private List<EmployeC> listEmploye;
   private EmployeC employe;
   private List<ParametrageDecideurSignataireC> listParametrage;
  
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   
   private DroitC droit;
   
   boolean selectedDet;
   
   int index;
 
   
   public EmployeC getEmploye() {
     return this.employe;
   }
   public void setEmploye(EmployeC employe) {
     this.employe = employe;
   }
   
   public int getIdDirectionUniversite() {
     return this.idDirectionUniversite;
   }
   
   public void setIdDirectionUniversite(int idDirectionUniversite) {
     this.idDirectionUniversite = idDirectionUniversite;
   }
   
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
   
   public int getIdPersonnel() {
     return this.idPersonnel;
   }
   
   public void setIdPersonnel(int idPersonnel) {
     this.idPersonnel = idPersonnel;
   }
   
   public int getIdfonction() {
     return this.idfonction;
   }
   
   public void setIdfonction(int idfonction) {
     this.idfonction = idfonction;
   }
   
   public ParametrageDecideurSignataireC getSelected() {
     return this.selected;
   }
   
   public void setSelected(ParametrageDecideurSignataireC selected) {
     this.selected = selected;
   }
   
   public List<ParametrageDecideurSignataireC> getListParametrage() {
     return this.listParametrage;
   }
 
   
   public void setListParametrage(List<ParametrageDecideurSignataireC> listParametrage) {
     this.listParametrage = listParametrage;
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
   
   public int getIdConge() {
     return this.idConge;
   }
   
   public void setIdConge(int idConge) {
     this.idConge = idConge;
   }
   
   public int getIdPosition() {
     return this.idPosition;
   }
   
   public void setIdPosition(int idPosition) {
     this.idPosition = idPosition;
   }
   
   public int getIdSanction() {
     return this.idSanction;
   }
   
   public void setIdSanction(int idSanction) {
     this.idSanction = idSanction;
   }
   
   public int getIdMofitFinCarrie() {
     return this.idMofitFinCarrie;
   }
   
   public void setIdMofitFinCarrie(int idMofitFinCarrie) {
     this.idMofitFinCarrie = idMofitFinCarrie;
   }
   
   public int getIdTypeOperation() {
     return this.idTypeOperation;
   }
   
   public void setIdTypeOperation(int idTypeOperation) {
     this.idTypeOperation = idTypeOperation;
   }
   
   public ParametrageDecideurDetailC getDetail() {
     return this.detail;
   }
   public void setDetail(ParametrageDecideurDetailC detail) {
     this.detail = detail;
   }
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
   
   public String getCodeEmpl() {
     return this.codeEmpl;
   }
   public void setCodeEmpl(String codeEmpl) {
     this.codeEmpl = codeEmpl;
   }
   public String getNomEmpl() {
     return this.nomEmpl;
   }
   public void setNomEmpl(String nomEmpl) {
     this.nomEmpl = nomEmpl;
   }
   public int getPosition() {
     return this.position;
   }
   public void setPosition(int position) {
     this.position = position;
   }
   
   public boolean isDisableMsg() {
	return disableMsg;
}
public void setDisableMsg(boolean disableMsg) {
	this.disableMsg = disableMsg;
}
@PostConstruct
   public void init() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     disableMsg=true;
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
       if (userFonction != null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), 
             Constante.Role.gestionAbsencePresence);
       }
       this.listEmploye = FactoryDAO.getInstance().getListEmployeSimple("");
     } 
   }
 
 
 
   
   public void changeTypeOperation(ValueChangeEvent event) {
     this.idTypeOperation = ((Integer)event.getNewValue()).intValue();
     if (this.idTypeOperation > 0) {
       
       setTypeOperation(this.idTypeOperation);
       this.selected = FichierBaseDAO.getInstance().getSignataire(this.idTypeOperation);
       setObject();
     } 
   }
 
 
   
   public void changePosition(ValueChangeEvent event) {
     this.position = ((Integer)event.getNewValue()).intValue();
   }
 
   
   public void removeDetail() {
     if (this.detail != null) {
       
       if (this.detail.getId() > 0)
         getListDeleted().add(this.detail); 
       getListDetail().remove(this.detail);
       
       clearDetail();
     } 
   }
   
   public void addDetail() {
     if (this.employe != null) {
       
       if (!isAdded(this.employe))
       {
         if (this.detail == null)
           this.detail = new ParametrageDecideurDetailC(); 
         this.detail.setIdEmploye(this.employe.getId());
         this.detail.setNomEmploye(this.nomEmpl);
         this.detail.setCodeEmploye(this.codeEmpl);
         this.detail.setPosition(this.position);
         if (!this.selectedDet) {
           
           getListDetail().add(this.detail);
         } else {
           
           getListDetail().remove(this.index);
           getListDetail().add(this.index, this.detail);
         } 
         clearDetail();
       }
       else
       {
         HelperC.afficherAttention("ATTENTION", "Ce signataire est déjà ajouté !");
       }
     
     } else {
       
       HelperC.afficherAttention("ATTENTION", "Il faut prréciser le signataire !");
     } 
   }
   private boolean isAdded(EmployeC empl) {
     boolean bl = false;
     
     for (ParametrageDecideurDetailC det : getListDetail()) {
       
       if (empl.getId() == det.getIdEmploye()) {
         
         bl = true;
         break;
       } 
     } 
     return bl;
   }
   private void clearDetail() {
     this.employe = null;
     this.detail = null;
     this.codeEmpl = "";
     this.nomEmpl = "";
     this.position = 0;
     this.selectedDet = false;
     this.index = 0;
   }
   
   public void setObject() {
	   disableMsg=true;
     if (this.selected != null) {
    	 disableMsg=false;
       setId(this.selected.getId());
       setListDetail(FichierBaseDAO.getInstance().getListParametrageDecideurDetail(this.selected.getId()));
       for (ParametrageDecideurDetailC det : getListDetail()) {
         
         this.employe = FactoryDAO.getInstance().getEmployeSimple(det.getIdEmploye());
         det.setCodeEmploye(this.employe.getCode());
         det.setNomEmploye(this.employe.getNomPrenom());
       } 
     } 
   }
 
   
   private void clear() {
     setId(0);
     disableMsg=true;
     setDirectionUniversite(null);
     this.idDirectionUniversite = 0; 
     this.idDirection = 0;
     this.idService = 0;
     this.idTypeOperation = 0;
     setTypeOperation(0);
     setDirection(null);
     setService(null);
     setSousService(null);
     setFonction(null);
     this.idfonction = 0;
     this.idPersonnel = 0;
     this.selected = null;
     getListDeleted().clear();
     getListDetail().clear();
   }
   
   public void initialiser() {
     clear();
   }
 
 
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     } else if (getId() != 0 && !this.droit.isModifier()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     } else if (getFonction() != null && getPersonnel() != null && this.idTypeOperation == 0) {
       HelperC.afficherAttention("ATTENTION", "Veillez selectionner les éléments entrant dans le paramétrage");
     }
     else if (getTypeOperation() == 0) {
       HelperC.afficherAttention("ATTENTION", "Veillez selectionner le type d'opération");
     }
     else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Saisie Parametrage décideur/signataire ");
       } else {
         hist.setOperation("Modification Parametrage décideur/signataire ");
       }  hist.setTable(Tables.getTableName(Tables.TableName.parametrageDecideurSignataire));
       setHistorique(hist);
 
       
       if (FichierBaseDAO.getInstance().insertUpdateParametrageDecideurSignataire(this)) {
         clear();
         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
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
       hist.setOperation("Suppression Parametrage décideur/signataire ");
       hist.setTable(Tables.getTableName(Tables.TableName.parametrageDecideurSignataire));
       hist.setIdLigne(getId());
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().deleteParametrageDecideurSignataire(this)) {
         clear();
         HelperC.afficherInformation("FELICITATION", "Succés de l'Opération");
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
       } 
     } 
   }
   
   public void onRowSelectEmploye(SelectEvent event) {
     this.employe = (EmployeC)event.getObject();
     if (this.employe != null) {
       
       this.codeEmpl = this.employe.getCode();
       this.nomEmpl = this.employe.getNomPrenom();
       PrimeFaces.current().executeScript("PF('dlgEmploye').hide();");
     }
     else {
       
       this.codeEmpl = "";
       this.nomEmpl = "";
     } 
   }
   
   public void searchEmployee() {
     if (!this.codeEmpl.equals("")) {
       this.employe = FactoryDAO.getInstance().getEmployeSimple(this.codeEmpl);
       if (this.employe != null) {
         this.codeEmpl = this.employe.getCode();
         this.nomEmpl = this.employe.getNomPrenom();
       } else {
         
         this.codeEmpl = "";
         this.nomEmpl = "";
       } 
     } else {
       this.codeEmpl = "";
       this.nomEmpl = "";
     } 
   }
 
   
   public void onSignataireSelected(SelectEvent event) {
     this.detail = (ParametrageDecideurDetailC)event.getObject();
     if (this.detail != null) {
       
       this.position = this.detail.getPosition();
       this.selectedDet = true;
       this.index = getListDetail().indexOf(this.detail);
       this.employe = FactoryDAO.getInstance().getEmployeSimple(this.detail.getIdEmploye());
       if (this.employe != null) {
         this.codeEmpl = this.employe.getCode();
         this.nomEmpl = this.employe.getNomPrenom();
       } else {
         
         this.codeEmpl = "";
         this.nomEmpl = "";
       } 
     } else {
       this.codeEmpl = "";
       this.nomEmpl = "";
     } 
   }
 
 
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (ParametrageDecideurSignataireC)event.getObject();
     if (this.selected != null) {
       setObject();
       PrimeFaces.current().executeScript("PF('recherchePrm').hide();");
     } 
   }
 }


