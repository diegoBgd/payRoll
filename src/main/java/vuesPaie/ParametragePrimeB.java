 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.CategoriePersonnelC;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.GradePersonnelC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ParametragePrimeC;
 import classesPaie.ParametragePrimeDetailC;
 import classesPaie.PrimeIndemniteC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Iterator;
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
 public class ParametragePrimeB
   extends ParametragePrimeC {
   private static final long serialVersionUID = 1759124516649373735L;
   private int idPrm;
   private int idGrade;
   private int idCategorie;
   private int idFonction;
   private int idPersonnel;
   private PrimeIndemniteC prime;
   private GradePersonnelC grade;
   private CategoriePersonnelC categorie;
   private Base fonction;
   private Base personnel;
   private ParametragePrimeC selected;
   private List<SelectItem> listPrime;
   private List<SelectItem> listGrade;
   private List<SelectItem> listCategorie;
   private List<SelectItem> listFonction;
   private List<SelectItem> listPersonnel;
   private List<ParametragePrimeC> listParametre;
   private List<ParametragePrimeDetailC> listElementPrm;
   private ParametragePrimeDetailC detail;
   private boolean prmSelected,disableMsg;
   private boolean disableDetail;
   ExerciceC exercice;
   DroitC droit;
   HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   OperateurC operateur;
   
   List<ParametragePrimeC> listParmAdded;
   
   int priority;
   
   public boolean isPrmSelected() {
     return this.prmSelected;
   }
   
   public void setPrmSelected(boolean prmSelected) {
     this.prmSelected = prmSelected;
   }
   
   public int getIdPrm() {
     return this.idPrm;
   }
   
   public void setIdPrm(int idPrm) {
     this.idPrm = idPrm;
   }
   
   public int getIdGrade() {
     return this.idGrade;
   }
   
   public void setIdGrade(int idGrade) {
     this.idGrade = idGrade;
   }
   
   public int getIdCategorie() {
     return this.idCategorie;
   }
   
   public void setIdCategorie(int idCategorie) {
     this.idCategorie = idCategorie;
   }
   
   public int getIdFonction() {
     return this.idFonction;
   }
   
   public void setIdFonction(int idFonction) {
     this.idFonction = idFonction;
   }
   
   public int getIdPersonnel() {
     return this.idPersonnel;
   }
   
   public void setIdPersonnel(int idPersonnel) {
     this.idPersonnel = idPersonnel;
   }
   
   public PrimeIndemniteC getPrime() {
     return this.prime;
   }
   
   public void setPrime(PrimeIndemniteC prime) {
     this.prime = prime;
   }
   
   public GradePersonnelC getGrade() {
     return this.grade;
   }
   
   public void setGrade(GradePersonnelC grade) {
     this.grade = grade;
   }
   
   public CategoriePersonnelC getCategorie() {
     return this.categorie;
   }
   
   public void setCategorie(CategoriePersonnelC categorie) {
     this.categorie = categorie;
   }
   
   public Base getFonction() {
     return this.fonction;
   }
   
   public void setFonction(Base fonction) {
     this.fonction = fonction;
   }
   
   public Base getPersonnel() {
     return this.personnel;
   }
   
   public void setPersonnel(Base personnel) {
     this.personnel = personnel;
   }
   
   public List<SelectItem> getListPrime() {
     return this.listPrime;
   }
   
   public void setListPrime(List<SelectItem> listPrime) {
     this.listPrime = listPrime;
   }
   
   public List<SelectItem> getListGrade() {
     return this.listGrade;
   }
   
   public void setListGrade(List<SelectItem> listGrade) {
     this.listGrade = listGrade;
   }
   
   public List<SelectItem> getListCategorie() {
     return this.listCategorie;
   }
   
   public void setListCategorie(List<SelectItem> listCategorie) {
     this.listCategorie = listCategorie;
   }
   
   public List<SelectItem> getListFonction() {
     return this.listFonction;
   }
   
   public void setListFonction(List<SelectItem> listFonction) {
     this.listFonction = listFonction;
   }
   
   public List<SelectItem> getListPersonnel() {
     return this.listPersonnel;
   }
   
   public void setListPersonnel(List<SelectItem> listPersonnel) {
     this.listPersonnel = listPersonnel;
   }
   
   public List<ParametragePrimeC> getListParametre() {
     return this.listParametre;
   }
   
   public void setListParametre(List<ParametragePrimeC> listParametre) {
     this.listParametre = listParametre;
   }
   
   public ParametragePrimeC getSelected() {
     return this.selected;
   }
   
   public void setSelected(ParametragePrimeC selected) {
     this.selected = selected;
   }
   public List<ParametragePrimeDetailC> getListElementPrm() {
     return this.listElementPrm;
   }
   public void setListElementPrm(List<ParametragePrimeDetailC> listElementPrm) {
     this.listElementPrm = listElementPrm;
   }
   
   public ParametragePrimeDetailC getDetail() {
     return this.detail;
   }
   
   public void setDetail(ParametragePrimeDetailC detail) {
     this.detail = detail;
   }
   
   public boolean isDisableDetail() {
     return this.disableDetail;
   }
   
   public void setDisableDetail(boolean disableDetail) {
     this.disableDetail = disableDetail;
   }
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   @PostConstruct
   private void init() {
     String codeExercice = (String)this.session.getAttribute("exercice");
     String codeOperateur = (String)this.session.getAttribute("operateur");
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     if (this.exercice == null || this.operateur == null) {
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       } catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       disableMsg=true;
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.elementPaie); 
       this.disableDetail = true;
       this.listCategorie = new ArrayList<SelectItem>();
       this.listFonction = new ArrayList<SelectItem>();
       this.listGrade = new ArrayList<SelectItem>();
       this.listPersonnel = new ArrayList<SelectItem>();
       this.listPrime = new ArrayList<SelectItem>();
       this.listElementPrm = new ArrayList<ParametragePrimeDetailC>();
       this.listParmAdded = new ArrayList<ParametragePrimeC>();
       
       chargementPersonnel();
       chargementFonction();
       chargementPrime();
     } 
   }
 
   
   public void chargementParametre() {
     this.listParametre = FichierBaseDAO.getInstance().getAllParametragePrime(0, 0, 0, 0);
     for (ParametragePrimeC parm : this.listParametre) {
       if (parm.getIdCategorie() > 0)
         parm.setLibelleCategorie(FichierBaseDAO.getInstance().getCategoriePersonnel(parm.getIdCategorie()).getDesignation()); 
       if (parm.getIdFonction() > 0)
         parm.setLibelleFonction(FichierBaseDAO.getInstance().getBaseById(parm.getIdFonction(), Tables.getTableName(Tables.TableName.fonction)).getDesignation()); 
       if (parm.getIdGrade() > 0)
         parm.setLibelleGrade(FichierBaseDAO.getInstance().getGradePersonnel(parm.getIdGrade()).getDesignation()); 
       if (parm.getIdPersonnel() > 0)
         parm.setLibellePersonnel(FichierBaseDAO.getInstance().getBaseById(parm.getIdPersonnel(), Tables.getTableName(Tables.TableName.personnel)).getDesignation()); 
       if (parm.getIdPrime() > 0) {
         parm.setLibellePrime(FichierBaseDAO.getInstance().getPrimeIndemnite(parm.getIdPrime()).getDesignation());
       }
       parm.setListDetail(FichierBaseDAO.getInstance().getAllParametragePrimeDetail(parm.getId()));
     } 
   }
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (ParametragePrimeC)event.getObject();
     if (this.selected != null) {
       disableMsg=true;
       parameterValues();
       PrimeFaces.current().executeScript("PF('dlgPrm').hide();");
     } 
   }
 
 
   
   private void parameterValues() {
     setId(this.selected.getId());
     this.idPersonnel = this.selected.getIdPersonnel();
     this.idCategorie = this.selected.getIdCategorie();
     this.idGrade = this.selected.getIdGrade();
     
     this.idFonction = this.selected.getIdFonction();
     this.idPrm = this.selected.getIdPrime();
     this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrm);
     
     chargementCategorie();
     chargementGrade();
     disableMsg=false;
     this.listParmAdded = FichierBaseDAO.getInstance().getDistictParametragePrime(this.idPersonnel, this.idCategorie, this.idGrade, this.idFonction);
     if (this.listParmAdded.size() > 0) {
       primeAjoute();
     } else {
       chargerPrime();
     }  setTypeBase(this.selected.getTypeBase());
     setTaux(this.selected.getTaux());
     setForfait(this.selected.getForfait());
     setPlafond(this.selected.getPlafond());
     setPlancher(this.selected.getPlancher());
     setCalculHeurSup(this.selected.isCalculHeurSup());
     if (this.selected.getTaux() > 0.0D) {
       this.disableDetail = false;
     } else {
       this.disableDetail = true;
     } 
     if (this.selected.getListDetail().size() > 0) {
       for (ParametragePrimeDetailC det : this.selected.getListDetail()) {
         for (ParametragePrimeDetailC dt : this.listElementPrm) {
           if (det.getCodeElement().equals(dt.getCodeElement())) {
             dt.setId(det.getId());
             dt.setAdded(true);
             dt.setTaux(det.getTaux());
             dt.setForfait(det.getForfait());
             dt.setPlafon(det.getPlafon());
             dt.setPlancher(det.getPlancher());
             dt.setDisable(false);
           } 
         } 
       } 
     }
   }
   
   public void changePersonnel(ValueChangeEvent event) {
     if (event != null && event.getNewValue() != null) {
       this.idPersonnel = ((Integer)event.getNewValue()).intValue();
       chargementCategorie();
     } 
   }
 
   
   public void changeFonction(ValueChangeEvent event) {
     if (event != null && event.getNewValue() != null) {
       this.idFonction = ((Integer)event.getNewValue()).intValue();
     }
   }
   
   public void changeCategorie(ValueChangeEvent event) {
     if (event != null && event.getNewValue() != null) {
       
       this.idCategorie = ((Integer)event.getNewValue()).intValue();
       chargementGrade();
     } 
   }
   public void changeGrade(ValueChangeEvent event) {
     if (event != null && event.getNewValue() != null)
       this.idGrade = ((Integer)event.getNewValue()).intValue(); 
   }
   
   public void changePrime(ValueChangeEvent event) {
     if (event != null && event.getNewValue() != null) {
       this.idPrm = ((Integer)event.getNewValue()).intValue();
       primeValues();
     } 
   }
 
   
   private void primeValues() {
     this.listParmAdded.clear();
     this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrm);
     if (this.prime != null)
     {
       checkElement();
     }
   }
 
 
   
   private void checkElement() {
				disableMsg=true;
     this.selected = FichierBaseDAO.getInstance().getParametragePrime(this.idPersonnel, this.idCategorie, this.idGrade, this.idFonction, this.idPrm, true);
     if (this.selected != null) {
       parameterValues();
     } else {
       
       this.listParmAdded = FichierBaseDAO.getInstance().getDistictParametragePrime(this.idPersonnel, this.idCategorie, this.idGrade, this.idFonction);
       if (this.listParmAdded.size() > 0) {
         primeAjoute();
       } else {
         chargerPrime();
       } 
     } 
   }
   private void chargementPersonnel() {
     this.listPersonnel.clear();
     this.listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));
     for (Base b : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel)))
     {
       this.listPersonnel.add(new SelectItem(Integer.valueOf(b.getId()), String.valueOf(b.getCode()) + "||" + b.getDesignation()));
     }
   }
   
   private void chargementFonction() {
     this.listFonction.add(new SelectItem(Integer.valueOf(0), ""));
     for (Base b : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction))) {
       this.listFonction.add(new SelectItem(Integer.valueOf(b.getId()), String.valueOf(b.getCode()) + "||" + b.getDesignation()));
     }
   }
   
   private void chargementCategorie() {
     this.listCategorie.clear();
     this.listCategorie.add(new SelectItem(Integer.valueOf(0), ""));
     for (CategoriePersonnelC cat : FichierBaseDAO.getInstance().getListCategoriePersonnelParIdPersonnel(this.idPersonnel))
     {
       this.listCategorie.add(new SelectItem(Integer.valueOf(cat.getId()), String.valueOf(cat.getCode()) + "||" + cat.getDesignation()));
     }
   }
 
   
   private void chargementGrade() {
     this.listGrade.clear();
     this.listGrade.add(new SelectItem(Integer.valueOf(0), ""));
     for (GradePersonnelC grade : FichierBaseDAO.getInstance().getListGradeParCategoriePersonnel(this.idCategorie))
     {
       this.listGrade.add(new SelectItem(Integer.valueOf(grade.getId()), String.valueOf(grade.getCode()) + "||" + grade.getDesignation()));
     }
   }
   
   private void chargementPrime() {
     this.listPrime.clear();
     this.listPrime.add(new SelectItem(Integer.valueOf(0), ""));
     for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemnite())
     {
       this.listPrime.add(new SelectItem(Integer.valueOf(prm.getId()), String.valueOf(prm.getCode()) + "||" + prm.getDesignation()));
     }
   }
 
   
   private void chargerPrime() {
     this.listElementPrm.clear();
     if (this.prime != null) {
       
      
         for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemnite())
					{
         if (this.prime.getId() != prm.getId()) {
           if (this.detail == null)
             this.detail = new ParametragePrimeDetailC(); 
           this.detail.setCodeElement(prm.getCode());
           this.detail.setLibelle(prm.getDesignation());
           this.listElementPrm.add(this.detail);
           this.detail = null;
         }  }
     
     } 
   }
   
   private void primeAjoute() {
     this.listElementPrm.clear();
     PrimeIndemniteC pm = null;
     
     for (ParametragePrimeC prm : this.listParmAdded) {
       
       pm = FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
       if (pm != null)
       {
 
         
         if (this.prime.getId() != pm.getId()) {
           if (this.detail == null)
             this.detail = new ParametragePrimeDetailC(); 
           this.detail.setCodeElement(pm.getCode());
           this.detail.setLibelle(pm.getDesignation());
           this.listElementPrm.add(this.detail);
           this.detail = null;
         }  
				} 
     } 
   }
   
   public void changeTaux() {
     if (getTaux() > 0.0D) {
       setForfait(0.0D);
       this.disableDetail = false;
     } else {
       
       this.disableDetail = true;
     } 
   }
   
   public void changeForfait() {
     if (getForfait() > 0.0D) {
       setTaux(0.0D);
       setPlafond(0.0D);
       setPlancher(0.0D);
       this.disableDetail = true;
     } else {
       
       this.disableDetail = false;
     } 
   }
   private int addPriority(ParametragePrimeDetailC det) {
     this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(det.getCodeElement());
     this.priority = FichierBaseDAO.getInstance().getPrioritePrime(this.idPersonnel, this.idCategorie, this.idGrade, this.idFonction, this.prime.getId());
     
     return this.priority;
   }
   
   public void affecterDetail() {
     for (ParametragePrimeDetailC det : this.listElementPrm) {
       if (det.isAdded()) {
         
         getListDetail().add(det);
         
         continue;
       } 
       if (det.getId() > 0) {
         getListDeleted().add(det);
       }
     } 
     
     PrimeFaces.current().executeScript("PF('dlgPrmElt').hide();");
   }
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
 
 
     
     }
     else if (this.idPrm == 0) {
       HelperC.afficherAttention("ATTENTION", "Il faut préciser la prime ou l'indemnité");
     } else {
       
       if (getListDetail().size() > 0) {
         
         for (ParametragePrimeDetailC det : getListDetail()) {
           this.priority = addPriority(det);
           this.priority++;
           
           if (this.priority > getPriorite()) {
             setPriorite(this.priority);
           }
         } 
       } else {
         setPriorite(1);
       } 
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création du paramétre prime ");
       } else {
         hist.setOperation("Modification du paramétre prime ");
       }  setHist(hist);
       setIdPrime(this.idPrm);
       setIdGrade(this.idGrade);
       setIdCategorie(this.idCategorie);
       setIdFonction(this.idFonction);
       setIdPersonnel(this.idPersonnel);
       
       if (FichierBaseDAO.getInstance().insertUpdateParametrePrime(this)) {
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         initialiser();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
   
   public void supprimer() {
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (this.droit.isSupprimer()) {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Suppression du paramétre prime ");
       }
       if (FichierBaseDAO.getInstance().deleteParametragePrime(this.selected)) {
 
         
         HelperC.afficherMessage("Information", "Succes de l'opération");
         initialiser();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de suppression ");
       } 
     } else {
       
       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
     } 
   }
 
   
   public void initialiser() {
     setId(0);
     this.idCategorie = 0;
     this.idFonction = 0;
     this.idGrade = 0;
     this.idPersonnel = 0;
     this.idPrm = 0;
     setTypeBase(0);
     setPlafond(0.0D);
     setPlancher(0.0D);
     setForfait(0.0D);
     setTaux(0.0D);
     setCalculHeurSup(false);
     this.listElementPrm.clear();
     chargerPrime();
   }
 }

