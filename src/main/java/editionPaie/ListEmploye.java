package editionPaie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;
import classesPaie.*;
import classesPaie.Tables.TableName;


@ManagedBean
@ViewScoped
public class ListEmploye {

	private int idPrsnl,idCateg,idGrd,idNivFrm,idLieuxTrv,sexe,etatCivil,choise,type,order;
	private List<EmployeC> listEmp;
	private List<SelectItem>listPersonnels,listCateg,listGrd,listNvfrm,listLieuTravail;
	private List<SelectItem> listColonne;
	private String[]selectedCols;
	
	HttpSession session = HelperC.getSession();
	private PieChartModel model;
	int choix;
	List<Base> listPers,listCat;
	String personel,categorie,grade,niveauFrm,fonction;
	
	public ListEmploye() {
		
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

	public List<EmployeC> getListEmp() {
		return listEmp;
	}

	public void setListEmp(List<EmployeC> listEmp) {
		this.listEmp = listEmp;
	}

	public List<SelectItem> getListPersonnels() {
		return listPersonnels;
	}

	public void setListPersonnels(List<SelectItem> listPersonnels) {
		this.listPersonnels = listPersonnels;
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

	public List<SelectItem> getListColonne() {
		return listColonne;
	}

	public void setListColonne(List<SelectItem> listColonne) {
		this.listColonne = listColonne;
	}

	public String[] getSelectedCols() {
		return selectedCols;
	}

	public void setSelectedCols(String[] selectedCols) {
		this.selectedCols = selectedCols;
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
	    	 listEmp=new ArrayList<EmployeC>();
	     }
	    	 
	     }
	

	private void chargementPersonnel() {
	    listPersonnels= new ArrayList<SelectItem>();
	    listPersonnels.add(new SelectItem(Integer.valueOf(0), ""));
	    listPers= FichierBaseDAO.getInstance().getAllBase(Tables.getTableName(TableName.personnel));
	    
	    for (Base prs :listPers)
	    {
	    	listPersonnels.add(new SelectItem(Integer.valueOf(prs.getId()), prs.getDesignation()));
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
		  listEmp.clear();
		  model=new PieChartModel();
	      model.set(" ", 0);
		  listEmp=FactoryDAO.getInstance().getListEmploye(idPrsnl, idCateg, idGrd, idNivFrm, 0, sexe, etatCivil, idLieuxTrv,0,choix,order);
	  }
	  
	  public void showModel()
	  {
		  model=new PieChartModel();
		  switch (type) {
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
				for (EmployeC emp : listEmp) {
					if(b.getId()==emp.getIdPersnl())
					{
						nbr++;
					}
					if(emp.getIdPersnl()==0)
						n++;
				}
				model.set(b.getDesignation(), nbr);
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
				for (EmployeC emp : listEmp) {
					if(b.getId()==emp.getIdCatgrie())
					{
						nbr++;
					}
					if(emp.getIdCatgrie()==0)
						n++;
				}
				model.set(b.getDesignation(), nbr);
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
		  
		  for (EmployeC emp : listEmp) {
			  switch (emp.getSexe()) {
			case 1:
				m++;
				break;

			case 2:
				f++;
				break;
			}
		  }
		  model.set("Masculin", m);
		  model.set("F�minin", f);
		  model.setLegendPosition("e");
		  model.setShowDatatip(true);
		  model.setShowDataLabels(true);
		 
		  
	  }
	  public void visualiser(){
		  if(listEmp.size()>0)
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
	            doc.addTitle("Liste employe");
	            
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
	            res.setHeader("content-disposition", "inline;filename=Liste_employes.pdf");
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
		        
		        cell = HelperItext.getPdfCell("LISTE DES EMPLOYES ", FontFactory.getFont("Times-Roman", 12F, 1, BaseColor.BLACK), 1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
		        table.addCell(cell);
		        
		        cell = HelperItext.getPdfCell("\n", FontFactory.getFont("Times-Roman", 8F, 0, BaseColor.BLACK), 1, 3, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
		        table.addCell(cell);
		        
		        return table;
		    }
	  private PdfPTable getTableList()
		        throws DocumentException
		    {
		        PdfPTable tabInfo = new PdfPTable(3);
		        int widthsInfo[] = {
		         1,2,7
		        };
		        tabInfo.setWidthPercentage(100F);
		        tabInfo.setWidths(widthsInfo);
		        
		        PdfPCell cell = new PdfPCell();
		        
		        Phrase phrase = null;
		        Paragraph p = null;
		        
		        phrase = new Phrase();
                phrase.add(new Chunk("N�", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 15, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                
		        phrase = new Phrase();
                phrase.add(new Chunk("Matricule", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                
                phrase = new Phrase();
                phrase.add(new Chunk("Nom et Pr�nom", FontFactory.getFont("Courier", 9F,1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);
                /*
                phrase = new Phrase();
                phrase.add(new Chunk("Date naissance", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);
                
                phrase = new Phrase();
                phrase.add(new Chunk("Date entr�e ", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);
                
                phrase = new Phrase();
                phrase.add(new Chunk("Date retraite", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);
                
                phrase = new Phrase();
                phrase.add(new Chunk("Personnel", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);
                
                phrase = new Phrase();
                phrase.add(new Chunk("Cat�gorie", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);
                
                phrase = new Phrase();
                phrase.add(new Chunk("Grade", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);
                
                phrase = new Phrase();
                phrase.add(new Chunk("Fonction", FontFactory.getFont("Courier", 9F, 1)));
                cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
                tabInfo.addCell(cell);
                tabInfo.setHeaderRows(1);*/
             int i=0;
             
		  for(EmployeC emp : listEmp)
		    {
			  i++;
			//  completeEmploye(emp);
			  
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
		                
		                /*
		                p = new Paragraph();
		                p.add(new Chunk(emp.getDateNaissanceS(), FontFactory.getFont("Courier", 9F, 0)));
		                cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		                tabInfo.addCell(cell);
		                
		                p = new Paragraph();
		                p.add(new Chunk(emp.getDateEntreS(), FontFactory.getFont("Courier", 9F, 0)));
		                cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		                tabInfo.addCell(cell);
		                
		                
		                p = new Paragraph();
		                p.add(new Chunk(emp.getDateRetraiteS(), FontFactory.getFont("Courier", 9F, 0)));
		                cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		                tabInfo.addCell(cell);
		                
		                p = new Paragraph();
		                p.add(new Chunk(personel, FontFactory.getFont("Courier", 9F, 0)));
		                cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		                tabInfo.addCell(cell);
		                
		                
		                p = new Paragraph();
		                p.add(new Chunk(categorie, FontFactory.getFont("Courier", 9F, 0)));
		                cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		                tabInfo.addCell(cell);
		                
		                p = new Paragraph();
		                p.add(new Chunk(grade, FontFactory.getFont("Courier", 9F, 0)));
		                cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		                tabInfo.addCell(cell);
		                
		                p = new Paragraph();
		                p.add(new Chunk(fonction, FontFactory.getFont("Courier", 9F, 0)));
		                cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 1.0F);
		                tabInfo.addCell(cell);
		                */
		      }

		       
		        return tabInfo;
		    }
	  private void completeEmploye(EmployeC emp){
			if(emp!=null)
			{
				Base fx = FichierBaseDAO.getInstance().getBaseById(emp.getIdFnctn(), Tables.getTableName(Tables.TableName.fonction));
				Base nv=FichierBaseDAO.getInstance().getBaseById(emp.getIdNvFormt(), Tables.getTableName(Tables.TableName.niveauFormation));
				Base catg=FichierBaseDAO.getInstance().getBaseById(emp.getIdCatgrie(), Tables.getTableName(Tables.TableName.categoriePersonnel));
				Base pers=FichierBaseDAO.getInstance().getBaseById(emp.getIdPersnl(), Tables.getTableName(Tables.TableName.personnel));
				Base grd=FichierBaseDAO.getInstance().getBaseById(emp.getIdGrd(), Tables.getTableName(Tables.TableName.gradePersonnel));
				if(fx!=null)
					fonction=fx.getDesignation();
				else
					fonction="";
				
				if(nv!=null)
					niveauFrm=nv.getDesignation();
				else
					niveauFrm="";
				
				if(catg!=null)
					categorie=catg.getDesignation();
				else
					categorie="";
				if(pers!=null)
					personel=pers.getDesignation();
				else
					personel="";
				if(grd!=null)
					grade=grd.getDesignation();
				else
					grade="";
				
			}
}
}
