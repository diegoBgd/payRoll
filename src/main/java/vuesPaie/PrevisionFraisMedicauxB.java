      package vuesPaie;
      
      import classesPaie.Base;
      import classesPaie.Constante;
      import classesPaie.DroitC;
      import classesPaie.EmployeC;
      import classesPaie.ExerciceC;
      import classesPaie.HelperC;
      import classesPaie.Historique;
      import classesPaie.MarcheProgrammeC;
      import classesPaie.OperateurC;
      import classesPaie.PrevisionFraisMedicauxC;
      import classesPaie.Tables;
      import java.io.IOException;
      import java.util.ArrayList;
      import java.util.Calendar;
      import java.util.List;
      import javax.annotation.PostConstruct;
      import javax.faces.application.FacesMessage;
      import javax.faces.bean.ManagedBean;
      import javax.faces.bean.ViewScoped;
      import javax.faces.context.FacesContext;
      import javax.servlet.http.HttpSession;
      import org.primefaces.event.SelectEvent;
      import persistencePaie.FactoryDAO;
      import persistencePaie.FichierBaseDAO;

      @ManagedBean
      @ViewScoped
      public class PrevisionFraisMedicauxB
        extends PrevisionFraisMedicauxC
      {
        private static final long serialVersionUID = -1974349284765822458L;
      private List<PrevisionFraisMedicauxC> allPrevisionFraisMedicaux = new ArrayList<PrevisionFraisMedicauxC>();
      private PrevisionFraisMedicauxC selected = null;
      private EmployeC selection = null; private String code; private String codeEmployeRecherche; private String nomEmployeRecherche; private String prenomEmployeRecherche; private int idEmploye;
     private List<EmployeC> listEmploye = new ArrayList<EmployeC>();
        
        private MarcheProgrammeC marche;
        
        private OperateurC operateur;
        private ExerciceC exercice;
        private DroitC droit;
     private HttpSession session = HelperC.getSession(); Base userFonction;
        
        public PrevisionFraisMedicauxB() {
       chargerPrevisionFraisMedicaux();
       setReferenceConge(getReferenceConge());
       setReferenceFraisMedicaux(getReferenceFraisMedicaux());
       setReferenceCongeS(HelperC.TraitementMontant.getMontantFormate(getReferenceConge(), 0));
       setReferenceFraisMedicauxS(HelperC.TraitementMontant.getMontantFormate(getReferenceFraisMedicaux(), 0));
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
        
        public List<PrevisionFraisMedicauxC> getAllPrevisionFraisMedicaux() {
       return this.allPrevisionFraisMedicaux;
        }
      
      
        
        public void setAllPrevisionFraisMedicaux(List<PrevisionFraisMedicauxC> allPrevisionFraisMedicaux) {
        this.allPrevisionFraisMedicaux = allPrevisionFraisMedicaux;
        }
      
        
        public PrevisionFraisMedicauxC getSelected() {
        return this.selected;
        }
      
        
        public void setSelected(PrevisionFraisMedicauxC selected) {
       this.selected = selected;
        }
      
        
        public EmployeC getSelection() {
      return this.selection;
        }
      
        
        public void setSelection(EmployeC selection) {
     this.selection = selection;
        }
      
        
        public List<EmployeC> getListEmploye() {
       return this.listEmploye;
        }
      
        
        public void setListEmploye(List<EmployeC> listEmploye) {
      this.listEmploye = listEmploye;
        }
      
        
        public String getCode() {
      return this.code;
        }
      
        
        public void setCode(String code) {
      this.code = code;
        }
      
        
        public String getCodeEmployeRecherche() {
     return this.codeEmployeRecherche;
        }
      
        
        public void setCodeEmployeRecherche(String codeEmployeRecherche) {
      this.codeEmployeRecherche = codeEmployeRecherche;
        }
      
        
        public String getNomEmployeRecherche() {
      return this.nomEmployeRecherche;
        }
      
        
        public void setNomEmployeRecherche(String nomEmployeRecherche) {
       this.nomEmployeRecherche = nomEmployeRecherche;
        }
      
        
        public String getPrenomEmployeRecherche() {
       return this.prenomEmployeRecherche;
        }
      
        
        public void setPrenomEmployeRecherche(String prenomEmployeRecherche) {
       this.prenomEmployeRecherche = prenomEmployeRecherche;
        }
      
        
        public int getIdEmploye() {
      return this.idEmploye;
        }
      
        
        public void setIdEmploye(int idEmploye) {
      this.idEmploye = idEmploye;
        }
      
        
        public MarcheProgrammeC getMarche() {
      return this.marche;
        }
      
        
        public void setMarche(MarcheProgrammeC marche) {
      this.marche = marche;
        }
      
        
        @PostConstruct
        private void charger() {
      String codeExercice = (String)this.session.getAttribute("exercice");
      String codeOperateur = (String)this.session.getAttribute("operateur");
          
     if (codeExercice == null || codeOperateur == null) {
            
            try {
          FacesContext context = FacesContext.getCurrentInstance();
           context.getExternalContext().redirect("/payRoll/login.xhtml");
         } catch (IOException e) {
              
           e.printStackTrace();
            } 
          } else {
         this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
         this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
         this.userFonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
         if (this.userFonction != null) {
           this.droit = FichierBaseDAO.getInstance().getDroit(this.userFonction.getId(), Constante.Role.gestionConge);
            }
         this.allPrevisionFraisMedicaux = FactoryDAO.getInstance().getListAllPrevisionFraisMedicaux();
          } 
        }
      
        
        public void findByCode() {
       this.selection = FactoryDAO.getInstance().getEmploye(getCode(), false);
       if (this.selection != null)
         setObject1(); 
       if (getEmploye() != null) {
         this.selected = FactoryDAO.getInstance().getPrevisionFraisMedicaux(getEmploye());
         if (this.selected != null) {
           setObject();
            }
          } else {
         clear1(true);
          } 
        }
      
      
      
      
        
        private void chargerPrevisionFraisMedicaux() {}
      
         
        private void clear1(boolean b) {
       if (b)
         setEmploye(this.selection); 
       setCode("");
       setIdEmploye(0);
       setCodeEmployeRecherche("");
       setPrenomEmployeRecherche("");
       setNomEmployeRecherche("");
       this.listEmploye.clear();
       this.selection = null;
        }
      
        
        private void clear(boolean b) {
       if (b)
         getEmploye().setId(0); 
       setEmploye(null);
       setCode("");
       setId(0);
       setPrevisionParEmploye(0.0D);
       setPrevisionParEmployeS("");
       setPrevisionParPersonneACharge(0.0D);
       setPrevisionParPersonneAChargeS("");
       setReferenceConge(0.0D);
       setReferenceCongeS("");
       setReferenceFraisMedicaux(0.0D);
       setReferenceFraisMedicauxS("");
       setMontantANouveau(0.0D);
       setMontantANouveauS("");
       setMontantPeriode(0.0D);
       setMontantPeriodeS("");
       setDesignation("");
       this.selected = null;
        }
      
      
        
        public void chargerEmploye() {
       this.listEmploye = FactoryDAO.getInstance().getListEmploye(this.codeEmployeRecherche, this.nomEmployeRecherche, false);
        }
      
      
        
        public void changeReferenceConge() {
          try {
         setReferenceConge(Double.valueOf(getReferenceCongeS().replace(" ", "").replace(",", ".").trim()).doubleValue());
       } catch (Exception e) {
         setReferenceConge(0.0D);
          } finally {
         if (getReferenceConge() < 0.0D) {
           setReferenceConge(0.0D);
            }
         if (getReferenceConge() > 0.0D) {
              
           setReferenceCongeS(HelperC.TraitementMontant.getMontantFormate(getReferenceConge(), 0));
           setReferenceConge(Double.valueOf(getReferenceCongeS().replace(" ", "").replace(",", ".").trim()).doubleValue());
            } else {
              
           setReferenceCongeS("");
           setReferenceConge(0.0D);
            } 
          } 
        }
      
      
      
      
      
        
        public void changeReferenceFraisMedicaux() {
          try {
         setReferenceFraisMedicaux(Double.valueOf(getReferenceFraisMedicauxS().replace(" ", "").replace(",", ".").trim()).doubleValue());
       } catch (Exception e) {
         setReferenceFraisMedicaux(0.0D);
          } finally {
         if (getReferenceFraisMedicaux() < 0.0D) {
           setReferenceFraisMedicaux(0.0D);
            }
         if (getReferenceFraisMedicaux() > 0.0D) {
              
           setReferenceFraisMedicauxS(HelperC.TraitementMontant.getMontantFormate(getReferenceFraisMedicaux(), 0));
           setReferenceFraisMedicaux(Double.valueOf(getReferenceFraisMedicauxS().replace(" ", "").replace(",", ".").trim()).doubleValue());
            } else {
              
           setReferenceFraisMedicauxS("");
           setReferenceFraisMedicaux(0.0D);
            } 
          } 
        }
      
      
      
      
        
        public void changeMontantANouveau() {
          try {
         setMontantANouveau(Double.valueOf(getMontantANouveauS().replace(" ", "").replace(",", ".").trim()).doubleValue());
       } catch (Exception e) {
         setMontantANouveau(0.0D);
          } finally {
         if (getMontantANouveau() < 0.0D) {
           setMontantANouveau(0.0D);
            }
         if (getMontantANouveau() > 0.0D) {
              
           setMontantANouveauS(HelperC.TraitementMontant.getMontantFormate(getMontantANouveau(), 0));
           setMontantANouveau(Double.valueOf(getMontantANouveauS().replace(" ", "").replace(",", ".").trim()).doubleValue());
            } else {
              
           setMontantANouveauS("");
           setMontantANouveau(0.0D);
            } 
          } 
        }
      
      
      
      
        
        public void changeMontantPeriode() {
          try {
         setMontantPeriode(Double.valueOf(getMontantPeriodeS().replace(" ", "").replace(",", ".").trim()).doubleValue());
       } catch (Exception e) {
         setMontantPeriode(0.0D);
          } finally {
         if (getMontantPeriode() < 0.0D) {
           setMontantPeriode(0.0D);
            }
         if (getMontantPeriode() > 0.0D) {
              
           setMontantPeriodeS(HelperC.TraitementMontant.getMontantFormate(getMontantPeriode(), 0));
           setMontantPeriode(Double.valueOf(getMontantPeriodeS().replace(" ", "").replace(",", ".").trim()).doubleValue());
            } else {
              
           setMontantPeriodeS("");
           setMontantPeriode(0.0D);
            } 
          } 
        }
      
      
      
        
        public void save() {
       if (getId() == 0 && !this.droit.isCreer()) {
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de cr�er");
       } else if (getId() > 0 && !this.droit.isModifier()) {
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de modifier");
       } else if (getEmploye() == null || getDesignation().trim().equalsIgnoreCase("") || getMontantPeriode() == 0.0D) {
         HelperC.afficherMessage("Information", "Veillez remplir tous les champs obligatoires");
          } else {
            
         this.selected = FactoryDAO.getInstance().getPrevisionFraisMedicaux(getEmploye(), getId());
         if (this.selected != null) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Information", "Cette prevision de frais m�dical  est d�ja saisie"));
            }
            else {
              
           Historique hist = new Historique();
           hist.setDateOperation(Calendar.getInstance().getTime());
           hist.setOperateur(this.operateur);
           if (getId() == 0) {
             hist.setOperation("Cr�ation des pr�visions des frais medicaux " + getCode());
              } else {
             hist.setOperation("Modification des pr�visions des frais medicaux " + getCode());
           }  hist.setTable(Tables.getTableName(Tables.TableName.previsionFraisMedicaux));
           setHistorique(hist);
           if (FactoryDAO.getInstance().insertUpdatePrevisionFraisMedicaux(this)) {
             HelperC.afficherMessage("F�licitation", "Enregistrement avec succ�");
             clear(true);
             this.allPrevisionFraisMedicaux = FactoryDAO.getInstance().getListAllPrevisionFraisMedicaux();
              } else {
                
             HelperC.afficherMessage("F�licitation", "Enregistrement avec succ�s");
              } 
            } 
          } 
        } public void delete() {
       if (this.selected == null) {
         HelperC.afficherAttention("ATTENTION", "Aucun �l�ment � supprimer");
       } else if (this.selected != null && !this.droit.isSupprimer()) {
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit de supprimer");
          } else {
         FactoryDAO.getInstance().delete(getId(), Tables.getTableName(Tables.TableName.previsionFraisMedicaux));
         HelperC.afficherMessage("F�licitation", "Enregistrement avec succ�s");
         clear(true);
         this.allPrevisionFraisMedicaux = FactoryDAO.getInstance().getListAllPrevisionFraisMedicaux();
          } 
        }
      
        
        private void setObject() {
       if (this.selected != null) {
         setId(this.selected.getId());
         setEmploye(this.selected.getEmploye());
         if (getEmploye() != null) {
           this.idEmploye = getEmploye().getId();
            } else {
           this.idEmploye = 0;
         }  setDesignation(this.selected.getDesignation());
         setReferenceConge(this.selected.getReferenceConge());
         setReferenceCongeS(this.selected.getReferenceCongeS());
         setReferenceFraisMedicaux(this.selected.getReferenceFraisMedicaux());
         setReferenceFraisMedicauxS(this.selected.getReferenceFraisMedicauxS());
         setMontantANouveau(this.selected.getMontantANouveau());
         setMontantANouveauS(this.selected.getMontantANouveauS());
         setMontantPeriode(this.selected.getMontantPeriode());
         setMontantPeriodeS(this.selected.getMontantPeriodeS());
         setPrevisionParEmploye(this.selected.getPrevisionParEmploye());
         setPrevisionParEmployeS(this.selected.getPrevisionParEmployeS());
         setPrevisionParPersonneACharge(this.selected.getPrevisionParPersonneACharge());
         setPrevisionParPersonneAChargeS(this.selected.getPrevisionParPersonneAChargeS());
          } 
        }
      
      
        
        private void setObject1() {
       if (this.selection != null) {
            
         setEmploye(this.selection);
         if (getEmploye() != null) {
           this.code = getEmploye().getCode();
      
      
            
            }
            else {
      
      
      
              
           this.code = "";
            } 
          } 
        }
      
        
        public void onRowselected1(SelectEvent event) {
       this.selection = (EmployeC)event.getObject();
       setObject1();
       if (getEmploye() != null) {
         this.selected = FactoryDAO.getInstance().getPrevisionFraisMedicaux(getEmploye());
         if (this.selected != null) {
           setObject();
            }
          } else {
         clear1(true);
          } 
        }
        public void onRowselected(SelectEvent event) {
       this.selected = (PrevisionFraisMedicauxC)event.getObject();
       setObject();
        }
      
        
        public void initialiser1() {
       clear1(true);
        }
        
        public void initialiser() {
       clear(true);
        }
       
      }


 