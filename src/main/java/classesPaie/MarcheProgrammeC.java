/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarcheProgrammeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1058098683753291434L;
/*     */   private int id;
/*     */   private int heureNormaleEmploye;
/*     */   private int heureNormaleJour;
/*     */   private int nombreJoursConge;
/*     */   private int prochainNumeroDossier;
/*     */   private int nombreJoursCommercial;
/*     */   private int tauxCourtTerme;
/*     */   private int tauxMoyenTerme;
/*     */   private int joursSupplementaire;
/*     */   private int nombreAnneeJourSuppl;
/*     */   private double tauxInteretCredit;
/*     */   private double tauxTaxeInteret;
/*     */   private double tauxImpotForfaitaire;
/*     */   private double tauxSuperieurImpotForfaitaire;
/*     */   private double trancheMontantImpotForfaitaire;
/*     */   private double allocationMensuelleConjoint;
/*     */   private double allocationMensuelleEnfant;
/*     */   private double pourcentageLogementRajout;
/*     */   private double pourcentageForfaitLogement;
/*     */   private double valeurAchat;
/*     */   private double tauxIndice;
/*     */   private double coefficientMultiplicateur;
/*     */   private double baseTAInferieur;
/*     */   private double baseTASuperieur;
/*     */   private double tauxCongeMaladie;
/*     */   private double tauxCongeMaternite;
/*     */   private double tauxMinIndemniteLogement;
/*     */   private double fraisMedicauxEmploye;
/*     */   private double fraisMedicauxCharge;
/*     */   private double salaireBaseMaxHeureSuppl;
/*     */   private double heurePause;
/*     */   private boolean congeMensuel;
/*     */   private boolean penaliteConge;
/*     */   private boolean presenceJournalier;
/*     */   private boolean heureNuitSamedi;
/*     */   private Historique historique;
/*     */   
/*     */   public int getId() {
/*  56 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  61 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxInteretCredit() {
/*  66 */     return this.tauxInteretCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxInteretCredit(double tauxInteretCredit) {
/*  71 */     this.tauxInteretCredit = tauxInteretCredit;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxTaxeInteret() {
/*  76 */     return this.tauxTaxeInteret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxTaxeInteret(double tauxTaxeInteret) {
/*  81 */     this.tauxTaxeInteret = tauxTaxeInteret;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxImpotForfaitaire() {
/*  86 */     return this.tauxImpotForfaitaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxImpotForfaitaire(double tauxImpotForfaitaire) {
/*  91 */     this.tauxImpotForfaitaire = tauxImpotForfaitaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxSuperieurImpotForfaitaire() {
/*  96 */     return this.tauxSuperieurImpotForfaitaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxSuperieurImpotForfaitaire(double tauxSuperieurImpotForfaitaire) {
/* 101 */     this.tauxSuperieurImpotForfaitaire = tauxSuperieurImpotForfaitaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTrancheMontantImpotForfaitaire() {
/* 106 */     return this.trancheMontantImpotForfaitaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTrancheMontantImpotForfaitaire(double trancheMontantImpotForfaitaire) {
/* 111 */     this.trancheMontantImpotForfaitaire = trancheMontantImpotForfaitaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeureNormaleEmploye() {
/* 116 */     return this.heureNormaleEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureNormaleEmploye(int heureNormaleEmploye) {
/* 121 */     this.heureNormaleEmploye = heureNormaleEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeureNormaleJour() {
/* 126 */     return this.heureNormaleJour;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureNormaleJour(int heureNormaleJour) {
/* 131 */     this.heureNormaleJour = heureNormaleJour;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNombreJoursConge() {
/* 136 */     return this.nombreJoursConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreJoursConge(int nombreJoursConge) {
/* 141 */     this.nombreJoursConge = nombreJoursConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAllocationMensuelleConjoint() {
/* 146 */     return this.allocationMensuelleConjoint;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllocationMensuelleConjoint(double allocationMensuelleConjoint) {
/* 151 */     this.allocationMensuelleConjoint = allocationMensuelleConjoint;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAllocationMensuelleEnfant() {
/* 156 */     return this.allocationMensuelleEnfant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllocationMensuelleEnfant(double allocationMensuelleEnfant) {
/* 161 */     this.allocationMensuelleEnfant = allocationMensuelleEnfant;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentageLogementRajout() {
/* 166 */     return this.pourcentageLogementRajout;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageLogementRajout(double pourcentageLogementRajout) {
/* 171 */     this.pourcentageLogementRajout = pourcentageLogementRajout;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPourcentageForfaitLogement() {
/* 176 */     return this.pourcentageForfaitLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPourcentageForfaitLogement(double pourcentageForfaitLogement) {
/* 181 */     this.pourcentageForfaitLogement = pourcentageForfaitLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getValeurAchat() {
/* 186 */     return this.valeurAchat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValeurAchat(double valeurAchat) {
/* 191 */     this.valeurAchat = valeurAchat;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxIndice() {
/* 196 */     return this.tauxIndice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxIndice(double tauxIndice) {
/* 201 */     this.tauxIndice = tauxIndice;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getCoefficientMultiplicateur() {
/* 206 */     return this.coefficientMultiplicateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCoefficientMultiplicateur(double coefficientMultiplicateur) {
/* 211 */     this.coefficientMultiplicateur = coefficientMultiplicateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBaseTAInferieur() {
/* 216 */     return this.baseTAInferieur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseTAInferieur(double baseTAInferieur) {
/* 221 */     this.baseTAInferieur = baseTAInferieur;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBaseTASuperieur() {
/* 226 */     return this.baseTASuperieur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseTASuperieur(double baseTASuperieur) {
/* 231 */     this.baseTASuperieur = baseTASuperieur;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxCongeMaladie() {
/* 236 */     return this.tauxCongeMaladie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxCongeMaladie(double tauxCongeMaladie) {
/* 241 */     this.tauxCongeMaladie = tauxCongeMaladie;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxCongeMaternite() {
/* 246 */     return this.tauxCongeMaternite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxCongeMaternite(double tauxCongeMaternite) {
/* 251 */     this.tauxCongeMaternite = tauxCongeMaternite;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxMinIndemniteLogement() {
/* 256 */     return this.tauxMinIndemniteLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMinIndemniteLogement(double tauxMinIndemniteLogement) {
/* 261 */     this.tauxMinIndemniteLogement = tauxMinIndemniteLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getFraisMedicauxEmploye() {
/* 266 */     return this.fraisMedicauxEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFraisMedicauxEmploye(double fraisMedicauxEmploye) {
/* 271 */     this.fraisMedicauxEmploye = fraisMedicauxEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getFraisMedicauxCharge() {
/* 276 */     return this.fraisMedicauxCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFraisMedicauxCharge(double fraisMedicauxCharge) {
/* 281 */     this.fraisMedicauxCharge = fraisMedicauxCharge;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSalaireBaseMaxHeureSuppl() {
/* 286 */     return this.salaireBaseMaxHeureSuppl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSalaireBaseMaxHeureSuppl(double salaireBaseMaxHeureSuppl) {
/* 291 */     this.salaireBaseMaxHeureSuppl = salaireBaseMaxHeureSuppl;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCongeMensuel() {
/* 296 */     return this.congeMensuel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCongeMensuel(boolean congeMensuel) {
/* 301 */     this.congeMensuel = congeMensuel;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPenaliteConge() {
/* 306 */     return this.penaliteConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPenaliteConge(boolean penaliteConge) {
/* 311 */     this.penaliteConge = penaliteConge;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPresenceJournalier() {
/* 316 */     return this.presenceJournalier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPresenceJournalier(boolean presenceJournalier) {
/* 321 */     this.presenceJournalier = presenceJournalier;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHeureNuitSamedi() {
/* 326 */     return this.heureNuitSamedi;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeureNuitSamedi(boolean heureNuitSamedi) {
/* 331 */     this.heureNuitSamedi = heureNuitSamedi;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProchainNumeroDossier() {
/* 336 */     return this.prochainNumeroDossier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProchainNumeroDossier(int prochainNumeroDossier) {
/* 341 */     this.prochainNumeroDossier = prochainNumeroDossier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNombreJoursCommercial() {
/* 346 */     return this.nombreJoursCommercial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreJoursCommercial(int nombreJoursCommercial) {
/* 351 */     this.nombreJoursCommercial = nombreJoursCommercial;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTauxCourtTerme() {
/* 356 */     return this.tauxCourtTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxCourtTerme(int tauxCourtTerme) {
/* 361 */     this.tauxCourtTerme = tauxCourtTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTauxMoyenTerme() {
/* 366 */     return this.tauxMoyenTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMoyenTerme(int tauxMoyenTerme) {
/* 371 */     this.tauxMoyenTerme = tauxMoyenTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public Historique getHistorique() {
/* 376 */     return this.historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHistorique(Historique historique) {
/* 381 */     this.historique = historique;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getJoursSupplementaire() {
/* 386 */     return this.joursSupplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJoursSupplementaire(int joursSupplementaire) {
/* 391 */     this.joursSupplementaire = joursSupplementaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNombreAnneeJourSuppl() {
/* 396 */     return this.nombreAnneeJourSuppl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNombreAnneeJourSuppl(int nombreAnneeJourSuppl) {
/* 401 */     this.nombreAnneeJourSuppl = nombreAnneeJourSuppl;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getHeurePause() {
/* 406 */     return this.heurePause;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeurePause(double heurePause) {
/* 411 */     this.heurePause = heurePause;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\MarcheProgrammeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */