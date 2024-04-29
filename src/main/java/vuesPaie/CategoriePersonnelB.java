 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.CategoriePersonnelC;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.io.Serializable;
 import java.util.ArrayList;
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
 public class CategoriePersonnelB
   extends CategoriePersonnelC
   implements Serializable
 {
   private static final long serialVersionUID = 749760639710887039L;
   private int idPersonnel;
   private CategoriePersonnelC selected;
   private List<CategoriePersonnelC> categoriePersonnels;
   private OperateurC operateur;
   private List<SelectItem> personnels = new ArrayList<SelectItem>();
   private HttpSession session = HelperC.getSession();
   private boolean disableMsg;
   private ExerciceC exercice;
   
   public int getIdPersonnel() {
     return this.idPersonnel;
   }
   private DroitC droit; Base userFonction;
   
   public void setIdPersonnel(int idPersonnel) {
     this.idPersonnel = idPersonnel;
   }
 
   
   public CategoriePersonnelC getSelected() {
     return this.selected;
   }
 
   
   public void setSelected(CategoriePersonnelC selected) {
     this.selected = selected;
   }
   
   public List<CategoriePersonnelC> getCategoriePersonnels() {
     return this.categoriePersonnels;
   }
   
   public void setCategoriePersonnels(List<CategoriePersonnelC> categoriePersonnels) {
     this.categoriePersonnels = categoriePersonnels;
   }
   
   public List<SelectItem> getPersonnels() {
     return this.personnels;
   }
   
   public void setPersonnels(List<SelectItem> personnels) {
     this.personnels = personnels;
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
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
       }
       this.personnels.add(new SelectItem(Integer.valueOf(0), ""));
       
       for (Base perso : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel)))
       {
         this.personnels.add(new SelectItem(Integer.valueOf(perso.getId()), perso.getDesignation()));
       }
				chargerCategorie();
     } 
   }
 
   
   public void changeCode() {
     if (getCode() != null && !getCode().equals("") && 
       this.idPersonnel > 0) {
       
       this.selected = FichierBaseDAO.getInstance().getCategoriePersonnel(getCode(), this.idPersonnel);
       setObject();
     } 
   }
   
   private void clear(boolean b) {
     if (b) {
       
       setPersonnel(null);
       this.idPersonnel = 0;
     } 
     setId(0);
     setCode("");
     setDesignation("");
     this.selected = null;
			  this.categoriePersonnels.clear();
   }
 
   
   public void changePersonnel(ValueChangeEvent event) {
     this.idPersonnel = ((Integer)event.getNewValue()).intValue();
     if (this.idPersonnel != 0)
     {
       setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.personnel)));
     }
     if (getPersonnel() != null) {
       
       chargerCategorie();
     } else {
       
       this.categoriePersonnels.clear();
     } 
   }
			private void chargerCategorie() {
				this.categoriePersonnels = FichierBaseDAO.getInstance().getListeCategoriePersonnel(getPersonnel());
			}
 
   
   private void setObject() {
				disableMsg=true;
     if (this.selected != null) {
       disableMsg=false;
       setId(this.selected.getId());
       setPersonnel(this.selected.getPersonnel());
       if (getPersonnel() != null)
       {
         this.idPersonnel = getPersonnel().getId();
       }
       setCode(this.selected.getCode());
       setDesignation(this.selected.getDesignation());
     } 
   }
 
   
   public void onRowSelected() {
     if (this.selected != null)
     {
       setObject();
     }
   }
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getPersonnel() == null) {
       
       HelperC.afficherMessage("Information", "Précisez le Personnel");
     }
     else if ((getPersonnel() != null && getCode().equals("")) || (getPersonnel() != null && getDesignation().equals(""))) {
       
       HelperC.afficherMessage("Information", "Le Code et la Désignation sont obligatoires!");
     }
     else if (FichierBaseDAO.getInstance().getCategoriePersonnelByDesignationNotCurrent(getPersonnel(), getDesignation(), getId()) != null) {
       
       HelperC.afficherMessage("Information", "L'élément de ce Code existe déjé!");
     }
     else if (FichierBaseDAO.getInstance().insertUpdateCategoriePersonnel(this)) {
       
       HelperC.afficherMessage("Information", "Succès de l'opération ");
				chargerCategorie();
       clear(false);
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de l'opération ");
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (getId() > 0 && !this.droit.isSupprimer()) {
       
       HelperC.afficherMessage("Information", "Vous n'avez pas le droit de supprimer");
     }
     else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.categoriePersonnel))) {
				chargerCategorie();
       clear(false);
       HelperC.afficherMessage("Information", "Succes de l'opération");
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression ");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 }

