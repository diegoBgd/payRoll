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
/*     */ public class TypeFormationB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = 7415527835658069410L;
/*     */   private Base typeFormation;
/*     */   private List<Base> typeFormations;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  37 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   public Base getTypeFormation() {
/*  42 */     return this.typeFormation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeFormation(Base typeFormation) {
/*  47 */     this.typeFormation = typeFormation;
/*     */   }
/*     */   
/*     */   public List<Base> getTypeFormations() {
/*  51 */     return this.typeFormations;
/*     */   }
/*     */   
/*     */   public void setTypeFormations(List<Base> typeFormations) {
/*  55 */     this.typeFormations = typeFormations;
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
/*  93 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  94 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  95 */     if (this.exercice == null || this.operateur == null) {
/*     */ 
/*     */       
/*     */       try {
/*  99 */         FacesContext context = FacesContext.getCurrentInstance();
/* 100 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 102 */       catch (IOException e) {
/*     */         
/* 104 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
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
/* 119 */     this.typeFormations = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeFormation));
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
/* 134 */     if (this.typeFormation != null) {
/*     */       
/* 136 */       setId(this.typeFormation.getId());
/* 137 */       setCode(this.typeFormation.getCode());
/* 138 */       setDesignation(this.typeFormation.getDesignation());
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
/* 149 */       this.typeFormation = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeFormation));
/* 150 */       if (this.typeFormation == null) {
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
/* 162 */     this.typeFormation = (Base)event.getObject();
/* 163 */     if (this.typeFormation != null)
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
/* 183 */     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeFormation)) != null) {
/*     */       
/* 185 */       HelperC.afficherMessage("Information", "Ce type de formation existe d�j� ");
/*     */     }
/* 187 */     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.typeFormation)) != null) {
/*     */       
/* 189 */       HelperC.afficherMessage("Information", "Cette d�signation existe d�j� ");
/*     */     } else {
/*     */       
/* 192 */       Historique hist = new Historique();
/* 193 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 194 */       hist.setOperateur(this.operateur);
/* 195 */       if (getId() == 0) {
/*     */         
/* 197 */         hist.setOperation("Cr�ation du type formation " + getCode());
/*     */       } else {
/*     */         
/* 200 */         hist.setOperation("Modification de l'exercice " + getCode());
/*     */       } 
/* 202 */       hist.setTable(Tables.getTableName(Tables.TableName.typeFormation));
/* 203 */       setHistorique(hist);
/* 204 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeFormation))) {
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
/* 225 */       HelperC.afficherMessage("Information", "Pr�cisez la formation � supprimer");
/*     */     }
/* 227 */     else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */       
/* 229 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 231 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeFormation), getId())) {
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

/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\TypeFormationB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */