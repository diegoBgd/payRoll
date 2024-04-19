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
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeFraisB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = -365714400725990680L;
/*     */   private Base typeFrais;
/*     */   private List<Base> listTypeFrais;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  36 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   public Base getTypeFrais() {
/*  41 */     return this.typeFrais;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeFrais(Base typeFrais) {
/*  46 */     this.typeFrais = typeFrais;
/*     */   }
/*     */   
/*     */   public List<Base> getListTypeFrais() {
/*  50 */     return this.listTypeFrais;
/*     */   }
/*     */   
/*     */   public void setListTypeFrais(List<Base> listTypeFrais) {
/*  54 */     this.listTypeFrais = listTypeFrais;
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
/*  90 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  91 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  92 */     if (codeOperateur == null || codeExercice == null) {
/*     */ 
/*     */       
/*     */       try {
/*  96 */         FacesContext context = FacesContext.getCurrentInstance();
/*  97 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/*  99 */       catch (IOException e) {
/*     */         
/* 101 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 105 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 106 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 107 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 108 */       if (this.userFonction != null)
/*     */       {
/* 110 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
/* 112 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 118 */     this.listTypeFrais = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeFrais));
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 123 */     if (b)
/*     */     {
/* 125 */       setCode("");
/*     */     }
/* 127 */     setId(0);
/* 128 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 133 */     if (this.typeFrais != null) {
/*     */       
/* 135 */       setId(this.typeFrais.getId());
/* 136 */       setCode(this.typeFrais.getCode());
/* 137 */       setDesignation(this.typeFrais.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 143 */     if (getCode().trim().equals("")) {
/*     */       
/* 145 */       clear(true);
/*     */     } else {
/*     */       
/* 148 */       this.typeFrais = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeFrais));
/* 149 */       if (this.typeFrais == null) {
/*     */         
/* 151 */         clear(false);
/*     */       } else {
/*     */         
/* 154 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelect(SelectEvent event) {
/* 161 */     this.typeFrais = (Base)event.getObject();
/* 162 */     if (this.typeFrais != null)
/*     */     {
/* 164 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 170 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 172 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 174 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 176 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 178 */     else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */       
/* 180 */       HelperC.afficherMessage("Information", "Completez tous les champs");
/*     */     }
/* 182 */     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeFrais)) != null) {
/*     */       
/* 184 */       HelperC.afficherMessage("Information", "Ce code du type de frais existe d�ja");
/*     */     }
/* 186 */     else if (FichierBaseDAO.getInstance().getBases(getDesignation().replace("'", "\\'").trim(), getId(), Tables.getTableName(Tables.TableName.typeFrais)) != null) {
/*     */       
/* 188 */       HelperC.afficherMessage("Information", "ce type de frais est deja saisi");
/*     */     } else {
/*     */       
/* 191 */       Historique hist = new Historique();
/* 192 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 193 */       hist.setOperateur(this.operateur);
/* 194 */       if (getId() == 0) {
/*     */         
/* 196 */         hist.setOperation("Cr�ation du type de frais " + getCode());
/*     */       } else {
/*     */         
/* 199 */         hist.setOperation("Modification du type de frais " + getCode());
/*     */       } 
/* 201 */       hist.setTable(Tables.getTableName(Tables.TableName.typeFrais));
/* 202 */       setHistorique(hist);
/* 203 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeFrais))) {
/*     */         
/* 205 */         clear(true);
/* 206 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 207 */         charger();
/*     */       } else {
/*     */         
/* 210 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 217 */     if (getId() == 0) {
/*     */       
/* 219 */       HelperC.afficherMessage("Information", "Pr�cisez le type de frais � supprimer");
/*     */     }
/* 221 */     else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */       
/* 223 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 225 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeFrais), getId())) {
/*     */       
/* 227 */       clear(true);
/* 228 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 229 */       charger();
/*     */     } else {
/*     */       
/* 232 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 238 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   

/*     */ }

