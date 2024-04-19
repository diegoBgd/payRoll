 package classesPaie;
 
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 
 public class EmployeC
   implements Serializable
 {
   private static final long serialVersionUID = 7998642975120007812L;
   private int id;
   private int nombreHeureNormal;
   private int complement;
   private int modeReglement;
   private int etatCivil;
   private int typeEmploye;
   private int sexe;
   private int basePaiement;
   private int age,anciennete;
   private int idPersnl;
   private int idCatgrie;
   private int idGrd;
   private int idNvFormt;
   private int idFnctn;
   private int idServce;
			private int jourConge;
   private int idDirection;
   private int idDepartement;
   private double pourcentageLogement;
   private double salaireBase;
   private double pourcentageSalaire;
   private double montantLogement;
   private Date dateDelivrance;
   private Date dateNaissance;
   private Date dateEntre;
   private Date dateSortie;
   private Date dateDebutContrat;
   private Date dateFinContrat;
   private String code;
   private String nom;
   private String prenom;
   private String adresse;
   private String cni;
   private String telService;
   private String email;
   private String telHabitat;
   private String telMobile;
   private List<DetailPrimeEmployeC> listeDetailPrime = new ArrayList<DetailPrimeEmployeC>(); 
            private String matricule; 
            private String section; 
            private String suffixeComptable; 
            private String numCaisseSociale; 
            private String lieuNaissance; 
            private String numCAMMut; 
            private String urlPhoto; 
            private String employeurConjoint; 
            private String dateDelivranceS; 
            private String dateNaissanceS;
            private String dateEntreS; 
            private String dateSortieS; 
            private String dateDebutContratS; 
            private String dateFinContratS,dateRetraiteS; 
            private String libelleNationalite;
            private String nomPrenom; 
            private String codeLine; 
            private boolean employeOccasionnel; 
            private boolean deuxiemeEmployeur; 
            private boolean etat; 
            private boolean etatProfession; 
            private boolean etatGrade; 
            private boolean etatOrgane; 
            private boolean etatNiveauFormation; 
            private boolean partenaire; 
            private boolean baseInclutLogement; 
            private Base contrat; 
            private Base staff; 
            private Base fonction; 
            private AffectationC affectationEmploye; 
            private CategoriePersonnelC categoriePersonnel; 
            private GradePersonnelC gradePersonnel; 
            private CotisationC cotisation; 
            private Historique historique; 
            private DetailGradeC detailGrade; 
            private EmployeDetailContratC detailContrat; 
            private Base niveauFormation; 
            private DetailOrganeC detailOrgane; 
            private DetailNiveauFormationC detailNiveauFormation;
   private List<DetailPrimeEmployeC> listeDetailPrimeDeleted = new ArrayList<DetailPrimeEmployeC>();
   private List<DetailIndemniteEmployeC> listeDetailIndemnite = new ArrayList<DetailIndemniteEmployeC>();
   private List<DetailIndemniteEmployeC> listeDetailIndemniteDeleted = new ArrayList<DetailIndemniteEmployeC>();
   private List<DetailCotisationEmployeC> listeDetailCotisation = new ArrayList<DetailCotisationEmployeC>();
   private List<DetailCotisationEmployeC> listeDetailCotisationDeleted = new ArrayList<DetailCotisationEmployeC>();
			private List<DetailComissionEmployeC> listeDetailComission = new ArrayList<DetailComissionEmployeC>();
			private List<DetailComissionEmployeC> listeDetailComissionDeleted = new ArrayList<DetailComissionEmployeC>();
   private List<DetailDeductionC> listeDetailDeduction = new ArrayList<DetailDeductionC>();
   private List<DetailDeductionC> listeDetailDeductionDeleted = new ArrayList<DetailDeductionC>();
   private List<DetailBanqueEmployeC> listBanquePaiement = new ArrayList<DetailBanqueEmployeC>();
   private List<DetailBanqueEmployeC> listBanquePaiementDeleted = new ArrayList<DetailBanqueEmployeC>();
			private Base directionUb; private Base lieuTravail;
   private SousServiceC sousService;
   private DirectionC direction;
   private ServicesC service;
   private Date dateRetraite;

			public int getIdDepartement() {
				return idDepartement;
			}
			public void setIdDepartement(int idDepartement) {
				this.idDepartement = idDepartement;
			}
   public int getTypeEmploye() {
     return this.typeEmploye;
   }
   
   public void setTypeEmploye(int typeEmploye) {
     this.typeEmploye = typeEmploye;
   }
   
   public Base getLieuTravail() {
     return this.lieuTravail;
   }
   
   public void setLieuTravail(Base lieuTravail) {
     this.lieuTravail = lieuTravail;
   }
   
   public SousServiceC getSousService() {
     return this.sousService;
   }
   
   public void setSousService(SousServiceC sousService) {
     this.sousService = sousService;
   }
   
   public EmployeDetailContratC getDetailContrat() {
     return this.detailContrat;
   }
   
   public void setDetailContrat(EmployeDetailContratC detailContrat) {
     this.detailContrat = detailContrat;
   }
   
   public double getMontantLogement() {
     return this.montantLogement;
   }
   
   public void setMontantLogement(double montantLogement) {
     this.montantLogement = montantLogement;
   }
   
   public boolean isBaseInclutLogement() {
     return this.baseInclutLogement;
   }
   
   public void setBaseInclutLogement(boolean baseInclutLogement) {
     this.baseInclutLogement = baseInclutLogement;
   }
   
   public boolean isEtatProfession() {
     return this.etatProfession;
   }
   
   public void setEtatProfession(boolean etatProfession) {
     this.etatProfession = etatProfession;
   }
   
   public boolean isEtatGrade() {
     return this.etatGrade;
   }
 
   
   public void setEtatGrade(boolean etatGrade) {
     this.etatGrade = etatGrade;
   }
 
   
   public boolean isEtatOrgane() {
     return this.etatOrgane;
   }
   
   public void setEtatOrgane(boolean etatOrgane) {
     this.etatOrgane = etatOrgane;
   }
   
   public DetailOrganeC getDetailOrgane() {
     return this.detailOrgane;
   }
   
   public void setDetailOrgane(DetailOrganeC detailOrgane) {
     this.detailOrgane = detailOrgane;
   }
   
   public boolean isEtat() {
     return this.etat;
   }
   
   public void setEtat(boolean etat) {
     this.etat = etat;
   }
   
   public DetailGradeC getDetailGrade() {
     return this.detailGrade;
   }
   
   public void setDetailGrade(DetailGradeC detailGrade) {
     this.detailGrade = detailGrade;
   }
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public int getNombreHeureNormal() {
     return this.nombreHeureNormal;
   }
   
   public void setNombreHeureNormal(int nombreHeureNormal) {
     this.nombreHeureNormal = nombreHeureNormal;
   }
   
   public int getComplement() {
     return this.complement;
   }
   
   public void setComplement(int complement) {
     this.complement = complement;
   }
   
   public double getPourcentageLogement() {
     return this.pourcentageLogement;
   }
   
   public void setPourcentageLogement(double pourcentageLogement) {
     this.pourcentageLogement = pourcentageLogement;
   }
   
   public double getSalaireBase() {
     return this.salaireBase;
   }
   
   public void setSalaireBase(double salaireBase) {
     this.salaireBase = salaireBase;
   }
   
   public Date getDateDelivrance() {
     return this.dateDelivrance;
   }
   
   public void setDateDelivrance(Date dateDelivrance) {
     this.dateDelivrance = dateDelivrance;
   }
   
   public Date getDateNaissance() {
     return this.dateNaissance;
   }
   
   public void setDateNaissance(Date dateNaissance) {
     this.dateNaissance = dateNaissance;
   }
   
   public Date getDateEntre() {
     return this.dateEntre;
   }
   
   public void setDateEntre(Date dateEntre) {
     this.dateEntre = dateEntre;
   }
   
   public Date getDateSortie() {
     return this.dateSortie;
   }
   
   public void setDateSortie(Date dateSortie) {
     this.dateSortie = dateSortie;
   }
   
   public String getCode() {
     return this.code;
   }
   
   public void setCode(String code) {
     this.code = code;
   }
   
   public String getNom() {
     return this.nom;
   }
   
   public void setNom(String nom) {
     this.nom = nom;
   }
   
   public String getPrenom() {
     return this.prenom;
   }
   
   public void setPrenom(String prenom) {
     this.prenom = prenom;
   }
 
   
   public String getCni() {
     return this.cni;
   }
   
   public void setCni(String cni) {
     this.cni = cni;
   }
   
   public String getTelService() {
     return this.telService;
   }
   
   public void setTelService(String telService) {
     this.telService = telService;
   }
   
   public String getEmail() {
     return this.email;
   }
   
   public void setEmail(String email) {
     this.email = email;
   }
   
   public String getTelHabitat() {
     return this.telHabitat;
   }
   
   public void setTelHabitat(String telHabitat) {
     this.telHabitat = telHabitat;
   }
   
   public String getTelMobile() {
     return this.telMobile;
   }
   
   public void setTelMobile(String telMobile) {
     this.telMobile = telMobile;
   }
   
   public String getMatricule() {
     return this.matricule;
   }
   
   public void setMatricule(String matricule) {
     this.matricule = matricule;
   }
   
   public String getSection() {
     return this.section;
   }
   
   public void setSection(String section) {
     this.section = section;
   }
   
   public int getModeReglement() {
     return this.modeReglement;
   }
   
   public void setModeReglement(int modeReglement) {
     this.modeReglement = modeReglement;
   }
   
   public String getSuffixeComptable() {
     return this.suffixeComptable;
   }
   
   public void setSuffixeComptable(String suffixeComptable) {
     this.suffixeComptable = suffixeComptable;
   }
   
   public String getNumCaisseSociale() {
     return this.numCaisseSociale;
   }
   
   public void setNumCaisseSociale(String numCaisseSociale) {
     this.numCaisseSociale = numCaisseSociale;
   }
   
   public String getLieuNaissance() {
     return this.lieuNaissance;
   }
   
   public void setLieuNaissance(String lieuNaissance) {
     this.lieuNaissance = lieuNaissance;
   }
   
   public int getSexe() {
     return this.sexe;
   }
   
   public void setSexe(int sexe) {
     this.sexe = sexe;
   }
   
   public int getEtatCivil() {
     return this.etatCivil;
   }
   
   public void setEtatCivil(int etatCivil) {
     this.etatCivil = etatCivil;
   }
   
   public String getNumCAMMut() {
     return this.numCAMMut;
   }
   
   public void setNumCAMMut(String numCAMMut) {
     this.numCAMMut = numCAMMut;
   }
   
   public String getUrlPhoto() {
     return this.urlPhoto;
   }
   
   public void setUrlPhoto(String urlPhoto) {
     this.urlPhoto = urlPhoto;
   }
   
   public int getBasePaiement() {
     return this.basePaiement;
   }
   
   public void setBasePaiement(int basePaiement) {
     this.basePaiement = basePaiement;
   }
   
   public String getEmployeurConjoint() {
     return this.employeurConjoint;
   }
   
   public void setEmployeurConjoint(String employeurConjoint) {
     this.employeurConjoint = employeurConjoint;
   }
   
   public boolean isEmployeOccasionnel() {
     return this.employeOccasionnel;
   }
   
   public void setEmployeOccasionnel(boolean employeOccasionnel) {
     this.employeOccasionnel = employeOccasionnel;
   }
   
   public boolean isDeuxiemeEmployeur() {
     return this.deuxiemeEmployeur;
   }
   
   public void setDeuxiemeEmployeur(boolean deuxiemeEmployeur) {
     this.deuxiemeEmployeur = deuxiemeEmployeur;
   }
   
   public Base getContrat() {
     return this.contrat;
   }
   public void setContrat(Base contrat) {
     this.contrat = contrat;
   }
   public List<DetailPrimeEmployeC> getListeDetailPrime() {
     return this.listeDetailPrime;
   }
   
   public void setListeDetailPrime(List<DetailPrimeEmployeC> listeDetailPrime) {
     this.listeDetailPrime = listeDetailPrime;
   }
   
   public List<DetailPrimeEmployeC> getListeDetailPrimeDeleted() {
     return this.listeDetailPrimeDeleted;
   }
 
   
   public void setListeDetailPrimeDeleted(List<DetailPrimeEmployeC> listeDetailPrimeDeleted) {
     this.listeDetailPrimeDeleted = listeDetailPrimeDeleted;
   }
   
   public List<DetailIndemniteEmployeC> getListeDetailIndemnite() {
     return this.listeDetailIndemnite;
   }
 
   
   public void setListeDetailIndemnite(List<DetailIndemniteEmployeC> listeDetailIndemnite) {
     this.listeDetailIndemnite = listeDetailIndemnite;
   }
   
   public List<DetailIndemniteEmployeC> getListeDetailIndemniteDeleted() {
     return this.listeDetailIndemniteDeleted;
   }
 
   
   public void setListeDetailIndemniteDeleted(List<DetailIndemniteEmployeC> listeDetailIndemniteDeleted) {
     this.listeDetailIndemniteDeleted = listeDetailIndemniteDeleted;
   }
   
   public List<DetailDeductionC> getListeDetailDeduction() {
     return this.listeDetailDeduction;
   }
   
   public void setListeDetailDeduction(List<DetailDeductionC> listeDetailDeduction) {
     this.listeDetailDeduction = listeDetailDeduction;
   }
   
   public List<DetailDeductionC> getListeDetailDeductionDeleted() {
     return this.listeDetailDeductionDeleted;
   }
 
   
   public void setListeDetailDeductionDeleted(List<DetailDeductionC> listeDetailDeductionDeleted) {
     this.listeDetailDeductionDeleted = listeDetailDeductionDeleted;
   }
   
   public List<DetailBanqueEmployeC> getListBanquePaiement() {
     return this.listBanquePaiement;
   }
   
   public void setListBanquePaiement(List<DetailBanqueEmployeC> listBanquePaiement) {
     this.listBanquePaiement = listBanquePaiement;
   }
   
   public List<DetailBanqueEmployeC> getListBanquePaiementDeleted() {
     return this.listBanquePaiementDeleted;
   }
 
   
   public void setListBanquePaiementDeleted(List<DetailBanqueEmployeC> listBanquePaiementDeleted) {
     this.listBanquePaiementDeleted = listBanquePaiementDeleted;
   }
   
   public String getDateDelivranceS() {
     return this.dateDelivranceS;
   }
   
   public void setDateDelivranceS(String dateDelivranceS) {
     this.dateDelivranceS = dateDelivranceS;
   }
   
   public String getDateNaissanceS() {
     return this.dateNaissanceS;
   }
   
   public void setDateNaissanceS(String dateNaissanceS) {
     this.dateNaissanceS = dateNaissanceS;
   }
   
   public String getDateEntreS() {
     return this.dateEntreS;
   }
   
   public void setDateEntreS(String dateEntreS) {
     this.dateEntreS = dateEntreS;
   }
   
   public String getDateSortieS() {
     return this.dateSortieS;
   }
   
   public void setDateSortieS(String dateSortieS) {
     this.dateSortieS = dateSortieS;
   }
   
   public Date getDateDebutContrat() {
     return this.dateDebutContrat;
   }
   
   public void setDateDebutContrat(Date dateDebutContrat) {
     this.dateDebutContrat = dateDebutContrat;
   }
   
   public String getDateDebutContratS() {
     return this.dateDebutContratS;
   }
   
   public void setDateDebutContratS(String dateDebutContratS) {
     this.dateDebutContratS = dateDebutContratS;
   }
   
   public Date getDateFinContrat() {
     return this.dateFinContrat;
   }
   
   public void setDateFinContrat(Date dateFinContrat) {
     this.dateFinContrat = dateFinContrat;
   }
   
   public String getDateFinContratS() {
     return this.dateFinContratS;
   }
   
   public void setDateFinContratS(String dateFinContratS) {
     this.dateFinContratS = dateFinContratS;
   }
   
   public double getPourcentageSalaire() {
     return this.pourcentageSalaire;
   }
   
   public void setPourcentageSalaire(double pourcentageSalaire) {
     this.pourcentageSalaire = pourcentageSalaire;
   }
   
   public Historique getHistorique() {
     return this.historique;
   }
   
   public void setHistorique(Historique historique) {
     this.historique = historique;
   }
   
   public CotisationC getCotisation() {
     return this.cotisation;
   }
   
   public void setCotisation(CotisationC cotisation) {
     this.cotisation = cotisation;
   }
   
   public List<DetailCotisationEmployeC> getListeDetailCotisation() {
     return this.listeDetailCotisation;
   }
 
   
   public void setListeDetailCotisation(List<DetailCotisationEmployeC> listeDetailCotisation) {
     this.listeDetailCotisation = listeDetailCotisation;
   }
   
   public List<DetailCotisationEmployeC> getListeDetailCotisationDeleted() {
     return this.listeDetailCotisationDeleted;
   }
 
   
   public void setListeDetailCotisationDeleted(List<DetailCotisationEmployeC> listeDetailCotisationDeleted) {
     this.listeDetailCotisationDeleted = listeDetailCotisationDeleted;
   }
   
   public boolean isEtatNiveauFormation() {
     return this.etatNiveauFormation;
   }
   
   public void setEtatNiveauFormation(boolean etatNiveauFormation) {
     this.etatNiveauFormation = etatNiveauFormation;
   }
   
   public Base getStaff() {
     return this.staff;
   }
   
   public void setStaff(Base staff) {
     this.staff = staff;
   }
   
   public CategoriePersonnelC getCategoriePersonnel() {
     return this.categoriePersonnel;
   }
   
   public void setCategoriePersonnel(CategoriePersonnelC categoriePersonnel) {
     this.categoriePersonnel = categoriePersonnel;
   }
   
   public GradePersonnelC getGradePersonnel() {
     return this.gradePersonnel;
   }
   
   public void setGradePersonnel(GradePersonnelC gradePersonnel) {
     this.gradePersonnel = gradePersonnel;
   }
   
   public String getLibelleNationalite() {
     return this.libelleNationalite;
   }
   
   public void setLibelleNationalite(String libelleNationalite) {
     this.libelleNationalite = libelleNationalite;
   }
   
   public Base getDirectionUb() {
     return this.directionUb;
   }
   
   public void setDirectionUb(Base directionUb) {
     this.directionUb = directionUb;
   }
 
   
   public DirectionC getDirection() {
     return this.direction;
   }
   
   public void setDirection(DirectionC direction) {
     this.direction = direction;
   }
   
   public ServicesC getService() {
     return this.service;
   }
   
   public void setService(ServicesC service) {
     this.service = service;
   }
   
   public Base getNiveauFormation() {
     return this.niveauFormation;
   }
   
   public void setNiveauFormation(Base niveauFormation) {
     this.niveauFormation = niveauFormation;
   }
   
   public boolean isPartenaire() {
     return this.partenaire;
   }
   
   public void setPartenaire(boolean partenaire) {
     this.partenaire = partenaire;
   }
 
   
   public int getIdServce() {
     return this.idServce;
   }
 
   
   public void setIdServce(int idServce) {
     this.idServce = idServce;
   }
   

   public int getIdDirection() {
     return this.idDirection;
   }
   
   public void setIdDirection(int idDirection) {
     this.idDirection = idDirection;
   }
   

			public Date getDateRetraite() {
				return dateRetraite;
			}
			public void setDateRetraite(Date dateRetraite) {
				this.dateRetraite = dateRetraite;
			}
 
 
   
   public String getNomPrenom() {
     return this.nomPrenom;
   }
   
   public void setNomPrenom(String nomPrenom) {
     this.nomPrenom = nomPrenom;
   }
   
   public String getAdresse() {
     return this.adresse;
   }
   
   public void setAdresse(String adresse) {
     this.adresse = adresse;
   }
   
   public DetailNiveauFormationC getDetailNiveauFormation() {
     return this.detailNiveauFormation;
   }
 
   
   public void setDetailNiveauFormation(DetailNiveauFormationC detailNiveauFormation) {
     this.detailNiveauFormation = detailNiveauFormation;
   }
   
   public Base getFonction() {
     return this.fonction;
   }
   
   public void setFonction(Base fonction) {
     this.fonction = fonction;
   }
   
   public AffectationC getAffectationEmploye() {
     return this.affectationEmploye;
   }
   
   public void setAffectationEmploye(AffectationC affectationEmploye) {
     this.affectationEmploye = affectationEmploye;
   }

            
			public String getDateRetraiteS() {
				return dateRetraiteS;
			}
		
			public void setDateRetraiteS(String dateRetraiteS) {
				this.dateRetraiteS = dateRetraiteS;
			}
		
			public int getAnciennete() {
				if (getDateEntre() != null)
					anciennete = (int) HelperC.daysBetween(getDateEntre(), new Date()) / 365;
				return anciennete;
			}
			public void setAnciennete(int anciennete) {
				this.anciennete = anciennete;
			}
   public int getAge() {
     this.age = 0;
     if (getDateNaissance() != null)
       this.age = (int)HelperC.daysBetween(getDateNaissance(), new Date()) / 365; 
     return this.age;
   }
   
   public void setAge(int age) {
     this.age = age;
   }
   
   public int getIdPersnl() {
     return this.idPersnl;
   }
   
   public void setIdPersnl(int idPersnl) {
     this.idPersnl = idPersnl;
   }
   
   public int getIdCatgrie() {
     return this.idCatgrie;
   }
   
   public void setIdCatgrie(int idCatgrie) {
     this.idCatgrie = idCatgrie;
   }
   
   public int getIdGrd() {
     return this.idGrd;
   }
 
   
   public void setIdGrd(int idGrd) {
     this.idGrd = idGrd;
   }
   
   public int getIdNvFormt() {
     return this.idNvFormt;
   }
   
   public void setIdNvFormt(int idNvFormt) {
     this.idNvFormt = idNvFormt;
   }
   
   public int getIdFnctn() {
     return this.idFnctn;
   }
   
   public void setIdFnctn(int idFnctn) {
     this.idFnctn = idFnctn;
   }
			public String getCodeLine() {
				return codeLine;
			}
			public void setCodeLine(String codeLine) {
				this.codeLine = codeLine;
			}
			public List<DetailComissionEmployeC> getListeDetailComission() {
				return listeDetailComission;
			}
			public void setListeDetailComission(List<DetailComissionEmployeC> listeDetailComission) {
				this.listeDetailComission = listeDetailComission;
			}
			public List<DetailComissionEmployeC> getListeDetailComissionDeleted() {
				return listeDetailComissionDeleted;
			}
			public void setListeDetailComissionDeleted(List<DetailComissionEmployeC> listeDetailComissionDeleted) {
				this.listeDetailComissionDeleted = listeDetailComissionDeleted;
			}
			public int getJourConge() {
				return jourConge;
			}
			public void setJourConge(int jourConge) {
				this.jourConge = jourConge;
			}

 }


