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

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ProfessionB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = -5289226743168992229L;
/*     */   private Base profession;
/*     */   private List<Base> professions;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  38 */   private HttpSession session = HelperC.getSession();
/*     */   private DroitC droit;
/*     */   Base userFonction;
/*     */   
/*     */   public Base getProfession() {
/*  43 */     return this.profession;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProfession(Base profession) {
/*  48 */     this.profession = profession;
/*     */   }
/*     */   
/*     */   public List<Base> getProfessions() {
/*  52 */     return this.professions;
/*     */   }
/*     */   
/*     */   public void setProfessions(List<Base> professions) {
/*  56 */     this.professions = professions;
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
/* 111 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 112 */       if (this.userFonction != null)
/*     */       {
/* 114 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
/* 116 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 122 */     this.professions = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.profession));
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 127 */     if (b)
/*     */     {
/* 129 */       setCode("");
/*     */     }
/* 131 */     setId(0);
/* 132 */     this.profession = null;
/* 133 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 138 */     if (getCode().trim().equals("")) {
/*     */       
/* 140 */       clear(true);
/*     */     } else {
/*     */       
/* 143 */       this.profession = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.profession));
/*     */     } 
/* 145 */     if (this.profession == null) {
/*     */       
/* 147 */       clear(true);
/*     */     } else {
/*     */       
/* 150 */       setObject();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 156 */     if (this.profession != null) {
/*     */       
/* 158 */       setId(this.profession.getId());
/* 159 */       setCode(this.profession.getCode());
/* 160 */       setDesignation(this.profession.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 166 */     this.profession = (Base)event.getObject();
/* 167 */     if (this.profession != null)
/*     */     {
/* 169 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 175 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 180 */     if (getCode().trim().equals("") || getDesignation().trim().equals(""))
/*     */     {
/* 182 */       HelperC.afficherMessage("Information", "Code et D�signation obligatoires");
/*     */     }
/*     */     
/* 185 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 187 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */       return;
/*     */     } 
/* 190 */     if (getId() != 0 && !this.droit.isModifier()) {
/*     */       
/* 192 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 197 */       Historique hist = new Historique();
/* 198 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 199 */       hist.setOperateur(this.operateur);
/* 200 */       if (getId() == 0) {
/*     */         
/* 202 */         hist.setOperation("Cr�ation de la profession " + getCode());
/*     */       } else {
/*     */         
/* 205 */         hist.setOperation("Modification de la profession " + getCode());
/*     */       } 
/* 207 */       hist.setTable(Tables.getTableName(Tables.TableName.profession));
/* 208 */       setHistorique(hist);
/* 209 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.profession))) {
/*     */         
/* 211 */         clear(true);
/* 212 */         HelperC.afficherMessage("F�licitaions", "Succ�s de l'Op�ration");
/* 213 */         charger();
/*     */       } else {
/*     */         
/* 216 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */       }
/*     */     
/* 219 */     } catch (Exception e) {
/*     */       
/* 221 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 229 */       if (getId() == 0) {
/*     */         
/* 231 */         HelperC.afficherMessage("Information", "Pr�cisez la profession � supprimer");
/*     */       }
/* 233 */       else if (this.droit.isSupprimer()) {
/*     */         
/* 235 */         if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.profession))) {
/*     */           
/* 237 */           clear(true);
/* 238 */           HelperC.afficherMessage("F�licitaions", "Succ�s de l'Op�ration");
/* 239 */           charger();
/*     */         } else {
/*     */           
/* 242 */           HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */         } 
/*     */       } else {
/*     */         
/* 246 */         HelperC.afficherMessage("D�sol�", "Vous n'avez pas le droit de supprimer");
/*     */       }
/*     */     
/* 249 */     } catch (Exception e) {
/*     */       
/* 251 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\ProfessionB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */