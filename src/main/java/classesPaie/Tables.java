/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ public class Tables
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3059993536607917864L;
/*     */   
/*     */   public enum TableName
/*     */   {
/*  13 */     province, commune, zone, quartier, devise, fonction, departement,
/*  14 */     pays, banque, typeCredit, typeContrat, categorieEmploye,
/*  15 */     sourceFinancementCredit, typeFrais, typeFormation, typeFraisMedicaux,
/*  16 */     typeSanction, grade, lieuxTravail, marcheProgramme, bareme, detailBareme,
/*  17 */     historique, credit, employe, profession, trancheRemboursementCredit, role,
/*  18 */     operateur, exercice, bulletinPaie, deduction, primesIndemnites, echellon,
/*  19 */     organismesSociaux, organismeSupportantBaseSalarial, retenu, detailRetenu,
/*  20 */     typeAbsence, nhifranges, unite, detailprofession, detailEchelon,
/*  21 */     detailBanqueEmploye, detailPrimeEmploye, detailIndemniteEmploye,
/*  22 */     detailCotisationEmploye, detailDeductionEmploye, saisieabsence,
/*  23 */     typeConge, coordonneesSociete, bulletinPaiePrime, bulletinPaieIndemnite,
/*  24 */     detailBulletinPaieCredit, bulletinPaieCotisation, bulletinCredit,
/*  25 */     detailUnite, saisieconge, saisieformation, saisiesanction, saisiefraisdivers,
/*  26 */     saisiefraismedicaux, saisiepresence, textaccompOV, affectationprojet,
/*  27 */     caissesociale, coteGratification, signataire, typeVisa, typeVisaTravail,
/*  28 */     cotisationSpecial, previsionFraisMedicaux, saisiePrevisionConge, saisieDemandeConge,
/*  29 */     droit, compteEmploye, ordreVirement, detailOrdreVirement, avanceQuinzaine, avanceDetail,
/*  30 */     detailAvanceQuinzaine, niveauHierarchique, organe, detailCredit, detailOrgane,
/*  31 */     detailParametrageHeureSupplementaire, parametrageHeureSupplementaire, cotisation,
/*  32 */     cotisationDetail, jourFerie, niveauFormation, detailGradeNiveauFormation,
/*  33 */     detailGradeEchelon, typeNotation, detailNotationBonificationAvancement,
/*  34 */     detailNotationIndemnite, detailNotationPrime, primeIndemnite, detailPrime,
/*  35 */     detailNiveauFormation, parametrageDureeConge, criteresGenerauxEvaluation, detailCritereGeneraux,
/*  36 */     critere, detailCritere, evaluationEmploye, detailEvaluationCritereGeneral,
/*  37 */     detailEvaluationCritere, detailEvaluationBonification, detailEvaluationPrime,
/*  38 */     detailEvaluationIndemnite, joursCongeEmploye, criteresRecrutement,
/*  39 */     heureSupplementaire, detailHeureSupplementaire, personnel,
/*  40 */     categoriePersonnel, critereEvaluation, typeCritere, detailCritereEvaluation,
/*  41 */     gradePersonnelDetail, gradePersonnel, grilleCotisation,bulletinComission,
/*  42 */     grilleCotisationDetail, avaliseur, remboursementCredit, detailGradeEmploye,
/*  43 */     personneCharge, personneChargeDetail, conditionsRempliesEmploye, conditionNomination,
/*  44 */     conditionNominationDetail, parametrageAllocationFamilliale, bulletinAllocation,
/*  45 */     bulletinDeduction, bulletinHeureSup, pointagePresence, bulletinAvance, HeurePreste,
/*  46 */     fonctionnaire, affectation, organeAdministrative, organeAdministrativeDetail,
/*  47 */     directionDetail, direction, services, planningConge, typeAppreciationAvancement,
/*  48 */     typeSoinSante, evaluationEmployeDetail, deuxiemeEvaluationEmploye,
/*  49 */     deuxiemeEvaluationEmployeDetailCritere, parametrageBonificationTitreComplementaire,
/*  50 */     parametrageBonificationAvancementGrade, parametrageAvancementGrade, parametrageDateNotation,
/*  51 */     traitementSalarial, troisiemeEvaluationEmploye, signataireDetail, partenaire, partenaireDetail,
/*  52 */     demandeAvancementGrade, parametrageGeneral, creditPrevisionRemboursement, parametrageJournal,
/*  53 */     parametrageJournalDetail, parametrageJournalElement, paieAnormal, saisiePositionDetailPrime,
/*  54 */     saisiePositionDetailIndemnite, saisiePositionEmploye, conditionPosition, detailParametragePositionPrim,
/*  55 */     parametragePosition, demandeProlongationRetraite, parametrageSanction,motifRetraite,
/*  56 */     directionGnle, parametragePlanConge, campus, parametrageFinCarriere,detailComissionEmploye,
/*  57 */     fonctionDetailTaches, taches, conseil, conseilDetail, roleMembreOrgane, faculteInstitut, centreOrganismeRecherche,
/*  58 */     departementSection, filieresOptions, saisiePlanConge, saisieRegimeDisciplinaire, demandeRetraiteAnticipe,
/*  59 */     finCarriere, saisieSortie, parametrageDecideurSignataire, finCarriereDetailIndemnite, parametrageDecideurDetail,
/*  60 */     finCarriereDetailPrime, employeDetailContrat, parametreEmail, natureConge, sousService, parametragePrime,
/*  61 */     parametragePrimeDetail, parametreCotisation, parametreCotisationDetail, elementJournal,parametrageAvancementSalaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTableName(TableName tbl) {
/*  66 */     String tableName = "";
/*     */     
/*  68 */     switch (tbl) {
/*     */       case province:
/*  70 */         tableName = "tbl_province";
/*     */         break;
/*     */       case commune:
/*  73 */         tableName = "tbl_commune";
/*     */         break;
/*     */       case zone:
/*  76 */         tableName = "tbl_zone";
/*     */         break;
/*     */       case quartier:
/*  79 */         tableName = "tbl_quartier";
/*     */         break;
/*     */       case devise:
/*  82 */         tableName = "tbl_devise";
/*     */         break;
/*     */       case fonction:
/*  85 */         tableName = "tbl_fonction";
/*     */         break;
/*     */       case departement:
/*  88 */         tableName = "tbl_departement";
/*     */         break;
/*     */       case pays:
/*  91 */         tableName = "tbl_pays";
/*     */         break;
/*     */       case banque:
/*  94 */         tableName = "tbl_banque";
/*     */         break;
/*     */       case typeCredit:
/*  97 */         tableName = "tbl_type_credit";
/*     */         break;
/*     */       case typeContrat:
/* 100 */         tableName = "tbl_type_contrat";
/*     */         break;
/*     */       case categorieEmploye:
/* 103 */         tableName = "tbl_categorie_employe";
/*     */         break;
/*     */       case sourceFinancementCredit:
/* 106 */         tableName = "tbl_source_financement_credit";
/*     */         break;
/*     */       case typeFrais:
/* 109 */         tableName = "tbl_type_frais";
/*     */         break;
/*     */       case typeFormation:
/* 112 */         tableName = "tbl_type_formation";
/*     */         break;
/*     */       case typeFraisMedicaux:
/* 115 */         tableName = "tbl_type_frais_medicaux";
/*     */         break;
/*     */       case typeSanction:
/* 118 */         tableName = "tbl_type_sanction";
/*     */         break;
/*     */       case grade:
/* 121 */         tableName = "tbl_grade";
/*     */         break;
/*     */       case lieuxTravail:
/* 124 */         tableName = "tbl_lieu_travail";
/*     */         break;
/*     */       case marcheProgramme:
/* 127 */         tableName = "tbl_marche_programme";
/*     */         break;
/*     */       case bareme:
/* 130 */         tableName = "tbl_bareme_ire";
/*     */         break;
/*     */       case detailBareme:
/* 133 */         tableName = "tbl_bareme_ire_detail";
/*     */         break;
/*     */       case historique:
/* 136 */         tableName = "tbl_historique";
/*     */         break;
/*     */       case credit:
/* 139 */         tableName = "tbl_credit";
/*     */         break;
/*     */       case employe:
/* 142 */         tableName = "tbl_employe";
/*     */         break;
/*     */       case profession:
/* 145 */         tableName = "tbl_profession";
/*     */         break;
/*     */       case trancheRemboursementCredit:
/* 148 */         tableName = "tbl_tranche_remboursement_credit";
/*     */         break;
/*     */       case role:
/* 151 */         tableName = "tbl_role";
/*     */         break;
/*     */       case operateur:
/* 154 */         tableName = "tbl_utilisateur";
/*     */         break;
/*     */       case exercice:
/* 157 */         tableName = "tbl_exercice";
/*     */         break;
/*     */       case bulletinPaie:
/* 160 */         tableName = "tbl_bulletin_paie";
/*     */         break;
/*     */       case deduction:
/* 163 */         tableName = "tbl_deduction";
/*     */         break;
/*     */       case primesIndemnites:
/* 166 */         tableName = "tbl_primes_indemnite";
/*     */         break;
/*     */       case echellon:
/* 169 */         tableName = "tbl_echellon";
/*     */         break;
/*     */       case organismesSociaux:
/* 172 */         tableName = "tbl_organismes_sociaux";
/*     */         break;
/*     */       case organismeSupportantBaseSalarial:
/* 175 */         tableName = "tbl_organisme_supportant_base_salarial";
/*     */         break;
/*     */       case retenu:
/* 178 */         tableName = "tbl_retenue_cotisation";
/*     */         break;
/*     */       case detailRetenu:
/* 181 */         tableName = "tbl_detail_retenu";
/*     */         break;
/*     */       case typeAbsence:
/* 184 */         tableName = "tbl_type_absence";
/*     */         break;
/*     */      

/*     */       case detailprofession:
/* 193 */         tableName = "tbl_employe_detail_profession";
/*     */         break;
/*     */       case detailEchelon:
/* 196 */         tableName = "tbl_detail_echelon";
/*     */         break;
/*     */       case detailBanqueEmploye:
/* 199 */         tableName = "tbl_employe_detail_banque";
/*     */         break;
/*     */       case detailPrimeEmploye:
/* 202 */         tableName = "tbl_employe_detail_prime";
/*     */         break;
/*     */       case detailIndemniteEmploye:
/* 205 */         tableName = "tbl_employe_detail_indemnite";
/*     */         break;
/*     */       case detailCotisationEmploye:
/* 208 */         tableName = "tbl_employe_detail_cotisation";
/*     */         break;
/*     */       case detailDeductionEmploye:
/* 211 */         tableName = "tbl_employe_detail_deduction";
/*     */         break;
/*     */       case saisieabsence:
/* 214 */         tableName = "tbl_saisie_absence";
/*     */         break;
/*     */       case typeConge:
/* 217 */         tableName = "tbl_type_conge";
/*     */         break;
/*     */       case coordonneesSociete:
/* 220 */         tableName = "tbl_coordonnees_societe";
/*     */         break;
/*     */       case bulletinPaiePrime:
/* 223 */         tableName = "tbl_bulletin_prime";
/*     */         break;
/*     */       case bulletinPaieIndemnite:
/* 226 */         tableName = "tbl_bulletin_indemnite";
/*     */         break;
/*     */       case detailBulletinPaieCredit:
/* 229 */         tableName = "tbl_detail_bulletin_paie_credit";
/*     */         break;
/*     */       case bulletinDeduction:
/* 232 */         tableName = "tbl_bulletin_deduction";
/*     */         break;
/*     */       case bulletinPaieCotisation:
/* 235 */         tableName = "tbl_bulletin_cotisation";
/*     */         break;
/*     */       case detailUnite:
/* 238 */         tableName = "tbl_detail_unite";
/*     */         break;
/*     */       case saisieconge:
/* 241 */         tableName = "tbl_saisie_conge";
/*     */         break;
/*     */       case saisieformation:
/* 244 */         tableName = "tbl_saisie_formation";
/*     */         break;
/*     */       case saisiefraisdivers:
/* 247 */         tableName = "tbl_saisie_frais_divers";
/*     */         break;
/*     */       case saisiefraismedicaux:
/* 250 */         tableName = "tbl_saisie_frais_medicaux";
/*     */         break;
/*     */       case textaccompOV:
/* 253 */         tableName = "tbl_texte_accompagnant_ov";
/*     */         break;
/*     */       case saisiepresence:
/* 256 */         tableName = "tbl_saisie_presence";
/*     */         break;
/*     */       case saisiesanction:
/* 259 */         tableName = "tbl_saisie_sanction";
/*     */         break;
/*     */       case affectationprojet:
/* 262 */         tableName = "tbl_affectation_projet";
/*     */         break;
/*     */       case caissesociale:
/* 265 */         tableName = "tbl_caisse_sociale";
/*     */         break;
/*     */       case coteGratification:
/* 268 */         tableName = "tbl_cote_gratification";
/*     */         break;
/*     */       case signataire:
/* 271 */         tableName = "tbl_signataire";
/*     */         break;
/*     */       case typeVisa:
/* 274 */         tableName = "tbl_type_visa";
/*     */         break;
/*     */       case typeVisaTravail:
/* 277 */         tableName = "tbl_type_visa_travail";
/*     */         break;
/*     */       case cotisationSpecial:
/* 280 */         tableName = "tbl_cotisation_special";
/*     */         break;
/*     */       case previsionFraisMedicaux:
/* 283 */         tableName = "tbl_prevision_frais_medicaux";
/*     */         break;
/*     */       case saisiePrevisionConge:
/* 286 */         tableName = "tbl_saisie_prevision_conge";
/*     */         break;
/*     */       case saisieDemandeConge:
/* 289 */         tableName = "tbl_saisie_demande_conge";
/*     */         break;
/*     */       case droit:
/* 292 */         tableName = "tbl_droit";
/*     */         break;
/*     */       case compteEmploye:
/* 295 */         tableName = "tbl_compte_employe";
/*     */         break;
/*     */       case ordreVirement:
/* 298 */         tableName = "tbl_ordre_virement";
/*     */         break;
/*     */       case detailOrdreVirement:
/* 301 */         tableName = "tbl_detail_ordre_virement";
/*     */         break;
/*     */       case avanceQuinzaine:
/* 304 */         tableName = "tbl_avance_quinzaine";
/*     */         break;
/*     */       case detailAvanceQuinzaine:
/* 307 */         tableName = "tbl_detail_avance_quinzaine";
/*     */         break;
/*     */       case niveauHierarchique:
/* 310 */         tableName = "tbl_niveau_hierarchique";
/*     */         break;
/*     */       case organe:
/* 313 */         tableName = "tbl_organe";
/*     */         break;
/*     */       case detailOrgane:
/* 316 */         tableName = "tbl_employe_detail_organe";
/*     */         break;
/*     */       case detailParametrageHeureSupplementaire:
/* 319 */         tableName = "tbl_detail_parametrage_heure_supplementaire";
/*     */         break;
/*     */       case parametrageHeureSupplementaire:
/* 322 */         tableName = "tbl_parametrage_heure_supplementaire";
/*     */         break;
/*     */       case cotisation:
/* 325 */         tableName = "tbl_cotisation";
/*     */         break;
/*     */       case cotisationDetail:
/* 328 */         tableName = "tbl_cotisation_detail";
/*     */         break;
/*     */       case jourFerie:
/* 331 */         tableName = "tbl_jour_ferie";
/*     */         break;
/*     */       case niveauFormation:
/* 334 */         tableName = "tbl_niveau_formation";
/*     */         break;
/*     */       case detailGradeNiveauFormation:
/* 337 */         tableName = "tbl_detail_grade_niveau_formation";
/*     */         break;
/*     */       case detailGradeEchelon:
/* 340 */         tableName = "tbl_detail_grade_echelon";
/*     */         break;
/*     */       case typeNotation:
/* 343 */         tableName = "tbl_type_notation";
/*     */         break;
/*     */       case detailNotationBonificationAvancement:
/* 346 */         tableName = "tbl_detail_notation_bonification_avancement";
/*     */         break;
/*     */       case detailNotationIndemnite:
/* 349 */         tableName = "tbl_detail_notation_indemnite";
/*     */         break;
/*     */       case detailNotationPrime:
/* 352 */         tableName = "tbl_detail_notation_prime";
/*     */         break;
/*     */       case primeIndemnite:
/* 355 */         tableName = "tbl_prime_indemnite";
/*     */         break;
/*     */       case detailPrime:
/* 358 */         tableName = "tbl_detail_prime";
/*     */         break;
/*     */       case detailNiveauFormation:
/* 361 */         tableName = "tbl_employe_detail_formation";
/*     */         break;
/*     */       case parametrageDureeConge:
/* 364 */         tableName = "tbl_parametrage_duree_conge";
/*     */         break;
/*     */       case criteresGenerauxEvaluation:
/* 367 */         tableName = "tbl_crieteres_generaux_evaluation";
/*     */         break;
/*     */       case detailCritereGeneraux:
/* 370 */         tableName = "tbl_detail_critere_generaux";
/*     */         break;
/*     */       case critere:
/* 373 */         tableName = "tbl_critere";
/*     */         break;
/*     */       case detailCritere:
/* 376 */         tableName = "tbl_detail_critere";
/*     */         break;
/*     */       case evaluationEmploye:
/* 379 */         tableName = "tbl_evaluation1";
/*     */         break;
/*     */       case detailEvaluationCritereGeneral:
/* 382 */         tableName = "tbl_detail_evaluation_critere_general";
/*     */         break;
/*     */       case detailEvaluationCritere:
/* 385 */         tableName = "tbl_detail_evaluation_critere";
/*     */         break;
/*     */       case detailEvaluationBonification:
/* 388 */         tableName = "tbl_detail_evaluation_bonification";
/*     */         break;
/*     */       case detailEvaluationPrime:
/* 391 */         tableName = "tbl_detail_evaluation_prime";
/*     */         break;
/*     */       case detailEvaluationIndemnite:
/* 394 */         tableName = "tbl_detail_evaluation_indemnite";
/*     */         break;
/*     */       
/*     */       case joursCongeEmploye:
/* 398 */         tableName = "tbl_jours_conge_employe";
/*     */         break;
/*     */       
/*     */       case heureSupplementaire:
/* 402 */         tableName = "tbl_heure_supplementaire";
/*     */         break;
/*     */       
/*     */       case detailHeureSupplementaire:
/* 406 */         tableName = "tbl_heure_supplementaire_detail";
/*     */         break;
/*     */ 
/*     */       
/*     */       case personnel:
/* 411 */         tableName = "tbl_personnel";
/*     */         break;
/*     */       
/*     */       case categoriePersonnel:
/* 415 */         tableName = "tbl_categorie_personnel";
/*     */         break;
/*     */       
/*     */       case critereEvaluation:
/* 419 */         tableName = "tbl_critere_evaluation";
/*     */         break;
/*     */       
/*     */       case typeCritere:
/* 423 */         tableName = "tbl_type_critere";
/*     */         break;
/*     */       
/*     */       case detailCritereEvaluation:
/* 427 */         tableName = "tbl_critere_evaluation_detail";
/*     */         break;
/*     */       
/*     */       case gradePersonnelDetail:
/* 431 */         tableName = "tbl_grade_personnel_detail";
/*     */         break;
/*     */       
/*     */       case gradePersonnel:
/* 435 */         tableName = "tbl_grade_personnel";
/*     */         break;
/*     */       case grilleCotisation:
/* 438 */         tableName = "tbl_grille_cotisation";
/*     */         break;
/*     */       case grilleCotisationDetail:
/* 441 */         tableName = "tbl_grille_cotisation_detail";
/*     */         break;
/*     */       case detailCredit:
/* 444 */         tableName = "tbl_credit_detail";
/*     */         break;
/*     */       case avaliseur:
/* 447 */         tableName = "tbl_credit_avaliseur";
/*     */         break;
/*     */       case remboursementCredit:
/* 450 */         tableName = "tbl_credit_rembourse";
/*     */         break;
/*     */       case detailGradeEmploye:
/* 453 */         tableName = "tbl_employe_detail_grade";
/*     */         break;
/*     */       case personneCharge:
/* 456 */         tableName = "tbl_personne_charge";
/*     */         break;
/*     */       case personneChargeDetail:
/* 459 */         tableName = "tbl_personne_charge_detail";
/*     */         break;
/*     */       case conditionsRempliesEmploye:
/* 462 */         tableName = "tbl_employe_conditions_remplies";
/*     */         break;
/*     */       case conditionNomination:
/* 465 */         tableName = "tbl_conditions_nomination";
/*     */         break;
/*     */       case conditionNominationDetail:
/* 468 */         tableName = "tbl_conditions_nomination_detail";
/*     */         break;
/*     */       case parametrageAllocationFamilliale:
/* 471 */         tableName = "tbl_parametrage_allocation_familliale";
/*     */         break;
/*     */       case bulletinAllocation:
/* 474 */         tableName = "tbl_bulletin_allocation";
/*     */         break;
/*     */       case bulletinHeureSup:
/* 477 */         tableName = "tbl_bulletin_heure_sup";
/*     */         break;
/*     */       case pointagePresence:
/* 480 */         tableName = "tbl_pointage_presence";
/*     */         break;
/*     */       case bulletinCredit:
/* 483 */         tableName = "tbl_bulletin_credit";
/*     */         break;
/*     */       case bulletinAvance:
/* 486 */         tableName = "tbl_bulletin_avance";
/*     */         break;
/*     */       case HeurePreste:
/* 489 */         tableName = "tbl_heures_prestes";
/*     */         break;
/*     */       case fonctionnaire:
/* 492 */         tableName = "tbl_fonctionnaire";
/*     */         break;
/*     */       case affectation:
/* 495 */         tableName = "tbl_affectation";
/*     */         break;
/*     */       case organeAdministrative:
/* 498 */         tableName = "tbl_organe_administrative";
/*     */         break;
/*     */       case organeAdministrativeDetail:
/* 501 */         tableName = "tbl_organe_administrative_detail";
/*     */         break;
/*     */       case directionDetail:
/* 504 */         tableName = "tbl_direction_detail";
/*     */         break;
/*     */       case direction:
/* 507 */         tableName = "tbl_direction";
/*     */         break;
/*     */       case services:
/* 510 */         tableName = "tbl_services";
/*     */         break;
/*     */       
/*     */       case planningConge:
/* 514 */         tableName = "tbl_planning_conge";
/*     */         break;
/*     */       case typeAppreciationAvancement:
/* 517 */         tableName = "tbl_type_appreciation_avancement";
/*     */         break;
/*     */       case typeSoinSante:
/* 520 */         tableName = "tbl_type_soin_sante";
/*     */         break;
/*     */       case evaluationEmployeDetail:
/* 523 */         tableName = "tbl_evaluation1_detail";
/*     */         break;
/*     */       case deuxiemeEvaluationEmploye:
/* 526 */         tableName = "tbl_evaluation2";
/*     */         break;
/*     */       case deuxiemeEvaluationEmployeDetailCritere:
/* 529 */         tableName = "tbl_evaluation2_detail";
/*     */         break;
/*     */       case parametrageBonificationTitreComplementaire:
/* 532 */         tableName = "tbl_parametrage_bonification_titre_complementaire";
/*     */         break;
/*     */       case parametrageBonificationAvancementGrade:
/* 535 */         tableName = "tbl_parametrage_bonification_avancement_grade";
/*     */         break;
/*     */       case parametrageAvancementGrade:
/* 538 */         tableName = "tbl_parametrage_avancement_grade";
/*     */         break;
/*     */       case parametrageDateNotation:
/* 541 */         tableName = "tbl_parametrage_date_notation";
/*     */         break;
/*     */       case troisiemeEvaluationEmploye:
/* 544 */         tableName = "tbl_evaluation3";
/*     */         break;
/*     */       case traitementSalarial:
/* 547 */         tableName = "tbl_traitement_salarial";
/*     */         break;
/*     */       case signataireDetail:
/* 550 */         tableName = "tbl_signataire_detail";
/*     */         break;
/*     */       case partenaire:
/* 553 */         tableName = "tbl_partenaire";
/*     */         break;
/*     */       case partenaireDetail:
/* 556 */         tableName = "tbl_partenaire_detail";
/*     */         break;
/*     */       case demandeAvancementGrade:
/* 559 */         tableName = "tbl_demande_avancement_grade";
/*     */         break;
/*     */       case parametrageGeneral:
/* 562 */         tableName = "tbl_parametrage_general";
/*     */         break;
/*     */       case creditPrevisionRemboursement:
/* 565 */         tableName = "tbl_credit_pevision";
/*     */         break;
/*     */       case parametrageJournal:
/* 568 */         tableName = "tbl_parametre_journal";
/*     */         break;
/*     */       case parametrageJournalDetail:
/* 571 */         tableName = "tbl_parametre_journal_detail";
/*     */         break;
/*     */       case paieAnormal:
/* 574 */         tableName = "tbl_paie_anormal";
/*     */         break;
/*     */       case saisiePositionDetailPrime:
/* 577 */         tableName = "tbl_saisie_position_detail_prime";
/*     */         break;
/*     */       case saisiePositionDetailIndemnite:
/* 580 */         tableName = "tbl_saisie_position_detail_indemnite";
/*     */         break;
/*     */       case saisiePositionEmploye:
/* 583 */         tableName = "tbl_saisie_position_employe";
/*     */         break;
/*     */       case conditionPosition:
/* 586 */         tableName = "tbl_condition_position";
/*     */         break;
/*     */       case detailParametragePositionPrim:
/* 589 */         tableName = "tbl_parametrage_position_prime";
/*     */         break;
/*     */       case parametragePosition:
/* 592 */         tableName = "tbl_parametrage_position";
/*     */         break;
/*     */       case demandeProlongationRetraite:
/* 595 */         tableName = "tbl_demande_prolongation_retraite";
/*     */         break;
/*     */       case parametrageSanction:
/* 598 */         tableName = "tbl_parametrage_sanction";
/*     */         break;
/*     */       case directionGnle:
/* 601 */         tableName = "tbl_direction_generale";
/*     */         break;
/*     */       case parametragePlanConge:
/* 604 */         tableName = "tbl_parametrage_plan_conge";
/*     */         break;
/*     */       case campus:
/* 607 */         tableName = "tbl_campus";
/*     */         break;
/*     */       case parametrageFinCarriere:
/* 610 */         tableName = "tbl_parametrage_fin_carriere";
/*     */         break;
/*     */       case fonctionDetailTaches:
/* 613 */         tableName = "tbl_fonction_detail_taches";
/*     */         break;
/*     */       case taches:
/* 616 */         tableName = "tbl_taches";
/*     */         break;
/*     */       case conseil:
/* 619 */         tableName = "tbl_conseil";
/*     */         break;
/*     */       case conseilDetail:
/* 622 */         tableName = "tbl_conseil_detail";
/*     */         break;
/*     */       case roleMembreOrgane:
/* 625 */         tableName = "tbl_role_membre_organe";
/*     */         break;
/*     */       case faculteInstitut:
/* 628 */         tableName = "tbl_faculte_institut";
/*     */         break;
/*     */       case centreOrganismeRecherche:
/* 631 */         tableName = "tbl_centre_organisme_recherche";
/*     */         break;
/*     */       case departementSection:
/* 634 */         tableName = "tbl_departement_section";
/*     */         break;
/*     */       case filieresOptions:
/* 637 */         tableName = "tbl_filieres_options";
/*     */         break;
/*     */       case saisiePlanConge:
/* 640 */         tableName = "tbl_saisie_plan_conge";
/*     */         break;
/*     */       case saisieRegimeDisciplinaire:
/* 643 */         tableName = "tbl_saisie_regime_disciplinaire";
/*     */         break;
/*     */       case demandeRetraiteAnticipe:
/* 646 */         tableName = "tbl_demande_retraite_anticipe";
/*     */         break;
/*     */       case finCarriere:
/* 649 */         tableName = "tbl_fin_carriere";
/*     */         break;
/*     */       case saisieSortie:
/* 652 */         tableName = "tbl_sortie";
/*     */         break;
/*     */       case parametrageDecideurSignataire:
/* 655 */         tableName = "tbl_parametrage_signataire";
/*     */         break;
/*     */       case finCarriereDetailIndemnite:
/* 658 */         tableName = "tbl_fin_carriere_detail_indemnite";
/*     */         break;
/*     */       case finCarriereDetailPrime:
/* 661 */         tableName = "tbl_fin_carriere_detail_prime";
/*     */         break;
/*     */       case employeDetailContrat:
/* 664 */         tableName = "tbl_employe_detail_contrat";
/*     */         break;
/*     */       case parametreEmail:
/* 667 */         tableName = "tbl_parametre_e_mail";
/*     */         break;
/*     */       case natureConge:
/* 670 */         tableName = "tbl_nature_conge";
/*     */         break;
/*     */       case sousService:
/* 673 */         tableName = "tbl_sous_service";
/*     */         break;
/*     */       case parametragePrime:
/* 676 */         tableName = "tbl_parametrage_prime";
/*     */         break;
/*     */       case parametragePrimeDetail:
/* 679 */         tableName = "tbl_parametrage_prime_detail";
/*     */         break;
/*     */       case parametreCotisation:
/* 682 */         tableName = "tbl_parametre_cotisation";
/*     */         break;
/*     */       case parametreCotisationDetail:
/* 685 */         tableName = "tbl_parametre_cotisation_detail";
/*     */         break;
/*     */       case parametrageJournalElement:
/* 688 */         tableName = "tbl_parametre_journal_element";
/*     */         break;
/*     */       case elementJournal:
/* 691 */         tableName = "tbl_element_journal";
/*     */         break;
/*     */       case parametrageDecideurDetail:
/* 694 */         tableName = "tbl_parametrage_signataire_detail";
/*     */         break;
/*     */       case avanceDetail:
/* 697 */         tableName = "tbl_avance_detail";
/*     */         break;
/*     */		case parametrageAvancementSalaire:
					tableName = "tbl_parametrage_avancement_salaire";
				break;
				case motifRetraite:
					tableName = "tbl_motif_retraite";
					break;
				case detailComissionEmploye:
					tableName = "tbl_employe_detail_comission";
					break;
				case bulletinComission:
					tableName="tbl_bulletin_comission";
					break;
default:
	break;     } 
/*     */ 
/*     */     
/* 702 */     return tableName;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\Tables.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */