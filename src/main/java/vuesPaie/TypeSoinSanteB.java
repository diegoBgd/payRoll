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
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeSoinSanteB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = -7740075500322127672L;
/*     */   private Base typeSoinSante;
/*     */   private List<Base> listeTypeSoinSante;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  37 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   public Base getTypeSoinSante() {
/*  42 */     return this.typeSoinSante;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeSoinSante(Base typeSoinSante) {
/*  47 */     this.typeSoinSante = typeSoinSante;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Base> getListeTypeSoinSante() {
/*  52 */     return this.listeTypeSoinSante;
/*     */   }
/*     */   
/*     */   public void setListeTypeSoinSante(List<Base> listeTypeSoinSante) {
/*  56 */     this.listeTypeSoinSante = listeTypeSoinSante;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  61 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  66 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  71 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  76 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  81 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  86 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  92 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  93 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  94 */     if (codeOperateur == null || codeExercice == null) {
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
/*     */       
/* 107 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 108 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 109 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 110 */       if (this.userFonction != null)
/*     */       {
/* 112 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
/* 114 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 120 */     this.listeTypeSoinSante = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeSoinSante));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 125 */     if (getCode().trim().equals("")) {
/*     */       
/* 127 */       clear(true);
/*     */     } else {
/*     */       
/* 130 */       this.typeSoinSante = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeSoinSante));
/* 131 */       if (this.typeSoinSante == null) {
/*     */         
/* 133 */         clear(false);
/*     */       } else {
/*     */         
/* 136 */         affecter();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 143 */     if (b)
/*     */     {
/* 145 */       setCode("");
/*     */     }
/* 147 */     setId(0);
/* 148 */     this.typeSoinSante = null;
/* 149 */     setDesignation("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 154 */     this.typeSoinSante = (Base)event.getObject();
/* 155 */     if (this.typeSoinSante != null)
/*     */     {
/* 157 */       affecter();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void affecter() {
/* 163 */     if (this.typeSoinSante != null) {
/*     */       
/* 165 */       setId(this.typeSoinSante.getId());
/* 166 */       setCode(this.typeSoinSante.getCode());
/* 167 */       setDesignation(this.typeSoinSante.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 173 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/*     */     try {
/* 180 */       if (getId() == 0 && !this.droit.isCreer()) {
/*     */         
/* 182 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */       }
/* 184 */       else if (getId() > 0 && !this.droit.isModifier()) {
/*     */         
/* 186 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */       }
/* 188 */       else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */         
/* 190 */         HelperC.afficherMessage("Information", "Code et D�signation obligatoires");
/*     */       } else {
/*     */         
/* 193 */         this.typeSoinSante = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeSoinSante));
/* 194 */         if (this.typeSoinSante == null) {
/*     */           
/* 196 */           Historique hist = new Historique();
/* 197 */           hist.setDateOperation(Calendar.getInstance().getTime());
/* 198 */           hist.setOperateur(this.operateur);
/* 199 */           if (getId() == 0) {
/*     */             
/* 201 */             hist.setOperation("Cr�ation du type de soin de sant� " + getCode());
/*     */           } else {
/*     */             
/* 204 */             hist.setOperation("Modification du type de soin de sant� " + getCode());
/*     */           } 
/* 206 */           hist.setTable(Tables.getTableName(Tables.TableName.typeSoinSante));
/* 207 */           setHistorique(hist);
/* 208 */           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeSoinSante))) {
/*     */             
/* 210 */             clear(true);
/* 211 */             HelperC.afficherMessage("F�licitaions", "Succ�s de l'Op�ration");
/* 212 */             charger();
/*     */           } else {
/*     */             
/* 215 */             HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */           } 
/*     */         } else {
/*     */           
/* 219 */           HelperC.afficherMessage("Information", "Ce personnel vient d'�tre cr�ee par un autre op�rateur");
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
/* 235 */         HelperC.afficherMessage("Information", "Pr�cisez le type de soin  � supprimer");
/*     */       }
/* 237 */       else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */         
/* 239 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */       }
/* 241 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.typeSoinSante))) {
/*     */         
/* 243 */         clear(true);
/* 244 */         HelperC.afficherMessage("F�licitation", "Succ�s de l'Op�ration");
/* 245 */         charger();
/*     */       } else {
/*     */         
/* 248 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */       }
/*     */     
/* 251 */     } catch (Exception e) {
/*     */       
/* 253 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   

/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\TypeSoinSanteB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */