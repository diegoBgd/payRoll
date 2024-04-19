/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageAllocationFamillialeC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametrageAllocationFamillialeB extends ParametrageAllocationFamillialeC {
/*     */   private static final long serialVersionUID = 3945794877543851804L;
/*     */   private ParametrageAllocationFamillialeC allocationSelected;
/*     */   private List<ParametrageAllocationFamillialeC> listAllocations;
/*     */   private List<SelectItem> listStatuts;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*     */   private HttpSession session;
			private boolean disableMsg;
/*     */   Base userFonction;
/*     */   
/*     */   public ParametrageAllocationFamillialeB() {
/*  37 */     this.listStatuts = new ArrayList<SelectItem>();
/*  38 */     this.session = HelperC.getSession();
/*  39 */     chargerParametrages();
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/*  45 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  46 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  47 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  48 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  49 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/*  53 */         FacesContext context = FacesContext.getCurrentInstance();
/*  54 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/*  56 */       catch (IOException e) {
/*     */         
/*  58 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/*  62 */       this.listStatuts.add(new SelectItem(Integer.valueOf(0), ""));
/*  63 */       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.Conjoint)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.Conjoint))));
/*  64 */       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantLegitime)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantLegitime))));
/*  65 */       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantNaturelReconnu)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantNaturelReconnu))));
/*  66 */       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantOrphelinSousTutelle)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantOrphelinSousTutelle))));
/*  67 */       this.listStatuts.add(new SelectItem(Integer.valueOf(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantInaptePhysiquement)), Constante.getLibelleStatutPersonneACharge(Constante.getStatutPersonneCharge(Constante.StatutPersonneACharge.EnfantInaptePhysiquement))));
/*     */       
/*  69 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/*  70 */       if (this.userFonction != null)
/*     */       {
/*  72 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
/*     */       }
/*  74 */         chargerParametrages();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ParametrageAllocationFamillialeC getAllocationSelected() {
/*  80 */     return this.allocationSelected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllocationSelected(ParametrageAllocationFamillialeC allocationSelected) {
/*  85 */     this.allocationSelected = allocationSelected;
/*     */   }
/*     */   public List<ParametrageAllocationFamillialeC> getListAllocations() {
/*  88 */     return this.listAllocations;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListAllocations(List<ParametrageAllocationFamillialeC> listAllocations) {
/*  93 */     this.listAllocations = listAllocations;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListStatuts() {
/*  97 */     return this.listStatuts;
/*     */   }
/*     */   
/*     */   public void setListStatuts(List<SelectItem> listStatuts) {
/* 101 */     this.listStatuts = listStatuts;
/*     */   }
			
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   public void changeStatutPersonneCharge() {
/* 106 */     if (getStatutPersonnel() > 0) {
/*     */       
/* 108 */       this.allocationSelected = FichierBaseDAO.getInstance().getParametrageAllocationFamillialeParStatutPersonne(getStatutPersonnel());
/* 109 */       if (this.allocationSelected != null) {
/*     */         
/* 111 */         onRowSelectedParametrage();
/*     */       } 
				else
					setMontantAllocation(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 121 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 123 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 125 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 127 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 129 */     else if (getStatutPersonnel() == 0) {
/*     */       
/* 131 */       HelperC.afficherMessage("Information", "Veuillez pr�ciser le stattut du param�trage SVP!", FacesMessage.SEVERITY_ERROR);
/*     */     }
/* 133 */     else if (FichierBaseDAO.getInstance().insertUpdateParametrageAllocationFamilliale(this)) {
/*     */       
/* 135 */       chargerParametrages();
/* 136 */       initialiser();
/* 137 */       HelperC.afficherMessage("Information", "Op�ration effectu�e avec suc�s!", FacesMessage.SEVERITY_INFO);
/*     */     } else {
/*     */       
/* 140 */       HelperC.afficherMessage("Information", "Echec de l'op�ration!", FacesMessage.SEVERITY_ERROR);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerParametrages() {
/* 146 */     this.listAllocations = FichierBaseDAO.getInstance().getAllParametrageAllocationFamilliale();
/* 147 */     if (this.listAllocations.size() > 0)
/*     */     {
/*     */       
/* 150 */       for (ParametrageAllocationFamillialeC alloc : this.listAllocations)
/*     */       {
/* 152 */         alloc.setStatutPersonnelS(Constante.getLibelleStatutPersonneACharge(alloc.getStatutPersonnel()));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRowSelectedParametrage() {
/* 160 */     setId(this.allocationSelected.getId());
/* 161 */     setStatutPersonnel(this.allocationSelected.getStatutPersonnel());
/* 162 */     setMontantAllocation(this.allocationSelected.getMontantAllocation());
/* 163 */     setAgeMaximal(this.allocationSelected.getAgeMaximal());
/* 164 */     setAgeReportMaximal(this.allocationSelected.getAgeReportMaximal());
			  disableMsg=false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 169 */     if (getId() > 0)
/*     */     {
/* 171 */       if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.parametrageAllocationFamilliale))) {
/*     */         
/* 173 */         chargerParametrages();
/* 174 */         initialiser();
/*     */       } else {
/*     */         
/* 177 */         HelperC.afficherMessage("Information", "Echec de l'Op�ration!", FacesMessage.SEVERITY_ERROR);
/*     */       } 
/*     */     }
				else
					 HelperC.afficherDeleteMessage();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 184 */   
/* 186 */       setStatutPersonnel(0);
/*     */     
/* 188 */     setId(0);
/* 189 */     setAgeMaximal(0);
/* 190 */     setMontantAllocation(0.0D);
/* 191 */     setAgeReportMaximal(0);
		      disableMsg=true;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\ParametrageAllocationFamillialeB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */