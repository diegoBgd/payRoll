/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import classesPaie.TypeFraisMedicauxC;
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class TypeFraisMedicauxB
/*     */   extends TypeFraisMedicauxC
/*     */ {
/*     */   private static final long serialVersionUID = 2263001459723292564L;
/*     */   private TypeFraisMedicauxC typeFraisMedicaux;
/*     */   private List<TypeFraisMedicauxC> listTypeFraisMedicaux;
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*     */   private DroitC droit;
/*  37 */   private HttpSession session = HelperC.getSession();
/*     */   
/*     */   Base userFonction;
/*     */   
/*     */   public TypeFraisMedicauxC getTypeFraisMedicaux() {
/*  42 */     return this.typeFraisMedicaux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeFraisMedicaux(TypeFraisMedicauxC typeFraisMedicaux) {
/*  47 */     this.typeFraisMedicaux = typeFraisMedicaux;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  53 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  58 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/*  63 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/*  68 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public List<TypeFraisMedicauxC> getListTypeFraisMedicaux() {
/*  72 */     return this.listTypeFraisMedicaux;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListTypeFraisMedicaux(List<TypeFraisMedicauxC> listTypeFraisMedicaux) {
/*  77 */     this.listTypeFraisMedicaux = listTypeFraisMedicaux;
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   private void init() {
/*  83 */     String codeExercice = (String)this.session.getAttribute("exercice");
/*  84 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/*  85 */     if (codeOperateur == null || codeExercice == null) {
/*     */ 
/*     */       
/*     */       try {
/*  89 */         FacesContext context = FacesContext.getCurrentInstance();
/*  90 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/*  92 */       catch (IOException e) {
/*     */         
/*  94 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/*  98 */       this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*  99 */       this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 100 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 101 */       if (this.userFonction != null)
/*     */       {
/* 103 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.fichierBase);
/*     */       }
/* 105 */       charger();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void charger() {
/* 111 */     this.listTypeFraisMedicaux = FichierBaseDAO.getInstance().getAllTypeFraisMedicaux();
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 116 */     if (b)
/*     */     {
/* 118 */       setCode("");
/*     */     }
/* 120 */     setId(0);
/* 121 */     setDesignation("");
/* 122 */     setPourcentageS("");
/* 123 */     setMontantS("");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 128 */     if (this.typeFraisMedicaux != null) {
/*     */       
/* 130 */       setId(this.typeFraisMedicaux.getId());
/* 131 */       setCode(this.typeFraisMedicaux.getCode());
/* 132 */       setDesignation(this.typeFraisMedicaux.getDesignation());
/* 133 */       setPourcentage(this.typeFraisMedicaux.getPourcentage());
/* 134 */       setPourcentageS(HelperC.TraitementMontant.getMontantFormate(getPourcentage(), 2));
/* 135 */       setMontant(this.typeFraisMedicaux.getMontant());
/* 136 */       setMontantS(HelperC.TraitementMontant.getMontantFormate(getMontant(), 2));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 142 */     if (getCode().trim().equals("")) {
/*     */       
/* 144 */       clear(true);
/*     */     } else {
/*     */       
/* 147 */       this.typeFraisMedicaux = FichierBaseDAO.getInstance().getTypeFraisMedicaux(getCode());
/* 148 */       if (this.typeFraisMedicaux == null) {
/*     */         
/* 150 */         clear(false);
/*     */       } else {
/*     */         
/* 153 */         setObject();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelected(SelectEvent event) {
/* 160 */     this.typeFraisMedicaux = (TypeFraisMedicauxC)event.getObject();
/* 161 */     if (this.typeFraisMedicaux != null)
/*     */     {
/* 163 */       setObject();
/*     */     }
/*     */   }
/*     */   
/*     */   public void changePourcentage() {
/*     */     try {
/* 169 */       setPourcentage(Integer.valueOf(getPourcentageS().replace("_", "").replace(" ", "").replace(",", ".").trim()).intValue());
/*     */     }
/* 171 */     catch (Exception e) {
/* 172 */       setPourcentage(0);
/*     */     } finally {
/*     */       
/* 175 */       setPourcentageS(HelperC.TraitementMontant.getMontantFormate(getPourcentage(), 2));
/* 176 */       setPourcentage(Integer.valueOf(getPourcentageS().replace("_", "").replace(" ", "").replace(",", ".").trim()).intValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeMontant() {
/*     */     try {
/* 182 */       setMontant(Double.valueOf(getMontantS().replace("-", "").replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     }
/* 184 */     catch (Exception e) {
/* 185 */       setMontant(0.0D);
/*     */     } finally {
/*     */       
/* 188 */       setMontantS(HelperC.TraitementMontant.getMontantFormate(getMontant(), 2));
/* 189 */       setMontant(Double.valueOf(getMontantS().replace(" ", "").replace(",", ".").trim()).doubleValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void enregistrer() {
/* 194 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 196 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 198 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 200 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 202 */     else if (getCode().trim().equals("") || getDesignation().trim().equals("")) {
/*     */       
/* 204 */       HelperC.afficherMessage("Information", "Completez tous les champs");
/*     */     }
/* 206 */     else if (FichierBaseDAO.getInstance().getTypeFraisMedicaux(getCode(), getId()) != null) {
/*     */       
/* 208 */       HelperC.afficherMessage("Information", "Ce code existe d�j� ");
/*     */     }
/* 210 */     else if (FichierBaseDAO.getInstance().getTypeFraisMedicauxByDesignation(getDesignation(), getId()) != null) {
/*     */       
/* 212 */       HelperC.afficherMessage("Information", "Ce d�signation existe d�j� ");
/*     */     } else {
/*     */       
/* 215 */       Historique hist = new Historique();
/* 216 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 217 */       hist.setOperateur(this.operateur);
/* 218 */       if (getId() == 0) {
/*     */         
/* 220 */         hist.setOperation("Cr�ation du type de frais m�dical " + getCode());
/*     */       } else {
/*     */         
/* 223 */         hist.setOperation("Modification du type de frais m�dical " + getCode());
/*     */       } 
/* 225 */       hist.setTable(Tables.getTableName(Tables.TableName.typeFraisMedicaux));
/* 226 */       setHistorique(hist);
/* 227 */       if (FichierBaseDAO.getInstance().insertUpdateTypeFraisMedicaux(this)) {
/*     */         
/* 229 */         clear(true);
/* 230 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration ");
/* 231 */         charger();
/*     */       } else {
/*     */         
/* 234 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/* 241 */     if (getId() == 0) {
/*     */       
/* 243 */       HelperC.afficherMessage("Information", "Pr�cisez le type de frais M�dicaux � supprimer");
/*     */     }
/* 245 */     else if (getId() > 0 && !this.droit.isSupprimer()) {
/*     */       
/* 247 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
/*     */     }
/* 249 */     else if (FichierBaseDAO.getInstance().delete(Tables.getTableName(Tables.TableName.typeFraisMedicaux), getId())) {
/*     */       
/* 251 */       clear(true);
/* 252 */       HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/* 253 */       charger();
/*     */     } else {
/*     */       
/* 256 */       HelperC.afficherMessage("D�sol� ", "Echec de suppression");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 262 */     clear(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   

/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\TypeFraisMedicauxB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */