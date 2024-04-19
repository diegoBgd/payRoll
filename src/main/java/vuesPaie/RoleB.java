/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.RoleC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class RoleB
/*     */   extends RoleC
/*     */ {
/*     */   private static final long serialVersionUID = 2643530410296697040L;
/*     */   private OperateurC operateur;
/*     */   private DroitC droit;
/*     */   private DroitC droit2;
/*  40 */   private HttpSession session = HelperC.getSession();
/*  41 */   private List<SelectItem> listeFonction = new ArrayList<SelectItem>();
/*  42 */   private List<DroitC> listeDroit = new ArrayList<DroitC>(); 
		   private int idFonction;
/*     */   private boolean userExist;
/*     */   private boolean selectAll;
/*     */   Base userFonction;
/*     */   ExerciceC exercice;
/*     */   
/*     */   public boolean isSelectAll() {
/*  49 */     return this.selectAll;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectAll(boolean selectAll) {
/*  54 */     this.selectAll = selectAll;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdFonction() {
/*  59 */     return this.idFonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdFonction(int idFonction) {
/*  64 */     this.idFonction = idFonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUserExist() {
/*  69 */     return this.userExist;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserExist(boolean userExist) {
/*  74 */     this.userExist = userExist;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListeFonction() {
/*  78 */     return this.listeFonction;
/*     */   }
/*     */   
/*     */   public void setListeFonction(List<SelectItem> listeFonction) {
/*  82 */     this.listeFonction = listeFonction;
/*     */   }
/*     */   
/*     */   public List<DroitC> getListeDroit() {
/*  86 */     return this.listeDroit;
/*     */   }
/*     */   
/*     */   public void setListeDroit(List<DroitC> listeDroit) {
/*  90 */     this.listeDroit = listeDroit;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/*  96 */     this.operateur = new OperateurC();
/*  97 */     this.exercice = new ExerciceC();
/*  98 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  99 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 100 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 101 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 102 */     String exist = "false";
/* 103 */     if (this.session.getAttribute("existUser") != null)
/*     */     {
/* 105 */       exist = this.session.getAttribute("existUser").toString();
/*     */     }
/* 107 */     if (exist != null)
/*     */     {
/* 109 */       this.userExist = Boolean.getBoolean(exist);
/*     */     }
/* 111 */     if (this.userExist)
/*     */     {
/* 113 */       if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */         
/*     */         try {
/* 117 */           FacesContext context = FacesContext.getCurrentInstance();
/* 118 */           context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */         }
/* 120 */         catch (IOException e) {
/*     */           
/* 122 */           e.printStackTrace();
/*     */         } 
/*     */       } else {
/*     */         
/* 126 */         this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 127 */         this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 128 */         if (this.userFonction != null)
/*     */         {
/* 130 */           this.droit2 = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.superUtilisateur);
/*     */         }
/*     */       } 
/*     */     }
/* 134 */     chargerDroit();
/* 135 */     this.listeFonction.add(new SelectItem(Integer.valueOf(0), " "));
/*     */     
/* 137 */     for (Base f : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.fonction)))
/*     */     {
/* 139 */       this.listeFonction.add(new SelectItem(Integer.valueOf(f.getId()), String.valueOf(f.getCode()) + " || " + f.getDesignation()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeFonction(ValueChangeEvent event) {
				
/* 146 */     this.idFonction = ((Integer)event.getNewValue()).intValue();
				for (DroitC drt : getDetails()) {
/*     */ 
/*     */         
/* 200 */         drt.setCreer(false);
/* 201 */         drt.setAfficher(false);
/* 202 */         drt.setSupprimer(false);
/* 203 */         drt.setModifier(false);
/*     */       } 
/* 147 */     setFonction(FichierBaseDAO.getInstance().getBaseById(this.idFonction, Tables.getTableName(Tables.TableName.fonction)));
/* 148 */     if (getFonction() != null) {
/*     */       
/* 150 */       this.listeDroit = FichierBaseDAO.getInstance().getListeDroit(getFonction());
/* 151 */       affecter();
/*     */     } else {
/*     */       
/* 154 */       clear(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chargerDroit() {
/* 161 */     for (int i = 1; i <= (Constante.Role.values()).length; i++) {
/*     */       
/* 163 */       this.droit = new DroitC();
/* 164 */       this.droit.setNumero(i);
/* 165 */       this.droit.setRol(Constante.getRole(i));
/* 166 */       this.droit.setLibelle(Constante.getLibelleRole(this.droit.getRol()));
/* 167 */       this.droit.setCreer(false);
/* 168 */       this.droit.setModifier(false);
/* 169 */       this.droit.setSupprimer(false);
/* 170 */       this.droit.setAfficher(false);
/* 171 */       getDetails().add(this.droit);
/* 172 */       this.droit.setDesactiveModifier(false);
/* 173 */       this.droit.setDesactiveSupprimer(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectAll() {
/* 182 */     if (this.selectAll) {
/*     */ 
/*     */       
/* 185 */       for (DroitC drt : getDetails())
/*     */       {
/*     */         
/* 188 */         drt.setCreer(true);
/* 189 */         drt.setAfficher(true);
/* 190 */         drt.setSupprimer(true);
/* 191 */         drt.setModifier(true);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 197 */       for (DroitC drt : getDetails()) {
/*     */ 
/*     */         
/* 200 */         drt.setCreer(false);
/* 201 */         drt.setAfficher(false);
/* 202 */         drt.setSupprimer(false);
/* 203 */         drt.setModifier(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void affecter() {
/* 211 */     if (this.listeDroit.size() > 0) {
/* 212 */       getDetails().clear();
/* 213 */       chargerDroit();
/* 214 */       for (DroitC d : this.listeDroit) {
/* 215 */         for (DroitC det : getDetails()) {
/* 216 */           if (d.getRol() == det.getRol()) {
/* 217 */             det.setId(d.getId());
/* 218 */             det.setCreer(d.isCreer());
/* 219 */             det.setModifier(d.isModifier());
/* 220 */             det.setSupprimer(d.isSupprimer());
/* 221 */             det.setAfficher(d.isAfficher());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveRole() {
/* 230 */     if (this.userExist) {
/*     */       
/* 232 */       if (this.droit2 != null) {
/*     */         
/* 234 */         if (isModification() && !this.droit2.isModifier()) {
/*     */           
/* 236 */           HelperC.afficherInformation("Information", "Vous n'avez pas le droit de Modifier");
/*     */         }
/* 238 */         else if (!isModification() && !this.droit2.isCreer()) {
/*     */           
/* 240 */           HelperC.afficherInformation("Information", "Vous n'avez pas le droit de Cr�er");
/*     */         }
/* 242 */         else if (getFonction() == null) {
/*     */           
/* 244 */           HelperC.afficherInformation("Information", "Pr�cisez la fonction");
/*     */         } else {
/*     */           
/* 247 */           Historique hist = new Historique();
/* 248 */           hist.setDateOperation(Calendar.getInstance().getTime());
/* 249 */           hist.setOperateur(this.operateur);
/* 250 */           if (!isModification()) {
/*     */             
/* 252 */             hist.setOperation("Cr�ation du r�le " + getFonction().getCode());
/*     */           } else {
/*     */             
/* 255 */             hist.setOperation("Modification du r�le " + getFonction().getCode());
/*     */           } 
/* 257 */           hist.setTable(Tables.getTableName(Tables.TableName.fonction));
/* 258 */           setHistorique(hist);
/* 259 */           if (FichierBaseDAO.getInstance().insertUpdateDroit(this)) {
/*     */             
/* 261 */             HelperC.afficherInformation("FELICITATION", "Succ�s de l'op�ration");
/* 262 */             initialiser();
/*     */           } else {
/*     */             
/* 265 */             HelperC.afficherErreur("D�sol�!", "Echec de l'op�ration");
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 270 */         Historique hist = new Historique();
/* 271 */         hist.setDateOperation(Calendar.getInstance().getTime());
/* 272 */         hist.setOperateur(this.operateur);
/* 273 */         if (!isModification()) {
/*     */           
/* 275 */           hist.setOperation("Cr�ation du r�le " + getFonction().getCode());
/*     */         } else {
/*     */           
/* 278 */           hist.setOperation("Modification du r�le " + getFonction().getCode());
/*     */         } 
/* 280 */         hist.setTable(Tables.getTableName(Tables.TableName.fonction));
/* 281 */         setHistorique(hist);
/* 282 */         if (FichierBaseDAO.getInstance().insertUpdateDroit(this)) {
/*     */           
/* 284 */           HelperC.afficherInformation("FELICITATION", "Succ�s de l'op�ration");
/* 285 */           initialiser();
/*     */         } else {
/*     */           
/* 288 */           HelperC.afficherErreur("D�sol�!", "Echec de l'op�ration");
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 293 */       Historique hist = new Historique();
/* 294 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 295 */       hist.setOperateur(this.operateur);
/* 296 */      
				if(this.getFonction()!=null)
				{
				if (!isModification()) {

					hist.setOperation("Cr�ation du r�le " + getFonction().getCode());
				} else {

					hist.setOperation("Modification du r�le " + getFonction().getCode());
				}
				hist.setTable(Tables.getTableName(Tables.TableName.fonction));
				setHistorique(hist);
/* 305 */       if (FichierBaseDAO.getInstance().insertUpdateDroit(this)) {
/*     */         
/* 307 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'op�ration");
/* 308 */         initialiser();
/*     */       } else {
/*     */         
/* 311 */         HelperC.afficherErreur("D�sol�!", "Echec de l'op�ration");
/*     */       } 
				}
				else
					  HelperC.afficherAttention("Attention", "Il faut pr�ciser la fonction");
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isModification() {
/* 317 */     boolean b = false;
/* 318 */     Iterator<DroitC> iterator = getDetails().iterator(); if (iterator.hasNext()) { DroitC det = iterator.next();
/* 319 */       if (det.getId() > 0) {
/* 320 */         b = true;
/*     */       } else {
/*     */         
/* 323 */         return b;
/*     */       }  }
/* 325 */      return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 331 */     if (b) {
/*     */       
/* 333 */       setFonction(null);
/* 334 */       this.idFonction = 0;
/*     */     } 
/* 336 */     getDetails().clear();
/* 337 */     chargerDroit();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 342 */     clear(true);
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\RoleB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */