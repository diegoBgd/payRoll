 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.io.Serializable;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.component.UIComponent;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import persistencePaie.FichierBaseDAO;

 @ManagedBean
 @ViewScoped
 public class BaseVue
   extends Base
   implements Serializable
 {
   private static final long serialVersionUID = 9223372036854775000L;
   private Base baseSelectionne;
   private List<Base> listBase;
   private String tableName;
   private OperateurC operateur;
   private DroitC droit;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private boolean userExist,disableMsg;
   Base userFonction;
   
   public DroitC getDroit() {
     return this.droit;
   }
 
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }
 
   
   public boolean isUserExist() {
     return this.userExist;
   }
 
   
   public void setUserExist(boolean userExist) {
     this.userExist = userExist;
   }

	 public boolean isDisableMsg() {
			return disableMsg;
			}

			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   
   @PostConstruct
   public void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     String exist = this.session.getAttribute("existUser").toString();
 				disableMsg=true;
 				
     if (exist != null)
     {
       if (exist.equals("true")) {
         
         this.userExist = true;
       } else {
         
         this.userExist = false;
       } 
     }
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     if (this.userExist) {
       
       if (this.operateur == null || this.exercice == null) {
 
         
         try {
           FacesContext context = FacesContext.getCurrentInstance();
           context.getExternalContext().redirect("/payRoll/login.jsf");
         }
         catch (IOException e) {
           
           e.printStackTrace();
         } 
       } else {
         
         searchTable();
         chargerBases();
       } 
     } else {
       
       searchTable();
       chargerBases();
     } 
   }

 
   
   public ExerciceC getExercice() {
     return this.exercice;
   }
 
   
   public void setExercice(ExerciceC exercice) {
     this.exercice = exercice;
   }
 
   
   public List<Base> getListBase() {
     return this.listBase;
   }
   
   public void setListBase(List<Base> listBase) {
     this.listBase = listBase;
   }
 
   
   public String getTableName() {
     return this.tableName;
   }
 
   
   public void setTableName(String tableName) {
     this.tableName = tableName;
   }
 
   
   public Base getBaseSelectionne() {
     return this.baseSelectionne;
   }
 
   
   public void setBaseSelectionne(Base baseSelectionne) {
     this.baseSelectionne = baseSelectionne;
   }
 
   
   public void changeCode() {
			
     if (!getCode().trim().equals("")) {
       
       this.baseSelectionne = FichierBaseDAO.getInstance().getBaseByCode(getCode(), this.tableName);
       if (this.baseSelectionne != null) {
         
         takeSelection();
       } else {
         
         clearAttributes(false);
       } 
     } else {
       
       clearAttributes(true);
     } 
   }
 
   
   public void insertUpdate() {
     if (this.userExist) {
       
       if (this.droit.isCreer()) {
         
         if (!getCode().trim().equals("") || !getDesignation().trim().equals("")) {
 
           
           if (FichierBaseDAO.getInstance().insertUpdateBase(this, this.tableName)) {
             
             clearAttributes(true);
             chargerBases();
             HelperC.afficherMessage("Félicitation!", "Succès de l'Opération");
           } else {
             
             HelperC.afficherAttention("Désolé!", "Echec de l'Opération");
           }
         
         } else {
           
           HelperC.afficherAttention("Attention", "Précisez la référence et le libellé");
         } 
       } else {
         
         HelperC.afficherAttention("Attention", "Vous n'avez pas droit é la création !");
       }
     
     } else if (!getCode().trim().equals("") || !getDesignation().trim().equals("")) {
       
       if (FichierBaseDAO.getInstance().insertUpdateBase(this, this.tableName)) {
         
         clearAttributes(true);
         chargerBases();
         HelperC.afficherMessage("Félicitation!", "Succès de l'Opération");
       } else {
         
         HelperC.afficherMessage("Désolé!", "Echec de l'Opération");
       } 
     } else {
       
       HelperC.afficherAttention("Attention", "Il faut préciser la référence");
     } 
   }
 
 
   
   private void chargerBases() {
			if(tableName!=null && !tableName.equals(""))
     this.listBase = FichierBaseDAO.getInstance().getAllBase(this.tableName);
   }
 
   
   private void searchTable() {
     UIComponent frm = null;
     FacesContext context = FacesContext.getCurrentInstance();
     frm = context.getViewRoot().findComponent("frmFonction");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.fonction);
     }
     frm = context.getViewRoot().findComponent("frmCampus");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.campus);
     }
     frm = context.getViewRoot().findComponent("frmDirUb");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.directionGnle);
     }
     
     frm = context.getViewRoot().findComponent("frmRoleMembreOrgane");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.roleMembreOrgane);
     }
     
     frm = context.getViewRoot().findComponent("frmLieu");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.lieuxTravail);
     }
     
     frm = context.getViewRoot().findComponent("frmTypContrat");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.typeContrat);
     }
     
     frm = context.getViewRoot().findComponent("frmPrsonl");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.personnel);
     }
     
     frm = context.getViewRoot().findComponent("frmElJrnl");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.elementJournal);
     }
     
			  frm = context.getViewRoot().findComponent("frmMotifRetr");
     if (frm != null)
     {
       this.tableName = Tables.getTableName(Tables.TableName.motifRetraite);
     }
     
     if (this.userExist) {
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
       }
     } 
   }
 
 
   
   private void clearAttributes(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setDesignation("");
     this.baseSelectionne = null;
			  disableMsg=true;
   }
 
   
   public void takeSelection() {
				disableMsg=true;
     if (this.baseSelectionne != null) {
       disableMsg=false;
       setId(this.baseSelectionne.getId());
       setCode(this.baseSelectionne.getCode());
       setDesignation(this.baseSelectionne.getDesignation());
     } 
   }
 
   
   public void delete() {
     if (this.droit!=null && !this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas droit à la Suppression");
     }
     else if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (FichierBaseDAO.getInstance().delete(getId(), this.tableName)) {
       
       clearAttributes(true);
       this.baseSelectionne = null;
       chargerBases();
       HelperC.afficherMessage("Félicitations", "Succès de l'Opération");
     } else {
       
       HelperC.afficherMessage("Désolé!", "Echec de l'Opération");
     } 
   }
 
   
   public void initialiserControles() {
     clearAttributes(true);
   }
 }


