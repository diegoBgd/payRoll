package editionPaie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
import classesPaie.Base;
import classesPaie.BulletinPrimeC;
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.HelperItext;
import classesPaie.ItextFooterHelper;
import classesPaie.OperateurC;
import classesPaie.PrimeIndemniteC;
@ManagedBean
@ViewScoped
public class PrimeIndemniteEmployeB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5649740466960139282L;
	private List<SelectItem> listPrime;
	private List<EmployeC> listEmploye,listToPrint;
	private EmployeC employe;
	private int idPrime,order;
	private String datePrintDeb;
    private String datePrintFin;
    private Date dateDeb;
    private Date dateFin;
    private PrimeIndemniteC primeInd;
    private String code,codeEmploye,
                   nom,prenom,libelleEmploye;
    Base userFonction;
    HttpSession session;
    HttpServletRequest request;
    OperateurC operateur;
    ExerciceC exercice;
    DroitC droit;
    
	public PrimeIndemniteEmployeB() 
	{
		 session = HelperC.getSession();
	}

	public List<SelectItem> getListPrime() {
		return listPrime;
	}

	public void setListPrime(List<SelectItem> listPrime) {
		this.listPrime = listPrime;
	}

	public List<EmployeC> getListEmploye() {
		return listEmploye;
	}

	public void setListEmploye(List<EmployeC> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public List<EmployeC> getListToPrint() {
		return listToPrint;
	}

	public void setListToPrint(List<EmployeC> listToPrint) {
		this.listToPrint = listToPrint;
	}

	public EmployeC getEmploye() {
		return employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public int getIdPrime() {
		return idPrime;
	}

	public void setIdPrime(int idPrime) {
		this.idPrime = idPrime;
	}

	public String getDatePrintDeb() {
		return datePrintDeb;
	}

	public void setDatePrintDeb(String datePrintDeb) {
		this.datePrintDeb = datePrintDeb;
	}

	public String getDatePrintFin() {
		return datePrintFin;
	}

	public void setDatePrintFin(String datePrintFin) {
		this.datePrintFin = datePrintFin;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public PrimeIndemniteC getPrimeInd() {
		return primeInd;
	}

	public void setPrimeInd(PrimeIndemniteC primeInd) {
		this.primeInd = primeInd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeEmploye() {
		return codeEmploye;
	}

	public void setCodeEmploye(String codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLibelleEmploye() {
		return libelleEmploye;
	}

	public void setLibelleEmploye(String libelleEmploye) {
		this.libelleEmploye = libelleEmploye;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@PostConstruct
    private void init()
    {
        String codeOperateur = (String)session.getAttribute("operateur");
        String codeExercice = (String)session.getAttribute("exercice");
        operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
        exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
        if(operateur == null || exercice == null)
        {
            try
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("/payRoll/login.xhtml");
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
        
            userFonction = FichierBaseDAO.getInstance().getFonctionActive(operateur.getIdEmploye());
            if(userFonction != null)
            {
                droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), classesPaie.Constante.Role.bulletinPaie);
            }
            listPrime = new ArrayList<SelectItem>();
            chargementPrime();
            code="";nom="";prenom="";codeEmploye="";
            listToPrint=new ArrayList<EmployeC>();
        }
    }
	
	public void changeDateDeb()
    {
        if(getDatePrintDeb().replace("/", "").replace("_", "").trim().equals(""))
        {
            setDateDeb(null);
        } else
        {
            setDateDeb(HelperC.validerDate(getDatePrintDeb()));
            if(getDateDeb() == null)
            {
                setDatePrintDeb("");
            } else
            {
                setDatePrintDeb(HelperC.convertDate(getDateDeb()));
            }
        }
    }

    public void changeDateFin()
    {
        if(getDatePrintFin().replace("/", "").replace("_", "").trim().equals(""))
        {
            setDateFin(null);
        } else
        {
            setDateFin(HelperC.validerDate(getDatePrintFin()));
            if(getDateFin() == null)
            {
                setDatePrintFin("");
            } else
            {
                setDatePrintFin(HelperC.convertDate(getDateFin()));
            }
        }
    }
    
    private void chargementPrime(){
    	listPrime.add(new SelectItem(0,""));
    	for (PrimeIndemniteC prm : FichierBaseDAO.getInstance().getAllPrimeIndemnite()) 
    	{
    		listPrime.add(new SelectItem(prm.getId(),prm.getCode()+" || "+prm.getDesignation()));
		}
    }
    public void changePrime(ValueChangeEvent event)
    {
        idPrime = ((Integer)event.getNewValue()).intValue();
        primeInd = FichierBaseDAO.getInstance().getPrimeIndemnite(idPrime);
       
    }
    
    public void chargerEmploye(){
    	listEmploye=FactoryDAO.getInstance().getListEmploye(code, nom,false);
    }
    
    public void searchEmploye(){
    	listToPrint.clear();
    	
    	if(!codeEmploye.equals(""))
    	{
    		employe=FactoryDAO.getInstance().getEmploye(codeEmploye, false);
    		if(employe!=null)
    		{
    			libelleEmploye=employe.getNomPrenom();
    			listToPrint.add(employe);
    		}
    		else{
    			libelleEmploye="";
    			listToPrint.clear();
    		}
    	}
    	else{
    		employe=null;
    		libelleEmploye="";
    		listToPrint.clear();
    	}
    }
    
    public void onEmployeSelected(SelectEvent event)
    {
    	listToPrint.clear();
        employe = (EmployeC)event.getObject();
        if(employe != null)
        {
        	codeEmploye=employe.getCode();
        	libelleEmploye=employe.getNomPrenom();
        	listToPrint.add(employe);
        }
    	PrimeFaces.current().executeScript("PF('dlgEmploye').hide();");
    }
    
    public void printData(){
    	if(primeInd!=null)
    		printPrime();
    	else
    		 HelperC.afficherAttention("ATTENTION", "Il faut pr�ciser une prime ou indemnit� !");
    }
    
    private void printPrime(){
    	
    	try
        {
            Image image = null;
            Document doc = new Document(PageSize.A4);
            ByteArrayOutputStream docMem = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, docMem);
            doc.addAuthor("Crost Soft");
            doc.addProducer();
            doc.addCreationDate();
            doc.addTitle("Bulletin de paie");
            doc.open();
            ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            image = Image.getInstance((new StringBuilder(String.valueOf(servletContext.getRealPath("/resources")))).append("\\Images\\").append("logo.png").toString());
            //image.scaleAbsoluteHeight(90F);
            image.scaleAbsolute(100.0F,100.0F);
            doc.add(image);
            writer.setPageEvent(new ItextFooterHelper(new Phrase("Produit GATECH", new Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 8F, 0))));
            
            doc.add(pageHeader());
            doc.add(getTablePrime());
            //doc.add(tableAmande());
            doc.close();
            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
            res.setHeader("Cache-Control", "Max-age=100");
            res.setContentType("application/pdf");
            res.setHeader("content-disposition", (new StringBuilder("inline;filename=primeEmploye.pdf").toString()));
            ServletOutputStream out = res.getOutputStream();
            res.setContentLength(docMem.size());
            docMem.writeTo(out);
            out.flush();
            out.close();
            context.responseComplete();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

	private PdfPTable pageHeader()
        throws DocumentException, IOException
    {
        PdfPTable table = null;
        table = new PdfPTable(4);
        PdfPCell cell = new PdfPCell();
        table.setWidthPercentage(100F);
        cell.setBorder(0);
        int largeurCollones[] = {
            25,25,25,25
        };
        table.setWidths(largeurCollones);
       
        cell = HelperItext.getPdfCell(HelperC.convertDate(Calendar.getInstance().getTime()), FontFactory.getFont("Times-Roman", 8F, 0, BaseColor.BLACK), 1, 3, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        
        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        
        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        
        cell = HelperItext.getPdfCell(primeInd.getDesignation().toUpperCase(), FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        
        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        
        cell = HelperItext.getPdfCell("\n", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        
        return table;
    }
	private PdfPTable getTablePrime()
        throws DocumentException
    {
        PdfPTable tabInfo = new PdfPTable(5);
        int widthsInfo[] = {
            5,5,30, 10,10
        };
        tabInfo.setWidthPercentage(105F);
        tabInfo.setWidths(widthsInfo);
        Paragraph p = null;
        PdfPCell cell = new PdfPCell();
        
        double total = 0.0D,salarial=0,patronal=0,base=0,
        		totalGnle=0,totalSalarial=0,totalPatronal=0,
        		totalBase=0;
        
        chargementEmploye();
        
        if(listToPrint.size() > 0)
        {
            p = new Paragraph();
            p.add(new Chunk(" ", FontFactory.getFont("Times", 10F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            
            p = new Paragraph();
            p.add(new Chunk("Code", FontFactory.getFont("Times", 10F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            
            p = new Paragraph();
            p.add(new Chunk("Nom et prénom", FontFactory.getFont("Times", 10F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            
            p = new Paragraph();
            p.add(new Chunk("Date", FontFactory.getFont("Times", 10F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            
            p = new Paragraph();
            p.add(new Chunk("Montant", FontFactory.getFont("Times", 10F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 0, 15, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            
            tabInfo.setHeaderRows(1);
            List<BulletinPrimeC> listPrimeIndemnite;
            int j=0;
            
            
            for(EmployeC emp : listToPrint)
            {
            	listPrimeIndemnite=FactoryDAO.getInstance().getListBulletinDetailPrimeIndmnite(emp.getId(), idPrime, dateDeb, dateFin);
            	
            	
            	if(listPrimeIndemnite.size()>0)
            	{
            		for (BulletinPrimeC bPrm : listPrimeIndemnite) 
            		{
            			if(bPrm.getMontantPrime()>0)
            			{
            				j++;
            				
            			p = new Paragraph();
                        p.add(new Chunk(" "+j, FontFactory.getFont("Times", 8F, 0)));
                        p.setAlignment(0);
                        cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
                        tabInfo.addCell(cell);
                        
                        p = new Paragraph();
                        p.add(new Chunk(emp.getCode(), FontFactory.getFont("Times", 8F, 0)));
                        p.setAlignment(0);
                        cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
                        tabInfo.addCell(cell);
                        
                        p = new Paragraph();
                        p.add(new Chunk(emp.getNomPrenom(), FontFactory.getFont("Times", 8F, 0)));
                        p.setAlignment(0);
                        cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
                        tabInfo.addCell(cell);
                        
                        p = new Paragraph();
                        p.add(new Chunk(HelperC.changeDateFormate(bPrm.getDatePaie()), FontFactory.getFont("Times", 8F, 0)));
                        p.setAlignment(0);
                        cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
                        tabInfo.addCell(cell);
                        
                        p = new Paragraph();
                        p.add(new Chunk(HelperC.decimalNumber(bPrm.getMontantPrime(), 0, true), FontFactory.getFont("Times", 8F, 0)));
                        p.setAlignment(2);
                        cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
                        tabInfo.addCell(cell);
                        
                        totalGnle+=bPrm.getMontantPrime();
            			}
					}
            	}            
              
            }

            p = new Paragraph();
            p.add(new Chunk("Total ", FontFactory.getFont("Times", 9F,1)));
            p.setAlignment(0);
            cell = HelperItext.getCellule(p, 1, 0, 6, 4, 0.5F, 3F);
            tabInfo.addCell(cell);
           
            p = new Paragraph();
            p.add(new Chunk(HelperC.decimalNumber(totalGnle, 0, true), FontFactory.getFont("Times", 9F,1)));
            p.setAlignment(2);
            cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
           
        }
        return tabInfo;
    }


private void chargementEmploye()
{

	if(employe==null)
		listToPrint=FactoryDAO.getInstance().getAllEmploye(false,0);
}
}
