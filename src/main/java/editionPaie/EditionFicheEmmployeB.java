 package editionPaie;
 import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;

import classesPaie.EmployeC;
import classesPaie.HelperC;
import classesPaie.HelperItext;
import classesPaie.ItextFooterHelper;
import classesPaie.Tables;
import classesPaie.Tables.TableName;
import persistencePaie.FichierBaseDAO;

import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.io.Serializable;
 import java.net.InetAddress;
 import java.util.Calendar;

 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.ServletContext;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 @ManagedBean
 @ViewScoped
 public class EditionFicheEmmployeB implements Serializable {
   private static final long serialVersionUID = 2787823076539505210L;
   
   public EmployeC getEmploye() {
     return this.employe;
   }
   private EmployeC employe;

            public void init() {}

   public void setEmploye(EmployeC employe) {
     this.employe = employe;
   }
 
   
   public void visualiser() {
     Image image = null;
     
     try {
       Document doc = new Document(PageSize.A4);
       ByteArrayOutputStream docMem = new ByteArrayOutputStream();
       PdfWriter writer = PdfWriter.getInstance(doc, docMem);
       doc.addAuthor("Produit Crost Soft");
       doc.addProducer();
       doc.addCreationDate();
       doc.addTitle("FICHE DE L'EMPLOYE");
       doc.open();
       
       doc.add((Element)logoDocument());
       writer.setPageEvent((PdfPageEvent)new ItextFooterHelper(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0))));
       doc.add((Element)pageHeader());
       doc.add((Element)formulaireContenu());
       
       doc.close();
       FacesContext context = FacesContext.getCurrentInstance();
       HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
       res.setHeader("Cache-Control", "Max-age=100");
       res.setContentType("application/pdf");
       res.setHeader("content-disposition", "inline;filename=FicheEmploye.pdf");
       ServletOutputStream out = res.getOutputStream();
       res.setContentLength(docMem.size());
       docMem.writeTo((OutputStream)out);
       out.flush();
       out.close();
       context.responseComplete();
     }
     catch (Exception e) {
       
       System.out.println("Visualisation: " + e.getMessage());
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
     InetAddress localhost = InetAddress.getLocalHost();
     HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
     String url = request.getRequestURL().toString();
     
     Image image = null;
     ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
     image = Image.getInstance(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + "logoUniversite.png");
     image.scaleAbsoluteHeight(80.0F);
     image.scaleAbsoluteWidth(150.0F);
     ip = localhost.getHostAddress();
     BarcodeQRCode barcodeQRCode = new BarcodeQRCode(url.replaceAll("localhost", ip), 1000, 1000, null);
     Image codeQrImage = barcodeQRCode.getImage();
     codeQrImage.scaleAbsolute(100.0F, 100.0F);
     
     table.setWidths(largeurCollones);
     cell = HelperItext.getCellule((Element)image, 1, 0, 0, 0, 0.0F, 3.0F);
     table.addCell(cell);
     
     cell = HelperItext.getCellule((Element)codeQrImage, 1, 0, 0, 0, 0.0F, 3.0F);
     table.addCell(cell);
     
     return table;
   }
 
 
   
   private PdfPTable pageHeader() throws DocumentException, IOException {
     PdfPTable table = null;
     table = new PdfPTable(2);
     PdfPCell cell = new PdfPCell();
     table.setWidthPercentage(100.0F);
     cell.setBorder(0);
     int[] largeurCollones = {
         50, 50
       };
     table.setWidths(largeurCollones);
     cell = HelperItext.getPdfCell(HelperC.convertDate(Calendar.getInstance().getTime()), FontFactory.getFont("Times-Roman", 8.0F, 0, BaseColor.BLACK), 1, 3, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
     table.addCell(cell);
     cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12.0F, 0, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
     table.addCell(cell);
     
     cell = HelperItext.getPdfCell("FICHE DE L'EMPLOYE", FontFactory.getFont("Times-Roman", 12.0F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
     table.addCell(cell);
     return table;
   }
 
 
   
   private PdfPTable formulaireContenu() throws DocumentException, IOException {
     PdfPTable tabInfo = new PdfPTable(4);
     int[] widthsInfo = {
         20, 5, 30, 25
       };
     tabInfo.setWidthPercentage(100.0F);
     tabInfo.setWidths(widthsInfo);
     tabInfo.setKeepTogether(true);
     PdfPCell cell = new PdfPCell();
     String etatCivil = "", sexe = "", personnel = "", categorie = "";
     String grade = "", formation = "", fonction = "", contrat = "";
     String lieuxTravail = "";
     
     if (this.employe != null) {
       
       Image image = null;
       ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
                if(this.employe.getUrlPhoto()!=null)
                {
       image = Image.getInstance(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + this.employe.getUrlPhoto());
       image.scaleAbsoluteHeight(150.0F);
       image.scaleAbsoluteWidth(150.0F);
       }
       if (this.employe.getLieuNaissance() == null)
       {
         this.employe.setLieuNaissance("");
       }
       if (this.employe.getAdresse() == null)
       {
         this.employe.setAdresse("");
       }
       if (this.employe.getMatricule() == null)
       {
         this.employe.setMatricule("");
       }
       if (this.employe.getNumCaisseSociale() == null)
       {
         this.employe.setNumCaisseSociale("");
       }

                if(this.employe.getCni()==null)
					this.employe.setCni("");
				if(this.employe.getDateDelivranceS()==null)
					this.employe.setDateDelivranceS("");
				if(this.employe.getDateNaissanceS()==null)
					this.employe.setDateNaissanceS("");
				if(this.employe.getTelMobile()==null)
					this.employe.setTelMobile("");
				if(this.employe.getTelHabitat()==null)
					this.employe.setTelHabitat("");
				if(this.employe.getEmail()==null)
					this.employe.setEmail("");
				if(this.employe.getDateEntreS()==null)
					this.employe.setDateEntreS("");
				if(this.employe.getDateSortieS()==null)
					this.employe.setDateSortieS("");
					
       if (this.employe.getIdPersnl() > 0)
         personnel = FichierBaseDAO.getInstance().getBaseById(this.employe.getIdPersnl(), Tables.getTableName(Tables.TableName.personnel)).getDesignation(); 
       if (this.employe.getIdCatgrie() > 0)
         categorie = FichierBaseDAO.getInstance().getCategoriePersonnel(this.employe.getIdCatgrie()).getDesignation(); 
       if (this.employe.getIdGrd() > 0)
         grade = FichierBaseDAO.getInstance().getGradePersonnel(this.employe.getIdGrd()).getDesignation(); 
       if (this.employe.getIdNvFormt() > 0)
         formation = FichierBaseDAO.getInstance().getBaseById(this.employe.getIdPersnl(), Tables.getTableName(Tables.TableName.niveauFormation)).getDesignation(); 
       if (this.employe.getIdFnctn() > 0) {
         fonction = FichierBaseDAO.getInstance().getBaseById(this.employe.getIdFnctn(), Tables.getTableName(Tables.TableName.fonction)).getDesignation();
       }
       if (this.employe.getContrat() != null)
         contrat = this.employe.getContrat().getDesignation(); 
       if (this.employe.getLieuTravail() != null) {
         lieuxTravail = this.employe.getLieuTravail().getDesignation();
       }
       switch (this.employe.getEtatCivil()) {
         case 1:
           etatCivil = "C�libataire";
           break;
         
         case 2:
           etatCivil = "Mari�(e)";
           break;
         
         case 3:
           etatCivil = "Veuf(ve)";
           break;
         case 4:
           etatCivil = "Divorc�(e)";
           break;
					default:
						etatCivil="";
						break;
       } 
 
       
       switch (this.employe.getSexe()) {
         case 1:
           sexe = "Masculin";
           break;
         
         case 2:
           sexe = "F�minin";
           break;
       } 
 
 
       
       cell = HelperItext.getPdfCell("  ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 4, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       Phrase phrase = null;
       Paragraph p = null;
       cell = HelperItext.getPdfCell(" IDENTIFICATION ", FontFactory.getFont("Greek-Regular", 12.0F, 0, BaseColor.WHITE), 1, 1, 0, 4, BaseColor.DARK_GRAY, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Matricule ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getCode(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
				phrase = new Phrase();
				if(image!=null)
       	 cell = HelperItext.getCellule(image, 1, 0, 0, 0, 0.0F, 3.0F);
				else
					 cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       cell.setRowspan(11);
       tabInfo.addCell(cell);
 		
				
       
       phrase = new Phrase();
       phrase.add(new Chunk("Nom et Pr�nom ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getNomPrenom(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Lieu de naissance ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getLieuNaissance(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Nationalit� ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
                if(this.employe.getLibelleNationalite()!=null)
     	  phrase.add(new Chunk(this.employe.getLibelleNationalite(), FontFactory.getFont("Courier", 9.0F, 1)));
                else
                	 phrase.add(new Chunk("", FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
 
       
       phrase = new Phrase();
       phrase.add(new Chunk("N� C.N.I ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getCni(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Date d�livrance C.N.I ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getDateDelivranceS(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Date de naissance", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getDateNaissanceS(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Etat civil ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(etatCivil, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Sexe ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(sexe, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("T�l�phone ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(String.valueOf(this.employe.getTelMobile()) + " / " + this.employe.getTelHabitat(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("E-Mail ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getEmail(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" INFORMATION SUR LA CARIERRE ", FontFactory.getFont("Greek-Regular", 12.0F, 0, BaseColor.WHITE), 1, 1, 0, 4, BaseColor.DARK_GRAY, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Personnel ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(personnel, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Cat�orie ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(categorie, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Grade ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(grade, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Niveau de formation ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(formation, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Fonction ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(fonction, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" AUTRES INFORMATIONS ", FontFactory.getFont("Greek-Regular", 12.0F, 0, BaseColor.WHITE), 1, 1, 0, 4, BaseColor.DARK_GRAY, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Type de contrat ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(contrat, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Date du contrat ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getDateDebutContratS(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Date fin contrat", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getDateFinContratS(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Date entr�e en fonction ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getDateEntreS(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Date fin fonction ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(this.employe.getDateSortieS(), FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk("Lieux de travail ", FontFactory.getFont("Courier", 9.0F, 0)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 3.0F);
       tabInfo.addCell(cell);
       
       cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0, BaseColor.WHITE, BaseColor.WHITE, 3);
       tabInfo.addCell(cell);
       
       phrase = new Phrase();
       phrase.add(new Chunk(lieuxTravail, FontFactory.getFont("Courier", 9.0F, 1)));
       cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 3.0F);
       tabInfo.addCell(cell);
     } 
     return tabInfo;
   }
 }

