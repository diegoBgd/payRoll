 package classesPaie;
 
 import java.io.Serializable;
 
 public class Constante
   implements Serializable
 {
   private static final long serialVersionUID = 9202745023707800690L;
   
   public enum Base
   {
     SaisieManuel, SalaireBaseNormalSansAbsenceNiHeureSuppl, SalaireBaseNormalMoinsAbsenceSansHeureSuppl, SalaireBaseNormalMoinsAbsencePlusHeureSuppl;
   }
 
   
   public static Base getBase(int ba) {
     Base bas = null;
     
     switch (ba) {
       case 1:
         bas = Base.SaisieManuel;
         break;
       case 2:
         bas = Base.SalaireBaseNormalSansAbsenceNiHeureSuppl;
         break;
       case 3:
         bas = Base.SalaireBaseNormalMoinsAbsenceSansHeureSuppl;
         break;
       case 4:
         bas = Base.SalaireBaseNormalMoinsAbsencePlusHeureSuppl;
         break;
     } 
 
     
     return bas;
   }
 
   
   public static int getBase(Base bas) {
     int ba = 0;
     
     switch (bas) {
       case SaisieManuel:
         ba = 1;
         break;
       case SalaireBaseNormalSansAbsenceNiHeureSuppl:
         ba = 2;
         break;
       case SalaireBaseNormalMoinsAbsenceSansHeureSuppl:
         ba = 3;
         break;
       case SalaireBaseNormalMoinsAbsencePlusHeureSuppl:
         ba = 4;
         break;
     } 
 
     
     return ba;
   }
 
   
   public static String getLibelleBase(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Saisie manuelle";
         break;
       case 2:
         libelle = "Salaire base normal sans absence ni heure supplémentaire";
         break;
       case 3:
         libelle = "Salaire base normal moin absence sans heure supplémentaire";
         break;
       case 4:
         libelle = "Salaire base normal moin absence plus heure supplémentaire";
         break;
     } 
 
     
     return libelle;
   }
 
   
   public static String getLibelleBase(Base bas) {
     String libelle = "";
     
     switch (bas) {
       case SaisieManuel:
         libelle = "Saisie manuelle";
         break;
       case SalaireBaseNormalSansAbsenceNiHeureSuppl:
         libelle = "Salaire base normal sans absence ni heure supplémentaire";
         break;
       case SalaireBaseNormalMoinsAbsenceSansHeureSuppl:
         libelle = "Salaire base normal moin absence sans heure supplémentaire";
         break;
       case SalaireBaseNormalMoinsAbsencePlusHeureSuppl:
         libelle = "Salaire base normal moin absence plus heure supplémentaire";
         break;
     } 
 
     
     return libelle;
   }
 
   
   public enum Imposabilite
   {
     nonImposable, imposable;
   }
   
   public static Imposabilite getImposabilite(int s) {
     Imposabilite impos = null;
     switch (s) {
       case 1:
         impos = Imposabilite.nonImposable;
         break;
       case 2:
         impos = Imposabilite.imposable;
         break;
     } 
 
 
     
     return impos;
   }
 
   
   public static int getImposabilite(Imposabilite impos) {
     int imp = 0;
     switch (impos) {
       case nonImposable:
         imp = 1;
         break;
       case imposable:
         imp = 2;
         break;
     } 
 
     
     return imp;
   }
 
   
   public static String getLibelleImposabilite(int impos) {
     String libelle = "";
     
     switch (impos) {
       case 1:
         libelle = "non imposable";
         break;
       case 2:
         libelle = "imposable";
         break;
     } 
 
     
     return libelle;
   }
 
   
   public enum Charge
   {
     impotSurEmploye, chargePatronale;
   }
   
   public static Charge getCharge(int s) {
     Charge charg = null;
     switch (s) {
       case 1:
         charg = Charge.impotSurEmploye;
         break;
       case 2:
         charg = Charge.chargePatronale;
         break;
     } 
 
     
     return charg;
   }
 
   
   public static int getCharge(Charge charg) {
     int cha = 0;
     switch (charg) {
       case impotSurEmploye:
         cha = 1;
         break;
       case chargePatronale:
         cha = 2;
         break;
     } 
 
     
     return cha;
   }
 
   
   public static String getLibelleCharge(int cha) {
     String libelle = "";
     
     switch (cha) {
       case 1:
         libelle = "impot sur employé";
         break;
       case 2:
         libelle = "charge patronale";
         break;
     } 
 
 
     
     return libelle;
   }
 
 
 
   
   public enum Role
   {
      superUtilisateur,structureAdministrative, parametrage, fichierBase, elementPaie, profilEmploye,
     creditAvance, bulletinPaie, gestionConge, gestionPosition, gestionAbsencePresence,
     mesuresDisciplinaires, organisationAcademique, premiereEvaluation, deuxiemeEvaluation,
     troisiemeEvaluation, saisieSanction, saisieRecourSanction, traitementRecours, parametrageConge,
     saisiePlanConge, validationPlanConge, saisieDemandeConge, validationDemandeConge, clotureConge,
     parametrageFinCarriere, demandeProlongationRetraite, validationProlongation, demandeRetraiteAnticipe,
     validationRetraiteAnticipe, finCarriere;
   } 
   
   public static Role getRole(int r) {
     Role role = null;
     switch (r) {
				 case 1:
         role = Role.superUtilisateur;
         break;
       case 2:
         role = Role.structureAdministrative;
         break;
      
       case 3:
         role = Role.parametrage;
         break;
       case 4:
         role = Role.fichierBase;
         break;
       case 5:
         role = Role.elementPaie;
         break;
       case 6:
         role = Role.profilEmploye;
         break;
       case 7:
         role = Role.creditAvance;
         break;
       case 8:
         role = Role.bulletinPaie;
         break;
       case 9:
         role = Role.gestionConge;
         break;
       case 10:
         role = Role.gestionPosition;
         break;
       case 11:
         role = Role.gestionAbsencePresence;
         break;
       case 12:
         role = Role.mesuresDisciplinaires;
         break;
       case 13:
         role = Role.organisationAcademique;
         break;
       case 14:
         role = Role.premiereEvaluation;
         break;
       case 15:
         role = Role.deuxiemeEvaluation;
         break;
       case 16:
         role = Role.troisiemeEvaluation;
         break;
       case 17:
         role = Role.saisieSanction;
         break;
       case 18:
         role = Role.saisieRecourSanction;
         break;
       case 19:
         role = Role.traitementRecours;
         break;
       case 20:
         role = Role.parametrageConge;
         break;
       case 21:
         role = Role.saisiePlanConge;
         break;
       case 22:
         role = Role.validationPlanConge;
         break;
       case 23:
         role = Role.saisieDemandeConge;
         break;
       case 24:
         role = Role.validationDemandeConge;
         break;
       case 25:
         role = Role.clotureConge;
         break;
       case 26:
         role = Role.parametrageFinCarriere;
         break;
       case 27:
         role = Role.demandeProlongationRetraite;
         break;
       case 28:
         role = Role.validationProlongation;
         break;
       case 29:
         role = Role.demandeRetraiteAnticipe;
         break;
       case 30:
         role = Role.validationRetraiteAnticipe;
         break;
       case 31:
         role = Role.finCarriere;
         break;
     } 
     
     return role;
   }
   
   public static String getLibelleRole(Role role) {
     String lib = "";
     switch (role) {
				   case superUtilisateur:
         lib = "Utilisateurs et des droits ";
         break;
       case structureAdministrative:
         lib = "Structure Administrative";
         break;
      
       case parametrage:
         lib = "Paramétres généraux";
         break;
       case fichierBase:
         lib = "Fichiers de base";
         break;
       case elementPaie:
         lib = "Eléments de paie";
         break;
       case profilEmploye:
         lib = "Fiche employé";
         break;
       case creditAvance:
         lib = "Crédits/Avance";
         break;
       case bulletinPaie:
         lib = "Bulletins de paie";
         break;
       case gestionConge:
         lib = "Gestion des congés";
         break;
       case gestionPosition:
         lib = "Gestion des positions";
         break;
       case gestionAbsencePresence:
         lib = "Gestion des présences et absences";
         break;
       case mesuresDisciplinaires:
         lib = "Mesures Disciplinaires";
         break;
       case organisationAcademique:
         lib = "Organisation Académique";
         break;
       case premiereEvaluation:
         lib = "Premiérevaluation";
         break;
       case deuxiemeEvaluation:
         lib = "Deuxiémevaluation";
         break;
       case troisiemeEvaluation:
         lib = "Troisiémevaluation";
         break;
       case saisieSanction:
         lib = "Saisir les sanctions";
         break;
       case saisieRecourSanction:
         lib = "Saisir des recours sur les sanctions";
         break;
       case traitementRecours:
         lib = "Traitement des recours";
         break;
       case parametrageConge:
         lib = "Paramétrage congé";
         break;
       case validationPlanConge:
         lib = "Validation plan congé";
         break;
       case saisieDemandeConge:
         lib = "Saisie demande congé";
         break;
       case validationDemandeConge:
         lib = "Validation demande congé";
         break;
       case saisiePlanConge:
         lib = "Saisie du plan de congé";
         break;
       case clotureConge:
         lib = "Cléture de congé";
         break;
       case demandeProlongationRetraite:
         lib = "Demande prolongation retraite";
         break;
       case finCarriere:
         lib = "Fin de carriére";
         break;
       case demandeRetraiteAnticipe:
         lib = "Demande retraite anticipée";
         break;
       case parametrageFinCarriere:
         lib = "Paramétrage fin carriére";
         break;
       case validationProlongation:
         lib = "Validation prolongation retraite";
         break;
       case validationRetraiteAnticipe:
         lib = "Validation retraite anticipée";
         break;
     } 
     
     return lib;
   }
   
   public static int getRole(Role role) {
     int r = 0;
     
		switch (role) {
		case superUtilisateur:
			r = 1;
			return r;
		case structureAdministrative:
			r = 1;

			return r;
		
		case parametrage:
			r = 3;
			return r;
		case fichierBase:
			r = 4;
			return r;
		case elementPaie:
			r = 5;
			return r;
		case profilEmploye:
			r = 6;
			return r;
		case creditAvance:
			r = 7;
			return r;
		case bulletinPaie:
			r = 8;
			return r;
		case gestionConge:
			r = 9;
			return r;
		case gestionPosition:
			r = 10;
			return r;
		case gestionAbsencePresence:
			r = 11;
			return r;
		case mesuresDisciplinaires:
			r = 12;
			return r;
		case organisationAcademique:
			r = 13;
			return r;
		case premiereEvaluation:
			r = 14;
			return r;
		case deuxiemeEvaluation:
			r = 15;
			return r;
		case troisiemeEvaluation:
			r = 16;
			return r;
		case saisieSanction:
			r = 17;
			return r;
		case saisieRecourSanction:
			r = 18;
			return r;
		case traitementRecours:
			r = 19;
			return r;
		case parametrageConge:
			r = 20;
			return r;
		case saisiePlanConge:
			r = 21;
			return r;
		case validationPlanConge:
			r = 22;
			return r;
		case saisieDemandeConge:
			r = 23;
			return r;
		case validationDemandeConge:
			r = 24;
			return r;
		case clotureConge:
			r = 25;
			return r;
		case parametrageFinCarriere:
			r = 26;
			return r;
		case demandeProlongationRetraite:
			r = 27;
			return r;
		case validationProlongation:
			r = 28;
			return r;
		case demandeRetraiteAnticipe:
			r = 29;
			return r;
		case validationRetraiteAnticipe:
			r = 30;
			return r;
		case finCarriere:
			r = 31;
			return r;
		}
		r = 0;
		return r;
	}
 
 
   
   public enum TypePrime
   {
     unePrime, uneIndemnite;
   }
   
   public static TypePrime getTypePrime(int r) {
     TypePrime type = null;
     switch (r) {
       case 1:
         type = TypePrime.unePrime;
         break;
       case 2:
         type = TypePrime.uneIndemnite;
         break;
     } 
 
     
     return type;
   }
   
   public static String getLibelleTypePrime(TypePrime type) {
     String lib = "";
     
     switch (type) {
       case unePrime:
         lib = "est Une prime";
         break;
       case uneIndemnite:
         lib = "est une indemnité";
         break;
     } 
 
     
     return lib;
   }
   
   public static int getTypePrime(TypePrime type) {
     int r = 0;
     
     switch (type) {
       case unePrime:
         r = 1;
         break;
       case uneIndemnite:
         r = 2;
         break;
     } 
     
     return r;
   }
 
   
   public enum TypeJourFerie
   {
     WE, CF;
   }
 
   
   public static int getTypeJourFerie(TypeJourFerie typeJourFerie) {
     int id = 0;
     switch (typeJourFerie) {
       case CF:
         id = 1;
         break;
       case WE:
         id = 2;
         break;
     } 
 
 
     
     return id;
   }
   
   public static TypeJourFerie getTypeJourFerie(int typeJourFerie) {
     TypeJourFerie type = null;
     switch (typeJourFerie) {
       case 1:
         type = TypeJourFerie.CF;
         break;
       case 2:
         type = TypeJourFerie.WE;
         break;
     } 
 
 
     
     return type;
   }
   
   public static String getLibelleTypeJourFerie(int typeJourFerie) {
     String type = "";
     switch (typeJourFerie) {
       case 1:
         type = "Jour Férié";
         break;
       case 2:
         type = "Week End";
         break;
     } 
 
 
     
     return type;
   }
 
 
   
   public enum Frequence
   {
     mensuelle, annuelle, trimestrielle, semestrielle, fixe, quinzaine;
   }
   
   public static Frequence getFrequence(int r) {
     Frequence freq = null;
     switch (r) {
       case 1:
         freq = Frequence.quinzaine;
         break;
       case 2:
         freq = Frequence.mensuelle;
         break;
       case 3:
         freq = Frequence.trimestrielle;
         break;
       case 4:
         freq = Frequence.semestrielle;
         break;
       case 5:
         freq = Frequence.annuelle;
         break;
       case 6:
         freq = Frequence.fixe;
         break;
     } 
     
     return freq;
   }
   
   public static String getLibelleFrequence(Frequence freq) {
     String lib = "";
     
     switch (freq) {
       case quinzaine:
         lib = "quinzaine";
         break;
       case mensuelle:
         lib = "mensuelle";
         break;
       case trimestrielle:
         lib = "trimestrielle";
         break;
       case semestrielle:
         lib = "semestrielle";
         break;
       case annuelle:
         lib = "annuelle";
         break;
       case fixe:
         lib = "fixe";
         break;
     } 
 
     
     return lib;
   }
 
   
   public static String getLibelleFrequence(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Quinzaine";
         break;
       case 2:
         libelle = "Mensuelle";
         break;
       case 3:
         libelle = "Trimestrielle";
         break;
       case 4:
         libelle = "Semestrielle";
         break;
       case 5:
         libelle = "Annuelle";
         break;
       case 6:
         libelle = "Fixe";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getFrequence(Frequence freq) {
     int r = 0;
     
     switch (freq) {
       case quinzaine:
         r = 1;
         break;
       case mensuelle:
         r = 2;
         break;
       case trimestrielle:
         r = 3;
         break;
       case semestrielle:
         r = 4;
         break;
       case annuelle:
         r = 5;
         break;
       case fixe:
         r = 6;
         break;
     } 
     
     return r;
   }
 
 
   
   public enum TypeCotisation
   {
     impot, cotisation, assuranceMaladie;
   }
   
   public static TypeCotisation getTypeCotisation(int r) {
     TypeCotisation type = null;
     switch (r) {
       case 1:
         type = TypeCotisation.impot;
         break;
       case 2:
         type = TypeCotisation.cotisation;
         break;
       case 3:
         type = TypeCotisation.assuranceMaladie;
         break;
     } 
 
     
     return type;
   }
   
   public static String getLibelleTypeCotisation(TypeCotisation type) {
     String lib = "";
     
     switch (type) {
       case impot:
         lib = "Impét sur salaire";
         break;
       case cotisation:
         lib = "Cotisation sociale";
         break;
       case assuranceMaladie:
         lib = "Assurance maladie";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleTypeCotisation(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Impot sur salaire";
         break;
       case 2:
         libelle = "Cotisation sociale";
         break;
       case 3:
         libelle = "Assurance Maladie";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getTypeCotisation(TypeCotisation type) {
     int r = 0;
     
     switch (type) {
       case impot:
         r = 1;
         break;
       case cotisation:
         r = 2;
         break;
       case assuranceMaladie:
         r = 3;
         break;
     } 
     
     return r;
   }
 
 
   
   public enum TypeSpecial
   {
     deplacement, logement, autre;
   }
   
   public static TypeSpecial getTypeSpecial(int r) {
     TypeSpecial type = null;
     switch (r) {
       case 1:
         type = TypeSpecial.deplacement;
         break;
       case 2:
         type = TypeSpecial.logement;
         break;
       case 3:
         type = TypeSpecial.autre;
         break;
     } 
     
     return type;
   }
   
   public static String getLibelleTypeSpecial(TypeSpecial type) {
     String lib = "";
     
     switch (type) {
       case deplacement:
         lib = "Prime/indemnité de déplacement";
         break;
       case logement:
         lib = "Prime/indemnité de logement";
         break;
       case autre:
         lib = "Autres primes";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleTypePrimeSpecial(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Prime de déplacement";
         break;
       case 2:
         libelle = "Prime de logement";
         break;
       case 3:
         libelle = "Autre prime";
         break;
     } 
 
     
     return libelle;
   }
 
   
   public static String getLibelleTypeIndemniteSpecial(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Indemnité de déplacement";
         break;
       case 2:
         libelle = "Indemnité de logement";
         break;
       case 3:
         libelle = "Autre indemnité";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getTypeSpecial(TypeSpecial type) {
     int r = 0;
     
     switch (type) {
       case deplacement:
         r = 1;
         break;
       case logement:
         r = 2;
         break;
       case autre:
         r = 3;
         break;
     } 
     
     return r;
   }
 
 
   
   public enum JoursConge
   {
     ouvrable, plusWeekEnd;
   }
   
   public static JoursConge getJoursConge(int r) {
     JoursConge type = null;
     switch (r) {
       case 1:
         type = JoursConge.ouvrable;
         break;
       case 2:
         type = JoursConge.plusWeekEnd;
         break;
     } 
 
     
     return type;
   }
   
   public static String getLibelleJoursConge(JoursConge type) {
     String lib = "";
     
     switch (type) {
       case ouvrable:
         lib = "ouvrables";
         break;
       case plusWeekEnd:
         lib = "ouvrables plus les week end";
         break;
     } 
 
     
     return lib;
   }
 
   
   public static String getLibelleJoursConge(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Ouvrables";
         break;
       case 2:
         libelle = "Ouvrables plus les week end";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getJoursConge(JoursConge type) {
     int r = 0;
     
     switch (type) {
       case ouvrable:
         r = 1;
         break;
       case plusWeekEnd:
         r = 2;
         break;
     } 
     
     return r;
   }
   
   public enum StatutPersonneACharge
   {
     Conjoint, EnfantLegitime, EnfantNaturelReconnu, EnfantOrphelinSousTutelle, EnfantInaptePhysiquement;
   }
   
   public static int getStatutPersonneCharge(StatutPersonneACharge p) {
     int idPersonne = 0;
     switch (p) {
       case Conjoint:
         idPersonne = 1;
         break;
       case EnfantLegitime:
         idPersonne = 2;
         break;
       case EnfantNaturelReconnu:
         idPersonne = 3;
         break;
       case EnfantOrphelinSousTutelle:
         idPersonne = 4;
         break;
       case EnfantInaptePhysiquement:
         idPersonne = 5;
         break;
     } 
 
     
     return idPersonne;
   }
   
   public static String getLibelleStatutPersonneACharge(int statut) {
     String lib = "";
     
     switch (statut) {
       case 1:
         lib = "Conjoint";
         break;
       case 2:
         lib = "Enfant Légitime";
         break;
       case 3:
         lib = "Enfant Naturel Reconnu";
         break;
       case 4:
         lib = "Enfant Orphelin Sous Tutelle";
         break;
       case 5:
         lib = "Enfant Inapte Physiquement";
         break;
     } 
 
     
     return lib;
   }
 
 
   
   public enum TypeHeurePreste
   {
     Normal, HeureSupplementaire;
   }
   
   public static int getTypeHeurePreste(TypeHeurePreste t) {
     int idType = 0;
     switch (t) {
       case Normal:
         idType = 1;
         break;
       case HeureSupplementaire:
         idType = 2;
         break;
     } 
     
     return idType;
   }
   
   public static String getLibelleTypeHeurePreste(int type) {
     String lib = "";
     
     switch (type) {
       case 1:
         lib = "Heures Normales";
         break;
       case 2:
         lib = "Heures Supplementaires";
         break;
     } 
 
     
     return lib;
   }
 
   
   public enum TypeAvancement
   {
     salaireEntree, avancementTraitement, avancementGrade, changementPosition, sanctionDisciplinaire, finCarriereAv,finCarriereAp;
   }
   
   public static TypeAvancement getTypeAvancement(int r) {
     TypeAvancement type = null;
     switch (r) {
       case 0:
         type = TypeAvancement.salaireEntree;
         break;
       case 1:
         type = TypeAvancement.avancementTraitement;
         break;
       case 2:
         type = TypeAvancement.avancementGrade;
         break;
       case 3:
         type = TypeAvancement.changementPosition;
         break;
       case 4:
         type = TypeAvancement.sanctionDisciplinaire;
         break;
       case 5:
         type = TypeAvancement.finCarriereAv;
         break;
				case 6:
					 type = TypeAvancement.finCarriereAp;
					break;
     } 
     
     return type;
   }
   
   public static String getLibelleTypeAvancement(TypeAvancement type) {
     String lib = "";
     
     switch (type) {
       case avancementTraitement:
         lib = "avancement de traitement";
         break;
       case avancementGrade:
         lib = "avancement de Grade";
         break;
       case changementPosition:
         lib = "changement position";
         break;
       case sanctionDisciplinaire:
         lib = "sanction disciplinaire";
         break;
       case finCarriereAv:
         lib = "Fin carriére";
         break;
       case salaireEntree:
         lib = "Salaire l'entrée";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleTypeAvancement(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 0:
         libelle = "Salaire l'entrée";
         break;
       case 1:
         libelle = "avancement de traitement";
         break;
       case 2:
         libelle = "avancement de Grade";
         break;
       case 3:
         libelle = "changement de position";
         break;
       case 4:
         libelle = "sanction disciplinaire";
         break;
       case 5:
         libelle = "Fin carriére";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getTypeAvancement(TypeAvancement type) {
     int r = 0;
     
     switch (type) {
       case salaireEntree:
         r = 0;
         break;
       case avancementTraitement:
         r = 1;
         break;
       case avancementGrade:
         r = 2;
         break;
       case changementPosition:
         r = 3;
         break;
       case sanctionDisciplinaire:
         r = 4;
         break;
       case finCarriereAv:
         r = 5;
         break;
				 case finCarriereAp:
         r = 6;
         break;
     } 
     return r;
   }
 
   
   public enum EtatDemandeAvancementGrade
   {
     demandeEnAttenete, demandeValide, demandeRefuse;
   }
   
   public static EtatDemandeAvancementGrade getEtatDemandeAvancementGrade(int r) {
     EtatDemandeAvancementGrade type = null;
     switch (r) {
       case 1:
         type = EtatDemandeAvancementGrade.demandeEnAttenete;
         break;
       case 2:
         type = EtatDemandeAvancementGrade.demandeValide;
         break;
       case 3:
         type = EtatDemandeAvancementGrade.demandeRefuse;
         break;
     } 
 
     
     return type;
   }
 
   
   public static String getLibelleEtatDemandeAvancementGrade(EtatDemandeAvancementGrade type) {
     String lib = "";
     
     switch (type) {
       case demandeEnAttenete:
         lib = "Demande non encore traitée";
         break;
       case demandeValide:
         lib = "Demande validée";
         break;
       case demandeRefuse:
         lib = "Demande refusée";
         break;
     } 
 
     
     return lib;
   }
 
   
   public static String getLibelleEtatDemandeAvancementGrade(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "demande non encore traité";
         break;
       case 2:
         libelle = "demande traité";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getEtatDemandeAvancementGrade(EtatDemandeAvancementGrade type) {
     int r = 0;
     
     switch (type) {
       case demandeEnAttenete:
         r = 1;
         break;
       case demandeValide:
         r = 2;
         break;
       case demandeRefuse:
         r = 3;
         break;
     } 
     return r;
   }
   
   public enum EtatPosition
   {
     demandePosition, decide,reintegre;
   }
   
   public static EtatPosition getEtatPosition(int r) {
     EtatPosition posi = null;
     switch (r) {
       case 1:
         posi = EtatPosition.demandePosition;
         break;
       case 2:
         posi = EtatPosition.decide;
         break;
       case 3:
	     posi = EtatPosition.reintegre;
	     break;
        
     } 
     
     return posi;
   }
   
   public static String getLibelleEtatPosition(EtatPosition posi) {
     String lib = "";
     
     switch (posi) {
       case demandePosition:
         lib = "demande position";
         break;
       case decide:
         lib = "Décidé";
         break;
				case reintegre:
         lib = "Réintégré";
         break;
				 default:
				   break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleEtatPosition(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Demande position";
         break;
       case 2:
         libelle = "accà¨s dans une position";
         break;
       case 3:
         libelle = "demande position refusée";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getEtatPosition(EtatPosition position) {
     int r = 0;
     
     switch (position) {
       case demandePosition:
         r = 1;
         break;
       case decide:
         r = 2;
         break;
				 default:
					break;     
	} 
     return r;
   }
 
   
   public enum Position
   {
     activiteDeService, detachement, suspensionActiviteDeService, disponibilite, suspensionFonctionParMesureDOrdre;
   }
   
   public static Position getPosition(int r) {
     Position posi = null;
     switch (r) {
       case 1:
         posi = Position.activiteDeService;
         break;
       case 2:
         posi = Position.detachement;
         break;
       case 3:
         posi = Position.suspensionActiviteDeService;
         break;
       case 4:
         posi = Position.disponibilite;
         break;
       case 5:
         posi = Position.suspensionFonctionParMesureDOrdre;
         break;
     } 
     
     return posi;
   }
   
   public static String getLibellePosition(Position posi) {
     String lib = "";
     
     switch (posi) {
       case activiteDeService:
         lib = "L'activité de service";
         break;
       case detachement:
         lib = "Le détachement";
         break;
       case suspensionActiviteDeService:
         lib = "La suspension d'activités de service";
         break;
       case disponibilite:
         lib = "La disponibilité";
         break;
       case suspensionFonctionParMesureDOrdre:
         lib = "La suspension de fonction par mesure d'ordre";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibellePosition(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "L'activité de service";
         break;
       case 2:
         libelle = "Le détachement";
         break;
       case 3:
         libelle = "La suspension d'activités de service";
         break;
       case 4:
         libelle = "La disponibilité";
         break;
       case 5:
         libelle = "La suspension de fonction par mesure d'ordre";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getPosition(Position position) {
     int r = 0;
     
     switch (position) {
       case activiteDeService:
         r = 1;
         break;
       case detachement:
         r = 2;
         break;
       case suspensionActiviteDeService:
         r = 3;
         break;
       case disponibilite:
         r = 4;
         break;
       case suspensionFonctionParMesureDOrdre:
         r = 5;
         break;
     } 
     return r;
   }
 
   
   public enum SanctionsDisciplinaires
   {
     avertissement, blame, retenueMoitieTraitementCinqJoursMin, retenueMoitieTraitementUnMois, disponibiliteDisciplinaire, revocation;
   }
   
   public static SanctionsDisciplinaires getSanctionsDisciplinaires(int r) {
     SanctionsDisciplinaires sanction = null;
     switch (r) {
       case 1:
         sanction = SanctionsDisciplinaires.avertissement;
         break;
       case 2:
         sanction = SanctionsDisciplinaires.blame;
         break;
       case 3:
         sanction = SanctionsDisciplinaires.retenueMoitieTraitementCinqJoursMin;
         break;
       case 4:
         sanction = SanctionsDisciplinaires.retenueMoitieTraitementUnMois;
         break;
       case 5:
         sanction = SanctionsDisciplinaires.disponibiliteDisciplinaire;
         break;
       case 6:
         sanction = SanctionsDisciplinaires.revocation;
         break;
     } 
     
     return sanction;
   }
   
   public static String getLibelleSanctionsDisciplinaires(SanctionsDisciplinaires sanction) {
     String lib = "";
     
     switch (sanction) {
       case avertissement:
         lib = "L'avertissemnt";
         break;
       case blame:
         lib = "Le blame";
         break;
       case retenueMoitieTraitementCinqJoursMin:
         lib = "La retenue de la moitié de traitement pendant cinq jours au minimum et quinze jours au maximum";
         break;
       case retenueMoitieTraitementUnMois:
         lib = "La retenue de la moitié de traitement pendant un mois";
         break;
       case disponibiliteDisciplinaire:
         lib = "La disponibité disciplinaire pour une durée de 1 2 mois";
         break;
       case revocation:
         lib = "La révocation";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleSanctionsDisciplinaires(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "L'avertissemnt";
         break;
       case 2:
         libelle = "Le blame";
         break;
       case 3:
         libelle = "La retenue de la moitié de traitement pendant cinq jours au minimum et quinze jours au maximum";
         break;
       case 4:
         libelle = "La retenue de la moitié de traitement pendant un mois";
         break;
       case 5:
         libelle = "La disponibité disciplinaire pour une durée de 1 2 mois";
         break;
       case 6:
         libelle = "La révocation";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getSanctionsDisciplinaires(SanctionsDisciplinaires sanction) {
     int r = 0;
     
     switch (sanction) {
       case avertissement:
         r = 1;
         break;
       case blame:
         r = 2;
         break;
       case retenueMoitieTraitementCinqJoursMin:
         r = 3;
         break;
       case retenueMoitieTraitementUnMois:
         r = 4;
         break;
       case disponibiliteDisciplinaire:
         r = 5;
         break;
       case revocation:
         r = 6;
         break;
     } 
     return r;
   }
   
   public enum EtatSanction
   {
     saisie, recours, decision;
   }
   
   public static EtatSanction getEtatSanction(int r) {
     EtatSanction sanction = null;
     switch (r) {
       case 1:
         sanction = EtatSanction.saisie;
         break;
       case 2:
         sanction = EtatSanction.recours;
         break;
       case 3:
         sanction = EtatSanction.decision;
         break;
       
     } 
 
     
     return sanction;
   }
   
   public static String getLibelleEtatSanction(EtatSanction sanction) {
     String lib = "";
     
     switch (sanction) {
       case saisie :
         lib = "Saisie";
         break;
       case recours:
         lib = "Recours sur sanction";
         break;
       case decision:
         lib = "Décision";
         break;
      
     } 
 
     
     return lib;
   }
 
   
   public static String getLibelleEtatSanction(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Sanction accordée";
         break;
       case 2:
         libelle = "Recours sur sanction";
         break;
       case 3:
         libelle = "Recours accepté";
         break;
       case 4:
         libelle = "Recours rejeté";
         break;
     } 
 
 
     
     return libelle;
   }
   
   public static int getEtatSanction(EtatSanction sanction) {
     int r = 0;
     
     switch (sanction) {
       case saisie:
         r = 1;
         break;
       case recours:
         r = 2;
         break;
       case decision:
         r = 3;
         break;
       
     } 
     
     return r;
   }
   
   public enum EtatDemandeProlongationRetraite
   {
     demandeProlongation, decide;
   }
   
   public static EtatDemandeProlongationRetraite getEtatDemandeProlongationRetraite(int r) {
     EtatDemandeProlongationRetraite dema = null;
     switch (r) {
       case 1:
         dema = EtatDemandeProlongationRetraite.demandeProlongation;
         break;
       case 2:
         dema = EtatDemandeProlongationRetraite.decide;
         break;
      
     } 
     
     return dema;
   }
   
   public static String getLibelleEtatDemandeProlongationRetraite(EtatDemandeProlongationRetraite dema) {
     String lib = "";
     
     switch (dema) {
       case demandeProlongation:
         lib = "Demande prolongation age retraite";
         break;
       case decide:
         lib = "Prolongation age retraite accordée";
         break;
      
     } 
     
     return lib;
   }
 
   
   public static String getLibelleEtatDemandeProlongationRetraite(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 0:
         libelle = "Demande prolongation age retraite";
         break;
       case 1:
         libelle = "Prolongation age retraite accordée";
         break;
       case 2:
         libelle = "Prolongation age retraite refusée";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getEtatDemandeProlongationRetraite(EtatDemandeProlongationRetraite demande) {
     int r = 0;
     
     switch (demande) {
       case demandeProlongation:
         r = 1;
         break;
       case decide:
         r = 2;
         break;
       
     } 
     return r;
   }
 
   
   public enum EtatDemandeRetraiteAnticipe
   {
     demandeRetraiteAnticipe, traitementRetraite;
   }
   
   public static EtatDemandeRetraiteAnticipe getEtatDemandeRetraiteAnticipe(int r) {
     EtatDemandeRetraiteAnticipe dema = null;
     switch (r) {
       case 1:
         dema = EtatDemandeRetraiteAnticipe.demandeRetraiteAnticipe;
         break;
       case 2:
         dema = EtatDemandeRetraiteAnticipe.traitementRetraite;
         break;
     } 
 
     
     return dema;
   }
   
   public static String getLibelleEtatDemandeRetraiteAnticipe(EtatDemandeRetraiteAnticipe dema) {
     String lib = "";
     
     switch (dema) {
       case demandeRetraiteAnticipe:
         lib = "Demande retraite anricipée";
         break;
       case traitementRetraite:
         lib = "Traitement retraite anricipée ";
         break;
     } 
 
     
     return lib;
   }
 
   
   public static String getLibelleEtatDemandeRetraiteAnticipe(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Demande retraite anticipée";
         break;
       case 2:
         libelle = "Retraite anticipée accordée";
         break;
       case 3:
         libelle = "Retraite anticipée refusée";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getEtatDemandeRetraiteAnticipe(EtatDemandeRetraiteAnticipe demande) {
     int r = 0;
     
     switch (demande) {
       case demandeRetraiteAnticipe:
         r = 1;
         break;
       case traitementRetraite:
         r = 2;
         break;
     } 
     
     return r;
   }
 
   
   public enum EtatDemandeConge
   {
     demandeConge, accord, refusConge, finConge;
   }
   
   public static EtatDemandeConge getEtatDemandeConge(int r) {
     EtatDemandeConge dema = null;
     switch (r) {
       case 1:
         dema = EtatDemandeConge.demandeConge;
         break;
       case 2:
         dema = EtatDemandeConge.accord;
         break;
       case 3:
         dema = EtatDemandeConge.refusConge;
         break;
       case 4:
         dema = EtatDemandeConge.finConge;
         break;
     } 
     
     return dema;
   }
   
   public static String getLibelleEtatDemandeConge(EtatDemandeConge dema) {
     String lib = "";
     
     switch (dema) {
       case demandeConge:
         lib = "Demande Congé";
         break;
       case accord:
         lib = "Accord Congé";
         break;
       case refusConge:
         lib = "Refus Congé";
         break;
       case finConge:
         lib = "Fin Congé";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleEtatDemandeConge(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Demande Congé";
         break;
       case 2:
         libelle = "Accord Congé";
         break;
       case 3:
         libelle = "Refus Congé";
         break;
       case 4:
         libelle = "Fin Congé";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getEtatDemandeConge(EtatDemandeConge demande) {
     int r = 0;
     
     switch (demande) {
       case demandeConge:
         r = 1;
         break;
       case accord:
         r = 2;
         break;
       case refusConge:
         r = 3;
         break;

		default:
			break;     
			} 
 
     
     return r;
   }
   
   public enum EtatDemandePlanningConge
   {
     demandePlanningConge, accordPlanningConge, refusPlanningConge;
   }
   
   public static EtatDemandePlanningConge getEtatDemandePlanningConge(int r) {
     EtatDemandePlanningConge dema = null;
     switch (r) {
       case 1:
         dema = EtatDemandePlanningConge.demandePlanningConge;
         break;
       case 2:
         dema = EtatDemandePlanningConge.accordPlanningConge;
         break;
       case 3:
         dema = EtatDemandePlanningConge.refusPlanningConge;
         break;
     } 
     
     return dema;
   }
   
   public static String getLibelleEtatDemandePlanningConge(EtatDemandePlanningConge dema) {
     String lib = "";
     
     switch (dema) {
       case demandePlanningConge:
         lib = "Demande Planning Congé";
         break;
       case accordPlanningConge:
         lib = "Accord Planning Congé";
         break;
       case refusPlanningConge:
         lib = "Refus Planning Congé";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleEtatDemandePlanningConge(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Demande Planning Congé";
         break;
       case 2:
         libelle = "Accord Planning Congé";
         break;
       case 3:
         libelle = "Refus Planning Congé";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getEtatDemandePlanningConge(EtatDemandePlanningConge demande) {
     int r = 0;
     
     switch (demande) {
       case demandePlanningConge:
         r = 1;
         break;
       case accordPlanningConge:
         r = 2;
         break;
       case refusPlanningConge:
         r = 3;
         break;
     } 
     return r;
   }
   
   public enum ModeReglement
   {
     Espece, cheque, Virement, Mandat;
   }
   
   public static int getModeRegelement(ModeReglement m) {
     int idMode = 0;
     switch (m) {
       
       case Espece:
         idMode = 0;
         break;
       case cheque:
         idMode = 1;
         break;
       case Virement:
         idMode = 2;
         break;
       case Mandat:
         idMode = 3;
         break;
     } 
     
     return idMode;
   }
   
   public static String getLibelleModeReglement(int mode) {
     String lib = "";
     
     switch (mode) {
       case 0:
         lib = "Espéce";
         break;
       case 1:
         lib = "chéque";
         break;
       case 2:
         lib = "Virement";
         break;
       case 3:
         lib = "Mandat";
         break;
     } 
     
     return lib;
   }
 
   
   public enum TypeDocument
   {
     bulletinPaie, ordreVirement, journalPaie, demandeConge, ficheCotation, declarationIRE;
   }
   
   public static int getTypeDocument(TypeDocument typeDoc) {
     int type = 0;
     switch (typeDoc) {
       case bulletinPaie:
         type = 1;
         break;
       case ordreVirement:
         type = 2;
         break;
       case journalPaie:
         type = 3;
         break;
       case demandeConge:
         type = 4;
         break;
       case ficheCotation:
         type = 5;
         break;
       case declarationIRE:
         type = 6;
         break;
     } 
 
     
     return type;
   }
   
   public static TypeDocument getTypeDocument(int typeDoc) {
     TypeDocument type = null;
     switch (typeDoc) {
       case 1:
         type = TypeDocument.bulletinPaie;
         break;
       case 2:
         type = TypeDocument.ordreVirement;
         break;
       case 3:
         type = TypeDocument.journalPaie;
         break;
       case 4:
         type = TypeDocument.demandeConge;
         break;
       case 5:
         type = TypeDocument.ficheCotation;
         break;
       case 6:
         type = TypeDocument.declarationIRE;
         break;
     } 
 
     
     return type;
   }
   
   public static String getLibelleTypeDocument(TypeDocument type) {
     String libelle = "";
     switch (type) {
       case bulletinPaie:
         libelle = "Bulletin de paie";
         break;
       case ordreVirement:
         libelle = "Ordre de virement";
         break;
       case journalPaie:
         libelle = "Journal de paie";
         break;
       case demandeConge:
         libelle = "Demande de congé";
         break;
       case ficheCotation:
         libelle = "Fiche de cotation";
         break;
       case declarationIRE:
         libelle = "Déclaration IRE";
         break;
     } 
 
     
     return libelle;
   }
   
   public enum EtatDemandeSortie
   {
     demandeSortie, traite;
   }
   
   public static EtatDemandeSortie getEtatDemandeSortie(int r) {
     EtatDemandeSortie dema = null;
     switch (r) {
       case 1:
         dema = EtatDemandeSortie.demandeSortie;
         break;
       case 2:
         dema = EtatDemandeSortie.traite;
         break;
      
     } 
     
     return dema;
   }
   
   public static String getLibelleEtatDemandeSortie(EtatDemandeSortie dema) {
     String lib = "";
     
     switch (dema) {
       case demandeSortie:
         lib = "Demande Sortie";
         break;
       case traite:
         lib = "Traité";
         break;
      
     } 
     
     return lib;
   }
 
   
   public static String getLibelleEtatDemandeSortie(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Demande Sortie";
         break;
       case 2:
         libelle = "Traité";
         break;
      
     } 
 
     
     return libelle;
   }
   
   public static int getEtatDemandeSortie(EtatDemandeSortie demande) {
     int r = 0;
     
     switch (demande) {
       case demandeSortie:
         r = 1;
         break;
       case traite:
         r = 2;
         break;
    
     } 
     return r;
   }
 
   
   public enum TypeOperationDecideur
   {
     demandePosition, saisiePosition, premiereEvaluation, deuxiemeEvaluation, troisiemeEvaluation,
     demandeAvancement, changementGrade, saisieSanction, recoursSanction, decisionSanction,
     saisieDemandePlanning, planningConge, saisieDemandeConge, validationConge, saisieSortie, validationSortie,
     saisieAbsence, demandeRetraiteAnticipe, validationRetraiteAnticipe, demandeProlongationRetraite,
     validationProlongationRetraite, finCarriere, validationRecours;
   }
   
   public static TypeOperationDecideur getTypeOperationDecideur(int r) {
     TypeOperationDecideur type = null;
     switch (r) {
       case 1:
         type = TypeOperationDecideur.demandePosition;
         break;
       case 2:
         type = TypeOperationDecideur.saisiePosition;
         break;
       case 3:
         type = TypeOperationDecideur.premiereEvaluation;
         break;
       case 4:
         type = TypeOperationDecideur.deuxiemeEvaluation;
         break;
       case 5:
         type = TypeOperationDecideur.troisiemeEvaluation;
         break;
       case 6:
         type = TypeOperationDecideur.demandeAvancement;
         break;
       case 7:
         type = TypeOperationDecideur.changementGrade;
         break;
       case 8:
         type = TypeOperationDecideur.saisieSanction;
         break;
       case 9:
         type = TypeOperationDecideur.recoursSanction;
         break;
       case 10:
         type = TypeOperationDecideur.decisionSanction;
         break;
       case 11:
         type = TypeOperationDecideur.saisieDemandePlanning;
         break;
       case 12:
         type = TypeOperationDecideur.planningConge;
         break;
       case 13:
         type = TypeOperationDecideur.saisieDemandeConge;
         break;
       case 14:
         type = TypeOperationDecideur.validationConge;
         break;
       case 15:
         type = TypeOperationDecideur.saisieSortie;
         break;
       case 16:
         type = TypeOperationDecideur.validationSortie;
         break;
       case 17:
         type = TypeOperationDecideur.saisieAbsence;
         break;
       case 18:
         type = TypeOperationDecideur.demandeRetraiteAnticipe;
         break;
       case 19:
         type = TypeOperationDecideur.validationRetraiteAnticipe;
         break;
       case 20:
         type = TypeOperationDecideur.demandeProlongationRetraite;
         break;
       case 21:
         type = TypeOperationDecideur.validationProlongationRetraite;
         break;
       case 22:
         type = TypeOperationDecideur.finCarriere;
         break;
       case 23:
         type = TypeOperationDecideur.validationRecours;
         break;
     } 
     
     return type;
   }
   
   public static String getLibelleTypeOperationDecideur(TypeOperationDecideur type) {
     String lib = "";
     
     switch (type) {
       case demandePosition:
         lib = "Demande changement de position";
         break;
       case saisiePosition:
         lib = "Validation changement de position";
         break;
       case premiereEvaluation:
         lib = "Premiérevaluation";
         break;
       case deuxiemeEvaluation:
         lib = "Deuxiémevaluation";
         break;
       case troisiemeEvaluation:
         lib = "Troisiémevaluation";
         break;
       case demandeAvancement:
         lib = "Demande avancement Grade";
         break;
       case changementGrade:
         lib = "Validation avancement Grade";
         break;
       case saisieSanction:
         lib = "Saisie des sanctions";
         break;
       case recoursSanction:
         lib = "Recours sur sanction";
         break;
       case decisionSanction:
         lib = "Décision sur les recours des sanctions";
         break;
       case saisieDemandePlanning:
         lib = "Saisie de la planification des congés";
         break;
       case planningConge:
         lib = "Validation de la planification des Congés";
         break;
       case saisieDemandeConge:
         lib = "Saisie de la demande de congé";
         break;
       case validationConge:
         lib = "Validation de la demande de Congé";
         break;
       case saisieSortie:
         lib = "Saisie des Sorties";
         break;
       case validationSortie:
         lib = "Validation des Sorties";
         break;
       case saisieAbsence:
         lib = "Saisie des Absences";
         break;
       case demandeRetraiteAnticipe:
         lib = "Demande de la Retraite Anticipée";
         break;
       case validationRetraiteAnticipe:
         lib = "Validation de la Retraite Anticipée";
         break;
       case demandeProlongationRetraite:
         lib = "Demande de la Prolongation de la Retraite";
         break;
       case validationProlongationRetraite:
         lib = "Validation de la Prolongation de la Retraite";
         break;
       case finCarriere:
         lib = "Gestion de fin de la carriére";
         break;
       case validationRecours:
         lib = "Validation recours";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleTypeOperationDecideur(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Demande changement de position";
         break;
       case 2:
         libelle = "Validation changement position";
         break;
       case 3:
         libelle = "Premiére Evaluation";
         break;
       case 4:
         libelle = "Deuxiéme Evaluation";
         break;
       case 5:
         libelle = "Troisiéme Evaluation";
         break;
       case 6:
         libelle = "Demande Avancement Grade";
         break;
       case 7:
         libelle = "Validation avancement Grade";
         break;
       case 8:
         libelle = "Saisie Sanction";
         break;
       case 9:
         libelle = "Recours sur Sanction";
         break;
       case 10:
         libelle = "Decision Recours sur Sanction";
         break;
       case 11:
         libelle = "Saisie de la planification des congés";
         break;
       case 12:
         libelle = "Validation de la planification des Congés";
         break;
       case 13:
         libelle = "Saisie de la demande de congé";
         break;
       case 14:
         libelle = "Validation de la demande de Congé";
         break;
       case 15:
         libelle = "Saisie des Sorties";
         break;
       case 16:
         libelle = "Validation des Sorties";
         break;
       case 17:
         libelle = "Saisie des Absences";
         break;
       case 18:
         libelle = "Demande de la Retraite Anticipée";
         break;
       case 19:
         libelle = "Validation de la Retraite Anticipée";
         break;
       case 20:
         libelle = "Demande de la Prolongation de la Retraite";
         break;
       case 21:
         libelle = "Validation de la Prolongation de la Retraite";
         break;
       case 22:
         libelle = "Gestion de fin de la carriére";
         break;
     } 
     
     return libelle;
   }
   
   public static int getTypeOperationDecideur(TypeOperationDecideur type) {
     int r = 0;
     
     switch (type) {
       case demandePosition:
         r = 1;
         break;
       case saisiePosition:
         r = 2;
         break;
       case premiereEvaluation:
         r = 3;
         break;
       case deuxiemeEvaluation:
         r = 4;
         break;
       case troisiemeEvaluation:
         r = 5;
         break;
       case demandeAvancement:
         r = 6;
         break;
       case changementGrade:
         r = 7;
         break;
       case saisieSanction:
         r = 8;
         break;
       case recoursSanction:
         r = 9;
         break;
       case decisionSanction:
         r = 10;
         break;
       case saisieDemandePlanning:
         r = 11;
         break;
       case planningConge:
         r = 12;
         break;
       case saisieDemandeConge:
         r = 13;
         break;
       case validationConge:
         r = 13;
         break;
       case saisieSortie:
         r = 14;
         break;
       case validationSortie:
         r = 15;
         break;
       case saisieAbsence:
         r = 16;
         break;
       case demandeRetraiteAnticipe:
         r = 17;
         break;
       case validationRetraiteAnticipe:
         r = 18;
         break;
       case demandeProlongationRetraite:
         r = 19;
         break;
       case validationProlongationRetraite:
         r = 20;
         break;
       case finCarriere:
         r = 21;
         break;
     } 
 
     
     return r;
   }
   
   public enum MotifFinCarriere
   {
     licenciementEchecStage, demissionDOfficePourNonRepriseFonctionApresSuspension,
     demissionDOfficePourNonRepriseFonctionApresMiseEnDisponibilite,
     demissionDOfficePourFausseDeclarationOuManoeuvresFrauduleuse, demissionEcrite, revocation,
     inaptitudePhysique, inaptitudeProfessionnelle, miseEnRetraite, deces;
   }
   
   public static MotifFinCarriere getMotifFinCarriere(int r) {
     MotifFinCarriere motif = null;
     switch (r) {
       case 1:
         motif = MotifFinCarriere.licenciementEchecStage;
         break;
       case 2:
         motif = MotifFinCarriere.demissionDOfficePourNonRepriseFonctionApresSuspension;
         break;
       case 3:
         motif = MotifFinCarriere.demissionDOfficePourNonRepriseFonctionApresMiseEnDisponibilite;
         break;
       case 4:
         motif = MotifFinCarriere.demissionDOfficePourFausseDeclarationOuManoeuvresFrauduleuse;
         break;
       case 5:
         motif = MotifFinCarriere.demissionEcrite;
         break;
       case 6:
         motif = MotifFinCarriere.revocation;
         break;
       case 7:
         motif = MotifFinCarriere.inaptitudePhysique;
         break;
       case 8:
         motif = MotifFinCarriere.inaptitudeProfessionnelle;
         break;
       case 9:
         motif = MotifFinCarriere.miseEnRetraite;
         break;
       case 10:
         motif = MotifFinCarriere.deces;
         break;
     } 
     
     return motif;
   }
   
   public static String getLibelleMotifFinCarriere(MotifFinCarriere motif) {
     String lib = "";
     
     switch (motif) {
       case licenciementEchecStage:
         lib = "Licenciement pourchec de stage";
         break;
       case demissionDOfficePourNonRepriseFonctionApresSuspension:
         lib = "Démission d'office pour la non reprise de fonction aprés suspension d'activité de service";
         break;
       case demissionDOfficePourNonRepriseFonctionApresMiseEnDisponibilite:
         lib = "Démission d'office pour la non reprise de fonction aprés mise en disponibilité";
         break;
       case demissionDOfficePourFausseDeclarationOuManoeuvresFrauduleuse:
         lib = "Démission d'office pour fausses déclarations ou manoeuvres frauduleuses lors de l'engagement";
         break;
       case demissionEcrite:
         lib = "Démissioncrite";
         break;
       case revocation:
         lib = "Révocation";
         break;
       case inaptitudePhysique:
         lib = "Inaptitude physique ";
         break;
       case inaptitudeProfessionnelle:
         lib = "Inaptitude professionnelle";
         break;
       case miseEnRetraite:
         lib = "Mise en retraite";
         break;
       case deces:
         lib = "Décà¨s";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleMotifFinCarriere(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Licenciement pourchec de stage";
         break;
       case 2:
         libelle = "Démission d'office pour la non reprise de fonction aprés suspension d'activité de service";
         break;
       case 3:
         libelle = "Démission d'office pour la non reprise de fonction aprés mise en disponibilité";
         break;
       case 4:
         libelle = "Démission d'office pour fausses déclarations ou manoeuvres frauduleuses lors de l'engagement";
         break;
       case 5:
         libelle = "Démissioncrite";
         break;
       case 6:
         libelle = "Révocation";
         break;
       case 7:
         libelle = "Inaptitude physique";
         break;
       case 8:
         libelle = "Inaptitude professionnelle";
         break;
       case 9:
         libelle = "Mise en retraite";
         break;
       case 10:
         libelle = "Décà¨s";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getMotifFinCarriere(MotifFinCarriere motif) {
     int r = 0;
     
     switch (motif) {
       case licenciementEchecStage:
         r = 1;
         break;
       case demissionDOfficePourNonRepriseFonctionApresSuspension:
         r = 2;
         break;
       case demissionDOfficePourNonRepriseFonctionApresMiseEnDisponibilite:
         r = 3;
         break;
       case demissionDOfficePourFausseDeclarationOuManoeuvresFrauduleuse:
         r = 4;
         break;
       case demissionEcrite:
         r = 5;
         break;
       case revocation:
         r = 6;
         break;
       case inaptitudePhysique:
         r = 7;
         break;
       case inaptitudeProfessionnelle:
         r = 8;
         break;
       case miseEnRetraite:
         r = 9;
         break;
       case deces:
         r = 10;
         break;
     } 
     return r;
   }
 
 
   
   public enum TypeAbsence
   {
     absence, retard;
   }
   
   public static TypeAbsence getTypeAbsence(int r) {
     TypeAbsence typeAbsence = null;
     switch (r) {
       case 1:
         typeAbsence = TypeAbsence.absence;
         break;
       case 2:
         typeAbsence = TypeAbsence.retard;
         break;
     } 
 
     
     return typeAbsence;
   }
   
   public static String getLibelleTypeAbsence(TypeAbsence type) {
     String lib = "";
     
     switch (type) {
       case absence:
         lib = "Absence au travail";
         break;
       case retard:
         lib = "Retard au travail";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleTypeAbsence(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Absence au travail";
         break;
       case 2:
         libelle = "Retard au travail";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getTypeAbsence(TypeAbsence type) {
     int r = 0;
     
     switch (type) {
       case absence:
         r = 1;
         break;
       case retard:
         r = 2;
         break;
     } 
     return r;
   }
 
   
   public enum SorteConge
   {
     congeReposAnnuel, congeCirconstance, congeMaternite, congeMedical, congeSabbatique;
   }
   
   public static SorteConge getSorteConge(int r) {
     SorteConge sorteConge = null;
     switch (r) {
       case 1:
         sorteConge = SorteConge.congeReposAnnuel;
         break;
       case 2:
         sorteConge = SorteConge.congeCirconstance;
         break;
       case 3:
         sorteConge = SorteConge.congeMaternite;
         break;
       case 4:
         sorteConge = SorteConge.congeMedical;
         break;
       case 5:
         sorteConge = SorteConge.congeSabbatique;
         break;
     } 
 
     
     return sorteConge;
   }
   
   public static String getLibelleSorteConge(SorteConge type) {
     String lib = "";
     
     switch (type) {
       case congeReposAnnuel:
         lib = "Congé de repos annuel";
         break;
       case congeCirconstance:
         lib = "Congé de circonstance";
         break;
       case congeMaternite:
         lib = "Congé de maternité";
         break;
       case congeMedical:
         lib = "Congé médical";
         break;
       case congeSabbatique:
         lib = "Congé sabbatique";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleSorteConge(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Congé de repos annuel";
         break;
       case 2:
         libelle = "Congé de circonstance";
         break;
       case 3:
         libelle = "Congé de maternité";
         break;
       case 4:
         libelle = "Congé médical";
         break;
       case 5:
         libelle = "Congé sabbatique";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getSorteConge(SorteConge type) {
     int r = 0;
     
     switch (type) {
       case congeReposAnnuel:
         r = 1;
         break;
       case congeCirconstance:
         r = 2;
         break;
       case congeMaternite:
         r = 3;
         break;
       case congeMedical:
         r = 4;
         break;
       case congeSabbatique:
         r = 5;
         break;
     } 
     return r;
   }
   
   public enum TypeContrat
   {
     contratEssai, contratDureeIndeterminee, contratDureeDeterminee, contratTempsPartiel,
     contratTravailIntermittent, contratTravailTemporaire, contratDureeDetermineeSenior,
     contratDureeDetermineeObjetDefini, contratApprentissage, contratProfessionnalisation,
     contratAccompagnementEmploi, contratInitiativeEmploi;
   }
   
   public static TypeContrat getTypeContrat(int r) {
     TypeContrat type = null;
     switch (r) {
       case 1:
         type = TypeContrat.contratEssai;
         break;
       case 2:
         type = TypeContrat.contratDureeIndeterminee;
         break;
       case 3:
         type = TypeContrat.contratDureeDeterminee;
         break;
       case 4:
         type = TypeContrat.contratTempsPartiel;
         break;
       case 5:
         type = TypeContrat.contratTravailIntermittent;
         break;
       case 6:
         type = TypeContrat.contratTravailTemporaire;
         break;
       case 7:
         type = TypeContrat.contratDureeDetermineeSenior;
         break;
       case 8:
         type = TypeContrat.contratDureeDetermineeObjetDefini;
         break;
       case 9:
         type = TypeContrat.contratApprentissage;
         break;
       case 10:
         type = TypeContrat.contratProfessionnalisation;
         break;
       case 11:
         type = TypeContrat.contratAccompagnementEmploi;
         break;
       case 12:
         type = TypeContrat.contratInitiativeEmploi;
         break;
     } 
     
     return type;
   }
   
   public static String getLibelleTypeContrat(TypeContrat type) {
     String lib = "";
     
     switch (type) {
       case contratEssai:
         lib = "Contrat d'Essai";
         break;
       case contratDureeIndeterminee:
         lib = "Contrat à  durée indéterminée";
         break;
       case contratDureeDeterminee:
         lib = "contrat à  durée déterminée";
         break;
       case contratTempsPartiel:
         lib = "Contrat à  temps partiel";
         break;
       case contratTravailIntermittent:
         lib = "Contrat de travail intermittent";
         break;
       case contratTravailTemporaire:
         lib = "Contrat de travail temporaire";
         break;
       case contratDureeDetermineeSenior:
         lib = "Contrat à  durée déterminée senior";
         break;
       case contratDureeDetermineeObjetDefini:
         lib = "Contrat à  durée déterminée objet défini";
         break;
       case contratApprentissage:
         lib = "Contrat d'apprentissage";
         break;
       case contratProfessionnalisation:
         lib = "Contrat de professionnalisation";
         break;
       case contratAccompagnementEmploi:
         lib = "Contrat d'accompagnement dans l'emploi";
         break;
       case contratInitiativeEmploi:
         lib = "Contrat initiative emploi";
         break;
     } 
     
     return lib;
   }
 
   
   public static String getLibelleTypeContrat(int ba) {
     String libelle = "";
     
     switch (ba) {
       case 1:
         libelle = "Contrat d'Essai";
         break;
       case 2:
         libelle = "Contrat à  durée indéterminée";
         break;
       case 3:
         libelle = "contrat à  durée déterminée";
         break;
       case 4:
         libelle = "Contrat à  temps partiel";
         break;
       case 5:
         libelle = "Contrat de travail intermittent";
         break;
       case 6:
         libelle = "Contrat de travail temporaire";
         break;
       case 7:
         libelle = "Contrat à  durée déterminée senior";
         break;
       case 8:
         libelle = "Contrat à  durée déterminée objet défini";
         break;
       case 9:
         libelle = "Contrat d'apprentissage";
         break;
       case 10:
         libelle = "Contrat de professionnalisation";
         break;
       case 11:
         libelle = "Contrat d'accompagnement dans l'emploi";
         break;
       case 12:
         libelle = "Contrat initiative emploi";
         break;
     } 
 
     
     return libelle;
   }
   
   public static int getTypeContrat(TypeContrat type) {
     int r = 0;
     
     switch (type) {
       
       case contratEssai:
         r = 1;
         break;
       case contratDureeIndeterminee:
         r = 2;
         break;
       case contratDureeDeterminee:
         r = 3;
         break;
       case contratTempsPartiel:
         r = 4;
         break;
       case contratTravailIntermittent:
         r = 5;
         break;
       case contratTravailTemporaire:
         r = 6;
         break;
       case contratDureeDetermineeSenior:
         r = 7;
         break;
       case contratDureeDetermineeObjetDefini:
         r = 8;
         break;
       case contratApprentissage:
         r = 9;
         break;
       case contratProfessionnalisation:
         r = 10;
         break;
       case contratAccompagnementEmploi:
         r = 11;
         break;
       case contratInitiativeEmploi:
         r = 12;
         break;
     } 
 
     
     return r;
   }
 }


