/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.JoursCongeEmployeC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageDureeCongeC;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
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
/*     */ public class SaisiePrevisionCongeB
/*     */   extends JoursCongeEmployeC
/*     */ {
/*     */   private static final long serialVersionUID = 3512300548840150641L;
/*  37 */   private List<JoursCongeEmployeC> allSaisiePrevisionConge = new ArrayList<JoursCongeEmployeC>();
/*     */   private OperateurC operateur;
/*     */   private DroitC droit;
/*  40 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   private Constante.SorteConge sorteConge;
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void charger() {
/*  49 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  50 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*     */     
/*  52 */     if (codeExercice == null || codeOperateur == null) {
/*     */       try {
/*  54 */         FacesContext context = FacesContext.getCurrentInstance();
/*  55 */         context.getExternalContext().redirect(
/*  56 */             "/payRoll/login.xhtml");
/*  57 */       } catch (IOException e) {
/*     */         
/*  59 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*  62 */       setExercice(FichierBaseDAO.getInstance().getExercice(codeExercice));
/*  63 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  64 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/*  65 */       if (this.userFonction != null)
/*  66 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge); 
/*  67 */       setSorteConge(Constante.SorteConge.congeReposAnnuel);
/*  68 */       findEmployes();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  74 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  78 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/*  82 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  86 */     this.session = session;
/*     */   }
/*     */   
/*     */   public List<JoursCongeEmployeC> getAllSaisiePrevisionConge() {
/*  90 */     return this.allSaisiePrevisionConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllSaisiePrevisionConge(List<JoursCongeEmployeC> allSaisiePrevisionConge) {
/*  95 */     this.allSaisiePrevisionConge = allSaisiePrevisionConge;
/*     */   }
/*     */   
/*     */   public Constante.SorteConge getSorteConge() {
/*  99 */     return this.sorteConge;
/*     */   }
/*     */   
/*     */   public void setSorteConge(Constante.SorteConge sorteConge) {
/* 103 */     this.sorteConge = sorteConge;
/*     */   }
/*     */ 
/*     */   
/*     */   private void getAllConges() {
/* 108 */     if (this.allSaisiePrevisionConge.size() > 0) {
/* 109 */       for (JoursCongeEmployeC j : this.allSaisiePrevisionConge) {
/* 110 */         j.setNumero(this.allSaisiePrevisionConge.indexOf(j) + 1);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void findEmployes() {
/* 118 */     if (getExercice() != null) {
/* 119 */       this.allSaisiePrevisionConge = FactoryDAO.getInstance().getListAllJoursCongeEmploye(getExercice().getId());
/*     */     }
/* 121 */     if (this.allSaisiePrevisionConge.size() == 0) {
/*     */       
/* 123 */       JoursCongeEmployeC jour = null;
/* 124 */       for (EmployeC employe : FactoryDAO.getInstance().getAllEmploye(false, 0))
/*     */       {
/* 126 */         jour = new JoursCongeEmployeC();
/* 127 */         jour.setEmploye(employe);
/* 128 */         jour.setExercice(getExercice());
/* 129 */         ParametrageDureeCongeC conge = new ParametrageDureeCongeC();
/* 130 */        
/* 131 */         if (conge != null) {
/* 132 */           jour.setJoursDu(conge.getNombreJoursAnnuel());
/* 133 */           jour.setJoursDuS(HelperC.TraitementMontant.getMontantFormate(
/* 134 */                 conge.getNombreJoursAnnuel(), 0));
/*     */         } 
/* 136 */         getAllSaisiePrevisionConge().add(jour);
/*     */       }
/*     */     
/* 139 */     } else if (this.allSaisiePrevisionConge.size() > 0) {
/* 140 */       if (getExercice() != null) {
/* 141 */         setAllSaisiePrevisionConge(FactoryDAO.getInstance().getListAllJoursCongeEmploye(getExercice().getId()));
/*     */       }
/*     */       
/* 144 */       for (EmployeC emp : FactoryDAO.getInstance().getAllEmploye(false, 0)) {
/* 145 */         JoursCongeEmployeC jour = FactoryDAO.getInstance().getJoursCongeEmploye(emp);
/*     */ 
/*     */         
/* 148 */         getAllSaisiePrevisionConge().add(jour);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 153 */     getAllConges();
/*     */   }
/*     */   
/*     */   public void save() {
/* 157 */     if (getId() == 0 && !this.droit.isCreer()) {
/* 158 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
/* 159 */     } else if (getId() > 0 && !this.droit.isModifier()) {
/* 160 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/* 161 */     } else if (this.allSaisiePrevisionConge.size() > 0) {
/* 162 */       for (JoursCongeEmployeC j : this.allSaisiePrevisionConge) {
/* 163 */         if (FactoryDAO.getInstance().insertUpdateJoursCongeEmploye(j)) {
/* 164 */           findEmployes(); continue;
/*     */         } 
/* 166 */         HelperC.afficherMessage("Désolé", "Echec d'enregistrement");
/*     */       } 
/*     */       
/* 169 */       HelperC.afficherMessage("Félicitation", "Enregistrement effectué avec succès");
/*     */     } else {
/* 171 */       HelperC.afficherMessage(
/* 172 */           "Information", 
/* 173 */           "Il n'y a pas d'employés sur lesquels on va prévioir les congés", 
/* 174 */           FacesMessage.SEVERITY_ERROR);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\SaisiePrevisionCongeB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */