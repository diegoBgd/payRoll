package classesPaie;

import java.util.List;
import persistencePaie.FichierBaseDAO;


public class ValeurFormuleC
{

    private double partSalarial;
    private double partPatronal;
    private double baseCalcule;
    private double montantPrime;
    private double montantBase;
    private EmployeC employe;
    private ParametreCotisationC cotisation;
    private PrimeIndemniteC prime;
    private List<BulletinHeureSupplementaireC> listHsup;

    public ValeurFormuleC()
    {
    }

   

    public List<BulletinHeureSupplementaireC> getListHsup() {
		return listHsup;
	}

	public void setListHsup(List<BulletinHeureSupplementaireC> listHsup) {
		this.listHsup = listHsup;
	}

	public EmployeC getEmploye()
    {
        return employe;
    }

    public void setEmploye(EmployeC employe)
    {
        this.employe = employe;
    }

    public double getPartSalarial()
    {
        return partSalarial;
    }

    public void setPartSalarial(double partSalarial)
    {
        this.partSalarial = partSalarial;
    }

    public double getPartPatronal()
    {
        return partPatronal;
    }

    public void setPartPatronal(double partPatronal)
    {
        this.partPatronal = partPatronal;
    }

    public double getBaseCalcule()
    {
        return baseCalcule;
    }

    public void setBaseCalcule(double baseCalcule)
    {
        this.baseCalcule = baseCalcule;
    }

    public double getMontantPrime()
    {
        return montantPrime;
    }

    public void setMontantPrime(double montantPrime)
    {
        this.montantPrime = montantPrime;
    }

    public ParametreCotisationC getCotisation()
    {
        return cotisation;
    }

    public void setCotisation(ParametreCotisationC cotisation)
    {
        this.cotisation = cotisation;
    }

    public PrimeIndemniteC getPrime()
    {
        return prime;
    }

    public void setPrime(PrimeIndemniteC prime)
    {
        this.prime = prime;
    }

    public double getMontantBase()
    {
        return montantBase;
    }

    public void setMontantBase(double montantBase)
    {
        this.montantBase = montantBase;
    }

    public void calculValeurPrime()
    {
        String elementsFormule = "";
        String expressionFormule = "";
        String code = "";
        double montant = 0.0D;
    }

    public void calculValeurFormuleCotisaion()
    {
//        String elementsFormule = "";
//        String expressionFormule = "";
//        String code = "";
//        double montant = 0.0D;
//        CalculFormuleC.baseCalcule = 0.0D;
//        CalculFormuleC.partSalarial = 0.0D;
//        CalculFormuleC.partPatronal = 0.0D;
//        if(cotisation != null)
//        {
//            if(cotisation.getIdBareme() > 0)
//            {
//                BaremeIPRC bar = FichierBaseDAO.getInstance().getBareme(cotisation.getIdBareme());
//                if(bar != null)
//                {
//                    cotisation.setFormule(bar.getFormule());
//                }
//            }
//            if(cotisation.getFormule() != null && !cotisation.getFormule().equals(""))
//            {
//                expressionFormule = cotisation.getFormule();
//                elementsFormule = Helper.getElementFormule(expressionFormule);
//                String elements[] = elementsFormule.split(" ");
//                for(int i = 0; i <= elements.length - 1; i++)
//                {
//                    montant = 0.0D;
//                    if(elements[i].toString().startsWith("P"))
//                    {
//                        code = elements[i].toString().substring(1);
//                     
//                        
//                    }
//                    if(elements[i].toString().startsWith("C"))
//                    {
//                        code = elements[i].toString().substring(1);
//                        CotisationC cot = FichierBaseDAO.getInstance().getCotisation(code);
//                        CalculFormuleC.listHeurSup = listHsup;
//                        
//                        if(cot != null && cot.getFormule() != null && !cot.getFormule().equals("") && !cot.getFormule().equals("SB"))
//                        {
//                            CalculFormuleC.valeurFormuleCotisationImpot(cot, employe, montantBase);
//                            montant = CalculFormuleC.partSalarial;
//                            expressionFormule = expressionFormule.replaceAll(elements[i], (new StringBuilder()).append(montant).toString());
//                        }
//                        CalculFormuleC.partSalarial = 0.0D;
//                        CalculFormuleC.partPatronal = 0.0D;
//                    }
//                }
//
//                cotisation.setFormule(expressionFormule);
//            }
//            CalculFormuleC.valeurFormuleCotisationImpot(cotisation, employe, montantBase);
//            baseCalcule = CalculFormuleC.baseCalcule;
//            partSalarial = CalculFormuleC.partSalarial;
//            partPatronal = CalculFormuleC.partPatronal;
//            CalculFormuleC.listHeurSup = null;
//        }
    }
}
