 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.DirectionC;
 import classesPaie.DirectionDetailC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.Tables;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.SelectEvent;
 import persistencePaie.FichierBaseDAO;

 @ManagedBean
 @ViewScoped
 public class DirectionB
   extends DirectionC
 {
   private static final long serialVersionUID = 2623286573101275374L;
   private int idOrgane;
   private int idFonction;
   private List<SelectItem> listeOrganes = new ArrayList<SelectItem>(); 
			private DirectionDetailC detail; 
			private OperateurC operateur;
   private HttpSession session = HelperC.getSession(); 
		    private DroitC droit;
   private DirectionC direction = null; 
			private List<DirectionC> listeDirection; 
			private ExerciceC exercice;
   private Base directionUb;
			private boolean disableMsg;
			
   Base userFonction;
   
   public Base getDirectionUb() {
     return this.directionUb;
   }
 
   
   public void setDirectionUb(Base directionUb) {
     this.directionUb = directionUb;
   }
 
   
   public int getIdOrgane() {
     return this.idOrgane;
   }
 
   
   public void setIdOrgane(int idOrgane) {
     this.idOrgane = idOrgane;
   }
 
   
   public int getIdFonction() {
     return this.idFonction;
   }
 
   
   public void setIdFonction(int idFonction) {
     this.idFonction = idFonction;
   }
 
   
   public DirectionDetailC getDetail() {
     return this.detail;
   }
 
   
   public void setDetail(DirectionDetailC detail) {
     this.detail = detail;
   }
 
   
   public DirectionC getDirection() {
     return this.direction;
   }
 
   
   public void setDirection(DirectionC direction) {
     this.direction = direction;
   }
   
   public List<SelectItem> getListeOrganes() {
     return this.listeOrganes;
   }
 
   
   public void setListeOrganes(List<SelectItem> listeOrganes) {
     this.listeOrganes = listeOrganes;
   }
 
   
   public List<DirectionC> getListeDirection() {
     return this.listeDirection;
   }
 
   
   public void setListeDirection(List<DirectionC> listeDirection) {
     this.listeDirection = listeDirection;
   }
 
   public boolean isDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(boolean disableMsg) {
		this.disableMsg = disableMsg;
	}
   @PostConstruct
   public void init() {
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
         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.structureAdministrative);
       }
       this.listeOrganes.add(new SelectItem(Integer.valueOf(0), " "));
       
       for (Base dUb : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(Tables.TableName.directionGnle)))
       {
         this.listeOrganes.add(new SelectItem(Integer.valueOf(dUb.getId()), String.valueOf(dUb.getCode()) + " || " + dUb.getDesignation()));
       }
     } 
   }

 
   
   public void searchDirection() {
     if (getCode() != null && !getCode().equals(""))
     {
       if (this.idOrgane > 0) {
         
         this.direction = FichierBaseDAO.getInstance().getDirection(getCode(), this.idOrgane);
         if (this.direction != null) {
           affecter();
         }
       } else {
         HelperC.afficherAttention("ATTENTION", "Il faut d'abord préciser la direction ");
       }  } 
   }
   
   public void changeDirection(ValueChangeEvent event) {
     this.idOrgane = ((Integer)event.getNewValue()).intValue();
     if (this.idOrgane > 0) {
       this.directionUb = FichierBaseDAO.getInstance().getBaseById(this.idOrgane, 
           Tables.getTableName(Tables.TableName.directionGnle));
       if (this.directionUb != null) {
         setOrgane(this.directionUb);
       }
       chargerDirection();
     } 
   }
 
   
   private void chargerDirection() {
     this.listeDirection = FichierBaseDAO.getInstance().getListDirectionParOrgane(this.idOrgane);
   }
 
   
   private void clear() {
     setId(0);
     setCode("");
     setDesignation("");
     this.direction = null;
				disableMsg=true;
   }
 
   
   public void initialiser() {
     clear();
     this.idOrgane = 0;
   }
 
   
   public void enregistrer() {
				if(getCode().equals("")) {
					HelperC.afficherAttention("ATTENTION", "Il faut préciser la référence");
					return;
				}
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() != 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
    
     
    else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Saisie Direction code: " + getCode());
       } else {
         
         hist.setOperation("Modification Direction code: " + getCode());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.direction));
       setHistorique(hist);
				if(this.getOrgane()!=null)
				{
       if (FichierBaseDAO.getInstance().insertUpdateDirection(this)) {
         
         chargerDirection();
         clear();
         HelperC.afficherInformation("FELICITATION", "Succès de l'Opération");
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
       } 
     }
				else HelperC.afficherAttention("ATTENTION", "Il faut préciser la direction");
			}
			
   }
 
   
   public void supprimer() {
			
     if (!this.droit.isSupprimer()) {
       
       HelperC.afficherDeleteMessage();
     }
     else if (direction ==null) {
       
       HelperC.afficherInformation("Information", "Prècisez l'élément à supprimer");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       hist.setOperation("Suppression Direction code: " + getCode());
       hist.setTable(Tables.getTableName(Tables.TableName.direction));
       hist.setIdLigne(getId());
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().deleteDirection(this)) {
         
         clear();
         chargerDirection();
         HelperC.afficherInformation("Info", "Succès de l'Opération");
       } else {
         
         HelperC.afficherErreur("DESOLE!", "Echec de l'Opération");
       } 
     } 
   }
 
   
   public void takeSelection(SelectEvent event) {
     direction = (DirectionC)event.getObject();
     affecter();
   }
   
   public void affecter() {
     if (direction != null) {
       
       setId(direction.getId());
       setCode(direction.getCode());
       setDesignation(direction.getDesignation());
				disableMsg=false;
       if (direction.getOrgane() != null) {
         
         setOrgane(this.direction.getOrgane());
         this.idOrgane = this.direction.getOrgane().getId();
       } 
     } 
   }
 }


