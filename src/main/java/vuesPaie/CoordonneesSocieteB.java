/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.CoordonneesSocieteC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.ExerciceC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.Historique;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.PaysC;
/*     */ import classesPaie.Tables;
import classesPaie.Tables.TableName;

/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.imageio.stream.FileImageOutputStream;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.event.CaptureEvent;
/*     */ import org.primefaces.event.FileUploadEvent;
/*     */ import persistencePaie.FichierBaseDAO;
/*     */ 
/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class CoordonneesSocieteB
/*     */   extends CoordonneesSocieteC
/*     */   implements Serializable {
/*     */   private static final long serialVersionUID = 9188342987307292627L;
/*     */   private String periodeDebutS;
/*  40 */   private List<PaysC> listPays = new ArrayList<PaysC>(); private String periodeFinS; private String periodeDebutFraisMedicauxS; private CoordonneesSocieteC coordonneesSociete; private OperateurC operateur;
/*  41 */   private HttpSession session = HelperC.getSession(); private ExerciceC exercice;
/*     */   private DroitC droit;
			private boolean disableMsg;
/*     */   Base userFonction;
/*     */   
/*     */   public String getPeriodeDebutS() {
/*  46 */     return this.periodeDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPeriodeDebutS(String periodeDebutS) {
/*  51 */     this.periodeDebutS = periodeDebutS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPeriodeFinS() {
/*  56 */     return this.periodeFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPeriodeFinS(String periodeFinS) {
/*  61 */     this.periodeFinS = periodeFinS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPeriodeDebutFraisMedicauxS() {
/*  66 */     return this.periodeDebutFraisMedicauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPeriodeDebutFraisMedicauxS(String periodeDebutFraisMedicauxS) {
/*  71 */     this.periodeDebutFraisMedicauxS = periodeDebutFraisMedicauxS;
/*     */   }
/*     */ 
/*     */   
/*     */   public CoordonneesSocieteC getCoordonneesSociete() {
/*  76 */     return this.coordonneesSociete;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCoordonneesSociete(CoordonneesSocieteC coordonneesSociete) {
/*  81 */     this.coordonneesSociete = coordonneesSociete;
/*     */   }
/*     */   
/*     */   public List<PaysC> getListPays() {
/*  85 */     return this.listPays;
/*     */   }
/*     */   
/*     */   public void setListPays(List<PaysC> listPays) {
/*  89 */     this.listPays = listPays;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperateurC getOperateur() {
/*  94 */     return this.operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperateur(OperateurC operateur) {
/*  99 */     this.operateur = operateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExerciceC getExercice() {
/* 104 */     return this.exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExercice(ExerciceC exercice) {
/* 109 */     this.exercice = exercice;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/* 114 */     return this.session;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/* 119 */     this.session = session;
/*     */   }
			
			
			public boolean isDisableMsg() {
				return disableMsg;
			}
			public void setDisableMsg(boolean disableMsg) {
				this.disableMsg = disableMsg;
			}
/*     */   @PostConstruct
/*     */   private void init() {
/* 125 */     String codeOperateur = (String)this.session.getAttribute("operateur");
/* 126 */     String codeExercice = (String)this.session.getAttribute("exercice");
/* 127 */     this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
/* 128 */     this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
/* 129 */     if (this.exercice == null || this.operateur == null) {
/*     */ 
/*     */       
/*     */       try {
/* 133 */         FacesContext context = FacesContext.getCurrentInstance();
/* 134 */         context.getExternalContext().redirect("/payRoll/login.xhtml");
/*     */       }
/* 136 */       catch (IOException e) {
/*     */         
/* 138 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 142 */       this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 143 */       if (this.userFonction != null)
/*     */       {
/* 145 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.parametrage);
/*     */       }
/* 147 */       this.coordonneesSociete = FichierBaseDAO.getInstance().getCoordonneesSociete();
/* 148 */       if (this.coordonneesSociete != null) {
/*     */         
/* 150 */         setObject();
/*     */       } else {
/*     */         
/* 153 */         clear(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setObject() {
/* 160 */     if (this.coordonneesSociete != null) {
/*     */       
/* 162 */       setId(this.coordonneesSociete.getId());
/* 163 */       setCode(this.coordonneesSociete.getCode());
/* 164 */       setRepresentant(this.coordonneesSociete.getRepresentant());
/* 165 */       setNomSociete(this.coordonneesSociete.getNomSociete());
/* 166 */       setAdresse(this.coordonneesSociete.getAdresse());
/* 167 */       setDescriptif(this.coordonneesSociete.getDescriptif());
/* 168 */       setTelephoneMobile(this.coordonneesSociete.getTelephoneMobile());
/* 169 */       setTelephoneFixe(this.coordonneesSociete.getTelephoneFixe());
/* 170 */       setFax(this.coordonneesSociete.getFax());
/* 171 */       setEmail(this.coordonneesSociete.getEmail());
/* 172 */       setLogo(this.coordonneesSociete.getLogo());
/* 173 */       setNoEmployeurInss(this.coordonneesSociete.getNoEmployeurInss());
				disableMsg=false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void telecharger(FileUploadEvent event) {
/*     */     try {
/* 181 */       InputStream in = event.getFile().getInputstream();
/* 182 */       int i = (int)(Math.random() * 1.0E8D);
/* 183 */       setLogo(String.valueOf(String.valueOf(i)) + ".jpg");
/* 184 */       ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
/* 185 */       OutputStream out = new FileOutputStream(new File(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + getLogo()));
/* 186 */       int read = 0;
/* 187 */       byte[] bytes = new byte[1024];
/* 188 */       while ((read = in.read(bytes)) != -1)
/*     */       {
/* 190 */         out.write(bytes, 0, read);
/*     */       }
/* 192 */       in.close();
/* 193 */       out.flush();
/* 194 */       out.close();
/*     */     }
/* 196 */     catch (IOException e) {
/*     */       
/* 198 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void oncapture(CaptureEvent captureEvent) {
/* 204 */     int i = (int)(Math.random() * 1.0E8D);
/* 205 */     setLogo(String.valueOf(String.valueOf(i)) + ".jpg");
/* 206 */     byte[] data = captureEvent.getData();
/* 207 */     ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
/*     */     
/*     */     try {
/* 210 */       FileImageOutputStream imageOutput = new FileImageOutputStream(new File(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + getLogo()));
/* 211 */       imageOutput.write(data, 0, data.length);
/* 212 */       imageOutput.close();
/*     */     }
/* 214 */     catch (IOException e) {
/*     */       
/* 216 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clear(boolean b) {
/* 222 */     if (b)
/*     */     {
/* 224 */       setCode("");
/*     */     }
/* 226 */     setId(0);
/* 227 */     setRepresentant("");
/* 228 */     setNomSociete("");
/* 229 */     setAdresse("");
/* 230 */     setTelephoneMobile("");
/* 231 */     setTelephoneFixe("");
/* 232 */     setFax("");
/* 233 */     setEmail("");
/* 234 */     setDescriptif("");
/* 235 */     this.periodeDebutS = "";
/* 236 */     this.periodeFinS = "";
/* 237 */     this.periodeDebutFraisMedicauxS = "";
/* 238 */     setLogo("avatar.jpg");
/* 239 */     setNoEmployeurInss("");
			  disableMsg=true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeCode() {
/* 244 */     if (getCode().trim().equals("")) {
/*     */       
/* 246 */       clear(false);
/*     */     } else {
/*     */       
/* 249 */       this.coordonneesSociete = FichierBaseDAO.getInstance().getCoordonneesSociete(getCode());
/* 250 */       if (this.coordonneesSociete != null) {
/*     */         
/* 252 */         setObject();
/*     */       } else {
/*     */         
/* 255 */         clear(false);
/*     */       } 
/*     */     } 
/*     */   }


			public void supprimmer() {
				if(droit.isSupprimer())
				{
					if(getId()>0)
					{
						if(FichierBaseDAO.getInstance().delete(getId(), Tables.getTableName(TableName.coordonneesSociete)))
						{
							 HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
							 clear(true);
						}
						else
							HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");	
					}
					else
						 HelperC.afficherAttention("ATTENTION", "Il faut avoir l'�l�ment pour supprimmer");
				}
				else {
					 HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de suppression");
				}
			}
/*     */   public void enregistrer() {
/* 262 */     if (getId() == 0 && !this.droit.isCreer()) {
/*     */       
/* 264 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
/*     */     }
/* 266 */     else if (getId() > 0 && !this.droit.isModifier()) {
/*     */       
/* 268 */       HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
/*     */     }
/* 270 */     else if (getCode().trim().equals("")) {
/*     */       
/* 272 */       HelperC.afficherMessage("Information", "Pr�cisez le code");
/*     */     }
/* 274 */     else if (getNomSociete().trim().equals("")) {
/*     */       
/* 276 */       HelperC.afficherMessage("Information", "Pr�cisez le nom de la soci�t�");
/*     */     } else {
/*     */       
/* 279 */       Historique hist = new Historique();
/* 280 */       hist.setDateOperation(Calendar.getInstance().getTime());
/* 281 */       hist.setOperateur(this.operateur);
/* 282 */       if (getId() == 0) {
/*     */         
/* 284 */         hist.setOperation("Cr�ation des coordonn�es de la soci�t� " + getNomSociete());
/*     */       } else {
/*     */         
/* 287 */         hist.setOperation("Modification des coordonn�es de la soci�t� " + getNomSociete());
/*     */       } 
/* 289 */       hist.setTable(Tables.getTableName(Tables.TableName.coordonneesSociete));
/* 290 */       setHistorique(hist);
/* 291 */       if (FichierBaseDAO.getInstance().insertUpdateCoordonneesSociete(this)) {
/*     */         
/* 293 */         HelperC.afficherMessage("Information", "Succ�s de l'op�ration");
/*     */       } else {
/*     */         
/* 296 */         HelperC.afficherMessage("D�sol�", "Echec de l'op�ration ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */  
/*     */ }
