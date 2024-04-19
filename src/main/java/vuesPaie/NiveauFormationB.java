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
/*     */ public class NiveauFormationB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = -1864592058311941323L;
/*     */   private Base selected;
/*     */   private List<Base> niveauxFormations;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  34 */   private HttpSession session = HelperC.getSession();
/*     */ 	private boolean disableMsg;
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  39 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  44 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  49 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  54 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  59 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  64 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getSelected() {
/*  69 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(Base selected) {
/*  74 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public List<Base> getNiveauxFormations() {
/*  78 */     return this.niveauxFormations;
/*     */   }
/*     */   
/*     */   public void setNiveauxFormations(List<Base> niveauxFormations) {
/*  82 */     this.niveauxFormations = niveauxFormations;
/*     */   }
			
			
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  88 */     this.operateur = new OperateurC();
/*  89 */     this.exercice = new ExerciceC();
/*  90 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  91 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  92 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  93 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  94 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/*  98 */         FacesContext context = FacesContext.getCurrentInstance();
/*  99 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 101 */       catch (IOException e) {
/*     */         
/* 103 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/* 107 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 113 */     this.niveauxFormations = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.niveauFormation));
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
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
				disableMsg=true;
/* 128 */     if (this.selected != null) {
/*     */       disableMsg=false;
/* 130 */       setId(this.selected.getId());
/* 131 */       setCode(this.selected.getCode());
/* 132 */       setDesignation(this.selected.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 138 */     if (getCode().trim().equals("")) {
/*     */       
/* 140 */       clear(true);
/*     */     } else {
/*     */       
/* 143 */       this.selected = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.niveauFormation));
/* 144 */       if (this.selected == null) {
/*     */         
/* 146 */         clear(false);
/*     */       } else {
/*     */         
/* 149 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 156 */     this.selected = (Base)event.getObject();
/* 157 */     if (this.selected != null)
/*     */     {
/* 159 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 165 */     if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */       
/* 167 */       HelperC.afficherMessage("Information", "Code et D�signation sont obligatoires");
/*     */     }
/* 169 */     else if (FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.niveauFormation)) != null) {
/*     */       
/* 171 */       HelperC.afficherMessage("Information", "Ce code existe d�j� ");
/*     */     }
/* 173 */     else if (FichierBaseDAO.getInstance().getBases(getDesignation(), getId(), Tables.getTableName(Tables.TableName.niveauFormation)) != null) {
/*     */       
/* 175 */       HelperC.afficherMessage("Information", "Cette d�signation existe d�j� ");
/*     */     } else {
/*     */       
/* 178 */       Historique hist = new Historique();
/* 179 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 180 */       hist.setOperateur(this.operateur);
/* 181 */       if (getId() == 0) {
/*     */         
/* 183 */         hist.setOperation("Cr�ation du niveau de formation " + getCode());
/*     */       } else {
/*     */         
/* 186 */         hist.setOperation("Modification du niveau de formation " + getCode());
/*     */       } 
/* 188 */       hist.setTable(Tables.getTableName(Tables.TableName.niveauFormation));
/* 189 */       setHistorique(hist);
/* 190 */       if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.niveauFormation))) {
/*     */         
/* 192 */         clear(true);
/* 193 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 194 */         charger();
/*     */       } else {
/*     */         
/* 197 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 204 */     if (getId() == 0) {
/*     */       
/* 206 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 208 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.niveauFormation), getId())) {
/*     */        HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 210 */       clear(true);
/* 211 */      
/* 212 */       charger();
/*     */     } else {
/*     */       
/* 215 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 221 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\NiveauFormationB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */