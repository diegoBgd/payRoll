 package vuesPaie;
 
 import classesPaie.BaremeIPRC;
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.CotisationC;
 import classesPaie.CotisationDetailC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.ParametreCotisationC;
 import classesPaie.PrimeIndemniteC;
 import java.io.IOException;
 import java.util.ArrayList;
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
 public class ParametreCotisationB
   extends ParametreCotisationC
 {
   private static final long serialVersionUID = -5163107367751544507L;
   private List<CotisationC> listCotisation;
   private List<ParametreCotisationC> listeParametre;
   private ParametreCotisationC selected = null;
   
   int indexPrmElt;
   
   int indexCotElt;
   
   private List<PrimeIndemniteC> listPrimes;
   private List<SelectItem> cotisations;
   private String codeElement;
   private String libelleElement;
   private List<CotisationDetailC> listElementPrm;
   private List<CotisationDetailC> listElementCot;
   private List<CotisationDetailC> listCotAdded;
   private CotisationDetailC detail;
   private HttpSession session = HelperC.getSession(); 
            private int idPrime; 
            private int idCot; 
            private boolean detailSelected; 
            private boolean disableSelectPrm; 
            private List<BaremeIPRC> listeBareme; 
            private PrimeIndemniteC prime; 
            private OperateurC operateur; 
            private ExerciceC exercice; 
            private Base selectedOrganisme; 
            private List<Base> listeOrganisme;
   private DroitC droit;
   Base userFonction;
   int priority = 0;
 
 
 
 
   
   public boolean isDisableSelectPrm() {
     return this.disableSelectPrm;
   }
   
   public void setDisableSelectPrm(boolean disableSelectPrm) {
     this.disableSelectPrm = disableSelectPrm;
   }
   
   public List<PrimeIndemniteC> getListPrimes() {
     return this.listPrimes;
   }
   
   public void setListPrimes(List<PrimeIndemniteC> listPrimes) {
     this.listPrimes = listPrimes;
   }
   
   public CotisationDetailC getDetail() {
     return this.detail;
   }
   
   public void setDetail(CotisationDetailC detail) {
     this.detail = detail;
   }
   
   public List<BaremeIPRC> getListeBareme() {
     return this.listeBareme;
   }
   
   public void setListeBareme(List<BaremeIPRC> listeBareme) {
     this.listeBareme = listeBareme;
   }
   
   public DroitC getDroit() {
     return this.droit;
   }
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }
   
   public List<Base> getListeOrganisme() {
     return this.listeOrganisme;
   }
   
   public void setListeOrganisme(List<Base> listeOrganisme) {
     this.listeOrganisme = listeOrganisme;
   }
   
   public String getCodeElement() {
     return this.codeElement;
   }
   
   public void setCodeElement(String codeElement) {
     this.codeElement = codeElement;
   }
   
   public List<CotisationC> getListCotisation() {
     return this.listCotisation;
   }
   
   public void setListCotisation(List<CotisationC> listCotisation) {
     this.listCotisation = listCotisation;
   }
   
   public ParametreCotisationC getSelected() {
     return this.selected;
   }
   
   public void setSelected(ParametreCotisationC selected) {
     this.selected = selected;
   }
   
   public int getIdPrime() {
     return this.idPrime;
   }
   
   public void setIdPrime(int idPrime) {
     this.idPrime = idPrime;
   }
   
   public boolean isDetailSelected() {
     return this.detailSelected;
   }
   
   public void setDetailSelected(boolean detailSelected) {
     this.detailSelected = detailSelected;
   }
   
   public PrimeIndemniteC getPrime() {
     return this.prime;
   }
   
   public void setPrime(PrimeIndemniteC prime) {
     this.prime = prime;
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
   
   public Base getSelectedOrganisme() {
     return this.selectedOrganisme;
   }
   
   public void setSelectedOrganisme(Base selectedOrganisme) {
     this.selectedOrganisme = selectedOrganisme;
   }
   
   public String getLibelleElement() {
     return this.libelleElement;
   }
   
   public void setLibelleElement(String libelleElement) {
     this.libelleElement = libelleElement;
   }
   
   public List<CotisationDetailC> getListElementPrm() {
     return this.listElementPrm;
   }
   
   public void setListElementPrm(List<CotisationDetailC> listElementPrm) {
     this.listElementPrm = listElementPrm;
   }
   
   public List<CotisationDetailC> getListElementCot() {
     return this.listElementCot;
   }
   
   public void setListElementCot(List<CotisationDetailC> listElementCot) {
     this.listElementCot = listElementCot;
   }
   
   public List<SelectItem> getCotisations() {
     return this.cotisations;
   }
   
   public void setCotisations(List<SelectItem> cotisations) {
     this.cotisations = cotisations;
   }
   
   public int getIdCot() {
     return this.idCot;
   }
   
   public void setIdCot(int idCot) {
     this.idCot = idCot;
   }
   
   public List<ParametreCotisationC> getListeParametre() {
     return this.listeParametre;
   }
   
   public void setListeParametre(List<ParametreCotisationC> listeParametre) {
     this.listeParametre = listeParametre;
   }
   public List<CotisationDetailC> getListCotAdded() {
     return this.listCotAdded;
   }
   
   public void setListCotAdded(List<CotisationDetailC> listCotAdded) {
     this.listCotAdded = listCotAdded;
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
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), 
             Constante.Role.parametrage);
       }
       this.listElementPrm = new ArrayList<CotisationDetailC>();
       this.listElementCot = new ArrayList<CotisationDetailC>();
       
       chargerDetailPrmtr();
       chargerCotisation();
       
       this.disableSelectPrm = true;
       this.selected = null;
     } 
   }
 
 
   
   public void changeValueTauxSalarial() {
     if (getTauxSalarial() > 0.0D)
       setForfaitSalarial(0.0D); 
   }
   
   public void changeValueForfaitSalarial() {
     if (getForfaitSalarial() > 0.0D)
       setTauxSalarial(0.0D); 
   }
   
   public void changeValueTauxPatronal() {
     if (getTauxPatronal() > 0.0D)
       setForfaitPatronal(0.0D); 
   }
   
   public void changeValueForfaitPatronal() {
     if (getForfaitPatronal() > 0.0D)
       setTauxPatronal(0.0D); 
   }
   public void changePlafond() {
     if (getForfaitPatronal() > 0.0D) {
       setPlafonPatronal(0.0D);
     }
     if (getForfaitSalarial() > 0.0D)
       setPlafonSalarial(0.0D); 
   }
   
   public void changePlancher() {
     if (getForfaitPatronal() > 0.0D) {
       setPlancherPatronal(0.0D);
     }
     if (getForfaitSalarial() > 0.0D) {
       setPlancherSalarial(0.0D);
     }
   }
 
   
   private void chargerDetailPrmtr() {
     addBaseAddition();
 
     
    
      
				for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemniteImposable()) {
					
				
				if (this.detail == null)
         this.detail = new CotisationDetailC(); 
       this.detail.setCodeElement(prm.getCode());
       this.detail.setLibelleElement(prm.getDesignation());
       this.detail.setTypeElement("A");
       this.detail.setTypePrm("P");
       this.listElementPrm.add(this.detail);
       this.detail = null; }
 
     
     addBaseRemovable();
 
     
    for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemniteImposable()) {
       if (this.detail == null)
         this.detail = new CotisationDetailC(); 
       this.detail.setCodeElement(prm.getCode());
       this.detail.setLibelleElement(prm.getDesignation());
       this.detail.setTypeElement("M");
       this.detail.setTypePrm("P");
       this.listElementCot.add(this.detail);
       this.detail = null; }
   
   }
 
   
   private void addBaseAddition() {
     if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("SB");
     this.detail.setLibelleElement("SALAIRE DE BASE");
     this.detail.setTypeElement("A");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(1);
     this.listElementPrm.add(this.detail);
     this.detail = null;
     
     if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("HS");
     this.detail.setLibelleElement("HEURES SUPPLEMENTAIRES");
     this.detail.setTypeElement("A");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(2);
     this.listElementPrm.add(this.detail);
     this.detail = null;
     
     if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("LG");
     this.detail.setLibelleElement("LOGEMENT");
     this.detail.setTypeElement("A");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(3);
     this.listElementPrm.add(this.detail);
     this.detail = null;
     
     if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("AL");
     this.detail.setLibelleElement("ALLOCATIONS FAMILIALES");
     this.detail.setTypeElement("A");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(4);
     this.listElementPrm.add(this.detail);
     this.detail = null;
			
			if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("BR");
     this.detail.setLibelleElement("SALAIRES BRUTES");
     this.detail.setTypeElement("A");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(5);
     this.listElementPrm.add(this.detail);
     this.detail = null;
              
   }
 
   
   private void addBaseRemovable() {
     if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("SB");
     this.detail.setLibelleElement("SALAIRE DE BASE");
     this.detail.setTypeElement("M");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(1);
     this.listElementCot.add(this.detail);
     this.detail = null;
     
     if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("HS");
     this.detail.setLibelleElement("HEURES SUPPLEMENTAIRES");
     this.detail.setTypeElement("M");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(2);
     this.listElementCot.add(this.detail);
     this.detail = null;
     
     if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("LG");
     this.detail.setLibelleElement("LOGEMENT");
     this.detail.setTypeElement("M");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(3);
     this.listElementCot.add(this.detail);
     this.detail = null;
     
     if (this.detail == null)
       this.detail = new CotisationDetailC(); 
     this.detail.setCodeElement("AL");
     this.detail.setLibelleElement("ALLOCATIONS FAMILIALES");
     this.detail.setTypeElement("M");
     this.detail.setTypePrm("P");
     this.detail.setTypeBase(4);
     this.listElementCot.add(this.detail);
     this.detail = null;
   }
   
   private void chargerCotisationDetail() {
     CotisationC cot = null;
 
     
     int i = 0;
     
    
      for (ParametreCotisationC pm : FichierBaseDAO.getInstance().getAllParametreCotisation(this.idCot)) {
	

                cot = FichierBaseDAO.getInstance().getCotisation(pm.getIdCotisation());
       if (this.detail == null)
         this.detail = new CotisationDetailC(); 
       this.detail.setCodeElement(cot.getCode());
       this.detail.setLibelleElement(cot.getDesignation());
       this.detail.setTypeElement("M");
       this.detail.setTypePrm("C");
       this.listElementCot.add(i, this.detail);
       this.detail = null;
       i++; 
   }
   }
   
   public void changeCotisation(ValueChangeEvent event) {
     this.idCot = ((Integer)event.getNewValue()).intValue();
     if (this.idCot > 0) {
       
       this.selected = FichierBaseDAO.getInstance().getParameterCotisation(this.idCot, false);
       if (this.selected != null) {
         
         setObject();
       }
       else {
         
         clear(false);
         chargerCotisationDetail();
         setIdCotisation(this.idCot);
       } 
     } 
   }
 
   
   public void searchValue() {
     if (this.idCot > 0) {
       
       this.selected = FichierBaseDAO.getInstance().getParameterCotisation(this.idCot, false);
       if (this.selected != null) {
         
         setObject();
       }
       else {
         
         clear(false);
       } 
     } 
   }
   
   public void chargementParametre() {
     CotisationC cotis = null;
     this.listeParametre = FichierBaseDAO.getInstance().getAllParametreCotisation();
			  int num=0;
     for (ParametreCotisationC cot : this.listeParametre) {
       num++;
       cotis = FichierBaseDAO.getInstance().getCotisation(cot.getIdCotisation());
       cot.setCode(cotis.getCode());
       cot.setLibelle(cotis.getDesignation());
				cot.setPriorite(num);
     } 
   }
 
   
   private void chargerCotisation() {
     this.cotisations = new ArrayList<SelectItem>();
     this.cotisations.add(new SelectItem(Integer.valueOf(0), ""));
     
     for (CotisationC cot : FichierBaseDAO.getInstance().getAllCotisation())
     {
       this.cotisations.add(new SelectItem(Integer.valueOf(cot.getId()), String.valueOf(cot.getCode()) + "||" + cot.getDesignation()));
     }
   }
 
 
   
   public void changeCalculationValue() {
     setForfaitPatronal(0.0D);
     setForfaitSalarial(0.0D);
     setTauxPatronal(0.0D);
     setTauxSalarial(0.0D);
   }
 
   
   public void onSelected(SelectEvent event) {
     this.selected = (ParametreCotisationC)event.getObject();
     setObject();
     PrimeFaces.current().executeScript("PF('dlgCotisation').hide();");
   }
 
 
   
   public void changeElement(ValueChangeEvent event) {
     this.idPrime = ((Integer)event.getNewValue()).intValue();
     this.prime = FichierBaseDAO.getInstance().getPrimeIndemnite(this.idPrime);
     
     if (this.prime != null) {
       this.codeElement = String.valueOf(this.prime.getTypePrime()) + this.prime.getCode();
     } else {
       this.codeElement = "";
     } 
   }
   
   public void changeBaseSal(ValueChangeEvent event) {
     setTypeBaseSalarial(((Integer)event.getNewValue()).intValue());
     switch (getTypeBaseSalarial()) {
       case 0:
       case 1:
         setBaseFixe(0.0D);
         break;
       case 2:
         setForfaitPatronal(0.0D);
         setForfaitSalarial(0.0D);
         break;
     } 
   }
   public void changeBasePatr(ValueChangeEvent event) {
     setTypeBasePatronal(((Integer)event.getNewValue()).intValue());
     switch (getTypeBasePatronal()) {
       case 0:
       case 1:
         setBaseFixe(0.0D);
         break;
       case 2:
         setForfaitPatronal(0.0D);
         setForfaitSalarial(0.0D);
         break;
     } 
   }
   private void clear(boolean b) {
     if (b) {
       
       setId(0);
       this.idCot = 0;
     } 
     setForfaitPatronal(0.0D);
     setForfaitSalarial(0.0D);
     setTauxPatronal(0.0D);
     setTauxSalarial(0.0D);
     setPlancherPatronal(0.0D);
     setPlancherSalarial(0.0D);
     setPlafonPatronal(0.0D);
     setPlafonSalarial(0.0D);
     setTypeBasePatronal(0);
     setTypeBaseSalarial(0);
			  setTauxPlafonPatronal(0);
			  setTauxPlafonSalarial(0);
     setBaseFixe(0.0D);
     this.idPrime = 0;
     this.selected = null;
   }
 
 
 
   
   private void setObject() {
     if (this.selected != null) {
       this.idCot = this.selected.getIdCotisation();
       setId(this.selected.getId());
       setForfaitPatronal(this.selected.getForfaitPatronal());
       setForfaitSalarial(this.selected.getForfaitSalarial());
       setTauxPatronal(this.selected.getTauxPatronal());
       setTauxSalarial(this.selected.getTauxSalarial());
       setIdCotisation(this.selected.getIdCotisation());
       setPlafonPatronal(this.selected.getPlafonPatronal());
       setPlafonSalarial(this.selected.getPlafonSalarial());
       setPlancherPatronal(this.selected.getPlancherPatronal());
       setPlancherSalarial(this.selected.getPlancherSalarial());
       setTypeBasePatronal(this.selected.getTypeBasePatronal());
       setTypeBaseSalarial(this.selected.getTypeBaseSalarial());
       setPlafondBase(this.selected.getPlafondBase());
       setPlancherBase(this.selected.getPlancherBase());
       setBaseFixe(this.selected.getBaseFixe());
                setTauxPlafonPatronal(selected.getTauxPlafonPatronal());
                setTauxPlafonSalarial(selected.getTauxPlafonSalarial());
       chargerCotisationDetail();
       setDetailElementValue();
     } 
   }
 
   
   private void setDetailElementValue() {
     for (CotisationDetailC det : this.listElementPrm) {
       
       this.detail = FichierBaseDAO.getInstance().getCotisationDetail(det.getCodeElement(), this.selected.getId(), "A");
       if (this.detail != null) {
         
         if (this.detail.getTypeElement().equals("A")) {
           
           det.setSeleceted(true);
           det.setDisable(false);
           det.setId(this.detail.getId());
           det.setForfait(this.detail.getForfait());
           det.setTaux(this.detail.getTaux());
           det.setTauxMax(this.detail.getTauxMax());
           det.setIdEntete(this.detail.getIdEntete());
           det.setPlafon(this.detail.getPlafon());
           det.setPlancher(this.detail.getPlancher());
         } 
 
         
         this.detail = null;
       } 
     } 
     
     for (CotisationDetailC det : this.listElementCot) {
       
       this.detail = FichierBaseDAO.getInstance().getCotisationDetail(det.getCodeElement(), this.selected.getId(), "M");
       if (this.detail != null) {
         
         if (this.detail.getTypeElement().equals("M")) {
           
           det.setSeleceted(true);
           det.setDisable(false);
           det.setId(this.detail.getId());
           det.setForfait(this.detail.getForfait());
           det.setTaux(this.detail.getTaux());
           det.setTauxMax(this.detail.getTauxMax());
           det.setIdEntete(this.detail.getIdEntete());
           det.setPlafon(this.detail.getPlafon());
           det.setPlancher(this.detail.getPlancher());
         } 
         
         this.detail = null;
       } 
     } 
   }
 

   public void changePrime(ValueChangeEvent ev) {
     this.idPrime = ((Integer)ev.getNewValue()).intValue();
     if (this.idPrime != 0) {
       setPrime(FichierBaseDAO.getInstance().getPrimeIndemnite(
             this.idPrime));
     }
   }
   
   public void completeDetail() {
     if (this.detail != null) {
       
       this.codeElement = this.detail.getCodeElement();
       this.libelleElement = this.detail.getLibelleElement();
     } 
   }
   public void enregistrer() {
     if (getIdCotisation() == 0) {
       HelperC.afficherMessage("Information", 
           "Préciser la cotisation é parametrer");
     }
     else {
       
       for (CotisationDetailC det : this.listElementPrm) {
         
         if (det.isSeleceted()) {
           getListDetail().add(det); continue;
         } 
         if (det.getId() > 0) {
           getListDeleted().add(det);
         }
       } 
       if (this.listElementCot.size() > 0) {
         for (CotisationDetailC det : this.listElementCot) {
           if (det.isSeleceted()) {
             getListDetail().add(det);
           
           }
           else if (det.getId() > 0) {
             getListDeleted().add(det);
           } 
 
           
           this.priority = addPriority(det);
           this.priority++;
           if (this.priority > 0 && 
             this.priority > getPriorite()) {
             setPriorite(this.priority);
           }
         } 
       } else {
         
         setPriorite(1);
       } 
       if (getId() == 0)
         if (this.droit.isCreer()) {
           if (FichierBaseDAO.getInstance().insertUpdateParametreCotisation(
               this)) {
             clear(true);
             HelperC.afficherMessage("Information", 
                 "Succès de l'opération");
             charger();
           } else {
             HelperC.afficherMessage("Désolé", "Echec de l'opération");
           } 
         } else {
           HelperC.afficherMessage("ATTENTION", 
               "Vous n'avez pas le droit de créer ");
         }  
       if (getId() > 0)
         if (this.droit.isModifier()) {
           if (FichierBaseDAO.getInstance().insertUpdateParametreCotisation(
               this)) {
             clear(true);
             HelperC.afficherMessage("Information", 
                 "Succès de l'opération");
             charger();
           } else {
             HelperC.afficherMessage("Désolé", "Echec de l'opération");
           } 
         } else {
           HelperC.afficherMessage("ATTENTION", 
               "Vous n'avez pas le droit de modifier ");
         }  
     } 
   }
   
   public void supprimer() {
     if (getId() == 0) {
       HelperC.afficherDeleteMessage();
     }
     else if (this.droit.isSupprimer()) {
       if (FichierBaseDAO.getInstance()
         .deleteParametreCotisation(this.selected)) {
         clear(true);
         charger();
         HelperC.afficherMessage("Information", 
             "Succès de l'opération ");
       } else {
         HelperC.afficherMessage("Désolé", "Echec de suppression");
       } 
     } else {
       HelperC.afficherMessage("ATTENTION", 
           "Vous n'avez pas le droit de supprimer ");
     } 
   }

			public void updatePriority(){
				if(listeParametre!=null && listeParametre.size()>0)
					
					if(FichierBaseDAO.getInstance().updateCotisationPriority(listeParametre))
						 HelperC.afficherMessage("Information", 
								           "Succès de l'opération");
					else
						HelperC.afficherAttention("Désolé", "Echec de l'opération");
						
			}

   public void initialiser() {
     clear(true);
     for (CotisationDetailC det : this.listElementCot) {
       det.setSeleceted(false);
       det.setPlafon(0.0D);
       det.setPlancher(0.0D);
       det.setTaux(0.0D);
       det.setForfait(0.0D);
       det.setDisable(true);
     } 
     for (CotisationDetailC det : this.listElementPrm) {
       det.setSeleceted(false);
       det.setPlafon(0.0D);
       det.setPlancher(0.0D);
       det.setTaux(0.0D);
       det.setForfait(0.0D);
       det.setDisable(true);
     } 
     this.detail = null;
   }
 
   
   private int addPriority(CotisationDetailC det) {
     CotisationC cot = null;
     cot = FichierBaseDAO.getInstance().getCotisation(det.getCodeElement());
     if (cot != null) {
       this.priority = FichierBaseDAO.getInstance().getPrioriteCot(cot.getId());
     }
     return this.priority;
   }
 }


