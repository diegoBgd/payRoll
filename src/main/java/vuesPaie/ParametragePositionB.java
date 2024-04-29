 package vuesPaie;
 
 import classesPaie.Base;
import classesPaie.CategoriePersonnelC;
import classesPaie.ConditionPositionC;
import classesPaie.Constante;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
import classesPaie.ParametragePositionC;
import classesPaie.ParametragePositionDetailC;
import classesPaie.ParametragePrimeC;
import classesPaie.PrimeIndemniteC;
import classesPaie.Tables;
import classesPaie.Tables.TableName;

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
 public class ParametragePositionB
   extends ParametragePositionC
 {
		
		private static final long serialVersionUID = 2390725938584318293L;
  

   private int dureeDetail,idPrime;
   private int anciennetteMin;
   private int anciennetteMax;
   private int index = 1;
            
   private ParametragePositionC selected;
   private HttpSession session = HelperC.getSession();
   
   private ParametragePositionDetailC selectedPrime;
   private List<ParametragePositionC> listParametrage;
   private List<SelectItem> listCondition;
			private List<SelectItem> listPersonnel,listCateg;
			private List<SelectItem> listPrime;
   private OperateurC operateur; 
            private ExerciceC exercice; 
			ConditionPositionC condition;
   PrimeIndemniteC prime;
            List<ParametragePrimeC> listParmPrim;
            int idParam;
   public List<SelectItem> getListCondition() {
     return this.listCondition;
   }
   
   public void setListCondition(List<SelectItem> listCondition) {
     this.listCondition = listCondition;
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
 
   public int getAnciennetteMin() {
     return this.anciennetteMin;
   }
   
   public void setAnciennetteMin(int anciennetteMin) {
     this.anciennetteMin = anciennetteMin;
   }
   
   public int getAnciennetteMax() {
     return this.anciennetteMax;
   }
   
   public void setAnciennetteMax(int anciennetteMax) {
     this.anciennetteMax = anciennetteMax;
   }
   
   public int getDureeDetail() {
     return this.dureeDetail;
   }
   
   public void setDureeDetail(int dureeDetail) {
     this.dureeDetail = dureeDetail;
   }
   
   public int getIndex() {
     return this.index;
   }
   
   public void setIndex(int index) {
     this.index = index;
   }
			public ParametragePositionC getSelected() {
				return selected;
			}
			public void setSelected(ParametragePositionC selected) {
				this.selected = selected;
			}
			
			public ParametragePositionDetailC getSelectedPrime() {
				return selectedPrime;
			}
			public void setSelectedPrime(ParametragePositionDetailC selectedPrime) {
				this.selectedPrime = selectedPrime;
			}
			public List<ParametragePositionC> getListParametrage() {
				return listParametrage;
			}
			public void setListParametrage(List<ParametragePositionC> listParametrage) {
				this.listParametrage = listParametrage;
			}
			public List<SelectItem> getListPersonnel() {
				return listPersonnel;
			}
			public void setListPersonnel(List<SelectItem> listPersonnel) {
				this.listPersonnel = listPersonnel;
			}

			public int getIdPrime() {
				return idPrime;
			}
			public void setIdPrime(int idPrime) {
				this.idPrime = idPrime;
			}
			
			public List<SelectItem> getListPrime() {
				return listPrime;
			}
			public void setListPrime(List<SelectItem> listPrime) {
				this.listPrime = listPrime;
			}
			
			public List<SelectItem> getListCateg() {
				return listCateg;
			}
			public void setListCateg(List<SelectItem> listCateg) {
				this.listCateg = listCateg;
			}
			
	
			
   @PostConstruct
   private void init() {
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
       listParmPrim=new ArrayList<ParametragePrimeC>();
       this.listCondition = new ArrayList<SelectItem>();

       listCateg=new ArrayList<SelectItem>();
				listCateg.add(new SelectItem(0,""));
				
				chargerPrime();
				chargerPersonnel();
     } 
   }


   public void chargement() {
     this.listParametrage = FichierBaseDAO.getInstance().getListParametrePosition();
				for (ParametragePositionC prmPs : listParametrage) 
				{
					prmPs.setLibellePersonnel(FichierBaseDAO.getInstance().getBaseById(prmPs.getIdPersonnel(), Tables.getTableName(TableName.personnel)).getDesignation());
					prmPs.setLibelleCondition(FichierBaseDAO.getInstance().getConditionPosition(prmPs.getIdCondition()).getLibellePosition());
				}
   }
   private void setObject() {
     if (this.selected != null) {
       setId(this.selected.getId());
       setAjoutAllocationFamiliale(this.selected.isAjoutAllocationFamiliale());
       setAjoutAllocationLogement(this.selected.isAjoutAllocationLogement());
       setAvancementTraitement(this.selected.isAvancementTraitement());
       setAvancementGrade(selected.isAvancementGrade());
       setIdPersonnel(selected.getIdPersonnel());
				chargerCategorie();
				setIdCategorie(selected.getIdCategorie());				
                setIdCondition(selected.getIdCondition());
                condition=FichierBaseDAO.getInstance().getConditionPosition(selected.getIdCondition());
                setIdposition(Constante.getPosition(condition.getPosition()));
                chargerCondition();
       setDureePosition(this.selected.getDureePosition());
       setListDetailPrime(selected.getListDetailPrime());

      for (ParametragePositionDetailC det : selected.getListDetailPrime()) {
					prime=FichierBaseDAO.getInstance().getPrimeIndemnite(det.getIdPrime());
					if(prime!=null)
					det.setLibellePrime(prime.getDesignation());
				}
     } 
   }
 
   
   private void clear() {
       setId(0);
       setAjoutAllocationFamiliale(false);
       setAjoutAllocationLogement(false);
       setAvancementTraitement(false);
       setAvancementGrade(false);
       setIdPersonnel(0);
                setIdCondition(0);
       setDureePosition(0);
       getListDetailPrime().clear(); 
   }
 
   private void chargerPrime(){
				listPrime=new ArrayList<SelectItem>();
				listPrime.add(new SelectItem(0,""));
				for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemnite()) {
					listPrime.add(new SelectItem(prm.getId(),prm.getCode()+"-"+prm.getDesignation()));
				}
			}
			
			private void chargerPersonnel(){
				listPersonnel=new ArrayList<SelectItem>();
				listPersonnel.add(new SelectItem(0,""));
				for (Base b : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.personnel))) {
					listPersonnel.add(new SelectItem(b.getId(),b.getCode()+"-"+b.getDesignation()));
				}
			}
			
			private void chargerCategorie(){
				listCateg.clear();
				listCateg.add(new SelectItem(0,""));
				for (CategoriePersonnelC cat : FichierBaseDAO.getInstance().getListCategoriePersonnelParIdPersonnel(getIdPersonnel())) {
					listCateg.add(new SelectItem(cat.getId(),cat.getCode()+"-"+cat.getDesignation()));
				}
			}
   public void onRowSelect(SelectEvent event) {
     this.selected = (ParametragePositionC)event.getObject();
     if (this.selected != null)
       setObject(); 
     
			PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
   }
 
  public void onPrmRowSelect(SelectEvent event)
			{
				selectedPrime=(ParametragePositionDetailC)event.getObject();
				if(selectedPrime!=null)
					idPrime=selectedPrime.getIdPrime();
			}
   
   public void initialiser() {
     clear();
   }
   
   public void changePosition(ValueChangeEvent event) {
    setIdposition(((Integer)event.getNewValue()).intValue());
    chargerCondition();
    
   }
   
			public void changerIdPrime(ValueChangeEvent event){
				idPrime=((Integer)event.getNewValue()).intValue();
				prime=FichierBaseDAO.getInstance().getPrimeIndemnite(idPrime);
				
			}
			
			public void changerIdCategorie(ValueChangeEvent event){
				setIdCategorie(((Integer)event.getNewValue()).intValue());
				afficherPrime();
				
			}
			
			public void changePersonnel(ValueChangeEvent event){
				getListDetailPrime().clear();
				setIdPersonnel(((Integer)event.getNewValue()).intValue());
				chargerCategorie();		
				afficherPrime();
			}
			private void afficherPrime() {
				List<ParametragePrimeC>listParmetrage1=FichierBaseDAO.getInstance().getDistictParametragePrime (0, 0, 0, 0);
				List<ParametragePrimeC>listParmetrage3=FichierBaseDAO.getInstance().getDistictParametragePrime (getIdPersonnel(),0, 0, 0);
				List<ParametragePrimeC>listParmetrage2=FichierBaseDAO.getInstance().getDistictParametragePrime (getIdPersonnel(),getIdCategorie(), 0, 0);
				
				listParmPrim.clear();
				listParmPrim.addAll(listParmetrage1);
				listParmPrim.addAll(listParmetrage2);
				listParmPrim.addAll(listParmetrage3);
				getListDetailPrime().clear();
						
				for (ParametragePrimeC prm : listParmPrim) 
				{
					prime=FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
					idPrime=prime.getId();
					idParam=prm.getId();
					ajouterDetailPrime();
				}
			}
   private void chargerCondition() {
		     this.listCondition.clear();
             this.listCondition.add(new SelectItem(Integer.valueOf(0), ""));
     for (ConditionPositionC condi : FichierBaseDAO.getInstance().getListeConditionPosition(getIdposition()))
       this.listCondition.add(new SelectItem(Integer.valueOf(condi.getId()), condi.getCondition())); 
   }
   
  
 
   
   public void changeCondition(ValueChangeEvent event) {
     if (event.getNewValue() != null) {
       
       setIdCondition(((Integer)event.getNewValue()).intValue());

     } 
   }
 
   
   public void ajouterDetailPrime(){
	              int num=0;
				if(selectedPrime==null)
					selectedPrime=new ParametragePositionDetailC();
				selectedPrime.setIdPrime(idPrime);
				selectedPrime.setLibellePrime(prime.getDesignation());
				selectedPrime.setIndex(num);
				selectedPrime.setIdParm(idParam);
				if(!isExistPrime(idPrime))
				{
				num++;
				getListDetailPrime().add(selectedPrime);
			
				}
				clearDetail();
			}

			private boolean isExistPrime(int idPrm) {
				boolean b=false;
				for (ParametragePositionDetailC det : getListDetailPrime()) {
					if(det.getIdPrime()==idPrm)
					{
						b=true;
						break;
					}
				}
				return b;
			}
			public void removeDetailPrime(){
			 	if(selectedPrime!=null)
			 	{
			 		if(selectedPrime.getId()>0)
			 			getListRemoved().add(selectedPrime);
			 		getListDetailPrime().remove(selectedPrime);
			 		clearDetail();
			 	}
			}
   
   private void clearDetail() {
     selectedPrime = null;
     idPrime = 0;
    
   }

   public void save() {
     if (getIdCondition() == 0) {
       HelperC.afficherMessage("Information", "Veillez saisir la position!");
     } 
     else if (FichierBaseDAO.getInstance().insertUpdateParametragePosition(this)) {
       HelperC.afficherMessage("Information", "Succès d'enregistrement");
       chargement();
       clear();
     } else {
       HelperC.afficherMessage("information", "Echec d'enregistrement");
     } 
   }
 
 
 
   
   public void supprimer() {
     try {
       if (getId() == 0) {
         
         HelperC.afficherDeleteMessage();
       
       }
       else if (FichierBaseDAO.getInstance().deleteParametragePosition(selected)) {
         
         HelperC.afficherMessage("Félicitation", "Succès de l'Opération");
         clear();
         chargement();
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'Opération");
       
       }
     
     }
     catch (Exception e) {
       System.out.println(e.getMessage());
     } 
   }
 }


