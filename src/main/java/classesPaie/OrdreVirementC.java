package classesPaie;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package classesPaie:
//            BanqueC, OperateurC, Historique, ExerciceC

public class OrdreVirementC
    implements Serializable
{

    private static final long serialVersionUID = 0x811c75b51b112135L;
    private int id;
    private Date date;
    private Date datePaie;
    private double netAPaye;
    private String numero;
    private String libelleEcriture;
    private String netAPayeString;
    private BanqueC banque;
    private OperateurC operateur;
    private Historique historique;
    private ExerciceC exercice;
    private List listeDetailOrdreVirement;
    private List listeDetailOrdreVirementDeleted;

    public OrdreVirementC()
    {
        listeDetailOrdreVirement = new ArrayList();
        listeDetailOrdreVirementDeleted = new ArrayList();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDatePaie()
    {
        return datePaie;
    }

    public void setDatePaie(Date datePaie)
    {
        this.datePaie = datePaie;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getLibelleEcriture()
    {
        return libelleEcriture;
    }

    public void setLibelleEcriture(String libelleEcriture)
    {
        this.libelleEcriture = libelleEcriture;
    }

    public BanqueC getBanque()
    {
        return banque;
    }

    public void setBanque(BanqueC banque)
    {
        this.banque = banque;
    }

    public OperateurC getOperateur()
    {
        return operateur;
    }

    public void setOperateur(OperateurC operateur)
    {
        this.operateur = operateur;
    }

    public List getListeDetailOrdreVirement()
    {
        return listeDetailOrdreVirement;
    }

    public void setListeDetailOrdreVirement(List listeDetailOrdreVirement)
    {
        this.listeDetailOrdreVirement = listeDetailOrdreVirement;
    }

    public List getListeDetailOrdreVirementDeleted()
    {
        return listeDetailOrdreVirementDeleted;
    }

    public void setListeDetailOrdreVirementDeleted(List listeDetailOrdreVirementDeleted)
    {
        this.listeDetailOrdreVirementDeleted = listeDetailOrdreVirementDeleted;
    }

    public Historique getHistorique()
    {
        return historique;
    }

    public void setHistorique(Historique historique)
    {
        this.historique = historique;
    }

    public ExerciceC getExercice()
    {
        return exercice;
    }

    public void setExercice(ExerciceC exercice)
    {
        this.exercice = exercice;
    }

    public double getNetAPaye()
    {
        return netAPaye;
    }

    public void setNetAPaye(double netAPaye)
    {
        this.netAPaye = netAPaye;
    }

    public String getNetAPayeString()
    {
        return netAPayeString;
    }

    public void setNetAPayeString(String netAPayeString)
    {
        this.netAPayeString = netAPayeString;
    }
}
