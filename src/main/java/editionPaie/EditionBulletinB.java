package editionPaie;

import classesPaie.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.net.InetAddress;
import java.util.*;
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

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
@ManagedBean
@ViewScoped
public class EditionBulletinB
    implements Serializable
{

    private static final long serialVersionUID = 0x3988f8de2f605b13L;
    private BulletinPaieC bulletin;
    private EmployeC employe;
    private String mois;
    private String profession;
    private String matricule;
    private String grade;
    private String categorie;
    private String fonction;
    private String code1,code2;
    private List<String> listDate;
    private CoordonneesSocieteC societe;
    private String datePrint;
    private int idEmp1,idEmp2;
    private List<SelectItem>listEmp1,listEmp2;
    private Date datePaie;
    HttpSession session = HelperC.getSession();
    ExerciceC exercice;
    List<EmployeC>listEmploye;
    ParametrageDecideurSignataireC signataire;
    public EditionBulletinB()
    {
    }

    public String getCategorie()
    {
        return categorie;
    }

    public void setCategorie(String categorie)
    {
        this.categorie = categorie;
    }

    public String getFonction()
    {
        return fonction;
    }

    public void setFonction(String fonction)
    {
        this.fonction = fonction;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getMatricule()
    {
        return matricule;
    }

    public void setMatricule(String matricule)
    {
        this.matricule = matricule;
    }

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public CoordonneesSocieteC getSociete()
    {
        return societe;
    }

    public void setSociete(CoordonneesSocieteC societe)
    {
        this.societe = societe;
    }

    public String getMois()
    {
        return mois;
    }

    public void setMois(String mois)
    {
        this.mois = mois;
    }

    public BulletinPaieC getBulletin()
    {
        return bulletin;
    }

    public void setBulletin(BulletinPaieC bulletin)
    {
        this.bulletin = bulletin;
    }

    public EmployeC getEmploye()
    {
        return employe;
    }

    public void setEmploye(EmployeC employe)
    {
        this.employe = employe;
    }

    public String getDatePrint() {
		return datePrint;
	}

	public void setDatePrint(String datePrint) {
		this.datePrint = datePrint;
	}

	public int getIdEmp1() {
		return idEmp1;
	}

	public void setIdEmp1(int idEmp1) {
		this.idEmp1 = idEmp1;
	}

	public int getIdEmp2() {
		return idEmp2;
	}

	public void setIdEmp2(int idEmp2) {
		this.idEmp2 = idEmp2;
	}

	public List<SelectItem> getListEmp1() {
		return listEmp1;
	}

	public void setListEmp1(List<SelectItem> listEmp1) {
		this.listEmp1 = listEmp1;
	}

	public List<SelectItem> getListEmp2() {
		return listEmp2;
	}

	public void setListEmp2(List<SelectItem> listEmp2) {
		this.listEmp2 = listEmp2;
	}

	public List<String> getListDate() {
		return listDate;
	}

	public void setListDate(List<String> listDate) {
		this.listDate = listDate;
	}
	
	public Date getDatePaie() {
		return datePaie;
	}

	public void setDatePaie(Date datePaie) {
		this.datePaie = datePaie;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	@PostConstruct
	 private void init() {
	    
	    String codeOperateur = (String)this.session.getAttribute("operateur");
	    String codeExercice = (String)this.session.getAttribute("exercice");
	     if (codeOperateur == null || codeExercice == null) {
	 
	      
	      try {
	         FacesContext context = FacesContext.getCurrentInstance();
	        context.getExternalContext().redirect("/payRoll/login.xhtml");
	     }
	      catch (IOException e) {
	         
	        e.printStackTrace();
	       } 
	     } else {
	    	 exercice=FichierBaseDAO.getInstance().getExercice(codeExercice);
	    	 chargerEmploye();
	    	 listDate=FactoryDAO.getInstance().getListDatePaie(exercice.getId(), 1);
	    	 signataire=FichierBaseDAO.getInstance().getSignataire(1);
	     }
	    	 
	     }

	public void changeDatePaie(ValueChangeEvent event)
	{
		datePrint=(String)event.getNewValue().toString();
	       if(datePrint!=null)
	    	   datePaie=HelperC.validerDate(datePrint);
	}
	public void changeEmploye1(ValueChangeEvent event)
	{
		  code1 = ((String)event.getNewValue()).toString();
	}
	public void changeEmploye2(ValueChangeEvent event)
	{
		 code2 = ((String)event.getNewValue()).toString();
	}
	
	private void chargerListEmploye(){
		
		listEmploye=FactoryDAO.getInstance().getListEmployeSimple(code1, code2);
	}
	
	public void print()
	{
		//Image image = null;
        Document doc = new Document(PageSize.A4);
        ByteArrayOutputStream docMem = new ByteArrayOutputStream();
		 
       
        doc.addAuthor("Crost Soft");
        doc.addProducer();
        doc.addCreationDate();
        doc.addTitle("Bulletin de paie");
        
      
		try
        {
			 PdfWriter writer = PdfWriter.getInstance(doc, docMem);
			  doc.open();
			  chargerListEmploye();
		for (EmployeC emp : listEmploye) {
			employe=emp;
			bulletin=FactoryDAO.getInstance().getBulletinPaie(employe.getId(), datePaie, exercice.getId());
			if(bulletin!=null)
			{
			
			doc.newPage();
            
//            ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
//            image = Image.getInstance((new StringBuilder(String.valueOf(servletContext.getRealPath("/resources")))).append("\\Images\\").append("logoUniversite.png").toString());
//            image.scaleAbsoluteHeight(90F);
//            image.scaleAbsoluteWidth(180F);
//            doc.add(image);
			doc.add(logoDocument());
            writer.setPageEvent(new ItextFooterHelper(new Phrase("Produit GATECH", new Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 8F, 0))));
            doc.add(pageHeader());
            doc.add(getTableBulletin());
            doc.add(getSignataire());
			}
	           
	        }
		 doc.close();
         FacesContext context = FacesContext.getCurrentInstance();
         HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
         res.setHeader("Cache-Control", "Max-age=100");
         res.setContentType("application/pdf");
         res.setHeader("content-disposition", "inline;filename=BulletinPaie.pdf");
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
	private PdfPTable getSignataire() throws DocumentException, IOException {
		PdfPTable table = null;
		table = new PdfPTable(3);
		PdfPCell cell = new PdfPCell();
		table.setWidthPercentage(100F);
		cell.setBorder(0);
		int largeurCollones[] = { 50, 50,50 };
		
		table.setWidths(largeurCollones);
		Paragraph p = null;
		
		/*
		 * for (ParametrageDecideurDetailC det : signataire.getListDetail()) {
		 * 
		 * p = new Paragraph(); p.add(new Chunk("", FontFactory.getFont("Verdana", 8F,
		 * 0))); p.setAlignment(2); cell = HelperItext.getCellule(p, 1, 0, 0, 0, 0.5F,
		 * 3F); table.addCell(cell);
		 * 
		 * p = new Paragraph(); p.add(new Chunk("\n\n\n"+det.getNomEmploye(),
		 * FontFactory.getFont("Verdana", 8F, 0))); cell = HelperItext.getCellule(p, 1,
		 * 0, 0, 0, 0.5F, 3F); table.addCell(cell); }
		 */
		
		p = new Paragraph();
		p.add(new Chunk("\n\n\nPréparé par : ", FontFactory.getFont("Verdana", 10F, 1)));
		p.add(new Chunk("\n\nR.H", FontFactory.getFont("Verdana", 10F, 1)));
		cell = HelperItext.getCellule(p, 1, 0, 0, 0, 0.5F, 3F);
		table.addCell(cell);

		

		p = new Paragraph();
		p.add(new Chunk("\n\n\nVérifié par : ", FontFactory.getFont("Verdana", 10F, 1)));
		p.add(new Chunk("\n\nComptable ", FontFactory.getFont("Verdana", 10F, 1)));
		cell = HelperItext.getCellule(p, 1, 0, 0, 0, 0.5F, 3F);
		table.addCell(cell);

		
		p = new Paragraph();
		p.add(new Chunk("\n\n\nAccepté par :", FontFactory.getFont("Verdana", 10F, 1)));
		p.add(new Chunk("\n\nEmloyé", FontFactory.getFont("Verdana", 10F, 1)));
		cell = HelperItext.getCellule(p, 1, 0, 0, 0, 0.5F, 3F);
		table.addCell(cell);
		
		return table;
	}
	private void chargerEmploye(){
		listEmp1=new ArrayList<SelectItem>();
		listEmp2=new ArrayList<SelectItem>();
		listEmp1.add(new SelectItem("", ""));
		listEmp2.add(new SelectItem("", ""));
		
		for (EmployeC b : FactoryDAO.getInstance().getListEmployeSimple("")) {
			listEmp1.add(new SelectItem(b.getCode(), b.getCode()+"-"+b.getNomPrenom()));
			listEmp2.add(new SelectItem(b.getCode(), b.getCode()+"-"+b.getNomPrenom()));
		}
	}
	
	public void printBulletin()
    {
        try
        {
            //Image image = null;
            Document doc = new Document(PageSize.A4);
            ByteArrayOutputStream docMem = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, docMem);
            doc.addAuthor("Gatech");
            doc.addProducer();
            doc.addCreationDate();
            doc.addTitle("Bulletin de paie");
            doc.open();
           
//            ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
//            image = Image.getInstance((new StringBuilder(String.valueOf(servletContext.getRealPath("/resources")))).append("\\Images\\").append("logoUniversite.png").toString());
//            image.scaleAbsoluteHeight(90F);
//            image.scaleAbsoluteWidth(180F);
          
           // doc.add(image);
            doc.add(logoDocument());
            writer.setPageEvent(new ItextFooterHelper(new Phrase("Produit Gatech", new Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 8F, 0))));
            doc.add(pageHeader());
            doc.add(getTableBulletin());
            doc.newPage();
            doc.close();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
            res.setHeader("Cache-Control", "Max-age=100");
            res.setContentType("application/pdf");
            res.setHeader("content-disposition", (new StringBuilder("inline;filename=BulletinPaie[")).append(employe.getNomPrenom().replaceAll(" ", "-")).append("].pdf").toString());
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
	private PdfPTable logoDocument() throws DocumentException, IOException {
	    PdfPTable table = null;
	     table = new PdfPTable(2);
	    PdfPCell cell = new PdfPCell();
	    table.setWidthPercentage(100.0F);
	    cell.setBorder(0);
	    int[] largeurCollones = {
	       80, 20
	      };
	    String ip = "";
	    String urlValue="";
	    InetAddress localhost = InetAddress.getLocalHost();
	    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String url = request.getRequestURL().toString();
	     urlValue=url.replaceAll("localhost", ip);
	     ip = localhost.getHostAddress();
	     
	    Image image = null;
	    ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
	    image = Image.getInstance(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + "logo.png");
	 //   image.scaleAbsoluteHeight(50.0F);
	    image.scaleAbsolute(100.0F,100.0F);
	    
	    
	    BarcodeQRCode barcodeQRCode = new BarcodeQRCode(urlValue, 1000, 1000, null);
	    Image codeQrImage = barcodeQRCode.getImage();
	    codeQrImage.scaleAbsolute(100.0F, 100.0F);
	    
	    table.setWidths(largeurCollones);
	    cell = HelperItext.getCellule((Element)image, 1, 0, 0, 0, 0.0F, 3.0F);
	    table.addCell(cell);
	    
	    cell = HelperItext.getCellule((Element)codeQrImage, 1, 0, 0, 0, 0.0F, 3.0F);
	    table.addCell(cell);
	    
	    return table;
	  }
    private PdfPTable pageHeader()
        throws DocumentException, IOException
    {
        PdfPTable table = null;
        table = new PdfPTable(2);
        PdfPCell cell = new PdfPCell();
        table.setWidthPercentage(100F);
        cell.setBorder(0);
        int largeurCollones[] = {
            50, 50
        };
        table.setWidths(largeurCollones);
        cell = HelperItext.getPdfCell(HelperC.convertDate(Calendar.getInstance().getTime()), FontFactory.getFont("Times-Roman", 8F, 0, BaseColor.BLACK), 1, 3, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        cell = HelperItext.getPdfCell("BULLETIN DE PAIE ", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        return table;
    }

    private PdfPTable getTableBulletin()
        throws DocumentException
    {
        PdfPTable tabInfo = new PdfPTable(6);
        int widthsInfo[] = {
            40, 15, 8, 15, 8, 15
        };
        tabInfo.setWidthPercentage(100F);
        tabInfo.setWidths(widthsInfo);
        tabInfo.setKeepTogether(true);
        PdfPCell cell = new PdfPCell();
        elementToPrint();
        Phrase phrase = null;
        Paragraph p = null;
        cell = HelperItext.getPdfCell("  ", FontFactory.getFont("Courier", 12F, 0, BaseColor.BLACK), 2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk("Mois de paie: ", FontFactory.getFont("Courier", 9F, 0)));
        phrase.add(new Chunk(HelperC.moisEnLettres(bulletin.getMois()), FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk("Employé :", FontFactory.getFont("Courier", 9F, 0)));
        phrase.add(new Chunk(employe.getNomPrenom(), FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk("Date paiement :", FontFactory.getFont("Courier", 9F, 0)));
        phrase.add(new Chunk(HelperC.convertDate(bulletin.getDatePaie()), FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk("Matricule :", FontFactory.getFont("Courier", 9F, 0)));
        phrase.add(new Chunk(employe.getCode(), FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk("Mode règlement :", FontFactory.getFont("Courier", 9F, 0)));
        phrase.add(new Chunk("Virement", FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk("Grade :", FontFactory.getFont("Courier", 9F, 0)));
        phrase.add(new Chunk(grade, FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk(" ", FontFactory.getFont("Courier", 9F, 0)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk("Catégorie :", FontFactory.getFont("Courier", 9F, 0)));
        phrase.add(new Chunk(categorie, FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk(" ", FontFactory.getFont("Courier", 9F, 0)));
        cell = HelperItext.getCellule(phrase, 1, 0, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        phrase = new Phrase();
        phrase.add(new Chunk("Profession :", FontFactory.getFont("Courier", 9F, 0)));
        phrase.add(new Chunk(profession, FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(phrase, 1, 2, 0, 3, 0.0F, 3F);
        tabInfo.addCell(cell);
        cell = HelperItext.getPdfCell(" ", FontFactory.getFont("Courier", 12F, 0, BaseColor.BLACK), 2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
        tabInfo.addCell(cell);
        p = new Paragraph();
        p.add(new Chunk("RUBRIQUES ", FontFactory.getFont("Courier", 9F, 1)));
        p.setAlignment(1);
        cell = HelperItext.getCellule(p, 1, 2, 15, 6, 0.5F, 3F);
        tabInfo.addCell(cell);
        p = new Paragraph();
        p.add(new Chunk("Salaire de base ", FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(p, 1, 2, 4, 4, 0.5F, 3F);
        tabInfo.addCell(cell);
        p = new Paragraph();
        p.add(new Chunk(HelperC.decimalNumber(bulletin.getSalaireBase(), 0, true), FontFactory.getFont("Courier", 9F, 1)));
        p.setAlignment(2);
        cell = HelperItext.getCellule(p, 1, 2, 8, 2, 0.5F, 1.0F);
        tabInfo.addCell(cell);
        
        if(bulletin.getTotalLogement() > 0.0D)
        {
            p = new Paragraph();
            p.add(new Chunk("Logement ", FontFactory.getFont("Courier", 9F, 0)));
            cell = HelperItext.getCellule(p, 1, 2, 4, 4, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            
            p.add(new Chunk(HelperC.decimalNumber(bulletin.getTotalLogement(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
            p.setAlignment(2);
            cell = HelperItext.getCellule(p, 1, 2, 8, 2, 0.5F, 1.0F);
            tabInfo.addCell(cell);
        }
        if(bulletin.getListAllocation().size() > 0)
        {
            p = new Paragraph();
            p.add(new Chunk("Allocations ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 12, 6, 0.5F, 3F);
            tabInfo.addCell(cell);
            
            for(BulletinAllocationC alc : bulletin.getListAllocation())
            {
                phrase = new Phrase();
                phrase.add(new Chunk(Constante.getLibelleStatutPersonneACharge(alc.getPersonneCharge().getRelation()), FontFactory.getFont("Courier", 9F, 0)));
                cell = HelperItext.getCellule(phrase, 1, 2, 4, 4, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(alc.getMontant()*alc.getNombre(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 3, 8, 2, 0.5F, 1.0F);
                tabInfo.addCell(cell);
            }

        }
        if(bulletin.getListHeureSup().size() > 0)
        {
            p = new Paragraph();
            p.add(new Chunk("Heures supplémentaires ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 4, 2, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Taux ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 0, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("  ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 8, 3, 0.5F, 3F);
            tabInfo.addCell(cell);
            for(BulletinHeureSupplementaireC hs: bulletin.getListHeureSup())
            {
               
                phrase = new Phrase();
                phrase.add(new Chunk(hs.getHeurePreste(), FontFactory.getFont("Courier", 9F, 0)));
                cell = HelperItext.getCellule(phrase, 1, 2, 4, 2, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                p = new Paragraph();
                p.add(new Chunk((new StringBuilder(String.valueOf(hs.getPourcentage()))).append("%").toString(), FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(p, 1, 2, 0, 0, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(hs.getMontant(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 3, 8, 3, 0.5F, 1.0F);
                tabInfo.addCell(cell);
            }

        }
        if(bulletin.getListePrime().size() > 0)
        {
            p = new Paragraph();
            p.add(new Chunk("Primes et indemnité", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 12, 6, 0.5F, 3F);
            tabInfo.addCell(cell);
            for( BulletinPrimeC prime:bulletin.getListePrime())
            {
                
                phrase = new Phrase();
                phrase.add(new Chunk(prime.getLibellePrime(), FontFactory.getFont("Courier", 9F, 0)));
                cell = HelperItext.getCellule(phrase, 1, 2, 4, 4, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(prime.getMontantPrime(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 3, 8, 2, 0.5F, 1.0F);
                tabInfo.addCell(cell);
            }
            
            phrase = new Phrase(" ");
            cell = HelperItext.getCellule(phrase, 1, 2, 4, 4, 0.5F, 2F);
            tabInfo.addCell(cell);
            
            p = new Paragraph(" ");
            cell = HelperItext.getCellule(p, 1, 3, 8, 2, 0.5F, 2F);
            tabInfo.addCell(cell);
        }
        if(bulletin.getListeIndemnite().size() > 0)
        {
            p = new Paragraph();
            p.add(new Chunk("Indemnité ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 12, 6, 0.5F, 3F);
            tabInfo.addCell(cell);
            for(BulletinIndemniteC ind:bulletin.getListeIndemnite())
            {
               
                phrase = new Phrase();
                phrase.add(new Chunk(ind.getLibelleIndemnite(), FontFactory.getFont("Courier", 9F, 0)));           
                cell = HelperItext.getCellule(phrase, 1, 2, 4, 4, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(ind.getMontantIndemnite(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 3, 8, 2, 0.5F, 1.0F);
                tabInfo.addCell(cell);
            }
            
           
        }
        
        p = new Paragraph();
        p.add(new Chunk("SALAIRE BRUT ", FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(p, 1, 2, 15, 4, 0.5F, 3F);
        tabInfo.addCell(cell);
        
        p = new Paragraph();     
        p.add(new Chunk(HelperC.decimalNumber(bulletin.getTotalBrute(), 0, true), FontFactory.getFont("Courier", 9F, 1)));
        p.setAlignment(2);
        cell = HelperItext.getCellule(p, 1, 2, 11, 4, 0.5F, 1.0F);
        tabInfo.addCell(cell);
        cell = HelperItext.getPdfCell(" ", FontFactory.getFont("Courier", 12F, 0, BaseColor.BLACK), 2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
        tabInfo.addCell(cell);
        
        if(bulletin.getListeCotisation().size() > 0)
        {
            p = new Paragraph();
            p.add(new Chunk("COTISATIONS ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 13, 6, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk(" ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 13, 2, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Salarial ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 9, 2, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Patronal ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 9, 2, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Libellé ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 13, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Base ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Taux ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Montant ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Taux ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk("Montant ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            for( BulletinCotisationC cot: bulletin.getListeCotisation())
            {
               
                p = new Paragraph();
                p.add(new Chunk(cot.getLibelleCotisation(), FontFactory.getFont("Courier", 9F, 0)));
                cell = HelperItext.getCellule(p, 1, 2, 13, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(cot.getMontantBase(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                p = new Paragraph();
                if(cot.getTauxSalarial() > 0.0D)
                {
                    p.add(new Chunk((new StringBuilder(String.valueOf(cot.getTauxSalarial()))).append(" %").toString(), FontFactory.getFont("Courier", 9F, 0)));
                } else
                {
                    p.add(new Chunk(" ", FontFactory.getFont("Courier", 9F, 0)));
                }
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(cot.getMontantCotisation(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                p = new Paragraph();
                if(cot.getTauxPatronal() > 0.0D)
                {
                    p.add(new Chunk((new StringBuilder(String.valueOf(cot.getTauxPatronal()))).append(" %").toString(), FontFactory.getFont("Courier", 9F, 0)));
                } else
                {
                    p.add(new Chunk(" ", FontFactory.getFont("Courier", 9F, 0)));
                }
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(cot.getMontantPatronal(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 2, 9, 0, 0.5F, 3F);
                tabInfo.addCell(cell);
            }

            p = new Paragraph();
            p.add(new Chunk("TOTAL ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 15, 2, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk(HelperC.decimalNumber(bulletin.getTotalCotisation(), 0, true), FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(2);
            cell = HelperItext.getCellule(p, 1, 2, 11, 2, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.add(new Chunk(HelperC.decimalNumber(bulletin.getTotalPatronal(), 0, true), FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(2);
            cell = HelperItext.getCellule(p, 1, 2, 11, 2, 0.5F, 3F);
            tabInfo.addCell(cell);
        }
        cell = HelperItext.getPdfCell(" ", FontFactory.getFont("Courier", 12F, 0, BaseColor.BLACK), 2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 3);
        tabInfo.addCell(cell);
        if(bulletin.getListDeduction().size() > 0 || bulletin.getListAvance().size() > 0 || bulletin.getListCredit().size() > 0)
        {
            p = new Paragraph();
            p.add(new Chunk("RETENU ET DEDUCTIONS ", FontFactory.getFont("Courier", 9F, 1)));
            p.setAlignment(1);
            cell = HelperItext.getCellule(p, 1, 2, 15, 6, 0.5F, 3F);
            tabInfo.addCell(cell);
            
            for( BulletinDeductionC ded:bulletin.getListDeduction())
            {
               if(ded.getDeduction().isSoustract()) {
                phrase = new Phrase();
                phrase.add(new Chunk(ded.getLibelleDeduction(), FontFactory.getFont("Courier", 9F, 0)));
                cell = HelperItext.getCellule(phrase, 1, 2, 4, 4, 0.5F, 3F);
                tabInfo.addCell(cell);
                
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(ded.getMontantRetenu(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                p.setAlignment(2);
                cell = HelperItext.getCellule(p, 1, 3, 8, 2, 0.5F,3F);
                tabInfo.addCell(cell);
               }
            }

            if(bulletin.getListAvance().size() > 0)
            {
                for(BulletinAvanceC avc:bulletin.getListAvance())
                {
                    
                    phrase = new Phrase();
                    phrase.add(new Chunk((new StringBuilder("Avance sur salaire du :")).append(avc.getDatePrint()).toString(), FontFactory.getFont("Courier", 9F, 0)));
                    cell = HelperItext.getCellule(phrase, 1, 2, 4, 4, 0.5F, 1.0F);
                    tabInfo.addCell(cell);
                    p = new Paragraph();
                    p.add(new Chunk(HelperC.decimalNumber(avc.getMontantAvance(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                    p.setAlignment(2);
                    cell = HelperItext.getCellule(p, 1, 3, 8, 2, 0.5F, 1.0F);
                    tabInfo.addCell(cell);
                }

            }
            if(bulletin.getListCredit().size() > 0)
            {
                for(BulletinCreditC crd:bulletin.getListCredit())
                {
                    
                    phrase = new Phrase();
                    phrase.add(new Chunk(crd.getLibelle()+"(Dossier N°: "+crd.getNoDossier()+")", FontFactory.getFont("Courier", 9F, 0)));
                    cell = HelperItext.getCellule(phrase, 1, 2, 4, 4, 0.5F, 3F);
                    tabInfo.addCell(cell);
                    
                    
                    p = new Paragraph();
                    p.add(new Chunk(HelperC.decimalNumber(crd.getMontant(), 0, true), FontFactory.getFont("Courier", 9F, 0)));
                    p.setAlignment(2);
                    cell = HelperItext.getCellule(p, 1, 3, 8, 2, 0.5F, 3F);
                    tabInfo.addCell(cell);
                }

            }
            p = new Paragraph();
            p.add(new Chunk("TOTAL ", FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 15, 4, 0.5F, 3F);
            tabInfo.addCell(cell);
            p = new Paragraph();
            p.setAlignment(2);
            p.add(new Chunk(HelperC.decimalNumber(bulletin.getTotalAvance() + bulletin.getTotalDeduction() + bulletin.getTotalCredit(), 0, true), FontFactory.getFont("Courier", 9F, 1)));
            cell = HelperItext.getCellule(p, 1, 2, 15, 4, 0.5F, 3F);
            tabInfo.addCell(cell);
        }
        cell = HelperItext.getPdfCell(" ", FontFactory.getFont("Courier", 12F, 0, BaseColor.BLACK), 2, 2, 0, 6, BaseColor.WHITE, BaseColor.WHITE, 1);
        tabInfo.addCell(cell);
        p = new Paragraph();
        p.add(new Chunk("NET ", FontFactory.getFont("Courier", 9F, 1)));
        cell = HelperItext.getCellule(p, 1, 2, 15, 4, 0.5F, 3F);
        tabInfo.addCell(cell);
        p = new Paragraph();
        p.add(new Chunk(HelperC.decimalNumber(bulletin.getTotalNetPay(), 0, true), FontFactory.getFont("Courier", 9F, 1)));
        p.setAlignment(2);
        cell = HelperItext.getCellule(p, 1, 2, 15, 2, 0.5F, 3F);
        tabInfo.addCell(cell);
        return tabInfo;
    }

    
    private void elementToPrint()
    {
        employe = null;
        categorie = "";
        if(bulletin != null)
        {
            if(employe == null)
            {
                employe = FactoryDAO.getInstance().getEmploye(bulletin.getIdEmploye(), false);
            }
            if(employe != null && employe.getFonction() != null)
            {
                profession = employe.getFonction().getDesignation();
            } else
            {
                profession = "";
            }
            if(employe != null)
            {
                matricule = employe.getMatricule();
            }
            if(employe != null)
            {
                DetailGradeC gradeDetail = FactoryDAO.getInstance().getDetailGradeParEmploye(employe);
                if(gradeDetail != null )
                {
                    grade = FichierBaseDAO.getInstance().getGradePersonnel(gradeDetail.getIdNvGrd()).getDesignation();
                } else
                {
                    grade = "";
                }
            }
            societe = FichierBaseDAO.getInstance().getCoordonneesSociete();
        }
    }
}
