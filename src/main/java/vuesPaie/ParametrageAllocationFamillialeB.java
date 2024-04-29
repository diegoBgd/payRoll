 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageAllocationFamillialeC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.application.FacesMessage;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;
 import persistencePaie.FichierBaseDAO;
 
 @ManagedBean
 @ViewScoped
 public class ParametrageAllocationFamillialeB extends ParametrageAllocationFamillialeC {
   private static final long serialVersionUID = 3945794877543851804L;
   private ParametrageAllocationFamillialeC allocationSelected;
   private List<ParametrageAllocationFamillialeC> listAllocations;
   private List<SelectItem> listStatuts;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session;
			private boolean disableMsg;
   Base userFonction;
   
   public ParametrageAllocationFamillialeB() {
     this.listStatuts = new ArrayList<SelectItem>();
     this.session = HelperC.getSession();
     chargerParametrages();
   }
 
   
   @PostConstruct
   public void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
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
       this.listStatuts.add(new SelectItem(Integer.valueOf(0), ""));
       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.Conjoint)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.Conjoint))));
       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantLegitime)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantLegitime))));
       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantNaturelReconnu)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantNaturelReconnu))));
       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantOrphelinSousTutelle)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantOrphelinSousTutelle))));
       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantInaptePhysiquement)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantInaptePhysiquement))));
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
       }
         chargerParametrages();
     } 
   }
 
   
   public ParametrageAllocationFamillialeC getAllocationSelected() {
     return this.allocationSelected;
   }
 
   
   public void setAllocationSelected(ParametrageAllocationFamillialeC allocationSelected) {
     this.allocationSelected = allocationSelected;
   }
   public List<ParametrageAllocationFamillialeC> getListAllocations() {
     return this.listAllocations;
   }
 
   
   public void setListAllocations(List<ParametrageAllocationFamillialeC> listAllocations) {
     this.listAllocations = listAllocations;
   }
   
   public List<SelectItem> getListStatuts() {
     return this.listStatuts;
   }
   
   public void setListStatuts(List<SelectItem> listStatuts) {
     this.listStatuts = listStatuts;
   }
			
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   public void changeStatutPersonneCharge() {
     if (getStatutPersonnel() > 0) {
       
       this.allocationSelected = FichierBaseDAO.getInstance().getParametrageAllocationFamillialeParStatutPersonne(getStatutPersonnel());
       if (this.allocationSelected != null) {
         
         onRowSelectedParametrage();
       } 
				else
					setMontantAllocation(0);
     } 
   }
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getStatutPersonnel() == 0) {
       
       HelperC.afficherMessage("Information", "Veuillez préciser le stattut du paramétrage SVP!", FacesMessage.SEVERITY_ERROR);
     }
     else if (FichierBaseDAO.getInstance().insertUpdateParametrageAllocationFamilliale(this)) {
       
       chargerParametrages();
       initialiser();
       HelperC.afficherMessage("Information", "Opération effectuée avec sucés!", FacesMessage.SEVERITY_INFO);
     } else {
       
       HelperC.afficherMessage("Information", "Echec de l'opération!", FacesMessage.SEVERITY_ERROR);
     } 
   }
 
   
   private void chargerParametrages() {
     this.listAllocations = FichierBaseDAO.getInstance().getAllParametrageAllocationFamilliale();
     if (this.listAllocations.size() > 0)
     {
       
       for (ParametrageAllocationFamillialeC alloc : this.listAllocations)
       {
         alloc.setStatutPersonnelS(Constante.getLibelleStatutPersonneACharge(alloc.getStatutPersonnel()));
       }
     }
   }
 
 
   
   public void onRowSelectedParametrage() {
     setId(this.allocationSelected.getId());
     setStatutPersonnel(this.allocationSelected.getStatutPersonnel());
     setMontantAllocation(this.allocationSelected.getMontantAllocation());
     setAgeMaximal(this.allocationSelected.getAgeMaximal());
     setAgeReportMaximal(this.allocationSelected.getAgeReportMaximal());
			  disableMsg=false;
   }
 
   
   public void supprimer() {
     if (getId() > 0)
     {
       if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.parametrageAllocationFamilliale))) {
         
         chargerParametrages();
         initialiser();
       } else {
         
         HelperC.afficherMessage("Information", "Echec de l'Opération!", FacesMessage.SEVERITY_ERROR);
       } 
     }
				else
					 HelperC.afficherDeleteMessage();
   }
 
   
   public void initialiser() {
   
       setStatutPersonnel(0);
     
     setId(0);
     setAgeMaximal(0);
     setMontantAllocation(0.0D);
     setAgeReportMaximal(0);
		      disableMsg=true;
   }
 }


