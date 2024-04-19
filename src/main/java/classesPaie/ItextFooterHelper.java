/*    */ package classesPaie;
/*    */ 
/*    */ import com.itextpdf.text.Document;
/*    */ import com.itextpdf.text.Font;
/*    */ import com.itextpdf.text.Phrase;
/*    */ import com.itextpdf.text.pdf.ColumnText;
/*    */ import com.itextpdf.text.pdf.PdfContentByte;
/*    */ import com.itextpdf.text.pdf.PdfPageEventHelper;
/*    */ import com.itextpdf.text.pdf.PdfWriter;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ItextFooterHelper
/*    */   extends PdfPageEventHelper
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = -2346252816269970672L;
/*    */   Font ffont;
/*    */   Phrase footer;
/*    */   
/*    */   public ItextFooterHelper(Phrase footer) {
/* 20 */     this.ffont = new Font(Font.FontFamily.TIMES_ROMAN, 3.0F, 2);
/* 21 */     this.footer = footer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEndPage(PdfWriter writer, Document document) {
/* 26 */     PdfContentByte cb = writer.getDirectContent();
/* 27 */     ColumnText.showTextAligned(cb, 1, this.footer, (document.right() - document.left()) / 2.0F + document.leftMargin(), document.bottom() - 10.0F, 0.0F);
/*    */   }
/*    */ }


/* Location:              G:\PAIE\!\classesPaie\ItextFooterHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */