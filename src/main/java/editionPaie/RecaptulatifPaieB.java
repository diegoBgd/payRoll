package editionPaie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import classesPaie.BulletinPaieC;
import classesPaie.BulletinPrimeC;
import classesPaie.CotisationC;
import classesPaie.DeductionC;
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
public class RecaptulatifPaieB {

	private String datePrintDeb,datePrintFin;
	 private Date dateDeb;
	    private Date dateFin;
	  Base userFonction;
	  HttpSession session;
	  HttpServletRequest request;
	  OperateurC operateur;
	  ExerciceC exercice;
	  DroitC droit;
	 List<PrimeIndemniteC> listPrime;
	 List<CotisationC> listeCotisation;
	 List<DeductionC> listeDeduction;
	 List<BulletinPaieC> listBulletin;
	 
	public RecaptulatifPaieB() {
		session = HelperC.getSession();
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
            
        }
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
    
    private void chargerPrime()
    {
    	listPrime=FichierBaseDAO.getInstance().getAllPrimeIndemnite();
    }
    private void chargerCotisation(){
    	
    	listeCotisation=FichierBaseDAO.getInstance().getAllCotisation();
    }
    private void chargerDeduction(){
    	
    	listeDeduction=FichierBaseDAO.getInstance().getAllDeduction();
    }
    
 public void printPaie(){
    	
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
            image.scaleAbsolute(100.0F,100.0F);
            //image.scaleAbsoluteWidth(180F);
            doc.add(image);
            writer.setPageEvent(new ItextFooterHelper(new Phrase("Produit GATECH", new Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 8F, 0))));
            
            doc.add(pageHeader());
            doc.add(getTableRecap());
           
            doc.close();
            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
            res.setHeader("Cache-Control", "Max-age=100");
            res.setContentType("application/pdf");
            res.setHeader("content-disposition", (new StringBuilder("inline;filename=RecaptulatifPaie.pdf").toString()));
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
	        
	        cell = HelperItext.getPdfCell("RECAPTULATIF PAIE", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("\n", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        return table;
	    }
 		private PdfPTable getTableRecap()
 	        throws DocumentException
 	    {
 	        PdfPTable tabInfo = new PdfPTable(3);
 	        int widthsInfo[] = {
 	           50,25,25
 	        };
 	        tabInfo.setWidthPercentage(105F);
 	        tabInfo.setWidths(widthsInfo);
 	        Paragraph p = null;
 	        PdfPCell cell = new PdfPCell();
 	        
 	       double montantPrm=0,montantCot=0,montantDed=0,montantCred=0,
 	    		   montantHs=0,montantNet=0,montantAlc=0,montantLog=0,
 	    		   montantAvance,montantBase=0,totalBrut=0,totalCot=0,
 	    		   totalDed=0,totalNet=0;
 	        
 	       chargerPrime();
 	       chargerCotisation();
 	       chargerDeduction();
	       
 	      p = new Paragraph();
          p.add(new Chunk("Libell�", FontFactory.getFont("Times", 10F, 1)));
          p.setAlignment(1);
          cell = HelperItext.getCellule(p, 1, 0, 15, 0, 0.5F, 3F);
          tabInfo.addCell(cell);
          
          p = new Paragraph();
          p.add(new Chunk("A payer", FontFactory.getFont("Times", 10F, 1)));
          p.setAlignment(1);
          cell = HelperItext.getCellule(p, 1, 0, 11, 0, 0.5F, 3F);
          tabInfo.addCell(cell);
          
          p = new Paragraph();
          p.add(new Chunk("A d�duire", FontFactory.getFont("Times", 10F, 1)));
          p.setAlignment(1);
          cell = HelperItext.getCellule(p, 1, 0, 11, 0, 0.5F, 3F);
          tabInfo.addCell(cell);
          tabInfo.setHeaderRows(1);
          
          montantBase=FactoryDAO.getInstance().getTotalNet(0, dateDeb, dateFin);
          
          if(montantBase>0)
          {
        	  p = new Paragraph();
              p.add(new Chunk("Salaire Normal", FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(0);
              cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                 
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(montantBase, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                 
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
              
              totalBrut+=montantBase;
          }
          
          montantAlc=FactoryDAO.getInstance().getTotalAllocationFm(0, dateDeb, dateDeb);
          
          if(montantAlc>0)
          {
        	  p = new Paragraph();
              p.add(new Chunk("Allocations Familliales", FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(0);
              cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                 
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(montantAlc, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                 
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
              
              totalBrut+=montantAlc;
          }
          
          montantLog=FactoryDAO.getInstance().getTotalLogement(0, dateDeb, dateFin);
          
          if(montantLog>0)
          {
        	  p = new Paragraph();
              p.add(new Chunk("Allocations Logements", FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(0);
              cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                 
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(montantLog, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                 
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
              
              totalBrut+=montantLog;
          }
          montantHs=FactoryDAO.getInstance().getTotalHS(0, dateDeb, dateFin);
        
	       if(montantHs>0)
	       {
	    	   p = new Paragraph();
               p.add(new Chunk("Heures suppl�mentaires", FontFactory.getFont("Times", 8F, 0)));
               p.setAlignment(0);
               cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
               tabInfo.addCell(cell);
                  
               p = new Paragraph();
               p.add(new Chunk(HelperC.decimalNumber(montantHs, 0, true), FontFactory.getFont("Times", 8F, 0)));
               p.setAlignment(2);
               cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
               tabInfo.addCell(cell);
                  
               p = new Paragraph();
               p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
               p.setAlignment(2);
               cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
               tabInfo.addCell(cell);
               
               totalBrut+=montantHs;
	       }
 	        if(listPrime.size() > 0)
 	        {
 	           
 	            		for (PrimeIndemniteC Prm : listPrime) 
 	            		{
 	            			
 	            			montantPrm=FactoryDAO.getInstance().getTotalPrime(0, Prm.getId(), dateDeb, dateFin);
 	            			if(montantPrm>0)
 	            			{
 	            			p = new Paragraph();
   	                        p.add(new Chunk(Prm.getDesignation(), FontFactory.getFont("Times", 8F, 0)));
   	                        p.setAlignment(0);
   	                        cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
   	                        tabInfo.addCell(cell);
   	                        
   	                        p = new Paragraph();
	                        p.add(new Chunk(HelperC.decimalNumber(montantPrm, 0, true), FontFactory.getFont("Times", 8F, 0)));
	                        p.setAlignment(2);
	                        cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
	                        tabInfo.addCell(cell);
   	                        
   	                        p = new Paragraph();
   	                        p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
   	                        p.setAlignment(2);
   	                        cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
   	                        tabInfo.addCell(cell);
   	                        
   	                        totalBrut+=montantPrm;
 	            			}
 	            			
 	            	}            
 	            
 	            	
 	          
 	           
 	        }
 	        
 	        if(listeCotisation.size()>0)
 	        {
 	        	for (CotisationC cot : listeCotisation) {
 	        		montantCot=FactoryDAO.getInstance().getTotalCotisationSalarial(0, cot.getId(), dateDeb, dateFin);
 	        		
 	        		if(montantCot>0)
 	        		{
 	        			p = new Paragraph();
	                    p.add(new Chunk(cot.getDesignation(), FontFactory.getFont("Times", 8F, 0)));
	                    p.setAlignment(0);
	                    cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
	                    tabInfo.addCell(cell);
	                        
	                    p = new Paragraph();
                        p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
                        p.setAlignment(2);
                        cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
                        tabInfo.addCell(cell);
	                        
	                    p = new Paragraph();
	                    p.add(new Chunk(HelperC.decimalNumber(montantCot, 0, true), FontFactory.getFont("Times", 8F, 0)));
	                    p.setAlignment(2);
	                    cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
	                    tabInfo.addCell(cell);
	                    
	                    totalCot+=montantCot;
 	        		}
				}
 	        }
 	     
 	       montantNet=totalBrut-totalCot;
 	       
 	       if(montantNet!=0)
 	       {
 	    	  p = new Paragraph();
              p.add(new Chunk("Salaire Net", FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(0);
              cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                  
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(montantNet, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                  
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
 	       }
 	       
 	       if(listeDeduction.size()>0)
	        {
	        	for (DeductionC ded : listeDeduction) {
	        		montantDed=FactoryDAO.getInstance().getTotalDeduction(0, ded.getId(), dateDeb, dateFin);
	        		
	        		if(montantDed>0)
	        		{
	        			p = new Paragraph();
	                    p.add(new Chunk(ded.getDesignation(), FontFactory.getFont("Times", 8F, 0)));
	                    p.setAlignment(0);
	                    cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
	                    tabInfo.addCell(cell);
	                        
	                    p = new Paragraph();
                        p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
                        p.setAlignment(2);
                        cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
                        tabInfo.addCell(cell);
	                        
	                    p = new Paragraph();
	                    p.add(new Chunk(HelperC.decimalNumber(montantDed, 0, true), FontFactory.getFont("Times", 8F, 0)));
	                    p.setAlignment(2);
	                    cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
	                    tabInfo.addCell(cell);
	                    
	                    totalDed+=montantDed;
	        		}
				}
	        }
 	       montantAvance=FactoryDAO.getInstance().getTotalAvance(0, dateDeb, dateDeb);
 	       
 	       if(montantAvance>0)
 	       {
 	    	  p = new Paragraph();
              p.add(new Chunk("Avances sur salaire", FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(0);
              cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                  
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
                  
              p = new Paragraph();
              p.add(new Chunk(HelperC.decimalNumber(montantAvance, 0, true), FontFactory.getFont("Times", 8F, 0)));
              p.setAlignment(2);
              cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
              tabInfo.addCell(cell);
              
              totalDed+=montantAvance;
 	       }
 	       
 	       montantCred=FactoryDAO.getInstance().getTotalCredit(0, dateDeb, dateFin);
 	       
 	      if(montantCred>0)
	       {
	    	 p = new Paragraph();
             p.add(new Chunk("Cr�dit", FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(0);
             cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
                 
             p = new Paragraph();
             p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(2);
             cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
                 
             p = new Paragraph();
             p.add(new Chunk(HelperC.decimalNumber(montantCred, 0, true), FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(2);
             cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
             
             totalDed+=montantCred;
	       }
 	      
 	      totalNet=montantNet-totalDed;
 	      
 	      if(totalNet>0)
 	      {
 	    	 p = new Paragraph();
             p.add(new Chunk("Net � Payer", FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(0);
             cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
                 
             p = new Paragraph();
             p.add(new Chunk(HelperC.decimalNumber(totalNet, 0, true), FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(2);
             cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
                 
             p = new Paragraph();
             p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(2);
             cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
             
             
             p = new Paragraph();
             p.add(new Chunk("Virement", FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(0);
             cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
                 
             p = new Paragraph();
             p.add(new Chunk(HelperC.decimalNumber(0, 0, true), FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(2);
             cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
                 
             p = new Paragraph();
             p.add(new Chunk(HelperC.decimalNumber(totalNet, 0, true), FontFactory.getFont("Times", 8F, 0)));
             p.setAlignment(2);
             cell = HelperItext.getCellule(p, 1, 0, 10, 0, 0.5F, 3F);
             tabInfo.addCell(cell);
 	      }
 	      
 	    
 	      
 	        return tabInfo;
 	    }
}
