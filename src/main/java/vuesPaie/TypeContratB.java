/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeContratB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = -3891763416929479090L;
/*     */   private Base typeContrat;
/*     */   private List<Base> typeContrats;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  37 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   public Base getTypeContrat() {
/*  42 */     return this.typeContrat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeContrat(Base typeContrat) {
/*  47 */     this.typeContrat = typeContrat;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Base> getTypeContrats() {
/*  52 */     return this.typeContrats;
/*     */   }
/*     */   
/*     */   public void setTypeContrats(List<Base> typeContrats) {
/*  56 */     this.typeContrats = typeContrats;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  61 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  66 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  71 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  76 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  81 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  86 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  92 */     this.operateur = new OperateurC();
/*  93 */     this.exercice = new ExerciceC();
/*  94 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  95 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  96 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  97 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  98 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 102 */         FacesContext context = FacesContext.getCurrentInstance();
/* 103 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 105 */       catch (IOException e) {
/*     */         
/* 107 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 111 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 112 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 113 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 114 */       if (this.userFonction != null)
/*     */       {
/* 116 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
/* 118 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 124 */     this.typeContrats = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeContrat));
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 129 */     if (b)
/*     */     {
/* 131 */       setCode("");
/*     */     }
/* 133 */     setId(0);
/* 134 */     this.typeContrat = null;
/* 135 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 140 */     if (getCode().trim().equals("")) {
/*     */       
/* 142 */       clear(true);
/*     */     } else {
/*     */       
/* 145 */       this.typeContrat = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeContrat));
/*     */     } 
/* 147 */     if (this.typeContrat == null) {
/*     */       
/* 149 */       clear(true);
/*     */     } else {
/*     */       
/* 152 */       setObject();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 158 */     if (this.typeContrat != null) {
/*     */       
/* 160 */       setId(this.typeContrat.getId());
/* 161 */       setCode(this.typeContrat.getCode());
/* 162 */       setDesignation(this.typeContrat.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 168 */     this.typeContrat = (Base)event.getObject();
/* 169 */     if (this.typeContrat != null)
/*     */     {
/* 171 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 177 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 182 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 184 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 186 */     else if (getId() == 0 && !this.droit.isModifier()) {
/*     */       
/* 188 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 190 */     else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */       
/* 192 */       HelperC.afficherMessage("Information", "Code et D�signation obligatoires");
/*     */     }
/* 194 */     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeContrat)) != null) {
/*     */       
/* 196 */       HelperC.afficherMessage("Information", "Ce code est d�j� utilis�");
/*     */     }
/* 198 */     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.typeContrat)) != null) {
/*     */       
/* 200 */       HelperC.afficherMessage("information", "Cette d�signation est d�j� utilis�e");
/*     */     } else {
/*     */       
/* 203 */       Historique hist = new Historique();
/* 204 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 205 */       hist.setOperateur(this.operateur);
/* 206 */       if (getId() == 0) {
/*     */         
/* 208 */         hist.setOperation("Cr�ation du type contrat " + getCode());
/*     */       } else {
/*     */         
/* 211 */         hist.setOperation("Modification du type contrat " + getCode());
/*     */       } 
/* 213 */       hist.setTable(Tables.getTableName(Tables.TableName.typeContrat));
/* 214 */       setHistorique(hist);
/* 215 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeContrat))) {
/*     */         
/* 217 */         clear(true);
/* 218 */         HelperC.afficherMessage("F�licitaions", "Succ�s de l'Op�ration");
/* 219 */         charger();
/*     */       } else {
/*     */         
/* 222 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 231 */       if (getId() == 0) {
/*     */         
/* 233 */         HelperC.afficherDeleteMessage();
/*     */       }
/* 235 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.typeContrat))) {
/*     */         
/* 237 */         clear(true);
/* 238 */         HelperC.afficherMessage("F�licitaions", "Succ�s de l'Op�ration");
/* 239 */         charger();
/*     */       } else {
/*     */         
/* 242 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */       }
/*     */     
/* 245 */     } catch (Exception e) {
/*     */       
/* 247 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   
/*     */ }

