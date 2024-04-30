package classesPaie;

import java.io.Serializable;
import persistencePaie.FichierBaseDAO;

public class CalculCotisationParBaremeC implements Serializable {
	private static final long serialVersionUID = 3514853604400549563L;
	public static double montantSalarial = 0.0D, montantPatronal = 0.0D, taux = 0.0D;

	public static void valeurCotisationONPR(double montantBase) {
		double onpr = 0.0D;

		GrilleCotisationC grille = null;
		DetailGrilleCotisationC detail = null;

		montantSalarial = 0.0D;
		montantPatronal = 0.0D;
		taux = 0.0D;

		grille = FichierBaseDAO.getInstance().getGrilleCotisation();
		if (grille != null) {
			onpr = montantBase / grille.getCoefficient();

			detail = FichierBaseDAO.getInstance().getDetail(onpr);

			if (detail != null) {
				montantSalarial = detail.getTotalCotisation();
			}
		}
	}

	public static void valeurCotisationImpot(double montantBase, CotisationC cotisation, int typeEmploye) {
		double ire = 0.0D;
		BaremeIPRC bareme = null;
		montantSalarial = 0.0D;
		montantPatronal = 0.0D;
		taux = 0.0D;
		// System.out.println(HelperC.decimalNumber(montantBase, 0, true));
		if (cotisation != null) {

			if (cotisation.getTypeBaremme() == 1) {

				double tranche = 0.0D;
				double montantA = 0.0D;
				double borneFinA = 0.0D;
				bareme = FichierBaseDAO.getInstance().getBaremeIRE(typeEmploye);

				if (bareme != null) {

					tranche = montantBase;

					int i = 0;
					for (DetailBaremeC det : bareme.getListDetailBareme()) {

						if (bareme.getType() == 2) {

							det.setBorneDebut(det.getBorneDebut() / 12.0D);
							det.setBorneFin(det.getBorneFin() / 12.0D);
						}

						if (det.getBorneFin() == 0.0D) {
							det.setBorneFin(Integer.MAX_VALUE);
						}

						if (montantBase >= det.getBorneDebut() && montantBase <= det.getBorneFin()) {

							tranche = montantBase - borneFinA;

							if (tranche > 0.0D) {

								ire += tranche * det.getPourcentage() / 100.0D;
								if (i > 0)
									montantA = (bareme.getListDetailBareme().get(i - 1)).getBorneDebut()
											* (bareme.getListDetailBareme().get(i - 1)).getPourcentage() / 100.0D;
								ire += montantA;
								det.setCotisation(ire);
							}
							taux = det.getPourcentage();
						}
						i++;
						borneFinA = det.getBorneFin();
						montantA = 0.0D;
					}

					montantSalarial = Math.rint(ire);
				}
			}
		}
	}
}
