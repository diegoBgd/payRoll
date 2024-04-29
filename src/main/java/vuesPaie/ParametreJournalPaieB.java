 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.CotisationC;
 import classesPaie.DeductionC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageJournalC;
 import classesPaie.ParametrageJournalDetailC;
 import classesPaie.ParametrageJournalElementC;
 import classesPaie.PrimeIndemniteC;
 import java.io.IOException;
 import java.util.ArrayList;
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
 public class ParametreJournalPaieB
   extends ParametrageJournalC
 {
   private static final long serialVersionUID = 6195269262643867390L;
   private ParametrageJournalC selectedParametre;
   private ParametrageJournalDetailC selectedDetail;
   private List<CotisationC> listCotisaton;
   private CotisationC cotisation;
   private List<PrimeIndemniteC> listPrime;
   private PrimeIndemniteC prime;
   private List<DeductionC> listDeduction;
   private DeductionC deduction;
   private List<SelectItem> listElement;
   private List<ParametrageJournalC> listJournal;
   private HttpSession session = HelperC.getSession(); 
			private List<ParametrageJournalElementC> listDetElement; 
			private ParametrageJournalElementC selectedElement; 
			private int itemeId; 
			private int sign; 
			private boolean disableMsg;
			private int typeElmt; 
			private String codeElement; 
			private String titreElement; 
			private String libelleElement; 
			private OperateurC operateur;
			private ExerciceC exercice; 
			private DroitC droit; 
			Base userFonction;
   int index;
   boolean selected;
   
   public CotisationC getCotisation() {
     return this.cotisation;
   }
 
   
   public void setCotisation(CotisationC cotisation) {
     this.cotisation = cotisation;
   }
 
   
   public PrimeIndemniteC getPrime() {
     return this.prime;
   }
 
   
   public void setPrime(PrimeIndemniteC prime) {
     this.prime = prime;
   }
 
   
   public int getTypeElmt() {
     return this.typeElmt;
   }
 
   
   public void setTypeElmt(int typeElmt) {
     this.typeElmt = typeElmt;
   }
 
   
   public int getItemeId() {
     return this.itemeId;
   }
 
   
   public void setItemeId(int itemeId) {
     this.itemeId = itemeId;
   }
 
   
   public ParametrageJournalC getSelectedParametre() {
     return this.selectedParametre;
   }
 
   
   public void setSelectedParametre(ParametrageJournalC selectedParametre) {
     this.selectedParametre = selectedParametre;
   }
 
   
   public ParametrageJournalDetailC getSelectedDetail() {
     return this.selectedDetail;
   }
 
   
   public void setSelectedDetail(ParametrageJournalDetailC selectedDetail) {
     this.selectedDetail = selectedDetail;
   }
 
   
   public DeductionC getDeduction() {
     return this.deduction;
   }
 
   
   public void setDeduction(DeductionC deduction) {
     this.deduction = deduction;
   }
 
   
   public String getCodeElement() {
     return this.codeElement;
   }
 
   
   public void setCodeElement(String codeElement) {
     this.codeElement = codeElement;
   }
 
   
   public String getTitreElement() {
     return this.titreElement;
   }
 
   
   public void setTitreElement(String titreElement) {
     this.titreElement = titreElement;
   }
 
   
   public String getLibelleElement() {
     return this.libelleElement;
   }
 
   
   public void setLibelleElement(String libelleElement) {
     this.libelleElement = libelleElement;
   }
   
   public List<CotisationC> getListCotisaton() {
     return this.listCotisaton;
   }
   
   public void setListCotisaton(List<CotisationC> listCotisaton) {
     this.listCotisaton = listCotisaton;
   }
   
   public List<PrimeIndemniteC> getListPrime() {
     return this.listPrime;
   }
   
   public void setListPrime(List<PrimeIndemniteC> listPrime) {
     this.listPrime = listPrime;
   }
   
   public List<DeductionC> getListDeduction() {
     return this.listDeduction;
   }
   
   public void setListDeduction(List<DeductionC> listDeduction) {
     this.listDeduction = listDeduction;
   }
   
   public List<SelectItem> getListElement() {
     return this.listElement;
   }
   
   public void setListElement(List<SelectItem> listElement) {
     this.listElement = listElement;
   }
   
   public List<ParametrageJournalElementC> getListDetElement() {
     return this.listDetElement;
   }
   
   public void setListDetElement(List<ParametrageJournalElementC> listDetElement) {
     this.listDetElement = listDetElement;
   }
   
   public ParametrageJournalElementC getSelectedElement() {
     return this.selectedElement;
   }
   
   public void setSelectedElement(ParametrageJournalElementC selectedElement) {
     this.selectedElement = selectedElement;
   }
   
   public int getSign() {
     return this.sign;
   }
   
   public void setSign(int sign) {
     this.sign = sign;
   }
   
   public List<ParametrageJournalC> getListJournal() {
     return this.listJournal;
   }
   
   public void setListJournal(List<ParametrageJournalC> listJournal) {
     this.listJournal = listJournal;
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
       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
       }
       this.listElement = new ArrayList<SelectItem>();
       this.listDetElement = new ArrayList<ParametrageJournalElementC>();
     } 
   }
 
 
   
   private void chargementElement() {
     this.listElement.clear();
     switch (this.typeElmt) {
       
       case 1:
         chargementAutreElement();
         break;
       
       case 2:
         chargementPrime();
         break;
       
       case 3:
       case 4:
         chargementCotisation();
         break;
       
       case 5:
         chargementDeduction();
         break;
     } 
   }
 
 
   
   private void chargementCotisation() {
     this.listDetElement.clear();
     this.listCotisaton = FichierBaseDAO.getInstance().getAllCotisation();
     
     if (this.listCotisaton != null && this.listCotisaton.size() > 0)
     {
       
       for (CotisationC cot : this.listCotisaton) {
         
         if (this.selectedElement == null) {
           this.selectedElement = new ParametrageJournalElementC();
         }
         this.selectedElement.setIdElement(cot.getId());
         this.selectedElement.setLibelleElment(cot.getDesignation());
         this.listDetElement.add(this.selectedElement);
         this.selectedElement = null;
       } 
     }
   }
 
 
   
   private void chargementPrime() {
     this.listPrime = FichierBaseDAO.getInstance().getAllPrimeIndemnite();
     this.listDetElement.clear();
     
     if (this.listPrime != null && this.listPrime.size() > 0)
     {
       
       for (PrimeIndemniteC prm : this.listPrime) {
         this.selectedElement = new ParametrageJournalElementC();
         this.selectedElement.setIdElement(prm.getId());
         this.selectedElement.setLibelleElment(prm.getDesignation());
         this.listDetElement.add(this.selectedElement);
         this.selectedElement = null;
       } 
     }
   }
 
 
 
   
   private void chargementDeduction() {
     this.listDetElement.clear();
     this.listDeduction = FichierBaseDAO.getInstance().getAllDeduction();
     if (this.listDeduction != null && this.listDeduction.size() > 0)
     {
       
       for (DeductionC ded : this.listDeduction) {
         if (this.selectedElement == null)
           this.selectedElement = new ParametrageJournalElementC(); 
         this.selectedElement.setIdElement(ded.getId());
         this.selectedElement.setLibelleElment(ded.getDesignation());
         this.listDetElement.add(this.selectedElement);
         this.selectedElement = null;
       } 
     }
   }
 
 
   
   public void searchParametre() {
     if (getCode() != null && !getCode().equals("")) {
       
       this.selectedParametre = FichierBaseDAO.getInstance().getParametrageJournal(getCode());
       setObject();
     } 
   }
   
   private void setObject() {
				disableMsg=true;
     if (this.selectedParametre != null) {
       disableMsg=false;
       setId(this.selectedParametre.getId());
       setCode(this.selectedParametre.getCode());
       setLibelle(this.selectedParametre.getLibelle());
       setListeDetail(this.selectedParametre.getListeDetail());
       setNumIndex();
     } 
   }
 
 
   
   private void chargementAutreElement() {
     this.listElement.add(new SelectItem(Integer.valueOf(1), "Salaire de base"));
     this.listElement.add(new SelectItem(Integer.valueOf(2), "Montant heures supplémentaires"));
     this.listElement.add(new SelectItem(Integer.valueOf(3), "Indemnité  de logement"));
     this.listElement.add(new SelectItem(Integer.valueOf(4), "Allocation familiale"));
     this.listElement.add(new SelectItem(Integer.valueOf(5), "Total primes soumis é cotisation imposable"));
     this.listElement.add(new SelectItem(Integer.valueOf(6), "Base imposable"));
     this.listElement.add(new SelectItem(Integer.valueOf(7), "Total cotisation salariale"));
     this.listElement.add(new SelectItem(Integer.valueOf(8), "Total cotisations employeur"));
     this.listElement.add(new SelectItem(Integer.valueOf(9), "Total prime et indemnité hors cotisations"));
     this.listElement.add(new SelectItem(Integer.valueOf(10), "Total déduction"));
     this.listElement.add(new SelectItem(Integer.valueOf(11), "Total remboursement crédits"));
     this.listElement.add(new SelectItem(Integer.valueOf(12), "Net é payer"));
     this.listElement.add(new SelectItem(Integer.valueOf(13), "Nombre de jours additionnels"));
     this.listElement.add(new SelectItem(Integer.valueOf(14), "Total pour les jours additionnels"));
     this.listElement.add(new SelectItem(Integer.valueOf(15), "Nombre de personnes pris en charge"));
     this.listElement.add(new SelectItem(Integer.valueOf(16), "Nombre d'heures normales"));
     this.listElement.add(new SelectItem(Integer.valueOf(17), "Nombre d'heures supplémentaires"));
     this.listElement.add(new SelectItem(Integer.valueOf(18), "Nombre d'heures non travaillées"));
     this.listElement.add(new SelectItem(Integer.valueOf(19), "Nombre d'heures travaillées"));
     this.listElement.add(new SelectItem(Integer.valueOf(20), "Montant congémaladie"));
     this.listElement.add(new SelectItem(Integer.valueOf(21), "Monatnt congé maternité"));
			  this.listElement.add(new SelectItem(Integer.valueOf(22), "Monatnt comission"));
     this.listElement.add(new SelectItem(Integer.valueOf(23), "Pénalité heures non travaillées"));
     this.listElement.add(new SelectItem(Integer.valueOf(24), "Commentaire bulletin"));
     this.listElement.add(new SelectItem(Integer.valueOf(25), "Colonne vierge"));
     this.listElement.add(new SelectItem(Integer.valueOf(26), "Date entrée fonction"));
     
     this.listDetElement.clear();
     
     for (SelectItem it : this.listElement) {
       
       if (this.selectedElement == null)
         this.selectedElement = new ParametrageJournalElementC(); 
         this.selectedElement.setIdElement(Integer.valueOf(it.getValue().toString()).intValue());
         this.selectedElement.setLibelleElment(it.getLabel().toString());
       
         this.listDetElement.add(this.selectedElement);
         this.selectedElement = null;
     } 
   }
 
   
   public void chargerParametrage() {
     this.listJournal = FichierBaseDAO.getInstance().getListJournalParametre();
   }
   
   public void takeSelectedJournal() {
     if (this.selectedParametre != null) {
       
       setObject();
       PrimeFaces.current().executeScript("PF('dlgJrnl').hide();");
     } 
   }
   
   public void changeType(ValueChangeEvent event) {
     this.typeElmt = ((Integer)event.getNewValue()).intValue();
     chargementElement();
   }
 
 
 
   
   private void searchElement(ParametrageJournalElementC elt) {
     for (ParametrageJournalElementC elmt : this.listDetElement) {
       
       if (elmt.getIdElement() == elt.getIdElement()) {
         
         elmt.setAdded(true);
         elmt.setId(elt.getId());
         elmt.setIdElement(elt.getIdElement());
         elmt.setIdEntete(elt.getIdEntete());
         elmt.setSigne(elt.getSigne());
       } 
     } 
   }
 
 
 
 
   
   public void addDetail() {
     if (this.typeElmt > 0) {
       if (this.selectedDetail == null) {
         this.selectedDetail = new ParametrageJournalDetailC();
       }
       this.selectedDetail.setTitrElement(this.titreElement);
       this.selectedDetail.setTypeElement(this.typeElmt);
       this.selectedDetail.setLibelle(this.libelleElement);
       addElement();
       if (!this.selected) {
         getListeDetail().add(this.selectedDetail);
       } else {
         getListeDetail().remove(this.index);
         getListeDetail().add(this.index, this.selectedDetail);
       } 
       
       setNumIndex();
       clearDetail();
     }
     else {
       
       HelperC.afficherAttention("ATTENTION", "Il faut préciser le type des léléments");
     } 
   }
 
   
   public void addElement() {
     if (this.selectedDetail != null) {
       
       int index = 0;
 
       
       for (ParametrageJournalElementC elmt : this.listDetElement) {
         
         if (elmt.isAdded()) {
           
           ParametrageJournalElementC selectedElt = new ParametrageJournalElementC();
           selectedElt.setId(elmt.getId());
           selectedElt.setAdded(true);
           selectedElt.setIdElement(elmt.getIdElement());
           selectedElt.setSigne(elmt.getSigne());
           selectedElt.setIdEntete(elmt.getIdEntete());
           selectedElt.setLibelleElment(elmt.getLibelleElment());
           if (!checkElement(selectedElt)) {
             this.selectedDetail.getListDetailElement().add(selectedElt);
             continue;
           } 
           index = checkElementIndex(elmt);
           
           this.selectedDetail.getListDetailElement().remove(index);
           this.selectedDetail.getListDetailElement().add(selectedElt);
 
           
           continue;
         } 
         
         if (elmt.getId() > 0) {
           this.selectedDetail.getLiteDeletedElement().add(elmt);
         }
       } 
       clearElements();
     
     }
     else {
       
       HelperC.afficherAttention("ATTENTION", "Il faut préciser le titre d'une colonne");
     } 
   }
 
   
   private boolean checkElement(ParametrageJournalElementC elt) {
     boolean bl = false;
     for (ParametrageJournalElementC e : this.selectedDetail.getListDetailElement()) {
       if (e.getIdElement() == elt.getIdElement()) {
         
         bl = true;
         break;
       } 
     } 
     return bl;
   }
   
   private int checkElementIndex(ParametrageJournalElementC elt) {
     int index = 0;
     for (ParametrageJournalElementC e : this.selectedDetail.getListDetailElement()) {
       if (e.getIdElement() == elt.getIdElement()) {
         
         index = this.selectedDetail.getListDetailElement().indexOf(e);
         break;
       } 
     } 
     return index;
   }
 
   
   private void clearElements() {
     for (ParametrageJournalElementC elmt : this.listDetElement) {
       
       elmt.setAdded(false);
       elmt.setSigne(0);
     } 
   }
   
   public void removeDetail() {
     if (this.selectedDetail != null) {
       
       if (this.selectedDetail.getId() > 0)
       {
         getListDeleted().add(this.selectedDetail);
       }
       getListeDetail().remove(this.index);
       setNumIndex();
       clearDetail();
       clearElements();
     } 
   }
 
   
   private void setNumIndex() {
     int i = 0;
     if (getListeDetail().size() > 0)
     {
       
       for (ParametrageJournalDetailC det : getListeDetail()) {
 
         
         i++;
         det.setIndexNum(i);
       } 
     }
   }
 

   
   private void clearDetail() {
     this.selectedDetail = null;
     this.titreElement = "";
     this.codeElement = "";
     this.typeElmt = 0;
     this.libelleElement = "";
     this.typeElmt = 0;
     this.itemeId = 0;
     this.selected = false;
     this.index = 0;
     this.listDetElement.clear();
			  disableMsg=true;
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.selectedDetail = (ParametrageJournalDetailC)event.getObject();
     if (this.selectedDetail != null) {
       
       this.titreElement = this.selectedDetail.getTitrElement();
       this.typeElmt = this.selectedDetail.getTypeElement();
       this.selected = true;
       if (this.typeElmt > 0) {
         
         chargementElement();
         
         for (ParametrageJournalElementC elt : this.selectedDetail.getListDetailElement())
         {
           searchElement(elt);
         }
       } 
       
       this.index = getListeDetail().indexOf(this.selectedDetail);
     } 
   }
 
   
   public void save() {
     if (getId() == 0 && getListeDetail().size() > 0 && !this.droit.isCreer()) {
       
       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de créer le bulletin ");
       return;
     } 
     if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier le bulletin ");
       return;
     } 
              if(getCode()!=null && !getCode().equals(""))
     if (FichierBaseDAO.getInstance().insertUpdateParametrageJournal(this)) {
       
       HelperC.afficherMessage("Information", "Succès de l'opération ");
       clearDetail();
       initialize();
     } else {
       
       HelperC.afficherMessage("Désolé", "Echec de l'opération ");
     } 
   }
 
   
   public void initialize() {
     setCode("");
     setId(0);
     setLibelle("");
     setTypeElmt(0);
     getListeDetail().clear();
   }
 
   
   public void supprimer() {
     if (getId() != 0) {

       FichierBaseDAO.getInstance().deleteParametrageJournal(this);
       clearDetail();
       initialize();
 
     
     }
     else {
    
       HelperC.afficherDeleteMessage();
     } 
   }
 }


