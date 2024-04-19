 package classesPaie;
 
 import com.itextpdf.text.Chunk;
 import com.itextpdf.text.Document;
 import com.itextpdf.text.Element;
 import com.itextpdf.text.FontFactory;
 import com.itextpdf.text.Phrase;
 import com.itextpdf.text.Rectangle;
 import com.itextpdf.text.pdf.ColumnText;
 import com.itextpdf.text.pdf.PdfPageEventHelper;
 import com.itextpdf.text.pdf.PdfWriter;
 import java.io.Serializable;
 
 
 public class HeaderFooterInfo
   extends PdfPageEventHelper
   implements Serializable
 {
   private static final long serialVersionUID = -3491599511654805318L;
   
   public void onEndPage(PdfWriter writer, Document document) {
     Phrase p = null;
     Rectangle rect = writer.getBoxSize("art");
     p = new Phrase();
     p.add((Element)new Chunk(" ", FontFactory.getFont("Times", 8.0F, 0)));
     ColumnText.showTextAligned(writer.getDirectContent(), 1, p, rect.getLeft(), rect.getBottom(), 0.0F);
     p = new Phrase();
     p.add((Element)new Chunk(" " + document.getPageNumber(), FontFactory.getFont("Times", 8.0F, 0)));
     ColumnText.showTextAligned(writer.getDirectContent(), 2, p, rect.getRight(), rect.getBottom(), 0.0F);
   }
 }


