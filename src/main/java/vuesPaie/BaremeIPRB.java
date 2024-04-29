 package vuesPaie;
 
 import classesPaie.BaremeIPRC;
 import classesPaie.Base;
import classesPaie.BulletinPrimeC;
 import classesPaie.Constante;
 import classesPaie.CotisationC;
 import classesPaie.DeductionC;
 import classesPaie.DetailBaremeC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.PrimeIndemniteC;
 import classesPaie.Tables;

 import java.io.IOException;
 import java.io.Serializable;
 import java.util.Calendar;
 import java.util.List;

 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;

 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;

 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class BaremeIPRB
   extends BaremeIPRC
   implements Serializable
 {
   private static final long serialVersionUID = 5659574157386814683L;
   private int idPrime;
   private int idCotisation;
   private int idRetenu;
   private double borneDebut;
   private double borneFin;
   private double pourcentage;
   private double sommeRetranche;
   private String dateS;
   private String separator;
   private String borneDebutS;
   private String borneFinS;
   private String sommeRetrancheS;
   private String elementBase;
   private BaremeIPRC bareme;
   private int index = 1; 
			private DetailBaremeC detailBareme; 
			private OperateurC operateur; 
			private ExerciceC exercice; 
			private DroitC droit;
   private HttpSession session = HelperC.getSession(); 
			private List<SelectItem> listPrimesIndemnites; 
			private List<SelectItem> listCotisations; 
			private List<SelectItem> listRetenu;
   private List<BaremeIPRC> listBareme;
   private PrimeIndemniteC prime; 
			private CotisationC cotisation; 
			private DeductionC deduction; 
			private boolean disableMsg;
			Base userFonction;
			
   public CotisationC getCotisation() {
     return this.cotisation;
   }
  
   public void setCotisation(CotisationC cotisation) {
     this.cotisation = cotisation;
   }
 
   
   public DeductionC getDeduction() {
     return this.deduction;
   }
 
   
   public void setDeduction(DeductionC deduction) {
     this.deduction = deduction;
   }
 
   
   public int getIdPrime() {
     return this.idPrime;
   }
 
   
   public void setIdPrime(int idPrime) {
     this.idPrime = idPrime;
   }
 
   
   public int getIdCotisation() {
     return this.idCotisation;
   }
 
   
   public void setIdCotisation(int idCotisation) {
     this.idCotisation = idCotisation;
   }
 
   
   public int getIdRetenu() {
     return this.idRetenu;
   }
 
   
   public void setIdRetenu(int idRetenu) {
     this.idRetenu = idRetenu;
   }
 
   
   public String getElementBase() {
     return this.elementBase;
   }
 
   
   public void setElementBase(String elementBase) {
     this.elementBase = elementBase;
   }
   
   public List<SelectItem> getListPrimesIndemnites() {
     return this.listPrimesIndemnites;
   }
   
   public void setListPrimesIndemnites(List<SelectItem> listPrimesIndemnites) {
     this.listPrimesIndemnites = listPrimesIndemnites;
   }
   
   public List<SelectItem> getListCotisations() {
     return this.listCotisations;
   }
   
   public void setListCotisations(List<SelectItem> listCotisations) {
     this.listCotisations = listCotisations;
   }
   
   public List<SelectItem> getListRetenu() {
     return this.listRetenu;
   }
   
   public void setListRetenu(List<SelectItem> listRetenu) {
     this.listRetenu = listRetenu;
   }
 
   
   public String getDateS() {
     return this.dateS;
   }
 
   
   public void setDateS(String dateS) {
     this.dateS = dateS;
   }
 
   
   public String getSeparator() {
     return this.separator;
   }
 
   
   public void setSeparator(String separator) {
     this.separator = separator;
   }
 
   
   public BaremeIPRC getBareme() {
     return this.bareme;
   }
 
   
   public void setBareme(BaremeIPRC bareme) {
     this.bareme = bareme;
   }
 
   
   public DetailBaremeC getDetailBareme() {
     return this.detailBareme;
   }
 
   
   public void setDetailBareme(DetailBaremeC detailBareme) {
     this.detailBareme = detailBareme;
   }
 
   
   public double getBorneDebut() {
     return this.borneDebut;
   }
 
   
   public void setBorneDebut(double borneDebut) {
     this.borneDebut = borneDebut;
   }
 
   
   public double getBorneFin() {
     return this.borneFin;
   }
 
   
   public void setBorneFin(double borneFin) {
     this.borneFin = borneFin;
   }
 
   
   public double getPourcentage() {
     return this.pourcentage;
   }
 
   
   public void setPourcentage(double pourcentage) {
     this.pourcentage = pourcentage;
   }
 
   
   public double getSommeRetranche() {
     return this.sommeRetranche;
   }
 
   
   public void setSommeRetranche(double sommeRetranche) {
     this.sommeRetranche = sommeRetranche;
   }
 
   
   public int getIndex() {
     return this.index;
   }
 
   
   public void setIndex(int index) {
     this.index = index;
   }
 
   
   public String getBorneDebutS() {
     return this.borneDebutS;
   }
 
   
   public void setBorneDebutS(String borneDebutS) {
     this.borneDebutS = borneDebutS;
   }
 
   
   public String getBorneFinS() {
     return this.borneFinS;
   }
 
   
   public void setBorneFinS(String borneFinS) {
     this.borneFinS = borneFinS;
   }
 
   
   public String getSommeRetrancheS() {
     return this.sommeRetrancheS;
   }
 
   
   public void setSommeRetrancheS(String sommeRetrancheS) {
     this.sommeRetrancheS = sommeRetrancheS;
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
 
   
   public HttpSession getSession() {
     return this.session;
   }
 
   
   public void setSession(HttpSession session) {
     this.session = session;
   }
 
   
   public PrimeIndemniteC getPrime() {
     return this.prime;
   }
 
   
   public void setPrime(PrimeIndemniteC prime) {
     this.prime = prime;
   }
   
   public List<BaremeIPRC> getListBareme() {
     return this.listBareme;
   }
   
   public void setListBareme(List<BaremeIPRC> listBareme) {
     this.listBareme = listBareme;
   }

			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   @PostConstruct
   private void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     this.separator = " ";
     if (this.exercice == null || this.operateur == null) {
       
       try
       {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e)
       {
         e.printStackTrace();
       }
     
     } else {
       disableMsg=true;
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
       }
       setDate(Calendar.getInstance().getTime());
       this.dateS = HelperC.convertDate(getDate());
       setTypeEmploye(1);
       this.elementBase = "";
				chargerBaremeActif();
     } 
   }
 
			public void changePrime(ValueChangeEvent event) {
     setTypeEmploye(((Integer)event.getNewValue()).intValue());
			   chargerBaremeActif();
     
   }
   private void setDetailBareme() {
     setListDetailBareme(FichierBaseDAO.getInstance().getListeDetailBareme(this.bareme));
     if (getListDetailBareme().size() > 0) {
       
       for (DetailBaremeC deta : getListDetailBareme())
       {
         
         deta.setIndex(getListDetailBareme().indexOf(deta) + 1);
         this.borneDebut = deta.getBorneFin() + 1.0D;
         this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
         this.index = getListDetailBareme().indexOf(deta) + 2;
       }
     
     } else {
       
       this.index = 1;
       this.borneDebut = 0.0D;
       this.borneDebutS = "0";
       this.borneFin = 0.0D;
       this.borneFinS = "";
       this.pourcentage = 0.0D;
       this.sommeRetranche = 0.0D;
       this.sommeRetrancheS = "";
     } 
   }
 
 
   
   public void changeNumero() {
     if (this.bareme != null) {
       bareme=FichierBaseDAO.getInstance().getBareme(getNumero());
       setObject();
       setDetailBareme();
     } else {
       
       clear(false);
       clearDetail();
     } 
   }

		  private void chargerBaremeActif(){
			  disableMsg=true;
			  bareme=FichierBaseDAO.getInstance().getBaremeIRE(getTypeEmploye());
			  if(bareme!=null){
				 
				  setObject();
				  setDetailBareme();
			  }
		  }
  
   public void changeDate() {
     if (this.dateS.replace("/", "").replace("_", "").trim().equals("")) {
       
       setDate(null);
     } else {
       
       setDate(HelperC.validerDate(this.dateS));
       if (getDate() == null) {
         
         this.dateS = "";
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         this.dateS = HelperC.convertDate(getDate());
       } 
     } 
   }
 
 
   
   public void changeBorneDebut() {
     try {
       this.borneDebut = Double.valueOf(this.borneDebutS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     }
     catch (Exception e) {
       
       this.borneDebut = 0.0D;
     } 
     this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
     this.borneDebut = Double.valueOf(this.borneDebutS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
     this.borneDebut = Double.valueOf(this.borneDebutS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
     this.borneDebut = Double.valueOf(this.borneDebutS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
   }
 
 
   
   public void changeBorneFin() {
     try {
       this.borneFin = Double.valueOf(this.borneFinS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     }
     catch (Exception e) {
       
       this.borneFin = 0.0D;
     } 
     this.borneFinS = HelperC.TraitementMontant.getMontantFormate(this.borneFin, 0);
     this.borneFin = Double.valueOf(this.borneFinS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     this.borneFinS = HelperC.TraitementMontant.getMontantFormate(this.borneFin, 0);
     this.borneFin = Double.valueOf(this.borneFinS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     this.borneFinS = HelperC.TraitementMontant.getMontantFormate(this.borneFin, 0);
     this.borneFin = Double.valueOf(this.borneFinS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
   }
 
 
   
   public void changeSommeRetranche() {
     try {
       this.sommeRetranche = Double.valueOf(this.sommeRetrancheS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     }
     catch (Exception e) {
       
       this.sommeRetranche = 0.0D;
     } 
     this.sommeRetrancheS = HelperC.TraitementMontant.getMontantFormate(this.sommeRetranche, 0);
     this.sommeRetranche = Double.valueOf(this.sommeRetrancheS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     this.sommeRetrancheS = HelperC.TraitementMontant.getMontantFormate(this.sommeRetranche, 0);
     this.sommeRetranche = Double.valueOf(this.sommeRetrancheS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
     
     this.sommeRetrancheS = HelperC.TraitementMontant.getMontantFormate(this.sommeRetranche, 0);
     this.sommeRetranche = Double.valueOf(this.sommeRetrancheS.replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue();
   }
 
   
   public void cloturerBaremeIRE() {
     chargerBareme();
   }
   
   public void chargement() {
     this.listBareme = FichierBaseDAO.getInstance().getListBareme(false);
   }
   
   public void NoncloturerBaremeIRE() {
     this.borneFin = 0.0D;
     this.borneFinS = "";
   }
 
   
   private void chargerBareme() {
     double pource = 0.0D;
     double somme = 0.0D;
     double petitBorne = 0.0D;
     double grandBorne = 0.0D;
     
     for (DetailBaremeC det : getListDetailBareme()) {
 
       
       if (this.detailBareme == null) {
         continue;
       }
       
       if (det.getIndex() == this.detailBareme.getIndex()) {
         
         this.detailBareme = det;
         this.detailBareme.setExiste(true);
         break;
       } 
       pource = det.getPourcentage();
       somme = det.getSommeRetranche();
       petitBorne = det.getBorneDebut();
       grandBorne = det.getBorneFin();
     } 
     if ((this.pourcentage != 0.0D && pource > this.pourcentage) || (this.sommeRetranche != 0.0D && somme > this.sommeRetranche)) {
       
       HelperC.afficherMessage("Information", "le pourcentage ou la somme é retrancher doit étre supérieur(ere)  é celui ou celle déjé inséré(e) dans le tableau");
 
     
     }
     else if (petitBorne > 0.0D && grandBorne == 1.0D) {
       
       HelperC.afficherMessage("Information", "Le baréme est déjé cloturé");
     } 
     if (this.detailBareme == null)
     {
       this.detailBareme = new DetailBaremeC();
     }
     this.detailBareme.setBorneDebut(this.borneDebut);
     this.detailBareme.setBorneDebutString(this.borneDebutS);
     this.detailBareme.setBorneFin(this.borneFin);
     this.detailBareme.setBorneFinString(this.borneFinS);
     this.detailBareme.setPourcentage(this.pourcentage);
     this.detailBareme.setSommeRetranche(this.sommeRetranche);
     this.detailBareme.setSommeRetrancheString(this.sommeRetrancheS);
     this.detailBareme.setIndex(this.index);
     clearDetailBareme();
     if (!this.detailBareme.isExiste())
     {
       getListDetailBareme().add(this.detailBareme);
     }
     this.detailBareme = null;
   }
 
   
   public void charger() {
     if (this.borneFin == 0.0D && this.borneDebut == 0.0D) {
       
       HelperC.afficherMessage("Information", "Précisez la borne Fin");
     }
     else if (this.borneFin < this.borneDebut && this.borneFin != 0.0D) {
       
       HelperC.afficherMessage("Information", "La borne Début  ne peut pas étre supérieure é la borne Fin ");
     }
     else if (this.pourcentage != 0.0D && this.sommeRetranche != 0.0D) {
       
       HelperC.afficherMessage("Information", "La somme é retrancher et le pourcentage ne peuvent pas étre ajouter en méme temps");
 
     
     }
     else if (this.index > 1 && this.sommeRetranche == 0.0D && this.pourcentage == 0.0D) {
       
       HelperC.afficherMessage("Information", "le pourcentage ou la somme é retrancher doit étre supérieur  é 0");
     }
     else if (this.borneDebut > 0.0D && this.borneFin == 0.0D) {
       
       PrimeFaces.current().executeScript("PF('confirmDialog').show();");
     }
     else {
       
       chargerBareme();

     } 
   }
 
   
   private void clearDetailBareme() {
     this.borneDebut = 0.0D;
     this.borneDebutS = "";
     this.pourcentage = 0.0D;
     this.sommeRetranche = 0.0D;
     this.sommeRetrancheS = "";
     this.index = this.detailBareme.getIndex() + 1;
     this.borneDebut = this.detailBareme.getBorneFin() + 1.0D;
     this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
     this.borneFin = 0.0D;
     this.borneFinS = "";
   }
 
   
   public void enlever() {
     if (this.detailBareme != null) {
       
       if (this.detailBareme.getId() > 0)
       {
         getListDetailBaremeDeleted().add(this.detailBareme);
       }
       getListDetailBareme().remove(this.detailBareme);
       clearDetail();
     } 
   }
 
   
   public void clearDetail() {
     this.detailBareme = null;
     this.borneDebut = 0.0D;
     this.borneDebutS = "";
     this.borneFin = 0.0D;
     this.borneFinS = "";
     this.pourcentage = 0.0D;
     this.sommeRetranche = 0.0D;
     this.sommeRetrancheS = "";
     this.index = 1;
   }
 
   
   private void setObject() {
	 			disableMsg=true;
     if (this.bareme != null) {
        disableMsg=false;
       setId(this.bareme.getId());
       setNumero(this.bareme.getNumero());
       setDate(this.bareme.getDate());
       this.dateS = HelperC.convertDate(getDate());
       setPensionComplementaire(this.bareme.getPensionComplementaire());
       setFormule(this.bareme.getFormule());
       setTransportBrut(this.bareme.getTransportBrut());
       setMontantPersonneCharge(this.bareme.getMontantPersonneCharge());
       setLogementNonImposable(this.bareme.getLogementNonImposable());
       setPourcentagePersonneCharge(this.bareme.getPourcentagePersonneCharge());
       setDescription(this.bareme.getDescription());
       setActif(this.bareme.isActif());
       setTypeEmploye(this.bareme.getTypeEmploye());
     } 
   }
 
 
   
   private void affecterDetail() {
     if (this.detailBareme != null) {
       
       this.borneDebut = this.detailBareme.getBorneDebut();
       this.borneDebutS = HelperC.TraitementMontant.getMontantFormate(this.borneDebut, 0);
       this.borneFin = this.detailBareme.getBorneFin();
       this.borneFinS = HelperC.TraitementMontant.getMontantFormate(this.borneFin, 0);
       this.pourcentage = this.detailBareme.getPourcentage();
       this.sommeRetranche = this.detailBareme.getSommeRetranche();
       this.sommeRetrancheS = HelperC.TraitementMontant.getMontantFormate(this.sommeRetranche, 0);
       this.index = this.detailBareme.getIndex();
     } 
   }
 
   
   public void onRowSelectedDetail(SelectEvent event) {
     this.detailBareme = (DetailBaremeC)event.getObject();
     if (this.detailBareme != null)
     {
       affecterDetail();
     }
   }
   
   public void onRowSelect(SelectEvent event) {
     this.bareme = (BaremeIPRC)event.getObject();
     setObject();
     setDetailBareme();
     PrimeFaces.current().executeScript("PF('dlgBrm').hide();");
   }
   
   private void clear(boolean b) {
     if (b)
     {
       setNumero("");
     }
     this.bareme = null;
     setId(0);
     setDate(Calendar.getInstance().getTime());
     this.dateS = HelperC.convertDate(getDate());
     setTransportBrut(0.0D);
     setPensionComplementaire(0.0D);
     setPourcentagePersonneCharge(0.0D);
     setLogementNonImposable(0.0D);
     setType(1);
     setTypeEmploye(0);
     setFormule("");
      disableMsg=true;
     setDescription("");
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getType() == 0) {
       
       HelperC.afficherMessage("Information", "Précisez le type de baréme");
     }
     else if (getListDetailBareme() == null) {
       
       HelperC.afficherMessage("Information", "Précisez les détails de Baréme ");
     } 
     Historique hist = new Historique();
     hist.setDateOperation(Calendar.getInstance().getTime());
     hist.setOperateur(this.operateur);
     if (getId() == 0) {
       
       hist.setOperation("Création du baréme " + getNumero());
     } else {
      
       hist.setOperation("Modification du baréme " + getNumero());
     } 
     hist.setTable(Tables.getTableName(Tables.TableName.bareme));
     setHistorique(hist);
     
				if(isActif())
				{
					BaremeIPRC brm= bareme=FichierBaseDAO.getInstance().getBaremeIRE(getTypeEmploye());
					if(getId()!=brm.getId())
					{
						HelperC.afficherAttention("Attetion", "Un autre baréme active pour ce type d'employé existe !");
						return;
					}
				}
     if (FichierBaseDAO.getInstance().insertUpdateBareme(this)) {
       
       HelperC.afficherMessage("Information", "succès de l'opération ");
				chargerBaremeActif();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de l'opération");
     } 
   }
 
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (this.droit.isSupprimer()) {
       
       if (FichierBaseDAO.getInstance().deleteBareme(this.bareme)) {
         
         clear(true);
         HelperC.afficherMessage("Information", "Succes de l'opération");
         setListDetailBareme(null);
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de suppression ");
       } 
     } else {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
     } 
   }
 }

