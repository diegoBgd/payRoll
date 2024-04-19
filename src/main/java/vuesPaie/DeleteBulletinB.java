/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.BulletinPaieC;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class DeleteBulletinB
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2835657924633121011L;
/*     */   private Date dateDebut;
/*     */   private Date dateFin;
/*     */   private String dateDebPrnt;
/*     */   private String dateFnPrnt;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  39 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   private DroitC droit;
/*     */   
/*     */   public int getProgressValue() {
/*  44 */     return this.progressValue;
/*     */   }
/*     */   private List<BulletinPaieC> listeBulletin; private int progressValue;
/*     */   
/*     */   public void setProgressValue(int progressValue) {
/*  49 */     this.progressValue = progressValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateDebut() {
/*  54 */     return this.dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  59 */     this.dateDebut = dateDebut;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFin() {
/*  64 */     return this.dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/*  69 */     this.dateFin = dateFin;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateDebPrnt() {
/*  74 */     return this.dateDebPrnt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateDebPrnt(String dateDebPrnt) {
/*  79 */     this.dateDebPrnt = dateDebPrnt;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFnPrnt() {
/*  84 */     return this.dateFnPrnt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFnPrnt(String dateFnPrnt) {
/*  89 */     this.dateFnPrnt = dateFnPrnt;
/*     */   }
/*     */   
/*     */   public List<BulletinPaieC> getListeBulletin() {
/*  93 */     return this.listeBulletin;
/*     */   }
/*     */   
/*     */   public void setListeBulletin(List<BulletinPaieC> listeBulletin) {
/*  97 */     this.listeBulletin = listeBulletin;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/* 103 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 104 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 105 */     if (codeOperateur == null || codeExercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 109 */         FacesContext context = FacesContext.getCurrentInstance();
/* 110 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 112 */       catch (IOException e) {
/*     */         
/* 114 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 118 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 119 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 120 */       Base userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 121 */       if (userFonction != null)
/*     */       {
/* 123 */         this.droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), Constante.Role.bulletinPaie);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateDeb() {
/* 130 */     if (getDateDebPrnt().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 132 */       setDateDebut(null);
/*     */     } else {
/*     */       
/* 135 */       setDateDebut(HelperC.validerDate(getDateDebPrnt()));
/* 136 */       if (getDateDebut() == null) {
/*     */         
/* 138 */         setDateDebPrnt("");
/*     */       } else {
/*     */         
/* 141 */         setDateDebPrnt(HelperC.convertDate(getDateDebut()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDateFin() {
/* 148 */     if (getDateFnPrnt().replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 150 */       setDateFin(null);
/*     */     } else {
/*     */       
/* 153 */       setDateFin(HelperC.validerDate(getDateFnPrnt()));
/* 154 */       if (getDateFnPrnt() == null) {
/*     */         
/* 156 */         setDateFnPrnt("");
/*     */       } else {
/*     */         
/* 159 */         setDateFnPrnt(HelperC.convertDate(getDateFin()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargementBulletin() {
/* 166 */     this.listeBulletin = FactoryDAO.getInstance().getListBulletinPaie(0, this.dateDebut, this.dateFin, this.exercice.getId());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() throws InterruptedException {
/* 172 */     if (this.droit != null && this.droit.isSupprimer()) {
/*     */       
/* 174 */       chargementBulletin();
/* 175 */       if (this.listeBulletin.size() > 0) {
/*     */         
/* 177 */         int i = 0;
/* 178 */         this.progressValue = 0;
/*     */         
/* 180 */         for (BulletinPaieC bulletin : this.listeBulletin) {
/*     */           
/* 182 */           FactoryDAO.getInstance().deleteBulletinPaie(bulletin);
/* 183 */           i++;
/* 184 */           this.progressValue = i * 100 / this.listeBulletin.size();
/* 185 */           Thread.sleep(60L);
/*     */         } 
/*     */       } 
/*     */       
/* 189 */       HelperC.afficherMessage("Information", "Op�ration termin�e !");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\DeleteBulletinB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */