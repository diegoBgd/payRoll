 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageGeneralC;
 import classesPaie.SaisieAbsenceC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;

 @ManagedBean
 @ViewScoped
 public class SaisieAbsenceB
   extends SaisieAbsenceC
 {
   private static final long serialVersionUID = 5659574157386814683L;
   private List<SaisieAbsenceC> allSaisieAbsence = new ArrayList<SaisieAbsenceC>();
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>(); 
			private SaisieAbsenceC selected;
			private EmployeC selection; 
			private String code; 
			private String nomEmploye; 
			private String codeEmployeRecherche;
   private HttpSession session = HelperC.getSession(); 
		    private String nomEmployeRecherche; 
		    private String prenomEmployeRecherche; 
		    private int idEmploye; 
		    private boolean disableMsg;
		    private OperateurC operateur; 
		    private ExerciceC exercice; 
		    private DroitC droit;
   Base userFonction;
   ParametrageGeneralC prm;
   
   @PostConstruct
   private void charger() {
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
       disableMsg=true;
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge);
       }
       setDateS(HelperC.changeDateFormate(getDate()));
       this.prm = FichierBaseDAO.getInstance().getParametrageGeneral();
       chargerAbscence();
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
   
   public List<SaisieAbsenceC> getAllSaisieAbsence() {
     return this.allSaisieAbsence;
   }
   
   public void setAllSaisieAbsence(List<SaisieAbsenceC> allSaisieAbsence) {
     this.allSaisieAbsence = allSaisieAbsence;
   }
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
 
   
   public SaisieAbsenceC getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(SaisieAbsenceC selected) {
     this.selected = selected;
   }
 
   
   public EmployeC getSelection() {
     return this.selection;
   }
 
   
   public void setSelection(EmployeC selection) {
     this.selection = selection;
   }
 
   
   public String getCode() {
     return this.code;
   }
 
   
   public void setCode(String code) {
     this.code = code;
   }
  
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   public int getIdEmploye() {
     return this.idEmploye;
   }
 
   
   public void setIdEmploye(int idEmploye) {
     this.idEmploye = idEmploye;
   }
 
   
   public String getNomEmploye() {
     return this.nomEmploye;
   }
 
   
   public void setNomEmploye(String nomEmploye) {
     this.nomEmploye = nomEmploye;
   }
 
   
   public void changeDate() {
     if (getDateS().replace("/", "").replace("_", "").trim().equals("")) {
       
       setDate(null);
     } else {
       
       setDate(HelperC.validerDate(getDateS()));
       if (getDate() == null) {
         
         setDateS("");
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         setDateS(HelperC.convertDate(getDate()));
       } 
     } 
   }
 
   
   private void chargerAbscence() {
     this.allSaisieAbsence = FactoryDAO.getInstance().getAllSaisieAbsence();
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setObservation("");
     }
              disableMsg=true;
     setEmploye(null);
     this.code = "";
     this.nomEmploye = "";
     setId(0);
     setDate(Calendar.getInstance().getTime());
     setDateS(HelperC.convertDate(Calendar.getInstance().getTime()));
     setDuree(0);
     setMoisPaie(0);
     setObservation("");
     setRetenuPaie(false);;
     this.selected = null;
     chargerAbscence();
   }
 
   
   public void compareHour() {
     if (this.prm != null && getDuree() > this.prm.getNbreHeureJour()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous ne depassez pas le nombre d'heures travaillé par jour !");
       setDuree(0);
     } 
   }
 
   
   public void findByCode() {
     if (this.code != null && !this.code.equals("")) {
       
       this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
       if (this.selection != null) {
         
         setEmploye(this.selection);
         this.nomEmploye = this.selection.getNomPrenom();
         searchAbscence();
       } 
     } 
   }
 
   
   public void chargerEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,false);
   }
 
   
   public void save() {
     setIdExercice(this.exercice.getId());
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getEmploye().getId() == 0 || getDate() == null || getDuree() == 0) {
       
       HelperC.afficherMessage("Information", "Veuillez remplir tous les champs obligatoires");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création de la saisie d'absence ");
       } else {
         
         hist.setOperation("Modification de la saisie d'absence ");
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.saisieabsence));
       setHistorique(hist);
      
       if (FactoryDAO.getInstance().insertUpdateSaisieAbsence(this)) {
         
         HelperC.afficherMessage("Félicitation", "Enregistrement avec Succès");
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé!!", "Echec d'enregistrement");
       } 
     } 
   }
 
   
   public void delete() {
     if (this.selected == null) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (this.selected != null && !this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     } 
     if (this.selected != null) {
       
       FactoryDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.saisieabsence));
       HelperC.afficherMessage("Félicitation", "Suppression avec Succès");
       clear(true);
     } 
   }
 
   
   private void setObject() {
     if (this.selected != null) {
       disableMsg=false;
       setId(this.selected.getId());
       if (this.selected.getDate() != null) {
         
         setDate(this.selected.getDate());
         setDateS(HelperC.convertDate(this.selected.getDate()));
       } else {
         
         setDate(Calendar.getInstance().getTime());
         setDateS(HelperC.convertDate(Calendar.getInstance().getTime()));
       } 
       setMoisPaie(this.selected.getMoisPaie());
       setDuree(this.selected.getDuree());
       setObservation(this.selected.getObservation());
       setAbsence(this.selected.getAbsence());
       setRetenuPaie(this.selected.isRetenuPaie());
       setMoisPaie(this.selected.getMoisPaie());
       this.selection = this.selected.getEmploye();
       setEmploye(this.selected.getEmploye());
       setObject1();
     } 
   }
 
   
   public void searchAbscence() {
     if (getEmploye() != null && getDate() != null) {
       
       this.selected = FactoryDAO.getInstance().getSaisieAbsenceParEmployeEtDate(getEmploye().getId(), getDate());
       if (this.selected != null)
       {
         setObject();
       }
     } 
   }
 
   
   private void setObject1() {
     if (this.selection != null) {
       
       setEmploye(this.selection);
       if (getEmploye() != null) {
         
         this.code = getEmploye().getCode();
         this.nomEmploye = this.selection.getNomPrenom();
         setEmploye(this.selection);
       } else {
         
         this.code = "";
         this.nomEmploye = "";
       } 
     } 
   }
 
   
   public void onRowselected1(SelectEvent event) {
     this.selection = (EmployeC)event.getObject();
     setObject1();
     PrimeFaces.current().executeScript("PF('dlg').hide();");
   }
 
 public void initialiser()
			{
				clear(true);
			}
   
   public void onRowselected() {
     setObject();
   }
 }

