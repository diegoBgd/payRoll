package classesPaie;

import java.io.Serializable;

// Referenced classes of package classesPaie:
//            OrdreVirementC, EmployeC, BanqueC, Historique

public class DetailOrdreVirementC
    implements Serializable
{

    private static final long serialVersionUID = 0x2cb87570944a4017L;
    private int id;
    private int index;
    private String numeroCompte;
    private String montantString;
    private double montant;
    private OrdreVirementC ordreVirement;
    private EmployeC employe;
    private BanqueC banque;
    private Historique historique;

    public DetailOrdreVirementC()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNumeroCompte()
    {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte)
    {
        this.numeroCompte = numeroCompte;
    }

    public double getMontant()
    {
        return montant;
    }

    public void setMontant(double montant)
    {
        this.montant = montant;
    }

    public OrdreVirementC getOrdreVirement()
    {
        return ordreVirement;
    }

    public void setOrdreVirement(OrdreVirementC ordreVirement)
    {
        this.ordreVirement = ordreVirement;
    }

    public EmployeC getEmploye()
    {
        return employe;
    }

    public void setEmploye(EmployeC employe)
    {
        this.employe = employe;
    }

    public BanqueC getBanque()
    {
        return banque;
    }

    public void setBanque(BanqueC banque)
    {
        this.banque = banque;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getMontantString()
    {
        return montantString;
    }

    public void setMontantString(String montantString)
    {
        this.montantString = montantString;
    }

    public Historique getHistorique()
    {
        return historique;
    }

    public void setHistorique(Historique historique)
    {
        this.historique = historique;
    }
}
