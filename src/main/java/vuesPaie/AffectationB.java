package vuesPaie;

import classesPaie.AffectationC;
import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.Tables;

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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;


@ManagedBean
@ViewScoped
public class AffectationB
  extends AffectationC
  implements Serializable
{
  private static final long serialVersionUID = 9223372036854775000L;
  private List<SelectItem> listeFonction;
  private int idFonction;
  private int idFonctionRecherche;
  private int tabIndex;
  private String matricule;
  private String ancienFonction;
   
  private OperateurC operateur;
  private DroitC droit;
  private String matriculeRecherche;
  AffectationC ancienAffectation = null; 
			private String nomRecherche; 
			private String prenomRecherche; 
			private List<EmployeC> listeFonctionnaire; 
			private Base fonctionRecherche; 
			private List<AffectationC> listeAffectation; 
			private AffectationC affectation; 
			private EmployeC employe; 
			private boolean userExist; 
			private boolean readOnly; 
			ExerciceC exercice; 
			Base userFonction;
  HttpSession session;
  public AffectationB() {
    this.listeFonction = new ArrayList<SelectItem>();
    
    this.session = HelperC.getSession();
  }

  
  public boolean isUserExist() {
    return this.userExist;
  }

  
  public void setUserExist(boolean userExist) {
    this.userExist = userExist;
  }

  
  public int getIdFonction() {
    return this.idFonction;
  }

  
  public void setIdFonction(int idFonction) {
    this.idFonction = idFonction;
  }

  
  public String getMatricule() {
    return this.matricule;
  }

  
  public void setMatricule(String matricule) {
    this.matricule = matricule;
  }

  
  public String getMatriculeRecherche() {
    return this.matriculeRecherche;
  }

  
  public void setMatriculeRecherche(String matriculeRecherche) {
    this.matriculeRecherche = matriculeRecherche;
  }

  
  public String getNomRecherche() {
    return this.nomRecherche;
  }

  
  public void setNomRecherche(String nomRecherche) {
    this.nomRecherche = nomRecherche;
  }

  
  public String getPrenomRecherche() {
    return this.prenomRecherche;
  }

  
  public void setPrenomRecherche(String prenomRecherche) {
    this.prenomRecherche = prenomRecherche;
  }
  
  public List<SelectItem> getListeFonction() {
    return this.listeFonction;
  }
  
  public void setListeFonction(List<SelectItem> listeFonction) {
    this.listeFonction = listeFonction;
  }
  
  public List<EmployeC> getListeFonctionnaire() {
    return this.listeFonctionnaire;
  }
  
  public void setListeFonctionnaire(List<EmployeC> listeFonctionnaire) {
    this.listeFonctionnaire = listeFonctionnaire;
  }
  
  public List<AffectationC> getListeAffectation() {
    return this.listeAffectation;
  }
  
  public void setListeAffectation(List<AffectationC> listeAffectation) {
    this.listeAffectation = listeAffectation;
  }

  
  public int getIdFonctionRecherche() {
    return this.idFonctionRecherche;
  }

  
  public void setIdFonctionRecherche(int idFonctionRecherche) {
    this.idFonctionRecherche = idFonctionRecherche;
  }

  
  public AffectationC getAffectation() {
    return this.affectation;
  }

  
  public void setAffectation(AffectationC affectation) {
    this.affectation = affectation;
  }

  
  public int getTabIndex() {
    return this.tabIndex;
  }

  
  public void setTabIndex(int tabIndex) {
    this.tabIndex = tabIndex;
  }

  
  public boolean isReadOnly() {
    return this.readOnly;
  }

  
  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }
  
  public EmployeC getEmploye() {
    return this.employe;
  }
  
  public void setEmploye(EmployeC employe) {
    this.employe = employe;
  }
  
  public String getAncienFonction() {
    return this.ancienFonction;
  }
  
  public void setAncienFonction(String ancienFonction) {
    this.ancienFonction = ancienFonction;
  }

  
  @PostConstruct
  public void init() {
    String codeOperateur = (String)this.session.getAttribute("operateur");
    String codeExercice = (String)this.session.getAttribute("exercice");
    this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
    this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
    if (this.operateur == null || this.exercice == null) {

      
      try {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("/payRoll/login.jsf");
      }
      catch (IOException e) {
        
        e.printStackTrace();
      } 
    } else {
      
      this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
      if (this.userFonction != null)
      {
        this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.structureAdministrative);
      }
      chargerFonction();
    } 
  }

  
  private void chargerFonction() {
    this.listeFonction.clear();
    this.listeFonction.add(new SelectItem(Integer.valueOf(0), " "));
    
    for (Base fonction : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction)))
    {
      this.listeFonction.add(new SelectItem(Integer.valueOf(fonction.getId()), String.valueOf(fonction.getCode()) + " || " + fonction.getDesignation()));
    }
  }


  
  public void changeFonction(ValueChangeEvent event) {
    this.idFonction = ((Integer)event.getNewValue()).intValue();
    setFonction(FichierBaseDAO.getInstance().getBaseById(this.idFonction, Tables.getTableName(Tables.TableName.fonction)));
  }

  
  public void changeFonctionnaire() {
    if (!this.matricule.trim().equals("")) {

      
      this.employe = FichierBaseDAO.getInstance().getFonctionnaireActif(this.matricule, new Date());
      if (this.employe != null) {
        
        setIdEmploye(this.employe.getId());
        setNomPrenom(this.employe.getNomPrenom());
        this.ancienAffectation = FichierBaseDAO.getInstance().getFonctionActuel(this.employe.getId());
        if (this.ancienAffectation != null) {
          
          this.ancienFonction = this.ancienAffectation.getFonction().getDesignation();
        } else {
          
          this.ancienFonction = "";
        } 
      }  if (this.employe.getDateEntre() != null) {
        
        setDateDebut(this.employe.getDateEntre());
        setDateDebutS(HelperC.changeDateFormate(getDateDebut()));
      } 
    } else {
      
      setNomPrenom("");
      setFonction(null);
      this.ancienFonction = "";
      HelperC.afficherInformation("Information", "Le fonctionnaire de ce Matricule n'existe pas ou n'est plus actif");
    } 
  }


  
  public void changeDateDebut() {
    if (getDateDebutS().replace("/", "").replace("_", "").trim().equals("")) {
      
      setDateDebut(null);
    } else {
      
      setDateDebut(HelperC.validerDate(getDateDebutS()));
      if (getDateDebut() == null) {
        
        setDateDebutS("");
        HelperC.afficherInformation("Information", "Date invalide");
      } else {
        
        setDateDebutS(HelperC.convertDate(getDateDebut()));
      } 
    } 
  }

  
  public void changeDateFin() {
    if (getDateFinS().replace("/", "").replace("_", "").trim().equals("")) {
      
      setDateFin(null);
    } else {
      
      setDateFin(HelperC.validerDate(getDateFinS()));
      if (getDateFin() == null) {
        
        setDateFinS("");
        HelperC.afficherInformation("Information", "Date invalide");
      } else {
        
        setDateFinS(HelperC.convertDate(getDateFin()));
      } 
    } 
  }

  
  private void clear(boolean b) {
    setId(0);
    setIdEmploye(0);
    this.matricule = "";
    setNomPrenom("");
    setFonction(null);
    this.idFonction = 0;
    setDateDebut(null);
    setDateDebutS("");
    setDateFin(null);
    setDateFinS("");
    setReference("");
    this.ancienAffectation = null;
  }

  
  public void initialiser() {
    clear(true);
  }

  
  public void enregistrer() {
    if (getId() == 0 && !this.droit.isCreer()) {
      
      HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
    }
    else if (getId() != 0 && !this.droit.isModifier()) {
      
      HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
    }
    else if (getIdEmploye()>0) {
      
      HelperC.afficherInformation("Information", "Précisez le Fonctionnaire");
    
    }
    else if (getDateDebut() == null) {
      
      HelperC.afficherInformation("Information", "Précisez la date  Début de validité");
    }
    else if (getDateDebut().before(employe.getDateEntre())) {
      
      HelperC.afficherAttention("ATTENTION", "Pas d'affectation avant l'étre fonctionnaire");
    }
    else if (getDateFin() != null && !getDateFin().after(getDateDebut())) {
      
      HelperC.afficherAttention("ATTENTION", "La Date de début de validité doit précéder celle de Fin de validité");
    } else {
      
      Historique hist = new Historique();
      hist.setDateOperation(Calendar.getInstance().getTime());
      hist.setOperateur(this.operateur);
      if (getId() == 0) {
        
        hist.setOperation("Saisie Affectation Fonctionnaire: " + employe.getCode());
      } else {
        
        hist.setOperation("Modification Affectation Fonctionnaire: " +employe.getMatricule());
      } 
      hist.setTable(Tables.getTableName(Tables.TableName.affectation));
      setHistorique(hist);
      
      if (getDateFin() != null) {
        setId(this.ancienAffectation.getId());
        setFonction(this.ancienAffectation.getFonction());
      } 
      
      if (FichierBaseDAO.getInstance().insertUpdateAffectation(this)) {
        
        clear(false);
        HelperC.afficherInformation("FELICITATION", "succès de l'Opération");
      } else {
        
        HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
      } 
    } 
  }

  
  public void supprimer() {
    if (!this.droit.isSupprimer()) {
      
      HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
    }
    else if (getId() == 0) {
      
      HelperC.afficherDeleteMessage();
    } else {
      
      Historique hist = new Historique();
      hist.setDateOperation(Calendar.getInstance().getTime());
      hist.setOperateur(this.operateur);
      hist.setOperation("Suppression Affectation: " + employe.getMatricule());
      hist.setTable(Tables.getTableName(Tables.TableName.affectation));
      hist.setIdLigne(getId());
      setHistorique(hist);
      if (FichierBaseDAO.getInstance().deleteAffectation(this)) {
        
        clear(false);
        HelperC.afficherInformation("FELICITATION", "succès de l'Opération");
      } else {
        
        HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
      } 
    } 
  }

  
  public void initRechercheFonctionnaire() {
    this.matriculeRecherche = "";
    this.nomRecherche = "";
    this.prenomRecherche = "";
    if (this.listeFonctionnaire != null)
    {
      this.listeFonctionnaire.clear();
    }
    PrimeFaces.current().executeScript("PF('dlgRechercheFonctionnaire').hide();");
  }

  
  public void rechercherFonctionnaire() {
    this.listeFonctionnaire = FichierBaseDAO.getInstance().getListeFonctionnaire(this.matriculeRecherche, this.nomRecherche, this.prenomRecherche);
    HelperC.afficherInformation("Information", String.valueOf(this.listeFonctionnaire.size()) + " élément(s) trouvé(s)");
  }

  
  public void affecterFonctionnaire() {
//     if (getFonctionnaire() != null) {
//       
//       setMatricule(getFonctionnaire().getMatricule());
//       setNomPrenom(getFonctionnaire().getNomPrenom());
//       if (this.fonctionRecherche != null)
//       {
//         this.idFonction = this.fonctionRecherche.getId();
//       }
//       PrimeFaces.current().executeScript("PF('dlgRechercheFonctionnaire').hide();");
//     } 
      }

  
  public void initRechercheAffectation() {
    this.matriculeRecherche = "";
    this.nomRecherche = "";
    this.prenomRecherche = "";
    this.fonctionRecherche = null;
    this.idFonctionRecherche = 0;
    setComment("");
    if (this.listeAffectation != null)
    {
      this.listeAffectation.clear();
    }
    PrimeFaces.current().executeScript("PF('dlgRecherche').hide();");
  }


  
  public void rechercherAffectation() {
    this.listeAffectation = FichierBaseDAO.getInstance().getListeAffectation(this.nomRecherche, this.prenomRecherche, this.matriculeRecherche, this.fonctionRecherche);
  }


  
  public void takeAffectation() {
    if (this.affectation != null) {
      
      setId(this.affectation.getId());
      this.tabIndex = 0;
			    employe = FactoryDAO.getInstance().getEmployeSimple(affectation.getIdEmploye());
      setFonction(this.affectation.getFonction());
      this.idFonction = getFonction().getId();
                     
      
      setMatricule(employe.getMatricule());
      setNomPrenom(employe.getNomPrenom());
      setDateDebut(this.affectation.getDateDebut());
      setDateDebutS(this.affectation.getDateDebutS());
      setDateFin(this.affectation.getDateFin());
      setDateFinS(this.affectation.getDateFinS());
      setReference(this.affectation.getReference());
      setMotPasse(this.affectation.getMotPasse());
      setComment(this.affectation.getComment());
     
      changeFonctionnaire();
      if (employe.isPartenaire()) {
        
        this.readOnly = false;
      } else {
        
        this.readOnly = true;
      } 
      PrimeFaces.current().executeScript("PF('dlgRecherche').hide();");
    } 
  }
}

