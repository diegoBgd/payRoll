 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import classesPaie.TypeCreditC;
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
 public class TypeCreditB
   extends TypeCreditC
 {
   private static final long serialVersionUID = 7663214010747562208L;
   private String tauxMinimumS;
   private String tauxMaximumS;
   private TypeCreditC typeCredit;
   private List<TypeCreditC> typeCredits;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private DroitC droit;
   Base userFonction;
   
   public TypeCreditC getTypeCredit() {
     return this.typeCredit;
   }
 
   
   public void setTypeCredit(TypeCreditC typeCredit) {
     this.typeCredit = typeCredit;
   }
   
   public List<TypeCreditC> getTypeCredits() {
     return this.typeCredits;
   }
   
   public void setTypeCredits(List<TypeCreditC> typeCredits) {
     this.typeCredits = typeCredits;
   }
 
   
   public String getTauxMinimumS() {
     return this.tauxMinimumS;
   }
 
   
   public void setTauxMinimumS(String tauxMinimumS) {
     this.tauxMinimumS = tauxMinimumS;
   }
 
   
   public String getTauxMaximumS() {
     return this.tauxMaximumS;
   }
 
   
   public void setTauxMaximumS(String tauxMaximumS) {
     this.tauxMaximumS = tauxMaximumS;
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
   private void init() {
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
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.creditAvance);
       }
       this.typeCredits = FichierBaseDAO.getInstance().getAllTypeCredit();
     } 
   }
 
   
   public void changeTauxMinimum() {
     try {
       setTauxMinimum(Double.valueOf(this.tauxMinimumS.replace("-", "")
             .replace(" ", "").replace(",", ".").trim()).doubleValue());
     } catch (Exception e) {
       setTauxMinimum(0.0D);
     } finally {
       
       this.tauxMinimumS = HelperC.TraitementMontant.getMontantFormate(
           getTauxMinimum(), 0);
       setTauxMinimum(Double.valueOf(this.tauxMinimumS.replace(" ", "")
             .replace(",", ".").trim()).doubleValue());
     } 
   }
   
   public void changeTauxMaximun() {
     try {
       setTauxMaximum(Double.valueOf(this.tauxMaximumS.replace("-", "")
             .replace(" ", "").replace(",", ".").trim()).doubleValue());
     } catch (Exception e) {
       setTauxMaximum(0.0D);
     } finally {
       
       this.tauxMaximumS = HelperC.TraitementMontant.getMontantFormate(
           getTauxMaximum(), 0);
       setTauxMaximum(Double.valueOf(this.tauxMaximumS.replace(" ", "")
             .replace(",", ".").trim()).doubleValue());
     } 
   }
 
   
   private void setObjet() {
     if (this.typeCredit != null) {
       
       setId(this.typeCredit.getId());
       setCode(this.typeCredit.getCode());
       setLibelle(this.typeCredit.getLibelle());
       setTerme(this.typeCredit.getTerme());
       setTauxMinimum(this.typeCredit.getTauxMinimum());
       this.tauxMinimumS = HelperC.TraitementMontant.getMontantFormate(getTauxMinimum(), 0);
       setTauxMaximum(this.typeCredit.getTauxMaximum());
       this.tauxMaximumS = HelperC.TraitementMontant.getMontantFormate(getTauxMaximum(), 0);
       setCentraliserEcritureEnComptabilite(this.typeCredit.isCentraliserEcritureEnComptabilite());
       setAffecterDeuxiemeCompteCredit(this.typeCredit.isAffecterDeuxiemeCompteCredit());
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setLibelle("");
     setTerme("");
     setTauxMinimum(0.0D);
     setTauxMaximum(0.0D);
     this.tauxMinimumS = "";
     this.tauxMaximumS = "";
     setCentraliserEcritureEnComptabilite(false);
     setAffecterDeuxiemeCompteCredit(false);
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(false);
     } else {
       
       this.typeCredit = FichierBaseDAO.getInstance().getTypeCredit(getCode());
     } 
     if (this.typeCredit == null) {
       
       clear(false);
     } else {
       
       setObjet();
     } 
   }
 
   
   public void onRowSelect(SelectEvent event) {
     this.typeCredit = (TypeCreditC)event.getObject();
     if (this.typeCredit != null)
     {
       setObjet();
     }
   }
 
   
   public void enregistrer() {
     if (getCode().trim().equals("") || getLibelle().trim().equals("") || getTerme().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Completez tous les champs");
     }
     else if (FichierBaseDAO.getInstance().getTypeCreditNotCurrent(getCode(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Ce code existe déjé");
     }
     else if (FichierBaseDAO.getInstance().getTypeCreditByLibelle(getLibelle(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Ce libellé existe déjé");
     } else {
       
       if (getId() == 0 && !this.droit.isCreer()) {
         
         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer ");
         return;
       } 
       if (getId() > 0 && !this.droit.isModifier()) {
         
         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier ");
         return;
       } 
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du type crédit " + getCode());
       } else {
         
         hist.setOperation("Modification du type crédit  " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeCredit));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateTypeCredit(this)) {
         
         HelperC.afficherMessage("Information", "Sucés de l'opération");
         clear(true);
         this.typeCredits = FichierBaseDAO.getInstance().getAllTypeCredit();
       } else {
         
         HelperC.afficherMessage("Information", "Echec de l'opération ");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
     } else {
       
       if (!this.droit.isSupprimer()) {
         
         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
         return;
       } 
       if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeCredit), getId())) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Sucès de l'opération");
         this.typeCredits = FichierBaseDAO.getInstance().getAllTypeCredit();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de suppression");
       } 
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
 }


