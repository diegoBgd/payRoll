 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DetailParametrageHeureSupplementaireC;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.HeuresPrestees;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageHeureSupplementaireC;
 import java.io.IOException;
 import java.util.ArrayList;
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
 public class SaisieHeurSupVue extends HeuresPrestees {
   private static final long serialVersionUID = 8043562123066843782L;
   private EmployeC employe;
   private List<DetailParametrageHeureSupplementaireC> listHeurSup;
   private List<EmployeC> listEmploye;
   private List<HeuresPrestees> listHeurPreste;
   private String nomRechEmp;
   private String prenomRechEmp;
   private String codeRechEmp;
   private String nomEmploye;
   private String codeEmploye;
   private HeuresPrestees hPreste;
   int idEmploye;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private DroitC droit;
            private boolean disableMsg;
   ParametrageHeureSupplementaireC parm;
   
   public HeuresPrestees gethPreste() {
     return this.hPreste;
   }
 
   
   public void sethPreste(HeuresPrestees hPreste) {
     this.hPreste = hPreste;
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
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
   
   public List<HeuresPrestees> getListHeurPreste() {
     return this.listHeurPreste;
   }
   
   public void setListHeurPreste(List<HeuresPrestees> listHeurPreste) {
     this.listHeurPreste = listHeurPreste;
   }
 
 
   
   public String getNomRechEmp() {
     return this.nomRechEmp;
   }
 
   
   public void setNomRechEmp(String nomRechEmp) {
     this.nomRechEmp = nomRechEmp;
   }
 
   
   public String getPrenomRechEmp() {
     return this.prenomRechEmp;
   }
 
   
   public void setPrenomRechEmp(String prenomRechEmp) {
     this.prenomRechEmp = prenomRechEmp;
   }
 
   
   public String getCodeRechEmp() {
     return this.codeRechEmp;
   }
 
   
   public void setCodeRechEmp(String codeRechEmp) {
     this.codeRechEmp = codeRechEmp;
   }
 
   
   public EmployeC getEmploye() {
     return this.employe;
   }
 
   
   public void setEmploye(EmployeC employe) {
     this.employe = employe;
   }
   
   public List<DetailParametrageHeureSupplementaireC> getListHeurSup() {
     return this.listHeurSup;
   }
 
   
   public void setListHeurSup(List<DetailParametrageHeureSupplementaireC> listHeurSup) {
     this.listHeurSup = listHeurSup;
   }
 
   
   public void chargementEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeRechEmp, this.nomRechEmp,true);
   }
 
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   @PostConstruct
   private void init() {
     this.operateur = new OperateurC();
     this.exercice = new ExerciceC();
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     if (codeOperateur == null || codeExercice == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       disableMsg=true;
       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.bulletinPaie);
       }
       this.listHeurPreste = new ArrayList<HeuresPrestees>();
       chargement();
     } 
   }
 
   
   private void chargement() {
     this.parm = FichierBaseDAO.getInstance().getParametrageHeureSupplementaire();
     if (this.parm != null) {
       
       this.listHeurSup = FichierBaseDAO.getInstance().getListeDetailParametrageHeureSupplementaire(this.parm);
       if (this.listHeurSup != null && this.listHeurSup.size() > 0)
       {
         for (DetailParametrageHeureSupplementaireC det : this.listHeurSup) {
           
           if (this.hPreste == null)
           {
             this.hPreste = new HeuresPrestees();
           }
           this.hPreste.setTemps("De " + det.getHeureDebutS() + " é " + det.getHeureFinS());
           this.hPreste.setPourcent(det.getTaux());
           this.hPreste.setNumeroHs(this.parm.getNumero());
           this.hPreste.setIdExercice(this.exercice.getId());
           this.listHeurPreste.add(this.hPreste);
           this.hPreste = null;
         } 
       }
     } 
   }
 
 
   
   public void onEmployeSelected(SelectEvent event) {
     this.employe = (EmployeC)event.getObject();
     if (this.employe != null) {
       
       this.idEmploye = this.employe.getId();
       setIdEmploye(this.employe.getId());
       this.nomEmploye = this.employe.getNomPrenom();
       this.codeEmploye = this.employe.getCode();
       PrimeFaces.current().executeScript("PF('dlgEmploye').hide();");
     } 
   }
 
 
   
   public void searchEmploye() {
     if (!this.codeEmploye.equals("")) {
       
       this.employe = FactoryDAO.getInstance().getEmploye(this.codeEmploye, true);
       if (this.employe != null) {
         
         this.idEmploye = this.employe.getId();
         setIdEmploye(this.employe.getId());
         this.nomEmploye = this.employe.getNomPrenom();
       } 
     } 
   }
 
   
   public void changeMonth(ValueChangeEvent event) {
     setMois(((Integer)event.getNewValue()).intValue());
     if (getMois() > 0)
     {
       chargerHeurSup();
     }
   }
 
   
   private void chargerHeurSup() {
     this.listHeurPreste = FactoryDAO.getInstance().getListHeureSupplementaire(this.idEmploye, this.exercice.getId(), getMois());
     DetailParametrageHeureSupplementaireC det = null;
     if (this.listHeurPreste.size() > 0) {
       disableMsg=false;
       
       for (HeuresPrestees hp : this.listHeurPreste) {
 
 
         
         this.parm = FichierBaseDAO.getInstance().getParametrageHeureSupplementaire(hp.getNumeroHs());
         
         if (this.parm != null)
         {
           
           det = FichierBaseDAO.getInstance().getListeDetailParametrageHeureSupplementaire(hp.getPourcent(), this.parm.getId());
           if (det != null)
             hp.setTemps("De " + det.getHeureDebutS() + " à " + det.getHeureFinS()); 
           hp.setIdExercice(this.exercice.getId());
         }
       
       } 
     } else {
       
       chargement();
     } 
   }
 
 
 
   
   public void save() {
     for (HeuresPrestees hp : this.listHeurPreste) {
 
       
       hp.setIdEmploye(this.idEmploye);
       hp.setMois(getMois());
     } 
     
     if ((this.droit != null && this.droit.isCreer()) || this.droit.isModifier()) {
       FactoryDAO.getInstance().insertUpdateHeureSupplementaire(this.listHeurPreste);
       initialize();
     
     }
     else {
       
       HelperC.afficherAttention("ATTENTION", 
           "Vous n'avez pas le droit de créer");
     } 
   }
   
   public void deleteHsup() {
     if (this.droit != null && this.droit.isSupprimer()) {
       if(employe!=null && listHeurPreste!=null && listHeurPreste.size()>0)
				{
       FactoryDAO.getInstance().deleteHeurePrste(this.employe.getId(), getMois(), this.exercice.getId());
       initialize();
				}
				else
					 HelperC.afficherDeleteMessage();
     }
     else {
       
       HelperC.afficherAttention("ATTENTION", 
           "Vous n'avez pas le droit de supprimer");
     } 
   }
   
   public void initialize() {
     this.employe = null;
     setMois(0);
     this.nomEmploye = "";
     this.codeEmploye = "";
     this.nomRechEmp = "";
     this.codeRechEmp = "";
     this.prenomRechEmp = "";
     this.listHeurPreste.clear();
			  disableMsg=true;
     chargement();
   }
 }

