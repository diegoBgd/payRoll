/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DetailParametrageHeureSupplementaireC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.HeuresPrestees;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageHeureSupplementaireC;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class SaisieHeurSupVue extends HeuresPrestees {
/*     */   private static final long serialVersionUID = 8043562123066843782L;
/*     */   private EmployeC employe;
/*     */   private List<DetailParametrageHeureSupplementaireC> listHeurSup;
/*     */   private List<EmployeC> listEmploye;
/*     */   private List<HeuresPrestees> listHeurPreste;
/*     */   private String nomRechEmp;
/*     */   private String prenomRechEmp;
/*     */   private String codeRechEmp;
/*     */   private String nomEmploye;
/*     */   private String codeEmploye;
/*     */   private HeuresPrestees hPreste;
/*     */   int idEmploye;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  44 */   private HttpSession session = HelperC.getSession();
/*     */   private DroitC droit;
            private boolean disableMsg;
/*     */   ParametrageHeureSupplementaireC parm;
/*     */   
/*     */   public HeuresPrestees gethPreste() {
/*  49 */     return this.hPreste;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sethPreste(HeuresPrestees hPreste) {
/*  54 */     this.hPreste = hPreste;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeEmploye() {
/*  59 */     return this.codeEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeEmploye(String codeEmploye) {
/*  64 */     this.codeEmploye = codeEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomEmploye() {
/*  69 */     return this.nomEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomEmploye(String nomEmploye) {
/*  74 */     this.nomEmploye = nomEmploye;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/*  78 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/*  82 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public List<HeuresPrestees> getListHeurPreste() {
/*  86 */     return this.listHeurPreste;
/*     */   }
/*     */   
/*     */   public void setListHeurPreste(List<HeuresPrestees> listHeurPreste) {
/*  90 */     this.listHeurPreste = listHeurPreste;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomRechEmp() {
/*  96 */     return this.nomRechEmp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomRechEmp(String nomRechEmp) {
/* 101 */     this.nomRechEmp = nomRechEmp;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrenomRechEmp() {
/* 106 */     return this.prenomRechEmp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrenomRechEmp(String prenomRechEmp) {
/* 111 */     this.prenomRechEmp = prenomRechEmp;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCodeRechEmp() {
/* 116 */     return this.codeRechEmp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodeRechEmp(String codeRechEmp) {
/* 121 */     this.codeRechEmp = codeRechEmp;
/*     */   }
/*     */ 
/*     */   
/*     */   public EmployeC getEmploye() {
/* 126 */     return this.employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 131 */     this.employe = employe;
/*     */   }
/*     */   
/*     */   public List<DetailParametrageHeureSupplementaireC> getListHeurSup() {
/* 135 */     return this.listHeurSup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListHeurSup(List<DetailParametrageHeureSupplementaireC> listHeurSup) {
/* 140 */     this.listHeurSup = listHeurSup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargementEmploye() {
/* 145 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeRechEmp, this.nomRechEmp,true);
/*     */   }
 
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/* 151 */     this.operateur = new OperateurC();
/* 152 */     this.exercice = new ExerciceC();
/* 153 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 154 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 155 */     if (codeOperateur == null || codeExercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 159 */         FacesContext context = FacesContext.getCurrentInstance();
/* 160 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 162 */       catch (IOException e) {
/*     */         
/* 164 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       disableMsg=true;
/* 168 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 169 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 170 */       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 171 */       if (userFonction != null)
/*     */       {
/* 173 */         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.bulletinPaie);
/*     */       }
/* 175 */       this.listHeurPreste = new ArrayList<HeuresPrestees>();
/* 176 */       chargement();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargement() {
/* 182 */     this.parm = FichierBaseDAO.getInstance().getParametrageHeureSupplementaire();
/* 183 */     if (this.parm != null) {
/*     */       
/* 185 */       this.listHeurSup = FichierBaseDAO.getInstance().getListeDetailParametrageHeureSupplementaire(this.parm);
/* 186 */       if (this.listHeurSup != null && this.listHeurSup.size() > 0)
/*     */       {
/* 188 */         for (DetailParametrageHeureSupplementaireC det : this.listHeurSup) {
/*     */           
/* 190 */           if (this.hPreste == null)
/*     */           {
/* 192 */             this.hPreste = new HeuresPrestees();
/*     */           }
/* 194 */           this.hPreste.setTemps("De " + det.getHeureDebutS() + " � " + det.getHeureFinS());
/* 195 */           this.hPreste.setPourcent(det.getTaux());
/* 196 */           this.hPreste.setNumeroHs(this.parm.getNumero());
/* 197 */           this.hPreste.setIdExercice(this.exercice.getId());
/* 198 */           this.listHeurPreste.add(this.hPreste);
/* 199 */           this.hPreste = null;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEmployeSelected(SelectEvent event) {
/* 208 */     this.employe = (EmployeC)event.getObject();
/* 209 */     if (this.employe != null) {
/*     */       
/* 211 */       this.idEmploye = this.employe.getId();
/* 212 */       setIdEmploye(this.employe.getId());
/* 213 */       this.nomEmploye = this.employe.getNomPrenom();
/* 214 */       this.codeEmploye = this.employe.getCode();
/* 215 */       PrimeFaces.current().executeScript("PF('dlgEmploye').hide();");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void searchEmploye() {
/* 222 */     if (!this.codeEmploye.equals("")) {
/*     */       
/* 224 */       this.employe = FactoryDAO.getInstance().getEmploye(this.codeEmploye, true);
/* 225 */       if (this.employe != null) {
/*     */         
/* 227 */         this.idEmploye = this.employe.getId();
/* 228 */         setIdEmploye(this.employe.getId());
/* 229 */         this.nomEmploye = this.employe.getNomPrenom();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeMonth(ValueChangeEvent event) {
/* 236 */     setMois(((Integer)event.getNewValue()).intValue());
/* 237 */     if (getMois() > 0)
/*     */     {
/* 239 */       chargerHeurSup();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerHeurSup() {
/* 245 */     this.listHeurPreste = FactoryDAO.getInstance().getListHeureSupplementaire(this.idEmploye, this.exercice.getId(), getMois());
/* 246 */     DetailParametrageHeureSupplementaireC det = null;
/* 247 */     if (this.listHeurPreste.size() > 0) {
/*     */       disableMsg=false;
/*     */       
/* 250 */       for (HeuresPrestees hp : this.listHeurPreste) {
/*     */ 
/*     */ 
/*     */         
/* 254 */         this.parm = FichierBaseDAO.getInstance().getParametrageHeureSupplementaire(hp.getNumeroHs());
/*     */         
/* 256 */         if (this.parm != null)
/*     */         {
/*     */           
/* 259 */           det = FichierBaseDAO.getInstance().getListeDetailParametrageHeureSupplementaire(hp.getPourcent(), this.parm.getId());
/* 260 */           if (det != null)
/* 261 */             hp.setTemps("De " + det.getHeureDebutS() + " � " + det.getHeureFinS()); 
/* 262 */           hp.setIdExercice(this.exercice.getId());
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 268 */       chargement();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save() {
/* 276 */     for (HeuresPrestees hp : this.listHeurPreste) {
/*     */ 
/*     */       
/* 279 */       hp.setIdEmploye(this.idEmploye);
/* 280 */       hp.setMois(getMois());
/*     */     } 
/*     */     
/* 283 */     if ((this.droit != null && this.droit.isCreer()) || this.droit.isModifier()) {
/* 284 */       FactoryDAO.getInstance().insertUpdateHeureSupplementaire(this.listHeurPreste);
/* 285 */       initialize();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 290 */       HelperC.afficherAttention("ATTENTION", 
/* 291 */           "Vous n'avez pas le droit de cr�er");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void deleteHsup() {
/* 296 */     if (this.droit != null && this.droit.isSupprimer()) {
/*     */       if(employe!=null && listHeurPreste!=null && listHeurPreste.size()>0)
				{
/* 298 */       FactoryDAO.getInstance().deleteHeurePrste(this.employe.getId(), getMois(), this.exercice.getId());
/* 299 */       initialize();
				}
				else
					 HelperC.afficherDeleteMessage();
/*     */     }
/*     */     else {
/*     */       
/* 303 */       HelperC.afficherAttention("ATTENTION", 
/* 304 */           "Vous n'avez pas le droit de supprimer");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 309 */     this.employe = null;
/* 310 */     setMois(0);
/* 311 */     this.nomEmploye = "";
/* 312 */     this.codeEmploye = "";
/* 313 */     this.nomRechEmp = "";
/* 314 */     this.codeRechEmp = "";
/* 315 */     this.prenomRechEmp = "";
/* 316 */     this.listHeurPreste.clear();
			  disableMsg=true;
/* 317 */     chargement();
/*     */   }
/*     */ }

