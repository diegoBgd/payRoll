 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import classesPaie.TypeNotationC;
 import java.io.IOException;
 import java.util.ArrayList;
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
 public class TypeNotationB
   extends TypeNotationC
 {
   private static final long serialVersionUID = 7974345831064783703L;
   private TypeNotationC selected;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private boolean disableMsg;

   private List<TypeNotationC> listNotation;
   
   public DroitC getDroit() {
     return this.droit;
   }
   Base userFonction; private DroitC droit;
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
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
 
   
   public List<TypeNotationC> getListNotation() {
     return this.listNotation;
   }
   
   public void setListNotation(List<TypeNotationC> listNotation) {
     this.listNotation = listNotation;
   }
 
   
   public static long getSerialversionuid() {
     return 749760639710887039L;
   }
 
   
   public TypeNotationC getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(TypeNotationC selected) {
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
       disableMsg=true;
       this.listNotation = new ArrayList<TypeNotationC>();
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
       }
       chargement();
     } 
   }
		
 
   
   private void chargement() {
     this.listNotation = FichierBaseDAO.getInstance().getAllTypeNotation();
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setDesignation("");
     setPourcentageMax(0.0D);
     setPourcentageMaxS("");
     setPourcentageMin(0.0D);
     setPourcentageMinS("");
     setTauxAugmentation(0.0D);
     setTauxAugmentationS("");
     this.selected = null;
			  disableMsg=true;
   }
 
   
   public void changeCode() {
     if (!getCode().trim().equals("")) {
       disableMsg=true;
       this.selected = FichierBaseDAO.getInstance().getTypeNotation(getCode());
       if (this.selected != null) {
         disableMsg=false;
         setObject();
       } else {
         
         clear(false);
       } 
     } 
   }
 
   
   private void setObject() {
     if (this.selected != null) {
       
       setCode(this.selected.getCode());
       setId(this.selected.getId());
       setDesignation(this.selected.getDesignation());
       setPourcentageMax(this.selected.getPourcentageMax());
       setPourcentageMin(this.selected.getPourcentageMin());
       setPourcentageMaxS(this.selected.getPourcentageMaxS());
       setPourcentageMinS(this.selected.getPourcentageMinS());
       setTauxAugmentation(this.selected.getTauxAugmentation());
       setTauxAugmentationS(this.selected.getTauxAugmentationS());
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (TypeNotationC)event.getObject();
     if (this.selected != null)
     {
       setObject();
     }
   }
 
   
   public void enregistrer() {
     if (getCode().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Précisez le code");
     }
     else if (getDesignation().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Précisez la désignation");
     }
     else if (getPourcentageMax() == 0.0D) {
       
       HelperC.afficherMessage("Information", "Précisez le taux maximum");
     }
     else if (FichierBaseDAO.getInstance().getTypeNotationNotCurrent(getCode(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Ce code du type notation existe déjé ");
     }
     else if (FichierBaseDAO.getInstance().getTypeNotationNotCurrents(getDesignation(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Le type notation de méme désignation existe déjé ");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du type de notation " + getCode());
       } else {
         
         hist.setOperation("Modification du type de notation" + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeNotation));
       setHistorique(hist);
       if (getId() == 0 && !this.droit.isCreer()) {
         
         HelperC.afficherMessage("Information", "Vous n'avez pas le droit de créer le type de notation !");
         return;
       } 
       if (getId() > 0 && !this.droit.isModifier()) {
         
         HelperC.afficherMessage("Information", "Vous n'avez pas le droit de modifier le type de notation !");
         return;
       } 
       if (FichierBaseDAO.getInstance().insertUpdateTypeNotation(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         chargement();
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     } else {
       
       if (!this.droit.isSupprimer()) {
         
         HelperC.afficherMessage("Information", "Vous n'avez pas le droit de supprimer le type de notation !");
         return;
       } 
       if (FichierBaseDAO.getInstance().deleteTypeNotation(this)) {
         
         clear(true);
         chargement();
         HelperC.afficherMessage("Information", "Succes de l'opération");
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de suppression ");
       } 
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 }
