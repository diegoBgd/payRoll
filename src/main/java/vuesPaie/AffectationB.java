/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.AffectationC;
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;

/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;

import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;

/*     */ import org.primefaces.PrimeFaces;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class AffectationB
/*     */   extends AffectationC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 9223372036854775000L;
/*     */   private List<SelectItem> listeFonction;
/*     */   private int idFonction;
/*     */   private int idFonctionRecherche;
/*     */   private int tabIndex;
/*     */   private String matricule;
/*     */   private String ancienFonction;
/*     */    
/*     */   private OperateurC operateur;
/*     */   private DroitC droit;
/*     */   private String matriculeRecherche;
/*  47 */   AffectationC ancienAffectation = null; 
			private String nomRecherche; 
			private String prenomRecherche; 
			private List<EmployeC> listeFonctionnaire; 
			private Base fonctionRecherche; 
			private List<AffectationC> listeAffectation; 
			private AffectationC affectation; 
			private EmployeC employe; 
			private boolean userExist; 
			private boolean readOnly; 
			ExerciceC exercice; 
			Base userFonction;
/*     */   HttpSession session;
/*     */   public AffectationB() {
/*  50 */     this.listeFonction = new ArrayList<SelectItem>();
/*     */     
/*  52 */     this.session = HelperC.getSession();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUserExist() {
/*  57 */     return this.userExist;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserExist(boolean userExist) {
/*  62 */     this.userExist = userExist;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdFonction() {
/*  67 */     return this.idFonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdFonction(int idFonction) {
/*  72 */     this.idFonction = idFonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMatricule() {
/*  77 */     return this.matricule;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMatricule(String matricule) {
/*  82 */     this.matricule = matricule;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMatriculeRecherche() {
/*  87 */     return this.matriculeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMatriculeRecherche(String matriculeRecherche) {
/*  92 */     this.matriculeRecherche = matriculeRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomRecherche() {
/*  97 */     return this.nomRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomRecherche(String nomRecherche) {
/* 102 */     this.nomRecherche = nomRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrenomRecherche() {
/* 107 */     return this.prenomRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrenomRecherche(String prenomRecherche) {
/* 112 */     this.prenomRecherche = prenomRecherche;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListeFonction() {
/* 116 */     return this.listeFonction;
/*     */   }
/*     */   
/*     */   public void setListeFonction(List<SelectItem> listeFonction) {
/* 120 */     this.listeFonction = listeFonction;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListeFonctionnaire() {
/* 124 */     return this.listeFonctionnaire;
/*     */   }
/*     */   
/*     */   public void setListeFonctionnaire(List<EmployeC> listeFonctionnaire) {
/* 128 */     this.listeFonctionnaire = listeFonctionnaire;
/*     */   }
/*     */   
/*     */   public List<AffectationC> getListeAffectation() {
/* 132 */     return this.listeAffectation;
/*     */   }
/*     */   
/*     */   public void setListeAffectation(List<AffectationC> listeAffectation) {
/* 136 */     this.listeAffectation = listeAffectation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdFonctionRecherche() {
/* 141 */     return this.idFonctionRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdFonctionRecherche(int idFonctionRecherche) {
/* 146 */     this.idFonctionRecherche = idFonctionRecherche;
/*     */   }
/*     */ 
/*     */   
/*     */   public AffectationC getAffectation() {
/* 151 */     return this.affectation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAffectation(AffectationC affectation) {
/* 156 */     this.affectation = affectation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabIndex() {
/* 161 */     return this.tabIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTabIndex(int tabIndex) {
/* 166 */     this.tabIndex = tabIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/* 171 */     return this.readOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReadOnly(boolean readOnly) {
/* 176 */     this.readOnly = readOnly;
/*     */   }
/*     */   
/*     */   public EmployeC getEmploye() {
/* 180 */     return this.employe;
/*     */   }
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 184 */     this.employe = employe;
/*     */   }
/*     */   
/*     */   public String getAncienFonction() {
/* 188 */     return this.ancienFonction;
/*     */   }
/*     */   
/*     */   public void setAncienFonction(String ancienFonction) {
/* 192 */     this.ancienFonction = ancienFonction;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/* 198 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 199 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 200 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 201 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 202 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 206 */         FacesContext context = FacesContext.getCurrentInstance();
/* 207 */         context.getExternalContext().redirect("/payRoll/login.jsf");
/*     */       }
/* 209 */       catch (IOException e) {
/*     */         
/* 211 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 215 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 216 */       if (this.userFonction != null)
/*     */       {
/* 218 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.structureAdministrative);
/*     */       }
/* 220 */       chargerFonction();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerFonction() {
/* 226 */     this.listeFonction.clear();
/* 227 */     this.listeFonction.add(new SelectItem(Integer.valueOf(0), " "));
/*     */     
/* 229 */     for (Base fonction : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction)))
/*     */     {
/* 231 */       this.listeFonction.add(new SelectItem(Integer.valueOf(fonction.getId()), String.valueOf(fonction.getCode()) + " || " + fonction.getDesignation()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeFonction(ValueChangeEvent event) {
/* 238 */     this.idFonction = ((Integer)event.getNewValue()).intValue();
/* 239 */     setFonction(FichierBaseDAO.getInstance().getBaseById(this.idFonction, Tables.getTableName(Tables.TableName.fonction)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeFonctionnaire() {
/* 244 */     if (!this.matricule.trim().equals("")) {
/*     */ 
/*     */       
/* 247 */       this.employe = FichierBaseDAO.getInstance().getFonctionnaireActif(this.matricule, new Date());
/* 248 */       if (this.employe != null) {
/*     */         
/* 250 */         setIdEmploye(this.employe.getId());
/* 251 */         setNomPrenom(this.employe.getNomPrenom());
/* 252 */         this.ancienAffectation = FichierBaseDAO.getInstance().getFonctionActuel(this.employe.getId());
/* 253 */         if (this.ancienAffectation != null) {
/*     */           
/* 255 */           this.ancienFonction = this.ancienAffectation.getFonction().getDesignation();
/*     */         } else {
/*     */           
/* 258 */           this.ancienFonction = "";
/*     */         } 
/* 260 */       }  if (this.employe.getDateEntre() != null) {
/*     */         
/* 262 */         setDateDebut(this.employe.getDateEntre());
/* 263 */         setDateDebutS(HelperC.changeDateFormate(getDateDebut()));
/*     */       } 
/*     */     } else {
/*     */       
/* 267 */       setNomPrenom("");
/* 268 */       setFonction(null);
/* 269 */       this.ancienFonction = "";
/* 270 */       HelperC.afficherInformation("Information", "Le fonctionnaire de ce Matricule n'existe pas ou n'est plus actif");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDateDebut() {
/* 277 */     if (getDateDebutS().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 279 */       setDateDebut(null);
/*     */     } else {
/*     */       
/* 282 */       setDateDebut(HelperC.validerDate(getDateDebutS()));
/* 283 */       if (getDateDebut() == null) {
/*     */         
/* 285 */         setDateDebutS("");
/* 286 */         HelperC.afficherInformation("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 289 */         setDateDebutS(HelperC.convertDate(getDateDebut()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateFin() {
/* 296 */     if (getDateFinS().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 298 */       setDateFin(null);
/*     */     } else {
/*     */       
/* 301 */       setDateFin(HelperC.validerDate(getDateFinS()));
/* 302 */       if (getDateFin() == null) {
/*     */         
/* 304 */         setDateFinS("");
/* 305 */         HelperC.afficherInformation("Information", "Date invalide");
/*     */       } else {
/*     */         
/* 308 */         setDateFinS(HelperC.convertDate(getDateFin()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 315 */     setId(0);
/* 316 */     setIdEmploye(0);
/* 317 */     this.matricule = "";
/* 318 */     setNomPrenom("");
/* 319 */     setFonction(null);
/* 320 */     this.idFonction = 0;
/* 321 */     setDateDebut(null);
/* 322 */     setDateDebutS("");
/* 323 */     setDateFin(null);
/* 324 */     setDateFinS("");
/* 325 */     setReference("");
/* 326 */     this.ancienAffectation = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 331 */     clear(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 336 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 338 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 340 */     else if (getId() != 0 && !this.droit.isModifier()) {
/*     */       
/* 342 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 344 */     else if (getIdEmploye()>0) {
/*     */       
/* 346 */       HelperC.afficherInformation("Information", "Pr�cisez le Fonctionnaire");
/*     */     
/*     */     }
/* 349 */     else if (getDateDebut() == null) {
/*     */       
/* 351 */       HelperC.afficherInformation("Information", "Pr�cisez la date  D�but de validit�");
/*     */     }
/* 353 */     else if (getDateDebut().before(employe.getDateEntre())) {
/*     */       
/* 355 */       HelperC.afficherAttention("ATTENTION", "Pas d'affectation avant l'�tre fonctionnaire");
/*     */     }
/* 357 */     else if (getDateFin() != null && !getDateFin().after(getDateDebut())) {
/*     */       
/* 359 */       HelperC.afficherAttention("ATTENTION", "La Date de d�but de validit� doit pr�c�der celle de Fin de validit�");
/*     */     } else {
/*     */       
/* 362 */       Historique hist = new Historique();
/* 363 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 364 */       hist.setOperateur(this.operateur);
/* 365 */       if (getId() == 0) {
/*     */         
/* 367 */         hist.setOperation("Saisie Affectation Fonctionnaire: " + employe.getCode());
/*     */       } else {
/*     */         
/* 370 */         hist.setOperation("Modification Affectation Fonctionnaire: " +employe.getMatricule());
/*     */       } 
/* 372 */       hist.setTable(Tables.getTableName(Tables.TableName.affectation));
/* 373 */       setHistorique(hist);
/*     */       
/* 375 */       if (getDateFin() != null) {
/* 376 */         setId(this.ancienAffectation.getId());
/* 377 */         setFonction(this.ancienAffectation.getFonction());
/*     */       } 
/*     */       
/* 380 */       if (FichierBaseDAO.getInstance().insertUpdateAffectation(this)) {
/*     */         
/* 382 */         clear(false);
/* 383 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'Op�ration");
/*     */       } else {
/*     */         
/* 386 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 393 */     if (!this.droit.isSupprimer()) {
/*     */       
/* 395 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 397 */     else if (getId() == 0) {
/*     */       
/* 399 */       HelperC.afficherDeleteMessage();
/*     */     } else {
/*     */       
/* 402 */       Historique hist = new Historique();
/* 403 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 404 */       hist.setOperateur(this.operateur);
/* 405 */       hist.setOperation("Suppression Affectation: " + employe.getMatricule());
/* 406 */       hist.setTable(Tables.getTableName(Tables.TableName.affectation));
/* 407 */       hist.setIdLigne(getId());
/* 408 */       setHistorique(hist);
/* 409 */       if (FichierBaseDAO.getInstance().deleteAffectation(this)) {
/*     */         
/* 411 */         clear(false);
/* 412 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'Op�ration");
/*     */       } else {
/*     */         
/* 415 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initRechercheFonctionnaire() {
/* 422 */     this.matriculeRecherche = "";
/* 423 */     this.nomRecherche = "";
/* 424 */     this.prenomRecherche = "";
/* 425 */     if (this.listeFonctionnaire != null)
/*     */     {
/* 427 */       this.listeFonctionnaire.clear();
/*     */     }
/* 429 */     PrimeFaces.current().executeScript("PF('dlgRechercheFonctionnaire').hide();");
/*     */   }
/*     */ 
/*     */   
/*     */   public void rechercherFonctionnaire() {
/* 434 */     this.listeFonctionnaire = FichierBaseDAO.getInstance().getListeFonctionnaire(this.matriculeRecherche, this.nomRecherche, this.prenomRecherche);
/* 435 */     HelperC.afficherInformation("Information", String.valueOf(this.listeFonctionnaire.size()) + " �l�ment(s) trouv�(s)");
/*     */   }
/*     */ 
/*     */   
/*     */   public void affecterFonctionnaire() {
//     if (getFonctionnaire() != null) {
//       
//       setMatricule(getFonctionnaire().getMatricule());
//       setNomPrenom(getFonctionnaire().getNomPrenom());
//       if (this.fonctionRecherche != null)
//       {
//         this.idFonction = this.fonctionRecherche.getId();
//       }
//       PrimeFaces.current().executeScript("PF('dlgRechercheFonctionnaire').hide();");
//     } 
      }
/*     */ 
/*     */   
/*     */   public void initRechercheAffectation() {
/* 454 */     this.matriculeRecherche = "";
/* 455 */     this.nomRecherche = "";
/* 456 */     this.prenomRecherche = "";
/* 457 */     this.fonctionRecherche = null;
/* 458 */     this.idFonctionRecherche = 0;
/* 459 */     setComment("");
/* 460 */     if (this.listeAffectation != null)
/*     */     {
/* 462 */       this.listeAffectation.clear();
/*     */     }
/* 464 */     PrimeFaces.current().executeScript("PF('dlgRecherche').hide();");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void rechercherAffectation() {
/* 470 */     this.listeAffectation = FichierBaseDAO.getInstance().getListeAffectation(this.nomRecherche, this.prenomRecherche, this.matriculeRecherche, this.fonctionRecherche);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeAffectation() {
/* 476 */     if (this.affectation != null) {
/*     */       
/* 478 */       setId(this.affectation.getId());
/* 479 */       this.tabIndex = 0;
			    employe = FactoryDAO.getInstance().getEmployeSimple(affectation.getIdEmploye());
/* 480 */       setFonction(this.affectation.getFonction());
/* 481 */       this.idFonction = getFonction().getId();
                     
/* 482 */       
/* 483 */       setMatricule(employe.getMatricule());
/* 484 */       setNomPrenom(employe.getNomPrenom());
/* 485 */       setDateDebut(this.affectation.getDateDebut());
/* 486 */       setDateDebutS(this.affectation.getDateDebutS());
/* 487 */       setDateFin(this.affectation.getDateFin());
/* 488 */       setDateFinS(this.affectation.getDateFinS());
/* 489 */       setReference(this.affectation.getReference());
/* 490 */       setMotPasse(this.affectation.getMotPasse());
/* 491 */       setComment(this.affectation.getComment());
/* 492 */      
/* 493 */       changeFonctionnaire();
/* 494 */       if (employe.isPartenaire()) {
/*     */         
/* 496 */         this.readOnly = false;
/*     */       } else {
/*     */         
/* 499 */         this.readOnly = true;
/*     */       } 
/* 501 */       PrimeFaces.current().executeScript("PF('dlgRecherche').hide();");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\AffectationB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */