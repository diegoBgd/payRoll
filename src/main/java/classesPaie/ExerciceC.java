/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExerciceC
/*    */   extends Base
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String exercicePrecedent,printDeb,printFin;
/*    */   private Date dateDebut;
/*    */   private Date dateFin;
/*    */   private Historique historique;
/*    */   
/*    */   public String getExercicePrecedent() {
/* 25 */     return this.exercicePrecedent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExercicePrecedent(String exercicePrecedent) {
/* 30 */     this.exercicePrecedent = exercicePrecedent;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateDebut() {
/* 35 */     return this.dateDebut;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateDebut(Date dateDebut) {
/* 40 */     this.dateDebut = dateDebut;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateFin() {
/* 45 */     return this.dateFin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDateFin(Date dateFin) {
/* 50 */     this.dateFin = dateFin;
/*    */   }
/*    */ 
/*    */   
/*    */   public Historique getHistorique() {
/* 55 */     return this.historique;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHistorique(Historique historique) {
/* 60 */     this.historique = historique;
/*    */   }
			public String getPrintDeb() {
				printDeb=HelperC.convertDate(getDateDebut());
				return printDeb;
			}
			public void setPrintDeb(String printDeb) {
				this.printDeb = printDeb;
			}
			public String getPrintFin() {
				printFin=HelperC.convertDate(getDateFin());
				return printFin;
			}
			public void setPrintFin(String printFin) {
				this.printFin = printFin;
			}

/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ExerciceC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */