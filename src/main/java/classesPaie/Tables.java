package classesPaie;

import java.io.Serializable;

public class Tables implements Serializable {
	private static final long serialVersionUID = 3059993536607917864L;

	public enum TableName {
		province, commune, zone, quartier, devise, fonction, departement, pays, banque, typeCredit, typeContrat,
		categorieEmploye, sourceFinancementCredit, typeFrais, typeFormation, typeFraisMedicaux, typeSanction, grade,
		lieuxTravail, marcheProgramme, bareme, detailBareme, historique, credit, employe, profession,
		trancheRemboursementCredit, role, operateur, exercice, bulletinPaie, deduction, primesIndemnites, echellon,
		organismesSociaux, organismeSupportantBaseSalarial, retenu, detailRetenu, typeAbsence, nhifranges, unite,
		detailprofession, detailEchelon, detailBanqueEmploye, detailPrimeEmploye, detailIndemniteEmploye,
		detailCotisationEmploye, detailDeductionEmploye, saisieabsence, typeConge, coordonneesSociete,
		bulletinPaiePrime, bulletinPaieIndemnite, detailBulletinPaieCredit, bulletinPaieCotisation, bulletinCredit,
		detailUnite, saisieconge, saisieformation, saisiesanction, saisiefraisdivers, saisiefraismedicaux,
		saisiepresence, textaccompOV, affectationprojet, caissesociale, coteGratification, signataire, typeVisa,
		typeVisaTravail, cotisationSpecial, previsionFraisMedicaux, saisiePrevisionConge, saisieDemandeConge, droit,
		compteEmploye, ordreVirement, detailOrdreVirement, avanceQuinzaine, avanceDetail, detailAvanceQuinzaine,
		niveauHierarchique, organe, detailCredit, detailOrgane, detailParametrageHeureSupplementaire,
		parametrageHeureSupplementaire, cotisation, cotisationDetail, jourFerie, niveauFormation,
		detailGradeNiveauFormation, detailGradeEchelon, typeNotation, detailNotationBonificationAvancement,
		detailNotationIndemnite, detailNotationPrime, primeIndemnite, detailPrime, detailNiveauFormation,
		parametrageDureeConge, criteresGenerauxEvaluation, detailCritereGeneraux, critere, detailCritere,
		evaluationEmploye, detailEvaluationCritereGeneral, detailEvaluationCritere, detailEvaluationBonification,
		detailEvaluationPrime, detailEvaluationIndemnite, joursCongeEmploye, criteresRecrutement, heureSupplementaire,
		detailHeureSupplementaire, personnel, categoriePersonnel, critereEvaluation, typeCritere,
		detailCritereEvaluation, gradePersonnelDetail, gradePersonnel, grilleCotisation, bulletinComission,
		grilleCotisationDetail, avaliseur, remboursementCredit, detailGradeEmploye, personneCharge,
		personneChargeDetail, conditionsRempliesEmploye, conditionNomination, conditionNominationDetail,
		parametrageAllocationFamilliale, bulletinAllocation, bulletinDeduction, bulletinHeureSup, pointagePresence,
		bulletinAvance, HeurePreste, fonctionnaire, affectation, organeAdministrative, organeAdministrativeDetail,
		directionDetail, direction, services, planningConge, typeAppreciationAvancement, typeSoinSante,
		evaluationEmployeDetail, deuxiemeEvaluationEmploye, deuxiemeEvaluationEmployeDetailCritere,
		parametrageBonificationTitreComplementaire, parametrageBonificationAvancementGrade, parametrageAvancementGrade,
		parametrageDateNotation, traitementSalarial, troisiemeEvaluationEmploye, signataireDetail, partenaire,
		partenaireDetail, demandeAvancementGrade, parametrageGeneral, creditPrevisionRemboursement, parametrageJournal,
		parametrageJournalDetail, parametrageJournalElement, paieAnormal, saisiePositionDetailPrime,
		saisiePositionDetailIndemnite, saisiePositionEmploye, conditionPosition, detailParametragePositionPrim,
		parametragePosition, demandeProlongationRetraite, parametrageSanction, motifRetraite, directionGnle,
		parametragePlanConge, campus, parametrageFinCarriere, detailComissionEmploye, fonctionDetailTaches, taches,
		conseil, conseilDetail, roleMembreOrgane, faculteInstitut, centreOrganismeRecherche, departementSection,
		filieresOptions, saisiePlanConge, saisieRegimeDisciplinaire, demandeRetraiteAnticipe, finCarriere, saisieSortie,
		parametrageDecideurSignataire, finCarriereDetailIndemnite, parametrageDecideurDetail, finCarriereDetailPrime,
		employeDetailContrat, parametreEmail, natureConge, sousService, parametragePrime, parametragePrimeDetail,
		parametreCotisation, parametreCotisationDetail, elementJournal, parametrageAvancementSalaire;
	}

	public static String getTableName(TableName tbl) {
		String tableName = "";

		switch (tbl) {
		case province:
			tableName = "tbl_province";
			break;
		case commune:
			tableName = "tbl_commune";
			break;
		case zone:
			tableName = "tbl_zone";
			break;
		case quartier:
			tableName = "tbl_quartier";
			break;
		case devise:
			tableName = "tbl_devise";
			break;
		case fonction:
			tableName = "tbl_fonction";
			break;
		case departement:
			tableName = "tbl_departement";
			break;
		case pays:
			tableName = "tbl_pays";
			break;
		case banque:
			tableName = "tbl_banque";
			break;
		case typeCredit:
			tableName = "tbl_type_credit";
			break;
		case typeContrat:
			tableName = "tbl_type_contrat";
			break;
		case categorieEmploye:
			tableName = "tbl_categorie_employe";
			break;
		case sourceFinancementCredit:
			tableName = "tbl_source_financement_credit";
			break;
		case typeFrais:
			tableName = "tbl_type_frais";
			break;
		case typeFormation:
			tableName = "tbl_type_formation";
			break;
		case typeFraisMedicaux:
			tableName = "tbl_type_frais_medicaux";
			break;
		case typeSanction:
			tableName = "tbl_type_sanction";
			break;
		case grade:
			tableName = "tbl_grade";
			break;
		case lieuxTravail:
			tableName = "tbl_lieu_travail";
			break;
		case marcheProgramme:
			tableName = "tbl_marche_programme";
			break;
		case bareme:
			tableName = "tbl_bareme_ire";
			break;
		case detailBareme:
			tableName = "tbl_bareme_ire_detail";
			break;
		case historique:
			tableName = "tbl_historique";
			break;
		case credit:
			tableName = "tbl_credit";
			break;
		case employe:
			tableName = "tbl_employe";
			break;
		case profession:
			tableName = "tbl_profession";
			break;
		case trancheRemboursementCredit:
			tableName = "tbl_tranche_remboursement_credit";
			break;
		case role:
			tableName = "tbl_role";
			break;
		case operateur:
			tableName = "tbl_utilisateur";
			break;
		case exercice:
			tableName = "tbl_exercice";
			break;
		case bulletinPaie:
			tableName = "tbl_bulletin_paie";
			break;
		case deduction:
			tableName = "tbl_deduction";
			break;
		case primesIndemnites:
			tableName = "tbl_primes_indemnite";
			break;
		case echellon:
			tableName = "tbl_echellon";
			break;
		case organismesSociaux:
			tableName = "tbl_organismes_sociaux";
			break;
		case organismeSupportantBaseSalarial:
			tableName = "tbl_organisme_supportant_base_salarial";
			break;
		case retenu:
			tableName = "tbl_retenue_cotisation";
			break;
		case detailRetenu:
			tableName = "tbl_detail_retenu";
			break;
		case typeAbsence:
			tableName = "tbl_type_absence";
			break;

		case detailprofession:
			tableName = "tbl_employe_detail_profession";
			break;
		case detailEchelon:
			tableName = "tbl_detail_echelon";
			break;
		case detailBanqueEmploye:
			tableName = "tbl_employe_detail_banque";
			break;
		case detailPrimeEmploye:
			tableName = "tbl_employe_detail_prime";
			break;
		case detailIndemniteEmploye:
			tableName = "tbl_employe_detail_indemnite";
			break;
		case detailCotisationEmploye:
			tableName = "tbl_employe_detail_cotisation";
			break;
		case detailDeductionEmploye:
			tableName = "tbl_employe_detail_deduction";
			break;
		case saisieabsence:
			tableName = "tbl_saisie_absence";
			break;
		case typeConge:
			tableName = "tbl_type_conge";
			break;
		case coordonneesSociete:
			tableName = "tbl_coordonnees_societe";
			break;
		case bulletinPaiePrime:
			tableName = "tbl_bulletin_prime";
			break;
		case bulletinPaieIndemnite:
			tableName = "tbl_bulletin_indemnite";
			break;
		case detailBulletinPaieCredit:
			tableName = "tbl_detail_bulletin_paie_credit";
			break;
		case bulletinDeduction:
			tableName = "tbl_bulletin_deduction";
			break;
		case bulletinPaieCotisation:
			tableName = "tbl_bulletin_cotisation";
			break;
		case detailUnite:
			tableName = "tbl_detail_unite";
			break;
		case saisieconge:
			tableName = "tbl_saisie_conge";
			break;
		case saisieformation:
			tableName = "tbl_saisie_formation";
			break;
		case saisiefraisdivers:
			tableName = "tbl_saisie_frais_divers";
			break;
		case saisiefraismedicaux:
			tableName = "tbl_saisie_frais_medicaux";
			break;
		case textaccompOV:
			tableName = "tbl_texte_accompagnant_ov";
			break;
		case saisiepresence:
			tableName = "tbl_saisie_presence";
			break;
		case saisiesanction:
			tableName = "tbl_saisie_sanction";
			break;
		case affectationprojet:
			tableName = "tbl_affectation_projet";
			break;
		case caissesociale:
			tableName = "tbl_caisse_sociale";
			break;
		case coteGratification:
			tableName = "tbl_cote_gratification";
			break;
		case signataire:
			tableName = "tbl_signataire";
			break;
		case typeVisa:
			tableName = "tbl_type_visa";
			break;
		case typeVisaTravail:
			tableName = "tbl_type_visa_travail";
			break;
		case cotisationSpecial:
			tableName = "tbl_cotisation_special";
			break;
		case previsionFraisMedicaux:
			tableName = "tbl_prevision_frais_medicaux";
			break;
		case saisiePrevisionConge:
			tableName = "tbl_saisie_prevision_conge";
			break;
		case saisieDemandeConge:
			tableName = "tbl_saisie_demande_conge";
			break;
		case droit:
			tableName = "tbl_droit";
			break;
		case compteEmploye:
			tableName = "tbl_compte_employe";
			break;
		case ordreVirement:
			tableName = "tbl_ordre_virement";
			break;
		case detailOrdreVirement:
			tableName = "tbl_detail_ordre_virement";
			break;
		case avanceQuinzaine:
			tableName = "tbl_avance_quinzaine";
			break;
		case detailAvanceQuinzaine:
			tableName = "tbl_detail_avance_quinzaine";
			break;
		case niveauHierarchique:
			tableName = "tbl_niveau_hierarchique";
			break;
		case organe:
			tableName = "tbl_organe";
			break;
		case detailOrgane:
			tableName = "tbl_employe_detail_organe";
			break;
		case detailParametrageHeureSupplementaire:
			tableName = "tbl_detail_parametrage_heure_supplementaire";
			break;
		case parametrageHeureSupplementaire:
			tableName = "tbl_parametrage_heure_supplementaire";
			break;
		case cotisation:
			tableName = "tbl_cotisation";
			break;
		case cotisationDetail:
			tableName = "tbl_cotisation_detail";
			break;
		case jourFerie:
			tableName = "tbl_jour_ferie";
			break;
		case niveauFormation:
			tableName = "tbl_niveau_formation";
			break;
		case detailGradeNiveauFormation:
			tableName = "tbl_detail_grade_niveau_formation";
			break;
		case detailGradeEchelon:
			tableName = "tbl_detail_grade_echelon";
			break;
		case typeNotation:
			tableName = "tbl_type_notation";
			break;
		case detailNotationBonificationAvancement:
			tableName = "tbl_detail_notation_bonification_avancement";
			break;
		case detailNotationIndemnite:
			tableName = "tbl_detail_notation_indemnite";
			break;
		case detailNotationPrime:
			tableName = "tbl_detail_notation_prime";
			break;
		case primeIndemnite:
			tableName = "tbl_prime_indemnite";
			break;
		case detailPrime:
			tableName = "tbl_detail_prime";
			break;
		case detailNiveauFormation:
			tableName = "tbl_employe_detail_formation";
			break;
		case parametrageDureeConge:
			tableName = "tbl_parametrage_duree_conge";
			break;
		case criteresGenerauxEvaluation:
			tableName = "tbl_crieteres_generaux_evaluation";
			break;
		case detailCritereGeneraux:
			tableName = "tbl_detail_critere_generaux";
			break;
		case critere:
			tableName = "tbl_critere";
			break;
		case detailCritere:
			tableName = "tbl_detail_critere";
			break;
		case evaluationEmploye:
			tableName = "tbl_evaluation1";
			break;
		case detailEvaluationCritereGeneral:
			tableName = "tbl_detail_evaluation_critere_general";
			break;
		case detailEvaluationCritere:
			tableName = "tbl_detail_evaluation_critere";
			break;
		case detailEvaluationBonification:
			tableName = "tbl_detail_evaluation_bonification";
			break;
		case detailEvaluationPrime:
			tableName = "tbl_detail_evaluation_prime";
			break;
		case detailEvaluationIndemnite:
			tableName = "tbl_detail_evaluation_indemnite";
			break;

		case joursCongeEmploye:
			tableName = "tbl_jours_conge_employe";
			break;

		case heureSupplementaire:
			tableName = "tbl_heure_supplementaire";
			break;

		case detailHeureSupplementaire:
			tableName = "tbl_heure_supplementaire_detail";
			break;

		case personnel:
			tableName = "tbl_personnel";
			break;

		case categoriePersonnel:
			tableName = "tbl_categorie_personnel";
			break;

		case critereEvaluation:
			tableName = "tbl_critere_evaluation";
			break;

		case typeCritere:
			tableName = "tbl_type_critere";
			break;

		case detailCritereEvaluation:
			tableName = "tbl_critere_evaluation_detail";
			break;

		case gradePersonnelDetail:
			tableName = "tbl_grade_personnel_detail";
			break;

		case gradePersonnel:
			tableName = "tbl_grade_personnel";
			break;
		case grilleCotisation:
			tableName = "tbl_grille_cotisation";
			break;
		case grilleCotisationDetail:
			tableName = "tbl_grille_cotisation_detail";
			break;
		case detailCredit:
			tableName = "tbl_credit_detail";
			break;
		case avaliseur:
			tableName = "tbl_credit_avaliseur";
			break;
		case remboursementCredit:
			tableName = "tbl_credit_rembourse";
			break;
		case detailGradeEmploye:
			tableName = "tbl_employe_detail_grade";
			break;
		case personneCharge:
			tableName = "tbl_personne_charge";
			break;
		case personneChargeDetail:
			tableName = "tbl_personne_charge_detail";
			break;
		case conditionsRempliesEmploye:
			tableName = "tbl_employe_conditions_remplies";
			break;
		case conditionNomination:
			tableName = "tbl_conditions_nomination";
			break;
		case conditionNominationDetail:
			tableName = "tbl_conditions_nomination_detail";
			break;
		case parametrageAllocationFamilliale:
			tableName = "tbl_parametrage_allocation_familliale";
			break;
		case bulletinAllocation:
			tableName = "tbl_bulletin_allocation";
			break;
		case bulletinHeureSup:
			tableName = "tbl_bulletin_heure_sup";
			break;
		case pointagePresence:
			tableName = "tbl_pointage_presence";
			break;
		case bulletinCredit:
			tableName = "tbl_bulletin_credit";
			break;
		case bulletinAvance:
			tableName = "tbl_bulletin_avance";
			break;
		case HeurePreste:
			tableName = "tbl_heures_prestes";
			break;
		case fonctionnaire:
			tableName = "tbl_fonctionnaire";
			break;
		case affectation:
			tableName = "tbl_affectation";
			break;
		case organeAdministrative:
			tableName = "tbl_organe_administrative";
			break;
		case organeAdministrativeDetail:
			tableName = "tbl_organe_administrative_detail";
			break;
		case directionDetail:
			tableName = "tbl_direction_detail";
			break;
		case direction:
			tableName = "tbl_direction";
			break;
		case services:
			tableName = "tbl_services";
			break;

		case planningConge:
			tableName = "tbl_planning_conge";
			break;
		case typeAppreciationAvancement:
			tableName = "tbl_type_appreciation_avancement";
			break;
		case typeSoinSante:
			tableName = "tbl_type_soin_sante";
			break;
		case evaluationEmployeDetail:
			tableName = "tbl_evaluation1_detail";
			break;
		case deuxiemeEvaluationEmploye:
			tableName = "tbl_evaluation2";
			break;
		case deuxiemeEvaluationEmployeDetailCritere:
			tableName = "tbl_evaluation2_detail";
			break;
		case parametrageBonificationTitreComplementaire:
			tableName = "tbl_parametrage_bonification_titre_complementaire";
			break;
		case parametrageBonificationAvancementGrade:
			tableName = "tbl_parametrage_bonification_avancement_grade";
			break;
		case parametrageAvancementGrade:
			tableName = "tbl_parametrage_avancement_grade";
			break;
		case parametrageDateNotation:
			tableName = "tbl_parametrage_date_notation";
			break;
		case troisiemeEvaluationEmploye:
			tableName = "tbl_evaluation3";
			break;
		case traitementSalarial:
			tableName = "tbl_traitement_salarial";
			break;
		case signataireDetail:
			tableName = "tbl_signataire_detail";
			break;
		case partenaire:
			tableName = "tbl_partenaire";
			break;
		case partenaireDetail:
			tableName = "tbl_partenaire_detail";
			break;
		case demandeAvancementGrade:
			tableName = "tbl_demande_avancement_grade";
			break;
		case parametrageGeneral:
			tableName = "tbl_parametrage_general";
			break;
		case creditPrevisionRemboursement:
			tableName = "tbl_credit_pevision";
			break;
		case parametrageJournal:
			tableName = "tbl_parametre_journal";
			break;
		case parametrageJournalDetail:
			tableName = "tbl_parametre_journal_detail";
			break;
		case paieAnormal:
			tableName = "tbl_paie_anormal";
			break;
		case saisiePositionDetailPrime:
			tableName = "tbl_saisie_position_detail_prime";
			break;
		case saisiePositionDetailIndemnite:
			tableName = "tbl_saisie_position_detail_indemnite";
			break;
		case saisiePositionEmploye:
			tableName = "tbl_saisie_position_employe";
			break;
		case conditionPosition:
			tableName = "tbl_condition_position";
			break;
		case detailParametragePositionPrim:
			tableName = "tbl_parametrage_position_prime";
			break;
		case parametragePosition:
			tableName = "tbl_parametrage_position";
			break;
		case demandeProlongationRetraite:
			tableName = "tbl_demande_prolongation_retraite";
			break;
		case parametrageSanction:
			tableName = "tbl_parametrage_sanction";
			break;
		case directionGnle:
			tableName = "tbl_direction_generale";
			break;
		case parametragePlanConge:
			tableName = "tbl_parametrage_plan_conge";
			break;
		case campus:
			tableName = "tbl_campus";
			break;
		case parametrageFinCarriere:
			tableName = "tbl_parametrage_fin_carriere";
			break;
		case fonctionDetailTaches:
			tableName = "tbl_fonction_detail_taches";
			break;
		case taches:
			tableName = "tbl_taches";
			break;
		case conseil:
			tableName = "tbl_conseil";
			break;
		case conseilDetail:
			tableName = "tbl_conseil_detail";
			break;
		case roleMembreOrgane:
			tableName = "tbl_role_membre_organe";
			break;
		case faculteInstitut:
			tableName = "tbl_faculte_institut";
			break;
		case centreOrganismeRecherche:
			tableName = "tbl_centre_organisme_recherche";
			break;
		case departementSection:
			tableName = "tbl_departement_section";
			break;
		case filieresOptions:
			tableName = "tbl_filieres_options";
			break;
		case saisiePlanConge:
			tableName = "tbl_saisie_plan_conge";
			break;
		case saisieRegimeDisciplinaire:
			tableName = "tbl_saisie_regime_disciplinaire";
			break;
		case demandeRetraiteAnticipe:
			tableName = "tbl_demande_retraite_anticipe";
			break;
		case finCarriere:
			tableName = "tbl_fin_carriere";
			break;
		case saisieSortie:
			tableName = "tbl_sortie";
			break;
		case parametrageDecideurSignataire:
			tableName = "tbl_parametrage_signataire";
			break;
		case finCarriereDetailIndemnite:
			tableName = "tbl_fin_carriere_detail_indemnite";
			break;
		case finCarriereDetailPrime:
			tableName = "tbl_fin_carriere_detail_prime";
			break;
		case employeDetailContrat:
			tableName = "tbl_employe_detail_contrat";
			break;
		case parametreEmail:
			tableName = "tbl_parametre_e_mail";
			break;
		case natureConge:
			tableName = "tbl_nature_conge";
			break;
		case sousService:
			tableName = "tbl_sous_service";
			break;
		case parametragePrime:
			tableName = "tbl_parametrage_prime";
			break;
		case parametragePrimeDetail:
			tableName = "tbl_parametrage_prime_detail";
			break;
		case parametreCotisation:
			tableName = "tbl_parametre_cotisation";
			break;
		case parametreCotisationDetail:
			tableName = "tbl_parametre_cotisation_detail";
			break;
		case parametrageJournalElement:
			tableName = "tbl_parametre_journal_element";
			break;
		case elementJournal:
			tableName = "tbl_element_journal";
			break;
		case parametrageDecideurDetail:
			tableName = "tbl_parametrage_signataire_detail";
			break;
		case avanceDetail:
			tableName = "tbl_avance_detail";
			break;
		case parametrageAvancementSalaire:
			tableName = "tbl_parametrage_avancement_salaire";
			break;
		case motifRetraite:
			tableName = "tbl_motif_retraite";
			break;
		case detailComissionEmploye:
			tableName = "tbl_employe_detail_comission";
			break;
		case bulletinComission:
			tableName = "tbl_bulletin_comission";
			break;
		default:
			break;
		}

		return tableName;
	}
}
