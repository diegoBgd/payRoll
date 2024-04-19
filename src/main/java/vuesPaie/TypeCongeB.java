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
/*     */ import classesPaie.TypeCongeC;
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
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeCongeB
/*     */   extends TypeCongeC
/*     */ {
/*     */   private static final long serialVersionUID = -3785327091564332422L;
/*     */   private TypeCongeC selected;
/*     */   private List<TypeCongeC> typeConges;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
 			private boolean disableMsg;
/*  41 */   private HttpSession session = HelperC.getSession();
/*     */   Base userFonction;
/*     */   
/*     */   public List<TypeCongeC> getTypeConges() {
/*  45 */     return this.typeConges;
/*     */   }
/*     */   public void setTypeConges(List<TypeCongeC> typeConges) {
/*  48 */     this.typeConges = typeConges;
/*     */   }
/*     */   public OperateurC getOperateur() {
/*  51 */     return this.operateur;
/*     */   }
/*     */   public void setOperateur(OperateurC operateur) {
/*  54 */     this.operateur = operateur;
/*     */   }
/*     */   public ExerciceC getExercice() {
/*  57 */     return this.exercice;
/*     */   }
/*     */   public void setExercice(ExerciceC exercice) {
/*  60 */     this.exercice = exercice;
/*     */   }
/*     */   public HttpSession getSession() {
/*  63 */     return this.session;
/*     */   }
/*     */   public void setSession(HttpSession session) {
/*  66 */     this.session = session;
/*     */   }
/*     */   
/*     */   public TypeCongeC getSelected() {
/*  70 */     return this.selected;
/*     */   }
/*     */   public void setSelected(TypeCongeC selected) {
/*  73 */     this.selected = selected;
/*     */   }
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/*  80 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  81 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  82 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  83 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  84 */     if (this.exercice == null || this.operateur == null) {
/*     */       
/*     */       try {
/*  87 */         FacesContext context = FacesContext.getCurrentInstance();
/*  88 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*  89 */       } catch (IOException e) {
/*     */         
/*  91 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/*  95 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/*  96 */       if (this.userFonction != null) {
/*  97 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge);
/*     */       }
/*  99 */       this.typeConges = FichierBaseDAO.getInstance().getAllTypeConge();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 107 */     if (b)
/* 108 */       setCode(""); 
/* 109 */     setId(0);
/* 110 */     setDesignation("");
/* 111 */     setSorteConge(0);
/* 112 */     setAnnuel(false);
/* 113 */     this.selected = null;
			  disableMsg=true;
			  setNombreJour(0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 118 */     if (this.selected != null) {
/*     */        disableMsg=false;
/* 120 */       setId(this.selected.getId());
/* 121 */       setCode(this.selected.getCode());
/* 122 */       setDesignation(this.selected.getDesignation());
/* 123 */       setSorteConge(this.selected.getSorteConge());
/* 124 */       setAnnuel(this.selected.isAnnuel());
				setNombreJour(this.selected.getNombreJour());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 130 */     if (getCode().trim().equals("")) {
/* 131 */       clear(true);
/*     */     } else {
/* 133 */       this.selected = FichierBaseDAO.getInstance().getTypeConge(getCode());
/* 134 */       if (this.selected == null) {
/* 135 */         clear(false);
/*     */       } else {
/* 137 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 143 */     this.selected = (TypeCongeC)event.getObject();
/* 144 */     if (this.selected != null) {
/* 145 */       setObject();
/*     */     }
/*     */   }
/*     */   
/*     */   public void enregistrer() {
/* 150 */     if (getId() == 0 && !this.droit.isCreer()) {
/* 151 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/* 152 */     } else if (getId() > 0 && !this.droit.isModifier()) {
/* 153 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/* 154 */     } else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/* 155 */       HelperC.afficherMessage("Information", "Code et D�signation sont obligatoires");
/* 156 */     } else if (getSorteConge() == 0) {
/* 157 */       HelperC.afficherMessage("Information", "Le sorte de cong� est obligatoire");
/*     */     }
/* 159 */     else if (FichierBaseDAO.getInstance().getTypeConge(getCode(), getId()) != null) {
/* 160 */       HelperC.afficherMessage("Information", "Ce code existe d�j� ");
/* 161 */     } else if (FichierBaseDAO.getInstance().getTypeConges(getDesignation(), getId()) != null) {
/* 162 */       HelperC.afficherMessage("Information", "Cette d�signation existe d�j�");
/*     */     }
/*     */     else {
/*     */       
/* 166 */      
/* 175 */       if (FichierBaseDAO.getInstance().insertUpdateTypeConge(this)) {
/* 176 */         clear(true);
/* 177 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 178 */         this.typeConges = FichierBaseDAO.getInstance().getAllTypeConge();
/*     */       } else {
/*     */         
/* 181 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void supprimer() {
/* 187 */     if (getId() == 0) {
/* 188 */       HelperC.afficherDeleteMessage();
/* 189 */     } else if (getId() > 0 && !this.droit.isSupprimer()) {
/* 190 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 192 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeConge), getId())) {
/* 193 */       clear(true);
/* 194 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 195 */       this.typeConges = FichierBaseDAO.getInstance().getAllTypeConge();
/*     */     } else {
/*     */       
/* 198 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 204 */     clear(true);
/*     */   }
/*     */ }

