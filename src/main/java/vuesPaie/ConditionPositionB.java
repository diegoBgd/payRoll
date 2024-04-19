/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.ConditionPositionC;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
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
/*     */ public class ConditionPositionB
/*     */   extends ConditionPositionC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 749760639710887039L;
/*     */   private ConditionPositionC selected;
/*     */   private int idPosition;
/*  35 */   private List<ConditionPositionC> listConditionsPostition = new ArrayList<ConditionPositionC>();
/*     */   private OperateurC operateur;
/*  37 */   private HttpSession session = HelperC.getSession();
/*     */   private ExerciceC exercice;
/*     */   
/*     */   public List<ConditionPositionC> getListConditionsPostition() {
/*  41 */     return this.listConditionsPostition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListConditionsPostition(List<ConditionPositionC> listConditionsPostition) {
/*  46 */     this.listConditionsPostition = listConditionsPostition;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConditionPositionC getSelected() {
/*  51 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(ConditionPositionC selected) {
/*  56 */     this.selected = selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdPosition() {
/*  61 */     return this.idPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdPosition(int idPosition) {
/*  66 */     this.idPosition = idPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  73 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  78 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  83 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  88 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  93 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  98 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getSerialversionuid() {
/* 103 */     return 749760639710887039L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/* 111 */     this.operateur = new OperateurC();
/* 112 */     this.exercice = new ExerciceC();
/* 113 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 114 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 115 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 116 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 117 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/*     */         
/* 121 */         FacesContext context = FacesContext.getCurrentInstance();
/* 122 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 124 */       catch (IOException e) {
/*     */         
/* 126 */         e.printStackTrace();
/*     */       } 
/*     */     }
/* 129 */     this.listConditionsPostition = FichierBaseDAO.getInstance().getListeConditionPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 134 */     if (b)
/*     */     {
/* 136 */       setId(0);
/*     */     }
/* 138 */     setCondition("");
/* 139 */     setPosition(null);
/* 140 */     setLibellePosition("");
/* 141 */     this.selected = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 146 */     if (this.selected != null) {
/*     */       
/* 148 */       setId(this.selected.getId());
/* 149 */       setCondition(this.selected.getCondition());
/* 150 */       setLibellePosition(this.selected.getLibellePosition());
/* 151 */       setPosition(this.selected.getPosition());
/* 152 */       if (getPosition() != null)
/*     */       {
/* 154 */         this.idPosition = Constante.getPosition(getPosition());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changePosition(ValueChangeEvent event) {
/* 161 */     this.idPosition = ((Integer)event.getNewValue()).intValue();
/* 162 */     if (this.idPosition != 0)
/*     */     {
/* 164 */       setPosition(Constante.getPosition(this.idPosition));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 170 */     this.selected = (ConditionPositionC)event.getObject();
/* 171 */     if (this.selected != null)
/*     */     {
/* 173 */       setObject();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 179 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 184 */     if (getPosition() == null) {
/*     */       
/* 186 */       HelperC.afficherMessage("Information", "Veillez selectionner la position!");
/*     */     }
/* 188 */     else if (getCondition().equalsIgnoreCase("")) {
/*     */       
/* 190 */       HelperC.afficherMessage("Information", "Veillez saisir la condition");
/*     */     } else {
/*     */       
/* 193 */       this.selected = FichierBaseDAO.getInstance().getConditionPosition(getCondition(), getPosition(), getId());
/* 194 */       if (this.selected == null) {
/*     */         
/* 196 */         if (FichierBaseDAO.getInstance().insertUpdateConditionPosition(this)) {
/*     */           
/* 198 */           HelperC.afficherMessage("Information", "Succ�s d'enregistrement");
/* 199 */           this.listConditionsPostition = FichierBaseDAO.getInstance().getListeConditionPosition();
/* 200 */           clear(true);
/*     */         } else {
/*     */           
/* 203 */           HelperC.afficherMessage("information", "Echec d'enregistrement");
/*     */         } 
/*     */       } else {
/*     */         
/* 207 */         HelperC.afficherMessage("information", "Cet employ� est d�j� dans ce grade");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 216 */       if (getId() == 0) {
/*     */         
/* 218 */         HelperC.afficherDeleteMessage();
/*     */       }
/* 220 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.conditionPosition))) {
/*     */         
/* 222 */         HelperC.afficherMessage("F�licitation", "Succ�s de l'Op�ration");
/*     */       } else {
/*     */         
/* 225 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */       }
/*     */     
/* 228 */     } catch (Exception e) {
/*     */       
/* 230 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fermer() {
/*     */     try {
/* 238 */       FacesContext.getCurrentInstance().getExternalContext().redirect("/asystPaie/masterPage.jsf");
/*     */     }
/* 240 */     catch (IOException e) {
/*     */       
/* 242 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\ConditionPositionB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */