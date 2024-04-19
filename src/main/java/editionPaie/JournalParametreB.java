package editionPaie;

import classesPaie.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
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
import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
@ManagedBean
@ViewScoped
public class JournalParametreB
    implements Serializable
{

    private static final long serialVersionUID = 0xba989179a200814dL;
    private List<EmployeC> listEmploye;
    private int mois,taille;
    private boolean showTbl;
    
    private int idParamtre,order;
    private int nomBreColonne;
    private Date dateDeb;
    private Date dateFin;
    private List<ParametrageJournalDetailC> listElement;
    private List<ParametrageJournalC> listJournal;
    private List<SelectItem> listParametre;
    private List<BulletinPaieC> listeBulletin;
    private List<DynamicTableC> listDynamic;
    private List<String> listColonne;
    private ParametrageJournalC journal;
    private String datePrintDeb;
    private String datePrintFin;
    private List<String> listDateDb,listDateFn;
    
    Base userFonction;
    HttpSession session;
    HttpServletRequest request;
    OperateurC operateur;
    ExerciceC exercice;
    DroitC droit;

    public JournalParametreB()
    {
        session = HelperC.getSession();
    }

	public boolean isShowTbl() {
		return showTbl;
	}
	public void setShowTbl(boolean showTbl) {
		this.showTbl = showTbl;
	}
	public List<DynamicTableC> getListDynamic() {
		return listDynamic;
	}
	public void setListDynamic(List<DynamicTableC> listDynamic) {
		this.listDynamic = listDynamic;
	}
	public List<BulletinPaieC> getListeBulletin() {
		return listeBulletin;
	}

	public void setListeBulletin(List<BulletinPaieC> listeBulletin) {
		this.listeBulletin = listeBulletin;
	}

	public Date getDateDeb()
    {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb)
    {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin()
    {
        return dateFin;
    }

    public void setDateFin(Date dateFin)
    {
        this.dateFin = dateFin;
    }

    public String getDatePrintDeb()
    {
        return datePrintDeb;
    }

    public void setDatePrintDeb(String datePrintDeb)
    {
        this.datePrintDeb = datePrintDeb;
    }

    public String getDatePrintFin()
    {
        return datePrintFin;
    }

    public void setDatePrintFin(String datePrintFin)
    {
        this.datePrintFin = datePrintFin;
    }
  
    public List<ParametrageJournalDetailC> getListElement() {
		return listElement;
	}

	public void setListElement(List<ParametrageJournalDetailC> listElement) {
		this.listElement = listElement;
	}

	public int getMois()
    {
        return mois;
    }

    public void setMois(int mois)
    {
        this.mois = mois;
    }

    public List<EmployeC> getListEmploye() {
		return listEmploye;
	}

	public void setListEmploye(List<EmployeC> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public int getIdParamtre()
    {
        return idParamtre;
    }

    public void setIdParamtre(int idParamtre)
    {
        this.idParamtre = idParamtre;
    }

    public int getNomBreColonne()
    {
        return nomBreColonne;
    }

    public void setNomBreColonne(int nomBreColonne)
    {
        this.nomBreColonne = nomBreColonne;
    }
  
    public List<SelectItem> getListParametre() {
		return listParametre;
	}

	public void setListParametre(List<SelectItem> listParametre) {
		this.listParametre = listParametre;
	}

    public List<ParametrageJournalC> getListJournal() {
		return listJournal;
	}


	public void setListJournal(List<ParametrageJournalC> listJournal) {
		this.listJournal = listJournal;
	}

	public ParametrageJournalC getJournal()
    {
        return journal;
    }

    public void setJournal(ParametrageJournalC journal)
    {
        this.journal = journal;
    }
	

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public List<String> getListDateDb() {
		return listDateDb;
	}

	public void setListDateDb(List<String> listDateDb) {
		this.listDateDb = listDateDb;
	}

	public List<String> getListDateFn() {
		return listDateFn;
	}

	public void setListDateFn(List<String> listDateFn) {
		this.listDateFn = listDateFn;
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
        	showTbl=false;
            userFonction = FichierBaseDAO.getInstance().getFonctionActive(operateur.getIdEmploye());
            if(userFonction != null)
            {
                droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(), classesPaie.Constante.Role.bulletinPaie);
            }
            listParametre = new ArrayList<SelectItem>();            
            chargementElement();
            chargerDate();
        }
    }
	
	public void changeTaille(){
		
	}
    public void changeParametre(ValueChangeEvent event)
    {
        idParamtre = ((Integer)event.getNewValue()).intValue();
        journal = FichierBaseDAO.getInstance().getParametrageJournal(idParamtre,false);
        
        if(journal != null)
        {
            //listElement = journal.getListeDetail();
            listColonne=FichierBaseDAO.getInstance().getListColonne(journal.getId());
        }
    }

    private void chargerDate(){
    	listDateDb=FactoryDAO.getInstance().getListDatePaie(exercice.getId(), 1);
    	listDateFn=FactoryDAO.getInstance().getListDatePaie(exercice.getId(), 1);
    }
    public void changeDateDeb(ValueChangeEvent event)
    {
       datePrintDeb=(String)event.getNewValue().toString();
       if(datePrintDeb!=null)
    	   dateDeb=HelperC.validerDate(datePrintDeb);
    }

    public void changeDateFin(ValueChangeEvent event)
    {
    	datePrintFin=(String)event.getNewValue().toString();
        if(datePrintFin!=null)
     	   dateFin=HelperC.validerDate(datePrintFin);
    }

    public void printJournal()
    {
        if(idParamtre > 0)
        {
        int diff=(int) HelperC.daysBetween(dateDeb, dateFin);
        	if(diff==0)
        	{
        		mois=HelperC.getMonthFromDate(dateDeb);
        	}
            journalData();
        } else
        {
            HelperC.afficherMessage("Information", "Il faut pr�ciser le journal � �diter !");
        }
    }

    public void printJournalExcel()
    {
        if(idParamtre > 0)
        {
        int diff=(int) HelperC.daysBetween(dateDeb, dateFin);
        	if(diff==0)
        	{
        		mois=HelperC.getMonthFromDate(dateDeb);
        	}
            try {
				getTableJournalExcel();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else
        {
            HelperC.afficherMessage("Information", "Il faut pr�ciser le journal � �diter !");
        }
    }

    private void journalData()
    {
        try
        {
            Image image = null;
            if(droit.isAfficher())
            {
                Document doc = new Document(PageSize.A3.rotate());
                ByteArrayOutputStream docMem = new ByteArrayOutputStream();
                PdfWriter writer = PdfWriter.getInstance(doc, docMem);
                doc.addAuthor("Crost Soft");
                doc.addProducer();
                doc.addCreationDate();
                doc.addTitle("Journal de paie");
                Rectangle rect = new Rectangle(90F, 20F, 800F, 500F);
                writer.setBoxSize("art", rect);
                HeaderFooterInfo footer = new HeaderFooterInfo();
                writer.setPageEvent(footer);
                doc.open();
                ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
                image = Image.getInstance((new StringBuilder(String.valueOf(servletContext.getRealPath("/resources")))).append("\\Images\\").append("logo.png").toString());
               // image.scaleAbsoluteHeight(90F);
                image.scaleAbsolute(100.0F,100.0F);
                doc.add(image);
                doc.add(pageHeader());
                doc.add(getTableJournal());
                doc.close();
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
                res.setHeader("Cache-Control", "Max-age=30");
                res.setContentType("application/pdf");
                res.setHeader("content-disposition", "inline;filename=JournalPaie.pdf");
                ServletOutputStream out = res.getOutputStream();
                res.setContentLength(docMem.size());
                docMem.writeTo(out);
                out.flush();
                out.close();
                context.responseComplete();
            } else
            {
                HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit d'affichage ");
            }
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
        table = new PdfPTable(2);
        PdfPCell cell = new PdfPCell();
        table.setWidthPercentage(105F);
        cell.setBorder(0);
        int largeurCollones[] = {
            50, 50
        };
        table.setWidths(largeurCollones);
        cell = HelperItext.getPdfCell(HelperC.convertDate(Calendar.getInstance().getTime()), FontFactory.getFont("Times-Roman", 8F, 0, BaseColor.BLACK), 1, 3, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        cell = HelperItext.getPdfCell((new StringBuilder("JOURNAL DE PAIE : MOIS DE ")).append(HelperC.moisEnLettres(mois).toUpperCase()).toString(), FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        cell = HelperItext.getPdfCell("  ", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
        table.addCell(cell);
        return table;
    } 

    private PdfPTable getTableJournal()
        throws DocumentException
    {
        PdfPTable tabInfo = null;
        ParametrageJournalC entete;
        if(listColonne != null && listColonne.size() > 0)
        {
            tabInfo = new PdfPTable(listColonne.size() + 2);
            tabInfo.setWidthPercentage(105F);
            int widthsInfo[] = new int[listColonne.size() + 2];
            List<ParametrageJournalElementC> listElt;
        
            for(int i = 0; i < widthsInfo.length; i++)
            {
                if(i == 0)
                {
                    widthsInfo[i] = 1;
                }
                if(i == 1)
                {
                    widthsInfo[i] = 5;
                } else
                {
                    widthsInfo[i] = 2;
                }
            }

            tabInfo.setWidths(widthsInfo);
            chargementEmploye();
            PdfPCell cell = new PdfPCell();
            double dataToPrint = 0.0D;
//            double totalBase = 0.0D;
//            double totalHsup = 0.0D;
//            double totalAllocation = 0.0D;
//            double totalPrimeScot = 0.0D;
//            double totalLogement = 0.0D;
//            double totalSalarial = 0.0D;
//            double totalDeduction = 0.0D;
//            double totalNet = 0.0D;
//            double totalBaseImposable = 0.0D;
//            double totalPatronal = 0.0D;
//            double totalPrimeHcot = 0.0D;
//            double totalCredit = 0.0D;
//            double totalJrAdd = 0.0D;
            int totNbreJrsAdd = 0;
            Phrase phrase = null;
            Paragraph p = null;
            phrase = new Phrase();
            phrase.add(new Chunk("Code ", FontFactory.getFont("Times", 8F, 1)));
            cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            phrase = new Phrase();
            phrase.add(new Chunk("Nom et pr�nom ", FontFactory.getFont("Times", 8F, 1)));
            cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
            tabInfo.addCell(cell);
            int index = 0;
            Map<String, Double> totaux = new HashMap<String, Double>();
            for(String s :listColonne)
            {
                
                index = listColonne.indexOf(s);
                phrase = new Phrase();
                phrase.add(new Chunk(s, FontFactory.getFont("Times", 8F, 1)));
                if(index == listColonne.size() - 1)
                {
                    cell = HelperItext.getCellule(phrase, 1, 0, 15, 0, 0.5F, 3F);
                } else
                {
                    cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
                }
                tabInfo.addCell(cell);
                totaux.put(s, 0.0);
            }

            if(listEmploye.size() > 0)
            {
                int j = 0,k=0;
                tabInfo.setHeaderRows(1);
              
                for(EmployeC employe:listEmploye)
                {
                	k++;
                    listeBulletin = FactoryDAO.getInstance().getListBulletinPaie(employe.getId(), dateDeb, dateFin, exercice.getId());
                    if(listeBulletin.size()>0)
                    {
                  
                    phrase = new Phrase();
                    phrase.add(new Chunk(employe.getCode(), FontFactory.getFont("Times", 8F, 0)));
                    cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
                    tabInfo.addCell(cell);
                    phrase = new Phrase();
                    phrase.add(new Chunk(employe.getNomPrenom(), FontFactory.getFont("Times", 8F, 0)));
                    cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
                    tabInfo.addCell(cell);
                    
                  
                    for(String col:listColonne)
                    {
                    	
                    	listElement=FichierBaseDAO.getInstance().getListParametrageJournal(col,idParamtre);
                        j++;
                    	
                        for(BulletinPaieC bulletin:listeBulletin)
                        {
                        	
                           for (ParametrageJournalDetailC det : listElement) 
                           {
							
                        	   listElt=FichierBaseDAO.getInstance().getListElementJournal(det.getId());
                        	   
                        	   for (ParametrageJournalElementC elt : listElt) 
                        	   {
								   					
										switch (det.getTypeElement()) {
										default:
											break;

										case 1: // '\001'
											switch (elt.getIdElement()) {
											case 1: // '\001'
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getSalaireBase();
													break;
												case 1:
													dataToPrint -= bulletin.getSalaireBase();
													break;
												}
												break;

											case 2: // '\002'
											
													switch (elt.getSigne()) {
													case 0:
														dataToPrint += bulletin.getTotalHS();
														break;
													case 1:
														dataToPrint -= bulletin.getTotalHS();
														break;
													}
												break;

											case 3: // '\003'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalLogement();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalLogement();
													break;
												}
												
												break;

											case 4: // '\004'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalAllocation();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalAllocation();
													break;
												}
												break;

											case 5: // '\005'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalPrimeScotisation();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalPrimeScotisation();
													break;
												}
												break;

											case 6: // '\006'
											
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalBaseImposable();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalBaseImposable();
													break;
												}
												
												break;

											case 7: // '\007'
											
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalCotisation();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalCotisation();
													break;
												}
												
												break;

											case 8: // '\b'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalPatronal();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalPatronal();
													break;
												}
												break;

											case 9: // '\t'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalPrimeHrCot();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalPrimeHrCot();
													break;
												}
												
												break;

											case 10: // '\n'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalDeduction();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalDeduction();
													break;
												}
										
												break;

											case 11: // '\013'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalCredit();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalCredit();
													break;
												}
												break;

											case 12: // '\f'
											
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalNetPay();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalNetPay();
													break;
												}
												
												break;

											case 13: // '\r'
												dataToPrint += bulletin
														.getNbreJourAdd();
												totNbreJrsAdd = (int) ((double) totNbreJrsAdd + dataToPrint);
												break;

											case 14: // '\016'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getTotalJourAdd();
													break;
												case 1:
													dataToPrint -= bulletin.getTotalJourAdd();
													break;
												}
												break;

											case 16: // '\020'
												
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += bulletin.getNombreHeureNormal();
													break;
												case 1:
													dataToPrint -= bulletin.getNombreHeureNormal();
													break;
												}
												
												break;

											case 18: // '\022'
												//dataToPrint += bulletin.getNombreHeureNormal()- bulletin.getNombreHeurePreste();
												switch (elt.getSigne()) {
												case 0:
													dataToPrint += (bulletin.getNombreHeureNormal()- bulletin.getNombreHeurePreste());
													break;
												case 1:
													dataToPrint -= (bulletin.getNombreHeureNormal()- bulletin.getNombreHeurePreste());
													break;
												}
												break;
											case 22:
												switch (elt.getSigne()) {
												case 0:
												dataToPrint+=bulletin.getTotalComission();
													break;
												case 1:
													dataToPrint-=bulletin.getTotalComission();
													break;
												}
												break;
											}
											break;

										case 2: // '\002'
											// dataToPrint +=FactoryDAO.getInstance().getTotalPrime(bulletin.getId(),elt.getIdElement());
											 switch (elt.getSigne()) {
												case 0:
													 dataToPrint +=FactoryDAO.getInstance().getTotalPrime(bulletin.getId(),elt.getIdElement());
													break;
												case 1:
													 dataToPrint -=FactoryDAO.getInstance().getTotalPrime(bulletin.getId(),elt.getIdElement());
													break;
												}
											break;

										case 3: // '\003'
									
											switch (elt.getSigne()) {
												case 0:
													 dataToPrint +=FactoryDAO.getInstance().getTotalCotisationSalarial(bulletin.getId(),elt.getIdElement(), getDateDeb(),getDateFin());
													break;
												case 1:
													 dataToPrint -=FactoryDAO.getInstance().getTotalCotisationSalarial(bulletin.getId(),elt.getIdElement(), getDateDeb(),getDateFin());
													break;
												}
											break;

										case 4: // '\004'
											
											 switch (elt.getSigne()) {
												case 0:
													 dataToPrint +=FactoryDAO.getInstance().getTotalCotisationPatronal(bulletin.getId(),elt.getIdElement(), getDateDeb(),getDateFin());
													break;
												case 1:
													 dataToPrint -=FactoryDAO.getInstance().getTotalCotisationPatronal(bulletin.getId(),elt.getIdElement(), getDateDeb(),getDateFin());
													break;
												}
											break;

										case 5: // '\005'
											
											 switch (elt.getSigne()) {
												case 0:
													 dataToPrint +=FactoryDAO.getInstance().getTotalDeduction(bulletin.getId(),elt.getIdElement());
													break;
												case 1:
													 dataToPrint -=FactoryDAO.getInstance().getTotalDeduction(bulletin.getId(),elt.getIdElement());
													break;
												}
											break;
										}
									}
                           }
                        }

                        p = new Paragraph();
                        p.add(new Chunk(HelperC.decimalNumber(dataToPrint, 0, true), FontFactory.getFont("Times", 8F, 0)));
                        p.setAlignment(2);
                        
                        if(j == listColonne.size())
                        {
                            cell = HelperItext.getCellule(p, 1, 0, 15, 0, 0.5F, 3F);
                        } else
                        {
                            cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
                        }
                        double tot=totaux.get(col);
                        totaux.put(col,tot+dataToPrint);
                        tabInfo.addCell(cell);
                        
                        tot=0;
                        dataToPrint = 0.0D;
                        
                    }
                  
                    j = 0;
                }
                }
                p = new Paragraph();
                p.add(new Chunk("Totaux : "+k, FontFactory.getFont("Times", 8F, 1)));
                cell = HelperItext.getCellule(p, 1, 0, 7, 2, 0.5F, 3F);
                tabInfo.addCell(cell);
                j = 0;
                for(String col:listColonne)
                {
//                	listElement=FichierBaseDAO.getInstance().getListParametrageJournal(col);
//                	
//                for(ParametrageJournalDetailC det:listElement)
//                {
//                
//                 listElt=FichierBaseDAO.getInstance().getListElementJournal(det.getId());
//                	  
//                  for (ParametrageJournalElementC elt : listElt) 
//               	   {
//                    j++;
//                    switch(det.getTypeElement())
//                    {
//                   
//                    default:
//                        break;
//
//                    case 1: // '\001'
//                        switch(elt.getIdElement())
//                        {
//                        case 1: // '\001'
//                            dataToPrint += totalBase;
//                            break;
//
//                        case 2: // '\002'
//                            dataToPrint += totalHsup;
//                            break;
//
//                        case 3: // '\003'
//                            dataToPrint += totalLogement;
//                            break;
//
//                        case 4: // '\004'
//                            dataToPrint += totalAllocation;
//                            break;
//
//                        case 5: // '\005'
//                            dataToPrint += totalPrimeScot;
//                            break;
//
//                        case 6: // '\006'
//                            dataToPrint += totalBaseImposable;
//                            break;
//
//                        case 7: // '\007'
//                            dataToPrint += totalSalarial;
//                            break;
//
//                        case 8: // '\b'
//                            dataToPrint += totalPatronal;
//                            break;
//
//                        case 9: // '\t'
//                            dataToPrint += totalPrimeHcot;
//                            break;
//
//                        case 10: // '\n'
//                            dataToPrint += totalDeduction;
//                            break;
//
//                        case 11: // '\013'
//                            dataToPrint += totalCredit;
//                            break;
//
//                        case 12: // '\f'
//                            dataToPrint += totalNet;
//                            break;
//
//                        case 13: // '\r'
//                            dataToPrint += totNbreJrsAdd;
//                            break;
//
//                        case 14: // '\016'
//                            dataToPrint += totalJrAdd;
//                            break;
//                        }
//                        break;
//
//                    case 2: // '\002'
//                        dataToPrint += FactoryDAO.getInstance().getTotalPrime(0, 0);
//                        break;
//
//                    case 3: // '\003'
//                        dataToPrint += totalSalarial;
//                        break;
//                    case 4: // '\004'
//                    	dataToPrint+=totalPatronal;
//                    	break;
//                    case 5: // '\005'
//                    	dataToPrint+=totalDeduction;
//                    	break;
//                    }
//                   
//               	   }
//                }
                p = new Paragraph();
                p.add(new Chunk(HelperC.decimalNumber(totaux.get(col), 0, true), FontFactory.getFont("Times", 8F, 1)));
                p.setAlignment(2);
                if(j == listElement.size())
                {
                    cell = HelperItext.getCellule(p, 1, 0, 15, 0, 0.5F, 3F);
                } else
                {
                    cell = HelperItext.getCellule(p, 1, 0, 7, 0, 0.5F, 3F);
                }
                tabInfo.addCell(cell);
                dataToPrint = 0.0D;
                
                }
            }
        }
        return tabInfo;
    }

    private void getTableJournalExcel()
            throws DocumentException
        {
           
    	 HSSFWorkbook wb = new HSSFWorkbook();
		 HSSFSheet sheet = wb.createSheet("ListeBE");		
		 HSSFRow row =null;
		 HSSFCellStyle cellStyle = null;
		 HSSFCellStyle style=null,style1 = null;
		
		 cellStyle = wb.createCellStyle();
		 cellStyle.setFillForegroundColor(HSSFColor.CORAL.index);
		 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		 style = wb.createCellStyle();
			
		 style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		 
            ParametrageJournalC entete;
            if(listColonne != null && listColonne.size() > 0)
            {
               
                List<ParametrageJournalElementC> listElt;
            
               
                chargementEmploye();
               
                double dataToPrint = 0.0D;

                int totNbreJrsAdd = 0;
               
                row = sheet.createRow(0);				
				cellStyle = wb.createCellStyle();
				row.createCell(0).setCellValue("");
				cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				row.getCell(0).setCellStyle(cellStyle);

				row.createCell(1).setCellValue("Matricule");
				cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				row.getCell(1).setCellStyle(cellStyle);

				row.createCell(2).setCellValue("Nom et Prenom");
				cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				row.getCell(2).setCellStyle(cellStyle);

				
                int index = 0,colmn=3;
                Map<String, Double> totaux = new HashMap<String, Double>();
                for(String s :listColonne)
                {
                    
                    index = listColonne.indexOf(s);
                
                    totaux.put(s, 0.0);
                    row.createCell(colmn).setCellValue(s);
    				cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
    				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    				row.getCell(colmn).setCellStyle(cellStyle);
    				
    				colmn++;
                }

                if(listEmploye.size() > 0)
                {
                    int k=1;
                    int n =0;
                  
                    for(EmployeC employe:listEmploye)
                    {
                    	
                     	
                        listeBulletin = FactoryDAO.getInstance().getListBulletinPaie(employe.getId(), dateDeb, dateFin, exercice.getId());
                        
                        if(listeBulletin.size()>0)
                        {
                      
                        	row = sheet.createRow(k);
        					HSSFCell cellE =null;
        					row.createCell(0).setCellValue(k);
                        
        					row.createCell(1).setCellValue(employe.getCode());
        					
        					row.createCell(2).setCellValue(employe.getNomPrenom());
        					
        					k++;
        					n = 3;
        					
                        for(String col:listColonne)
                        {
                        	
                        	listElement=FichierBaseDAO.getInstance().getListParametrageJournal(col,idParamtre);
                          
                         	
                            for(BulletinPaieC bulletin:listeBulletin)
                            {
                            	
                               for (ParametrageJournalDetailC det : listElement) 
                               {
    							
                            	   listElt=FichierBaseDAO.getInstance().getListElementJournal(det.getId());
                            	   
                            	   for (ParametrageJournalElementC elt : listElt) 
                            	   {
    								   					
    										switch (det.getTypeElement()) {
    										default:
    											break;

    										case 1: // '\001'
    											switch (elt.getIdElement()) {
    											case 1: // '\001'
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getSalaireBase();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getSalaireBase();
    													break;
    												}
    												break;

    											case 2: // '\002'
    											
    													switch (elt.getSigne()) {
    													case 0:
    														dataToPrint += bulletin.getTotalHS();
    														break;
    													case 1:
    														dataToPrint -= bulletin.getTotalHS();
    														break;
    													}
    												break;

    											case 3: // '\003'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalLogement();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalLogement();
    													break;
    												}
    												
    												break;

    											case 4: // '\004'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalAllocation();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalAllocation();
    													break;
    												}
    												break;

    											case 5: // '\005'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalPrimeScotisation();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalPrimeScotisation();
    													break;
    												}
    												break;

    											case 6: // '\006'
    											
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalBaseImposable();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalBaseImposable();
    													break;
    												}
    												
    												break;

    											case 7: // '\007'
    											
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalCotisation();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalCotisation();
    													break;
    												}
    												
    												break;

    											case 8: // '\b'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalPatronal();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalPatronal();
    													break;
    												}
    												break;

    											case 9: // '\t'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalPrimeHrCot();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalPrimeHrCot();
    													break;
    												}
    												
    												break;

    											case 10: // '\n'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalDeduction();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalDeduction();
    													break;
    												}
    										
    												break;

    											case 11: // '\013'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalCredit();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalCredit();
    													break;
    												}
    												break;

    											case 12: // '\f'
    											
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalNetPay();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalNetPay();
    													break;
    												}
    												
    												break;

    											case 13: // '\r'
    												dataToPrint += bulletin
    														.getNbreJourAdd();
    												totNbreJrsAdd = (int) ((double) totNbreJrsAdd + dataToPrint);
    												break;

    											case 14: // '\016'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getTotalJourAdd();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getTotalJourAdd();
    													break;
    												}
    												break;

    											case 16: // '\020'
    												
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += bulletin.getNombreHeureNormal();
    													break;
    												case 1:
    													dataToPrint -= bulletin.getNombreHeureNormal();
    													break;
    												}
    												
    												break;

    											case 18: // '\022'
    												//dataToPrint += bulletin.getNombreHeureNormal()- bulletin.getNombreHeurePreste();
    												switch (elt.getSigne()) {
    												case 0:
    													dataToPrint += (bulletin.getNombreHeureNormal()- bulletin.getNombreHeurePreste());
    													break;
    												case 1:
    													dataToPrint -= (bulletin.getNombreHeureNormal()- bulletin.getNombreHeurePreste());
    													break;
    												}
    												break;
    											case 22:
    												switch (elt.getSigne()) {
    												case 0:
    												dataToPrint+=bulletin.getTotalComission();
    													break;
    												case 1:
    													dataToPrint-=bulletin.getTotalComission();
    													break;
    												}
    												break;
    											}
    											break;

    										case 2: // '\002'
    											// dataToPrint +=FactoryDAO.getInstance().getTotalPrime(bulletin.getId(),elt.getIdElement());
    											 switch (elt.getSigne()) {
    												case 0:
    													 dataToPrint +=FactoryDAO.getInstance().getTotalPrime(bulletin.getId(),elt.getIdElement());
    													break;
    												case 1:
    													 dataToPrint -=FactoryDAO.getInstance().getTotalPrime(bulletin.getId(),elt.getIdElement());
    													break;
    												}
    											break;

    										case 3: // '\003'
    									
    											switch (elt.getSigne()) {
    												case 0:
    													 dataToPrint +=FactoryDAO.getInstance().getTotalCotisationSalarial(bulletin.getId(),elt.getIdElement(), getDateDeb(),getDateFin());
    													break;
    												case 1:
    													 dataToPrint -=FactoryDAO.getInstance().getTotalCotisationSalarial(bulletin.getId(),elt.getIdElement(), getDateDeb(),getDateFin());
    													break;
    												}
    											break;

    										case 4: // '\004'
    											
    											 switch (elt.getSigne()) {
    												case 0:
    													 dataToPrint +=FactoryDAO.getInstance().getTotalCotisationPatronal(bulletin.getId(),elt.getIdElement(), getDateDeb(),getDateFin());
    													break;
    												case 1:
    													 dataToPrint -=FactoryDAO.getInstance().getTotalCotisationPatronal(bulletin.getId(),elt.getIdElement(), getDateDeb(),getDateFin());
    													break;
    												}
    											break;

    										case 5: // '\005'
    											
    											 switch (elt.getSigne()) {
    												case 0:
    													 dataToPrint +=FactoryDAO.getInstance().getTotalDeduction(bulletin.getId(),elt.getIdElement());
    													break;
    												case 1:
    													 dataToPrint -=FactoryDAO.getInstance().getTotalDeduction(bulletin.getId(),elt.getIdElement());
    													break;
    												}
    											break;
    										}
    									}
                               }
                            }

                            
                            row.createCell(n).setCellValue(dataToPrint);
                         
                            double tot=totaux.get(col);
                            totaux.put(col,tot+dataToPrint);
                           
                            
                            tot=0;
                            dataToPrint = 0.0D;
                            n++;
                        }
                      
                      
                    }
                       
                    }
                    row = sheet.createRow(k+1);
                    row.createCell(1).setCellValue("Totaux : ");
                    row.createCell(2).setCellValue(" ");
                   
                    
                    int j = 3;
                    
                    for(String col:listColonne)
                    {

                    row.createCell(j).setCellValue(totaux.get(col));
                                    
                    j++;
                    }
                }
            }
            FacesContext context = FacesContext.getCurrentInstance();
	        HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();
	        res.setContentType("application/vnd.ms-excel");
	        res.setHeader("Content-disposition", "attachment;filename=journalPaie.xls");
	       
	        ServletOutputStream out;
			try {
				out = res.getOutputStream();
				 wb.write(out);
			        out.flush();
			        out.close();
			        FacesContext.getCurrentInstance().responseComplete();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        }

    public void chargementEmploye()
    {
    	listEmploye=null;
        listEmploye = FactoryDAO.getInstance().getAllEmploye(false,order);
    }

    private void chargementElement()
    {
    	
        listParametre.add(new SelectItem(Integer.valueOf(0), ""));
        listJournal = FichierBaseDAO.getInstance().getListJournalParametre();
        if(listJournal != null && listJournal.size() > 0)
        {
           
            for(ParametrageJournalC prm: listJournal)
            {
            	 listParametre.add(new SelectItem(prm.getId(),prm.getCode()+"-"+prm.getLibelle()));             
            }

        }
    }
    
    public void chargementColonne(){
    	DynamicTableC dyn=null;
    	chargementEmploye();
    	String[]coln = null;
    	double dataToPrint = 0.0;
        int totNbreJrsAdd = 0;
     
        showTbl=true;
        listDynamic=new ArrayList<DynamicTableC>();
   
        if(listElement!=null && listElement.size()>0)
		{
    		int i=0;
			for(ParametrageJournalDetailC det:listElement)
	         {
				dyn=new DynamicTableC();
				dyn.setNomColone(det.getTitrElement());
				dyn.setValeur(""+i);
			
				listDynamic.add(dyn);
				i++;
	         }
			  
		}
    		
    		int k=0;
             for(EmployeC employe:listEmploye)
             {
               
                listeBulletin = FactoryDAO.getInstance().getListBulletinPaie(employe.getId(), dateDeb, dateFin, exercice.getId());
                k=0;
                
                 for(ParametrageJournalDetailC det:listElement)
                 {
                	 
                	 coln=new String[listElement.size()];
                	 int j = 0;
                	 
                     for(BulletinPaieC bulletin:listeBulletin)
                     {
                        
                         switch(det.getTypeElement())
                         {
                         default:
                             break;

                         case 1: // '\001'
                             switch(0)
                             {
                             case 1: // '\001'
                                 dataToPrint += bulletin.getSalaireBase();
                                 break;

                             case 2: // '\002'
                                 dataToPrint += bulletin.getTotalHS();
                                 break;

                             case 3: // '\003'
                                 dataToPrint += bulletin.getTotalLogement();
                                 break;

                             case 4: // '\004'
                                 dataToPrint += bulletin.getTotalAllocation();
                                 break;

                             case 5: // '\005'
                                 dataToPrint += bulletin.getTotalPrimeScotisation();
                                 break;

                             case 6: // '\006'
                                 dataToPrint += bulletin.getTotalBaseImposable();
                                 break;

                             case 7: // '\007'
                                 dataToPrint += bulletin.getTotalCotisation();
                                 break;

                             case 8: // '\b'
                                 dataToPrint += bulletin.getTotalPatronal();
                                 break;

                             case 9: // '\t'
                                 dataToPrint += bulletin.getTotalPrimeHrCot();
                                 break;

                             case 10: // '\n'
                                 dataToPrint += bulletin.getTotalDeduction();
                                 break;

                             case 11: // '\013'
                                 dataToPrint += bulletin.getTotalCredit();
                                 break;

                             case 12: // '\f'
                                 dataToPrint += bulletin.getTotalNetPay();
                                 break;

                             case 13: // '\r'
                                 dataToPrint += bulletin.getNbreJourAdd();
                                 totNbreJrsAdd = (int)((double)totNbreJrsAdd + dataToPrint);
                                 break;

                             case 14: // '\016'
                                 dataToPrint += bulletin.getTotalJourAdd();
                                 break;

                             case 16: // '\020'
                                 dataToPrint += bulletin.getNombreHeureNormal();
                                 break;

                             case 18: // '\022'
                                 dataToPrint += bulletin.getNombreHeureNormal() - bulletin.getNombreHeurePreste();
                                 break;
                             }
                             break;

                         case 2: // '\002'
                            // dataToPrint += FactoryDAO.getInstance().getTotalPrime(bulletin.getId(), det.getIdElement());
                             break;

                         case 3: // '\003'
                             //dataToPrint = FactoryDAO.getInstance().getTotalCotisationSalarial(0, det.getIdElement(), getDateDeb(), getDateFin());
                             break;

                         case 4: // '\004'
                            // dataToPrint = FactoryDAO.getInstance().getTotalCotisationPatronal(0, det.getIdElement(), getDateDeb(), getDateFin());
                             break;

                         case 5: // '\005'
                           //  dataToPrint = FactoryDAO.getInstance().getTotalDeduction(bulletin.getId(), det.getIdElement());
                             break;
                         } 
                        
                     }
                   
                     coln[j]=HelperC.decimalNumber(dataToPrint, 0, true);
                     j++;
                     dataToPrint=0;
                     dyn=new DynamicTableC(); 				
      				 dyn.setValeur(""+j);
      				 dyn.setNomColone(HelperC.decimalNumber(dataToPrint, 0, true));
      			
      				
                 }
                
              
 				k++;
               
             }
		}
    
}
