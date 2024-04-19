/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DetailGrilleCotisationC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.GrilleCotisationC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import java.io.IOException;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class GrilleCotisationB
/*     */   extends GrilleCotisationC
/*     */ {
/*     */   private static final long serialVersionUID = 3668522777115339190L;
/*     */   private String categorie;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*     */   private double trancheDeb;
/*     */   private double trancheFin;
/*     */   private double pu;
/*     */   private double unite;
/*     */   private double totMontant;
/*     */   private DetailGrilleCotisationC detail;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private int idDetail;
			private boolean disableMsg;
/*  45 */   private HttpSession session = HelperC.getSession();
/*     */   boolean selected;
/*     */   int index;
/*     */   
/*     */   public String getDateDebutS() {
/*  50 */     return this.dateDebutS;
/*     */   }
/*     */   Base userFonction; GrilleCotisationC grille; private DroitC droit;
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/*  55 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/*  60 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/*  65 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTotMontant() {
/*  70 */     return this.totMontant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTotMontant(double totMontant) {
/*  75 */     this.totMontant = totMontant;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCategorie() {
/*  80 */     return this.categorie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCategorie(String categorie) {
/*  85 */     this.categorie = categorie;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTrancheDeb() {
/*  90 */     return this.trancheDeb;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTrancheDeb(double trancheDeb) {
/*  95 */     this.trancheDeb = trancheDeb;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTrancheFin() {
/* 100 */     return this.trancheFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTrancheFin(double trancheFin) {
/* 105 */     this.trancheFin = trancheFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPu() {
/* 110 */     return this.pu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPu(double pu) {
/* 115 */     this.pu = pu;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getUnite() {
/* 120 */     return this.unite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnite(double unite) {
/* 125 */     this.unite = unite;
/*     */   }
/*     */ 
/*     */   
/*     */   public DetailGrilleCotisationC getDetail() {
/* 130 */     return this.detail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetail(DetailGrilleCotisationC detail) {
/* 135 */     this.detail = detail;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdDetail() {
/* 140 */     return this.idDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdDetail(int idDetail) {
/* 145 */     this.idDetail = idDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public DroitC getDroit() {
/* 150 */     return this.droit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDroit(DroitC droit) {
/* 155 */     this.droit = droit;
/*     */   }

  
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
			
/*     */   @PostConstruct
/*     */   private void init() {
/* 161 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 162 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 163 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 164 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
			  disableMsg=true;
/* 165 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 169 */         FacesContext context = FacesContext.getCurrentInstance();
/* 170 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 172 */       catch (IOException e) {
/*     */         
/* 174 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 178 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 179 */       if (this.userFonction != null)
/*     */       {
/* 181 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
/*     */       }
/* 183 */       this.grille = FichierBaseDAO.getInstance().getGrilleCotisation();
/* 184 */       if (this.grille != null) {
/*     */         disableMsg=false;
/* 186 */         setId(this.grille.getId());
/* 187 */         setCoefficient(this.grille.getCoefficient());
/* 188 */         setDateDebut(this.grille.getDateDebut());
/* 189 */         this.dateDebutS = HelperC.changeDateFormate(this.grille.getDateDebut());
/* 190 */         if (this.grille.getDateFin() != null)
/*     */         {
/* 192 */           this.dateFinS = HelperC.changeDateFormate(this.grille.getDateFin());
/*     */         }
/* 194 */         this.grille.setListDetail(FichierBaseDAO.getInstance().getListeGrilleDetail(this.grille));
/* 195 */         setListDetail(this.grille.getListDetail());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 202 */     this.detail = (DetailGrilleCotisationC)event.getObject();
/* 203 */     setDetailValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDetailValue() {
/* 208 */     if (this.detail != null) {
/*     */       
/* 210 */       this.pu = this.detail.getValeurAchat();
/* 211 */       this.categorie = this.detail.getCategorie();
/* 212 */       this.trancheDeb = this.detail.getTrancheMin();
/* 213 */       this.trancheFin = this.detail.getTrancheMax();
/* 214 */       this.unite = this.detail.getPointAchete();
/* 215 */       this.idDetail = this.detail.getId();
/* 216 */       this.index = getListDetail().indexOf(this.detail);
/* 217 */       this.selected = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDetail() {
				if(categorie!=null && !categorie.equals(""))
				{
/* 223 */     if (this.detail == null)
/*     */     {
/* 225 */       this.detail = new DetailGrilleCotisationC();
/*     */     }
/* 227 */     this.detail.setCategorie(this.categorie);
/* 228 */     this.detail.setPointAchete(this.unite);
/* 229 */     this.detail.setTrancheMax(this.trancheFin);
/* 230 */     this.detail.setTrancheMin(this.trancheDeb);
/* 231 */     this.detail.setValeurAchat(this.pu);
/* 232 */     this.detail.setId(this.idDetail);
/* 233 */     if (!this.selected) {
/*     */       
/* 235 */       getListDetail().add(this.detail);
/*     */     } else {
/*     */       
/* 238 */       getListDetail().remove(this.index);
/* 239 */       getListDetail().add(this.index, this.detail);
/*     */     } 
/* 241 */     clearDetail();
				}
				else
					HelperC.afficherAttention("Info", "Il faut pr�ciser la cat�gorie");
/*     */   }
/*     */ 
/*     */   
/*     */   public void searcheElement() {
/* 246 */     if (getListDetail().size() > 0)
/*     */     {
/* 248 */       for (DetailGrilleCotisationC det : getListDetail()) {
/*     */ 
/*     */         
/* 251 */         if (det.getCategorie().equals(this.categorie)) {
/*     */           
/* 253 */           this.detail = det;
/* 254 */           setDetailValue();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeDetail() {
/* 263 */     if (this.detail != null) {
/*     */       
/* 265 */       if (this.detail.getId() > 0)
/*     */       {
/* 267 */         getListDeleted().add(this.detail);
/*     */       }
/* 269 */       getListDetail().remove(this.detail);
/* 270 */       clearDetail();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearDetail() {
/* 276 */     this.detail = null;
/* 277 */     this.pu = 0.0D;
/* 278 */     this.categorie = "";
/* 279 */     this.trancheDeb = 0.0D;
/* 280 */     this.trancheFin = 0.0D;
/* 281 */     this.unite = 0.0D;
/* 282 */     this.index = 0;
/* 283 */     this.selected = false;
/* 284 */     this.totMontant = 0.0D;
/* 285 */     this.idDetail = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 290 */     setId(0);
/* 291 */     this.dateDebutS = "";
/* 292 */     this.dateFinS = "";
/* 293 */     setCoefficient(0.0D);
/* 294 */     getListDeleted().clear();
/* 295 */     getListDetail().clear();
			  disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateDebut() {
/* 300 */     if (this.dateDebutS.replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 302 */       setDateDebut(null);
/*     */     } else {
/*     */       
/* 305 */       setDateDebut(HelperC.validerDate(this.dateDebutS));
/* 306 */       if (getDateDebut() == null) {
/*     */         
/* 308 */         this.dateDebutS = "";
/* 309 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 312 */         this.dateDebutS = HelperC.convertDate(getDateDebut());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateFin() {
/* 319 */     if (this.dateFinS.replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 321 */       setDateFin(null);
/*     */     } else {
/*     */       
/* 324 */       setDateFin(HelperC.validerDate(this.dateFinS));
/* 325 */       if (getDateFinS() == null) {
/*     */         
/* 327 */         this.dateFinS = "";
/* 328 */         HelperC.afficherMessage("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 331 */         this.dateFinS = HelperC.convertDate(getDateFin());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 338 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 340 */       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
/*     */       return;
/*     */     } 
/* 343 */     if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 345 */       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier ");
/*     */       return;
/*     */     } 
/* 348 */     if (getDateDebut() != null && getCoefficient() > 0.0D)
/*     */     {
/* 350 */       if (FichierBaseDAO.getInstance().insertUpdateGrilleCotisation(this)) {
/*     */         
/* 352 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/*     */       } 
/*     */     }
				else {
/*     */         
/* 355 */         HelperC.afficherMessage("Information", "Il faut pr�ciser la date d�but et le coefficient! ");
/*     */       } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete() {
/* 362 */     if (!this.droit.isSupprimer()) {
/*     */       
/* 364 */       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
/*     */       return;
/*     */     } 
/* 367 */     if (getId() > 0) {
/*     */       
/* 369 */       FichierBaseDAO.getInstance().deleteGrilleCotisation(this);
/*     */     } else {
/*     */       
/* 372 */       HelperC.afficherDeleteMessage();
/*     */     } 
/*     */   }
/*     */ }

