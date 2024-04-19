/*    */ package vuesPaie;
/*    */ 
/*    */ import classesPaie.Base;
import classesPaie.CategoriePersonnelC;
import classesPaie.DetailPrimeEmployeC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.GradePersonnelC;
/*    */ import classesPaie.HelperC;
/*    */ import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
/*    */ import classesPaie.ParametragePrimeC;
import classesPaie.PrimeIndemniteC;
import classesPaie.Tables;
import classesPaie.Tables.TableName;

/*    */ import java.io.IOException;
/*    */ import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
/*    */ import java.util.List;
import java.util.Random;

/*    */ import javax.annotation.PostConstruct;
/*    */ import javax.faces.bean.ManagedBean;
/*    */ import javax.faces.bean.ViewScoped;
/*    */ import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
/*    */ import javax.servlet.http.HttpSession;

import persistencePaie.FactoryDAO;
/*    */ import persistencePaie.FichierBaseDAO;
/*    */ import persistencePaie.UtilitaireDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ManagedBean
/*    */ @ViewScoped
/*    */ public class UtilitaireVue
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3942587143941760472L;
/* 28 */   private HttpSession session = HelperC.getSession();
/*    */   private EmployeC selectedEmploye;
/*    */   private OperateurC operateur;
/*    */   private List<SelectItem> listPersonnel,listCateg,listGrd,listFoct;
/*    */   private ExerciceC exercice;
/*    */   private int idPrsnl,idCateg,idGrd,idFonct;
/*    */   private String infoMsg,codeLine,matricule,mail,nom;
/*    */   List<EmployeC>listEmploye;
/*    */   private List<ParametragePrimeC> listParmPrim;
/*    */   private boolean sendMail;
/*    */   ParametrageGeneralC parm;

/*    */   public String getInfoMsg() {
/* 40 */     return this.infoMsg;
/*    */   }
/*    */   
/*    */   public void setInfoMsg(String infoMsg) {
/* 44 */     this.infoMsg = infoMsg;
/*    */   }
			public int getIdCateg() {
				return idCateg;
			}
			public void setIdCateg(int idCateg) {
				this.idCateg = idCateg;
			}
			public int getIdGrd() {
				return idGrd;
			}
			public void setIdGrd(int idGrd) {
				this.idGrd = idGrd;
			}
			public int getIdFonct() {
				return idFonct;
			}
			public void setIdFonct(int idFonct) {
				this.idFonct = idFonct;
			}
			public int getIdPrsnl() {
				return idPrsnl;
			}
			public void setIdPrsnl(int idPrsnl) {
				this.idPrsnl = idPrsnl;
			}
			public List<SelectItem> getListPersonnel() {
				return listPersonnel;
			}
			public void setListPersonnel(List<SelectItem> listPersonnel) {
				this.listPersonnel = listPersonnel;
			}
			public List<ParametragePrimeC> getListParmPrim() {
				return listParmPrim;
			}
			public void setListParmPrim(List<ParametragePrimeC> listParmPrim) {
				this.listParmPrim = listParmPrim;
			}
			
			public List<SelectItem> getListCateg() {
				return listCateg;
			}
			public void setListCateg(List<SelectItem> listCateg) {
				this.listCateg = listCateg;
			}
			public List<SelectItem> getListGrd() {
				return listGrd;
			}
			public void setListGrd(List<SelectItem> listGrd) {
				this.listGrd = listGrd;
			}
			public List<SelectItem> getListFoct() {
				return listFoct;
			}
			public void setListFoct(List<SelectItem> listFoct) {
				this.listFoct = listFoct;
			}
			
			public String getCodeLine() {
				return codeLine;
			}
			public void setCodeLine(String codeLine) {
				this.codeLine = codeLine;
			}
			
			public EmployeC getSelectedEmploye() {
				return selectedEmploye;
			}
			public void setSelectedEmploye(EmployeC selectedEmploye) {
				this.selectedEmploye = selectedEmploye;
			}
			public String getMatricule() {
				return matricule;
			}
			public void setMatricule(String matricule) {
				this.matricule = matricule;
			}
			
			public boolean isSendMail() {
				return sendMail;
			}
			public void setSendMail(boolean sendMail) {
				this.sendMail = sendMail;
			}
			
			public String getMail() {
				return mail;
			}
			public void setMail(String mail) {
				this.mail = mail;
			}
			
			public String getNom() {
				return nom;
			}
			public void setNom(String nom) {
				this.nom = nom;
			}
/*    */   @PostConstruct
/*    */   public void init() {
/* 50 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 51 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 52 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 53 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*    */     
/* 55 */     if (this.operateur == null || this.exercice == null) {
/*    */       
/*    */       try {
/*    */         
/* 59 */         FacesContext context = FacesContext.getCurrentInstance();
/* 60 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*    */       }
/* 62 */       catch (IOException e) {
/*    */         
/* 64 */         e.printStackTrace();
/*    */       } 
/*    */     }
			else{
				parm=FichierBaseDAO.getInstance().getParametrageGeneral();
				chargementPersonnel();
				chargerFonction();
			}
/*    */   }
/*    */ 
/*    */   
public void chargerParmetre(){
	 			listParmPrim = FichierBaseDAO.getInstance().getListParametragePrime(0, 0, 0, 0);
	 			PrimeIndemniteC prim;
	 			if(listParmPrim.size()>0)
	 			{
	 				for (ParametragePrimeC prm : listParmPrim) {
	 					prim=FichierBaseDAO.getInstance().getPrimeIndemnite(prm.getIdPrime());
	 					prm.setLibellePrime(prim.getDesignation());
					}
	 			}
			}
private void chargementPersonnel() {
	listPersonnel= new ArrayList<SelectItem>();
	listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));

    
    for (Base prs :FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.personnel)))
    {
    	listPersonnel.add(new SelectItem(Integer.valueOf(prs.getId()), prs.getDesignation()));
    }
   }

  public void changePersonnel(ValueChangeEvent event) {
	  idPrsnl = ((Integer)event.getNewValue()).intValue();
	  chargementCategorie();
	 
   }
  
  private void searchEmploye(){
	  listEmploye=FactoryDAO.getInstance().getListEmploye(idPrsnl, 0, 0, 0, 0, 0, 0, 0,0, 1, 0);
  }
  
  private void chargementCategorie(){
	  listCateg= new ArrayList<SelectItem>();
	  listCateg.add(new SelectItem(Integer.valueOf(0), ""));
	  List<CategoriePersonnelC> listCat=FichierBaseDAO.getInstance().getListCategoriePersonnelParIdPersonnel(idPrsnl);
	
	    for (CategoriePersonnelC cat : listCat)
	    {
	    	listCateg.add(new SelectItem(Integer.valueOf(cat.getId()), cat.getDesignation()));
	    }
  }
  
  
  public void changeCategorie(ValueChangeEvent event) {
	  idCateg = ((Integer)event.getNewValue()).intValue();
	  chargementGrade();
   }
  
  private void chargementGrade(){
	  listGrd= new ArrayList<SelectItem>();
	  listGrd.add(new SelectItem(Integer.valueOf(0), ""));
	    List<GradePersonnelC> listGrad=FichierBaseDAO.getInstance().getGradesPersonnelParIdCategorie(idCateg);
	    for (GradePersonnelC grd : listGrad)
	    {
	    	listGrd.add(new SelectItem(Integer.valueOf(grd.getId()), grd.getDesignation()));
	    }
  }
  public void changeGrade(ValueChangeEvent event) {
	  idGrd = ((Integer)event.getNewValue()).intValue();
	  
   } 
  
  private void chargerFonction(){
	  listFoct=new ArrayList<SelectItem>();
	  listFoct.add(new SelectItem(Integer.valueOf(0), ""));
	  for (Base fx : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.fonction))) {
		  listFoct.add(new SelectItem(Integer.valueOf(fx.getId()), fx.getDesignation()));
	}
  }
  public void changeFonction(ValueChangeEvent event) {
	  idFonct = ((Integer)event.getNewValue()).intValue();
	  
   }
   public void updateFicheEmploye() {
    searchEmploye();
    DetailPrimeEmployeC detPrm=null;
    PrimeIndemniteC prime=null;
    List<ParametragePrimeC>lisParmPFx,listPrmP,listAllPrm,listParmPCt,listParmPGd,listParmPCGd,listParmPGF,listParmPCF,listParmAll;
     this.infoMsg = "";
     boolean ok=false;

				for (EmployeC empl : listEmploye) {
				
					lisParmPFx=FichierBaseDAO.getInstance().getListParametragePrime(empl.getIdPersnl(), 0, 0, empl.getIdFnctn());
					listPrmP=FichierBaseDAO.getInstance().getListParametragePrime(empl.getIdPersnl(), 0, 0, 0);
					listAllPrm=FichierBaseDAO.getInstance().getListParametragePrime(empl.getIdPersnl(), empl.getIdCatgrie(), empl.getIdGrd(), empl.getIdFnctn());
					listParmPCt=FichierBaseDAO.getInstance().getListParametragePrime(empl.getIdPersnl(),empl.getIdCatgrie(), 0, 0);
					listParmPGd=FichierBaseDAO.getInstance().getListParametragePrime(empl.getIdPersnl(), 0,empl.getIdGrd(), 0);
					listParmPCGd=FichierBaseDAO.getInstance().getListParametragePrime(empl.getIdPersnl(), empl.getIdCatgrie(), empl.getIdGrd(), 0);
					listParmPGF=FichierBaseDAO.getInstance().getListParametragePrime(empl.getIdPersnl(), 0,empl.getIdGrd(),empl.getIdFnctn());
					listParmPCF=FichierBaseDAO.getInstance().getListParametragePrime(empl.getIdPersnl(), empl.getIdCatgrie(), 0, empl.getIdFnctn());
					listParmAll=FichierBaseDAO.getInstance().getListParametragePrime(0, 0, 0, 0);
					
					listParmPrim=new ArrayList<ParametragePrimeC>();
					if(lisParmPFx.size()>0)
					 listParmPrim.addAll(lisParmPFx);
					
					if(listPrmP.size()>0)
					listParmPrim.addAll(listPrmP);
					
					if(listAllPrm.size()>0)
					listParmPrim.addAll(listAllPrm);
					
					if(listParmPCt.size()>0)
					listParmPrim.addAll(listParmPCt);
					
					if(listParmPGd.size()>0)
					listParmPrim.addAll(listParmPGd);
					
					if(listParmPCGd.size()>0)
					listParmPrim.addAll(listParmPCGd);
					
					if(listParmPGF.size()>0)
					listParmPrim.addAll(listParmPGF);
					
					if(listParmPCF.size()>0)
					listParmPrim.addAll(listParmPCF);
					
					if(listParmAll.size()>0)
					listParmPrim.addAll(listParmAll);
					
					lisParmPFx=null;listPrmP=null;listAllPrm=null;listParmPCt=null;listParmPGd=null;listParmPCGd=null;listParmPGF=null;listParmPCF=null;listParmAll=null;
					
					ok = updatePrim(empl);
			}
			if (ok) {
				this.infoMsg = "Op�ration r�ussie !";
				listParmPrim.clear();
			} else {
				this.infoMsg = "Op�ration �chou�e !";
			}
		}
 
public void chargermentParm(){
	boolean bl=false;
	listParmPrim=FichierBaseDAO.getInstance().getListParametragePrime(idPrsnl,idCateg,idGrd, idFonct);
	listEmploye=FactoryDAO.getInstance().getListEmploye(idPrsnl, idCateg,idGrd, 0, 0, 0, 0, 0,idFonct, 1, 0);
	if(listEmploye.size()>0){
	for (EmployeC empl : listEmploye) {
		bl=updatePrim(empl);
	}
	if (bl) {
		this.infoMsg = "Op�ration r�ussie !";
		listParmPrim.clear();
	} else {
		this.infoMsg = "Op�ration �chou�e !";
	}
	}
}
   private boolean updatePrim(EmployeC empl) {
	DetailPrimeEmployeC detPrm;
	PrimeIndemniteC prime;
	boolean bl=false;
	for (ParametragePrimeC prm : listParmPrim) {
	detPrm = FactoryDAO.getInstance().getDetailPrimeEmploye(
			prm.getId(), prm.getIdPrime(), empl.getId());
	prime = FichierBaseDAO.getInstance().getPrimeIndemnite(
			prm.getIdPrime());
	if (detPrm == null)
		detPrm = new DetailPrimeEmployeC();
	detPrm.setMontant(prm.getForfait());
	detPrm.setIdParametre(prm.getId());
	detPrm.setTaux(prm.getTaux());
	detPrm.setEmploye(empl);
	detPrm.setPrime(prime);
	bl = UtilitaireDAO.getInstance().updatePrimeEmploye(detPrm);
}
	return bl;
} 

  
   }
   
   
   


