/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DeviseC;
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
/*     */ public class DeviseB
/*     */   extends DeviseC
/*     */ {
/*     */   private static final long serialVersionUID = 4055882882261088023L;
/*     */   private DeviseC devise;
/*     */   private List<DeviseC> devises;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  38 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   public DeviseC getDevise() {
/*  43 */     return this.devise;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDevise(DeviseC devise) {
/*  48 */     this.devise = devise;
/*     */   }
/*     */   
/*     */   public List<DeviseC> getDevises() {
/*  52 */     return this.devises;
/*     */   }
/*     */   
/*     */   public void setDevises(List<DeviseC> devises) {
/*  56 */     this.devises = devises;
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
/* 124 */     this.devises = FichierBaseDAO.getInstance().getAllDevise();
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 129 */     if (b)
/*     */     {
/* 131 */       setCode("");
/*     */     }
/* 133 */     setId(0);
/* 134 */     setDesignation("");
/* 135 */     setSymbole("");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 140 */     if (this.devise != null) {
/*     */       
/* 142 */       setId(this.devise.getId());
/* 143 */       setCode(this.devise.getCode());
/* 144 */       setDesignation(this.devise.getDesignation());
/* 145 */       setSymbole(this.devise.getSymbole());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 151 */     if (getCode().trim().equals("")) {
/*     */       
/* 153 */       clear(true);
/*     */     } else {
/*     */       
/* 156 */       this.devise = FichierBaseDAO.getInstance().getDevise(getCode());
/* 157 */       if (this.devise == null) {
/*     */         
/* 159 */         clear(false);
/*     */       } else {
/*     */         
/* 162 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 169 */     this.devise = (DeviseC)event.getObject();
/* 170 */     if (this.devise != null)
/*     */     {
/* 172 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 178 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 180 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
/*     */     }
/* 182 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 184 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 186 */     else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */       
/* 188 */       HelperC.afficherMessage("Information", "Code et Désignation sont obligatoires");
/*     */     }
/* 190 */     else if (FichierBaseDAO.getInstance().getDeviseNotCurrent(getCode(), getId()) != null) {
/*     */       
/* 192 */       HelperC.afficherMessage("Information", "Ce code existe déjà dans devise");
/*     */     }
/* 194 */     else if (FichierBaseDAO.getInstance().getDeviseByDesignation(getDesignation(), getId()) != null) {
/*     */       
/* 196 */       HelperC.afficherMessage("Information", "cette designation est deja saisie");
/*     */     } else {
/*     */       
/* 199 */       Historique hist = new Historique();
/* 200 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 201 */       hist.setOperateur(this.operateur);
/* 202 */       if (getId() == 0) {
/*     */         
/* 204 */         hist.setOperation("Création des devises " + getCode());
/*     */       } else {
/*     */         
/* 207 */         hist.setOperation("Modification des devises " + getCode());
/*     */       } 
/* 209 */       hist.setTable(Tables.getTableName(Tables.TableName.devise));
/* 210 */       setHistorique(hist);
/* 211 */       if (FichierBaseDAO.getInstance().insertUpdateDevise(this)) {
/*     */         
/* 213 */         clear(true);
/* 214 */         HelperC.afficherMessage("Information", "Succès de l'opération ");
/* 215 */         charger();
/*     */       } else {
/*     */         
/* 218 */         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 225 */     if (getId() == 0) {
/*     */       
/* 227 */       HelperC.afficherMessage("Information", "Précisez la devise à supprimer ");
/*     */     }
/* 229 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.devise), getId())) {
/*     */       
/* 231 */       clear(true);
/* 232 */       HelperC.afficherMessage("Information", "Succès de l'opération ");
/* 233 */       charger();
/*     */     } else {
/*     */       
/* 236 */       HelperC.afficherMessage("Désolé", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 242 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fermer() {
/*     */     try {
/* 249 */       FacesContext.getCurrentInstance().getExternalContext().redirect("/asystPaie/masterPage.jsf");
/*     */     }
/* 251 */     catch (IOException e) {
/*     */       
/* 253 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\DeviseB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */