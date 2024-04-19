package editionPaie;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import classesPaie.*;
import persistencePaie.*;
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
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

@ManagedBean
@ViewScoped
public class OrdreVirementB implements Serializable {

	private static final long serialVersionUID = 0x4ec9c769d9abe37cL;
	private List<SelectItem> listBank;
	private int mois;
	private int idBank;
	private String compteSociete;
	private String nomSociete,nomSignataire;
	private List<DetailBanqueEmployeC> listBanque;
	private BanqueC bank;
	private ExerciceC exercice;
	private HttpSession session;
	HttpServletRequest request;
	private OperateurC operateur;
	private DroitC droit;
	private Date datePaie;
	private String datePaieString;
	private CoordonneesSocieteC societe;
	Base userFonction;
	List<BanqueC> listBq;
	ParametrageDecideurSignataireC signataire;
	double totalVrt = 0;

	public OrdreVirementB() {
		session = HelperC.getSession();
	}

	public String getDatePaieString() {
		return datePaieString;
	}

	public void setDatePaieString(String datePaieString) {
		this.datePaieString = datePaieString;
	}

	public Date getDatePaie() {
		return datePaie;
	}

	public void setDatePaie(Date datePaie) {
		this.datePaie = datePaie;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public CoordonneesSocieteC getSociete() {
		return societe;
	}

	public void setSociete(CoordonneesSocieteC societe) {
		this.societe = societe;
	}

	public String getCompteSociete() {
		return compteSociete;
	}

	public void setCompteSociete(String compteSociete) {
		this.compteSociete = compteSociete;
	}

	public int getIdBank() {
		return idBank;
	}

	public void setIdBank(int idBank) {
		this.idBank = idBank;
	}

	public BanqueC getBank() {
		return bank;
	}

	public List<SelectItem> getListBank() {
		return listBank;
	}

	public void setListBank(List<SelectItem> listBank) {
		this.listBank = listBank;
	}

	public List<DetailBanqueEmployeC> getListBanque() {
		return listBanque;
	}

	public void setListBanque(List<DetailBanqueEmployeC> listBanque) {
		this.listBanque = listBanque;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public DroitC getDroit() {
		return droit;
	}

	public void setDroit(DroitC droit) {
		this.droit = droit;
	}

	public String getNomSignataire() {
		return nomSignataire;
	}

	public void setNomSignataire(String nomSignataire) {
		this.nomSignataire = nomSignataire;
	}

	@PostConstruct
	private void init() {
		operateur = new OperateurC();
		exercice = new ExerciceC();
		String codeOperateur = (String) session.getAttribute("operateur");
		String codeExercice = (String) session.getAttribute("exercice");
		operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);
		if (operateur == null || exercice == null) {
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			userFonction = FichierBaseDAO.getInstance().getFonctionActive(operateur.getIdEmploye());
			if (userFonction != null) {
				droit = FichierBaseDAO.getInstance().getDroit(userFonction.getId(),
						classesPaie.Constante.Role.bulletinPaie);
			}
			//chargementBank();
			
			signataire=FichierBaseDAO.getInstance().getSignataire(3);
			
			societe = FichierBaseDAO.getInstance().getCoordonneesSociete();
			if (societe != null) {
				nomSociete = societe.getNomSociete();
			}
		}
	}

	public void changeDatePaie() {
		if (getDatePaieString().replace("/", "").replace("_", "").trim().equals("")) {
			setDatePaie(null);
		} else {
			setDatePaie(HelperC.validerDate(getDatePaieString()));
			if (getDatePaie() == null) {
				setDatePaieString("");
			} else {
				setDatePaieString(HelperC.convertDate(getDatePaie()));
				setMois(HelperC.getMonthFromDate(getDatePaie()));
			}
		}
	}

	private void chargementBank() {
		listBank = new ArrayList<SelectItem>();
		listBank.add(new SelectItem(Integer.valueOf(0), ""));

		for (BanqueC bk : FichierBaseDAO.getInstance().getAllBanque()) {
			listBank.add(new SelectItem(Integer.valueOf(bk.getId()), bk.getCode() + "||" + bk.getDesignation()));
		}

	}

	public void changeBank(ValueChangeEvent event) {
		idBank = ((Integer) event.getNewValue()).intValue();
		bank = FichierBaseDAO.getInstance().getBanque(idBank);
		if (bank != null) {
			compteSociete = bank.getCompte();
		}
	}

	private void chargementBankEmploye() {
		listBanque = FactoryDAO.getInstance().getListeDetailBanque(idBank);

	}

	public void printOV() {
		if (getDatePaie() != null) {
			ovData();
		} else {
			HelperC.afficherMessage("Information", "Il faut préciser la date paie");
		}
	}

	public void ovData() {
		try {
			Image image = null;
			if (droit.isAfficher()) {
				Document doc = new Document(PageSize.A4);
				ByteArrayOutputStream docMem = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(doc, docMem);
				doc.addAuthor("GATECH");
				doc.addProducer();
				doc.addCreationDate();
				doc.addTitle("Ordre de virement");
				Rectangle rect = new Rectangle(90F, 20F, 550F, 500F);
				writer.setBoxSize("art", rect);
				HeaderFooterInfo footer = new HeaderFooterInfo();
				writer.setPageEvent(footer);
				doc.open();

				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
						.getContext();
				image = Image.getInstance((new StringBuilder(String.valueOf(servletContext.getRealPath("/resources"))))
						.append("\\Images\\").append("logo.png").toString());
				// image.scaleAbsoluteHeight(90F);
				image.scaleAbsolute(100.0F, 100.0F);
				doc.add(image);
				doc.add(pageHeader());
				doc.add(getSignataire());
				doc.newPage();
				doc.add(getTableOV());
				
				doc.close();
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();
				res.setHeader("Cache-Control", "Max-age=30");
				res.setContentType("application/pdf");
				res.setHeader("content-disposition", "inline;filename=OrdreVirement.pdf");
				ServletOutputStream out = res.getOutputStream();
				res.setContentLength(docMem.size());
				docMem.writeTo(out);
				out.flush();
				out.close();
				context.responseComplete();
			} else {
				HelperC.afficherMessage("ATTENTION", "Vous n'avez pas le droit d'affichage ");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private PdfPTable pageHeader() throws DocumentException, IOException {
		PdfPTable table = null;
		table = new PdfPTable(2);
		PdfPCell cell = new PdfPCell();
		table.setWidthPercentage(105F);
		cell.setBorder(0);
		int largeurCollones[] = { 50, 50 };
		calculTotalVirement();
		if (bank == null) {
			bank = FichierBaseDAO.getInstance().getBanqueSociete();
			if (bank != null)
				compteSociete = bank.getCompte();
		}
		table.setWidths(largeurCollones);
		Paragraph p = null;
		p = new Paragraph();
		p.add(new Chunk("Bujumbura, le"+HelperC.convertDate(Calendar.getInstance().getTime())+"\n\n", FontFactory.getFont("Verdana", 8F, 0)));
		p.setAlignment(2);
		cell = HelperItext.getCellule(p, 1, 0, 0, 2, 0.5F, 3F);
		table.addCell(cell);

		p = new Paragraph();
		p.add(new Chunk("", FontFactory.getFont("Times", 8F, 0)));
		p.setAlignment(2);
		cell = HelperItext.getCellule(p, 1, 0, 0, 2, 0.5F, 3F);
		table.addCell(cell);

		p = new Paragraph();
		p.add(new Chunk("Réf:SVG/MBR/......./ "+HelperC.getYearFromDate(datePaie), FontFactory.getFont("Verdana", 8F, 0)));
		cell = HelperItext.getCellule(p, 1, 0, 0, 0, 0.5F, 3F);
		table.addCell(cell);

		p = new Paragraph();
		p.add(new Chunk("A Monsieur l'Administrateur Directeur Général", FontFactory.getFont("Verdana", 8F, 1)));
		p.add(new Chunk("\nà", FontFactory.getFont("Times", 8F, 1)));
		p.add(new Chunk("\nBUJUMBURA", FontFactory.getFont("Verdana", 8F, 1)));
		p.setAlignment(1);
		cell = HelperItext.getCellule(p, 1, 0, 0, 0, 0.5F, 3F);
		table.addCell(cell);

		p = new Paragraph();
		p.add(new Chunk(" ", FontFactory.getFont("Times", 8F, 1)));
		p.setAlignment(2);
		cell = HelperItext.getCellule(p, 1, 0, 0, 2, 0.5F, 3F);
		table.addCell(cell);

		p = new Paragraph();
		p.add((new Chunk("Objet : ", FontFactory.getFont("Verdana", 9F, 1))).setUnderline(1.0F, -2F));
		p.add(new Chunk("Transfert de compte à compte\n\n", FontFactory.getFont("Verdana", 9F, 1)));
		cell = HelperItext.getCellule(p, 1, 0, 0, 2, 0.5F, 3F);
		table.addCell(cell);

		p = new Paragraph();
		p.add(new Chunk(" ", FontFactory.getFont("Times", 8F, 1)));
		p.setAlignment(2);
		cell = HelperItext.getCellule(p, 1, 0, 0, 2, 0.5F, 3F);
		table.addCell(cell);

		p = new Paragraph();
		p.add(new Chunk("Monsieur l'Administrateur Directeur Général,\n\n ", FontFactory.getFont("Verdana", 9F, 0)));
		p.add(new Chunk("Par le débitt de notre compte BIF N° ",FontFactory.getFont("Verdana", 9F, 0)));
		
		p.add(new Chunk(compteSociete, FontFactory.getFont("Verdana", 9F, 1)));
		
		p.add(new Chunk(" nous vous prions de bien vouloir créditer les comptes de nos employés conformement aux listes annexes, pour un montant total de : ", FontFactory.getFont("Verdana", 9F, 0)));
		
		p.add(new Chunk(HelperC.TraitementMontant.getMontantEnLettre (totalVrt), FontFactory.getFont("Verdana", 9F, 1)));
		p.add(new Chunk(" ("+HelperC.decimalNumber(totalVrt, 0, true)+") ", FontFactory.getFont("Verdana", 9F, 1)));
		p.add(new Chunk(" avec mention ", FontFactory.getFont("Verdana", 9F, 0)));
		p.add(new Chunk("Paiement salaire " + HelperC.moisEnLettres(mois).toUpperCase()+".",
				FontFactory.getFont("Verdana", 9F, 1)));
		p.add(new Chunk(
				"\n\nVeuillez aggréer Monsieur l'Administrateur Directeur Général, l'expression de notre considération distingueé. ",
				FontFactory.getFont("Verdana", 9F, 0)));

		cell = HelperItext.getCellule(p, 1, 0, 0, 2, 0.5F, 3F);
		table.addCell(cell);

		
		p = new Paragraph();
		p.add(new Chunk(" ", FontFactory.getFont("Verdana", 8F, 1)));
		p.setAlignment(2);
		cell = HelperItext.getCellule(p, 1, 0, 0, 2, 0.5F, 3F);
		table.addCell(cell);

		return table;
	}
	private PdfPTable getSignataire() throws DocumentException, IOException {
		PdfPTable table = null;
		table = new PdfPTable(2);
		PdfPCell cell = new PdfPCell();
		table.setWidthPercentage(105F);
		cell.setBorder(0);
		int largeurCollones[] = { 50, 50 };
		
		table.setWidths(largeurCollones);
		Paragraph p = null;
		
		for (ParametrageDecideurDetailC det  : signataire.getListDetail()) {
			
			p = new Paragraph();
			p.add(new Chunk("", FontFactory.getFont("Verdana", 8F, 0)));
			p.setAlignment(2);
			cell = HelperItext.getCellule(p, 1, 0, 0, 0, 0.5F, 3F);
			table.addCell(cell);
			
			p = new Paragraph();
			p.add(new Chunk("\n\n\n"+det.getNomEmploye(), FontFactory.getFont("Verdana", 8F, 0)));
			cell = HelperItext.getCellule(p, 1, 0, 0, 0, 0.5F, 3F);
			table.addCell(cell);
		}
		

		
		return table;
	}
	private void calculTotalVirement() {
		EmployeC employe = null;
		BulletinPaieC bulletin = null;
		double montantVrt = 0;
		totalVrt=0;
		listBq = FichierBaseDAO.getInstance().getAllBanque();
		for (BanqueC b : listBq) {
			idBank = b.getId();
			chargementBankEmploye();

			for (DetailBanqueEmployeC detBk : listBanque) {

				employe = FactoryDAO.getInstance().getEmploye(detBk.getIdEmploye(), false);
				if (employe != null) {
					bulletin = FactoryDAO.getInstance().getBulletinPaie(employe.getId(), getDatePaie(),
							exercice.getId());

					if (bulletin != null && bulletin.getTotalNetPay() > 0.0D) {

						if (detBk.getPourcentageSalaire() > 0.0D) {
							montantVrt = (bulletin.getTotalNetPay() * detBk.getPourcentageSalaire()) / 100D;
						} else {
							montantVrt = bulletin.getTotalNetPay();
						}
						totalVrt += montantVrt;
					}
				}
			}
		}
	}

	private PdfPTable getTableVirement() throws DocumentException {
		PdfPTable tabInfo = new PdfPTable(5);
		int widthsInfo[] = { 10, 25, 30, 15, 10 };
		tabInfo.setWidthPercentage(105F);
		tabInfo.setWidths(widthsInfo);
		EmployeC employe = null;
		BulletinPaieC bulletin = null;
		chargementBankEmploye();
		double montantVrt = 0.0D;
		totalVrt = 0.0D;
		int nbr = 0;
		Phrase phrase = null;
		Paragraph p = null;
		PdfPCell cell = new PdfPCell();

		if (listBanque.size() > 0) {
			phrase = new Phrase();
			phrase.add(new Chunk("Code ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk("B\351n\351ficiaire ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk("Banque ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk("N\260 compte ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk("Montant ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 15, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
			tabInfo.setHeaderRows(1);

			for (DetailBanqueEmployeC detBk : listBanque) {

				employe = FactoryDAO.getInstance().getEmploye(detBk.getIdEmploye(), false);
				if (employe != null) {
					bulletin = FactoryDAO.getInstance().getBulletinPaie(employe.getId(), getDatePaie(),
							exercice.getId());

					if (bulletin != null && bulletin.getTotalNetPay() > 0.0D) {
						nbr++;
						if (detBk.getPourcentageSalaire() > 0.0D) {
							montantVrt = (bulletin.getTotalNetPay() * detBk.getPourcentageSalaire()) / 100D;
						} else {
							montantVrt = bulletin.getTotalNetPay();
						}
						totalVrt += montantVrt;
						p = new Paragraph();
						p.add(new Chunk(employe.getCode(), FontFactory.getFont("Times", 8F, 0)));
						cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
						tabInfo.addCell(cell);
						p = new Paragraph();
						p.add(new Chunk(employe.getNomPrenom(), FontFactory.getFont("Times", 8F, 0)));
						cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
						tabInfo.addCell(cell);
						p = new Paragraph();
						p.add(new Chunk(bank.getDesignation(), FontFactory.getFont("Times", 8F, 0)));
						cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
						tabInfo.addCell(cell);
						p = new Paragraph();
						p.add(new Chunk(detBk.getNumeroCompte(), FontFactory.getFont("Times", 8F, 0)));
						cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
						tabInfo.addCell(cell);
						p = new Paragraph();
						p.add(new Chunk(HelperC.decimalNumber(montantVrt, 0, true),
								FontFactory.getFont("Times", 8F, 0)));
						p.setAlignment(2);
						cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
						tabInfo.addCell(cell);
					}
				}
				montantVrt = 0.0D;
			}

			totalVrt = Double.parseDouble(HelperC.decimalNumber(totalVrt, 0, true).replaceAll(" ", ""));
			phrase = new Phrase();
			phrase.add(new Chunk("Nombre : ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 4, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk((new StringBuilder(String.valueOf(HelperC.decimalNumber(nbr, 0, true)))).append(" ")
					.append(classesPaie.HelperC.TraitementMontant.getMontantEnLettre(nbr).toUpperCase()).toString(),
					FontFactory.getFont("Times", 8F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 8, 4, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk("Montant total : ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 4, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk(HelperC.decimalNumber(totalVrt, 0, true), FontFactory.getFont("Times", 8F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 8, 4, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk("Soit : ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 4, 0, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk(classesPaie.HelperC.TraitementMontant.getMontantEnLettre(totalVrt).toUpperCase(),
					FontFactory.getFont("Times", 8F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 8, 4, 0.5F, 3F);
			tabInfo.addCell(cell);
			phrase = new Phrase();
			phrase.add(new Chunk("Mention : ", FontFactory.getFont("Times", 8F, 1)));
			phrase.add(new Chunk(
					(new StringBuilder("TRAITEMENT ET INDEMNITE DU MOIS DE "))
							.append(HelperC.moisEnLettres(mois).toUpperCase()).toString(),
					FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 15, 5, 0.5F, 3F);
			tabInfo.addCell(cell);
		}
		return tabInfo;
	}

	private PdfPTable getTableOV() throws DocumentException {
		PdfPTable tabInfo = new PdfPTable(4);
		int widthsInfo[] = { 5, 25, 15, 10 };
		tabInfo.setWidthPercentage(105F);
		tabInfo.setWidths(widthsInfo);
		EmployeC employe = null;
		BulletinPaieC bulletin = null;
	

		double montantVrt = 0.0D;
		totalVrt = 0.0D;
		double subTot = 0;
		int nbr = 0;
		Phrase phrase = null;
		Paragraph p = null;
		PdfPCell cell = new PdfPCell();

		

		if (listBq.size() > 0) {
			phrase = new Phrase();
			phrase.add(new Chunk("N° ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Bénéficiaire ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("N° compte ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 7, 0, 0.5F, 3F);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Montant ", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 15, 0, 0.5F, 3F);
			tabInfo.addCell(cell);

			tabInfo.setHeaderRows(1);

			for (BanqueC b : listBq) {
				idBank = b.getId();
				chargementBankEmploye();
				subTot = 0;
				if (listBanque.size() > 0) {

					p = new Paragraph();
					p.add(new Chunk(b.getDesignation(), FontFactory.getFont("Times", 8F, 1)));
					cell = HelperItext.getCellule(p, 1, 0, 14, 4, 0.5F, 3F);
					tabInfo.addCell(cell);

					for (DetailBanqueEmployeC detBk : listBanque) {

						employe = FactoryDAO.getInstance().getEmploye(detBk.getIdEmploye(), false);
						if (employe != null) {
							bulletin = FactoryDAO.getInstance().getBulletinPaie(employe.getId(), getDatePaie(),
									exercice.getId());

							if (bulletin != null && bulletin.getTotalNetPay() > 0.0D) {
								nbr++;
								if (detBk.getPourcentageSalaire() > 0.0D) {
									montantVrt = (bulletin.getTotalNetPay() * detBk.getPourcentageSalaire()) / 100D;
								} else {
									montantVrt = bulletin.getTotalNetPay();
								}

								subTot += montantVrt;

								totalVrt += montantVrt;
								p = new Paragraph();
								p.add(new Chunk(""+nbr, FontFactory.getFont("Times", 8F, 0)));
								cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
								tabInfo.addCell(cell);

								p = new Paragraph();
								p.add(new Chunk(employe.getNomPrenom(), FontFactory.getFont("Times", 8F, 0)));
								cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
								tabInfo.addCell(cell);

								p = new Paragraph();
								p.add(new Chunk(detBk.getNumeroCompte(), FontFactory.getFont("Times", 8F, 0)));
								cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
								tabInfo.addCell(cell);

								p = new Paragraph();
								p.add(new Chunk(HelperC.decimalNumber(montantVrt, 0, true),
										FontFactory.getFont("Times", 8F, 0)));
								p.setAlignment(2);
								cell = HelperItext.getCellule(p, 1, 0, 14, 0, 0.5F, 3F);
								tabInfo.addCell(cell);
							}
						}
						montantVrt = 0.0D;
					}

					p = new Paragraph();
					p.add(new Chunk("Total " + b.getDesignation(), FontFactory.getFont("Times", 8F, 1)));
					cell = HelperItext.getCellule(p, 1, 0, 14, 3, 0.5F, 3F);
					tabInfo.addCell(cell);

					p = new Paragraph();
					p.add(new Chunk(HelperC.decimalNumber(subTot, 0, true), FontFactory.getFont("Times", 8F, 1)));
					p.setAlignment(2);
					cell = HelperItext.getCellule(p, 1, 0, 14, 3, 0.5F, 3F);
					tabInfo.addCell(cell);
				}
			}
			
			
			p = new Paragraph();
			p.add(new Chunk("Total général", FontFactory.getFont("Times", 8F, 1)));
			cell = HelperItext.getCellule(p, 1, 0, 14, 3, 0.5F, 3F);
			tabInfo.addCell(cell);

			p = new Paragraph();
			p.add(new Chunk(HelperC.decimalNumber(totalVrt, 0, true), FontFactory.getFont("Times", 8F, 1)));
			p.setAlignment(2);
			cell = HelperItext.getCellule(p, 1, 0, 14, 3, 0.5F, 3F);
			tabInfo.addCell(cell);
			
			  
			 
			 
		}
		return tabInfo;
	}
	
	@SuppressWarnings("deprecation")
	public void printOVExcel() {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("ordre virement");
		HSSFRow row = null;
		HSSFCellStyle cellStyle = null;
		HSSFCellStyle style = null, style1 = null,style2;
		HSSFFont font = wb.createFont();
		font.setBold(true);
		
		cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor((short) 29);
		cellStyle.setFillPattern((short) 1);

		style = wb.createCellStyle();

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

	
		EmployeC employe = null;
		BulletinPaieC bulletin = null;
		listBq = FichierBaseDAO.getInstance().getAllBanque();
		double montantVrt = 0.0D;
		totalVrt = 0.0D;

		if (listBq.size() > 0) {

			row = sheet.createRow(0);
			cellStyle = wb.createCellStyle();
			cellStyle.setFont(font);
			row.createCell(0).setCellValue("N°");
			cellStyle.setFillForegroundColor((short) 13);
			cellStyle.setFillPattern((short) 1);
			row.getCell(0).setCellStyle(cellStyle);

			row.createCell(1).setCellValue("Bénéficiaire");
			cellStyle.setFillForegroundColor((short) 13);
			cellStyle.setFillPattern((short) 1);
			row.getCell(1).setCellStyle(cellStyle);

			row.createCell(2).setCellValue("N° compte");
			cellStyle.setFillForegroundColor((short) 13);
			cellStyle.setFillPattern((short) 1);
			row.getCell(2).setCellStyle(cellStyle);

			row.createCell(3).setCellValue("Montant");
			cellStyle.setFillForegroundColor((short) 13);
			cellStyle.setFillPattern((short) 1);
			row.getCell(3).setCellStyle(cellStyle);

			double subTot = 0;
			int i = 1, k = 0;
			for (BanqueC b : listBq) {

				idBank = b.getId();
				chargementBankEmploye();
				subTot = 0;
				
			

				row = sheet.createRow(i);
				row.createCell(0).setCellValue(b.getDesignation());
				sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
			
			
				
				for (DetailBanqueEmployeC detBk : listBanque) {
					
					
					employe = FactoryDAO.getInstance().getEmploye(detBk.getIdEmploye(), false);
					if (employe != null) {
						bulletin = FactoryDAO.getInstance().getBulletinPaie(employe.getId(), getDatePaie(),
								exercice.getId());
						
						if (bulletin != null && bulletin.getTotalNetPay() > 0.0D) {

							if (detBk.getPourcentageSalaire() > 0.0D) {
								montantVrt = (bulletin.getTotalNetPay() * detBk.getPourcentageSalaire()) / 100D;
							} else {
								montantVrt = bulletin.getTotalNetPay();
							}
							i++;
							k++;
							subTot += montantVrt;

							totalVrt += montantVrt;

							row = sheet.createRow(i);
							HSSFCell cellE = null;

							row.createCell(0).setCellValue(k);

							row.createCell(1).setCellValue(employe.getNomPrenom());

							row.createCell(2).setCellValue(detBk.getNumeroCompte());

							row.createCell(3).setCellValue(montantVrt);
							cellE = row.getCell(3);
							cellE.setCellType(0);
							row.getCell(3).setCellStyle(style);

						}
					}
					
				}
				
				i++;
				
				style2 = wb.createCellStyle();
				style2.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
				style2.setFont(font);
				row = sheet.createRow(i);
				row.createCell(0).setCellValue("Sous total :");
				sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 2));
				row.getCell(0).setCellStyle(style2);
				
				
				row.createCell(3).setCellValue(subTot);
				//row.getCell(3).setCellType(0);			
				row.getCell(3).setCellStyle(style2);
				
				i++;
				
			}
			
			HSSFCell cellT = null;
			style1 = wb.createCellStyle();
			style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
			style1.setFillForegroundColor((short) 22);
			style1.setFillPattern((short) 1);
			style1.setFont(font);
			
			row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue("TOTAL :");
			sheet.addMergedRegion(new CellRangeAddress(i + 1, i + 1, 0, 2));
			row.getCell(0).setCellStyle(style1);

			row.createCell(3).setCellValue(totalVrt);
			cellT = row.getCell(3);
			cellT.setCellType(0);
			row.getCell(3).setCellStyle(style1);

		
		}
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-disposition", "attachment;filename=ordreVirement.xls");

		try {
			ServletOutputStream out = res.getOutputStream();
			wb.write((OutputStream) out);
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
