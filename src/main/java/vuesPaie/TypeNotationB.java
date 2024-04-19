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
/*     */ import classesPaie.TypeNotationC;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
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
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeNotationB
/*     */   extends TypeNotationC
/*     */ {
/*     */   private static final long serialVersionUID = 7974345831064783703L;
/*     */   private TypeNotationC selected;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  35 */   private HttpSession session = HelperC.getSession();
/*     */   private boolean disableMsg;

/*     */   private List<TypeNotationC> listNotation;
/*     */   
/*     */   public DroitC getDroit() {
/*  40 */     return this.droit;
/*     */   }
/*     */   Base userFonction; private DroitC droit;
/*     */   
/*     */   public void setDroit(DroitC droit) {
/*  45 */     this.droit = droit;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  50 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  55 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  60 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  65 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  70 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  75 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<TypeNotationC> getListNotation() {
/*  80 */     return this.listNotation;
/*     */   }
/*     */   
/*     */   public void setListNotation(List<TypeNotationC> listNotation) {
/*  84 */     this.listNotation = listNotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getSerialversionuid() {
/*  89 */     return 749760639710887039L;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeNotationC getSelected() {
/*  94 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(TypeNotationC selected) {
/*  99 */     this.selected = selected;
/*     */   }
/*     */ 
public boolean isDisableMsg() {
	return disableMsg;
}
public void setDisableMsg(boolean disableMsg) {
	this.disableMsg = disableMsg;
}
			@PostConstruct
/*     */   private void init() {
/* 105 */     
/* 107 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 108 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 109 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 110 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 111 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 115 */         FacesContext context = FacesContext.getCurrentInstance();
/* 116 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 118 */       catch (IOException e) {
/*     */         
/* 120 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/* 124 */       this.listNotation = new ArrayList<TypeNotationC>();
/* 125 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 126 */       if (this.userFonction != null)
/*     */       {
/* 128 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
/* 130 */       chargement();
/*     */     } 
/*     */   }
		
/*     */ 
/*     */   
/*     */   private void chargement() {
/* 136 */     this.listNotation = FichierBaseDAO.getInstance().getAllTypeNotation();
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 141 */     if (b)
/*     */     {
/* 143 */       setCode("");
/*     */     }
/* 145 */     setId(0);
/* 146 */     setDesignation("");
/* 147 */     setPourcentageMax(0.0D);
/* 148 */     setPourcentageMaxS("");
/* 149 */     setPourcentageMin(0.0D);
/* 150 */     setPourcentageMinS("");
/* 151 */     setTauxAugmentation(0.0D);
/* 152 */     setTauxAugmentationS("");
/* 153 */     this.selected = null;
			  disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 158 */     if (!getCode().trim().equals("")) {
/*     */       disableMsg=true;
/* 160 */       this.selected = FichierBaseDAO.getInstance().getTypeNotation(getCode());
/* 161 */       if (this.selected != null) {
/*     */         disableMsg=false;
/* 163 */         setObject();
/*     */       } else {
/*     */         
/* 166 */         clear(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 173 */     if (this.selected != null) {
/*     */       
/* 175 */       setCode(this.selected.getCode());
/* 176 */       setId(this.selected.getId());
/* 177 */       setDesignation(this.selected.getDesignation());
/* 178 */       setPourcentageMax(this.selected.getPourcentageMax());
/* 179 */       setPourcentageMin(this.selected.getPourcentageMin());
/* 180 */       setPourcentageMaxS(this.selected.getPourcentageMaxS());
/* 181 */       setPourcentageMinS(this.selected.getPourcentageMinS());
/* 182 */       setTauxAugmentation(this.selected.getTauxAugmentation());
/* 183 */       setTauxAugmentationS(this.selected.getTauxAugmentationS());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 189 */     this.selected = (TypeNotationC)event.getObject();
/* 190 */     if (this.selected != null)
/*     */     {
/* 192 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 198 */     if (getCode().trim().equals("")) {
/*     */       
/* 200 */       HelperC.afficherMessage("Information", "Pr�cisez le code");
/*     */     }
/* 202 */     else if (getDesignation().trim().equals("")) {
/*     */       
/* 204 */       HelperC.afficherMessage("Information", "Pr�cisez la d�signation");
/*     */     }
/* 206 */     else if (getPourcentageMax() == 0.0D) {
/*     */       
/* 208 */       HelperC.afficherMessage("Information", "Pr�cisez le taux maximum");
/*     */     }
/* 210 */     else if (FichierBaseDAO.getInstance().getTypeNotationNotCurrent(getCode(), getId()) != null) {
/*     */       
/* 212 */       HelperC.afficherMessage("Information", "Ce code du type notation existe d�j� ");
/*     */     }
/* 214 */     else if (FichierBaseDAO.getInstance().getTypeNotationNotCurrents(getDesignation(), getId()) != null) {
/*     */       
/* 216 */       HelperC.afficherMessage("Information", "Le type notation de m�me d�signation existe d�j� ");
/*     */     } else {
/*     */       
/* 219 */       Historique hist = new Historique();
/* 220 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 221 */       hist.setOperateur(this.operateur);
/* 222 */       if (getId() == 0) {
/*     */         
/* 224 */         hist.setOperation("Cr�ation du type de notation " + getCode());
/*     */       } else {
/*     */         
/* 227 */         hist.setOperation("Modification du type de notation" + getCode());
/*     */       } 
/* 229 */       hist.setTable(Tables.getTableName(Tables.TableName.typeNotation));
/* 230 */       setHistorique(hist);
/* 231 */       if (getId() == 0 && !this.droit.isCreer()) {
/*     */         
/* 233 */         HelperC.afficherMessage("Information", "Vous n'avez pas le droit de cr�er le type de notation !");
/*     */         return;
/*     */       } 
/* 236 */       if (getId() > 0 && !this.droit.isModifier()) {
/*     */         
/* 238 */         HelperC.afficherMessage("Information", "Vous n'avez pas le droit de modifier le type de notation !");
/*     */         return;
/*     */       } 
/* 241 */       if (FichierBaseDAO.getInstance().insertUpdateTypeNotation(this)) {
/*     */         
/* 243 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 244 */         chargement();
/* 245 */         clear(true);
/*     */       } else {
/*     */         
/* 248 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 255 */     if (getId() == 0) {
/*     */       
/* 257 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 260 */       if (!this.droit.isSupprimer()) {
/*     */         
/* 262 */         HelperC.afficherMessage("Information", "Vous n'avez pas le droit de supprimer le type de notation !");
/*     */         return;
/*     */       } 
/* 265 */       if (FichierBaseDAO.getInstance().deleteTypeNotation(this)) {
/*     */         
/* 267 */         clear(true);
/* 268 */         chargement();
/* 269 */         HelperC.afficherMessage("Information", "Succes de l'op�ration");
/*     */       } else {
/*     */         
/* 272 */         HelperC.afficherMessage("D�sol�", "Echec de suppression ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 279 */     clear(true);
/*     */   }
/*     */ }
