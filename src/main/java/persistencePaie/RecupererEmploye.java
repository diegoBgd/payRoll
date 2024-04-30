 package persistencePaie;
 
 import classesPaie.BanqueC;
 import classesPaie.Constante;
 import classesPaie.CotisationC;
 import classesPaie.DeductionC;
 import classesPaie.DetailBanqueEmployeC;
 import classesPaie.DetailCotisationEmployeC;
 import classesPaie.DetailDeductionC;
 import classesPaie.DetailPrimeEmployeC;
 import classesPaie.EmployeC;
import classesPaie.HelperC;
 import classesPaie.PrimeIndemniteC;

 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 
 
 
 
 public class RecupererEmploye
   implements Serializable
 {
   private static final long serialVersionUID = 1900786180063404192L;
static   int size=0;
   public static void main(String[] args) {
     Connection conn = null;
     Statement stmt = null;
     ResultSet rs = null;
     EmployeC empl = null;
     EmployeC employe = null;
     String code = "";
     String nom = "";
     String prenom = "";
     String nationalite = "";
     String sexe = "";
     Date dateNaissance = null;
     String lieuNaissance = "";
     String etatCivil = "";
     String adresse1 = "";
     String adresse2 = "";
     String telHabitat = "";
     String telMobile = "";
     String fax = "";
     String email = "";
     String numeroPermis = "";
     String compteCCP = "";
     String numeroCarteMutuelle = "";
     String nomConjoint = "";
     String employeurConjoint = "";
     String profession = "";
     String categorie = "";
     String suffixeComptable = "";
     Date dateEntree = null;
     Date dateSortie = null;
     String sections = "";
     String services = "";
     String contrat = "";
     String caisseSocial = "";
     String lieuTravail = "";
     String conjoint = "";
     int enfant = 0;
     double complement = 0.0D;
     String modeReglement = "";
     int nombreHeureTravail = 0;
     String codeBanque1 = "";
     String codeBanque2 = "";
     String codeBanque3 = "";
     String codeBanque4 = "";
     String compteBanque1 = "";
     String compteBanque2 = "";
     String compteBanque3 = "";
     String compteBanque4 = "";
     float tauxBanque2 = 0.0F;
     float tauxBanque3 = 0.0F;
     float tauxBanque4 = 0.0F;
     double montantBase = 0.0D;
     float coteGratification = 0.0F;
     float pourcentageIndemniteLogement = 0.0F;
     String base = "";
     double montantIndemniteLogement = 0.0D;
     float nombreJoursConge = 0.0F;
     String photo = "";
     float nombreJoursCongeRecupere = 0.0F;
     String grade = "";
     int echelon = 0;
     int modeReglt = 0;
     String numeroCarteIdentite = "";
     Date dateDelivrance = null;
     Date dateDelivrancePermisConduire = null;
     String employeTransfert = "";
     String nouveauMatricule = "";
     boolean retraitTransportBrutImpot = false;
     String codeOrganismeBaseSalariale = "";
     boolean gratuite = false;
     double pointCotise = 0.0D;
     boolean occasionnel = false;
     boolean deuxiemeEmployeur = false;
     String codeSecretPresence = "";
     boolean heureSuppWeekend = false;
     float heureSupplementaireNuit = 0.0F;
     boolean droitHeureSupp = false;
     float heureSuppMatin = 0.0F;
     boolean droitTotalHeureSuppWeekend = false;
     String typeVisa = "";
     Date dateEmissionVisa = null;
     Date dateExpirationVisa = null;
     String typeVisaTravail = "";
     Date dateEmissionVisaTravail = null;
     Date dateExpirationVisaTravail = null;
     String paysEtat = "";
     
     try {
       String sql = "SELECT code_employe,nom,prenom,nationalite,sexe,date_naissance,lieu_naissance,etat_civile,adresse1,adresse2,tel_habitat,tel_mobile,fax,email,no_permis_conduire,compte_ccp,no_carte_mutuelle,nom_conjoint,employeur_conjoint,profession,categorie,suffixe_comptable,date_entree,date_sortie,sections,services,contrat,caisse_social,lieu_travail,conjoint,enfants,complement,mode_reglement,nbre_heure_travail,code_banque1,code_banque2,code_banque3,code_banque4,compte_banque1,compte_banque2,compte_banque3,compte_banque4,taux_banque2,taux_banque3,taux_banque4,montant_base,cote_gratification,pourcentage_indemnite_logement,base,montant_indemnite_logement,nbre_jour_conge,photo,nbre_jour_conge_recup,grade,echellon,no_carte_identite,date_delivrance_identite,date_delivrance_permis_conduire,employe_transfert,nouveau_matricule,retrait_transport_brut_impot,code_organisme_sal,gratuite,point_cotise,occasionnel,empl_deuxieme_emploi,code_secret_presence,heures_supp_weekend,heures_supp_nuit,droit_heures_supp,heures_supp_matin,droit_total_heures_supp_weekend,type_visa,date_emission_visa,date_expiration_visa,type_visa_travail,date_emission_visa_trav,date_expiration_visa_trav,pays_etat FROM  [PAIEUB1].[dbo].[tbl_employe] ";
     
       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       String connectionString = "jdbc:sqlserver://A-PC-DIEUDONNE\\SQL2;database=PAIEUB1;user=sa;password=asyst@16";
       
       conn = DriverManager.getConnection(connectionString);
       stmt = conn.createStatement();
       for (rs = stmt.executeQuery(sql); rs.next(); )
       {
         code = rs.getString("code_employe");
         nom = rs.getString("nom");
         prenom = rs.getString("prenom");
         nationalite = rs.getString("nationalite");
         sexe = rs.getString("sexe");
         dateNaissance = rs.getDate("date_naissance");
         lieuNaissance = rs.getString("lieu_naissance");
         etatCivil = rs.getString("etat_civile");
         adresse1 = rs.getString("adresse1");
         adresse2 = rs.getString("adresse2");
         telHabitat = rs.getString("tel_habitat");
         telMobile = rs.getString("tel_mobile");
         fax = rs.getString("fax");
         
         if (rs.getObject("email") != null) {
           
           email = rs.getString("email");
         } else {
           
           email = "";
         } 
         numeroPermis = rs.getString("no_permis_conduire");
         compteCCP = rs.getString("compte_ccp");
         numeroCarteMutuelle = rs.getString("no_carte_mutuelle");
         nomConjoint = rs.getString("nom_conjoint");
         employeurConjoint = rs.getString("employeur_conjoint");
         profession = rs.getString("profession");
         categorie = rs.getString("categorie");
         suffixeComptable = rs.getString("suffixe_comptable");
         dateEntree = rs.getDate("date_entree");
         dateSortie = rs.getDate("date_sortie");
         sections = rs.getString("sections");
         services = rs.getString("services");
         contrat = rs.getString("contrat");
         caisseSocial = rs.getString("caisse_social");
         lieuTravail = rs.getString("lieu_travail");
         conjoint = rs.getString("conjoint");
         enfant = rs.getInt("enfants");
         complement = rs.getDouble("complement");
         modeReglement = rs.getString("mode_reglement");
         nombreHeureTravail = rs.getInt("nbre_heure_travail");
         codeBanque1 = rs.getString("code_banque1");
         codeBanque2 = rs.getString("code_banque2");
         codeBanque3 = rs.getString("code_banque3");
         codeBanque4 = rs.getString("code_banque4");
         compteBanque1 = rs.getString("compte_banque1");
         compteBanque2 = rs.getString("compte_banque2");
         compteBanque3 = rs.getString("compte_banque3");
         compteBanque4 = rs.getString("compte_banque4");
         tauxBanque2 = rs.getFloat("taux_banque2");
         tauxBanque3 = rs.getFloat("taux_banque3");
         tauxBanque4 = rs.getFloat("taux_banque4");
         montantBase = rs.getDouble("montant_base");
         coteGratification = rs.getFloat("cote_gratification");
         pourcentageIndemniteLogement = rs.getFloat("pourcentage_indemnite_logement");
         montantIndemniteLogement = rs.getDouble("montant_indemnite_logement");
         nombreJoursConge = rs.getFloat("nbre_jour_conge");
         photo = rs.getString("photo");
         nombreJoursCongeRecupere = rs.getFloat("nbre_jour_conge_recup");
         grade = rs.getString("grade");
         echelon = rs.getInt("echellon");
         numeroCarteIdentite = rs.getString("no_carte_identite");
         dateDelivrance = rs.getDate("date_delivrance_identite");
         dateDelivrancePermisConduire = rs.getDate("date_delivrance_permis_conduire");
         employeTransfert = rs.getString("employe_transfert");
         nouveauMatricule = rs.getString("nouveau_matricule");
         retraitTransportBrutImpot = rs.getBoolean("retrait_transport_brut_impot");
         codeOrganismeBaseSalariale = rs.getString("code_organisme_sal");
         gratuite = rs.getBoolean("gratuite");
         pointCotise = rs.getDouble("point_cotise");
         occasionnel = rs.getBoolean("occasionnel");
         deuxiemeEmployeur = rs.getBoolean("empl_deuxieme_emploi");
         codeSecretPresence = rs.getString("code_secret_presence");
         heureSuppWeekend = rs.getBoolean("heures_supp_weekend");
         heureSupplementaireNuit = rs.getFloat("heures_supp_nuit");
         droitHeureSupp = rs.getBoolean("droit_heures_supp");
         heureSuppMatin = rs.getFloat("heures_supp_matin");
         droitTotalHeureSuppWeekend = rs.getBoolean("droit_total_heures_supp_weekend");
         typeVisa = rs.getString("type_visa");
         dateEmissionVisa = rs.getDate("date_emission_visa");
         dateEmissionVisa = rs.getDate("date_expiration_visa");
         typeVisaTravail = rs.getString("type_visa_travail");
         dateEmissionVisaTravail = rs.getDate("date_emission_visa_trav");
         dateExpirationVisaTravail = rs.getDate("date_expiration_visa_trav");
         paysEtat = rs.getString("pays_etat");
         empl = new EmployeC();
         empl.setCode(code);
         empl.setNomPrenom(nom+" "+prenom);
         empl.setPrenom(prenom);
         empl.setAdresse(adresse1);
         empl.setDateDelivrance(dateDelivrance);
         empl.setEmail(email);
         empl.setTelMobile(telMobile);
         empl.setTelHabitat(telHabitat);
         empl.setUrlPhoto(photo);
         empl.setMatricule(code);
         empl.setSection(sections);
         empl.setDateEntre(dateEntree);
         empl.setDateSortie(dateSortie);
         empl.setDateNaissance(dateNaissance);
         empl.setLieuNaissance(lieuNaissance);
         empl.setNumCaisseSociale(caisseSocial);
         empl.setSuffixeComptable(suffixeComptable);
         if (etatCivil.equals("C"))
         {
           empl.setEtatCivil(0);
         }
         if (etatCivil.equals("M"))
         {
           empl.setEtatCivil(1);
         }
         if (etatCivil.equals("D"))
         {
           empl.setEtatCivil(2);
         }
         if (etatCivil.equals("V"))
         {
           empl.setEtatCivil(3);
         }
         if (sexe != null && sexe.equals("M"))
         {
           empl.setSexe(1);
         }
         if (sexe != null && sexe.equals("F"))
         {
           empl.setSexe(2);
         }
         empl.setNombreHeureNormal(nombreHeureTravail);
         empl.setPourcentageLogement(pourcentageIndemniteLogement);
         empl.setSalaireBase(montantBase);
         empl.setEmployeOccasionnel(occasionnel);
         empl.setDeuxiemeEmployeur(deuxiemeEmployeur);
         if (modeReglement.equals("V"))
         {
           empl.setModeReglement(Constante.getModeRegelement(Constante.ModeReglement.Virement));
         }
         if (modeReglement.equals("E"))
         {
           empl.setModeReglement(Constante.getModeRegelement(Constante.ModeReglement.Espece));
         }
         if (modeReglement.equals("M"))
         {
           empl.setModeReglement(Constante.getModeRegelement(Constante.ModeReglement.Mandat));
         }
         empl.setEmployeurConjoint(employeurConjoint);
         empl.setNumCAMMut(numeroCarteMutuelle);
        			// empl.setListeDetailPrime(getListPrime(empl.getCode(), conn));
           //empl.setListeDetailDeduction(getListDeductionPaie(empl.getCode(),HelperC.toDate(2021, 1, 31),"2021",conn));
      				// empl.setListeDetailCotisation(getListCotisation(empl.getCode(), conn));
       				//empl.setListBanquePaiement(getListBanque(empl.getCode(), conn));
         	employe = FactoryDAO.getInstance().getEmploye(empl.getCode(), false);
         
         if (employe != null)
         {
            empl.setId(employe.getId());
					// UtilitaireDAO.getInstance().insertDetailDeductionEmploye(employe.getId(), empl.getListeDetailDeduction()) ;
					 UtilitaireDAO.getInstance().updateDateSortie(empl.getId(), empl.getDateSortie());
					 System.out.println(empl.getCode() + " " + empl.getNomPrenom());
					 if(empl.getDateSortie()==null)
						 size++;
         }
			
        // FactoryDAO.getInstance().insertUpdateEmploye(empl);
       //  System.out.println(String.valueOf(empl.getCode()) + " " + empl.getNom() + " " + empl.getPrenom());
         employe = null;
       
       }
			   System.out.println("---------------------------------");
      System.out.println(size);
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     } 
     if (rs != null) {
       
       try {
         
         rs.close();
       }
       catch (Exception exception) {}
     }
     if (stmt != null) {
       
       try {
         
         stmt.close();
       }
       catch (Exception exception) {}
     }
     if (conn != null) {
       
       try {
         
         conn.close();
       }
       catch (Exception exception) {}
     }
     
     if (rs != null) {
       
       try {
         
         rs.close();
       }
       catch (Exception exception) {}
     }
     if (stmt != null) {
       
       try {
         
         stmt.close();
       }
       catch (Exception exception) {}
     }
     if (conn != null) {
       
       try {
         
         conn.close();
       }
       catch (Exception exception) {}
     }
     
     if (rs != null) {
       
       try {
         
         rs.close();
       }
       catch (Exception exception) {}
     }
     if (stmt != null) {
       
       try {
         
         stmt.close();
       }
       catch (Exception exception) {}
     }
     if (conn != null) {
       
       try {
         
         conn.close();
       }
       catch (Exception exception) {}
     }
   }
 
 
   
   private static List<DetailPrimeEmployeC> getListPrime(String codeEmploye, Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     DetailPrimeEmployeC primeIndem = null;
     List<DetailPrimeEmployeC> listPrime = new ArrayList<DetailPrimeEmployeC>();
     PrimeIndemniteC prm = null;
     String sql = "SELECT code_prime,code_employe,montant FROM [PAIEUB1].[dbo].[tbl_prime_indemnite_soumise_cotisation_octroye]  WHERE code_employe='" + 
       
       codeEmploye + "'";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         primeIndem = new DetailPrimeEmployeC();
         primeIndem.setMontant(rs.getDouble("montant"));
         prm = FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getString("code_prime"));
         if (prm != null)
         {
           primeIndem.setPrime(prm);
           listPrime.add(primeIndem);
         }
       
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listPrime;
   }
 
   
   private static List<DetailDeductionC> getListDeduction(String codeEmploye, Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     DetailDeductionC detail = null;
     List<DetailDeductionC> listDeduction = new ArrayList<DetailDeductionC>();
     DeductionC ded = null;
     String sql = "SELECT code_deduction,code_employe,montant FROM [PAIEUB1].[dbo].[tbl_deduction_octroye]  WHERE code_employe='" + 
       
       codeEmploye + "'";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       
       while (rs.next())
       {
         detail = new DetailDeductionC();
         detail.setMontant(rs.getDouble("montant"));
         ded = FichierBaseDAO.getInstance().getDeduction(rs.getString("code_deduction"));
         if (ded != null)
         {
           detail.setDeduction(ded);
           listDeduction.add(detail);
         }
       
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listDeduction;
   }
 

   private static List<DetailDeductionC> getListDeductionPaie(String codeEmploye,Date datePaie,String exercice, Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     DetailDeductionC detail = null;
     List<DetailDeductionC> listDeduction = new ArrayList<DetailDeductionC>();
     DeductionC ded = null;
     String sql = "SELECT code_deduction,code_employe,montant FROM [PAIEUB1].[dbo].[tbl_deduction_paie]  WHERE code_employe='" + 
       
       codeEmploye + "' AND date_paie='"+HelperC.convertDate(datePaie, false)+"' AND no_dossier='"+exercice+"'";
    
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       
       while (rs.next())
       {
         detail = new DetailDeductionC();
         detail.setMontant(rs.getDouble("montant"));
         ded = FichierBaseDAO.getInstance().getDeduction(rs.getString("code_deduction"));
				  
         if (ded != null)
         {
           detail.setDeduction(ded);
           listDeduction.add(detail);
					size++;
         		  }
//				if(ded!=null)
//					System.out.println(ded.getCode()+" "+HelperC.decimalNumber(detail.getMontant(), 0, true));
//				else
//					System.out.println("NULL= "+rs.getString("code_deduction")+" "+HelperC.decimalNumber(detail.getMontant(), 0, true));
       }
     	System.out.println(size);

     } catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listDeduction;
   }
 

   
   private static List<DetailCotisationEmployeC> getListCotisation(String codeEmploye, Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     DetailCotisationEmployeC detail = null;
     List<DetailCotisationEmployeC> listCotisation = new ArrayList<DetailCotisationEmployeC>();
     CotisationC cot = null;
     String sql = "SELECT code_cotisation,code_employe,part_salarial,part_patronal "+
		 				   "FROM [PAIEUB1].[dbo].[tbl_cotisation_sociale_octroye]  WHERE code_employe='" + 
       codeEmploye + "'";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         detail = new DetailCotisationEmployeC();
         detail.setMontantPatronal(rs.getDouble("part_patronal"));
         detail.setMontantSalarial(rs.getDouble("part_salarial"));
         cot = FichierBaseDAO.getInstance().getCotisation(rs.getString("code_cotisation"));
         if (cot != null)
         {
           detail.setCotisation(cot);
           listCotisation.add(detail);
         }
       
       }
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listCotisation;
   }
 
   
   private static List<DetailBanqueEmployeC> getListBanque(String codeEmploye, Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     DetailBanqueEmployeC detail = null;
     List<DetailBanqueEmployeC> listBk = new ArrayList<DetailBanqueEmployeC>();
     BanqueC bk = null;
     String sql = "SELECT code_banque1,compte_banque1 FROM [PAIEUB1].[dbo].[tbl_employe]  WHERE code_employe='" + 
       codeEmploye + "' AND code_banque1 IS NOT NULL";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         detail = new DetailBanqueEmployeC();
         bk = FichierBaseDAO.getInstance().getBanque(rs.getString("code_banque1"));
         detail.setNumeroCompte(rs.getString("compte_banque1"));
         detail.setBanque(bk);
         listBk.add(detail);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listBk;
   }


 }


