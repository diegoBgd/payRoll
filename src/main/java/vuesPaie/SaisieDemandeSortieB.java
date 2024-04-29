 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.HelperItext;
 import classesPaie.Historique;
 import classesPaie.ItextFooterHelper;
 import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
 import classesPaie.SaisieDemandeSortieC;
 import classesPaie.Tables;
 import com.itextpdf.text.BaseColor;
 import com.itextpdf.text.Chunk;
 import com.itextpdf.text.Document;
 import com.itextpdf.text.DocumentException;
 import com.itextpdf.text.Element;
 import com.itextpdf.text.Font;
 import com.itextpdf.text.FontFactory;
 import com.itextpdf.text.Image;
 import com.itextpdf.text.PageSize;
 import com.itextpdf.text.Paragraph;
 import com.itextpdf.text.Phrase;
 import com.itextpdf.text.pdf.PdfPCell;
 import com.itextpdf.text.pdf.PdfPTable;
 import com.itextpdf.text.pdf.PdfPageEvent;
 import com.itextpdf.text.pdf.PdfWriter;
 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.component.UIComponent;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.ServletContext;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;

 
 @ManagedBean
 @ViewScoped
 public class SaisieDemandeSortieB
   extends SaisieDemandeSortieC
 {
   private static final long serialVersionUID = -4364944743135832410L;
   private SaisieDemandeSortieC selected;
   private EmployeC selection;
   private List<SaisieDemandeSortieC> allSaisieDemandeSortie = new ArrayList<SaisieDemandeSortieC>();
   private List<SelectItem> listTypeConge = new ArrayList<SelectItem>();
   private List<SelectItem> listNatureConge = new ArrayList<SelectItem>(); 
			private String code; 
			private String codeRecherche; 
			private String nomRecherche;
   private String nom;
   private String service;
   private List<EmployeC> listEmploye = new ArrayList<EmployeC>(); 
			private String categorie; 
			private String grade; 
			private String codeEmployeRecherche; 
			private String nomEmployeRecherche; 
			private String prenomEmployeRecherche;
   private OperateurC operateur;
   private ExerciceC exercice;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
 	private int typeDec;
   ParametrageGeneralC parm;
   private boolean afficherMotif = false,demandeLine,desableSave;
 
 
   
   public SaisieDemandeSortieC getSelected() {
     return this.selected;
   }
   
   public void setSelected(SaisieDemandeSortieC selected) {
     this.selected = selected;
   }
   
   public EmployeC getSelection() {
     return this.selection;
   }
   
   public void setSelection(EmployeC selection) {
     this.selection = selection;
   }
   
   public List<SaisieDemandeSortieC> getAllSaisieDemandeSortie() {
     return this.allSaisieDemandeSortie;
   }
 
   
   public void setAllSaisieDemandeSortie(List<SaisieDemandeSortieC> allSaisieDemandeSortie) {
     this.allSaisieDemandeSortie = allSaisieDemandeSortie;
   }
   
   public List<SelectItem> getListTypeConge() {
     return this.listTypeConge;
   }
   
   public void setListTypeConge(List<SelectItem> listTypeConge) {
     this.listTypeConge = listTypeConge;
   }
   
   public List<SelectItem> getListNatureConge() {
     return this.listNatureConge;
   }
   
   public void setListNatureConge(List<SelectItem> listNatureConge) {
     this.listNatureConge = listNatureConge;
   }
   
   public String getCode() {
     return this.code;
   }
   
   public void setCode(String code) {
     this.code = code;
   }
   
   public String getCodeRecherche() {
     return this.codeRecherche;
   }
   
   public void setCodeRecherche(String codeRecherche) {
     this.codeRecherche = codeRecherche;
   }
   
   public String getNomRecherche() {
     return this.nomRecherche;
   }
   
   public void setNomRecherche(String nomRecherche) {
     this.nomRecherche = nomRecherche;
   }
   
   public String getNom() {
     return this.nom;
   }
   
   public void setNom(String nom) {
     this.nom = nom;
   }
   
   public String getService() {
     return this.service;
   }
   
   public void setService(String service) {
     this.service = service;
   }
   
   public String getCategorie() {
     return this.categorie;
   }
   
   public void setCategorie(String categorie) {
     this.categorie = categorie;
   }
   
   public String getGrade() {
     return this.grade;
   }
   
   public void setGrade(String grade) {
     this.grade = grade;
   }
   
   public String getCodeEmployeRecherche() {
     return this.codeEmployeRecherche;
   }
   
   public void setCodeEmployeRecherche(String codeEmployeRecherche) {
     this.codeEmployeRecherche = codeEmployeRecherche;
   }
   
   public String getNomEmployeRecherche() {
     return this.nomEmployeRecherche;
   }
   
   public void setNomEmployeRecherche(String nomEmployeRecherche) {
     this.nomEmployeRecherche = nomEmployeRecherche;
   }
   
   public String getPrenomEmployeRecherche() {
     return this.prenomEmployeRecherche;
   }
   
   public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
     this.prenomEmployeRecherche = prenomEmployeRecherche;
   }
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
   
   public OperateurC getOperateur() {
     return this.operateur;
   }
   
   public void setOperateur(OperateurC operateur) {
     this.operateur = operateur;
   }
   
   public ExerciceC getExercice() {
     return this.exercice;
   }
   
   public void setExercice(ExerciceC exercice) {
     this.exercice = exercice;
   }
   
   public DroitC getDroit() {
     return this.droit;
   }
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }
   
   public HttpSession getSession() {
     return this.session;
   }
   
   public void setSession(HttpSession session) {
     this.session = session;
   }
   

   public boolean isAfficherMotif() {
     return this.afficherMotif;
   }
   
   public void setAfficherMotif(boolean afficherMotif) {
     this.afficherMotif = afficherMotif;
   }

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
			
			
   @PostConstruct
   private void charger() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     
     if (this.operateur == null || this.exercice == null) {
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       } catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(
           this.operateur.getIdEmploye());
       if (userFonction != null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), 
             Constante.Role.gestionConge);
       }
 		parm=FichierBaseDAO.getInstance().getParametrageGeneral(); 
       setIdExercice(exercice.getId());
       searchForm();
     } 
   }
		

		private void employeInLine(){
			selection = FactoryDAO.getInstance().getEmploye(operateur.getIdEmploye(), false);
			if (this.selection != null) {
				setObject1();
			}
		}
   public void findByCode() {
     this.selection = FactoryDAO.getInstance().getEmploye(this.code, false);
     
     if (this.selection != null) {
       setObject1();
     }
   }
   
   public void findEmployeRecherche() {
     this.selection = FactoryDAO.getInstance().getEmploye(this.codeRecherche, false);
     
     if (this.selection != null) {
       setObject1();
     }
   }
 
   
   public void findDemandeSortie() {
     setEtatDemandeSortie(Constante.EtatDemandeSortie.demandeSortie);
    allSaisieDemandeSortie = FactoryDAO.getInstance().getListeSaisieDemandeSortie(Constante.getEtatDemandeSortie(getEtatDemandeSortie()),0);
   }
			public void findDemandeTraite() {
    setEtatDemandeSortie(Constante.EtatDemandeSortie.traite);
    allSaisieDemandeSortie = FactoryDAO.getInstance().getListeSaisieDemandeSortie(Constante.getEtatDemandeSortie(getEtatDemandeSortie()),typeDec);
   }
		
			public void getListDemandeSortie() {

				allSaisieDemandeSortie = FactoryDAO.getInstance().getListeSaisieDemandeSortie(getEmploye());
			}

   public void chargerEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,false);
   }
   
   public void changeHeureDepart() {
				desableSave=false;
     if (getHeureDepart() != null) {
       if (getHeureDepart() != null && 
         
         !getHeureDepart().replace("_", "").trim().replace(":", "").equals("") && (
         Integer.parseInt(getHeureDepart().split(":")[0]) > 23 || 
         Integer.parseInt(getHeureDepart().split(":")[1]) > 59))
       {
         desableSave=true;
         HelperC.afficherMessage("Information", "Heure Invalide!");
       }
       try {
         calculTempsSortie();
         if (getNombreMin() < 0L) {
           HelperC.afficherAttention("ATTENTION", "L'heure de retour ne peut pas étre inférieure é l'heure de départ ");
					desableSave=true;
         }
       } catch (ParseException e) {
         
         e.printStackTrace();
       } 
     } 
   }
   
   public void changeHeureRetour() {
				desableSave=false;
     if (getHeureRetour() != null) {
       if (getHeureRetour() != null && 
         
         !getHeureRetour().replace("_", "").trim().replace(":", "").equals("") && (
         Integer.parseInt(getHeureRetour().split(":")[0]) > 23 || 
         Integer.parseInt(getHeureRetour().split(":")[1]) > 59)) {
         HelperC.afficherMessage("Information", "Heure Invalide!");
					desableSave=true;
       }
       
       try {
         calculTempsSortie();
         if (getNombreMin() < 0L) {
           HelperC.afficherAttention("ATTENTION", "L'heure de retour ne peut pas étre inférieure é l'heure de départ ");
					desableSave=true;
         }
       } catch (ParseException e) {
         
         e.printStackTrace();
       } 
     } 
   }
 
 
   
   private void calculTempsSortie() throws ParseException {
     if (!getHeureDepart().equalsIgnoreCase("") && !getHeureRetour().equalsIgnoreCase("")) {
       
       Date HD = (new SimpleDateFormat("hh:mm")).parse(getHeureDepart());
       
       Date HF = (new SimpleDateFormat("hh:mm")).parse(getHeureRetour());
 
       long result = (HF.getTime() - HD.getTime()) / 60000L;
       setNombreMin(result);
      
     } 
   }
 
 
 
   
   private void setObject() {
     if (this.selected != null) {
       setId(this.selected.getId());
       setEmploye(this.selected.getEmploye());
				setDateDemande(this.selected.getDateDemande());
       setDateDemandeS(this.selected.getDateDemandeS());
       setHeureDepart(this.selected.getHeureDepart());
       setHeureRetour(this.selected.getHeureRetour());
       setNombreMin(this.selected.getNombreMin());
       setMotifSortie(this.selected.getMotifSortie());
       setDateSortie(this.selected.getDateSortie());
       setDateSortieS(this.selected.getDateSortieS());
       setDateValidation(this.selected.getDateValidation());
       setDateValidationS(this.selected.getDateValidationS());
       setImputableAuxPresences(this.selected.isImputableAuxPresences());
       setDecision(this.selected.getDecision());
       setEtatSortie(this.selected.getEtatSortie());
       setMotifRefusSortie(this.selected.getMotifRefusSortie());
				setEtatDemandeSortie(selected.getEtatDemandeSortie());
				selection=selected.getEmploye();
				setObject1();
       
     } 
   }
 
 
 
   
   private void setObject1() {
     if (this.selection != null) {
       
       setEmploye(this.selection);
       if (getEmploye() != null) {
         this.code = getEmploye().getCode();
         this.codeRecherche = getEmploye().getCode();
       } else {
         this.code = "";
         this.codeRecherche = "";
       } 
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
       setId(0); 
     setEmploye(null);
     this.selected = null;
     this.selection = null;
     this.code = "";
     this.nom = "";
     setDateDemande(null);
     setDateDemandeS("");
     setHeureDepart("");
     setHeureRetour("");
     setNombreMin(0L);
     setMotifSortie("");
     setDateSortie(null);
     setDateSortieS("");
     setDateValidation(null);
     setDateValidationS("");
     setImputableAuxPresences(false);
     setDecideur(null);
     setEtatSortie(0);
     setEtatDemandeSortie(null);
     setLibelleEtatSortie("");
     setMotifRefusSortie("");
			  setDecision(0);
     this.allSaisieDemandeSortie.clear();
			  desableSave=false;
   }
   
   public void initialiser() {
     clear(true);
   }
   
   public void changeDateDemande() {
			  desableSave=false;
     if (getDateDemandeS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateDemande(null);
     } else {
       
       setDateDemande(HelperC.validerDate(getDateDemandeS()));
       if (getDateDemande() == null) {
         setDateDemandeS("");
         HelperC.afficherMessage("Information", "Date invalide");
					desableSave=true;
       } else {
         
         setDateDemandeS(HelperC.convertDate(getDateDemande()));
         if (getDateSortie() != null && getDateSortie().before(getDateDemande())) {
           HelperC.afficherMessage("Information", "La date sortie ne peut pas étre antérieure é la date demande");
					desableSave=true;
         }
         if (getDateValidation() != null && getDateValidation().before(getDateDemande())) {
           HelperC.afficherMessage("Information", "La date validation  ne peut pas étre antérieure é la date demande");
					desableSave=true;
         }
       } 
     } 
   }
   
   public void changeDateSortie() {
			desableSave=false;
     if (getDateSortieS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateSortie(null);
     } else {
       
       setDateSortie(HelperC.validerDate(getDateSortieS()));
       if (getDateSortie() == null) {
         setDateSortieS("");
         HelperC.afficherMessage("Information", "Date invalide");
				desableSave=true;
       } else {
         
         setDateSortieS(HelperC.convertDate(getDateSortie()));
         if (getDateDemande() != null && getDateSortie().before(getDateDemande())) {
           HelperC.afficherMessage("Information", "La date sortie ne peut pas étre antérieure é la date demande");
					desableSave=true;
         }
         if (getDateValidation() != null && getDateSortie().before(getDateValidation())) {
           HelperC.afficherMessage("Information", "La date sortie ne peut pas étre antérieure é la date validation demande");
					desableSave=true;
         }
       } 
     } 
   }
 
   
   public void changeDateValidation() {
			desableSave=false;
     if (getDateValidationS().replace("/", "").replace("_", "").trim().equals("")) {
       setDateValidation(null);
     } else {
       
       setDateValidation(HelperC.validerDate(getDateValidationS()));
       if (getDateValidation() == null) {
         setDateValidationS("");
         HelperC.afficherMessage("Information", "Date invalide");
					desableSave=true;
       } else {
         
         setDateValidationS(HelperC.convertDate(getDateValidation()));
         if (getDateDemande() != null && getDateValidation().before(getDateDemande())) {
           HelperC.afficherMessage("Information", "La date de validation ne peut pas étre antérieure é la date de demande");
					desableSave=true;
         }
         if (getDateSortie() != null && getDateSortie().before(getDateValidation())) {
           HelperC.afficherMessage("Information", "La date de sortie ne peut pas étre antérieure é la date de validation de demande");
					desableSave=true;
         }
       } 
     } 
   }
   
   private void searchForm() {
     UIComponent frm = null;
     FacesContext context = FacesContext.getCurrentInstance();
     
     frm = context.getViewRoot().findComponent("frmSaisieDemandeSortie");
     if (frm != null) {
       setDateDemande(new Date());
       setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
       setEtatDemandeSortie(Constante.EtatDemandeSortie.demandeSortie);
      
     } 
 
     
     frm = context.getViewRoot().findComponent("frmValidationDemandeSortie");
     if (frm != null) {
       setDateValidation(new Date());
       setDateValidationS(HelperC.changeDateFormate(getDateValidation()));
       setEtatDemandeSortie(Constante.EtatDemandeSortie.traite);
      
     } 

			  frm = context.getViewRoot().findComponent("frmDemandeSortieLine");
     if (frm != null) {
       setDateDemande(new Date());
       setDateDemandeS(HelperC.changeDateFormate(getDateDemande()));
       setEtatDemandeSortie(Constante.EtatDemandeSortie.demandeSortie);
       demandeLine=true;
				employeInLine();
     } 
   }
 
   
   
   public void changeImputable(ValueChangeEvent event) {
     setImputableAuxPresences(((Boolean)event.getNewValue()).booleanValue());
   }
 
   
   public void modifDemande() {
     setObject();
   }
   
   public void clear2() {
     this.listEmploye.clear();
     this.codeEmployeRecherche = "";
     this.nomEmployeRecherche = "";
     this.prenomEmployeRecherche = "";
   }
   public void clear3() {
     this.allSaisieDemandeSortie.clear();
     this.codeRecherche = "";
     PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
   }
   
   public void saveDemande() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     } else if (getId() > 0 && !this.droit.isModifier()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     } else if (getEmploye() == null || getDateDemande() == null || getDateSortie() == null || 
       getMotifSortie().equalsIgnoreCase("")) {
       HelperC.afficherMessage("Information", "Veillez préciser l'employé, la date demande, la date sortie et le motif");
     }
     else if (getDateSortie().before(getDateDemande())) {
       HelperC.afficherMessage("Information", "La date  sortie ne peut pas étre antérieure é la date demande");
     }
     else if (getNombreMin() < 0L) {
       HelperC.afficherAttention("ATTENTION", "L'heure de retour ne peut pas étre inférieure é l'heure de départ ");
     }
     else if (this.selected != null && !getEtatDemandeSortie().equals(Constante.EtatDemandeSortie.demandeSortie)) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier une demande déjé validée");
     }
     else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création de la saisie de demande de la sortie ");
       } else {
         hist.setOperation("Modification de la saisie de demande de la sortie ");
       }  hist.setTable(Tables.getTableName(Tables.TableName.saisieSortie));
       setHistorique(hist);
       setEtatSortie(Constante.getEtatDemandeSortie(Constante.EtatDemandeSortie.demandeSortie));
       if (FactoryDAO.getInstance().insertUpdateSaisieDemandeSortie(this)) {
         HelperC.afficherMessage("Félicitation", "Enregistrement avec Succès");
         
         clear(true);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec d'enregistrement");
       } 
     } 
   }

			
			
   public void saveDecision() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     } else if (getId() > 0 && !this.droit.isModifier()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     } else if (getId() == 0) {
       HelperC.afficherAttention("ATTENTION", "Veillez d'abord choisir la demande de sortie é valider ");
     } else if (getEmploye() == null || getDateDemande() == null || getDateSortie() == null || 
       getMotifSortie().equalsIgnoreCase("")) {
       HelperC.afficherMessage("Information", "Veillez remplir tous les champs obligatoires");
     }
     else if (getDateSortie().before(getDateDemande())) {
       HelperC.afficherMessage("Information", "La date sortie ne peut pas étre antérieure é la date demande");
     } else if (getDateSortie().before(getDateValidation())) {
       HelperC.afficherMessage("Information", "La date sortie ne peut pas étre antérieure é la date de validation demande");
     } else if (getDateValidation().before(getDateDemande())) {
       HelperC.afficherMessage("Information", "La date validation ne peut pas étre antérieure é la date demande");
     }
     else if (getNombreMin() < 0L) {
       HelperC.afficherAttention("ATTENTION", "L'heure de retour ne peut pas étre inférieure é l'heure de départ ");
     }
     
      setEtatSortie(Constante.getEtatDemandeSortie(Constante.EtatDemandeSortie.traite));
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création de la saisie de demande sortie ");
       } else {
         hist.setOperation("Modification de la saisie de demande sortie ");
       }  hist.setTable(Tables.getTableName(Tables.TableName.saisieSortie));
       setHistorique(hist);
       
       if (FactoryDAO.getInstance().insertUpdateSaisieDemandeSortie(this)) {
         HelperC.afficherMessage("Information", "Enregistrement avec Succès");
			
			if (parm != null) {
				if (getDecision() == 1)
					HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(),
							parm.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de congé a été acceptée ",
							"Demande congé");
				if (getDecision() == 2)
					HelperC.sendEmail(parm.getSmtpServer(), parm.getMailOrigine(), parm.getMailOrigine(),
							parm.getPwdOrigine(), getEmploye().getEmail(), "Votre demande de congé a été refusée",
							"Demande congé");
			}
         clear(true);
       } else {
         
         HelperC.afficherMessage("Information", "Echec d'enregistrement");
       } 
     } 
   
   
   public void delete() {
     if (this.selected == null) {
       HelperC.afficherDeleteMessage();
     } else if (this.selected != null && !this.droit.isSupprimer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     }  if (this.selected != null && selected.getDecision()==0) {
       	FactoryDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.saisieSortie));
       
       clear(true);
       HelperC.afficherMessage("Félicitation", "Suppression avec Succès");
     } 
				else
					 HelperC.afficherAttention("ATTENTION", "On ne peut pas supprimer la demande aprés la prise de décision ");
   }
   
   public void onRowselected(SelectEvent event) {
     this.selected = (SaisieDemandeSortieC)event.getObject();
     setObject();
     PrimeFaces.current().executeScript("PF('dlgDemand').hide();");
     PrimeFaces.current().executeScript("PF('dlgValid').hide();");
   }
   
   public void onRowselected1(SelectEvent event) {
     this.selection = (EmployeC)event.getObject();
     setObject1();
     PrimeFaces.current().executeScript("PF('dlg').hide();");
     PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
     PrimeFaces.current().executeScript("PF('rechercheDialog1').hide();");
   }
   
   public void onRowselected2(SelectEvent event) {
     this.selection = (EmployeC)event.getObject();
     setObject1();
     PrimeFaces.current().executeScript("PF('dlge').hide();");
     
     PrimeFaces.current().executeScript("PF('rechercheDialog1').hide();");
   }

			public void annuler() {
     if (this.selected == null) {
       HelperC.afficherAttention("ATTENTION", "Aucun élément é annuler");
     } else  {
       if(FactoryDAO.getInstance().annulerDecisionSortie(selected))
       {
       clear(true);
       HelperC.afficherMessage("Info", "Annulation réussie !");
				}
     } 
   }
   

			
   public void printSortie() {
     if (this.selected == null) {
       HelperC.afficherAttention("Attention", "Veillez d'abord selectionner la sortie é imprimer");
     } else if (!getEtatDemandeSortie().equals(Constante.EtatDemandeSortie.traite)) {
       HelperC.afficherAttention("Attention", "cette sortie n'est pas accordée");
     } else {
       try {
         Image image = null;
         Document doc = new Document(PageSize.A4);
         ByteArrayOutputStream docMem = new ByteArrayOutputStream();
         PdfWriter writer = PdfWriter.getInstance(doc, docMem);
         
         doc.addAuthor("Asyst Resources LTD");
         doc.addProducer();
         doc.addCreationDate();
         doc.addTitle("Billet de sortie");
 
         
         doc.open();
         
         ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
         image = Image.getInstance(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + "logoUniversite.png");
         image.scaleAbsoluteHeight(90.0F);
         image.scaleAbsoluteWidth(180.0F);
         
         doc.add((Element)image);
 
         
         writer.setPageEvent((PdfPageEvent)new ItextFooterHelper(new Phrase("Produit Asyst Resources Ltd", 
                 new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0))));
         
         doc.add((Element)pageHeader());
         doc.add((Element)getTableBillet());
         doc.add((Element)getTableSignature());
         doc.close();
         
         FacesContext context = FacesContext.getCurrentInstance();
         HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
         
         res.setHeader("Cache-Control", "Max-age=100");
         res.setContentType("application/pdf");
         res.setHeader("content-disposition", "inline;filename=BilletSortie.pdf");
         
         ServletOutputStream out = res.getOutputStream();
         
         res.setContentLength(docMem.size());
         docMem.writeTo((OutputStream)out);
         out.flush();
         out.close();
         context.responseComplete();
       }
       catch (Exception e) {
         
         System.out.println(e.getMessage());
       } 
     } 
   }
   
   private PdfPTable pageHeader() throws DocumentException, IOException {
     PdfPTable table = null;
     table = new PdfPTable(2);
     PdfPCell cell = new PdfPCell();
     table.setWidthPercentage(100.0F);
     cell.setBorder(0);
     int[] largeurCollones = { 50, 50 };
     table.setWidths(largeurCollones);
 
     
     cell = HelperItext.getPdfCell(HelperC.convertDate(Calendar.getInstance().getTime()), 
         FontFactory.getFont("Times-Roman", 8.0F, 0, BaseColor.BLACK), 
         1, 3, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
     table.addCell(cell);
     
     cell = HelperItext.getPdfCell("", 
         FontFactory.getFont("Times-Roman", 12.0F, 0, BaseColor.BLACK), 
         1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
     table.addCell(cell);
     
     cell = HelperItext.getPdfCell("BILLET DE SORTIE ", 
         FontFactory.getFont("Times-Roman", 12.0F, 1, BaseColor.BLACK), 
         1, 1, 1, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
     cell.setBorder(2);
     table.addCell(cell);
     
     return table;
   }
   
   private PdfPTable getTableBillet() throws DocumentException {
     PdfPTable tabInfo = new PdfPTable(2);
     int[] widthsInfo = { 50, 50 };
     tabInfo.setWidthPercentage(100.0F);
     tabInfo.setWidths(widthsInfo);
     
     PdfPCell cell = new PdfPCell();
 		
    
     Phrase phrase = null;
     
     Paragraph p = null;
     cell = HelperItext.getPdfCell("  ", 
         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
     tabInfo.addCell(cell);
     
     phrase = new Phrase();
     phrase.add((Element)new Chunk("Nom et Prénom : ", FontFactory.getFont("Courier", 9.0F, 0)));
     phrase.add((Element)new Chunk(getEmploye().getNomPrenom(), FontFactory.getFont("Courier", 9.0F, 1)));
     
     p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         0, 3, 0.0F, 3.0F);
     
     tabInfo.addCell(cell);
     
     cell = HelperItext.getPdfCell("  ", 
         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
     tabInfo.addCell(cell);
     
     phrase = new Phrase();
     phrase.add((Element)new Chunk("Date Sortie : ", FontFactory.getFont("Courier", 9.0F, 0)));
     phrase.add((Element)new Chunk(getDateSortieS(), FontFactory.getFont("Courier", 9.0F, 1)));
     
     p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         0, 3, 0.0F, 3.0F);
     
     tabInfo.addCell(cell);
     
     cell = HelperItext.getPdfCell("  ", 
         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
     tabInfo.addCell(cell);
     
     phrase = new Phrase();
     phrase.add((Element)new Chunk("Heure de départ : ", FontFactory.getFont("Courier", 9.0F, 0)));
     phrase.add((Element)new Chunk(getHeureDepart(), FontFactory.getFont("Courier", 9.0F, 1)));
     
     p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         0, 3, 0.0F, 3.0F);
     tabInfo.addCell(cell);
     
     cell = HelperItext.getPdfCell("  ", 
         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
     tabInfo.addCell(cell);
     
     phrase = new Phrase();
     phrase.add((Element)new Chunk("Heure de retour : ", FontFactory.getFont("Courier", 9.0F, 0)));
     phrase.add((Element)new Chunk(getHeureRetour(), FontFactory.getFont("Courier", 9.0F, 1)));
     
     p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         5, 3, 0.0F, 3.0F);
     tabInfo.addCell(cell);
     
     cell = HelperItext.getPdfCell("  ", 
         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
     tabInfo.addCell(cell);
     
     phrase = new Phrase();
     phrase.add((Element)new Chunk("Motif : ", FontFactory.getFont("Courier", 9.0F, 0)));
     phrase.add((Element)new Chunk(getMotifSortie(), FontFactory.getFont("Courier", 9.0F, 1)));
     
     p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         0, 3, 0.0F, 3.0F);
     tabInfo.addCell(cell);

			 phrase = new Phrase();
			  p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         5, 3, 0.0F, 3.0F);
     tabInfo.addCell(cell);
     
     cell = HelperItext.getPdfCell("  ", 
         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
     tabInfo.addCell(cell);

			  phrase = new Phrase();
     phrase.add((Element)new Chunk("Décision : ", FontFactory.getFont("Courier", 9.0F, 0)));
     phrase.add((Element)new Chunk(getLibelleDecision(), FontFactory.getFont("Courier", 9.0F, 1)));
     
     p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         0, 3, 0.0F, 3.0F);
     tabInfo.addCell(cell);

     return tabInfo;
   }
 
   
   private PdfPTable getTableSignature() throws DocumentException {
     PdfPTable tabInfo = new PdfPTable(4);
     int[] widthsInfo = { 20, 10, 20, 40 };
     tabInfo.setWidthPercentage(100.0F);
     tabInfo.setWidths(widthsInfo);
     tabInfo.setKeepTogether(true);
     PdfPCell cell = new PdfPCell();
 
     
     Phrase phrase = null;
     
     Paragraph p = null;
     cell = HelperItext.getPdfCell("  ", 
         FontFactory.getFont("Courier", 12.0F, 0, BaseColor.BLACK), 
         2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
     tabInfo.addCell(cell);
     
     phrase = new Phrase();
     phrase.add((Element)new Chunk("Signature du demandeur ", FontFactory.getFont("Courier", 9.0F, 0)));
     phrase.add((Element)new Chunk(" ", FontFactory.getFont("Courier", 9.0F, 0)));
     
     p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         0, 3, 0.0F, 1.0F);
     
     tabInfo.addCell(cell);
     
     phrase = new Phrase();
     phrase.add((Element)new Chunk("Visa pour autorisation ", FontFactory.getFont("Courier", 9.0F, 0)));
     phrase.add((Element)new Chunk(" ", FontFactory.getFont("Courier", 9.0F, 0)));
     p = new Paragraph();
     p.add((Element)phrase);
     cell = HelperItext.getCellule((Element)p, 
         1, 0, 
         0, 3, 0.0F, 1.0F);
     
     tabInfo.addCell(cell);
 
     
     return tabInfo;
   }
 }


