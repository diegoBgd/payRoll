 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.Constante;
 import classesPaie.CoordonneesSocieteC;
 import classesPaie.DroitC;
 import classesPaie.ExerciceC;
 import classesPaie.HelperC;
 import classesPaie.Historique;
 import classesPaie.OperateurC;
 import classesPaie.PaysC;
 import classesPaie.Tables;
import classesPaie.Tables.TableName;

 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.List;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.imageio.stream.FileImageOutputStream;
 import javax.servlet.ServletContext;
 import javax.servlet.http.HttpSession;
 import org.primefaces.event.CaptureEvent;
 import org.primefaces.event.FileUploadEvent;
 import persistencePaie.FichierBaseDAO;
 
 @ManagedBean
 @ViewScoped
 public class CoordonneesSocieteB
   extends CoordonneesSocieteC
   implements Serializable {
   private static final long serialVersionUID = 9188342987307292627L;
   private String periodeDebutS;
   private List<PaysC> listPays = new ArrayList<PaysC>(); private String periodeFinS; private String periodeDebutFraisMedicauxS; private CoordonneesSocieteC coordonneesSociete; private OperateurC operateur;
   private HttpSession session = HelperC.getSession(); private ExerciceC exercice;
   private DroitC droit;
			private boolean disableMsg;
   Base userFonction;
   
   public String getPeriodeDebutS() {
     return this.periodeDebutS;
   }
 
   
   public void setPeriodeDebutS(String periodeDebutS) {
     this.periodeDebutS = periodeDebutS;
   }
 
   
   public String getPeriodeFinS() {
     return this.periodeFinS;
   }
 
   
   public void setPeriodeFinS(String periodeFinS) {
     this.periodeFinS = periodeFinS;
   }
 
   
   public String getPeriodeDebutFraisMedicauxS() {
     return this.periodeDebutFraisMedicauxS;
   }
 
   
   public void setPeriodeDebutFraisMedicauxS(String periodeDebutFraisMedicauxS) {
     this.periodeDebutFraisMedicauxS = periodeDebutFraisMedicauxS;
   }
 
   
   public CoordonneesSocieteC getCoordonneesSociete() {
     return this.coordonneesSociete;
   }
 
   
   public void setCoordonneesSociete(CoordonneesSocieteC coordonneesSociete) {
     this.coordonneesSociete = coordonneesSociete;
   }
   
   public List<PaysC> getListPays() {
     return this.listPays;
   }
   
   public void setListPays(List<PaysC> listPays) {
     this.listPays = listPays;
   }
 
   
   public OperateurC getOperateur() {
     return this.operateur;
   }
 
   
   public void setOperateur(OperateurC operateur) {
     this.operateur = operateur;
   }
 
   
   public ExerciceC getExercice() {
     return this.exercice;
   }
 
   
   public void setExercice(ExerciceC exercice) {
     this.exercice = exercice;
   }
 
   
   public HttpSession getSession() {
     return this.session;
   }
 
   
   public void setSession(HttpSession session) {
     this.session = session;
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
     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
     if (this.exercice == null || this.operateur == null) {
 
       
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
       this.coordonneesSociete = FichierBaseDAO.getInstance().getCoordonneesSociete();
       if (this.coordonneesSociete != null) {
         
         setObject();
       } else {
         
         clear(true);
       } 
     } 
   }
 
   
   private void setObject() {
     if (this.coordonneesSociete != null) {
       
       setId(this.coordonneesSociete.getId());
       setCode(this.coordonneesSociete.getCode());
       setRepresentant(this.coordonneesSociete.getRepresentant());
       setNomSociete(this.coordonneesSociete.getNomSociete());
       setAdresse(this.coordonneesSociete.getAdresse());
       setDescriptif(this.coordonneesSociete.getDescriptif());
       setTelephoneMobile(this.coordonneesSociete.getTelephoneMobile());
       setTelephoneFixe(this.coordonneesSociete.getTelephoneFixe());
       setFax(this.coordonneesSociete.getFax());
       setEmail(this.coordonneesSociete.getEmail());
       setLogo(this.coordonneesSociete.getLogo());
       setNoEmployeurInss(this.coordonneesSociete.getNoEmployeurInss());
				disableMsg=false;
     } 
   }
 
 
   
   public void telecharger(FileUploadEvent event) {
     try {
       InputStream in = event.getFile().getInputstream();
       int i = (int)(Math.random() * 1.0E8D);
       setLogo(String.valueOf(String.valueOf(i)) + ".jpg");
       ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
       OutputStream out = new FileOutputStream(new File(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + getLogo()));
       int read = 0;
       byte[] bytes = new byte[1024];
       while ((read = in.read(bytes)) != -1)
       {
         out.write(bytes, 0, read);
       }
       in.close();
       out.flush();
       out.close();
     }
     catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
   
   public void oncapture(CaptureEvent captureEvent) {
     int i = (int)(Math.random() * 1.0E8D);
     setLogo(String.valueOf(String.valueOf(i)) + ".jpg");
     byte[] data = captureEvent.getData();
     ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
     
     try {
       FileImageOutputStream imageOutput = new FileImageOutputStream(new File(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + getLogo()));
       imageOutput.write(data, 0, data.length);
       imageOutput.close();
     }
     catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
   
   private void clear(boolean b) {
     if (b)
     {
       setCode("");
     }
     setId(0);
     setRepresentant("");
     setNomSociete("");
     setAdresse("");
     setTelephoneMobile("");
     setTelephoneFixe("");
     setFax("");
     setEmail("");
     setDescriptif("");
     this.periodeDebutS = "";
     this.periodeFinS = "";
     this.periodeDebutFraisMedicauxS = "";
     setLogo("avatar.jpg");
     setNoEmployeurInss("");
			  disableMsg=true;
   }
 
   
   public void changeCode() {
     if (getCode().trim().equals("")) {
       
       clear(false);
     } else {
       
       this.coordonneesSociete = FichierBaseDAO.getInstance().getCoordonneesSociete(getCode());
       if (this.coordonneesSociete != null) {
         
         setObject();
       } else {
         
         clear(false);
       } 
     } 
   }


			public void supprimmer() {
				if(droit.isSupprimer())
				{
					if(getId()>0)
					{
						if(FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(TableName.coordonneesSociete)))
						{
							 HelperC.afficherMessage("Information", "succès de l'opération");
							 clear(true);
						}
						else
							HelperC.afficherMessage("Désolé", "Echec de l'opération ");	
					}
					else
						 HelperC.afficherAttention("ATTENTION", "Il faut avoir l'élément pour supprimmer");
				}
				else {
					 HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de suppression");
				}
			}
   public void enregistrer() {
     if (getId() == 0 && !this.droit.isCreer()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de créer");
     }
     else if (getId() > 0 && !this.droit.isModifier()) {
       
       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
     }
     else if (getCode().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Précisez le code");
     }
     else if (getNomSociete().trim().equals("")) {
       
       HelperC.afficherMessage("Information", "Précisez le nom de la société");
     } else {
       
       Historique hist = new Historique();
       hist.setDateOperation(Calendar.getInstance().getTime());
       hist.setOperateur(this.operateur);
       if (getId() == 0) {
         
         hist.setOperation("Création des coordonnées de la société " + getNomSociete());
       } else {
         
         hist.setOperation("Modification des coordonnées de la société " + getNomSociete());
       } 
       hist.setTable(Tables.getTableName(Tables.TableName.coordonneesSociete));
       setHistorique(hist);
       if (FichierBaseDAO.getInstance().insertUpdateCoordonneesSociete(this)) {
         
         HelperC.afficherMessage("Information", "succès de l'opération");
       } else {
         
         HelperC.afficherMessage("Désolé", "Echec de l'opération ");
       } 
     } 
   }
 
 
  
 }
