/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
import classesPaie.CategoriePersonnelC;
import classesPaie.ConditionPositionC;
import classesPaie.Constante;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
import classesPaie.ParametragePositionC;
import classesPaie.ParametragePositionDetailC;
import classesPaie.ParametragePrimeC;
import classesPaie.PrimeIndemniteC;
import classesPaie.Tables;
import classesPaie.Tables.TableName;

/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;

import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametragePositionB
/*     */   extends ParametragePositionC
/*     */ {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2390725938584318293L;
/*     */  

/*     */   private int dureeDetail,idPrime;
/*     */   private int anciennetteMin;
/*     */   private int anciennetteMax;
/*  40 */   private int index = 1;
            
/*     */   private ParametragePositionC selected;
/*  49 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   private ParametragePositionDetailC selectedPrime;
/*     */   private List<ParametragePositionC> listParametrage;
/*     */   private List<SelectItem> listCondition;
			private List<SelectItem> listPersonnel,listCateg;
			private List<SelectItem> listPrime;
/*     */   private OperateurC operateur; 
            private ExerciceC exercice; 
			ConditionPositionC condition;
/*     */   PrimeIndemniteC prime;
            List<ParametragePrimeC> listParmPrim;
            int idParam;
/*     */   public List<SelectItem> getListCondition() {
/*  74 */     return this.listCondition;
/*     */   }
/*     */   
/*     */   public void setListCondition(List<SelectItem> listCondition) {
/*  78 */     this.listCondition = listCondition;
/*     */   }
/*     */   
/*     */   public OperateurC getOperateur() {
/*  82 */     return this.operateur;
/*     */   }
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  86 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/*  90 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  94 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/*  98 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 102 */     this.session = session;
/*     */   }
 
/*     */   public int getAnciennetteMin() {
/* 171 */     return this.anciennetteMin;
/*     */   }
/*     */   
/*     */   public void setAnciennetteMin(int anciennetteMin) {
/* 175 */     this.anciennetteMin = anciennetteMin;
/*     */   }
/*     */   
/*     */   public int getAnciennetteMax() {
/* 179 */     return this.anciennetteMax;
/*     */   }
/*     */   
/*     */   public void setAnciennetteMax(int anciennetteMax) {
/* 183 */     this.anciennetteMax = anciennetteMax;
/*     */   }
/*     */   
/*     */   public int getDureeDetail() {
/* 187 */     return this.dureeDetail;
/*     */   }
/*     */   
/*     */   public void setDureeDetail(int dureeDetail) {
/* 191 */     this.dureeDetail = dureeDetail;
/*     */   }
/*     */   
/*     */   public int getIndex() {
/* 195 */     return this.index;
/*     */   }
/*     */   
/*     */   public void setIndex(int index) {
/* 199 */     this.index = index;
/*     */   }
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
			
	
			
/*     */   @PostConstruct
/*     */   private void init() {
/* 204 */     this.operateur = new OperateurC();
/* 205 */     this.exercice = new ExerciceC();
/*     */     
/* 207 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 208 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 209 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 210 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/* 212 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/* 215 */         FacesContext context = FacesContext.getCurrentInstance();
/* 216 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 217 */       } catch (IOException e) {
/*     */         
/* 219 */         e.printStackTrace();
/*     */       }
/*     */     
/*     */     } else {
/*     */       listParmPrim=new ArrayList<ParametragePrimeC>();
/* 224 */       this.listCondition = new ArrayList<SelectItem>();

/* 225 */       listCateg=new ArrayList<SelectItem>();
				listCateg.add(new SelectItem(0,""));
				
				chargerPrime();
				chargerPersonnel();
/*     */     } 
/*     */   }


/*     */   public void chargement() {
/* 233 */     this.listParametrage = FichierBaseDAO.getInstance().getListParametrePosition();
				for (ParametragePositionC prmPs : listParametrage) 
				{
					prmPs.setLibellePersonnel(FichierBaseDAO.getInstance().getBaseById(prmPs.getIdPersonnel(), Tables.getTableName(TableName.personnel)).getDesignation());
					prmPs.setLibelleCondition(FichierBaseDAO.getInstance().getConditionPosition(prmPs.getIdCondition()).getLibellePosition());
				}
/*     */   }
/*     */   private void setObject() {
/* 236 */     if (this.selected != null) {
/* 237 */       setId(this.selected.getId());
/* 238 */       setAjoutAllocationFamiliale(this.selected.isAjoutAllocationFamiliale());
/* 239 */       setAjoutAllocationLogement(this.selected.isAjoutAllocationLogement());
/* 240 */       setAvancementTraitement(this.selected.isAvancementTraitement());
/* 242 */       setAvancementGrade(selected.isAvancementGrade());
/* 244 */       setIdPersonnel(selected.getIdPersonnel());
				chargerCategorie();
				setIdCategorie(selected.getIdCategorie());				
                setIdCondition(selected.getIdCondition());
                condition=FichierBaseDAO.getInstance().getConditionPosition(selected.getIdCondition());
                setIdposition(Constante.getPosition(condition.getPosition()));
                chargerCondition();
/* 245 */       setDureePosition(this.selected.getDureePosition());
/* 246 */       setListDetailPrime(selected.getListDetailPrime());

/* 252 */      for (ParametragePositionDetailC det : selected.getListDetailPrime()) {
					prime=FichierBaseDAO.getInstance().getPrimeIndemnite(det.getIdPrime());
					if(prime!=null)
					det.setLibellePrime(prime.getDesignation());
				}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear() {
/* 259 */       setId(0);
/* 238 */       setAjoutAllocationFamiliale(false);
/* 239 */       setAjoutAllocationLogement(false);
/* 240 */       setAvancementTraitement(false);
/* 242 */       setAvancementGrade(false);
/* 244 */       setIdPersonnel(0);
                setIdCondition(0);
/* 245 */       setDureePosition(0);
/* 246 */       getListDetailPrime().clear(); 
/*     */   }
/*     */ 
/*     */   private void chargerPrime(){
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
/*     */   public void onRowSelect(SelectEvent event) {
/* 278 */     this.selected = (ParametragePositionC)event.getObject();
/* 279 */     if (this.selected != null)
/* 280 */       setObject(); 
/* 281 */     
			PrimeFaces.current().executeScript("PF('rechercheDialog').hide();");
/*     */   }
/*     */ 
/*     */  public void onPrmRowSelect(SelectEvent event)
			{
				selectedPrime=(ParametragePositionDetailC)event.getObject();
				if(selectedPrime!=null)
					idPrime=selectedPrime.getIdPrime();
			}
/*     */   
/*     */   public void initialiser() {
/* 292 */     clear();
/*     */   }
/*     */   
/*     */   public void changePosition(ValueChangeEvent event) {
/* 296 */    setIdposition(((Integer)event.getNewValue()).intValue());
/* 297 */    chargerCondition();
/* 299 */    
/*     */   }
/*     */   
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
/*     */   private void chargerCondition() {
		     this.listCondition.clear();
             this.listCondition.add(new SelectItem(Integer.valueOf(0), ""));
/* 306 */     for (ConditionPositionC condi : FichierBaseDAO.getInstance().getListeConditionPosition(getIdposition()))
/* 307 */       this.listCondition.add(new SelectItem(Integer.valueOf(condi.getId()), condi.getCondition())); 
/*     */   }
/*     */   
/*     */  
/*     */ 
/*     */   
/*     */   public void changeCondition(ValueChangeEvent event) {
/* 320 */     if (event.getNewValue() != null) {
/*     */       
/* 322 */       setIdCondition(((Integer)event.getNewValue()).intValue());

/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ajouterDetailPrime(){
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
/*     */   
/*     */   private void clearDetail() {
/* 366 */     selectedPrime = null;
/* 367 */     idPrime = 0;
/* 368 */    
/*     */   }

/*     */   public void save() {
/* 430 */     if (getIdCondition() == 0) {
/* 431 */       HelperC.afficherMessage("Information", "Veillez saisir la position!");
/* 432 */     } 
/* 436 */     else if (FichierBaseDAO.getInstance().insertUpdateParametragePosition(this)) {
/* 437 */       HelperC.afficherMessage("Information", "Succ�s d'enregistrement");
/* 438 */       chargement();
/* 439 */       clear();
/*     */     } else {
/* 441 */       HelperC.afficherMessage("information", "Echec d'enregistrement");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 450 */       if (getId() == 0) {
/*     */         
/* 452 */         HelperC.afficherDeleteMessage();
/*     */       
/*     */       }
/* 455 */       else if (FichierBaseDAO.getInstance().deleteParametragePosition(selected)) {
/*     */         
/* 457 */         HelperC.afficherMessage("F�licitation", "Succ�s de l'Op�ration");
/* 458 */         clear();
/* 459 */         chargement();
/*     */       } else {
/*     */         
/* 462 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 467 */     catch (Exception e) {
/* 468 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\ParametrageDureeEtTraitementPositionB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */