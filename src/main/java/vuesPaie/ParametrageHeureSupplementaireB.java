 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DetailParametrageHeureSupplementaireC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageHeureSupplementaireC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.Calendar;
 import java.util.Date;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.PrimeFaces;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class ParametrageHeureSupplementaireB
   extends ParametrageHeureSupplementaireC
 {
   private static final long serialVersionUID = -7451782281177619223L;
   private int idDetail;
   private double taux;
   private String heureDebutS;
   private String heureFinS;
   private String tauxS;
   private String dateDebutS;
   private String dateFinS;
   private String tauxJourSupplementaireS;
   private ParametrageHeureSupplementaireC heureSupplementaire;
   private DetailParametrageHeureSupplementaireC detailHeureSupplementaire;
   private OperateurC operateur;
   private int index = 1; private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private boolean selected,disableMsg;
  
   
   public int getIdDetail() {
     return this.idDetail;
   }
   private DroitC droit; Base userFonction;
   
   public void setIdDetail(int idDetail) {
     this.idDetail = idDetail;
   }
 
   
   public boolean isSelected() {
     return this.selected;
   }
 
   
   public void setSelected(boolean selected) {
     this.selected = selected;
   }
 
   
   public int getIndex() {
     return this.index;
   }
 
   
   public void setIndex(int index) {
     this.index = index;
   }
 
   
   public double getTaux() {
     return this.taux;
   }
 
   
   public void setTaux(double taux) {
     this.taux = taux;
   }
 
   
   public String getHeureDebutS() {
     return this.heureDebutS;
   }
 
   
   public void setHeureDebutS(String heureDebutS) {
     this.heureDebutS = heureDebutS;
   }
 
   
   public String getHeureFinS() {
     return this.heureFinS;
   }
 
   
   public void setHeureFinS(String heureFinS) {
     this.heureFinS = heureFinS;
   }
 
   
   public String getTauxS() {
     return this.tauxS;
   }
 
   
   public void setTauxS(String tauxS) {
     this.tauxS = tauxS;
   }
 
   
   public ParametrageHeureSupplementaireC getHeureSupplementaire() {
     return this.heureSupplementaire;
   }
 
   
   public void setHeureSupplementaire(ParametrageHeureSupplementaireC heureSupplementaire) {
     this.heureSupplementaire = heureSupplementaire;
   }
 
   
   public DetailParametrageHeureSupplementaireC getDetailHeureSupplementaire() {
     return this.detailHeureSupplementaire;
   }
 
   
   public void setDetailHeureSupplementaire(DetailParametrageHeureSupplementaireC detailHeureSupplementaire) {
     this.detailHeureSupplementaire = detailHeureSupplementaire;
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
 
   
   public String getDateDebutS() {
     return this.dateDebutS;
   }
 
   
   public void setDateDebutS(String dateDebutS) {
     this.dateDebutS = dateDebutS;
   }
 
   
   public String getDateFinS() {
     return this.dateFinS;
   }
 
   
   public void setDateFinS(String dateFinS) {
     this.dateFinS = dateFinS;
   }
 
   
   public String getTauxJourSupplementaireS() {
     return this.tauxJourSupplementaireS;
   }
 
   
   public void setTauxJourSupplementaireS(String tauxJourSupplementaireS) {
     this.tauxJourSupplementaireS = tauxJourSupplementaireS;
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
			  disableMsg=true;
     if (this.exercice == null || this.operateur == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
      
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
       }
       this.heureSupplementaire = FichierBaseDAO.getInstance().getParametrageHeureSupplementaire();
       if (this.heureSupplementaire != null) {
         
         setObject();
         setDetailParametrageHeureSupplementaire();
       }
       else {
         
         clear(false);
         clearDetail();
       } 
     } 
   }
 
   
   private void setDetailParametrageHeureSupplementaire() {
     setListDetailHeureSupplementaire(FichierBaseDAO.getInstance().getListeDetailParametrageHeureSupplementaire(this.heureSupplementaire));
     if (getListDetailHeureSupplementaire().size() > 0) {
       
       for (DetailParametrageHeureSupplementaireC deta : getListDetailHeureSupplementaire())
       {
         
         deta.setIndex(getListDetailHeureSupplementaire().indexOf(deta) + 1);
         this.index = getListDetailHeureSupplementaire().indexOf(deta) + 2;
       }
     
     } else {
       
       this.index = 1;
       this.heureDebutS = "";
       this.heureFinS = "";
       this.taux = 0.0D;
       this.tauxS = "";
     } 
   }
 
   
   public void changeNumero() {
     this.heureSupplementaire = FichierBaseDAO.getInstance().getParametrageHeureSupplementaire(getNumero());
     if (this.heureSupplementaire != null) {
       
       setObject();
       setDetailParametrageHeureSupplementaire();
     } else {
       
       clear(false);
       clearDetail();
     } 
   }
 

   public void changeHeureDebut() {
     if (this.heureDebutS != null && this.heureDebutS != null && !this.heureDebutS.replace("_", "").trim().replace(":", "").equals(""))
     {
       if (Integer.parseInt(this.heureDebutS.split(":")[0]) > 24 || Integer.parseInt(this.heureDebutS.split(":")[1]) > 59 || Integer.parseInt(this.heureDebutS.split(":")[2]) > 59) {
         
         HelperC.afficherMessage("Information", "Heure Invalide!");
       } else {
         
         searchHeurDebut();
       } 
     }
   }
 
   
   public void changeHeureFin() {
     if (this.heureFinS != null && this.heureFinS != null && !this.heureFinS.replace("_", "").trim().replace(":", "").equals("") && (Integer.parseInt(this.heureFinS.split(":")[0]) > 24 || Integer.parseInt(this.heureFinS.split(":")[1]) > 59 || Integer.parseInt(this.heureFinS.split(":")[2]) > 59))
     {
       HelperC.afficherMessage("Information", "Heure Invalide!");
     }
   }
 
   
   private void searchHeurDebut() {
     for (DetailParametrageHeureSupplementaireC hsp : getListDetailHeureSupplementaire()) {
 
       
       if (hsp.getHeureDebutS().equals(this.heureDebutS)) {
         
         this.detailHeureSupplementaire = hsp;
         affecterDetail();
         this.selected = true;
         break;
       } 
     } 
   }
 
   
  
		

   public void charger() {
     if (this.heureDebutS.replace("_", "").trim().equals("") || this.heureFinS.replace("_", "").trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Précisez les heures début et fin");
     }
     else if (this.taux == 0.0D) {
       
       HelperC.afficherMessage("Information", "Le taux des heures supplementaires ne peut pas étre égal é 0");
     } else {
       
       if (this.detailHeureSupplementaire == null)
       {
         this.detailHeureSupplementaire = new DetailParametrageHeureSupplementaireC();
       }
       this.detailHeureSupplementaire.setHeureDebutS(this.heureDebutS);
       this.detailHeureSupplementaire.setHeureFinS(this.heureFinS);
       this.detailHeureSupplementaire.setTaux(this.taux);
       this.detailHeureSupplementaire.setId(this.idDetail);
       this.detailHeureSupplementaire.setIndex(this.index);
       if (!this.selected) {
         
         getListDetailHeureSupplementaire().add(this.detailHeureSupplementaire);
       } else {
         
         getListDetailHeureSupplementaire().remove(this.index - 1);
         getListDetailHeureSupplementaire().add(this.index - 1, this.detailHeureSupplementaire);
       } 
       clearDetailHeureSupplementaire();
     } 
   }
 
   
   private void clearDetailHeureSupplementaire() {
     this.heureDebutS = "";
     this.heureFinS = "";
     this.taux = 0.0D;
     this.tauxS = "";
     this.index = this.detailHeureSupplementaire.getIndex() + 1;
     this.selected = false;
     this.idDetail = 0;
     this.detailHeureSupplementaire = null;
    
   }
 
   
   public void enlever() {
     if (this.index == 0) {
       
       HelperC.afficherMessage("Information", "Précisez l'élément é supprimer");
     } else {
       
       for (DetailParametrageHeureSupplementaireC det : getListDetailHeureSupplementaire()) {
         
         if (det.getIndex() == this.index) {
           
           this.detailHeureSupplementaire = det;
           
           break;
         } 
       } 
       getListDetailHeureSupplementaireDeleted().add(this.detailHeureSupplementaire);
       getListDetailHeureSupplementaire().remove(this.detailHeureSupplementaire);
       for (DetailParametrageHeureSupplementaireC deta : getListDetailHeureSupplementaire()) {
         
         deta.setIndex(getListDetailHeureSupplementaire().indexOf(deta) + 1);
         this.heureFinS = "";
         this.taux = 0.0D;
         this.tauxS = "";
         this.index = getListDetailHeureSupplementaire().indexOf(deta) + 2;
       } 
     } 
   }
 
 
   
   public void clearDetail() {
     this.detailHeureSupplementaire = null;
     this.heureDebutS = "";
     this.heureFinS = "";
     this.taux = 0.0D;
     this.tauxS = "";
     this.index = 1;
   }
 
   
   private void setObject() {
				disableMsg=true;
     if (this.heureSupplementaire != null) {
       disableMsg=false;
       setId(this.heureSupplementaire.getId());
       setActif(heureSupplementaire.isActif());
       setNumero(heureSupplementaire.getNumero());
     } 
   }
   
   private void affecterDetail() {
     if (this.detailHeureSupplementaire != null) {
       
       this.taux = this.detailHeureSupplementaire.getTaux();
       this.heureDebutS = this.detailHeureSupplementaire.getHeureDebutS();
       this.heureFinS = this.detailHeureSupplementaire.getHeureFinS();
       this.index = this.detailHeureSupplementaire.getIndex();
       this.idDetail = this.detailHeureSupplementaire.getId();
     } 
   }
 
   
   public void onRowSelectedDetail(SelectEvent event) {
     this.detailHeureSupplementaire = (DetailParametrageHeureSupplementaireC)event.getObject();
     if (this.detailHeureSupplementaire != null) {
       
       affecterDetail();
       this.selected = true;
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setNumero("");
     }
     this.heureSupplementaire = null;
     setId(0);
			  disableMsg=true;
     setActif(false);
     setDateDebutS("");
     setDateFinS("");
     setTauxJourSupplementaireS("");
     getListDetailHeureSupplementaire().clear();
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
     
     else if (getListDetailHeureSupplementaire() == null) {
       
       HelperC.afficherMessage("Information", "Précisez les détails de Baréme ");
     } 
     Historique hist = new Historique();
     hist.setDateOperation(Calendar.getInstance().getTime());
     hist.setOperateur(this.operateur);
     if (getId() == 0) {
       
       hist.setOperation("Création du parametrage des heures supplementaires " + getNumero());
     } else {
       
       hist.setOperation("Modification du parametrage des heures supplementaires " + getNumero());
     } 
     hist.setTable(Tables.getTableName(Tables.TableName.heureSupplementaire));
     setHistorique(hist);
             
     if (FichierBaseDAO.getInstance().insertUpdateParametrageHeureSupplementaire(this)) {
       
       HelperC.afficherMessage("Information", "Succès de l'opération ");
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de l'opération");
     } 
   }
 
   
   public void supprimer() {
     if (!this.droit.isSupprimer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
       return;
     } 
     if (getId() == 0) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (FichierBaseDAO.getInstance().deleteParametrageHeureSupplementaire(this)) {
       
       clear(true);
       HelperC.afficherMessage("Information", "Succes de l'opération");
       setListDetailHeureSupplementaire(null);
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de suppression ");
     } 
   }
 }


