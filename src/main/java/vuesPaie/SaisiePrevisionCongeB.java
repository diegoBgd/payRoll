 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.HelperC;
 import classesPaie.JoursCongeEmployeC;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageDureeCongeC;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.application.FacesMessage;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class SaisiePrevisionCongeB
   extends JoursCongeEmployeC
 {
   private static final long serialVersionUID = 3512300548840150641L;
   private List<JoursCongeEmployeC> allSaisiePrevisionConge = new ArrayList<JoursCongeEmployeC>();
   private OperateurC operateur;
   private DroitC droit;
   private HttpSession session = HelperC.getSession();
   
   Base userFonction;
   
   private Constante.SorteConge sorteConge;
 
   
   @PostConstruct
   private void charger() {
     String codeExercice = (String)this.session.getAttribute("exercice");
     String codeOperateur = (String)this.session.getAttribute("operateur");
     
     if (codeExercice == null || codeOperateur == null) {
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect(
             "/payRoll/login.xhtml");
       } catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       setExercice(FichierBaseDAO.getInstance().getExercice(codeExercice));
       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge); 
       setSorteConge(Constante.SorteConge.congeReposAnnuel);
       findEmployes();
     } 
   }
 
   
   public OperateurC getOperateur() {
     return this.operateur;
   }
   
   public void setOperateur(OperateurC operateur) {
     this.operateur = operateur;
   }
   
   public HttpSession getSession() {
     return this.session;
   }
   
   public void setSession(HttpSession session) {
     this.session = session;
   }
   
   public List<JoursCongeEmployeC> getAllSaisiePrevisionConge() {
     return this.allSaisiePrevisionConge;
   }
 
   
   public void setAllSaisiePrevisionConge(List<JoursCongeEmployeC> allSaisiePrevisionConge) {
     this.allSaisiePrevisionConge = allSaisiePrevisionConge;
   }
   
   public Constante.SorteConge getSorteConge() {
     return this.sorteConge;
   }
   
   public void setSorteConge(Constante.SorteConge sorteConge) {
     this.sorteConge = sorteConge;
   }
 
   
   private void getAllConges() {
     if (this.allSaisiePrevisionConge.size() > 0) {
       for (JoursCongeEmployeC j : this.allSaisiePrevisionConge) {
         j.setNumero(this.allSaisiePrevisionConge.indexOf(j) + 1);
       }
     }
   }
 
 
   
   public void findEmployes() {
     if (getExercice() != null) {
       this.allSaisiePrevisionConge = FactoryDAO.getInstance().getListAllJoursCongeEmploye(getExercice().getId());
     }
     if (this.allSaisiePrevisionConge.size() == 0) {
       
       JoursCongeEmployeC jour = null;
       for (EmployeC employe : FactoryDAO.getInstance().getAllEmploye(false, 0))
       {
         jour = new JoursCongeEmployeC();
         jour.setEmploye(employe);
         jour.setExercice(getExercice());
         ParametrageDureeCongeC conge = new ParametrageDureeCongeC();
        
         if (conge != null) {
           jour.setJoursDu(conge.getNombreJoursAnnuel());
           jour.setJoursDuS(HelperC.TraitementMontant.getMontantFormate(
                 conge.getNombreJoursAnnuel(), 0));
         } 
         getAllSaisiePrevisionConge().add(jour);
       }
     
     } else if (this.allSaisiePrevisionConge.size() > 0) {
       if (getExercice() != null) {
         setAllSaisiePrevisionConge(FactoryDAO.getInstance().getListAllJoursCongeEmploye(getExercice().getId()));
       }
       
       for (EmployeC emp : FactoryDAO.getInstance().getAllEmploye(false, 0)) {
         JoursCongeEmployeC jour = FactoryDAO.getInstance().getJoursCongeEmploye(emp);
 
         
         getAllSaisiePrevisionConge().add(jour);
       } 
     } 
 
     
     getAllConges();
   }
   
   public void save() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     } else if (getId() > 0 && !this.droit.isModifier()) {
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     } else if (this.allSaisiePrevisionConge.size() > 0) {
       for (JoursCongeEmployeC j : this.allSaisiePrevisionConge) {
         if (FactoryDAO.getInstance().insertUpdateJoursCongeEmploye(j)) {
           findEmployes(); continue;
         } 
         HelperC.afficherMessage("Désolé", "Echec d'enregistrement");
       } 
       
       HelperC.afficherMessage("Félicitation", "Enregistrement effectué avec succès");
     } else {
       HelperC.afficherMessage(
           "Information", 
           "Il n'y a pas d'employés sur lesquels on va prévioir les congés", 
           FacesMessage.SEVERITY_ERROR);
     } 
   }
 }


