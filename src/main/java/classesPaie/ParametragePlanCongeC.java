/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParametragePlanCongeC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4744495137381872850L;
/*     */   private int id;
/*     */   private int nombreEmploye,idExercice;
/*     */     
/*     */   private Historique historique;
/*     */   
/*     */   private DirectionC direction;
/*     */   private ServicesC service;
/*     */   
/*     */    private String libelleServie;
/*     */   public int getId() {
/*  29 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  33 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getNombreEmploye() {
/*  37 */     return this.nombreEmploye;
/*     */   }
/*     */   
/*     */ 	public void setNombreEmploye(int nombreEmploye) {
				this.nombreEmploye = nombreEmploye;
			}
/*     */   
/*     */   public Historique getHistorique() {
/*  61 */     return this.historique;
/*     */   }

/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  65 */     this.historique = historique;
/*     */   }
/*     */  
/*     */   
/*     */   public DirectionC getDirection() {
/*  77 */     return this.direction;
/*     */   }
/*     */   
/*     */   public void setDirection(DirectionC direction) {
/*  81 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public ServicesC getService() {
/*  85 */     return this.service;
/*     */   }
/*     */   
/*     */   public void setService(ServicesC service) {
/*  89 */     this.service = service;
/*     */   }
/*     */   
/*     */   
			public String getLibelleServie() {
				return libelleServie;
			}
			public void setLibelleServie(String libelleServie) {
				this.libelleServie = libelleServie;
			}
			public int getIdExercice() {
				return idExercice;
			}
			public void setIdExercice(int idExercice) {
				this.idExercice = idExercice;
			}
			
			
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\ParametragePlanCongeC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */