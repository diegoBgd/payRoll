/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParametrageDecideurSignataireC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8411807025737927605L;
/*     */   private int id;
/*     */   private Historique historique;
/*     */   private Base personnel;
/*     */   private Base fonction;
/*     */   private Base directionUniversite;
/*     */   private DirectionC direction;
/*     */   private ServicesC service;
/*     */   private FaculteInstitutC faculte;
/*     */   private SousServiceC sousService;
/*     */   private int typeOperation;
/*  28 */   private List<ParametrageDecideurDetailC> listDetail = new ArrayList<ParametrageDecideurDetailC>();
/*  29 */   private List<ParametrageDecideurDetailC> listDeleted = new ArrayList<ParametrageDecideurDetailC>();
/*     */ 
/*     */   
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  37 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Historique getHistorique() {
/*  41 */     return this.historique;
/*     */   }
/*     */   
/*     */   public void setHistorique(Historique historique) {
/*  45 */     this.historique = historique;
/*     */   }
/*     */   
/*     */   public Base getPersonnel() {
/*  49 */     return this.personnel;
/*     */   }
/*     */   
/*     */   public void setPersonnel(Base personnel) {
/*  53 */     this.personnel = personnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public Base getFonction() {
/*  58 */     return this.fonction;
/*     */   }
/*     */   
/*     */   public void setFonction(Base fonction) {
/*  62 */     this.fonction = fonction;
/*     */   }
/*     */   
/*     */   public Base getDirectionUniversite() {
/*  66 */     return this.directionUniversite;
/*     */   }
/*     */   
/*     */   public void setDirectionUniversite(Base directionUniversite) {
/*  70 */     this.directionUniversite = directionUniversite;
/*     */   }
/*     */   
/*     */   public DirectionC getDirection() {
/*  74 */     return this.direction;
/*     */   }
/*     */   
/*     */   public void setDirection(DirectionC direction) {
/*  78 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public ServicesC getService() {
/*  82 */     return this.service;
/*     */   }
/*     */   
/*     */   public void setService(ServicesC service) {
/*  86 */     this.service = service;
/*     */   }
/*     */   
/*     */   public FaculteInstitutC getFaculte() {
/*  90 */     return this.faculte;
/*     */   }
/*     */   
/*     */   public void setFaculte(FaculteInstitutC faculte) {
/*  94 */     this.faculte = faculte;
/*     */   }
/*     */ 
/*     */   
/*     */   public SousServiceC getSousService() {
/*  99 */     return this.sousService;
/*     */   }
/*     */   
/*     */   public void setSousService(SousServiceC sousService) {
/* 103 */     this.sousService = sousService;
/*     */   }
/*     */   
/*     */   public List<ParametrageDecideurDetailC> getListDetail() {
/* 107 */     return this.listDetail;
/*     */   }
/*     */   
/*     */   public void setListDetail(List<ParametrageDecideurDetailC> listDetail) {
/* 111 */     this.listDetail = listDetail;
/*     */   }
/*     */   
/*     */   public List<ParametrageDecideurDetailC> getListDeleted() {
/* 115 */     return this.listDeleted;
/*     */   }
/*     */   
/*     */   public void setListDeleted(List<ParametrageDecideurDetailC> listDeleted) {
/* 119 */     this.listDeleted = listDeleted;
/*     */   }
/*     */   
/*     */   public int getTypeOperation() {
/* 123 */     return this.typeOperation;
/*     */   }
/*     */   
/*     */   public void setTypeOperation(int typeOperation) {
/* 127 */     this.typeOperation = typeOperation;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\ParametrageDecideurSignataireC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */