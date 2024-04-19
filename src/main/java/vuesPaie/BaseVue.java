/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.component.UIComponent;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class BaseVue
/*     */   extends Base
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 9223372036854775000L;
/*     */   private Base baseSelectionne;
/*     */   private List<Base> listBase;
/*     */   private String tableName;
/*     */   private OperateurC operateur;
/*     */   private DroitC droit;
/*     */   private ExerciceC exercice;
/*  36 */   private HttpSession session = HelperC.getSession();
/*     */   private boolean userExist,disableMsg;
/*     */   Base userFonction;
/*     */   
/*     */   public DroitC getDroit() {
/*  41 */     return this.droit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDroit(DroitC droit) {
/*  46 */     this.droit = droit;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUserExist() {
/*  51 */     return this.userExist;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserExist(boolean userExist) {
/*  56 */     this.userExist = userExist;
/*     */   }

	/*     */ public boolean isDisableMsg() {
			return disableMsg;
			}

			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/*  62 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  63 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  64 */     String exist = this.session.getAttribute("existUser").toString();
 				disableMsg=true;
 				
/*  65 */     if (exist != null)
/*     */     {
/*  67 */       if (exist.equals("true")) {
/*     */         
/*  69 */         this.userExist = true;
/*     */       } else {
/*     */         
/*  72 */         this.userExist = false;
/*     */       } 
/*     */     }
/*  75 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  76 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  77 */     if (this.userExist) {
/*     */       
/*  79 */       if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */         
/*     */         try {
/*  83 */           FacesContext context = FacesContext.getCurrentInstance();
/*  84 */           context.getExternalContext().redirect("/payRoll/login.jsf");
/*     */         }
/*  86 */         catch (IOException e) {
/*     */           
/*  88 */           e.printStackTrace();
/*     */         } 
/*     */       } else {
/*     */         
/*  92 */         searchTable();
/*  93 */         chargerBases();
/*     */       } 
/*     */     } else {
/*     */       
/*  97 */       searchTable();
/*  98 */       chargerBases();
/*     */     } 
/*     */   }

/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 104 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 109 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Base> getListBase() {
/* 114 */     return this.listBase;
/*     */   }
/*     */   
/*     */   public void setListBase(List<Base> listBase) {
/* 118 */     this.listBase = listBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTableName() {
/* 123 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableName(String tableName) {
/* 128 */     this.tableName = tableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getBaseSelectionne() {
/* 133 */     return this.baseSelectionne;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseSelectionne(Base baseSelectionne) {
/* 138 */     this.baseSelectionne = baseSelectionne;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
			
/* 143 */     if (!getCode().trim().equals("")) {
/*     */       
/* 145 */       this.baseSelectionne = FichierBaseDAO.getInstance().getBaseByCode(getCode(), this.tableName);
/* 146 */       if (this.baseSelectionne != null) {
/*     */         
/* 148 */         takeSelection();
/*     */       } else {
/*     */         
/* 151 */         clearAttributes(false);
/*     */       } 
/*     */     } else {
/*     */       
/* 155 */       clearAttributes(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertUpdate() {
/* 161 */     if (this.userExist) {
/*     */       
/* 163 */       if (this.droit.isCreer()) {
/*     */         
/* 165 */         if (!getCode().trim().equals("") || !getDesignation().trim().equals("")) {
/*     */ 
/*     */           
/* 168 */           if (FichierBaseDAO.getInstance().insertUpdateBase(this, this.tableName)) {
/*     */             
/* 170 */             clearAttributes(true);
/* 171 */             chargerBases();
/* 172 */             HelperC.afficherMessage("F�licitation!", "Succ�s de l'Op�ration");
/*     */           } else {
/*     */             
/* 175 */             HelperC.afficherMessage("D�sol�!", "Echec de l'Op�ration");
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 180 */           HelperC.afficherMessage("Information", "Pr�cisez le code et la d�signation");
/*     */         } 
/*     */       } else {
/*     */         
/* 184 */         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas droit � la cr�ation !");
/*     */       }
/*     */     
/* 187 */     } else if (!getCode().trim().equals("") || !getDesignation().trim().equals("")) {
/*     */       
/* 189 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, this.tableName)) {
/*     */         
/* 191 */         clearAttributes(true);
/* 192 */         chargerBases();
/* 193 */         HelperC.afficherMessage("F�licitation!", "Succ�s de l'Op�ration");
/*     */       } else {
/*     */         
/* 196 */         HelperC.afficherMessage("D�sol�!", "Echec de l'Op�ration");
/*     */       } 
/*     */     } else {
/*     */       
/* 200 */       HelperC.afficherMessage("Information", "Pr�cisez le code et la d�signation");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargerBases() {
			if(tableName!=null && !tableName.equals(""))
/* 207 */     this.listBase = FichierBaseDAO.getInstance().getAllBase(this.tableName);
/*     */   }
/*     */ 
/*     */   
/*     */   private void searchTable() {
/* 212 */     UIComponent frm = null;
/* 213 */     FacesContext context = FacesContext.getCurrentInstance();
/* 214 */     frm = context.getViewRoot().findComponent("frmFonction");
/* 215 */     if (frm != null)
/*     */     {
/* 217 */       this.tableName = Tables.getTableName(Tables.TableName.fonction);
/*     */     }
/* 219 */     frm = context.getViewRoot().findComponent("frmCampus");
/* 220 */     if (frm != null)
/*     */     {
/* 222 */       this.tableName = Tables.getTableName(Tables.TableName.campus);
/*     */     }
/* 224 */     frm = context.getViewRoot().findComponent("frmDirUb");
/* 225 */     if (frm != null)
/*     */     {
/* 227 */       this.tableName = Tables.getTableName(Tables.TableName.directionGnle);
/*     */     }
/*     */     
/* 230 */     frm = context.getViewRoot().findComponent("frmRoleMembreOrgane");
/* 231 */     if (frm != null)
/*     */     {
/* 233 */       this.tableName = Tables.getTableName(Tables.TableName.roleMembreOrgane);
/*     */     }
/*     */     
/* 236 */     frm = context.getViewRoot().findComponent("frmLieu");
/* 237 */     if (frm != null)
/*     */     {
/* 239 */       this.tableName = Tables.getTableName(Tables.TableName.lieuxTravail);
/*     */     }
/*     */     
/* 242 */     frm = context.getViewRoot().findComponent("frmTypContrat");
/* 243 */     if (frm != null)
/*     */     {
/* 245 */       this.tableName = Tables.getTableName(Tables.TableName.typeContrat);
/*     */     }
/*     */     
/* 248 */     frm = context.getViewRoot().findComponent("frmPrsonl");
/* 249 */     if (frm != null)
/*     */     {
/* 251 */       this.tableName = Tables.getTableName(Tables.TableName.personnel);
/*     */     }
/*     */     
/* 254 */     frm = context.getViewRoot().findComponent("frmElJrnl");
/* 255 */     if (frm != null)
/*     */     {
/* 257 */       this.tableName = Tables.getTableName(Tables.TableName.elementJournal);
/*     */     }
/*     */     
			  frm = context.getViewRoot().findComponent("frmMotifRetr");
/* 255 */     if (frm != null)
/*     */     {
/* 257 */       this.tableName = Tables.getTableName(Tables.TableName.motifRetraite);
/*     */     }
/*     */     
/* 260 */     if (this.userExist) {
/*     */       
/* 262 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 263 */       if (this.userFonction != null)
/*     */       {
/* 265 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearAttributes(boolean b) {
/* 273 */     if (b)
/*     */     {
/* 275 */       setCode("");
/*     */     }
/* 277 */     setId(0);
/* 278 */     setDesignation("");
/* 279 */     this.baseSelectionne = null;
			  disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeSelection() {
				disableMsg=true;
/* 284 */     if (this.baseSelectionne != null) {
/*     */       disableMsg=false;
/* 286 */       setId(this.baseSelectionne.getId());
/* 287 */       setCode(this.baseSelectionne.getCode());
/* 288 */       setDesignation(this.baseSelectionne.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete() {
/* 294 */     if (this.droit!=null && !this.droit.isSupprimer()) {
/*     */       
/* 296 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas droit � la Suppression");
/*     */     }
/* 298 */     else if (getId() == 0) {
/*     */       
/* 300 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 302 */     else if (FichierBaseDAO.getInstance().delete(getId(), this.tableName)) {
/*     */       
/* 304 */       clearAttributes(true);
/* 305 */       this.baseSelectionne = null;
/* 306 */       chargerBases();
/* 307 */       HelperC.afficherMessage("F�licitations", "Succ�s de l'Op�ration");
/*     */     } else {
/*     */       
/* 310 */       HelperC.afficherMessage("D�sol�!", "Echec de l'Op�ration");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiserControles() {
/* 316 */     clearAttributes(true);
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\BaseVue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */