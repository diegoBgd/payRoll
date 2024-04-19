/*     */ package persistencePaie;
/*     */ 
/*     */ import classesPaie.BanqueC;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.CotisationC;
/*     */ import classesPaie.DeductionC;
/*     */ import classesPaie.DetailBanqueEmployeC;
/*     */ import classesPaie.DetailCotisationEmployeC;
/*     */ import classesPaie.DetailDeductionC;
/*     */ import classesPaie.DetailPrimeEmployeC;
/*     */ import classesPaie.EmployeC;
import classesPaie.HelperC;
/*     */ import classesPaie.PrimeIndemniteC;

/*     */ import java.io.Serializable;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecupererEmploye
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1900786180063404192L;
static   int size=0;
/*     */   public static void main(String[] args) {
/*  31 */     Connection conn = null;
/*  32 */     Statement stmt = null;
/*  33 */     ResultSet rs = null;
/*  34 */     EmployeC empl = null;
/*  35 */     EmployeC employe = null;
/*  36 */     String code = "";
/*  37 */     String nom = "";
/*  38 */     String prenom = "";
/*  39 */     String nationalite = "";
/*  40 */     String sexe = "";
/*  41 */     Date dateNaissance = null;
/*  42 */     String lieuNaissance = "";
/*  43 */     String etatCivil = "";
/*  44 */     String adresse1 = "";
/*  45 */     String adresse2 = "";
/*  46 */     String telHabitat = "";
/*  47 */     String telMobile = "";
/*  48 */     String fax = "";
/*  49 */     String email = "";
/*  50 */     String numeroPermis = "";
/*  51 */     String compteCCP = "";
/*  52 */     String numeroCarteMutuelle = "";
/*  53 */     String nomConjoint = "";
/*  54 */     String employeurConjoint = "";
/*  55 */     String profession = "";
/*  56 */     String categorie = "";
/*  57 */     String suffixeComptable = "";
/*  58 */     Date dateEntree = null;
/*  59 */     Date dateSortie = null;
/*  60 */     String sections = "";
/*  61 */     String services = "";
/*  62 */     String contrat = "";
/*  63 */     String caisseSocial = "";
/*  64 */     String lieuTravail = "";
/*  65 */     String conjoint = "";
/*  66 */     int enfant = 0;
/*  67 */     double complement = 0.0D;
/*  68 */     String modeReglement = "";
/*  69 */     int nombreHeureTravail = 0;
/*  70 */     String codeBanque1 = "";
/*  71 */     String codeBanque2 = "";
/*  72 */     String codeBanque3 = "";
/*  73 */     String codeBanque4 = "";
/*  74 */     String compteBanque1 = "";
/*  75 */     String compteBanque2 = "";
/*  76 */     String compteBanque3 = "";
/*  77 */     String compteBanque4 = "";
/*  78 */     float tauxBanque2 = 0.0F;
/*  79 */     float tauxBanque3 = 0.0F;
/*  80 */     float tauxBanque4 = 0.0F;
/*  81 */     double montantBase = 0.0D;
/*  82 */     float coteGratification = 0.0F;
/*  83 */     float pourcentageIndemniteLogement = 0.0F;
/*  84 */     String base = "";
/*  85 */     double montantIndemniteLogement = 0.0D;
/*  86 */     float nombreJoursConge = 0.0F;
/*  87 */     String photo = "";
/*  88 */     float nombreJoursCongeRecupere = 0.0F;
/*  89 */     String grade = "";
/*  90 */     int echelon = 0;
/*  91 */     int modeReglt = 0;
/*  92 */     String numeroCarteIdentite = "";
/*  93 */     Date dateDelivrance = null;
/*  94 */     Date dateDelivrancePermisConduire = null;
/*  95 */     String employeTransfert = "";
/*  96 */     String nouveauMatricule = "";
/*  97 */     boolean retraitTransportBrutImpot = false;
/*  98 */     String codeOrganismeBaseSalariale = "";
/*  99 */     boolean gratuite = false;
/* 100 */     double pointCotise = 0.0D;
/* 101 */     boolean occasionnel = false;
/* 102 */     boolean deuxiemeEmployeur = false;
/* 103 */     String codeSecretPresence = "";
/* 104 */     boolean heureSuppWeekend = false;
/* 105 */     float heureSupplementaireNuit = 0.0F;
/* 106 */     boolean droitHeureSupp = false;
/* 107 */     float heureSuppMatin = 0.0F;
/* 108 */     boolean droitTotalHeureSuppWeekend = false;
/* 109 */     String typeVisa = "";
/* 110 */     Date dateEmissionVisa = null;
/* 111 */     Date dateExpirationVisa = null;
/* 112 */     String typeVisaTravail = "";
/* 113 */     Date dateEmissionVisaTravail = null;
/* 114 */     Date dateExpirationVisaTravail = null;
/* 115 */     String paysEtat = "";
/*     */     
/*     */     try {
/* 118 */       String sql = "SELECT code_employe,nom,prenom,nationalite,sexe,date_naissance,lieu_naissance,etat_civile,adresse1,adresse2,tel_habitat,tel_mobile,fax,email,no_permis_conduire,compte_ccp,no_carte_mutuelle,nom_conjoint,employeur_conjoint,profession,categorie,suffixe_comptable,date_entree,date_sortie,sections,services,contrat,caisse_social,lieu_travail,conjoint,enfants,complement,mode_reglement,nbre_heure_travail,code_banque1,code_banque2,code_banque3,code_banque4,compte_banque1,compte_banque2,compte_banque3,compte_banque4,taux_banque2,taux_banque3,taux_banque4,montant_base,cote_gratification,pourcentage_indemnite_logement,base,montant_indemnite_logement,nbre_jour_conge,photo,nbre_jour_conge_recup,grade,echellon,no_carte_identite,date_delivrance_identite,date_delivrance_permis_conduire,employe_transfert,nouveau_matricule,retrait_transport_brut_impot,code_organisme_sal,gratuite,point_cotise,occasionnel,empl_deuxieme_emploi,code_secret_presence,heures_supp_weekend,heures_supp_nuit,droit_heures_supp,heures_supp_matin,droit_total_heures_supp_weekend,type_visa,date_emission_visa,date_expiration_visa,type_visa_travail,date_emission_visa_trav,date_expiration_visa_trav,pays_etat FROM  [PAIEUB1].[dbo].[tbl_employe] ";
     
/* 135 */       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
/* 136 */       String connectionString = "jdbc:sqlserver://A-PC-DIEUDONNE\\SQL2;database=PAIEUB1;user=sa;password=asyst@16";
/*     */       
/* 138 */       conn = DriverManager.getConnection(connectionString);
/* 139 */       stmt = conn.createStatement();
/* 140 */       for (rs = stmt.executeQuery(sql); rs.next(); )
/*     */       {
/* 142 */         code = rs.getString("code_employe");
/* 143 */         nom = rs.getString("nom");
/* 144 */         prenom = rs.getString("prenom");
/* 145 */         nationalite = rs.getString("nationalite");
/* 146 */         sexe = rs.getString("sexe");
/* 147 */         dateNaissance = rs.getDate("date_naissance");
/* 148 */         lieuNaissance = rs.getString("lieu_naissance");
/* 149 */         etatCivil = rs.getString("etat_civile");
/* 150 */         adresse1 = rs.getString("adresse1");
/* 151 */         adresse2 = rs.getString("adresse2");
/* 152 */         telHabitat = rs.getString("tel_habitat");
/* 153 */         telMobile = rs.getString("tel_mobile");
/* 154 */         fax = rs.getString("fax");
/*     */         
/* 156 */         if (rs.getObject("email") != null) {
/*     */           
/* 158 */           email = rs.getString("email");
/*     */         } else {
/*     */           
/* 161 */           email = "";
/*     */         } 
/* 163 */         numeroPermis = rs.getString("no_permis_conduire");
/* 164 */         compteCCP = rs.getString("compte_ccp");
/* 165 */         numeroCarteMutuelle = rs.getString("no_carte_mutuelle");
/* 166 */         nomConjoint = rs.getString("nom_conjoint");
/* 167 */         employeurConjoint = rs.getString("employeur_conjoint");
/* 168 */         profession = rs.getString("profession");
/* 169 */         categorie = rs.getString("categorie");
/* 170 */         suffixeComptable = rs.getString("suffixe_comptable");
/* 171 */         dateEntree = rs.getDate("date_entree");
/* 172 */         dateSortie = rs.getDate("date_sortie");
/* 173 */         sections = rs.getString("sections");
/* 174 */         services = rs.getString("services");
/* 175 */         contrat = rs.getString("contrat");
/* 176 */         caisseSocial = rs.getString("caisse_social");
/* 177 */         lieuTravail = rs.getString("lieu_travail");
/* 178 */         conjoint = rs.getString("conjoint");
/* 179 */         enfant = rs.getInt("enfants");
/* 180 */         complement = rs.getDouble("complement");
/* 181 */         modeReglement = rs.getString("mode_reglement");
/* 182 */         nombreHeureTravail = rs.getInt("nbre_heure_travail");
/* 183 */         codeBanque1 = rs.getString("code_banque1");
/* 184 */         codeBanque2 = rs.getString("code_banque2");
/* 185 */         codeBanque3 = rs.getString("code_banque3");
/* 186 */         codeBanque4 = rs.getString("code_banque4");
/* 187 */         compteBanque1 = rs.getString("compte_banque1");
/* 188 */         compteBanque2 = rs.getString("compte_banque2");
/* 189 */         compteBanque3 = rs.getString("compte_banque3");
/* 190 */         compteBanque4 = rs.getString("compte_banque4");
/* 191 */         tauxBanque2 = rs.getFloat("taux_banque2");
/* 192 */         tauxBanque3 = rs.getFloat("taux_banque3");
/* 193 */         tauxBanque4 = rs.getFloat("taux_banque4");
/* 194 */         montantBase = rs.getDouble("montant_base");
/* 195 */         coteGratification = rs.getFloat("cote_gratification");
/* 196 */         pourcentageIndemniteLogement = rs.getFloat("pourcentage_indemnite_logement");
/* 197 */         montantIndemniteLogement = rs.getDouble("montant_indemnite_logement");
/* 198 */         nombreJoursConge = rs.getFloat("nbre_jour_conge");
/* 199 */         photo = rs.getString("photo");
/* 200 */         nombreJoursCongeRecupere = rs.getFloat("nbre_jour_conge_recup");
/* 201 */         grade = rs.getString("grade");
/* 202 */         echelon = rs.getInt("echellon");
/* 203 */         numeroCarteIdentite = rs.getString("no_carte_identite");
/* 204 */         dateDelivrance = rs.getDate("date_delivrance_identite");
/* 205 */         dateDelivrancePermisConduire = rs.getDate("date_delivrance_permis_conduire");
/* 206 */         employeTransfert = rs.getString("employe_transfert");
/* 207 */         nouveauMatricule = rs.getString("nouveau_matricule");
/* 208 */         retraitTransportBrutImpot = rs.getBoolean("retrait_transport_brut_impot");
/* 209 */         codeOrganismeBaseSalariale = rs.getString("code_organisme_sal");
/* 210 */         gratuite = rs.getBoolean("gratuite");
/* 211 */         pointCotise = rs.getDouble("point_cotise");
/* 212 */         occasionnel = rs.getBoolean("occasionnel");
/* 213 */         deuxiemeEmployeur = rs.getBoolean("empl_deuxieme_emploi");
/* 214 */         codeSecretPresence = rs.getString("code_secret_presence");
/* 215 */         heureSuppWeekend = rs.getBoolean("heures_supp_weekend");
/* 216 */         heureSupplementaireNuit = rs.getFloat("heures_supp_nuit");
/* 217 */         droitHeureSupp = rs.getBoolean("droit_heures_supp");
/* 218 */         heureSuppMatin = rs.getFloat("heures_supp_matin");
/* 219 */         droitTotalHeureSuppWeekend = rs.getBoolean("droit_total_heures_supp_weekend");
/* 220 */         typeVisa = rs.getString("type_visa");
/* 221 */         dateEmissionVisa = rs.getDate("date_emission_visa");
/* 222 */         dateEmissionVisa = rs.getDate("date_expiration_visa");
/* 223 */         typeVisaTravail = rs.getString("type_visa_travail");
/* 224 */         dateEmissionVisaTravail = rs.getDate("date_emission_visa_trav");
/* 225 */         dateExpirationVisaTravail = rs.getDate("date_expiration_visa_trav");
/* 226 */         paysEtat = rs.getString("pays_etat");
/* 227 */         empl = new EmployeC();
/* 228 */         empl.setCode(code);
/* 229 */         empl.setNomPrenom(nom+" "+prenom);
/* 230 */         empl.setPrenom(prenom);
/* 231 */         empl.setAdresse(adresse1);
/* 232 */         empl.setDateDelivrance(dateDelivrance);
/* 233 */         empl.setEmail(email);
/* 234 */         empl.setTelMobile(telMobile);
/* 235 */         empl.setTelHabitat(telHabitat);
/* 236 */         empl.setUrlPhoto(photo);
/* 237 */         empl.setMatricule(code);
/* 238 */         empl.setSection(sections);
/* 239 */         empl.setDateEntre(dateEntree);
/* 240 */         empl.setDateSortie(dateSortie);
/* 241 */         empl.setDateNaissance(dateNaissance);
/* 242 */         empl.setLieuNaissance(lieuNaissance);
/* 243 */         empl.setNumCaisseSociale(caisseSocial);
/* 244 */         empl.setSuffixeComptable(suffixeComptable);
/* 245 */         if (etatCivil.equals("C"))
/*     */         {
/* 247 */           empl.setEtatCivil(0);
/*     */         }
/* 249 */         if (etatCivil.equals("M"))
/*     */         {
/* 251 */           empl.setEtatCivil(1);
/*     */         }
/* 253 */         if (etatCivil.equals("D"))
/*     */         {
/* 255 */           empl.setEtatCivil(2);
/*     */         }
/* 257 */         if (etatCivil.equals("V"))
/*     */         {
/* 259 */           empl.setEtatCivil(3);
/*     */         }
/* 261 */         if (sexe != null && sexe.equals("M"))
/*     */         {
/* 263 */           empl.setSexe(1);
/*     */         }
/* 265 */         if (sexe != null && sexe.equals("F"))
/*     */         {
/* 267 */           empl.setSexe(2);
/*     */         }
/* 269 */         empl.setNombreHeureNormal(nombreHeureTravail);
/* 270 */         empl.setPourcentageLogement(pourcentageIndemniteLogement);
/* 271 */         empl.setSalaireBase(montantBase);
/* 272 */         empl.setEmployeOccasionnel(occasionnel);
/* 273 */         empl.setDeuxiemeEmployeur(deuxiemeEmployeur);
/* 274 */         if (modeReglement.equals("V"))
/*     */         {
/* 276 */           empl.setModeReglement(Constante.getModeRegelement(Constante.ModeReglement.Virement));
/*     */         }
/* 278 */         if (modeReglement.equals("E"))
/*     */         {
/* 280 */           empl.setModeReglement(Constante.getModeRegelement(Constante.ModeReglement.Espece));
/*     */         }
/* 282 */         if (modeReglement.equals("M"))
/*     */         {
/* 284 */           empl.setModeReglement(Constante.getModeRegelement(Constante.ModeReglement.Mandat));
/*     */         }
/* 286 */         empl.setEmployeurConjoint(employeurConjoint);
/* 287 */         empl.setNumCAMMut(numeroCarteMutuelle);
        			// empl.setListeDetailPrime(getListPrime(empl.getCode(), conn));
/* 289 */           //empl.setListeDetailDeduction(getListDeductionPaie(empl.getCode(),HelperC.toDate(2021, 1, 31),"2021",conn));
      				// empl.setListeDetailCotisation(getListCotisation(empl.getCode(), conn));
       				//empl.setListBanquePaiement(getListBanque(empl.getCode(), conn));
/* 292 */         	employe = FactoryDAO.getInstance().getEmploye(empl.getCode(), false);
/*     */         
/* 294 */         if (employe != null)
/*     */         {
/* 296 */            empl.setId(employe.getId());
					// UtilitaireDAO.getInstance().insertDetailDeductionEmploye(employe.getId(), empl.getListeDetailDeduction()) ;
					 UtilitaireDAO.getInstance().updateDateSortie(empl.getId(), empl.getDateSortie());
					 System.out.println(empl.getCode() + " " + empl.getNomPrenom());
					 if(empl.getDateSortie()==null)
						 size++;
/*     */         }
			
/* 298 */        // FactoryDAO.getInstance().insertUpdateEmploye(empl);
/* 299 */       //  System.out.println(String.valueOf(empl.getCode()) + " " + empl.getNom() + " " + empl.getPrenom());
/* 300 */         employe = null;
/*     */       
/*     */       }
			   System.out.println("---------------------------------");
/*     */      System.out.println(size);
/*     */     }
/* 305 */     catch (Exception e) {
/*     */       
/* 307 */       System.out.println(e.getMessage());
/* 308 */       e.printStackTrace();
/*     */     } 
/* 310 */     if (rs != null) {
/*     */       
/*     */       try {
/*     */         
/* 314 */         rs.close();
/*     */       }
/* 316 */       catch (Exception exception) {}
/*     */     }
/* 318 */     if (stmt != null) {
/*     */       
/*     */       try {
/*     */         
/* 322 */         stmt.close();
/*     */       }
/* 324 */       catch (Exception exception) {}
/*     */     }
/* 326 */     if (conn != null) {
/*     */       
/*     */       try {
/*     */         
/* 330 */         conn.close();
/*     */       }
/* 332 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 335 */     if (rs != null) {
/*     */       
/*     */       try {
/*     */         
/* 339 */         rs.close();
/*     */       }
/* 341 */       catch (Exception exception) {}
/*     */     }
/* 343 */     if (stmt != null) {
/*     */       
/*     */       try {
/*     */         
/* 347 */         stmt.close();
/*     */       }
/* 349 */       catch (Exception exception) {}
/*     */     }
/* 351 */     if (conn != null) {
/*     */       
/*     */       try {
/*     */         
/* 355 */         conn.close();
/*     */       }
/* 357 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 360 */     if (rs != null) {
/*     */       
/*     */       try {
/*     */         
/* 364 */         rs.close();
/*     */       }
/* 366 */       catch (Exception exception) {}
/*     */     }
/* 368 */     if (stmt != null) {
/*     */       
/*     */       try {
/*     */         
/* 372 */         stmt.close();
/*     */       }
/* 374 */       catch (Exception exception) {}
/*     */     }
/* 376 */     if (conn != null) {
/*     */       
/*     */       try {
/*     */         
/* 380 */         conn.close();
/*     */       }
/* 382 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<DetailPrimeEmployeC> getListPrime(String codeEmploye, Connection con) {
/* 389 */     Statement stmt = null;
/* 390 */     ResultSet rs = null;
/* 391 */     DetailPrimeEmployeC primeIndem = null;
/* 392 */     List<DetailPrimeEmployeC> listPrime = new ArrayList<DetailPrimeEmployeC>();
/* 393 */     PrimeIndemniteC prm = null;
/* 394 */     String sql = "SELECT code_prime,code_employe,montant FROM [PAIEUB1].[dbo].[tbl_prime_indemnite_soumise_cotisation_octroye]  WHERE code_employe='" + 
/*     */       
/* 396 */       codeEmploye + "'";
/*     */     
/*     */     try {
/* 399 */       stmt = con.createStatement();
/* 400 */       rs = stmt.executeQuery(sql);
/* 401 */       while (rs.next())
/*     */       {
/* 403 */         primeIndem = new DetailPrimeEmployeC();
/* 404 */         primeIndem.setMontant(rs.getDouble("montant"));
/* 405 */         prm = FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getString("code_prime"));
/* 406 */         if (prm != null)
/*     */         {
/* 408 */           primeIndem.setPrime(prm);
/* 409 */           listPrime.add(primeIndem);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 414 */     } catch (Exception e) {
/*     */       
/* 416 */       System.out.println(e.getMessage());
/* 417 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 421 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 425 */           rs.close();
/*     */         }
/* 427 */         catch (Exception exception) {}
/*     */       }
/* 429 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 433 */           stmt.close();
/*     */         }
/* 435 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 439 */     return listPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<DetailDeductionC> getListDeduction(String codeEmploye, Connection con) {
/* 444 */     Statement stmt = null;
/* 445 */     ResultSet rs = null;
/* 446 */     DetailDeductionC detail = null;
/* 447 */     List<DetailDeductionC> listDeduction = new ArrayList<DetailDeductionC>();
/* 448 */     DeductionC ded = null;
/* 449 */     String sql = "SELECT code_deduction,code_employe,montant FROM [PAIEUB1].[dbo].[tbl_deduction_octroye]  WHERE code_employe='" + 
/*     */       
/* 451 */       codeEmploye + "'";
/*     */     
/*     */     try {
/* 454 */       stmt = con.createStatement();
/* 455 */       rs = stmt.executeQuery(sql);
/*     */       
/* 457 */       while (rs.next())
/*     */       {
/* 459 */         detail = new DetailDeductionC();
/* 460 */         detail.setMontant(rs.getDouble("montant"));
/* 461 */         ded = FichierBaseDAO.getInstance().getDeduction(rs.getString("code_deduction"));
/* 462 */         if (ded != null)
/*     */         {
/* 464 */           detail.setDeduction(ded);
/* 465 */           listDeduction.add(detail);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 470 */     } catch (Exception e) {
/*     */       
/* 472 */       System.out.println(e.getMessage());
/* 473 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 477 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 481 */           rs.close();
/*     */         }
/* 483 */         catch (Exception exception) {}
/*     */       }
/* 485 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 489 */           stmt.close();
/*     */         }
/* 491 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 495 */     return listDeduction;
/*     */   }
/*     */ 

/*     */   private static List<DetailDeductionC> getListDeductionPaie(String codeEmploye,Date datePaie,String exercice, Connection con) {
/* 444 */     Statement stmt = null;
/* 445 */     ResultSet rs = null;
/* 446 */     DetailDeductionC detail = null;
/* 447 */     List<DetailDeductionC> listDeduction = new ArrayList<DetailDeductionC>();
/* 448 */     DeductionC ded = null;
/* 449 */     String sql = "SELECT code_deduction,code_employe,montant FROM [PAIEUB1].[dbo].[tbl_deduction_paie]  WHERE code_employe='" + 
/*     */       
/* 451 */       codeEmploye + "' AND date_paie='"+HelperC.convertDate(datePaie, false)+"' AND no_dossier='"+exercice+"'";
/*     */    
/*     */     try {
/* 454 */       stmt = con.createStatement();
/* 455 */       rs = stmt.executeQuery(sql);
/*     */       
/* 457 */       while (rs.next())
/*     */       {
/* 459 */         detail = new DetailDeductionC();
/* 460 */         detail.setMontant(rs.getDouble("montant"));
/* 461 */         ded = FichierBaseDAO.getInstance().getDeduction(rs.getString("code_deduction"));
				  
/* 462 */         if (ded != null)
/*     */         {
/* 464 */           detail.setDeduction(ded);
/* 465 */           listDeduction.add(detail);
					size++;
         		  }
//				if(ded!=null)
//					System.out.println(ded.getCode()+" "+HelperC.decimalNumber(detail.getMontant(), 0, true));
//				else
//					System.out.println("NULL= "+rs.getString("code_deduction")+" "+HelperC.decimalNumber(detail.getMontant(), 0, true));
/*     */       }
/*     */     	System.out.println(size);

/* 470 */     } catch (Exception e) {
/*     */       
/* 472 */       System.out.println(e.getMessage());
/* 473 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 477 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 481 */           rs.close();
/*     */         }
/* 483 */         catch (Exception exception) {}
/*     */       }
/* 485 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 489 */           stmt.close();
/*     */         }
/* 491 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 495 */     return listDeduction;
/*     */   }
/*     */ 

/*     */   
/*     */   private static List<DetailCotisationEmployeC> getListCotisation(String codeEmploye, Connection con) {
/* 500 */     Statement stmt = null;
/* 501 */     ResultSet rs = null;
/* 502 */     DetailCotisationEmployeC detail = null;
/* 503 */     List<DetailCotisationEmployeC> listCotisation = new ArrayList<DetailCotisationEmployeC>();
/* 504 */     CotisationC cot = null;
/* 505 */     String sql = "SELECT code_cotisation,code_employe,part_salarial,part_patronal "+
		 				   "FROM [PAIEUB1].[dbo].[tbl_cotisation_sociale_octroye]  WHERE code_employe='" + 
/* 506 */       codeEmploye + "'";
/*     */     
/*     */     try {
/* 509 */       stmt = con.createStatement();
/* 510 */       rs = stmt.executeQuery(sql);
/* 511 */       while (rs.next())
/*     */       {
/* 513 */         detail = new DetailCotisationEmployeC();
/* 514 */         detail.setMontantPatronal(rs.getDouble("part_patronal"));
/* 515 */         detail.setMontantSalarial(rs.getDouble("part_salarial"));
/* 516 */         cot = FichierBaseDAO.getInstance().getCotisation(rs.getString("code_cotisation"));
/* 517 */         if (cot != null)
/*     */         {
/* 519 */           detail.setCotisation(cot);
/* 520 */           listCotisation.add(detail);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 525 */     } catch (Exception e) {
/*     */       
/* 527 */       System.out.println(e.getMessage());
/* 528 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 532 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 536 */           rs.close();
/*     */         }
/* 538 */         catch (Exception exception) {}
/*     */       }
/* 540 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 544 */           stmt.close();
/*     */         }
/* 546 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 550 */     return listCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<DetailBanqueEmployeC> getListBanque(String codeEmploye, Connection con) {
/* 555 */     Statement stmt = null;
/* 556 */     ResultSet rs = null;
/* 557 */     DetailBanqueEmployeC detail = null;
/* 558 */     List<DetailBanqueEmployeC> listBk = new ArrayList<DetailBanqueEmployeC>();
/* 559 */     BanqueC bk = null;
/* 560 */     String sql = "SELECT code_banque1,compte_banque1 FROM [PAIEUB1].[dbo].[tbl_employe]  WHERE code_employe='" + 
/* 561 */       codeEmploye + "' AND code_banque1 IS NOT NULL";
/*     */     
/*     */     try {
/* 564 */       stmt = con.createStatement();
/* 565 */       rs = stmt.executeQuery(sql);
/* 566 */       while (rs.next())
/*     */       {
/* 568 */         detail = new DetailBanqueEmployeC();
/* 569 */         bk = FichierBaseDAO.getInstance().getBanque(rs.getString("code_banque1"));
/* 570 */         detail.setNumeroCompte(rs.getString("compte_banque1"));
/* 571 */         detail.setBanque(bk);
/* 572 */         listBk.add(detail);
/*     */       }
/*     */     
/*     */     }
/* 576 */     catch (Exception e) {
/*     */       
/* 578 */       System.out.println(e.getMessage());
/* 579 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 583 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 587 */           rs.close();
/*     */         }
/* 589 */         catch (Exception exception) {}
/*     */       }
/* 591 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 595 */           stmt.close();
/*     */         }
/* 597 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 601 */     return listBk;
/*     */   }


/*     */ }


