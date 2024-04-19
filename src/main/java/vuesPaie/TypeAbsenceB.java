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
/*     */ public class TypeAbsenceB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = -1453927867592142501L;
/*     */   private Base typeAbsence;
/*     */   private List<Base> typeAbsences;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  37 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   public Base getTypeAbsence() {
/*  42 */     return this.typeAbsence;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeAbsence(Base typeAbsence) {
/*  47 */     this.typeAbsence = typeAbsence;
/*     */   }
/*     */   
/*     */   public List<Base> getTypeAbsences() {
/*  51 */     return this.typeAbsences;
/*     */   }
/*     */   
/*     */   public void setTypeAbsences(List<Base> typeAbsences) {
/*  55 */     this.typeAbsences = typeAbsences;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  60 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  65 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  70 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  75 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  80 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  85 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  91 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  92 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  93 */     if (codeOperateur == null || codeExercice == null) {
/*     */ 
/*     */       
/*     */       try {
/*  97 */         FacesContext context = FacesContext.getCurrentInstance();
/*  98 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 100 */       catch (IOException e) {
/*     */         
/* 102 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 106 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 107 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 108 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 109 */       if (this.userFonction != null)
/*     */       {
/* 111 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
/* 113 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 119 */     this.typeAbsences = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAbsence));
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 124 */     if (b)
/*     */     {
/* 126 */       setCode("");
/*     */     }
/* 128 */     setId(0);
/* 129 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 134 */     if (this.typeAbsence != null) {
/*     */       
/* 136 */       setId(this.typeAbsence.getId());
/* 137 */       setCode(this.typeAbsence.getCode());
/* 138 */       setDesignation(this.typeAbsence.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 144 */     if (getCode().trim().equals("")) {
/*     */       
/* 146 */       clear(true);
/*     */     } else {
/*     */       
/* 149 */       this.typeAbsence = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeAbsence));
/* 150 */       if (this.typeAbsence == null) {
/*     */         
/* 152 */         clear(false);
/*     */       } else {
/*     */         
/* 155 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 162 */     this.typeAbsence = (Base)event.getObject();
/* 163 */     if (this.typeAbsence != null)
/*     */     {
/* 165 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 171 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 173 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 175 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 177 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 179 */     else if (getCode().trim().equals("")) {
/*     */       
/* 181 */       HelperC.afficherMessage("Information", "Code et D�signation sont obligatoires");
/*     */     }
/* 183 */     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeAbsence)) != null) {
/*     */       
/* 185 */       HelperC.afficherMessage("Information", "Ce code existe d�j� ");
/*     */     }
/* 187 */     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.typeAbsence)) != null) {
/*     */       
/* 189 */       HelperC.afficherMessage("Information", "Cette d�signation existe d�j� ");
/*     */     } else {
/*     */       
/* 192 */       Historique hist = new Historique();
/* 193 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 194 */       hist.setOperateur(this.operateur);
/* 195 */       if (getId() == 0) {
/*     */         
/* 197 */         hist.setOperation("Cr�ation du type d'absence " + getCode());
/*     */       } else {
/*     */         
/* 200 */         hist.setOperation("Modification du type d'absence " + getCode());
/*     */       } 
/* 202 */       hist.setTable(Tables.getTableName(Tables.TableName.typeAbsence));
/* 203 */       setHistorique(hist);
/* 204 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeAbsence))) {
/*     */         
/* 206 */         clear(true);
/* 207 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 208 */         charger();
/*     */       } else {
/*     */         
/* 211 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 218 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 223 */     if (getId() == 0) {
/*     */       
/* 225 */       HelperC.afficherMessage("Information", "Pr�cisez le type d'absence � supprimer");
/*     */     }
/* 227 */     else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */       
/* 229 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 231 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeAbsence), getId())) {
/*     */       
/* 233 */       clear(true);
/* 234 */       HelperC.afficherMessage("Information", "Succes de l'op�ration ");
/* 235 */       charger();
/*     */     } else {
/*     */       
/* 238 */       HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */  
/*     */ }

