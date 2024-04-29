 package vuesPaie;
 
 import classesPaie.CritereEvaluationC;
 import classesPaie.DetailCritereEvaluationC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Iterator;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;
 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class CritereEvaluationB
   extends CritereEvaluationC
 {
   private static final long serialVersionUID = 5957551831356483932L;
   private List<CritereEvaluationC> allCritereEvaluation = new ArrayList<CritereEvaluationC>();
   private List<SelectItem> listTypeCritere = new ArrayList<SelectItem>();
   private CritereEvaluationC selected = null;
   int index;
   private DetailCritereEvaluationC selectedDetail;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private boolean disableMsg;
   private double noteDetail;
   
   private String designationDetail;
   
   private String noteDetailS;
   private int idType;
   boolean seleced = false;
   
   public int getIdType() {
     return this.idType;
   }
   
   public void setIdType(int idType) {
     this.idType = idType;
   }
   
   public List<SelectItem> getListTypeCritere() {
     return this.listTypeCritere;
   }
   
   public void setListTypeCritere(List<SelectItem> listTypeCritere) {
     this.listTypeCritere = listTypeCritere;
   }
   
   public DetailCritereEvaluationC getSelectedDetail() {
     return this.selectedDetail;
   }
   
   public void setSelectedDetail(DetailCritereEvaluationC selectedDetail) {
     this.selectedDetail = selectedDetail;
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
   
   public double getNoteDetail() {
     return this.noteDetail;
   }
   
   public void setNoteDetail(double noteDetail) {
     this.noteDetail = noteDetail;
   }
   
   public String getDesignationDetail() {
     return this.designationDetail;
   }
   
   public void setDesignationDetail(String designationDetail) {
     this.designationDetail = designationDetail;
   }
   
   public String getNoteDetailS() {
     return this.noteDetailS;
   }
   
   public void setNoteDetailS(String noteDetailS) {
     this.noteDetailS = noteDetailS;
   }
   
   public List<CritereEvaluationC> getAllCritereEvaluation() {
     return this.allCritereEvaluation;
   }
 
   
   public void setAllCritereEvaluation(List<CritereEvaluationC> allCritereEvaluation) {
     this.allCritereEvaluation = allCritereEvaluation;
   }
   
   public CritereEvaluationC getSelected() {
     return this.selected;
   }
   
   public void setSelected(CritereEvaluationC selected) {
     this.selected = selected;
   }


			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   @PostConstruct
   private void charger() {
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
				disableMsg=true;
       chargementCritere();
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       clear(true);
     } else {
       this.selected = FichierBaseDAO.getInstance().getCritereEvaluation(
           getCode());
       if (this.selected == null) {
         clear(false);
       } else {
         setObject();
     
         Iterator<DetailCritereEvaluationC> iterator = FichierBaseDAO.getInstance().getListeDetailCritereEvaluation(this.selected).iterator(); while (iterator.hasNext()) { DetailCritereEvaluationC detail = iterator.next();
         
           getListDetailCritere().add(detail);
           calculNoteGeneral(); }
       
       } 
     } 
   }
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
				} 
			  disableMsg=false;
     setId(0);
     setDesignation("");
     setNoteGeneral(0.0D);
     setNoteGeneralS("");
     this.designationDetail = "";
     this.noteDetail = 0.0D;
     this.noteDetailS = "";
     this.selected = null;
     this.idType = 0;
     getListDetailCritere().clear();
   }
 
   
   private void setObject() {
     if (this.selected != null) {
       setId(this.selected.getId());
       setCode(this.selected.getCode());
       setDesignation(this.selected.getDesignation());
       setNoteGeneral(this.selected.getNoteGeneral());
       setNoteGeneralS(this.selected.getNoteGeneralS());
				disableMsg=false;
     } 
   }
 
 
   
   private void setDetail() {
     this.index = getListDetailCritere().indexOf(this.selectedDetail);
     this.designationDetail = this.selectedDetail.getDesignation();
     this.noteDetail = this.selectedDetail.getNote();
     this.seleced = true;
   }
 
   
   private void clearDetail() {
     this.designationDetail = "";
     this.noteDetail = 0.0D;
     this.seleced = false;
     this.index = 0;
     this.selectedDetail = null;
   }
   
   public void onRowSelected(SelectEvent event) {
     clear(true);
     this.selected = (CritereEvaluationC)event.getObject();
     if (this.selected != null) {
				disableMsg=true;
       setObject();
       setListDetailCritere(FichierBaseDAO.getInstance()
           .getListeDetailCritereEvaluation(this.selected));
       for (DetailCritereEvaluationC detail : getListDetailCritere()) {
         
         detail.setIndex(getListDetailCritere().indexOf(detail));
         calculNoteGeneral();
       } 
       
       PrimeFaces.current().executeScript("PF('dlgPnl').hide();");
     } 
   }
 
   
   public void onRowSelectedDetail(SelectEvent event) {
     this.selectedDetail = (DetailCritereEvaluationC)event.getObject();
     if (this.selectedDetail != null) {
       this.index = getListDetailCritere().indexOf(this.selectedDetail);
       setDetail();
     } 
   }
   
   public void ajouter() {
     if (this.noteDetail > 0.0D) {
       if (this.selectedDetail == null) {
         this.selectedDetail = new DetailCritereEvaluationC();
         this.selectedDetail.setIndex(getListDetailCritere().size());
       } 
       this.selectedDetail.setDesignation(this.designationDetail);
       this.selectedDetail.setNote(this.noteDetail);
       
       if (!this.seleced) {
         getListDetailCritere().add(this.selectedDetail);
       } else {
         this.selectedDetail.setIndex(this.index);
         getListDetailCritere().remove(this.index);
         getListDetailCritere().add(this.index, this.selectedDetail);
       } 
       calculNoteGeneral();
       
       clearDetail();
     } else {
       HelperC.afficherMessage("Information", 
           "La note doit étre supérieure é zéro! ");
     } 
   }
   
   private void calculNoteGeneral() {
     double noteGen = 0.0D;
     for (DetailCritereEvaluationC deta : getListDetailCritere()) {
       noteGen += deta.getNote();
     }
     setNoteGeneral(noteGen);
     setNoteGeneralS(HelperC.TraitementMontant.getMontantFormate(
           getNoteGeneral(), 0));
   }
   
   public void enlever() {
     if (getListDetailCritere().size() > 0 && 
       this.selectedDetail != null) {
       
       getListDetailCritereDeleted().add(this.selectedDetail);
       getListDetailCritere().remove(this.selectedDetail);
       
       calculNoteGeneral();
     } 
     
     clearDetail();
   }
   
   public void enregistrer() {
     if (getCode().trim().equals("") || 
       getDesignation().trim().equals("") || 
       getNoteGeneral() == 0.0D) {
       HelperC.afficherMessage("Information", 
           "Completez tous les champs nécessaires");
     } else if (FichierBaseDAO.getInstance().getCritereEvaluation(
         getCode(), getId()) != null) {
       HelperC.afficherMessage("Information", 
           "Ce code de critere existe déjé ");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création d'un critére d'évaluation " + 
             getCode());
       } else {
         hist.setOperation("Modification d'un critére d'évaluation " + 
             getCode());
       }  hist.setTable(Tables.getTableName(Tables.TableName.critereEvaluation));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance()
         .insertUpdateCritereEvaluation(this)) {
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération");
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération");
       } 
     } 
   }
   
   private void chargementCritere() {
     this.allCritereEvaluation = FichierBaseDAO.getInstance()
       .getAllCritereEvaluation();
   }
   
   public void supprimer() {
     if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création d'un critére d'évaluation " + 
             getCode());
       } else {
         hist.setOperation("Modification d'un critére d'évaluation " + 
             getCode());
       }  hist.setTable(Tables.getTableName(Tables.TableName.critereEvaluation));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().deleteCritereEvaluation(this)) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         chargementCritere();
       } else {
         HelperC.afficherMessage("Désolé", "Echec de suppression");
       } 
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 }


