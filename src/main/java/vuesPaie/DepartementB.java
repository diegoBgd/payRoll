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
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class DepartementB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = 5726012767319035278L;
/*     */   private Base departement;
/*     */   private List<Base> departements;
/*  38 */   private FacesMessage msg = null;
/*  39 */   private HttpSession session = HelperC.getSession();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   
/*     */   public Base getDepartement() {
/*  44 */     return this.departement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDepartement(Base departement) {
/*  49 */     this.departement = departement;
/*     */   }
/*     */   
/*     */   public List<Base> getDepartements() {
/*  53 */     return this.departements;
/*     */   }
/*     */   
/*     */   public void setDepartements(List<Base> departements) {
/*  57 */     this.departements = departements;
/*     */   }
/*     */ 
/*     */   
/*     */   public FacesMessage getMsg() {
/*  62 */     return this.msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMsg(FacesMessage msg) {
/*  67 */     this.msg = msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  72 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  77 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  82 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  87 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  92 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  97 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/* 103 */     this.operateur = new OperateurC();
/* 104 */     this.exercice = new ExerciceC();
/* 105 */     this.operateur = (OperateurC)this.session.getAttribute("operateur");
/* 106 */     this.exercice = (ExerciceC)this.session.getAttribute("exercice");
/* 107 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/*     */         
/* 111 */         FacesContext context = FacesContext.getCurrentInstance();
/* 112 */         context.getExternalContext().redirect("/payRoll/identification.xhtml");
/*     */       }
/* 114 */       catch (IOException e) {
/*     */         
/* 116 */         e.printStackTrace();
/*     */       } 
/*     */     }
/* 119 */     charger();
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 124 */     this.departements = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.departement));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 129 */     if (getCode().trim().equals("")) {
/*     */       
/* 131 */       clear(true);
/*     */     } else {
/*     */       
/* 134 */       this.departement = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.departement));
/* 135 */       if (this.departement == null) {
/*     */         
/* 137 */         clear(false);
/*     */       } else {
/*     */         
/* 140 */         affecter();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 147 */     if (b)
/*     */     {
/* 149 */       setCode("");
/*     */     }
/* 151 */     setId(0);
/* 152 */     this.departement = null;
/* 153 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelectedDepartement(SelectEvent event) {
/* 158 */     this.departement = (Base)event.getObject();
/* 159 */     if (this.departement != null)
/*     */     {
/* 161 */       affecter();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void affecter() {
/* 167 */     if (this.departement != null) {
/*     */       
/* 169 */       setId(this.departement.getId());
/* 170 */       setCode(this.departement.getCode());
/* 171 */       setDesignation(this.departement.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 177 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/*     */     try {
/* 184 */       if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */         
/* 186 */         this.msg = new FacesMessage("Information", "Code et Désignation obligatoires");
/* 187 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */       } else {
/*     */         
/* 190 */         this.departement = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.departement));
/* 191 */         if (this.departement == null) {
/*     */           
/* 193 */           Historique hist = new Historique();
/* 194 */           hist.setDateOperation(Calendar.getInstance().getTime());
/* 195 */           hist.setOperateur(this.operateur);
/* 196 */           if (getId() == 0) {
/*     */             
/* 198 */             hist.setOperation("Création du département " + getCode());
/*     */           } else {
/*     */             
/* 201 */             hist.setOperation("Modification du département " + getCode());
/*     */           } 
/* 203 */           hist.setTable(Tables.getTableName(Tables.TableName.departement));
/* 204 */           setHistorique(hist);
/* 205 */           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.departement))) {
/*     */             
/* 207 */             clear(true);
/* 208 */             this.msg = new FacesMessage("Félicitaions", "Succès de l'Opération");
/* 209 */             FacesContext.getCurrentInstance().addMessage(null, this.msg);
/* 210 */             charger();
/*     */           } else {
/*     */             
/* 213 */             this.msg = new FacesMessage("Désolé", "Echec de l'Opération");
/* 214 */             FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */           } 
/*     */         } else {
/*     */           
/* 218 */           this.msg = new FacesMessage("Information", "Ce departement vient d'être créee par un autre opérateur");
/* 219 */           FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */         }
/*     */       
/*     */       } 
/* 223 */     } catch (Exception e) {
/*     */       
/* 225 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 233 */       if (getId() == 0) {
/*     */         
/* 235 */         this.msg = new FacesMessage("Information", "Précisez le departement à supprimer");
/* 236 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */       }
/* 238 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.departement))) {
/*     */         
/* 240 */         clear(true);
/* 241 */         this.msg = new FacesMessage("Félicitation", "Succès de l'Opération");
/* 242 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/* 243 */         charger();
/*     */       } else {
/*     */         
/* 246 */         this.msg = new FacesMessage("Désolé", "Echec de l'Opération");
/* 247 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */       }
/*     */     
/* 250 */     } catch (Exception e) {
/*     */       
/* 252 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   

/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\DepartementB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */