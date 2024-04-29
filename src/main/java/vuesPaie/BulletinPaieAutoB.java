 package vuesPaie;

 import classesPaie.*;

		   import com.lowagie.text.Document;
 import com.lowagie.text.HeaderFooter;
 import com.lowagie.text.Phrase;

 import java.io.IOException;
import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Date;
 import java.util.Iterator;
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
 public class BulletinPaieAutoB implements Serializable {
   private static final long serialVersionUID = 722684984145788954L;
   private List<EmployeC> listEmploye;
   private BulletinPaieC bulletin;
   private int mois;
   private int progressValue;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession(); 
			 private boolean disableSave;
			 private DroitC droit; 
			 private Date datePaie; 
			 private List<PaieAnormalC> listAnormal; 
			 private String code; 
			 private String nomPrenom; 
			 private String moisLettre; 
			 private String datePaieString; 
			 BulletinAllocationC detailAllocation; 
			 BulletinHeureSupplementaireC detailHeurSup; 
			 BulletinIndemniteC detailIndemnite; 
			 BulletinPrimeC detailPrime; 
			 BulletinCotisationC detailCotisation; 
			 BulletinComissionC detailComission;
			 BulletinDeductionC detailDeduction; 
			 BulletinCreditC detailCredit; 
			 BulletinAvanceC detailAvance; 
			 ParametrageGeneralC parametre; 
			 List<ParametreCotisationC> listCotisation;
			 TraitementSalarialC traitement;
			 SaisiePositionEmployeC position; 
			 FinCarriereC finCarriere; 
			 PrimeIndemniteC prime; 
			 CotisationC cotisation;
			 CotisationC cmission;
			 double montantBaseHs; 
			 double montantPrime; 
			 double baseCalcule; 
			 double montantCotisation; 
			 double partPatronal; 
			 double montantNetMin; 
			 double tauxSal; 
			 double tauxPatr; 
			 double monatntCmssion;
			 double tauxCom;
			 ParametragePrimeC parametrePrime; 
			 ParametreCotisationC parametreCot,parametreCom; 
			 List<CotisationDetailC> listParmCotDetail; 
			 List<CotisationDetailC> listCotPrm; 
			 CreditRembourseC detailCredRembourse;
			 DetailBanqueEmployeC bkVrt;
			 Historique hist;
			 int typeEmploye; 
			 int idParmPrim;
			 int idCom;
   public void setListAnormal(List<PaieAnormalC> listAnormal) {
     this.listAnormal = listAnormal;
   }
			 public List<PaieAnormalC> getListAnormal() {
     return this.listAnormal;
   }
   public String getDatePaieString() {
     return this.datePaieString;
   }
 
   
   public void setDatePaieString(String datePaieString) {
     this.datePaieString = datePaieString;
   }
 
   
   public int getProgressValue() {
     return this.progressValue;
   }
 
   
   public void setProgressValue(int progressValue) {
     this.progressValue = progressValue;
   }
 
   
   public String getCode() {
     return this.code;
   }
 
   
   public void setCode(String code) {
     this.code = code;
   }
 
   
   public String getNomPrenom() {
     return this.nomPrenom;
   }
 
   
   public void setNomPrenom(String nomPrenom) {
     this.nomPrenom = nomPrenom;
   }
 
   
   public Date getDatePaie() {
     return this.datePaie;
   }
 
   
   public void setDatePaie(Date datePaie) {
     this.datePaie = datePaie;
   }
 
   
   public int getMois() {
     return this.mois;
   }
 
   
   public void setMois(int mois) {
     this.mois = mois;
   }
   
   public List<EmployeC> getListEmploye() {
     return this.listEmploye;
   }
   
   public void setListEmploye(List<EmployeC> listEmploye) {
     this.listEmploye = listEmploye;
   }
 
   
   public BulletinPaieC getBulletin() {
     return this.bulletin;
   }
 
   
   public void setBulletin(BulletinPaieC bulletin) {
     this.bulletin = bulletin;
   }
 
   
   public DroitC getDroit() {
     return this.droit;
   }
 
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }
   
   public String getMoisLettre() {
     return this.moisLettre;
   }
   
   public void setMoisLettre(String moisLettre) {
     this.moisLettre = moisLettre;
   }

	 		public boolean isDisableSave() {
	 			return disableSave;
	 		}

	 		public void setDisableSave(boolean disableSave) {
	 			this.disableSave = disableSave;
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
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.bulletinPaie);
       }
       this.parametre = FichierBaseDAO.getInstance().getParametrageGeneral();
       if (this.parametre != null) {
         this.montantNetMin = this.parametre.getMontantNetMin();
       }
       this.listAnormal = new ArrayList<PaieAnormalC>();
       this.listCotPrm = new ArrayList<CotisationDetailC>();
     } 
   }
			
 
   
   private void chargementEmploye() {
     this.listEmploye = FactoryDAO.getInstance().getListAllEmploye("", "", true);
   }
 
 
 
   
   public void generationBulletin() throws InterruptedException {
     try {
					disableSave=false;
       if (getDatePaie().before(this.exercice.getDateDebut()) || getDatePaie().after(this.exercice.getDateFin())) {
         
         HelperC.afficherAttention("ATTENTION", "La date de la paie ne doit pasétre en déhors de la période de l'exercice !");
 		   disableSave=true;
        
       }
       else if (this.mois > 0 && getDatePaie() != null) {
         DetailCotisationEmployeC detCotEmp=null;
         chargementEmploye();
         if (this.listEmploye != null && this.listEmploye.size() > 0)
         {
           this.progressValue = 0;
           int i = 0;
           int nbreHeur = 0;
           
           for (EmployeC employe : this.listEmploye) {
 
             
             i++;
             this.progressValue = i * 100 / this.listEmploye.size();
             
             this.nomPrenom = String.valueOf(i) + " sur " + this.listEmploye.size() + " employés";
             this.typeEmploye = employe.getTypeEmploye();
 
             
             if (employe.getDateEntre() != null && employe.getDateEntre().before(getDatePaie())) {
               
               this.bulletin = FactoryDAO.getInstance().getBulletinPaie(employe.getId(), getDatePaie(), this.exercice.getId());
						 

							
               if (this.bulletin == null) {
                   this.bulletin = new BulletinPaieC();
						
							
                 Thread.sleep(60L);
                 
               
                 this.bulletin.setBasePaiement(employe.getBasePaiement());
                 this.bulletin.setDatePaie(this.datePaie);
                 this.bulletin.setIdEmploye(employe.getId());
                 this.bulletin.setIdExercice(this.exercice.getId());
                 this.bulletin.setMois(this.mois);
                 this.bulletin.setNombreHeureNormal(this.parametre.getNbreJourMois() * this.parametre.getNbreHeureJour());
                 this.bulletin.setNombreHeurePreste(this.parametre.getNbreJourMois() * this.parametre.getNbreHeureJour());

                 this.traitement = FactoryDAO.getInstance().getSalaireActuel(employe, datePaie);
                 this.bulletin.setModeReglement(employe.getModeReglement());
                 position = FactoryDAO.getInstance().getSaisiePositionEmploye(employe, datePaie);

                 if(employe.getDateRetraite()==null)
							{
									this.bulletin.setTotalComplementAllocation(employe.getComplement());
                		 if (this.traitement != null) 
									this.bulletin.setSalaireBase(this.traitement.getSalaireBase());
                  	else 
                   		this.bulletin.setSalaireBase(employe.getSalaireBase());
                  
                 		if (this.parametre != null) 
								{
                   
                   	this.bulletin.setTauxJrsFerie(this.parametre.getTauxJrFerie());
                   	this.bulletin.setNbreJourNormal(this.parametre.getNbreJourMois());
                  	} 
              
								bkVrt=FactoryDAO.getInstance().getBanquePrincipal(employe.getId());
								if(bkVrt!=null)
								{
									bulletin.setCompteVirement(bkVrt.getNumeroCompte());
									if(bkVrt.getBanque()!=null)
										bulletin.setIdBkVrt(bkVrt.getBanque().getId());
								}
                 		nbreHeur = FactoryDAO.getInstance().getNombreHeureAbsence(employe.getId(), getMois(), this.exercice.getId());
                 		this.bulletin.setNombreHeurePreste(this.parametre.getNbreHeureMois() - nbreHeur);
                 
                 		if (employe.getMontantLogement() > 0.0D) {
                   
                   		this.bulletin.setTotalLogement(employe.getMontantLogement());
                 			} else {
                   
                   	this.bulletin.setTotalLogement(employe.getSalaireBase() * employe.getPourcentageLogement() / 100.0D);
                 		} 
                	}
                 		this.bulletin.setCommentaire("SALAIRE DU MOIS  DE " + HelperC.getMoisEnTouteLettre(this.mois).toUpperCase());

                           if(employe.getDateRetraite()==null)
                 		chargementPrimeEmploye(this.bulletin, employe);
                           else
                           {
                        	   prime=FichierBaseDAO.getInstance().getPrimeRetraite();
                        	   if(prime!=null)
                        	   {
                        		   if(traitement!=null)
                        			   montantPrime=traitement.getSalaireBase();
                        		   
                        	   createNewPrime(true, bulletin, employe);
                        	   }
                           }
                           if(employe.getDateRetraite()==null)
                	 addAllocation(this.bulletin, employe);
               
                 addHeureSup(this.bulletin, employe);
							if(employe.getDateRetraite()!=null)
							{
								List<CotisationC>listCt=FichierBaseDAO.getInstance().getAllCotisationRetraite();
								if(listCt!=null && listCt.size()>0)
								{
									for (CotisationC c : listCt) 
									{
										detCotEmp=new DetailCotisationEmployeC();
										detCotEmp.setCotisation(c);
										employe.getListeDetailCotisation().add(detCotEmp);
									}
									
								}
							}
                 chargementEmplCotisation(this.bulletin, employe);
                 addDeduction(this.bulletin, employe);
						   deductionSanction(employe, bulletin);
                 addCredit(this.bulletin, employe);
                 addAvance(this.bulletin, employe);
						   chargementEmplComission(bulletin, employe);
                 if(position!=null && !position.isAjoutAllocationFamiliale())
								bulletin.getListAllocation().clear();

							if(position!=null && !position.isAjoutIndemniteLogement())
								bulletin.setTotalLogement(0);
							
                 if (this.bulletin.getTotalNetPay() >= this.montantNetMin) {
                   hist=new Historique();
							 hist.setDateOperation(new Date());
							 hist.setOperateur(operateur);
							 hist.setTable(Tables.getTableName(Tables.TableName.bulletinPaie));
							
							if(bulletin.getId()==0)
								hist.setOperation("Enregistrement de la paie de "+employe.getNomPrenom()+" pour le mois de "+getMoisLettre()+" : Net ="+HelperC.decimalNumber(bulletin.getTotalNetPay(), 0, true));
							else
								hist.setOperation("Modification de la paie de "+employe.getNomPrenom()+" pour le mois de "+getMoisLettre()+" : Net ="+HelperC.decimalNumber(bulletin.getTotalNetPay(), 0, true));
							
							bulletin.setHistory(hist);
                   FactoryDAO.getInstance().insertUpdateBulletinPaie(this.bulletin);
                 } else {
                   
                   PaieAnormalC anormalPay = new PaieAnormalC();
                   anormalPay.setIdEmploye(employe.getId());
                   anormalPay.setNomPrenom(employe.getNomPrenom());
							 anormalPay.setMontantPaie(employe.getSalaireBase());
                   anormalPay.setCode(employe.getCode());
                   anormalPay.setDatePaie(this.bulletin.getDatePaie());
                   anormalPay.setMontantPaie(this.bulletin.getTotalNetPay());
                   anormalPay.setIdExercice(this.exercice.getId());
                   FactoryDAO.getInstance().insertPaieAnormal(anormalPay);
                 } 
                 
                 this.montantBaseHs = 0.0D;
                 nbreHeur = 0;
                 this.typeEmploye = 0;
                
             } 
					}
             
             chargementPaieAnormal();
           } 
 
           
           HelperC.afficherMessage("Information", "Opération terminée !");
         }
       
       } else {
         
         HelperC.afficherMessage("Information", "Il faut préciser le mois et la date!");
         this.progressValue = 0;
       }
     
     } catch (Exception e) {
       
       HelperC.afficherMessage("ERREUR", "Echec de l'opération ! :" + e.getMessage(), FacesMessage.SEVERITY_ERROR);
     } 
   }
 
   
  
   public void chargementPaieAnormal() {
     EmployeC empl = null;
     this.listAnormal = FactoryDAO.getInstance().getListePaieAnormal(getDatePaie());
     int indice = 0;
     if (this.listAnormal != null && this.listAnormal.size() > 0)
     {
       
       for (PaieAnormalC pan : this.listAnormal) {
 
         
         indice++;
         empl = FactoryDAO.getInstance().getEmployeSimple(pan.getIdEmploye());
         pan.setNomPrenom(empl.getNomPrenom());
         pan.setCode(empl.getCode());
         pan.setIndexNum(indice);
                   pan.setPrintBase(HelperC.decimalNumber(empl.getSalaireBase(), 0, true));
       } 
     }
   }
 
 
  

//   private void chargementPrimeEmploye(BulletinPaieC bulltinPaie, EmployeC employe) {
//     if (employe != null && employe.getListeDetailPrime() != null && employe.getListeDetailPrime().size() > 0) {
// 
//       
//      
//        
//        for (SaisiePositionDetailPrimeC pm : this.position.getListeDetailPrime()) {
//           
//           this.prime = pm.getPrime();
//           
//          this.montantPrime = pm.getMontant();
//           
//           if (this.prime != null)
//          {
//            if (this.montantPrime > 0.0D) {
//               createNewPrime(true, bulltinPaie, employe); continue;
//            } 
//            createNewPrime(false, bulltinPaie, employe);
//           }
//         
//         } 
//      
//					
//       
//       Collections.sort(bulltinPaie.getListePrime(), new PrimeOrderC());
//       valeurPrimeSansDetail(bulltinPaie, employe);
// 
//       
//       valeurPrimeAvecDetail(bulltinPaie, employe);
//     } 
//   }

private void chargementPrimeEmploye(BulletinPaieC bulltinPaie, EmployeC employe) {
	if (employe != null && employe.getListeDetailPrime() != null
			&& employe.getListeDetailPrime().size() > 0) {

		bulltinPaie.getListePrime().clear();

		for (DetailPrimeEmployeC pm :employe.getListeDetailPrime()) {

			this.prime = pm.getPrime();
			
			this.idParmPrim = pm.getIdParametre();
			if (pm.getMontant() > 0.0D) {

				this.montantPrime = pm.getMontant();
				pm.setCalculated(true);
			}
			if (pm.getIdParametre() == 0 && pm.getTaux() > 0.0D) {
				this.montantPrime = pm.getTaux() / 100.0D * bulltinPaie.getSalaireBase();
				pm.setCalculated(true);
			}
			if (this.prime != null) {
				createNewPrime(pm.isCalculated(),bulltinPaie,employe);
			}
		}

	}
	Collections.sort(bulltinPaie.getListePrime(), new PrimeOrderC());
	valeurPrimeSansDetail(bulltinPaie,employe);

	valeurPrimeAvecDetail(bulltinPaie,employe);

}
   private void createNewPrime(boolean calculate, BulletinPaieC bulltinPaie, EmployeC employe) {
     if (this.prime != null) {
       
       if (this.detailPrime == null)
       {
         this.detailPrime = new BulletinPrimeC();
       }
       
       this.detailPrime.setIdPrime(this.prime.getId());
       this.detailPrime.setCodePrime(this.prime.getCode());
       this.detailPrime.setLibellePrime(this.prime.getDesignation());
       this.detailPrime.setMontantPrime(this.montantPrime);
       this.detailPrime.setPrimeBulletin(this.prime);
       this.detailPrime.setCalculated(calculate);
       this.detailPrime.setPriority(FichierBaseDAO.getInstance().getPrioritePrime(employe.getIdPersnl(), employe.getIdCatgrie(), employe.getIdGrd(), employe.getIdFnctn(), this.prime.getId()));
       this.detailPrime.setIdParametre(this.idParmPrim);
       bulltinPaie.getListePrime().add(this.detailPrime);
       clearPrime();
     } 
   }
   
   private void clearPrime() {
     this.montantPrime = 0.0D;
     this.detailPrime = null;
     this.prime = null;
     this.idParmPrim = 0;
   }
 
   
   private void valeurPrimeSansDetail(BulletinPaieC bulltinPaie, EmployeC employe) {
     for (BulletinPrimeC prmB : bulltinPaie.getListePrime()) {
       
       if (!prmB.isCalculated()) {
         
         this.parametrePrime = FichierBaseDAO.getInstance().getParametragePrime(prmB.getIdParametre(), true);
         
         if (this.parametrePrime != null && this.parametrePrime.getListDetail().size() == 0) {
 
           
           if (this.parametrePrime.getForfait() > 0.0D) {
             
             this.montantPrime = this.parametrePrime.getForfait();
             prmB.setCalculated(true);
           } 
           
           if (this.parametrePrime.getTaux() > 0.0D) {
             
             switch (this.parametrePrime.getTypeBase()) {
               case 0:
                 this.baseCalcule = bulltinPaie.getSalaireBase();
                 break;
               case 1:
                 this.baseCalcule = bulltinPaie.getTotalmontantBase();
                 break;
             } 
             
             this.montantPrime = this.baseCalcule * this.parametrePrime.getTaux() / 100.0D;
             
             if (this.parametrePrime.getPlafond() > 0.0D && 
               this.montantPrime > this.parametrePrime.getPlafond())
               this.montantPrime = this.parametrePrime.getPlafond(); 
             if (this.parametrePrime.getPlancher() > 0.0D && 
               this.montantPrime < this.parametrePrime.getPlancher()) {
               this.montantPrime = this.parametrePrime.getPlancher();
             }
             prmB.setCalculated(true);
           } 
           
           prmB.setMontantPrime(this.montantPrime);
         } 
         this.parametrePrime = null;
       } 
       this.montantPrime = 0.0D;
     } 
   }
   
   private boolean allPrimeCalculated(List<BulletinPrimeC> listPrm) {
     boolean bl = false;
     for (BulletinPrimeC prm : listPrm) {
       
       if (!prm.isCalculated()) {
         
         bl = true;
         break;
       } 
     } 
     return bl;
   }
   private void valeurPrimeAvecDetail(BulletinPaieC bulltinPaie, EmployeC employe) {
     this.montantPrime = 0.0D;
     for (BulletinPrimeC prm : bulltinPaie.getListePrime()) {
       
       if (!prm.isCalculated()) {
         
         this.parametrePrime = FichierBaseDAO.getInstance().getParametragePrime(prm.getIdParametre(), true);
         if (this.parametrePrime != null) {
           
           if (this.parametrePrime.getListDetail().size() > 0)
           {
             this.montantPrime = searchInCalculateValues(this.parametrePrime, bulltinPaie, prm);
             prm.setMontantPrime(this.montantPrime);
             prm.setCalculated(true);
           }
         
         } else {
           
           prm.setCalculated(true);
         } 
         this.parametrePrime = null;
       } 
       this.montantPrime = 0.0D;
     } 
   }
   private double searchInCalculateValues(ParametragePrimeC paPrm, BulletinPaieC bulltinPaie, BulletinPrimeC bPm) {
     PrimeIndemniteC prim = null;
     double montant = 0.0D, totPrm = 0.0D;
     for (ParametragePrimeDetailC det : paPrm.getListDetail()) {
       
       prim = FichierBaseDAO.getInstance().getPrimeIndemnite(det.getCodeElement());
       
       if (prim != null) {
         for (BulletinPrimeC bPrm : bulltinPaie.getListePrime()) {
           if (bPrm.isCalculated() && prim.getId() == bPrm.getIdPrime()) {
             
             montant = det.getTaux() * bPrm.getMontantPrime() / 100.0D;
             
             if (det.getPlafon() > 0.0D && 
               montant > det.getPlafon())
               montant = det.getPlafon(); 
             if (det.getPlancher() < 0.0D && 
               montant < det.getPlancher())
               montant = det.getPlancher(); 
             totPrm += montant;
             bPm.setCalculated(true);
             
             montant = 0.0D;
           } 
         } 
       }
     } 
 
     
     return totPrm;
   }
 
   
   private void chargementEmplCotisation(BulletinPaieC bulltinPaie, EmployeC employe) {
     boolean bl = false;
 
     
     if (employe != null && employe.getListeDetailCotisation() != null && employe.getListeDetailCotisation().size() > 0) {
 
 
       
       for (DetailCotisationEmployeC cot : employe.getListeDetailCotisation()) {
 
         
         this.cotisation = cot.getCotisation();
         
         if (this.montantCotisation == 0.0D && this.partPatronal == 0.0D) {
           
           this.parametreCot = FichierBaseDAO.getInstance().getParameterCotisation(this.cotisation.getId(), true);
           
           if (this.parametreCot != null && this.parametreCot.getListDetail().size() == 0) {
 
             
             if (this.parametreCot.getForfaitPatronal() > 0.0D)
               this.partPatronal = this.parametreCot.getForfaitPatronal(); 
             if (this.parametreCot.getForfaitSalarial() > 0.0D) {
               this.montantCotisation = this.parametreCot.getForfaitSalarial();
             }
             
             if (this.parametreCot.getTauxPatronal() > 0.0D) {
               
               switch (this.parametreCot.getTypeBasePatronal()) {
                 case 0:
                   this.baseCalcule = employe.getSalaireBase();
                   break;
                 
                 case 1:
                   this.baseCalcule = bulltinPaie.getTotalmontantBase();
                   break;
                 case 2:
                   this.baseCalcule = this.parametreCot.getBaseFixe();
                   break;
               } 
               
               if (this.parametreCot.getPlafondBase() > 0.0D)
               {
                 if (this.baseCalcule > this.parametreCot.getPlafondBase())
                   this.baseCalcule = this.parametreCot.getPlafondBase(); 
               }
               if (this.parametreCot.getPlancherBase() > 0.0D)
               {
                 if (this.baseCalcule < this.parametreCot.getPlancherBase()) {
                   this.baseCalcule = this.parametreCot.getPlancherBase();
                 }
               }
               this.partPatronal = this.baseCalcule * this.parametreCot.getTauxPatronal() / 100.0D;
               if (this.parametreCot.getPlafonPatronal() > 0.0D && 
                 this.partPatronal > this.parametreCot.getPlafonPatronal())
                 this.partPatronal = this.parametreCot.getPlafonPatronal(); 
               if (this.parametreCot.getPlancherPatronal() > 0.0D && 
                 this.partPatronal < this.parametreCot.getPlancherPatronal()) {
                 this.partPatronal = this.parametreCot.getPlancherPatronal();
               }
               this.tauxPatr = this.parametreCot.getTauxPatronal();
             } 
 
             
             if (this.parametreCot.getTauxSalarial() > 0.0D) {
               
               this.baseCalcule = 0.0D;
               
               switch (this.parametreCot.getTypeBaseSalarial()) {
                 case 0:
                   this.baseCalcule = employe.getSalaireBase();
                   break;
                 
                 case 1:
                   this.baseCalcule = bulltinPaie.getTotalmontantBase();
                   break;
                 case 2:
                   this.baseCalcule = this.parametreCot.getBaseFixe();
                   break;
               } 
               
               if (this.parametreCot.getPlafondBase() > 0.0D)
               {
                 if (this.baseCalcule > this.parametreCot.getPlafondBase())
                   this.baseCalcule = this.parametreCot.getPlafondBase(); 
               }
               if (this.parametreCot.getPlancherBase() > 0.0D)
               {
                 if (this.baseCalcule < this.parametreCot.getPlancherBase()) {
                   this.baseCalcule = this.parametreCot.getPlancherBase();
                 }
               }
               this.montantCotisation = this.parametreCot.getTauxSalarial() * this.baseCalcule / 100.0D;
               this.tauxSal = this.parametreCot.getTauxSalarial();
               if (this.parametreCot.getPlafonSalarial() > 0.0D && 
                 this.montantCotisation > this.parametreCot.getPlafonSalarial()) {
                 this.montantCotisation = this.parametreCot.getPlafonSalarial();
               }
               if (this.parametreCot.getPlancherSalarial() > 0.0D && 
                 this.montantCotisation < this.parametreCot.getPlancherSalarial()) {
                 this.montantCotisation = this.parametreCot.getPlancherSalarial();
               }
             } 
             if (cot.getMontantSalarial() > 0.0D) {
               this.montantCotisation = cot.getMontantSalarial();
               bl = true;
             } 
             if (cot.getMontantPatronal() > 0.0D)
               this.partPatronal = cot.getMontantPatronal(); 
             bl = true;
           }
           else {
             
             if (cot.getMontantSalarial() > 0.0D) {
               this.montantCotisation = cot.getMontantSalarial();
               bl = true;
             } 
             if (cot.getMontantPatronal() > 0.0D) {
               this.partPatronal = cot.getMontantPatronal();
             }
           } 
         } 
         if (this.cotisation != null)
         {
           createNewCotisation(bl, bulltinPaie);
         }
         bl = false;
       } 
				  Collections.sort(bulltinPaie.getListeCotisation(),new CotisationOrderC());
      
       for (BulletinCotisationC cot : bulltinPaie.getListeCotisation())
       {
         calculValeurCotisation(cot, bulltinPaie);
				   cot.setMontantCotisation(Math.round(cot.getMontantCotisation()));
       }
     } 
   }
 
   
   private void calculValeurCotisation(BulletinCotisationC cot, BulletinPaieC bulltinPaie) {
     this.parametreCot = FichierBaseDAO.getInstance().getParameterCotisation(cot.getIdCotisation(), true);
     double totPrm = 0.0D, totCot = 0.0D, tmpMontant = 0.0D, taux = 0.0D, diff = 0.0D, montantPrm = 0.0D, montantSal = 0.0D, montantPatr = 0.0D, montantBase = 0.0D;
     CotisationC cotisation = FichierBaseDAO.getInstance().getCotisation(cot.getIdCotisation());
 
     
     if (this.parametreCot != null && this.parametreCot.getListDetail().size() > 0) {
       
       onlyEmployePrime(this.parametreCot.getId());
       
       switch (this.parametreCot.getTypeBaseSalarial()) {
         case 0:
           this.baseCalcule = bulltinPaie.getSalaireBase();
           break;
         
         case 1:
           this.baseCalcule = bulltinPaie.getTotalmontantBase();
           break;
         case 2:
           this.baseCalcule = this.parametreCot.getBaseFixe();
           break;
       } 
       if (this.listCotPrm.size() > 0) {
         for (CotisationDetailC det : this.listCotPrm) {
           
           if (det != null) {
             double aloc;
             if (det.getForfait() > 0.0D) {
               totPrm += det.getForfait();
             }
             switch (det.getTypeBase()) {
               
               case 1:
                 if (det.getTaux() > 0.0D) {
 
                   
                   tmpMontant = this.baseCalcule * det.getTaux() / 100.0D;
                   
                   if (det.getPlafon() > 0.0D && 
                     tmpMontant > det.getPlafon()) {
                     tmpMontant = det.getPlafon();
                   }
                   if (det.getPlancher() > 0.0D && 
                     tmpMontant < det.getPlancher()) {
                     tmpMontant = det.getPlancher();
                   }
                   totPrm += tmpMontant;
                 
                 }
                 else {
                   
                   tmpMontant = this.baseCalcule;
                   totPrm += tmpMontant;
                 } 
                 tmpMontant = 0.0D;
                 continue;
 
 
               
               case 2:
                 if (det.getTauxMax() > 0.0D) {
 
 
 
                   
                   double HS = 0.0D;
                   HS = this.baseCalcule * det.getTauxMax() / 100.0D;
                   
                   if (HS > bulltinPaie.getTotalHS()) {
 
                     
                     tmpMontant = HS - bulltinPaie.getTotalHS();
                     
                     if (det.getPlafon() > 0.0D && 
                       tmpMontant > det.getPlafon()) {
                       tmpMontant = det.getPlafon();
                     }
                     if (det.getPlancher() > 0.0D && 
                       tmpMontant < det.getPlancher())
                       tmpMontant = det.getPlancher(); 
                   } 
                   totPrm += tmpMontant;
                 } 
 
                 
                 if (det.getTaux() > 0.0D) {
                   
                   tmpMontant = bulltinPaie.getTotalHS() * det.getTaux();
                   
                   if (det.getPlafon() > 0.0D && 
                     tmpMontant > det.getPlafon()) {
                     tmpMontant = det.getPlafon();
                   }
                   if (det.getPlancher() > 0.0D && 
                     tmpMontant < det.getPlancher()) {
                     tmpMontant = det.getPlancher();
                   }
                   totPrm += tmpMontant;
                 } 
                 
                 if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {
                   
                   tmpMontant = bulltinPaie.getTotalHS();
                   totPrm += tmpMontant;
                 } 
                 tmpMontant = 0.0D;
                 continue;
               
               case 3:
                 if (det.getTauxMax() > 0.0D) {
 
                   
                   double logement = 0.0D;
 
                   
                   logement = this.baseCalcule * det.getTauxMax() / 100.0D;
                   if (bulltinPaie.getTotalLogement() > logement) {
                     
                     tmpMontant = bulltinPaie.getTotalLogement() - logement;
                     
                     if (det.getPlafon() > 0.0D && 
                       tmpMontant > det.getPlafon()) {
                       tmpMontant = det.getPlafon();
                     }
                     if (det.getPlancher() > 0.0D && 
                       tmpMontant < det.getPlancher())
                       tmpMontant = det.getPlancher(); 
                   } 
                   totPrm += tmpMontant;
                 } 
                 if (det.getTaux() > 0.0D) {
 
                   
                   tmpMontant = bulltinPaie.getTotalLogement() * det.getTaux() / 100.0D;
                   
                   if (det.getPlafon() > 0.0D && 
                     tmpMontant > det.getPlafon()) {
                     tmpMontant = det.getPlafon();
                   }
                   if (det.getPlancher() > 0.0D && 
                     tmpMontant < det.getPlancher()) {
                     tmpMontant = det.getPlancher();
                   }
                   totPrm += tmpMontant;
                 } 
                 if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {
                   
                   tmpMontant = bulltinPaie.getTotalLogement();
                   totPrm += tmpMontant;
                 } 
                 tmpMontant = 0.0D;
                 continue;
               case 4:
                 aloc = 0.0D;
                 if (det.getTauxMax() > 0.0D) {
                   aloc = this.baseCalcule * det.getTauxMax() / 100.0D;
                   
                   if (aloc > bulltinPaie.getTotalAllocation())
                     tmpMontant = aloc - 
                       bulltinPaie.getTotalAllocation(); 
                   if (det.getPlafon() > 0.0D && 
                     tmpMontant > det.getPlafon()) {
                     tmpMontant = det.getPlafon();
                   }
                   if (det.getPlancher() > 0.0D && 
                     tmpMontant < det.getPlancher()) {
                     tmpMontant = det.getPlancher();
                   }
                   totPrm += tmpMontant;
                 } 
                 if (det.getTaux() > 0.0D) {
 
                   
                   tmpMontant = bulltinPaie.getTotalAllocation() * det.getTaux() / 100.0D;
                   
                   if (det.getPlafon() > 0.0D && 
                     tmpMontant > det.getPlafon()) {
                     tmpMontant = det.getPlafon();
                   }
                   if (det.getPlancher() > 0.0D && 
                     tmpMontant < det.getPlancher()) {
                     tmpMontant = det.getPlancher();
                   }
                   totPrm += tmpMontant;
                 } 
                 if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {
                   
                   tmpMontant = bulltinPaie.getTotalAllocation();
                   totPrm += tmpMontant;
                 } 
                 tmpMontant = 0.0D;
                 continue;
							
					case 5:
						
						if(det.getTaux()>0)
						{
							tmpMontant=bulltinPaie.getTotalBrute()*det.getTaux()/100;
							
							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
					                   tmpMontant = det.getPlafon();
					            }
							
							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
								
					              tmpMontant = det.getPlancher();
					       }
							totPrm += tmpMontant;
						}
						if(det.getForfait()>0)
						{
							totPrm+=det.getForfait();
						}
						 if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {
					           
					            tmpMontant = bulltinPaie.getTotalBrute();
					            totPrm += tmpMontant;
					        } 
					           tmpMontant = 0.0D;
						
						break;
						
						default:
							montantPrm = getValeurPrime(det.getCodeElement(),
									bulltinPaie, false);

							if (det.getTauxMax() > 0.0D) {

								double montant = 0.0D;
								montant = this.baseCalcule * det.getTauxMax()
										/ 100.0D;

								if (montantPrm > montant) {

									tmpMontant = montantPrm - montant;
								}
								if (det.getPlafon() > 0.0D
										&& tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D
										&& tmpMontant < det.getPlancher()) {
									tmpMontant = det.getPlancher();
								}
							}
							if (det.getTaux() > 0.0D) {

								tmpMontant = montantPrm * det.getTaux()
										/ 100.0D;

								if (det.getPlafon() > 0.0D
										&& tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D
										&& tmpMontant < det.getPlancher())
									tmpMontant = det.getPlancher();
							}
							if (det.getTaux() == 0.0D
									&& det.getTauxMax() == 0.0D) {
								tmpMontant = montantPrm;
							}
							totPrm += tmpMontant;
							tmpMontant = 0.0D;
							break;
             } 
             
             
           } 
         } 
       }
 
 
 
       
       this.listParmCotDetail = FichierBaseDAO.getInstance().getAllCotisationDetail(this.parametreCot.getId(), "M", "C");
       
       double montantDet = 0.0D;
       for (CotisationDetailC det : this.listParmCotDetail) {
         
         montantDet = getValeurCotisation(det.getCodeElement(), bulltinPaie);
         if (det.getTaux() > 0.0D) {
           montantDet = montantDet * det.getTaux() / 100.0D;
         }
         totCot += montantDet;
         montantDet = 0.0D;
       } 
       
       this.listParmCotDetail = FichierBaseDAO.getInstance().getAllCotisationDetail(this.parametreCot.getId(), "M", "P");
       for (CotisationDetailC det : this.listParmCotDetail) {
         
         montantDet = getValeurPrime(det.getCodeElement(), bulltinPaie, true);
         if (det.getTaux() > 0.0D) {
           montantDet = montantDet * det.getTaux() / 100.0D;
         }
         totCot += montantDet;
         montantDet = 0.0D;
       } 
       if (this.parametreCot.getTauxSalarial() > 0.0D) {
 
         
         montantBase = totPrm - totCot;
         
         if (this.parametreCot.getPlafondBase() > 0.0D)
         {
           if (montantBase > this.parametreCot.getPlafondBase())
             montantBase = this.parametreCot.getPlafondBase(); 
         }
         if (this.parametreCot.getPlancherBase() > 0.0D)
         {
           if (montantBase < this.parametreCot.getPlancherBase()) {
             montantBase = this.parametreCot.getPlancherBase();
           }
         }
         if (this.parametreCot.getPlafonSalarial() > 0.0D && 
           montantBase > this.parametreCot.getPlafonSalarial()) {
           montantBase = this.parametreCot.getPlafonSalarial();
         }
         if (this.parametreCot.getPlancherSalarial() > 0.0D && 
           montantBase < this.parametreCot.getPlancherSalarial()) {
           montantBase = this.parametreCot.getPlancherSalarial();
         }
         montantSal = montantBase * this.parametreCot.getTauxSalarial() / 100.0D;
 
         
         cot.setTauxSalarial(this.parametreCot.getTauxSalarial());
         cot.setMontantCotisation(montantSal);
         cot.setMontantBase(montantBase);
         cot.setCalculated(true);
       } 
       if (this.parametreCot.getForfaitSalarial() > 0.0D) {
         
         montantSal = this.parametreCot.getForfaitPatronal();
         cot.setMontantCotisation(montantSal);
         cot.setCalculated(true);
       } 
       
       if (this.parametreCot.getForfaitPatronal() > 0.0D) {
         
         montantPatr = this.parametreCot.getForfaitPatronal();
         cot.setMontantPatronal(montantPatr);
         cot.setCalculated(true);
       } 
       
       if (this.parametreCot.getTauxPatronal() > 0.0D) {
 
 
         
         montantBase = totPrm - totCot;
         
         if (this.parametreCot.getPlafondBase() > 0.0D)
         {
           if (montantBase > this.parametreCot.getPlafondBase())
             montantBase = this.parametreCot.getPlafondBase(); 
         }
         if (this.parametreCot.getPlancherBase() > 0.0D)
         {
           if (montantBase < this.parametreCot.getPlancherBase()) {
             montantBase = this.parametreCot.getPlancherBase();
           }
         }
         if (this.parametreCot.getPlafonPatronal() > 0.0D && 
           montantBase > this.parametreCot.getPlafonPatronal()) {
           montantBase = this.parametreCot.getPlafonPatronal();
         }
         if (this.parametreCot.getPlancherPatronal() > 0.0D && 
           montantBase < this.parametreCot.getPlancherPatronal()) {
           montantBase = this.parametreCot.getPlancherPatronal();
         }
         montantPatr = montantBase * this.parametreCot.getTauxPatronal() / 100.0D;
         
         cot.setMontantPatronal(montantPatr);
         cot.setCalculated(true);
         cot.setTauxPatronal(this.parametreCot.getTauxPatronal());
       } 


				if(parametreCot.getTauxPlafonSalarial()>0)
				{
					 double montCot=0;
					 montantBase = totPrm - totCot;
					 montCot=montantBase*parametreCot.getTauxPlafonSalarial()/100;
					 if(cot.getMontantCotisation()>montCot)
						 montantSal=montCot;
					 else
						 montantSal=cot.getMontantCotisation();
					 
					 cot.setMontantCotisation(montantSal);
					 cot.setCalculated(true);
					 cot.setTauxSalarial(this.parametreCot.getTauxSalarial());
				}
				
				if(parametreCot.getTauxPlafonPatronal()>0)
				{
					 double montCot=0;
					 
					 montantBase = totPrm - totCot;
					 montCot=montantBase*parametreCot.getTauxPlafonPatronal()/100;
					 if(cot.getMontantCotisation()>montCot)
						 montantPatr=montCot;
					 else
						 montantPatr=cot.getMontantCotisation();
					 
					 cot.setMontantPatronal(montantPatr);
					 cot.setCalculated(true);
					 cot.setTauxPatronal(this.parametreCot.getTauxPatronal());
				}

       if (cotisation.getTypeBaremme() == 1) {
         
         montantBase = totPrm - totCot;
         
         if (this.parametreCot.getPlafondBase() > 0.0D)
         {
           if (montantBase > this.parametreCot.getPlafondBase())
             montantBase = this.parametreCot.getPlafondBase(); 
         }
         if (this.parametreCot.getPlancherBase() > 0.0D)
         {
           if (montantBase < this.parametreCot.getPlancherBase())
             montantBase = this.parametreCot.getPlancherBase(); 
         }
         CalculCotisationParBaremeC.valeurCotisationImpot(montantBase, cotisation, this.typeEmploye);
         cot.setMontantCotisation(CalculCotisationParBaremeC.montantSalarial);
         cot.setMontantPatronal(CalculCotisationParBaremeC.montantPatronal);
         cot.setTauxSalarial(CalculCotisationParBaremeC.taux);
         cot.setMontantBase(montantBase);
         cot.setTypeCotisation(this.typeEmploye);
       } 
       
       if (cotisation.getTypeBaremme() == 2) {
         montantBase = totPrm - totCot;
         
         if (this.parametreCot.getPlafondBase() > 0.0D)
         {
           if (montantBase > this.parametreCot.getPlafondBase())
             montantBase = this.parametreCot.getPlafondBase(); 
         }
         if (this.parametreCot.getPlancherBase() > 0.0D)
         {
           if (montantBase < this.parametreCot.getPlancherBase()) {
             montantBase = this.parametreCot.getPlancherBase();
           }
         }
         CalculCotisationParBaremeC.valeurCotisationONPR(montantBase);
         montantSal = CalculCotisationParBaremeC.montantSalarial;
         cot.setMontantCotisation(montantSal);
         if (cot.getTauxPatronal() > 0.0D)
         {
           cot.setMontantPatronal(this.bulletin.getSalaireBase() * cot.getTauxPatronal() / 100.0D);
         }
         
         cot.setCalculated(true);
       } 
     } 
   }

private void chargementEmplComission(BulletinPaieC bulltinPaie,EmployeC employe) {
	boolean bl = false;
	if (employe != null && employe.getListeDetailComission() != null
			&& employe.getListeDetailComission().size() > 0) {

		bulltinPaie.getListComission().clear();

		for (DetailComissionEmployeC com : employe.getListeDetailComission()) {

			cmission = com.getComission();
			idCom = this.cmission.getId();

			if (this.monatntCmssion == 0.0) {

				this.parametreCom = FichierBaseDAO.getInstance().getParameterCotisation(cmission.getId(), true);

				/// **************************************************LORSQUE PARAMETRE
				/// COTISATION N'A PAS DE DETAIL************************
				if (this.parametreCom != null && this.parametreCom.getListDetail().size() == 0) {

					

					
					tauxCom=com.getTaux();
					
					if (com.getMontant() > 0.0D) {
						this.monatntCmssion= com.getMontant();
						bl = true;
					}
					
				} 
			}
			if (this.cmission != null) {
				createNewComission(bl,bulltinPaie);
			}
			bl = false;
		}

		/// ***********************************************************LORSQUE PARAMETRE
		/// COTISATION A DE DETAIL**************************************

	
		for (BulletinComissionC com : bulltinPaie.getListComission()) {

			calculValeurComission(com,bulltinPaie);
			com.setMontant(Math.round(com.getMontant()));

		}
	}
}
private void createNewComission(boolean calculated,BulletinPaieC bulltinPaie) {
	if (this.detailComission == null) {
		this.detailComission = new BulletinComissionC();
	}

	this.detailComission.setIdComission(this.idCom);
	
	this.detailComission.setMontant(monatntCmssion);
	
	this.detailComission.setTaux(this.tauxCom);

	bulltinPaie.getListComission().add(this.detailComission);
	clearComission();
}
private void clearComission() {
	cmission = null;
	this.monatntCmssion = 0;	
	idCom=0;
	tauxCom=0;
}

private void calculValeurComission(BulletinComissionC com,BulletinPaieC bulltinPaie) {
	parametreCom = FichierBaseDAO.getInstance().getParameterCotisation(com.getIdComission(), true);
	
	double totPrm = 0.0D, totCot = 0.0D, tmpMontant = 0.0D, taux = 0.0D, diff = 0.0D, montantPrm = 0.0D,

			montantCom = 0.0D, montantPatr = 0.0D, montantBase = 0.0D;
	
	CotisationC cotisation = FichierBaseDAO.getInstance().getCotisation(com.getIdComission());

	if (parametreCom != null && parametreCom.getListDetail().size() > 0) {

		onlyEmployePrime(parametreCom.getId());

		switch (this.parametreCom.getTypeBaseSalarial()) {
		case 0:
			this.baseCalcule = bulltinPaie.getSalaireBase();
			break;

		case 1:
			this.baseCalcule = bulltinPaie.getTotalmontantBase();
			break;
		case 2:
			this.baseCalcule = this.parametreCom.getBaseFixe();
			break;
		}
		if (this.listCotPrm.size() > 0) {
			for (CotisationDetailC det : this.listCotPrm) {

				if (det != null) {
					double aloc;
					if (det.getForfait() > 0.0D) {
						totPrm += det.getForfait();
					}
					taux=det.getTaux();
					switch (det.getTypeBase()) {

					case 1:
						if (det.getTaux() > 0.0D) {

							tmpMontant = this.baseCalcule * det.getTaux() / 100.0D;

							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
								tmpMontant = det.getPlafon();
							}
							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
								tmpMontant = det.getPlancher();
							}
							totPrm += tmpMontant;

						} else {

							tmpMontant = this.baseCalcule;
							totPrm += tmpMontant;
						}
						tmpMontant = 0.0D;
						continue;

					case 2:
						if (det.getTauxMax() > 0.0D) {

							double HS = 0.0D;
							HS = this.baseCalcule * det.getTauxMax() / 100.0D;

							if (HS > bulltinPaie.getTotalHS()) {

								tmpMontant = HS - bulltinPaie.getTotalHS();

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
									tmpMontant = det.getPlancher();
							}
							totPrm += tmpMontant;
						}

						if (det.getTaux() > 0.0D) {

							tmpMontant = bulltinPaie.getTotalHS() * det.getTaux();

							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
								tmpMontant = det.getPlafon();
							}
							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
								tmpMontant = det.getPlancher();
							}
							totPrm += tmpMontant;
						}

						if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

							tmpMontant = bulltinPaie.getTotalHS();
							totPrm += tmpMontant;
						}
						tmpMontant = 0.0D;
						continue;

					case 3:
						if (det.getTauxMax() > 0.0D) {

							double logement = 0.0D;

							logement = this.baseCalcule * det.getTauxMax() / 100.0D;
							if (bulltinPaie.getTotalLogement() > logement) {

								tmpMontant = bulltinPaie.getTotalLogement() - logement;

								if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
									tmpMontant = det.getPlafon();
								}
								if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
									tmpMontant = det.getPlancher();
							}
							totPrm += tmpMontant;
						}
						if (det.getTaux() > 0.0D) {

							tmpMontant = bulltinPaie.getTotalLogement() * det.getTaux() / 100.0D;

							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
								tmpMontant = det.getPlafon();
							}
							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
								tmpMontant = det.getPlancher();
							}
							totPrm += tmpMontant;
						}
						if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

							tmpMontant = bulltinPaie.getTotalLogement();
							totPrm += tmpMontant;
						}
						tmpMontant = 0.0D;
						continue;

					case 4:
						aloc = 0.0D;
						if (det.getTauxMax() > 0.0D) {
							aloc = this.baseCalcule * det.getTauxMax() / 100.0D;

							if (aloc > bulltinPaie.getTotalAllocation())
								tmpMontant = aloc - bulltinPaie.getTotalAllocation();
							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
								tmpMontant = det.getPlafon();
							}
							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
								tmpMontant = det.getPlancher();
							}
							totPrm += tmpMontant;
						}
						if (det.getTaux() > 0.0D) {

							tmpMontant = bulltinPaie.getTotalAllocation() * det.getTaux() / 100.0D;

							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
								tmpMontant = det.getPlafon();
							}
							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
								tmpMontant = det.getPlancher();
							}
							totPrm += tmpMontant;
						}
						if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

							tmpMontant = bulltinPaie.getTotalAllocation();
							totPrm += tmpMontant;
						}
						tmpMontant = 0.0D;
						continue;
					case 5:

						if (det.getTaux() > 0) {
							tmpMontant = bulltinPaie.getTotalBrute() * det.getTaux() / 100;

							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
								tmpMontant = det.getPlafon();
							}

							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {

								tmpMontant = det.getPlancher();
							}
							totPrm += tmpMontant;
						}
						if (det.getForfait() > 0) {
							totPrm += det.getForfait();
						}
						if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {

							tmpMontant =bulltinPaie. getTotalBrute();
							totPrm += tmpMontant;
						}
						tmpMontant = 0.0D;

						break;

					default:
						montantPrm = getValeurPrime(det.getCodeElement(),bulltinPaie, false);

						if (det.getTauxMax() > 0.0D) {

							double montant = 0.0D;
							montant = this.baseCalcule * det.getTauxMax() / 100.0D;

							if (montantPrm > montant) {

								tmpMontant = montantPrm - montant;
							}
							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
								tmpMontant = det.getPlafon();
							}
							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher()) {
								tmpMontant = det.getPlancher();
							}
						}
						if (det.getTaux() > 0.0D) {

							tmpMontant = montantPrm * det.getTaux() / 100.0D;

							if (det.getPlafon() > 0.0D && tmpMontant > det.getPlafon()) {
								tmpMontant = det.getPlafon();
							}
							if (det.getPlancher() > 0.0D && tmpMontant < det.getPlancher())
								tmpMontant = det.getPlancher();
						}
						if (det.getTaux() == 0.0D && det.getTauxMax() == 0.0D) {
							tmpMontant = montantPrm;
						}

						totPrm += tmpMontant;
						tmpMontant = 0.0D;
						break;
					}

				}
			}
		}

		this.listParmCotDetail = FichierBaseDAO.getInstance().getAllCotisationDetail(this.parametreCom.getId(), "M",
				"C");

		double montantDet = 0.0D;
		for (CotisationDetailC det : this.listParmCotDetail) {

			montantDet = getValeurCotisation(det.getCodeElement(),bulltinPaie);
			if (det.getTaux() > 0.0D) {
				montantDet = montantDet * det.getTaux() / 100.0D;
			}

			totCot += montantDet;
			montantDet = 0.0D;
		}

		this.listParmCotDetail = FichierBaseDAO.getInstance().getAllCotisationDetail(this.parametreCom.getId(), "M",
				"P");
		for (CotisationDetailC det : this.listParmCotDetail) {

			montantDet = getValeurPrime(det.getCodeElement(),bulltinPaie, true);
			if (det.getTaux() > 0.0D) {
				montantDet = montantDet * det.getTaux() / 100.0D;
			}
			totCot += montantDet;
			montantDet = 0.0D;
		}
		montantBase = totPrm - totCot;
		//montantCom=montantBase*parametreCom..gettgetTaux()/100;
		com.setComission(cotisation);
		com.setIdComission(cotisation.getId());
		com.setTaux(taux);
		com.setMontant(montantBase);
		com.setCalculated(true);
		
	}
}
   private void onlyEmployePrime(int idEntete) {
     this.listCotPrm.clear();
     for (BulletinPrimeC bPm : this.bulletin.getListePrime()) {
       
       if (FichierBaseDAO.getInstance().getCotisationDetail(bPm.getCodePrime(), idEntete) != null)
         this.listCotPrm.add(FichierBaseDAO.getInstance().getCotisationDetail(bPm.getCodePrime(), idEntete)); 
     } 
     this.listCotPrm.add(FichierBaseDAO.getInstance().getCotisationDetailSB("SB", idEntete));
     this.listCotPrm.add(FichierBaseDAO.getInstance().getCotisationDetail("HS", idEntete));
     this.listCotPrm.add(FichierBaseDAO.getInstance().getCotisationDetailLOG("LG", idEntete));
     this.listCotPrm.add(FichierBaseDAO.getInstance().getCotisationDetailAL("AL", idEntete));
			   this.listCotPrm.add(FichierBaseDAO.getInstance().getCotisationDetailBRUT("BR", idEntete));
				
   }
   
   private double getValeurPrime(String code, BulletinPaieC blt, boolean removable) {
     double montant = 0.0D;
     PrimeIndemniteC prm = FichierBaseDAO.getInstance().getPrimeIndemnite(code);
     if (prm != null) {
 
       
       for (BulletinPrimeC bPm : blt.getListePrime()) {
         if (bPm.getIdPrime() == prm.getId()) {
           
           montant = bPm.getMontantPrime();
 
           
           break;
         } 
       } 
     } else if (removable) {
       
       if (code.equals("SB"))
         montant = blt.getSalaireBase(); 
       if (code.equals("LG"))
         montant = blt.getTotalLogement(); 
       if (code.equals("HS"))
         montant = blt.getTotalHS(); 
       if (code.equals("AL")) {
         montant = blt.getTotalAllocation();
       }
     } 
     
     return montant;
   }
   
   private double getValeurCotisation(String code, BulletinPaieC blt) {
     double montant = 0.0D;
     ParametreCotisationC pCot = null;
     CotisationC cot = FichierBaseDAO.getInstance().getCotisation(code);
     if (cot != null)
     {
   
       for (BulletinCotisationC bCot : blt.getListeCotisation()) {
         if (bCot.getIdCotisation() == cot.getId()) {
           
           montant = bCot.getMontantCotisation();
           break;
         } 
       } 
     }
     return montant;
   }
   
   private void createNewCotisation(boolean calculated, BulletinPaieC bulltinPaie) {
     if (this.detailCotisation == null)
     {
       this.detailCotisation = new BulletinCotisationC();
     }
     this.detailCotisation.setIdCotisation(this.cotisation.getId());
     this.detailCotisation.setLibelleCotisation(this.cotisation.getDesignation());
     this.detailCotisation.setMontantCotisation(this.montantCotisation);
     this.detailCotisation.setMontantPatronal(this.partPatronal);
     this.detailCotisation.setCotisation(this.cotisation);
     this.detailCotisation.setMontantBase(this.baseCalcule);
     this.detailCotisation.setCalculated(calculated);
     this.detailCotisation.setTauxPatronal(this.tauxPatr);
     this.detailCotisation.setTauxSalarial(this.tauxSal);
     this.detailCotisation.setPriority(FichierBaseDAO.getInstance().getPrioriteCot(this.cotisation.getId()));
     bulltinPaie.getListeCotisation().add(this.detailCotisation);
     clearCotisation();
   }
   
   private void clearCotisation() {
     this.montantCotisation = 0.0D;
     this.detailCotisation = null;
     this.cotisation = null;
     this.baseCalcule = 0.0D;
     this.partPatronal = 0.0D;
     this.parametreCot = null;
     this.tauxPatr = 0.0D;
     this.tauxSal = 0.0D;
   }
 
 
 
   
	private void deductionSanction(EmployeC employe, BulletinPaieC bulltinPaie) {
		List<SaisieSanctionC> listSanction = FactoryDAO.getInstance().getListeSanctionRetenu(employe, getMois(),
				exercice.getId());
		DeductionC deduction = null;
		double montant = 0;
		for (SaisieSanctionC saisie : listSanction) {

			montant = saisie.getMontantRetenu();
			if (montant > 0) {
				deduction = FichierBaseDAO.getInstance().getDeduction(saisie.getPrm().getIdRetenu());
				if (deduction != null) {
					this.detailDeduction = new BulletinDeductionC();
					this.detailDeduction.setMontantRetenu(montant);
					this.detailDeduction.setDeduction(deduction);
					this.detailDeduction.setIdRetenu(deduction.getId());
					if (montant > 0.0D) {
						bulltinPaie.getListDeduction().add(this.detailDeduction);
					}
					montant = 0.0D;
					this.detailDeduction = null;
				}
			}
		}

	}

   private void addDeduction(BulletinPaieC bulltinPaie, EmployeC employe) {
     double montant = 0.0D;
     for (DetailDeductionC det : employe.getListeDetailDeduction()) {
 
       
       this.detailDeduction = new BulletinDeductionC();
       if (det.getMontant() != 0.0D)
       {
 
 
         
         montant = det.getMontant();
       }
       this.detailDeduction.setMontantRetenu(montant);
       this.detailDeduction.setDeduction(det.getDeduction());
       this.detailDeduction.setIdRetenu(det.getDeduction().getId());
       if (montant > 0.0D)
       {
         bulltinPaie.getListDeduction().add(this.detailDeduction);
       }
       montant = 0.0D;
       this.detailDeduction = null;
     } 
   }
 
 
   
		private void addCredit(BulletinPaieC bulltinPaie, EmployeC employe) {
			List<CreditDetailC> listCredit = null;
			CreditC crdi=null;
			
			if(FactoryDAO.getInstance().getListCreditRestant(employe.getId()))
				listCredit=FactoryDAO.getInstance().getListCreditEmploye(employe.getId(), getDatePaie());
		     if (listCredit != null && listCredit.size() > 0)
		      {
				for (CreditDetailC crd : listCredit) 
				{
					crdi=FactoryDAO.getInstance().getCreditEmploye(crd.getIdEntete());
					detailCredit=new BulletinCreditC();
					detailCredit.setMontant(crd.getTranche());
					detailCredit.setNoDossier(crdi.getNumeroDossier());
					detailCredit.setLibelle("Crédit : "+crdi.getBanque().getCode());
					bulltinPaie.getListCredit().add(detailCredit);
					
					detailCredRembourse=new CreditRembourseC();
					detailCredRembourse.setExercice(exercice);
					detailCredRembourse.setMontantRembourse(crd.getTranche());
					detailCredRembourse.setIdCredit(crdi.getId());
					detailCredRembourse.setDateRemboursement(getDatePaie());
				
					bulltinPaie.getListRemboursement().add(detailCredRembourse);
				} 
       
     }
   }
 
 
   
   private void addAllocation(BulletinPaieC bulltinPaie, EmployeC employe) {
     if (employe != null) {
       
       bulltinPaie.getListAllocation().clear();
       List<PersonneChargeC> listePersonneCharge = FactoryDAO.getInstance().getAllPersonnesChargeByEmploye(employe.getId());
       if (listePersonneCharge != null && listePersonneCharge.size() > 0)
       {
         for (PersonneChargeC pch : listePersonneCharge) {
 
           
           if (this.detailAllocation == null)
           {
             this.detailAllocation = new BulletinAllocationC();
           }
           this.detailAllocation.setIdPerson(pch.getId());
           this.detailAllocation.setMontant(pch.getMontant());
           this.detailAllocation.setNomPersonne(pch.getNomPersonneCharge());
           this.detailAllocation.setSatut(Constante.getLibelleStatutPersonneACharge(pch.getRelation()));
					 this.detailAllocation.setNombre(pch.getNombre());
           if (pch.getMontant() > 0.0D)
           {
             bulltinPaie.getListAllocation().add(this.detailAllocation);
           }
           this.detailAllocation = null;
         } 
       }
     } 
   }
 
 
   


	private void addAvance(BulletinPaieC bulltinPaie, EmployeC employe) {
	double totalRembourse = 0.0D;
	if (employe != null) {

	
		List<AvanceQuinzaineC> listAvance = FactoryDAO.getInstance().getListAvanceMoisEncours(employe.getId(),this.mois);
		for (AvanceQuinzaineC avance : listAvance) {

			
			if (avance.getMontant() >0) {

				if (this.detailAvance == null) {
					this.detailAvance = new BulletinAvanceC();
				}
				this.detailAvance.setDateAvance(avance.getDate());
				this.detailAvance.setMontantAvance(avance.getMontant());
				this.detailAvance.setIdAvance(avance.getId());
				bulltinPaie.getListAvance().add(this.detailAvance);
				this.detailAvance = null;
			}
		}
	}
}
   private void addHeureSup(BulletinPaieC bulltinPaie, EmployeC employe) {
     double montantHeur = 0.0D;
     this.montantBaseHs = 0.0D;
     List<HeuresPrestees> listeHeureSup = FactoryDAO.getInstance().getListHeurePrestees(employe.getId(), this.exercice.getId(), getMois());
     if (listeHeureSup != null && listeHeureSup.size() > 0) {
       
       this.montantBaseHs += bulltinPaie.getSalaireBase();
       
       if (this.parametre != null) {
         
         if (this.parametre.isAllocationBaseHsup())
         {
           this.montantBaseHs += bulltinPaie.getTotalAllocation();
         }
         if (this.parametre.isLogementBaseHsup())
         {
           this.montantBaseHs += bulltinPaie.getTotalLogement();
         }
         bulltinPaie.setNombreHeurJr(this.parametre.getNbreHeureJour());
         bulltinPaie.setNbreJourNormal(this.parametre.getNbreJourMois());
         bulltinPaie.setTauxJrsFerie(this.parametre.getTauxJrFerie());
       } 
 
       
       for (BulletinPrimeC bPm : bulltinPaie.getListePrime()) {
         
         this.parametrePrime = FichierBaseDAO.getInstance().getParametragePrime(bPm.getIdParametre(), false);
         if (this.parametrePrime != null && 
           this.parametrePrime.isCalculHeurSup()) {
           this.montantBaseHs += bPm.getMontantPrime();
         }
       } 
       bulltinPaie.setMontantBaseHSup(this.montantBaseHs);
       
       for (HeuresPrestees hs : listeHeureSup) {
 
         
         hs.setTemps(HelperC.heuresPreste(hs.getHeure(), hs.getMinute(), hs.getSec()));
         if (this.detailHeurSup == null)
         {
           this.detailHeurSup = new BulletinHeureSupplementaireC();
         }
         this.detailHeurSup.setHeurePreste(hs.getTemps());
         this.detailHeurSup.setHeures(hs.getHeure());
         this.detailHeurSup.setMinutes(hs.getMinute());
         this.detailHeurSup.setSecondes(hs.getSec());
         this.detailHeurSup.setPourcentage(hs.getPourcent());
         montantHeur = CalculHeureSupC.montantHeurePreste(bulltinPaie.getMontantBaseHSup(), bulltinPaie.getNombreHeureNormal(), hs.getPourcent(), hs.getHeure(), hs.getMinute(), hs.getSec());
         this.detailHeurSup.setMontant(montantHeur);
         this.detailHeurSup.setDateTravail(hs.getDatePrestation());
         bulltinPaie.getListHeureSup().add(this.detailHeurSup);
         this.detailHeurSup = null;
       } 
     } 
   }
 
 
   
   public void changeDatePaie() {
     if (getDatePaieString().replace("/", "").replace("_", "").trim().equals("")) {
       
       setDatePaie(null);
       this.moisLettre = "";
       this.mois = 0;
     } else {
       
       setDatePaie(HelperC.validerDate(getDatePaieString()));
       if (getDatePaie() == null) {
         
         setDatePaieString("");
         this.moisLettre = "";
         this.mois = 0;
       } else {
         
         setDatePaieString(HelperC.convertDate(getDatePaie()));
         this.mois = HelperC.getMonthFromDate(this.datePaie);
         this.moisLettre = HelperC.moisEnLettres(this.mois);
       } 
     } 
   }
 
   
   public void onComplete() {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Opération terminée !"));
   }
 
   
   public void cancel() {
     this.progressValue = 0;
     this.listEmploye.clear();
   }
 
   
   public void preProcessPdf(Object document) {
     Document doc = (Document)document;
     HeaderFooter hd = new HeaderFooter(new Phrase("LISTES DE PAIES ANORMALES"), true);
     hd.setBorder(0);
     doc.setHeader(hd);
   }
 }

