/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DirectionC;
/*     */ import classesPaie.DirectionDetailC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class DirectionB
/*     */   extends DirectionC
/*     */ {
/*     */   private static final long serialVersionUID = 2623286573101275374L;
/*     */   private int idOrgane;
/*     */   private int idFonction;
/*  46 */   private List<SelectItem> listeOrganes = new ArrayList<SelectItem>(); 
			private DirectionDetailC detail; 
			private OperateurC operateur;
/*  47 */   private HttpSession session = HelperC.getSession(); 
		    private DroitC droit;
/*  48 */   private DirectionC direction = null; 
			private List<DirectionC> listeDirection; 
			private ExerciceC exercice;
/*     */   private Base directionUb;
			private boolean disableMsg;
			
/*     */   Base userFonction;
/*     */   
/*     */   public Base getDirectionUb() {
/*  53 */     return this.directionUb;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDirectionUb(Base directionUb) {
/*  58 */     this.directionUb = directionUb;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdOrgane() {
/*  63 */     return this.idOrgane;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdOrgane(int idOrgane) {
/*  68 */     this.idOrgane = idOrgane;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdFonction() {
/*  73 */     return this.idFonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdFonction(int idFonction) {
/*  78 */     this.idFonction = idFonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public DirectionDetailC getDetail() {
/*  83 */     return this.detail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetail(DirectionDetailC detail) {
/*  88 */     this.detail = detail;
/*     */   }
/*     */ 
/*     */   
/*     */   public DirectionC getDirection() {
/*  93 */     return this.direction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDirection(DirectionC direction) {
/*  98 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListeOrganes() {
/* 102 */     return this.listeOrganes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListeOrganes(List<SelectItem> listeOrganes) {
/* 107 */     this.listeOrganes = listeOrganes;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DirectionC> getListeDirection() {
/* 112 */     return this.listeDirection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListeDirection(List<DirectionC> listeDirection) {
/* 117 */     this.listeDirection = listeDirection;
/*     */   }
/*     */ 
   public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}
/*     */   @PostConstruct
/*     */   public void init() {
/* 123 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 124 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 125 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 126 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
			  disableMsg=true;
/* 127 */     if (this.operateur == null || this.exercice == null) {
/*     */ 
/*     */       
/*     */       try {
/* 131 */         FacesContext context = FacesContext.getCurrentInstance();
/* 132 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 134 */       catch (IOException e) {
/*     */         
/* 136 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 140 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 141 */       if (this.userFonction != null)
/*     */       {
/* 143 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.structureAdministrative);
/*     */       }
/* 145 */       this.listeOrganes.add(new SelectItem(Integer.valueOf(0), " "));
/*     */       
/* 147 */       for (Base dUb : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.directionGnle)))
/*     */       {
/* 149 */         this.listeOrganes.add(new SelectItem(Integer.valueOf(dUb.getId()), String.valueOf(dUb.getCode()) + " || " + dUb.getDesignation()));
/*     */       }
/*     */     } 
/*     */   }

/*     */ 
/*     */   
/*     */   public void searchDirection() {
/* 156 */     if (getCode() != null && !getCode().equals(""))
/*     */     {
/* 158 */       if (this.idOrgane > 0) {
/*     */         
/* 160 */         this.direction = FichierBaseDAO.getInstance().getDirection(getCode(), this.idOrgane);
/* 161 */         if (this.direction != null) {
/* 162 */           affecter();
/*     */         }
/*     */       } else {
/* 165 */         HelperC.afficherAttention("ATTENTION", "Il faut d'abord pr�ciser la direction ");
/*     */       }  } 
/*     */   }
/*     */   
/*     */   public void changeDirection(ValueChangeEvent event) {
/* 170 */     this.idOrgane = ((Integer)event.getNewValue()).intValue();
/* 171 */     if (this.idOrgane > 0) {
/* 172 */       this.directionUb = FichierBaseDAO.getInstance().getBaseById(this.idOrgane, 
/* 173 */           Tables.getTableName(Tables.TableName.directionGnle));
/* 174 */       if (this.directionUb != null) {
/* 175 */         setOrgane(this.directionUb);
/*     */       }
/* 177 */       chargerDirection();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerDirection() {
/* 183 */     this.listeDirection = FichierBaseDAO.getInstance().getListDirectionParOrgane(this.idOrgane);
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear() {
/* 188 */     setId(0);
/* 189 */     setCode("");
/* 190 */     setDesignation("");
/* 191 */     this.direction = null;
				disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialiser() {
/* 196 */     clear();
/* 197 */     this.idOrgane = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void enregistrer() {
/* 202 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 204 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 206 */     else if (getId() != 0 && !this.droit.isModifier()) {
/*     */       
/* 208 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 210 */    
/* 223 */     
/* 227 */    else {
/*     */       
/* 232 */       Historique hist = new Historique();
/* 233 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 234 */       hist.setOperateur(this.operateur);
/* 235 */       if (getId() == 0) {
/*     */         
/* 237 */         hist.setOperation("Saisie Direction code: " + getCode());
/*     */       } else {
/*     */         
/* 240 */         hist.setOperation("Modification Direction code: " + getCode());
/*     */       } 
/* 242 */       hist.setTable(Tables.getTableName(Tables.TableName.direction));
/* 243 */       setHistorique(hist);
				if(this.getOrgane()!=null)
				{
/* 244 */       if (FichierBaseDAO.getInstance().insertUpdateDirection(this)) {
/*     */         
/* 246 */         chargerDirection();
/* 247 */         clear();
/* 248 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'Op�ration");
/*     */       } else {
/*     */         
/* 251 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Op�ration");
/*     */       } 
/*     */     }
				else HelperC.afficherAttention("ATTENTION", "Il faut pr�ciser la direction");
			}
			
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
			
/* 258 */     if (!this.droit.isSupprimer()) {
/*     */       
/* 260 */       HelperC.afficherDeleteMessage();
/*     */     }
/* 262 */     else if (direction ==null) {
/*     */       
/* 264 */       HelperC.afficherInformation("Information", "Pr�cisez l'�l�ment � supprimer");
/*     */     } else {
/*     */       
/* 267 */       Historique hist = new Historique();
/* 268 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 269 */       hist.setOperateur(this.operateur);
/* 270 */       hist.setOperation("Suppression Direction code: " + getCode());
/* 271 */       hist.setTable(Tables.getTableName(Tables.TableName.direction));
/* 272 */       hist.setIdLigne(getId());
/* 273 */       setHistorique(hist);
/* 274 */       if (FichierBaseDAO.getInstance().deleteDirection(this)) {
/*     */         
/* 276 */         clear();
/* 277 */         chargerDirection();
/* 278 */         HelperC.afficherInformation("FELICITATION", "Succ�s de l'Op�ration");
/*     */       } else {
/*     */         
/* 281 */         HelperC.afficherErreur("DESOLE!", "Echec de l'Op�ration");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeSelection(SelectEvent event) {
/* 288 */     direction = (DirectionC)event.getObject();
/* 289 */     affecter();
/*     */   }
/*     */   
/*     */   public void affecter() {
/* 293 */     if (direction != null) {
/*     */       
/* 295 */       setId(direction.getId());
/* 296 */       setCode(direction.getCode());
/* 297 */       setDesignation(direction.getDesignation());
				disableMsg=false;
/* 298 */       if (direction.getOrgane() != null) {
/*     */         
/* 300 */         setOrgane(this.direction.getOrgane());
/* 301 */         this.idOrgane = this.direction.getOrgane().getId();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\vuesPaie\DirectionB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */