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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.model.chart.PieChartModel;

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
import classesPaie.EmployeC;
import classesPaie.HelperC;
import classesPaie.HelperItext;
import classesPaie.ItextFooterHelper;
import classesPaie.ParametrageFinCarriereC;
import classesPaie.Tables;
import classesPaie.Tables.TableName;
@ManagedBean
@ViewScoped
public class ListeDesRetraites implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6038757355853050071L;
	private int idPrsnl,idCateg,idGrd,idNivFrm,idLieuxTrv,sexe,etatCivil,choise,type,order;
	private List<EmployeC> listRetraite;
	private List<SelectItem>listPersonnel,listCateg,listGrd,listNvfrm,listLieuTravail;
	HttpSession session = HelperC.getSession();
	private PieChartModel model;
	private String printDate;
	private Date dateRetraite;
	
	int choix;
	 List<Base> listPers,listCat;
	 List<EmployeC> listEmpl;
	 ParametrageFinCarriereC parm;
	public ListeDesRetraites() {
		
	}

	public int getIdPrsnl() {
		return idPrsnl;
	}

	public void setIdPrsnl(int idPrsnl) {
		this.idPrsnl = idPrsnl;
	}

	public int getIdCateg() {
		return idCateg;
	}

	public void setIdCateg(int idCateg) {
		this.idCateg = idCateg;
	}

	public int getIdGrd() {
		return idGrd;
	}

	public void setIdGrd(int idGrd) {
		this.idGrd = idGrd;
	}

	public int getIdNivFrm() {
		return idNivFrm;
	}

	public void setIdNivFrm(int idNivFrm) {
		this.idNivFrm = idNivFrm;
	}

	public int getIdLieuxTrv() {
		return idLieuxTrv;
	}

	public void setIdLieuxTrv(int idLieuxTrv) {
		this.idLieuxTrv = idLieuxTrv;
	}

	public int getSexe() {
		return sexe;
	}

	public void setSexe(int sexe) {
		this.sexe = sexe;
	}

	public int getEtatCivil() {
		return etatCivil;
	}

	public void setEtatCivil(int etatCivil) {
		this.etatCivil = etatCivil;
	}

	public int getChoise() {
		return choise;
	}

	public void setChoise(int choise) {
		this.choise = choise;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public List<EmployeC> getListRetraite() {
		return listRetraite;
	}

	public void setListRetraite(List<EmployeC> listRetraite) {
		this.listRetraite = listRetraite;
	}

	public List<SelectItem> getListPersonnel() {
		return listPersonnel;
	}

	public void setListPersonnel(List<SelectItem> listPersonnel) {
		this.listPersonnel = listPersonnel;
	}

	public List<SelectItem> getListCateg() {
		return listCateg;
	}

	public void setListCateg(List<SelectItem> listCateg) {
		this.listCateg = listCateg;
	}

	public List<SelectItem> getListGrd() {
		return listGrd;
	}

	public void setListGrd(List<SelectItem> listGrd) {
		this.listGrd = listGrd;
	}

	public List<SelectItem> getListNvfrm() {
		return listNvfrm;
	}

	public void setListNvfrm(List<SelectItem> listNvfrm) {
		this.listNvfrm = listNvfrm;
	}

	public List<SelectItem> getListLieuTravail() {
		return listLieuTravail;
	}

	public void setListLieuTravail(List<SelectItem> listLieuTravail) {
		this.listLieuTravail = listLieuTravail;
	}

	public PieChartModel getModel() {
		return model;
	}

	public void setModel(PieChartModel model) {
		this.model = model;
	}
	
	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public Date getDateRetraite() {
		return dateRetraite;
	} 

	public void setDateRetraite(Date dateRetraite) {
		this.dateRetraite = dateRetraite;
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
	    	 chargementPersonnel();
	    	 chargementCategorie();
	    	 chargementNivForm();
	    	 chargementGrade();
	    	 chargementLiexTrv();
	    	 model=new PieChartModel();
	    	 model.set(" ", 0);
	    	 listPers=new ArrayList<Base>();
	    	 listCat=new ArrayList<Base>();
	    	 listRetraite=new ArrayList<EmployeC>();
	    	 dateRetraite=new Date();
	    	 printDate=HelperC.convertDate(dateRetraite);
	    	 listEmpl=new ArrayList<EmployeC>();
	     }
	    	 
	     }
	private void chargementPersonnel() {
		listPersonnel= new ArrayList<SelectItem>();
		listPersonnel.add(new SelectItem(Integer.valueOf(0), ""));
	    listPers= FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.personnel));
	    
	    for (Base prs :listPers)
	    {
	    	listPersonnel.add(new SelectItem(Integer.valueOf(prs.getId()), prs.getDesignation()));
	    }
	   }

	  public void changePersonnel(ValueChangeEvent event) {
		  idPrsnl = ((Integer)event.getNewValue()).intValue();
		  
	   }
	  
	  private void chargementCategorie(){
		  listCateg= new ArrayList<SelectItem>();
		  listCateg.add(new SelectItem(Integer.valueOf(0), ""));
		  listCat=FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.categoriePersonnel));
		    for (Base cat : listCat)
		    {
		    	listCateg.add(new SelectItem(Integer.valueOf(cat.getId()), cat.getDesignation()));
		    }
	  }
	  
	  public void changeCategorie(ValueChangeEvent event) {
		  idCateg = ((Integer)event.getNewValue()).intValue();
		  
	   }
	  
	  private void chargementNivForm(){
		  listNvfrm= new ArrayList<SelectItem>();
		  listNvfrm.add(new SelectItem(Integer.valueOf(0), ""));
		    
		    for (Base nvf : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.niveauFormation)))
		    {
		    	listNvfrm.add(new SelectItem(Integer.valueOf(nvf.getId()), nvf.getDesignation()));
		    }
	  }
	  public void changerNiveauFormation(ValueChangeEvent event) {
		  idNivFrm = ((Integer)event.getNewValue()).intValue();
		  
	   } 
	  
	  private void chargementGrade(){
		  listGrd= new ArrayList<SelectItem>();
		  listGrd.add(new SelectItem(Integer.valueOf(0), ""));
		    
		    for (Base grd : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.gradePersonnel)))
		    {
		    	listGrd.add(new SelectItem(Integer.valueOf(grd.getId()), grd.getDesignation()));
		    }
	  }
	  public void changeGrade(ValueChangeEvent event) {
		  idGrd = ((Integer)event.getNewValue()).intValue();
		  
	   } 
	  private void chargementLiexTrv(){
		  listLieuTravail= new ArrayList<SelectItem>();
		  listLieuTravail.add(new SelectItem(Integer.valueOf(0), ""));
		    
		    for (Base lieux : FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.lieuxTravail)))
		    {
		    	listLieuTravail.add(new SelectItem(Integer.valueOf(lieux.getId()), lieux.getDesignation()));
		    }
	  }
	  public void changeLieuTravail(ValueChangeEvent event) {
		  idLieuxTrv = ((Integer)event.getNewValue()).intValue();
		  
	   } 
	 
	  public void changeChoice(){
		 choix=choise;
	  }
	  public void chargerEmploye(){
		  int nbrAn=0;
		  listRetraite.clear();
		  listEmpl.clear();
		  model=new PieChartModel();
	      model.set(" ", 0);
	      listEmpl=FactoryDAO.getInstance().getListEmploye(idPrsnl, idCateg, idGrd, idNivFrm, 0, sexe, etatCivil, idLieuxTrv,0,1,order);
	      for (EmployeC emp : listEmpl) {
	    	  
	    	  parm=FichierBaseDAO.getInstance().getParametrageFinCarriere(emp.getIdPersnl());
	    	  if(parm!=null)
	    	  {
	    		  if(emp.getDateNaissance()!=null)
	    		  {
	    		  nbrAn=HelperC.getYearFromDate(getDateRetraite())-HelperC.getYearFromDate(emp.getDateNaissance());
	    		  if(nbrAn>=parm.getAgeRetraite())
	    			  listRetraite.add(emp);
	    		  }
	    	  }
		  }
	      if(listRetraite.size()>0)
	      {
	    	  	 model.set("Pr�t � la retraite ", listRetraite.size());
				 model.setLegendPosition("e");
				 model.setShowDatatip(true);
				 model.setShowDataLabels(true);
				 
				 model.set("Encore actif ", listEmpl.size()-listRetraite.size());
				 model.setLegendPosition("e");
				 model.setShowDatatip(true);
				 model.setShowDataLabels(true);
	      }
	  }
	  public void changeDateRetraite(){
			 if (printDate.replace("/", "").replace("_", "").trim().equals("")) {
			    
			       setDateRetraite(null);
			     } else {
			      
			    	 setDateRetraite(HelperC.validerDate(printDate));
			       if (getDateRetraite() == null) {
			        printDate="";
			        HelperC.afficherMessage("Information", "Date invalide");
			     } else {
			         
			    	 printDate=HelperC.convertDate(getDateRetraite());
			       } 
			    } 
		}
	  
	  public void showModel()
	  {
		  model=new PieChartModel();
		  switch (choise) {
		case 1:
			modelByPersonel();
			break;
			
		case 2:
			modelBySex();			
			break;
			
		case 3:
			modelByCategorie();
			break;
		}
	  }
	 
	  private void modelByPersonel(){
		  int nbr=0,n=0;
		  listPers= FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.personnel));
		 if(listPers.size()>0)
		 {
			 for (Base b : listPers) 
			 {
				for (EmployeC emp : listRetraite) {
					if(b.getId()==emp.getIdPersnl())
					{
						nbr++;
					}
					if(emp.getIdPersnl()==0)
						n++;
				}
				model.set(b.getCode(), nbr);
				nbr=0;
			 }
			 model.set("-", n);
			 model.setLegendPosition("e");
			 model.setShowDatatip(true);
			 model.setShowDataLabels(true);
			
		 }
	  }
	  
	  private void modelByCategorie(){
		  int nbr=0,n=0;
		  listCat=FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.categoriePersonnel));
		 if(listCat.size()>0)
		 {
			 for (Base b : listCat) 
			 {
				for (EmployeC emp : listRetraite) {
					if(b.getId()==emp.getIdCatgrie())
					{
						nbr++;
					}
					if(emp.getIdCatgrie()==0)
						n++;
				}
				model.set(b.getCode(), nbr);
				nbr=0;
			 }
			 model.set("-", n);
			 model.setLegendPosition("e");
			 model.setShowDatatip(true);
			 model.setShowDataLabels(true);
			
		 }
	  }
	  
	  private void modelBySex(){
		  int m=0,f=0;
		  
		  for (EmployeC emp : listRetraite) {
			  switch (emp.getSexe()) {
			case 1:
				m++;
				break;

			case 2:
				f++;
				break;
			}
		  }
		  model.set("M", m);
		  model.set("F", f);
		  model.setLegendPosition("e");
		  model.setShowDatatip(true);
		  model.setShowDataLabels(true);
		 
		  
	  }
	  public void visualiser(){
		  if(listRetraite.size()>0)
			  printList();
	  }
	  private void printList()
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
	            image.scaleAbsoluteHeight(90F);
	            image.scaleAbsoluteWidth(180F);
	            doc.add(image);
	            writer.setPageEvent(new ItextFooterHelper(new Phrase("Produit GATECH", new Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 8F, 0))));
	            doc.add(pageHeader());
	            doc.add(getTableList());

	            doc.close();
	            FacesContext context = FacesContext.getCurrentInstance();
	            HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
	            res.setHeader("Cache-Control", "Max-age=100");
	            res.setContentType("application/pdf");
	            res.setHeader("content-disposition", "inline;filename=Liste_retraites.pdf");
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
		        table = new PdfPTable(2);
		        PdfPCell cell = new PdfPCell();
		        table.setWidthPercentage(100F);
		        table.setKeepTogether(true);
		        
		        cell.setBorder(0);
		        int largeurCollones[] = {
		            50, 50
		        };
		        table.setWidths(largeurCollones);
		        
		        cell = HelperItext.getPdfCell(HelperC.convertDate(Calendar.getInstance().getTime()), FontFactory.getFont("Times-Roman", 8F, 0, BaseColor.BLACK), 1, 3, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
		        table.addCell(cell);
		        
		        cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12F, 0, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
		        table.addCell(cell);
		        
		        cell = HelperItext.getPdfCell("LISTE DES EMPLOYES ACTIFS DONT LA RETRAITE COMMENCE AU "+HelperC.convertDate(getDateRetraite()), FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
		        table.addCell(cell);
		        
		        cell = HelperItext.getPdfCell("\n", FontFactory.getFont("Times-Roman", 8F, 0, BaseColor.BLACK), 1, 3, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
		        table.addCell(cell);
		        
		        return table;
		    }
	  private PdfPTable getTableList()
		        throws DocumentException
		    {
		        PdfPTable tabInfo = new PdfPTable(4);
		        int widthsInfo[] = {
		            10,10, 70,10
		        };
		        tabInfo.setWidthPercentage(100F);
		        tabInfo.setWidths(widthsInfo);
		        
		        PdfPCell cell = new PdfPCell();
		        
		        Phrase phrase = null;
		        Paragraph p = null;
		        
		      phrase = new Phrase();
              phrase.add(new Chunk("N�", FontFactory.getFont("Courier", 9F, 0)));
              cell = HelperItext.getCellule(phrase, 0, 1, 15, 1, 0.5F, 1.0F);
              tabInfo.addCell(cell);
              
		      phrase = new Phrase();
              phrase.add(new Chunk("Matricule", FontFactory.getFont("Courier", 9F, 0)));
              cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
              tabInfo.addCell(cell);
              
              phrase = new Phrase();
              phrase.add(new Chunk("Nom et Pr�nom", FontFactory.getFont("Courier", 9F, 0)));
              cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
              tabInfo.addCell(cell);
            
              phrase = new Phrase();
              phrase.add(new Chunk("Age", FontFactory.getFont("Courier", 9F, 0)));
              cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
              tabInfo.addCell(cell);
              
              tabInfo.setHeaderRows(1);
              
           int i=0;
           
		  for(EmployeC emp : listRetraite)
		    {
			  i++;
					  phrase = new Phrase();
		              phrase.add(new Chunk(""+i, FontFactory.getFont("Courier", 9F, 0)));
		              cell = HelperItext.getCellule(phrase, 0, 2, 14, 1, 0.5F, 1.0F);
		              tabInfo.addCell(cell);
            
		              phrase = new Phrase();
		              phrase.add(new Chunk(emp.getCode(), FontFactory.getFont("Courier", 9F, 0)));
		              cell = HelperItext.getCellule(phrase, 0, 1, 10, 1, 0.5F, 1.0F);
		              tabInfo.addCell(cell);
		               
		              p = new Paragraph();
		              p.add(new Chunk(emp.getNomPrenom(), FontFactory.getFont("Courier", 9F, 0)));
		              cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		              tabInfo.addCell(cell);
		                
		              p = new Paragraph();
		              p.add(new Chunk(""+emp.getAge(), FontFactory.getFont("Courier", 9F, 0)));
		              cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		              tabInfo.addCell(cell);
		      }

		       
		        return tabInfo;
		    }

}
