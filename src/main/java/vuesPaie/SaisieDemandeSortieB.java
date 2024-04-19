/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.HelperItext;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.ItextFooterHelper;
/*     */ import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
/*     */ import classesPaie.SaisieDemandeSortieC;
/*     */ import classesPaie.Tables;
/*     */ import com.itextpdf.text.BaseColor;
/*     */ import com.itextpdf.text.Chunk;
/*     */ import com.itextpdf.text.Document;
/*     */ import com.itextpdf.text.DocumentException;
/*     */ import com.itextpdf.text.Element;
/*     */ import com.itextpdf.text.Font;
/*     */ import com.itextpdf.text.FontFactory;
/*     */ import com.itextpdf.text.Image;
/*     */ import com.itextpdf.text.PageSize;
/*     */ import com.itextpdf.text.Paragraph;
/*     */ import com.itextpdf.text.Phrase;
/*     */ import com.itextpdf.text.pdf.PdfPCell;
/*     */ import com.itextpdf.text.pdf.PdfPTable;
/*     */ import com.itextpdf.text.pdf.PdfPageEvent;
/*     */ import com.itextpdf.text.pdf.PdfWriter;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.component.UIComponent;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class SaisieDemandeSortieB
/*     */   extends SaisieDemandeSortieC
/*     */ {
/*     */   private static final long serialVersionUID = -4364944743135832410L;
/*     */   private SaisieDemandeSortieC selected;
/*     */   private EmployeC selection;
/*  71 */   private List<SaisieDemandeSortieC> allSaisieDemandeSortie = new ArrayList<SaisieDemandeSortieC>();
/*  72 */   private List<SelectItem> listTypeConge = new ArrayList<SelectItem>();
/*  73 */   private List<SelectItem> listNatureConge = new ArrayList<SelectItem>(); 
			private String code; 
			private String codeRecherche; 
			private String nomRecherche;
/*     */   private String nom;
/*     */   private String service;
/*  76 */   private List<EmployeC> listEmploye = new ArrayList<EmployeC>(); 
			private String categorie; 
			private String grade; 
			private String codeEmployeRecherche; 
			private String nomEmployeRecherche; 
			private String prenomEmployeRecherche;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  80 */   private HttpSession session = HelperC.getSession();
/*     */ 	private int typeDec;
/*     */   ParametrageGeneralC parm;
/*     */   private boolean afficherMotif = false,demandeLine,desableSave;
/*     */ 
/*     */ 
/*     */   
/*     */   public SaisieDemandeSortieC getSelected() {
/*  88 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(SaisieDemandeSortieC selected) {
/*  92 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public EmployeC getSelection() {
/*  96 */     return this.selection;
/*     */   }
/*     */   
/*     */   public void setSelection(EmployeC selection) {
/* 100 */     this.selection = selection;
/*     */   }
/*     */   
/*     */   public List<SaisieDemandeSortieC> getAllSaisieDemandeSortie() {
/* 104 */     return this.allSaisieDemandeSortie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllSaisieDemandeSortie(List<SaisieDemandeSortieC> allSaisieDemandeSortie) {
/* 109 */     this.allSaisieDemandeSortie = allSaisieDemandeSortie;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListTypeConge() {
/* 113 */     return this.listTypeConge;
/*     */   }
/*     */   
/*     */   public void setListTypeConge(List<SelectItem> listTypeConge) {
/* 117 */     this.listTypeConge = listTypeConge;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListNatureConge() {
/* 121 */     return this.listNatureConge;
/*     */   }
/*     */   
/*     */   public void setListNatureConge(List<SelectItem> listNatureConge) {
/* 125 */     this.listNatureConge = listNatureConge;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 129 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 133 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getCodeRecherche() {
/* 137 */     return this.codeRecherche;
/*     */   }
/*     */   
/*     */   public void setCodeRecherche(String codeRecherche) {
/* 141 */     this.codeRecherche = codeRecherche;
/*     */   }
/*     */   
/*     */   public String getNomRecherche() {
/* 145 */     return this.nomRecherche;
/*     */   }
/*     */   
/*     */   public void setNomRecherche(String nomRecherche) {
/* 149 */     this.nomRecherche = nomRecherche;
/*     */   }
/*     */   
/*     */   public String getNom() {
/* 153 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/* 157 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public String getService() {
/* 161 */     return this.service;
/*     */   }
/*     */   
/*     */   public void setService(String service) {
/* 165 */     this.service = service;
/*     */   }
/*     */   
/*     */   public String getCategorie() {
/* 169 */     return this.categorie;
/*     */   }
/*     */   
/*     */   public void setCategorie(String categorie) {
/* 173 */     this.categorie = categorie;
/*     */   }
/*     */   
/*     */   public String getGrade() {
/* 177 */     return this.grade;
/*     */   }
/*     */   
/*     */   public void setGrade(String grade) {
/* 181 */     this.grade = grade;
/*     */   }
/*     */   
/*     */   public String getCodeEmployeRecherche() {
/* 185 */     return this.codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
/* 189 */     this.codeEmployeRecherche = codeEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getNomEmployeRecherche() {
/* 193 */     return this.nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setNomEmployeRecherche(String nomEmployeRecherche) {
/* 197 */     this.nomEmployeRecherche = nomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public String getPrenomEmployeRecherche() {
/* 201 */     return this.prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
/* 205 */     this.prenomEmployeRecherche = prenomEmployeRecherche;
/*     */   }
/*     */   
/*     */   public List<EmployeC> getListEmploye() {
/* 209 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<EmployeC> listEmploye) {
/* 213 */     this.listEmploye = listEmploye;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/* 217 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 221 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/* 225 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 229 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public DroitC getDroit() {
/* 233 */     return this.droit;
/*     */   }
/*     */   
/*     */   public void setDroit(DroitC droit) {
/* 237 */     this.droit = droit;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/* 241 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 245 */     this.session = session;
/*     */   }
/*     */   

/*     */   public boolean isAfficherMotif() {
/* 257 */     return this.afficherMotif;
/*     */   }
/*     */   
/*     */   public void setAfficherMotif(boolean afficherMotif) {
/* 261 */     this.afficherMotif = afficherMotif;
/*     */   }

		    public boolean isDemandeLine() {
				return demandeLine;
			}
		
			public void setDemandeLine(boolean demandeLine) {
				this.demandeLine = demandeLine;
			}
		
			public int getTypeDec() {
				return typeDec;
			}
		
			public void setTypeDec(int typeDec) {
				this.typeDec = typeDec;
			}
			
			public boolean isDesableSave() {
				return desableSave;
			}
			public void setDesableSave(boolean desableSave) {
				this.desableSave = desableSave;
			}
			
			
/*     */   @PostConstruct
/*     */   private void charger() {
/* 266 */     this.operateur = new OperateurC();
/* 267 */     this.exercice = new ExerciceC();
/* 268 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 269 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*     */     
/* 271 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 272 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/* 274 */     if (this.operateur == null || this.exercice == null) {
/*     */       try {
/* 276 */         FacesContext context = FacesContext.getCurrentInstance();
/* 277 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 278 */       } catch (IOException e) {
/*     */         
/* 280 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/* 283 */       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 284 */           this.operateur.getIdEmploye());
/* 285 */       if (userFonction != null) {
/* 286 */         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), 
/* 287 */             Constante.Role.gestionConge);
/*     */       }
/*     */ 		parm=FichierBaseDAO.getInstance().getParametrageGeneral(); 
/*     */       setIdExercice(exercice.getId());
/* 291 */       searchForm();
/*     */     } 
/*     */   }
		

		private void employeInLine(){
			selection = FactoryDAO.getInstance().getEmploye(operateur.getIdEmploye(), false);
			if (this.selection != null) {
				setObject1();
			}
		}
/*     */   public void findByCode() {
/* 299 */     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
/*     */     
/* 301 */     if (this.selection != null) {
/* 302 */       setObject1();
/*     */     }
/*     */   }
/*     */   
/*     */   public void findEmployeRecherche() {
/* 307 */     this.selection = FactoryDAO.getInstance().getEmploye(this.codeRecherche, false);
/*     */     
/* 309 */     if (this.selection != null) {
/* 310 */       setObject1();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void findDemandeSortie() {
/* 316 */     setEtatDemandeSortie(Constante.EtatDemandeSortie.demandeSortie);
/* 317 */    allSaisieDemandeSortie = FactoryDAO.getInstance().getListeSaisieDemandeSortie(Constante.getEtatDemandeSortie(getEtatDemandeSortie()),0);
/*     */   }
			public void findDemandeTraite() {
/* 316 */    setEtatDemandeSortie(Constante.EtatDemandeSortie.traite);
/* 317 */    allSaisieDemandeSortie = FactoryDAO.getInstance().getListeSaisieDemandeSortie(Constante.getEtatDemandeSortie(getEtatDemandeSortie()),typeDec);
/*     */   }
		
			public void getListDemandeSortie() {

				allSaisieDemandeSortie = FactoryDAO.getInstance().getListeSaisieDemandeSortie(getEmploye());
			}

/*     */   public void chargerEmploye() {
/* 330 */     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,false);
/*     */   }
/*     */   
/*     */   public void changeHeureDepart() {
				desableSave=false;
/* 334 */     if (getHeureDepart() != null) {
/* 335 */       if (getHeureDepart() != null && 
/*     */         
/* 337 */         !getHeureDepart().replace("_", "").trim().replace(":", "").equals("") && (
/* 338 */         Integer.parseInt(getHeureDepart().split(":")[0]) > 23 || 
/* 339 */         Integer.parseInt(getHeureDepart().split(":")[1]) > 59))
/*     */       {
/*     */         desableSave=true;
/* 342 */         HelperC.afficherMessage("Information", "Heure Invalide!");
/*     */       }
/*     */       try {
/* 345 */         calculTempsSortie();
/* 346 */         if (getNombreMin() < 0L) {
/* 347 */           HelperC.afficherAttention("ATTENTION", "L'heure de retour ne peut pas �tre inf�rieure � l'heure de d�part ");
					desableSave=true;
/*     */         }
/* 349 */       } catch (ParseException e) {
/*     */         
/* 351 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeHeureRetour() {
				desableSave=false;
/* 357 */     if (getHeureRetour() != null) {
/* 358 */       if (getHeureRetour() != null && 
/*     */         
/* 360 */         !getHeureRetour().replace("_", "").trim().replace(":", "").equals("") && (
/* 361 */         Integer.parseInt(getHeureRetour().split(":")[0]) > 23 || 
/* 362 */         Integer.parseInt(getHeureRetour().split(":")[1]) > 59)) {
/* 363 */         HelperC.afficherMessage("Information", "Heure Invalide!");
					desableSave=true;
/*     */       }
/*     */       
/*     */       try {
/* 367 */         calculTempsSortie();
/* 368 */         if (getNombreMin() < 0L) {
/* 369 */           HelperC.afficherAttention("ATTENTION", "L'heure de retour ne peut pas �tre inf�rieure � l'heure de d�part ");
					desableSave=true;
/*     */         }
/* 371 */       } catch (ParseException e) {
/*     */         
/* 373 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculTempsSortie() throws ParseException {
/* 381 */     if (!getHeureDepart().equalsIgnoreCase("") && !getHeureRetour().equalsIgnoreCase("")) {
/*     */       
/* 383 */       Date HD = (new SimpleDateFormat("hh:mm")).parse(getHeureDepart());
/*     */       
/* 385 */       Date HF = (new SimpleDateFormat("hh:mm")).parse(getHeureRetour());
 
/* 398 */       long result = (HF.getTime() - HD.getTime()) / 60000L;
/* 399 */       setNombreMin(result);
/* 400 */      
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 410 */     if (this.selected != null) {
/* 411 */       setId(this.selected.getId());
/* 412 */       setEmploye(this.selected.getEmploye());
				setDateDemande(this.selected.getDateDemande());
/* 421 */       setDateDemandeS(this.selected.getDateDemandeS());
/* 422 */       setHeureDepart(this.selected.getHeureDepart());
/* 423 */       setHeureRetour(this.selected.getHeureRetour());
/* 424 */       setNombreMin(this.selected.getNombreMin());
/* 425 */       setMotifSortie(this.selected.getMotifSortie());
/* 426 */       setDateSortie(this.selected.getDateSortie());
/* 427 */       setDateSortieS(this.selected.getDateSortieS());
/* 428 */       setDateValidation(this.selected.getDateValidation());
/* 429 */       setDateValidationS(this.selected.getDateValidationS());
/* 430 */       setImputableAuxPresences(this.selected.isImputableAuxPresences());
/* 431 */       setDecision(this.selected.getDecision());
/* 432 */       setEtatSortie(this.selected.getEtatSortie());
/*     */       setMotifRefusSortie(this.selected.getMotifRefusSortie());
				setEtatDemandeSortie(selected.getEtatDemandeSortie());
				selection=selected.getEmploye();
				setObject1();
/* 434 */       
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setObject1() {
/* 448 */     if (this.selection != null) {
/*     */       
/* 450 */       setEmploye(this.selection);
/* 451 */       if (getEmploye() != null) {
/* 452 */         this.code = getEmploye().getCode();
/* 453 */         this.codeRecherche = getEmploye().getCode();
/*     */       } else {
/* 455 */         this.code = "";
/* 456 */         this.codeRecherche = "";
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 463 */     if (b)
/* 464 */       setId(0); 
/* 465 */     setEmploye(null);
/* 466 */     this.selected = null;
/* 467 */     this.selection = null;
/* 468 */     this.code = "";
/* 469 */     this.nom = "";
/* 470 */     setDateDemande(null);
/* 471 */     setDateDemandeS("");
/* 472 */     setHeureDepart("");
/* 473 */     setHeureRetour("");
/* 474 */     setNombreMin(0L);
/* 475 */     setMotifSortie("");
/* 476 */     setDateSortie(null);
/* 477 */     setDateSortieS("");
/* 478 */     setDateValidation(null);
/* 479 */     setDateValidationS("");
/* 480 */     setImputableAuxPresences(false);
/* 481 */     setDecideur(null);
/* 482 */     setEtatSortie(0);
/* 483 */     setEtatDemandeSortie(null);
/* 484 */     setLibelleEtatSortie("");
/* 485 */     setMotifRefusSortie("");
			  setDecision(0);
/* 486 */     this.allSaisieDemandeSortie.clear();
			  desableSave=false;
/*     */   }
/*     */   
/*     */   public void initialiser() {
/* 490 */     clear(true);
/*     */   }
/*     */   
/*     */   public void changeDateDemande() {
			  desableSave=false;
/* 494 */     if (getDateDemandeS().replace("/", "").replace("_", "").trim().equals("")) {
/* 495 */       setDateDemande(null);
/*     */     } else {
/*     */       
/* 498 */       setDateDemande(HelperC.validerDate(getDateDemandeS()));
/* 499 */       if (getDateDemande() == null) {
/* 500 */         setDateDemandeS("");
/* 501 */         HelperC.afficherMessage("Information", "Date invalide");
					desableSave=true;
/*     */       } else {
/*     */         
/* 504 */         setDateDemandeS(HelperC.convertDate(getDateDemande()));
/* 505 */         if (getDateSortie() != null && getDateSortie().before(getDateDemande())) {
/* 506 */           HelperC.afficherMessage("Information", "La date sortie ne peut pas �tre ant�rieure � la date demande");
					desableSave=true;
/*     */         }
/* 508 */         if (getDateValidation() != null && getDateValidation().before(getDateDemande())) {
/* 509 */           HelperC.afficherMessage("Information", "La date validation  ne peut pas �tre ant�rieure � la date demande");
					desableSave=true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeDateSortie() {
			desableSave=false;
/* 516 */     if (getDateSortieS().replace("/", "").replace("_", "").trim().equals("")) {
/* 517 */       setDateSortie(null);
/*     */     } else {
/*     */       
/* 520 */       setDateSortie(HelperC.validerDate(getDateSortieS()));
/* 521 */       if (getDateSortie() == null) {
/* 522 */         setDateSortieS("");
/* 523 */         HelperC.afficherMessage("Information", "Date invalide");
				desableSave=true;
/*     */       } else {
/*     */         
/* 526 */         setDateSortieS(HelperC.convertDate(getDateSortie()));
/* 527 */         if (getDateDemande() != null && getDateSortie().before(getDateDemande())) {
/* 528 */           HelperC.afficherMessage("Information", "La date sortie ne peut pas �tre ant�rieure � la date demande");
					desableSave=true;
/*     */         }
/* 530 */         if (getDateValidation() != null && getDateSortie().before(getDateValidation())) {
/* 531 */           HelperC.afficherMessage("Information", "La date sortie ne peut pas �tre ant�rieure � la date validation demande");
					desableSave=true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateValidation() {
			desableSave=false;
/* 539 */     if (getDateValidationS().replace("/", "").replace("_", "").trim().equals("")) {
/* 540 */       setDateValidation(null);
/*     */     } else {
/*     */       
/* 543 */       setDateValidation(HelperC.validerDate(getDateValidationS()));
/* 544 */       if (getDateValidation() == null) {
/* 545 */         setDateValidationS("");
/* 546 */         HelperC.afficherMessage("Information", "Date invalide");
					desableSave=true;
/*     */       } else {
/*     */         
/* 549 */         setDateValidationS(HelperC.convertDate(getDateValidation()));
/* 550 */         if (getDateDemande() != null && getDateValidation().before(getDateDemande())) {
/* 551 */           HelperC.afficherMessage("Information", "La date de validation ne peut pas �tre ant�rieure � la date de demande");
					desableSave=true;
/*     */         }
/* 553 */         if (getDateSortie() != null && getDateSortie().before(getDateValidation())) {
/* 554 */           HelperC.afficherMessage("Information", "La date de sortie ne peut pas �tre ant�rieure � la date de validation de demande");
					desableSave=true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void searchForm() {
/* 561 */     UIComponent frm = null;
/* 562 */     FacesContext context = FacesContext.getCurrentInstance();
/*     */     
/* 564 */     frm = context.getViewRoot().findComponent("frmSaisieDemandeSortie");
/* 565 */     if (frm != null) {
/* 566 */       setDateDemande(new Date());
/* 567 */       setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
/* 568 */       setEtatDemandeSortie(Constante.EtatDemandeSortie.demandeSortie);
/* 569 */      
/*     */     } 
/*     */ 
/*     */     
/* 573 */     frm = context.getViewRoot().findComponent("frmValidationDemandeSortie");
/* 574 */     if (frm != null) {
/* 575 */       setDateValidation(new Date());
/* 576 */       setDateValidationS(HelperC.changeDateFormate(getDateValidation()));
/* 577 */       setEtatDemandeSortie(Constante.EtatDemandeSortie.traite);
/* 579 */      
/*     */     } 

			  frm = context.getViewRoot().findComponent("frmDemandeSortieLine");
/* 574 */     if (frm != null) {
/* 575 */       setDateDemande(new Date());
/* 576 */       setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
/* 577 */       setEtatDemandeSortie(Constante.EtatDemandeSortie.demandeSortie);
/* 579 */       demandeLine=true;
				employeInLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   
/*     */   public void changeImputable(ValueChangeEvent event) {
/* 595 */     setImputableAuxPresences(((Boolean)event.getNewValue()).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void modifDemande() {
/* 600 */     setObject();
/*     */   }
/*     */   
/*     */   public void clear2() {
/* 604 */     this.listEmploye.clear();
/* 605 */     this.codeEmployeRecherche = "";
/* 606 */     this.nomEmployeRecherche = "";
/* 607 */     this.prenomEmployeRecherche = "";
/*     */   }
/*     */   public void clear3() {
/* 610 */     this.allSaisieDemandeSortie.clear();
/* 611 */     this.codeRecherche = "";
/* 612 */     PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
/*     */   }
/*     */   
/*     */   public void saveDemande() {
/* 616 */     if (getId() == 0 && !this.droit.isCreer()) {
/* 617 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/* 618 */     } else if (getId() > 0 && !this.droit.isModifier()) {
/* 619 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/* 620 */     } else if (getEmploye() == null || getDateDemande() == null || getDateSortie() == null || 
/* 621 */       getMotifSortie().equalsIgnoreCase("")) {
/* 622 */       HelperC.afficherMessage("Information", "Veillez pr�ciser l'employ�, la date demande, la date sortie et le motif");
/*     */     }
/* 624 */     else if (getDateSortie().before(getDateDemande())) {
/* 625 */       HelperC.afficherMessage("Information", "La date  sortie ne peut pas �tre ant�rieure � la date demande");
/*     */     }
/* 627 */     else if (getNombreMin() < 0L) {
/* 628 */       HelperC.afficherAttention("ATTENTION", "L'heure de retour ne peut pas �tre inf�rieure � l'heure de d�part ");
/*     */     }
/* 630 */     else if (this.selected != null && !getEtatDemandeSortie().equals(Constante.EtatDemandeSortie.demandeSortie)) {
/* 631 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier une demande d�j� valid�e");
/*     */     }
/*     */     else {
/*     */       
/* 635 */       Historique hist = new Historique();
/* 636 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 637 */       hist.setOperateur(this.operateur);
/* 638 */       if (getId() == 0) {
/* 639 */         hist.setOperation("Cr�ation de la saisie de demande de la sortie ");
/*     */       } else {
/* 641 */         hist.setOperation("Modification de la saisie de demande de la sortie ");
/* 642 */       }  hist.setTable(Tables.getTableName(Tables.TableName.saisieSortie));
/* 643 */       setHistorique(hist);
/*     */       setEtatSortie(Constante.getEtatDemandeSortie(Constante.EtatDemandeSortie.demandeSortie));
/* 645 */       if (FactoryDAO.getInstance().insertUpdateSaisieDemandeSortie(this)) {
/* 646 */         HelperC.afficherMessage("F�licitation", "Enregistrement avec succ�s");
/*     */         
/* 648 */         clear(true);
/*     */       } else {
/*     */         
/* 651 */         HelperC.afficherMessage("D�sol�", "Echec d'enregistrement");
/*     */       } 
/*     */     } 
/*     */   }

			
			
/*     */   public void saveDecision() {
/* 657 */     if (getId() == 0 && !this.droit.isCreer()) {
/* 658 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/* 659 */     } else if (getId() > 0 && !this.droit.isModifier()) {
/* 660 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/* 661 */     } else if (getId() == 0) {
/* 662 */       HelperC.afficherAttention("ATTENTION", "Veillez d'abord choisir la demande de sortie � valider ");
/* 663 */     } else if (getEmploye() == null || getDateDemande() == null || getDateSortie() == null || 
/* 664 */       getMotifSortie().equalsIgnoreCase("")) {
/* 665 */       HelperC.afficherMessage("Information", "Veillez remplir tous les champs obligatoires");
/*     */     }
/* 667 */     else if (getDateSortie().before(getDateDemande())) {
/* 668 */       HelperC.afficherMessage("Information", "La date sortie ne peut pas �tre ant�rieure � la date demande");
/* 669 */     } else if (getDateSortie().before(getDateValidation())) {
/* 670 */       HelperC.afficherMessage("Information", "La date sortie ne peut pas �tre ant�rieure � la date de validation demande");
/* 671 */     } else if (getDateValidation().before(getDateDemande())) {
/* 672 */       HelperC.afficherMessage("Information", "La date validation ne peut pas �tre ant�rieure � la date demande");
/*     */     }
/* 674 */     else if (getNombreMin() < 0L) {
/* 675 */       HelperC.afficherAttention("ATTENTION", "L'heure de retour ne peut pas �tre inf�rieure � l'heure de d�part ");
/*     */     }
/* 677 */     
/* 685 */      setEtatSortie(Constante.getEtatDemandeSortie(Constante.EtatDemandeSortie.traite));
/*     */       
/* 687 */       Historique hist = new Historique();
/* 688 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 689 */       hist.setOperateur(this.operateur);
/* 690 */       if (getId() == 0) {
/* 691 */         hist.setOperation("Cr�ation de la saisie de demande sortie ");
/*     */       } else {
/* 693 */         hist.setOperation("Modification de la saisie de demande sortie ");
/* 694 */       }  hist.setTable(Tables.getTableName(Tables.TableName.saisieSortie));
/* 695 */       setHistorique(hist);
/*     */       
/* 697 */       if (FactoryDAO.getInstance().insertUpdateSaisieDemandeSortie(this)) {
/* 698 */         HelperC.afficherMessage("Information", "Enregistrement avec succ�s");
			
			if (parm != null) {
				if (getDecision() == 1)
					HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(),
							parm.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de cong� a �t� accept�e ",
							"Demande cong�");
				if (getDecision() == 2)
					HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(),
							parm.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de cong� a �t� refus�e",
							"Demande cong�");
			}
/* 700 */         clear(true);
/*     */       } else {
/*     */         
/* 703 */         HelperC.afficherMessage("Information", "Echec d'enregistrement");
/*     */       } 
/*     */     } 
/*     */   
/*     */   
/*     */   public void delete() {
/* 709 */     if (this.selected == null) {
/* 710 */       HelperC.afficherDeleteMessage();
/* 711 */     } else if (this.selected != null && !this.droit.isSupprimer()) {
/* 712 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/* 713 */     }  if (this.selected != null && selected.getDecision()==0) {
/* 714 */       	FactoryDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.saisieSortie));
/*     */       
/* 716 */       clear(true);
/* 717 */       HelperC.afficherMessage("F�licitation", "Suppression avec succ�s");
/*     */     } 
				else
					 HelperC.afficherAttention("ATTENTION", "On ne peut pas supprimer la demande apr�s la prise de d�cision ");
/*     */   }
/*     */   
/*     */   public void onRowselected(SelectEvent event) {
/* 722 */     this.selected = (SaisieDemandeSortieC)event.getObject();
/* 723 */     setObject();
/* 724 */     PrimeFaces.current().executeScript("PF('dlgDemand').hide();");
/* 725 */     PrimeFaces.current().executeScript("PF('dlgValid').hide();");
/*     */   }
/*     */   
/*     */   public void onRowselected1(SelectEvent event) {
/* 730 */     this.selection = (EmployeC)event.getObject();
/* 731 */     setObject1();
/* 732 */     PrimeFaces.current().executeScript("PF('dlg').hide();");
/* 733 */     PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
/* 734 */     PrimeFaces.current().executeScript("PF('rechercheDialog1').hide();");
/*     */   }
/*     */   
/*     */   public void onRowselected2(SelectEvent event) {
/* 738 */     this.selection = (EmployeC)event.getObject();
/* 739 */     setObject1();
/* 740 */     PrimeFaces.current().executeScript("PF('dlge').hide();");
/* 741 */     
/* 742 */     PrimeFaces.current().executeScript("PF('rechercheDialog1').hide();");
/*     */   }

			public void annuler() {
/* 709 */     if (this.selected == null) {
/* 710 */       HelperC.afficherAttention("ATTENTION", "Aucun �l�ment � annuler");
/* 711 */     } else  {
/* 714 */       if(FactoryDAO.getInstance().annulerDecisionSortie(selected))
/*     */       {
/* 716 */       clear(true);
/* 717 */       HelperC.afficherMessage("Info", "Annulation r�ussie !");
				}
/*     */     } 
/*     */   }
/*     */   

			
/*     */   public void printSortie() {
/* 748 */     if (this.selected == null) {
/* 749 */       HelperC.afficherAttention("Attention", "Veillez d'abord selectionner la sortie � imprimer");
/* 750 */     } else if (!getEtatDemandeSortie().equals(Constante.EtatDemandeSortie.traite)) {
/* 751 */       HelperC.afficherAttention("Attention", "cette sortie n'est pas accord�e");
/*     */     } else {
/*     */       try {
/* 754 */         Image image = null;
/* 755 */         Document doc = new Document(PageSize.A4);
/* 756 */         ByteArrayOutputStream docMem = new ByteArrayOutputStream();
/* 757 */         PdfWriter writer = PdfWriter.getInstance(doc, docMem);
/*     */         
/* 759 */         doc.addAuthor("Asyst Resources LTD");
/* 760 */         doc.addProducer();
/* 761 */         doc.addCreationDate();
/* 762 */         doc.addTitle("Billet de sortie");
/*     */ 
/*     */         
/* 765 */         doc.open();
/*     */         
/* 767 */         ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
/* 768 */         image = Image.getInstance(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + "logoUniversite.png");
/* 769 */         image.scaleAbsoluteHeight(90.0F);
/* 770 */         image.scaleAbsoluteWidth(180.0F);
/*     */         
/* 772 */         doc.add((Element)image);
/*     */ 
/*     */         
/* 775 */         writer.setPageEvent((PdfPageEvent)new ItextFooterHelper(new Phrase("Produit Asyst Resources Ltd", 
/* 776 */                 new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0))));
/*     */         
/* 778 */         doc.add((Element)pageHeader());
/* 779 */         doc.add((Element)getTableBillet());
/* 780 */         doc.add((Element)getTableSignature());
/* 781 */         doc.close();
/*     */         
/* 783 */         FacesContext context = FacesContext.getCurrentInstance();
/* 784 */         HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
/*     */         
/* 786 */         res.setHeader("Cache-Control", "Max-age=100");
/* 787 */         res.setContentType("application/pdf");
/* 788 */         res.setHeader("content-disposition", "inline;filename=BilletSortie.pdf");
/*     */         
/* 790 */         ServletOutputStream out = res.getOutputStream();
/*     */         
/* 792 */         res.setContentLength(docMem.size());
/* 793 */         docMem.writeTo((OutputStream)out);
/* 794 */         out.flush();
/* 795 */         out.close();
/* 796 */         context.responseComplete();
/*     */       }
/* 798 */       catch (Exception e) {
/*     */         
/* 800 */         System.out.println(e.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private PdfPTable pageHeader() throws DocumentException, IOException {
/* 806 */     PdfPTable table = null;
/* 807 */     table = new PdfPTable(2);
/* 808 */     PdfPCell cell = new PdfPCell();
/* 809 */     table.setWidthPercentage(100.0F);
/* 810 */     cell.setBorder(0);
/* 811 */     int[] largeurCollones = { 50, 50 };
/* 812 */     table.setWidths(largeurCollones);
/*     */ 
/*     */     
/* 815 */     cell = HelperItext.getPdfCell(HelperC.convertDate(Calendar.getInstance().getTime()), 
/* 816 */         FontFactory.getFont("Times-Roman", 8.0F, 0, BaseColor.BLACK), 
/* 817 */         1, 3, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
/* 818 */     table.addCell(cell);
/*     */     
/* 820 */     cell = HelperItext.getPdfCell("", 
/* 821 */         FontFactory.getFont("Times-Roman", 12.0F, 0, BaseColor.BLACK), 
/* 822 */         1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
/* 823 */     table.addCell(cell);
/*     */     
/* 825 */     cell = HelperItext.getPdfCell("BILLET DE SORTIE ", 
/* 826 */         FontFactory.getFont("Times-Roman", 12.0F, 1, BaseColor.BLACK), 
/* 827 */         1, 1, 1, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
/* 828 */     cell.setBorder(2);
/* 829 */     table.addCell(cell);
/*     */     
/* 831 */     return table;
/*     */   }
/*     */   
/*     */   private PdfPTable getTableBillet() throws DocumentException {
/* 835 */     PdfPTable tabInfo = new PdfPTable(2);
/* 836 */     int[] widthsInfo = { 50, 50 };
/* 837 */     tabInfo.setWidthPercentage(100.0F);
/* 838 */     tabInfo.setWidths(widthsInfo);
/*     */     
/* 840 */     PdfPCell cell = new PdfPCell();
/*     */ 		
/*     */    
/* 843 */     Phrase phrase = null;
/*     */     
/* 845 */     Paragraph p = null;
/* 846 */     cell = HelperItext.getPdfCell("  ", 
/* 847 */         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
/* 848 */         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
/* 849 */     tabInfo.addCell(cell);
/*     */     
/* 851 */     phrase = new Phrase();
/* 852 */     phrase.add((Element)new Chunk("Nom et Pr�nom : ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 853 */     phrase.add((Element)new Chunk(getEmploye().getNomPrenom(), FontFactory.getFont("Courier", 9.0F, 1)));
/*     */     
/* 855 */     p = new Paragraph();
/* 856 */     p.add((Element)phrase);
/* 857 */     cell = HelperItext.getCellule((Element)p, 
/* 858 */         1, 0, 
/* 859 */         0, 3, 0.0F, 3.0F);
/*     */     
/* 861 */     tabInfo.addCell(cell);
/*     */     
/* 863 */     cell = HelperItext.getPdfCell("  ", 
/* 864 */         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
/* 865 */         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
/* 866 */     tabInfo.addCell(cell);
/*     */     
/* 868 */     phrase = new Phrase();
/* 869 */     phrase.add((Element)new Chunk("Date Sortie : ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 870 */     phrase.add((Element)new Chunk(getDateSortieS(), FontFactory.getFont("Courier", 9.0F, 1)));
/*     */     
/* 872 */     p = new Paragraph();
/* 873 */     p.add((Element)phrase);
/* 874 */     cell = HelperItext.getCellule((Element)p, 
/* 875 */         1, 0, 
/* 876 */         0, 3, 0.0F, 3.0F);
/*     */     
/* 878 */     tabInfo.addCell(cell);
/*     */     
/* 880 */     cell = HelperItext.getPdfCell("  ", 
/* 881 */         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
/* 882 */         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
/* 883 */     tabInfo.addCell(cell);
/*     */     
/* 885 */     phrase = new Phrase();
/* 886 */     phrase.add((Element)new Chunk("Heure de d�part : ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 887 */     phrase.add((Element)new Chunk(getHeureDepart(), FontFactory.getFont("Courier", 9.0F, 1)));
/*     */     
/* 889 */     p = new Paragraph();
/* 890 */     p.add((Element)phrase);
/* 891 */     cell = HelperItext.getCellule((Element)p, 
/* 892 */         1, 0, 
/* 893 */         0, 3, 0.0F, 3.0F);
/* 894 */     tabInfo.addCell(cell);
/*     */     
/* 896 */     cell = HelperItext.getPdfCell("  ", 
/* 897 */         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
/* 898 */         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
/* 899 */     tabInfo.addCell(cell);
/*     */     
/* 901 */     phrase = new Phrase();
/* 902 */     phrase.add((Element)new Chunk("Heure de retour : ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 903 */     phrase.add((Element)new Chunk(getHeureRetour(), FontFactory.getFont("Courier", 9.0F, 1)));
/*     */     
/* 905 */     p = new Paragraph();
/* 906 */     p.add((Element)phrase);
/* 907 */     cell = HelperItext.getCellule((Element)p, 
/* 908 */         1, 0, 
/* 909 */         5, 3, 0.0F, 3.0F);
/* 910 */     tabInfo.addCell(cell);
/*     */     
/* 912 */     cell = HelperItext.getPdfCell("  ", 
/* 913 */         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
/* 914 */         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
/* 915 */     tabInfo.addCell(cell);
/*     */     
/* 917 */     phrase = new Phrase();
/* 918 */     phrase.add((Element)new Chunk("Motif : ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 919 */     phrase.add((Element)new Chunk(getMotifSortie(), FontFactory.getFont("Courier", 9.0F, 1)));
/*     */     
/* 921 */     p = new Paragraph();
/* 922 */     p.add((Element)phrase);
/* 923 */     cell = HelperItext.getCellule((Element)p, 
/* 924 */         1, 0, 
/* 925 */         0, 3, 0.0F, 3.0F);
/* 926 */     tabInfo.addCell(cell);

			 phrase = new Phrase();
			  p = new Paragraph();
/* 906 */     p.add((Element)phrase);
/* 907 */     cell = HelperItext.getCellule((Element)p, 
/* 908 */         1, 0, 
/* 909 */         5, 3, 0.0F, 3.0F);
/* 910 */     tabInfo.addCell(cell);
/*     */     
/* 912 */     cell = HelperItext.getPdfCell("  ", 
/* 913 */         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
/* 914 */         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
/* 915 */     tabInfo.addCell(cell);

			  phrase = new Phrase();
/* 918 */     phrase.add((Element)new Chunk("D�cision : ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 919 */     phrase.add((Element)new Chunk(getLibelleDecision(), FontFactory.getFont("Courier", 9.0F, 1)));
/*     */     
/* 921 */     p = new Paragraph();
/* 922 */     p.add((Element)phrase);
/* 923 */     cell = HelperItext.getCellule((Element)p, 
/* 924 */         1, 0, 
/* 925 */         0, 3, 0.0F, 3.0F);
/* 926 */     tabInfo.addCell(cell);

/* 930 */     return tabInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   private PdfPTable getTableSignature() throws DocumentException {
/* 935 */     PdfPTable tabInfo = new PdfPTable(4);
/* 936 */     int[] widthsInfo = { 20, 10, 20, 40 };
/* 937 */     tabInfo.setWidthPercentage(100.0F);
/* 938 */     tabInfo.setWidths(widthsInfo);
/* 939 */     tabInfo.setKeepTogether(true);
/* 940 */     PdfPCell cell = new PdfPCell();
/*     */ 
/*     */     
/* 943 */     Phrase phrase = null;
/*     */     
/* 945 */     Paragraph p = null;
/* 946 */     cell = HelperItext.getPdfCell("  ", 
/* 947 */         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
/* 948 */         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
/* 949 */     tabInfo.addCell(cell);
/*     */     
/* 951 */     phrase = new Phrase();
/* 952 */     phrase.add((Element)new Chunk("Signature du demandeur ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 953 */     phrase.add((Element)new Chunk(" ", FontFactory.getFont("Courier", 9.0F, 0)));
/*     */     
/* 955 */     p = new Paragraph();
/* 956 */     p.add((Element)phrase);
/* 957 */     cell = HelperItext.getCellule((Element)p, 
/* 958 */         1, 0, 
/* 959 */         0, 3, 0.0F, 1.0F);
/*     */     
/* 961 */     tabInfo.addCell(cell);
/*     */     
/* 963 */     phrase = new Phrase();
/* 964 */     phrase.add((Element)new Chunk("Visa pour autorisation ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 965 */     phrase.add((Element)new Chunk(" ", FontFactory.getFont("Courier", 9.0F, 0)));
/* 966 */     p = new Paragraph();
/* 967 */     p.add((Element)phrase);
/* 968 */     cell = HelperItext.getCellule((Element)p, 
/* 969 */         1, 0, 
/* 970 */         0, 3, 0.0F, 1.0F);
/*     */     
/* 972 */     tabInfo.addCell(cell);
/*     */ 
/*     */     
/* 975 */     return tabInfo;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\SaisieDemandeSortieB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */