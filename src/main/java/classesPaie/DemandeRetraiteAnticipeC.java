/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;

import classesPaie.Tables.TableName;
import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ public class DemandeRetraiteAnticipeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5276985280251846906L;
/*     */   private int id;
/*     */   private int anciennette;
/*     */   private int etat;
/*     */   private int idMotifDemande;
/*     */   private String dateDemandeS;
/*     */   private String dateDebutRetraiteS;
/*     */   private String dateDecisionS;
/*     */   private String libelleDemandeRetraite;
/*     */   private String libelleDecision;

/*     */   private Date dateDemande;
/*     */   private Date dateDebutRetraite;
/*     */   private Date dateDecision;
/*     */   private int decision;
/*     */   private Historique historique;
/*     */   private EmployeC employe;
/*     */   private boolean inLine;
/*     */   public int getId() {
/*  29 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  33 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getAnciennette() {
/*  37 */     return this.anciennette;
/*     */   }
/*     */   
/*     */   public void setAnciennette(int anciennette) {
/*  41 */     this.anciennette = anciennette;
/*     */   }
/*     */   
/*     */  
/*     */   public String getDateDemandeS() {
/*  53 */     return this.dateDemandeS;
/*     */   }
			public int getIdMotifDemande() {
				return idMotifDemande;
			}
			public void setIdMotifDemande(int idMotifDemande) {
				this.idMotifDemande = idMotifDemande;
			}
/*     */   
/*     */   public void setDateDemandeS(String dateDemandeS) {
/*  57 */     this.dateDemandeS = dateDemandeS;
/*     */   }
/*     */   
/*     */   public String getDateDebutRetraiteS() {
/*  61 */     return this.dateDebutRetraiteS;
/*     */   }
/*     */   
/*     */   public void setDateDebutRetraiteS(String dateDebutRetraiteS) {
/*  65 */     this.dateDebutRetraiteS = dateDebutRetraiteS;
/*     */   }
/*     */   
/*     */   public String getDateDecisionS() {
/*  69 */     return this.dateDecisionS;
/*     */   }
/*     */   
/*     */   public void setDateDecisionS(String dateDecisionS) {
/*  73 */     this.dateDecisionS = dateDecisionS;
/*     */   }
/*     */   
/*     */   public Date getDateDemande() {
/*  77 */     return this.dateDemande;
/*     */   }
/*     */   
/*     */   public void setDateDemande(Date dateDemande) {
/*  81 */     this.dateDemande = dateDemande;
/*     */   }
/*     */   
/*     */   public Date getDateDebutRetraite() {
/*  85 */     return this.dateDebutRetraite;
/*     */   }
/*     */   
/*     */   public void setDateDebutRetraite(Date dateDebutRetraite) {
/*  89 */     this.dateDebutRetraite = dateDebutRetraite;
/*     */   }
/*     */   
/*     */   public Date getDateDecision() {
/*  93 */     return this.dateDecision;
/*     */   }
/*     */   
/*     */   public void setDateDecision(Date dateDecision) {
/*  97 */     this.dateDecision = dateDecision;
/*     */   }
/*     */   
/*     */   public int getDecision() {
/* 101 */     return this.decision;
/*     */   }
/*     */   
/*     */   public void setDecision(int decision) {
/* 105 */     this.decision = decision;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/* 109 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 113 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public EmployeC getEmploye() {
/* 117 */     return this.employe;
/*     */   }
/*     */   
/*     */   public void setEmploye(EmployeC employe) {
/* 121 */     this.employe = employe;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelleDemandeRetraite() {
			libelleDemandeRetraite="";
	          if(getIdMotifDemande()>0)
	        	  libelleDemandeRetraite=FichierBaseDAO.getInstance().getBaseById(getIdMotifDemande(), Tables.getTableName(TableName.motifRetraite)).getDesignation();
/* 126 */     return this.libelleDemandeRetraite;
/*     */   }
/*     */   
/*     */   public void setLibelleDemandeRetraite(String libelleDemandeRetraite) {
/* 130 */     this.libelleDemandeRetraite = libelleDemandeRetraite;
/*     */   }
/*     */   

/*     */   public int getEtat() {
/* 143 */     return this.etat;
/*     */   }
/*     */   
/*     */   public void setEtat(int etat) {
/* 147 */     this.etat = etat;
/*     */   }
/*     */   
/*     */   public String getLibelleDecision() {
	          switch (decision) {
			case 1:
				libelleDecision="Accepté";
				break;

			case 2:
				libelleDecision="Refusé";
				break;
			}
/* 151 */     return this.libelleDecision;
/*     */   }
/*     */   
/*     */   public void setLibelleDecision(String libelleDecision) {
/* 155 */     this.libelleDecision = libelleDecision;
/*     */   }
			public boolean isInLine() {
				return inLine;
			}
			public void setInLine(boolean inLine) {
				this.inLine = inLine;
			}

/*     */ }

