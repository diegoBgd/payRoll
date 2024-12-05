package vuesPaie;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import classesPaie.Base;
import classesPaie.BulletinCotisationC;
import classesPaie.BulletinPaieC;
import classesPaie.BulletinPrimeC;
import classesPaie.Constante;
import classesPaie.CotisationC;
import classesPaie.DroitC;
import classesPaie.EcritureComptableC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.LiaisonComptaC;
import classesPaie.OperateurC;
import classesPaie.ParametrageGeneralC;
import classesPaie.PrimeIndemniteC;
import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
import persistencePaie.LiaisonComptaDAO;

@ManagedBean
@ViewScoped
public class EcritureComptableB implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5832144810463108229L;
	private List<EcritureComptableC>listEcriture;
	
	private String codeJrnl,exerciceCompta,libelleExcCpta,libelleJrnl,printTotDb,printTotCrd;
	private int mois;
	
	 HttpSession session = HelperC.getSession();
	 ExerciceC exercice;
	 DroitC droit;
	 Base userFonction;
	 OperateurC operateur;
	 Base exerCpta,jrnal;
	 List<BulletinPaieC>listPaie;
	 List<BulletinCotisationC>listCot;
	 List<BulletinPrimeC>listPrime;
	 ParametrageGeneralC prm;
	 LiaisonComptaC link;
	public EcritureComptableB() {
		
	}

	public List<EcritureComptableC> getListEcriture() {
		return listEcriture;
	}

	public void setListEcriture(List<EcritureComptableC> listEcriture) {
		this.listEcriture = listEcriture;
	}

	public String getCodeJrnl() {
		return codeJrnl;
	}

	public void setCodeJrnl(String codeJrnl) {
		this.codeJrnl = codeJrnl;
	}

	public String getExerciceCompta() {
		return exerciceCompta;
	}

	public void setExerciceCompta(String exerciceCompta) {
		this.exerciceCompta = exerciceCompta;
	}

	public String getLibelleExcCpta() {
		return libelleExcCpta;
	}

	public void setLibelleExcCpta(String libelleExcCpta) {
		this.libelleExcCpta = libelleExcCpta;
	}

	public String getLibelleJrnl() {
		return libelleJrnl;
	}

	public void setLibelleJrnl(String libelleJrnl) {
		this.libelleJrnl = libelleJrnl;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public String getPrintTotDb() {
		return printTotDb;
	}

	public void setPrintTotDb(String printTotDb) {
		this.printTotDb = printTotDb;
	}

	public String getPrintTotCrd() {
		return printTotCrd;
	}

	public void setPrintTotCrd(String printTotCrd) {
		this.printTotCrd = printTotCrd;
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
	      
	       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
	       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
	       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
	       
	       if (this.userFonction != null)
	       {
	         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
	       }
	       prm=FichierBaseDAO.getInstance().getParametrageGeneral();	
	       link=FichierBaseDAO.getInstance().getLiaisonCompta();
	     } 
	   }

	public void searchJournal() {
		if(link!=null)
		if(codeJrnl!=null && !codeJrnl.equals(""))
		{
			jrnal=LiaisonComptaDAO.getConnection(link).getJournal(codeJrnl);
			if(jrnal!=null)
				libelleJrnl=jrnal.getDesignation();
		}
	}
	public void searchExercice() {
		if(link!=null)
		if(exerciceCompta!=null && !exerciceCompta.equals(""))
		{
			exerCpta=LiaisonComptaDAO.getConnection(link).getExercice(exerciceCompta);
			if(exerCpta!=null)
				libelleExcCpta=exerCpta.getDesignation();
		}
	}
	public void chargerPaie() {
		listPaie=FactoryDAO.getInstance().getListBulletinPaie(0, mois, exercice.getId());
		listPrime=FactoryDAO.getInstance().getListPrime(mois, exercice.getId());		
		listCot=FactoryDAO.getInstance().getListCotisation(mois, exercice.getId());
		listEcriture=new ArrayList<EcritureComptableC>();
		Date datePaie = null;
		double totBase=0,totAvance=0,totNet=0,totBrut=0,totAll=0;
		String libellePaie="";
		
		if(listPaie.size()>0)
		{
			for (BulletinPaieC bp : listPaie) {
				totBase+=bp.getSalaireBase();
				datePaie=bp.getDatePaie();	
				libellePaie=bp.getCommentaire();
				totAvance+=bp.getTotalAvance();
				totNet+=bp.getTotalNetPay();
				totBrut+=bp.getTotalBrute();
				totAll+=bp.getTotalAllocation();
			}
			
			createEcriture(prm.getCompteCharge(),mois+"/"+exercice.getCode(),libellePaie,datePaie, totBrut, 0);
			
			/*
			 * for (BulletinPrimeC prime : listPrime) {
			 * 
			 * createEcriture(prime.getPrimeBulletin().getPrefixeComptable(),mois+"/"+
			 * exercice.getCode(),prime.getLibellePrime(),datePaie, prime.getMontantPrime(),
			 * 0);
			 * 
			 * }
			 */
			
			
			  for (BulletinCotisationC cot : listCot) {
			  if(cot.getMontantPatronal()>0)
			  {
			  createEcriture(cot.getCotisation().getChargePtronal(),mois+"/"+exercice.
			  getCode(),cot.getLibelleCotisation()+" (PATRONAL)",datePaie,cot.getMontantPatronal(), 0); 
			  }
			  }
			 
			
			for (BulletinCotisationC cot : listCot) {
				if(cot.getCotisation().getOrganisme()!=null)
				createEcriture(cot.getCotisation().getOrganisme().getCompteCptbl(),mois+"/"+exercice.getCode(),cot.getCotisation().getDesignation(),datePaie,0, cot.getMontantCotisation()+cot.getMontantPatronal());
			}
			if(totAvance>0)
			createEcriture(prm.getCompteAvance(),mois+"/"+exercice.getCode(),"AVANCES SUR SALAIRE",datePaie, 0, totAvance);
			
			createEcriture(prm.getCompteSalaire(),mois+"/"+exercice.getCode(),libellePaie,datePaie, 0, totNet);
		}
		calculTotal();
	}
	
	
	 private void createEcriture(String cpte,String piece,String libelle,Date dateEcr,double deb,double crd) {
		 
		 EcritureComptableC ecr=new EcritureComptableC();
		 ecr.setCodeJournal(codeJrnl);
		 ecr.setCompte(cpte);
		 ecr.setPiece(piece);
		 ecr.setDateEcriture(dateEcr);
		 ecr.setDebit(deb);
		 ecr.setCredit(crd);
		 ecr.setLibelle(libelle);
		 if(exerCpta!=null)
			 ecr.setIdExercice(exerCpta.getId());
		 listEcriture.add(ecr);
		 ecr=null;
	 }
	 public void transfer() {
		 if(listEcriture.size()>0)
		 {
			 LiaisonComptaDAO.getConnection(link).insertEcriture(listEcriture);
		 }
	 }
	 private void calculTotal() {
		 double deb=0,crd=0;
		 printTotCrd="0";
		 printTotDb="0";
		 if(listEcriture.size()>0)
		 {
			 for (EcritureComptableC ecr : listEcriture) {
				deb+=ecr.getDebit();
				crd+=ecr.getCredit();
			}
			 printTotCrd=HelperC.decimalNumber(crd, 0, true);
			 printTotDb=HelperC.decimalNumber(deb, 0, true);
		 }
	 }
}
