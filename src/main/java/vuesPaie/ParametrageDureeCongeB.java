 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageDureeCongeC;
 import classesPaie.Tables;

 import java.io.IOException;
 import java.util.ArrayList;
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
 public class ParametrageDureeCongeB
   extends ParametrageDureeCongeC
 {
   private static final long serialVersionUID = -6635429905868713036L;
   private int idPersonnel;
   private int idSorteConge;
   private String dateDecisionS;
   private String dateFinDecisionS;
   private ParametrageDureeCongeC selected;
   private List<ParametrageDureeCongeC> listParametrage;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private int idType;
   private List<SelectItem> listTypeConge = new ArrayList<SelectItem>();
   private List<SelectItem> listPersonnel = new ArrayList<SelectItem>();
 
   
   private DroitC droit;
   
   private Base userFonction;
 
   
   @PostConstruct
   private void init() {
     String codeExercice = (String)this.session.getAttribute("exercice");
     String codeOperateur = (String)this.session.getAttribute("operateur");
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance()
       .getOperateur(codeOperateur);
     
     if (this.operateur == null || this.exercice == null) {
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       } catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), 
             Constante.Role.parametrage);
       }
       this.listParametrage = FichierBaseDAO.getInstance().getAllParametrageDureeConge();
       
       this.listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));
       for (Base p : FichierBaseDAO.getInstance().getAllBase(
           Tables.getTableName(Tables.TableName.personnel))) {
         this.listPersonnel
           .add(new SelectItem(Integer.valueOf(p.getId()), p.getDesignation()));
       }

				listTypeConge.add(new SelectItem(0, ""));
				for (Base typ : FichierBaseDAO.getInstance().getAllBase(
						        Tables.getTableName(Tables.TableName.typeConge))) {
					listTypeConge.add(new SelectItem(Integer.valueOf(typ.getId()), typ.getDesignation()));
						}
     } 
   }
   
   public int getIdPersonnel() {
     return this.idPersonnel;
   }
   
   public void setIdPersonnel(int idPersonnel) {
     this.idPersonnel = idPersonnel;
   }
   
   public String getDateDecisionS() {
     return this.dateDecisionS;
   }
   
   public void setDateDecisionS(String dateDecisionS) {
     this.dateDecisionS = dateDecisionS;
   }
   
   public String getDateFinDecisionS() {
     return this.dateFinDecisionS;
   }
   
   public void setDateFinDecisionS(String dateFinDecisionS) {
     this.dateFinDecisionS = dateFinDecisionS;
   }
   
   public ParametrageDureeCongeC getSelected() {
     return this.selected;
   }
   
   public void setSelected(ParametrageDureeCongeC selected) {
     this.selected = selected;
   }
   
   public List<ParametrageDureeCongeC> getListParametrage() {
     return this.listParametrage;
   }
   
   public void setListParametrage(List<ParametrageDureeCongeC> listParametrage) {
     this.listParametrage = listParametrage;
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
   
   public int getIdType() {
     return this.idType;
   }
   
   public void setIdType(int idType) {
     this.idType = idType;
   }
   
   public List<SelectItem> getListTypeConge() {
     return this.listTypeConge;
   }
   
   public void setListTypeConge(List<SelectItem> listTypeConge) {
     this.listTypeConge = listTypeConge;
   }
   
   public List<SelectItem> getListPersonnel() {
     return this.listPersonnel;
   }
   
   public void setListPersonnel(List<SelectItem> listPersonnel) {
     this.listPersonnel = listPersonnel;
   }
   
   public DroitC getDroit() {
     return this.droit;
   }
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }
   
   public Base getUserFonction() {
     return this.userFonction;
   }
   
   public void setUserFonction(Base userFonction) {
     this.userFonction = userFonction;
   }
   
   public int getIdSorteConge() {
     return this.idSorteConge;
   }
   
   public void setIdSorteConge(int idSorteConge) {
     this.idSorteConge = idSorteConge;
   }
 
   
   public void changePersonnel(ValueChangeEvent event) {
     this.idPersonnel = ((Integer)event.getNewValue()).intValue();
     setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, 
           Tables.getTableName(Tables.TableName.personnel)));
   }
   
   public void changeSorteConge(ValueChangeEvent event) {
     this.idSorteConge = ((Integer)event.getNewValue()).intValue();
     setSorteConge(Constante.getSorteConge(this.idSorteConge));
     if (getSorteConge() != null) {
       setType(null);
       this.idType = 0;
     } 
   }

 
 
 
 
   
   private void clear(boolean b) {
     setId(0);
     setNombreAnneesAjoutJour(0);
     setNombreJoursAjoutes(0);
     setNombreJoursAnnuel(0);
   
     setJoursConge(0);
     setType(null);
     setLibelleJoursConge("");
     setLibelleSorteConge("");
     setPersonnel(null);
     setSorteConge(null);
    
     this.dateDecisionS = "";
     this.dateFinDecisionS = "";
     this.idType = 0;
     this.idPersonnel = 0;
     this.idSorteConge = 0;
     this.selected = null;
   }
 
   
   private void setObject() {
     if (this.selected != null) {
       setId(this.selected.getId());
       setNombreAnneesAjoutJour(this.selected.getNombreAnneesAjoutJour());
       setNombreJoursAjoutes(this.selected.getNombreJoursAjoutes());
       setNombreJoursAnnuel(this.selected.getNombreJoursAnnuel());
      
       setJoursConge(this.selected.getJoursConge());
       setType(this.selected.getType());
       idType=selected.getType().getId();
       setLibelleSorteConge(this.selected.getLibelleSorteConge());
       setLibelleJoursConge(this.selected.getLibelleJoursConge());
       
       if (this.selected.getPersonnel() != null) {
         this.idPersonnel = this.selected.getPersonnel().getId();
         setPersonnel(this.selected.getPersonnel());
       } else {
         this.idPersonnel = 0;
         setPersonnel(null);
       } 
      
     } 
   }
 
 
 
   
   public void onRowSelected(SelectEvent event) {
     this.selected = (ParametrageDureeCongeC)event.getObject();
     if (this.selected != null) {
       setObject();
				PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");

     }
   }
   
   public void chargerTypeConge(ValueChangeEvent event) {
     this.idType = ((Integer)event.getNewValue()).intValue();
     if (this.idType != 0) {
       setType(FichierBaseDAO.getInstance().getTypeConge(this.idType));
       if (getType() != null)
         setSorteConge(null); 
     } 
   }
   
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       HelperC.afficherAttention("ATTENTION", 
           "Vous n'avez pas le droit de créer");
     } else if (getId() > 0 && !this.droit.isModifier()) {
       HelperC.afficherAttention("ATTENTION", 
           "Vous n'avez pas le droit de modifier");
     } else if (getPersonnel() == null || 
       getNombreJoursAnnuel() == 0) {
       HelperC.afficherMessage(
           "Information", 
           "Le Personnel et le nombre de jours sont obligatoires");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         hist.setOperation("Création du parametrage des congés du personnel" + 
             getPersonnel().getDesignation());
       } else {
         hist.setOperation("du parametrage des congés du personnel" + 
             getPersonnel().getDesignation());
       }  hist.setTable(Tables.getTableName(Tables.TableName.parametrageDureeConge));
       setHistorique(hist);
       
       if (FichierBaseDAO.getInstance().insertUpdateParametrageDureeConge(this)) {
         clear(false);
         HelperC.afficherMessage("Information", "Succès de l'opération ");
         this.listParametrage = FichierBaseDAO.getInstance().getAllParametrageDureeConge();
       } else {
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
   
   public void supprimer() {
     if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     } else if (getId() > 0 && !this.droit.isSupprimer()) {
       HelperC.afficherAttention("ATTENTION", 
           "Vous n'avez pas le droit de supprimer");
     }
     else if (FichierBaseDAO.getInstance().delete(
         Tables.getTableName(Tables.TableName.parametrageDureeConge), getId())) {
       clear(true);
       HelperC.afficherMessage("Information", "Succès de l'opération ");
       this.listParametrage = FichierBaseDAO.getInstance().getAllParametrageDureeConge();
     } else {
       HelperC.afficherMessage("Désolé", "Echec de suppression");
     } 
   }
 
   
   public void initialiser() {
     clear(true);
   }
 
   
 
 }


