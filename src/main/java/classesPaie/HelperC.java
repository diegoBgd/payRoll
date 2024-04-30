package classesPaie;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BarcodePDF417;
import com.lowagie.text.pdf.PdfPCell;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.EmailValidator;
import org.primefaces.event.FileUploadEvent;

// Referenced classes of package classesPaie:
//            ExerciceC
 
public class HelperC
{
    public static class TraitementMontant
    {
 
        private static String unites(int unite)
        {
            String unit = "";
            switch(unite)
            {
            case 0: // '\0'
                unit = "z\351ro";
                break;

            case 1: // '\001'
                unit = "un";
                break;

            case 2: // '\002'
                unit = "deux";
                break;

            case 3: // '\003'
                unit = "trois";
                break;

            case 4: // '\004'
                unit = "quatre";
                break;

            case 5: // '\005'
                unit = "cinq";
                break;

            case 6: // '\006'
                unit = "six";
                break;

            case 7: // '\007'
                unit = "sept";
                break;

            case 8: // '\b'
                unit = "huit";
                break;

            case 9: // '\t'
                unit = "neuf";
                break;

            case 10: // '\n'
                unit = "dix";
                break;

            case 11: // '\013'
                unit = "onze";
                break;

            case 12: // '\f'
                unit = "douze";
                break;

            case 13: // '\r'
                unit = "treize";
                break;

            case 14: // '\016'
                unit = "quatorze";
                break;

            case 15: // '\017'
                unit = "quinze";
                break;

            case 16: // '\020'
                unit = "seize";
                break;

            case 17: // '\021'
                unit = "dix-sept";
                break;

            case 18: // '\022'
                unit = "dix-huit";
                break;

            case 19: // '\023'
                unit = "dix-neuf";
                break;
            }
            return unit;
        }

        private static String dizaines(int dizaine)
        {
        	
            String _dizaine = "";
            switch(dizaine)
            {
            case 2: // '\002'
                _dizaine = "vingt";
                break;

            case 3: // '\003'
                _dizaine = "trente";
                break;

            case 4: // '\004'
                _dizaine = "quarante";
                break;

            case 5: // '\005'
                _dizaine = "cinquante";
                break;

            case 6: // '\006'
                _dizaine = "soixante";
                break;

            case 7: // '\007'
                _dizaine = "soixante";
                break;

            case 8: // '\b'
                _dizaine = "quatre-vingt";
                break;

            case 9: // '\t'
                _dizaine = "quatre-vingt";
                break;
            }
            return _dizaine;
        }

        private static String getDizaines(double montant, boolean sup)
        {
            String _montant = "";
            if(montant < 20D)
            {
                _montant = unites((int)montant);
            } else
            {
                int div = (int)(montant / 10D);
                int mod = (int)(montant % 10D);
                _montant = dizaines(div);
                if(div == 7 || div == 9)
                {
                    int diz = 10 + mod;
                    _montant = (new StringBuilder(String.valueOf(_montant))).append("-").append(unites(diz)).toString();
                } else
                if(mod == 0 && div == 8)
                {
                    if(!sup)
                    {
                        _montant = (new StringBuilder(String.valueOf(_montant))).append("s").toString();
                    }
                } else
                if(mod == 1)
                {
                    _montant = (new StringBuilder(String.valueOf(_montant))).append(" et un").toString();
                } else
                if(mod > 1)
                {
                    _montant = (new StringBuilder(String.valueOf(_montant))).append("-").append(unites(mod)).toString();
                }
            }
            return _montant;
        }

        private static String getCentaines(double montant, boolean sup)
        {
            String _montant = "";
            int div = (int)(montant / 100D);
            int mod = (int)(montant % 100D);
            if(div == 1)
            {
                _montant = "cent";
            } else
            {
                _montant = (new StringBuilder(String.valueOf(unites(div)))).append(" cent").toString();
            }
            if(mod == 0)
            {
                if(div > 1 && !sup)
                {
                    _montant = (new StringBuilder(String.valueOf(_montant))).append("s").toString();
                }
            } else
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getDizaines(mod, sup)).toString();
            }
            return _montant;
        }

        private static String getMille(double montant)
        {
            String _montant = "";
            int div = (int)(montant / 1000D);
            int mod = (int)(montant % 1000D);
            if(div > 99)
            {
                _montant = (new StringBuilder(String.valueOf(getCentaines(div, true)))).append(" mille").toString();
            } else
            if(div > 1)
            {
                _montant = (new StringBuilder(String.valueOf(getDizaines(div, true)))).append(" mille").toString();
            } else
            if(div > 0)
            {
                _montant = "mille";
            }
            if(mod > 99)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getCentaines(mod, false)).toString();
            } else
            if(mod > 0)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getDizaines(mod, false)).toString();
            }
            return _montant;
        }

        private static String getMillion(double montant)
        {
            String _montant = "";
            int div = (int)(montant / 1000000D);
            int mod = (int)(montant % 1000000D);
            if(div > 99)
            {
                _montant = (new StringBuilder(String.valueOf(getCentaines(div, false)))).append(" millions").toString();
            } else
            if(div > 1)
            {
                _montant = (new StringBuilder(String.valueOf(getDizaines(div, false)))).append(" millions").toString();
            } else
            {
                _montant = "un million";
            }
            if(mod > 999)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getMille(mod)).toString();
            } else
            if(mod > 99)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getCentaines(mod, false)).toString();
            } else
            if(mod > 1)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getDizaines(mod, false)).toString();
            }
            return _montant;
        }

        private static String getMilliard(double montant)
        {
            String _montant = "";
            int div = (int)(montant / 1000000000D);
            int mod = (int)(montant % 1000000000D);
            if(div > 99)
            {
                _montant = (new StringBuilder(String.valueOf(getCentaines(div, false)))).append(" milliards").toString();
            } else
            if(div > 1)
            {
                _montant = (new StringBuilder(String.valueOf(getDizaines(div, false)))).append(" milliards").toString();
            } else
            {
                _montant = "un milliard";
            }
            if(mod > 0xf423f)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getMillion(mod)).toString();
            } else
            if(mod > 999)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getMille(mod)).toString();
            } else
            if(mod > 99)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getCentaines(mod, false)).toString();
            } else
            if(mod > 1)
            {
                _montant = (new StringBuilder(String.valueOf(_montant))).append(" ").append(getDizaines(mod, false)).toString();
            }
            return _montant;
        }

        public static String getMontantEnLettre(double montant)
        {
            String _montant = "";
            if(montant > 999999999999D)
            {
                _montant = "Ce montant ne peut pas \352tre trait\351";
            } else
            if(montant <= 999999999999D && montant > 999999999D)
            {
                _montant = getMilliard(montant);
            } else
            if(montant <= 999999999D && montant > 999999D)
            {
                _montant = getMillion(montant);
            } else
            if(montant <= 999999D && montant > 999D)
            {
                _montant = getMille(montant);
            } else
            if(montant <= 999D && montant > 99D)
            {
                _montant = getCentaines(montant, false);
            } else
            if(montant <= 99D && montant >= 0.0D)
            {
                _montant = getDizaines(montant, false);
            } else
            {
                _montant = "Le montant n\351gatif ne peut pas \352tre trait\351";
            }
            return _montant;
        }

        public static String getMontantEnLettreDevise(double montant, int nbreCentime, String devise)
        {
        	
            java.util.List montants = getValeurs(montant, nbreCentime);
            String _montant = "";
            if(((Double)montants.get(1)).doubleValue() > 0.0D)
            {
                _montant = (new StringBuilder(String.valueOf(getMontantEnLettre(((Double)montants.get(0)).doubleValue())))).append(" ").append(devise).append(" ").append(getMontantEnLettre(((Double)montants.get(1)).doubleValue())).append(" centimes").toString();
            } else
            if(((Double)montants.get(1)).doubleValue() == 0.0D)
            {
                _montant = (new StringBuilder(String.valueOf(getMontantEnLettre(((Double)montants.get(0)).doubleValue())))).append(" ").append(devise).toString();
            }
            return _montant;
        }

        private static java.util.List getValeurs(double somme, int nbreDecimal)
        {
            long _somme = (new Double(somme * 1000D)).longValue();
            somme = Double.valueOf(_somme).doubleValue() / 1000D;
            java.util.List sommes = new ArrayList();
            long e = (new Double(somme)).longValue();
            sommes.add(Double.valueOf(e));
            if(nbreDecimal > 0)
            {
                DecimalFormat df = new DecimalFormat();
                df.setGroupingUsed(false);
                df.setMaximumFractionDigits(nbreDecimal);
                String str = df.format(somme);
                char separator = df.getDecimalFormatSymbols().getDecimalSeparator();
                str = str.substring(str.indexOf(separator) + 1);
                if(str.trim().length() == 1)
                {
                    str = (new StringBuilder(String.valueOf(str))).append("0").toString();
                }
                double dec = Double.parseDouble(str);
                if(dec == Double.valueOf(somme).doubleValue())
                {
                    dec = 0.0D;
                }
                sommes.add(Double.valueOf(dec));
            } else
            {
                sommes.add(Double.valueOf(0.0D));
            }
            return sommes;
        }

        public static String getMontantFormate(double montant, int nbrCentime)
        {
            String montantString = "";
            java.util.List montants = getValeurs(montant, nbrCentime);
            if(montant > 0.0D)
            {
                if(((Double)montants.get(0)).doubleValue() == 0.0D)
                {
                    if(nbrCentime > 0)
                    {
                        montantString = (new StringBuilder("0.")).append(HelperC.decimalNumber(((Double)montants.get(1)).doubleValue(), 0, true)).toString();
                    } else
                    {
                        montantString = HelperC.decimalNumber(montant, 0, true);
                    }
                } else
                if(nbrCentime > 0)
                {
                    if(((Double)montants.get(1)).doubleValue() == 0.0D)
                    {
                        montantString = HelperC.decimalNumber(montant, 0, true);
                    } else
                    {
                        montantString = HelperC.decimalNumber(montant, nbrCentime, true);
                    }
                } else
                if(nbrCentime == 0)
                {
                    montantString = HelperC.decimalNumber(montant, nbrCentime, true);
                }
                String m[] = montantString.trim().split(",");
                double _dec = 0.0D;
                try
                {
                    _dec = Double.valueOf(m[1].trim()).doubleValue();
                }
                catch(Exception exception) { }
                if(_dec > 0.0D)
                {
                    switch(nbrCentime)
                    {
                    case 2: // '\002'
                        if(m[1].trim().length() == 1)
                        {
                            montantString = (new StringBuilder(String.valueOf(montantString))).append("0").toString();
                        }
                        break;

                    case 3: // '\003'
                        if(m[1].trim().length() == 1)
                        {
                            montantString = (new StringBuilder(String.valueOf(montantString))).append("00").toString();
                        } else
                        if(m[1].trim().length() == 2)
                        {
                            montantString = (new StringBuilder(String.valueOf(montantString))).append("0").toString();
                        }
                        break;

                    case 4: // '\004'
                        if(m[1].trim().length() == 1)
                        {
                            montantString = (new StringBuilder(String.valueOf(montantString))).append("000").toString();
                        } else
                        if(m[1].trim().length() == 2)
                        {
                            montantString = (new StringBuilder(String.valueOf(montantString))).append("00").toString();
                        } else
                        if(m[1].trim().length() == 3)
                        {
                            montantString = (new StringBuilder(String.valueOf(montantString))).append("0").toString();
                        }
                        break;
                    }
                }
            } else
            {
                montantString = "0";
            }
            return montantString;
        }

        public TraitementMontant()
        {
        }
    }


    public HelperC()
    {
    }

    public static Date stringTodate(String dte)
    {
        Date d = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        try
        {
            d = formatter.parse(dte);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return d;
    }

    public static Date toDate(int year, int month, int day)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        cal.set(5, day);
        return cal.getTime();
    }

    public static String convertDate(Date date, boolean formatfr)
    {
        SimpleDateFormat fmt = null;
        if(formatfr)
        {
            fmt = new SimpleDateFormat("dd-MM-yyyy");
        } else
        {
            fmt = new SimpleDateFormat("yyyy-MM-dd");
        }
        if(date != null)
        {
            String myDate = fmt.format(date);
            return myDate;
        } else
        {
            return "";
        }
    }

    public static String convertToSqlDate(Date date)
    {
        SimpleDateFormat fmt = null;
        fmt = new SimpleDateFormat("yyyyMMdd");
        if(date != null)
        {
            String myDate = fmt.format(date);
            return myDate;
        } else
        {
            return "";
        }
    }

    public static String convertDate(Date date)
    {
        SimpleDateFormat fmt = null;
        fmt = new SimpleDateFormat("dd/MM/yyyy");
        if(date != null)
        {
            String myDate = fmt.format(date);
            return myDate;
        } else
        {
            return "";
        }
    }

    public static HttpSession getSession()
    {
    	HttpSession session=null;
    	try {
    		FacesContext cont = FacesContext.getCurrentInstance();
    		session=(HttpSession)cont.getExternalContext().getSession(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
        
        return session;
    }

    public static String convertDat(Date date)
    {
        SimpleDateFormat fmt = null;
        fmt = new SimpleDateFormat("yyyy-MM-dd");
        if(date != null)
        {
            String myDate = fmt.format(date);
            return myDate;
        } else
        {
            return "";
        }
    }

    public static String convertDateHeureMin(Date date)
    {
        SimpleDateFormat fmt = null;
        fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if(date != null)
        {
            String myDate = fmt.format(date);
            return myDate;
        } else
        {
            return "";
        }
    }

    public static String convertHeureMin(Date date)
    {
        SimpleDateFormat fmt = null;
        fmt = new SimpleDateFormat("HH:mm:ss");
        if(date != null)
        {
            String myDate = fmt.format(date);
            return myDate;
        } else
        {
            return "";
        }
    }

    public static int getMonthFromDate(Date date)
    {
        String mois = convertDate(date, true).split("-")[1];
        int m = Integer.valueOf(mois).intValue();
        return m;
    }

    public static String getLetter(String referenceLetter, boolean next, boolean before)
    {
        String ltr = "";
        String lettre[] = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
            "U", "V", "W", "X", "Y", "Z"
        };
        if(referenceLetter != "")
        {
            for(int i = 0; i < lettre.length; i++)
            {
                if(referenceLetter.equals(lettre[i]))
                {
                    if(next && i + 1 < lettre.length)
                    {
                        ltr = lettre[i + 1];
                    }
                    if(before && i - 1 >= 0)
                    {
                        ltr = lettre[i - 1];
                    }
                }
            }

        }
        return ltr;
    }

    public static String getMoisDateEnTouteLettre(String date)
    {
        String myDate = "";
        if(date != null)
        {
            String dates[] = date.split("-");
            int v = Integer.parseInt(dates[1]);
            switch(v)
            {
            case 1: // '\001'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Janvier-").append(dates[2]).toString();
                break;

            case 2: // '\002'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-F\351vrier-").append(dates[2]).toString();
                break;

            case 3: // '\003'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Mars-").append(dates[2]).toString();
                break;

            case 4: // '\004'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Avril-").append(dates[2]).toString();
                break;

            case 5: // '\005'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Mai-").append(dates[2]).toString();
                break;

            case 6: // '\006'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Juin-").append(dates[2]).toString();
                break;

            case 7: // '\007'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Juillet-").append(dates[2]).toString();
                break;

            case 8: // '\b'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Ao\373t-").append(dates[2]).toString();
                break;

            case 9: // '\t'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Septembre-").append(dates[2]).toString();
                break;

            case 10: // '\n'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Octobre-").append(dates[2]).toString();
                break;

            case 11: // '\013'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-Novembre-").append(dates[2]).toString();
                break;

            case 12: // '\f'
                myDate = (new StringBuilder(String.valueOf(dates[0]))).append("-D\351cembre-").append(dates[2]).toString();
                break;
            }
        }
        return myDate;
    }

    public static String getMoisEnTouteLettre(int mois)
    {
        String myMois = "";
        switch(mois)
        {
        case 1: // '\001'
            myMois = "Janvier";
            break;

        case 2: // '\002'
            myMois = "F\351vrier";
            break;

        case 3: // '\003'
            myMois = "Mars";
            break;

        case 4: // '\004'
            myMois = "Avril";
            break;

        case 5: // '\005'
            myMois = "Mai";
            break;

        case 6: // '\006'
            myMois = "Juin";
            break;

        case 7: // '\007'
            myMois = "Juillet";
            break;

        case 8: // '\b'
            myMois = "Ao\373t";
            break;

        case 9: // '\t'
            myMois = "Septembre";
            break;

        case 10: // '\n'
            myMois = "Octobre";
            break;

        case 11: // '\013'
            myMois = "Novembre";
            break;

        case 12: // '\f'
            myMois = "D\351cembre";
            break;
        }
        return myMois;
    }

    public static int GetIntValueByBoolean(Boolean val)
    {
        int a = 0;
        if(val.booleanValue())
        {
            a = 1;
        }
        return a;
    }

    public static int GetInt(String pays)
    {
        return 0;
    }

    public static String FiltreCode(String codedebut, String codefin, String tableName, String champcode)
    {
        String sqlRequest = "";
        if(!codedebut.equals(""))
        {
            sqlRequest = (new StringBuilder(String.valueOf(sqlRequest))).append(" AND ").append(tableName).append(".").append(champcode).append(">='").append(codedebut).append("'").toString();
        }
        if(!codefin.equals(""))
        {
            sqlRequest = (new StringBuilder(String.valueOf(sqlRequest))).append(" AND ").append(tableName).append(".").append(champcode).append("<='").append(codefin).append("'").toString();
        }
        return sqlRequest;
    }

    public static String FiltreDate(Date datedebut, Date datefin, String tableName, String champcode)
    {
        String sqlRequest = "";
        if(datedebut != null)
        {
            sqlRequest = (new StringBuilder(String.valueOf(sqlRequest))).append(" AND ").append(tableName).append(".").append(champcode).append(">='").append(convertDate(datedebut, false)).append("'").toString();
        }
        if(datefin != null)
        {
            sqlRequest = (new StringBuilder(String.valueOf(sqlRequest))).append(" AND ").append(tableName).append(".").append(champcode).append("<='").append(convertDate(datefin, false)).append(" 23:59:59'").toString();
        }
        return sqlRequest;
    }

    public static String FiltreDate(String datedebut, String datefin, String tableName, String champcode)
    {
        String sqlRequest = "";
        if(datedebut != null && datedebut.trim() != "")
        {
            sqlRequest = (new StringBuilder(String.valueOf(sqlRequest))).append(" AND ").append(tableName).append(".").append(champcode).append(">='").append(datedebut).append("'").toString();
        }
        if(datefin != null && datefin.trim() != "")
        {
            sqlRequest = (new StringBuilder(String.valueOf(sqlRequest))).append(" AND ").append(tableName).append(".").append(champcode).append("<='").append(datefin).append("'").toString();
        }
        return sqlRequest;
    }

    public static long daysBetween(Date datedebut, Date datefin)
    {
        long days = 0L;
        long diff = datefin.getTime() - datedebut.getTime();
        days = diff / 0x5265c00L;
        return days;
    }

    public static Date getDateAdded(Date datetomodify, int intadd, int positiontoadd)
    {
        Calendar ctest = Calendar.getInstance();
        ctest.setTime(datetomodify);
        switch(positiontoadd)
        {
        case 0: // '\0'
            ctest.add(5, intadd);
            break;

        case 1: // '\001'
            ctest.add(2, intadd);
            break;

        case 2: // '\002'
            ctest.add(1, intadd);
            break;
        }
        Date dateadded = ctest.getTime();
        return dateadded;
    }

    public static int getDataInDate(Date datedata, int position)
    {
        int t = 0;
        Calendar date = new GregorianCalendar();
        date.setTime(datedata);
        switch(position)
        {
        case 0: // '\0'
            t = date.get(5);
            break;

        case 1: // '\001'
            t = date.get(2);
            break;

        case 2: // '\002'
            t = date.get(1);
            break;
        }
        return t;
    }

    public static int getDaysinMoth(Date datedata)
    {
        int days = 0;
        Calendar datefin = new GregorianCalendar();
        datefin.setTime(datedata);
        Calendar c = Calendar.getInstance();
        c.set(1, datefin.get(1));
        c.set(2, datefin.get(2));
        days = c.getActualMaximum(5);
        return days;
    }

    public static void afficherInformation(String titre, String message)
    {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, titre, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void afficherErreur(String titre, String message)
    {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, titre, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void afficherAttention(String titre, String message)
    {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, titre, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static String completerCompte(String compte)
    {
        String _compte = "";
        if(compte == null || compte.trim().equals(""))
        {
            _compte = "0000000";
        } else
        if(compte.trim().length() == 1)
        {
            _compte = (new StringBuilder(String.valueOf(compte))).append("000000").toString();
        } else
        if(compte.trim().length() == 2)
        {
            _compte = (new StringBuilder(String.valueOf(compte))).append("00000").toString();
        } else
        if(compte.trim().length() == 3)
        {
            _compte = (new StringBuilder(String.valueOf(compte))).append("0000").toString();
        } else
        if(compte.trim().length() == 4)
        {
            _compte = (new StringBuilder(String.valueOf(compte))).append("000").toString();
        } else
        if(compte.trim().length() == 5)
        {
            _compte = (new StringBuilder(String.valueOf(compte))).append("00").toString();
        } else
        if(compte.trim().length() == 6)
        {
            _compte = (new StringBuilder(String.valueOf(compte))).append("0").toString();
        } else
        if(compte.trim().length() == 7)
        {
            _compte = compte;
        }
        return _compte;
    }

    public static String completerChrono(int chrono)
    {
        String _chrono = "";
        if(chrono < 10)
        {
            _chrono = (new StringBuilder("0000")).append(chrono).toString();
        } else
        if(chrono < 100)
        {
            _chrono = (new StringBuilder("000")).append(chrono).toString();
        } else
        if(chrono < 1000)
        {
            _chrono = (new StringBuilder("00")).append(chrono).toString();
        } else
        if(chrono < 10000)
        {
            _chrono = (new StringBuilder("0")).append(chrono).toString();
        } else
        {
            _chrono = (new StringBuilder()).append(chrono).toString();
        }
        return _chrono;
    }

    public static String determinerClefControle(String chrono, ExerciceC exercice)
    {
        String clef = "";
        int a1 = Integer.valueOf(exercice.getCode().substring(2, 3)).intValue();
        int a2 = Integer.valueOf(exercice.getCode().substring(3, 4)).intValue();
        int a3 = Integer.valueOf(chrono.substring(0, 1)).intValue();
        int a4 = Integer.valueOf(chrono.substring(1, 2)).intValue();
        int a5 = Integer.valueOf(chrono.substring(2, 3)).intValue();
        int a6 = Integer.valueOf(chrono.substring(3, 4)).intValue();
        int a7 = Integer.valueOf(chrono.substring(4, 5)).intValue();
        int b1 = a1 * 7;
        int b2 = a2 * 8;
        int b3 = a3 * 9;
        int b4 = a4 * 10;
        int b5 = a5 * 11;
        int b6 = a6 * 12;
        int b7 = a7 * 13;
        int somme = b1 + b2 + b3 + b4 + b5 + b6 + b7;
        int mod = somme % 24;
        switch(mod)
        {
        case 0: // '\0'
            clef = "A";
            break;

        case 1: // '\001'
            clef = "B";
            break;

        case 2: // '\002'
            clef = "C";
            break;

        case 3: // '\003'
            clef = "D";
            break;

        case 4: // '\004'
            clef = "E";
            break;

        case 5: // '\005'
            clef = "F";
            break;

        case 6: // '\006'
            clef = "G";
            break;

        case 7: // '\007'
            clef = "H";
            break;

        case 8: // '\b'
            clef = "J";
            break;

        case 9: // '\t'
            clef = "K";
            break;

        case 10: // '\n'
            clef = "L";
            break;

        case 11: // '\013'
            clef = "M";
            break;

        case 12: // '\f'
            clef = "N";
            break;

        case 13: // '\r'
            clef = "P";
            break;

        case 14: // '\016'
            clef = "Q";
            break;

        case 15: // '\017'
            clef = "R";
            break;

        case 16: // '\020'
            clef = "S";
            break;

        case 17: // '\021'
            clef = "T";
            break;

        case 18: // '\022'
            clef = "U";
            break;

        case 19: // '\023'
            clef = "V";
            break;

        case 20: // '\024'
            clef = "W";
            break;

        case 21: // '\025'
            clef = "X";
            break;

        case 22: // '\026'
            clef = "Y";
            break;

        case 23: // '\027'
            clef = "Z";
            break;
        }
        return clef;
    }

    public static boolean verifierClefControle(String nif)
    {
        boolean valide = false;
        if(nif == null || nif.trim().length() != 8)
        {
            afficherMessage("Attention!!", "NIF Invalide");
        } else
        {
            String clef = "";
            int a1 = Integer.valueOf(nif.substring(0, 1)).intValue();
            int a2 = Integer.valueOf(nif.substring(1, 2)).intValue();
            int a3 = Integer.valueOf(nif.substring(2, 3)).intValue();
            int a4 = Integer.valueOf(nif.substring(3, 4)).intValue();
            int a5 = Integer.valueOf(nif.substring(4, 5)).intValue();
            int a6 = Integer.valueOf(nif.substring(5, 6)).intValue();
            int a7 = Integer.valueOf(nif.substring(6, 7)).intValue();
            int b1 = a1 * 7;
            int b2 = a2 * 8;
            int b3 = a3 * 9;
            int b4 = a4 * 10;
            int b5 = a5 * 11;
            int b6 = a6 * 12;
            int b7 = a7 * 13;
            int somme = b1 + b2 + b3 + b4 + b5 + b6 + b7;
            int mod = somme % 24;
            switch(mod)
            {
            case 0: // '\0'
                clef = "A";
                break;

            case 1: // '\001'
                clef = "B";
                break;

            case 2: // '\002'
                clef = "C";
                break;

            case 3: // '\003'
                clef = "D";
                break;

            case 4: // '\004'
                clef = "E";
                break;

            case 5: // '\005'
                clef = "F";
                break;

            case 6: // '\006'
                clef = "G";
                break;

            case 7: // '\007'
                clef = "H";
                break;

            case 8: // '\b'
                clef = "J";
                break;

            case 9: // '\t'
                clef = "K";
                break;

            case 10: // '\n'
                clef = "L";
                break;

            case 11: // '\013'
                clef = "M";
                break;

            case 12: // '\f'
                clef = "N";
                break;

            case 13: // '\r'
                clef = "P";
                break;

            case 14: // '\016'
                clef = "Q";
                break;

            case 15: // '\017'
                clef = "R";
                break;

            case 16: // '\020'
                clef = "S";
                break;

            case 17: // '\021'
                clef = "T";
                break;

            case 18: // '\022'
                clef = "U";
                break;

            case 19: // '\023'
                clef = "V";
                break;

            case 20: // '\024'
                clef = "W";
                break;

            case 21: // '\025'
                clef = "X";
                break;

            case 22: // '\026'
                clef = "Y";
                break;

            case 23: // '\027'
                clef = "Z";
                break;
            }
            if(nif.substring(7, 8).trim().equals(clef.trim()))
            {
                valide = true;
            } else
            {
                afficherMessage("Attention!!", "NIF Invalide");
            }
        }
        return valide;
    }

    public static String nbreDecimalSansSeparateur(String nombre)
    {
        String valeur = "";
        if(nombre != null && !nombre.trim().equals(""))
        {
            String tab[] = NbreDecimal(nombre, 3, Boolean.valueOf(false)).split(",");
            valeur = (new StringBuilder(String.valueOf(tab[0]))).append(".").append(tab[1]).toString();
        }
        return valeur;
    }

    public static String NbreDecimal(String Nombre, int NbrePositionsDecimales, Boolean SeparateurMilliers)
    {
        double mount = Double.parseDouble(Nombre);
        return decimalNumber(mount, NbrePositionsDecimales, SeparateurMilliers.booleanValue());
    }

    public static String decimalNumber(double nombre, int nbrDc, boolean separat)
    {
        DecimalFormat format = null;
        String nbr = "0";
        String fmt = "";
        if(nombre != 0.0D)
        {
        	
            if(nbrDc > 0)
            {
                fmt = (new StringBuilder(String.valueOf(fmt))).append(".0").toString();
                for(int i = 1; i < nbrDc; i++)
                {
                    fmt = (new StringBuilder(String.valueOf(fmt))).append("#").toString();
                }

            }
            if(separat)
            {
                format = new DecimalFormat((new StringBuilder("###,###,###")).append(fmt).toString());
                DecimalFormatSymbols s = format.getDecimalFormatSymbols();
                s.setGroupingSeparator(' ');
                format.setDecimalFormatSymbols(s);
            } else
            {
                format = new DecimalFormat((new StringBuilder("######")).append(fmt).toString());
            }
            nbr = format.format(nombre);
        }
        return nbr;
    }

    public static String NoSepartorNumber(String number)
    {
        String nb = "";
        String mots[] = null;
        String decimal = "";
        for(int k = 0; k < number.length() - 1; k++)
        {
            if(number.charAt(k) == ',')
            {
                mots = number.split(",");
                decimal = mots[1];
            }
        }

        DecimalFormat formater = new DecimalFormat("###,###,###,###,##0");
        try
        {
            nb = formater.parse(number).toString();
            if(decimal != "")
            {
                nb = (new StringBuilder(String.valueOf(nb))).append(".").append(mots[1]).toString();
            }
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return nb;
    }

    public static String getdayOfWeek(Date dte)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(dte);
        int dayOfWeek = c.get(7) - 1;
        String jour = "";
        switch(dayOfWeek)
        {
        case 1: // '\001'
            jour = "LUNDI";
            break;

        case 2: // '\002'
            jour = "MARDI";
            break;

        case 3: // '\003'
            jour = "MERCREDI";
            break;

        case 4: // '\004'
            jour = "JEUDI";
            break;

        case 5: // '\005'
            jour = "VENDREDI";
            break;

        case 6: // '\006'
            jour = "SAMEDI";
            break;

        case 7: // '\007'
            jour = "DIMANCHE";
            break;

        default:
            jour = "0";
            break;
        }
        return jour;
    }

    public static String changeDateFormate(Date dt)
    {
        SimpleDateFormat fmt = null;
        fmt = new SimpleDateFormat("dd/MM/yyyy");
        if(dt != null)
        {
            String myDate = fmt.format(dt);
            return myDate;
        } else
        {
            return "";
        }
    }

    public static String changeStringFormat(String dt)
    {
        String dte = "";
        String mot[] = dt.split("-");
        dte = (new StringBuilder(String.valueOf(mot[2]))).append("-").append(mot[1]).append("-").append(mot[0]).toString();
        return dte;
    }

    public static long differenceDate(Date date1, Date date2)
    {
        long UNE_HEURE = 0x36ee80L;
        long diff = 0L;
        if(date1 != null && date2 != null)
        {
            diff = ((date2.getTime() - date1.getTime()) + UNE_HEURE) / (UNE_HEURE * 24L);
        }
        return diff;
    }

    public static String moisEnLettres(int s)
    {
        String mois = "";
        switch(s)
        {
        case 1: // '\001'
            mois = "Janvier";
            break;

        case 2: // '\002'
            mois = "F\351vrier";
            break;

        case 3: // '\003'
            mois = "Mars";
            break;

        case 4: // '\004'
            mois = "Avril";
            break;

        case 5: // '\005'
            mois = "Mais";
            break;

        case 6: // '\006'
            mois = "Juin";
            break;

        case 7: // '\007'
            mois = "Juillet";
            break;

        case 8: // '\b'
            mois = "Ao\373t";
            break;

        case 9: // '\t'
            mois = "Septembre";
            break;

        case 10: // '\n'
            mois = "Octobre";
            break;

        case 11: // '\013'
            mois = "Novembre";
            break;

        case 12: // '\f'
            mois = "D\351cembre";
            break;
        }
        return mois;
    }

    public static Date addYear(Date date, int YearsNumber)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1, Integer.valueOf(convertDate(date, true).split("-")[2]).intValue());
        cal.set(2, Integer.valueOf(convertDate(date, true).split("-")[1]).intValue() - 1);
        cal.set(5, Integer.valueOf(convertDate(date, true).split("-")[0]).intValue());
        cal.add(1, YearsNumber);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int monthsNumber)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1, Integer.valueOf(convertDate(date, true).split("-")[2]).intValue());
        cal.set(2, Integer.valueOf(convertDate(date, true).split("-")[1]).intValue() - 1);
        cal.set(5, Integer.valueOf(convertDate(date, true).split("-")[0]).intValue());
        cal.add(2, monthsNumber);
        return cal.getTime();
    }

    public static Date addDay(Date date, int daysNumber)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1, Integer.valueOf(convertDate(date, true).split("-")[2]).intValue());
        cal.set(2, Integer.valueOf(convertDate(date, true).split("-")[1]).intValue() - 1);
        cal.set(5, Integer.valueOf(convertDate(date, true).split("-")[0]).intValue());
        cal.add(5, daysNumber);
        return cal.getTime();
    }

    public static Date addDate(Date date, int daysNumber, int monthsNumber, int YearsNumber)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1, Integer.valueOf(convertDate(date, true).split("-")[2]).intValue());
        cal.set(2, Integer.valueOf(convertDate(date, true).split("-")[1]).intValue() - 1);
        cal.set(5, Integer.valueOf(convertDate(date, true).split("-")[0]).intValue());
        cal.add(5, daysNumber);
        cal.add(5, daysNumber);
        cal.add(2, monthsNumber);
        return cal.getTime();
    }

    public static String convertDateWithHourMinutesSec(Date date, boolean formatfr)
    {
        SimpleDateFormat fmt = null;
        if(formatfr)
        {
            fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        } else
        {
            fmt = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        }
        if(date != null)
        {
            String myDate = fmt.format(date);
            return myDate;
        } else
        {
            return "";
        }
    }

    public static double getElapsedTime(Date date1, Date date2)
    {
        double durationInDays = 0.0D;
        double durationInSec = 0.0D;
        if(date1 != null && date2 != null)
        {
            if(date2.after(date1))
            {
                durationInSec = (date2.getTime() - date1.getTime()) / 1000L;
            }
            durationInDays = durationInSec / 86400D;
        }
        return durationInDays;
    }

    public static double arrondissementDouble(double a, int n)
    {
        double p = Math.pow(10D, n);
        return Math.floor(a * p + 0.5D) / p;
    }

    public static double calculEcart(double dureePrevue, double dureeRealisee)
    {
        double ecart = 0.0D;
        ecart = dureePrevue - dureeRealisee;
        return ecart;
    }

    public static double ecartPourcentage(double dureePrevue, double dureeRealisee)
    {
        double ecartPourc = 0.0D;
        ecartPourc = (dureeRealisee * 100D) / dureePrevue;
        return ecartPourc;
    }

    public static String GetBooleanValueByString(String val)
    {
        if(val.compareTo("true") == 0)
        {
            return "Oui";
        } else
        {
            return "Non";
        }
    }

    public static boolean GetBooleanValueByInt(int val)
    {
        boolean b = false;
        switch(val)
        {
        case 0: // '\0'
            b = false;
            break;

        case 1: // '\001'
            b = true;
            break;
        }
        return b;
    }

    public static String motCripte(String mot)
    {
        String crpt = "";
        String pos = "";
        for(int i = 0; i < mot.length(); i++)
        {
            pos = getPosition(String.valueOf(mot.charAt(i)));
            
            if(!pos.equals(" "))
            {
                crpt = (new StringBuilder(String.valueOf(crpt))).append(getPosition(String.valueOf(mot.charAt(i)))).toString();
            } else
            {
                crpt = (new StringBuilder(String.valueOf(crpt))).append("S").append(String.valueOf(mot.charAt(i))).append("S ").toString();
            }
        }

        return crpt;
    }

    private static String getPosition(String s)
    {
        String value = " ";
        String size = "";
        String tble[][] = {
            {
                "A", "B", "C", "1", "D", "E"
            }, {
                "F", "2", "G", "H", "3", "I"
            }, {
                "J", "K", "4", "5", "L", "M"
            }, {
                "N", "O", "6", "7", "P", "Q"
            }, {
                "R", "8", "S", "T", "9", "U"
            }, {
                "V", "W", "X", "0", "Y", "Z"
            }
        };
        size = typeChar(s);
        for(int i = 0; i < tble.length; i++)
        {
            for(int j = 0; j < tble[i].length; j++)
            {
                if(s.toUpperCase().trim().equals(tble[i][j]))
                {
                    value = (new StringBuilder(String.valueOf(i))).append(size).append(j).append(" ").toString();
                    return value;
                }
            }

        }

        return value;
    }

    public static String decodeWord(String chaine[])
    {
        String mot = "";
        int k = 0;
        int j = 0;
        char typ = '\0';
        for(int i = 0; i < chaine.length; i++)
        {
            if(chaine[i].length() <= 2)
            {
                continue;
            }
            if(chaine[i].charAt(0) != 'S')
            {
                try
                {
                    typ = chaine[i].charAt(1);
                    k = Integer.valueOf(chaine[i].split(String.valueOf(typ))[0]).intValue();
                    j = Integer.valueOf(chaine[i].split(String.valueOf(typ))[1]).intValue();
                    if(j <= 5 && k <= 5)
                    {
                        if(typ == 'U')
                        {
                            mot = (new StringBuilder(String.valueOf(mot))).append(getletter(k, j).toUpperCase()).toString();
                        }
                        if(typ == 'L')
                        {
                            mot = (new StringBuilder(String.valueOf(mot))).append(getletter(k, j).toLowerCase()).toString();
                        }
                        if(typ == 'N')
                        {
                            mot = (new StringBuilder(String.valueOf(mot))).append(getletter(k, j)).toString();
                        }
                        continue;
                    }
                }
                catch(NumberFormatException e)
                {
                    return null;
                }
                return null;
            }
            typ = chaine[i].charAt(1);
            if(typ == 'E')
            {
                mot = (new StringBuilder(String.valueOf(mot))).append(" ").toString();
            } else
            {
                mot = (new StringBuilder(String.valueOf(mot))).append(chaine[i].charAt(1)).toString();
            }
        }

        return mot;
    }

    private static String getletter(int i, int j)
    {
        String mot = "";
        String tble[][] = {
            {
                "A", "B", "C", "1", "D", "E"
            }, {
                "F", "2", "G", "H", "3", "I"
            }, {
                "J", "K", "4", "5", "L", "M"
            }, {
                "N", "O", "6", "7", "P", "Q"
            }, {
                "R", "8", "S", "T", "9", "U"
            }, {
                "V", "W", "X", "0", "Y", "Z"
            }
        };
        mot = tble[i][j];
        return mot;
    }

    private static String typeChar(String s)
    {
        String type = "";
        Pattern pU = Pattern.compile("[A-Z]");
        Pattern pL = Pattern.compile("[a-z]");
        Pattern pN = Pattern.compile("[0-9]");
        Matcher matcherU = pU.matcher(s);
        Matcher matcherL = pL.matcher(s);
        Matcher matcherN = pN.matcher(s);
        if(matcherU.find())
        {
            type = "U";
        }
        if(matcherL.find())
        {
            type = "L";
        }
        if(matcherN.find())
        {
            type = "N";
        }
        if(s.equals(" "))
        {
            type = "E";
        }
        return type;
    }

    public static long getdifferenceJours(Date date1, Date date2)
    {
        long durationInDays = 0L;
        long durationInSec = 0L;
        if(date1 != null && date2 != null)
        {
            if(date2.after(date1))
            {
                durationInSec = (date2.getTime() - date1.getTime()) / 1000L;
            }
            durationInDays = durationInSec / 0x15180L;
        }
        return durationInDays;
    }

    public static Date validerDate(String myDate)
    {
        Date dateValide = null;
        String tDate[] = myDate.split("/");
        boolean verify=true;
        try
        {
            int day = Integer.valueOf(tDate[0]).intValue();
            int month = Integer.valueOf(tDate[1]).intValue();
            int year = Integer.valueOf(tDate[2]).intValue();
            
            if(month<0 || month>12)
            	verify=false;
            
            switch (month) {
            
            case 2:
				if(day>29)
					verify=false;
				break;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if(day>31)
					verify=false;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
			if(day>30)
				verify=false;
				break;
			}
            if(verify)
            	dateValide = toDate(year, month, day);
        }
        catch(Exception e)
        {
            dateValide = null;
        }
        return dateValide;
    }

    public static Date validerHeure(String myHour)
    {
        Date dateValide = null;
        String tDate[] = myHour.split(":");
        try
        {
            int hour = Integer.valueOf(tDate[0]).intValue();
            int minute = Integer.valueOf(tDate[1]).intValue();
            int seconde = Integer.valueOf(tDate[2]).intValue();
            dateValide = toHour(hour, minute, seconde);
        }
        catch(Exception e)
        {
            dateValide = null;
        }
        return dateValide;
    }

    public static Date toHour(int hour, int minute, int seconde)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(10, hour);
        cal.set(12, minute);
        cal.set(13, seconde);
        return cal.getTime();
    }

   

    public static void sendEmail(String serverSmtp,String user,String origin,String pwdOrgine,String destination,String txtMsg,String objectMsg) {
    	
		
		  Properties properties = new Properties();
		  properties.setProperty("mail.transport.protocol", "smtp");
		  properties.setProperty("mail.smtp.host", serverSmtp);
		  properties.setProperty("mail.smtp.user", origin);
		  properties.setProperty("mail.from", origin);
		  properties.setProperty("mail.smtp.starttls.enable", "true");
		  properties.setProperty("mail.smtp.ssl.trust", serverSmtp);
		  
		  //properties.setProperty("mail.session.mail.smtp.port", "465");
		  
		  properties.setProperty("mail.smtp.auth", "true"); try { InternetAddress[]
		  listeAdresse; Session session = Session.getInstance(properties); MimeMessage
		  message = new MimeMessage(session); message.setSubject(objectMsg);
		  message.setContent(txtMsg, "text/html");
		  message.addRecipients(Message.RecipientType.TO, destination); Transport
		  transport = null; transport = session.getTransport("smtp");
		  transport.connect(origin, pwdOrgine); InternetAddress adresse = new
		  InternetAddress(destination); listeAdresse= new InternetAddress[1];
		  listeAdresse[0]=adresse;
		  
		  transport.sendMessage(message,listeAdresse); if (transport != null) {
		  transport.close(); } System.out.println("Sent message successfully...."); }
		  catch (MessagingException mex) { mex.printStackTrace(); }
		 
	    
    }
    public static String[] changeDoubleInString(String mntStrng)
    {
        double mnt;
        String mntRetuned[];
        mnt = 0.0D;
        mntRetuned = new String[2];
        try
        {
            mnt = Double.valueOf(mntStrng.replace(" ", "").replace(",", ".")).doubleValue();
            mntRetuned[0] = (new StringBuilder()).append(mnt).toString();
            
        }
        catch(Exception e)
        {
            mnt = 0.0D;
            mntRetuned[0] = "0";
        }
        if(mnt < 0.0D)
        {
            mntRetuned[0] = "0";
        }
        if(mnt > 0.0D)
        {
            mntRetuned[1] = TraitementMontant.getMontantFormate(mnt, 0);
            mntRetuned[0] = (new StringBuilder()).append(Double.valueOf(mntRetuned[1].replace(" ", "").replace(",", ".").trim())).toString();
        } else
        {
            mntRetuned[0] = "0";
            mntRetuned[1] = "";
        }
       
        if(mnt < 0.0D)
        {
            mntRetuned[0] = "0";
        }
        if(mnt > 0.0D)
        {
            mntRetuned[1] = TraitementMontant.getMontantFormate(mnt, 0);
            mntRetuned[0] = (new StringBuilder()).append(Double.valueOf(mntRetuned[1].replace(" ", "").replace(",", ".").trim())).toString();
        } else
        {
            mntRetuned[0] = "0";
            mntRetuned[1] = "";
        }
      
        if(mnt < 0.0D)
        {
            mntRetuned[0] = "0";
        }
        if(mnt > 0.0D)
        {
            mntRetuned[1] = TraitementMontant.getMontantFormate(mnt, 0);
            mntRetuned[0] = (new StringBuilder()).append(Double.valueOf(mntRetuned[1].replace(" ", "").replace(",", ".").trim())).toString();
        } else
        {
            mntRetuned[0] = "0";
            mntRetuned[1] = "";
        }
        return mntRetuned;
    }

    public static String[] changeIntInString(String nombreStrng)
    {
        int nombre;
        String mntRetuned[];
        nombre = 0;
        mntRetuned = new String[2];
        try
        {
            nombre = Integer.parseInt(nombreStrng.replace(" ", "").replace(",", "."));
            mntRetuned[0] = (new StringBuilder()).append(nombre).toString();
            
        }
        catch(Exception e)
        {
            nombre = 0;
            mntRetuned[0] = "0";
        }
        if(nombre < 0)
        {
            mntRetuned[0] = "0";
        }
        if(nombre > 0)
        {
            mntRetuned[1] = TraitementMontant.getMontantFormate(nombre, 0);
            mntRetuned[0] = (new StringBuilder()).append(Integer.parseInt(mntRetuned[1].replace(" ", "").replace(",", ".").trim())).toString();
        } else
        {
            mntRetuned[0] = "0";
            mntRetuned[1] = "";
        }
       
        if(nombre < 0)
        {
            mntRetuned[0] = "0";
        }
        if(nombre > 0)
        {
            mntRetuned[1] = TraitementMontant.getMontantFormate(nombre, 0);
            mntRetuned[0] = (new StringBuilder()).append(Integer.parseInt(mntRetuned[1].replace(" ", "").replace(",", ".").trim())).toString();
        } else
        {
            mntRetuned[0] = "0";
            mntRetuned[1] = "";
        }
        
        if(nombre < 0)
        {
            mntRetuned[0] = "0";
        }
        if(nombre > 0)
        {
            mntRetuned[1] = TraitementMontant.getMontantFormate(nombre, 0);
            mntRetuned[0] = (new StringBuilder()).append(Integer.parseInt(mntRetuned[1].replace(" ", "").replace(",", ".").trim())).toString();
        } else
        {
            mntRetuned[0] = "0";
            mntRetuned[1] = "";
        }
        return mntRetuned;
    }

    public static String FormterNombre(String txt, int length)
    {
        String p = String.valueOf(length);
        if(txt.trim().equals(""))
        {
            txt = "00000000";
        } else
        if(txt.trim().length() < length)
        {
            txt = String.format((new StringBuilder("%")).append(p).append("s").toString(), new Object[] {
                txt.trim()
            }).replace(' ', '0');
        }
        return txt;
    }

    public static void afficherMessage(String titre, String message)
    {
        FacesMessage msg = new FacesMessage(titre, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public static void afficherDeleteMessage()
    {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Info", "il faut préciser l'élément à supprimer");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public static void afficherMessage(String titre, String message, javax.faces.application.FacesMessage.Severity severity)
    {
        FacesMessage msg = new FacesMessage(severity, titre, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static String getExtension(FileUploadEvent file)
    {
        String extension = "";
        try
        {
            if(file != null)
            {
                if(file.getFile().getFileName().contains(".docx"))
                {
                    extension = ".docx";
                } else
                if(file.getFile().getFileName().contains(".doc"))
                {
                    extension = ".doc";
                } else
                if(file.getFile().getFileName().contains(".xlsx"))
                {
                    extension = ".xlsx";
                } else
                if(file.getFile().getFileName().contains(".xls"))
                {
                    extension = ".xls";
                } else
                if(file.getFile().getFileName().contains(".csv"))
                {
                    extension = ".csv";
                } else
                if(file.getFile().getFileName().contains(".jpg"))
                {
                    extension = ".jpg";
                } else
                if(file.getFile().getFileName().contains(".gif"))
                {
                    extension = ".gif";
                } else
                if(file.getFile().getFileName().contains(".png"))
                {
                    extension = ".png";
                } else
                if(file.getFile().getFileName().contains(".jpeg"))
                {
                    extension = ".jpeg";
                } else
                if(file.getFile().getFileName().contains(".pdf"))
                {
                    extension = ".pdf";
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return extension;
    }

    public static void creerRepertoire(String nom)
    {
        try
        {
            ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            String url = (new StringBuilder(String.valueOf(servletContext.getRealPath("/resources")))).append("\\").append(nom).toString();
            File directory = new File(url);
            if(!directory.exists())
            {
                directory.mkdir();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static String creerFichier(FileUploadEvent event, String repertoire, String nomFichier)
    {
        String url = "";
        try
        {
            InputStream in = event.getFile().getInputstream();
            ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            url = (new StringBuilder(String.valueOf(servletContext.getRealPath("/resources")))).append("\\").append(repertoire).append("\\").append(nomFichier).toString();
            OutputStream out = new FileOutputStream(new File(url));
            int read = 0;
            byte bytes[] = new byte[1024];
            while((read = in.read(bytes)) != -1) 
            {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return url;
    }

    public static String getURL(String repertoire, String nomFichier)
    {
        String url = "";
        try
        {
            ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            url = (new StringBuilder(String.valueOf(servletContext.getRealPath("/resources")))).append("\\").append(repertoire).append("\\").append(nomFichier).toString();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return url;
    }

    public static int getYearFromDate(Date date)
    {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    public static String HeureValide(String heure)
    {
        String hour = "";
        String H = heure.split(":")[0].replaceAll("_", "");
        String M = heure.split(":")[1].replaceAll("_", "");
        String S = heure.split(":")[2].replaceAll("_", "");
        int h = 0;
        int m = 0;
        int s = 0;
        if(H.length() == 1)
        {
            H = (new StringBuilder("0")).append(H).toString();
        }
        if(M.length() == 1)
        {
            M = (new StringBuilder("0")).append(M).toString();
        }
        if(S.length() == 1)
        {
            S = (new StringBuilder("0")).append(S).toString();
        }
        heure = (new StringBuilder(String.valueOf(H))).append(":").append(M).append(":").append(S).toString();
        if(heure != null)
        {
            if(H.equals(""))
            {
                h = 0;
                H = "0";
            } else
            {
                h = Integer.parseInt(H);
                if(h > 24)
                {
                    return null;
                }
            }
            if(M.equals(""))
            {
                m = 0;
                M = "0";
            } else
            {
                m = Integer.parseInt(M);
                if(m > 59)
                {
                    h = m / 60;
                    m -= 60;
                    H = String.valueOf(h);
                }
            }
            if(S.equals(""))
            {
                s = 0;
                S = "0";
            } else
            {
                s = Integer.parseInt(S);
                if(s > 59)
                {
                    m += s / 60;
                    s -= 60;
                    M = String.valueOf(m);
                    S = String.valueOf(s);
                }
            }
            if(H.length() == 1)
            {
                H = (new StringBuilder("0")).append(H).toString();
            }
            if(M.length() == 1)
            {
                M = (new StringBuilder("0")).append(M).toString();
            }
            if(S.length() == 1)
            {
                S = (new StringBuilder("0")).append(S).toString();
            }
            hour = (new StringBuilder(String.valueOf(H))).append(":").append(M).append(":").append(S).toString();
        }
        return hour;
    }

    public static String differenceHeure(String heureDeb, String heureFin)
    {
        String valeur = "00:00:00";
        try
        {
            int minutes = 0;
            int hours = 0;
            int sec = 0;
            int hDeb = Integer.valueOf(heureDeb.split(":")[0]).intValue();
            int mDeb = Integer.valueOf(heureDeb.split(":")[1]).intValue();
            int sDeb = Integer.valueOf(heureDeb.split(":")[2]).intValue();
            int hFin = Integer.valueOf(heureFin.split(":")[0]).intValue();
            int mFin = Integer.valueOf(heureFin.split(":")[1]).intValue();
            int sFin = Integer.valueOf(heureFin.split(":")[2]).intValue();
            if(hFin == 0)
            {
                hFin = 24;
            }
            if(hDeb == 0)
            {
                hDeb = 24;
            }
            if(sFin >= sDeb)
            {
                sec = sFin - sDeb;
            } else
            {
                sec = (60 + sFin) - sDeb;
                if(mFin > 0)
                {
                    mFin--;
                }
                if(mFin == 0)
                {
                    mFin = 60;
                    hFin--;
                }
            }
            if(mFin >= mDeb)
            {
                minutes = mFin - mDeb;
            } else
            {
                minutes = (60 + mFin) - mDeb;
                hFin--;
            }
            hours = hFin - hDeb;
            valeur = HeureValide((new StringBuilder(String.valueOf(hours))).append(":").append(minutes).append(":").append(sec).toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return valeur;
    }

    public static String sommHeure(String heureDeb, String heureFin)
    {
        String valeur = "00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try
        {
            Date deb = sdf.parse(heureDeb);
            Date fin = sdf.parse(heureFin);
            int minutes = 0;
            int hours = 0;
            int sec = 0;
            sec += fin.getSeconds() + deb.getSeconds();
            if(sec >= 60)
            {
                minutes = sec / 60;
                sec -= 60;
            }
            minutes += fin.getMinutes() + deb.getMinutes();
            if(minutes >= 60)
            {
                hours = minutes / 60;
                minutes -= 60;
            }
            hours += fin.getHours() + deb.getHours();
            valeur = (new StringBuilder(String.valueOf(hours))).append(":").append(minutes).append(":").append(sec).toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return valeur;
    }

    public static String heuresPreste(int heure, int min, int sec)
    {
        String valeur = "";
        if(sec > 59)
        {
            min += sec / 60;
            sec -= 60;
        }
        if(min > 59)
        {
            heure += min / 60;
            min -= 60;
        }
        valeur = (new StringBuilder(String.valueOf(heure))).append(" Heures ").append(min).append(" Min ").append(sec).append(" Sec").toString();
        return valeur;
    }

    public static String getElementFormule(String formule)
    {
        String formResult = "";
        String formModify = "";
        formule = formule.replaceAll(" ", "");
        formModify = formule.replace('(', ' ');
        formModify = formModify.replace(')', ' ');
        formModify = formModify.replaceAll(" ", "");
        formResult = formModify.replace('+', ' ');
        formResult = formResult.replace('-', ' ');
        formResult = formResult.replace('/', ' ');
        formResult = formResult.replace('*', ' ');
        return formResult;
    }

    public static double getFormulaValue(String formule)
    {
        double valeur = 0.0D;
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try
        {
            valeur = Double.valueOf(engine.eval(formule).toString()).doubleValue();
        }
        catch(ScriptException e)
        {
            e.printStackTrace();
        }
        return valeur;
    }

    public static PdfPCell getImmageCellule(String text, int colspan, int border)
    { 
        PdfPCell colonne = null;
        Image imgCode = null;
        BarcodePDF417 code = new BarcodePDF417();
        code.setText(text);
        try
        {
            imgCode = code.getImage();
            colonne = new PdfPCell();
            colonne.setColspan(colspan);
            colonne.setVerticalAlignment(2);
            colonne.setHorizontalAlignment(2);
            colonne.setBorder(border);
            colonne.addElement(imgCode);
        }
        catch(BadElementException e)
        {
            e.printStackTrace();
        }
        return colonne;
    }

    public static PdfPCell getCellule(String label, Font font, int alignmentVertical, int alignmentHorizontal, int border, int colspan, Color backgroundColor, Color bordercolor, 
            float borderwidth, float paddingWidth)
    {
        Paragraph par = new Paragraph(label, font);
        PdfPCell cell = new PdfPCell(par);
        if(border > 0)
        {
            cell.setBorder(border);
            cell.setBorderWidth(borderwidth);
            if(bordercolor != null)
            {
                cell.setBorderColor(bordercolor);
            }
        } else
        {
            cell.setBorder(0);
        }
        cell.setVerticalAlignment(alignmentVertical);
        cell.setHorizontalAlignment(alignmentHorizontal);
        cell.setColspan(colspan);
        if(backgroundColor != null)
        {
            cell.setBackgroundColor(backgroundColor);
        }
        cell.setPadding(paddingWidth);
        return cell;
    }

    public static PdfPCell getCellule(Element element, int alignmentVertical, int alignmentHorizontal, int border, int colspan, float borderwidth, Color bordercolor, float cellPadding)
    {
        PdfPCell cell = new PdfPCell();
        cell.addElement(element);
        if(border > 0)
        {
            cell.setBorder(border);
            cell.setBorderWidth(borderwidth);
            if(bordercolor != null)
            {
                cell.setBorderColor(bordercolor);
            }
        } else
        {
            cell.setBorder(0);
        }
        cell.setVerticalAlignment(alignmentVertical);
        cell.setHorizontalAlignment(alignmentHorizontal);
        cell.setColspan(colspan);
        cell.setPadding(cellPadding);
        return cell;
    }

    public static boolean testerPassword(String password)
    {
        boolean isValide = false;
        if(password != null && !password.replace(" ", "").trim().equals("") && password.replace(" ", "").trim().length() >= 8)
        {
            Pattern pattern1 = Pattern.compile("\\W");
            Pattern pattern2 = Pattern.compile("[A-Z]");
            Pattern pattern3 = Pattern.compile("[0-9]");
            Matcher matcher = pattern1.matcher(password.replace(" ", "").trim());
            if(matcher.find())
            {
                matcher = pattern2.matcher(password.replace(" ", "").trim());
                if(matcher.find())
                {
                    matcher = pattern3.matcher(password.replace(" ", "").trim());
                    if(matcher.find())
                    {
                        isValide = true;
                    }
                }
            }
        }
        return isValide;
    }
    
    
}
