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
/*     */ public class ParametrageGeneralC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3623199434878924818L;
/*     */   private int id;
/*     */   private int nbreDecimal;
/*     */   private int dureCourTerme;
/*     */   private int dureLongTerme;
/*     */   private int dureMoyenTerme;
/*     */   private int nbreHeureMois;
/*     */   private int nbreHeureJour;
/*     */   private int nbreJourMois;
/*     */   private double tauxMaxLogement;
/*     */   private double montantNetMin;
/*     */   private double tauxJrFerie;
/*     */   private boolean allocationBaseHsup;
/*     */   private boolean logementBaseHsup;
/*     */   private String mailOrigine,pwdOrigine,smtpServer,port;
/*     */   public int getId() {
/*  30 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  35 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNbreDecimal() {
/*  40 */     return this.nbreDecimal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNbreDecimal(int nbreDecimal) {
/*  45 */     this.nbreDecimal = nbreDecimal;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxMaxLogement() {
/*  50 */     return this.tauxMaxLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxMaxLogement(double tauxMaxLogement) {
/*  55 */     this.tauxMaxLogement = tauxMaxLogement;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllocationBaseHsup() {
/*  60 */     return this.allocationBaseHsup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllocationBaseHsup(boolean allocationBaseHsup) {
/*  65 */     this.allocationBaseHsup = allocationBaseHsup;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLogementBaseHsup() {
/*  70 */     return this.logementBaseHsup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogementBaseHsup(boolean logementBaseHsup) {
/*  75 */     this.logementBaseHsup = logementBaseHsup;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDureCourTerme() {
/*  80 */     return this.dureCourTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDureCourTerme(int dureCourTerme) {
/*  85 */     this.dureCourTerme = dureCourTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDureLongTerme() {
/*  90 */     return this.dureLongTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDureLongTerme(int dureLongTerme) {
/*  95 */     this.dureLongTerme = dureLongTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDureMoyenTerme() {
/* 100 */     return this.dureMoyenTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDureMoyenTerme(int dureMoyenTerme) {
/* 105 */     this.dureMoyenTerme = dureMoyenTerme;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMontantNetMin() {
/* 110 */     return this.montantNetMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMontantNetMin(double montantNetMin) {
/* 115 */     this.montantNetMin = montantNetMin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNbreHeureMois() {
/* 120 */     return this.nbreHeureMois;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNbreHeureMois(int nbreHeureMois) {
/* 125 */     this.nbreHeureMois = nbreHeureMois;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNbreHeureJour() {
/* 130 */     return this.nbreHeureJour;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNbreHeureJour(int nbreHeureJour) {
/* 135 */     this.nbreHeureJour = nbreHeureJour;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNbreJourMois() {
/* 140 */     return this.nbreJourMois;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNbreJourMois(int nbreJourMois) {
/* 145 */     this.nbreJourMois = nbreJourMois;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxJrFerie() {
/* 150 */     return this.tauxJrFerie;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxJrFerie(double tauxJrFerie) {
/* 155 */     this.tauxJrFerie = tauxJrFerie;
/*     */   }
public String getMailOrigine() {
	return mailOrigine;
}
public void setMailOrigine(String mailOrigine) {
	this.mailOrigine = mailOrigine;
}
public String getPwdOrigine() {
	return pwdOrigine;
}
public void setPwdOrigine(String pwdOrigine) {
	this.pwdOrigine = pwdOrigine;
}
public String getSmtpServer() {
	return smtpServer;
}
public void setSmtpServer(String smtpServer) {
	this.smtpServer = smtpServer;
}
public String getPort() {
	return port;
}
public void setPort(String port) {
	this.port = port;
}


/*     */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageGeneralC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */