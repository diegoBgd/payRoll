/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
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
/*     */ public class TypeSanctionB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = 5176764009000635033L;
/*     */   private Base typeSanction;
/*     */   private List<Base> typeSanctions;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  34 */   private HttpSession session = HelperC.getSession();
/*     */ 
/*     */ 
/*     */   
/*     */   public Base getTypeSanction() {
/*  39 */     return this.typeSanction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeSanction(Base typeSanction) {
/*  44 */     this.typeSanction = typeSanction;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Base> getTypeSanctions() {
/*  49 */     return this.typeSanctions;
/*     */   }
/*     */   
/*     */   public void setTypeSanctions(List<Base> typeSanctions) {
/*  53 */     this.typeSanctions = typeSanctions;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  58 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  63 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  68 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  73 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  78 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  83 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  89 */     this.operateur = new OperateurC();
/*  90 */     this.exercice = new ExerciceC();
/*  91 */     this.operateur = (OperateurC)this.session.getAttribute("operateur");
/*  92 */     this.exercice = (ExerciceC)this.session.getAttribute("exercice");
/*  93 */     if (this.operateur == null || this.exercice == null) {
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
/* 106 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 112 */     this.typeSanctions = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeSanction));
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 117 */     if (b)
/*     */     {
/* 119 */       setCode("");
/*     */     }
/* 121 */     setId(0);
/* 122 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 127 */     if (this.typeSanction != null) {
/*     */       
/* 129 */       setId(this.typeSanction.getId());
/* 130 */       setCode(this.typeSanction.getCode());
/* 131 */       setDesignation(this.typeSanction.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 137 */     if (getCode().trim().equals("")) {
/*     */       
/* 139 */       clear(true);
/*     */     } else {
/*     */       
/* 142 */       this.typeSanction = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeSanction));
/* 143 */       if (this.typeSanction == null) {
/*     */         
/* 145 */         clear(false);
/*     */       } else {
/*     */         
/* 148 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 155 */     this.typeSanction = (Base)event.getObject();
/* 156 */     if (this.typeSanction != null)
/*     */     {
/* 158 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 164 */     if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */       
/* 166 */       HelperC.afficherMessage("Information", "Code et D�signation sont obligatoires");
/*     */     }
/* 168 */     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeSanction)) != null) {
/*     */       
/* 170 */       HelperC.afficherMessage("Information", "Ce code existe d�j� ");
/*     */     }
/* 172 */     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.typeSanction)) != null) {
/*     */       
/* 174 */       HelperC.afficherMessage("Information", "Cette d�signation existe d�j� ");
/*     */     } else {
/*     */       
/* 177 */       Historique hist = new Historique();
/* 178 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 179 */       hist.setOperateur(this.operateur);
/* 180 */       if (getId() == 0) {
/*     */         
/* 182 */         hist.setOperation("Cr�ation du type sanction " + getCode());
/*     */       } else {
/*     */         
/* 185 */         hist.setOperation("Modification du type sanction " + getCode());
/*     */       } 
/* 187 */       hist.setTable(Tables.getTableName(Tables.TableName.typeSanction));
/* 188 */       setHistorique(hist);
/* 189 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeSanction))) {
/*     */         
/* 191 */         clear(true);
/* 192 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 193 */         charger();
/*     */       } else {
/*     */         
/* 196 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 203 */     if (getId() == 0) {
/*     */       
/* 205 */       HelperC.afficherMessage("Information", "Pr�cisez le type de sanction � supprimer");
/*     */     }
/* 207 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeSanction), getId())) {
/*     */       
/* 209 */       clear(true);
/* 210 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 211 */       charger();
/*     */     } else {
/*     */       
/* 214 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 220 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */ 
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\TypeSanctionB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */