 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.RoleC;
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
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class RoleB
   extends RoleC
 {
   private static final long serialVersionUID = 2643530410296697040L;
   private OperateurC operateur;
   private DroitC droit;
   private DroitC droit2;
   private HttpSession session = HelperC.getSession();
   private List<SelectItem> listeFonction = new ArrayList<SelectItem>();
   private List<DroitC> listeDroit = new ArrayList<DroitC>(); 
		   private int idFonction;
   private boolean userExist;
   private boolean selectAll;
   Base userFonction;
   ExerciceC exercice;
   
   public boolean isSelectAll() {
     return this.selectAll;
   }
 
   
   public void setSelectAll(boolean selectAll) {
     this.selectAll = selectAll;
   }
 
   
   public int getIdFonction() {
     return this.idFonction;
   }
 
   
   public void setIdFonction(int idFonction) {
     this.idFonction = idFonction;
   }
 
   
   public boolean isUserExist() {
     return this.userExist;
   }
 
   
   public void setUserExist(boolean userExist) {
     this.userExist = userExist;
   }
   
   public List<SelectItem> getListeFonction() {
     return this.listeFonction;
   }
   
   public void setListeFonction(List<SelectItem> listeFonction) {
     this.listeFonction = listeFonction;
   }
   
   public List<DroitC> getListeDroit() {
     return this.listeDroit;
   }
   
   public void setListeDroit(List<DroitC> listeDroit) {
     this.listeDroit = listeDroit;
   }
 
   
   @PostConstruct
   public void init() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     String exist = "false";
     if (this.session.getAttribute("existUser") != null)
     {
       exist = this.session.getAttribute("existUser").toString();
     }
     if (exist != null)
     {
       this.userExist = Boolean.getBoolean(exist);
     }
     if (this.userExist)
     {
       if (this.operateur == null || this.exercice == null) {
 
         
         try {
           FacesContext context = FacesContext.getCurrentInstance();
           context.getExternalContext().redirect("/payRoll/login.xhtml");
         }
         catch (IOException e) {
           
           e.printStackTrace();
         } 
       } else {
         
         this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
         this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
         if (this.userFonction != null)
         {
           this.droit2 = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.superUtilisateur);
         }
       } 
     }
     chargerDroit();
     this.listeFonction.add(new SelectItem(Integer.valueOf(0), " "));
     
     for (Base f : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction)))
     {
       this.listeFonction.add(new SelectItem(Integer.valueOf(f.getId()), String.valueOf(f.getCode()) + " || " + f.getDesignation()));
     }
   }
 
 
   
   public void changeFonction(ValueChangeEvent event) {
				
     this.idFonction = ((Integer)event.getNewValue()).intValue();
				for (DroitC drt : getDetails()) {
 
         
         drt.setCreer(false);
         drt.setAfficher(false);
         drt.setSupprimer(false);
         drt.setModifier(false);
       } 
     setFonction(FichierBaseDAO.getInstance().getBaseById(this.idFonction, Tables.getTableName(Tables.TableName.fonction)));
     if (getFonction() != null) {
       
       this.listeDroit = FichierBaseDAO.getInstance().getListeDroit(getFonction());
       affecter();
     } else {
       
       clear(false);
     } 
   }
 
 
   
   private void chargerDroit() {
     for (int i = 1; i <= (Constante.Role.values()).length; i++) {
       
       this.droit = new DroitC();
       this.droit.setNumero(i);
       this.droit.setRol(Constante.getRole(i));
       this.droit.setLibelle(Constante.getLibelleRole(this.droit.getRol()));
       this.droit.setCreer(false);
       this.droit.setModifier(false);
       this.droit.setSupprimer(false);
       this.droit.setAfficher(false);
       getDetails().add(this.droit);
       this.droit.setDesactiveModifier(false);
       this.droit.setDesactiveSupprimer(false);
     } 
   }
 
 
 
 
   
   public void selectAll() {
     if (this.selectAll) {
 
       
       for (DroitC drt : getDetails())
       {
         
         drt.setCreer(true);
         drt.setAfficher(true);
         drt.setSupprimer(true);
         drt.setModifier(true);
       }
     
     }
     else {
       
       for (DroitC drt : getDetails()) {
 
         
         drt.setCreer(false);
         drt.setAfficher(false);
         drt.setSupprimer(false);
         drt.setModifier(false);
       } 
     } 
   }
 
 
   
   private void affecter() {
     if (this.listeDroit.size() > 0) {
       getDetails().clear();
       chargerDroit();
       for (DroitC d : this.listeDroit) {
         for (DroitC det : getDetails()) {
           if (d.getRol() == det.getRol()) {
             det.setId(d.getId());
             det.setCreer(d.isCreer());
             det.setModifier(d.isModifier());
             det.setSupprimer(d.isSupprimer());
             det.setAfficher(d.isAfficher());
           } 
         } 
       } 
     } 
   }
 
   
   public void saveRole() {
     if (this.userExist) {
       
       if (this.droit2 != null) {
         
         if (isModification() && !this.droit2.isModifier()) {
           
           HelperC.afficherInformation("Information", "Vous n'avez pas le droit de Modifier");
         }
         else if (!isModification() && !this.droit2.isCreer()) {
           
           HelperC.afficherInformation("Information", "Vous n'avez pas le droit de Créer");
         }
         else if (getFonction() == null) {
           
           HelperC.afficherInformation("Information", "Précisez la fonction");
         } else {
           
           Historique hist = new Historique();
           hist.setDateOperation(Calendar.getInstance().getTime());
           hist.setOperateur(this.operateur);
           if (!isModification()) {
             
             hist.setOperation("Création du réle " + getFonction().getCode());
           } else {
             
             hist.setOperation("Modification du réle " + getFonction().getCode());
           } 
           hist.setTable(Tables.getTableName(Tables.TableName.fonction));
           setHistorique(hist);
           if (FichierBaseDAO.getInstance().insertUpdateDroit(this)) {
             
             HelperC.afficherInformation("FELICITATION", "Succès de l'opération");
             initialiser();
           } else {
             
             HelperC.afficherErreur("Désolé!", "Echec de l'opération");
           } 
         } 
       } else {
         
         Historique hist = new Historique();
         hist.setDateOperation(Calendar.getInstance().getTime());
         hist.setOperateur(this.operateur);
         if (!isModification()) {
           
           hist.setOperation("Création du réle " + getFonction().getCode());
         } else {
           
           hist.setOperation("Modification du réle " + getFonction().getCode());
         } 
         hist.setTable(Tables.getTableName(Tables.TableName.fonction));
         setHistorique(hist);
         if (FichierBaseDAO.getInstance().insertUpdateDroit(this)) {
           
           HelperC.afficherInformation("FELICITATION", "Succès de l'opération");
           initialiser();
         } else {
           
           HelperC.afficherErreur("Désolé!", "Echec de l'opération");
         } 
       } 
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
      
				if(this.getFonction()!=null)
				{
				if (!isModification()) {

					hist.setOperation("Création du réle " + getFonction().getCode());
				} else {

					hist.setOperation("Modification du réle " + getFonction().getCode());
				}
				hist.setTable(Tables.getTableName(Tables.TableName.fonction));
				setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateDroit(this)) {
         
         HelperC.afficherInformation("FELICITATION", "Succès de l'opération");
         initialiser();
       } else {
         
         HelperC.afficherErreur("Désolé!", "Echec de l'opération");
       } 
				}
				else
					  HelperC.afficherAttention("Attention", "Il faut préciser la fonction");
     } 
   }
   
   private boolean isModification() {
     boolean b = false;
     Iterator<DroitC> iterator = getDetails().iterator(); if (iterator.hasNext()) { DroitC det = iterator.next();
       if (det.getId() > 0) {
         b = true;
       } else {
         
         return b;
       }  }
      return b;
   }
 
 
   
   private void clear(boolean b) {
     if (b) {
       
       setFonction(null);
       this.idFonction = 0;
     } 
     getDetails().clear();
     chargerDroit();
   }
 
   
   public void initialiser() {
     clear(true);
   }
 }


