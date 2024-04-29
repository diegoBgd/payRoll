 package vuesPaie;
 
 import classesPaie.AvanceDetailC;
 import classesPaie.AvanceQuinzaineC;
 import classesPaie.BanqueC;
 import classesPaie.Base;
import classesPaie.BulletinPaieC;
 import classesPaie.Constante;
 import classesPaie.DetailBanqueEmployeC;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.TypeCreditC;
 import java.io.IOException;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.servlet.http.HttpSession;
 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class AvanceQuinzaineB
   extends AvanceQuinzaineC
   implements Serializable
 {
   private static final long serialVersionUID = -7301676475596039486L;
   private int index;
   private int idEpmloye;
   private int mois;
   private int typeReglrment;
   private String dateS;
   private String codeEmploye;
   private String nomEmploye;
   private String codeEmployeRecherche;
   private String nomEmployeRecherche;
   private String prenomEmployeRecherche;
   private String codeBanque;
   private String nomBanque;
   private String codeBankRech;
   private String nomBankRech;
   private String printMois;
   double total = 0.0D; 
			private AvanceQuinzaineC avanceQuinzaine; 
			private List<AvanceQuinzaineC> listAvance; 
			private List<AvanceDetailC> listDet; 
			private EmployeC employe; 
			private List<EmployeC> employes; 
			private TypeCreditC typeCredit; 
			private List<BanqueC> listBank; 
			private BanqueC banque; 
			private OperateurC operateur; 
			private ExerciceC exercice; 
			private HttpSession session; 
			private Date dateDeb; 
			private Date dateFin; 
			private DroitC droit; 
			private AvanceDetailC selectedDetail;
			private boolean disableMsg;
			private double salaireNet;
			Base userFonction; 
			int month;
			BulletinPaieC bulletin;
   
   public AvanceQuinzaineB() {
     this.session = HelperC.getSession();
   }
 
   
   public DroitC getDroit() {
     return this.droit;
   }
 
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }
 
   
   public int getIdEpmloye() {
     return this.idEpmloye;
   }
 
   
   public void setIdEpmloye(int idEpmloye) {
     this.idEpmloye = idEpmloye;
   }
 
   
   public int getMois() {
     return this.mois;
   }
 
   
   public void setMois(int mois) {
     this.mois = mois;
   }
 
   
   public Date getDateDeb() {
     return this.dateDeb;
   }
 
   
   public void setDateDeb(Date dateDeb) {
     this.dateDeb = dateDeb;
   }
 
   
   public Date getDateFin() {
     return this.dateFin;
   }
 
   
   public void setDateFin(Date dateFin) {
     this.dateFin = dateFin;
   }
 
 
   
   public String getCodeBankRech() {
     return this.codeBankRech;
   }
 
   
   public void setCodeBankRech(String codeBankRech) {
     this.codeBankRech = codeBankRech;
   }
 
   
   public String getNomBankRech() {
     return this.nomBankRech;
   }
 
   
   public void setNomBankRech(String nomBankRech) {
     this.nomBankRech = nomBankRech;
   }
 
   
   public List<BanqueC> getListBank() {
     return this.listBank;
   }
   
   public void setListBank(List<BanqueC> listBank) {
     this.listBank = listBank;
   }
 
   
   public String getDateS() {
     return this.dateS;
   }
 
   
   public void setDateS(String dateS) {
     this.dateS = dateS;
   }
 
   
   public String getCodeEmploye() {
     return this.codeEmploye;
   }
 
   
   public void setCodeEmploye(String codeEmploye) {
     this.codeEmploye = codeEmploye;
   }
 
   
   public String getNomEmploye() {
     return this.nomEmploye;
   }
 
   
   public void setNomEmploye(String nomEmploye) {
     this.nomEmploye = nomEmploye;
   }
 
   
   public AvanceQuinzaineC getAvanceQuinzaine() {
     return this.avanceQuinzaine;
   }
 
   
   public void setAvanceQuinzaine(AvanceQuinzaineC avanceQuinzaine) {
     this.avanceQuinzaine = avanceQuinzaine;
   }
 
   
   public EmployeC getEmploye() {
     return this.employe;
   }
 
   
   public void setEmploye(EmployeC employe) {
     this.employe = employe;
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
 
   
   public String getCodeBanque() {
     return this.codeBanque;
   }
 
   
   public void setCodeBanque(String codeBanque) {
     this.codeBanque = codeBanque;
   }
 
   
   public String getNomBanque() {
     return this.nomBanque;
   }
 
   
   public void setNomBanque(String nomBanque) {
     this.nomBanque = nomBanque;
   }
 
   
   public TypeCreditC getTypeCredit() {
     return this.typeCredit;
   }
 
   
   public void setTypeCredit(TypeCreditC typeCredit) {
     this.typeCredit = typeCredit;
   }
 
   
   public BanqueC getBanque() {
     return this.banque;
   }
 
   
   public void setBanque(BanqueC banque) {
     this.banque = banque;
   }
 
   
   public int getIndex() {
     return this.index;
   }
 
   
   public void setIndex(int index) {
     this.index = index;
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
   
   public List<AvanceQuinzaineC> getListAvance() {
     return this.listAvance;
   }
   
   public void setListAvance(List<AvanceQuinzaineC> listAvance) {
     this.listAvance = listAvance;
   }
   
   public List<EmployeC> getEmployes() {
     return this.employes;
   }
   
   public void setEmployes(List<EmployeC> employes) {
     this.employes = employes;
   }
   
   public int getTypeReglrment() {
     return this.typeReglrment;
   }
   
   public void setTypeReglrment(int typeReglrment) {
     this.typeReglrment = typeReglrment;
   }
   
   public String getPrintMois() {
     return this.printMois;
   }
   
   public void setPrintMois(String printMois) {
     this.printMois = printMois;
   }
   
   public List<AvanceDetailC> getListDet() {
     return this.listDet;
   }
   
   public void setListDet(List<AvanceDetailC> listDet) {
     this.listDet = listDet;
   }
   
   public AvanceDetailC getSelectedDetail() {
     return this.selectedDetail;
   }
   
   public void setSelectedDetail(AvanceDetailC selectedDetail) {
     this.selectedDetail = selectedDetail;
   }

			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   
			public double getSalaireNet() {
				return salaireNet;
			}
			public void setSalaireNet(double salaireNet) {
				this.salaireNet = salaireNet;
			}
			
   @PostConstruct
   private void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     if (this.operateur == null || this.exercice == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       disableMsg=true;
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.creditAvance);
       }
       setDate(Calendar.getInstance().getTime());
       this.dateS = HelperC.convertDate(getDate());
       this.codeBankRech = "";
       this.nomBankRech = "";
       this.listAvance = new ArrayList<AvanceQuinzaineC>();
       this.month = HelperC.getMonthFromDate(getDate());
       chargerDetail();
     } 
   }

 
   
   private void chargerDetail() {
     this.listDet = new ArrayList<AvanceDetailC>();
 
     
     for (int i = this.month; i <= 12; i++) {
       this.selectedDetail = new AvanceDetailC();
       this.selectedDetail.setMois(i);
       this.selectedDetail.setMontant(0.0D);
       setDetailValue(this.selectedDetail);
       this.listDet.add(this.selectedDetail);
     } 
   }
 
   
   private void setDetailValue(AvanceDetailC det) {
     if (getListDetail().size() > 0)
       for (AvanceDetailC avDet : getListDetail()) {
         
         if (avDet.getMois() == det.getMois()) {
           
           det.setId(avDet.getId());
           det.setSeleceted(true);
           det.setDisable(false);
           det.setMontant(avDet.getMontant());
           break;
         } 
       }  
   }
   
   public void changeDate() {
     if (this.dateS.replace("/", "").replace("_", "").trim().equals("")) {
       
       setDate(null);
     } else {
       
       setDate(HelperC.validerDate(this.dateS));
       if (getDate() == null) {
         
         this.dateS = "";
       } else {
         
         this.dateS = HelperC.convertDate(getDate());
         this.month = HelperC.getMonthFromDate(getDate());
         this.printMois = HelperC.moisEnLettres(this.month);
         setMoisConcerne(this.month);
         if (this.employe != null) {
           
           this.avanceQuinzaine = FactoryDAO.getInstance().getAvanceQuinzaine(getDate(), this.employe.getId(), this.exercice.getId());
           if (this.avanceQuinzaine != null) {
             takeSelectedAvance();
           } else {
             
           } 
         } 
       } 
     } 
   } public void changeModereglement(ValueChangeEvent event) {
     this.typeReglrment = ((Integer)event.getNewValue()).intValue();
     setModePaiement(this.typeReglrment);
     searchBank();
   }
   
   public void chargerAvance() {
     this.listAvance.clear();
     this.listAvance = FactoryDAO.getInstance().getAllAvanceQuinzaine(this.dateDeb, this.dateFin, 0, this.mois);
     if (this.listAvance != null && this.listAvance.size() > 0) {
       for (AvanceQuinzaineC av : this.listAvance) {
         
         av.setDateString(HelperC.changeDateFormate(av.getDate()));
         av.setNomEmploye(av.getEmploye().getNomPrenom());
       } 
     }
   }
 
   
   public void onRowAvanceSelect(SelectEvent event) {
     this.avanceQuinzaine = (AvanceQuinzaineC)event.getObject();
     if (this.avanceQuinzaine != null) {
       
       takeSelectedAvance();
       PrimeFaces.current().executeScript("PF('dlgAvnc').hide();");
     } 
   }
 
   
   private void takeSelectedAvance() {
     setId(this.avanceQuinzaine.getId());
     setDate(this.avanceQuinzaine.getDate());
     setModePaiement(this.avanceQuinzaine.getModePaiement());
     setMontant(this.avanceQuinzaine.getMontant());
     setNumeroCompte(this.avanceQuinzaine.getNumeroCompte());
     setModePaiement(this.avanceQuinzaine.getModePaiement());
      disableMsg=false;
     this.typeReglrment = this.avanceQuinzaine.getModePaiement();
     this.month = this.avanceQuinzaine.getMoisConcerne();
     this.printMois = HelperC.moisEnLettres(this.month);
     setMoisConcerne(this.month);
     this.dateS = HelperC.changeDateFormate(getDate());
     setListDetail(this.avanceQuinzaine.getListDetail());
     if (this.avanceQuinzaine.getEmploye() != null) {
       
       this.employe = this.avanceQuinzaine.getEmploye();
       setObjectEmploye();
       searchBank();
     } else {
       
       clearEmploye();
     } 
     if (this.avanceQuinzaine.getBanque() != null) {
       
       this.banque = this.avanceQuinzaine.getBanque();
       if (this.banque != null) {
         
         this.nomBanque = this.banque.getDesignation();
         setBanque(this.banque);
       } else {
         
         this.nomBanque = "";
       } 
     } else {
       
       this.codeBanque = "";
       this.nomBanque = "";
     } 
     chargerDetail();
   }
 
   
   private void setObjectEmploye() {
     if (this.employe != null) {
       
       this.codeEmploye = this.employe.getCode();
       this.nomEmploye = this.employe.getNomPrenom();
       this.idEpmloye = this.employe.getId();
       setEmploye(this.employe);
     } 
   }
 
 
   
   public void onRowSelectEmploye(SelectEvent event) {
     this.employe = (EmployeC)event.getObject();
     if (this.employe != null) {
       
       setObjectEmploye();
       PrimeFaces.current().executeScript("PF('dlgEmploye').hide();");
     }
     else {
       
       clearEmploye();
     } 
   }
 
   
   public void chargerEmploye() {
     this.employes = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche,false);
     HelperC.afficherMessage("Information", String.valueOf(this.employes.size()) + " élément(s) trouvé(s) ");
   }
 
   
   public void searchEmployee() {
     if (!this.codeEmploye.equals("")) {
       
       this.employe = FactoryDAO.getInstance().getEmploye(this.codeEmploye, false);
       if (this.employe != null) {
         
         setObjectEmploye();
         if (getDate() != null) {
           
           this.avanceQuinzaine = FactoryDAO.getInstance().getAvanceQuinzaine(getDate(), this.employe.getId(), this.exercice.getId());
           if (this.avanceQuinzaine != null) {
             takeSelectedAvance();
           } else {
            
           } 
         } 
       } else {
         
         clearEmploye();
       } 
     } else {
       
       clearEmploye();
     } 
   }
 
   
   public void chargerBanque() {
     this.listBank = FichierBaseDAO.getInstance().getBanque(this.codeBankRech, this.nomBankRech);
   }
 
   
   private void searchBank() {
     DetailBanqueEmployeC detail = null;
     
     if (this.employe != null) {
       if (this.typeReglrment == 4) {
         detail = FactoryDAO.getInstance().getBanquePrincipal(
             this.employe.getId());
         
         if (detail != null) {
           this.banque = detail.getBanque();
           this.nomBanque = this.banque.getDesignation();
           setNumeroCompte(detail.getNumeroCompte());
           setBanque(this.banque);
         } else {
           this.codeBanque = "";
           this.nomBanque = "";
           setNumeroCompte("");
         } 
       } else {
         this.codeBanque = "";
         this.nomBanque = "";
       } 
     } else {
       
       this.banque = null;
       this.nomBanque = "";
       setNumeroCompte("");
       setBanque(this.banque);
     } 
   }
 

	public void chargerPaie() {
		if (employe != null && mois > 0) {
			
			bulletin = FactoryDAO.getInstance().getPaieMois(idEpmloye, mois, exercice.getId());
			if (bulletin != null) {
				salaireNet = bulletin.getTotalNetPay();
				setMontant(Math.round(salaireNet / 2));
				setIdPaie(bulletin.getId());
				
				if (listDet.size() > 0)
					for (AvanceDetailC avDet : listDet) {

						if (avDet.getMois() == mois) {

							avDet.setSeleceted(true);
							avDet.setDisable(true);
							avDet.setMontant(getMontant());
							break;
						}
					}
			}
		}
	}
			
   
   public void enregistrer() {
     if (getDate() == null) {
       
       HelperC.afficherMessage("Information", "Précisez la date de l'avance ");
     } else {
       
       setOperateur(this.operateur);
       setExercice(this.exercice);
       setMoisConcerne(this.month);
       addDetail();
       removeDetail();
       
       if (getId() == 0 && !this.droit.isCreer()) {
         
         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer ");
         return;
       } 
       if (getId() > 0 && !this.droit.isModifier()) {
         
         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier ");
         return;
       } 
       if (this.total > getMontant() || this.total < getMontant()) {
         
         HelperC.afficherMessage("ATTENTION", "Le montant total é rembourser doit étre égal au montant de l'avance ");
         return;
       } 
       if (FactoryDAO.getInstance().insertUpdateAvanceQuinzaine(this)) {
         
         HelperC.afficherMessage("Information", "succès de l'opération ");
         initialiser();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
   private void addDetail() {
     this.total = 0.0D;
     getListDetail().clear();
     if (this.listDet.size() > 0)
     {
       for (AvanceDetailC det : this.listDet) {
         if (det.isSeleceted() && det.getMontant() > 0.0D) {
           
           getListDetail().add(det);
           this.total += det.getMontant();
         } 
       } 
     }
   }
   
   private void removeDetail() {
     for (AvanceDetailC det : this.listDet) {
       if (det.getId() > 0 && !det.isSeleceted())
         getListDeleted().add(det); 
     } 
   }
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     } else {
       
       if (!this.droit.isSupprimer()) {
         
         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
         return;
       } 
       if (FactoryDAO.getInstance().deleteAvanceQuinzaine(this.avanceQuinzaine)) {
         
         HelperC.afficherMessage("Information", "succès de l'opération ");
         initialiser();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
   
   public void clearEmploye() {
     this.employe = null;
     this.banque = null;
     this.codeEmploye = "";
     this.nomEmploye = "";
     this.codeBanque = "";
     this.nomBanque = "";
     this.idEpmloye = 0;
     setMontant(0.0D);
     setNumeroCompte("");
     setMontantString("");
   }
 
   
   public void initialiser() {
     this.codeEmploye = "";
     this.nomEmploye = "";
     this.codeBanque = "";
     this.nomBanque = "";
     this.printMois = "";
				bulletin=null;
				salaireNet=0;
				setIdPaie(0);
      disableMsg=true;
     setDate(null);
     setId(0);
     setBanque((BanqueC)null);
     setNumeroCompte("");
     setMontant(0.0D);
     setMontantString("");
     setModePaiement(1);
     getListDetail().clear();
     getListDeleted().clear();
     chargerDetail();
   }
 }

