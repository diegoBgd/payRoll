/*    */ package classesPaie;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TypeCongeC
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -5149893284486573347L;
/*    */   private int id;
/*    */   private int sorteConge,nombreJour;
/*    */   private String code;
/*    */   private String designation;
/*    */   private Historique historique;
/*    */   private boolean annuel;
/*    */   
/*    */   public int getId() {
/* 17 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 20 */     this.id = id;
/*    */   }
/*    */   public String getCode() {
/* 23 */     return this.code;
/*    */   }
/*    */   public void setCode(String code) {
/* 26 */     this.code = code;
/*    */   }
/*    */   public String getDesignation() {
/* 29 */     return this.designation;
/*    */   }
/*    */   public void setDesignation(String designation) {
/* 32 */     this.designation = designation;
/*    */   }
/*    */   
/*    */   public Historique getHistorique() {
/* 36 */     return this.historique;
/*    */   }
/*    */   public void setHistorique(Historique historique) {
/* 39 */     this.historique = historique;
/*    */   }
/*    */   public int getSorteConge() {
/* 42 */     return this.sorteConge;
/*    */   }
/*    */   public void setSorteConge(int sorteConge) {
/* 45 */     this.sorteConge = sorteConge;
/*    */   }
/*    */   public boolean isAnnuel() {
/* 48 */     return this.annuel;
/*    */   }
/*    */   public void setAnnuel(boolean annuel) {
/* 51 */     this.annuel = annuel;
/*    */   }
			public int getNombreJour() {
				return nombreJour;
			}
			public void setNombreJour(int nombreJour) {
				this.nombreJour = nombreJour;
			}

/*    */ }


/* Location:              G:\PAIE\!\classesPaie\TypeCongeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */