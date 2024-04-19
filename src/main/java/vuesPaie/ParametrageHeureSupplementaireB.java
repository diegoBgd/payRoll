/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DetailParametrageHeureSupplementaireC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageHeureSupplementaireC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametrageHeureSupplementaireB
/*     */   extends ParametrageHeureSupplementaireC
/*     */ {
/*     */   private static final long serialVersionUID = -7451782281177619223L;
/*     */   private int idDetail;
/*     */   private double taux;
/*     */   private String heureDebutS;
/*     */   private String heureFinS;
/*     */   private String tauxS;
/*     */   private String dateDebutS;
/*     */   private String dateFinS;
/*     */   private String tauxJourSupplementaireS;
/*     */   private ParametrageHeureSupplementaireC heureSupplementaire;
/*     */   private DetailParametrageHeureSupplementaireC detailHeureSupplementaire;
/*     */   private OperateurC operateur;
/*  47 */   private int index = 1; private ExerciceC exercice;
/*  48 */   private HttpSession session = HelperC.getSession();
/*     */   private boolean selected,disableMsg;
/*     */  
/*     */   
/*     */   public int getIdDetail() {
/*  53 */     return this.idDetail;
/*     */   }
/*     */   private DroitC droit; Base userFonction;
/*     */   
/*     */   public void setIdDetail(int idDetail) {
/*  58 */     this.idDetail = idDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSelected() {
/*  63 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(boolean selected) {
/*  68 */     this.selected = selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/*  73 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/*  78 */     this.index = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTaux() {
/*  83 */     return this.taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTaux(double taux) {
/*  88 */     this.taux = taux;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeureDebutS() {
/*  93 */     return this.heureDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureDebutS(String heureDebutS) {
/*  98 */     this.heureDebutS = heureDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeureFinS() {
/* 103 */     return this.heureFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureFinS(String heureFinS) {
/* 108 */     this.heureFinS = heureFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxS() {
/* 113 */     return this.tauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxS(String tauxS) {
/* 118 */     this.tauxS = tauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParametrageHeureSupplementaireC getHeureSupplementaire() {
/* 123 */     return this.heureSupplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureSupplementaire(ParametrageHeureSupplementaireC heureSupplementaire) {
/* 128 */     this.heureSupplementaire = heureSupplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public DetailParametrageHeureSupplementaireC getDetailHeureSupplementaire() {
/* 133 */     return this.detailHeureSupplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetailHeureSupplementaire(DetailParametrageHeureSupplementaireC detailHeureSupplementaire) {
/* 138 */     this.detailHeureSupplementaire = detailHeureSupplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/* 143 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 148 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 153 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 158 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 163 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 168 */     this.session = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebutS() {
/* 173 */     return this.dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebutS(String dateDebutS) {
/* 178 */     this.dateDebutS = dateDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFinS() {
/* 183 */     return this.dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinS(String dateFinS) {
/* 188 */     this.dateFinS = dateFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTauxJourSupplementaireS() {
/* 193 */     return this.tauxJourSupplementaireS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxJourSupplementaireS(String tauxJourSupplementaireS) {
/* 198 */     this.tauxJourSupplementaireS = tauxJourSupplementaireS;
/*     */   }
/*     */   
/*     */  


			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/* 212 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 213 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 214 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 215 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
			  disableMsg=true;
/* 216 */     if (this.exercice == null || this.operateur == null) {
/*     */ 
/*     */       
/*     */       try {
/* 220 */         FacesContext context = FacesContext.getCurrentInstance();
/* 221 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 223 */       catch (IOException e) {
/*     */         
/* 225 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 229 */      
/*     */       
/* 231 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 232 */       if (this.userFonction != null)
/*     */       {
/* 234 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
/*     */       }
/* 236 */       this.heureSupplementaire = FichierBaseDAO.getInstance().getParametrageHeureSupplementaire();
/* 237 */       if (this.heureSupplementaire != null) {
/*     */         
/* 239 */         setObject();
/* 240 */         setDetailParametrageHeureSupplementaire();
/*     */       }
/*     */       else {
/*     */         
/* 244 */         clear(false);
/* 245 */         clearDetail();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDetailParametrageHeureSupplementaire() {
/* 252 */     setListDetailHeureSupplementaire(FichierBaseDAO.getInstance().getListeDetailParametrageHeureSupplementaire(this.heureSupplementaire));
/* 253 */     if (getListDetailHeureSupplementaire().size() > 0) {
/*     */       
/* 255 */       for (DetailParametrageHeureSupplementaireC deta : getListDetailHeureSupplementaire())
/*     */       {
/*     */         
/* 258 */         deta.setIndex(getListDetailHeureSupplementaire().indexOf(deta) + 1);
/* 259 */         this.index = getListDetailHeureSupplementaire().indexOf(deta) + 2;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 264 */       this.index = 1;
/* 265 */       this.heureDebutS = "";
/* 266 */       this.heureFinS = "";
/* 267 */       this.taux = 0.0D;
/* 268 */       this.tauxS = "";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeNumero() {
/* 274 */     this.heureSupplementaire = FichierBaseDAO.getInstance().getParametrageHeureSupplementaire(getNumero());
/* 275 */     if (this.heureSupplementaire != null) {
/*     */       
/* 277 */       setObject();
/* 278 */       setDetailParametrageHeureSupplementaire();
/*     */     } else {
/*     */       
/* 281 */       clear(false);
/* 282 */       clearDetail();
/*     */     } 
/*     */   }
/*     */ 

/*     */   public void changeHeureDebut() {
/* 307 */     if (this.heureDebutS != null && this.heureDebutS != null && !this.heureDebutS.replace("_", "").trim().replace(":", "").equals(""))
/*     */     {
/* 309 */       if (Integer.parseInt(this.heureDebutS.split(":")[0]) > 24 || Integer.parseInt(this.heureDebutS.split(":")[1]) > 59 || Integer.parseInt(this.heureDebutS.split(":")[2]) > 59) {
/*     */         
/* 311 */         HelperC.afficherMessage("Information", "Heure Invalide!");
/*     */       } else {
/*     */         
/* 314 */         searchHeurDebut();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeHeureFin() {
/* 321 */     if (this.heureFinS != null && this.heureFinS != null && !this.heureFinS.replace("_", "").trim().replace(":", "").equals("") && (Integer.parseInt(this.heureFinS.split(":")[0]) > 24 || Integer.parseInt(this.heureFinS.split(":")[1]) > 59 || Integer.parseInt(this.heureFinS.split(":")[2]) > 59))
/*     */     {
/* 323 */       HelperC.afficherMessage("Information", "Heure Invalide!");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void searchHeurDebut() {
/* 329 */     for (DetailParametrageHeureSupplementaireC hsp : getListDetailHeureSupplementaire()) {
/*     */ 
/*     */       
/* 332 */       if (hsp.getHeureDebutS().equals(this.heureDebutS)) {
/*     */         
/* 334 */         this.detailHeureSupplementaire = hsp;
/* 335 */         affecterDetail();
/* 336 */         this.selected = true;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */  
		

/*     */   public void charger() {
/* 379 */     if (this.heureDebutS.replace("_", "").trim().equals("") || this.heureFinS.replace("_", "").trim().equals("")) {
/*     */       
/* 381 */       HelperC.afficherMessage("Information", "Pr�cisez les heures d�but et fin");
/*     */     }
/* 383 */     else if (this.taux == 0.0D) {
/*     */       
/* 385 */       HelperC.afficherMessage("Information", "Le taux des heures supplementaires ne peut pas �tre �gal � 0");
/*     */     } else {
/*     */       
/* 388 */       if (this.detailHeureSupplementaire == null)
/*     */       {
/* 390 */         this.detailHeureSupplementaire = new DetailParametrageHeureSupplementaireC();
/*     */       }
/* 392 */       this.detailHeureSupplementaire.setHeureDebutS(this.heureDebutS);
/* 393 */       this.detailHeureSupplementaire.setHeureFinS(this.heureFinS);
/* 394 */       this.detailHeureSupplementaire.setTaux(this.taux);
/* 395 */       this.detailHeureSupplementaire.setId(this.idDetail);
/* 396 */       this.detailHeureSupplementaire.setIndex(this.index);
/* 397 */       if (!this.selected) {
/*     */         
/* 399 */         getListDetailHeureSupplementaire().add(this.detailHeureSupplementaire);
/*     */       } else {
/*     */         
/* 402 */         getListDetailHeureSupplementaire().remove(this.index - 1);
/* 403 */         getListDetailHeureSupplementaire().add(this.index - 1, this.detailHeureSupplementaire);
/*     */       } 
/* 405 */       clearDetailHeureSupplementaire();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearDetailHeureSupplementaire() {
/* 411 */     this.heureDebutS = "";
/* 412 */     this.heureFinS = "";
/* 413 */     this.taux = 0.0D;
/* 414 */     this.tauxS = "";
/* 415 */     this.index = this.detailHeureSupplementaire.getIndex() + 1;
/* 416 */     this.selected = false;
/* 417 */     this.idDetail = 0;
/* 418 */     this.detailHeureSupplementaire = null;
/* 419 */    
/*     */   }
/*     */ 
/*     */   
/*     */   public void enlever() {
/* 424 */     if (this.index == 0) {
/*     */       
/* 426 */       HelperC.afficherMessage("Information", "Pr�cisez l'�l�ment � supprimer");
/*     */     } else {
/*     */       
/* 429 */       for (DetailParametrageHeureSupplementaireC det : getListDetailHeureSupplementaire()) {
/*     */         
/* 431 */         if (det.getIndex() == this.index) {
/*     */           
/* 433 */           this.detailHeureSupplementaire = det;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 438 */       getListDetailHeureSupplementaireDeleted().add(this.detailHeureSupplementaire);
/* 439 */       getListDetailHeureSupplementaire().remove(this.detailHeureSupplementaire);
/* 440 */       for (DetailParametrageHeureSupplementaireC deta : getListDetailHeureSupplementaire()) {
/*     */         
/* 442 */         deta.setIndex(getListDetailHeureSupplementaire().indexOf(deta) + 1);
/* 443 */         this.heureFinS = "";
/* 444 */         this.taux = 0.0D;
/* 445 */         this.tauxS = "";
/* 446 */         this.index = getListDetailHeureSupplementaire().indexOf(deta) + 2;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearDetail() {
/* 454 */     this.detailHeureSupplementaire = null;
/* 455 */     this.heureDebutS = "";
/* 456 */     this.heureFinS = "";
/* 457 */     this.taux = 0.0D;
/* 458 */     this.tauxS = "";
/* 459 */     this.index = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
				disableMsg=true;
/* 464 */     if (this.heureSupplementaire != null) {
/*     */       disableMsg=false;
/* 466 */       setId(this.heureSupplementaire.getId());
/* 467 */       setActif(heureSupplementaire.isActif());
/* 468 */       setNumero(heureSupplementaire.getNumero());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void affecterDetail() {
/* 482 */     if (this.detailHeureSupplementaire != null) {
/*     */       
/* 484 */       this.taux = this.detailHeureSupplementaire.getTaux();
/* 485 */       this.heureDebutS = this.detailHeureSupplementaire.getHeureDebutS();
/* 486 */       this.heureFinS = this.detailHeureSupplementaire.getHeureFinS();
/* 487 */       this.index = this.detailHeureSupplementaire.getIndex();
/* 488 */       this.idDetail = this.detailHeureSupplementaire.getId();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelectedDetail(SelectEvent event) {
/* 494 */     this.detailHeureSupplementaire = (DetailParametrageHeureSupplementaireC)event.getObject();
/* 495 */     if (this.detailHeureSupplementaire != null) {
/*     */       
/* 497 */       affecterDetail();
/* 498 */       this.selected = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 504 */     if (b)
/*     */     {
/* 506 */       setNumero("");
/*     */     }
/* 508 */     this.heureSupplementaire = null;
/* 509 */     setId(0);
			  disableMsg=true;
/* 510 */     setActif(false);
/* 512 */     setDateDebutS("");
/* 513 */     setDateFinS("");
/* 514 */     setTauxJourSupplementaireS("");
/* 515 */     getListDetailHeureSupplementaire().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 520 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 525 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 527 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 529 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 531 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 533 */     
/* 537 */     else if (getListDetailHeureSupplementaire() == null) {
/*     */       
/* 539 */       HelperC.afficherMessage("Information", "Pr�cisez les d�tails de Bar�me ");
/*     */     } 
/* 541 */     Historique hist = new Historique();
/* 542 */     hist.setDateOperation(Calendar.getInstance().getTime());
/* 543 */     hist.setOperateur(this.operateur);
/* 544 */     if (getId() == 0) {
/*     */       
/* 546 */       hist.setOperation("Cr�ation du parametrage des heures supplementaires " + getNumero());
/*     */     } else {
/*     */       
/* 549 */       hist.setOperation("Modification du parametrage des heures supplementaires " + getNumero());
/*     */     } 
/* 551 */     hist.setTable(Tables.getTableName(Tables.TableName.heureSupplementaire));
/* 552 */     setHistorique(hist);
             
/* 553 */     if (FichierBaseDAO.getInstance().insertUpdateParametrageHeureSupplementaire(this)) {
/*     */       
/* 555 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/*     */     } else {
/*     */       
/* 558 */       HelperC.afficherMessage("D�sol�", "Echec de l'op�ration");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 564 */     if (!this.droit.isSupprimer()) {
/*     */       
/* 566 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */       return;
/*     */     } 
/* 569 */     if (getId() == 0) {
/*     */       
/* 571 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 573 */     else if (FichierBaseDAO.getInstance().deleteParametrageHeureSupplementaire(this)) {
/*     */       
/* 575 */       clear(true);
/* 576 */       HelperC.afficherMessage("Information", "Succes de l'op�ration");
/* 577 */       setListDetailHeureSupplementaire(null);
/*     */     } else {
/*     */       
/* 580 */       HelperC.afficherMessage("D�sol�", "Echec de suppression ");
/*     */     } 
/*     */   }
/*     */ }


