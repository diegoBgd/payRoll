/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.LieuxTravailC;
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
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class LieuxTravailB
/*     */   extends LieuxTravailC
/*     */ {
/*     */   private static final long serialVersionUID = 300722569362046868L;
/*     */   private LieuxTravailC lieuTravail;
/*     */   private List<LieuxTravailC> lieuTravails;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  35 */   private HttpSession session = HelperC.getSession();
/*     */ 
/*     */ 
/*     */   
/*     */   public LieuxTravailC getLieuTravail() {
/*  40 */     return this.lieuTravail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLieuTravail(LieuxTravailC lieuTravail) {
/*  45 */     this.lieuTravail = lieuTravail;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<LieuxTravailC> getLieuTravails() {
/*  50 */     return this.lieuTravails;
/*     */   }
/*     */   
/*     */   public void setLieuTravails(List<LieuxTravailC> lieuTravails) {
/*  54 */     this.lieuTravails = lieuTravails;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  59 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  64 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  69 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  74 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  79 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  84 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  90 */     this.operateur = new OperateurC();
/*  91 */     this.exercice = new ExerciceC();
/*  92 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  93 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  94 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  95 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  96 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/*     */         
/* 100 */         FacesContext context = FacesContext.getCurrentInstance();
/* 101 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 103 */       catch (IOException e) {
/*     */         
/* 105 */         e.printStackTrace();
/*     */       } 
/*     */     }
/* 108 */     charger();
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 113 */     this.lieuTravails = FichierBaseDAO.getInstance().getAllLieuxTravail();
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 118 */     if (b)
/*     */     {
/* 120 */       setCode("");
/*     */     }
/* 122 */     setId(0);
/* 123 */     setDesignation("");
/* 124 */     setAvenue("");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 129 */     if (this.lieuTravail != null) {
/*     */       
/* 131 */       setId(this.lieuTravail.getId());
/* 132 */       setCode(this.lieuTravail.getCode());
/* 133 */       setDesignation(this.lieuTravail.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 139 */     if (getCode().trim().equals("")) {
/*     */       
/* 141 */       clear(false);
/*     */     } else {
/*     */       
/* 144 */       this.lieuTravail = FichierBaseDAO.getInstance().getLieuxTravail(getCode());
/* 145 */       if (this.lieuTravail == null) {
/*     */         
/* 147 */         clear(false);
/*     */       } else {
/*     */         
/* 150 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 157 */     this.lieuTravail = (LieuxTravailC)event.getObject();
/* 158 */     if (this.lieuTravail != null)
/*     */     {
/* 160 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 166 */     if (getCode().trim().equals("")) {
/*     */       
/* 168 */       HelperC.afficherMessage("Information", "Précisez le code");
/*     */     }
/* 170 */     else if (getDesignation().trim().equals("")) {
/*     */       
/* 172 */       HelperC.afficherMessage("Information", "Précisez la désignation");
/*     */     }
/* 174 */     else if (FichierBaseDAO.getInstance().getLieuxTravail(getCode(), getId()) != null) {
/*     */       
/* 176 */       HelperC.afficherMessage("Information", "Ce code est déjà utilisé");
/*     */     }
/* 178 */     else if (FichierBaseDAO.getInstance().getLieuxTravailByDesignation(getDesignation(), getId()) != null) {
/*     */       
/* 180 */       HelperC.afficherMessage("Information", "Cette désignation est déjà utilisé");
/*     */     } else {
/*     */       
/* 183 */       Historique hist = new Historique();
/* 184 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 185 */       hist.setOperateur(this.operateur);
/* 186 */       if (getId() == 0) {
/*     */         
/* 188 */         hist.setOperation("Création du lieu de travail " + getCode());
/*     */       } else {
/*     */         
/* 191 */         hist.setOperation("Modification du lieu de travail " + getCode());
/*     */       } 
/* 193 */       hist.setTable(Tables.getTableName(Tables.TableName.lieuxTravail));
/* 194 */       setHistorique(hist);
/* 195 */       if (FichierBaseDAO.getInstance().insertUpdateLieuxTravail(this)) {
/*     */         
/* 197 */         clear(true);
/* 198 */         HelperC.afficherMessage("Information", "Succès de l'opération");
/* 199 */         this.lieuTravails = FichierBaseDAO.getInstance().getAllLieuxTravail();
/*     */       } else {
/*     */         
/* 202 */         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 209 */     if (getId() == 0) {
/*     */       
/* 211 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 213 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.lieuxTravail), getId())) {
/*     */       
/* 215 */       clear(true);
/* 216 */       HelperC.afficherMessage("Information", "Succès de la suppression");
/* 217 */       this.lieuTravails = FichierBaseDAO.getInstance().getAllLieuxTravail();
/*     */     } else {
/*     */       
/* 220 */       HelperC.afficherMessage("Désolé", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 226 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\LieuxTravailB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */