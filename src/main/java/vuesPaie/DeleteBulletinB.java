 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.BulletinPaieC;
 import classesPaie.Constante;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
import classesPaie.Historique;
import classesPaie.OperateurC;
import classesPaie.Tables;

import java.io.IOException;
 import java.io.Serializable;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class DeleteBulletinB
   implements Serializable
 {
   private static final long serialVersionUID = -2835657924633121011L;
   private Date dateDebut;
   private Date dateFin;
   private String dateDebPrnt;
   private String dateFnPrnt;
   private OperateurC operateur;
   private ExerciceC exercice;
   private HttpSession session = HelperC.getSession();
   private String infoMsg;
   private int mois;
   
   private DroitC droit;
   
   public int getProgressValue() {
     return this.progressValue;
   }
   private List<BulletinPaieC> listeBulletin; private int progressValue;
   
   public void setProgressValue(int progressValue) {
     this.progressValue = progressValue;
   }
 
   
   public Date getDateDebut() {
     return this.dateDebut;
   }
 
   
   public void setDateDebut(Date dateDebut) {
     this.dateDebut = dateDebut;
   }
 
   
   public Date getDateFin() {
     return this.dateFin;
   }
 
   
   public void setDateFin(Date dateFin) {
     this.dateFin = dateFin;
   }
 
   
   public String getDateDebPrnt() {
     return this.dateDebPrnt;
   }
 
   
   public void setDateDebPrnt(String dateDebPrnt) {
     this.dateDebPrnt = dateDebPrnt;
   }
 
   
   public String getDateFnPrnt() {
     return this.dateFnPrnt;
   }
 
   
   public void setDateFnPrnt(String dateFnPrnt) {
     this.dateFnPrnt = dateFnPrnt;
   }
   
   public List<BulletinPaieC> getListeBulletin() {
     return this.listeBulletin;
   }
   
   public void setListeBulletin(List<BulletinPaieC> listeBulletin) {
     this.listeBulletin = listeBulletin;
   }
 
 
 public String getInfoMsg() {
	return infoMsg;
}


public void setInfoMsg(String infoMsg) {
	this.infoMsg = infoMsg;
}


public int getMois() {
	return mois;
}


public void setMois(int mois) {
	this.mois = mois;
}


@PostConstruct
   private void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     if (codeOperateur == null || codeExercice == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.bulletinPaie);
       }
     } 
   }
 
   
   public void changeDateDeb() {
     if (getDateDebPrnt().replace("/", "").replace("_", "").trim().equals("")) {
       
       setDateDebut(null);
     } else {
       
       setDateDebut(HelperC.validerDate(getDateDebPrnt()));
       if (getDateDebut() == null) {
         
         setDateDebPrnt("");
       } else {
         
         setDateDebPrnt(HelperC.convertDate(getDateDebut()));
       } 
     } 
   }
 
   
   public void changeDateFin() {
     if (getDateFnPrnt().replace("/", "").replace("_", "").trim().equals("")) {
       
       setDateFin(null);
     } else {
       
       setDateFin(HelperC.validerDate(getDateFnPrnt()));
       if (getDateFnPrnt() == null) {
         
         setDateFnPrnt("");
       } else {
         
         setDateFnPrnt(HelperC.convertDate(getDateFin()));
       } 
     } 
   }
 
   
   private void chargementBulletin() {
 
     this.listeBulletin = FactoryDAO.getInstance().getListBulletinPaie(0,mois, this.exercice.getId());
   }
 
 
   
	public void delete() throws InterruptedException {

		if (this.droit == null )
		{
			HelperC.afficherAttention("Information", "Il faut paramétrer les droits d'accès");
			return;
		}
			if(!this.droit.isSupprimer()) 
			{
				HelperC.afficherAttention("Information", "Vous n'avez pas le droit de supprimer");
				return;
			}
			if (mois > 0) {
				chargementBulletin();
				Historique hist = null;
				if (this.listeBulletin.size() > 0) {

					int i = 0;
					this.progressValue = 0;

					for (BulletinPaieC bulletin : this.listeBulletin) {

						hist = new Historique();
						hist.setDateOperation(new Date());
						hist.setOperateur(operateur);
						hist.setTable(Tables.getTableName(Tables.TableName.bulletinPaie));
						hist.setOperation("Suppression de la paie de " + bulletin.getNomEmploye() + " pour le mois de "
								+ HelperC.getMoisEnTouteLettre(bulletin.getMois()) + " : Net ="
								+ HelperC.decimalNumber(bulletin.getTotalNetPay(), 0, true));
						bulletin.setHistory(hist);

						FactoryDAO.getInstance().deleteBulletinPaie(bulletin);
						i++;
						this.progressValue = i * 100 / this.listeBulletin.size();
						Thread.sleep(60L);
					}
					HelperC.afficherMessage("Information", "Opération terminée !");
				}
				else
					HelperC.afficherAttention("Information", "Aucune information à supprimer");
			} else
				HelperC.afficherAttention("Information", "Il faut préciser le mois ");

	
			
	}
 }


