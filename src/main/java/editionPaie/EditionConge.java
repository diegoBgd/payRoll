package editionPaie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistencePaie.FactoryDAO;
import persistencePaie.FichierBaseDAO;

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
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPageEvent;

import classesPaie.Base;
import classesPaie.Constante;
import classesPaie.EmployeC;
import classesPaie.ExerciceC;
import classesPaie.HelperC;
import classesPaie.HelperItext;
import classesPaie.HistoriqueCongeC;
import classesPaie.ItextFooterHelper;
import classesPaie.OperateurC;
import classesPaie.SaisieDemandeCongeC;
import classesPaie.Tables;
import classesPaie.TypeCongeC;

@ManagedBean
@ViewScoped
public class EditionConge implements Serializable {
	private int idEmploye;
	private int idTypeConge;
	private int idExercice;
	private int nbrJrConge;
	private EmployeC employe;
	private List<HistoriqueCongeC> listConge;
	private List<SelectItem> listTypeConge = new ArrayList<SelectItem>();
	private HttpSession session = HelperC.getSession();
	private SaisieDemandeCongeC conge;
	private OperateurC operateur;
	private ExerciceC exercice;
	List<EmployeC> listEmp;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1043692575816948589L;

	public EditionConge() {

	}

	public int getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public int getIdTypeConge() {
		return idTypeConge;
	}

	public void setIdTypeConge(int idTypeConge) {
		this.idTypeConge = idTypeConge;
	}

	public SaisieDemandeCongeC getConge() {
		return conge;
	}

	public void setConge(SaisieDemandeCongeC conge) {
		this.conge = conge;
	}

	public EmployeC getEmploye() {
		return employe;
	}

	public void setEmploye(EmployeC employe) {
		this.employe = employe;
	}

	public int getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

	public int getNbrJrConge() {
		return nbrJrConge;
	}

	public void setNbrJrConge(int nbrJrConge) {
		this.nbrJrConge = nbrJrConge;
	}

	public List<HistoriqueCongeC> getListConge() {
		return listConge;
	}

	public void setListConge(List<HistoriqueCongeC> listConge) {
		this.listConge = listConge;
	}

	public List<SelectItem> getListTypeConge() {
		return listTypeConge;
	}

	public void setListTypeConge(List<SelectItem> listTypeConge) {
		this.listTypeConge = listTypeConge;
	}

	public void chargerTypeConge(ValueChangeEvent event) {
		this.idTypeConge = ((Integer) event.getNewValue()).intValue();

	}

	@PostConstruct
	private void charger() {
		this.operateur = new OperateurC();
		this.exercice = new ExerciceC();
		String codeOperateur = (String) this.session.getAttribute("operateur");
		String codeExercice = (String) this.session.getAttribute("exercice");

		this.operateur = FichierBaseDAO.getInstance().getOperateur(codeOperateur);
		this.exercice = FichierBaseDAO.getInstance().getExercice(codeExercice);

		if (this.operateur == null || this.exercice == null) {
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("/payRoll/login.xhtml");

			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			idExercice = exercice.getId();
			this.listTypeConge.add(new SelectItem(0, ""));

			for (TypeCongeC conge : FichierBaseDAO.getInstance().getAllTypeConge()) {
				this.listTypeConge.add(new SelectItem(Integer.valueOf(conge.getId()), conge.getDesignation()));
			}

		}
	}

	public void visualiser() {
		// Image image = null;

		try {
			Document doc = new Document(PageSize.A4);
			ByteArrayOutputStream docMem = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(doc, docMem);
			doc.addAuthor("Crost Soft");
			doc.addProducer();
			doc.addCreationDate();
			doc.addTitle("Bulletin de paie");
			doc.open();

			doc.add(logoDocument());
			writer.setPageEvent((PdfPageEvent) new ItextFooterHelper(
					new Phrase("Produit gatech", new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0))));
			doc.add(pageHeader("DEMANDE DE CONGE"));
			doc.add(formulaireContenu());

			doc.close();
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();
			res.setHeader("Cache-Control", "Max-age=100");
			res.setContentType("application/pdf");
			res.setHeader("content-disposition", "inline;filename=FicheConge.pdf");
			ServletOutputStream out = res.getOutputStream();
			res.setContentLength(docMem.size());
			docMem.writeTo(out);
			out.flush();
			out.close();
			context.responseComplete();
		} catch (Exception e) {
			System.out.println("Visualisation: " + e.getMessage());
		}
	}

	public void printListConge() {
		// Image image = null;

		try {
			Document doc = new Document(PageSize.A4);
			ByteArrayOutputStream docMem = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(doc, docMem);
			doc.addAuthor("Crost Soft");
			doc.addProducer();
			doc.addCreationDate();
			doc.addTitle("Solde des congé");
			doc.open();

			doc.add(logoDocument());
			writer.setPageEvent((PdfPageEvent) new ItextFooterHelper(
					new Phrase("Produit gatech", new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0))));
			doc.add(pageHeader("SOLDE DES CONGES"));
			doc.add(chargeListConge());

			doc.close();
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();
			res.setHeader("Cache-Control", "Max-age=100");
			res.setContentType("application/pdf");
			res.setHeader("content-disposition", "inline;filename=listConge.pdf");
			ServletOutputStream out = res.getOutputStream();
			res.setContentLength(docMem.size());
			docMem.writeTo(out);
			out.flush();
			out.close();
			context.responseComplete();
		} catch (Exception e) {
			System.out.println("Visualisation: " + e.getMessage());
		}
	}

	private PdfPTable logoDocument() throws DocumentException, IOException {
		PdfPTable table = null;
		table = new PdfPTable(2);
		PdfPCell cell = new PdfPCell();
		table.setWidthPercentage(100.0F);
		cell.setBorder(0);
		int[] largeurCollones = { 80, 20 };
		String ip = "";
		InetAddress localhost = InetAddress.getLocalHost();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getRequestURL().toString();

		Image image = null;
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		image = Image.getInstance(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\" + "logo.png");
		// image.scaleAbsoluteHeight(90.0F);
		image.scaleAbsolute(100.0F, 100.0F);
		ip = localhost.getHostAddress();
		BarcodeQRCode barcodeQRCode = new BarcodeQRCode(url.replaceAll("localhost", ip), 1000, 1000, null);
		Image codeQrImage = barcodeQRCode.getImage();
		codeQrImage.scaleAbsolute(100.0F, 100.0F);

		table.setWidths(largeurCollones);
		cell = HelperItext.getCellule(image, 1, 0, 0, 0, 0.0F, 3.0F);
		table.addCell(cell);

		cell = HelperItext.getCellule(codeQrImage, 1, 0, 0, 0, 0.0F, 3.0F);
		table.addCell(cell);

		return table;
	}

	private PdfPTable pageHeader(String titre) throws DocumentException, IOException {
		PdfPTable table = null;
		table = new PdfPTable(2);
		PdfPCell cell = new PdfPCell();
		table.setWidthPercentage(100.0F);
		cell.setBorder(0);
		int[] largeurCollones = { 50, 50 };
		table.setWidths(largeurCollones);
		cell = HelperItext.getPdfCell(HelperC.convertDate(Calendar.getInstance().getTime()),
				FontFactory.getFont("Times-Roman", 8.0F, 0, BaseColor.BLACK), 1, 3, 0, 2, BaseColor.WHITE,
				BaseColor.WHITE, 1);
		table.addCell(cell);
		cell = HelperItext.getPdfCell("", FontFactory.getFont("Times-Roman", 12.0F, 0, BaseColor.BLACK), 1, 1, 0, 2,
				BaseColor.WHITE, BaseColor.WHITE, 1);
		table.addCell(cell);

		cell = HelperItext.getPdfCell(titre, FontFactory.getFont("Times-Roman", 12.0F, 1, BaseColor.BLACK),
				1, 1, 0, 2, BaseColor.WHITE, BaseColor.WHITE, 1);
		table.addCell(cell);
		return table;
	}

	private PdfPTable formulaireContenu() throws DocumentException, IOException {
		PdfPTable tabInfo = new PdfPTable(4);
		int[] widthsInfo = { 30, 5, 20, 25 };
		tabInfo.setWidthPercentage(100.0F);
		tabInfo.setWidths(widthsInfo);
		tabInfo.setKeepTogether(true);
		PdfPCell cell = new PdfPCell();
		String etatCivil = "", sexe = "", personnel = "", categorie = "";
		String grade = "", formation = "", fonction = "", contrat = "";
		String lieuxTravail = "", libelleConge = "", printDateDebut = "", printDateFin = "", dure = "",
				libelleDecision = "", dateDecision = "";
		int jrPrs = 0, jrsRst = 0;
		if (this.employe != null) {

			Image image = null;
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			if (this.employe.getUrlPhoto() != null) {
				image = Image.getInstance(String.valueOf(servletContext.getRealPath("/resources")) + "\\Images\\"
						+ this.employe.getUrlPhoto());
				image.scaleAbsoluteHeight(150.0F);
				image.scaleAbsoluteWidth(150.0F);
			}
			if (this.employe.getLieuNaissance() == null) {
				this.employe.setLieuNaissance("");
			}
			if (this.employe.getAdresse() == null) {
				this.employe.setAdresse("");
			}
			if (this.employe.getMatricule() == null) {
				this.employe.setMatricule("");
			}
			if (this.employe.getNumCaisseSociale() == null) {
				this.employe.setNumCaisseSociale("");
			}

			if (this.employe.getCni() == null)
				this.employe.setCni("");
			if (this.employe.getDateDelivranceS() == null)
				this.employe.setDateDelivranceS("");
			if (this.employe.getDateNaissanceS() == null)
				this.employe.setDateNaissanceS("");
			if (this.employe.getTelMobile() == null)
				this.employe.setTelMobile("");
			if (this.employe.getTelHabitat() == null)
				this.employe.setTelHabitat("");
			if (this.employe.getEmail() == null)
				this.employe.setEmail("");
			if (this.employe.getDateEntreS() == null)
				this.employe.setDateEntreS("");
			if (this.employe.getDateSortieS() == null)
				this.employe.setDateSortieS("");

			if (conge != null) {
				libelleConge = conge.getTypeConge().getDesignation();
				printDateDebut = conge.getDateDebutS();
				printDateFin = conge.getDateFinS();
				dure = "" + conge.getDuree();
				jrPrs = FactoryDAO.getInstance().getCongeDejaPris(employe, idTypeConge, idExercice, null, null);
				jrsRst = nbrJrConge - (jrPrs);
				libelleDecision = conge.getLibelleDecision();
				dateDecision = conge.getDateDecisionS();
			}

			if (this.employe.getIdPersnl() > 0)
				personnel = FichierBaseDAO.getInstance()
						.getBaseById(this.employe.getIdPersnl(), Tables.getTableName(Tables.TableName.personnel))
						.getDesignation();
			if (this.employe.getIdCatgrie() > 0)
				categorie = FichierBaseDAO.getInstance().getCategoriePersonnel(this.employe.getIdCatgrie())
						.getDesignation();
			if (this.employe.getIdGrd() > 0)
				grade = FichierBaseDAO.getInstance().getGradePersonnel(this.employe.getIdGrd()).getDesignation();
			if (this.employe.getIdNvFormt() > 0)
				formation = FichierBaseDAO.getInstance()
						.getBaseById(this.employe.getIdPersnl(), Tables.getTableName(Tables.TableName.niveauFormation))
						.getDesignation();
			if (this.employe.getIdFnctn() > 0) {
				fonction = FichierBaseDAO.getInstance()
						.getBaseById(this.employe.getIdFnctn(), Tables.getTableName(Tables.TableName.fonction))
						.getDesignation();
			}
			if (this.employe.getContrat() != null)
				contrat = this.employe.getContrat().getDesignation();
			if (this.employe.getLieuTravail() != null) {
				lieuxTravail = this.employe.getLieuTravail().getDesignation();
			}
			switch (this.employe.getEtatCivil()) {
			case 0:
				etatCivil = "Célibataire";
				break;

			case 1:
				etatCivil = "Marié(e)";
				break;

			case 2:
				etatCivil = "Veuf(ve)";
				break;
			case 3:
				etatCivil = "Divorcé(e)";
				break;
			}

			switch (this.employe.getSexe()) {
			case 1:
				sexe = "Masculin";
				break;

			case 2:
				sexe = "Féminin";
				break;
			}

			cell = HelperItext.getPdfCell("  ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 4,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" Employé ", FontFactory.getFont("Greek-Regular", 12.0F, 0, BaseColor.WHITE),
					1, 1, 0, 4, BaseColor.DARK_GRAY, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			Phrase phrase = null;

			phrase = new Phrase();
			phrase.add(new Chunk("Matricule ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(this.employe.getCode(), FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Nom et Prénom ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(this.employe.getNomPrenom(), FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Personnel ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(personnel, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Catéorie ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(categorie, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Grade ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(grade, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Fonction ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(fonction, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" Information sur le congé ",
					FontFactory.getFont("Greek-Regular", 12.0F, 0, BaseColor.WHITE), 1, 1, 0, 4, BaseColor.DARK_GRAY,
					BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Type de congé ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(libelleConge, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Début congé ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);
			phrase.add(new Chunk(printDateDebut, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Fin congé", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(printDateFin, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Durée ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(dure, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Nombre de jours annuel ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("" + nbrJrConge, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Nombre de jours déjà pris", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("" + jrPrs, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Nombre de jours restant ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("" + jrsRst, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Décision ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(libelleDecision, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk("Date décision ", FontFactory.getFont("Courier", 9.0F, 0)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 0, 0.0F, 0);
			tabInfo.addCell(cell);

			cell = HelperItext.getPdfCell(" : ", FontFactory.getFont("Courier", 9.0F, 0, BaseColor.BLACK), 2, 2, 0, 0,
					BaseColor.WHITE, BaseColor.WHITE, 3);
			tabInfo.addCell(cell);

			phrase = new Phrase();
			phrase.add(new Chunk(dateDecision, FontFactory.getFont("Courier", 9.0F, 1)));
			cell = HelperItext.getCellule(phrase, 1, 0, 0, 2, 0.0F, 2);
			tabInfo.addCell(cell);
		}
		return tabInfo;
	}

	private PdfPTable chargeListConge() throws DocumentException {
		PdfPTable tabInfo = new PdfPTable(6);
		int widthsInfo[] = { 1, 2, 5, 2, 2, 2 };
		tabInfo.setWidthPercentage(100F);
		tabInfo.setWidths(widthsInfo);

		PdfPCell cell = new PdfPCell();

		Phrase phrase = null;
		Paragraph p = null;

		phrase = new Phrase();
		phrase.add(new Chunk("N°", FontFactory.getFont("Courier", 9F, 1)));
		cell = HelperItext.getCellule(phrase, 0, 1, 15, 1, 0.5F, 1.0F);
		tabInfo.addCell(cell);

		phrase = new Phrase();
		phrase.add(new Chunk("Matricule", FontFactory.getFont("Courier", 9F, 1)));
		cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
		tabInfo.addCell(cell);

		phrase = new Phrase();
		phrase.add(new Chunk("Nom et Prénom", FontFactory.getFont("Courier", 9F, 1)));
		cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
		tabInfo.addCell(cell);
		tabInfo.setHeaderRows(1);

		phrase = new Phrase();
		phrase.add(new Chunk("Jours de congé", FontFactory.getFont("Courier", 9F, 1)));
		cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
		tabInfo.addCell(cell);
		tabInfo.setHeaderRows(1);

		phrase = new Phrase();
		phrase.add(new Chunk("Congé déjà pris ", FontFactory.getFont("Courier", 9F, 1)));
		cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
		tabInfo.addCell(cell);
		tabInfo.setHeaderRows(1);

		phrase = new Phrase();
		phrase.add(new Chunk("Solde", FontFactory.getFont("Courier", 9F, 1)));
		cell = HelperItext.getCellule(phrase, 0, 1, 11, 1, 0.5F, 1.0F);
		tabInfo.addCell(cell);
		tabInfo.setHeaderRows(1);

		int i = 0;
		if (listConge != null && listConge.size() > 0) {
			for (HistoriqueCongeC hist : listConge) {
				i++;
				// completeEmploye(emp);

				phrase = new Phrase();
				phrase.add(new Chunk("" + i, FontFactory.getFont("Courier", 9F, 0)));
				cell = HelperItext.getCellule(phrase, 0, 2, 14, 1, 0.5F, 3F);
				tabInfo.addCell(cell);

				phrase = new Phrase();
				phrase.add(new Chunk(hist.getMatricule(), FontFactory.getFont("Courier", 9F, 0)));
				cell = HelperItext.getCellule(phrase, 0, 1, 10, 1, 0.5F, 3F);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk(hist.getNomEploye(), FontFactory.getFont("Courier", 9F, 0)));
				cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 3F);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk("" + hist.getCongeAnnuel(), FontFactory.getFont("Courier", 9F, 0)));
				cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F,3F);
				p.setAlignment(2);
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk("" + hist.getCongePris(), FontFactory.getFont("Courier", 9F, 0)));
				p.setAlignment(2);
				cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 3F);				
				tabInfo.addCell(cell);

				p = new Paragraph();
				p.add(new Chunk("" + hist.getSolde(), FontFactory.getFont("Courier", 9F, 0)));
				p.setAlignment(2);
				cell = HelperItext.getCellule(p, 0, 1, 10, 1, 0.5F, 3F);
				tabInfo.addCell(cell);

			}

		}
		return tabInfo;
	}

	public void chargerSoldeConge() {
		listEmp = FactoryDAO.getInstance().getListEmployeSimple("");
		listConge = new ArrayList<HistoriqueCongeC>();
		HistoriqueCongeC hc = null;
		for (EmployeC employe : listEmp) {

			int jrsPris = FactoryDAO.getInstance().getCongeDejaPris(employe, idTypeConge, idExercice, null, null);
			hc = new HistoriqueCongeC();
			hc.setCongeAnnuel(employe.getJourConge());
			hc.setCongePris(jrsPris);
			hc.setEmploye(employe);
			hc.setSolde(employe.getJourConge() - jrsPris);
			hc.setPrintDateDeb("");
			hc.setPrintDateFin("");
			listConge.add(hc);
		}
	}

}
