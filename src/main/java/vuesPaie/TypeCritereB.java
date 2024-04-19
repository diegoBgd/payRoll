/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import classesPaie.TypeCritereC;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeCritereB
/*     */   extends TypeCritereC
/*     */ {
/*     */   private static final long serialVersionUID = 2862485259253372895L;
/*  34 */   private List<TypeCritereC> allCriteres = new ArrayList<TypeCritereC>();
/*  35 */   private TypeCritereC selected = null;
/*  36 */   private HttpSession session = HelperC.getSession();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   
/*     */   public OperateurC getOperateur() {
/*  41 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  46 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  51 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  56 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  61 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  66 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<TypeCritereC> getAllCriteres() {
/*  71 */     return this.allCriteres;
/*     */   }
/*     */   
/*     */   public void setAllCriteres(List<TypeCritereC> allCriteres) {
/*  75 */     this.allCriteres = allCriteres;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeCritereC getSelected() {
/*  80 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(TypeCritereC selected) {
/*  85 */     this.selected = selected;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void charger() {
/*  91 */     this.operateur = new OperateurC();
/*  92 */     this.exercice = new ExerciceC();
/*  93 */     this.operateur = (OperateurC)this.session.getAttribute("operateur");
/*  94 */     this.exercice = (ExerciceC)this.session.getAttribute("exercice");
/*  95 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/*     */         
/*  99 */         FacesContext context = FacesContext.getCurrentInstance();
/* 100 */         context.getExternalContext().redirect("/payRoll/identification.xhtml");
/*     */       }
/* 102 */       catch (IOException e) {
/*     */         
/* 104 */         e.printStackTrace();
/*     */       } 
/*     */     }
/* 107 */     this.allCriteres = FichierBaseDAO.getInstance().getAllTypeCritere();
/*     */   }
/*     */ 
/*     */   
/*     */   public void findByCode() {
/* 112 */     if (getCode().equalsIgnoreCase("")) {
/*     */       
/* 114 */       clear(true);
/*     */     } else {
/*     */       
/* 117 */       this.selected = FichierBaseDAO.getInstance().getTypeCritere(getCode());
/* 118 */       if (this.selected == null) {
/*     */         
/* 120 */         clear(false);
/*     */       } else {
/*     */         
/* 123 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 130 */     if (b)
/*     */     {
/* 132 */       setCode("");
/*     */     }
/* 134 */     setId(0);
/* 135 */     setDesignation("");
/* 136 */     setNoteAppreciationGlobale(0.0D);
/* 137 */     setNoteAppreciationGlobaleS("");
/* 138 */     this.selected = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 143 */     if (this.selected != null) {
/*     */       
/* 145 */       setId(this.selected.getId());
/* 146 */       setCode(this.selected.getCode());
/* 147 */       setDesignation(this.selected.getDesignation());
/* 148 */       setNoteAppreciationGlobale(this.selected.getNoteAppreciationGlobale());
/* 149 */       setNoteAppreciationGlobaleS(this.selected.getNoteAppreciationGlobaleS());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeNoteAppreciation() {
/*     */     try {
/* 156 */       setNoteAppreciationGlobale(Double.valueOf(getNoteAppreciationGlobaleS().replace(" ", "").replace(",", ".").trim()).doubleValue());
/* 157 */     } catch (Exception e) {
/* 158 */       setNoteAppreciationGlobale(0.0D);
/*     */     } finally {
/* 160 */       if (getNoteAppreciationGlobale() < 0.0D) {
/* 161 */         setNoteAppreciationGlobale(0.0D);
/*     */       }
/* 163 */       if (getNoteAppreciationGlobale() > 0.0D) {
/*     */         
/* 165 */         setNoteAppreciationGlobaleS(HelperC.TraitementMontant.getMontantFormate(getNoteAppreciationGlobale(), 2));
/* 166 */         setNoteAppreciationGlobale(Double.valueOf(getNoteAppreciationGlobaleS().replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */       } else {
/*     */         
/* 169 */         setNoteAppreciationGlobaleS("");
/* 170 */         setNoteAppreciationGlobale(0.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 180 */     this.selected = (TypeCritereC)event.getObject();
/* 181 */     if (this.selected != null)
/*     */     {
/* 183 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 189 */     if (getDesignation().trim().equals("")) {
/*     */       
/* 191 */       HelperC.afficherMessage("Information", "Completez tous les champs n�cessaires");
/*     */     }
/* 193 */     else if (FichierBaseDAO.getInstance().getTypeCritere(getCode(), getId()) != null) {
/*     */       
/* 195 */       HelperC.afficherMessage("Information", "Ce code de type critere existe d�j� ");
/*     */     } else {
/*     */       
/* 198 */       Historique hist = new Historique();
/* 199 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 200 */       hist.setOperateur(this.operateur);
/* 201 */       if (getId() == 0) {
/*     */         
/* 203 */         hist.setOperation("Cr�ation d'un type crit�re d'�valuation " + getCode());
/*     */       } else {
/*     */         
/* 206 */         hist.setOperation("Modification d'un type crit�re d'�valuation " + getCode());
/*     */       } 
/* 208 */       hist.setTable(Tables.getTableName(Tables.TableName.typeCritere));
/* 209 */       setHistorique(hist);
/* 210 */       if (FichierBaseDAO.getInstance().insertUpdateTypeCritere(this)) {
/*     */         
/* 212 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 213 */         this.allCriteres = FichierBaseDAO.getInstance().getAllTypeCritere();
/* 214 */         clear(true);
/*     */       } else {
/*     */         
/* 217 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 224 */     if (getId() == 0) {
/*     */       
/* 226 */       HelperC.afficherMessage("Information", "Pr�cisez le type de critere � supprimer");
/*     */     }
/* 228 */     else if (FichierBaseDAO.getInstance().deleteTypeCritere(this)) {
/*     */       
/* 230 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 231 */       this.allCriteres = FichierBaseDAO.getInstance().getAllTypeCritere();
/* 232 */       clear(true);
/*     */     } else {
/*     */       
/* 235 */       HelperC.afficherMessage("D�sol�", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 241 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   

/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\TypeCritereB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */