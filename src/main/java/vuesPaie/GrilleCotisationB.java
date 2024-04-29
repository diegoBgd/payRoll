 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DetailGrilleCotisationC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.GrilleCotisationC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import java.io.IOException;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;

 @ManagedBean
 @ViewScoped
 public class GrilleCotisationB
   extends GrilleCotisationC
 {
   private static final long serialVersionUID = 3668522777115339190L;
   private String categorie;
   private String dateDebutS;
   private String dateFinS;
   private double trancheDeb;
   private double trancheFin;
   private double pu;
   private double unite;
   private double totMontant;
   private DetailGrilleCotisationC detail;
   private OperateurC operateur;
   private ExerciceC exercice;
   private int idDetail;
			private boolean disableMsg;
   private HttpSession session = HelperC.getSession();
   boolean selected;
   int index;
   
   public String getDateDebutS() {
     return this.dateDebutS;
   }
   Base userFonction; GrilleCotisationC grille; private DroitC droit;
   
   public void setDateDebutS(String dateDebutS) {
     this.dateDebutS = dateDebutS;
   }
 
   
   public String getDateFinS() {
     return this.dateFinS;
   }
 
   
   public void setDateFinS(String dateFinS) {
     this.dateFinS = dateFinS;
   }
 
   
   public double getTotMontant() {
     return this.totMontant;
   }
 
   
   public void setTotMontant(double totMontant) {
     this.totMontant = totMontant;
   }
 
   
   public String getCategorie() {
     return this.categorie;
   }
 
   
   public void setCategorie(String categorie) {
     this.categorie = categorie;
   }
 
   
   public double getTrancheDeb() {
     return this.trancheDeb;
   }
 
   
   public void setTrancheDeb(double trancheDeb) {
     this.trancheDeb = trancheDeb;
   }
 
   
   public double getTrancheFin() {
     return this.trancheFin;
   }
 
   
   public void setTrancheFin(double trancheFin) {
     this.trancheFin = trancheFin;
   }
 
   
   public double getPu() {
     return this.pu;
   }
 
   
   public void setPu(double pu) {
     this.pu = pu;
   }
 
   
   public double getUnite() {
     return this.unite;
   }
 
   
   public void setUnite(double unite) {
     this.unite = unite;
   }
 
   
   public DetailGrilleCotisationC getDetail() {
     return this.detail;
   }
 
   
   public void setDetail(DetailGrilleCotisationC detail) {
     this.detail = detail;
   }
 
   
   public int getIdDetail() {
     return this.idDetail;
   }
 
   
   public void setIdDetail(int idDetail) {
     this.idDetail = idDetail;
   }
 
   
   public DroitC getDroit() {
     return this.droit;
   }
 
   
   public void setDroit(DroitC droit) {
     this.droit = droit;
   }

  
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
			
   @PostConstruct
   private void init() {
     String codeOperateur = (String)this.session.getAttribute("operateur");
     String codeExercice = (String)this.session.getAttribute("exercice");
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
			  disableMsg=true;
     if (this.operateur == null || this.exercice == null) {
 
       
       try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/payRoll/login.xhtml");
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
     } else {
       
       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
       if (this.userFonction != null)
       {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
       }
       this.grille = FichierBaseDAO.getInstance().getGrilleCotisation();
       if (this.grille != null) {
         disableMsg=false;
         setId(this.grille.getId());
         setCoefficient(this.grille.getCoefficient());
         setDateDebut(this.grille.getDateDebut());
         this.dateDebutS = HelperC.changeDateFormate(this.grille.getDateDebut());
         if (this.grille.getDateFin() != null)
         {
           this.dateFinS = HelperC.changeDateFormate(this.grille.getDateFin());
         }
         this.grille.setListDetail(FichierBaseDAO.getInstance().getListeGrilleDetail(this.grille));
         setListDetail(this.grille.getListDetail());
       } 
     } 
   }
 
   
   public void onRowSelected(SelectEvent event) {
     this.detail = (DetailGrilleCotisationC)event.getObject();
     setDetailValue();
   }
 
   
   private void setDetailValue() {
     if (this.detail != null) {
       
       this.pu = this.detail.getValeurAchat();
       this.categorie = this.detail.getCategorie();
       this.trancheDeb = this.detail.getTrancheMin();
       this.trancheFin = this.detail.getTrancheMax();
       this.unite = this.detail.getPointAchete();
       this.idDetail = this.detail.getId();
       this.index = getListDetail().indexOf(this.detail);
       this.selected = true;
     } 
   }
 
   
   public void addDetail() {
				if(categorie!=null && !categorie.equals(""))
				{
     if (this.detail == null)
     {
       this.detail = new DetailGrilleCotisationC();
     }
     this.detail.setCategorie(this.categorie);
     this.detail.setPointAchete(this.unite);
     this.detail.setTrancheMax(this.trancheFin);
     this.detail.setTrancheMin(this.trancheDeb);
     this.detail.setValeurAchat(this.pu);
     this.detail.setId(this.idDetail);
     if (!this.selected) {
       
       getListDetail().add(this.detail);
     } else {
       
       getListDetail().remove(this.index);
       getListDetail().add(this.index, this.detail);
     } 
     clearDetail();
				}
				else
					HelperC.afficherAttention("Info", "Il faut préciser la catégorie");
   }
 
   
   public void searcheElement() {
     if (getListDetail().size() > 0)
     {
       for (DetailGrilleCotisationC det : getListDetail()) {
 
         
         if (det.getCategorie().equals(this.categorie)) {
           
           this.detail = det;
           setDetailValue();
         } 
       } 
     }
   }
 
 
   
   public void removeDetail() {
     if (this.detail != null) {
       
       if (this.detail.getId() > 0)
       {
         getListDeleted().add(this.detail);
       }
       getListDetail().remove(this.detail);
       clearDetail();
     } 
   }
 
   
   public void clearDetail() {
     this.detail = null;
     this.pu = 0.0D;
     this.categorie = "";
     this.trancheDeb = 0.0D;
     this.trancheFin = 0.0D;
     this.unite = 0.0D;
     this.index = 0;
     this.selected = false;
     this.totMontant = 0.0D;
     this.idDetail = 0;
   }
 
   
   public void initialize() {
     setId(0);
     this.dateDebutS = "";
     this.dateFinS = "";
     setCoefficient(0.0D);
     getListDeleted().clear();
     getListDetail().clear();
			  disableMsg=true;
   }
 
   
   public void changeDateDebut() {
     if (this.dateDebutS.replace("/", "").replace("_", "").trim().equals("")) {
       
       setDateDebut(null);
     } else {
       
       setDateDebut(HelperC.validerDate(this.dateDebutS));
       if (getDateDebut() == null) {
         
         this.dateDebutS = "";
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         this.dateDebutS = HelperC.convertDate(getDateDebut());
       } 
     } 
   }
 
   
   public void changeDateFin() {
     if (this.dateFinS.replace("/", "").replace("_", "").trim().equals("")) {
       
       setDateFin(null);
     } else {
       
       setDateFin(HelperC.validerDate(this.dateFinS));
       if (getDateFinS() == null) {
         
         this.dateFinS = "";
         HelperC.afficherMessage("Information", "Date invalide");
       } else {
         
         this.dateFinS = HelperC.convertDate(getDateFin());
       } 
     } 
   }
 
   
   public void save() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
       return;
     } 
     if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de modifier ");
       return;
     } 
     if (getDateDebut() != null && getCoefficient() > 0.0D)
     {
       if (FichierBaseDAO.getInstance().insertUpdateGrilleCotisation(this)) {
         
         HelperC.afficherMessage("Information", "Succès de l'opération");
       } 
     }
				else {
         
         HelperC.afficherMessage("Information", "Il faut préciser la date début et le coefficient! ");
       } 
   }
 
   
   public void delete() {
     if (!this.droit.isSupprimer()) {
       
       HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit de supprimer ");
       return;
     } 
     if (getId() > 0) {
       
       FichierBaseDAO.getInstance().deleteGrilleCotisation(this);
     } else {
       
       HelperC.afficherDeleteMessage();
     } 
   }
 }

