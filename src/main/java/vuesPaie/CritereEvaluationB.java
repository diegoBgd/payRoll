/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.CritereEvaluationC;
/*     */ import classesPaie.DetailCritereEvaluationC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
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
/*     */ public class CritereEvaluationB
/*     */   extends CritereEvaluationC
/*     */ {
/*     */   private static final long serialVersionUID = 5957551831356483932L;
/*  36 */   private List<CritereEvaluationC> allCritereEvaluation = new ArrayList<CritereEvaluationC>();
/*  37 */   private List<SelectItem> listTypeCritere = new ArrayList<SelectItem>();
/*  38 */   private CritereEvaluationC selected = null;
/*     */   int index;
/*     */   private DetailCritereEvaluationC selectedDetail;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  43 */   private HttpSession session = HelperC.getSession();
/*     */   private boolean disableMsg;
/*     */   private double noteDetail;
/*     */   
/*     */   private String designationDetail;
/*     */   
/*     */   private String noteDetailS;
/*     */   private int idType;
/*     */   boolean seleced = false;
/*     */   
/*     */   public int getIdType() {
/*  54 */     return this.idType;
/*     */   }
/*     */   
/*     */   public void setIdType(int idType) {
/*  58 */     this.idType = idType;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListTypeCritere() {
/*  62 */     return this.listTypeCritere;
/*     */   }
/*     */   
/*     */   public void setListTypeCritere(List<SelectItem> listTypeCritere) {
/*  66 */     this.listTypeCritere = listTypeCritere;
/*     */   }
/*     */   
/*     */   public DetailCritereEvaluationC getSelectedDetail() {
/*  70 */     return this.selectedDetail;
/*     */   }
/*     */   
/*     */   public void setSelectedDetail(DetailCritereEvaluationC selectedDetail) {
/*  74 */     this.selectedDetail = selectedDetail;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/*  78 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  82 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/*  86 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  90 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/*  94 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  98 */     this.session = session;
/*     */   }
/*     */   
/*     */   public double getNoteDetail() {
/* 102 */     return this.noteDetail;
/*     */   }
/*     */   
/*     */   public void setNoteDetail(double noteDetail) {
/* 106 */     this.noteDetail = noteDetail;
/*     */   }
/*     */   
/*     */   public String getDesignationDetail() {
/* 110 */     return this.designationDetail;
/*     */   }
/*     */   
/*     */   public void setDesignationDetail(String designationDetail) {
/* 114 */     this.designationDetail = designationDetail;
/*     */   }
/*     */   
/*     */   public String getNoteDetailS() {
/* 118 */     return this.noteDetailS;
/*     */   }
/*     */   
/*     */   public void setNoteDetailS(String noteDetailS) {
/* 122 */     this.noteDetailS = noteDetailS;
/*     */   }
/*     */   
/*     */   public List<CritereEvaluationC> getAllCritereEvaluation() {
/* 126 */     return this.allCritereEvaluation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllCritereEvaluation(List<CritereEvaluationC> allCritereEvaluation) {
/* 131 */     this.allCritereEvaluation = allCritereEvaluation;
/*     */   }
/*     */   
/*     */   public CritereEvaluationC getSelected() {
/* 135 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(CritereEvaluationC selected) {
/* 139 */     this.selected = selected;
/*     */   }


			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void charger() {
/* 145 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 146 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 147 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 148 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/* 150 */     if (this.operateur == null || this.exercice == null) {
/*     */       try {
/* 152 */         FacesContext context = FacesContext.getCurrentInstance();
/* 153 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 154 */       } catch (IOException e) {
/*     */         
/* 156 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
				disableMsg=true;
/* 159 */       chargementCritere();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 165 */     if (getCode().trim().equals("")) {
/* 166 */       clear(true);
/*     */     } else {
/* 168 */       this.selected = FichierBaseDAO.getInstance().getCritereEvaluation(
/* 169 */           getCode());
/* 170 */       if (this.selected == null) {
/* 171 */         clear(false);
/*     */       } else {
/* 173 */         setObject();
     
/* 176 */         Iterator<DetailCritereEvaluationC> iterator = FichierBaseDAO.getInstance().getListeDetailCritereEvaluation(this.selected).iterator(); while (iterator.hasNext()) { DetailCritereEvaluationC detail = iterator.next();
         
/* 182 */           getListDetailCritere().add(detail);
/* 183 */           calculNoteGeneral(); }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void clear(boolean b) {
/* 190 */     if (b)
/*     */     {
/* 192 */       setCode("");
				} 
			  disableMsg=false;
/* 193 */     setId(0);
/* 194 */     setDesignation("");
/* 195 */     setNoteGeneral(0.0D);
/* 196 */     setNoteGeneralS("");
/* 197 */     this.designationDetail = "";
/* 198 */     this.noteDetail = 0.0D;
/* 199 */     this.noteDetailS = "";
/* 200 */     this.selected = null;
/* 201 */     this.idType = 0;
/* 202 */     getListDetailCritere().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 207 */     if (this.selected != null) {
/* 208 */       setId(this.selected.getId());
/* 209 */       setCode(this.selected.getCode());
/* 210 */       setDesignation(this.selected.getDesignation());
/* 211 */       setNoteGeneral(this.selected.getNoteGeneral());
/* 212 */       setNoteGeneralS(this.selected.getNoteGeneralS());
				disableMsg=false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDetail() {
/* 219 */     this.index = getListDetailCritere().indexOf(this.selectedDetail);
/* 220 */     this.designationDetail = this.selectedDetail.getDesignation();
/* 221 */     this.noteDetail = this.selectedDetail.getNote();
/* 222 */     this.seleced = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearDetail() {
/* 227 */     this.designationDetail = "";
/* 228 */     this.noteDetail = 0.0D;
/* 229 */     this.seleced = false;
/* 230 */     this.index = 0;
/* 231 */     this.selectedDetail = null;
/*     */   }
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 235 */     clear(true);
/* 236 */     this.selected = (CritereEvaluationC)event.getObject();
/* 237 */     if (this.selected != null) {
				disableMsg=true;
/* 238 */       setObject();
/* 239 */       setListDetailCritere(FichierBaseDAO.getInstance()
/* 240 */           .getListeDetailCritereEvaluation(this.selected));
/* 241 */       for (DetailCritereEvaluationC detail : getListDetailCritere()) {
/*     */         
/* 243 */         detail.setIndex(getListDetailCritere().indexOf(detail));
/* 244 */         calculNoteGeneral();
/*     */       } 
/*     */       
/* 247 */       PrimeFaces.current().executeScript("PF('dlgPnl').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelectedDetail(SelectEvent event) {
/* 253 */     this.selectedDetail = (DetailCritereEvaluationC)event.getObject();
/* 254 */     if (this.selectedDetail != null) {
/* 255 */       this.index = getListDetailCritere().indexOf(this.selectedDetail);
/* 256 */       setDetail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ajouter() {
/* 261 */     if (this.noteDetail > 0.0D) {
/* 262 */       if (this.selectedDetail == null) {
/* 263 */         this.selectedDetail = new DetailCritereEvaluationC();
/* 264 */         this.selectedDetail.setIndex(getListDetailCritere().size());
/*     */       } 
/* 266 */       this.selectedDetail.setDesignation(this.designationDetail);
/* 267 */       this.selectedDetail.setNote(this.noteDetail);
/*     */       
/* 269 */       if (!this.seleced) {
/* 270 */         getListDetailCritere().add(this.selectedDetail);
/*     */       } else {
/* 272 */         this.selectedDetail.setIndex(this.index);
/* 273 */         getListDetailCritere().remove(this.index);
/* 274 */         getListDetailCritere().add(this.index, this.selectedDetail);
/*     */       } 
/* 276 */       calculNoteGeneral();
/*     */       
/* 278 */       clearDetail();
/*     */     } else {
/* 280 */       HelperC.afficherMessage("Information", 
/* 281 */           "La note doit �tre sup�rieure � z�ro! ");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void calculNoteGeneral() {
/* 286 */     double noteGen = 0.0D;
/* 287 */     for (DetailCritereEvaluationC deta : getListDetailCritere()) {
/* 288 */       noteGen += deta.getNote();
/*     */     }
/* 290 */     setNoteGeneral(noteGen);
/* 291 */     setNoteGeneralS(HelperC.TraitementMontant.getMontantFormate(
/* 292 */           getNoteGeneral(), 0));
/*     */   }
/*     */   
/*     */   public void enlever() {
/* 296 */     if (getListDetailCritere().size() > 0 && 
/* 297 */       this.selectedDetail != null) {
/*     */       
/* 299 */       getListDetailCritereDeleted().add(this.selectedDetail);
/* 300 */       getListDetailCritere().remove(this.selectedDetail);
/*     */       
/* 302 */       calculNoteGeneral();
/*     */     } 
/*     */     
/* 305 */     clearDetail();
/*     */   }
/*     */   
/*     */   public void enregistrer() {
/* 309 */     if (getCode().trim().equals("") || 
/* 310 */       getDesignation().trim().equals("") || 
/* 311 */       getNoteGeneral() == 0.0D) {
/* 312 */       HelperC.afficherMessage("Information", 
/* 313 */           "Completez tous les champs n�cessaires");
/* 314 */     } else if (FichierBaseDAO.getInstance().getCritereEvaluation(
/* 315 */         getCode(), getId()) != null) {
/* 316 */       HelperC.afficherMessage("Information", 
/* 317 */           "Ce code de critere existe d�j� ");
/*     */     } else {
/*     */       
/* 320 */       Historique hist = new Historique();
/* 321 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 322 */       hist.setOperateur(this.operateur);
/* 323 */       if (getId() == 0) {
/* 324 */         hist.setOperation("Cr�ation d'un crit�re d'�valuation " + 
/* 325 */             getCode());
/*     */       } else {
/* 327 */         hist.setOperation("Modification d'un crit�re d'�valuation " + 
/* 328 */             getCode());
/* 329 */       }  hist.setTable(Tables.getTableName(Tables.TableName.critereEvaluation));
/* 330 */       setHistorique(hist);
/* 331 */       if (FichierBaseDAO.getInstance()
/* 332 */         .insertUpdateCritereEvaluation(this)) {
/* 333 */         clear(true);
/* 334 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/*     */       } else {
/*     */         
/* 337 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void chargementCritere() {
/* 343 */     this.allCritereEvaluation = FichierBaseDAO.getInstance()
/* 344 */       .getAllCritereEvaluation();
/*     */   }
/*     */   
/*     */   public void supprimer() {
/* 348 */     if (getId() == 0) {
/* 349 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 353 */       Historique hist = new Historique();
/* 354 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 355 */       hist.setOperateur(this.operateur);
/* 356 */       if (getId() == 0) {
/* 357 */         hist.setOperation("Cr�ation d'un crit�re d'�valuation " + 
/* 358 */             getCode());
/*     */       } else {
/* 360 */         hist.setOperation("Modification d'un crit�re d'�valuation " + 
/* 361 */             getCode());
/* 362 */       }  hist.setTable(Tables.getTableName(Tables.TableName.critereEvaluation));
/* 363 */       setHistorique(hist);
/* 364 */       if (FichierBaseDAO.getInstance().deleteCritereEvaluation(this)) {
/*     */         
/* 366 */         clear(true);
/* 367 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 368 */         chargementCritere();
/*     */       } else {
/* 370 */         HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 377 */     clear(true);
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\CritereEvaluationB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */