/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.FichePresenceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
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
/*     */ public class FichePresenceB
/*     */   extends FichePresenceC
/*     */ {
/*     */   private static final long serialVersionUID = -2310124807870544746L;
/*     */   private int idEmploye;
/*     */   private String datePointageS;
/*  40 */   private List<SelectItem> listEmploye = new ArrayList<SelectItem>();
/*     */   private OperateurC operateur;
/*  42 */   private HttpSession session = HelperC.getSession(); private FichePresenceC presenceSelected; private DroitC droit;
/*     */   ExerciceC exercice;
/*     */   Base userFonction;
/*     */   
/*     */   public void init() {
/*  47 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  48 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  49 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  50 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/*  51 */     if (this.exercice == null || this.operateur == null) {
/*     */ 
/*     */       
/*     */       try {
/*  55 */         FacesContext context = FacesContext.getCurrentInstance();
/*  56 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/*  58 */       catch (IOException e) {
/*     */         
/*  60 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/*  64 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/*  65 */       if (this.userFonction != null)
/*     */       {
/*  67 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionAbsencePresence);
/*     */       }
/*  69 */       setExercise(this.exercice);
/*  70 */       setDatePointage(Calendar.getInstance().getTime());
/*  71 */       this.datePointageS = HelperC.convertDate(getDatePointage());
/*  72 */       this.listEmploye.add(new SelectItem(Integer.valueOf(0), ""));
/*     */       
/*  74 */       for (EmployeC emp : FactoryDAO.getInstance().getAllEmploye(false, 0))
/*     */       {
/*  76 */         this.listEmploye.add(new SelectItem(Integer.valueOf(emp.getId()), String.valueOf(emp.getNom()) + " " + emp.getPrenom()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIdEmploye() {
/*  84 */     return this.idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEmploye(int idEmploye) {
/*  89 */     this.idEmploye = idEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDatePointageS() {
/*  94 */     return this.datePointageS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDatePointageS(String datePointageS) {
/*  99 */     this.datePointageS = datePointageS;
/*     */   }
/*     */ 
/*     */   
/*     */   public FichePresenceC getPresenceSelected() {
/* 104 */     return this.presenceSelected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPresenceSelected(FichePresenceC presenceSelected) {
/* 109 */     this.presenceSelected = presenceSelected;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListEmploye() {
/* 113 */     return this.listEmploye;
/*     */   }
/*     */   
/*     */   public void setListEmploye(List<SelectItem> listEmploye) {
/* 117 */     this.listEmploye = listEmploye;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeEmploye(ValueChangeEvent event) {
/* 122 */     this.idEmploye = ((Integer)event.getNewValue()).intValue();
/* 123 */     setEmploye(FactoryDAO.getInstance().getEmploye(this.idEmploye, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeDatePointage() {
/* 128 */     if (this.datePointageS.replace("/", "").replace("_", "").trim().equals("")) {
/*     */       
/* 130 */       setDatePointage(null);
/*     */     } else {
/*     */       
/* 133 */       setDatePointage(HelperC.validerDate(this.datePointageS));
/* 134 */       if (getDatePointage() == null) {
/*     */         
/* 136 */         this.datePointageS = "";
/* 137 */         HelperC.afficherMessage("Information", "Date Invalide!");
/*     */       }
/* 139 */       else if (getDatePointage().after(Calendar.getInstance().getTime())) {
/*     */         
/* 141 */         this.datePointageS = "";
/* 142 */         HelperC.afficherMessage("Information", "Date Invalide!");
/*     */       } else {
/*     */         
/* 145 */         this.datePointageS = HelperC.convertDate(getDatePointage());
/*     */       } 
/*     */     } 
/* 148 */     showPresenceSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeHeureEntree() {
/* 153 */     if ((getHeureEntreee() != "" || getHeureEntreee() != null) && getHeureEntreee() != "" && !getHeureEntreee().replace("_", "").trim().replace(":", "").equals(""))
/*     */     {
/* 155 */       if (Integer.parseInt(getHeureEntreee().split(":")[0]) > 24 || Integer.parseInt(getHeureEntreee().split(":")[1]) > 59) {
/*     */         
/* 157 */         HelperC.afficherMessage("Information", "Heure Invalide!");
/*     */       } else {
/*     */         
/* 160 */         setHeureEntreee(getHeureEntreee());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeHeureSortie() {
/* 167 */     if ((getHeureSortie() != "" || getHeureSortie() != null) && getHeureSortie() != "" && !getHeureSortie().replace("_", "").trim().replace(":", "").equals(""))
/*     */     {
/* 169 */       if (Integer.parseInt(getHeureSortie().split(":")[0]) > 24 || Integer.parseInt(getHeureSortie().split(":")[1]) > 59) {
/*     */         
/* 171 */         HelperC.afficherMessage("Information", "Heure Invalide!");
/*     */       } else {
/*     */         
/* 174 */         setHeureSortie(getHeureSortie());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeTypeHeuresPrestees() {
/* 181 */     showPresenceSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 186 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 188 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 190 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 192 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 194 */     else if (getEmploye() == null) {
/*     */       
/* 196 */       HelperC.afficherMessage("Information", "Veuillez d'abord pr�ciser l'employ�!", FacesMessage.SEVERITY_ERROR);
/*     */     }
/* 198 */     else if (getDatePointage() == null) {
/*     */       
/* 200 */       HelperC.afficherMessage("Information", "Veuillez saisir au moins la date de pointage SVP!", FacesMessage.SEVERITY_ERROR);
/*     */     }
/* 202 */     else if (getIdTypeHeurePreste() == 0) {
/*     */       
/* 204 */       HelperC.afficherMessage("Information", "Veuillez pr�ciser le type d'heures prest�es SVP!", FacesMessage.SEVERITY_ERROR);
/*     */     } else {
/*     */       
/* 207 */       Historique hist = new Historique();
/* 208 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 209 */       hist.setOperateur(this.operateur);
/* 210 */       if (getId() == 0) {
/*     */         
/* 212 */         hist.setOperation("Pointage de l'employ� " + getEmploye().getCode());
/*     */       } else {
/*     */         
/* 215 */         hist.setOperation("Modification du pointage de l'employ� " + getEmploye().getCode());
/*     */       } 
/* 217 */       hist.setTable(Tables.getTableName(Tables.TableName.pointagePresence));
/* 218 */       setHistoric(hist);
/* 219 */       if (FactoryDAO.getInstance().insertUpdatePointagePresence(this)) {
/*     */         
/* 221 */         initialiser();
/* 222 */         HelperC.afficherMessage("Information", "Succ�s de l'Op�ration!", FacesMessage.SEVERITY_INFO);
/*     */       } else {
/*     */         
/* 225 */         HelperC.afficherMessage("Information", "Echec de l'Op�ration!", FacesMessage.SEVERITY_ERROR);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 232 */     if (getId() == 0) {
/*     */       
/* 234 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 236 */     else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */       
/* 238 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 240 */     else if (getId() > 0) {
/*     */       
/* 242 */       Historique hist = new Historique();
/* 243 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 244 */       hist.setOperateur(this.operateur);
/* 245 */       if (getId() == 0) {
/*     */         
/* 247 */         hist.setOperation("Pointage de l'employ� " + getEmploye().getCode());
/*     */       } else {
/*     */         
/* 250 */         hist.setOperation("Modification du pointage de l'employ� " + getEmploye().getCode());
/*     */       } 
/* 252 */       hist.setTable(Tables.getTableName(Tables.TableName.pointagePresence));
/* 253 */       setHistoric(hist);
/* 254 */       if (getIdTypeHeurePreste() > 0) {
/*     */         
/* 256 */         if (FactoryDAO.getInstance().deletePointagePresence(this)) {
/*     */           
/* 258 */           initialiser();
/* 259 */           HelperC.afficherMessage("Information", "Succ�s de l'Op�ration!", FacesMessage.SEVERITY_INFO);
/*     */         } else {
/*     */           
/* 262 */           HelperC.afficherMessage("Information", "Echec de l'Op�ration!", FacesMessage.SEVERITY_ERROR);
/*     */         } 
/*     */       } else {
/*     */         
/* 266 */         HelperC.afficherMessage("Information", "Il faut pr�ciser le type d'heures prest�es!", FacesMessage.SEVERITY_ERROR);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void showPresenceSelected() {
/* 273 */     if (getEmploye() != null && getDatePointage() != null) {
/*     */       
/* 275 */       this.presenceSelected = FactoryDAO.getInstance().getFichePresenceParEmployeEtDatePointage(getEmploye().getId(), HelperC.convertDat(getDatePointage()), getIdTypeHeurePreste());
/* 276 */       if (this.presenceSelected != null) {
/*     */         
/* 278 */         setId(this.presenceSelected.getId());
/* 279 */         setIdTypeHeurePreste(this.presenceSelected.getIdTypeHeurePreste());
/* 280 */         if (this.presenceSelected.getEmploye() != null) {
/*     */           
/* 282 */           this.idEmploye = this.presenceSelected.getEmploye().getId();
/* 283 */           setEmploye(this.presenceSelected.getEmploye());
/*     */         } else {
/*     */           
/* 286 */           this.idEmploye = 0;
/* 287 */           setEmploye(null);
/*     */         } 
/* 289 */         if (this.presenceSelected.getDatePointage() != null) {
/*     */           
/* 291 */           setDatePointage(this.presenceSelected.getDatePointage());
/* 292 */           this.datePointageS = HelperC.convertDate(this.presenceSelected.getDatePointage());
/*     */         } else {
/*     */           
/* 295 */           setDatePointage(null);
/* 296 */           this.datePointageS = "";
/*     */         } 
/* 298 */         setHeureEntreee(this.presenceSelected.getHeureEntreee());
/* 299 */         setHeureSortie(this.presenceSelected.getHeureSortie());
/*     */       } else {
/*     */         
/* 302 */         clearInfo(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearInfo(boolean b) {
/* 309 */     setId(0);
/* 310 */     if (b) {
/*     */       
/* 312 */       this.idEmploye = 0;
/* 313 */       setEmploye(null);
/* 314 */       setDatePointage(Calendar.getInstance().getTime());
/* 315 */       this.datePointageS = HelperC.convertDate(getDatePointage());
/*     */     } 
/* 317 */     setHeureEntreee("");
/* 318 */     setHeureSortie("");
/* 319 */     setIdTypeHeurePreste(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 324 */     clearInfo(true);
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\FichePresenceB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */