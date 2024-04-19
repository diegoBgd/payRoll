/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
import classesPaie.CategoriePersonnelC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.GradePersonnelC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageFinCarriereC;
/*     */ import classesPaie.Tables;
/*     */ import classesPaie.TypeNotationC;

/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ValueChangeEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
/*     */ import org.primefaces.event.SelectEvent;

import persistencePaie.FichierBaseDAO;
 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class ParametrageFinCarriereB
/*     */   extends ParametrageFinCarriereC
/*     */ {
/*     */   private static final long serialVersionUID = 5203706953535440034L;
/*     */   private int idPersonnel;
/*     */   private int idGrade;
/*     */   private int idTypeNotation,idCategorie;
/*     */   private ParametrageFinCarriereC selected;
/*  39 */   private List<ParametrageFinCarriereC> listParametrage = new ArrayList<ParametrageFinCarriereC>();
/*  40 */   private List<SelectItem> listTypeNotation = new ArrayList<SelectItem>();
/*  41 */   private List<SelectItem> listPersonnel = new ArrayList<SelectItem>();
/*  42 */   private List<SelectItem> listGrade = new ArrayList<SelectItem>();
			private List<SelectItem> listCagtorie=new ArrayList<SelectItem>();
/*     */   private OperateurC operateur;
/*     */   private ExerciceC exercice;
/*  45 */   private HttpSession session = HelperC.getSession();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIdCategorie() {
			return idCategorie;
			}
			public void setIdCategorie(int idCategorie) {
				this.idCategorie = idCategorie;
			}
/*     */   public int getIdPersonnel() {
/*  52 */     return this.idPersonnel;
/*     */   }

/*     */   
/*     */   public void setIdPersonnel(int idPersonnel) {
/*  56 */     this.idPersonnel = idPersonnel;
/*     */   }
/*     */   
/*     */   public int getIdGrade() {
/*  60 */     return this.idGrade;
/*     */   }
/*     */   
/*     */   public void setIdGrade(int idGrade) {
/*  64 */     this.idGrade = idGrade;
/*     */   }
/*     */   
/*     */   public ParametrageFinCarriereC getSelected() {
/*  68 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(ParametrageFinCarriereC selected) {
/*  72 */     this.selected = selected;
/*     */   }
/*     */   
/*     */   public List<ParametrageFinCarriereC> getListParametrage() {
/*  76 */     return this.listParametrage;
/*     */   }
/*     */   
/*     */   public void setListParametrage(List<ParametrageFinCarriereC> listParametrage) {
/*  80 */     this.listParametrage = listParametrage;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListPersonnel() {
/*  84 */     return this.listPersonnel;
/*     */   }
/*     */   
/*     */   public void setListPersonnel(List<SelectItem> listPersonnel) {
/*  88 */     this.listPersonnel = listPersonnel;
/*     */   }
/*     */   
/*     */   
/*     */   
/*     */   public OperateurC getOperateur() {
/* 100 */     return this.operateur;
/*     */   }
			public List<SelectItem> getListGrade() {
				return listGrade;
			}
			public void setListGrade(List<SelectItem> listGrade) {
				this.listGrade = listGrade;
			}
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/* 104 */     this.operateur = operateur;
/*     */   }
/*     */   
/*     */   public ExerciceC getExercice() {
/* 108 */     return this.exercice;
/*     */   }
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 112 */     this.exercice = exercice;
/*     */   }
/*     */   
/*     */   public HttpSession getSession() {
/* 116 */     return this.session;
/*     */   }
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 120 */     this.session = session;
/*     */   }
/*     */   
/*     */   public int getIdTypeNotation() {
/* 124 */     return this.idTypeNotation;
/*     */   }
/*     */   
/*     */   public void setIdTypeNotation(int idTypeNotation) {
/* 128 */     this.idTypeNotation = idTypeNotation;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getListTypeNotation() {
/* 132 */     return this.listTypeNotation;
/*     */   }
/*     */   
/*     */   public void setListTypeNotation(List<SelectItem> listTypeNotation) {
/* 136 */     this.listTypeNotation = listTypeNotation;
/*     */   }


			public List<SelectItem> getListCagtorie() {
				return listCagtorie;
			}
			public void setListCagtorie(List<SelectItem> listCagtorie) {
				this.listCagtorie = listCagtorie;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/* 141 */     this.operateur = new OperateurC();
/* 142 */     this.exercice = new ExerciceC();
/*     */     
/* 144 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 145 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 146 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 147 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/*     */     
/* 149 */     if (this.operateur == null || this.exercice == null) {
/*     */       
/*     */       try {
/* 152 */         FacesContext context = FacesContext.getCurrentInstance();
/* 153 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/* 154 */       } catch (IOException e) {
/*     */         
/* 156 */         e.printStackTrace();
/*     */       } 
/*     */     }
/* 159 */     this.listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));
/* 160 */     this.listTypeNotation.add(new SelectItem(Integer.valueOf(0), ""));
/* 161 */     this.listGrade.add(new SelectItem(Integer.valueOf(0), ""));
/* 162 */     for (Base personnel : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.personnel))) {
/* 163 */       this.listPersonnel.add(new SelectItem(Integer.valueOf(personnel.getId()), personnel.getDesignation()));
/*     */     }
/* 165 */     for (TypeNotationC typeNotation : FichierBaseDAO.getInstance().getAllTypeNotation()) {
/* 166 */       this.listTypeNotation.add(new SelectItem(Integer.valueOf(typeNotation.getId()), typeNotation.getDesignation()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 172 */     if (this.selected != null) {
/* 173 */       setId(this.selected.getId());
/* 174 */       setPersonnel(this.selected.getPersonnel());
				setCategorie(this.selected.getCategorie());
/* 175 */       if (getPersonnel() != null)
					{
					this.idPersonnel = getPersonnel().getId(); 
               
					chargerCategorie();
					if(getCategorie()!=null)
						idCategorie=getCategorie().getId();
					}
/* 182 */       setAgeMaxRetraite(this.selected.getAgeMaxRetraite());
/* 183 */       setAgeRetraite(this.selected.getAgeRetraite());
                     
                chargerGrade();
/* 187 */       setDernierGrade(this.selected.getDernierGrade());
/* 188 */       if (getDernierGrade() != null)
				{
/* 189 */         this.idGrade = getDernierGrade().getId(); 
       
				}
/* 197 */       setTypeNotation(this.selected.getTypeNotation());
/* 198 */       if (getTypeNotation() != null) {
/* 199 */         this.idTypeNotation = getTypeNotation().getId();
/*     */       }
               setPeriodeProlongation(selected.getPeriodeProlongation());
               setPeriodeSalire(selected.getPeriodeSalire());
               setPourcentageSalaire(selected.getPourcentageSalaire());
               setAnneesProlongationRetraite(selected.getAnneesProlongationRetraite());
               setPeriodeAnticipe(selected.getPeriodeAnticipe());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear() {
/* 206 */     setId(0);
/* 207 */     setPersonnel(null);
/* 208 */     this.idPersonnel = 0;
/* 209 */     setPeriodeProlongation(0);
/* 214 */     setAgeMaxRetraite(0);
/* 215 */     setAgeRetraite(0);
/* 216 */     setPourcentageSalaire(0);
/* 217 */     setAnneesProlongationRetraite(0);
/* 218 */     setPeriodeAnticipe(0);
/* 219 */     setDernierGrade(null);
/* 220 */     this.idGrade = 0;

/* 228 */     setTypeNotation(null);
/* 229 */     this.idTypeNotation = 0;
/* 230 */     this.selected = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRowSelect(SelectEvent event) {
/* 235 */     this.selected = (ParametrageFinCarriereC)event.getObject();
/* 236 */     if (this.selected != null) {
/* 237 */       setObject();
				PrimeFaces.current().executeScript("PF('dlgParm').hide();");
/*     */     }
/*     */   }
/*     */   
/*     */   public void initialiser() {
/* 242 */     clear();
/*     */   }
/*     */ 
/*     */   public void chargement()
			{
				listParametrage=FichierBaseDAO.getInstance().getListParametrageFinCarriere();
			}
/*     */   public void changePersonnel(ValueChangeEvent event) {
/* 247 */     this.idPersonnel = ((Integer)event.getNewValue()).intValue();
/* 248 */     if (this.idPersonnel != 0) {
/* 249 */       setPersonnel(FichierBaseDAO.getInstance().getBaseById(this.idPersonnel, Tables.getTableName(Tables.TableName.personnel)));
/* 250 */       if (getPersonnel() != null)
/*     */       {
				
/* 252 */         chargerCategorie();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeGrade(ValueChangeEvent event) {
/* 262 */     this.idGrade = ((Integer)event.getNewValue()).intValue();
/* 263 */     if (this.idGrade != 0) {
/* 264 */       setDernierGrade(FichierBaseDAO.getInstance().getGradePersonnel(this.idGrade));
				
/*     */     }
/*     */   }
/*     */ 
/*     */   
			public void changeCategorie(ValueChangeEvent event) {
/* 262 */     idCategorie = ((Integer)event.getNewValue()).intValue();
              setCategorie(FichierBaseDAO.getInstance().getCategoriePersonnel(idCategorie));
/* 263 */     chargerGrade();
/*     */   }

			private void chargerGrade(){
	           listGrade.add(new SelectItem(0, ""));
				for (GradePersonnelC grd : FichierBaseDAO.getInstance().getListGradeParCategoriePersonnel(idCategorie)) {
					 listGrade.add(new SelectItem(grd.getId(), grd.getDesignation()));
				}
			}
/*     */   public void changeTypeNotation(ValueChangeEvent event) {
/* 270 */     this.idTypeNotation = ((Integer)event.getNewValue()).intValue();
/* 271 */     if (this.idTypeNotation != 0) {
/* 272 */       setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(this.idTypeNotation));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void chargerCategorie() {
/*     */     listCagtorie.add(new SelectItem(0, ""));
			  for (CategoriePersonnelC cat : FichierBaseDAO.getInstance().getListCategoriePersonnelParIdPersonnel(idPersonnel)) {
				  listCagtorie.add(new SelectItem(Integer.valueOf(cat.getId()), cat.getDesignation()));
			}
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 290 */     if (getPersonnel() == null) {
/* 291 */       HelperC.afficherMessage("Information", "Veillez saisir la position!");
/*     */     
/*     */     }
/* 294 */     else if (FichierBaseDAO.getInstance().insertUpdateParametrageFinCarriere(this)) {
/* 295 */       HelperC.afficherMessage("Information", "Succ� d'enregistrement");
/*     */     } else {
/* 297 */       HelperC.afficherMessage("information", "Echec d'enregistrement");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 306 */       if (getId() == 0) {
/*     */         
/* 308 */         HelperC.afficherDeleteMessage();
/*     */       
/*     */       }
/* 311 */       else if (FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.parametrageFinCarriere))) {
/*     */         
/* 313 */         HelperC.afficherMessage("F�licitation", "Succ� de l'Op�ration");
/* 314 */         clear();
/*     */       } else {
/*     */         
/* 317 */         HelperC.afficherMessage("D�sol�", "Echec de l'Op�ration");
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 322 */     catch (Exception e) {
/* 323 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */  
/*     */ }


