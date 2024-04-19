/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.CategoriePersonnelC;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class CategoriePersonnelB
/*     */   extends CategoriePersonnelC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 749760639710887039L;
/*     */   private int idPersonnel;
/*     */   private CategoriePersonnelC selected;
/*     */   private List<CategoriePersonnelC> categoriePersonnels;
/*     */   private OperateurC operateur;
/*  37 */   private List<SelectItem> personnels = new ArrayList<SelectItem>();
/*  38 */   private HttpSession session = HelperC.getSession();
/*     */   private boolean disableMsg;
/*     */   private ExerciceC exercice;
/*     */   
/*     */   public int getIdPersonnel() {
/*  43 */     return this.idPersonnel;
/*     */   }
/*     */   private DroitC droit; Base userFonction;
/*     */   
/*     */   public void setIdPersonnel(int idPersonnel) {
/*  48 */     this.idPersonnel = idPersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public CategoriePersonnelC getSelected() {
/*  53 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(CategoriePersonnelC selected) {
/*  58 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public List<CategoriePersonnelC> getCategoriePersonnels() {
/*  62 */     return this.categoriePersonnels;
/*     */   }
/*     */   
/*     */   public void setCategoriePersonnels(List<CategoriePersonnelC> categoriePersonnels) {
/*  66 */     this.categoriePersonnels = categoriePersonnels;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getPersonnels() {
/*  70 */     return this.personnels;
/*     */   }
/*     */   
/*     */   public void setPersonnels(List<SelectItem> personnels) {
/*  74 */     this.personnels = personnels;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  79 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  84 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  89 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  94 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  99 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 104 */     this.session = session;
/*     */   }
			 
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/* 110 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 111 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 112 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 113 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 114 */     if (this.exercice == null || this.operateur == null) {
/*     */ 
/*     */       
/*     */       try {
/* 118 */         FacesContext context = FacesContext.getCurrentInstance();
/* 119 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 121 */       catch (IOException e) {
/*     */         
/* 123 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/* 127 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 128 */       if (this.userFonction != null)
/*     */       {
/* 130 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
/* 132 */       this.personnels.add(new SelectItem(Integer.valueOf(0), ""));
/*     */       
/* 134 */       for (Base perso : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel)))
/*     */       {
/* 136 */         this.personnels.add(new SelectItem(Integer.valueOf(perso.getId()), perso.getDesignation()));
/*     */       }
				chargerCategorie();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 143 */     if (getCode() != null && !getCode().equals("") && 
/* 144 */       this.idPersonnel > 0) {
/*     */       
/* 146 */       this.selected = FichierBaseDAO.getInstance().getCategoriePersonnel(getCode(), this.idPersonnel);
/* 147 */       setObject();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void clear(boolean b) {
/* 152 */     if (b) {
/*     */       
/* 154 */       setPersonnel(null);
/* 155 */       this.idPersonnel = 0;
/*     */     } 
/* 157 */     setId(0);
/* 158 */     setCode("");
/* 159 */     setDesignation("");
/* 160 */     this.selected = null;
			  this.categoriePersonnels.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void changePersonnel(ValueChangeEvent event) {
/* 165 */     this.idPersonnel = ((Integer)event.getNewValue()).intValue();
/* 166 */     if (this.idPersonnel != 0)
/*     */     {
/* 168 */       setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.personnel)));
/*     */     }
/* 170 */     if (getPersonnel() != null) {
/*     */       
/* 172 */       chargerCategorie();
/*     */     } else {
/*     */       
/* 175 */       this.categoriePersonnels.clear();
/*     */     } 
/*     */   }
			private void chargerCategorie() {
				this.categoriePersonnels = FichierBaseDAO.getInstance().getListeCategoriePersonnel(getPersonnel());
			}
/*     */ 
/*     */   
/*     */   private void setObject() {
				disableMsg=true;
/* 181 */     if (this.selected != null) {
/*     */       disableMsg=false;
/* 183 */       setId(this.selected.getId());
/* 184 */       setPersonnel(this.selected.getPersonnel());
/* 185 */       if (getPersonnel() != null)
/*     */       {
/* 187 */         this.idPersonnel = getPersonnel().getId();
/*     */       }
/* 189 */       setCode(this.selected.getCode());
/* 190 */       setDesignation(this.selected.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected() {
/* 196 */     if (this.selected != null)
/*     */     {
/* 198 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 204 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 206 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 208 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 210 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 212 */     else if (getPersonnel() == null) {
/*     */       
/* 214 */       HelperC.afficherMessage("Information", "Pr�cisez le Personnel");
/*     */     }
/* 216 */     else if ((getPersonnel() != null && getCode().equals("")) || (getPersonnel() != null && getDesignation().equals(""))) {
/*     */       
/* 218 */       HelperC.afficherMessage("Information", "Le Code et la D�signation sont obligatoires!");
/*     */     }
/* 220 */     else if (FichierBaseDAO.getInstance().getCategoriePersonnelByDesignationNotCurrent(getPersonnel(), getDesignation(), getId()) != null) {
/*     */       
/* 222 */       HelperC.afficherMessage("Information", "L'�l�ment de ce Code existe d�j�!");
/*     */     }
/* 224 */     else if (FichierBaseDAO.getInstance().insertUpdateCategoriePersonnel(this)) {
/*     */       
/* 226 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
				chargerCategorie();
/* 228 */       clear(false);
/*     */     } else {
/*     */       
/* 231 */       HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 237 */     if (getId() == 0) {
/*     */       
/* 239 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 241 */     else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */       
/* 243 */       HelperC.afficherMessage("Information", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 245 */     else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.categoriePersonnel))) {
				chargerCategorie();
/* 248 */       clear(false);
/* 249 */       HelperC.afficherMessage("Information", "Succes de l'op�ration");
/*     */     } else {
/*     */       
/* 252 */       HelperC.afficherMessage("D�sol�", "Echec de suppression ");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 258 */     clear(true);
/*     */   }
/*     */ }

