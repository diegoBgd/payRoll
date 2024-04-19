package editionPaie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
import classesPaie.DroitC;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.HelperItext;
import classesPaie.ItextFooterHelper;
import classesPaie.OperateurC;
import classesPaie.Tables;
import classesPaie.Tables.TableName;
@ManagedBean
@ViewScoped
public class ComparatifPaie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6347610658169944050L;
	private List<EmployeC>listEmploye;
	private List<String> listDatePaie,listDateCompare;
	private String datePaie,dateCompare,moiPaie,moiCompare;
	private List<Base>listPersonnel;
	private Date datePaiement,dateComparaison;
	private int order,typeMontant;
	Base userFonction;
    HttpSession session;
    HttpServletRequest request;
    OperateurC operateur;
    ExerciceC exercice;
    DroitC droit;
    
	public ComparatifPaie() {
		session = HelperC.getSession();
	}

	public List<EmployeC> getListEmploye() {
		return listEmploye;
	}

	public void setListEmploye(List<EmployeC> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public List<String> getListDatePaie() {
		return listDatePaie;
	}

	public void setListDatePaie(List<String> listDatePaie) {
		this.listDatePaie = listDatePaie;
	}

	public List<String> getListDateCompare() {
		return listDateCompare;
	}

	public void setListDateCompare(List<String> listDateCompare) {
		this.listDateCompare = listDateCompare;
	}

	public String getDatePaie() {
		return datePaie;
	}

	public void setDatePaie(String datePaie) {
		this.datePaie = datePaie;
	}

	public String getDateCompare() {
		return dateCompare;
	}

	public void setDateCompare(String dateCompare) {
		this.dateCompare = dateCompare;
	}
	
	public List<Base> getListPersonnel() {
		return listPersonnel;
	}

	public void setListPersonnel(List<Base> listPersonnel) {
		this.listPersonnel = listPersonnel;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public Date getDateComparaison() {
		return dateComparaison;
	}

	public void setDateComparaison(Date dateComparaison) {
		this.dateComparaison = dateComparaison;
	}

	public String getMoiPaie() {
		return moiPaie;
	}

	public void setMoiPaie(String moiPaie) {
		this.moiPaie = moiPaie;
	}

	public String getMoiCompare() {
		return moiCompare;
	}

	public void setMoiCompare(String moiCompare) {
		this.moiCompare = moiCompare;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getTypeMontant() {
		return typeMontant;
	}

	public void setTypeMontant(int typeMontant) {
		this.typeMontant = typeMontant;
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
            chargerDatePaie();
        }
    }
	
	private void chargerDatePaie(){
		listDatePaie=FactoryDAO.getInstance().getListDatePaie(exercice.getId(),0); 
		listDateCompare=FactoryDAO.getInstance().getListDatePaie(exercice.getId(),0);
		
		
	}
	public void printData(){
    	if(datePaie!=null)
    		printComparaison();
    	else
    		 HelperC.afficherAttention("ATTENTION", "Il faut pr�ciser la cotisation!");
    }
	public void changeDatePaie(ValueChangeEvent event)
    {
        datePaie = ((String)event.getNewValue()).toString();
        datePaiement=HelperC.stringTodate(datePaie);
        int month=HelperC.getMonthFromDate(datePaiement);
        moiPaie=HelperC.getMoisEnTouteLettre(month);
    }
	public void changeDateCompare(ValueChangeEvent event)
    {
        dateCompare = ((String)event.getNewValue()).toString();
        dateComparaison=HelperC.stringTodate(dateCompare);
       int month=HelperC.getMonthFromDate(dateComparaison);
       moiCompare=HelperC.getMoisEnTouteLettre(month);
    }
    
    private void printComparaison(){
    	
    	try
        {
            Image image = null;
            Document doc = new Document(PageSize.A4);
            ByteArrayOutputStream docMem = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, docMem);
            doc.addAuthor("Cost Soft");
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
            doc.add(getTableComaraison());
            //doc.add(tableAmande());
            doc.close();
            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
            res.setHeader("Cache-Control", "Max-age=100");
            res.setContentType("application/pdf");
            res.setHeader("content-disposition", (new StringBuilder("inline;filename=EtatComparatif.pdf").toString()));
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
	        
	        cell = HelperItext.getPdfCell("ETAT COMPARATIF DE LA PAIE", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        cell = HelperItext.getPdfCell("\n", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 1);
	        table.addCell(cell);
	        
	        return table;
	    }
    
    private PdfPTable getTableComaraison()
            throws DocumentException
        {
            PdfPTable tabInfo = new PdfPTable(7);
            int widthsInfo[] = {
                5,5,20, 10, 10, 10, 15
            };
            tabInfo.setWidthPercentage(105F);
            tabInfo.setWidths(widthsInfo);
            Paragraph p = null;
            PdfPCell cell = new PdfPCell();
            
           
            listPersonnel=FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.personnel));
            BulletinPaieC bulletinA=null;
            BulletinPaieC bulletinB=null;
            double bruteA=0,bruteB=0,difference=0;
            double totBruteA=0,totBrutB=0,totDifference=0;
            if(listPersonnel.size() > 0)
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
                p.add(new Chunk("Nom et pr�nom", FontFactory.getFont("Times", 10F, 1)));
                p.setAlignment(1);
                cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                
                p = new Paragraph();
                p.add(new Chunk("Brut "+moiPaie, FontFactory.getFont("Times", 10F, 1)));
                p.setAlignment(1);
                cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                
                p = new Paragraph();
                p.add(new Chunk("Brut "+moiCompare, FontFactory.getFont("Times", 10F,1)));
                p.setAlignment(1);
                cell = HelperItext.getCellule(p, 1, 2, 7, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                
                p = new Paragraph();
                p.add(new Chunk("Diff�rance ", FontFactory.getFont("Times", 10F, 1)));
                p.setAlignment(1);
                cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                
                p = new Paragraph();
                p.add(new Chunk("Observations", FontFactory.getFont("Times", 10F, 1)));
                p.setAlignment(1);
                cell = HelperItext.getCellule(p, 1, 0, 15, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);
            
                int j=0;
                
                listEmploye=FactoryDAO.getInstance().getAllEmploye(false,order);
                
			for (EmployeC emp : listEmploye) {
				bulletinA = FactoryDAO.getInstance().getBulletinPaie(
						emp.getId(), datePaiement, exercice.getId());
				bulletinB = FactoryDAO.getInstance().getBulletinPaie(
						emp.getId(), dateComparaison, exercice.getId());

				j++;
				if(bulletinA!=null)
					bruteA=bulletinA.getTotalBrute();
				if(bulletinB!=null)
					bruteB=bulletinB.getTotalBrute();
				
				difference=bruteA-bruteB;
				totBruteA+=bruteA;totBrutB+=bruteB;totDifference+=difference;
				
				p = new Paragraph();
				p.add(new Chunk(" " + j, FontFactory.getFont("Times", 8F, 0)));
				p.setAlignment(0);
				cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk(emp.getCode(), FontFactory.getFont("Times", 8F,
						0)));
				p.setAlignment(0);
				cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk(emp.getNomPrenom(), FontFactory.getFont(
						"Times", 8F, 0)));
				p.setAlignment(0);
				cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk(HelperC.decimalNumber(bruteA,
						0, true), FontFactory.getFont("Times", 8F, 0)));
				p.setAlignment(2);
				cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk(HelperC.decimalNumber(bruteB,
						0, true), FontFactory.getFont("Times", 8F, 0)));
				p.setAlignment(2);
				cell = HelperItext.getCellule(p, 1, 2, 6, 0, 0.5F, 3F);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk(HelperC.decimalNumber(difference, 0, true), FontFactory
						.getFont("Times", 8F, 0)));
				p.setAlignment(2);
				cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk("", FontFactory.getFont("Times", 8F, 1)));
				p.setAlignment(2);
				cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
				tabInfo.addCell(cell);

				bruteA=0;bruteB=0;difference=0;
			}
			
			p = new Paragraph();
			p.add(new Chunk("Total ", FontFactory.getFont("Times", 10F,1)));
			p.setAlignment(0);
			cell = HelperItext.getCellule(p, 1, 0, 6, 3, 0.5F, 3F);
			tabInfo.addCell(cell);

			
			p = new Paragraph();
			p.add(new Chunk(HelperC.decimalNumber(totBruteA,
					0, true), FontFactory.getFont("Times", 10F, 1)));
			p.setAlignment(2);
			cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
			tabInfo.addCell(cell);

			p = new Paragraph();
			p.add(new Chunk(HelperC.decimalNumber(totBrutB,
					0, true), FontFactory.getFont("Times", 10F, 1)));
			p.setAlignment(2);
			cell = HelperItext.getCellule(p, 1, 2, 6, 0, 0.5F, 3F);
			tabInfo.addCell(cell);

			p = new Paragraph();
			p.add(new Chunk(HelperC.decimalNumber(totDifference, 0, true), FontFactory
					.getFont("Times", 10F, 1)));
			p.setAlignment(2);
			cell = HelperItext.getCellule(p, 1, 0, 6, 0, 0.5F, 3F);
			tabInfo.addCell(cell);

			p = new Paragraph();
			p.add(new Chunk("", FontFactory.getFont("Times", 10F, 1)));
			p.setAlignment(2);
			cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
               
               
            }
            return tabInfo;
        }
	
}
