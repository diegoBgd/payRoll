 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
		  import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageGeneralC;
 import java.io.IOException;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import persistencePaie.FichierBaseDAO;
 
 @ManagedBean
 @ViewScoped
 public class ParametrageGeneralB
   extends ParametrageGeneralC
 {
   private static final long serialVersionUID = -8346467593125395780L;
   private ParametrageGeneralC parametre;
   private DroitC droit;
			private boolean disableMsg;
   OperateurC operateur;
   ExerciceC exercice;
   HttpSession session = HelperC.getSession();
 
 
   
   public ParametrageGeneralC getParametre() {
     return this.parametre;
   }
 
   
   public void setParametre(ParametrageGeneralC parametre) {
     this.parametre = parametre;
   }
 
   
   public DroitC getDroit() {
     return this.droit;
   }
 
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }


			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
   @PostConstruct
   private void init() {
			  disableMsg=true;
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
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.parametrage);
       }
       chargement();
     } 
   }
 
   
   private void chargement() {
     this.parametre = FichierBaseDAO.getInstance().getParametrageGeneral();
     if (this.parametre != null) {
       disableMsg=false;
       setId(this.parametre.getId());
       setNbreDecimal(this.parametre.getNbreDecimal());
       setTauxMaxLogement(this.parametre.getTauxMaxLogement());
       setAllocationBaseHsup(this.parametre.isAllocationBaseHsup());
       setLogementBaseHsup(this.parametre.isLogementBaseHsup());
       setDureCourTerme(this.parametre.getDureCourTerme());
       setDureLongTerme(this.parametre.getDureLongTerme());
       setDureMoyenTerme(this.parametre.getDureMoyenTerme());
       setMontantNetMin(this.parametre.getMontantNetMin());
       setNbreHeureJour(this.parametre.getNbreHeureJour());
       setNbreHeureMois(this.parametre.getNbreHeureJour() * this.parametre.getNbreHeureJour());
       setNbreJourMois(this.parametre.getNbreJourMois());
       setTauxJrFerie(this.parametre.getTauxJrFerie());
                setMailOrigine(this.parametre.getMailOrigine());
                setPwdOrigine(this.parametre.getPwdOrigine());
                setPort(this.parametre.getPort());
                setSmtpServer(this.parametre.getSmtpServer());
                
     } 
   }
 
   
   public void save() {
     if (getId() == 0 && (this.droit == null || !this.droit.isCreer())) {
       
       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer le bulletin ");
       return;
     } 
     if (getId() > 0 && (this.droit == null || !this.droit.isModifier())) {
       
       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier le bulletin ");
       return;
     } 
     if (FichierBaseDAO.getInstance().insertUpdateParametrageGeneral(this)) {
       
       HelperC.afficherMessage("Information", "Succès de l'opération ");
       initialise();
       chargement();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de l'opération ");
     } 
   }
 
   
   public void delete() {
     if (getId() > 0) {
       
       if (this.droit == null || !this.droit.isSupprimer()) {
         
         HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer le paramétrage ! ");
         return;
       } 
       if (FichierBaseDAO.getInstance().deleteParametrage(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         initialise();
       } 
     } 
   }
 
   
   public void initialise() {
     setId(0);
     setNbreDecimal(0);
     setTauxMaxLogement(0.0D);
     setDureCourTerme(0);
     setDureLongTerme(0);
     setDureMoyenTerme(0);
     setMontantNetMin(0.0D);
     setNbreHeureJour(0);
     setNbreHeureMois(0);
     setNbreJourMois(0);
     setTauxJrFerie(0.0D);
     setAllocationBaseHsup(false);
     setLogementBaseHsup(false);
			  setMailOrigine("");
			  setPwdOrigine("");
			  setPort("");
			  setSmtpServer("");
			  
			  disableMsg=true;
   }
 }

