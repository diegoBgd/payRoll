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
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeAppreciationAvancementB
/*     */   extends Base
/*     */ {
/*     */   private static final long serialVersionUID = -4488191200197067424L;
/*     */   private Base typeAppreciation;
/*     */   private List<Base> listeTypeAppreciations;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  36 */   private HttpSession session = HelperC.getSession();
/*     */   private boolean disableMsg;
/*     */ 
/*     */   
/*     */   public Base getTypeAppreciation() {
/*  41 */     return this.typeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeAppreciation(Base typeAppreciation) {
/*  46 */     this.typeAppreciation = typeAppreciation;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Base> getListeTypeAppreciations() {
/*  51 */     return this.listeTypeAppreciations;
/*     */   }
/*     */   
/*     */   public void setListeTypeAppreciations(List<Base> listeTypeAppreciations) {
/*  55 */     this.listeTypeAppreciations = listeTypeAppreciations;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  60 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  65 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  70 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  75 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  80 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  85 */     this.session = session;
/*     */   }
/*     */ 
public boolean isDisableMsg() {
	return disableMsg;
}
public void setDisableMsg(boolean disableMsg) {
	this.disableMsg = disableMsg;
}
/*     */   @PostConstruct
/*     */   private void init() {
/*  91 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  92 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  93 */     if (codeOperateur == null || codeExercice == null) {
/*     */ 
/*     */       
/*     */       try {
/*  97 */         FacesContext context = FacesContext.getCurrentInstance();
/*  98 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 100 */       catch (IOException e) {
/*     */         
/* 102 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 106 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 107 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 108 */       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 109 */       if (userFonction != null)
/*     */       {
/* 111 */         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.premiereEvaluation);
/*     */       }
/* 113 */       charger();
				disableMsg=true;
/*     */     } 
/*     */   }

/*     */ 
/*     */   
/*     */   private void charger() {
/* 119 */     this.listeTypeAppreciations = FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.typeAppreciationAvancement));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 124 */     if (getCode().trim().equals("")) {
/*     */       
/* 126 */       clear(true);
/*     */     } else {
/*     */       
/* 129 */       this.typeAppreciation = FichierBaseDAO.getInstance().getBaseByCode(getCode(), Tables.getTableName(Tables.TableName.typeAppreciationAvancement));
/* 130 */       if (this.typeAppreciation == null) {
/*     */         
/* 132 */         clear(false);
/*     */       } else {
/*     */         
/* 135 */         affecter();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 142 */     if (b)
/*     */     {
/* 144 */       setCode("");
/*     */     }
/* 146 */     setId(0);
/* 147 */     this.typeAppreciation = null;
/* 148 */     setDesignation("");
				disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 153 */     this.typeAppreciation = (Base)event.getObject();
/* 154 */     if (this.typeAppreciation != null)
/*     */     {
/* 156 */       affecter();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void affecter() {
				disableMsg=true;
/* 162 */     if (this.typeAppreciation != null) {
/*     */        disableMsg=false;
/* 164 */       setId(this.typeAppreciation.getId());
/* 165 */       setCode(this.typeAppreciation.getCode());
/* 166 */       setDesignation(this.typeAppreciation.getDesignation());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 172 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/*     */     try {
/* 179 */       if (getId() == 0 && !this.droit.isCreer()) {
/*     */         
/* 181 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */       }
/* 183 */       else if (getId() > 0 && !this.droit.isModifier()) {
/*     */         
/* 185 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */       }
/* 187 */       else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */         
/* 189 */         HelperC.afficherMessage("Information", "Code et D�signation obligatoires");
/*     */       } else {
/*     */         
/* 192 */         this.typeAppreciation = FichierBaseDAO.getInstance().getBase(getCode(), getId(), Tables.getTableName(Tables.TableName.typeAppreciationAvancement));
/* 193 */         if (this.typeAppreciation == null) {
/*     */           
/* 195 */           Historique hist = new Historique();
/* 196 */           hist.setDateOperation(Calendar.getInstance().getTime());
/* 197 */           hist.setOperateur(this.operateur);
/* 198 */           if (getId() == 0) {
/*     */             
/* 200 */             hist.setOperation("Cr�ation du type d'appreciation d'avancement " + getCode());
/*     */           } else {
/*     */             
/* 203 */             hist.setOperation("Modification du type d'appreciation d'avancement " + getCode());
/*     */           } 
/* 205 */           hist.setTable(Tables.getTableName(Tables.TableName.typeAppreciationAvancement));
/* 206 */           setHistorique(hist);
/* 207 */           if (FichierBaseDAO.getInstance().insertUpdateBase(this, Tables.getTableName(Tables.TableName.typeAppreciationAvancement))) {
/*     */             
/* 209 */             clear(true);
/* 210 */             HelperC.afficherMessage("F�licitaions", "Succ�s de l'Op�ration");
/* 211 */             charger();
/*     */           } else {
/*     */             
/* 214 */             HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */           } 
/*     */         } else {
/*     */           
/* 218 */           HelperC.afficherMessage("Information", "Ce type d'appr�ciation vient d'�tre cr�ee par un autre op�rateur");
/*     */         }
/*     */       
/*     */       } 
/* 222 */     } catch (Exception e) {
/*     */       
/* 224 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 232 */       if (getId() == 0) {
/*     */         
/* 234 */         HelperC.afficherDeleteMessage();
/*     */       }
/* 236 */       else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */         
/* 238 */         HelperC.afficherDeleteMessage();
/*     */       }
/* 240 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.typeAppreciationAvancement))) {
/*     */         
/* 242 */         clear(true);
/* 243 */         HelperC.afficherMessage("F�licitation", "Succ�s de l'Op�ration");
/* 244 */         charger();
/*     */       } else {
/*     */         
/* 247 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
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

