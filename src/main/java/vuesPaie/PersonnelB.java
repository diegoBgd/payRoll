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
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class PersonnelB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = 5731144679340826201L;
/*     */   private Base personnel;
/*     */   private List<Base> personnels;
/*     */   private OperateurC operateur;
/*  38 */   private FacesMessage msg = null;
/*  39 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   private ExerciceC exercice;
/*     */   
/*     */   public FacesMessage getMsg() {
/*  44 */     return this.msg;
/*     */   }
/*     */   private DroitC droit; Base userFonction;
/*     */   
/*     */   public void setMsg(FacesMessage msg) {
/*  49 */     this.msg = msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  54 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  59 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  64 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  69 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  74 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  79 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getPersonnel() {
/*  84 */     return this.personnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/*  89 */     this.personnel = personnel;
/*     */   }
/*     */   
/*     */   public List<Base> getPersonnels() {
/*  93 */     return this.personnels;
/*     */   }
/*     */   public void setPersonnels(List<Base> personnels) {
/*  96 */     this.personnels = personnels;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/* 102 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 103 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 104 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 105 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 106 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 110 */         FacesContext context = FacesContext.getCurrentInstance();
/* 111 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 113 */       catch (IOException e) {
/*     */         
/* 115 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 119 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 120 */       if (this.userFonction != null)
/*     */       {
/* 122 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge);
/*     */       }
/* 124 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 130 */     this.personnels = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 135 */     if (getCode().trim().equals("")) {
/*     */       
/* 137 */       clear(true);
/*     */     } else {
/*     */       
/* 140 */       this.personnel = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.personnel));
/* 141 */       if (this.personnel == null) {
/*     */         
/* 143 */         clear(false);
/*     */       } else {
/*     */         
/* 146 */         affecter();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 153 */     if (b)
/*     */     {
/* 155 */       setCode("");
/*     */     }
/* 157 */     setId(0);
/* 158 */     this.personnel = null;
/* 159 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelectedPersonnel(SelectEvent event) {
/* 164 */     this.personnel = (Base)event.getObject();
/* 165 */     if (this.personnel != null)
/*     */     {
/* 167 */       affecter();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void affecter() {
/* 173 */     if (this.personnel != null) {
/*     */       
/* 175 */       setId(this.personnel.getId());
/* 176 */       setCode(this.personnel.getCode());
/* 177 */       setDesignation(this.personnel.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 183 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/*     */     try {
/* 190 */       if (getId() == 0 && !this.droit.isCreer()) {
/*     */         
/* 192 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */       }
/* 194 */       else if (getId() > 0 && !this.droit.isModifier()) {
/*     */         
/* 196 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */       }
/* 198 */       else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */         
/* 200 */         this.msg = new FacesMessage("Information", "Code et D�signation obligatoires");
/* 201 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */       } else {
/*     */         
/* 204 */         this.personnel = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.personnel));
/* 205 */         if (this.personnel == null) {
/*     */           
/* 207 */           Historique hist = new Historique();
/* 208 */           hist.setDateOperation(Calendar.getInstance().getTime());
/* 209 */           hist.setOperateur(this.operateur);
/* 210 */           if (getId() == 0) {
/*     */             
/* 212 */             hist.setOperation("Cr�ation du personnel " + getCode());
/*     */           } else {
/*     */             
/* 215 */             hist.setOperation("Modification du personnel " + getCode());
/*     */           } 
/* 217 */           hist.setTable(Tables.getTableName(Tables.TableName.personnel));
/* 218 */           setHistorique(hist);
/* 219 */           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.personnel))) {
/*     */             
/* 221 */             clear(true);
/* 222 */             this.msg = new FacesMessage("F�licitaions", "Succ�s de l'Op�ration");
/* 223 */             FacesContext.getCurrentInstance().addMessage(null, this.msg);
/* 224 */             charger();
/*     */           } else {
/*     */             
/* 227 */             this.msg = new FacesMessage("D�sol�", "Echec de l'Op�ration");
/* 228 */             FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */           } 
/*     */         } else {
/*     */           
/* 232 */           this.msg = new FacesMessage("Information", "Ce personnel vient d'�tre cr�ee par un autre op�rateur");
/* 233 */           FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */         }
/*     */       
/*     */       } 
/* 237 */     } catch (Exception e) {
/*     */       
/* 239 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 247 */       if (getId() == 0) {
/*     */         
/* 249 */         this.msg = new FacesMessage("Information", "Pr�cisez le departement � supprimer");
/* 250 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */       }
/* 252 */       else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */         
/* 254 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */       }
/* 256 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.personnel))) {
/*     */         
/* 258 */         clear(true);
/* 259 */         this.msg = new FacesMessage("F�licitation", "Succ�s de l'Op�ration");
/* 260 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/* 261 */         charger();
/*     */       } else {
/*     */         
/* 264 */         this.msg = new FacesMessage("D�sol�", "Echec de l'Op�ration");
/* 265 */         FacesContext.getCurrentInstance().addMessage(null, this.msg);
/*     */       }
/*     */     
/* 268 */     } catch (Exception e) {
/*     */       
/* 270 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fermer() {
/*     */     try {
/* 278 */       FacesContext.getCurrentInstance().getExternalContext().redirect("/asystPaie/masterPage.jsf");
/*     */     }
/* 280 */     catch (IOException e) {
/*     */       
/* 282 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\PersonnelB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */