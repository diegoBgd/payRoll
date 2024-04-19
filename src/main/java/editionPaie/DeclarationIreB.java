package editionPaie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
import classesPaie.BaremeIPRC;
import classesPaie.CotisationC;
import classesPaie.DetailBaremeC;
import classesPaie.HelperC;
import classesPaie.HelperItext;
import classesPaie.ItextFooterHelper;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@ManagedBean
@ViewScoped
public class DeclarationIreB {

	private Date dateDeb,dateFin;
	private String datePrintDeb;
    private String datePrintFin;
    private List<BaremeIPRC> listBareme;
    
	public DeclarationIreB() 
	{
		
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

	public List<BaremeIPRC> getListBareme() {
		return listBareme;
	}

	public void setListBareme(List<BaremeIPRC> listBareme) {
		this.listBareme = listBareme;
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
	public void printDeclaration()
    {
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
            doc.add(getDeclarationTable());
            doc.add(tableAmande());
            doc.close();
            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
            res.setHeader("Cache-Control", "Max-age=100");
            res.setContentType("application/pdf");
            res.setHeader("content-disposition", (new StringBuilder("inline;filename=DeclarationIRE.pdf").toString()));
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
	        
	        cell = HelperItext.getPdfCell("IMPOT SUR REVENUS D'EMPLOI (IRE) ", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 15, 2, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("\n", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        return table;
	    }

	private PdfPTable getDeclarationTable() throws DocumentException{
			//List<EmployeC>listEmploye;
		    PdfPTable table = null;
	        table = new PdfPTable(6);
	        PdfPCell cell = new PdfPCell();
	        table.setWidthPercentage(100F);
	        cell.setBorder(0);
	        int largeurCollones[] = {
	            5,25,10,10,10,10
	        };
	        
	        int nbrEmploye=0;
	        double baseMontant=0,montantCotisation=0;
	        table.setWidths(largeurCollones);
	        chargementParametre();
	      
	        cell = HelperItext.getPdfCell("Ligne", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 3, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("Tranche", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("Nombre d'employés", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("Base imposable", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("Taux", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("Impôt dû ", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 15, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        CotisationC cot=null;
	        
	      
	        int i=0;double deb=0,fin=0;
	        for (BaremeIPRC brm : listBareme) 
	        {
	        	cot=FichierBaseDAO.getInstance().getImpot(1);
	        	
	        	 if(cot!=null)
	        	 {
	        	switch(brm.getTypeEmploye())
	        	{
	        	case 1:
	        		cell = HelperItext.getPdfCell("EMPLOYES PERMANENTS", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 12, 6, BaseColor.WHITE, BaseColor.BLACK, 1);
		     	    table.addCell(cell);		     	
	        		break;
	        	case 2:
	        		cell = HelperItext.getPdfCell("EMPLOYES OCCASIONNELS", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 12, 6, BaseColor.WHITE, BaseColor.BLACK, 1);
		     	    table.addCell(cell);	
	        		break;
	        	case 3:
	        		cell = HelperItext.getPdfCell("DEUXIEME EMPLOYE", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 12, 6, BaseColor.WHITE, BaseColor.BLACK, 1);
		     	    table.addCell(cell);	
	        		break;
	        	case 4:
	        		cell = HelperItext.getPdfCell("EMPLOYES PERMANENTS", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 12, 6, BaseColor.WHITE, BaseColor.BLACK, 1);
		     	    table.addCell(cell);	
	        		break;
	        	case 5:
	        		cell = HelperItext.getPdfCell("EMPLOYES DES O.N.G ETRANGERES ", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 12, 6, BaseColor.WHITE, BaseColor.BLACK, 1);
		     	    table.addCell(cell);	
	        		break;
	        	}
	           
	        	deb=0;fin=0;
	        	for (DetailBaremeC det : brm.getListDetailBareme()) 
	        	{		
	        		switch(brm.getType())
	        		{
	        		case 1:
	        			deb=det.getBorneDebut();
		        		fin=det.getBorneFin();
	        			break;
	        		case 2:
	        			
			        		deb=det.getBorneDebut()/12;
			        		fin=det.getBorneFin()/12;
	        			break;
			        	
	        		}
	              	
	        		nbrEmploye=FactoryDAO.getInstance().getListEmployeParBareme(deb, fin,brm.getTypeEmploye(),cot.getId(),dateDeb,dateFin);
	        		baseMontant=FactoryDAO.getInstance().getBaseParBareme(deb, fin,brm.getTypeEmploye(),cot.getId(),dateDeb,dateFin);
	        		montantCotisation=FactoryDAO.getInstance().getMontantParBareme(deb, fin,brm.getTypeEmploye(),cot.getId(),dateDeb,dateFin);
	        		
	        		i++;
	        		
	        		
	        		cell = HelperItext.getPdfCell(""+i, FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 3, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	     	        table.addCell(cell);
	     	       if(det.getBorneFin()>0)
	     	       {
	        		cell = HelperItext.getPdfCell(HelperC.decimalNumber(det.getBorneDebut(), 0, true)+"-"+HelperC.decimalNumber(det.getBorneFin(), 0, true)+" FBU", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 2, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	    	        table.addCell(cell);
	     	       }
	     	       else
	     	       {
	     	    	  cell = HelperItext.getPdfCell("Plus de "+HelperC.decimalNumber(det.getBorneDebut(), 0, true)+" FBU", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 2, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
		    	      table.addCell(cell);
		     	   
	     	       }
	    	        cell = HelperItext.getPdfCell(HelperC.decimalNumber(nbrEmploye, 0, true), FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 3, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	    	        table.addCell(cell);
	    	        
	    	        cell = HelperItext.getPdfCell(HelperC.decimalNumber(baseMontant, 0, true), FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 3, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	    	        table.addCell(cell);
	    	        
	    	        cell = HelperItext.getPdfCell(HelperC.decimalNumber(det.getPourcentage(), 0, true)+"%", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 3, 7, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	    	        table.addCell(cell);
	    	        
	    	        cell = HelperItext.getPdfCell(HelperC.decimalNumber(montantCotisation, 0, true), FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 3, 15, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	    	        table.addCell(cell);
	        		
	    	      
	    	        nbrEmploye=0;
	        		baseMontant=0;
	        		montantCotisation=0;
	        		
	        	}
	        		        	
			}
	        }
	        
	        cell = HelperItext.getPdfCell("\n", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("\n", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	   
	        return table;
	}
	private PdfPTable tableAmande()
	        throws DocumentException, IOException
	    {
	        PdfPTable table = null;
	        table = new PdfPTable(2);
	        PdfPCell cell = new PdfPCell();
	        table.setWidthPercentage(100F);
	        cell.setBorder(0);
	        int largeurCollones[] = {
	           50,50
	        };
	        table.setWidths(largeurCollones);
	       
	        cell = HelperItext.getPdfCell("PARTIE RESERVE A L'ADMINISTRATION FISCALE\n", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 2, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell(" ", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	    
	   
	        cell = HelperItext.getPdfCell("Pénalités de déclaration (BIF) \n", FontFactory.getFont("Times-Roman", 10F, 0, BaseColor.BLACK), 1, 2, 13, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 10F, 0, BaseColor.BLACK), 1, 2, 9, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell(" ", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 12, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	    
	        cell = HelperItext.getPdfCell(" ", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 8, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	     
	        cell = HelperItext.getPdfCell("Base", FontFactory.getFont("Times-Roman", 10F, 1, BaseColor.BLACK), 1, 2, 12, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("----------------------------", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 2, 8, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("Amande fixe", FontFactory.getFont("Times-Roman", 10F, 1, BaseColor.BLACK), 1, 2, 12, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("----------------------------", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 2,8, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("Total dû", FontFactory.getFont("Times-Roman", 10F, 1, BaseColor.BLACK), 1, 2, 12, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	       
	        cell = HelperItext.getPdfCell("----------------------------", FontFactory.getFont("Times-Roman", 10F, 1, BaseColor.BLACK), 1, 2, 8, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	      
	        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 10F, 1, BaseColor.BLACK), 1, 2, 14, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell(" ", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 10, 0, BaseColor.WHITE, BaseColor.BLACK, 1);
	        table.addCell(cell);
	        return table;
	    }
	private void chargementParametre(){
		listBareme=FichierBaseDAO.getInstance().getListBareme(true);
	}
	
	
}
