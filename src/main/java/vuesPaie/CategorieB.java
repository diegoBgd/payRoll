/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class CategorieB
/*     */   extends Base
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2150297787964447291L;
/*     */   private Base categorie;
/*     */   private List<Base> categories;
/*  35 */   private FacesMessage msg = null;
/*  36 */   private HttpSession session = HelperC.getSession();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   
/*     */   public FacesMessage getMsg() {
/*  41 */     return this.msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMsg(FacesMessage msg) {
/*  46 */     this.msg = msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  51 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  56 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  61 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  66 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  71 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  76 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getCategorie() {
/*  81 */     return this.categorie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCategorie(Base categorie) {
/*  86 */     this.categorie = categorie;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Base> getCategories() {
/*  91 */     return this.categories;
/*     */   }
/*     */   
/*     */   public void setCategories(List<Base> categories) {
/*  95 */     this.categories = categories;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/* 101 */     this.operateur = new OperateurC();
/* 102 */     this.exercice = new ExerciceC();
/* 103 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 104 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 105 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 106 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 107 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/*     */         
/* 111 */         FacesContext context = FacesContext.getCurrentInstance();
/* 112 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
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
/* 124 */     this.categories = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.categoriePersonnel));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 129 */     if (getCode().trim().equals("")) {
/*     */       
/* 131 */       clear(true);
/*     */     } else {
/*     */       
/* 134 */       this.categorie = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.categoriePersonnel));
/* 135 */       if (this.categorie == null) {
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
/* 152 */     this.categorie = null;
/* 153 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelectedCategorie(SelectEvent event) {
/* 158 */     this.categorie = (Base)event.getObject();
/* 159 */     if (this.categorie != null)
/*     */     {
/* 161 */       affecter();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void affecter() {
/* 167 */     if (this.categorie != null) {
/*     */       
/* 169 */       setId(this.categorie.getId());
/* 170 */       setCode(this.categorie.getCode());
/* 171 */       setDesignation(this.categorie.getDesignation());
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
/* 186 */         this.msg = new FacesMessage("Information", "Code et D�signation obligatoires");
/* 187 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */       } else {
/*     */         
/* 190 */         this.categorie = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.categoriePersonnel));
/* 191 */         if (this.categorie == null) {
/*     */           
/* 193 */           Historique hist = new Historique();
/* 194 */           hist.setDateOperation(Calendar.getInstance().getTime());
/* 195 */           hist.setOperateur(this.operateur);
/* 196 */           if (getId() == 0) {
/*     */             
/* 198 */             hist.setOperation("Cr�ation de la cat�gorie " + getCode());
/*     */           } else {
/*     */             
/* 201 */             hist.setOperation("Modification de la cat�gorie " + getCode());
/*     */           } 
/* 203 */           hist.setTable(Tables.getTableName(Tables.TableName.categoriePersonnel));
/* 204 */           setHistorique(hist);
/* 205 */           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.categoriePersonnel))) {
/*     */             
/* 207 */             clear(true);
/* 208 */             this.msg = new FacesMessage("F�licitaions", "Succ� de l'Op�ration");
/* 209 */             FacesContext.getCurrentInstance().addMessage(null, this.msg);
/* 210 */             charger();
/*     */           } else {
/*     */             
/* 213 */             this.msg = new FacesMessage("D�sol�", "Echec de l'Op�ration");
/* 214 */             FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */           } 
/*     */         } else {
/*     */           
/* 218 */           this.msg = new FacesMessage("Information", "Cette cat�gorie vient d'�tre cr�ee par un autre op�rateur");
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
/* 235 */        
/* 236 */        HelperC.afficherDeleteMessage();
/*     */       }
/* 238 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.categoriePersonnel))) {
/*     */         
/* 240 */         clear(true);
/* 241 */         this.msg = new FacesMessage("F�licitation", "Succ� de l'Op�ration");
/* 242 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/* 243 */         charger();
/*     */       } else {
/*     */         
/* 246 */         this.msg = new FacesMessage("D�sol�", "Echec de l'Op�ration");
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
/*     */  
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\CategorieB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */