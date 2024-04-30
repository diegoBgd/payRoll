 package classesPaie;
 
 import java.io.Serializable;
 import java.util.Date;
 
 
 public class AffectationC
   implements Serializable
 {
   private static final long serialVersionUID = -3339259287458640254L;
   private int id;
   private int idFonction;
   private String reference;
   private String motPasse;
   private String confirmPwd;
   private String nomPrenom;
   private String matricule;
   private String comment;
   private Date dateDebut;
   private Date dateFin;
   private int idEmploye;
   private Base fonction;
   private Historique historique;
   private String dateDebutS;
   private String dateFinS;
   private boolean createEmploye;
   
   public int getId() {
     return this.id;
   }
 
   
   public void setId(int id) {
     this.id = id;
   }
 
   
   public String getReference() {
     return this.reference;
   }
 
   
   public void setReference(String reference) {
     this.reference = reference;
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
 
   
  
   public Base getFonction() {
     return this.fonction;
   }
 
   
   public void setFonction(Base fonction) {
     this.fonction = fonction;
   }
 
   
   public Historique getHistorique() {
     return this.historique;
   }
 
   
   public void setHistorique(Historique historique) {
     this.historique = historique;
   }
 
   
   public String getDateDebutS() {
     return this.dateDebutS;
   }
 
   
   public void setDateDebutS(String dateDebutS) {
     this.dateDebutS = dateDebutS;
   }
 
   
   public String getDateFinS() {
     return this.dateFinS;
   }
 
   
   public void setDateFinS(String dateFinS) {
     this.dateFinS = dateFinS;
   }
 
   
   public String getMotPasse() {
     return this.motPasse;
   }
 
   
   public void setMotPasse(String motPasse) {
     this.motPasse = motPasse;
   }
 
   
   public String getConfirmPwd() {
     return this.confirmPwd;
   }
 
   
   public void setConfirmPwd(String confirmPwd) {
     this.confirmPwd = confirmPwd;
   }
   
   public int getIdFonction() {
     return this.idFonction;
   }
   
   public void setIdFonction(int idFonction) {
     this.idFonction = idFonction;
   }
 
   

   public void setNomPrenom(String nomPrenom) {
     this.nomPrenom = nomPrenom;
   }
   
   public String getComment() {
     return this.comment;
   }
   
   public void setComment(String comment) {
     this.comment = comment;
   }
 
   

   public void setMatricule(String matricule) {
     this.matricule = matricule;
   }
			public int getIdEmploye() {
				return idEmploye;
			}
			public void setIdEmploye(int idEmploye) {
				this.idEmploye = idEmploye;
			}
 
   
   public boolean isCreateEmploye() {
     return this.createEmploye;
   }
 
   
   public void setCreateEmploye(boolean createEmploye) {
     this.createEmploye = createEmploye;
   }
 }


