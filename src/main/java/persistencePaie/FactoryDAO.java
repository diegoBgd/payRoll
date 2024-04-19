package persistencePaie;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import classesPaie.*;

public class FactoryDAO implements Serializable {
	private static final long serialVersionUID = -5662572017654511290L;

	private FactoryDAO() {
		con = Connexion.getConnection();
	}
 
	private static FactoryDAO dao;
	private static Connection con;

	public static FactoryDAO getInstance() {
		if (dao == null) {
			dao = new FactoryDAO();
		}
		return dao;
	}

	private void releaseResource(Statement stmt, ResultSet rs) {
		if (rs != null) {

			try {

				rs.close();
			} catch (SQLException ex) {

				System.out.println(ex.getMessage());
			}
		}
		if (stmt != null) {

			try {

				stmt.close();
			} catch (SQLException ex) {

				System.out.println(ex.getMessage());
			}
		}
	}

	public int getId(String table) {
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
		String sql = "SELECT max(id) as id FROM " + table;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt("id");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		releaseResource(stmt, rs);
		return id + 1;
	}

	public boolean delete(int id, String tblName) {
		Statement stmt = null;
		boolean deleted = false;
		Connection conn = con;
		String sql = "DELETE FROM " + tblName + " WHERE id=" + id;

		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.commit();
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		releaseResource(stmt, null);

		return deleted;
	}

	public boolean deleteNotCommited(int id, String tblName) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + tblName + " WHERE id=" + id;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		releaseResource(stmt, null);

		return deleted;
	}

	public boolean deleteNotAutocommit(int id, String tblName) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + tblName + " WHERE id=" + id;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		releaseResource(stmt, null);

		return deleted;
	}

	public boolean deleteDetails(int idEntete, String colName, String tblName, Connection conn) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + tblName + " WHERE " + colName + "=" + idEntete;

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		releaseResource(stmt, null);

		return deleted;
	}

	public boolean delete(int id, String tblName, Connection conn) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + tblName + " WHERE id=" + id;

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return deleted;
	}

	public boolean deleteList(int idEntete, String colName, String tblName) {
		PreparedStatement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + tblName + " WHERE " + colName + "=" + idEntete;

		try {
			stmt = con.prepareStatement(sql);
			stmt.execute();
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return deleted;
	}

	private boolean insertHistorique(Historique hist, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		hist.setId(getId(Tables.getTableName(Tables.TableName.historique)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.historique)
				+ " (id,date_operation,id_operateur,operation,table_name,id_ligne) " + "VALUES (?,?,?,?,?,?) ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, hist.getId());
			stmt.setObject(2, hist.getDateOperation());
			if (hist.getOperateur() != null) {

				stmt.setInt(3, hist.getOperateur().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setString(4, hist.getOperation());
			stmt.setString(5, hist.getTable());
			stmt.setInt(6, hist.getIdLigne());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		releaseResource(stmt, null);

		return saved;
	}

	private Historique setHistorique(ResultSet rs) throws SQLException {
		Historique hist = new Historique();
		hist.setId(rs.getInt("id"));
		hist.setDateOperation(rs.getDate("date_operation"));
		hist.setOperateur(FichierBaseDAO.getInstance().getOperateur(rs.getInt("id_operateur")));
		hist.setOperation(rs.getString("operation"));
		hist.setTable(rs.getString("table_name"));
		hist.setIdLigne(rs.getInt("id_ligne"));
		return hist;
	}

	public List<Historique> getHistoriques(OperateurC oper, Date dateDebut, Date dateFin) {
		ResultSet rs = null;

		PreparedStatement stmt = null;
		rs = null;
		List<Historique> historiques = new ArrayList<Historique>();
		String sql = "SELECT A.* FROM " + Tables.getTableName(Tables.TableName.historique)
				+ " AS A WHERE A.id_operateur=? " + HelperC.FiltreDate(dateDebut, dateFin, "A", "date_operation")
				+ " ORDER BY A.date_operation";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, oper.getId());
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				historiques.add(setHistorique(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		releaseResource(stmt, rs);

		return historiques;
	}

	public boolean insertUpdateCredit(CreditC credit) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			if (credit.getId() == 0) {

				saved = insertCreditEmploye(credit, conn);
			} else {

				saved = updateCreditEmploye(credit, conn);
			}
			if (saved) {

				if (credit.getListDetail().size() > 0)
					saved = insertDetailCreditEmploye(credit, conn);

				if (credit.getListDeleted().size() > 0)
					saved = deleteDetailCredit(credit.getListDeleted(), conn);
			}
			if (saved) {
				credit.getHistorique().setIdLigne(credit.getId());
				saved = insertHistorique(credit.getHistorique(), conn);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private boolean insertCreditEmploye(CreditC credit, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.credit)
				+ " (date_credit,duree,id_employe,montant_credit,id_banque,"
				+ " numero_compte,numero_dossier,id_exercice,montant_total) " + "VALUES (?,?,?,?,?,?,?,?,?) ";

		try {

			stmt = conx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setObject(1, credit.getDatePret());
			stmt.setInt(2, credit.getDuree());
			stmt.setInt(3, credit.getIdEmploye());
			stmt.setDouble(4, credit.getMontantCredit());
			if(credit.getBanque()!=null)
				stmt.setInt(5, credit.getBanque().getId());
			else
				stmt.setObject(5, null);
			
			stmt.setObject(6, credit.getNumeroCompte());
			stmt.setObject(7, credit.getNumeroDossier());
			stmt.setInt(8, credit.getExercice().getId());
			stmt.setDouble(9, credit.getCapital());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
				generatedKey = rs.getInt(1);
				credit.setId(generatedKey);
			}

			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		releaseResource(stmt, null);

		return saved;
	}

	private boolean updateCreditEmploye(CreditC credit, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.credit)
				+ " SET date_credit=?,duree=?,id_employe=?,montant_credit=?,"
				+ " id_banque=?,numero_compte=?,montant_total=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setObject(1, credit.getDatePret());
			stmt.setInt(2, credit.getDuree());
			stmt.setInt(3, credit.getIdEmploye());
			stmt.setDouble(4, credit.getMontantCredit());
			if(credit.getBanque()!=null)
				stmt.setInt(5, credit.getBanque().getId());
			else
				stmt.setObject(5, null);
			stmt.setObject(6, credit.getNumeroCompte());
			stmt.setDouble(7, credit.getCapital());
			stmt.setInt(8, credit.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		releaseResource(stmt, null);

		return saved;
	}

	private boolean deleteDetailCredit(List<CreditDetailC> list, Connection conx) {
		PreparedStatement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.detailCredit) + " WHERE id=? ";

		try {

			stmt = conx.prepareStatement(sql);
			for (CreditDetailC det : list) {
				stmt.setInt(1, det.getId());
				stmt.addBatch();
			}

			stmt.executeBatch();
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return deleted;
	}

	private boolean deleteCredit(CreditC crd, Connection conx) {
		PreparedStatement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.credit) + " WHERE id=? ";

		try {

			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, crd.getId());

			stmt.execute();
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return deleted;
	}

	private CreditC setCreditEmploye(ResultSet rs) throws SQLException {
		CreditC credit = new CreditC();
		credit.setId(rs.getInt("id"));
		credit.setIdEmploye(rs.getInt("id_employe"));
		if(rs.getObject("id_banque")!=null)
		credit.setBanque(FichierBaseDAO.getInstance().getBanque(rs.getInt("id_banque")));
		credit.setDatePret(rs.getDate("date_credit"));
		credit.setDuree(rs.getInt("duree"));
		credit.setMontantCredit(rs.getDouble("montant_credit"));
		credit.setCapital(rs.getDouble("montant_total"));
		credit.setNumeroCompte(rs.getString("numero_compte"));
		credit.setNumeroDossier(rs.getString("numero_dossier"));
		credit.setEmploye(getEmployeSimple(credit.getIdEmploye()));

		return credit;
	}

	public CreditC getCreditEmploye(int id) {
		CreditC credit = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.credit) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				credit = setCreditEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		releaseResource(stmt, rs);

		return credit;
	}

	public String getNumeroDossier(int idExercice) {
		int numero = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT numero_dossier AS num FROM " + Tables.getTableName(Tables.TableName.credit)
				+ " WHERE id=(SELECT MAX(id) FROM " + Tables.getTableName(Tables.TableName.credit)
				+ " WHERE id_exercice=" + idExercice + ")";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("num") != null && !rs.getString("num").trim().equals("")) {
					numero = Integer.valueOf(rs.getString("num")).intValue();
				}
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		releaseResource(stmt, rs);

		return String.valueOf(numero + 1);
	}

	public CreditC getCreditEmploye(String numeroDossier) {
		CreditC credit = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.credit) + " WHERE numero_dossier=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, numeroDossier);
			rs = stmt.executeQuery();
			if (rs.next()) {
				credit = setCreditEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		releaseResource(stmt, rs);

		return credit;
	}

	public boolean deleteCreditEmploye(CreditC credit) {
		boolean deleted = false;
		PreparedStatement stmt = null;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			deleted = deleteDetailCredit(credit.getListDetail(), conn);
			if (deleted)
				deleted = deleteDetails(credit.getId(), "id_credit", "tbl_credit_rembourse", conn);
			if (deleted) {
				deleted = deleteCredit(credit, conn);
			}
			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {
			deleted = false;
			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
		releaseResource(stmt, null);

		return deleted;
	}

	public List<CreditC> getListCredit(int idEmploye) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CreditC> listCredit = new ArrayList<CreditC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.credit) + " WHERE 1=1";

		if (idEmploye > 0)
			sql += " AND id_employe=" + idEmploye;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next())
				listCredit.add(setCreditEmploye(rs));

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		releaseResource(stmt, rs);

		for (CreditC credit : listCredit) {
			credit.setListDetail(getListDetailCredit(credit.getId()));
		}
		return listCredit;
	}

	private boolean insertDetailCreditEmploye(CreditC crd, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		String sql1 = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailCredit)
				+ " (montant_tranche,date_debut,date_fin,id_entete)" + " VALUES (?,?,?,?) ";

		String sql2 = "UPDATE " + Tables.getTableName(Tables.TableName.detailCredit)
				+ " SET montant_tranche=?,date_debut=?,date_fin=? WHERE id=? ";
		boolean update = false;
		try {
			stmt1 = con.prepareStatement(sql1);
			stmt2 = con.prepareStatement(sql2);

			for (CreditDetailC creditDetail : crd.getListDetail()) {

				if (creditDetail.getId() == 0) {
					stmt1.setDouble(1, creditDetail.getTranche());
					stmt1.setObject(2, creditDetail.getDateDeb());
					stmt1.setObject(3, creditDetail.getDateFin());
					stmt1.setInt(4, crd.getId());

					stmt1.addBatch();
				}
				if (creditDetail.getId() > 0) {
					stmt2.setDouble(1, creditDetail.getTranche());
					stmt2.setObject(2, creditDetail.getDateDeb());
					stmt2.setObject(3, creditDetail.getDateFin());
					stmt2.setInt(4, creditDetail.getId());

					stmt2.addBatch();
					update = true;
				}

			}
			stmt1.executeBatch();
			if (update)
				stmt2.executeBatch();

			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt1, null);
			releaseResource(stmt2, null);
		}
		return saved;
	}

	private CreditDetailC setDetailCredit(ResultSet rs) throws SQLException {
		CreditDetailC det = new CreditDetailC();
		det.setId(rs.getInt("id"));
		det.setIdEntete(rs.getInt("id_entete"));
		det.setDateDeb(rs.getDate("date_debut"));
		det.setDateFin(rs.getDate("date_fin"));
		det.setTranche(rs.getDouble("montant_tranche"));

		return det;
	}

	public List<CreditDetailC> getListCreditEmploye(int idEmploye, Date datePaie) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CreditDetailC> listCredit = new ArrayList<CreditDetailC>();

		String sql = "SELECT A.* FROM " + Tables.getTableName(Tables.TableName.detailCredit) + " AS A LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.credit) + " AS B ON B.id=A.id_entete"
				+ " WHERE B.id_employe=?  AND A.date_debut <=? AND A.date_fin>=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idEmploye);

			stmt.setObject(2, HelperC.convertDate(datePaie, false));
			stmt.setObject(3, HelperC.convertDate(datePaie, false));
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				listCredit.add(setDetailCredit(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listCredit;
	}

	public List<CreditDetailC> getListDetailCredit(int idCredit) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CreditDetailC> listCredit = new ArrayList<CreditDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailCredit) + " WHERE id_entete=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idCredit);
			rs = stmt.executeQuery();
			while (rs.next())
				listCredit.add(setDetailCredit(rs));

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listCredit;
	}

	public CreditDetailC getCreditEmploye(int idEmploye, int idCrd) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CreditDetailC detail = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailCredit)
				+ " WHERE id_credit=? AND id_employe=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idCrd);
			stmt.setInt(2, idEmploye);
			rs = stmt.executeQuery();
			if (rs.next()) {
				detail = setDetailCredit(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detail;
	}

	public CreditDetailC getCreditDetail(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CreditDetailC detail = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailCredit) + " WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				detail = setDetailCredit(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detail;
	}

	private boolean insertAvaliseur(AvaliseurC avaliseur) {
		boolean saved = false;
		PreparedStatement stmt = null;
		avaliseur.setId(getId(Tables.getTableName(Tables.TableName.avaliseur)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.avaliseur) + " (id,id_employe,id_credit) "
				+ "VALUES (?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, avaliseur.getId());
			if (avaliseur.getEmploye() != null) {

				stmt.setInt(2, avaliseur.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setInt(3, avaliseur.getIdCredit());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateAvaliseur(AvaliseurC avaliseur) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.avaliseur) + " SET id_employe=? "
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			if (avaliseur.getEmploye() != null) {

				stmt.setInt(1, avaliseur.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setInt(2, avaliseur.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private AvaliseurC setDetailAvaliseur(ResultSet rs) throws SQLException {
		AvaliseurC avl = new AvaliseurC();
		avl.setId(rs.getInt("id"));
		avl.setIdEmploye(rs.getInt("id_employe"));
		avl.setIdCredit(rs.getInt("id_credit"));
		return avl;
	}

	public List<AvaliseurC> getListAvaliseur(int idCredit) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AvaliseurC> listAvaliseur = new ArrayList<AvaliseurC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.avaliseur) + " WHERE id_credit=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idCredit);
			rs = stmt.executeQuery();
			while (rs.next()) {
				listAvaliseur.add(setDetailAvaliseur(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listAvaliseur;
	}

	private boolean insertAvanceQuinzaine(AvanceQuinzaineC avanceQui, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		avanceQui.setId(getId(Tables.getTableName(Tables.TableName.avanceQuinzaine)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.avanceQuinzaine)
				+ " (id,date_avance,id_employe,id_banque,mode_paiement,montant,id_exercice,type_avance,mois_salaire) "
				+ "VALUES (?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, avanceQui.getId());
			stmt.setObject(2, avanceQui.getDate());
			if (avanceQui.getEmploye() != null) {

				stmt.setInt(3, avanceQui.getEmploye().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			if (avanceQui.getBanque() != null) {

				stmt.setInt(4, avanceQui.getBanque().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			stmt.setInt(5, avanceQui.getModePaiement());
			stmt.setDouble(6, avanceQui.getMontant());
			if (avanceQui.getExercice() != null) {

				stmt.setInt(7, avanceQui.getExercice().getId());
			} else {

				stmt.setObject(7, (Object) null);
			}
			stmt.setObject(8, Integer.valueOf(avanceQui.getTypeAvance()));
			stmt.setObject(9, Integer.valueOf(avanceQui.getMoisConcerne()));
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateAvanceQuinzaine(AvanceQuinzaineC avanceQui, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.avanceQuinzaine)
				+ " SET date_avance=?,id_employe=?,id_banque=?,mode_paiement=?,montant=?,"
				+ "type_avance=?,mois_salaire=? WHERE id=? ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setObject(1, avanceQui.getDate());
			if (avanceQui.getEmploye() != null) {

				stmt.setInt(2, avanceQui.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (avanceQui.getBanque() != null) {

				stmt.setInt(3, avanceQui.getBanque().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setInt(4, avanceQui.getModePaiement());
			stmt.setDouble(5, avanceQui.getMontant());
			stmt.setObject(6, Integer.valueOf(avanceQui.getTypeAvance()));
			stmt.setObject(7, Integer.valueOf(avanceQui.getMoisConcerne()));
			stmt.setInt(8, avanceQui.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateAvanceQuinzaine(AvanceQuinzaineC avanceQui) {
		boolean saved = false;
		Connection connexion = con;

		try {
			connexion.setAutoCommit(false);
			if (avanceQui.getId() == 0) {

				saved = insertAvanceQuinzaine(avanceQui, connexion);
				if (saved && avanceQui.getListDetail().size() > 0) {
					for (AvanceDetailC det : avanceQui.getListDetail()) {
						det.setIdEntete(avanceQui.getId());
						saved=insertDetailAvanceQuinzaine(det, connexion);
					}

				}
			} else {

				saved = updateAvanceQuinzaine(avanceQui, connexion);
				if (saved) {
					for (AvanceDetailC det : avanceQui.getListDetail()) {

						if (det.getId() == 0) {
							saved=insertDetailAvanceQuinzaine(det, connexion);
							
						}
						else
							saved=updateDetailAvanceQuinzaine(det, connexion);
					}
				}
			}
			if (saved) {
				if (avanceQui.getListDeleted().size() > 0)
					for (AvanceDetailC det : avanceQui.getListDeleted())
						saved=delete(det.getId(), Tables.getTableName(Tables.TableName.avanceDetail), connexion);
			}
			if(saved)
			{
				if(avanceQui.getIdPaie()>0)
				{
					deleteDetails(avanceQui.getId(), "id_entete", "tbl_avance_detail", connexion);
					deleteBulletinAvance(avanceQui.getIdPaie(), connexion);
					BulletinAvanceC av=new BulletinAvanceC();
					av.setDateAvance(avanceQui.getDate());
					av.setIdBulletin(avanceQui.getIdPaie());
					av.setMontantAvance(avanceQui.getMontant());
					av.setIdAvance(avanceQui.getId());
					saved=insertBulletinAvance(av, connexion);
					
				}
			}
			if (saved) {

				connexion.commit();
			} else {

				connexion.rollback();
			}

		} catch (SQLException e) {

			try {
				connexion.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
		return saved;
	}

	public AvanceQuinzaineC getAvanceQuinzaine(int id) {
		AvanceQuinzaineC avanceQui = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.avanceQuinzaine) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				avanceQui = setAvanceQuinzaine(rs);
			}
			if (avanceQui != null) {
				avanceQui.setListDetail(getListDetailAvance(avanceQui.getId()));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return avanceQui;
	}

	public AvanceQuinzaineC getAvanceQuinzaine(Date date, int idEmpl, int idExercice) {
		AvanceQuinzaineC avanceQui = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.avanceQuinzaine) + " WHERE date_avance='"
				+ HelperC.convertDate(date, false) + "' AND id_employe=" + idEmpl + " AND id_exercice=" + idExercice;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				avanceQui = setAvanceQuinzaine(rs);
			}
			if (avanceQui != null) {
				avanceQui.setListDetail(getListDetailAvance(avanceQui.getId()));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return avanceQui;
	}

	public AvanceQuinzaineC getAvanceQuinzaine(int mois, int idEmpl, int idExercice) {
		AvanceQuinzaineC avanceQui = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "SELECT tbl_avance_quinzaine.* FROM tbl_avance_quinzaine "
				+ "LEFT JOIN tbl_avance_detail ON tbl_avance_quinzaine.id= tbl_avance_detail.id_entete "
				+ "WHERE tbl_avance_quinzaine.id_exercice=" + idExercice + " AND tbl_avance_quinzaine.id_employe="
				+ idEmpl + " AND tbl_avance_detail.mois=" + mois;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				avanceQui = setAvanceQuinzaine(rs);
			}
			if (avanceQui != null) {
				avanceQui.setListDetail(getListDetailAvance(avanceQui.getId()));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return avanceQui;
	}

	public List<AvanceQuinzaineC> getAllAvanceQuinzaine() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<AvanceQuinzaineC> liste = new ArrayList<AvanceQuinzaineC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.avanceQuinzaine);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setAvanceQuinzaine(rs));
			}

			if (liste.size() > 0) {
				for (AvanceQuinzaineC avanceQui : liste) {
					avanceQui.setListDetail(getListDetailAvance(avanceQui.getId()));
				}
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<AvanceQuinzaineC> getAllAvanceQuinzaine(Date dateDebut, Date dateFin, int idEmploye, int mois) {
		List<AvanceQuinzaineC> liste = new ArrayList<AvanceQuinzaineC>();

		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.avanceQuinzaine) + " WHERE 1=1 ";
		if (dateDebut != null) {
			sql = String.valueOf(sql) + " AND date_avance>='" + HelperC.convertDat(dateDebut) + "'";
		}
		if (dateFin != null) {
			sql = String.valueOf(sql) + " AND date_avance<='" + HelperC.convertDat(dateFin) + "'";
		}
		if (idEmploye > 0) {
			sql = String.valueOf(sql) + " AND id_employe=" + idEmploye;
		}
		if (mois > 0) {
			sql = String.valueOf(sql) + " AND mois_salaire=" + mois;
		}

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			for (; rs.next(); liste.add(setAvanceQuinzaine(rs)))
				;

			if (liste.size() > 0) {
				for (AvanceQuinzaineC avanceQui : liste) {
					avanceQui.setListDetail(getListDetailAvance(avanceQui.getId()));
				}
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}
	public List<AvanceQuinzaineC> getListAvanceMoisEncours(int idEmploye, int mois) {
		List<AvanceQuinzaineC> liste = new ArrayList<AvanceQuinzaineC>();

		ResultSet rs = null;
		Statement stmt = null;
		AvanceQuinzaineC avq=null;
		String sql = "SELECT A.*,B.montant AS avance FROM tbl_avance_quinzaine AS A " + 
					 "LEFT JOIN tbl_avance_detail AS B ON B.id_entete=A.id WHERE 1=1 ";
		
		if (idEmploye > 0) {
			sql = String.valueOf(sql) + " AND A.id_employe=" + idEmploye;
		}
		if (mois > 0) {
			sql = String.valueOf(sql) + " AND B.mois=" + mois;
		}

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				avq=new AvanceQuinzaineC();
				avq.setId(rs.getInt("id"));
				avq.setDate(rs.getDate("date_avance"));
				avq.setMontant(rs.getDouble("avance"));
				
				liste.add(avq);
				
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}
	private AvanceQuinzaineC setAvanceQuinzaine(ResultSet rs) throws SQLException {
		AvanceQuinzaineC avanceQui = new AvanceQuinzaineC();
		avanceQui.setId(rs.getInt("id"));
		avanceQui.setDate(rs.getDate("date_avance"));
		avanceQui.setEmploye(getEmployeSimple(rs.getInt("id_employe")));
		if (rs.getObject("id_banque") != null)
			avanceQui.setBanque(FichierBaseDAO.getInstance().getBanque(rs.getInt("id_banque")));
		avanceQui.setModePaiement(rs.getInt("mode_paiement"));
		avanceQui.setTypeAvance(rs.getInt("type_avance"));
		avanceQui.setMontant(rs.getDouble("montant"));
		avanceQui.setMoisConcerne(rs.getInt("mois_salaire"));
		
		return avanceQui;
	}

	public boolean deleteAvanceQuinzaine(AvanceQuinzaineC avanceQui) {
		boolean saved = false;
		Connection connexion = con;

		try {
			connexion.setAutoCommit(false);
			deleteAvance(avanceQui.getId(), connexion);
			deleteDetails(avanceQui.getId(), "id_entete", "tbl_avance_detail", connexion);
			deleteDetails(avanceQui.getId(), "id", "tbl_avance_quinzaine", connexion);
			
			
		} catch (SQLException e) {

			try {
				connexion.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
		return saved;
	}

	private boolean insertDetailAvanceQuinzaine(AvanceDetailC det, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		det.setId(getId(Tables.getTableName(Tables.TableName.avanceDetail)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.avanceDetail)
				+ " (id,mois,montant,id_entete) " + "VALUES (?,?,?,?) ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, det.getId());
			stmt.setInt(2, det.getMois());
			stmt.setDouble(3, det.getMontant());
			stmt.setInt(4, det.getIdEntete());

			stmt.execute();
			saved = true;

			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailAvanceQuinzaine(AvanceDetailC det, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.avanceDetail)
				+ " SET mois=?,montant=? WHERE id=? ";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, det.getMois());
			stmt.setDouble(2, det.getMontant());
			stmt.setInt(3, det.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private AvanceDetailC setDetailAvanceQuinzaine(ResultSet rs) throws SQLException {
		AvanceDetailC det = new AvanceDetailC();
		det.setId(rs.getInt("id"));
		det.setMois(rs.getInt("mois"));
		det.setMontant(rs.getDouble("montant"));
		return det;
	}

	public List<AvanceDetailC> getListDetailAvance(int idEntete) {
		List<AvanceDetailC> liste = new ArrayList<AvanceDetailC>();

		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.avanceDetail) + " WHERE id_entete="
				+ idEntete;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			for (; rs.next(); liste.add(setDetailAvanceQuinzaine(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public boolean insertUpdateRembourssementCredit(CreditRembourseC remboursement) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (remboursement.getId() == 0) {

				saved = insertRemboursement(remboursement, conn);
			} else {

				saved = updateRemboursement(remboursement, conn);
			}
			if (saved) {
				remboursement.getHistorique().setIdLigne(remboursement.getId());
				saved = insertHistorique(remboursement.getHistorique(), conn);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private boolean insertRemboursement(CreditRembourseC remboursement, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		remboursement.setId(getId(Tables.getTableName(Tables.TableName.remboursementCredit)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.remboursementCredit)
				+ " (id_credit,montant_rembourse,date_remboursement,id_exercice,id_bulletin) " + " VALUES (?,?,?,?,?) ";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setObject(1, Integer.valueOf(remboursement.getIdCredit()));
			stmt.setDouble(2, remboursement.getMontantRembourse());
			stmt.setObject(3, remboursement.getDateRemboursement());
			stmt.setInt(4, remboursement.getExercice().getId());
			if (remboursement.getIdBulletin() > 0) {

				stmt.setInt(5, remboursement.getIdBulletin());
			} else {

				stmt.setObject(5, (Object) null);
			}
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateRemboursement(CreditRembourseC remboursement, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.remboursementCredit)
				+ " SET montant_rembourse=?,date_remboursement=? WHERE id=? ";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setDouble(1, remboursement.getMontantRembourse());
			stmt.setObject(2, remboursement.getDateRemboursement());
			stmt.setInt(3, remboursement.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		releaseResource(stmt, null);
		return saved;
	}

	private CreditRembourseC setRemboursement(ResultSet rs) throws SQLException {
		CreditRembourseC rembourse = new CreditRembourseC();
		rembourse.setId(rs.getInt("id"));
		rembourse.setDateRemboursement(rs.getDate("date_remboursement"));
		rembourse.setIdCredit(rs.getInt("id_credit"));
		rembourse.setExercice(FichierBaseDAO.getInstance().getExercice(rs.getInt("id_exercice")));
		rembourse.setMontantRembourse(rs.getDouble("montant_rembourse"));

		return rembourse;
	}

	public List<CreditRembourseC> getListRemboursementCredit(int idEmploye) {
		List<CreditRembourseC> listRembourst = new ArrayList<CreditRembourseC>();

		ResultSet rs = null;
		PreparedStatement stmt = null;

		String sql = "SELECT A.* FROM " + Tables.getTableName(Tables.TableName.remboursementCredit) + " AS A LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.credit) + " AS B ON B.id=A.id_credit" + " WHERE 1=1 ";
		if (idEmploye > 0)
			sql += " AND B.id_employe=" + idEmploye;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				listRembourst.add(setRemboursement(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listRembourst;
	}

	public boolean getListCreditRestant(int idEmploye) {
		boolean rest = false;

		ResultSet rs = null;
		PreparedStatement stmt = null;
		double montantCrd=0,montantRemb=0;
		String sql = "SELECT id,montant_total FROM tbl_credit" + 
				     "  WHERE id_employe=" + idEmploye;

		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				montantCrd= rs.getDouble("montant_total");
				montantRemb=getTotalRembourse(rs.getInt("id"));
				if (montantCrd>montantRemb) {
					rest = true;
					break;
				}
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return rest;
	}

	public CreditRembourseC getRemboursementCredit(int idCredit, Date dateRemboursement) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		CreditRembourseC creditRembourse = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.remboursementCredit) + " WHERE id_credit="
				+ idCredit + " AND date_remboursement='" + HelperC.convertDate(dateRemboursement, false) + "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				creditRembourse = setRemboursement(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return creditRembourse;
	}

	public double getTotalRembourse(int idCredit) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		double montant = 0.0D;
		String sql = "SELECT SUM(montant_rembourse) AS total FROM "
				+ Tables.getTableName(Tables.TableName.remboursementCredit) + 
				     " WHERE id_credit=" + idCredit;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("total");

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return montant;
	}

	public boolean deleteRemboursement(CreditRembourseC remboursement) {
		boolean deleted = false;
		Connection connexion = con;

		try {
			connexion.setAutoCommit(false);

			deleted = deleteDetails(remboursement.getId(), "id", "tbl_credit_rembourse", connexion);
			if (deleted) {

				remboursement.getHistorique().setIdLigne(remboursement.getId());
				deleted = insertHistorique(remboursement.getHistorique(), con);

			}
		} catch (SQLException e) {

			try {
				deleted = false;
				connexion.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
		return deleted;
	}
	
	private boolean insertBulletinComission(BulletinComissionC com, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinComission)
				+ " (id_com,id_bulletin,montant,taux)" + " VALUES (?,?,?,?)";
		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, com.getIdComission());
			pstmt.setInt(2, com.getIdBulletin());			
			pstmt.setDouble(3, com.getMontant());
			pstmt.setDouble(4, com.getTaux());
			
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinComission(BulletinComissionC com, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinComission)
				+ " SET id_com=?,montant=?,taux=? WHERE id=?";

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			
			pstmt.setInt(1, com.getIdComission());
			pstmt.setDouble(2, com.getMontant());
			pstmt.setDouble(3, com.getTaux());			
			pstmt.setInt(4, com.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}
	
	private BulletinComissionC setBulletinComission(ResultSet rs) throws SQLException {
		BulletinComissionC com = new BulletinComissionC();
		com.setId(rs.getInt("id"));	
		if (rs.getObject("id_com") != null) {
			com.setIdComission(rs.getInt("id_com"));
			com.setComission(FichierBaseDAO.getInstance().getCotisation(com.getIdComission()));
		}	
		com.setMontant(rs.getDouble("montant"));	
		com.setTaux(rs.getDouble("taux"));	
		return com;
	}
	
	private List<BulletinComissionC> getListBulletinComission(int idBulletin) {
		List<BulletinComissionC> listComission = new ArrayList<BulletinComissionC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinComission)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listComission.add(setBulletinComission(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listComission;
	}
	private boolean insertSaisieFormation(SaisieFormationC saisforma) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			saisforma.setId(getId(Tables.getTableName(Tables.TableName.saisieformation)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisieformation)
					+ " (id,id_employe,id_formation,observation,date_debut,date_fin) VALUES (?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, saisforma.getId());
			if (saisforma.getEmploye() != null) {

				stmt.setInt(2, saisforma.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (saisforma.getFormation() != null) {

				stmt.setInt(3, saisforma.getFormation().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setString(4, saisforma.getObservation());
			stmt.setObject(5, saisforma.getDateDebut());
			stmt.setObject(6, saisforma.getDateFin());
			stmt.execute();
			saved = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saved;
	}

	private boolean updateSaisieFormation(SaisieFormationC saisforma) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisieformation)
					+ " SET id_employe=?,id_formation=?,observation=?,date_debut=?,date_fin=? WHERE id=?";

			stmt = con.prepareStatement(sql);
			if (saisforma.getEmploye() != null) {

				stmt.setInt(1, saisforma.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (saisforma.getFormation() != null) {

				stmt.setInt(2, saisforma.getFormation().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setString(3, saisforma.getObservation());
			stmt.setObject(4, saisforma.getDateDebut());
			stmt.setObject(5, saisforma.getDateFin());
			stmt.setInt(6, saisforma.getId());
			stmt.execute();
			saved = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saved;
	}

	public boolean insertUpdateSaisieFormation(SaisieFormationC saisforma) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (saisforma.getId() == 0) {

				saved = insertSaisieFormation(saisforma);
			} else {

				saved = updateSaisieFormation(saisforma);
			}

			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saved;
	}

	private SaisieFormationC setSaisieFormation(ResultSet rs) throws SQLException {
		SaisieFormationC saisforma = new SaisieFormationC();
		saisforma.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			saisforma.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		}
		if (rs.getObject("id_formation") != null) {
			saisforma.setFormation(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_formation"),
					Tables.getTableName(Tables.TableName.typeFormation)));
		}
		saisforma.setObservation(rs.getString("observation"));
		saisforma.setDateDebut(rs.getDate("date_debut"));
		saisforma.setDateDebutS(HelperC.changeDateFormate(saisforma.getDateDebut()));
		saisforma.setDateFin(rs.getDate("date_fin"));
		saisforma.setDateFinS(HelperC.changeDateFormate(saisforma.getDateFin()));
		return saisforma;
	}

	public SaisieFormationC getSaisieFormation(EmployeC employe, Base formation, Date date_debut, Date date_fin) {
		SaisieFormationC saisforma = null;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(" SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieformation) + " "
					+ "WHERE id_employe='" + employe.getId() + "' AND id_formation='" + formation.getId() + "' "
					+ "AND date_debut='" + date_debut + "' AND date_fin='" + date_fin + "'");
			if (res.next()) {
				saisforma = setSaisieFormation(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saisforma;
	}

	public List<SaisieFormationC> getAllSaisieFormation() {
		List<SaisieFormationC> saisforma = new ArrayList<SaisieFormationC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieformation);
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				saisforma.add(setSaisieFormation(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return saisforma;
	}

	public List<SaisieFormationC> getlistSaisieFormation(EmployeC employe) {
		List<SaisieFormationC> saisforma = new ArrayList<SaisieFormationC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieformation) + " "
					+ "WHERE id_employe=" + employe.getId();
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				saisforma.add(setSaisieFormation(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return saisforma;
	}

	private boolean insertEmploye(EmployeC emp, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			// emp.setId(getId(Tables.getTableName(Tables.TableName.employe)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.employe)
					+ " (code, nom,id_lieu_travail, adresse, cni, date_delivrance, email,"
					+ "tel_mobile, tel_habitat, tel_service, url_photo, matricule, section, "
					+ "date_entre, date_sortie, date_naissance, lieu_naissance, nationalite, "
					+ "numero_caisse_social, suffixe_comptable, etat_civil, sexe, nbre_heure_normal,"
					+ "base_paiement, salaire_base, type_employe, mode_reglement,numero_cam, id_contrat,"
					+ "date_debut_contrat, date_fin_contrat, etat, partenaire, taux_logement, montant_logement,"
					+ "id_personnel, id_categorie, id_grade, id_niveau, id_fonction,jour_conge_annuel) " +

					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			stmt = conx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, emp.getCode());
			stmt.setString(2, emp.getNomPrenom().toUpperCase().trim());

			if (emp.getLieuTravail() != null) {
				stmt.setInt(3, emp.getLieuTravail().getId());
			} else {
				stmt.setObject(3, null);
			}
			stmt.setString(4, emp.getAdresse());
			stmt.setString(5, emp.getCni());
			stmt.setObject(6, emp.getDateDelivrance());
			stmt.setString(7, emp.getEmail().toLowerCase().trim());
			stmt.setString(8, emp.getTelMobile());
			stmt.setString(9, emp.getTelHabitat());
			stmt.setString(10, emp.getTelService());
			stmt.setString(11, emp.getUrlPhoto());
			stmt.setString(12, emp.getMatricule());
			stmt.setString(13, emp.getSection());
			stmt.setObject(14, emp.getDateEntre());
			stmt.setObject(15, emp.getDateSortie());
			stmt.setObject(16, emp.getDateNaissance());
			stmt.setString(17, emp.getLieuNaissance());
			stmt.setString(18, emp.getLibelleNationalite());
			stmt.setString(19, emp.getNumCaisseSociale());
			stmt.setString(20, emp.getSuffixeComptable());
			stmt.setInt(21, emp.getEtatCivil());
			stmt.setInt(22, emp.getSexe());
			stmt.setInt(23, emp.getNombreHeureNormal());
			stmt.setInt(24, emp.getBasePaiement());
			stmt.setDouble(25, emp.getSalaireBase());
			stmt.setInt(26, emp.getTypeEmploye());

			stmt.setInt(27, emp.getModeReglement());

			stmt.setString(28, emp.getNumCAMMut());
			if (emp.getContrat() != null) {

				stmt.setInt(29, emp.getContrat().getId());
			} else {

				stmt.setObject(29, null);
			}
			stmt.setObject(30, emp.getDateDebutContrat());
			stmt.setObject(31, emp.getDateFinContrat());
			stmt.setBoolean(32, emp.isEtat());
			stmt.setBoolean(33, emp.isPartenaire());

			stmt.setDouble(34, emp.getPourcentageLogement());
			stmt.setDouble(35, emp.getMontantLogement());

			if (emp.getIdPersnl() > 0) {
				stmt.setInt(36, emp.getIdPersnl());
			} else {
				stmt.setObject(36, null);
			}
			if (emp.getIdCatgrie() > 0) {
				stmt.setInt(37, emp.getIdCatgrie());
			} else {
				stmt.setObject(37, null);
			}
			if (emp.getIdGrd() > 0) {
				stmt.setInt(38, emp.getIdGrd());
			} else {
				stmt.setObject(38, null);
			}
			if (emp.getIdNvFormt() > 0) {
				stmt.setInt(39, emp.getIdNvFormt());
			} else {
				stmt.setObject(39, null);
			}
			if (emp.getIdFnctn() > 0) {
				stmt.setInt(40, emp.getIdFnctn());
			} else {
				stmt.setObject(40, null);
			}
			stmt.setInt(41, emp.getJourConge());
			
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
				generatedKey = rs.getInt(1);
				emp.setId(generatedKey);
			}

			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateEmploye(EmployeC emp, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe)
					+ " SET nom=?,id_lieu_travail=?, adresse=?, cni=?, date_delivrance=?, email=?,"
					+ "tel_mobile=?, tel_habitat=?, tel_service=?, url_photo=?, matricule=?, section=?, "
					+ "date_entre=?, date_sortie=?, date_naissance=?, lieu_naissance=?, nationalite=?, "
					+ "numero_caisse_social=?, suffixe_comptable=?, etat_civil=?, sexe=?, nbre_heure_normal=?,"
					+ "base_paiement=?, salaire_base=?, type_employe=?, mode_reglement=?,numero_cam=?, id_contrat=?,"
					+ "date_debut_contrat=?, date_fin_contrat=?, etat=?, partenaire=?, taux_logement=?, montant_logement=?,"
					+ "id_personnel=?, id_categorie=?, id_grade=?, id_niveau=?, id_fonction=?,date_retraite=?,"
					+ "jour_conge_annuel=?  WHERE id=?";

			stmt = conx.prepareStatement(sql);

			stmt.setString(1, emp.getNomPrenom().toUpperCase().trim());

			if (emp.getLieuTravail() != null) {
				stmt.setInt(2, emp.getLieuTravail().getId());
			} else {
				stmt.setObject(2, null);
			}
			stmt.setString(3, emp.getAdresse());
			stmt.setString(4, emp.getCni());
			stmt.setObject(5, emp.getDateDelivrance());
			if (emp.getEmail() != null)
				stmt.setString(6, emp.getEmail().toLowerCase().trim());
			else
				stmt.setObject(6, "");
			stmt.setString(7, emp.getTelMobile());
			stmt.setString(8, emp.getTelHabitat());
			stmt.setString(9, emp.getTelService());
			stmt.setString(10, emp.getUrlPhoto());
			stmt.setString(11, emp.getMatricule());
			stmt.setString(12, emp.getSection());
			stmt.setObject(13, emp.getDateEntre());
			stmt.setObject(14, emp.getDateSortie());
			stmt.setObject(15, emp.getDateNaissance());
			stmt.setString(16, emp.getLieuNaissance());
			stmt.setString(17, emp.getLibelleNationalite());
			stmt.setString(18, emp.getNumCaisseSociale());
			stmt.setString(19, emp.getSuffixeComptable());
			stmt.setInt(20, emp.getEtatCivil());
			stmt.setInt(21, emp.getSexe());
			stmt.setInt(22, emp.getNombreHeureNormal());
			stmt.setInt(23, emp.getBasePaiement());
			stmt.setDouble(24, emp.getSalaireBase());
			stmt.setInt(25, emp.getTypeEmploye());

			stmt.setInt(26, emp.getModeReglement());

			stmt.setString(27, emp.getNumCAMMut());
			if (emp.getContrat() != null) {

				stmt.setInt(28, emp.getContrat().getId());
			} else {

				stmt.setObject(28, null);
			}
			stmt.setObject(29, emp.getDateDebutContrat());
			stmt.setObject(30, emp.getDateFinContrat());
			stmt.setBoolean(31, emp.isEtat());
			stmt.setBoolean(32, emp.isPartenaire());

			stmt.setDouble(33, emp.getPourcentageLogement());
			stmt.setDouble(34, emp.getMontantLogement());

			if (emp.getIdPersnl() > 0) {
				stmt.setInt(35, emp.getIdPersnl());
			} else {
				stmt.setObject(35, null);
			}
			if (emp.getIdCatgrie() > 0) {
				stmt.setInt(36, emp.getIdCatgrie());
			} else {
				stmt.setObject(36, null);
			}
			if (emp.getIdGrd() > 0) {
				stmt.setInt(37, emp.getIdGrd());
			} else {
				stmt.setObject(37, null);
			}
			if (emp.getIdNvFormt() > 0) {
				stmt.setInt(38, emp.getIdNvFormt());
			} else {
				stmt.setObject(38, null);
			}
			if (emp.getIdFnctn() > 0) {
				stmt.setInt(39, emp.getIdFnctn());
			} else {
				stmt.setObject(39, null);
			}

			if (emp.getDateRetraite() != null) {
				stmt.setObject(40, emp.getDateRetraite());
			} else {
				stmt.setObject(40, null);
			}
			stmt.setInt(41, emp.getJourConge());
			stmt.setInt(42, emp.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateEmploye(EmployeC emp) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (emp.getId() == 0) {

				saved = insertEmploye(emp, conn);
			} else {

				saved = updateEmploye(emp, conn);
			}
			if (saved) {

				if (emp.getListeDetailPrime().size() > 0)
					for (DetailPrimeEmployeC detai1 : emp.getListeDetailPrime()) {

						detai1.setEmploye(emp);

						if (detai1.getId() == 0)

							saved = insertDetailPrimeEmploye(detai1, conn);
						else

							saved = updateDetailPrimeEmploye(detai1, conn);
					}

				if (emp.getListeDetailPrimeDeleted().size() > 0)
					for (DetailPrimeEmployeC detai1 : emp.getListeDetailPrimeDeleted()) {

						saved = delete(detai1.getId(), Tables.getTableName(Tables.TableName.detailPrimeEmploye));
					}

				if (emp.getListeDetailIndemnite().size() > 0)
					for (DetailIndemniteEmployeC detail2 : emp.getListeDetailIndemnite()) {

						detail2.setEmploye(emp);
						if (detail2.getId() == 0)

							saved = insertDetailIndemniteEmploye(detail2, conn);

						else
							saved = updateDetailIndemniteEmploye(detail2, conn);
					}

				if (emp.getListeDetailIndemniteDeleted().size() > 0)
					for (DetailIndemniteEmployeC detai1 : emp.getListeDetailIndemniteDeleted()) {
						saved = delete(detai1.getId(), Tables.getTableName(Tables.TableName.detailIndemniteEmploye));
					}
				if (emp.getListeDetailCotisation().size() > 0)
					for (DetailCotisationEmployeC detail3 : emp.getListeDetailCotisation()) {

						detail3.setEmploye(emp);
						if (detail3.getId() == 0)
							saved = insertDetailCotisation(detail3, conn);

						else
							saved = updateDetailCotisation(detail3, conn);
					}

				if (emp.getListeDetailCotisationDeleted().size() > 0)
					for (DetailCotisationEmployeC detail4 : emp.getListeDetailCotisationDeleted()) {
						saved = delete(detail4.getId(), Tables.getTableName(Tables.TableName.detailCotisationEmploye));
					}
				if (emp.getListeDetailDeduction().size() > 0)
					for (DetailDeductionC detail5 : emp.getListeDetailDeduction()) {

						detail5.setEmploye(emp);
						if (detail5.getId() == 0)

							saved = insertDetailDeduction(detail5, conn);

						else
							saved = updateDetailDuction(detail5, conn);
					}
			if(emp.getListeDetailComission().size()>0)
				for (DetailComissionEmployeC det : emp.getListeDetailComission()) {
					det.setIdEmpl(emp.getId());
					if(det.getId()==0)
					saved=insertDetailComission(det, conn);
					else
						saved=updateDetailComission(det, conn);
				}
			if(emp.getListeDetailComissionDeleted().size()>0)
			for (DetailComissionEmployeC det : emp.getListeDetailComissionDeleted()) {
				saved= delete(det.getId(), Tables.getTableName(Tables.TableName.detailComissionEmploye));
			}
				if (emp.getListeDetailDeductionDeleted().size() > 0)
					for (DetailDeductionC detail6 : emp.getListeDetailDeductionDeleted()) {
						saved = delete(detail6.getId(), Tables.getTableName(Tables.TableName.detailDeductionEmploye));
					}
				if (emp.getListBanquePaiement().size() > 0)
					for (DetailBanqueEmployeC detail7 : emp.getListBanquePaiement()) {

						detail7.setEmploye(emp);

						if (detail7.getId() == 0)

							saved = insertDetailBanqueEmploye(detail7, conn);

						else
							saved = updateDetailBanqueEmploye(detail7, conn);
					}

				if (emp.getListBanquePaiementDeleted().size() > 0)
					for (DetailBanqueEmployeC detail8 : emp.getListBanquePaiementDeleted()) {
						saved = delete(detail8.getId(), Tables.getTableName(Tables.TableName.detailBanqueEmploye));
					}

				if (emp.getIdFnctn() > 0) {

					AffectationC affectSelected = null;
					if (emp.getFonction() != null)
						affectSelected = FichierBaseDAO.getInstance().getFonctionEmploye(emp, emp.getIdFnctn());
					if (affectSelected == null) {

						AffectationC affect = new AffectationC();
						affect.setIdEmploye(emp.getId());
						affect.setIdFonction(emp.getIdFnctn());
						affect.setDateDebut(emp.getDateEntre());
						saved = insertAffectation(affect, conn);
					}
				}

				DetailOrganeC deta = null;
				deta = getDetailOrgane(emp, emp.getIdServce());
				if (deta == null) {

					deta = new DetailOrganeC();
					deta.setIdEmpl(emp.getId());

					deta.setIdDirection(emp.getIdDirection());
					deta.setIdDepartmt(emp.getIdDepartement());
					deta.setIdSrvice(emp.getIdServce());
					
					saved = insertDetailOrgane(deta, conn);
				}

				TraitementSalarialC traitement = getTraitementSalarial(emp.getId(),
						Constante.getTypeAvancement(Constante.TypeAvancement.salaireEntree), 0);
				if (traitement == null) {

					traitement = new TraitementSalarialC();
					traitement.setEmploye(emp);
					traitement.setSalaireBase(emp.getSalaireBase());
					traitement.setDateDebut(emp.getDateEntre());
					traitement.setTypeAvancement(Constante.getTypeAvancement(Constante.TypeAvancement.salaireEntree));
					insertTraitementSalarial(traitement, conn);
				} else {

					traitement.setSalaireBase(emp.getSalaireBase());
					updateTraitementSalarial(traitement, conn);
				}
			}
			if(saved)
			{
				emp.getHistorique().setIdLigne(emp.getId());
				saved=insertHistorique(emp.getHistorique(), conn);
			}
			if (saved) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		return saved;
	}

	public void updateEtatEmploye(EmployeC emp) {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe) + " SET etat=? WHERE id=?";

			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, emp.isEtat());
			stmt.setInt(2, emp.getId());
			stmt.execute();

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
	}

	public boolean deleteEmploye(EmployeC emp) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);
			deleted = deleteList(emp.getId(), "id_employe",
					Tables.getTableName(Tables.TableName.detailCotisationEmploye));
			deleted = deleteList(emp.getId(), "id_employe",
					Tables.getTableName(Tables.TableName.detailDeductionEmploye));
			deleted = deleteList(emp.getId(), "id_employe", Tables.getTableName(Tables.TableName.detailPrimeEmploye));
			deleted = deleteList(emp.getId(), "id_employe",
					Tables.getTableName(Tables.TableName.detailIndemniteEmploye));
			deleted = deleteList(emp.getId(), "id_employe", Tables.getTableName(Tables.TableName.detailBanqueEmploye));
			deleted = deleteList(emp.getId(), "id_employe", Tables.getTableName(Tables.TableName.detailOrgane));
			deleted = deleteList(emp.getId(), "id_employe", Tables.getTableName(Tables.TableName.affectation));
			deleted = deleteList(emp.getId(), "id_employe", Tables.getTableName(Tables.TableName.detailGradeEmploye));
			deleted = deleteList(emp.getId(), "id_employe",
					Tables.getTableName(Tables.TableName.detailNiveauFormation));
			deleted = deleteList(emp.getId(), "id_employe", Tables.getTableName(Tables.TableName.traitementSalarial));
			deleted = deleteList(emp.getId(), "id_employe", Tables.getTableName(Tables.TableName.detailComissionEmploye));
			
			if (deleted) {
				deleted = deleteNotAutocommit(emp.getId(), Tables.getTableName(Tables.TableName.employe));
			}
			if (deleted)
				deleted=insertHistorique(emp.getHistorique(), con);
			
			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public EmployeC getEmploye() {
		EmployeC emp = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe)
				+ " WHERE partenaire=0 OR partenaire IS NULL";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				emp = setEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return emp;
	}

	private EmployeC setEmploye(ResultSet rs) throws SQLException {
		EmployeC emp = new EmployeC();
		emp.setId(rs.getInt("id"));
		emp.setCode(rs.getString("code"));
		emp.setNomPrenom(rs.getString("nom"));

		if (rs.getObject("id_lieu_travail") != null)
			emp.setLieuTravail(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_lieu_travail"),
					Tables.getTableName(Tables.TableName.lieuxTravail)));
		emp.setAdresse(rs.getString("adresse"));
		emp.setCni(rs.getString("cni"));
		emp.setDateDelivrance(rs.getDate("date_delivrance"));
		emp.setDateDelivranceS(HelperC.changeDateFormate(emp.getDateDelivrance()));
		emp.setJourConge(rs.getInt("jour_conge_annuel"));
		emp.setEmail(rs.getString("email"));
		emp.setTelMobile(rs.getString("tel_mobile"));
		emp.setTelHabitat(rs.getString("tel_habitat"));
		emp.setTelService(rs.getString("tel_service"));
		emp.setUrlPhoto(rs.getString("url_photo"));
		emp.setMatricule(rs.getString("matricule"));
		emp.setSection(rs.getString("section"));
		emp.setDateEntre(rs.getDate("date_entre"));
		emp.setDateEntreS(HelperC.changeDateFormate(emp.getDateEntre()));
		emp.setDateSortie(rs.getDate("date_sortie"));
		emp.setDateSortieS(HelperC.changeDateFormate(emp.getDateSortie()));
		emp.setDateNaissance(rs.getDate("date_naissance"));
		emp.setDateNaissanceS(HelperC.changeDateFormate(emp.getDateNaissance()));
		emp.setLieuNaissance(rs.getString("lieu_naissance"));
		emp.setLibelleNationalite(rs.getString("nationalite"));
		emp.setNumCaisseSociale(rs.getString("numero_caisse_social"));
		emp.setSuffixeComptable(rs.getString("suffixe_comptable"));
		emp.setSexe(rs.getInt("sexe"));
		emp.setNombreHeureNormal(rs.getInt("nbre_heure_normal"));
		emp.setBasePaiement(rs.getInt("base_paiement"));
		emp.setSalaireBase(rs.getDouble("salaire_base"));

		emp.setModeReglement(rs.getInt("mode_reglement"));

		emp.setNumCAMMut(rs.getString("numero_cam"));
		emp.setPartenaire(rs.getBoolean("partenaire"));
		emp.setEtatCivil(rs.getInt("etat_civil"));
		emp.setTypeEmploye(rs.getInt("type_employe"));
		if (rs.getObject("id_contrat") != null) {
			emp.setContrat(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_contrat"),
					Tables.getTableName(Tables.TableName.typeContrat)));
		}
		emp.setDateDebutContrat(rs.getDate("date_debut_contrat"));
		emp.setDateDebutContratS(HelperC.changeDateFormate(emp.getDateDebutContrat()));
		emp.setDateFinContrat(rs.getDate("date_fin_contrat"));
		emp.setComplement(rs.getInt("complement"));
		emp.setMontantLogement(rs.getDouble("montant_logement"));
		emp.setPourcentageLogement(rs.getDouble("taux_logement"));
		emp.setDateFinContratS(HelperC.changeDateFormate(emp.getDateFinContrat()));
		emp.setDateRetraite(rs.getDate("date_retraite"));
		emp.setDateRetraiteS(HelperC.changeDateFormate(emp.getDateRetraite()));
		if (rs.getObject("id_personnel") != null) {
			emp.setIdPersnl(rs.getInt("id_personnel"));
		}
		if (rs.getObject("id_categorie") != null) {
			emp.setIdCatgrie(rs.getInt("id_categorie"));
		}
		if (rs.getObject("id_grade") != null) {
			emp.setIdGrd(rs.getInt("id_grade"));
		}
		if (rs.getObject("id_niveau") != null) {
			emp.setIdNvFormt(rs.getInt("id_niveau"));
		}
		if (rs.getObject("id_fonction") != null) {
			emp.setIdFnctn(rs.getInt("id_fonction"));
		}
		
		return emp;
	}

	public EmployeC getEmploye(int id, boolean withDetail) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE id=" + id
				+ " AND partenaire=0 ";

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			if (res.next()) {
				emp = setEmploye(res);
			}
			if (withDetail && emp != null) {
				emp.setListeDetailPrime(getListeDetailPrime(emp));
				emp.setListeDetailIndemnite(getListeDetailIndemnite(emp));
				emp.setListeDetailDeduction(getListeDetaildeduction(emp));
				emp.setListeDetailCotisation(getListeDetailCotisationEmploye(emp));
				emp.setListBanquePaiement(getListeDetailBanqueEmploye(emp));
				emp.setListeDetailComission(getListeComission(emp));
				emp.setDetailOrgane(getDetailOrgane(emp));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return emp;
	}

	public EmployeC getUser(int id, boolean withDetail) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE id=" + id
				+ " AND date_sortie IS NULL";

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			if (res.next()) {
				emp = setEmploye(res);
			}
			if (withDetail && emp != null) {
				emp.setListeDetailPrime(getListeDetailPrime(emp));
				emp.setListeDetailIndemnite(getListeDetailIndemnite(emp));
				emp.setListeDetailDeduction(getListeDetaildeduction(emp));
				emp.setListeDetailCotisation(getListeDetailCotisationEmploye(emp));
				emp.setListBanquePaiement(getListeDetailBanqueEmploye(emp));
				emp.setListeDetailComission(getListeComission(emp));
				emp.setDetailOrgane(getDetailOrgane(emp));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return emp;
	}

	public EmployeC getEmploye(String code, boolean withDetail) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE code='" + code + "'";

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			if (res.next()) {
				emp = setEmploye(res);
			}
			if (withDetail && emp != null) {
				emp.setListeDetailPrime(getListeDetailPrime(emp));
				emp.setListeDetailIndemnite(getListeDetailIndemnite(emp));
				emp.setListeDetailDeduction(getListeDetaildeduction(emp));
				emp.setListeDetailCotisation(getListeDetailCotisationEmploye(emp));
				emp.setListBanquePaiement(getListeDetailBanqueEmploye(emp));
				emp.setListeDetailComission(getListeComission(emp));
				emp.setDetailOrgane(getDetailOrgane(emp));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return emp;
	}

	public EmployeC getEmployeActif(String code, boolean withDetail) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE code='" + code
				+ "' AND date_sortie IS NULL";

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			if (res.next()) {
				emp = setEmploye(res);
			}
			if (withDetail && emp != null) {
				emp.setListeDetailPrime(getListeDetailPrime(emp));
				emp.setListeDetailIndemnite(getListeDetailIndemnite(emp));
				emp.setListeDetailDeduction(getListeDetaildeduction(emp));
				emp.setListeDetailCotisation(getListeDetailCotisationEmploye(emp));
				emp.setListBanquePaiement(getListeDetailBanqueEmploye(emp));
				emp.setListeDetailComission(getListeComission(emp));
				emp.setDetailOrgane(getDetailOrgane(emp));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return emp;
	}

	public EmployeC getEmployeSimple(String code) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();
			String sql = " SELECT id,code,nom,partenaire,id_fonction,email FROM "
					+ Tables.getTableName(Tables.TableName.employe) + " WHERE code='" + code
					+ "' AND date_sortie IS NULL";
			res = stmt.executeQuery(sql);
			if (res.next()) {
				emp = new EmployeC();
				emp.setId(res.getInt("id"));
				emp.setCode(res.getString("code"));
				emp.setNomPrenom(res.getString("nom"));
				emp.setPartenaire(res.getBoolean("partenaire"));
				emp.setIdFnctn(res.getInt("id_fonction"));
				emp.setEmail(res.getString("email"));
				//emp.setCodeLine(res.getString("code_line_service"));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return emp;
	}

	public EmployeC getEmploye(String code) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();
			String sql = " SELECT id,code,nom FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE code='"
					+ code + "' AND partenaire=0";
			res = stmt.executeQuery(sql);
			if (res.next()) {
				emp = new EmployeC();
				emp.setId(res.getInt("id"));
				emp.setCode(res.getString("code"));
				emp.setNomPrenom(res.getString("nom"));

			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return emp;
	}

	public EmployeC getEmployeSimple(int id) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "";

		try {
			stmt = con.createStatement();
			sql = " SELECT id,code,nom,matricule,partenaire,id_fonction,salaire_base FROM "
					+ Tables.getTableName(Tables.TableName.employe) + " WHERE id=" + id;

			res = stmt.executeQuery(sql);
			if (res.next()) {
				emp = new EmployeC();
				emp.setId(res.getInt("id"));
				emp.setCode(res.getString("code"));
				emp.setNomPrenom(res.getString("nom"));
				emp.setMatricule(res.getString("matricule"));
				emp.setPartenaire(res.getBoolean("partenaire"));
				emp.setSalaireBase(res.getDouble("salaire_base"));
				if (res.getObject("id_fonction") != null) {
					emp.setIdFnctn(res.getInt("id_fonction"));
				}
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return emp;
	}

	public List<EmployeC> getListEmployeSimple(String mot) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "";
		List<EmployeC> list = new ArrayList<EmployeC>();

		try {
			stmt = con.createStatement();
			sql = " SELECT id,code,nom,matricule,jour_conge_annuel FROM " + Tables.getTableName(Tables.TableName.employe)
					+ " WHERE partenaire=0 AND date_sortie IS NULL";
			if(mot!=null && !mot.equals(""))
				sql+=" AND nom LIKE '%"+mot+"%'";
			
			res = stmt.executeQuery(sql);
			while (res.next()) {
				emp = new EmployeC();
				emp.setId(res.getInt("id"));
				emp.setCode(res.getString("code"));
				emp.setNomPrenom(res.getString("nom"));
				emp.setMatricule(res.getString("matricule"));
				emp.setJourConge(res.getInt("jour_conge_annuel"));
				list.add(emp);
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return list;
	}

	public List<EmployeC> getListEmployeSimple(String code1, String code2) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "";
		List<EmployeC> list = new ArrayList<EmployeC>();

		try {
			stmt = con.createStatement();
			sql = " SELECT id,code,nom,matricule FROM " + Tables.getTableName(Tables.TableName.employe)
					+ " WHERE partenaire=0 AND date_sortie IS NULL";

			if (code1 != null && !code1.equals(""))
				sql = String.valueOf(sql) + " AND code>='" + code1 + "'";
			if (code2 != null && !code2.equals("")) {
				sql = String.valueOf(sql) + " AND code<='" + code2 + "'";
			}
			res = stmt.executeQuery(sql);
			while (res.next()) {
				emp = new EmployeC();
				emp.setId(res.getInt("id"));
				emp.setCode(res.getString("code"));
				emp.setNomPrenom(res.getString("nom"));
				emp.setMatricule(res.getString("matricule"));
				list.add(emp);
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return list;
	}

	public EmployeC getEmploye(String code, int id) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();
			String req = " SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE code='" + code
					+ "' AND (partenaire=0 OR partenaire IS NULL) AND id<>" + id;
			res = stmt.executeQuery(req);
			if (res.next()) {
				emp = setEmploye(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return emp;
	}

	public List<EmployeC> getListEmploye(int idPrsnl, int idCat, int idGrd, int idNvFrm, int idContrat, int sexe,
			int etatCivl, int idLieuxTrv, int idFonction, int actif, int order) {
		Statement stmt = null;
		ResultSet rs = null;
		List<EmployeC> liste = new ArrayList<EmployeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE 1=1 AND partenaire=0 ";

		if (idPrsnl > 0)
			sql = String.valueOf(sql) + " AND id_personnel=" + idPrsnl;
		if (idCat > 0)
			sql = String.valueOf(sql) + " AND id_categorie=" + idCat;
		if (idGrd > 0)
			sql = String.valueOf(sql) + " AND id_grade=" + idGrd;
		if (idNvFrm > 0)
			sql = String.valueOf(sql) + " AND id_niveau=" + idNvFrm;
		if (idContrat > 0)
			sql = String.valueOf(sql) + " AND id_contrat=" + idContrat;
		if (idLieuxTrv > 0)
			sql = String.valueOf(sql) + " AND id_lieu_travail=" + idContrat;
		if (sexe > 0)
			sql = String.valueOf(sql) + " AND sexe=" + sexe;
		if (etatCivl > 0) {
			sql = String.valueOf(sql) + " AND etat_civil=" + etatCivl;
		}
		if (idFonction > 0) {
			sql = String.valueOf(sql) + " AND id_fonction=" + idFonction;
		}
		if (actif == 1) {

			sql = String.valueOf(sql) + " AND date_retraite IS NULL";
		}
		if (actif == 2) {

			sql = String.valueOf(sql) + " AND date_retraite IS NOT NULL";
		}

		if (order == 0)
			sql = String.valueOf(sql) + " ORDER BY code";

		if (order == 1)
			sql = String.valueOf(sql) + " ORDER BY nom";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				liste.add(setEmploye(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public EmployeC getEmploye(String nom, String prenom, int id) {
		EmployeC emp = null;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();
			String req = " SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE nom='" + nom
					+ "' AND (partenaire=0 OR partenaire IS NULL) AND id<>" + id;
			res = stmt.executeQuery(req);
			if (res.next()) {
				emp = setEmploye(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		releaseResource(stmt, res);
		return emp;
	}

	public List<EmployeC> getListEmployeARetraite(Date date, int ageRetraite, int idPersonnel) {
		List<EmployeC> listeemploye = new ArrayList<EmployeC>();
		EmployeC employe = null;
		Statement stmt = null;
		ResultSet rs = null;
		int year = HelperC.getYearFromDate(date);
		try {
			stmt = con.createStatement();

			String sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.employe)
					+ " WHERE partenaire=0 AND date_retraite IS NULL";

			if (idPersonnel > 0) {
				sqlRequest += " AND id_personnel=" + idPersonnel;
			} else
				sqlRequest += " AND id_personnel IS NULL";

			rs = stmt.executeQuery(sqlRequest);

			while (rs.next()) {
				employe = setEmploye(rs);

				if (employe.getDateNaissance() != null) {
					if (year - HelperC.getYearFromDate(employe.getDateNaissance()) >= ageRetraite)
						listeemploye.add(employe);
				}

			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return listeemploye;
	}

	public List<EmployeC> getAllEmploye(boolean detail, int order) {
		List<EmployeC> listeemploye = new ArrayList<EmployeC>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.employe)
					+ " WHERE 1=1 AND partenaire=0 AND date_sortie IS NULL ";

			switch (order) {
			case 0:
				sqlRequest = String.valueOf(sqlRequest) + " ORDER BY  code";
				break;
			case 1:
				sqlRequest = String.valueOf(sqlRequest) + " ORDER BY nom ";
				break;
			}

			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				listeemploye.add(setEmploye(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		if (detail && listeemploye.size() > 0) {

			for (EmployeC emp : listeemploye) {

				emp.setListeDetailPrime(getListeDetailPrime(emp));
				emp.setListeDetailIndemnite(getListeDetailIndemnite(emp));
				emp.setListeDetailDeduction(getListeDetaildeduction(emp));
				emp.setListeDetailCotisation(getListeDetailCotisationEmploye(emp));
				emp.setListBanquePaiement(getListeDetailBanqueEmploye(emp));
				emp.setListeDetailComission(getListeComission(emp));
			}
		}

		return listeemploye;
	}

	public List<EmployeC> getListAllEmploye(String codeDeb, String codeFin, boolean detail) {
		List<EmployeC> listeemploye = new ArrayList<EmployeC>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.employe)
					+ " WHERE 1=1 AND partenaire=0 AND date_sortie IS NULL ";
			if (!codeDeb.equals("")) {
				sqlRequest = String.valueOf(sqlRequest) + " AND code>='" + codeDeb + "'";
			}
			if (!codeFin.equals("")) {
				sqlRequest = String.valueOf(sqlRequest) + " AND code<='" + codeFin + "'";
			}
			sqlRequest = String.valueOf(sqlRequest) + " ORDER BY code";

			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				listeemploye.add(setEmploye(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		if (detail && listeemploye.size() > 0) {

			for (EmployeC emp : listeemploye) {

				emp.setListeDetailPrime(getListeDetailPrime(emp));
				emp.setListeDetailIndemnite(getListeDetailIndemnite(emp));
				emp.setListeDetailDeduction(getListeDetaildeduction(emp));
				emp.setListeDetailCotisation(getListeDetailCotisationEmploye(emp));
				emp.setListBanquePaiement(getListeDetailBanqueEmploye(emp));
				emp.setListeDetailComission(getListeComission(emp));
			}
		}

		return listeemploye;
	}

	public List<EmployeC> getListEmploye(String code, String nom, boolean actif) {
		Statement stmt = null;
		ResultSet rs = null;
		List<EmployeC> liste = new ArrayList<EmployeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE 1=1 AND partenaire=0 ";
		if (code != null && code.trim() != "") {
			sql += " AND code like '" + code + "%'";
		}
		if (nom != null && nom.trim() != "") {
			sql += " AND nom like '%" + nom + "%'";
		}
		if (actif)
			sql += " AND date_retraite IS NULL";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				liste.add(setEmploye(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		
		return liste;
	}
	public List<EmployeC> getListEmploye(String nomPrenom, boolean actif) {
		Statement stmt = null;
		ResultSet rs = null;
		List<EmployeC> liste = new ArrayList<EmployeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE 1=1 AND partenaire=0 ";
		
		if (nomPrenom != null && nomPrenom.trim() != "") {
			sql += " AND nom like '%" + nomPrenom + "%'";
		}
		if (actif)
			sql += " AND date_retraite IS NULL";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				liste.add(setEmploye(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		
		if (liste.size() > 0) {

			for (EmployeC emp : liste) {

				emp.setListeDetailPrime(getListeDetailPrime(emp));
				emp.setListeDetailIndemnite(getListeDetailIndemnite(emp));
				emp.setListeDetailDeduction(getListeDetaildeduction(emp));
				emp.setListeDetailCotisation(getListeDetailCotisationEmploye(emp));
				emp.setListBanquePaiement(getListeDetailBanqueEmploye(emp));
				emp.setListeDetailComission(getListeComission(emp));
			}
		}
		return liste;
	}

	public int getListEmployeParBareme(double salaireMin, double salaireMax, int type, int idCot, Date datDb,
			Date datFn) {
		Statement stmt = null;
		ResultSet rs = null;
		int nombre = 0;

		try {
			stmt = con.createStatement();

			String sqlRequest = "SELECT count(*) AS Nbr FROM tbl_employe AS A LEFT JOIN tbl_bulletin_paie AS B "
					+ "ON A.id=B.id_employe LEFT JOIN tbl_bulletin_cotisation AS C ON B.id=C.id_bulletin "
					+ "WHERE C.id_cotisation=" + idCot + "  AND A.type_employe=" + type;

			if (salaireMax == 0.0D) {
				salaireMax = Double.MAX_VALUE;
			}
			sqlRequest += "  AND (C.montant_base between " + salaireMin + " AND " + salaireMax + ")";

			if (datDb != null)
				sqlRequest += " AND B.date_paie>='" + HelperC.convertDate(datDb, false) + "'";
			if (datFn != null)
				sqlRequest += " AND B.date_paie<='" + HelperC.convertDate(datFn, false) + "'";

			rs = stmt.executeQuery(sqlRequest);

			if (rs.next()) {

				nombre = rs.getInt("Nbr");
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return nombre;
	}

	public double getBaseParBareme(double salaireMin, double salaireMax, int type, int idCot, Date datDb, Date datFn) {

		Statement stmt = null;
		ResultSet rs = null;
		double nombre = 0;

		try {
			stmt = con.createStatement();

			String sqlRequest = "SELECT  SUM(C.montant_base) AS montant FROM tbl_employe AS A LEFT JOIN tbl_bulletin_paie AS B "
					+ "ON A.id=B.id_employe LEFT JOIN tbl_bulletin_cotisation AS C ON B.id=C.id_bulletin "
					+ "WHERE C.id_cotisation=" + idCot + "  AND A.type_employe=" + type;

			if (salaireMax == 0.0D) {
				salaireMax = Double.MAX_VALUE;
			}
			sqlRequest += "  AND (C.montant_base between " + salaireMin + " AND " + salaireMax + ")";

			if (datDb != null)
				sqlRequest += " AND B.date_paie>='" + HelperC.convertDate(datDb, false) + "'";
			if (datFn != null)
				sqlRequest += " AND B.date_paie<='" + HelperC.convertDate(datFn, false) + "'";

			rs = stmt.executeQuery(sqlRequest);

			if (rs.next()) {
				nombre = rs.getDouble("montant");
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return nombre;
	}

	public double getMontantParBareme(double salaireMin, double salaireMax, int type, int idCot, Date datDb,
			Date datFn) {

		Statement stmt = null;
		ResultSet rs = null;
		double nombre = 0;

		try {
			stmt = con.createStatement();

			String sqlRequest = "SELECT  SUM(C.montant_salarial) AS montant FROM tbl_employe AS A LEFT JOIN tbl_bulletin_paie AS B "
					+ "ON A.id=B.id_employe LEFT JOIN tbl_bulletin_cotisation AS C ON B.id=C.id_bulletin "
					+ "WHERE C.id_cotisation=" + idCot + "  AND A.type_employe=" + type;

			if (salaireMax == 0.0D) {
				salaireMax = Double.MAX_VALUE;
			}
			sqlRequest += "  AND (C.montant_base between " + salaireMin + " AND " + salaireMax + ")";

			if (datDb != null)
				sqlRequest += " AND B.date_paie>='" + HelperC.convertDate(datDb, false) + "'";
			if (datFn != null)
				sqlRequest += " AND B.date_paie<='" + HelperC.convertDate(datFn, false) + "'";

			rs = stmt.executeQuery(sqlRequest);

			if (rs.next()) {
				nombre = rs.getDouble("montant");
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return nombre;
	}

	public List<EmployeC> getAllEmployeByDesignation(String nom) {
		List<EmployeC> listeemploye = new ArrayList<EmployeC>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE nom LIKE'%"
					+ nom + "%' AND (partenaire=0 OR partenaire IS NULL) ORDER BY id";
			rs = stmt.executeQuery(sqlRequest);
			
			while (rs.next())
				listeemploye.add(setEmploye(rs));

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listeemploye;
	}

	private boolean insertDetailOrgane(DetailOrganeC detO, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			detO.setId(getId(Tables.getTableName(Tables.TableName.detailOrgane)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailOrgane)
					+ " (id, id_employe, id_direction,id_departement,id_service)"
					+ " VALUES (?,?,?,?,?) ";
			stmt = conx.prepareStatement(sql);

			stmt.setInt(1, detO.getId());
			stmt.setInt(2, detO.getIdEmpl());
			
			if (detO.getIdDirection() > 0) {
				stmt.setInt(3, detO.getIdDirection());
			} else {
				stmt.setObject(3, (Object) null);
			}
			
			if (detO.getIdDepartmt() > 0) {
				stmt.setInt(4, detO.getIdDepartmt());
			} else {
				stmt.setObject(4, (Object) null);
			}
			
			if (detO.getIdSrvice() > 0) {
				stmt.setInt(5, detO.getIdSrvice());
			} else {
				stmt.setObject(5, (Object) null);
			}

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailOrgane(DetailOrganeC detO, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailOrgane)
				+ " SET id_direction=?,id_departement=?,id_service=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);

			if (detO.getIdDirection() > 0) {
				stmt.setInt(1, detO.getIdDirection());
			} else {
				stmt.setObject(1, (Object) null);
			}
			
			if (detO.getIdDepartmt() > 0) {
				stmt.setInt(2, detO.getIdDepartmt());
			} else {
				stmt.setObject(2, (Object) null);
			}
			
			if (detO.getIdSrvice() > 0) {
				stmt.setInt(3, detO.getIdSrvice());
			} else {
				stmt.setObject(3, (Object) null);
			}
			stmt.setInt(4, detO.getId());

			stmt.executeUpdate();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateDetailOrgane(DetailOrganeC detO) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (detO.getId() == 0) {

				saved = insertDetailOrgane(detO, conn);
			} else {

				saved = updateDetailOrgane(detO, conn);
			}

			if (saved) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		return saved;
	}

	private DetailOrganeC setDetailOrgane(ResultSet rs) throws SQLException {
		DetailOrganeC detai = new DetailOrganeC();
		detai.setId(rs.getInt("id"));

		if (rs.getObject("id_direction") != null) {
			detai.setIdDirection(rs.getInt("id_direction"));
		}
		if (rs.getObject("id_departement") != null) {
			detai.setIdDepartmt(rs.getInt("id_departement"));
		}


		if (rs.getObject("id_service") != null) {
			detai.setIdSrvice(rs.getInt("id_service"));
		}

		return detai;
	}

	public List<DetailOrganeC> getListeDetailOrgane(EmployeC emp) {
		List<DetailOrganeC> detai = new ArrayList<DetailOrganeC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailOrgane) + "  WHERE  id_employe="
					+ emp.getId();

			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setDetailOrgane(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	public DetailOrganeC getDetailOrgane(EmployeC employe, int idServc) {
		DetailOrganeC detai = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailOrgane) + " WHERE id_employe="
				+ employe.getId();

		if (idServc > 0)
			sql = String.valueOf(sql) + " AND id_service=" + idServc;

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);

			if (res.next()) {
				detai = setDetailOrgane(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return detai;
	}

	public DetailOrganeC getDetailOrgane(EmployeC employe) {
		DetailOrganeC detai = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailOrgane) + " WHERE id_employe='"
				+ employe.getId() + "' ORDER BY id DESC LIMIT 1";

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);

			if (res.next()) {
				detai = setDetailOrgane(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return detai;
	}

	private boolean insertDetailBanqueEmploye(DetailBanqueEmployeC detail, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			detail.setId(getId(Tables.getTableName(Tables.TableName.detailBanqueEmploye)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailBanqueEmploye)
					+ " (id,id_employe,id_banque,numero_compte,pourcentage_salaire," + " montant,principal) "
					+ " VALUES (?,?,?,?,?,?,?) ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, detail.getId());
			if (detail.getEmploye() != null) {

				stmt.setInt(2, detail.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (detail.getBanque() != null) {

				stmt.setInt(3, detail.getBanque().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setString(4, detail.getNumeroCompte());
			stmt.setDouble(5, detail.getPourcentageSalaire());
			stmt.setDouble(6, detail.getMontant());
			stmt.setBoolean(7, detail.isPrincipal());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailBanqueEmploye(DetailBanqueEmployeC detail, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailBanqueEmploye)
				+ " SET id_employe=?,id_banque=?,numero_compte=?,"
				+ "pourcentage_salaire=?,montant=?,principal=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			if (detail.getEmploye() != null) {

				stmt.setInt(1, detail.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (detail.getBanque() != null) {

				stmt.setInt(2, detail.getBanque().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setString(3, detail.getNumeroCompte());
			stmt.setDouble(4, detail.getPourcentageSalaire());
			stmt.setDouble(5, detail.getMontant());
			stmt.setBoolean(6, detail.isPrincipal());
			stmt.setInt(7, detail.getId());
			stmt.executeUpdate();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private DetailBanqueEmployeC setDetailBanqueEmploye(ResultSet rs) throws SQLException {
		DetailBanqueEmployeC detai = new DetailBanqueEmployeC();
		detai.setId(rs.getInt("id"));
		detai.setIdEmploye(rs.getInt("id_employe"));
		if (rs.getObject("id_banque") != null) {
			detai.setBanque(FichierBaseDAO.getInstance().getBanque(rs.getInt("id_banque")));
		}
		detai.setNumeroCompte(rs.getString("numero_compte"));
		detai.setPourcentageSalaire(rs.getDouble("pourcentage_salaire"));
		detai.setMontant(rs.getDouble("montant"));
		detai.setPrincipal(rs.getBoolean("principal"));
		return detai;
	}

	public List<DetailBanqueEmployeC> getListeDetailBanqueEmploye(EmployeC emp) {
		List<DetailBanqueEmployeC> detai = new ArrayList<DetailBanqueEmployeC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailBanqueEmploye)
					+ "  WHERE  id_employe='" + emp.getId() + "'";

			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setDetailBanqueEmploye(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	public List<DetailBanqueEmployeC> getListeDetailBanque(int idBk) {
		List<DetailBanqueEmployeC> detai = new ArrayList<DetailBanqueEmployeC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailBanqueEmploye)
					+ "  WHERE  id_banque=" + idBk;
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setDetailBanqueEmploye(rs));
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	public DetailBanqueEmployeC getBanquePrincipal(int idEmploye) {
		DetailBanqueEmployeC detai = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailBanqueEmploye)
					+ "  WHERE  id_employe=" + idEmploye + " AND principal=1";
			rs = stmt.executeQuery(sqlRequest);
			if (rs.next()) {
				detai = setDetailBanqueEmploye(rs);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	private boolean insertAffectation(AffectationC affectation, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		affectation.setId(getId(Tables.getTableName(Tables.TableName.affectation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.affectation)
				+ " (id,id_employe,id_fonction,date_debut,reference) VALUES (?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, affectation.getId());
			stmt.setInt(2, affectation.getIdEmploye());
			stmt.setInt(3, affectation.getIdFonction());
			stmt.setObject(4, affectation.getDateDebut());
			stmt.setString(5, affectation.getReference());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateAffectation(AffectationC affectation) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.affectation)
				+ " SET  id_fonction=?,date_debut=?,reference=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, affectation.getFonction().getId());
			stmt.setObject(2, affectation.getDateDebut());
			stmt.setString(3, affectation.getReference());
			stmt.setInt(4, affectation.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public AffectationC getAffectationEmploye(EmployeC fonctionnaire) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AffectationC affectation = null;
		String sql = "SELECT id,id_fonction FROM " + Tables.getTableName(Tables.TableName.affectation)
				+ " WHERE id_employe=? AND date_fin IS NULL";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, fonctionnaire.getId());
			rs = stmt.executeQuery();

			if (rs.next()) {
				affectation = new AffectationC();
				affectation.setId(rs.getInt("id"));
				affectation.setFonction(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_fonction"),
						Tables.getTableName(Tables.TableName.fonction)));
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return affectation;
	}

	private boolean insertDetailGrade(DetailGradeC detailGrad, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			detailGrad.setId(getId(Tables.getTableName(Tables.TableName.detailGradeEmploye)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailGradeEmploye)
					+ " (id,id_employe,id_grade,date_debut_grade,motif,id_ancien_grade,type_avancement) "
					+ "VALUES (?,?,?,?,?,?,?) ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, detailGrad.getId());
			if (detailGrad.getIdEmpl() > 0) {

				stmt.setInt(2, detailGrad.getIdEmpl());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (detailGrad.getIdNvGrd() > 0) {

				stmt.setInt(3, detailGrad.getIdNvGrd());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setObject(4, detailGrad.getDateDebut());
			stmt.setString(5, detailGrad.getComment());

			if (detailGrad.getIdAncGrd() > 0) {

				stmt.setInt(6, detailGrad.getIdAncGrd());
			} else {

				stmt.setObject(6, (Object) null);
			}
			stmt.setInt(7, detailGrad.getIdNvGrd());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean insertNouveauGrade(int idEmpl, int idgrd, Date dateDeb, String comment, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			int id = getId(Tables.getTableName(Tables.TableName.detailGradeEmploye));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailGradeEmploye)
					+ " (id,id_employe,id_grade,date_debut_grade,motif) " + "VALUES (?,?,?,?,?) ";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setInt(2, idEmpl);
			stmt.setObject(3, dateDeb);
			stmt.setString(4, comment);

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateDetailGrade(DetailGradeC detg) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (detg.getId() == 0) {

				saved = insertDetailGrade(detg, conn);
			} else {

				// saved = updateDetailGrade(detg,conn);
			}

			if (saved) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		return saved;
	}

	private DetailGradeC setDetailEchelon(ResultSet rs) throws SQLException {
		DetailGradeC detai = new DetailGradeC();
		detai.setId(rs.getInt("id"));

		detai.setIdEmpl(rs.getInt("id_employe"));
		if (rs.getObject("id_grade") != null) {
			detai.setIdNvGrd(rs.getInt("id_grade"));
		}
		if (rs.getObject("id_ancien_grade") != null) {
			detai.setIdAncGrd(rs.getInt("id_ancien_grade"));
		}
		detai.setDateDebut(rs.getDate("date_debut_grade"));

		detai.setComment(rs.getString("motif"));
		return detai;
	}

	public List<DetailGradeC> getListeDetailGrade(EmployeC emp) {
		List<DetailGradeC> detai = new ArrayList<DetailGradeC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailGradeEmploye)
					+ "  WHERE  id_employe=" + emp.getId();
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setDetailEchelon(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	public DetailGradeC getDetailGradeParEmploye(EmployeC employe) {
		DetailGradeC detai = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "";

		try {
			stmt = con.createStatement();

			sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailGradeEmploye) + " WHERE id_employe='"
					+ employe.getId() + "' ORDER BY id DESC LIMIT 1";
			res = stmt.executeQuery(sql);

			if (res.next()) {
				detai = setDetailEchelon(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return detai;
	}

	public DetailGradeC getDetailGradeParEmploye(EmployeC employe, GradePersonnelC grade, int id) {
		DetailGradeC detai = null;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(" SELECT * FROM " + Tables.getTableName(Tables.TableName.detailGradeEmploye)
					+ " WHERE id_employe='" + employe.getId() + "' AND id_grade='" + grade.getId() + "' AND id<>'" + id
					+ "' ORDER BY id DESC LIMIT 1");
			if (res.next()) {
				detai = setDetailEchelon(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return detai;
	}

	public DetailGradeC getDetailGradeParEmploye(int idEmp, int idGrd, int idRef) {
		DetailGradeC detai = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailGradeEmploye) + " WHERE id_employe="
				+ idEmp;
		if (idRef > 0)
			sql += " AND ref_motif=" + idRef;
		if (idGrd > 0)
			sql += " AND id_grade=" + idGrd;

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			if (res.next()) {
				detai = setDetailEchelon(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return detai;
	}

	private boolean insertDetailNiveauFormation(DetailNiveauFormationC det, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailNiveauFormation)
				+ " (id,id_employe,id_niveau,date_debut,date_fin,etat) VALUES (?,?,?,?,?,?)";
		det.setId(getId(Tables.getTableName(Tables.TableName.detailNiveauFormation)));

		try {
			stmt = conx.prepareStatement(sqlRequest);
			stmt.setInt(1, det.getId());
			if (det.getEmploye() != null) {
				stmt.setInt(2, det.getEmploye().getId());
			}
			if (det.getNiveau() != null) {

				stmt.setInt(3, det.getNiveau().getId());
			} else {

				stmt.setObject(3, (Object) null, 4);
			}
			stmt.setObject(4, det.getDateDebut());
			stmt.setObject(5, det.getDateFin());
			stmt.setInt(6, HelperC.GetIntValueByBoolean(Boolean.valueOf(det.isEtat())));
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailNiveauFormation(DetailNiveauFormationC det, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.detailNiveauFormation)
				+ " SET id_niveau=?,date_debut=?,date_fin=?,etat=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sqlRequest);
			if (det.getNiveau() != null) {

				stmt.setInt(1, det.getNiveau().getId());
			} else {

				stmt.setObject(1, (Object) null, 4);
			}
			if (det.getDateDebut() != null) {

				stmt.setObject(2, det.getDateDebut());
			} else {

				stmt.setObject(2, null);
			}
			if (det.getDateFin() != null) {

				stmt.setObject(3, det.getDateFin());
			} else {

				stmt.setObject(3, null);
			}
			stmt.setInt(4, HelperC.GetIntValueByBoolean(Boolean.valueOf(det.isEtat())));
			stmt.setInt(5, det.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private DetailNiveauFormationC setDetailNiveauFormation(ResultSet rs) throws SQLException {
		DetailNiveauFormationC detai = new DetailNiveauFormationC();
		detai.setId(rs.getInt("id"));
		EmployeC employe = new EmployeC();
		detai.setEmploye(employe);
		if (rs.getObject("id_niveau") != null) {
			detai.setNiveau(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_niveau"),
					Tables.getTableName(Tables.TableName.niveauFormation)));
		}
		detai.setDateDebut(rs.getDate("date_debut"));
		detai.setDateDebutS(HelperC.changeDateFormate(detai.getDateDebut()));
		detai.setDateFin(rs.getDate("date_fin"));
		detai.setDateFinS(HelperC.changeDateFormate(detai.getDateFin()));
		detai.setEtat(rs.getBoolean("etat"));
		return detai;
	}

	public List<DetailNiveauFormationC> getListeDetailNiveauFormation(EmployeC emp) {
		List<DetailNiveauFormationC> detai = new ArrayList<DetailNiveauFormationC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailNiveauFormation)
					+ "  WHERE  id_employe='" + emp.getId() + "'";
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setDetailNiveauFormation(rs));

			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	public DetailNiveauFormationC getDetailNiveauFormation(EmployeC employe) {
		DetailNiveauFormationC detai = null;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(" SELECT * FROM " + Tables.getTableName(Tables.TableName.detailNiveauFormation)
					+ " WHERE id_employe='" + employe.getId() + "' ORDER BY id DESC LIMIT 1");
			if (res.next()) {
				detai = setDetailNiveauFormation(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return detai;
	}

	public DetailNiveauFormationC getDetailNiveauFormation(EmployeC employe, int idNiveau) {
		DetailNiveauFormationC detai = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailNiveauFormation)
				+ " WHERE id_employe=" + employe.getId() + " AND id_niveau=" + idNiveau;

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			if (res.next()) {
				detai = setDetailNiveauFormation(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return detai;
	}

	private boolean insertDetailPrimeEmploye(DetailPrimeEmployeC detP, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		detP.setId(getId(Tables.getTableName(Tables.TableName.detailPrimeEmploye)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailPrimeEmploye)
				+ " (id,id_employe,id_prime,montant,taux,id_parametre) VALUES (?,?,?,?,?,?) ";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, detP.getId());
			if (detP.getEmploye() != null) {

				stmt.setInt(2, detP.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}

			if (detP.getPrime() != null) {

				stmt.setInt(3, detP.getPrime().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, detP.getMontant());
			stmt.setDouble(5, detP.getTaux());

			if (detP.getIdParametre() > 0) {
				stmt.setInt(6, detP.getIdParametre());
			} else {
				stmt.setObject(6, (Object) null);
			}
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailPrimeEmploye(DetailPrimeEmployeC detP, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailPrimeEmploye)
				+ " SET id_employe=?,id_prime=?,montant=?,taux=?,id_parametre=? WHERE id=? ";

		try {
			stmt = conx.prepareStatement(sql);
			if (detP.getEmploye() != null) {

				stmt.setInt(1, detP.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (detP.getPrime() != null) {

				stmt.setInt(2, detP.getPrime().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, detP.getMontant());
			stmt.setDouble(4, detP.getTaux());

			if (detP.getIdParametre() > 0) {
				stmt.setInt(5, detP.getIdParametre());
			} else {
				stmt.setObject(5, (Object) null);
			}
			stmt.setInt(6, detP.getId());

			stmt.executeUpdate();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public List<DetailPrimeEmployeC> getListeDetailPrime(EmployeC emp) {
		List<DetailPrimeEmployeC> detai = new ArrayList<DetailPrimeEmployeC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailPrimeEmploye)
					+ " WHERE  id_employe=" + emp.getId() + " AND bloque=0";

			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setDetailPrimeEmploye(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	public DetailPrimeEmployeC getDetailPrimeEmploye(int idParm, int idPrime, int idEmpl) {
		DetailPrimeEmployeC detai = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailPrimeEmploye)
					+ " WHERE  id_prime=" + idPrime + " AND id_employe=" + idEmpl;

			rs = stmt.executeQuery(sqlRequest);
			if (rs.next()) {
				detai = setDetailPrimeEmploye(rs);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	private DetailPrimeEmployeC setDetailPrimeEmploye(ResultSet rs) throws SQLException {
		DetailPrimeEmployeC detai = new DetailPrimeEmployeC();
		detai.setId(rs.getInt("id"));
		if (rs.getObject("id_prime") != null) {
			detai.setPrime(FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getInt("id_prime")));
		}
		detai.setMontant(rs.getDouble("montant"));
		detai.setTaux(rs.getDouble("taux"));
		detai.setMontantS(HelperC.TraitementMontant.getMontantFormate(detai.getMontant(), 0));
		if (rs.getObject("id_parametre") != null) {
			detai.setIdParametre(rs.getInt("id_parametre"));
		}
		detai.setBloque(rs.getBoolean("bloque"));
		return detai;
	}

	private boolean insertDetailIndemniteEmploye(DetailIndemniteEmployeC detP, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			detP.setId(getId(Tables.getTableName(Tables.TableName.detailIndemniteEmploye)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailIndemniteEmploye)
					+ " (id,id_employe,id_indemnite,montant,taux) VALUES (?,?,?,?,?) ";
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, detP.getId());
			if (detP.getEmploye() != null) {

				stmt.setInt(2, detP.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (detP.getIndemnite() != null) {

				stmt.setInt(3, detP.getIndemnite().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, detP.getMontant());
			stmt.setDouble(5, detP.getTaux());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailIndemniteEmploye(DetailIndemniteEmployeC detP, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailIndemniteEmploye)
				+ " SET id_employe=?,id_indemnite=?,montant=?,taux=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			if (detP.getEmploye() != null) {

				stmt.setInt(1, detP.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (detP.getIndemnite() != null) {

				stmt.setInt(2, detP.getIndemnite().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, detP.getMontant());
			stmt.setDouble(4, detP.getTaux());
			stmt.setInt(6, detP.getId());
			stmt.executeUpdate();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public List<DetailIndemniteEmployeC> getListeDetailIndemnite(EmployeC emp) {
		List<DetailIndemniteEmployeC> detai = new ArrayList<DetailIndemniteEmployeC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailIndemniteEmploye) + "  "
					+ "WHERE  id_employe='" + emp.getId() + "'";
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setDetailIndemniteEmploye(rs));
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	public DetailIndemniteEmployeC getDetailIndemnite(int IdEmp, int idIndem) {
		DetailIndemniteEmployeC detai = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailIndemniteEmploye)
					+ " WHERE  id_employe=" + IdEmp + " AND id_indemnite=" + idIndem;
			rs = stmt.executeQuery(sqlRequest);
			if (rs.next()) {
				detai = setDetailIndemniteEmploye(rs);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	private DetailIndemniteEmployeC setDetailIndemniteEmploye(ResultSet rs) throws SQLException {
		DetailIndemniteEmployeC detai = new DetailIndemniteEmployeC();
		detai.setId(rs.getInt("id"));
		EmployeC employe = new EmployeC();
		detai.setEmploye(employe);
		if (rs.getObject("id_indemnite") != null) {
			detai.setIndemnite(FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getInt("id_indemnite")));
		}
		detai.setMontant(rs.getDouble("montant"));
		detai.setTaux(rs.getDouble("taux"));

		detai.setMontantS(HelperC.TraitementMontant.getMontantFormate(detai.getMontant(), 0));
		return detai;
	}

	private boolean insertDetailCotisation(DetailCotisationEmployeC detai, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		detai.setId(getId(Tables.getTableName(Tables.TableName.detailCotisationEmploye)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailCotisationEmploye)
				+ " (id,id_employe,id_cotisation,montant_salarial,montant_patronal) VALUES (?,?,?,?,?) ";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, detai.getId());
			if (detai.getEmploye() != null) {

				stmt.setInt(2, detai.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (detai.getCotisation() != null) {

				stmt.setInt(3, detai.getCotisation().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, detai.getMontantSalarial());
			stmt.setDouble(5, detai.getMontantPatronal());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	

	private boolean updateDetailCotisation(DetailCotisationEmployeC detai, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailCotisationEmploye)
				+ " SET id_employe=?,id_cotisation=?,montant_salarial=?,montant_patronal=? WHERE id=? ";

		try {
			stmt = conx.prepareStatement(sql);
			if (detai.getEmploye() != null) {

				stmt.setInt(1, detai.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (detai.getCotisation() != null) {

				stmt.setInt(2, detai.getCotisation().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, detai.getMontantSalarial());
			stmt.setDouble(4, detai.getMontantPatronal());
			stmt.setInt(5, detai.getId());
			stmt.executeUpdate();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private DetailCotisationEmployeC setDetailCotisationEmploye(ResultSet rs) throws SQLException {
		DetailCotisationEmployeC detai = new DetailCotisationEmployeC();
		detai.setId(rs.getInt("id"));
		if (rs.getObject("id_cotisation") != null) {
			detai.setCotisation(FichierBaseDAO.getInstance().getCotisation(rs.getInt("id_cotisation")));
		}
		detai.setMontantPatronal(rs.getDouble("montant_patronal"));
		detai.setMontantSalarial(rs.getDouble("montant_salarial"));
		EmployeC employe = new EmployeC();
		detai.setEmploye(employe);
		return detai;
	}

	public List<DetailCotisationEmployeC> getListeDetailCotisationEmploye(EmployeC emp) {
		List<DetailCotisationEmployeC> detai = new ArrayList<DetailCotisationEmployeC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailCotisationEmploye)
					+ " WHERE id_employe='" + emp.getId() + "' AND bloque=0";
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setDetailCotisationEmploye(rs));
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	private boolean insertDetailDeduction(DetailDeductionC detai, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			detai.setId(getId(Tables.getTableName(Tables.TableName.detailDeductionEmploye)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailDeductionEmploye)
					+ " (id,id_employe,id_deduction,montant) " + " VALUES (?,?,?,?) ";
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, detai.getId());
			if (detai.getEmploye() != null) {

				stmt.setInt(2, detai.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (detai.getDeduction() != null) {

				stmt.setInt(3, detai.getDeduction().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, detai.getMontant());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailDuction(DetailDeductionC detai, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailDeductionEmploye)
				+ " SET id_employe=?,id_deduction=?,montant=? WHERE id=? ";

		try {
			stmt = conx.prepareStatement(sql);
			if (detai.getEmploye() != null) {

				stmt.setInt(1, detai.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (detai.getDeduction() != null) {

				stmt.setInt(2, detai.getDeduction().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, detai.getMontant());
			stmt.setInt(4, detai.getId());
			stmt.executeUpdate();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private DetailDeductionC setDetaildeduction(ResultSet rs) throws SQLException {
		DetailDeductionC detai = new DetailDeductionC();
		detai.setId(rs.getInt("id"));
		if (rs.getObject("id_deduction") != null) {
			detai.setDeduction(FichierBaseDAO.getInstance().getDeduction(rs.getInt("id_deduction")));
		}
		detai.setMontant(rs.getDouble("montant"));
		detai.setMontantS(HelperC.TraitementMontant.getMontantFormate(detai.getMontant(), 0));
		EmployeC employe = new EmployeC();
		detai.setEmploye(employe);
		return detai;
	}

	public List<DetailDeductionC> getListeDetaildeduction(EmployeC emp) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DetailDeductionC> detai = new ArrayList<DetailDeductionC>();

		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailDeductionEmploye)
					+ " WHERE id_employe='" + emp.getId() + "'";
			rs = stmt.executeQuery(sqlRequest);

			while (rs.next()) {
				detai.add(setDetaildeduction(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	private boolean insertDetailComission(DetailComissionEmployeC detai, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		detai.setId(getId(Tables.getTableName(Tables.TableName.detailComissionEmploye)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailComissionEmploye)
				+ " (id_employe,id_com,montant,taux) VALUES (?,?,?,?) ";

		try {
			stmt = conx.prepareStatement(sql);
			
			if (detai.getIdEmpl() >0) {

				stmt.setInt(1, detai.getIdEmpl());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (detai.getComission() != null) {

				stmt.setInt(2, detai.getComission().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, detai.getMontant());
			stmt.setDouble(4, detai.getTaux());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}
	
	private boolean updateDetailComission(DetailComissionEmployeC detai, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailComissionEmploye)
				+ " SET id_com=?,montant=?,taux=? WHERE id=? ";

		try {
			stmt = conx.prepareStatement(sql);
			if (detai.getComission() != null) {

				stmt.setInt(1, detai.getComission().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			
			stmt.setDouble(2, detai.getMontant());
			stmt.setDouble(3, detai.getTaux());
			stmt.setInt(4, detai.getId());
			stmt.executeUpdate();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}
	
	private DetailComissionEmployeC setDetailComission(ResultSet rs) throws SQLException {
		DetailComissionEmployeC detai = new DetailComissionEmployeC();
		detai.setId(rs.getInt("id"));
		if (rs.getObject("id_com") != null) {
			detai.setComission(FichierBaseDAO.getInstance().getCotisation(rs.getInt("id_com")));
		}
		detai.setMontant(rs.getDouble("montant"));
		detai.setMontantS(HelperC.TraitementMontant.getMontantFormate(detai.getMontant(), 0));
		detai.setTaux(rs.getDouble("taux"));
		
		return detai;
	}
	public List<DetailComissionEmployeC> getListeComission(EmployeC emp) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DetailComissionEmployeC> detai = new ArrayList<DetailComissionEmployeC>();

		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailComissionEmploye)
					+ " WHERE id_employe='" + emp.getId() + "'";
			rs = stmt.executeQuery(sqlRequest);

			while (rs.next()) {
				detai.add(setDetailComission(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	private boolean insertPersonneCharge(PersonneChargeC person) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.personneCharge)
				+ "(id_employe,nom,statut_personne,date_naissance,date_saisie,"
				+ "montant_allocation,photo,genre,nombre) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sqlRequest);

			if (person.getEmploye() != null) {
				pstmt.setInt(1, person.getEmploye().getId());
			}
			pstmt.setString(2, person.getNomPersonneCharge());
			pstmt.setInt(3, person.getRelation());
			if (person.getDateNaissance() == null) {

				pstmt.setObject(4, null);
			} else {

				pstmt.setObject(4, person.getDateNaissance());
			}
			if (person.getDateSaisie() == null) {

				pstmt.setObject(5, null);
			} else {

				pstmt.setObject(5, person.getDateSaisie());
			}
			pstmt.setDouble(6, person.getMontant());
			pstmt.setString(7, person.getPhoto());
			pstmt.setString(8, person.getSexe());
			pstmt.setInt(9, person.getNombre());
			
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updatePersonneCharge(PersonneChargeC person) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.personneCharge)
				+ " SET nom=?,statut_personne=?,date_naissance=?,date_saisie=?,"
				+ "montant_allocation=?,photo=?,genre=?,nombre=? WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setString(1, person.getNomPersonneCharge());
			pstmt.setInt(2, person.getRelation());
			if (person.getDateNaissance() == null) {

				pstmt.setObject(3, null);
			} else {

				pstmt.setObject(3, person.getDateNaissance());
			}
			if (person.getDateSaisie() == null) {

				pstmt.setObject(4, null);
			} else {

				pstmt.setObject(4, person.getDateSaisie());
			}
			pstmt.setDouble(5, person.getMontant());
			pstmt.setString(6, person.getPhoto());
			pstmt.setString(7, person.getSexe());
			pstmt.setInt(8, person.getNombre());
			pstmt.setInt(9, person.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	public boolean insertUpdatePersonneCharge(PersonneChargeC personne) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);

			if (personne.getId() == 0) {

				saved = insertPersonneCharge(personne);

			} else
				saved = updatePersonneCharge(personne);

			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saved;
	}

	private PersonneChargeC setPersonneCharge(ResultSet rs) throws SQLException {
		PersonneChargeC person = new PersonneChargeC();
		person.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			person.setEmploye(getEmployeSimple(rs.getInt("id_employe")));
		}
		person.setNomPersonneCharge(rs.getString("nom"));
		person.setRelation(rs.getInt("statut_personne"));
		if (rs.getObject("date_naissance") != null) {
			person.setDateNaissance((Date) rs.getObject("date_naissance"));
		}
		if (rs.getObject("date_saisie") != null) {
			person.setDateSaisie((Date) rs.getObject("date_saisie"));
		}
		person.setLibelleRelation(Constante.getLibelleStatutPersonneACharge(person.getRelation()));
		person.setMontant(rs.getDouble("montant_allocation"));
		person.setPhoto(rs.getString("photo"));
		person.setSexe(rs.getString("genre"));
		person.setNombre(rs.getInt("nombre"));
		return person;
	}

	public PersonneChargeC getPersonneById(int idPersonne) {
		PersonneChargeC personne = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.personneCharge) + " WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idPersonne);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				personne = setPersonneCharge(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return personne;
	}

	public PersonneChargeC getPersonneByEmploye(int idEmploye) {
		PersonneChargeC personne = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.personneCharge)
				+ " WHERE id_employe=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idEmploye);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				personne = setPersonneCharge(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return personne;
	}

	public List<PersonneChargeC> getListPersonneByEmploye(int idEmploye) {
		List<PersonneChargeC> list = new ArrayList<PersonneChargeC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.personneCharge)
				+ " WHERE id_employe=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idEmploye);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list.add(setPersonneCharge(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return list;
	}

	public List<PersonneChargeC> getAllPersonnesChargeByEmploye(int idEmploye) {
		List<PersonneChargeC> personnes = new ArrayList<PersonneChargeC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.personneCharge)
				+ " WHERE id_employe=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idEmploye);
			rs = pstmt.executeQuery();

			for (; rs.next(); personnes.add(setPersonneCharge(rs)))
				;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return personnes;
	}

	public boolean deletePersonneEnCharge(PersonneChargeC personne) {
		boolean deleted = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			deleted = deleteList(personne.getId(), "id", Tables.getTableName(Tables.TableName.personneCharge));

			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return deleted;
	}

	public boolean insertUpdateBulletinPaie(BulletinPaieC bulletin) {
		boolean saved = false;
		Connection conn = con;

		try {
			conn.setAutoCommit(false);

			if (bulletin.getId() == 0) {

				saved = insertBulletinPaie(bulletin, conn);
			} else {

				saved = updateBulletinPaie(bulletin, conn);
			}

			if (saved) {

				if (bulletin.getListAllocation().size() > 0) {
					for (BulletinAllocationC alcBulletin : bulletin.getListAllocation()) {

						alcBulletin.setIdBulletin(bulletin.getId());

						if (alcBulletin.getId() == 0) {

							saved = insertBulletinAllocation(alcBulletin, conn);
							continue;
						}
						saved = updateBulletinAllocation(alcBulletin, conn);
					}
				}
				if (saved)
				if (bulletin.getListeCotisation().size() > 0) {
					for (BulletinCotisationC cotBulletin : bulletin.getListeCotisation()) {

						cotBulletin.setIdBulletin(bulletin.getId());
						if (cotBulletin.getId() == 0) {

							saved = insertBulletinCotisation(cotBulletin, conn);
							continue;
						}
						saved = updateBulletinCotisation(cotBulletin, conn);
					}
				}
				if (saved)
				if (bulletin.getListDeletedCotisation().size() > 0) {
					for (BulletinCotisationC cotBulletin : bulletin.getListDeletedCotisation()) {
						saved=delete(cotBulletin.getId(), Tables.getTableName(Tables.TableName.bulletinPaieCotisation), conn);
					}
				}
				if (saved)
				if (bulletin.getListDeduction().size() > 0) {
					for (BulletinDeductionC dedBulletin : bulletin.getListDeduction()) {

						dedBulletin.setIdBulletin(bulletin.getId());
						if (dedBulletin.getId() == 0) {

							saved = insertBulletinDeduction(dedBulletin, conn);
							continue;
						}
						saved = updateBulletinDeduction(dedBulletin, conn);
					}
				}
				if (saved)
				if (bulletin.getListDeletedDeduction().size() > 0)
					for (BulletinDeductionC dedBulletin : bulletin.getListDeletedDeduction()) {
						saved=delete(dedBulletin.getId(), Tables.getTableName(Tables.TableName.bulletinDeduction), conn);
					}
				if (saved)
				if (bulletin.getListHeureSup().size() > 0) {
					for (BulletinHeureSupplementaireC hsBulletin : bulletin.getListHeureSup()) {

						hsBulletin.setIdBulletin(bulletin.getId());
						if (hsBulletin.getId() == 0) {

							saved = insertBulletinHeureSupp(hsBulletin, conn);
							continue;
						}
						saved = updateBulletinHeureSupp(hsBulletin, conn);
					}
				}
				if (saved)
				if (bulletin.getListeIndemnite().size() > 0) {
					for (BulletinIndemniteC indBulletin : bulletin.getListeIndemnite()) {

						indBulletin.setIdBulletin(bulletin.getId());
						if (indBulletin.getId() == 0) {

							saved = insertBulletinIndemnite(indBulletin, conn);
							continue;
						}
						saved = updateBulletinIndemnite(indBulletin, conn);
					}
				}
				if (saved)
				if (bulletin.getListePrime().size() > 0) {
					for (BulletinPrimeC prmBulletin : bulletin.getListePrime()) {

						prmBulletin.setIdBulletin(bulletin.getId());
						if (prmBulletin.getId() == 0) {

							saved = insertBulletinPrimes(prmBulletin, conn);
							continue;
						}
						saved = updateBulletinPrime(prmBulletin, conn);
					}
				}
				if (saved)
				if (bulletin.getListDeletedPrime().size() > 0)
					for (BulletinPrimeC prmBulletin : bulletin.getListDeletedPrime()) {
						saved=delete(prmBulletin.getId(), Tables.getTableName(Tables.TableName.bulletinPaiePrime), conn);
					}
				if (saved)
				if (bulletin.getListCredit().size() > 0) {
					for (BulletinCreditC bcrd : bulletin.getListCredit()) {

						bcrd.setIdBulletin(bulletin.getId());
						if (bcrd.getId() == 0) {

							saved = insertBulletinCredit(bcrd, conn);

						} else {

							saved = updateBulletinCredit(bcrd, conn);
						}
					}
				}
				if (saved)
				if (bulletin.getListAvance().size() > 0) {
					for (BulletinAvanceC bavc : bulletin.getListAvance()) {

						bavc.setIdBulletin(bulletin.getId());
						if (bavc.getId() == 0) {

							saved = insertBulletinAvance(bavc, conn);
							continue;
						}
						saved = updateBulletinAvance(bavc, conn);
					}
				}
				if (saved)
				if (bulletin.getListRemboursement().size() > 0) {

					saved = deleteDetails(bulletin.getId(), "id_bulletin", "tbl_credit_rembourse", conn);
					if (saved)
						for (CreditRembourseC rem : bulletin.getListRemboursement()) {
							rem.setIdBulletin(bulletin.getId());
							saved = insertRemboursement(rem, conn);
						}
				}
				if (saved)
				if(bulletin.getListComission().size()>0) {
					
					// deleteDetails(bulletin.getId(), "id_bulletin", "tbl_bulletin_comission", conn);
					
					for (BulletinComissionC detCom : bulletin.getListComission()) {
						detCom.setIdBulletin(bulletin.getId());
						if(detCom.getId()==0)
							saved=insertBulletinComission(detCom, conn);
						else
							saved=updateBulletinComission(detCom, conn);
					}
				}
				if (saved)
				if(bulletin.getHistory()!=null)
				{
					bulletin.getHistory().setIdLigne(bulletin.getId());
					saved=insertHistorique(bulletin.getHistory(), conn);
				}
			}

			if (saved) {

				conn.commit();
			} else {

				conn.rollback();

			}

		} catch (SQLException e) {

			saved = false;
			e.printStackTrace();
			System.out.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
		return saved;
	}

	private boolean insertBulletinPaie(BulletinPaieC bulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			bulletin.setId(getId(Tables.getTableName(Tables.TableName.bulletinPaie)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ " (id,id_employe,date_paie,mois_paie,comment,total_allocation,"
					+ "total_credit,salaire_base,mode_reglement,total_logement,"
					+ "nbr_heure_normal,nbr_heure_preste,montant_base,id_exercice,"
					+ "base_paiement,nbr_jour_preste,montant_base_hsup,jour_additionnel,"
					+ "montant_jour_add,taux_jour_add,nbr_jour_normal,nbr_jour_ferie,"
					+ "taux_jour_ferie,compte_virement,id_bk_virement) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, bulletin.getId());
			stmt.setInt(2, bulletin.getIdEmploye());
			stmt.setObject(3, bulletin.getDatePaie());
			stmt.setInt(4, bulletin.getMois());
			stmt.setString(5, bulletin.getCommentaire());
			stmt.setDouble(6, bulletin.getTotalAllocation());
			stmt.setDouble(7, bulletin.getTotalCreditRembourse());
			stmt.setDouble(8, bulletin.getSalaireBase());
			stmt.setInt(9, bulletin.getModeReglement());
			stmt.setDouble(10, bulletin.getTotalLogement());
			stmt.setInt(11, bulletin.getNombreHeureNormal());
			stmt.setInt(12, bulletin.getNombreHeurePreste());
			stmt.setDouble(13, bulletin.getTotalmontantBase());
			stmt.setInt(14, bulletin.getIdExercice());
			stmt.setInt(15, bulletin.getBasePaiement());
			stmt.setInt(16, bulletin.getNombreJourPreste());
			stmt.setDouble(17, bulletin.getMontantBaseHSup());
			stmt.setInt(18, bulletin.getNbreJourAdd());
			stmt.setDouble(19, bulletin.getTotalJourAdd());
			stmt.setDouble(20, bulletin.getTauxJourAdd());
			stmt.setInt(21, bulletin.getNbreJourNormal());
			stmt.setInt(22, bulletin.getNbrJourFerie());
			stmt.setDouble(23, bulletin.getTauxJrsFerie());
			stmt.setString(24, bulletin.getCompteVirement());
			if (bulletin.getIdBkVrt() > 0)
				stmt.setInt(25, bulletin.getIdBkVrt());
			else
				stmt.setObject(25, null);

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saved;
	}

	private boolean updateBulletinPaie(BulletinPaieC bulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ " SET date_paie=?,mois_paie=?,comment=?,total_allocation=?,"
					+ "total_credit=?,salaire_base=?,mode_reglement=?,total_logement=?,"
					+ "nbr_heure_normal=?,nbr_heure_preste=?,montant_base=?,id_exercice=?,"
					+ "base_paiement=?,nbr_jour_preste=?,montant_base_hsup=?,jour_additionnel=?,"
					+ "montant_jour_add=?,taux_jour_add=?,nbr_jour_normal=?,nbr_jour_ferie=?,"
					+ "taux_jour_ferie=?,compte_virement=?,id_bk_virement=? WHERE id=?";
			stmt = connection.prepareStatement(sql);
			stmt.setObject(1, bulletin.getDatePaie());
			stmt.setInt(2, bulletin.getMois());
			stmt.setString(3, bulletin.getCommentaire());
			stmt.setDouble(4, bulletin.getTotalAllocation());
			stmt.setDouble(5, bulletin.getTotalCreditRembourse());
			stmt.setDouble(6, bulletin.getSalaireBase());
			stmt.setInt(7, bulletin.getModeReglement());
			stmt.setDouble(8, bulletin.getTotalLogement());
			stmt.setInt(9, bulletin.getNombreHeureNormal());
			stmt.setInt(10, bulletin.getNombreHeurePreste());
			stmt.setDouble(11, bulletin.getTotalmontantBase());
			stmt.setInt(12, bulletin.getIdExercice());
			stmt.setInt(13, bulletin.getBasePaiement());
			stmt.setInt(14, bulletin.getNombreJourPreste());
			stmt.setDouble(15, bulletin.getMontantBaseHSup());
			stmt.setInt(16, bulletin.getNbreJourAdd());
			stmt.setDouble(17, bulletin.getTotalJourAdd());
			stmt.setDouble(18, bulletin.getTauxJourAdd());
			stmt.setInt(19, bulletin.getNbreJourNormal());
			stmt.setInt(20, bulletin.getNbrJourFerie());
			stmt.setDouble(21, bulletin.getTauxJrsFerie());
			stmt.setString(22, bulletin.getCompteVirement());
			if (bulletin.getIdBkVrt() > 0)
				stmt.setInt(23, bulletin.getIdBkVrt());
			else
				stmt.setObject(23, null);
			stmt.setInt(24, bulletin.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saved;
	}

	private BulletinPaieC setBulletinPaie(ResultSet rs) throws SQLException {
		BulletinPaieC bulletin = new BulletinPaieC();
		bulletin.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			bulletin.setIdEmploye(rs.getInt("id_employe"));
		}
		bulletin.setCommentaire(rs.getString("comment"));
		bulletin.setDatePaie(rs.getDate("date_paie"));
		bulletin.setDatePaieString(HelperC.changeDateFormate(bulletin.getDatePaie()));
		bulletin.setModeReglement(rs.getInt("mode_reglement"));
		bulletin.setMois(rs.getInt("mois_paie"));
		bulletin.setTotalAllocation(rs.getDouble("total_allocation"));
		bulletin.setTotalCreditRembourse(rs.getDouble("total_credit"));
		bulletin.setTotalLogement(rs.getDouble("total_logement"));
		bulletin.setSalaireBase(rs.getDouble("salaire_base"));
		bulletin.setTotalmontantBase(rs.getDouble("montant_base"));
		bulletin.setNombreHeureNormal(rs.getInt("nbr_heure_normal"));
		bulletin.setNombreHeurePreste(rs.getInt("nbr_heure_preste"));
		bulletin.setIdExercice(rs.getInt("id_exercice"));
		bulletin.setNombreJourPreste(rs.getInt("nbr_jour_preste"));
		bulletin.setBasePaiement(rs.getInt("base_paiement"));
		bulletin.setMontantHeure(rs.getDouble("montant_base_hsup"));
		bulletin.setNbreJourAdd(rs.getInt("jour_additionnel"));
		bulletin.setTotalJourAdd(rs.getDouble("montant_jour_add"));
		bulletin.setTauxJourAdd(rs.getDouble("taux_jour_add"));
		bulletin.setNbreJourNormal(rs.getInt("nbr_jour_normal"));
		bulletin.setNbrJourFerie(rs.getInt("nbr_jour_ferie"));
		bulletin.setTauxJrsFerie(rs.getDouble("taux_jour_ferie"));
		bulletin.setCompteVirement(rs.getString("compte_virement"));

		if (rs.getObject("id_bk_virement") != null)
			bulletin.setIdBkVrt(rs.getInt("id_bk_virement"));

		return bulletin;
	}

	public BulletinPaieC getBulletinPaie(int idEmploye, Date datePaie, int idExercice) {
		BulletinPaieC bulletin = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinPaie)
				+ " WHERE id_employe=? AND date_paie=? AND id_exercice=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idEmploye);
			pstmt.setObject(2, HelperC.convertDate(datePaie, false));
			pstmt.setInt(3, idExercice);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bulletin = setBulletinPaie(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, rs);
		}
		if (bulletin != null) {

			bulletin.setListAllocation(getBulletinDetailAllocation(bulletin.getId()));
			bulletin.setListeCotisation(getBulletinDetailCotisation(bulletin.getId()));
			bulletin.setListDeduction(getBulletinDetailDeduction(bulletin.getId()));
			bulletin.setListHeureSup(getBulletinDetailHeureSup(bulletin.getId()));
			bulletin.setListeIndemnite(getBulletinDetailIndemnite(bulletin.getId()));
			bulletin.setListePrime(getBulletinDetailPrime(bulletin.getId()));
			bulletin.setListCredit(getBulletinDetailCredit(bulletin.getId()));
			bulletin.setListAvance(getBulletinDetailAvance(bulletin.getId()));
			bulletin.setListRemboursement(getBulletinDetailRemboursement(bulletin.getId()));
			bulletin.setListComission(getListBulletinComission(bulletin.getId()));
		}
		return bulletin;
	}
	public BulletinPaieC getPaieMois(int idEmploye, int mois, int idExercice) {
		BulletinPaieC bulletin = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinPaie)
				+ " WHERE id_employe=? AND mois_paie=? AND id_exercice=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idEmploye);
			pstmt.setInt(2, mois);
			pstmt.setInt(3, idExercice);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bulletin = setBulletinPaie(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, rs);
		}
		if (bulletin != null) {

			bulletin.setListAllocation(getBulletinDetailAllocation(bulletin.getId()));
			bulletin.setListeCotisation(getBulletinDetailCotisation(bulletin.getId()));
			bulletin.setListDeduction(getBulletinDetailDeduction(bulletin.getId()));
			bulletin.setListHeureSup(getBulletinDetailHeureSup(bulletin.getId()));
			bulletin.setListeIndemnite(getBulletinDetailIndemnite(bulletin.getId()));
			bulletin.setListePrime(getBulletinDetailPrime(bulletin.getId()));
			bulletin.setListCredit(getBulletinDetailCredit(bulletin.getId()));
			bulletin.setListAvance(getBulletinDetailAvance(bulletin.getId()));
			bulletin.setListRemboursement(getBulletinDetailRemboursement(bulletin.getId()));
			bulletin.setListComission(getListBulletinComission(bulletin.getId()));
		}
		return bulletin;
	}
	public List<BulletinPaieC> getListBulletinPaie(int idEmploye, int mois, int idExercice) {
		List<BulletinPaieC> listBulletin = new ArrayList<BulletinPaieC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinPaie) + " WHERE id_exercice="
				+ idExercice;
		if (idEmploye > 0) {
			sql = String.valueOf(sql) + " AND id_employe=" + idEmploye;
		}
		if (mois > 0) {
			sql = String.valueOf(sql) + " AND mois_paie=" + mois;
		}

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				listBulletin.add(setBulletinPaie(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, rs);
		}
		if (listBulletin.size() > 0) {

			for (BulletinPaieC bulletin : listBulletin) {

				bulletin.setListAvance(getBulletinDetailAvance(bulletin.getId()));
				bulletin.setListAllocation(getBulletinDetailAllocation(bulletin.getId()));
				bulletin.setListeCotisation(getBulletinDetailCotisation(bulletin.getId()));
				bulletin.setListDeduction(getBulletinDetailDeduction(bulletin.getId()));
				bulletin.setListHeureSup(getBulletinDetailHeureSup(bulletin.getId()));
				bulletin.setListeIndemnite(getBulletinDetailIndemnite(bulletin.getId()));
				bulletin.setListePrime(getBulletinDetailPrime(bulletin.getId()));
				bulletin.setListCredit(getBulletinDetailCredit(bulletin.getId()));
				bulletin.setListRemboursement(getBulletinDetailRemboursement(bulletin.getId()));
				bulletin.setListComission(getListBulletinComission(bulletin.getId()));
			}
		}

		return listBulletin;
	}

	public List<BulletinPaieC> getListBulletinPaie(int idEmploye, Date dateDeb, Date dateFin, int idExercice) {
		List<BulletinPaieC> listBulletin = new ArrayList<BulletinPaieC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinPaie) + " WHERE id_exercice= "
				+ idExercice;
		if (idEmploye > 0) {
			sql = String.valueOf(sql) + " AND id_employe=" + idEmploye;
		}
		if (dateDeb != null) {
			sql = String.valueOf(sql) + " AND date_paie>='" + HelperC.convertDate(dateDeb, false) + "'";
		}
		if (dateFin != null) {
			sql = String.valueOf(sql) + " AND date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listBulletin.add(setBulletinPaie(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, rs);
		}
		if (listBulletin.size() > 0)
			for (BulletinPaieC bulletin : listBulletin) {

				bulletin.setListAvance(getBulletinDetailAvance(bulletin.getId()));
				bulletin.setListAllocation(getBulletinDetailAllocation(bulletin.getId()));
				bulletin.setListeCotisation(getBulletinDetailCotisation(bulletin.getId()));
				bulletin.setListDeduction(getBulletinDetailDeduction(bulletin.getId()));
				bulletin.setListHeureSup(getBulletinDetailHeureSup(bulletin.getId()));
				bulletin.setListeIndemnite(getBulletinDetailIndemnite(bulletin.getId()));
				bulletin.setListePrime(getBulletinDetailPrime(bulletin.getId()));
				bulletin.setListCredit(getBulletinDetailCredit(bulletin.getId()));
				bulletin.setListRemboursement(getBulletinDetailRemboursement(bulletin.getId()));
				bulletin.setListComission(getListBulletinComission(bulletin.getId()));
			}
		return listBulletin;
	}

	public List<String> getListDatePaie(int idExercice, int order) {
		List<String> listDate = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT(date_paie) AS dte FROM " + Tables.getTableName(Tables.TableName.bulletinPaie)
				+ " WHERE id_exercice= " + idExercice;

		switch (order) {

		case 0:
			sql = String.valueOf(sql) + " ORDER BY date_paie ASC";
			break;
		case 1:
			sql = String.valueOf(sql) + " ORDER BY date_paie DESC";
			break;
		}

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listDate.add(HelperC.changeDateFormate(rs.getDate("dte")));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, rs);
		}

		return listDate;
	}

	public double getTotalNet(int idBulletin, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(salaire_base) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND id_bulletin=" + idBulletin;
		}

		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND date_paie>='" + HelperC.convertDate(dateDebut, false) + "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	public double getTotalLogement(int idBulletin, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(total_logement) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND id_bulletin=" + idBulletin;
		}

		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND date_paie>='" + HelperC.convertDate(dateDebut, false) + "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	private boolean insertBulletinAllocation(BulletinAllocationC alcBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinAllocation)
				+ " (id,id_bulletin,id_person_charge,montant,nombre) VALUES (?,?,?,?,?)";
		alcBulletin.setId(getId(Tables.getTableName(Tables.TableName.bulletinAllocation)));

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, alcBulletin.getId());
			pstmt.setInt(2, alcBulletin.getIdBulletin());
			pstmt.setInt(3, alcBulletin.getIdPerson());
			pstmt.setDouble(4, alcBulletin.getMontant());
			pstmt.setInt(5, alcBulletin.getNombre());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinAllocation(BulletinAllocationC alcBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinAllocation)
				+ " SET id_person_charge=?,montant=?,nombre=? WHERE id=?";

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, alcBulletin.getIdPerson());
			pstmt.setDouble(2, alcBulletin.getMontant());
			pstmt.setInt(3, alcBulletin.getNombre());
			pstmt.setInt(4, alcBulletin.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private BulletinAllocationC setBulletinAllocation(ResultSet rs) throws SQLException {
		BulletinAllocationC alcBulletin = new BulletinAllocationC();
		alcBulletin.setId(rs.getInt("id"));
		if (rs.getObject("id_person_charge") != null) {
			alcBulletin.setIdPerson(rs.getInt("id_person_charge"));
		}
		alcBulletin.setMontant(rs.getDouble("montant"));
		if (alcBulletin.getIdPerson() > 0) {
			alcBulletin.setPersonneCharge(getPersonneById(alcBulletin.getIdPerson()));
		}
		alcBulletin.setNombre(rs.getInt("nombre"));
		
		return alcBulletin;
	}

	private List<BulletinAllocationC> getBulletinDetailAllocation(int idBulletin) {
		List<BulletinAllocationC> listAllocation = new ArrayList<BulletinAllocationC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinAllocation)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listAllocation.add(setBulletinAllocation(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listAllocation;
	}

	public List<BulletinAllocationC> getListeAllocation(int mois, int idExercice) {
		List<BulletinAllocationC> listAllocation = new ArrayList<BulletinAllocationC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinAllocation) + ".id, "
				+ Tables.getTableName(Tables.TableName.bulletinAllocation) + ".id_person_charge," + "SUM("
				+ Tables.getTableName(Tables.TableName.bulletinAllocation) + ".montant) AS montant,"
				+ Tables.getTableName(Tables.TableName.personneCharge) + ".statut_personne " + "FROM "
				+ Tables.getTableName(Tables.TableName.bulletinAllocation) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.personneCharge) + " ON "
				+ Tables.getTableName(Tables.TableName.personneCharge) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinAllocation) + ".id_person_charge " + "LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinAllocation) + ".id_bulletin WHERE "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id_exercice=" + idExercice + " AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".mois_paie=" + mois;
		sqlRequest = String.valueOf(sqlRequest) + " GROUP BY id_person_charge ";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listAllocation.add(setBulletinAllocation(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listAllocation;
	}

	public double getTotalAllocationFm(int idBulletin, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(A.montant) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinAllocation) + " AS A  LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " AS B  ON A.id_bulletin=B.id WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_bulletin=" + idBulletin;
		}

		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie>='" + HelperC.convertDate(dateDebut, false)
					+ "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	private boolean insertBulletinCotisation(BulletinCotisationC cotBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinPaieCotisation)
				+ " (id,id_bulletin,id_cotisation,montant_salarial,montant_base,montant_patronal,taux_salarial,"
				+ "taux_patronal,type_cotisation) VALUES (?,?,?,?,?,?,?,?,?)";

		cotBulletin.setId(getId(Tables.getTableName(Tables.TableName.bulletinPaieCotisation)));

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, cotBulletin.getId());
			pstmt.setInt(2, cotBulletin.getIdBulletin());
			pstmt.setInt(3, cotBulletin.getIdCotisation());
			pstmt.setDouble(4, cotBulletin.getMontantCotisation());
			pstmt.setDouble(5, cotBulletin.getMontantBase());
			pstmt.setDouble(6, cotBulletin.getMontantPatronal());
			pstmt.setDouble(7, cotBulletin.getTauxSalarial());
			pstmt.setDouble(8, cotBulletin.getTauxPatronal());
			pstmt.setInt(9, cotBulletin.getTypeCotisation());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinCotisation(BulletinCotisationC cotBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinPaieCotisation)
				+ " SET id_cotisation=?,montant_salarial=?,montant_base=?,montant_patronal=?,"
				+ "taux_salarial=?,taux_patronal=?,type_cotisation=? WHERE id=?";

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, cotBulletin.getIdCotisation());
			pstmt.setDouble(2, cotBulletin.getMontantCotisation());
			pstmt.setDouble(3, cotBulletin.getMontantBase());
			pstmt.setDouble(4, cotBulletin.getMontantPatronal());
			pstmt.setDouble(5, cotBulletin.getTauxSalarial());
			pstmt.setDouble(6, cotBulletin.getTauxPatronal());
			pstmt.setInt(7, cotBulletin.getTypeCotisation());
			pstmt.setInt(8, cotBulletin.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private BulletinCotisationC setBulletinCotisation(ResultSet rs) throws SQLException {
		BulletinCotisationC cotBulletin = new BulletinCotisationC();
		cotBulletin.setId(rs.getInt("id"));
		if (rs.getObject("id_cotisation") != null) {
			cotBulletin.setIdCotisation(rs.getInt("id_cotisation"));
		}
		cotBulletin.setMontantBase(rs.getDouble("montant_base"));
		cotBulletin.setMontantPatronal(rs.getDouble("montant_patronal"));
		cotBulletin.setMontantCotisation(rs.getDouble("montant_salarial"));
		cotBulletin.setTauxPatronal(rs.getDouble("taux_patronal"));
		cotBulletin.setTauxSalarial(rs.getDouble("taux_salarial"));
		cotBulletin.setTypeCotisation(rs.getInt("type_cotisation"));

		if (cotBulletin.getIdCotisation() > 0) {

			cotBulletin.setCotisation(FichierBaseDAO.getInstance().getCotisation(cotBulletin.getIdCotisation()));
			cotBulletin.setCodeCotisation(cotBulletin.getCotisation().getCode());
		}
		return cotBulletin;
	}

	private List<BulletinCotisationC> getBulletinDetailCotisation(int idBulletin) {
		List<BulletinCotisationC> listCotisation = new ArrayList<BulletinCotisationC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinPaieCotisation)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listCotisation.add(setBulletinCotisation(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listCotisation;
	}

	public List<BulletinCotisationC> getListCotisationIRE(int idcotisation, Date dateDeb, Date dateFin, int typeCot,
			double taux) {
		List<BulletinCotisationC> listCotisation = new ArrayList<BulletinCotisationC>();
		Statement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".* FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".id_bulletin WHERE 1=1 AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".type_cotisation=" + typeCot + " AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".taux_salarial=" + taux;

		if (dateDeb != null)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".date_paie>='" + HelperC.convertDate(dateDeb, false) + "'";
		if (dateFin != null)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		if (idcotisation > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND "
					+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".id_cotisation=" + idcotisation;
		}

		try {
			pstmt = con.createStatement();

			rs = pstmt.executeQuery(sqlRequest);
			while (rs.next()) {
				listCotisation.add(setBulletinCotisation(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listCotisation;
	}

	public List<BulletinCotisationC> getListBulletinDetailCotisation(int idEmploye, int idcotisation, Date dateDeb,
			Date dateFin) {
		List<BulletinCotisationC> listCotisation = new ArrayList<BulletinCotisationC>();
		Statement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".* FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".id_bulletin " + "WHERE 1=1 ";

		if (dateDeb != null)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".date_paie>='" + HelperC.convertDate(dateDeb, false) + "'";
		if (dateFin != null)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		if (idcotisation > 0)
			sqlRequest = String.valueOf(sqlRequest) + " AND "
					+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".id_cotisation=" + idcotisation;
		if (idEmploye > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".id_employe=" + idEmploye;
		}

		try {
			pstmt = con.createStatement();

			rs = pstmt.executeQuery(sqlRequest);
			while (rs.next()) {
				listCotisation.add(setBulletinCotisation(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listCotisation;
	}

	public List<BulletinCotisationC> getListCotisation(int mois, int idExercice) {
		List<BulletinCotisationC> listCotisation = new ArrayList<BulletinCotisationC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".*,SUM("
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".montant_salarial)  AS sum_salarial "
				+ "FROM " + Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + ".id_bulletin WHERE "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id_exercice=" + idExercice + " AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".mois_paie=" + mois;
		sqlRequest = String.valueOf(sqlRequest) + " GROUP BY id_cotisation";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BulletinCotisationC cotBulletin = new BulletinCotisationC();
				cotBulletin.setId(rs.getInt("id"));
				if (rs.getObject("id_cotisation") != null) {
					cotBulletin.setIdCotisation(rs.getInt("id_cotisation"));
				}
				cotBulletin.setMontantBase(rs.getDouble("montant_base"));
				cotBulletin.setMontantPatronal(rs.getDouble("montant_patronal"));
				cotBulletin.setMontantCotisation(rs.getDouble("sum_salarial"));
				cotBulletin.setTauxPatronal(rs.getDouble("taux_patronal"));
				cotBulletin.setTauxSalarial(rs.getDouble("taux_salarial"));
				cotBulletin.getIdCotisation();

				listCotisation.add(cotBulletin);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listCotisation;
	}

	public double getTotalCotisationSalarial(int idBulletin, int idCotisation, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(A.montant_salarial) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + " AS A  LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " AS B  ON A.id_bulletin=B.id WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_bulletin=" + idBulletin;
		}
		if (idCotisation > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_cotisation=" + idCotisation;
		}
		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie>='" + HelperC.convertDate(dateDebut, false)
					+ "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	public double getTotalCotisationPatronal(int idBulletin, int idCotisation, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(A.montant_patronal) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaieCotisation) + " AS A LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " AS B ON A.id_bulletin=B.id WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_bulletin=" + idBulletin;
		}
		if (idCotisation > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_cotisation=" + idCotisation;
		}
		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie>='" + HelperC.convertDate(dateDebut, false)
					+ "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	private boolean insertBulletinDeduction(BulletinDeductionC dedBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinDeduction)
				+ " (id,id_bulletin,id_deduction,montant) VALUES (?,?,?,?)";
		dedBulletin.setId(getId(Tables.getTableName(Tables.TableName.bulletinDeduction)));

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, dedBulletin.getId());
			pstmt.setInt(2, dedBulletin.getIdBulletin());
			pstmt.setInt(3, dedBulletin.getIdRetenu());
			pstmt.setDouble(4, dedBulletin.getMontantRetenu());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinDeduction(BulletinDeductionC dedBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinDeduction)
				+ " SET id_deduction=?,montant=? WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, dedBulletin.getIdRetenu());
			pstmt.setDouble(2, dedBulletin.getMontantRetenu());
			pstmt.setInt(3, dedBulletin.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private BulletinDeductionC setBulletinDeduction(ResultSet rs) throws SQLException {
		BulletinDeductionC dedBulletin = new BulletinDeductionC();
		dedBulletin.setId(rs.getInt("id"));
		if (rs.getObject("id_deduction") != null) {
			dedBulletin.setIdRetenu(rs.getInt("id_deduction"));
		}
		dedBulletin.setMontantRetenu(rs.getDouble("montant"));
		if (dedBulletin.getIdRetenu() > 0) {
			dedBulletin.setDeduction(FichierBaseDAO.getInstance().getDeduction(dedBulletin.getIdRetenu()));
		}
		return dedBulletin;
	}

	private List<BulletinDeductionC> getBulletinDetailDeduction(int idBulletin) {
		List<BulletinDeductionC> listDeduction = new ArrayList<BulletinDeductionC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinDeduction)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listDeduction.add(setBulletinDeduction(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		
		return listDeduction;
	}

	public List<BulletinDeductionC> getListDeduction(int mois, int idExercice) {
		List<BulletinDeductionC> listDeduction = new ArrayList<BulletinDeductionC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinDeduction) + ".id, "
				+ Tables.getTableName(Tables.TableName.bulletinDeduction) + ".id_deduction, SUM("
				+ Tables.getTableName(Tables.TableName.bulletinDeduction) + ".montant) AS montant " + "FROM "
				+ Tables.getTableName(Tables.TableName.bulletinDeduction) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinDeduction) + ".id_bulletin WHERE "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id_exercice=" + idExercice + " AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".mois_paie=" + mois;

		sqlRequest = String.valueOf(sqlRequest) + " GROUP BY id_deduction";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listDeduction.add(setBulletinDeduction(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return listDeduction;
	}

	public double getTotalDeduction(int idBulletin, int idDeduction) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(montant) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinDeduction) + " WHERE id_bulletin=" + idBulletin;
		if (idDeduction > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND id_deduction=" + idDeduction;
		}

		try {
		

			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	public List<BulletinDeductionC> getListBulletinDetailDeduction(int idEmploye, int idDeduction, Date dateDeb,
			Date dateFin) {
		List<BulletinDeductionC> listDeduction = new ArrayList<BulletinDeductionC>();
		Statement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinDeduction) + ".* FROM "
				+ Tables.getTableName(Tables.TableName.bulletinDeduction) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinDeduction) + ".id_bulletin " + "WHERE 1=1 ";

		if (dateDeb != null)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".date_paie>='" + HelperC.convertDate(dateDeb, false) + "'";
		if (dateFin != null)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		if (idDeduction > 0)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinDeduction)
					+ ".id_deduction=" + idDeduction;
		if (idEmploye > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".id_employe=" + idEmploye;
		}

		try {
			pstmt = con.createStatement();

			rs = pstmt.executeQuery(sqlRequest);
			while (rs.next()) {
				listDeduction.add(setBulletinDeduction(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listDeduction;
	}

	public double getTotalDeduction(int idBulletin, int idDed, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(A.montant) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinDeduction) + " AS A  LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " AS B  ON A.id_bulletin=B.id WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_bulletin=" + idBulletin;
		}
		if (idDed > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_deduction=" + idDed;
		}
		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie>='" + HelperC.convertDate(dateDebut, false)
					+ "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	private boolean insertBulletinHeureSupp(BulletinHeureSupplementaireC heureSupBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinHeureSup)
				+ " (id,id_bulletin,pourcentage,montant,heures,minutes,secondes) VALUES (?,?,?,?,?,?,?)";
		heureSupBulletin.setId(getId(Tables.getTableName(Tables.TableName.bulletinHeureSup)));

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, heureSupBulletin.getId());
			pstmt.setInt(2, heureSupBulletin.getIdBulletin());
			pstmt.setDouble(3, heureSupBulletin.getPourcentage());
			pstmt.setDouble(4, heureSupBulletin.getMontant());
			pstmt.setInt(5, heureSupBulletin.getHeures());
			pstmt.setInt(6, heureSupBulletin.getMinutes());
			pstmt.setInt(7, heureSupBulletin.getSecondes());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinHeureSupp(BulletinHeureSupplementaireC heureSupBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinHeureSup)
				+ " SET pourcentage=?,montant=?,heures=?,minutes=?,secondes=? WHERE id=?";

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setDouble(1, heureSupBulletin.getPourcentage());
			pstmt.setDouble(2, heureSupBulletin.getMontant());
			pstmt.setInt(3, heureSupBulletin.getHeures());
			pstmt.setInt(4, heureSupBulletin.getMinutes());
			pstmt.setInt(5, heureSupBulletin.getSecondes());
			pstmt.setInt(6, heureSupBulletin.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private BulletinHeureSupplementaireC setBulletinHeureSup(ResultSet rs) throws SQLException {
		BulletinHeureSupplementaireC hSupBulletin = new BulletinHeureSupplementaireC();
		hSupBulletin.setId(rs.getInt("id"));
		hSupBulletin.setMontant(rs.getDouble("montant"));
		hSupBulletin.setPourcentage(rs.getDouble("pourcentage"));
		hSupBulletin.setHeures(rs.getInt("heures"));
		hSupBulletin.setMinutes(rs.getInt("minutes"));
		hSupBulletin.setSecondes(rs.getInt("secondes"));
		return hSupBulletin;
	}

	private List<BulletinHeureSupplementaireC> getBulletinDetailHeureSup(int idBulletin) {
		List<BulletinHeureSupplementaireC> listHeureSup = new ArrayList<BulletinHeureSupplementaireC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinHeureSup)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listHeureSup.add(setBulletinHeureSup(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listHeureSup;
	}

	public double getTotalHS(int idBulletin, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(A.montant) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinHeureSup) + " AS A  LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " AS B  ON A.id_bulletin=B.id WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_bulletin=" + idBulletin;
		}

		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie>='" + HelperC.convertDate(dateDebut, false)
					+ "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	private boolean insertBulletinIndemnite(BulletinIndemniteC indBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinPaieIndemnite)
				+ " (id,id_bulletin,id_indemnite,montant) VALUES (?,?,?,?)";

		indBulletin.setId(getId(Tables.getTableName(Tables.TableName.bulletinPaieIndemnite)));

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, indBulletin.getId());
			pstmt.setInt(2, indBulletin.getIdBulletin());
			pstmt.setInt(3, indBulletin.getIdIndemnite());
			pstmt.setDouble(4, indBulletin.getMontantIndemnite());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinIndemnite(BulletinIndemniteC indBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinPaieIndemnite)
				+ " SET id_indemnite=?,montant=? WHERE id=?";

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, indBulletin.getIdIndemnite());
			pstmt.setDouble(2, indBulletin.getMontantIndemnite());
			pstmt.setInt(3, indBulletin.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private BulletinIndemniteC setBulletinIndemnite(ResultSet rs) throws SQLException {
		BulletinIndemniteC indBulletin = new BulletinIndemniteC();
		indBulletin.setId(rs.getInt("id"));
		if (rs.getObject("id_indemnite") != null) {
			indBulletin.setIdIndemnite(rs.getInt("id_indemnite"));
		}
		indBulletin.setMontantIndemnite(rs.getDouble("montant"));
		if (indBulletin.getIdIndemnite() > 0) {
			indBulletin.setIndemnite(FichierBaseDAO.getInstance().getPrimeIndemnite(indBulletin.getIdIndemnite()));
		}
		return indBulletin;
	}

	private List<BulletinIndemniteC> getBulletinDetailIndemnite(int idBulletin) {
		List<BulletinIndemniteC> listIndemnite = new ArrayList<BulletinIndemniteC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinPaieIndemnite)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listIndemnite.add(setBulletinIndemnite(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listIndemnite;
	}

	public List<BulletinIndemniteC> getListIndemnite(int mois, int idExercice) {
		List<BulletinIndemniteC> listIndemnite = new ArrayList<BulletinIndemniteC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinPaieIndemnite) + ".id, "
				+ Tables.getTableName(Tables.TableName.bulletinPaieIndemnite) + ".id_indemnite, " + "SUM("
				+ Tables.getTableName(Tables.TableName.bulletinPaieIndemnite) + ".montant) AS montant " + "FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaieIndemnite) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinPaieIndemnite) + ".id_bulletin WHERE "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id_exercice=" + idExercice + " AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".mois_paie=" + mois;

		sqlRequest = String.valueOf(sqlRequest) + " GROUP BY id_indemnite";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				listIndemnite.add(setBulletinIndemnite(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listIndemnite;
	}

	private boolean insertBulletinPrimes(BulletinPrimeC prmBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinPaiePrime)
				+ " (id,id_bulletin,id_prime,montant,id_parametre) VALUES (?,?,?,?,?)";

		prmBulletin.setId(getId(Tables.getTableName(Tables.TableName.bulletinPaiePrime)));

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, prmBulletin.getId());
			pstmt.setInt(2, prmBulletin.getIdBulletin());
			pstmt.setInt(3, prmBulletin.getIdPrime());
			pstmt.setDouble(4, prmBulletin.getMontantPrime());
			if (prmBulletin.getIdParametre() > 0) {
				pstmt.setInt(5, prmBulletin.getIdParametre());
			} else {
				pstmt.setObject(5, (Object) null);
			}
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinPrime(BulletinPrimeC prmBulletin, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinPaiePrime)
				+ " SET id_prime=?,montant=?,id_parametre=? WHERE id=?";

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, prmBulletin.getIdPrime());
			pstmt.setDouble(2, prmBulletin.getMontantPrime());
			if (prmBulletin.getIdParametre() > 0) {
				pstmt.setInt(3, prmBulletin.getIdParametre());
			} else {
				pstmt.setObject(3, (Object) null);
			}
			pstmt.setInt(4, prmBulletin.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private BulletinPrimeC setBulletinPrime(ResultSet rs) throws SQLException {
		BulletinPrimeC prmBulletin = new BulletinPrimeC();
		prmBulletin.setId(rs.getInt("id"));
		if (rs.getObject("id_prime") != null) {
			prmBulletin.setIdPrime(rs.getInt("id_prime"));
		}
		prmBulletin.setMontantPrime(rs.getDouble("montant"));
		if (prmBulletin.getIdPrime() > 0) {

			prmBulletin.setPrimeBulletin(FichierBaseDAO.getInstance().getPrimeIndemnite(prmBulletin.getIdPrime()));
			prmBulletin.setCodePrime(prmBulletin.getPrimeBulletin().getCode());
		}

		if (rs.getObject("id_parametre") != null) {
			prmBulletin.setIdParametre(rs.getInt("id_parametre"));
		}
		return prmBulletin;
	}

	private List<BulletinPrimeC> getBulletinDetailPrime(int idBulletin) {
		List<BulletinPrimeC> listPrime = new ArrayList<BulletinPrimeC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinPaiePrime)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listPrime.add(setBulletinPrime(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listPrime;
	}

	public List<BulletinPrimeC> getListPrime(int mois, int idExercice) {
		List<BulletinPrimeC> listPrime = new ArrayList<BulletinPrimeC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinPaiePrime) + ".id, "
				+ Tables.getTableName(Tables.TableName.bulletinPaiePrime) + ".id_prime, " + "SUM("
				+ Tables.getTableName(Tables.TableName.bulletinPaiePrime) + ".montant) AS montant FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaiePrime) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinPaiePrime) + ".id_bulletin WHERE "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id_exercice=" + idExercice + " AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".mois_paie=" + mois;

		sqlRequest = String.valueOf(sqlRequest) + " GROUP BY id_prime";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listPrime.add(setBulletinPrime(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return listPrime;
	}

	public double getTotalPrime(int idBulletin, int idPrime) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(montant) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaiePrime) + " WHERE id_bulletin=" + idBulletin;
		if (idPrime > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND id_prime=" + idPrime;
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return montant;
	}

	public double getTotalPrime(int idBulletin, int idPrim, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(A.montant) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaiePrime) + " AS A  LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " AS B  ON A.id_bulletin=B.id WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_bulletin=" + idBulletin;
		}
		if (idPrim > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_prime=" + idPrim;
		}
		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie>='" + HelperC.convertDate(dateDebut, false)
					+ "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	public List<BulletinPrimeC> getListBulletinDetailPrimeIndmnite(int idEmploye, int idPrm, Date dateDeb,
			Date dateFin) {
		List<BulletinPrimeC> listPrime = new ArrayList<BulletinPrimeC>();
		BulletinPrimeC prm = null;
		Statement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinPaiePrime) + ".*,"
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".date_paie FROM "
				+ Tables.getTableName(Tables.TableName.bulletinPaiePrime) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinPaiePrime) + ".id_bulletin " + "WHERE 1=1 ";

		if (dateDeb != null)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".date_paie>='" + HelperC.convertDate(dateDeb, false) + "'";
		if (dateFin != null)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		if (idPrm > 0)
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaiePrime)
					+ ".id_prime=" + idPrm;
		if (idEmploye > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND " + Tables.getTableName(Tables.TableName.bulletinPaie)
					+ ".id_employe=" + idEmploye;
		}

		try {
			pstmt = con.createStatement();

			rs = pstmt.executeQuery(sqlRequest);
			while (rs.next()) {
				prm = setBulletinPrime(rs);
				prm.setDatePaie(rs.getDate("date_paie"));
				listPrime.add(prm);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listPrime;
	}

	private boolean insertBulletinCredit(BulletinCreditC bcrd, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinCredit)
				+ " (id,id_bulletin,numero_dossier,montant,tranche_additionnel,libelle)" + " VALUES (?,?,?,?,?,?)";
		bcrd.setId(getId(Tables.getTableName(Tables.TableName.bulletinCredit)));

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, bcrd.getId());
			pstmt.setInt(2, bcrd.getIdBulletin());
			pstmt.setString(3, bcrd.getNoDossier());
			pstmt.setDouble(4, bcrd.getMontantTranche());
			pstmt.setDouble(5, bcrd.getTranchAdded());
			pstmt.setString(6, bcrd.getLibelle());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinCredit(BulletinCreditC bcrd, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinCredit)
				+ " SET numero_dossier=?,montant=?,tranche_additionnel=?,libelle=? WHERE id=?";

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setString(1, bcrd.getNoDossier());
			pstmt.setDouble(2, bcrd.getMontantTranche());
			pstmt.setDouble(3, bcrd.getTranchAdded());
			pstmt.setString(4, bcrd.getLibelle());
			pstmt.setInt(5, bcrd.getId());

			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private BulletinCreditC setBulletinCredit(ResultSet rs) throws SQLException {
		BulletinCreditC bcrd = new BulletinCreditC();
		bcrd.setId(rs.getInt("id"));
		bcrd.setNoDossier(rs.getString("numero_dossier"));
		bcrd.setMontantTranche(rs.getDouble("montant"));
		bcrd.setTranchAdded(rs.getDouble("tranche_additionnel"));
		bcrd.setLibelle(rs.getString("libelle"));
		return bcrd;
	}

	private List<BulletinCreditC> getBulletinDetailCredit(int idBulletin) {
		List<BulletinCreditC> listCrd = new ArrayList<BulletinCreditC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinCredit)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listCrd.add(setBulletinCredit(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return listCrd;
	}

	private List<CreditRembourseC> getBulletinDetailRemboursement(int idBulletin) {
		List<CreditRembourseC> listCrd = new ArrayList<CreditRembourseC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.remboursementCredit)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listCrd.add(setRemboursement(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return listCrd;
	}

	public BulletinCreditC getToutCredit(int mois, int idExercice) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BulletinCreditC cred = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinCredit) + ".*, " + "SUM("
				+ Tables.getTableName(Tables.TableName.bulletinCredit) + ".montant) AS montant FROM "
				+ Tables.getTableName(Tables.TableName.bulletinCredit) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinCredit) + ".id_bulletin WHERE "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id_exercice=" + idExercice + " AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".mois_paie=" + mois;

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cred = setBulletinCredit(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return cred;
	}

	public double getTotalCredit(int idBulletin, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(A.montant+A.tranche_additionnel) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinCredit) + " AS A  LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " AS B  ON A.id_bulletin=B.id WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_bulletin=" + idBulletin;
		}

		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie>='" + HelperC.convertDate(dateDebut, false)
					+ "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	private boolean insertBulletinAvance(BulletinAvanceC bulltAv, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.bulletinAvance)
				+ " (id,id_bulletin,date_avance,montant,id_avance) VALUES (?,?,?,?,?)";

		bulltAv.setId(getId(Tables.getTableName(Tables.TableName.bulletinAvance)));

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setInt(1, bulltAv.getId());
			pstmt.setInt(2, bulltAv.getIdBulletin());
			pstmt.setString(3, HelperC.convertDate(bulltAv.getDateAvance(), false));
			pstmt.setDouble(4, bulltAv.getMontantAvance());
			pstmt.setInt(5, bulltAv.getIdAvance());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateBulletinAvance(BulletinAvanceC bulltAv, Connection connection) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.bulletinAvance)
				+ " SET date_avance=?,montant=?,id_avance=? WHERE id=?";

		try {
			pstmt = connection.prepareStatement(sqlRequest);
			pstmt.setString(1, HelperC.convertDate(bulltAv.getDateAvance(), false));
			pstmt.setDouble(2, bulltAv.getMontantAvance());
			pstmt.setInt(3, bulltAv.getIdAvance());
			pstmt.setInt(4, bulltAv.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private BulletinAvanceC setBulletinAvance(ResultSet rs) throws SQLException {
		BulletinAvanceC bavc = new BulletinAvanceC();
		bavc.setId(rs.getInt("id"));
		bavc.setDateAvance(rs.getDate("date_avance"));
		bavc.setMontantAvance(rs.getDouble("montant"));
		bavc.setId(rs.getInt("id_avance"));
		return bavc;
	}

	private List<BulletinAvanceC> getBulletinDetailAvance(int idBulletin) {
		List<BulletinAvanceC> listAvance = new ArrayList<BulletinAvanceC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bulletinAvance)
				+ " WHERE id_bulletin=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idBulletin);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				listAvance.add(setBulletinAvance(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return listAvance;
	}

	public BulletinAvanceC getToutAvance(int mois, int idExercice) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BulletinAvanceC avc = null;
		String sqlRequest = "SELECT " + Tables.getTableName(Tables.TableName.bulletinAvance) + ".*,SUM("
				+ Tables.getTableName(Tables.TableName.bulletinAvance) + ".montant) AS montant FROM "
				+ Tables.getTableName(Tables.TableName.bulletinAvance) + " LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " ON "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id="
				+ Tables.getTableName(Tables.TableName.bulletinAvance) + ".id_bulletin WHERE "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".id_exercice=" + idExercice + " AND "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + ".mois_paie=" + mois;

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				avc = setBulletinAvance(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return avc;
	}

	public double getAvanceRembourse(int idAvance) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double montantAvance = 0.0D;
		String sqlRequest = "SELECT SUM(montant) AS somme FROM " + Tables.getTableName(Tables.TableName.bulletinAvance)
				+ " WHERE id_avance=" + idAvance;

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montantAvance = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return montantAvance;
	}

	public double getTotalAvance(int idBulletin, Date dateDebut, Date dateFin) {
		double montant = 0.0D;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT SUM(A.montant) AS somme FROM "
				+ Tables.getTableName(Tables.TableName.bulletinAvance) + " AS A  LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.bulletinPaie) + " AS B  ON A.id_bulletin=B.id WHERE 1=1";
		if (idBulletin > 0) {
			sqlRequest = String.valueOf(sqlRequest) + " AND A.id_bulletin=" + idBulletin;
		}

		if (dateDebut != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie>='" + HelperC.convertDate(dateDebut, false)
					+ "'";
		}
		if (dateFin != null) {
			sqlRequest = String.valueOf(sqlRequest) + " AND B.date_paie<='" + HelperC.convertDate(dateFin, false) + "'";
		}

		try {
			pstmt = con.prepareStatement(sqlRequest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				montant = rs.getDouble("somme");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return montant;
	}

	public boolean deleteBulletinPaie(BulletinPaieC bulletin) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinPaie) + " WHERE id="
				+ bulletin.getId();

		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			deleteBulletinAllocation(bulletin, stmt);
			deleteBulletinAvance(bulletin, stmt);
			deleteBulletinCotisation(bulletin, stmt);
			deleteBulletinCredit(bulletin, stmt);
			deleteBulletinDeduction(bulletin, stmt);
			deleteBulletinHeureSup(bulletin, stmt);
			deleteBulletinIndemnite(bulletin, stmt);
			deleteBulletinPrime(bulletin, stmt);
			deleteRemboursement(bulletin.getId(), stmt);
			deletePaieAnormal(bulletin.getDatePaie(), stmt);
			stmt.executeUpdate(sql);
			
			if(bulletin.getHistory()!=null)
			{
				bulletin.getHistory().setIdLigne(bulletin.getId());
				insertHistorique(bulletin.getHistory(), con);
			}
			
			con.commit();
			deleted = true;

		} catch (SQLException e) {

			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	public void deleteListBulletinPaie(List<BulletinPaieC> list) {
		Statement stmt = null;
		String sql = "";

		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			if (list.size() > 0) {

				for (BulletinPaieC bulletin : list) {

					sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinPaie) + " WHERE id="
							+ bulletin.getId();
					stmt.executeUpdate(sql);
					deleteBulletinAllocation(bulletin, stmt);
					deleteBulletinAvance(bulletin, stmt);
					deleteBulletinCotisation(bulletin, stmt);
					deleteBulletinCredit(bulletin, stmt);
					deleteBulletinDeduction(bulletin, stmt);
					deleteBulletinHeureSup(bulletin, stmt);
					deleteBulletinIndemnite(bulletin, stmt);
					deleteBulletinPrime(bulletin, stmt);
					deleteRemboursement(bulletin.getId(), stmt);
				}
			}

			con.commit();

		} catch (SQLException e) {

			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
	}

	private boolean deleteBulletinAllocation(BulletinPaieC bulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinAllocation) + " WHERE id_bulletin="
				+ bulletin.getId();

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean deleteBulletinAvance(BulletinPaieC bulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinAvance) + " WHERE id_bulletin="
				+ bulletin.getId();

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean deleteBulletinAvance(int idBulletin, Connection conn) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinAvance) + " WHERE id_bulletin="
				+ idBulletin;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}
	private boolean deleteAvance(int idAvance, Connection conn) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinAvance) + " WHERE id_avance="
				+ idAvance;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}
	private boolean deleteBulletinCotisation(BulletinPaieC bulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinPaieCotisation)
				+ " WHERE id_bulletin=" + bulletin.getId();

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean deleteBulletinCredit(BulletinPaieC bulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinCredit) + " WHERE id_bulletin="
				+ bulletin.getId();

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean deleteBulletinDeduction(BulletinPaieC bulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinDeduction) + " WHERE id_bulletin="
				+ bulletin.getId();

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean deleteBulletinHeureSup(BulletinPaieC bulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinHeureSup) + " WHERE id_bulletin="
				+ bulletin.getId();

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean deleteBulletinIndemnite(BulletinPaieC bulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinPaieIndemnite)
				+ " WHERE id_bulletin=" + bulletin.getId();

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean deleteBulletinPrime(BulletinPaieC bulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.bulletinPaiePrime) + " WHERE id_bulletin="
				+ bulletin.getId();

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean deleteRemboursement(int idBulletin, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.remboursementCredit) + " WHERE id_bulletin="
				+ idBulletin;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean insertPointagePresence(FichePresenceC pointage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		pointage.setId(getId(Tables.getTableName(Tables.TableName.pointagePresence)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.pointagePresence)
				+ " (id,id_dossier,id_employe,heure_entree,heure_sortie,date_pointage,type_presence) VALUES (?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, pointage.getId());
			if (pointage.getExercise() != null) {
				stmt.setInt(2, pointage.getExercise().getId());
			}
			if (pointage.getEmploye() != null) {
				stmt.setInt(3, pointage.getEmploye().getId());
			}
			stmt.setObject(4, pointage.getHeureEntreee());
			stmt.setObject(5, pointage.getHeureSortie());
			stmt.setObject(6, pointage.getDatePointage());
			stmt.setInt(7, pointage.getIdTypeHeurePreste());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updatePointagePresence(FichePresenceC pointage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.pointagePresence)
				+ " SET heure_entree=?,heure_sortie=?,date_pointage=?,type_presence=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, pointage.getHeureEntreee());
			stmt.setObject(2, pointage.getHeureSortie());
			stmt.setObject(3, pointage.getDatePointage());
			stmt.setInt(4, pointage.getIdTypeHeurePreste());
			stmt.setInt(5, pointage.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdatePointagePresence(FichePresenceC pointage) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (pointage.getId() == 0) {

				saved = insertPointagePresence(pointage);
				if (!saved) {
					pointage.setId(0);
				}
			} else {

				saved = updatePointagePresence(pointage);
			}
			if (saved) {

				pointage.getHistoric().setIdLigne(pointage.getId());
				saved = insertHistorique(pointage.getHistoric(), conn);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private FichePresenceC setPointagePresence(ResultSet rs) throws SQLException {
		FichePresenceC pointage = new FichePresenceC();
		pointage.setId(rs.getInt("id"));
		if (rs.getObject("id_dossier") != null) {
			pointage.setExercise(FichierBaseDAO.getInstance().getExercice(rs.getInt("id_dossier")));
		}
		if (rs.getObject("id_employe") != null) {
			pointage.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		}
		pointage.setHeureEntreee(rs.getString("heure_entree"));
		pointage.setHeureSortie(rs.getString("heure_sortie"));
		if (rs.getObject("date_pointage") != null) {
			pointage.setDatePointage((Date) rs.getObject("date_pointage"));
		}
		pointage.setIdTypeHeurePreste(rs.getInt("type_presence"));
		return pointage;
	}

	public FichePresenceC getPointagePresence(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FichePresenceC pointage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.pointagePresence) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				pointage = setPointagePresence(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return pointage;
	}

	public boolean deletePointagePresence(FichePresenceC pointage) {
		boolean deleted = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			deleted = insertHistorique(pointage.getHistoric(), conn);
			if (deleted) {
				deleted = deleteNotAutocommit(pointage.getId(), Tables.getTableName(Tables.TableName.pointagePresence));
			}
			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public FichePresenceC getFichePresenceParEmployeEtDatePointage(int idEmploye, String date, int typeHeure) {
		FichePresenceC fiche = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.pointagePresence)
				+ " WHERE id_employe=? AND date_pointage=? AND type_presence=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idEmploye);
			pstmt.setString(2, date);
			pstmt.setInt(3, typeHeure);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fiche = setPointagePresence(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return fiche;
	}

	public List<FichePresenceC> getListHeureTravail(int idEmploye, int typeHeure, int mois) {
		List<FichePresenceC> listHeures = new ArrayList<FichePresenceC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.pointagePresence)
				+ " WHERE (SELECT MONTH(date_pointage))=? AND id_employe=? AND type_presence=? ";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, mois);
			pstmt.setInt(2, idEmploye);
			pstmt.setInt(3, typeHeure);
			for (rs = pstmt.executeQuery(); rs.next(); listHeures.add(setPointagePresence(rs)))
				;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return listHeures;
	}

	private boolean insertSaisieAbsence(SaisieAbsenceC saisabse) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {

			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisieabsence)
					+ " (id_employe,motif,date_absence,duree,retenu,mois_paie,id_exercice)"
					+ " VALUES (?,?,?,?,?,?,?) ";
			stmt = con.prepareStatement(sql);

			if (saisabse.getEmploye() != null) {

				stmt.setInt(1, saisabse.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setString(2, saisabse.getObservation());
			stmt.setObject(3, saisabse.getDate());
			stmt.setInt(4, saisabse.getDuree());
			stmt.setBoolean(5, saisabse.isRetenuPaie());
			stmt.setInt(6, saisabse.getMoisPaie());
			stmt.setInt(7, saisabse.getIdExercice());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateSaisieAbsence(SaisieAbsenceC saisabse) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisieabsence)
					+ " SET motif=?,date_absence=?,duree=?,retenu=?,mois_paie=?,id_exercice=? WHERE id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, saisabse.getObservation());
			stmt.setObject(2, saisabse.getDate());
			stmt.setInt(3, saisabse.getDuree());
			stmt.setBoolean(4, saisabse.isRetenuPaie());
			stmt.setInt(5, saisabse.getMoisPaie());
			stmt.setInt(6, saisabse.getIdExercice());
			stmt.setInt(7, saisabse.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateSaisieAbsence(SaisieAbsenceC saisabse) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (saisabse.getId() == 0) {

				saved = insertSaisieAbsence(saisabse);
			} else {

				saved = updateSaisieAbsence(saisabse);
			}
			if (saved) {

				saisabse.getHistorique().setIdLigne(saisabse.getId());
				saved = insertHistorique(saisabse.getHistorique(), conn);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private SaisieAbsenceC setSaisieAbsence(ResultSet rs) throws SQLException {
		SaisieAbsenceC saisabse = new SaisieAbsenceC();
		saisabse.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			saisabse.setEmploye(getEmployeSimple(rs.getInt("id_employe")));
		}
		saisabse.setObservation(rs.getString("motif"));
		saisabse.setDate(rs.getDate("date_absence"));
		saisabse.setDateS(HelperC.changeDateFormate(saisabse.getDate()));
		saisabse.setDuree(rs.getInt("duree"));
		//saisabse.setRetenuPaie(rs.getBoolean("retenu"));
		saisabse.setMoisPaie(rs.getInt("mois_paie"));
		return saisabse;
	}

	public SaisieAbsenceC getSaisieAbsence(int idEmploye, Date date, int duree) {
		SaisieAbsenceC saisabse = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieabsence) + " WHERE id_employe="
				+ idEmploye + " AND date_absence='" + date + "' AND duree='" + duree + "'";

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			if (res.next()) {
				saisabse = setSaisieAbsence(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saisabse;
	}

	public int getNombreHeureAbsence(int idEmploye, int mois, int idExercice) {
		int nbrHr = 0;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();

			String sql = " SELECT SUM(duree) as hrs FROM " + Tables.getTableName(Tables.TableName.saisieabsence)
					+ " WHERE id_employe=" + idEmploye + " AND mois_paie=" + mois + " AND id_exercice=" + idExercice
					+ " AND retenu=1";

			res = stmt.executeQuery(sql);
			if (res.next()) {
				nbrHr = res.getInt("hrs");

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return nbrHr;
	}

	public SaisieAbsenceC getSaisieAbsenceParEmployeEtDate(int idEmploye, Date date) {
		SaisieAbsenceC saisabse = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieabsence) + " WHERE id_employe="
				+ idEmploye + " AND date_absence='" + HelperC.convertDate(date, false) + "'";

		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);

			if (res.next()) {
				saisabse = setSaisieAbsence(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saisabse;
	}

	public List<SaisieAbsenceC> getAllSaisieAbsence() {
		List<SaisieAbsenceC> saisabse = new ArrayList<SaisieAbsenceC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieabsence);
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				saisabse.add(setSaisieAbsence(rs));
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saisabse;
	}

	public List<SaisieAbsenceC> getListSaisieAbsence(EmployeC employe) {
		List<SaisieAbsenceC> saisabse = new ArrayList<SaisieAbsenceC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieabsence) + "WHERE id_employe="
					+ employe.getId();
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				saisabse.add(setSaisieAbsence(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saisabse;
	}

	private boolean insertHeuresPreste(HeuresPrestees hp) {
		boolean saved = false;
		PreparedStatement stmt = null;
		hp.setId(getId(Tables.getTableName(Tables.TableName.HeurePreste)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.HeurePreste)
				+ " (id,taux,heures,minutes,seconde,id_employe,mois,id_exercice,numero_hs) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, hp.getId());
			stmt.setDouble(2, hp.getPourcent());
			stmt.setInt(3, hp.getHeure());
			stmt.setInt(4, hp.getMinute());
			stmt.setInt(5, hp.getSec());
			stmt.setInt(6, hp.getIdEmploye());
			stmt.setInt(7, hp.getMois());
			stmt.setInt(8, hp.getIdExercice());
			stmt.setString(9, hp.getNumeroHs());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateHeurePreste(HeuresPrestees hp) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE " + Tables.getTableName(Tables.TableName.HeurePreste)
					+ " SET taux=?,heures=?,minutes=?,seconde=?,id_employe=?,mois=?,numero_hs=?" + " WHERE id=?";

			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, hp.getPourcent());
			stmt.setInt(2, hp.getHeure());
			stmt.setInt(3, hp.getMinute());
			stmt.setInt(4, hp.getSec());
			stmt.setInt(5, hp.getIdEmploye());
			stmt.setInt(6, hp.getMois());
			stmt.setString(7, hp.getNumeroHs());
			stmt.setInt(8, hp.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateHeurePreste(HeuresPrestees hp) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (hp.getId() == 0) {

				saved = insertHeuresPreste(hp);
			} else {

				saved = updateHeurePreste(hp);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	public boolean insertUpdateHeureSupplementaire(List<HeuresPrestees> listhSup) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			for (HeuresPrestees hp : listhSup) {

				if (hp.getHeure() > 0 || hp.getMinute() > 0 || hp.getSec() > 0) {

					if (hp.getId() == 0) {

						saved = insertHeuresPreste(hp);
						continue;
					}
					saved = updateHeurePreste(hp);
				}
			}

			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	public List<HeuresPrestees> getListHeurePrestees(int idEmploye, int idExercice, int mois) {
		List<HeuresPrestees> listHeures = new ArrayList<HeuresPrestees>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";
		HeuresPrestees hp = null;

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT taux,SUM(heures) AS heures,SUM(minutes) AS minutes,SUM(seconde)  AS seconde FROM  "
					+ Tables.getTableName(Tables.TableName.HeurePreste) + " WHERE id_employe=" + idEmploye
					+ " AND mois=" + mois + " AND id_exercice=" + idExercice + " GROUP BY taux ";

			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				hp = new HeuresPrestees();
				hp.setHeure(rs.getInt("heures"));
				hp.setMinute(rs.getInt("minutes"));
				hp.setSec(rs.getInt("seconde"));
				hp.setPourcent(rs.getDouble("taux"));

				listHeures.add(hp);

			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listHeures;
	}

	public List<HeuresPrestees> getListHeureSupplementaire(int idEmploye, int idExercice, int mois) {
		List<HeuresPrestees> listHeures = new ArrayList<HeuresPrestees>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM  " + Tables.getTableName(Tables.TableName.HeurePreste) + " WHERE id_employe="
					+ idEmploye + " AND mois=" + mois + " AND id_exercice=" + idExercice;
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				listHeures.add(setHeurePreste(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listHeures;
	}

	private HeuresPrestees setHeurePreste(ResultSet rs) throws SQLException {
		HeuresPrestees hp = new HeuresPrestees();
		hp.setId(rs.getInt("id"));
		hp.setHeure(rs.getInt("heures"));
		hp.setMinute(rs.getInt("minutes"));
		hp.setSec(rs.getInt("seconde"));
		hp.setIdEmploye(rs.getInt("id_employe"));
		hp.setPourcent(rs.getDouble("taux"));
		hp.setNumeroHs(rs.getString("numero_hs"));
		return hp;
	}

	public HeuresPrestees getHeurePrste(int idEmploye, Date date, double taux) {
		HeuresPrestees hp = null;
		Statement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.createStatement();
			String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.HeurePreste) + " WHERE id_employe="
					+ idEmploye + " AND taux=" + taux + " AND date_travail='" + HelperC.convertDate(date, false) + "'";
			res = stmt.executeQuery(sql);
			if (res.next()) {
				hp = setHeurePreste(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return hp;
	}

	public boolean deleteHeurePrste(int idEmploye, int mois, int idExercice) {
		PreparedStatement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.HeurePreste) + " WHERE id_employe="
				+ idEmploye + " AND mois=" + mois + " AND id_exercice=" + idExercice;

		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean insertSaisieDemandeConge(SaisieDemandeCongeC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		saisie.setId(getId(Tables.getTableName(Tables.TableName.saisieDemandeConge)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisieDemandeConge)
				+ " (id, id_employe, id_type_conge, date_debut,date_fin, motif,"
				+ " date_demande, date_decision, decision, id_exercice,etat,inLine) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, saisie.getId());

			if (saisie.getEmploye() != null)
				stmt.setInt(2, saisie.getEmploye().getId());
			else
				stmt.setObject(2, (Object) null);

			if (saisie.getTypeConge() != null)
				stmt.setInt(3, saisie.getTypeConge().getId());
			else
				stmt.setObject(3, (Object) null);

			stmt.setObject(4, saisie.getDateDebut());
			stmt.setObject(5, saisie.getDateFin());
			stmt.setString(6, saisie.getMotif());
			stmt.setObject(7, saisie.getDateDemande());
			stmt.setObject(8, saisie.getDateDecision());
			stmt.setInt(9, saisie.getDecision());
			stmt.setInt(10, saisie.getExercise().getId());
			stmt.setInt(11, saisie.getEtat());
			stmt.setBoolean(12, saisie.isInLine());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateSaisieDemandeConge(SaisieDemandeCongeC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisieDemandeConge) + " SET  "
				+ "  id_type_conge=?, date_debut=?,date_fin=?, motif=?,"
				+ " date_demande=?, date_decision=?, decision=?,etat=?," + " inLine=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);

			if (saisie.getTypeConge() != null)
				stmt.setInt(1, saisie.getTypeConge().getId());
			else
				stmt.setObject(1, (Object) null);

			stmt.setObject(2, saisie.getDateDebut());
			stmt.setObject(3, saisie.getDateFin());
			stmt.setString(4, saisie.getMotif());
			stmt.setObject(5, saisie.getDateDemande());
			stmt.setObject(6, saisie.getDateDecision());
			stmt.setInt(7, saisie.getDecision());
			stmt.setInt(8, saisie.getEtat());
			stmt.setBoolean(9, saisie.isInLine());
			stmt.setInt(10, saisie.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateSaisieDemandeConge(SaisieDemandeCongeC saisie) {
		boolean saved = false;
		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (saisie.getId() == 0) {
				saved = insertSaisieDemandeConge(saisie);
				if (!saved) {
					saisie.setId(0);
				}
			} else {
				saved = updateSaisieDemandeConge(saisie);
			}
			if (saved) {
				saisie.getHistorique().setIdLigne(saisie.getId());
				saved = insertHistorique(saisie.getHistorique(), conn);
			}

			if (saved) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saved;
	}

	private SaisieDemandeCongeC setSaisieDemandeConge(ResultSet rs) throws SQLException {
		SaisieDemandeCongeC saisie = new SaisieDemandeCongeC();
		saisie.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null)
			saisie.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		if (rs.getObject("id_type_conge") != null)
			saisie.setTypeConge(FichierBaseDAO.getInstance().getTypeConge(rs.getInt("id_type_conge")));
		saisie.setDateDebut(rs.getDate("date_debut"));
		if (saisie.getDateDebut() != null)
			saisie.setDateDebutS(HelperC.changeDateFormate(saisie.getDateDebut()));
		saisie.setDateFin(rs.getDate("date_fin"));
		if (saisie.getDateFin() != null)
			saisie.setDateFinS(HelperC.changeDateFormate(saisie.getDateFin()));
		saisie.setMotif(rs.getString("motif"));

		saisie.setDateDemande(rs.getDate("date_demande"));
		if (saisie.getDateDemande() != null)
			saisie.setDateDemandeS(HelperC.changeDateFormate(saisie.getDateDemande()));
		saisie.setDateDecision(rs.getDate("date_decision"));
		saisie.setEtat(rs.getInt("etat"));
		saisie.setDecision(rs.getInt("decision"));
		saisie.setInLine(rs.getBoolean("inLine"));
		return saisie;
	}

	public SaisieDemandeCongeC getSaisieDemandeConge(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisieDemandeCongeC saisie = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieDemandeConge) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisieDemandeConge(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return saisie;
	}

	public boolean deleteSaisieDemandeConge(SaisieDemandeCongeC saisie) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);
			deleted = insertHistorique(saisie.getHistorique(), con);
			if (deleted)
				deleted = deleteNotAutocommit(saisie.getId(), Tables.getTableName(Tables.TableName.saisieDemandeConge));
			if (deleted) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public SaisieDemandeCongeC getSaisieDemandeConge(EmployeC employe, Date dateDebut, Date dateFin, int id) {
		SaisieDemandeCongeC demande = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieDemandeConge)
				+ " WHERE id_employe=? AND date_debut>=? ANd date_fin<=? AND id<>?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			stmt.setObject(2, dateDebut);
			stmt.setObject(3, dateFin);
			stmt.setInt(4, id);
			res = stmt.executeQuery();
			if (res.next()) {
				demande = setSaisieDemandeConge(res);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return demande;
	}

	public SaisieDemandeCongeC getSaisieDemandeConge(EmployeC employe, Date dateDebut, int id) {
		SaisieDemandeCongeC demande = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieDemandeConge)
				+ " WHERE id_employe=? AND date_debut>=? AND id<>?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			stmt.setObject(2, dateDebut);
			stmt.setInt(3, id);
			res = stmt.executeQuery();
			if (res.next()) {
				demande = setSaisieDemandeConge(res);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return demande;
	}

	public int getCongeDejaPris(EmployeC employe, int idTyp, int idExercice, Date deb, Date fn) {
		List<SaisieDemandeCongeC> demande = new ArrayList<SaisieDemandeCongeC>();
		int dys = 0;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();

			String sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieDemandeConge)
					+ " WHERE id_employe=" + employe.getId() + " AND id_type_conge=" + idTyp
					+ " AND decision=2 AND id_exercice=" + idExercice + " ";
			if (deb != null)
				sqlRequest += " AND date_debut <>'" + HelperC.convertDate(deb, false) + "'";
			if (fn != null)
				sqlRequest += " AND date_fin <> '" + HelperC.convertDate(fn, false) + "'";

			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				demande.add(setSaisieDemandeConge(rs));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		for (SaisieDemandeCongeC saisie : demande) {
			dys += (int) HelperC.daysBetween(saisie.getDateDebut(), saisie.getDateFin());
		}
		return dys;
	}

	public List<SaisieDemandeCongeC> getListCongeTraite(int decision, int idExercice) {
		List<SaisieDemandeCongeC> demande = new ArrayList<SaisieDemandeCongeC>();

		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieDemandeConge)
				+ " WHERE id_exercice=" + idExercice + " AND  decision=" + decision;

		sqlRequest = String.valueOf(sqlRequest) + " ORDER BY date_debut DESC";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				demande.add(setSaisieDemandeConge(rs));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<SaisieDemandeCongeC> getListCongeLine(int idExercice, int idEmploye) {
		List<SaisieDemandeCongeC> demande = new ArrayList<SaisieDemandeCongeC>();

		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieDemandeConge)
				+ " WHERE id_exercice=" + idExercice + " AND  inLine=1 AND id_employe=" + idEmploye;

		sqlRequest = String.valueOf(sqlRequest) + " ORDER BY date_debut DESC";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				demande.add(setSaisieDemandeConge(rs));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<SaisieDemandeCongeC> getListSaisieDemandeConge(int etat, int idExercice) {
		Statement stmt = null;
		ResultSet rs = null;
		List<SaisieDemandeCongeC> demande = new ArrayList<SaisieDemandeCongeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieDemandeConge) + " WHERE id_exercice="
				+ idExercice + " AND decision=" + etat;
	
		
		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				demande.add(setSaisieDemandeConge(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<SaisieDemandeCongeC> getListSaisieFinConge(int etat) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SaisieDemandeCongeC> demande = new ArrayList<SaisieDemandeCongeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieDemandeConge) + " "
				+ " WHERE etat=? AND date_attribution IS NOT NULL AND date_debut IS NOT NULL "
				+ " AND date_fin IS NOT NULL AND date_retour IS NULL AND current_date>date_fin";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, etat);
			rs = stmt.executeQuery();
			while (rs.next()) {
				demande.add(setSaisieDemandeConge(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public boolean annulerConge(SaisieDemandeCongeC saisie) {
		boolean saved = false;
		Statement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisieDemandeConge)
				+ " SET  date_decision=null,decision=1 WHERE id=" + saisie.getId();

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean insertJoursCongeEmploye(JoursCongeEmployeC jour) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			jour.setId(getId(Tables.getTableName(Tables.TableName.joursCongeEmploye)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.joursCongeEmploye)
					+ " (id,id_employe,id_exercice,jours_du,jours_pris,jours_reportes)" + " VALUES (?,?,?,?,?,?) ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, jour.getId());
			if (jour.getEmploye() != null) {

				stmt.setInt(2, jour.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (jour.getExercice() != null) {

				stmt.setInt(3, jour.getExercice().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, jour.getJoursDu());
			stmt.setDouble(5, jour.getJoursPris());
			stmt.setDouble(6, jour.getJoursReportes());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateJoursCongeEmploye(JoursCongeEmployeC jour) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE " + Tables.getTableName(Tables.TableName.joursCongeEmploye)
					+ " SET id_employe=?,id_exercice=?,jours_du=?,jours_pris=?,jours_reportes=? WHERE id=?";
			stmt = con.prepareStatement(sql);
			if (jour.getEmploye() != null) {

				stmt.setInt(1, jour.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (jour.getExercice() != null) {

				stmt.setInt(2, jour.getExercice().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, jour.getJoursDu());
			stmt.setDouble(4, jour.getJoursPris());
			stmt.setDouble(5, jour.getJoursReportes());
			stmt.setDouble(6, jour.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateJoursCongeEmploye(JoursCongeEmployeC jour) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (jour.getId() == 0) {

				saved = insertJoursCongeEmploye(jour);
			} else {

				saved = updateJoursCongeEmploye(jour);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		return saved;
	}

	private JoursCongeEmployeC setJoursCongeEmploye(ResultSet rs) throws SQLException {
		JoursCongeEmployeC jour = new JoursCongeEmployeC();
		jour.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			jour.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		}
		if (rs.getObject("id_exercice") != null) {
			jour.setExercice(FichierBaseDAO.getInstance().getExercice(rs.getInt("id_exercice")));
		}
		jour.setJoursDu(rs.getDouble("jours_du"));
		jour.setJoursDuS(HelperC.TraitementMontant.getMontantFormate(jour.getJoursDu(), 1));
		jour.setJoursPris(rs.getDouble("jours_pris"));
		jour.setJoursReportes(rs.getDouble("jours_reportes"));
		return jour;
	}

	public JoursCongeEmployeC getJoursCongeEmploye(EmployeC employe, int id) {
		JoursCongeEmployeC jour = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.joursCongeEmploye)
				+ " WHERE id_employe=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			stmt.setInt(2, id);
			res = stmt.executeQuery();
			if (res.next()) {
				jour = setJoursCongeEmploye(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return jour;
	}

	public JoursCongeEmployeC getJoursCongeEmploye(EmployeC employe) {
		JoursCongeEmployeC jour = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.joursCongeEmploye)
				+ " WHERE id_employe=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			res = stmt.executeQuery();
			if (res.next()) {
				jour = setJoursCongeEmploye(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return jour;
	}

	public List<JoursCongeEmployeC> getListAllJoursCongeEmploye(int idExercice) {
		List<JoursCongeEmployeC> prevision = new ArrayList<JoursCongeEmployeC>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sqlRequest = " SELECT JC.* FROM " + Tables.getTableName(Tables.TableName.joursCongeEmploye)
					+ " AS JC LEFT OUTER JOIN " + Tables.getTableName(Tables.TableName.employe)
					+ " AS E ON JC.id_employe=E.id WHERE id_exercice='" + idExercice + "' ORDER BY E.nom";
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				prevision.add(setJoursCongeEmploye(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return prevision;
	}

	private boolean insertPrevisionFraisMedicaux(PrevisionFraisMedicauxC prevision) {
		boolean saved = false;
		PreparedStatement stmt = null;

		try {
			prevision.setId(getId(Tables.getTableName(Tables.TableName.previsionFraisMedicaux)));
			String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.previsionFraisMedicaux)
					+ " (id,id_employe,prevision_par_employe,prevision_par_personne_a_charge,designation," +

					"reference_conge,reference_frais_medicaux,montant_a_nouveau,montant_periode,nombre_personnes_charge)"
					+

					" " + " VALUES (?,?,?,?,?,?,?,?,?,?) ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, prevision.getId());
			if (prevision.getEmploye() != null) {

				stmt.setInt(2, prevision.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, prevision.getPrevisionParEmploye());
			stmt.setDouble(4, prevision.getPrevisionParPersonneACharge());
			stmt.setString(5, prevision.getDesignation());
			stmt.setDouble(6, prevision.getReferenceConge());
			stmt.setDouble(7, prevision.getReferenceFraisMedicaux());
			stmt.setDouble(8, prevision.getMontantANouveau());
			stmt.setDouble(9, prevision.getMontantPeriode());
			stmt.setInt(10, prevision.getNombrePersonnesAcharge());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updatePrevisionFraisMedicaux(PrevisionFraisMedicauxC prevision) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.previsionFraisMedicaux)
				+ " SET id_employe=?,prevision_par_employe=?,prevision_par_personne_a_charge=?,designation=?," +

				"reference_conge=?,reference_frais_medicaux=?,montant_a_nouveau=?,montant_periode=?," +

				"nombre_personnes_charge=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			if (prevision.getEmploye() != null) {

				stmt.setInt(1, prevision.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setDouble(2, prevision.getPrevisionParEmploye());
			stmt.setDouble(3, prevision.getPrevisionParPersonneACharge());
			stmt.setString(4, prevision.getDesignation());
			stmt.setDouble(5, prevision.getReferenceConge());
			stmt.setDouble(6, prevision.getReferenceFraisMedicaux());
			stmt.setDouble(7, prevision.getMontantANouveau());
			stmt.setDouble(8, prevision.getMontantPeriode());
			stmt.setInt(9, prevision.getNombrePersonnesAcharge());
			stmt.setInt(10, prevision.getId());
			stmt.execute();
			saved = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saved;
	}

	public boolean insertUpdatePrevisionFraisMedicaux(PrevisionFraisMedicauxC prevision) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (prevision.getId() == 0) {

				saved = insertPrevisionFraisMedicaux(prevision);
			} else {

				saved = updatePrevisionFraisMedicaux(prevision);
			}

			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		return saved;
	}

	private PrevisionFraisMedicauxC setPrevisionFraisMedicaux(ResultSet rs) throws SQLException {
		PrevisionFraisMedicauxC prevision = new PrevisionFraisMedicauxC();
		prevision.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			prevision.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		}
		prevision.setPrevisionParEmploye(rs.getDouble("prevision_par_employe"));
		prevision.setPrevisionParEmployeS(
				HelperC.TraitementMontant.getMontantFormate(prevision.getPrevisionParEmploye(), 0));
		prevision.setPrevisionParPersonneACharge(rs.getDouble("prevision_par_personne_a_charge"));
		prevision.setPrevisionParPersonneAChargeS(
				HelperC.TraitementMontant.getMontantFormate(prevision.getPrevisionParPersonneACharge(), 0));
		prevision.setReferenceConge(rs.getDouble("reference_conge"));
		prevision.setReferenceCongeS(HelperC.TraitementMontant.getMontantFormate(prevision.getReferenceConge(), 0));
		prevision.setReferenceFraisMedicaux(rs.getDouble("reference_frais_medicaux"));
		prevision.setReferenceFraisMedicauxS(
				HelperC.TraitementMontant.getMontantFormate(prevision.getReferenceFraisMedicaux(), 0));
		prevision.setMontantANouveau(rs.getDouble("montant_a_nouveau"));
		prevision.setMontantANouveauS(HelperC.TraitementMontant.getMontantFormate(prevision.getMontantANouveau(), 0));
		prevision.setMontantPeriode(rs.getDouble("montant_periode"));
		prevision.setMontantPeriodeS(HelperC.TraitementMontant.getMontantFormate(prevision.getMontantPeriode(), 0));
		prevision.setDesignation(rs.getString("designation"));
		prevision.setNombrePersonnesAcharge(rs.getInt("nombre_personnes_charge"));
		return prevision;
	}

	public PrevisionFraisMedicauxC getPrevisionFraisMedicaux(EmployeC employe, int id) {
		PrevisionFraisMedicauxC prevision = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.previsionFraisMedicaux)
				+ " WHERE id_employe=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			stmt.setInt(2, id);
			res = stmt.executeQuery();
			if (res.next()) {
				prevision = setPrevisionFraisMedicaux(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return prevision;
	}

	public PrevisionFraisMedicauxC getPrevisionFraisMedicaux(EmployeC employe) {
		PrevisionFraisMedicauxC prevision = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.previsionFraisMedicaux)
				+ " WHERE id_employe=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			res = stmt.executeQuery();
			if (res.next()) {
				prevision = setPrevisionFraisMedicaux(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return prevision;
	}

	public List<PrevisionFraisMedicauxC> getListAllPrevisionFraisMedicaux() {
		List<PrevisionFraisMedicauxC> prevision = new ArrayList<PrevisionFraisMedicauxC>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.previsionFraisMedicaux)
					+ " ORDER BY id";
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				prevision.add(setPrevisionFraisMedicaux(rs));
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return prevision;
	}

	private boolean insertEvaluationEmploye(EvaluationEmployeC evaluation, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		evaluation.setId(getId(Tables.getTableName(Tables.TableName.evaluationEmploye)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.evaluationEmploye)
				+ " (id,id_employe,date_evaluation,id_type_notation,id_type_appreciation,"
				+ "note_evaluation,justification_discordance,annee_validite,pourcentage,"
				+ "note_generale,id_last_grad,id_new_grad,last_salary,new_salary,taux_nouv_salaire) " +

				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, evaluation.getId());
			if (evaluation.getEmploye() != null) {

				stmt.setInt(2, evaluation.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setObject(3, evaluation.getDateEvaluation());
			if (evaluation.getTypeNotation() != null) {

				stmt.setInt(4, evaluation.getTypeNotation().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			if (evaluation.getTypeAppreciation() != null) {

				stmt.setInt(5, evaluation.getTypeAppreciation().getId());
			} else {

				stmt.setObject(5, (Object) null);
			}
			stmt.setDouble(6, evaluation.getNoteEvaluation());
			stmt.setString(7, evaluation.getJustificationDiscordance());
			stmt.setInt(8, evaluation.getAnneeValidite());
			stmt.setDouble(9, evaluation.getPourcentage());
			stmt.setDouble(10, evaluation.getNoteGenerale());
			stmt.setInt(11, evaluation.getIdAnGrade());
			stmt.setInt(12, evaluation.getIdNvGrade());
			stmt.setDouble(13, evaluation.getAncienSalary());
			stmt.setDouble(14, evaluation.getNouveauSalaire());
			stmt.setDouble(15, evaluation.getTauxAvSal());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateEvaluationEmploye(EvaluationEmployeC evaluation, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.evaluationEmploye)
				+ " SET id_employe=?,date_evaluation=?,id_type_notation=?,id_type_appreciation=?,note_evaluation=?,"
				+ "justification_discordance=?,annee_validite=?,pourcentage=?,note_generale=?,id_last_grad=?,"
				+ "id_new_grad=?,last_salary=?,new_salary=?,taux_nouv_salaire=? WHERE id=?";

		try {
			stmt = conn.prepareStatement(sql);
			if (evaluation.getEmploye() != null) {

				stmt.setInt(1, evaluation.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setObject(2, evaluation.getDateEvaluation());
			if (evaluation.getTypeNotation() != null) {

				stmt.setInt(3, evaluation.getTypeNotation().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			if (evaluation.getTypeAppreciation() != null) {

				stmt.setInt(4, evaluation.getTypeAppreciation().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			stmt.setDouble(5, evaluation.getNoteEvaluation());
			stmt.setString(6, evaluation.getJustificationDiscordance());
			stmt.setInt(7, evaluation.getAnneeValidite());
			stmt.setDouble(8, evaluation.getPourcentage());
			stmt.setDouble(9, evaluation.getNoteGenerale());
			stmt.setInt(10, evaluation.getIdAnGrade());
			stmt.setInt(11, evaluation.getIdNvGrade());
			stmt.setDouble(12, evaluation.getAncienSalary());
			stmt.setDouble(13, evaluation.getNouveauSalaire());
			stmt.setDouble(14, evaluation.getTauxAvSal());
			stmt.setInt(15, evaluation.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateEvaluationEmploye(EvaluationEmployeC evaluation) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (evaluation.getId() == 0) {

				saved = insertEvaluationEmploye(evaluation, conn);
				if (!saved) {
					evaluation.setId(0);
				}
			} else {

				saved = updateEvaluationEmploye(evaluation, conn);
			}
			if (saved) {
				for (EvaluationEmployeDetailCritereC detail : evaluation.getListDetailEvaluation()) {

					detail.setEntete(evaluation);
					if (detail.getId() == 0) {

						saved = insertEvaluationEmployeDetailCritere(detail, conn);
					} else {

						saved = updateEvaluationEmployeDetailCritere(detail, conn);
					}
					if (!saved) {
						break;
					}
				}
			}

			if (saved) {
				for (EvaluationEmployeDetailCritereC detail : evaluation.getListDetailEvaluationDeleted()) {

					if (detail.getId() > 0) {
						saved = deleteEvaluationEmployeDetailCritere(detail, conn);
					}
					if (!saved) {
						break;
					}
				}
			}

			if (saved) {
				evaluation.getTraitement().setIdRef(evaluation.getId());

				if (evaluation.getTraitement().getId() == 0)
					saved = insertTraitementSalarial(evaluation.getTraitement(), conn);
				else
					saved = updateTraitementSalarial(evaluation.getTraitement(), conn);
			}

			if (saved) {
				if (evaluation.getNouvelgrd() != null && evaluation.getNouvelgrd().getId() == 0)
					saved = insertDetailGrade(evaluation.getNouvelgrd(), conn);
			}
			if (saved) {

				evaluation.getHistorique().setIdLigne(evaluation.getId());
				saved = insertHistorique(evaluation.getHistorique(), conn);
			}
			if (saved) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private EvaluationEmployeC setEvaluationEmploye(ResultSet rs) throws SQLException {
		EvaluationEmployeC evaluation = new EvaluationEmployeC();
		evaluation.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			evaluation.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		}
		evaluation.setDateEvaluation(rs.getDate("date_evaluation"));
		evaluation.setDateEvaluationS(HelperC.changeDateFormate(evaluation.getDateEvaluation()));
		if (rs.getObject("id_type_notation") != null) {
			evaluation.setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(rs.getInt("id_type_notation")));
		}
		if (rs.getObject("id_type_appreciation") != null) {
			evaluation.setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_type_appreciation"),
					Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
		}
		evaluation.setNoteEvaluation(rs.getDouble("note_evaluation"));
		evaluation.setTauxAvSal(rs.getDouble("taux_nouv_salaire"));
		evaluation.setJustificationDiscordance(rs.getString("justification_discordance"));
		evaluation.setAnneeValidite(rs.getInt("annee_validite"));
		evaluation.setPourcentage(rs.getDouble("pourcentage"));
		evaluation.setAncienSalary(rs.getDouble("last_salary"));
		evaluation.setNouveauSalaire(rs.getDouble("new_salary"));
		evaluation.setNoteGenerale(rs.getDouble("note_generale"));
		evaluation.setNoteEvaluation(rs.getDouble("note_evaluation"));
		evaluation.setIdAnGrade(rs.getInt("id_last_grad"));
		evaluation.setIdNvGrade(rs.getInt("id_new_grad"));
		return evaluation;
	}

	public EvaluationEmployeC getEvaluationEmploye(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EvaluationEmployeC evaluation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.evaluationEmploye) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				evaluation = setEvaluationEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return evaluation;
	}

	public EvaluationEmployeC getEvaluationEmploye(EmployeC employe, int annee) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EvaluationEmployeC evaluation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.evaluationEmploye)
				+ " WHERE id_employe=? and annee_validite=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			stmt.setInt(2, annee);
			rs = stmt.executeQuery();
			if (rs.next()) {
				evaluation = setEvaluationEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return evaluation;
	}

	public boolean deleteEvaluationEmploye(EvaluationEmployeC evaluation) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);
			deleted = insertHistorique(evaluation.getHistorique(), con);
			if (deleted) {
				deleted = deleteAllEvaluationEmployeDetailCritere(evaluation);
			}
			if (deleted) {
				deleted = deleteNotAutocommit(evaluation.getId(),
						Tables.getTableName(Tables.TableName.evaluationEmploye));
			}
			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	private boolean insertEvaluationEmployeDetailCritere(EvaluationEmployeDetailCritereC evaluation, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		evaluation.setId(getId(Tables.getTableName(Tables.TableName.evaluationEmployeDetail)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.evaluationEmployeDetail)
				+ " (id,id_entete,id_detail_critere,note_obtenue,applicable) " + " VALUES (?,?,?,?,?)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, evaluation.getId());
			if (evaluation.getEntete() != null) {

				stmt.setInt(2, evaluation.getEntete().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (evaluation.getDetailCritere() != null) {

				stmt.setInt(3, evaluation.getDetailCritere().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, evaluation.getNoteObtenue());
			stmt.setBoolean(5, evaluation.isApplicable());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateEvaluationEmployeDetailCritere(EvaluationEmployeDetailCritereC evaluation, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.evaluationEmployeDetail)
				+ " SET  id_entete=?,id_detail_critere=?,note_obtenue=?,applicable=?  WHERE id=?";

		try {
			stmt = conn.prepareStatement(sql);
			if (evaluation.getEntete() != null) {

				stmt.setInt(1, evaluation.getEntete().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (evaluation.getDetailCritere() != null) {

				stmt.setInt(2, evaluation.getDetailCritere().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, evaluation.getNoteObtenue());
			stmt.setBoolean(4, evaluation.isApplicable());
			stmt.setInt(5, evaluation.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private EvaluationEmployeDetailCritereC setEvaluationEmployeDetailCritere(ResultSet rs) throws SQLException {
		EvaluationEmployeDetailCritereC evaluation = new EvaluationEmployeDetailCritereC();
		evaluation.setId(rs.getInt("id"));
		if (rs.getObject("id_entete") != null) {
			evaluation.setEntete(getEvaluationEmploye(rs.getInt("id_entete")));
		}
		if (rs.getObject("id_detail_critere") != null) {
			evaluation.setDetailCritere(
					FichierBaseDAO.getInstance().getDetailCritereEvaluation(rs.getInt("id_detail_critere")));
		}
		evaluation.setNoteObtenue(rs.getDouble("note_obtenue"));
		evaluation.setNoteObtenueS(HelperC.TraitementMontant.getMontantFormate(evaluation.getNoteObtenue(), 2));
		evaluation.setApplicable(rs.getBoolean("applicable"));
		return evaluation;
	}

	public EvaluationEmployeDetailCritereC getEvaluationEmployeDetailCritere(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EvaluationEmployeDetailCritereC evaluation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.evaluationEmployeDetail) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				evaluation = setEvaluationEmployeDetailCritere(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return evaluation;
	}

	public boolean deleteAllEvaluationEmployeDetailCritere(EvaluationEmployeC evaluation) {
		boolean deleted = false;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.evaluationEmployeDetail)
				+ " WHERE id_entete=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, evaluation.getId());
			stmt.execute();
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	public boolean deleteEvaluationEmployeDetailCritere(EvaluationEmployeDetailCritereC evaluation, Connection conn) {
		boolean deleted = false;

		try {
			conn.setAutoCommit(false);

			deleted = deleteNotAutocommit(evaluation.getId(),
					Tables.getTableName(Tables.TableName.evaluationEmployeDetail));

			if (deleted) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<EvaluationEmployeDetailCritereC> getListeEvaluationEmployeDetailCritere(EvaluationEmployeC evaluation) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<EvaluationEmployeDetailCritereC> liste = new ArrayList<EvaluationEmployeDetailCritereC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.evaluationEmployeDetail)
				+ " WHERE id_entete=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, evaluation.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				liste.add(setEvaluationEmployeDetailCritere(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertDeuxiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC deuxieme) {
		boolean saved = false;
		PreparedStatement stmt = null;
		deuxieme.setId(getId(Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmploye)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmploye)
				+ " (id,id_premiere_evaluation,date_evaluation,id_type_notation,id_type_appreciation,"
				+ "note_evaluation,justification_discordance,pourcentage,note_generale) "
				+ " VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, deuxieme.getId());
			if (deuxieme.getPremiereEvaluation() != null) {

				stmt.setInt(2, deuxieme.getPremiereEvaluation().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setObject(3, deuxieme.getDateEvaluation());
			if (deuxieme.getTypeNotation() != null) {

				stmt.setInt(4, deuxieme.getTypeNotation().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			if (deuxieme.getTypeAppreciation() != null) {

				stmt.setInt(5, deuxieme.getTypeAppreciation().getId());
			} else {

				stmt.setObject(5, (Object) null);
			}
			stmt.setDouble(6, deuxieme.getNoteEvaluation());
			stmt.setString(7, deuxieme.getJustificationDiscordance());
			stmt.setDouble(8, deuxieme.getPourcentage());
			stmt.setDouble(9, deuxieme.getNoteGenerale());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDeuxiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC deuxieme) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmploye)
				+ " SET  id_premiere_evaluation=?,date_evaluation=?,id_type_notation=?,id_type_appreciation=?,"
				+ " note_evaluation=?,justification_discordance=?,pourcentage=?,note_generale=?" + " WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (deuxieme.getPremiereEvaluation() != null) {

				stmt.setInt(1, deuxieme.getPremiereEvaluation().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setObject(2, deuxieme.getDateEvaluation());
			if (deuxieme.getTypeNotation() != null) {

				stmt.setInt(3, deuxieme.getTypeNotation().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			if (deuxieme.getTypeAppreciation() != null) {

				stmt.setInt(4, deuxieme.getTypeAppreciation().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			stmt.setDouble(5, deuxieme.getNoteEvaluation());
			stmt.setString(6, deuxieme.getJustificationDiscordance());
			stmt.setDouble(7, deuxieme.getPourcentage());
			stmt.setDouble(8, deuxieme.getNoteGenerale());
			stmt.setInt(9, deuxieme.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateDeuxiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC deuxieme) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (deuxieme.getId() == 0) {

				saved = insertDeuxiemeEvaluationEmploye(deuxieme);
				if (!saved) {
					deuxieme.setId(0);
				}
			} else {

				saved = updateDeuxiemeEvaluationEmploye(deuxieme);
			}
			if (saved) {
				for (DeuxiemeEvaluationEmployeDetailCritereC detail : deuxieme.getListDetailDeuxiemeEvaluation()) {

					detail.setEntete(deuxieme);
					if (detail.getId() == 0) {

						saved = insertDeuxiemeEvaluationEmployeDetailCritere(detail);
					} else {

						saved = updateDeuxiemeEvaluationEmployeDetailCritere(detail);
					}
					if (!saved) {
						break;
					}
				}
			}

			if (saved) {
				for (DeuxiemeEvaluationEmployeDetailCritereC detail : deuxieme
						.getListDetailDeuxiemeEvaluationDeleted()) {

					if (detail.getId() > 0) {
						saved = deleteDeuxiemeEvaluationEmployeDetailCritere(detail);
					}
					if (!saved) {
						break;
					}
				}
			}

			if (saved) {

				deuxieme.getHistorique().setIdLigne(deuxieme.getId());
				saved = insertHistorique(deuxieme.getHistorique(), con);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private DeuxiemeEvaluationEmployeC setDeuxiemeEvaluationEmploye(ResultSet rs) throws SQLException {
		DeuxiemeEvaluationEmployeC deuxieme = new DeuxiemeEvaluationEmployeC();
		deuxieme.setId(rs.getInt("id"));
		if (rs.getObject("id_premiere_evaluation") != null) {
			deuxieme.setPremiereEvaluation(getEvaluationEmploye(rs.getInt("id_premiere_evaluation")));
		}
		deuxieme.setDateEvaluation(rs.getDate("date_evaluation"));
		deuxieme.setDateEvaluationS(HelperC.changeDateFormate(deuxieme.getDateEvaluation()));
		if (rs.getObject("id_type_notation") != null) {
			deuxieme.setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(rs.getInt("id_type_notation")));
		}
		if (rs.getObject("id_type_appreciation") != null) {
			deuxieme.setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_type_appreciation"),
					Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
		}
		deuxieme.setNoteEvaluation(rs.getDouble("note_evaluation"));
		deuxieme.setNoteEvaluationS(HelperC.TraitementMontant.getMontantFormate(deuxieme.getNoteEvaluation(), 2));
		deuxieme.setJustificationDiscordance(rs.getString("justification_discordance"));
		deuxieme.setPourcentage(rs.getDouble("pourcentage"));
		deuxieme.setPourcentageS(HelperC.TraitementMontant.getMontantFormate(deuxieme.getPourcentage(), 2));
		deuxieme.setNoteGenerale(rs.getDouble("note_generale"));
		deuxieme.setNoteGeneraleS(HelperC.TraitementMontant.getMontantFormate(deuxieme.getNoteGenerale(), 2));
		return deuxieme;
	}

	public DeuxiemeEvaluationEmployeC getDeuxiemeEvaluationEmploye(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DeuxiemeEvaluationEmployeC deuxieme = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmploye)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				deuxieme = setDeuxiemeEvaluationEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deuxieme;
	}

	public DeuxiemeEvaluationEmployeC getDeuxiemeEvaluationEmploye(EvaluationEmployeC evaluation) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DeuxiemeEvaluationEmployeC deuxieme = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmploye)
				+ " WHERE id_premiere_evaluation=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, evaluation.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				deuxieme = setDeuxiemeEvaluationEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deuxieme;
	}

	public boolean deleteDeuxiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC deuxieme) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);
			deleted = insertHistorique(deuxieme.getHistorique(), con);
			if (deleted) {
				deleted = deleteAllDeuxiemeEvaluationEmployeDetailCritere(deuxieme);
			}
			if (deleted) {
				deleted = deleteNotAutocommit(deuxieme.getId(),
						Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmploye));
			}
			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	private boolean insertDeuxiemeEvaluationEmployeDetailCritere(DeuxiemeEvaluationEmployeDetailCritereC deuxieme) {
		boolean saved = false;
		PreparedStatement stmt = null;
		deuxieme.setId(getId(Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmployeDetailCritere)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmployeDetailCritere)
				+ " (id,id_entete,id_detail_critere,note_obtenue,applicable) " + " VALUES (?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, deuxieme.getId());
			if (deuxieme.getEntete() != null) {

				stmt.setInt(2, deuxieme.getEntete().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (deuxieme.getDetailCritere() != null) {

				stmt.setInt(3, deuxieme.getDetailCritere().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, deuxieme.getNoteObtenue());
			stmt.setBoolean(5, deuxieme.isApplicable());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDeuxiemeEvaluationEmployeDetailCritere(DeuxiemeEvaluationEmployeDetailCritereC deuxieme) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmployeDetailCritere) + " SET  "
				+ " id_entete=?,id_detail_critere=?,note_obtenue=?,applicable=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (deuxieme.getEntete() != null) {

				stmt.setInt(1, deuxieme.getEntete().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (deuxieme.getDetailCritere() != null) {

				stmt.setInt(2, deuxieme.getDetailCritere().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, deuxieme.getNoteObtenue());
			stmt.setBoolean(4, deuxieme.isApplicable());
			stmt.setInt(5, deuxieme.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateDeuxiemeEvaluationEmployeDetailCritere(
			DeuxiemeEvaluationEmployeDetailCritereC deuxieme) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (deuxieme.getId() == 0) {

				saved = insertDeuxiemeEvaluationEmployeDetailCritere(deuxieme);
				if (!saved) {
					deuxieme.setId(0);
				}
			} else {

				saved = updateDeuxiemeEvaluationEmployeDetailCritere(deuxieme);
			}
			if (saved) {

				deuxieme.getHistorique().setIdLigne(deuxieme.getId());
				saved = insertHistorique(deuxieme.getHistorique(), con);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private DeuxiemeEvaluationEmployeDetailCritereC setDeuxiemeEvaluationEmployeDetailCritere(ResultSet rs)
			throws SQLException {
		DeuxiemeEvaluationEmployeDetailCritereC deuxieme = new DeuxiemeEvaluationEmployeDetailCritereC();
		deuxieme.setId(rs.getInt("id"));
		if (rs.getObject("id_entete") != null) {
			deuxieme.setEntete(getDeuxiemeEvaluationEmploye(rs.getInt("id_entete")));
		}
		if (rs.getObject("id_detail_critere") != null) {
			deuxieme.setDetailCritere(getEvaluationEmployeDetailCritere(rs.getInt("id_detail_critere")));
		}
		deuxieme.setNoteObtenue(rs.getDouble("note_obtenue"));
		deuxieme.setNoteObtenueS(HelperC.TraitementMontant.getMontantFormate(deuxieme.getNoteObtenue(), 2));
		deuxieme.setApplicable(rs.getBoolean("applicable"));
		return deuxieme;
	}

	public DeuxiemeEvaluationEmployeDetailCritereC getDeuxiemeEvaluationEmployeDetailCritere(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DeuxiemeEvaluationEmployeDetailCritereC deuxieme = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmployeDetailCritere)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				deuxieme = setDeuxiemeEvaluationEmployeDetailCritere(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return deuxieme;
	}

	public boolean deleteAllDeuxiemeEvaluationEmployeDetailCritere(DeuxiemeEvaluationEmployeC evaluation) {
		boolean deleted = false;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmployeDetailCritere)
				+ " WHERE id_entete=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, evaluation.getId());
			stmt.execute();
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	public boolean deleteDeuxiemeEvaluationEmployeDetailCritere(DeuxiemeEvaluationEmployeDetailCritereC deuxieme) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);
			deleted = insertHistorique(deuxieme.getHistorique(), con);
			if (deleted) {
				deleted = deleteNotAutocommit(deuxieme.getId(),
						Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmployeDetailCritere));
			}
			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<DeuxiemeEvaluationEmployeDetailCritereC> getListeDeuxiemeEvaluationEmployeDetailCritere(
			DeuxiemeEvaluationEmployeC evaluation) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DeuxiemeEvaluationEmployeDetailCritereC> liste = new ArrayList<DeuxiemeEvaluationEmployeDetailCritereC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmployeDetailCritere)
				+ " WHERE id_entete=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, evaluation.getId());
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setDeuxiemeEvaluationEmployeDetailCritere(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertTroisiemeEvaluationEmploye(TroisiemeEvaluationEmployeC troisieme, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		troisieme.setId(getId(Tables.getTableName(Tables.TableName.troisiemeEvaluationEmploye)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.troisiemeEvaluationEmploye)
				+ " (id,id_deuxieme_evaluation,date_evaluation,id_type_notation,id_type_appreciation,id_grade,"
				+ "id_nouv_grade,salaire,nouv_salaire,taux_grd,taux_cot) " +

				" VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, troisieme.getId());
			if (troisieme.getDeuxiemeEvaluation() != null) {

				stmt.setInt(2, troisieme.getDeuxiemeEvaluation().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setObject(3, troisieme.getDateEvaluation());
			if (troisieme.getTypeNotation() != null) {

				stmt.setInt(4, troisieme.getTypeNotation().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			if (troisieme.getTypeAppreciation() != null) {

				stmt.setInt(5, troisieme.getTypeAppreciation().getId());
			} else {

				stmt.setObject(5, (Object) null);
			}

			stmt.setInt(6, troisieme.getIdAncGrd());
			if (troisieme.getIdNvGrd() > 0)
				stmt.setInt(7, troisieme.getIdNvGrd());
			else
				stmt.setObject(7, (Object) null);

			stmt.setDouble(8, troisieme.getAncienSalary());
			stmt.setDouble(9, troisieme.getNouveauSalaire());
			stmt.setDouble(10, troisieme.getTauxAv());
			stmt.setDouble(11, troisieme.getTauxCot());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateTroisiemeEvaluationEmploye(TroisiemeEvaluationEmployeC troisieme, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.troisiemeEvaluationEmploye)
				+ " SET  id_deuxieme_evaluation=?,date_evaluation=?,id_type_notation=?,id_type_appreciation=?,"
				+ "id_grade=?,id_nouv_grade=?,salaire=?,nouv_salaire=?,taux_grd=?,taux_cot=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);
			if (troisieme.getDeuxiemeEvaluation() != null) {

				stmt.setInt(1, troisieme.getDeuxiemeEvaluation().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setObject(2, troisieme.getDateEvaluation());
			if (troisieme.getTypeNotation() != null) {

				stmt.setInt(3, troisieme.getTypeNotation().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			if (troisieme.getTypeAppreciation() != null) {

				stmt.setInt(4, troisieme.getTypeAppreciation().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}

			stmt.setInt(5, troisieme.getIdAncGrd());
			if (troisieme.getIdNvGrd() > 0)
				stmt.setInt(6, troisieme.getIdNvGrd());
			else
				stmt.setObject(6, (Object) null);

			stmt.setDouble(7, troisieme.getAncienSalary());
			stmt.setDouble(8, troisieme.getNouveauSalaire());
			stmt.setDouble(8, troisieme.getTauxAv());
			stmt.setDouble(9, troisieme.getTauxCot());

			stmt.setInt(10, troisieme.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateTroisiemeEvaluationEmploye(TroisiemeEvaluationEmployeC troisieme) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			if (troisieme.getId() == 0) {

				saved = insertTroisiemeEvaluationEmploye(troisieme, conn);

				if (!saved) {
					troisieme.setId(0);
				}
			} else {

				saved = updateTroisiemeEvaluationEmploye(troisieme, conn);
			}
			if (saved) {
				troisieme.getTraitement().setIdRef(troisieme.getId());

				if (troisieme.getTraitement().getId() == 0)
					saved = insertTraitementSalarial(troisieme.getTraitement(), conn);
				else
					saved = updateTraitementSalarial(troisieme.getTraitement(), conn);
			}

			if (saved) {
				if (troisieme.getNouvelgrd() != null && troisieme.getNouvelgrd().getId() == 0)
					saved = insertDetailGrade(troisieme.getNouvelgrd(), conn);
			}
			if (saved) {

				troisieme.getHistorique().setIdLigne(troisieme.getId());
				saved = insertHistorique(troisieme.getHistorique(), conn);
			}

			if (saved) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private TroisiemeEvaluationEmployeC setTroisiemeEvaluationEmploye(ResultSet rs) throws SQLException {
		TroisiemeEvaluationEmployeC troisieme = new TroisiemeEvaluationEmployeC();
		troisieme.setId(rs.getInt("id"));
		if (rs.getObject("id_deuxieme_evaluation") != null) {
			troisieme.setDeuxiemeEvaluation(getDeuxiemeEvaluationEmploye(rs.getInt("id_deuxieme_evaluation")));
		}
		troisieme.setDateEvaluation(rs.getDate("date_evaluation"));
		troisieme.setDateEvaluationS(HelperC.changeDateFormate(troisieme.getDateEvaluation()));
		if (rs.getObject("id_type_notation") != null) {
			troisieme.setTypeNotation(FichierBaseDAO.getInstance().getTypeNotation(rs.getInt("id_type_notation")));
		}
		if (rs.getObject("id_type_appreciation") != null) {
			troisieme.setTypeAppreciation(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_type_appreciation"),
					Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
		}
		troisieme.setAncienSalary(rs.getDouble("salaire"));
		troisieme.setNouveauSalaire(rs.getDouble("nouv_salaire"));
		troisieme.setTauxAv(rs.getDouble("taux_grd"));
		troisieme.setTauxCot(rs.getDouble("taux_cot"));
		troisieme.setIdNvGrd(rs.getInt("id_grade"));
		troisieme.setIdAncGrd(rs.getInt("id_nouv_grade"));
		return troisieme;
	}

	public List<TroisiemeEvaluationEmployeC> getListTroisiemeEvaluationEmploye(int idEmpl) {
		Statement stmt = null;
		ResultSet rs = null;
		List<TroisiemeEvaluationEmployeC> list = new ArrayList<TroisiemeEvaluationEmployeC>();
		String sql = "SELECT A.* FROM " + Tables.getTableName(Tables.TableName.troisiemeEvaluationEmploye) + " AS A "
				+ "LEFT JOIN " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmploye) + " AS B "
				+ "ON B.id=A.id_deuxieme_evaluation LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.evaluationEmploye) + " AS C "
				+ "ON C.id=B.id_premiere_evaluation WHERE C.id_employe=" + idEmpl + " AND A.id_nouv_grade IS NULL";

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(setTroisiemeEvaluationEmploye(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return list;
	}

	public List<EvaluationEmployeC> getListEvaluationEmploye(int idEmpl) {
		Statement stmt = null;
		ResultSet rs = null;
		List<EvaluationEmployeC> list = new ArrayList<EvaluationEmployeC>();
		String sql = "SELECT A.* FROM " + Tables.getTableName(Tables.TableName.troisiemeEvaluationEmploye) + " AS A "
				+ "LEFT JOIN " + Tables.getTableName(Tables.TableName.deuxiemeEvaluationEmploye) + " AS B "
				+ "ON B.id=A.id_deuxieme_evaluation LEFT JOIN "
				+ Tables.getTableName(Tables.TableName.evaluationEmploye) + " AS C "
				+ "ON C.id=B.id_premiere_evaluation WHERE C.id_employe=" + idEmpl + " AND A.id_nouv_grade IS NULL";

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(setEvaluationEmploye(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return list;
	}

	public int getNombreCotation(int idEmpl, int notation, int appreciation) {
		Statement stmt = null;
		ResultSet rs = null;
		int nb = 0;

		String sql = "SELECT COUNT(id) AS nombre FROM "+ Tables.getTableName(Tables.TableName.evaluationEmploye) + 
				      " WHERE id_employe=" + idEmpl + " AND id_nouv_grade =0 ";
		if(notation>0)
				sql+= " AND id_type_notation=" + notation;
		if(appreciation>0)
				sql+=" AND id_type_appreciation=" + appreciation;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getObject("nombre") != null)
					nb = rs.getInt("nombre");

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return nb;
	}

	public TroisiemeEvaluationEmployeC getTroisiemeEvaluationEmploye(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TroisiemeEvaluationEmployeC troisieme = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.troisiemeEvaluationEmploye)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				troisieme = setTroisiemeEvaluationEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return troisieme;
	}

	public TroisiemeEvaluationEmployeC getTroisiemeEvaluationEmploye(DeuxiemeEvaluationEmployeC evaluation) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TroisiemeEvaluationEmployeC troisieme = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.troisiemeEvaluationEmploye)
				+ " WHERE id_deuxieme_evaluation=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, evaluation.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				troisieme = setTroisiemeEvaluationEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return troisieme;
	}

	public boolean deleteTroisiemeEvaluationEmploye(TroisiemeEvaluationEmployeC troisieme) {
		boolean deleted = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			deleted = insertHistorique(troisieme.getHistorique(), conn);
			if (deleted) {
				if (troisieme.getTraitement() != null)
					deleteTraitement(troisieme.getTraitement().getEmploye().getId(), troisieme.getId(),
							Constante.getTypeAvancement(Constante.TypeAvancement.avancementTraitement), conn);
				if (troisieme.getNouvelgrd() != null)
					deleteGrade(troisieme.getNouvelgrd().getIdEmpl(), troisieme.getId(), troisieme.getIdNvGrd(), conn);
				deleted = delete(troisieme.getId(), Tables.getTableName(Tables.TableName.troisiemeEvaluationEmploye),
						conn);
			}
			if (deleted) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public boolean deleteGrade(int idEmpl, int idRef, int idGrd, Connection conn) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.detailGradeEmploye) + " WHERE id_employe"
				+ idEmpl + " AND ref_motif=" + idRef + " id_grade=" + idGrd;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		releaseResource(stmt, null);

		return deleted;
	}

	public boolean deleteTraitement(int idEmpl, int idRef, int typeAvc, Connection conn) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.traitementSalarial) + " WHERE id_employe="
				+ idEmpl + " AND ref_avancement=" + idRef + " AND type_avancement=" + typeAvc;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		releaseResource(stmt, null);

		return deleted;
	}

	private boolean insertTraitementSalarial(TraitementSalarialC traitement, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		traitement.setId(getId(Tables.getTableName(Tables.TableName.traitementSalarial)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.traitementSalarial)
				+ " (id,id_employe,salaire_base,pourcentage,type_avancement,date_debut,"
				+ "ancien_salaire,date_fin,commentaire,ref_avancement)  VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, traitement.getId());
			if (traitement.getEmploye() != null) {

				stmt.setInt(2, traitement.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, traitement.getSalaireBase());
			stmt.setDouble(4, traitement.getPourcentage());
			stmt.setInt(5, traitement.getTypeAvancement());
			stmt.setObject(6, traitement.getDateDebut());
			stmt.setDouble(7, traitement.getAncienSalaire());
			stmt.setObject(8, traitement.getDateFin());
			stmt.setObject(9, traitement.getComment());
			stmt.setInt(10, traitement.getIdRef());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateTraitementSalarial(TraitementSalarialC traitement, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.traitementSalarial)
				+ " SET  id_employe=?,salaire_base=?,pourcentage=?,type_avancement=?,date_debut=?,"
				+ " ancien_salaire=?,date_fin=?,commentaire=?,ref_avancement=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (traitement.getEmploye() != null) {

				stmt.setInt(1, traitement.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setDouble(2, traitement.getSalaireBase());
			stmt.setDouble(3, traitement.getPourcentage());
			stmt.setInt(4, traitement.getTypeAvancement());
			stmt.setObject(5, traitement.getDateDebut());
			stmt.setDouble(6, traitement.getAncienSalaire());
			stmt.setObject(7, traitement.getDateFin());
			stmt.setObject(8, traitement.getComment());
			stmt.setInt(9, traitement.getIdRef());
			stmt.setInt(10, traitement.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateTraitementSalarial(TraitementSalarialC traitement) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			if (traitement.getId() == 0) {

				saved = insertTraitementSalarial(traitement, conn);
				if (!saved) {
					traitement.setId(0);
				}
			} else {

				saved = updateTraitementSalarial(traitement, conn);
			}
			if (saved) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private TraitementSalarialC setTraitementSalarial(ResultSet rs) throws SQLException {
		TraitementSalarialC traitement = new TraitementSalarialC();
		traitement.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			traitement.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		}
		traitement.setSalaireBase(rs.getDouble("salaire_base"));
		traitement.setSalaireBaseS(HelperC.TraitementMontant.getMontantFormate(traitement.getSalaireBase(), 2));
		traitement.setPourcentage(rs.getDouble("pourcentage"));
		traitement.setPourcentageS(HelperC.TraitementMontant.getMontantFormate(traitement.getPourcentage(), 2));
		traitement.setTypeAvancement(rs.getInt("type_avancement"));
		traitement.setDateDebut(rs.getDate("date_debut"));
		traitement.setDateDebutS(HelperC.changeDateFormate(traitement.getDateDebut()));
		traitement.setAncienSalaire(rs.getDouble("ancien_salaire"));
		traitement.setAncienSalaireS(HelperC.TraitementMontant.getMontantFormate(traitement.getAncienSalaire(), 2));
		if (rs.getObject("date_fin") != null) {
			traitement.setDateFin(rs.getDate("date_fin"));
		}
		traitement.setComment(rs.getString("commentaire"));
		traitement.setIdRef(rs.getInt("ref_avancement"));
		return traitement;
	}

	public TraitementSalarialC getTraitementSalarial(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TraitementSalarialC traitement = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.traitementSalarial) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				traitement = setTraitementSalarial(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return traitement;
	}

	public TraitementSalarialC getTraitementSalarial(EmployeC employe) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TraitementSalarialC traitement = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.traitementSalarial)
				+ " WHERE id_employe=?  ORDER BY id DESC ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				traitement = setTraitementSalarial(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return traitement;
	}

	public TraitementSalarialC getSalaireActuel(EmployeC employe, Date dateJrs) {
		Statement stmt = null;
		ResultSet rs = null;
		TraitementSalarialC traitement = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.traitementSalarial) + " WHERE id_employe="
				+ employe.getId() + " AND date_debut<='" + HelperC.convertDate(dateJrs, false) + "'"
				+ " ORDER BY id DESC LIMIT 1";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				traitement = setTraitementSalarial(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return traitement;
	}

	public TraitementSalarialC getTraitementSalarial(int idEmploye, int avancement, int idRef) {
		Statement stmt = null;
		ResultSet rs = null;
		TraitementSalarialC traitement = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.traitementSalarial) + " WHERE id_employe="
				+ idEmploye + " AND type_avancement=" + avancement + " AND ref_avancement=" + idRef;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				traitement = setTraitementSalarial(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return traitement;
	}

	public boolean deleteTraitementSalarial(TraitementSalarialC traitement) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);
			deleted = insertHistorique(traitement.getHistorique(), con);
			if (deleted) {
				deleted = deleteNotAutocommit(traitement.getId(),
						Tables.getTableName(Tables.TableName.traitementSalarial));
			}
			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	private boolean insertDemandeAvancementGrade(DemandeAvancementGradeC demande) {
		boolean saved = false;
		PreparedStatement stmt = null;
		demande.setId(getId(Tables.getTableName(Tables.TableName.demandeAvancementGrade)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.demandeAvancementGrade)
				+ " (id,id_employe,date_demande,etat,id_grade_demande,motif,id_niveau_formation) "
				+ " VALUES (?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, demande.getId());
			if (demande.getEmploye() != null) {

				stmt.setInt(2, demande.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setObject(3, demande.getDateDemande());
			stmt.setInt(4, demande.getEtat());
			if (demande.getGradeDemande() != null) {

				stmt.setInt(5, demande.getGradeDemande().getId());
			} else {

				stmt.setObject(5, (Object) null);
			}
			stmt.setString(6, demande.getMotif());
			if (demande.getNiveauFormation() != null) {

				stmt.setInt(7, demande.getNiveauFormation().getId());
			} else {

				stmt.setObject(7, (Object) null);
			}
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDemandeAvancementGrade(DemandeAvancementGradeC demande) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.demandeAvancementGrade)
				+ " SET  id_employe=?,date_demande=?,etat=?,id_grade_demande=?,motif=?,id_niveau_formation=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (demande.getEmploye() != null) {

				stmt.setInt(1, demande.getEmploye().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setObject(2, demande.getDateDemande());
			stmt.setInt(3, demande.getEtat());
			if (demande.getGradeDemande() != null) {

				stmt.setInt(4, demande.getGradeDemande().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			stmt.setString(5, demande.getMotif());
			if (demande.getNiveauFormation() != null) {

				stmt.setInt(6, demande.getNiveauFormation().getId());
			} else {

				stmt.setObject(6, (Object) null);
			}
			stmt.setInt(7, demande.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean updateEtatDemandeAvancementGrade(DemandeAvancementGradeC demande) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.demandeAvancementGrade)
				+ " SET etat=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, demande.getEtat());
			stmt.setInt(2, demande.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateDemandeAvancementGrade(DemandeAvancementGradeC demande) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (demande.getId() == 0) {

				saved = insertDemandeAvancementGrade(demande);
				if (!saved) {
					demande.setId(0);
				}
			} else {

				saved = updateDemandeAvancementGrade(demande);
			}

			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private DemandeAvancementGradeC setDemandeAvancementGrade(ResultSet rs) throws SQLException {
		DemandeAvancementGradeC demande = new DemandeAvancementGradeC();
		demande.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			demande.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		}
		demande.setDateDemande(rs.getDate("date_demande"));
		demande.setEtat(rs.getInt("etat"));
		if (rs.getObject("id_grade_demande") != null) {
			demande.setGradeDemande(FichierBaseDAO.getInstance().getGradePersonnel(rs.getInt("id_grade_demande")));
		}
		demande.setMotif(rs.getString("motif"));
		if (rs.getObject("id_niveau_formation") != null) {
			demande.setNiveauFormation(FichierBaseDAO.getInstance().getBaseById(rs.getInt("id_niveau_formation"),
					Tables.getTableName(Tables.TableName.niveauFormation)));
		}
		return demande;
	}

	public DemandeAvancementGradeC getDemandeAvancementGrade(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DemandeAvancementGradeC demande = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeAvancementGrade) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				demande = setDemandeAvancementGrade(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return demande;
	}

	public boolean deleteDemandeAvancementGrade(DemandeAvancementGradeC demande) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(demande.getId(),
					Tables.getTableName(Tables.TableName.demandeAvancementGrade));

			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<DemandeAvancementGradeC> getListeDemandeAvancementGrade(int etat, int idEmploye) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DemandeAvancementGradeC> ParamAvancement = new ArrayList<DemandeAvancementGradeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeAvancementGrade) + " where etat="
				+ etat;
		if (idEmploye > 0) {
			sql = String.valueOf(sql) + " AND id_employe=" + idEmploye;
		}

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); ParamAvancement.add(setDemandeAvancementGrade(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return ParamAvancement;
	}

	public boolean deletePrevision(int idCredit) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.creditPrevisionRemboursement)
				+ " WHERE id_detail_credit=" + idCredit;

		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.commit();
			deleted = true;

		} catch (SQLException e) {

			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	public boolean insertPaieAnormal(PaieAnormalC pan) {
		boolean saved = false;
		PreparedStatement stmt = null;
		pan.setId(getId(Tables.getTableName(Tables.TableName.paieAnormal)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.paieAnormal)
				+ " (id,id_employe,date_paie,montant,id_exercice) VALUES (?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, pan.getId());
			stmt.setInt(2, pan.getIdEmploye());
			stmt.setObject(3, pan.getDatePaie());
			stmt.setDouble(4, pan.getMontantPaie());
			stmt.setInt(5, pan.getIdExercice());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public List<PaieAnormalC> getListePaieAnormal(Date date) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PaieAnormalC> listPaieANormal = new ArrayList<PaieAnormalC>();
		PaieAnormalC pan = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.paieAnormal) + " where date_paie='"
				+ HelperC.convertDate(date, false) + "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				pan = new PaieAnormalC();
				pan.setId(rs.getInt("id"));
				pan.setIdEmploye(rs.getInt("id_employe"));
				pan.setDatePaie(rs.getDate("date_paie"));
				pan.setMontantPaie(rs.getDouble("montant"));
				listPaieANormal.add(pan);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listPaieANormal;
	}

	private boolean deletePaieAnormal(Date datePaie, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.paieAnormal) + " where date_paie='"
				+ HelperC.convertDate(datePaie, false) + "'";

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean insertSaisiePositionDetailPrime(SaisiePositionDetailPrimeC saisie, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		saisie.setId(getId(Tables.getTableName(Tables.TableName.saisiePositionDetailPrime)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisiePositionDetailPrime)
				+ " (id,id_saisie,id_prime,montant,id_param_prime,garder) " + " VALUES (?,?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, saisie.getId());
			if (saisie.getSaisie() != null) {

				stmt.setInt(2, saisie.getSaisie().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (saisie.getPrime() != null) {

				stmt.setInt(3, saisie.getPrime().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, saisie.getMontant());
			if (saisie.getIdParm() > 0)
				stmt.setInt(5, saisie.getIdParm());
			else
				stmt.setObject(5, (Object) null);

			stmt.setBoolean(6, saisie.isKept());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateSaisiePositionDetailPrime(SaisiePositionDetailPrimeC saisie, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisiePositionDetailPrime)
				+ " SET  id_prime=?,montant=?,id_param_prime=?,garder=?  WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);

			if (saisie.getPrime() != null) {

				stmt.setInt(1, saisie.getPrime().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setDouble(2, saisie.getMontant());

			if (saisie.getIdParm() > 0)
				stmt.setInt(3, saisie.getIdParm());
			else
				stmt.setObject(3, (Object) null);
			stmt.setBoolean(4, saisie.isKept());
			stmt.setInt(5, saisie.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private SaisiePositionDetailPrimeC setSaisiePositionDetailPrime(ResultSet rs) throws SQLException {
		SaisiePositionDetailPrimeC saisie = new SaisiePositionDetailPrimeC();
		saisie.setId(rs.getInt("id"));
		if (rs.getObject("id_saisie") != null) {
			saisie.setSaisie(getSaisiePositionEmploye(rs.getInt("id_saisie")));
		}
		if (rs.getObject("id_prime") != null) {
			saisie.setPrime(FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getInt("id_prime")));
		}
		saisie.setMontant(rs.getDouble("montant"));
		saisie.setIdParm(rs.getInt("id_param_prime"));
		saisie.setKept(rs.getBoolean("garder"));
		return saisie;
	}

	public SaisiePositionDetailPrimeC getSaisiePositionDetailPrime(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisiePositionDetailPrimeC saisie = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionDetailPrime)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisiePositionDetailPrime(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return saisie;
	}

	public boolean deleteSaisiePositionDetailPrime(SaisiePositionDetailPrimeC saisie) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(saisie.getId(),
					Tables.getTableName(Tables.TableName.saisiePositionDetailPrime));

			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<SaisiePositionDetailPrimeC> getListeSaisiePositionDetailPrime(SaisiePositionEmployeC saisie) {
		List<SaisiePositionDetailPrimeC> detai = new ArrayList<SaisiePositionDetailPrimeC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionDetailPrime)
					+ " WHERE  id_saisie=" + saisie.getId();
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setSaisiePositionDetailPrime(rs));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	private boolean insertSaisiePositionDetailIndemnite(SaisiePositionDetailIndemniteC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		saisie.setId(getId(Tables.getTableName(Tables.TableName.saisiePositionDetailIndemnite)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisiePositionDetailIndemnite)
				+ " (id,id_saisie,id_indemnite,montant) VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, saisie.getId());
			if (saisie.getSaisie() != null) {

				stmt.setInt(2, saisie.getSaisie().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (saisie.getIndemnite() != null) {

				stmt.setInt(3, saisie.getIndemnite().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, saisie.getMontant());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateSaisiePositionDetailIndemnite(SaisiePositionDetailIndemniteC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisiePositionDetailIndemnite) + " SET  "
				+ " id_saisie=?,id_indemnite=?,montant=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (saisie.getSaisie() != null) {

				stmt.setInt(1, saisie.getSaisie().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (saisie.getIndemnite() != null) {

				stmt.setInt(2, saisie.getIndemnite().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, saisie.getMontant());
			stmt.setInt(4, saisie.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateSaisiePositionDetailIndemnite(SaisiePositionDetailIndemniteC saisie) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (saisie.getId() == 0) {

				saved = insertSaisiePositionDetailIndemnite(saisie);
				if (!saved) {
					saisie.setId(0);
				}
			} else {

				saved = updateSaisiePositionDetailIndemnite(saisie);
			}

			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private SaisiePositionDetailIndemniteC setSaisiePositionDetailIndemnite(ResultSet rs) throws SQLException {
		SaisiePositionDetailIndemniteC saisie = new SaisiePositionDetailIndemniteC();
		saisie.setId(rs.getInt("id"));
		if (rs.getObject("id_saisie") != null) {
			saisie.setSaisie(getSaisiePositionEmploye(rs.getInt("id_saisie")));
		}
		if (rs.getObject("id_indemnite") != null) {
			saisie.setIndemnite(FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getInt("id_indemnite")));
		}
		saisie.setMontant(rs.getDouble("montant"));
		return saisie;
	}

	public SaisiePositionDetailIndemniteC getSaisiePositionDetailIndemnite(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisiePositionDetailIndemniteC saisie = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionDetailIndemnite)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisiePositionDetailIndemnite(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saisie;
	}

	public boolean deleteSaisiePositionDetailIndemnite(SaisiePositionDetailIndemniteC saisie) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(saisie.getId(),
					Tables.getTableName(Tables.TableName.saisiePositionDetailIndemnite));

			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<SaisiePositionDetailIndemniteC> getListeSaisiePositionDetailIndemnite(SaisiePositionEmployeC saisie) {
		List<SaisiePositionDetailIndemniteC> detai = new ArrayList<SaisiePositionDetailIndemniteC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionDetailIndemnite) + "  "
					+ "WHERE  id_saisie='" + saisie.getId() + "'";
			rs = stmt.executeQuery(sqlRequest);
			for (; rs.next(); detai.add(setSaisiePositionDetailIndemnite(rs)))
				;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	private boolean insertSaisiePositionEmploye(SaisiePositionEmployeC saisie, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		saisie.setId(getId(Tables.getTableName(Tables.TableName.saisiePositionEmploye)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisiePositionEmploye)
				+ " (id, id_employe, id_condition, date_demande, date_debut, date_fin, etat_position,"
				+ " motif_demande, ajout_allocation_familiale, ajout_indemnite_logement, decision,"
				+ " motif_refus, avancement_salaire, avancement_grade, ancien_salaire, taux_encours,"
				+ "nouveau_salaire,inLine,id_persnl,id_categ,id_grd,id_fonction) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setInt(1, saisie.getId());

			if (saisie.getEmploye() != null) {

				stmt.setInt(2, saisie.getEmploye().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (saisie.getConditionPosition() != null) {

				stmt.setInt(3, saisie.getConditionPosition().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setObject(4, saisie.getDateDemandePosition());
			stmt.setObject(5, saisie.getDateDebut());
			stmt.setObject(6, saisie.getDateFin());
			stmt.setInt(7, saisie.getEtat());

			stmt.setString(8, saisie.getMotifDemande());
			stmt.setBoolean(9, saisie.isAjoutAllocationFamiliale());
			stmt.setBoolean(10, saisie.isAjoutIndemniteLogement());

			stmt.setInt(11, saisie.getDecision());
			stmt.setString(12, saisie.getMotifRefus());
			stmt.setBoolean(13, saisie.isAvancementTraitement());
			stmt.setBoolean(14, saisie.isAvancementGrade());
			stmt.setDouble(15, saisie.getAncienSalaire());
			stmt.setDouble(16, saisie.getTaux());
			stmt.setDouble(17, saisie.getNouveauSalaire());
			stmt.setBoolean(18, saisie.isInLine());

			if (saisie.getIdPrs() > 0) {
				stmt.setInt(19, saisie.getIdPrs());
			} else {

				stmt.setObject(19, (Object) null);
			}

			if (saisie.getIdCtg() > 0) {
				stmt.setInt(20, saisie.getIdCtg());
			} else {

				stmt.setObject(20, (Object) null);
			}

			if (saisie.getIdGrd() > 0) {
				stmt.setInt(21, saisie.getIdGrd());
			} else {

				stmt.setObject(21, (Object) null);
			}

			if (saisie.getIdFx() > 0) {
				stmt.setInt(22, saisie.getIdFx());
			} else {

				stmt.setObject(22, (Object) null);
			}

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateSaisiePositionEmploye(SaisiePositionEmployeC saisie, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisiePositionEmploye)
				+ " SET  id_condition=?, date_demande=?, date_debut=?, date_fin=?, etat_position=?,"
				+ " motif_demande=?, ajout_allocation_familiale=?, ajout_indemnite_logement=?, decision=?,"
				+ " motif_refus=?, avancement_salaire=?, avancement_grade=?, ancien_salaire=?, taux_encours=?,"
				+ " nouveau_salaire=?,date_decision=?,id_persnl=?,id_categ=?,id_grd=?,id_fonction=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);

			if (saisie.getConditionPosition() != null) {

				stmt.setInt(1, saisie.getConditionPosition().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setObject(2, saisie.getDateDemandePosition());
			stmt.setObject(3, saisie.getDateDebut());
			stmt.setObject(4, saisie.getDateFin());
			stmt.setInt(5, saisie.getEtat());
			stmt.setString(6, saisie.getMotifDemande());
			stmt.setBoolean(7, saisie.isAjoutAllocationFamiliale());
			stmt.setBoolean(8, saisie.isAjoutIndemniteLogement());
			stmt.setInt(9, saisie.getDecision());
			stmt.setString(10, saisie.getMotifRefus());
			stmt.setBoolean(11, saisie.isAvancementTraitement());
			stmt.setBoolean(12, saisie.isAvancementGrade());
			stmt.setDouble(13, saisie.getAncienSalaire());
			stmt.setDouble(14, saisie.getTaux());
			stmt.setDouble(15, saisie.getNouveauSalaire());
			stmt.setObject(16, saisie.getDateDecision());

			if (saisie.getIdPrs() > 0) {
				stmt.setInt(17, saisie.getIdPrs());
			} else {

				stmt.setObject(17, (Object) null);
			}

			if (saisie.getIdCtg() > 0) {
				stmt.setInt(18, saisie.getIdCtg());
			} else {

				stmt.setObject(18, (Object) null);
			}

			if (saisie.getIdFx() > 0) {
				stmt.setInt(19, saisie.getIdGrd());
			} else {

				stmt.setObject(19, (Object) null);
			}
			if (saisie.getIdFx() > 0) {
				stmt.setInt(20, saisie.getIdFx());
			} else {

				stmt.setObject(20, (Object) null);
			}
			stmt.setInt(21, saisie.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateSaisiePositionEmploye(SaisiePositionEmployeC saisie) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (saisie.getId() == 0) {

				saved = insertSaisiePositionEmploye(saisie, conn);
				if (!saved) {
					saisie.setId(0);
				}
			} else {

				saved = updateSaisiePositionEmploye(saisie, conn);
			}
			if (saved) {

				for (SaisiePositionDetailPrimeC detai1 : saisie.getListeDetailPrime()) {

					detai1.setSaisie(saisie);
					if (detai1.getId() == 0) {

						saved = insertSaisiePositionDetailPrime(detai1, conn);

					} else
						saved = updateSaisiePositionDetailPrime(detai1, conn);
				}

				for (SaisiePositionDetailPrimeC detai1 : saisie.getListeDetailPrimeDeleted()) {
					saved = delete(detai1.getId(), Tables.getTableName(Tables.TableName.saisiePositionDetailPrime));
				}

				if (saisie.getDecision() == 1
						&& saisie.getEtat() == Constante.getEtatPosition(Constante.EtatPosition.decide))
					for (SaisiePositionDetailPrimeC detai1 : saisie.getListeDetailPrime()) {

						if (!detai1.isKept())
							saved = updatePrimeEmploye(saisie.getEmploye().getId(), detai1.getPrime().getId(), true,
									conn);
						else
							saved = updatePrimeEmploye(saisie.getEmploye().getId(), detai1.getPrime().getId(), false,
									conn);
					}
			}

			if (saved) {
				if (saisie.getDecision() > 0 && saisie.getTraitement() != null) {
					saisie.getTraitement().setIdRef(saisie.getId());

					if (saisie.getTraitement().getId() == 0)
						saved = insertTraitementSalarial(saisie.getTraitement(), conn);
					else
						saved = updateTraitementSalarial(saisie.getTraitement(), conn);
				}
			}

			if (saved) {

				saisie.getHistorique().setIdLigne(saisie.getId());
				saved = insertHistorique(saisie.getHistorique(), conn);
			}
			if (saved)
				if (saisie.getDecision() == 1
						&& saisie.getEtat() == Constante.getEtatPosition(Constante.EtatPosition.decide)) {
					saved = updateCarriereEmploye(saisie, conn);

				}

			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
		return saved;
	}

	private SaisiePositionEmployeC setSaisiePositionEmploye(ResultSet rs) throws SQLException {
		SaisiePositionEmployeC saisie = new SaisiePositionEmployeC();
		saisie.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null) {
			saisie.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		}
		if (saisie.getEmploye() != null) {
			saisie.setTraitement(getTraitementSalarial(saisie.getEmploye()));
		}
		if (rs.getObject("id_condition") != null) {
			saisie.setConditionPosition(FichierBaseDAO.getInstance().getConditionPosition(rs.getInt("id_condition")));
		}
		saisie.setDateDemandePosition(rs.getDate("date_demande"));
		saisie.setDateDemandePositionS(HelperC.changeDateFormate(saisie.getDateDemandePosition()));
		saisie.setDateDebut(rs.getDate("date_debut"));
		saisie.setDateDebutS(HelperC.changeDateFormate(saisie.getDateDebut()));
		if (rs.getObject("date_fin") != null) {
			saisie.setDateFin(rs.getDate("date_fin"));
			saisie.setDateFinS(HelperC.changeDateFormate(saisie.getDateFin()));
		}
		saisie.setEtat(rs.getInt("etat_position"));
		saisie.setDecision(rs.getInt("decision"));
		saisie.setMotifDemande(rs.getString("motif_demande"));
		saisie.setAjoutAllocationFamiliale(rs.getBoolean("ajout_allocation_familiale"));
		saisie.setAjoutIndemniteLogement(rs.getBoolean("ajout_indemnite_logement"));
		if (rs.getObject("date_decision") != null)
			saisie.setDateDecision(rs.getDate("date_decision"));

		saisie.setMotifRefus(rs.getString("motif_refus"));
		saisie.setAvancementTraitement(rs.getBoolean("avancement_salaire"));
		saisie.setAvancementGrade(rs.getBoolean("avancement_grade"));
		saisie.setAncienSalaire(rs.getDouble("ancien_salaire"));
		saisie.setNouveauSalaire(rs.getDouble("nouveau_salaire"));
		saisie.setInLine(rs.getBoolean("inLine"));

		if (rs.getObject("id_persnl") != null)
			saisie.setIdPrs(rs.getInt("id_persnl"));

		if (rs.getObject("id_categ") != null)
			saisie.setIdCtg(rs.getInt("id_categ"));

		if (rs.getObject("id_grd") != null)
			saisie.setIdGrd(rs.getInt("id_grd"));

		if (rs.getObject("id_fonction") != null)
			saisie.setIdFx(rs.getInt("id_fonction"));

		return saisie;
	}

	public SaisiePositionEmployeC getSaisiePositionEmploye(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisiePositionEmployeC saisie = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionEmploye) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisiePositionEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return saisie;
	}

	public SaisiePositionEmployeC getSaisiePositionEmploye(EmployeC employe, ConditionPositionC condi, Date dateDemande,
			Constante.EtatPosition etatPosi) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisiePositionEmployeC saisie = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionEmploye)
				+ " WHERE id_employe=? AND id_condition_position=? AND date_demande_position=? AND etat_position=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			stmt.setInt(2, condi.getId());
			stmt.setObject(3, dateDemande);
			stmt.setInt(4, Constante.getEtatPosition(etatPosi));
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisiePositionEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return saisie;
	}

	public SaisiePositionEmployeC getSaisiePositionEmploye(EmployeC employe, Date datePaie) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisiePositionEmployeC saisie = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionEmploye)
				+ " WHERE id_employe=? AND date_debut <=?  AND date_fin>=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			stmt.setObject(2, datePaie);
			stmt.setObject(3, datePaie);
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisiePositionEmploye(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		if (saisie != null) {

			saisie.setListeDetailPrime(getListeSaisiePositionDetailPrime(saisie));
		}
		return saisie;
	}

	public boolean deleteSaisiePositionEmploye(SaisiePositionEmployeC saisie) {
		boolean deleted = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			deleted = insertHistorique(saisie.getHistorique(), conn);
			if (deleted) {
				deleted = deleteDetails(saisie.getId(), "id_saisie",
						Tables.getTableName(Tables.TableName.saisiePositionDetailPrime), conn);
				deleted = deleteNotAutocommit(saisie.getId(),
						Tables.getTableName(Tables.TableName.saisiePositionEmploye));
			}
			if (deleted) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public boolean annulerPositionEmploye(SaisiePositionEmployeC saisie) {
		boolean deleted = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			deleted = insertHistorique(saisie.getHistorique(), conn);
			if (deleted) {
				deleted = deleteDetails(saisie.getId(), "id_saisie",
						Tables.getTableName(Tables.TableName.saisiePositionDetailPrime), conn);
				if (deleted)
					deleted = annulerPosition(saisie, conn);
				if (deleted)
					deleted = deleteTraitement(saisie.getTraitement().getEmploye().getId(), saisie.getId(),
							Constante.getTypeAvancement(Constante.TypeAvancement.changementPosition), conn);
				if (deleted) {
					for (SaisiePositionDetailPrimeC detai1 : saisie.getListeDetailPrime()) {
						if (!detai1.isKept())
							deleted = updatePrimeEmploye(saisie.getEmploye().getId(), detai1.getPrime().getId(), false,
									conn);
					}
				}
			}
			if (deleted) {

				conn.commit();
			} else {

				conn.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return deleted;
	}

	private boolean annulerPosition(SaisiePositionEmployeC saisie, Connection conx) {
		boolean saved = false;
		Statement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisiePositionEmploye)
				+ " SET  date_decision=null,etat_position=1,decision=0,nouveau_salaire=0,"
				+ " taux_encours=0,ancien_salaire=0 WHERE id=" + saisie.getId();

		try {
			stmt = conx.createStatement();
			stmt.execute(sql);
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public List<SaisiePositionEmployeC> getListeSaisiePositionEmploye(int etat) {
		Statement stmt = null;
		ResultSet rs = null;
		List<SaisiePositionEmployeC> positionEmploye = new ArrayList<SaisiePositionEmployeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionEmploye)
				+ " where etat_position=" + etat;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				positionEmploye.add(setSaisiePositionEmploye(rs));
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return positionEmploye;
	}

	public List<SaisiePositionEmployeC> getListeSaisiePositionLine(int idEmpl) {
		Statement stmt = null;
		ResultSet rs = null;
		List<SaisiePositionEmployeC> positionEmploye = new ArrayList<SaisiePositionEmployeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionEmploye)
				+ " where inLine=1 AND id_employe=" + idEmpl;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				positionEmploye.add(setSaisiePositionEmploye(rs));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return positionEmploye;
	}

	private boolean updateCarriereEmploye(SaisiePositionEmployeC pos, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe)
				+ " SET  id_personnel=?,id_categorie=?,id_grade=?,id_fonction=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);

			if (pos.getIdPrs() > 0)
				stmt.setInt(1, pos.getIdPrs());
			else
				stmt.setObject(1, null);

			if (pos.getIdCtg() > 0)
				stmt.setInt(2, pos.getIdCtg());
			else
				stmt.setObject(2, null);

			if (pos.getIdGrd() > 0)
				stmt.setInt(3, pos.getIdGrd());
			else
				stmt.setObject(3, null);

			if (pos.getIdFx() > 0)
				stmt.setInt(4, pos.getIdFx());
			else
				stmt.setObject(4, null);

			stmt.setInt(5, pos.getEmploye().getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public List<SaisiePositionEmployeC> getListePositionEmploye(int decision, int etat) {
		Statement stmt = null;
		ResultSet rs = null;
		List<SaisiePositionEmployeC> positionEmploye = new ArrayList<SaisiePositionEmployeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePositionEmploye) + " where decision="
				+ decision + " AND etat_position=" + etat;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				positionEmploye.add(setSaisiePositionEmploye(rs));
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			for (SaisiePositionEmployeC s : positionEmploye) {
				s.setListeDetailPrime(getListeSaisiePositionDetailPrime(s));
			}
			releaseResource(stmt, rs);
		}
		return positionEmploye;
	}

	private boolean insertSaisieSanction(SaisieSanctionC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		saisie.setId(getId(Tables.getTableName(Tables.TableName.saisiesanction)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisiesanction)
				+ " (id, id_employe, id_sanction, montant_retenu, taux_retenu, salaire_base,"
				+ " date_debut_sanction, raison_sanction, date_fin_sanction, etat_sanction, "
				+ " date_recours, date_decision, justification_recours, motif_decision, date_saisie,"
				+ " id_operateur, mois_paie, id_exercice, decision, cloture) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, saisie.getId());
			if (saisie.getEmploye() != null) {
				stmt.setInt(2, saisie.getEmploye().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			stmt.setInt(3, saisie.getIdSanction());

			stmt.setDouble(4, saisie.getMontantRetenu());
			stmt.setDouble(5, saisie.getTauxRetenu());
			stmt.setDouble(6, saisie.getSalaireBase());
			stmt.setObject(7, saisie.getDateDebutSanction());
			stmt.setString(8, saisie.getMotifSanction());
			stmt.setObject(9, saisie.getDateFinSanction());
			stmt.setInt(10, saisie.getEtat());
			stmt.setObject(11, saisie.getDateRecours());
			stmt.setObject(12, saisie.getDateDecision());
			stmt.setString(13, saisie.getMotifRecours());
			stmt.setString(14, saisie.getMotifDecision());
			stmt.setObject(15, saisie.getDateSaisie());
			stmt.setInt(16, saisie.getIdOperateur());
			stmt.setInt(17, saisie.getMoisPaie());
			stmt.setInt(18, saisie.getIdExercice());
			stmt.setInt(19, saisie.getDecision());
			stmt.setBoolean(20, saisie.isCloture());
		

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateSaisieSanction(SaisieSanctionC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisiesanction) + " SET  "
				+ " id_employe=?, id_sanction=?, montant_retenu=?, taux_retenu=?, salaire_base=?,"
				+ " date_debut_sanction=?, raison_sanction=?, date_fin_sanction=?, etat_sanction=?, "
				+ " date_recours=?, date_decision=?, justification_recours=?, motif_decision=?,"
				+ " date_saisie=?,mois_paie=?,decision=?, cloture=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);

			if (saisie.getEmploye() != null) {
				stmt.setInt(1, saisie.getEmploye().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			stmt.setInt(2, saisie.getIdSanction());

			stmt.setDouble(3, saisie.getMontantRetenu());
			stmt.setDouble(4, saisie.getTauxRetenu());
			stmt.setDouble(5, saisie.getSalaireBase());
			stmt.setObject(6, saisie.getDateDebutSanction());
			stmt.setString(7, saisie.getMotifSanction());
			stmt.setObject(8, saisie.getDateFinSanction());
			stmt.setInt(9, saisie.getEtat());
			stmt.setObject(10, saisie.getDateRecours());
			stmt.setObject(11, saisie.getDateDecision());
			stmt.setString(12, saisie.getMotifRecours());
			stmt.setString(13, saisie.getMotifDecision());
			stmt.setObject(14, saisie.getDateSaisie());

			stmt.setInt(15, saisie.getMoisPaie());

			stmt.setInt(16, saisie.getDecision());
			stmt.setBoolean(17, saisie.isCloture());
			stmt.setInt(18, saisie.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateSaisieSanction(SaisieSanctionC saisie) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (saisie.getId() == 0) {
				saved = insertSaisieSanction(saisie);
				if (!saved) {
					saisie.setId(0);
				}
			} else {
				saved = updateSaisieSanction(saisie);
			}

			if (saved) {
				saisie.getHistorique().setIdLigne(saisie.getId());
				saved = insertHistorique(saisie.getHistorique(), con);
			}

			if (saved) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saved;
	}

	private SaisieSanctionC setSaisieSanction(ResultSet rs) throws SQLException {
		SaisieSanctionC saisie = new SaisieSanctionC();
		saisie.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null)
			saisie.setEmploye(getEmployeSimple(rs.getInt("id_employe")));
		saisie.setCloture(rs.getBoolean("cloture"));
		saisie.setDateDebutSanction(rs.getDate("date_debut_sanction"));
		saisie.setDateDecision(rs.getDate("date_decision"));
		saisie.setDateFinSanction(rs.getDate("date_fin_sanction"));
		saisie.setDateRecours(rs.getDate("date_recours"));
		saisie.setDateSaisie(rs.getDate("date_saisie"));
		saisie.setEtat(rs.getInt("etat_sanction"));
		saisie.setMoisPaie(rs.getInt("mois_paie"));
		saisie.setMontantRetenu(rs.getDouble("montant_retenu"));
		saisie.setMotifDecision(rs.getString("motif_decision"));
		saisie.setMotifRecours(rs.getString("justification_recours"));
		saisie.setMotifSanction(rs.getString("raison_sanction"));
		saisie.setSalaireBase(rs.getDouble("salaire_base"));
		saisie.setTauxRetenu(rs.getDouble("taux_retenu"));
		saisie.setIdSanction(rs.getInt("id_sanction"));
		saisie.setDecision(rs.getInt("decision"));
		saisie.setInLine(rs.getBoolean("inLine"));
		ParametrageSanctionC prm = FichierBaseDAO.getInstance().getParametrageSanction(saisie.getIdSanction());
		saisie.setPrm(prm);
		if (prm != null)
			saisie.setLibelleSanction(prm.getLibelleSanction());
		return saisie;
	}

	public SaisieSanctionC getSaisieSanction(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisieSanctionC saisie = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiesanction) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisieSanction(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return saisie;
	}

	public SaisieSanctionC getSaisieSanction(EmployeC employe, int type) {
		Statement stmt = null;
		ResultSet rs = null;
		SaisieSanctionC saisie = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiesanction) + " WHERE id_employe="
				+ employe.getId() + " AND type_sanction=" + type;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				saisie = setSaisieSanction(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return saisie;
	}

	public boolean deleteSaisieSanction(SaisieSanctionC saisie) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);
			deleted = insertHistorique(saisie.getHistorique(), con);
			if (deleted)
				deleted = deleteNotAutocommit(saisie.getId(), Tables.getTableName(Tables.TableName.saisiesanction));
			if (deleted) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public double getMontantSanctionRetenu(EmployeC employe, int moisRet, int idExercice) {
		Statement stmt = null;
		ResultSet rs = null;
		double retenu = 0;

		String sql = "SELECT SUM(montant_retenu) AS retenu FROM " + Tables.getTableName(Tables.TableName.saisiesanction)
				+ " WHERE id_employe=" + employe.getId() + " AND id_exercice=" + idExercice + " AND mois_paie="
				+ moisRet;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getObject("retenu") != null)
					retenu = rs.getDouble("retenu");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return retenu;
	}

	public boolean annulerRecourSanction(SaisieSanctionC saisie) {
		boolean saved = false;
		Statement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisiesanction)
				+ " SET  date_recours=null,etat_sanction=1 WHERE id=" + saisie.getId();

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean annulerDecisionSanction(SaisieSanctionC saisie) {
		boolean saved = false;
		Statement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisiesanction)
				+ " SET  date_decision=null,etat_sanction=2,cloture=0 WHERE id=" + saisie.getId();

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public List<SaisieSanctionC> getListeSanction(int etat) {
		Statement stmt = null;
		ResultSet rs = null;
		List<SaisieSanctionC> mesuresDisciplinaires = new ArrayList<SaisieSanctionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiesanction) + " WHERE"
				+ " etat_sanction=" + etat;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				mesuresDisciplinaires.add(setSaisieSanction(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return mesuresDisciplinaires;
	}

	public List<SaisieSanctionC> getListeSaisieTraite(int etat, int decision) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SaisieSanctionC> mesuresDisciplinaires = new ArrayList<SaisieSanctionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiesanction) + " WHERE "
				+ "etat_sanction=" + etat + " AND decision=" + decision;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				mesuresDisciplinaires.add(setSaisieSanction(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return mesuresDisciplinaires;
	}

	public List<SaisieSanctionC> getListeSanctionTraite(int idEmpl) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SaisieSanctionC> mesuresDisciplinaires = new ArrayList<SaisieSanctionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiesanction) + " WHERE id_employe="
				+ idEmpl;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				mesuresDisciplinaires.add(setSaisieSanction(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return mesuresDisciplinaires;
	}

	public List<SaisieSanctionC> getListeSanctionRetard(int typeSanction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SaisieSanctionC> mesuresDisciplinaires = new ArrayList<SaisieSanctionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiesanction)
				+ "  WHERE decision=0 AND type_sanction=" + typeSanction + " AND (etat_sanction=1 OR etat_sanction=2) ";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				mesuresDisciplinaires.add(setSaisieSanction(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}
		return mesuresDisciplinaires;
	}

	public List<SaisieSanctionC> getListeSanctionRetenu(EmployeC employe, int moisRet, int idExercice) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SaisieSanctionC> mesuresDisciplinaires = new ArrayList<SaisieSanctionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiesanction) + " WHERE id_employe="
				+ employe.getId() + " AND id_exercice=" + idExercice + " AND mois_paie=" + moisRet;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				mesuresDisciplinaires.add(setSaisieSanction(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}
		return mesuresDisciplinaires;
	}

	private boolean insertDemandeProlongationRetraite(DemandeProlongationRetraiteC demande) {
		boolean saved = false;
		PreparedStatement stmt = null;
		demande.setId(getId(Tables.getTableName(Tables.TableName.demandeProlongationRetraite)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.demandeProlongationRetraite)
				+ " (id, id_employe, age, age_retraite_demande, motif_demande, "
				+ "date_demande, decision, date_decision, etat,inLine) " + " VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, demande.getId());
			if (demande.getEmploye() != null) {
				stmt.setInt(2, demande.getEmploye().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			stmt.setInt(3, demande.getAge());
			stmt.setInt(4, demande.getAgeRetraiteDemande());
			stmt.setString(5, demande.getMotifDemande());
			stmt.setObject(6, demande.getDateDemande());
			stmt.setInt(7, demande.getDecision());
			stmt.setObject(8, demande.getDateDecision());
			stmt.setInt(9, demande.getEtat());
			stmt.setBoolean(10, demande.isInLine());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateDemandeProlongationRetraite(DemandeProlongationRetraiteC demande) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.demandeProlongationRetraite)
				+ " SET  id_employe=?, age=?, age_retraite_demande=?, motif_demande=?, "
				+ "date_demande=?, decision=?, date_decision=?, etat=?  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (demande.getEmploye() != null) {
				stmt.setInt(1, demande.getEmploye().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			stmt.setInt(2, demande.getAge());
			stmt.setInt(3, demande.getAgeRetraiteDemande());
			stmt.setString(4, demande.getMotifDemande());
			stmt.setObject(5, demande.getDateDemande());
			stmt.setInt(6, demande.getDecision());
			stmt.setObject(7, demande.getDateDecision());
			stmt.setInt(8, demande.getEtat());
			stmt.setInt(9, demande.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateDemandeProlongationRetraite(DemandeProlongationRetraiteC demande) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (demande.getId() == 0) {
				saved = insertDemandeProlongationRetraite(demande);
				if (!saved) {
					demande.setId(0);
				}
			} else {
				saved = updateDemandeProlongationRetraite(demande);
			}

			if (saved) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saved;
	}

	private DemandeProlongationRetraiteC setDemandeProlongationRetraite(ResultSet rs) throws SQLException {
		DemandeProlongationRetraiteC demande = new DemandeProlongationRetraiteC();
		demande.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null)
			demande.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		demande.setAge(rs.getInt("age"));
		demande.setAgeRetraiteDemande(rs.getInt("age_retraite_demande"));
		demande.setMotifDemande(rs.getString("motif_demande"));
		demande.setDateDemande(rs.getDate("date_demande"));
		demande.setDateDemandeS(HelperC.changeDateFormate(demande.getDateDemande()));
		demande.setDecision(rs.getInt("decision"));
		demande.setDateDecision(rs.getDate("date_decision"));
		demande.setDateDecisionS(HelperC.changeDateFormate(demande.getDateDecision()));
		demande.setEtat(rs.getInt("etat"));
		demande.setEtatProlongation(Constante.getEtatDemandeProlongationRetraite(demande.getEtat()));
		demande.setInLine(rs.getBoolean("inLine"));

		return demande;
	}

	public DemandeProlongationRetraiteC getDemandeProlongationRetraite(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DemandeProlongationRetraiteC demande = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeProlongationRetraite)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				demande = setDemandeProlongationRetraite(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public boolean deleteDemandeProlongationRetraite(DemandeProlongationRetraiteC demande) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(demande.getId(),
					Tables.getTableName(Tables.TableName.demandeProlongationRetraite));
			if (deleted) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public boolean annulerProlongagtionRetraite(DemandeProlongationRetraiteC demande) {
		boolean saved = false;
		Statement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.demandeProlongationRetraite)
				+ " SET  date_decision=null,etat=1,decision=0 WHERE id=" + demande.getId();

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public List<DemandeProlongationRetraiteC> getListeDemandeProlongationRetraite(int etat) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DemandeProlongationRetraiteC> demande = new ArrayList<DemandeProlongationRetraiteC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeProlongationRetraite)
				+ " where etat=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, etat);
			rs = stmt.executeQuery();
			while (rs.next()) {
				demande.add(setDemandeProlongationRetraite(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<DemandeProlongationRetraiteC> getListeDemandeProlongationRetraiteLine(int idEmpl) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DemandeProlongationRetraiteC> demande = new ArrayList<DemandeProlongationRetraiteC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeProlongationRetraite)
				+ " where id_employe=" + idEmpl + " AND inLine=1";

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			while (rs.next()) {
				demande.add(setDemandeProlongationRetraite(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<DemandeProlongationRetraiteC> getListeDemandeProlongationRetraiteTraite(int etat, int decision) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DemandeProlongationRetraiteC> demande = new ArrayList<DemandeProlongationRetraiteC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeProlongationRetraite)
				+ " where etat =" + etat + " AND decision=" + decision;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				demande.add(setDemandeProlongationRetraite(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public DemandeProlongationRetraiteC getDemandeProlongationParEmploye(int idEmploye, int decision, int etat) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DemandeProlongationRetraiteC demande = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeProlongationRetraite)
				+ " WHERE id=? AND decision=? AND etat=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idEmploye);
			stmt.setInt(2, 0);
			stmt.setInt(3, 1);
			rs = stmt.executeQuery();
			if (rs.next()) {
				demande = setDemandeProlongationRetraite(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	private boolean insertDemandeRetraiteAnticipe(DemandeRetraiteAnticipeC demande) {
		boolean saved = false;
		PreparedStatement stmt = null;
		demande.setId(getId(Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe)
				+ " (id,id_employe,anciennette,id_motif,date_demande,date_debut_retraite,"
				+ "decision,date_decision,etat,inLine) " + " VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, demande.getId());
			if (demande.getEmploye() != null) {
				stmt.setInt(2, demande.getEmploye().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			stmt.setInt(3, demande.getAnciennette());
			stmt.setInt(4, demande.getIdMotifDemande());
			stmt.setObject(5, demande.getDateDemande());
			stmt.setObject(6, demande.getDateDebutRetraite());
			stmt.setInt(7, demande.getDecision());
			stmt.setObject(8, demande.getDateDecision());
			stmt.setInt(9, demande.getEtat());
			stmt.setBoolean(10, demande.isInLine());
			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateDemandeRetraiteAnticipe(DemandeRetraiteAnticipeC demande) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe) + " SET  "
				+ " id_employe=?,anciennette=?,id_motif=?,date_demande=?,date_debut_retraite=?,"
				+ "decision=?,date_decision=?,etat=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (demande.getEmploye() != null) {
				stmt.setInt(1, demande.getEmploye().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			stmt.setInt(2, demande.getAnciennette());
			stmt.setInt(3, demande.getIdMotifDemande());
			stmt.setObject(4, demande.getDateDemande());
			stmt.setObject(5, demande.getDateDebutRetraite());
			stmt.setInt(6, demande.getDecision());
			stmt.setObject(7, demande.getDateDecision());
			stmt.setInt(8, demande.getEtat());
			stmt.setInt(9, demande.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateDemandeRetraiteAnticipe(DemandeRetraiteAnticipeC demande) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (demande.getId() == 0) {
				saved = insertDemandeRetraiteAnticipe(demande);
				if (!saved) {
					demande.setId(0);
				}
			} else {
				saved = updateDemandeRetraiteAnticipe(demande);
			}

			if (saved) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saved;
	}

	private DemandeRetraiteAnticipeC setDemandeRetraiteAnticipe(ResultSet rs) throws SQLException {
		DemandeRetraiteAnticipeC demande = new DemandeRetraiteAnticipeC();
		demande.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null)
			demande.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		demande.setAnciennette(rs.getInt("anciennette"));
		demande.setIdMotifDemande(rs.getInt("id_motif"));
		demande.setDateDemande(rs.getDate("date_demande"));
		demande.setDateDemandeS(HelperC.changeDateFormate(demande.getDateDemande()));
		demande.setDateDebutRetraite(rs.getDate("date_debut_retraite"));
		demande.setDateDebutRetraiteS(HelperC.changeDateFormate(demande.getDateDebutRetraite()));
		demande.setDecision(rs.getInt("decision"));
		demande.setDateDecision(rs.getDate("date_decision"));
		demande.setDateDecisionS(HelperC.changeDateFormate(demande.getDateDecision()));
		demande.setEtat(rs.getInt("etat"));
		demande.setInLine(rs.getBoolean("inLine"));

		return demande;
	}

	public DemandeRetraiteAnticipeC getDemandeRetraiteAnticipe(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DemandeRetraiteAnticipeC demande = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				demande = setDemandeRetraiteAnticipe(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public boolean deleteDemandeRetraiteAnticipe(DemandeRetraiteAnticipeC demande) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(demande.getId(),
					Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe));
			if (deleted) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<DemandeRetraiteAnticipeC> getListeRetraiteAnticipeValide(int etat, int decision) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DemandeRetraiteAnticipeC> demande = new ArrayList<DemandeRetraiteAnticipeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe) + " where etat="
				+ etat + " AND decision=" + decision + " AND date_retraite IS NULL";

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				demande.add(setDemandeRetraiteAnticipe(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<DemandeRetraiteAnticipeC> getListeDemandeRetraiteAnticipe(int etat) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DemandeRetraiteAnticipeC> demande = new ArrayList<DemandeRetraiteAnticipeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe) + " where etat="
				+ etat;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				demande.add(setDemandeRetraiteAnticipe(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<DemandeRetraiteAnticipeC> getListeDemandeRetraiteAnticipeLine(int idEmpl) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DemandeRetraiteAnticipeC> demande = new ArrayList<DemandeRetraiteAnticipeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe)
				+ " where id_employe=" + idEmpl;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				demande.add(setDemandeRetraiteAnticipe(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<DemandeRetraiteAnticipeC> getListeDemandeRetraiteAnticipeTraite(int etat) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DemandeRetraiteAnticipeC> demande = new ArrayList<DemandeRetraiteAnticipeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe) + " where etat="
				+ etat;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				demande.add(setDemandeRetraiteAnticipe(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public boolean annulerRetraiteAnticipe(DemandeRetraiteAnticipeC demande) {
		boolean saved = false;
		Statement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe)
				+ " SET  date_decision=null,etat=1,decision=0 WHERE id=" + demande.getId();

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean insertFinCarriereDetailIndemnite(FinCarriereDetailIndemniteC fin) {
		boolean saved = false;
		PreparedStatement stmt = null;
		fin.setId(getId(Tables.getTableName(Tables.TableName.finCarriereDetailIndemnite)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.finCarriereDetailIndemnite)
				+ " (id,id_entete,id_indemnite,montant) " + " VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, fin.getId());
			if (fin.getEntete() != null) {
				stmt.setInt(2, fin.getEntete().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			if (fin.getIndemnite() != null) {
				stmt.setInt(3, fin.getIndemnite().getId());
			} else {
				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, fin.getMontant());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateFinCarriereDetailIndemnite(FinCarriereDetailIndemniteC fin) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.finCarriereDetailIndemnite) + " SET  "
				+ " id_entete=?,id_indemnite=?,montant=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (fin.getEntete() != null) {
				stmt.setInt(1, fin.getEntete().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			if (fin.getIndemnite() != null) {
				stmt.setInt(2, fin.getIndemnite().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, fin.getMontant());
			stmt.setInt(4, fin.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateFinCarriereDetailIndemnite(FinCarriereDetailIndemniteC fin) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (fin.getId() == 0) {
				saved = insertFinCarriereDetailIndemnite(fin);
				if (!saved) {
					fin.setId(0);
				}
			} else {
				saved = updateFinCarriereDetailIndemnite(fin);
			}

			if (saved) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saved;
	}

	private FinCarriereDetailIndemniteC setFinCarriereDetailIndemnite(ResultSet rs) throws SQLException {
		FinCarriereDetailIndemniteC fin = new FinCarriereDetailIndemniteC();
		fin.setId(rs.getInt("id"));
		if (rs.getObject("id_entete") != null)
			fin.setEntete(getFinCarriere(rs.getInt("id_entete")));
		if (rs.getObject("id_indemnite") != null)
			fin.setIndemnite(FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getInt("id_indemnite")));
		fin.setMontant(rs.getDouble("montant"));

		return fin;
	}

	public FinCarriereDetailIndemniteC getFinCarriereDetailIndemnite(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FinCarriereDetailIndemniteC fin = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.finCarriereDetailIndemnite)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				fin = setFinCarriereDetailIndemnite(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return fin;
	}

	public boolean deleteFinCarriereDetailIndemnite(FinCarriereDetailIndemniteC fin) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(fin.getId(),
					Tables.getTableName(Tables.TableName.finCarriereDetailIndemnite));
			if (deleted) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<FinCarriereDetailIndemniteC> getListeFinCarriereDetailIndemnite(FinCarriereC fin) {
		List<FinCarriereDetailIndemniteC> detai = new ArrayList<FinCarriereDetailIndemniteC>();

		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";
		try {
			stmt = con.createStatement();

			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.finCarriereDetailIndemnite) + "  "
					+ "WHERE  id_entete=" + fin.getId();
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setFinCarriereDetailIndemnite(rs));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return detai;
	}

	private boolean insertFinCarriereDetailPrime(FinCarriereDetailPrimeC fin, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		fin.setId(getId(Tables.getTableName(Tables.TableName.finCarriereDetailPrime)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.finCarriereDetailPrime)
				+ " (id,id_entete,id_prime,montant,id_parametre) " + " VALUES (?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, fin.getId());
			if (fin.getEntete() != null) {
				stmt.setInt(2, fin.getEntete().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			if (fin.getPrime() != null) {
				stmt.setInt(3, fin.getPrime().getId());
			} else {
				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, fin.getMontant());
			stmt.setInt(5, fin.getIdParm());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateFinCarriereDetailPrime(FinCarriereDetailPrimeC fin, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.finCarriereDetailPrime) + " SET  "
				+ " id_entete=?,id_prime=?,montant=?,id_parametre=?" + "  WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);
			if (fin.getEntete() != null) {
				stmt.setInt(1, fin.getEntete().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			if (fin.getPrime() != null) {
				stmt.setInt(2, fin.getPrime().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, fin.getMontant());
			stmt.setInt(4, fin.getIdParm());
			stmt.setInt(5, fin.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private FinCarriereDetailPrimeC setFinCarriereDetailPrime(ResultSet rs) throws SQLException {
		FinCarriereDetailPrimeC fin = new FinCarriereDetailPrimeC();
		fin.setId(rs.getInt("id"));
		if (rs.getObject("id_entete") != null)
			fin.setEntete(getFinCarriere(rs.getInt("id_entete")));
		if (rs.getObject("id_prime") != null)
			fin.setPrime(FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getInt("id_prime")));
		fin.setMontant(rs.getDouble("montant"));
		fin.setIdParm(rs.getInt("id_parametre"));
		return fin;
	}

	public FinCarriereDetailPrimeC getFinCarriereDetailPrime(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FinCarriereDetailPrimeC fin = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.finCarriereDetailPrime) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				fin = setFinCarriereDetailPrime(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return fin;
	}

	public boolean deleteFinCarriereDetailPrime(FinCarriereDetailPrimeC fin) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(fin.getId(), Tables.getTableName(Tables.TableName.finCarriereDetailPrime));
			if (deleted) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<FinCarriereDetailPrimeC> getListeFinCarriereDetailPrime(FinCarriereC fin) {
		List<FinCarriereDetailPrimeC> detai = new ArrayList<FinCarriereDetailPrimeC>();

		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";
		try {
			stmt = con.createStatement();

			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.finCarriereDetailPrime) + "  "
					+ "WHERE  id_entete='" + fin.getId() + "'";
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setFinCarriereDetailPrime(rs));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return detai;
	}

	private boolean insertFinCarriere(FinCarriereC fin, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		fin.setId(getId(Tables.getTableName(Tables.TableName.finCarriere)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.finCarriere)
				+ " (id, id_employe, date_retraite, duree_traitement, montant, type_retraite, "
				+ "montant_apres,date_salaire)  VALUES (?,?,?,?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setInt(1, fin.getId());

			if (fin.getEmploye() != null) {
				stmt.setInt(2, fin.getEmploye().getId());
			} else {
				stmt.setObject(2, (Object) null);

			}

			stmt.setObject(3, fin.getDateRetraite());
			stmt.setInt(4, fin.getDureSalaireTotal());
			stmt.setDouble(5, fin.getMontant());
			stmt.setInt(6, fin.getTypeRetraite());
			stmt.setDouble(7, fin.getMontantApres());
			stmt.setObject(8, fin.getDateSalaire());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateFinCarriere(FinCarriereC fin, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.finCarriere) + " SET  "
				+ " date_retraite=?, duree_traitement=?, montant=?, type_retraite=?, montant_apres=?,"
				+ "date_salaire=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setObject(1, fin.getDateRetraite());
			stmt.setInt(2, fin.getDureSalaireTotal());
			stmt.setDouble(3, fin.getMontant());
			stmt.setInt(4, fin.getTypeRetraite());
			stmt.setDouble(5, fin.getMontantApres());
			stmt.setObject(6, fin.getDateSalaire());
			stmt.setInt(7, fin.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateAnticipe(Date date, int idEmpl, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.demandeRetraiteAnticipe)
				+ " SET  date_retraite=? WHERE id_employe=?";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setObject(1, date);
			stmt.setInt(2, idEmpl);

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updatePrimeEmploye(int idEmpl, int idPrm, boolean bloq, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailPrimeEmploye)
				+ " SET  bloque=? WHERE id_employe=? AND id_prime=?";

		try {
			stmt = conx.prepareStatement(sql);
			if (bloq)
				stmt.setBoolean(1, true);
			else
				stmt.setBoolean(1, false);

			stmt.setInt(2, idEmpl);
			stmt.setInt(3, idPrm);

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateAllPrimeEmploye(int idEmpl, boolean bloq, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailPrimeEmploye)
				+ " SET  bloque=? WHERE id_employe=?";

		try {
			stmt = conx.prepareStatement(sql);
			if (bloq)
				stmt.setBoolean(1, true);
			else
				stmt.setBoolean(1, false);

			stmt.setInt(2, idEmpl);

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateAllCotisation(int idEmpl, boolean bloq, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailCotisationEmploye)
				+ " SET  bloque=? WHERE id_employe=?";

		try {
			stmt = conx.prepareStatement(sql);
			if (bloq)
				stmt.setBoolean(1, true);
			else
				stmt.setBoolean(1, false);

			stmt.setInt(2, idEmpl);

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateEmploye(int idEmpl, Date date, boolean add, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe) + " SET  date_retraite=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);
			if (add)
				stmt.setObject(1, date);
			else
				stmt.setObject(1, null);

			stmt.setInt(2, idEmpl);

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateFinCarriere(FinCarriereC fin) {
		boolean saved = false;
		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (fin.getId() == 0) {
				saved = insertFinCarriere(fin, conn);
				if (!saved)
					fin.setId(0);

			} else {
				saved = updateFinCarriere(fin, conn);
			}
			if (saved) {
				if (fin.getListeDetailPrime() != null && fin.getListeDetailPrime().size() > 0) {

					for (FinCarriereDetailPrimeC detai1 : fin.getListeDetailPrime()) {
						detai1.setEntete(fin);
						if (detai1.getId() == 0)
							saved = insertFinCarriereDetailPrime(detai1, conn);

						else
							saved = updateFinCarriereDetailPrime(detai1, conn);
					}
				}

			}
			if (fin.getListeDetailPrimeDeleted() != null && fin.getListeDetailPrimeDeleted().size() > 0) {

				for (FinCarriereDetailPrimeC detai1 : fin.getListeDetailPrime()) {
					saved = delete(detai1.getId(), Tables.getTableName(Tables.TableName.finCarriereDetailPrime));
				}

			}

			if (saved) {
				fin.getTraitementAvant().setIdRef(fin.getId());

				if (fin.getTraitementAvant().getId() == 0)
					saved = insertTraitementSalarial(fin.getTraitementAvant(), conn);
				else
					saved = updateTraitementSalarial(fin.getTraitementAvant(), conn);
			}
			if (saved) {
				fin.getTraitementApres().setIdRef(fin.getId());

				if (fin.getTraitementApres().getId() == 0)
					saved = insertTraitementSalarial(fin.getTraitementApres(), conn);
				else
					saved = updateTraitementSalarial(fin.getTraitementApres(), conn);
			}
			if (saved)
				saved = updateAnticipe(fin.getDateRetraite(), fin.getEmploye().getId(), conn);
			if (saved)
				saved = updateAllPrimeEmploye(fin.getEmploye().getId(), true, conn);
			if (saved)
				saved = updateAllCotisation(fin.getEmploye().getId(), true, conn);
			if (saved)
				saved = updateEmploye(fin.getEmploye().getId(), fin.getDateRetraite(), true, conn);

			if (saved) {
				fin.getHistorique().setIdLigne(fin.getId());
				saved = insertHistorique(fin.getHistorique(), conn);
			}

			if (saved)
				conn.commit();
			else
				conn.rollback();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saved;
	}

	private FinCarriereC setFinCarriere(ResultSet rs) throws SQLException {
		FinCarriereC fin = new FinCarriereC();
		fin.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null)
			fin.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		fin.setDateRetraite(rs.getDate("date_retraite"));
		fin.setMontantApres(rs.getDouble("montant_apres"));
		fin.setDureSalaireTotal(rs.getInt("duree_traitement"));
		fin.setTypeRetraite(rs.getInt("type_retraite"));
		fin.setMontant(rs.getDouble("montant"));
		fin.setPrintDateRetraite(HelperC.convertDate(fin.getDateRetraite()));
		return fin;
	}

	public FinCarriereC getFinCarriere(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FinCarriereC fin = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.finCarriere) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				fin = setFinCarriere(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return fin;
	}

	public FinCarriereC getFinCarriereEmploye(int idEmploye) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FinCarriereC fin = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.finCarriere) + " WHERE id_employe=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idEmploye);
			rs = stmt.executeQuery();
			if (rs.next()) {
				fin = setFinCarriere(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		if (fin != null) {

			fin.setListeDetailPrime(getListeFinCarriereDetailPrime(fin));
		}
		return fin;
	}

	public boolean annulerFinCarriere(FinCarriereC fin) {
		boolean deleted = false;
		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			deleted = insertHistorique(fin.getHistorique(), conn);
			if (deleted)
				deleted = deleteList(fin.getId(), "id_entete",
						Tables.getTableName(Tables.TableName.finCarriereDetailPrime));
			if (deleted)
				deleted = deleteTraitement(fin.getTraitementAvant().getEmploye().getId(), fin.getId(),
						Constante.getTypeAvancement(Constante.TypeAvancement.finCarriereAv), conn);
			if (deleted)
				deleted = deleteTraitement(fin.getTraitementApres().getEmploye().getId(), fin.getId(),
						Constante.getTypeAvancement(Constante.TypeAvancement.finCarriereAp), conn);
			if (deleted)
				deleted = updateAnticipe(null, fin.getEmploye().getId(), conn);
			if (deleted)
				deleted = updateAllPrimeEmploye(fin.getEmploye().getId(), false, conn);
			if (deleted)
				deleted = updateAllCotisation(fin.getEmploye().getId(), false, conn);
			if (deleted)
				deleted = updateEmploye(fin.getEmploye().getId(), fin.getDateRetraite(), false, conn);
			if (deleted)
				deleted = delete(fin.getId(), Tables.getTableName(Tables.TableName.finCarriere), conn);

			if (deleted)
				conn.commit();
			else
				conn.rollback();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<FinCarriereC> getListeFinCarriere() {
		List<FinCarriereC> detai = new ArrayList<FinCarriereC>();

		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";
		try {
			stmt = con.createStatement();

			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.finCarriere);
			rs = stmt.executeQuery(sqlRequest);
			while (rs.next()) {
				detai.add(setFinCarriere(rs));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return detai;
	}

	private boolean insertSaisiePlanConge(SaisiePlanCongeC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		saisie.setId(getId(Tables.getTableName(Tables.TableName.saisiePlanConge)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisiePlanConge)
				+ " (id, id_employe, date_debut, date_fin, id_exercice, id_type_conge) " + " VALUES (?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, saisie.getId());
			stmt.setInt(2, saisie.getIdEmploye());
			stmt.setObject(3, saisie.getDateDebut());
			stmt.setObject(4, saisie.getDateFin());
			stmt.setInt(5, saisie.getIdExercice());
			stmt.setInt(6, saisie.getIdTypeConge());
			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateSaisiePlanConge(SaisiePlanCongeC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisiePlanConge) + " SET  "
				+ "  date_debut=?, date_fin=?,id_type_conge=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setObject(1, saisie.getDateDebut());
			stmt.setObject(2, saisie.getDateFin());
			stmt.setInt(3, saisie.getIdTypeConge());
			stmt.setInt(4, saisie.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateSaisiePlanConge(SaisiePlanCongeC saisie) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (saisie.getId() == 0) {
				saved = insertSaisiePlanConge(saisie);
				if (!saved) {
					saisie.setId(0);
				}
			} else {
				saved = updateSaisiePlanConge(saisie);
			}

			if (saved) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saved;
	}

	private SaisiePlanCongeC setSaisiePlanConge(ResultSet rs) throws SQLException {
		SaisiePlanCongeC saisie = new SaisiePlanCongeC();
		saisie.setId(rs.getInt("id"));
		saisie.setIdEmploye(rs.getInt("id_employe"));
		saisie.setIdTypeConge(rs.getInt("id_type_conge"));
		saisie.setDateDebut(rs.getDate("date_debut"));
		saisie.setDateDebutS(HelperC.changeDateFormate(saisie.getDateDebut()));
		saisie.setDateFin(rs.getDate("date_fin"));
		saisie.setDateFinS(HelperC.changeDateFormate(saisie.getDateFin()));
		saisie.setEmploye(getEmployeSimple(saisie.getIdEmploye()));

		return saisie;
	}

	public SaisiePlanCongeC getSaisieDemandePlanConge(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisiePlanCongeC saisie = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePlanConge) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisiePlanConge(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return saisie;
	}

	public List<SaisiePlanCongeC> getListSaisiePlanConge(int idEx) {
		Statement stmt = null;
		ResultSet rs = null;
		List<SaisiePlanCongeC> list = new ArrayList<SaisiePlanCongeC>();

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePlanConge) + " WHERE id_exercice="
				+ idEx;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(setSaisiePlanConge(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return list;
	}

	public SaisiePlanCongeC getSaisiePlanConge(EmployeC employe, int idExercice, int idType) {
		SaisiePlanCongeC demande = null;
		Statement stmt = null;
		ResultSet res = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisiePlanConge) + " WHERE id_employe="
				+ employe.getId() + " AND id_exercice=" + idExercice + " AND id_type_conge=" + idType;
		try {
			stmt = con.createStatement();

			res = stmt.executeQuery(sql);
			if (res.next()) {
				demande = setSaisiePlanConge(res);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return demande;
	}

	public boolean deleteSaisiePlanConge(SaisiePlanCongeC saisie) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(saisie.getId(), Tables.getTableName(Tables.TableName.saisiePlanConge));
			if (deleted) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	private boolean insertSaisieDemandeSortie(SaisieDemandeSortieC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.saisieSortie)
				+ " (id_employe,id_exercice,date_demande,heure_depart,heure_retour,motif_sortie,"
				+ "date_decision,decision,date_sortie,motif_refus_sortie,retirer_au_conge,etat,inline) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);

			if (saisie.getEmploye() != null) {
				stmt.setInt(1, saisie.getEmploye().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			stmt.setInt(2, saisie.getIdExercice());
			stmt.setObject(3, saisie.getDateDemande());
			stmt.setString(4, saisie.getHeureDepart());
			stmt.setString(5, saisie.getHeureRetour());

			stmt.setString(6, saisie.getMotifSortie());
			stmt.setObject(7, saisie.getDateValidation());

			stmt.setInt(8, saisie.getDecision());
			stmt.setObject(9, saisie.getDateSortie());
			stmt.setString(10, saisie.getMotifRefusSortie());
			stmt.setBoolean(11, saisie.isImputableAuxPresences());
			stmt.setInt(12, saisie.getEtatSortie());
			stmt.setBoolean(13, saisie.isInLine());
			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateSaisieDemandeSortie(SaisieDemandeSortieC saisie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisieSortie)
				+ " SET id_employe=?,id_exercice=?,date_demande=?,heure_depart=?,heure_retour=?,motif_sortie=?,"
				+ "date_decision=?,decision=?,date_sortie=?,motif_refus_sortie=?,retirer_au_conge=?,etat=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);

			if (saisie.getEmploye() != null) {
				stmt.setInt(1, saisie.getEmploye().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			stmt.setInt(2, saisie.getIdExercice());
			stmt.setObject(3, saisie.getDateDemande());
			stmt.setString(4, saisie.getHeureDepart());
			stmt.setString(5, saisie.getHeureRetour());
			stmt.setString(6, saisie.getMotifSortie());
			stmt.setObject(7, saisie.getDateValidation());
			stmt.setInt(8, saisie.getDecision());
			stmt.setObject(9, saisie.getDateSortie());
			stmt.setString(10, saisie.getMotifRefusSortie());
			stmt.setBoolean(11, saisie.isImputableAuxPresences());
			stmt.setInt(12, saisie.getEtatSortie());
			stmt.setInt(13, saisie.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateSaisieDemandeSortie(SaisieDemandeSortieC saisie) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (saisie.getId() == 0) {
				saved = insertSaisieDemandeSortie(saisie);
				if (!saved) {
					saisie.setId(0);
				}
			} else {
				saved = updateSaisieDemandeSortie(saisie);
			}

			if (saved) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return saved;
	}

	private SaisieDemandeSortieC setSaisieDemandeSortie(ResultSet rs) throws SQLException {
		SaisieDemandeSortieC saisie = new SaisieDemandeSortieC();
		saisie.setId(rs.getInt("id"));
		if (rs.getObject("id_employe") != null)
			saisie.setEmploye(getEmploye(rs.getInt("id_employe"), false));
		saisie.setDateDemande(rs.getDate("date_demande"));
		saisie.setDateDemandeS(HelperC.changeDateFormate(saisie.getDateDemande()));
		saisie.setHeureDepart(rs.getString("heure_depart"));
		saisie.setHeureRetour(rs.getString("heure_retour"));
		saisie.setDecision(rs.getInt("decision"));
		saisie.setMotifSortie(rs.getString("motif_sortie"));
		saisie.setDateSortie(rs.getDate("date_sortie"));
		saisie.setDateSortieS(HelperC.changeDateFormate(saisie.getDateSortie()));
		saisie.setDateValidation(rs.getDate("date_decision"));
		saisie.setDateValidationS(HelperC.changeDateFormate(saisie.getDateValidation()));
		saisie.setImputableAuxPresences(rs.getBoolean("retirer_au_conge"));
		saisie.setInLine(rs.getBoolean("inline"));
		saisie.setEtatSortie(rs.getInt("etat"));
		saisie.setEtatDemandeSortie(Constante.getEtatDemandeSortie(saisie.getEtatSortie()));

		saisie.setMotifRefusSortie(rs.getString("motif_refus_sortie"));

		return saisie;
	}

	public SaisieDemandeSortieC getSaisieDemandeSortie(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SaisieDemandeSortieC saisie = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieSortie) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				saisie = setSaisieDemandeSortie(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return saisie;
	}

	public boolean deleteSaisieDemandeSortie(SaisieDemandeSortieC saisie) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(saisie.getId(), Tables.getTableName(Tables.TableName.saisieSortie));
			if (deleted) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	public List<SaisieDemandeSortieC> getListeSaisieDemandeSortie(int etat, int decision) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SaisieDemandeSortieC> demande = new ArrayList<SaisieDemandeSortieC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieSortie)
				+ " where etat=? AND decision=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, etat);
			stmt.setInt(2, decision);
			rs = stmt.executeQuery();

			while (rs.next()) {
				demande.add(setSaisieDemandeSortie(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public List<SaisieDemandeSortieC> getListeSaisieDemandeSortie(EmployeC employe) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SaisieDemandeSortieC> demande = new ArrayList<SaisieDemandeSortieC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.saisieSortie) + " where id_employe=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, employe.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				demande.add(setSaisieDemandeSortie(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return demande;
	}

	public boolean annulerDecisionSortie(SaisieDemandeSortieC sortie) {
		boolean saved = false;
		Statement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisieSortie)
				+ " SET  date_decision=null,etat=1,decision=0 WHERE id=" + sortie.getId();

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}
	
	public boolean updateConge(EmployeC emp) {
		boolean saved = false;
		Statement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.saisieSortie)
				+ " SET  jour_conge_annuel="+emp.getJourConge() +" WHERE id=" + emp.getId();

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}
}
