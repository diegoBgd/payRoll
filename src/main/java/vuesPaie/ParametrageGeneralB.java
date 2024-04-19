/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
		  import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageGeneralC;
/*     */ import java.io.IOException;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametrageGeneralB
/*     */   extends ParametrageGeneralC
/*     */ {
/*     */   private static final long serialVersionUID = -8346467593125395780L;
/*     */   private ParametrageGeneralC parametre;
/*     */   private DroitC droit;
			private boolean disableMsg;
/*     */   OperateurC operateur;
/*     */   ExerciceC exercice;
/*  28 */   HttpSession session = HelperC.getSession();
/*     */ 
/*     */ 
/*     */   
/*     */   public ParametrageGeneralC getParametre() {
/*  33 */     return this.parametre;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParametre(ParametrageGeneralC parametre) {
/*  38 */     this.parametre = parametre;
/*     */   }
/*     */ 
/*     */   
/*     */   public DroitC getDroit() {
/*  43 */     return this.droit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDroit(DroitC droit) {
/*  48 */     this.droit = droit;
/*     */   }


			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void init() {
			  disableMsg=true;
/*  54 */     this.operateur = new OperateurC();
/*  55 */     this.exercice = new ExerciceC();
/*  56 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  57 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  58 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  59 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  60 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/*  64 */         FacesContext context = FacesContext.getCurrentInstance();
/*  65 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/*  67 */       catch (IOException e) {
/*     */         
/*  69 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/*  73 */       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/*  74 */       if (userFonction != null)
/*     */       {
/*  76 */         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.parametrage);
/*     */       }
/*  78 */       chargement();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargement() {
/*  84 */     this.parametre = FichierBaseDAO.getInstance().getParametrageGeneral();
/*  85 */     if (this.parametre != null) {
/*     */       disableMsg=false;
/*  87 */       setId(this.parametre.getId());
/*  88 */       setNbreDecimal(this.parametre.getNbreDecimal());
/*  89 */       setTauxMaxLogement(this.parametre.getTauxMaxLogement());
/*  90 */       setAllocationBaseHsup(this.parametre.isAllocationBaseHsup());
/*  91 */       setLogementBaseHsup(this.parametre.isLogementBaseHsup());
/*  92 */       setDureCourTerme(this.parametre.getDureCourTerme());
/*  93 */       setDureLongTerme(this.parametre.getDureLongTerme());
/*  94 */       setDureMoyenTerme(this.parametre.getDureMoyenTerme());
/*  95 */       setMontantNetMin(this.parametre.getMontantNetMin());
/*  96 */       setNbreHeureJour(this.parametre.getNbreHeureJour());
/*  97 */       setNbreHeureMois(this.parametre.getNbreHeureJour() * this.parametre.getNbreHeureJour());
/*  98 */       setNbreJourMois(this.parametre.getNbreJourMois());
/*  99 */       setTauxJrFerie(this.parametre.getTauxJrFerie());
                setMailOrigine(this.parametre.getMailOrigine());
                setPwdOrigine(this.parametre.getPwdOrigine());
                setPort(this.parametre.getPort());
                setSmtpServer(this.parametre.getSmtpServer());
                
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 105 */     if (getId() == 0 && (this.droit == null || !this.droit.isCreer())) {
/*     */       
/* 107 */       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de cr�er le bulletin ");
/*     */       return;
/*     */     } 
/* 110 */     if (getId() > 0 && (this.droit == null || !this.droit.isModifier())) {
/*     */       
/* 112 */       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier le bulletin ");
/*     */       return;
/*     */     } 
/* 115 */     if (FichierBaseDAO.getInstance().insertUpdateParametrageGeneral(this)) {
/*     */       
/* 117 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 118 */       initialise();
/* 119 */       chargement();
/*     */     } else {
/*     */       
/* 122 */       HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete() {
/* 128 */     if (getId() > 0) {
/*     */       
/* 130 */       if (this.droit == null || !this.droit.isSupprimer()) {
/*     */         
/* 132 */         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer le param�trage ! ");
/*     */         return;
/*     */       } 
/* 135 */       if (FichierBaseDAO.getInstance().deleteParametrage(this)) {
/*     */         
/* 137 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 138 */         initialise();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialise() {
/* 145 */     setId(0);
/* 146 */     setNbreDecimal(0);
/* 147 */     setTauxMaxLogement(0.0D);
/* 148 */     setDureCourTerme(0);
/* 149 */     setDureLongTerme(0);
/* 150 */     setDureMoyenTerme(0);
/* 151 */     setMontantNetMin(0.0D);
/* 152 */     setNbreHeureJour(0);
/* 153 */     setNbreHeureMois(0);
/* 154 */     setNbreJourMois(0);
/* 155 */     setTauxJrFerie(0.0D);
/* 156 */     setAllocationBaseHsup(false);
/* 157 */     setLogementBaseHsup(false);
			  setMailOrigine("");
			  setPwdOrigine("");
			  setPort("");
			  setSmtpServer("");
			  
			  disableMsg=true;
/*     */   }
/*     */ }

