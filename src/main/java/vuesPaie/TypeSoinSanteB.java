 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
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
 public class TypeSoinSanteB
   extends Base
 {
   private static final long serialVersionUID = -7740075500322127672L;
   private Base typeSoinSante;
   private List<Base> listeTypeSoinSante;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   public Base getTypeSoinSante() {
     return this.typeSoinSante;
   }
 
   
   public void setTypeSoinSante(Base typeSoinSante) {
     this.typeSoinSante = typeSoinSante;
   }
 
   
   public List<Base> getListeTypeSoinSante() {
     return this.listeTypeSoinSante;
   }
   
   public void setListeTypeSoinSante(List<Base> listeTypeSoinSante) {
     this.listeTypeSoinSante = listeTypeSoinSante;
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
 
   
   @PostConstruct
   private void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     if (codeOperateur == null || codeExercice == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
       }
       charger();
     } 
   }
 
   
   private void charger() {
     this.listeTypeSoinSante = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeSoinSante));
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.typeSoinSante = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeSoinSante));
       if (this.typeSoinSante == null) {
         
         clear(false);
       } else {
         
         affecter();
       } 
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     this.typeSoinSante = null;
     setDesignation("");
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.typeSoinSante = (Base)event.getObject();
     if (this.typeSoinSante != null)
     {
       affecter();
     }
   }
 
   
   private void affecter() {
     if (this.typeSoinSante != null) {
       
       setId(this.typeSoinSante.getId());
       setCode(this.typeSoinSante.getCode());
       setDesignation(this.typeSoinSante.getDesignation());
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   
   public void enregistrer() {
     try {
       if (getId() == 0 && !this.droit.isCreer()) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
       }
       else if (getId() > 0 && !this.droit.isModifier()) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
       }
       else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
         
         HelperC.afficherMessage("Information", "Code et Désignation obligatoires");
       } else {
         
         this.typeSoinSante = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeSoinSante));
         if (this.typeSoinSante == null) {
           
           Historique hist = new Historique();
           hist.setDateOperation(Calendar.getInstance().getTime());
           hist.setOperateur(this.operateur);
           if (getId() == 0) {
             
             hist.setOperation("Création du type de soin de santé " + getCode());
           } else {
             
             hist.setOperation("Modification du type de soin de santé " + getCode());
           } 
           hist.setTable(Tables.getTableName(Tables.TableName.typeSoinSante));
           setHistorique(hist);
           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeSoinSante))) {
             
             clear(true);
             HelperC.afficherMessage("Félicitaions", "Succès de l'Opération");
             charger();
           } else {
             
             HelperC.afficherMessage("Désolé", "Echec de l'Opération");
           } 
         } else {
           
           HelperC.afficherMessage("Information", "Ce personnel vient d'étre créee par un autre opérateur");
         }
       
       } 
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void supprimer() {
     try {
       if (getId() == 0) {
         
         HelperC.afficherMessage("Information", "Précisez le type de soin  é supprimer");
       }
       else if (getId() > 0 && !this.droit.isSupprimer()) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
       }
       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.typeSoinSante))) {
         
         clear(true);
         HelperC.afficherMessage("Félicitation", "Succès de l'Opération");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'Opération");
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   

 }


