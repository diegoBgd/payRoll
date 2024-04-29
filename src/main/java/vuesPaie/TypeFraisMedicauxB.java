 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import classesPaie.TypeFraisMedicauxC;
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
 public class TypeFraisMedicauxB
   extends TypeFraisMedicauxC
 {
   private static final long serialVersionUID = 2263001459723292564L;
   private TypeFraisMedicauxC typeFraisMedicaux;
   private List<TypeFraisMedicauxC> listTypeFraisMedicaux;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   public TypeFraisMedicauxC getTypeFraisMedicaux() {
     return this.typeFraisMedicaux;
   }
 
   
   public void setTypeFraisMedicaux(TypeFraisMedicauxC typeFraisMedicaux) {
     this.typeFraisMedicaux = typeFraisMedicaux;
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
   
   public List<TypeFraisMedicauxC> getListTypeFraisMedicaux() {
     return this.listTypeFraisMedicaux;
   }
 
   
   public void setListTypeFraisMedicaux(List<TypeFraisMedicauxC> listTypeFraisMedicaux) {
     this.listTypeFraisMedicaux = listTypeFraisMedicaux;
   }
 
   
   @PostConstruct
   private void init() {
     String codeExercice = (String)this.session.getAttribute("exercice");
     String codeOperateur = (String)this.session.getAttribute("operateur");
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
     this.listTypeFraisMedicaux = FichierBaseDAO.getInstance().getAllTypeFraisMedicaux();
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setDesignation("");
     setPourcentageS("");
     setMontantS("");
   }
 
   
   private void setObject() {
     if (this.typeFraisMedicaux != null) {
       
       setId(this.typeFraisMedicaux.getId());
       setCode(this.typeFraisMedicaux.getCode());
       setDesignation(this.typeFraisMedicaux.getDesignation());
       setPourcentage(this.typeFraisMedicaux.getPourcentage());
       setPourcentageS(HelperC.TraitementMontant.getMontantFormate(getPourcentage(), 2));
       setMontant(this.typeFraisMedicaux.getMontant());
       setMontantS(HelperC.TraitementMontant.getMontantFormate(getMontant(), 2));
     } 
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(true);
     } else {
       
       this.typeFraisMedicaux = FichierBaseDAO.getInstance().getTypeFraisMedicaux(getCode());
       if (this.typeFraisMedicaux == null) {
         
         clear(false);
       } else {
         
         setObject();
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.typeFraisMedicaux = (TypeFraisMedicauxC)event.getObject();
     if (this.typeFraisMedicaux != null)
     {
       setObject();
     }
   }
   
   public void changePourcentage() {
     try {
       setPourcentage(Integer.valueOf(getPourcentageS().replace("_", "").replace(" ", "").replace(",", ".").trim()).intValue());
     }
     catch (Exception e) {
       setPourcentage(0);
     } finally {
       
       setPourcentageS(HelperC.TraitementMontant.getMontantFormate(getPourcentage(), 2));
       setPourcentage(Integer.valueOf(getPourcentageS().replace("_", "").replace(" ", "").replace(",", ".").trim()).intValue());
     } 
   }
   
   public void changeMontant() {
     try {
       setMontant(Double.valueOf(getMontantS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
     }
     catch (Exception e) {
       setMontant(0.0D);
     } finally {
       
       setMontantS(HelperC.TraitementMontant.getMontantFormate(getMontant(), 2));
       setMontant(Double.valueOf(getMontantS().replace(" ", "").replace(",", ".").trim()).doubleValue());
     } 
   }
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Completez tous les champs");
     }
     else if (FichierBaseDAO.getInstance().getTypeFraisMedicaux(getCode(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Ce code existe déjé ");
     }
     else if (FichierBaseDAO.getInstance().getTypeFraisMedicauxByDesignation(getDesignation(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "Ce désignation existe déjé ");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création du type de frais médical " + getCode());
       } else {
         
         hist.setOperation("Modification du type de frais médical " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.typeFraisMedicaux));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateTypeFraisMedicaux(this)) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         charger();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherMessage("Information", "Précisez le type de frais Médicaux é supprimer");
     }
     else if (getId() > 0 && !this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }
     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeFraisMedicaux), getId())) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succès de l'opération");
       charger();
     } else {
       
       HelperC.afficherMessage("Désolé ", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
 
   

 }


