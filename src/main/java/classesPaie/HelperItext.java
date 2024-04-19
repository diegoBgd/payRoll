/*     */ package classesPaie;
/*     */ 
/*     */ import com.itextpdf.text.BadElementException;
/*     */ import com.itextpdf.text.BaseColor;
/*     */ import com.itextpdf.text.Document;
/*     */ import com.itextpdf.text.Element;
/*     */ import com.itextpdf.text.Font;
/*     */ import com.itextpdf.text.Image;
/*     */ import com.itextpdf.text.Paragraph;
/*     */ import com.itextpdf.text.Phrase;
/*     */ import com.itextpdf.text.Rectangle;
/*     */ import com.itextpdf.text.pdf.ColumnText;
/*     */ import com.itextpdf.text.pdf.PdfPCell;
/*     */ import com.itextpdf.text.pdf.PdfWriter;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class HelperItext
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4479545637647547695L;
/*     */   
/*     */   public static PdfPCell getCellulePdf(String label_cellule, Font format, int alignement_vertical, int alignement_horizontal, int bordure, int cellules_fusionnees, BaseColor couleur) throws BadElementException {
/*  23 */     PdfPCell cellule = null;
/*  24 */     Paragraph para = new Paragraph(label_cellule, format);
/*  25 */     cellule = new PdfPCell((Phrase)para);
/*  26 */     cellule.setVerticalAlignment(alignement_vertical);
/*  27 */     cellule.setHorizontalAlignment(alignement_horizontal);
/*  28 */     cellule.setBorder(bordure);
/*  29 */     cellule.setColspan(cellules_fusionnees);
/*  30 */     cellule.setBackgroundColor(couleur);
/*  31 */     cellule.setPaddingBottom(5.0F);
/*  32 */     return cellule;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static PdfPCell getCellulePdfBarcode(Image label_cellule, int alignement_vertical, int alignement_horizontal, int bordure, int cellules_fusionnees, BaseColor couleur) throws BadElementException {
/*  38 */     PdfPCell cellule = null;
/*  39 */     cellule = new PdfPCell(label_cellule, true);
/*  40 */     cellule.setVerticalAlignment(alignement_vertical);
/*  41 */     cellule.setHorizontalAlignment(alignement_horizontal);
/*  42 */     cellule.setPadding(10.0F);
/*  43 */     cellule.setBorder(bordure);
/*  44 */     cellule.setColspan(cellules_fusionnees);
/*  45 */     cellule.setBackgroundColor(couleur);
/*  46 */     return cellule;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static PdfPCell getPdfCellEntete(Element element, int alignmentVertical, int alignmentHorizontal, int border, int colspan, int borderwidth, BaseColor bordercolor, float cellPadding) throws BadElementException {
/*  52 */     PdfPCell cell = new PdfPCell();
/*  53 */     cell.addElement(element);
/*  54 */     if (border > 0) {
/*     */       
/*  56 */       cell.setBorder(border);
/*  57 */       cell.setBorderWidth(borderwidth);
/*  58 */       if (bordercolor != null)
/*     */       {
/*  60 */         cell.setBorderColor(bordercolor);
/*     */       }
/*     */     } else {
/*     */       
/*  64 */       cell.setBorder(0);
/*     */     } 
/*  66 */     cell.setVerticalAlignment(alignmentVertical);
/*  67 */     cell.setHorizontalAlignment(alignmentHorizontal);
/*  68 */     cell.setColspan(colspan);
/*  69 */     cell.setPadding(cellPadding);
/*  70 */     return cell;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PdfPCell getPdfCell(String label_cellule, Font font, int alignement_vertical, int alignement_horizontal, int bordure, int cellules_fusionnees, BaseColor couleur, BaseColor borderColor, int borderWidth) throws BadElementException {
/*  77 */     PdfPCell colonne = null;
/*  78 */     Paragraph para = new Paragraph(label_cellule, font);
/*  79 */     colonne = new PdfPCell((Phrase)para);
/*  80 */     if (alignement_vertical == 1) {
/*     */       
/*  82 */       colonne.setVerticalAlignment(1);
/*     */     }
/*  84 */     else if (alignement_vertical == 2) {
/*     */       
/*  86 */       colonne.setVerticalAlignment(0);
/*     */     }
/*  88 */     else if (alignement_vertical == 3) {
/*     */       
/*  90 */       colonne.setVerticalAlignment(2);
/*     */     } 
/*  92 */     if (alignement_horizontal == 1) {
/*     */       
/*  94 */       colonne.setHorizontalAlignment(1);
/*     */     }
/*  96 */     else if (alignement_horizontal == 2) {
/*     */       
/*  98 */       colonne.setHorizontalAlignment(0);
/*     */     }
/* 100 */     else if (alignement_horizontal == 3) {
/*     */       
/* 102 */       colonne.setHorizontalAlignment(2);
/*     */     } 
/* 104 */     colonne.setBorder(bordure);
    
/* 137 */     colonne.setColspan(cellules_fusionnees);
/* 138 */     colonne.setBackgroundColor(couleur);
/* 139 */     colonne.setPaddingBottom(5.0F);
/* 140 */     colonne.setBorderColor(borderColor);
/* 141 */     colonne.setBorderWidth(borderWidth);
/* 142 */     return colonne;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PdfPCell getCellule(Element element, int alignmentVertical, int alignmentHorizontal, int border, int colspan, float borderwidth, float cellPadding) {
/* 147 */     PdfPCell cell = new PdfPCell();
/* 148 */     cell.addElement(element);
/* 149 */     if (border > 0) {
/*     */       
/* 151 */       cell.setBorder(border);
/* 152 */       cell.setBorderWidth(borderwidth);
/*     */     } else {
/*     */       
/* 155 */       cell.setBorder(0);
/*     */     } 
/* 157 */     cell.setUseAscender(true);
/* 158 */     if (alignmentVertical == 1) {
/*     */       
/* 160 */       cell.setVerticalAlignment(5);
/*     */     }
/* 162 */     else if (alignmentVertical == 2) {
/*     */       
/* 164 */       cell.setVerticalAlignment(0);
/*     */     }
/* 166 */     else if (alignmentVertical == 3) {
/*     */       
/* 168 */       cell.setVerticalAlignment(2);
/*     */     } 
/* 170 */     if (alignmentHorizontal == 1) {
/*     */       
/* 172 */       cell.setHorizontalAlignment(1);
/*     */     }
/* 174 */     else if (alignmentHorizontal == 2) {
/*     */       
/* 176 */       cell.setHorizontalAlignment(0);
/*     */     }
/* 178 */     else if (alignmentHorizontal == 3) {
/*     */       
/* 180 */       cell.setHorizontalAlignment(2);
/*     */     } 
/* 182 */     cell.setColspan(colspan);
/* 183 */     cell.setPadding(cellPadding);
/* 184 */     return cell;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEndPage(PdfWriter writer, Document document) {
/* 189 */     Rectangle rect = writer.getBoxSize("art");
/* 190 */     ColumnText.showTextAligned(writer.getDirectContent(), 1, new Phrase("Bottom Left"), rect.getLeft(), rect.getBottom(), 0.0F);
/* 191 */     ColumnText.showTextAligned(writer.getDirectContent(), 1, new Phrase("Bottom Right"), rect.getRight(), rect.getBottom(), 0.0F);
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\classesPaie\HelperItext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */