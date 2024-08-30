package persistencePaie;

import classesPaie.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FichierBaseDAO implements Serializable {
	private static final long serialVersionUID = 4372980689520979261L;

	private FichierBaseDAO() {
		con = Connexion.getConnection();
	}

	private static FichierBaseDAO dao;
	private static Connection con;

	public static FichierBaseDAO getInstance() {
		if (dao == null) {
			dao = new FichierBaseDAO();
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
		} finally {

			releaseResource(stmt, rs);
		}

		return id + 1;
	}

	public boolean delete(String TableName, int id) {
		boolean destroyed = false;
		Statement stmt = null;
		String sql = "DELETE FROM " + TableName + " WHERE id=" + id;

		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.commit();
			destroyed = true;
		} catch (SQLException e) {

			Logger.getLogger(FichierBaseDAO.class.getName()).log(Level.SEVERE, (String) null, e);
		}
		return destroyed;
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
		} finally {

			releaseResource(stmt, null);
		}

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
		} finally {

			releaseResource(stmt, null);
		}

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
		} finally {

			releaseResource(stmt, null);
		}

		return deleted;
	}

	public boolean deleteList(int idEntete, String colName, String tblName) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + tblName + " WHERE " + colName + "=" + idEntete;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return deleted;
	}

	public boolean deleteList(int idEntete, String colName, String tblName, Connection conn) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + tblName + " WHERE " + colName + "=" + idEntete;

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			deleted = true;

		} catch (SQLException ex) {

			deleted = false;
			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return deleted;
	}

	private boolean insertBase(Base b, String tblName) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "";

		sqlRequest = "INSERT INTO " + tblName + " (id,code,designation) VALUES (?,?,?)";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			b.setId(getId(tblName));
			pstmt.setInt(1, b.getId());
			pstmt.setString(2, b.getCode());
			pstmt.setString(3, b.getDesignation());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, null);
		}

		return saved;
	}

	private boolean updateBase(Base base, String tblName) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + tblName + " SET designation=? WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setString(1, base.getDesignation());
			pstmt.setInt(2, base.getId());
			pstmt.executeUpdate();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, null);
		}

		return saved;
	}

	public boolean insertUpdateBase(Base b, String tblName) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (b.getId() == 0) {

				saved = insertBase(b, tblName);
			} else {

				saved = updateBase(b, tblName);
			}
			if (saved) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private Base setBase(ResultSet rs) throws SQLException {
		Base b = new Base();
		b.setId(rs.getInt("id"));
		b.setCode(rs.getString("code"));
		b.setDesignation(rs.getString("designation"));
		return b;
	}

	public Base getBaseById(int idB, String tblName) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Base b = null;
		String sql = "SELECT * FROM " + tblName + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idB);
			rs = stmt.executeQuery();
			if (rs.next()) {
				b = setBase(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return b;
	}

	public Base getBase(String code, int id, String tblName) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Base b = null;
		String sql = "SELECT * FROM " + tblName + " WHERE code=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				b = setBase(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return b;
	}

	public Base getBases(String designation, int id, String tblName) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Base b = null;
		String sql = "SELECT * FROM " + tblName + " WHERE designation=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				b = setBase(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return b;
	}

	public Base getBaseByCode(String codeB, String tblName) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Base b = null;
		String sql = "SELECT * FROM " + tblName + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, codeB);
			rs = stmt.executeQuery();
			if (rs.next()) {
				b = setBase(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(stmt, rs);
		}

		return b;
	}

	public Base getBaseByDesignation(String designB, String tblName) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Base b = null;
		String sql = "SELECT * FROM " + tblName + " WHERE designation=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designB);
			rs = stmt.executeQuery();
			if (rs.next()) {
				b = setBase(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(stmt, rs);
		}

		return b;
	}

	public List<Base> getAllBase(String tblName) {
		List<Base> listB = new ArrayList<Base>();
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		if (tblName != null && !tblName.equals(""))
			sql = "SELECT * FROM " + tblName + " ORDER BY code";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				listB.add(setBase(rs));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(stmt, rs);
		}

		return listB;
	}

	public void deleteBase(Base base, String tableName) {
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id='" + base.getId() + "'");

		} catch (SQLException ex) {

			Logger.getLogger(FichierBaseDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
		} finally {

			releaseResource(stmt, null);
		}
	}

	public List<Base> getListBase(String motCode, String motDesigantion, String tblName) {
		List<Base> listB = new ArrayList<Base>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + tblName + " WHERE 1=1 ";

		try {
			stmt = con.prepareStatement(sql);
			if (motCode != null && !motCode.trim().equals("")) {
				sql = String.valueOf(sql) + " AND code LIKE '%" + motCode + "'%";
			}
			if (motDesigantion != null && !motDesigantion.trim().equals("")) {
				sql = String.valueOf(sql) + " AND designation LIKE '%" + motDesigantion + "'%";
			}
			sql = String.valueOf(sql) + " ORDER BY code";
			rs = stmt.executeQuery();
			while (rs.next()) {
				listB.add(setBase(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return listB;
	}

	public List<Base> getListBases(String codeDebut, String codeFin, String tblName) {
		List<Base> listB = new ArrayList<Base>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + tblName + " tout  WHERE 1=1 ";

		try {
			stmt = con.prepareStatement(sql);
			sql = "SELECT * FROM " + tblName + " tout  WHERE 1=1 ";
			if (codeDebut != "" && codeFin != "") {
				sql = String.valueOf(sql) + HelperC.FiltreCode(codeDebut, codeFin, "tout", "code");
			}
			sql = String.valueOf(sql) + " ORDER BY code";
			for (rs = stmt.executeQuery(); rs.next(); listB.add(setBase(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return listB;
	}

	public List<Base> getListBaseNotCurrent(String motCode, String motDesigantion, int id, String tblName) {
		List<Base> listB = new ArrayList<Base>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + tblName + " WHERE id<>?";

		try {
			stmt = con.prepareStatement(sqlRequest);
			stmt.setInt(1, id);
			sqlRequest = "SELECT * FROM " + tblName + " WHERE id<>" + id;
			if (motCode != null && !motCode.trim().equals("")) {
				sqlRequest = String.valueOf(sqlRequest) + " AND code LIKE '%" + motCode + "%'";
			}
			if (motDesigantion != null && !motDesigantion.trim().equals("")) {
				sqlRequest = String.valueOf(sqlRequest) + " AND designation LIKE '%" + motDesigantion + "%'";
			}
			sqlRequest = String.valueOf(sqlRequest) + " ORDER BY code,designation";
			for (rs = stmt.executeQuery(sqlRequest); rs.next(); listB.add(setBase(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return listB;
	}

	public boolean insertPremierOperateur(OperateurC op) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (op.getEmploye().getId() == 0) {
				saved = insertPremierEmploye(op.getEmploye(), conn);
			} else {
				saved = updatePremierEmploye(op.getEmploye(), conn);
			}
			if (saved) {

				op.setIdEmploye(op.getEmploye().getId());
				saved = insertOperateur(op, conn);
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

	private boolean insertPremierEmploye(EmployeC empl, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		empl.setId(getId(Tables.getTableName(Tables.TableName.employe)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.employe)
				+ " (id,code,nom,matricule,partenaire,id_fonction)  VALUES (?,?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, empl.getId());
			stmt.setString(2, empl.getCode());
			stmt.setString(3, empl.getNom());
			stmt.setString(4, empl.getMatricule());
			stmt.setInt(5, HelperC.GetIntValueByBoolean(Boolean.valueOf(empl.isPartenaire())));
			stmt.setInt(6, empl.getIdFnctn());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updatePremierEmploye(EmployeC empl, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe)
				+ " SET nom=?,matricule=?,partenaire=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setString(1, empl.getNom());
			stmt.setString(2, empl.getMatricule());
			stmt.setInt(3, HelperC.GetIntValueByBoolean(Boolean.valueOf(empl.isPartenaire())));
			stmt.setInt(4, empl.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateOperateur(OperateurC op) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (op.getId() == 0) {

				saved = insertOperateur(op, conn);

			} else {

				saved = updateOperateur(op, conn);
			}
			if (saved)
				saved = updateFonctionEmpl(op.getIdFonction(), op.getIdEmploye(), conn);
			if (saved) {

				op.getHistorique().setIdLigne(op.getId());
				saved = insertHistorique(op.getHistorique(), conn);
			}
			if (saved) {

				con.setAutoCommit(true);
			} else {

				con.rollback();
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private boolean insertOperateur(OperateurC oper, Connection conx) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		oper.setId(getId(Tables.getTableName(Tables.TableName.operateur)));
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.operateur)
				+ " (id,id_employe,login,pwd,actif) VALUES (?,?,?,?,?) ";

		try {
			pstmt = conx.prepareStatement(sqlRequest);
			pstmt.setInt(1, oper.getId());
			pstmt.setInt(2, oper.getIdEmploye());
			pstmt.setString(3, oper.getLogin());
			pstmt.setString(4, oper.getCodeSecret());
			pstmt.setInt(5, HelperC.GetIntValueByBoolean(Boolean.valueOf(oper.isActif())));

			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, null);
		}

		return saved;
	}

	private boolean updateOperateur(OperateurC oper, Connection conx) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.operateur)
				+ " SET login=?,pwd=?,actif=? WHERE id=? ";

		try {
			pstmt = conx.prepareStatement(sqlRequest);
			pstmt.setString(1, oper.getLogin());
			pstmt.setObject(2, oper.getCodeSecret());
			pstmt.setInt(3, HelperC.GetIntValueByBoolean(Boolean.valueOf(oper.isActif())));

			pstmt.setInt(4, oper.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, null);
		}

		return saved;
	}

	private boolean updateFonctionEmpl(int idFonc, int idEmpl, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe) + " SET id_fonction=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idFonc);
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

	private OperateurC setOperateur(ResultSet rs) throws SQLException {
		OperateurC op = new OperateurC();
		op.setId(rs.getInt("id"));
		op.setLogin(rs.getString("login"));
		op.setCodeSecret(rs.getString("pwd"));
		op.setIdEmploye(rs.getInt("id_employe"));
		op.setActif(rs.getBoolean("actif"));

		return op;
	}

	public OperateurC getOperateur(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OperateurC oper = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.operateur) + " WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				oper = setOperateur(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return oper;
	}

	public OperateurC getOperateur(String login) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OperateurC oper = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.operateur) + " WHERE login=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			if (rs.next()) {
				oper = setOperateur(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return oper;
	}

	public OperateurC getOperateur(String login, String codeSecret) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OperateurC oper = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.operateur) + " WHERE login=? AND pwd=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, codeSecret);
			rs = stmt.executeQuery();
			if (rs.next()) {
				oper = setOperateur(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return oper;
	}

	public OperateurC getLineOperateur(String login, String pwd) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OperateurC oper = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.operateur)
				+ " WHERE login=? AND line_user=1 AND pwd=? AND actif=1";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if (rs.next()) {
				oper = setOperateur(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return oper;
	}

	public OperateurC getOperateur(EmployeC emp) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OperateurC oper = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.operateur)
				+ " WHERE id_employe=? AND actif=1";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, emp.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				oper = setOperateur(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return oper;
	}

	public OperateurC getLineOperateur(String login) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OperateurC oper = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.operateur)
				+ " WHERE login=? AND line_user=1";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			if (rs.next()) {
				oper = setOperateur(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return oper;
	}

	public boolean searchUserExist() {
		boolean b = false;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.operateur);

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				b = true;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(stmt, rs);
		}

		return b;
	}

	public boolean deleteOperateur(OperateurC op) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			saved = deleteNotAutocommit(op.getId(), Tables.getTableName(Tables.TableName.operateur));
			if (saved) {

				op.getHistorique().setIdLigne(op.getId());
				saved = insertHistorique(op.getHistorique(), conn);
			}
			if (saved) {

				con.setAutoCommit(true);
			} else {

				con.rollback();
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	public AffectationC getFonctionEmploye(EmployeC fonctionnaire, int idFonction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AffectationC affectation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.affectation)
				+ " WHERE id_employe=? AND id_fonction=? ";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, fonctionnaire.getId());
			stmt.setInt(2, idFonction);

			rs = stmt.executeQuery();
			if (rs.next()) {
				affectation = setAffectation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return affectation;
	}

	private boolean insertCoordonneesSociete(CoordonneesSocieteC coord) {
		boolean saved = false;
		PreparedStatement stmt = null;
		coord.setId(getId(Tables.getTableName(Tables.TableName.coordonneesSociete)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.coordonneesSociete)
				+ " (id,code,representant,nom_societe,adresse," + "telephone_mobile,telephone_fixe,fax,e_mail,"
				+ "no_empl_inss,logo) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, coord.getId());
			stmt.setString(2, coord.getCode());
			stmt.setString(3, coord.getRepresentant());
			stmt.setString(4, coord.getNomSociete());
			stmt.setString(5, coord.getAdresse());
			stmt.setString(6, coord.getTelephoneMobile());
			stmt.setString(7, coord.getTelephoneFixe());
			stmt.setString(8, coord.getFax());
			stmt.setString(9, coord.getEmail());
			stmt.setString(10, coord.getNoEmployeurInss());
			stmt.setString(11, coord.getLogo());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateCoordonneesSociete(CoordonneesSocieteC coord) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.coordonneesSociete)
				+ " SET representant=?,nom_societe=?,adresse=?,telephone_mobile=?,"
				+ "telephone_fixe=?,fax=?,e_mail=?,no_empl_inss=?,logo=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, coord.getRepresentant());
			stmt.setString(2, coord.getNomSociete());
			stmt.setString(3, coord.getAdresse());
			stmt.setString(4, coord.getTelephoneMobile());
			stmt.setString(5, coord.getTelephoneFixe());
			stmt.setString(6, coord.getFax());
			stmt.setString(7, coord.getEmail());
			stmt.setString(8, coord.getNoEmployeurInss());
			stmt.setString(9, coord.getLogo());
			stmt.setInt(10, coord.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateCoordonneesSociete(CoordonneesSocieteC coord) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (coord.getId() == 0) {

				saved = insertCoordonneesSociete(coord);
			} else {

				saved = updateCoordonneesSociete(coord);
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

	public CoordonneesSocieteC getCoordonneesSociete() {
		PreparedStatement stmt = null;
		CoordonneesSocieteC coord = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.coordonneesSociete);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next();) {
				coord = setCoordonneesSociete(rs);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return coord;
	}

	public CoordonneesSocieteC getCoordonneesSociete(int id) {
		CoordonneesSocieteC coord = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.coordonneesSociete) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				coord = setCoordonneesSociete(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return coord;
	}

	public CoordonneesSocieteC getCoordonneesSociete(String code) {
		CoordonneesSocieteC coord = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.coordonneesSociete) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				coord = setCoordonneesSociete(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return coord;
	}

	private CoordonneesSocieteC setCoordonneesSociete(ResultSet rs) throws SQLException {
		CoordonneesSocieteC coord = new CoordonneesSocieteC();
		coord.setId(rs.getInt("id"));
		coord.setCode(rs.getString("code"));
		coord.setRepresentant(rs.getString("representant"));
		coord.setNomSociete(rs.getString("nom_societe"));
		coord.setAdresse(rs.getString("adresse"));
		coord.setTelephoneMobile(rs.getString("telephone_mobile"));
		coord.setTelephoneFixe(rs.getString("telephone_fixe"));
		coord.setFax(rs.getString("fax"));
		coord.setEmail(rs.getString("e_mail"));
		coord.setLogo(rs.getString("logo"));
		coord.setNoEmployeurInss(rs.getString("no_empl_inss"));
		return coord;
	}

	private boolean insertExercice(ExerciceC exer) {
		boolean saved = false;
		PreparedStatement stmt = null;
		exer.setId(getId(Tables.getTableName(Tables.TableName.exercice)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.exercice) + " "
				+ "(id,code,designation,date_debut,date_fin,exercice_precendent,"
				+ "id_operateur_creation,date_creation) VALUES (?,?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, exer.getId());
			stmt.setString(2, exer.getCode());
			stmt.setString(3, exer.getDesignation());
			stmt.setObject(4, exer.getDateDebut());
			stmt.setObject(5, exer.getDateFin());
			stmt.setString(6, exer.getExercicePrecedent());
			if (exer.getOperCreation() != null) {

				stmt.setInt(7, exer.getOperCreation().getId());
			} else {

				stmt.setObject(7, (Object) null);
			}
			stmt.setObject(8, exer.getDateCreation());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateExercice(ExerciceC exer) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.exercice) + " SET code=?,designation=?, "
				+ "date_debut=?, date_fin=?, exercice_precendent=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, exer.getCode());
			stmt.setString(2, exer.getDesignation());
			stmt.setObject(3, exer.getDateDebut());
			stmt.setObject(4, exer.getDateFin());
			stmt.setString(5, exer.getExercicePrecedent());
			stmt.setInt(6, exer.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateExercice(ExerciceC exer) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (exer.getId() == 0) {

				saved = insertExercice(exer);
			} else {

				saved = updateExercice(exer);
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

	private ExerciceC setExercice(ResultSet rs) throws SQLException {
		ExerciceC exer = new ExerciceC();
		exer.setId(rs.getInt("id"));
		exer.setCode(rs.getString("code"));
		exer.setDesignation(rs.getString("designation"));
		exer.setDateDebut(rs.getDate("date_debut"));
		exer.setDateFin(rs.getDate("date_fin"));
		if (rs.getObject("exercice_precendent") != null) {
			exer.setExercicePrecedent(rs.getString("exercice_precendent"));
		}
		return exer;
	}

	public ExerciceC getExercice(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ExerciceC exer = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.exercice) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				exer = setExercice(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return exer;
	}

	public ExerciceC getExercice(String code) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ExerciceC exer = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.exercice) + " WHERE code=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				exer = setExercice(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return exer;
	}

	public ExerciceC getExerciceNotCurrent(String code, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ExerciceC exer = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.exercice) + " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				exer = setExercice(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return exer;
	}

	public ExerciceC getExerciceByDesignation(String designation, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ExerciceC exer = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.exercice)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				exer = setExercice(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return exer;
	}

	public ExerciceC getExerciceNotCurrent(Date date, int id) {
		Statement stmt = null;
		ResultSet rs = null;
		ExerciceC exer = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM " + Tables.getTableName(Tables.TableName.exercice)
					+ " WHERE date_debut<='" + HelperC.convertDate(date, false) + "' AND date_fin >='"
					+ HelperC.convertDate(date, false) + "' AND id<>" + id);
			if (rs.next()) {
				exer = setExercice(rs);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return exer;
	}

	public ExerciceC getExercice(Date date) {
		Statement stmt = null;
		ResultSet rs = null;
		ExerciceC exer = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM " + Tables.getTableName(Tables.TableName.exercice)
					+ " WHERE date_debut<='" + HelperC.convertDate(date, false) + "' AND date_fin >='"
					+ HelperC.convertDate(date, false) + "'");
			if (rs.next()) {
				exer = setExercice(rs);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return exer;
	}

	public List<ExerciceC> getListExercice() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ExerciceC> listExercice = new ArrayList<ExerciceC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.exercice) + "  ORDER BY code DESC";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				listExercice.add(setExercice(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return listExercice;
	}

	private boolean insertHistorique(Historique hist, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		hist.setId(getId(Tables.getTableName(Tables.TableName.historique)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.historique)
				+ " (id,date_operation,id_operateur,operation,table_name,id_ligne) " + "VALUES (?,?,?,?,?,?) ";

		try {
			stmt = conx.prepareStatement(sql);
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
		} finally {

			releaseResource(stmt, null);
		}

		return saved;
	}

	private Historique setHistorique(ResultSet rs) throws SQLException {
		Historique hist = new Historique();
		hist.setId(rs.getInt("id"));
		hist.setDateOperation(rs.getDate("date_operation"));
		hist.setOperation(rs.getString("operation"));
		hist.setTable(rs.getString("table_name"));
		hist.setIdLigne(rs.getInt("id_ligne"));
		return hist;
	}

	public List<Historique> getHistoriques(OperateurC oper, Date dateDebut, Date dateFin) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Historique> historiques = new ArrayList<Historique>();
		String sql = "SELECT A.* FROM " + Tables.getTableName(Tables.TableName.historique)
				+ " AS A WHERE A.id_operateur=? " + HelperC.FiltreDate(dateDebut, dateFin, "A", "date_operation")
				+ " ORDER BY A.date_operation";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, oper.getId());
			for (rs = stmt.executeQuery(); rs.next(); historiques.add(setHistorique(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return historiques;
	}

	public boolean insertUpdateDroit(RoleC role) {
		boolean saved = true;

		try {
			con.setAutoCommit(false);
			for (DroitC d : role.getDetails()) {

				d.setFonction(role.getFonction());
				if (d.isCreer() || d.isModifier() || d.isSupprimer()) {

					if (d.getId() == 0) {

						saved = insertDroit(d);
						if (!saved) {
							d.setId(0);
						}
					} else {

						saved = updateDroit(d);
					}

				} else if (d.getId() > 0) {

					saved = deleteNotAutocommit(d.getId(), Tables.getTableName(Tables.TableName.droit));
				}
				if (!saved) {
					break;
				}
			}

			if (saved) {

				con.setAutoCommit(true);
			} else {

				con.rollback();
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private boolean insertDroit(DroitC droit) {
		PreparedStatement pstmt = null;
		boolean saved = false;
		droit.setId(getId(Tables.getTableName(Tables.TableName.droit)));
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.droit)
				+ " (id,id_fonction,role,creer,modifier,supprimer,editer)" + " VALUES (?,?,?,?,?,?,?) ";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, droit.getId());
			pstmt.setInt(2, droit.getFonction().getId());
			pstmt.setInt(3, Constante.getRole(droit.getRol()));
			pstmt.setBoolean(4, droit.isCreer());
			pstmt.setBoolean(5, droit.isModifier());
			pstmt.setBoolean(6, droit.isSupprimer());
			pstmt.setBoolean(7, droit.isAfficher());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateDroit(DroitC droit) {
		PreparedStatement pstmt = null;
		boolean saved = false;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.droit)
				+ " SET creer=?,modifier=?,supprimer=?,editer=? WHERE id=? ";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setBoolean(1, droit.isCreer());
			pstmt.setBoolean(2, droit.isModifier());
			pstmt.setBoolean(3, droit.isSupprimer());
			pstmt.setBoolean(4, droit.isAfficher());
			pstmt.setInt(5, droit.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private DroitC setDroit(ResultSet rs) throws SQLException {
		DroitC droit = new DroitC();
		droit.setId(rs.getInt("id"));
		Base fonction = new Base();
		fonction.setId(rs.getInt("id_fonction"));
		droit.setFonction(fonction);
		droit.setRol(Constante.getRole(rs.getInt("role")));
		droit.setCreer(rs.getBoolean("creer"));
		droit.setModifier(rs.getBoolean("modifier"));
		droit.setSupprimer(rs.getBoolean("supprimer"));
		droit.setAfficher(rs.getBoolean("editer"));
		return droit;
	}

	public List<DroitC> getListeDroit(Base fonction) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DroitC> droits = new ArrayList<DroitC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.droit) + " WHERE id_fonction="
				+ fonction.getId();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			for (; rs.next(); droits.add(setDroit(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return droits;
	}

	public DroitC getDroit(int idFx, Constante.Role r) {
		Statement stmt = null;
		ResultSet rs = null;
		DroitC droit = null;
		String sql = "";

		sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.droit) + " WHERE role=" + Constante.getRole(r)
				+ " AND id_fonction=" + idFx;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				droit = setDroit(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return droit;
	}

	public boolean insertUpdateParametrageGeneral(ParametrageGeneralC parametre) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (parametre.getId() == 0) {

				saved = insertParametrageGeneral(parametre);
			} else {

				saved = updateParametrageGeneral(parametre);
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

	private boolean insertParametrageGeneral(ParametrageGeneralC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametrageGeneral)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageGeneral)
				+ " (id,nbr_decimal,taux_max_logement,logement_inclut_hsup,allocation_inclut_hsup,"
				+ "dure_cour_terme,dure_moyen_terme,dure_long_terme,montant_net_min,nombre_heure_jour,"
				+ "nombre_heure_mois,nombre_jour_mois,taux_jour_ferie,mail_source,pwd_source,"
				+ "serveur_smtp,server_port) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getId());
			stmt.setInt(2, parametrage.getNbreDecimal());
			stmt.setDouble(3, parametrage.getTauxMaxLogement());
			stmt.setBoolean(4, parametrage.isLogementBaseHsup());
			stmt.setBoolean(5, parametrage.isAllocationBaseHsup());
			stmt.setInt(6, parametrage.getDureCourTerme());
			stmt.setInt(7, parametrage.getDureMoyenTerme());
			stmt.setInt(8, parametrage.getDureLongTerme());
			stmt.setDouble(9, parametrage.getMontantNetMin());
			stmt.setInt(10, parametrage.getNbreHeureJour());
			stmt.setInt(11, parametrage.getNbreHeureMois());
			stmt.setInt(12, parametrage.getNbreJourMois());
			stmt.setDouble(13, parametrage.getTauxJrFerie());
			stmt.setString(14, parametrage.getMailOrigine());
			stmt.setString(15, parametrage.getPwdOrigine());
			stmt.setString(16, parametrage.getSmtpServer());
			stmt.setString(17, parametrage.getPort());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametrageGeneral(ParametrageGeneralC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageGeneral)
				+ " SET nbr_decimal=?,taux_max_logement=?,logement_inclut_hsup=?,allocation_inclut_hsup=?,"
				+ "dure_cour_terme=?,dure_moyen_terme=?,dure_long_terme=?,montant_net_min=?,nombre_heure_jour=?,"
				+ "nombre_heure_mois=?,nombre_jour_mois=?,taux_jour_ferie=?,mail_source=?,pwd_source=?,"
				+ "serveur_smtp=?,server_port=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getNbreDecimal());
			stmt.setDouble(2, parametrage.getTauxMaxLogement());
			stmt.setBoolean(3, parametrage.isLogementBaseHsup());
			stmt.setBoolean(4, parametrage.isAllocationBaseHsup());
			stmt.setInt(5, parametrage.getDureCourTerme());
			stmt.setInt(6, parametrage.getDureMoyenTerme());
			stmt.setInt(7, parametrage.getDureLongTerme());
			stmt.setDouble(8, parametrage.getMontantNetMin());
			stmt.setInt(9, parametrage.getNbreHeureJour());
			stmt.setInt(10, parametrage.getNbreHeureMois());
			stmt.setInt(11, parametrage.getNbreJourMois());
			stmt.setDouble(12, parametrage.getTauxJrFerie());
			stmt.setString(13, parametrage.getMailOrigine());
			stmt.setString(14, parametrage.getPwdOrigine());
			stmt.setString(15, parametrage.getSmtpServer());
			stmt.setString(16, parametrage.getPort());

			stmt.setInt(17, parametrage.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private ParametrageGeneralC setParametrageGeneral(ResultSet rs) throws SQLException {
		ParametrageGeneralC prm = new ParametrageGeneralC();
		prm.setId(rs.getInt("id"));
		prm.setNbreDecimal(rs.getInt("nbr_decimal"));
		prm.setTauxMaxLogement(rs.getDouble("taux_max_logement"));
		prm.setAllocationBaseHsup(rs.getBoolean("allocation_inclut_hsup"));
		prm.setLogementBaseHsup(rs.getBoolean("logement_inclut_hsup"));
		prm.setDureCourTerme(rs.getInt("dure_cour_terme"));
		prm.setDureMoyenTerme(rs.getInt("dure_moyen_terme"));
		prm.setDureLongTerme(rs.getInt("dure_long_terme"));
		prm.setMontantNetMin(rs.getDouble("montant_net_min"));
		prm.setNbreHeureJour(rs.getInt("nombre_heure_jour"));
		prm.setNbreHeureMois(rs.getInt("nombre_heure_mois"));
		prm.setNbreJourMois(rs.getInt("nombre_jour_mois"));
		prm.setTauxJrFerie(rs.getDouble("taux_jour_ferie"));
		prm.setMailOrigine(rs.getString("mail_source"));
		prm.setPwdOrigine(rs.getString("pwd_source"));
		prm.setSmtpServer(rs.getString("serveur_smtp"));
		prm.setPort(rs.getString("server_port"));

		return prm;
	}

	public ParametrageGeneralC getParametrageGeneral() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageGeneralC prmtr = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageGeneral);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prmtr = setParametrageGeneral(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return prmtr;
	}

	public boolean deleteParametrage(ParametrageGeneralC parmetre) {
		boolean deleted = false;
		Statement stmt = null;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.parametrageGeneral) + " WHERE id="
				+ parmetre.getId();

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

	private boolean insertBanque(BanqueC banque) {
		boolean saved = false;
		PreparedStatement stmt = null;
		banque.setId(getId(Tables.getTableName(Tables.TableName.banque)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.banque)
				+ " (id,code,designation,telephone1,telephone2,adresse,compte,"
				+ "banque_virement) VALUES (?,?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, banque.getId());
			stmt.setString(2, banque.getCode());
			stmt.setString(3, banque.getDesignation());
			stmt.setString(4, banque.getTelephone1());
			stmt.setString(5, banque.getTelephone2());
			stmt.setString(6, banque.getAdresse());
			stmt.setString(7, banque.getCompte());
			stmt.setString(8, banque.getBanqueVirement());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateBanque(BanqueC banque) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.banque)
				+ " SET designation=?, telephone1=?, telephone2=?,adresse=?,compte=?,"
				+ "banque_virement=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, banque.getDesignation());
			stmt.setString(2, banque.getTelephone1());
			stmt.setString(3, banque.getTelephone2());
			stmt.setString(4, banque.getAdresse());
			stmt.setString(5, banque.getCompte());
			stmt.setString(6, banque.getBanqueVirement());
			stmt.setInt(7, banque.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateBanque(BanqueC banque) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (banque.getId() == 0) {

				saved = insertBanque(banque);
			} else {

				saved = updateBanque(banque);
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

	private BanqueC setBanque(ResultSet rs) throws SQLException {
		BanqueC banque = new BanqueC();
		banque.setId(rs.getInt("id"));
		banque.setCode(rs.getString("code"));
		banque.setDesignation(rs.getString("designation"));
		banque.setTelephone1(rs.getString("telephone1"));
		banque.setTelephone2(rs.getString("telephone2"));
		banque.setAdresse(rs.getString("adresse"));
		banque.setCompte(rs.getString("compte"));
		banque.setBanqueVirement(rs.getString("banque_virement"));
		return banque;
	}

	public BanqueC getBanque(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BanqueC banque = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				banque = setBanque(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return banque;
	}

	public BanqueC getBanqueNotCurrent(String code, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BanqueC banque = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " WHERE code=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				banque = setBanque(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return banque;
	}

	public BanqueC getBanqueByDesignation(String designation, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BanqueC banque = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " WHERE designation=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				banque = setBanque(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return banque;
	}

	public BanqueC getBanque(String code) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BanqueC banque = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				banque = setBanque(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return banque;
	}

	public BanqueC getBanqueSociete() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BanqueC banque = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " WHERE compte<>'' ";

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.next()) {
				banque = setBanque(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return banque;
	}

	public List<BanqueC> getBanque(String code, String nom) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BanqueC> liste = new ArrayList<BanqueC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " WHERE 1=1";
		if (!code.equals("")) {
			sql = String.valueOf(sql) + " AND code like '%" + code + "%'";
		}
		if (!nom.equals("")) {
			sql = String.valueOf(sql) + " AND designation like '%" + nom + "%'";
		}

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setBanque(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<BanqueC> getBanques(String codeDebut, String codeFin) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BanqueC> liste = new ArrayList<BanqueC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " ban WHERE 1=1";
		if (codeDebut != "" && codeFin != "") {
			sql = String.valueOf(sql) + HelperC.FiltreCode(codeDebut, codeFin, "ban", "code");
		}

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setBanque(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<BanqueC> getAllBanque() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BanqueC> liste = new ArrayList<BanqueC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " ORDER BY code,designation ";

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setBanque(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<BanqueC> getBanques() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BanqueC> liste = new ArrayList<BanqueC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.banque) + " ORDER BY code,designation";

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setBanque(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertTypeCredit(TypeCreditC typ) {
		boolean saved = false;
		PreparedStatement stmt = null;
		typ.setId(getId(Tables.getTableName(Tables.TableName.typeCredit)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.typeCredit)
				+ " (id,code,libelle,terme,taux_minimum,taux_maximum,"
				+ "centraliser_ecriture_en_comptabilite,affecter_deuxieme_compte_credit) "
				+ "VALUES (?,?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, typ.getId());
			stmt.setString(2, typ.getCode());
			stmt.setString(3, typ.getLibelle());
			stmt.setString(4, typ.getTerme());
			stmt.setDouble(5, typ.getTauxMinimum());
			stmt.setDouble(6, typ.getTauxMaximum());
			stmt.setBoolean(7, typ.isCentraliserEcritureEnComptabilite());
			stmt.setBoolean(8, typ.isAffecterDeuxiemeCompteCredit());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateTypeCredit(TypeCreditC typ) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.typeCredit) + " SET libelle=?, "
				+ "terme=?, taux_minimum=?, taux_maximum=?,centraliser_ecriture_en_comptabilite=?,"
				+ "affecter_deuxieme_compte_credit=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, typ.getLibelle());
			stmt.setString(2, typ.getTerme());
			stmt.setDouble(3, typ.getTauxMinimum());
			stmt.setDouble(4, typ.getTauxMaximum());
			stmt.setBoolean(5, typ.isCentraliserEcritureEnComptabilite());
			stmt.setBoolean(6, typ.isAffecterDeuxiemeCompteCredit());
			stmt.setInt(7, typ.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateTypeCredit(TypeCreditC typ) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (typ.getId() == 0) {

				saved = insertTypeCredit(typ);
			} else {

				saved = updateTypeCredit(typ);
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

	public TypeCreditC getTypeCredit(int id) {
		PreparedStatement stmt = null;
		TypeCreditC typ = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCredit) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typ = setTypeCredit(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typ;
	}

	public TypeCreditC getTypeCredit(String code) {
		PreparedStatement stmt = null;
		TypeCreditC typ = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCredit) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typ = setTypeCredit(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typ;
	}

	public TypeCreditC getTypeCreditNotCurrent(String code, int id) {
		PreparedStatement stmt = null;
		TypeCreditC typ = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCredit) + " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typ = setTypeCredit(rs);

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typ;
	}

	public TypeCreditC getTypeCreditByLibelle(String designation, int id) {
		PreparedStatement stmt = null;
		TypeCreditC typ = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCredit)
				+ " WHERE libelle=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typ = setTypeCredit(rs);

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typ;
	}

	public List<TypeCreditC> getAllTypeCredit(String code, String nom) {
		PreparedStatement stmt = null;
		List<TypeCreditC> liste = new ArrayList<TypeCreditC>();
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCredit) + " WHERE code like '%" + code
				+ "%' OR libelle like '%" + nom + "%'";

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setTypeCredit(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<TypeCreditC> getTypeCredit(String codeDebut, String codeFin) {
		PreparedStatement stmt = null;
		List<TypeCreditC> liste = new ArrayList<TypeCreditC>();
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCredit) + " cred WHERE 1=1";
		if (codeDebut != "" && codeFin != "") {
			sql = String.valueOf(sql) + HelperC.FiltreCode(codeDebut, codeFin, "cred", "code");
		}

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setTypeCredit(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<TypeCreditC> getAllTypeCredit() {
		ResultSet rs = null;
		List<TypeCreditC> liste = new ArrayList<TypeCreditC>();
		Statement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCredit);

		try {
			stmt = con.createStatement();
			for (rs = stmt.executeQuery(sql); rs.next(); liste.add(setTypeCredit(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private TypeCreditC setTypeCredit(ResultSet rs) throws SQLException {
		TypeCreditC typ = new TypeCreditC();
		typ.setId(rs.getInt("id"));
		typ.setCode(rs.getString("code"));
		typ.setLibelle(rs.getString("libelle"));
		typ.setTerme(rs.getString("terme"));
		typ.setTauxMinimum(rs.getDouble("taux_minimum"));
		typ.setTauxMinimumString(HelperC.TraitementMontant.getMontantFormate(typ.getTauxMinimum(), 0));
		typ.setTauxMaximum(rs.getDouble("taux_maximum"));
		typ.setTauxMaximumString(HelperC.TraitementMontant.getMontantFormate(typ.getTauxMaximum(), 0));
		typ.setCentraliserEcritureEnComptabilite(rs.getBoolean("centraliser_ecriture_en_comptabilite"));
		typ.setAffecterDeuxiemeCompteCredit(rs.getBoolean("affecter_deuxieme_compte_credit"));
		return typ;
	}

	private boolean insertLieuxTravail(LieuxTravailC lieu) {
		boolean saved = false;
		PreparedStatement stmt = null;
		lieu.setId(getId(Tables.getTableName(Tables.TableName.lieuxTravail)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.lieuxTravail)
				+ " (id,code,designation,avenue) VALUES (?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, lieu.getId());
			stmt.setString(2, lieu.getCode());
			stmt.setString(3, lieu.getDesignation());
			stmt.setString(4, lieu.getAvenue());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateLieuxTravail(LieuxTravailC lieu) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.lieuxTravail) + " "
				+ "SET designation=?,avenue=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, lieu.getDesignation());
			stmt.setString(2, lieu.getAvenue());
			stmt.setInt(3, lieu.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateLieuxTravail(LieuxTravailC lieu) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (lieu.getId() == 0) {

				saved = insertLieuxTravail(lieu);
			} else {

				saved = updateLieuxTravail(lieu);
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

	private LieuxTravailC setLieuxTravail(ResultSet rs) throws SQLException {
		LieuxTravailC lieu = new LieuxTravailC();
		lieu.setId(rs.getInt("id"));
		lieu.setCode(rs.getString("code"));
		lieu.setDesignation(rs.getString("designation"));
		lieu.setAvenue(rs.getString("avenue"));
		return lieu;
	}

	public LieuxTravailC getLieuxTravail(int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		LieuxTravailC lieu = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.lieuxTravail) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				lieu = setLieuxTravail(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return lieu;
	}

	public LieuxTravailC getLieuxTravail(String code) {
		ResultSet rs = null;
		LieuxTravailC lieu = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.lieuxTravail) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				lieu = setLieuxTravail(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return lieu;
	}

	public LieuxTravailC getLieuxTravailByDesignation(String designation, int id) {
		ResultSet rs = null;
		LieuxTravailC lieu = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.lieuxTravail)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				lieu = setLieuxTravail(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return lieu;
	}

	public LieuxTravailC getLieuxTravail(String code, int id) {
		ResultSet rs = null;
		LieuxTravailC lieu = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.lieuxTravail) + " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				lieu = setLieuxTravail(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return lieu;
	}

	public List<LieuxTravailC> getLieuxTravail(String codeDebut, String codeFin) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<LieuxTravailC> liste = new ArrayList<LieuxTravailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.lieuxTravail) + " lieu WHERE 1=1";
		if (codeDebut != "" && codeFin != "") {
			sql = String.valueOf(sql) + HelperC.FiltreCode(codeDebut, codeFin, "lieu", "code");
		}

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setLieuxTravail(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<LieuxTravailC> getAllLieuxTravail() {
		PreparedStatement stmt = null;
		List<LieuxTravailC> liste = new ArrayList<LieuxTravailC>();
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.lieuxTravail);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setLieuxTravail(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertDevise(DeviseC dev) {
		boolean saved = false;
		PreparedStatement stmt = null;
		dev.setId(getId(Tables.getTableName(Tables.TableName.devise)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.devise)
				+ " (id,code,designation,symbole) VALUES (?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, dev.getId());
			stmt.setString(2, dev.getCode());
			stmt.setString(3, dev.getDesignation());
			stmt.setString(4, dev.getSymbole());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDevise(DeviseC dev) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.devise)
				+ " SET designation=?, symbole=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dev.getDesignation());
			stmt.setString(2, dev.getSymbole());
			stmt.setInt(3, dev.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateDevise(DeviseC dev) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (dev.getId() == 0) {

				saved = insertDevise(dev);
			} else {

				saved = updateDevise(dev);
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

	public DeviseC getDevise(int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		DeviseC dev = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.devise) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				dev = setDevise(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return dev;
	}

	public DeviseC getDevise(String code) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		DeviseC dev = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.devise) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				dev = setDevise(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return dev;
	}

	public List<DeviseC> getListDevise(String code, String nom) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<DeviseC> liste = new ArrayList<DeviseC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.devise) + " WHERE 1=1";
		if (code != null && !code.trim().equals("")) {
			sql = String.valueOf(sql) + " AND code LIKE '%" + code + "'%";
		}
		if (nom != null && !nom.trim().equals("")) {
			sql = String.valueOf(sql) + " AND designation LIKE '%" + nom + "'%";
		}

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setDevise(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<DeviseC> getDevises(String codeDebut, String codeFin) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DeviseC> liste = new ArrayList<DeviseC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.devise) + " devi WHERE 1=1";
		if (codeDebut != "" && codeFin != "") {
			sql = String.valueOf(sql) + HelperC.FiltreCode(codeDebut, codeFin, "devi", "code");
		}

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setDevise(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public DeviseC getDeviseNotCurrent(String code, int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		DeviseC dev = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.devise) + " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				dev = setDevise(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return dev;
	}

	public DeviseC getDeviseByDesignation(String designation, int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		DeviseC dev = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.devise)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				dev = setDevise(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return dev;
	}

	public List<DeviseC> getAllDevise() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<DeviseC> liste = new ArrayList<DeviseC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.devise);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setDevise(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private DeviseC setDevise(ResultSet rs) throws SQLException {
		DeviseC dev = new DeviseC();
		dev.setId(rs.getInt("id"));
		dev.setCode(rs.getString("code"));
		dev.setDesignation(rs.getString("designation"));
		dev.setSymbole(rs.getString("symbole"));
		return dev;
	}

	private boolean insertPays(PaysC pays) {
		boolean saved = false;
		PreparedStatement stmt = null;
		pays.setId(getId(Tables.getTableName(Tables.TableName.pays)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.pays)
				+ " (id,designation,continent,nationalite) VALUES (?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, pays.getId());
			stmt.setString(2, pays.getDesignation());
			stmt.setString(3, pays.getContinent());
			stmt.setString(4, pays.getNationalite());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updatePays(PaysC pays) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.pays)
				+ " SET designation=?, continent=?, nationalite=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pays.getDesignation());
			stmt.setString(2, pays.getContinent());
			stmt.setString(3, pays.getNationalite());
			stmt.setInt(4, pays.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdatePays(PaysC pays) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (pays.getId() == 0) {

				saved = insertPays(pays);
			} else {

				saved = updatePays(pays);
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

	private PaysC setPays(ResultSet rs) throws SQLException {
		PaysC pays = new PaysC();
		pays.setId(rs.getInt("id"));
		pays.setDesignation(rs.getString("designation"));
		pays.setContinent(rs.getString("continent"));
		pays.setNationalite(rs.getString("nationalite"));
		return pays;
	}

	public PaysC getPays(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PaysC pays = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.pays) + " WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				pays = setPays(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return pays;
	}

	public PaysC getPays(String designation, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PaysC pays = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.pays) + " WHERE designation=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				pays = setPays(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return pays;
	}

	public List<PaysC> getPays(String designation, String continent) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PaysC> listePays = new ArrayList<PaysC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.pays) + " WHERE designation LIKE '%"
				+ designation + "%' AND continent LIKE '%" + continent.trim() + "%'"
				+ " ORDER BY designation,continent";

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); listePays.add(setPays(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listePays;
	}

	public List<PaysC> getPays() {
		List<PaysC> list = new ArrayList<PaysC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.pays) + " ORDER BY designation  ";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); list.add(setPays(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e);
		} finally {

			releaseResource(stmt, rs);
		}
		return list;
	}

	private boolean insertTypeFraisMedicaux(TypeFraisMedicauxC typeF) {
		boolean saved = false;
		PreparedStatement stmt = null;
		typeF.setId(getId(Tables.getTableName(Tables.TableName.typeFraisMedicaux)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.typeFraisMedicaux)
				+ " (id,code,designation,pourcentage,montant) VALUES (?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, typeF.getId());
			stmt.setString(2, typeF.getCode());
			stmt.setString(3, typeF.getDesignation());
			stmt.setInt(4, typeF.getPourcentage());
			stmt.setDouble(5, typeF.getMontant());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateTypeFraisMedicaux(TypeFraisMedicauxC typeF) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.typeFraisMedicaux)
				+ " SET designation=?, pourcentage=?, montant=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, typeF.getDesignation());
			stmt.setInt(2, typeF.getPourcentage());
			stmt.setDouble(3, typeF.getMontant());
			stmt.setInt(4, typeF.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateTypeFraisMedicaux(TypeFraisMedicauxC typeF) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (typeF.getId() == 0) {

				saved = insertTypeFraisMedicaux(typeF);
			} else {

				saved = updateTypeFraisMedicaux(typeF);
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

	public TypeFraisMedicauxC getTypeFraisMedicaux(int id) {
		ResultSet rs = null;
		TypeFraisMedicauxC typeF = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeFraisMedicaux) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typeF = setTypeFraisMedicaux(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typeF;
	}

	public TypeFraisMedicauxC getTypeFraisMedicaux(String code) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		TypeFraisMedicauxC typeF = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeFraisMedicaux) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typeF = setTypeFraisMedicaux(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typeF;
	}

	public TypeFraisMedicauxC getTypeFraisMedicaux(String code, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TypeFraisMedicauxC typeF = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeFraisMedicaux)
				+ " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typeF = setTypeFraisMedicaux(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typeF;
	}

	public TypeFraisMedicauxC getTypeFraisMedicauxByDesignation(String designation, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TypeFraisMedicauxC typeF = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeFraisMedicaux)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typeF = setTypeFraisMedicaux(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typeF;
	}

	public List<TypeFraisMedicauxC> getAllTypeFraisMedicaux() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<TypeFraisMedicauxC> liste = new ArrayList<TypeFraisMedicauxC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeFraisMedicaux);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setTypeFraisMedicaux(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private TypeFraisMedicauxC setTypeFraisMedicaux(ResultSet rs) throws SQLException {
		TypeFraisMedicauxC typeF = new TypeFraisMedicauxC();
		typeF.setId(rs.getInt("id"));
		typeF.setCode(rs.getString("code"));
		typeF.setDesignation(rs.getString("designation"));
		typeF.setPourcentage(rs.getInt("pourcentage"));
		typeF.setPourcentageS(HelperC.TraitementMontant.getMontantFormate(typeF.getPourcentage(), 0));
		typeF.setMontant(rs.getDouble("montant"));
		typeF.setMontantS(HelperC.TraitementMontant.getMontantFormate(typeF.getMontant(), 0));
		return typeF;
	}

	private boolean insertBareme(BaremeIPRC bar) {
		boolean saved = false;
		PreparedStatement stmt = null;
		bar.setId(getId(Tables.getTableName(Tables.TableName.bareme)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.bareme)
				+ " (id,numero,type_bareme,libelle,type_employe,actif) VALUES (?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bar.getId());
			stmt.setString(2, bar.getNumero());
			stmt.setInt(3, bar.getType());
			stmt.setString(4, bar.getDescription());
			stmt.setInt(5, bar.getTypeEmploye());
			stmt.setBoolean(6, bar.isActif());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateBareme(BaremeIPRC bar) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.bareme)
				+ " SET type_bareme=?,libelle=?,type_employe=?,actif=?  WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bar.getType());
			stmt.setString(2, bar.getDescription());
			stmt.setInt(3, bar.getTypeEmploye());
			stmt.setBoolean(4, bar.isActif());
			stmt.setInt(5, bar.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateBareme(BaremeIPRC bar) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			if (bar.getId() == 0) {

				saved = insertBareme(bar);
			} else {

				saved = updateBareme(bar);
			}
			if (saved) {
				for (DetailBaremeC deta : bar.getListDetailBareme()) {

					deta.setBareme(bar);
					if (deta.getId() == 0) {

						saved = insertDetailBareme(deta);
					} else {

						saved = updateDetailBareme(deta);
					}
					if (!saved) {
						break;
					}
				}
			}

			if (saved && bar.getListDetailBaremeDeleted() != null && bar.getListDetailBaremeDeleted().size() > 0) {
				for (DetailBaremeC deta : bar.getListDetailBaremeDeleted()) {

					if (deta.getId() > 0) {
						saved = deleteNotCommited(deta.getId(), Tables.getTableName(Tables.TableName.detailBareme));
					}
					if (!saved) {
						break;
					}
				}
			}

			if (saved) {

				bar.getHistorique().setIdLigne(bar.getId());
				saved = insertHistorique(bar.getHistorique(), conn);
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

	public BaremeIPRC getBaremeIRE(int typeEmpl) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		BaremeIPRC bar = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bareme)
				+ " WHERE type_employe=? AND actif=1 ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, typeEmpl);
			rs = stmt.executeQuery();

			if (rs.next()) {
				bar = setBareme(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (bar != null) {
			bar.setListDetailBareme(getListeDetailBareme(bar));
		}
		return bar;
	}

	public BaremeIPRC getBareme(int idBareme) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		BaremeIPRC bar = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bareme) + " WHERE id=" + idBareme;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				bar = setBareme(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (bar != null) {
			bar.setListDetailBareme(getListeDetailBareme(bar));
		}
		return bar;
	}

	public BaremeIPRC getBareme(String numro) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		BaremeIPRC bar = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bareme) + " WHERE numero='" + numro + "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				bar = setBareme(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (bar != null) {
			bar.setListDetailBareme(getListeDetailBareme(bar));
		}
		return bar;
	}

	public List<BaremeIPRC> getListBareme(boolean withDetails) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<BaremeIPRC> list = new ArrayList<BaremeIPRC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.bareme) + " WHERE actif=1";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next())
				list.add(setBareme(rs));

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (withDetails) {

			for (BaremeIPRC bar : list) {
				bar.setListDetailBareme(getListeDetailBareme(bar));
			}
		}

		return list;
	}

	public boolean deleteBareme(BaremeIPRC bareme) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			saved = deleteList(bareme.getId(), "id_bareme", Tables.getTableName(Tables.TableName.detailBareme), conn);
			saved = delete(bareme.getId(), Tables.getTableName(Tables.TableName.bareme), conn);

			if (saved) {
				conn.commit();
			} else {
				saved = false;
				conn.rollback();
			}

		} catch (SQLException e) {

			saved = false;
			System.out.println(e.getMessage());
		}
		return saved;
	}

	private BaremeIPRC setBareme(ResultSet rs) throws SQLException {
		BaremeIPRC bar = new BaremeIPRC();
		bar.setId(rs.getInt("id"));
		bar.setNumero(rs.getString("numero"));
		bar.setType(rs.getInt("type_bareme"));
		bar.setDescription(rs.getString("libelle"));
		bar.setTypeEmploye(rs.getInt("type_employe"));
		bar.setActif(rs.getBoolean("actif"));
		return bar;
	}

	private boolean insertDetailBareme(DetailBaremeC deta) {
		boolean saved = false;
		PreparedStatement stmt = null;
		deta.setId(getId(Tables.getTableName(Tables.TableName.detailBareme)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailBareme)
				+ "(id,id_bareme,borne_debut,borne_fin,pourcentage) VALUES (?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, deta.getId());
			if (deta.getBareme() != null) {

				stmt.setInt(2, deta.getBareme().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, deta.getBorneDebut());
			stmt.setDouble(4, deta.getBorneFin());
			stmt.setDouble(5, deta.getPourcentage());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailBareme(DetailBaremeC deta) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailBareme)
				+ " SET id_bareme=?,borne_debut=?, borne_fin=?, pourcentage=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			if (deta.getBareme() != null) {

				stmt.setInt(1, deta.getBareme().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setDouble(2, deta.getBorneDebut());
			stmt.setDouble(3, deta.getBorneFin());
			stmt.setDouble(4, deta.getPourcentage());
			stmt.setInt(5, deta.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public List<DetailBaremeC> getListeDetailBareme(BaremeIPRC bareme) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<DetailBaremeC> liste = new ArrayList<DetailBaremeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailBareme) + " WHERE id_bareme=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bareme.getId());
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setDetailBareme(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private DetailBaremeC setDetailBareme(ResultSet rs) throws SQLException {
		DetailBaremeC deta = new DetailBaremeC();
		deta.setId(rs.getInt("id"));
		deta.setBorneDebut(rs.getDouble("borne_debut"));
		deta.setBorneDebutString(HelperC.TraitementMontant.getMontantFormate(deta.getBorneDebut(), 0));
		deta.setBorneFin(rs.getDouble("borne_fin"));
		deta.setBorneFinString(HelperC.TraitementMontant.getMontantFormate(deta.getBorneFin(), 0));
		deta.setPourcentage(rs.getDouble("pourcentage"));
		deta.setPourcentageString(HelperC.TraitementMontant.getMontantFormate(deta.getPourcentage(), 0));
		return deta;
	}

	private boolean insertOrganismeSupportantBaseSalariale(OrganismeSupportantBaseSalarialC organisme) {
		boolean saved = false;
		PreparedStatement stmt = null;
		organisme.setId(getId(Tables.getTableName(Tables.TableName.organismesSociaux)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.organismesSociaux)
				+ " (id,code,designation,compte) VALUES (?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, organisme.getId());
			stmt.setString(2, organisme.getCode());
			stmt.setString(3, organisme.getDesignation());
			stmt.setString(4, organisme.getCompteCptbl());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateOrganismeSupportantBaseSalariale(OrganismeSupportantBaseSalarialC organisme) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.organismesSociaux)
				+ " SET designation=?,compte=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, organisme.getDesignation());
			stmt.setString(2, organisme.getCompteCptbl());
			stmt.setInt(3, organisme.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateOrganismeSupportantBaseSalariale(OrganismeSupportantBaseSalarialC organisme) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (organisme.getId() == 0) {

				saved = insertOrganismeSupportantBaseSalariale(organisme);
			} else {

				saved = updateOrganismeSupportantBaseSalariale(organisme);
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

	public OrganismeSupportantBaseSalarialC getOrganismeSupportantBaseSalariale(int id) {
		ResultSet rs = null;
		OrganismeSupportantBaseSalarialC organisme = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.organismesSociaux)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				organisme = setOrganismeSupportantBaseSalariale(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return organisme;
	}

	public OrganismeSupportantBaseSalarialC getOrganismeSupportantBaseSalariale(String code) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		OrganismeSupportantBaseSalarialC organisme = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.organismesSociaux)
				+ " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				organisme = setOrganismeSupportantBaseSalariale(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return organisme;
	}

	
	public OrganismeSupportantBaseSalarialC getOrganismesSupportantBaseSalariale(String designation, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OrganismeSupportantBaseSalarialC organisme = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.organismesSociaux)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				organisme = setOrganismeSupportantBaseSalariale(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return organisme;
	}

	public List<OrganismeSupportantBaseSalarialC> getAllOrganismeSupportantBaseSalarial() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<OrganismeSupportantBaseSalarialC> liste = new ArrayList<OrganismeSupportantBaseSalarialC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.organismesSociaux);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setOrganismeSupportantBaseSalariale(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private OrganismeSupportantBaseSalarialC setOrganismeSupportantBaseSalariale(ResultSet rs) throws SQLException {
		OrganismeSupportantBaseSalarialC organisme = new OrganismeSupportantBaseSalarialC();
		organisme.setId(rs.getInt("id"));
		organisme.setCode(rs.getString("code"));
		organisme.setDesignation(rs.getString("designation"));
		organisme.setCompteCptbl(rs.getString("compte"));
		return organisme;
	}

	public boolean insertUpdateCotisation(CotisationC cotisation) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);

			if (cotisation.getId() == 0) {

				saved = insertCotisation(cotisation);
			} else {

				saved = updateCotisation(cotisation);
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

	private boolean insertCotisation(CotisationC cotisation) {
		boolean saved = false;
		PreparedStatement stmt = null;
		cotisation.setId(getId(Tables.getTableName(Tables.TableName.cotisation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.cotisation)
				+ " (id,code,designation,id_organisme,type_bareme,desactive,obligatoire,retraite,"
				+ "compte_salarial,compte_patronal,charge_salarial,charge_patronal,type_element)  "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, cotisation.getId());
			stmt.setString(2, cotisation.getCode());
			stmt.setString(3, cotisation.getDesignation());
			if (cotisation.getIdOrganisme() > 0) {

				stmt.setInt(4, cotisation.getIdOrganisme());
			} else {

				stmt.setObject(4, (Object) null);
			}
			stmt.setInt(5, cotisation.getTypeBaremme());
			stmt.setBoolean(6, cotisation.isHide());
			stmt.setBoolean(7, cotisation.isObligatoire());
			stmt.setBoolean(8, cotisation.isRetraite());
			stmt.setString(9, cotisation.getPrefixeSalarial());
			stmt.setString(10, cotisation.getPrefixePatronal());
			stmt.setString(11, cotisation.getChargeSalarial());
			stmt.setString(12, cotisation.getChargePtronal());
			stmt.setInt(13, cotisation.getTypeElement());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateCotisation(CotisationC cotisation) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.cotisation)
				+ " SET designation=?,id_organisme=?,type_bareme=?,desactive=?,"
				+ "obligatoire=?,retraite=?,compte_salarial=?,compte_patronal=?,"
				+ "charge_salarial=?,charge_patronal=?,type_element=? WHERE id=?  ";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, cotisation.getDesignation());
			if (cotisation.getIdOrganisme() > 0) {

				stmt.setInt(2, cotisation.getIdOrganisme());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setInt(3, cotisation.getTypeBaremme());
			stmt.setInt(4, HelperC.GetIntValueByBoolean(Boolean.valueOf(cotisation.isHide())));
			stmt.setBoolean(5, cotisation.isObligatoire());
			stmt.setBoolean(6, cotisation.isRetraite());
			stmt.setString(7, cotisation.getPrefixeSalarial());
			stmt.setString(8, cotisation.getPrefixePatronal());
			stmt.setString(9, cotisation.getChargeSalarial());
			stmt.setString(10, cotisation.getChargePtronal());
			stmt.setInt(11, cotisation.getTypeElement());
			stmt.setInt(12, cotisation.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private CotisationC setCotisation(ResultSet rs) throws SQLException {
		CotisationC cotisation = new CotisationC();
		cotisation.setId(rs.getInt("id"));
		cotisation.setCode(rs.getString("code"));
		cotisation.setDesignation(rs.getString("designation"));
		cotisation.setIdOrganisme(rs.getInt("id_organisme"));
		cotisation.setTypeBaremme(rs.getInt("type_bareme"));
		cotisation.setHide(rs.getBoolean("desactive"));
		cotisation.setObligatoire(rs.getBoolean("obligatoire"));
		cotisation.setRetraite(rs.getBoolean("retraite"));
		cotisation.setPrefixePatronal(rs.getString("compte_patronal"));
		cotisation.setPrefixeSalarial(rs.getString("compte_salarial"));
		cotisation.setChargePtronal(rs.getString("charge_patronal"));
		cotisation.setChargeSalarial(rs.getString("charge_salarial"));
		cotisation.setTypeElement(rs.getInt("type_element"));
		return cotisation;
	}

	public CotisationC getCotisation(String code) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		CotisationC cotisation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.cotisation)
				+ " WHERE code=? AND desactive=0";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();

			if (rs.next()) {
				cotisation = setCotisation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return cotisation;
	}

	public CotisationC getImpot(int typeBareme) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		CotisationC cotisation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.cotisation) + " WHERE type_bareme=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, typeBareme);
			rs = stmt.executeQuery();

			if (rs.next()) {
				cotisation = setCotisation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return cotisation;
	}

	public CotisationC getCotisation(int id) {
		ResultSet rs = null;
		Statement stmt = null;
		CotisationC cotisation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.cotisation) + " WHERE id= " + id;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				cotisation = setCotisation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return cotisation;
	}

	public List<CotisationC> getAllCotisation() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CotisationC> liste = new ArrayList<CotisationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.cotisation) + " WHERE desactive=0";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setCotisation(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<CotisationC> getListCotisation(int type) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CotisationC> liste = new ArrayList<CotisationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.cotisation)
				+ " WHERE desactive=0 AND type_element=" + type;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setCotisation(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<CotisationC> getAllCotisationRetraite() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CotisationC> liste = new ArrayList<CotisationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.cotisation)
				+ " WHERE desactive=0 AND retraite=1";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setCotisation(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertParametreCotisation(ParametreCotisationC cotisation, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		cotisation.setId(getId(Tables.getTableName(Tables.TableName.parametreCotisation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametreCotisation)
				+ " (id,id_cotisation,plancher_salarial,plafon_salarial,taux_salarial,taux_patronal,"
				+ "forfait_salarial,forfait_patronal,plancher_patronal,plafon_patronal,"
				+ "type_base_salarial,type_base_patronal,priorite,plafond_base,plancher_base,"
				+ "base_fixe,taux_plafon_salarial,taux_plafon_patronal)  "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cotisation.getId());
			stmt.setInt(2, cotisation.getIdCotisation());
			stmt.setDouble(3, cotisation.getPlancherSalarial());
			stmt.setDouble(4, cotisation.getPlafonSalarial());
			stmt.setDouble(5, cotisation.getTauxSalarial());
			stmt.setDouble(6, cotisation.getTauxPatronal());
			stmt.setDouble(7, cotisation.getForfaitSalarial());
			stmt.setDouble(8, cotisation.getForfaitPatronal());
			stmt.setDouble(9, cotisation.getPlancherPatronal());
			stmt.setDouble(10, cotisation.getPlafonPatronal());
			stmt.setInt(11, cotisation.getTypeBaseSalarial());
			stmt.setInt(12, cotisation.getTypeBasePatronal());
			stmt.setInt(13, cotisation.getPriorite());
			stmt.setDouble(14, cotisation.getPlafondBase());
			stmt.setDouble(15, cotisation.getPlancherBase());
			stmt.setDouble(16, cotisation.getBaseFixe());
			stmt.setDouble(17, cotisation.getTauxPlafonSalarial());
			stmt.setDouble(18, cotisation.getTauxPlafonPatronal());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametreCotisation(ParametreCotisationC cotisation, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametreCotisation)
				+ " SET plancher_salarial=?,plafon_salarial=?,taux_salarial=?,taux_patronal=?,"
				+ "forfait_salarial=?,forfait_patronal=?,plancher_patronal=?,plafon_patronal=?,"
				+ "id_cotisation=?,type_base_salarial=?,type_base_patronal=?,priorite=?,"
				+ "plafond_base=?,plancher_base=?,base_fixe=?,taux_plafon_salarial=?,"
				+ "taux_plafon_patronal=? WHERE id=? ";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setDouble(1, cotisation.getPlancherSalarial());
			stmt.setDouble(2, cotisation.getPlafonSalarial());
			stmt.setDouble(3, cotisation.getTauxSalarial());
			stmt.setDouble(4, cotisation.getTauxPatronal());
			stmt.setDouble(5, cotisation.getForfaitSalarial());
			stmt.setDouble(6, cotisation.getForfaitPatronal());
			stmt.setDouble(7, cotisation.getPlancherPatronal());
			stmt.setDouble(8, cotisation.getPlafonPatronal());
			stmt.setInt(9, cotisation.getIdCotisation());
			stmt.setInt(10, cotisation.getTypeBaseSalarial());
			stmt.setInt(11, cotisation.getTypeBasePatronal());
			stmt.setInt(12, cotisation.getPriorite());
			stmt.setDouble(13, cotisation.getPlafondBase());
			stmt.setDouble(14, cotisation.getPlancherBase());
			stmt.setDouble(15, cotisation.getBaseFixe());
			stmt.setDouble(16, cotisation.getTauxPlafonSalarial());
			stmt.setDouble(17, cotisation.getTauxPlafonPatronal());
			stmt.setInt(18, cotisation.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateParametreCotisation(ParametreCotisationC cotisation) {
		boolean saved = false;

		try {
			Connection conn = con;

			con.setAutoCommit(false);
			if (cotisation.getId() == 0) {

				saved = insertParametreCotisation(cotisation, conn);
				if (saved) {
					if (cotisation.getListDetail().size() > 0) {
						for (CotisationDetailC det : cotisation.getListDetail()) {

							det.setIdEntete(cotisation.getId());
							saved = insertCotisationDetail(det, conn);
						}
					}
				}
			} else {

				saved = updateParametreCotisation(cotisation, conn);
				if (saved) {

					if (cotisation.getListDetail().size() > 0)
						for (CotisationDetailC det : cotisation.getListDetail()) {

							det.setIdEntete(cotisation.getId());

							if (det.getId() > 0) {
								saved = updateCotisationDetail(det, conn);
								continue;
							}
							saved = insertCotisationDetail(det, conn);
						}
					if (cotisation.getListDeleted().size() > 0) {
						for (CotisationDetailC det : cotisation.getListDeleted()) {
							delete(det.getId(), Tables.getTableName(Tables.TableName.parametreCotisationDetail), conn);
						}
					}
				}
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

	public boolean updateCotisationPriority(List<ParametreCotisationC> listCot) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "";

		sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametreCotisation) + " SET priorite=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			for (ParametreCotisationC prm : listCot) {
				stmt.setInt(1, prm.getPriorite());
				stmt.setInt(2, prm.getId());
				stmt.addBatch();
			}

			stmt.executeBatch();

			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public ParametreCotisationC getParametreCotisation(int id) {
		ResultSet rs = null;
		ParametreCotisationC cotisation = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisation) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				cotisation = setParametreCotisation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return cotisation;
	}

	public ParametreCotisationC getParameterCotisation(int idCotisation, boolean detail) {
		ResultSet rs = null;
		Statement stmt = null;
		ParametreCotisationC cotisation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisation)
				+ " WHERE id_cotisation=" + idCotisation;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				cotisation = setParametreCotisation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		if (cotisation != null && detail)
			cotisation.setListDetail(getAllCotisationDetail(cotisation.getId()));
		return cotisation;
	}

	public int getPrioriteCot(int idCot) {
		int priorite = 0;

		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT priorite FROM " + Tables.getTableName(Tables.TableName.parametreCotisation)
				+ " WHERE id_cotisation=" + idCot;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				if (rs.getObject("priorite") != null)
					;
				priorite = rs.getInt("priorite");
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return priorite;
	}

	public List<ParametreCotisationC> getAllCotisation(int type) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametreCotisationC> liste = new ArrayList<ParametreCotisationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisation) + " WHERE type="
				+ type;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setParametreCotisation(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<ParametreCotisationC> getAllParametreCotisation() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametreCotisationC> liste = new ArrayList<ParametreCotisationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisation)
				+ " ORDER BY priorite";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setParametreCotisation(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (liste.size() > 0) {
			for (ParametreCotisationC cotisation : liste) {
				cotisation.setListDetail(getAllCotisationDetail(cotisation.getId()));
			}
		}
		return liste;
	}

	public List<ParametreCotisationC> getAllParametreCotisation(int idCotisation) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametreCotisationC> liste = new ArrayList<ParametreCotisationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisation)
				+ " WHERE id_cotisation<>" + idCotisation;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setParametreCotisation(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return liste;
	}

	private ParametreCotisationC setParametreCotisation(ResultSet rs) throws SQLException {
		ParametreCotisationC cotisation = new ParametreCotisationC();
		cotisation.setId(rs.getInt("id"));
		cotisation.setIdCotisation(rs.getInt("id_cotisation"));
		cotisation.setPlafonPatronal(rs.getDouble("plafon_patronal"));
		cotisation.setPlancherPatronal(rs.getDouble("plancher_patronal"));
		cotisation.setTauxPatronal(rs.getDouble("taux_patronal"));
		cotisation.setTauxSalarial(rs.getDouble("taux_salarial"));
		cotisation.setForfaitPatronal(rs.getDouble("forfait_salarial"));
		cotisation.setForfaitSalarial(rs.getDouble("forfait_salarial"));
		cotisation.setPlafonSalarial(rs.getDouble("plafon_salarial"));
		cotisation.setPlancherSalarial(rs.getDouble("plancher_salarial"));
		cotisation.setTypeBaseSalarial(rs.getInt("type_base_salarial"));
		cotisation.setTypeBasePatronal(rs.getInt("type_base_patronal"));
		cotisation.setPriorite(rs.getInt("priorite"));
		cotisation.setPlafondBase(rs.getDouble("plafond_base"));
		cotisation.setPlancherBase(rs.getDouble("plancher_base"));
		cotisation.setBaseFixe(rs.getDouble("base_fixe"));
		cotisation.setTauxPlafonPatronal(rs.getDouble("taux_plafon_patronal"));
		cotisation.setTauxPlafonSalarial(rs.getDouble("taux_plafon_salarial"));
		return cotisation;
	}

	public boolean deleteParametreCotisation(ParametreCotisationC cotisation) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			saved = deleteList(cotisation.getId(), "id_entete",
					Tables.getTableName(Tables.TableName.parametreCotisationDetail), conn);
			saved = delete(cotisation.getId(), Tables.getTableName(Tables.TableName.parametreCotisation), conn);

			if (saved) {

				conn.commit();
			} else {

				saved = false;
				conn.rollback();
			}

		} catch (SQLException e) {

			saved = false;
			System.out.println(e.getMessage());
		}
		return saved;
	}

	private boolean insertCotisationDetail(CotisationDetailC detail, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;
		detail.setId(getId(Tables.getTableName(Tables.TableName.parametreCotisationDetail)));

		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " (id,taux,forfait,plancher,plafond,code_element,type_element,id_entete,taux_max,"
				+ "type_base,type_parmetre) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, detail.getId());
			stmt.setDouble(2, detail.getTaux());
			stmt.setDouble(3, detail.getForfait());
			stmt.setDouble(4, detail.getPlancher());
			stmt.setDouble(5, detail.getPlafon());
			stmt.setString(6, detail.getCodeElement());
			stmt.setString(7, detail.getTypeElement());
			stmt.setInt(8, detail.getIdEntete());
			stmt.setDouble(9, detail.getTauxMax());
			stmt.setInt(10, detail.getTypeBase());
			stmt.setString(11, detail.getTypePrm());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateCotisationDetail(CotisationDetailC detail, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " SET taux=?,plancher=?,plafond=?,code_element=?,"
				+ "forfait=?,taux_max=?,type_base=?,type_parmetre=?" + " WHERE id=?  ";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setDouble(1, detail.getTaux());
			stmt.setDouble(2, detail.getPlancher());
			stmt.setDouble(3, detail.getPlafon());
			stmt.setString(4, detail.getCodeElement());
			stmt.setDouble(5, detail.getForfait());
			stmt.setDouble(6, detail.getTauxMax());
			stmt.setInt(7, detail.getTypeBase());
			stmt.setString(8, detail.getTypePrm());
			stmt.setInt(9, detail.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private CotisationDetailC setCotisationDetail(ResultSet rs) throws SQLException {
		CotisationDetailC det = new CotisationDetailC();
		det.setId(rs.getInt("id"));
		det.setCodeElement(rs.getString("code_element"));
		det.setTypeElement(rs.getString("type_element"));
		det.setForfait(rs.getDouble("forfait"));
		det.setPlafon(rs.getDouble("plafond"));
		det.setPlancher(rs.getDouble("plancher"));
		det.setTaux(rs.getDouble("taux"));
		det.setTauxMax(rs.getDouble("taux_max"));
		det.setTypeBase(rs.getInt("type_base"));
		det.setTypePrm(rs.getString("type_parmetre"));
		return det;
	}

	public CotisationDetailC getCotisationDetail(String code, int idEntete, String typEl) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CotisationDetailC det = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE code_element='" + code + "' AND id_entete=" + idEntete + " AND type_element='" + typEl + "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				det = setCotisationDetail(rs);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return det;
	}

	public CotisationDetailC getCotisationDetail(String code, int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CotisationDetailC det = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE code_element='" + code + "' AND id_entete=" + idEntete;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				det = setCotisationDetail(rs);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return det;
	}

	public CotisationDetailC getCotisationDetailHS(String code, int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CotisationDetailC det = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE code_element='" + code + "' AND id_entete=" + idEntete
				+ " AND type_base=2 AND type_element='A'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				det = setCotisationDetail(rs);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return det;
	}

	public CotisationDetailC getCotisationDetailLOG(String code, int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CotisationDetailC det = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE code_element='" + code + "' AND id_entete=" + idEntete
				+ " AND type_base=3 AND type_element='A'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				det = setCotisationDetail(rs);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return det;
	}

	public CotisationDetailC getCotisationDetailSB(String code, int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CotisationDetailC det = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE code_element='" + code + "' AND id_entete=" + idEntete
				+ " AND type_base=1 AND type_element='A'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				det = setCotisationDetail(rs);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return det;
	}

	public CotisationDetailC getCotisationDetailAL(String code, int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CotisationDetailC det = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE code_element='" + code + "' AND id_entete=" + idEntete
				+ " AND type_base=4 AND type_element='A'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				det = setCotisationDetail(rs);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return det;
	}

	public CotisationDetailC getCotisationDetailBRUT(String code, int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CotisationDetailC det = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE code_element='" + code + "' AND id_entete=" + idEntete
				+ " AND type_base=5 AND type_element='A'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				det = setCotisationDetail(rs);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return det;
	}

	public List<CotisationDetailC> getAllCotisationDetail(int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CotisationDetailC> liste = new ArrayList<CotisationDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE id_entete=" + idEntete;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setCotisationDetail(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<CotisationDetailC> getAllCotisationDetail(int idEntete, String type, String typePrm) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CotisationDetailC> liste = new ArrayList<CotisationDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE id_entete=" + idEntete + " AND type_element='" + type + "' AND type_parmetre='" + typePrm
				+ "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setCotisationDetail(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<CotisationDetailC> getAllCotisationAdded(int idEntete, String type) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CotisationDetailC> liste = new ArrayList<CotisationDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametreCotisationDetail)
				+ " WHERE id_entete<>" + idEntete + " AND type_element='" + type + "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setCotisationDetail(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertDeduction(DeductionC deduction) {
		boolean saved = false;
		PreparedStatement stmt = null;
		deduction.setId(getId(Tables.getTableName(Tables.TableName.deduction)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.deduction)
				+ " (id,code,designation,taux,prefixe_comptable,montant,soustraire_paie)  VALUES (?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, deduction.getId());
			stmt.setString(2, deduction.getCode());
			stmt.setString(3, deduction.getDesignation());
			stmt.setDouble(4, deduction.getTaux());
			stmt.setString(5, deduction.getPrefixeComptable());
			stmt.setDouble(6, deduction.getMontant());
			stmt.setBoolean(7, deduction.isSoustract());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDeduction(DeductionC deduction) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.deduction)
				+ " SET designation=?,taux=?,prefixe_comptable=?,montant=?,soustraire_paie=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, deduction.getDesignation());
			stmt.setDouble(2, deduction.getTaux());
			stmt.setString(3, deduction.getPrefixeComptable());
			stmt.setDouble(4, deduction.getMontant());
			stmt.setBoolean(5, deduction.isSoustract());
			stmt.setInt(6, deduction.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateDeduction(DeductionC deduction) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (deduction.getId() == 0) {

				saved = insertDeduction(deduction);
			} else {

				saved = updateDeduction(deduction);
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

	public DeductionC getDeduction(int id) {
		ResultSet rs = null;
		DeductionC deduction = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deduction) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				deduction = setDeduction(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return deduction;
	}

	public DeductionC getDeductionSanction() {
		ResultSet rs = null;
		DeductionC deduction = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deduction) + " WHERE type_sanction=1 ";

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.next()) {
				deduction = setDeduction(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return deduction;
	}

	public DeductionC getDeduction(String code) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		DeductionC deduction = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deduction)
				+ " WHERE code=? AND desactive=0";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				deduction = setDeduction(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return deduction;
	}

	public DeductionC getDeduction(String code, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DeductionC deduction = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deduction) + " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				deduction = setDeduction(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return deduction;
	}

	public List<DeductionC> getAllDeduction() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DeductionC> liste = new ArrayList<DeductionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deduction) + " WHERE desactive=0";

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setDeduction(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<DeductionC> getAllDeduction(String code, String designation) {
		Statement stmt = null;
		ResultSet rs = null;
		List<DeductionC> liste = new ArrayList<DeductionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.deduction) + " WHERE 1=1 ";
		if (code.trim() != "") {
			sql = String.valueOf(sql) + " AND code like '%" + code + "%' ";
		}
		if (designation != "") {
			sql = String.valueOf(sql) + " AND designation like '%" + designation + "%' ";
		}

		try {
			stmt = con.createStatement();
			for (rs = stmt.executeQuery(sql); rs.next(); liste.add(setDeduction(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private DeductionC setDeduction(ResultSet rs) throws SQLException {
		DeductionC deduction = new DeductionC();
		deduction.setId(rs.getInt("id"));
		deduction.setCode(rs.getString("code"));
		deduction.setDesignation(rs.getString("designation"));
		deduction.setTaux(rs.getInt("taux"));
		deduction.setTauxS(HelperC.TraitementMontant.getMontantFormate(deduction.getTaux(), 0));
		deduction.setMontant(rs.getDouble("montant"));
		deduction.setMontantS(HelperC.TraitementMontant.getMontantFormate(deduction.getMontant(), 0));
		deduction.setPrefixeComptable(rs.getString("prefixe_comptable"));
		deduction.setSoustract(rs.getBoolean("soustraire_paie"));
		return deduction;
	}

	private DeductionRangesC setDeductionRanges(ResultSet rs) throws SQLException {
		DeductionRangesC dedrang = new DeductionRangesC();
		dedrang.setId(rs.getInt("id"));
		dedrang.setBorneDebut(rs.getDouble("borne_debut"));
		dedrang.setBorneDebutS(HelperC.TraitementMontant.getMontantFormate(dedrang.getBorneDebut(), 0));
		dedrang.setBorneFin(rs.getDouble("borne_fin"));
		dedrang.setBorneFinS(HelperC.TraitementMontant.getMontantFormate(dedrang.getBorneFin(), 0));
		dedrang.setMontant(rs.getDouble("montant"));
		dedrang.setMontantS(HelperC.TraitementMontant.getMontantFormate(dedrang.getMontant(), 0));
		return dedrang;
	}

	public List<DeductionRangesC> getListeDetailNHIFDeductionRanges(DeductionRangesC dedrang) {
		List<DeductionRangesC> detai = new ArrayList<DeductionRangesC>();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlRequest = "";

		try {
			stmt = con.createStatement();
			sqlRequest = " SELECT * FROM " + Tables.getTableName(Tables.TableName.nhifranges);
			for (rs = stmt.executeQuery(sqlRequest); rs.next(); detai.add(setDeductionRanges(rs)))
				;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detai;
	}

	public List<LieuxTravailC> getAllLieuxTravail(String code, String designation) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<LieuxTravailC> liste = new ArrayList<LieuxTravailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.lieuxTravail) + " WHERE 1=1 ";
		if (!code.trim().equals("")) {
			sql = String.valueOf(sql) + "AND code like '%" + code + "%' ";
		}
		if (designation.trim().equals("")) {
			sql = String.valueOf(sql) + "AND designation like '%" + designation + "%' ";
		}

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setLieuxTravail(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertTypeConge(TypeCongeC typeCong) {
		boolean saved = false;
		PreparedStatement stmt = null;
		typeCong.setId(getId(Tables.getTableName(Tables.TableName.typeConge)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.typeConge)
				+ " (id,code,designation,annuel,sorte_conge,nombre_jour) VALUES (?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, typeCong.getId());
			stmt.setString(2, typeCong.getCode());
			stmt.setString(3, typeCong.getDesignation());
			stmt.setInt(4, HelperC.GetIntValueByBoolean(Boolean.valueOf(typeCong.isAnnuel())));
			stmt.setInt(5, typeCong.getSorteConge());
			stmt.setInt(6, typeCong.getNombreJour());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateTypeConge(TypeCongeC typeCong) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.typeConge)
				+ " SET designation=?, annuel=?,sorte_conge=?,nombre_jour=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, typeCong.getDesignation());
			stmt.setInt(2, HelperC.GetIntValueByBoolean(Boolean.valueOf(typeCong.isAnnuel())));
			stmt.setInt(3, typeCong.getSorteConge());
			stmt.setInt(4, typeCong.getNombreJour());
			stmt.setInt(5, typeCong.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateTypeConge(TypeCongeC typeCong) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (typeCong.getId() == 0) {

				saved = insertTypeConge(typeCong);
			} else {

				saved = updateTypeConge(typeCong);
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

	private TypeCongeC setTypeConge(ResultSet rs) throws SQLException {
		TypeCongeC typeCong = new TypeCongeC();
		typeCong.setId(rs.getInt("id"));
		typeCong.setCode(rs.getString("code"));
		typeCong.setDesignation(rs.getString("designation"));
		typeCong.setAnnuel(rs.getBoolean("annuel"));
		typeCong.setSorteConge(rs.getInt("sorte_conge"));
		typeCong.setNombreJour(rs.getInt("nombre_jour"));
		return typeCong;
	}

	public TypeCongeC getTypeConge(String code) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		TypeCongeC typeCong = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeConge) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typeCong = setTypeConge(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typeCong;
	}

	public TypeCongeC getTypeConge(String code, int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		TypeCongeC typeCong = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeConge) + " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typeCong = setTypeConge(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typeCong;
	}

	public TypeCongeC getTypeConges(String designation, int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		TypeCongeC typeCong = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeConge)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typeCong = setTypeConge(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typeCong;
	}

	public TypeCongeC getTypeConge(int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		TypeCongeC typeCong = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeConge) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				typeCong = setTypeConge(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return typeCong;
	}

	public List<TypeCongeC> getAllTypeConge() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<TypeCongeC> liste = new ArrayList<TypeCongeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeConge);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setTypeConge(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<TypeCongeC> getListTypeConge(Constante.SorteConge sorteConge) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<TypeCongeC> liste = new ArrayList<TypeCongeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeConge) + " WHERE sorte_conge=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Constante.getSorteConge(sorteConge));
			rs = stmt.executeQuery();
			while (rs.next()) {
				liste.add(setTypeConge(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return liste;
	}

	private boolean insertNiveau(NiveauHierarchiqueC niveau) {
		boolean saved = false;
		PreparedStatement stmt = null;
		niveau.setId(getId(Tables.getTableName(Tables.TableName.niveauHierarchique)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.niveauHierarchique)
				+ " (id,code,designation,niveau) VALUES (?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, niveau.getId());
			stmt.setString(2, niveau.getCode());
			stmt.setString(3, niveau.getDesignation());
			stmt.setInt(4, niveau.getNiveau());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateNiveau(NiveauHierarchiqueC niveau) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.niveauHierarchique)
				+ " SET designation=?, niveau=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, niveau.getDesignation());
			stmt.setInt(2, niveau.getNiveau());
			stmt.setInt(3, niveau.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateNiveau(NiveauHierarchiqueC niveau) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (niveau.getId() == 0) {

				saved = insertNiveau(niveau);
			} else {

				saved = updateNiveau(niveau);
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

	private NiveauHierarchiqueC setNiveau(ResultSet rs) throws SQLException {
		NiveauHierarchiqueC niveau = new NiveauHierarchiqueC();
		niveau.setId(rs.getInt("id"));
		niveau.setCode(rs.getString("code"));
		niveau.setDesignation(rs.getString("designation"));
		niveau.setNiveau(rs.getInt("niveau"));
		return niveau;
	}

	public NiveauHierarchiqueC getNiveau(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		NiveauHierarchiqueC niveau = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.niveauHierarchique) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				niveau = setNiveau(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return niveau;
	}

	public NiveauHierarchiqueC getNiveau(String code) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		NiveauHierarchiqueC niveau = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.niveauHierarchique) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				niveau = setNiveau(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return niveau;
	}

	public NiveauHierarchiqueC getNiveauNotCurrent(String code, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		NiveauHierarchiqueC niveau = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.niveauHierarchique)
				+ " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				niveau = setNiveau(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return niveau;
	}

	public NiveauHierarchiqueC getNiveauxNotCurrent(String designation, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		NiveauHierarchiqueC niveau = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.niveauHierarchique)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				niveau = setNiveau(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return niveau;
	}

	public List<NiveauHierarchiqueC> getAllNiveau() {
		ResultSet rs = null;
		List<NiveauHierarchiqueC> liste = new ArrayList<NiveauHierarchiqueC>();
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.niveauHierarchique);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setNiveau(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertParametrageHeureSupplementaire(ParametrageHeureSupplementaireC heureSupplementaire) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.heureSupplementaire) + " " + "(numero,actif)"
				+ " VALUES (?,?) ";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, heureSupplementaire.getNumero());
			stmt.setBoolean(2, heureSupplementaire.isActif());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametrageHeureSupplementaire(ParametrageHeureSupplementaireC heureSupplementaire) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.heureSupplementaire) + " "
				+ "SET actif=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setBoolean(1, heureSupplementaire.isActif());
			stmt.setInt(2, heureSupplementaire.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateParametrageHeureSupplementaire(ParametrageHeureSupplementaireC heureSupplementaire) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (heureSupplementaire.getId() == 0) {

				saved = insertParametrageHeureSupplementaire(heureSupplementaire);
			} else {

				saved = updateParametrageHeureSupplementaire(heureSupplementaire);
			}
			if (saved) {
				for (DetailParametrageHeureSupplementaireC deta : heureSupplementaire
						.getListDetailHeureSupplementaire()) {

					deta.setHeureSupplementaire(heureSupplementaire);
					if (deta.getId() == 0) {

						saved = insertDetailParametrageHeureSupplementaire(deta);
					} else {

						saved = updateDetailParametrageHeureSupplementaire(deta);
					}
					if (!saved) {
						break;
					}
				}
			}

			if (saved) {
				for (DetailParametrageHeureSupplementaireC deta : heureSupplementaire
						.getListDetailHeureSupplementaireDeleted()) {

					if (deta.getId() > 0) {
						saved = deleteNotCommited(deta.getId(),
								Tables.getTableName(Tables.TableName.detailHeureSupplementaire));
					}
					if (!saved) {
						break;
					}
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

	public ParametrageHeureSupplementaireC getParametrageHeureSupplementaire(String numero) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ParametrageHeureSupplementaireC param = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.heureSupplementaire) + " WHERE numero=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, numero);
			rs = stmt.executeQuery();
			if (rs.next()) {
				param = setParametrageHeureSupplementaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return param;
	}

	public ParametrageHeureSupplementaireC getParametrageHeureSupplementaire() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ParametrageHeureSupplementaireC param = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.heureSupplementaire) + " WHERE actif=1";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				param = setParametrageHeureSupplementaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (param != null) {
			param.setListDetailHeureSupplementaire(getListeDetailParametrageHeureSupplementaire(param));
		}
		return param;
	}

	public boolean deleteParametrageHeureSupplementaire(ParametrageHeureSupplementaireC param) {
		boolean deleted = false;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.heureSupplementaire) + " WHERE id=?";

		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, param.getId());
			deleteAllDetailParametrageHeureSupplementaire(param);
			stmt.execute();
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private ParametrageHeureSupplementaireC setParametrageHeureSupplementaire(ResultSet rs) throws SQLException {
		ParametrageHeureSupplementaireC param = new ParametrageHeureSupplementaireC();
		param.setId(rs.getInt("id"));
		param.setNumero(rs.getString("numero"));
		param.setActif(rs.getBoolean("actif"));
		return param;
	}

	private boolean insertDetailParametrageHeureSupplementaire(DetailParametrageHeureSupplementaireC deta) {
		boolean saved = false;
		PreparedStatement stmt = null;
		deta.setId(getId(Tables.getTableName(Tables.TableName.detailHeureSupplementaire)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailHeureSupplementaire) + " "
				+ "(id,heure_debut,heure_fin,taux,id_heure_supplementaire) " + "VALUES (?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, deta.getId());
			stmt.setObject(2, deta.getHeureDebutS());
			stmt.setObject(3, deta.getHeureFinS());
			stmt.setDouble(4, deta.getTaux());
			if (deta.getHeureSupplementaire() != null) {

				stmt.setInt(5, deta.getHeureSupplementaire().getId());
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

	private boolean updateDetailParametrageHeureSupplementaire(DetailParametrageHeureSupplementaireC deta) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailHeureSupplementaire)
				+ " SET heure_debut=?,heure_fin=?,taux=?,id_heure_supplementaire=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, deta.getHeureDebutS());
			stmt.setObject(2, deta.getHeureFinS());
			stmt.setDouble(3, deta.getTaux());
			if (deta.getHeureSupplementaire() != null) {

				stmt.setInt(4, deta.getHeureSupplementaire().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			stmt.setInt(5, deta.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean deleteAllDetailParametrageHeureSupplementaire(ParametrageHeureSupplementaireC param) {
		boolean deleted = false;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.detailHeureSupplementaire)
				+ " WHERE id_heure_supplementaire=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, param.getId());
			stmt.execute();
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	public List<DetailParametrageHeureSupplementaireC> getListeDetailParametrageHeureSupplementaire(
			ParametrageHeureSupplementaireC param) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<DetailParametrageHeureSupplementaireC> liste = new ArrayList<DetailParametrageHeureSupplementaireC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailHeureSupplementaire)
				+ " WHERE id_heure_supplementaire=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, param.getId());
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setDetailParametrageHeureSupplementaire(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public DetailParametrageHeureSupplementaireC getListeDetailParametrageHeureSupplementaire(double taux,
			int idEntete) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		DetailParametrageHeureSupplementaireC tranche = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailHeureSupplementaire)
				+ " WHERE taux=? AND id_heure_supplementaire=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, taux);
			stmt.setInt(2, idEntete);
			rs = stmt.executeQuery();
			if (rs.next()) {
				tranche = setDetailParametrageHeureSupplementaire(rs);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return tranche;
	}

	private DetailParametrageHeureSupplementaireC setDetailParametrageHeureSupplementaire(ResultSet rs)
			throws SQLException {
		DetailParametrageHeureSupplementaireC deta = new DetailParametrageHeureSupplementaireC();
		deta.setId(rs.getInt("id"));
		deta.setHeureDebutS(rs.getString("heure_debut"));
		deta.setHeureFinS(rs.getString("heure_fin"));
		deta.setTaux(rs.getDouble("taux"));
		deta.setTauxS(HelperC.TraitementMontant.getMontantFormate(deta.getTaux(), 2));
		ParametrageHeureSupplementaireC param = new ParametrageHeureSupplementaireC();
		deta.setHeureSupplementaire(param);
		return deta;
	}

	private boolean insertJourFerier(JourFerieC JrFerier) {
		boolean saved = false;
		PreparedStatement stmt = null;
		JrFerier.setId(getId(Tables.getTableName(Tables.TableName.jourFerie)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.jourFerie)
				+ " (id,code,designation,id_exercice,type_jour_ferier,date_jour_ferier) " + " VALUES (?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, JrFerier.getId());
			stmt.setString(2, JrFerier.getCode());
			stmt.setString(3, JrFerier.getDesignation());
			if (JrFerier.getExercice() != null) {

				stmt.setInt(4, JrFerier.getExercice().getId());
			} else {

				stmt.setObject(4, (Object) null);
			}
			stmt.setInt(5, JrFerier.getTypeJourFerie());
			stmt.setObject(6, JrFerier.getDateFerie());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateJourFerier(JourFerieC JrFerier) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.jourFerie)
				+ " SET designation=?,id_exercice=?,type_jour_ferier=?,date_jour_ferier=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, JrFerier.getDesignation());
			if (JrFerier.getExercice() != null) {

				stmt.setInt(2, JrFerier.getExercice().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setInt(3, JrFerier.getTypeJourFerie());
			stmt.setObject(4, JrFerier.getDateFerie());
			stmt.setInt(5, JrFerier.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateJourFerier(JourFerieC JrFerier) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (JrFerier.getId() == 0) {

				saved = insertJourFerier(JrFerier);
			} else {

				saved = updateJourFerier(JrFerier);
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

	public JourFerieC getCurrentJourFerier(int id, Date jour) {
		JourFerieC jourFerie = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.jourFerie)
					+ " WHERE date_jour_ferier = ? AND id<>? ";
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, jour);
			stmt.setInt(2, id);
			res = stmt.executeQuery();
			if (res.next()) {
				jourFerie = setJourFerie(res);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return jourFerie;
	}

	public JourFerieC getCurrentJourFerier(String code) {
		JourFerieC jourFerie = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.jourFerie) + " WHERE code = ?";
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, code);
			res = stmt.executeQuery();
			if (res.next()) {
				jourFerie = setJourFerie(res);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return jourFerie;
	}

	public JourFerieC getCurrentJourFerier(String code, int id) {
		JourFerieC jourFerie = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.jourFerie)
					+ " WHERE code = ? AND id<>?";
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, code);
			stmt.setInt(2, id);
			res = stmt.executeQuery();
			if (res.next()) {
				jourFerie = setJourFerie(res);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return jourFerie;
	}

	public JourFerieC getCurrentJourFeriers(String designation, int id) {
		JourFerieC jourFerie = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.jourFerie)
					+ " WHERE designation = ? AND id<>?";
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, designation);
			stmt.setInt(2, id);
			res = stmt.executeQuery();
			if (res.next()) {
				jourFerie = setJourFerie(res);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return jourFerie;
	}

	private JourFerieC setJourFerie(ResultSet rs) throws NumberFormatException, SQLException {
		JourFerieC jourF = new JourFerieC();
		jourF.setId(Integer.valueOf(rs.getInt("id")).intValue());
		jourF.setCode(rs.getString("code"));
		jourF.setDesignation(rs.getString("designation"));
		jourF.setExercice(getExercice(rs.getInt("id_exercice")));
		jourF.setTypeJourFerie(rs.getInt("type_jour_ferier"));
		jourF.setDateFerie(rs.getDate("date_jour_ferier"));
		jourF.setLibelleJourFerie(Constante.getLibelleTypeJourFerie(rs.getInt("type_jour_ferier")));
		jourF.setPrintDate(HelperC.convertDate(jourF.getDateFerie()));
		return jourF;
	}

	public JourFerieC getJourFerie(Date date) {
		JourFerieC jourFerie = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.jourFerie)
					+ " WHERE date_jour_ferier=?";
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, date);
			res = stmt.executeQuery();
			if (res.next()) {
				jourFerie = setJourFerie(res);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, res);
		}
		return jourFerie;
	}

	public List<JourFerieC> getAllJourFerier() {
		ResultSet rs = null;
		List<JourFerieC> liste = new ArrayList<JourFerieC>();
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.jourFerie);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setJourFerie(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertTypeNotation(TypeNotationC type) {
		boolean saved = false;
		PreparedStatement stmt = null;
		type.setId(getId(Tables.getTableName(Tables.TableName.typeNotation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.typeNotation)
				+ " (id,code,designation,pourcentage_min,pourcentage_max,taux_augmentation) " + " VALUES (?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, type.getId());
			stmt.setString(2, type.getCode());
			stmt.setString(3, type.getDesignation());
			stmt.setDouble(4, type.getPourcentageMin());
			stmt.setDouble(5, type.getPourcentageMax());
			stmt.setDouble(6, type.getTauxAugmentation());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateTypeNotation(TypeNotationC type) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.typeNotation) + " SET  "
				+ " code=?,designation=?,pourcentage_min=?,pourcentage_max=?,taux_augmentation=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, type.getCode());
			stmt.setString(2, type.getDesignation());
			stmt.setDouble(3, type.getPourcentageMin());
			stmt.setDouble(4, type.getPourcentageMax());
			stmt.setDouble(5, type.getTauxAugmentation());
			stmt.setInt(6, type.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateTypeNotation(TypeNotationC type) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (type.getId() == 0) {

				saved = insertTypeNotation(type);
				if (!saved) {
					type.setId(0);
				}
			} else {

				saved = updateTypeNotation(type);
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

	private TypeNotationC setTypeNotation(ResultSet rs) throws SQLException {
		TypeNotationC type = new TypeNotationC();
		type.setId(rs.getInt("id"));
		type.setCode(rs.getString("code"));
		type.setDesignation(rs.getString("designation"));
		type.setPourcentageMin(rs.getDouble("pourcentage_min"));
		type.setPourcentageMinS(HelperC.TraitementMontant.getMontantFormate(type.getPourcentageMin(), 2));
		type.setPourcentageMax(rs.getDouble("pourcentage_max"));
		type.setPourcentageMaxS(HelperC.TraitementMontant.getMontantFormate(type.getPourcentageMax(), 2));
		type.setTauxAugmentation(rs.getDouble("taux_augmentation"));
		type.setTauxAugmentationS(HelperC.TraitementMontant.getMontantFormate(type.getTauxAugmentation(), 2));
		return type;
	}

	public TypeNotationC getTypeNotation(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TypeNotationC type = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeNotation) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				type = setTypeNotation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return type;
	}

	public boolean deleteTypeNotation(TypeNotationC type) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(type.getId(), Tables.getTableName(Tables.TableName.typeNotation));

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

	public TypeNotationC getTypeNotation(String code) {
		TypeNotationC notation = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeNotation) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				notation = setTypeNotation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return notation;
	}

	public TypeNotationC getTypeNotationNotCurrent(String code, int id) {
		TypeNotationC notation = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.typeNotation)
				+ " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				notation = setTypeNotation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return notation;
	}

	public TypeNotationC getTypeNotationNotCurrents(String designation, int id) {
		TypeNotationC notation = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.typeNotation)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				notation = setTypeNotation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return notation;
	}

	public List<TypeNotationC> getAllTypeNotation() {
		List<TypeNotationC> notations = new ArrayList<TypeNotationC>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeNotation);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); notations.add(setTypeNotation(rs)))
				;

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return notations;
	}

	private boolean insertPrimeIndemnite(PrimeIndemniteC prime) {
		boolean saved = false;
		PreparedStatement stmt = null;
		prime.setId(getId(Tables.getTableName(Tables.TableName.primeIndemnite)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " (id,code,designation,type_element,prefixe_comptable,imposable,soumis_cotisation,"
				+ "desactive,retraite) VALUES (?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, prime.getId());
			stmt.setString(2, prime.getCode());
			stmt.setString(3, prime.getDesignation());
			stmt.setString(4, prime.getTypePrime());
			stmt.setString(5, prime.getPrefixeComptable());
			stmt.setInt(6, HelperC.GetIntValueByBoolean(Boolean.valueOf(prime.isImposable())));
			stmt.setInt(7, HelperC.GetIntValueByBoolean(Boolean.valueOf(prime.isSoumisCotisation())));
			stmt.setInt(8, HelperC.GetIntValueByBoolean(Boolean.valueOf(prime.isHide())));
			stmt.setInt(9, HelperC.GetIntValueByBoolean(Boolean.valueOf(prime.isRetraite())));
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updatePrimeIndemnite(PrimeIndemniteC prime) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " SET  designation=?,type_element=?,prefixe_comptable=?,"
				+ "imposable=?,soumis_cotisation=?,desactive=?,retraite=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, prime.getDesignation());
			stmt.setString(2, prime.getTypePrime());
			stmt.setString(3, prime.getPrefixeComptable());
			stmt.setInt(4, HelperC.GetIntValueByBoolean(Boolean.valueOf(prime.isImposable())));
			stmt.setInt(5, HelperC.GetIntValueByBoolean(Boolean.valueOf(prime.isSoumisCotisation())));
			stmt.setInt(6, HelperC.GetIntValueByBoolean(Boolean.valueOf(prime.isHide())));
			stmt.setInt(7, HelperC.GetIntValueByBoolean(Boolean.valueOf(prime.isRetraite())));
			stmt.setInt(8, prime.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdatePrimeIndemnite(PrimeIndemniteC prime) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (prime.getId() == 0) {

				saved = insertPrimeIndemnite(prime);
			} else {

				saved = updatePrimeIndemnite(prime);
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

	public boolean deletePrimeIndemnite(PrimeIndemniteC prime) {
		boolean deleted = false;
		Statement stmt = null;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.primeIndemnite) + " WHERE id="
				+ prime.getId();

		try {

			stmt = con.createStatement();

			stmt.execute(sql);
			deleted = true;

		} catch (SQLException e) {
			deleted = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private PrimeIndemniteC setPrimeIndemnite(ResultSet rs) throws SQLException {
		PrimeIndemniteC prime = new PrimeIndemniteC();
		prime.setId(rs.getInt("id"));
		prime.setCode(rs.getString("code"));
		prime.setDesignation(rs.getString("designation"));
		prime.setTypePrime(rs.getString("type_element"));
		prime.setPrefixeComptable(rs.getString("prefixe_comptable"));
		prime.setImposable(rs.getBoolean("imposable"));
		prime.setSoumisCotisation(rs.getBoolean("soumis_cotisation"));
		prime.setHide(rs.getBoolean("desactive"));
		prime.setRetraite(rs.getBoolean("retraite"));
		return prime;
	}

	public PrimeIndemniteC getPrimeIndemnite(int id) {
		PrimeIndemniteC prime = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prime = setPrimeIndemnite(rs);

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return prime;
	}

	public PrimeIndemniteC getPrimeIndemnite(String code, String type) {
		PrimeIndemniteC prime = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " WHERE code=? AND type_element=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setString(2, type);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prime = setPrimeIndemnite(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return prime;
	}

	public PrimeIndemniteC getPrimeIndemnite(String code) {
		PrimeIndemniteC prime = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " WHERE code=? AND desactive=0";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prime = setPrimeIndemnite(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return prime;
	}

	public PrimeIndemniteC getPrimeRetraite() {
		PrimeIndemniteC prime = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " WHERE retraite=1 AND desactive=0 ";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prime = setPrimeIndemnite(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return prime;
	}

	public PrimeIndemniteC getPrimeIndemniteNotCurrents(String designation, int id) {
		PrimeIndemniteC prime = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prime = setPrimeIndemnite(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return prime;
	}

	public List<PrimeIndemniteC> getAllPrimeIndemnite(String typeElement) {
		List<PrimeIndemniteC> primes = new ArrayList<PrimeIndemniteC>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " WHERE  1=1 AND desactive=0";
		if (typeElement != null && !typeElement.equals("")) {
			sql = String.valueOf(sql) + " AND type_element='" + typeElement + "'";
		}
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				primes.add(setPrimeIndemnite(rs));
			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return primes;
	}

	public List<PrimeIndemniteC> getAllPrimeIndemnite() {
		List<PrimeIndemniteC> primes = new ArrayList<PrimeIndemniteC>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " WHERE desactive=0 ORDER BY code";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				primes.add(setPrimeIndemnite(rs));

			}
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return primes;
	}

	public List<PrimeIndemniteC> getAllPrimeIndemniteImposable() {
		Statement stmt = null;
		ResultSet rs = null;
		List<PrimeIndemniteC> liste = new ArrayList<PrimeIndemniteC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " WHERE imposable=1 OR soumis_cotisation=1 AND desactive=0";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				liste.add(setPrimeIndemnite(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<PrimeIndemniteC> getAllPrimeIndemniteNonImposable() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PrimeIndemniteC> liste = new ArrayList<PrimeIndemniteC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.primeIndemnite)
				+ " WHERE imposable=0 AND desactive=0";

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setPrimeIndemnite(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public boolean insertUpdateParametrePrime(ParametragePrimeC parm) {
		boolean saved = false;

		try {

			Connection conn = con;
			conn.setAutoCommit(false);

			if (parm.getId() == 0) {

				saved = insertParametragePrime(parm, conn);
				if (saved && parm.getListDetail().size() > 0) {
					for (ParametragePrimeDetailC det : parm.getListDetail()) {
						det.setIdEntete(parm.getId());
						insertParametragePrimeDetail(det, conn);
					}

				}
			} else {

				saved = updateParametragePrime(parm, conn);
				if (saved && parm.getListDetail().size() > 0) {
					for (ParametragePrimeDetailC det : parm.getListDetail()) {

						det.setIdEntete(parm.getId());

						if (det.getId() == 0) {
							insertParametragePrimeDetail(det, conn);
							continue;
						}
						updateParametragePrimeDetail(det, conn);
					}
				}
				if (saved) {
					if (parm.getListDeleted().size() > 0) {
						for (ParametragePrimeDetailC det : parm.getListDeleted()) {
							delete(det.getId(), Tables.getTableName(Tables.TableName.parametragePrimeDetail), conn);
						}
					}
				}
			}
			if (saved) {

				parm.getHist().setIdLigne(parm.getId());
				saved = insertHistorique(parm.getHist(), conn);
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

	private boolean insertParametragePrime(ParametragePrimeC parametre, Connection conn) {
		PreparedStatement stmt = null;

		boolean saved = false;

		parametre.setId(getId(Tables.getTableName(Tables.TableName.parametragePrime)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametragePrime)
				+ " (id,id_personnel,id_categorie,id_grade,id_fonction,percent,forfait,plancher,"
				+ "plafond,clacul_hsup,id_prime,type_base,priorite)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, parametre.getId());

			if (parametre.getIdPersonnel() > 0) {
				stmt.setInt(2, parametre.getIdPersonnel());
			} else {
				stmt.setObject(2, (Object) null);
			}
			if (parametre.getIdCategorie() > 0) {
				stmt.setInt(3, parametre.getIdCategorie());
			} else {
				stmt.setObject(3, (Object) null);
			}
			if (parametre.getIdGrade() > 0) {
				stmt.setInt(4, parametre.getIdGrade());
			} else {
				stmt.setObject(4, (Object) null);
			}
			if (parametre.getIdFonction() > 0) {
				stmt.setInt(5, parametre.getIdFonction());
			} else {
				stmt.setObject(5, (Object) null);
			}
			stmt.setDouble(6, parametre.getTaux());
			stmt.setDouble(7, parametre.getForfait());
			stmt.setDouble(8, parametre.getPlancher());
			stmt.setDouble(9, parametre.getPlafond());
			stmt.setInt(10, HelperC.GetIntValueByBoolean(Boolean.valueOf(parametre.isCalculHeurSup())));
			stmt.setInt(11, parametre.getIdPrime());
			stmt.setInt(12, parametre.getTypeBase());
			stmt.setInt(13, parametre.getPriorite());

			stmt.executeUpdate();

			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametragePrime(ParametragePrimeC parametre, Connection conn) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametragePrime)
				+ " SET id_personnel=?,id_categorie=?,id_grade=?,id_fonction=?,percent=?,"
				+ "forfait=?,plancher=?,plafond=?,clacul_hsup=?,id_prime=?,type_base=?," + " priorite=? WHERE id=? ";

		try {
			stmt = conn.prepareStatement(sql);

			if (parametre.getIdPersonnel() > 0) {
				stmt.setInt(1, parametre.getIdPersonnel());
			} else {
				stmt.setObject(1, (Object) null);
			}
			if (parametre.getIdCategorie() > 0) {
				stmt.setInt(2, parametre.getIdCategorie());
			} else {
				stmt.setObject(2, (Object) null);
			}
			if (parametre.getIdGrade() > 0) {
				stmt.setInt(3, parametre.getIdGrade());
			} else {
				stmt.setObject(3, (Object) null);
			}
			if (parametre.getIdFonction() > 0) {
				stmt.setInt(4, parametre.getIdFonction());
			} else {
				stmt.setObject(4, (Object) null);
			}
			stmt.setDouble(5, parametre.getTaux());
			stmt.setDouble(6, parametre.getForfait());
			stmt.setDouble(7, parametre.getPlancher());
			stmt.setDouble(8, parametre.getPlafond());
			stmt.setInt(9, HelperC.GetIntValueByBoolean(Boolean.valueOf(parametre.isCalculHeurSup())));
			stmt.setInt(10, parametre.getIdPrime());
			stmt.setInt(11, parametre.getTypeBase());
			stmt.setInt(12, parametre.getPriorite());
			stmt.setInt(13, parametre.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private ParametragePrimeC setParametragePrime(ResultSet rs) throws SQLException {
		ParametragePrimeC prm = new ParametragePrimeC();
		prm.setId(rs.getInt("id"));
		if (rs.getObject("id_categorie") != null)
			prm.setIdCategorie(rs.getInt("id_categorie"));
		if (rs.getObject("id_fonction") != null)
			prm.setIdFonction(rs.getInt("id_fonction"));
		if (rs.getObject("id_grade") != null)
			prm.setIdGrade(rs.getInt("id_grade"));
		if (rs.getObject("id_personnel") != null)
			prm.setIdPersonnel(rs.getInt("id_personnel"));
		if (rs.getObject("id_prime") != null) {
			prm.setIdPrime(rs.getInt("id_prime"));
		}
		prm.setTaux(rs.getDouble("percent"));
		prm.setForfait(rs.getDouble("forfait"));
		prm.setPlancher(rs.getDouble("plancher"));
		prm.setPlafond(rs.getDouble("plafond"));
		prm.setCalculHeurSup(rs.getBoolean("clacul_hsup"));
		prm.setTypeBase(rs.getInt("type_base"));
		return prm;
	}

	public List<ParametragePrimeC> getAllParametragePrime(int idPersonnel, int idCategorie, int idGrade,
			int idFonction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametragePrimeC> liste = new ArrayList<ParametragePrimeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePrime) + " WHERE 1=1 ";

		if (idPersonnel > 0) {
			sql = String.valueOf(sql) + " AND id_personnel=" + idPersonnel;
		}
		if (idCategorie > 0) {
			sql = String.valueOf(sql) + " AND id_categorie=" + idCategorie;
		}
		if (idGrade > 0) {
			sql = String.valueOf(sql) + " AND id_grade=" + idGrade;
		}
		if (idFonction > 0) {
			sql = String.valueOf(sql) + " AND id_fonction=" + idFonction;
		}

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next())
				liste.add(setParametragePrime(rs));

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<ParametragePrimeC> getDistictParametragePrime(int idPersonnel, int idCategorie, int idGrade,
			int idFonction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametragePrimeC> liste = new ArrayList<ParametragePrimeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePrime) + " WHERE 1=1";

		if (idPersonnel > 0) {
			sql = String.valueOf(sql) + " AND id_personnel=" + idPersonnel;
		}
		if (idCategorie > 0) {
			sql = String.valueOf(sql) + " AND id_categorie=" + idCategorie;
		}
		if (idGrade > 0) {
			sql = String.valueOf(sql) + " AND id_grade=" + idGrade;
		}
		if (idFonction > 0) {
			sql = String.valueOf(sql) + " AND id_fonction=" + idFonction;
		}
		if (idPersonnel == 0) {
			sql = String.valueOf(sql) + " AND id_personnel IS NULL";
		}
		if (idCategorie == 0) {
			sql = String.valueOf(sql) + " AND id_categorie IS NULL";
		}
		if (idGrade == 0) {
			sql = String.valueOf(sql) + " AND id_grade IS NULL";
		}
		if (idFonction == 0) {
			sql = String.valueOf(sql) + " AND id_fonction IS NULL";
		}
		sql += " GROUP BY id_prime";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			for (; rs.next(); liste.add(setParametragePrime(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public ParametragePrimeC getParametragePrime(int idPersonnel, int idCategorie, int idGrade, int idFonction,
			int idPrime, boolean detail) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametragePrimeC prm = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePrime) + " WHERE 1=1 ";

		if (idPersonnel > 0) {
			sql = String.valueOf(sql) + " AND id_personnel=" + idPersonnel;
		}
		if (idCategorie > 0) {
			sql = String.valueOf(sql) + " AND id_categorie=" + idCategorie;
		}
		if (idGrade > 0) {
			sql = String.valueOf(sql) + " AND id_grade=" + idGrade;
		}
		if (idFonction > 0) {
			sql = String.valueOf(sql) + " AND id_fonction=" + idFonction;
		}
		if (idPrime > 0) {
			sql = String.valueOf(sql) + " AND id_prime=" + idPrime;
		}

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next())
				prm = setParametragePrime(rs);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (detail) {
			if (prm != null)
				prm.setListDetail(getAllParametragePrimeDetail(prm.getId()));
		}
		return prm;
	}

	public ParametragePrimeC getParametrePrime(int idPersonnel, int idCategorie, int idGrade, int idFonction,
			int idPrime, boolean detail) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametragePrimeC prm = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePrime) + " WHERE 1=1 ";

		if (idPersonnel > 0) {
			sql = String.valueOf(sql) + " AND id_personnel=" + idPersonnel;
		}
		if (idCategorie > 0) {
			sql = String.valueOf(sql) + " AND id_categorie=" + idCategorie;
		}

		if (idGrade > 0) {
			sql = String.valueOf(sql) + " AND id_grade=" + idGrade;
		}

		if (idFonction > 0) {
			sql = String.valueOf(sql) + " AND id_fonction=" + idFonction;
		}

		if (idPrime > 0) {
			sql = String.valueOf(sql) + " AND id_prime=" + idPrime;
		}

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next())
				prm = setParametragePrime(rs);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (detail) {
			if (prm != null)
				prm.setListDetail(getAllParametragePrimeDetail(prm.getId()));
		}
		return prm;
	}

	public int getPrioritePrime(int idPersonnel, int idCategorie, int idGrade, int idFonction, int idPrime) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int priority = 0;
		String sql = "SELECT priorite FROM " + Tables.getTableName(Tables.TableName.parametragePrime) + " WHERE 1=1 ";

		if (idPersonnel > 0) {
			sql = String.valueOf(sql) + " AND id_personnel=" + idPersonnel;
		} else {
			sql = String.valueOf(sql) + " AND id_personnel IS NULL";
		}
		if (idCategorie > 0) {
			sql = String.valueOf(sql) + " AND id_categorie=" + idCategorie;
		} else {
			sql = String.valueOf(sql) + " AND id_categorie IS NULL";
		}
		if (idGrade > 0) {
			sql = String.valueOf(sql) + " AND id_grade=" + idGrade;
		} else {
			sql = String.valueOf(sql) + " AND id_grade IS NULL";
		}
		if (idFonction > 0) {
			sql = String.valueOf(sql) + " AND id_fonction=" + idFonction;
		} else {
			sql = String.valueOf(sql) + " AND id_fonction IS NULL";
		}
		if (idPrime > 0) {
			sql = String.valueOf(sql) + " AND id_prime=" + idPrime;
		} else {
			sql = String.valueOf(sql) + " AND id_prime IS NULL";
		}

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getObject("priorite") != null)
					;
				priority = rs.getInt("priorite");
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return priority;
	}

	public List<ParametragePrimeC> getListParametragePrime(int idPersonnel, int idCategorie, int idGrade,
			int idFonction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametragePrimeC> liste = new ArrayList<ParametragePrimeC>();

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePrime) + " WHERE 1=1";

		if (idPersonnel > 0) {
			sql = String.valueOf(sql) + " AND id_personnel=" + idPersonnel;
		}
		if (idCategorie > 0) {
			sql = String.valueOf(sql) + " AND id_categorie=" + idCategorie;
		}
		if (idGrade > 0) {
			sql = String.valueOf(sql) + " AND id_grade=" + idGrade;
		}
		if (idFonction > 0) {
			sql = String.valueOf(sql) + " AND id_fonction=" + idFonction;
		}
		if (idPersonnel == 0) {
			sql = String.valueOf(sql) + " AND id_personnel IS NULL";
		}
		if (idCategorie == 0) {
			sql = String.valueOf(sql) + " AND id_categorie IS NULL";
		}
		if (idGrade == 0) {
			sql = String.valueOf(sql) + " AND id_grade IS NULL";
		}
		if (idFonction == 0) {
			sql = String.valueOf(sql) + " AND id_fonction IS NULL";
		}

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			for (; rs.next(); liste.add(setParametragePrime(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public boolean deleteParametragePrime(ParametragePrimeC parm) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			saved = deleteList(parm.getId(), "id_entete", Tables.getTableName(Tables.TableName.parametragePrimeDetail),
					conn);
			saved = delete(parm.getId(), Tables.getTableName(Tables.TableName.parametragePrime), conn);

			if (saved) {
				conn.commit();
			} else {
				saved = false;
				conn.rollback();
			}

		} catch (SQLException e) {

			saved = false;
			System.out.println(e.getMessage());
		}
		return saved;
	}

	public ParametragePrimeC getParametragePrime(int id, boolean detail) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametragePrimeC prm = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePrime) + " WHERE id=" + id;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next())
				prm = setParametragePrime(rs);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (detail) {
			if (prm != null)
				prm.setListDetail(getAllParametragePrimeDetail(prm.getId()));
		}
		return prm;
	}

	private boolean insertParametragePrimeDetail(ParametragePrimeDetailC detail, Connection conn) {
		PreparedStatement stmt = null;

		boolean saved = false;

		detail.setId(getId(Tables.getTableName(Tables.TableName.parametragePrimeDetail)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametragePrimeDetail)
				+ " (id_entete,code_element,taux,forfait,plancher,plafon)  VALUES (?,?,?,?,?,?) ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, detail.getIdEntete());
			stmt.setString(2, detail.getCodeElement());
			stmt.setDouble(3, detail.getTaux());
			stmt.setDouble(4, detail.getForfait());
			stmt.setDouble(5, detail.getPlancher());
			stmt.setDouble(6, detail.getPlafon());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println(e.getMessage());
				e1.printStackTrace();
			}

		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametragePrimeDetail(ParametragePrimeDetailC detail, Connection conn) {
		PreparedStatement stmt = null;

		boolean saved = false;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametragePrimeDetail)
				+ " code_element=?,taux=?,forfait=?,plancher=?,plafon=? WHERE id=? ";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, detail.getCodeElement());
			stmt.setDouble(2, detail.getTaux());
			stmt.setDouble(3, detail.getForfait());
			stmt.setDouble(4, detail.getPlancher());
			stmt.setDouble(5, detail.getPlafon());
			stmt.setInt(6, detail.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println(e.getMessage());
				e1.printStackTrace();
			}

		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private ParametragePrimeDetailC setParametragePrimeDetail(ResultSet rs) throws SQLException {
		ParametragePrimeDetailC det = new ParametragePrimeDetailC();
		det.setId(rs.getInt("id"));
		det.setTaux(rs.getDouble("taux"));
		det.setForfait(rs.getDouble("forfait"));
		det.setPlancher(rs.getDouble("plancher"));
		det.setPlafon(rs.getDouble("plafon"));
		det.setCodeElement(rs.getString("code_element"));
		det.setDisable(false);
		return det;
	}

	public List<ParametragePrimeDetailC> getAllParametragePrimeDetail(int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametragePrimeDetailC> listDet = new ArrayList<ParametragePrimeDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePrimeDetail);
		if (idEntete > 0) {
			sql = String.valueOf(sql) + " WHERE id_entete=" + idEntete;
		}
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			for (; rs.next(); listDet.add(setParametragePrimeDetail(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listDet;
	}

	public boolean isParametragePrimeDetailExist(String code) {
		boolean exist = false;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePrimeDetail)
				+ " WHERE code_element='" + code + "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				exist = true;
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return exist;
	}

	private boolean insertNatureConge(NatureCongeC nature) {
		boolean saved = false;
		PreparedStatement stmt = null;
		nature.setId(getId(Tables.getTableName(Tables.TableName.natureConge)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.natureConge)
				+ " (id,code,designation,nombre_jour_annuel,nombre_jours_ajoutes,"
				+ "nombre_annees_ajout,duree_max,Jours_conge,id_type_conge,id_personnel,"
				+ "reference_document,date_debut_decision,date_fin_decision) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, nature.getId());
			stmt.setString(2, nature.getCode());
			stmt.setString(3, nature.getDesignation());
			stmt.setInt(4, nature.getNombreJoursAnnuel());
			stmt.setInt(5, nature.getNombreJoursAjoutes());
			stmt.setInt(6, nature.getNombreAnneesAjoutJour());
			stmt.setInt(7, nature.getDureeMax());
			stmt.setInt(8, nature.getJoursConge());
			if (nature.getType() != null) {

				stmt.setInt(9, nature.getType().getId());
			} else {

				stmt.setObject(9, (Object) null);
			}
			if (nature.getPersonnel() != null) {
				stmt.setInt(10, nature.getPersonnel().getId());
			}
			stmt.setString(11, nature.getNumReferenceDecision());
			if (nature.getDateDecision() != null) {
				stmt.setObject(12, nature.getDateDecision());
			}
			if (nature.getDateFinDecision() == null) {

				stmt.setObject(13, null);
			} else {

				stmt.setObject(13, nature.getDateFinDecision());
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

	private boolean updateNatureConge(NatureCongeC nature) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.natureConge)
				+ " SET designation=?,nombre_jour_annuel=?,nombre_jours_ajoutes=?,"
				+ "nombre_annees_ajout=?,duree_max=?,Jours_conge=?,id_type_conge=?,id_personnel=?,"
				+ "reference_document=?,date_debut_decision=?,date_fin_decision=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nature.getDesignation());
			stmt.setInt(2, nature.getNombreJoursAnnuel());
			stmt.setInt(3, nature.getNombreJoursAjoutes());
			stmt.setInt(4, nature.getNombreAnneesAjoutJour());
			stmt.setInt(5, nature.getDureeMax());
			stmt.setInt(6, nature.getJoursConge());
			if (nature.getType() != null) {

				stmt.setInt(7, nature.getType().getId());
			} else {

				stmt.setObject(7, (Object) null);
			}
			if (nature.getPersonnel() != null) {
				stmt.setInt(8, nature.getPersonnel().getId());
			}
			stmt.setString(9, nature.getNumReferenceDecision());
			if (nature.getDateDecision() != null) {
				stmt.setObject(10, nature.getDateDecision());
			}
			if (nature.getDateFinDecision() == null) {

				stmt.setObject(11, null);
			} else {

				stmt.setObject(11, nature.getDateFinDecision());
			}
			stmt.setInt(12, nature.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateNatureConge(NatureCongeC nature) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (nature.getId() == 0) {

				saved = insertNatureConge(nature);
			} else {

				saved = updateNatureConge(nature);
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

	private NatureCongeC setNatureConge(ResultSet rs) throws SQLException {
		NatureCongeC nature = new NatureCongeC();
		nature.setId(rs.getInt("id"));
		nature.setCode(rs.getString("code"));
		nature.setDesignation(rs.getString("designation"));
		nature.setNombreJoursAnnuel(rs.getInt("nombre_jour_annuel"));
		nature.setNombreJoursAjoutes(rs.getInt("nombre_jours_ajoutes"));
		nature.setNombreAnneesAjoutJour(rs.getInt("nombre_annees_ajout"));
		nature.setDureeMax(rs.getInt("duree_max"));
		nature.setJoursConge(rs.getInt("Jours_conge"));
		nature.setLibelleJoursConge(Constante.getLibelleJoursConge(nature.getJoursConge()));
		if (rs.getObject("id_type_conge") != null) {
			nature.setType(getTypeConge(rs.getInt("id_type_conge")));
		}
		if (rs.getObject("id_personnel") != null) {
			nature.setPersonnel(getInstance().getBaseById(rs.getInt("id_personnel"),
					Tables.getTableName(Tables.TableName.personnel)));
		}
		nature.setNumReferenceDecision(rs.getString("reference_document"));
		nature.setDateDecision(rs.getDate("date_debut_decision"));
		nature.setDateFinDecision(rs.getDate("date_fin_decision"));
		return nature;
	}

	public NatureCongeC getNatureCongeParReferenceEtCode(String reference, String code) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		NatureCongeC nature = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.natureConge)
				+ " WHERE reference_document=? AND code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, reference);
			stmt.setString(2, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				nature = setNatureConge(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return nature;
	}

	public NatureCongeC getNatureConge(String code, int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		NatureCongeC nature = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.natureConge) + " WHERE code=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				nature = setNatureConge(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return nature;
	}

	public NatureCongeC getNatureConges(String designation, int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		NatureCongeC nature = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.natureConge)
				+ " WHERE designation=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				nature = setNatureConge(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return nature;
	}

	public NatureCongeC getNatureConge(int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		NatureCongeC nature = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.natureConge) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				nature = setNatureConge(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return nature;
	}

	public List<NatureCongeC> getAllNatureConge() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<NatureCongeC> liste = new ArrayList<NatureCongeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.natureConge);

		try {
			stmt = con.prepareStatement(sql);
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setNatureConge(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<NatureCongeC> getListNatureConge(TypeCongeC conge) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<NatureCongeC> liste = new ArrayList<NatureCongeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.natureConge) + " WHERE id_type_conge=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, conge.getId());
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setNatureConge(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertCategoriePersonnel(CategoriePersonnelC categorie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		categorie.setId(getId(Tables.getTableName(Tables.TableName.categoriePersonnel)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.categoriePersonnel)
				+ " (id,id_personnel,code,designation) VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, categorie.getId());
			if (categorie.getPersonnel() != null) {
				stmt.setInt(2, categorie.getPersonnel().getId());
			}
			stmt.setString(3, categorie.getCode());
			stmt.setString(4, categorie.getDesignation());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateCategoriePersonnel(CategoriePersonnelC categorie) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.categoriePersonnel)
				+ " SET id_personnel=?,code=?,designation=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (categorie.getPersonnel() != null) {
				stmt.setInt(1, categorie.getPersonnel().getId());
			}
			stmt.setString(2, categorie.getCode());
			stmt.setString(3, categorie.getDesignation());
			stmt.setInt(4, categorie.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateCategoriePersonnel(CategoriePersonnelC categorie) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (categorie.getId() == 0) {

				saved = insertCategoriePersonnel(categorie);
				if (!saved) {
					categorie.setId(0);
				}
			} else {

				saved = updateCategoriePersonnel(categorie);
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

	private CategoriePersonnelC setCategoriePersonnel(ResultSet rs) throws SQLException {
		CategoriePersonnelC categorie = new CategoriePersonnelC();
		categorie.setId(rs.getInt("id"));
		if (rs.getObject("id_personnel") != null) {
			categorie.setPersonnel(
					getBaseById(rs.getInt("id_personnel"), Tables.getTableName(Tables.TableName.personnel)));
		}
		categorie.setCode(rs.getString("code"));
		categorie.setDesignation(rs.getString("designation"));
		return categorie;
	}

	public CategoriePersonnelC getCategoriePersonnel(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CategoriePersonnelC categorie = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.categoriePersonnel) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				categorie = setCategoriePersonnel(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return categorie;
	}

	public CategoriePersonnelC getCategoriePersonnel(String code, int idPersonnel) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CategoriePersonnelC categorie = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.categoriePersonnel)
				+ " WHERE code=? AND id_personnel=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, idPersonnel);
			rs = stmt.executeQuery();
			if (rs.next()) {
				categorie = setCategoriePersonnel(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return categorie;
	}

	public CategoriePersonnelC getCategoriePersonnelByDesignationNotCurrent(Base personnel, String designation,
			int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CategoriePersonnelC categorie = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.categoriePersonnel)
				+ " WHERE id_personnel=? AND designation=? AND id<>?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, personnel.getId());
			pstmt.setString(2, designation);
			pstmt.setInt(3, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				categorie = setCategoriePersonnel(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, rs);
		}
		return categorie;
	}

	public List<CategoriePersonnelC> getListeCategoriePersonnel(Base personnel) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<CategoriePersonnelC> liste = new ArrayList<CategoriePersonnelC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.categoriePersonnel) + " WHERE 1=1";
		if (personnel != null)
			sql += " AND id_personnel=" + personnel.getId();
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				liste.add(setCategoriePersonnel(rs));
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertTypeCritere(TypeCritereC type) {
		boolean saved = false;
		PreparedStatement stmt = null;
		type.setId(getId(Tables.getTableName(Tables.TableName.typeCritere)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.typeCritere)
				+ " (id,code,designation,note_appreciation_globale) " + " VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, type.getId());
			stmt.setString(2, type.getCode());
			stmt.setString(3, type.getDesignation());
			stmt.setDouble(4, type.getNoteAppreciationGlobale());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateTypeCritere(TypeCritereC type) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.typeCritere) + " SET  "
				+ " code=?,designation=?,note_appreciation_globale=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, type.getCode());
			stmt.setString(2, type.getDesignation());
			stmt.setDouble(3, type.getNoteAppreciationGlobale());
			stmt.setInt(4, type.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateTypeCritere(TypeCritereC type) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (type.getId() == 0) {

				saved = insertTypeCritere(type);
				if (!saved) {
					type.setId(0);
				}
			} else {

				saved = updateTypeCritere(type);
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

	private TypeCritereC setTypeCritere(ResultSet rs) throws SQLException {
		TypeCritereC type = new TypeCritereC();
		type.setId(rs.getInt("id"));
		type.setCode(rs.getString("code"));
		type.setDesignation(rs.getString("designation"));
		type.setNoteAppreciationGlobale(rs.getDouble("note_appreciation_globale"));
		type.setNoteAppreciationGlobaleS(
				HelperC.TraitementMontant.getMontantFormate(type.getNoteAppreciationGlobale(), 0));
		return type;
	}

	public TypeCritereC getTypeCritere(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TypeCritereC type = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCritere) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				type = setTypeCritere(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return type;
	}

	public TypeCritereC getTypeCritere(String code) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TypeCritereC type = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCritere) + " WHERE code=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				type = setTypeCritere(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return type;
	}

	public TypeCritereC getTypeCritere(String code, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TypeCritereC type = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCritere) + " WHERE code=? AND id<>?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				type = setTypeCritere(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return type;
	}

	public boolean deleteTypeCritere(TypeCritereC type) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(type.getId(), Tables.getTableName(Tables.TableName.typeCritere));

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

	public List<TypeCritereC> getAllTypeCritere() {
		ResultSet rs = null;
		List<TypeCritereC> liste = new ArrayList<TypeCritereC>();
		Statement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.typeCritere);

		try {
			stmt = con.createStatement();
			for (rs = stmt.executeQuery(sql); rs.next(); liste.add(setTypeCritere(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertCritereEvaluation(CritereEvaluationC crietere) {
		boolean saved = false;
		PreparedStatement stmt = null;
		crietere.setId(getId(Tables.getTableName(Tables.TableName.critereEvaluation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.critereEvaluation)
				+ " (id,code,designation,note_general) " + " VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, crietere.getId());
			stmt.setString(2, crietere.getCode());
			stmt.setString(3, crietere.getDesignation());
			stmt.setDouble(4, crietere.getNoteGeneral());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateCritereEvaluation(CritereEvaluationC crietere) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.critereEvaluation) + " SET  "
				+ " code=?,designation=?,note_general=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, crietere.getCode());
			stmt.setString(2, crietere.getDesignation());
			stmt.setDouble(3, crietere.getNoteGeneral());
			stmt.setInt(4, crietere.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateCritereEvaluation(CritereEvaluationC critere) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (critere.getId() == 0) {

				saved = insertCritereEvaluation(critere);
				if (!saved) {
					critere.setId(0);
				}
			} else {

				saved = updateCritereEvaluation(critere);
			}
			for (DetailCritereEvaluationC detail : critere.getListDetailCritere()) {

				detail.setCritere(critere);

				if (detail.getId() == 0) {

					saved = insertDetailCritereEvaluation(detail);
					continue;
				}
				saved = updateDetailCritereEvaluation(detail);
			}

			for (DetailCritereEvaluationC detail : critere.getListDetailCritereDeleted()) {
				delete(detail.getId(), Tables.getTableName(Tables.TableName.detailCritereEvaluation));
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

	private CritereEvaluationC setCritereEvaluation(ResultSet rs) throws SQLException {
		CritereEvaluationC crietere = new CritereEvaluationC();
		crietere.setId(rs.getInt("id"));
		crietere.setCode(rs.getString("code"));
		crietere.setDesignation(rs.getString("designation"));
		crietere.setNoteGeneral(rs.getDouble("note_general"));
		crietere.setNoteGeneralS(HelperC.TraitementMontant.getMontantFormate(crietere.getNoteGeneral(), 0));
		return crietere;
	}

	public CritereEvaluationC getCritereEvaluation(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CritereEvaluationC crietere = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.critereEvaluation) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				crietere = setCritereEvaluation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return crietere;
	}

	public CritereEvaluationC getCritereEvaluation(String code, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CritereEvaluationC crietere = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.critereEvaluation)
				+ " WHERE code=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				crietere = setCritereEvaluation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return crietere;
	}

	public CritereEvaluationC getCritereEvaluation(String code) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CritereEvaluationC crietere = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.critereEvaluation) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				crietere = setCritereEvaluation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return crietere;
	}

	public boolean deleteCritereEvaluation(CritereEvaluationC crietere) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteAllDetailCritereEvaluation(crietere);

			if (deleted) {
				deleted = deleteNotAutocommit(crietere.getId(),
						Tables.getTableName(Tables.TableName.critereEvaluation));
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

	public List<CritereEvaluationC> getAllCritereEvaluation() {
		ResultSet rs = null;
		List<CritereEvaluationC> liste = new ArrayList<CritereEvaluationC>();
		Statement stmt = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.critereEvaluation);

		try {
			stmt = con.createStatement();
			for (rs = stmt.executeQuery(sql); rs.next(); liste.add(setCritereEvaluation(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	private boolean insertDetailCritereEvaluation(DetailCritereEvaluationC detail) {
		boolean saved = false;
		PreparedStatement stmt = null;
		detail.setId(getId(Tables.getTableName(Tables.TableName.detailCritereEvaluation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailCritereEvaluation) + " "
				+ "(id,id_entete,designation,note) " + "VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, detail.getId());
			if (detail.getCritere() != null) {

				stmt.setInt(2, detail.getCritere().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setString(3, detail.getDesignation());
			stmt.setDouble(4, detail.getNote());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailCritereEvaluation(DetailCritereEvaluationC detail) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailCritereEvaluation) + " "
				+ "SET id_entete=?,designation=?,note=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			if (detail.getCritere() != null) {

				stmt.setInt(1, detail.getCritere().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setString(2, detail.getDesignation());
			stmt.setDouble(3, detail.getNote());
			stmt.setInt(4, detail.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean deleteAllDetailCritereEvaluation(CritereEvaluationC critere) {
		boolean deleted = false;
		PreparedStatement stmt = null;
		String sql = "DELETE FOM " + Tables.getTableName(Tables.TableName.detailCritereEvaluation)
				+ " WHERE id_entete=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, critere.getId());
			stmt.execute();
			deleted = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	public List<DetailCritereEvaluationC> getListeDetailCritereEvaluation(CritereEvaluationC critere) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<DetailCritereEvaluationC> liste = new ArrayList<DetailCritereEvaluationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailCritereEvaluation)
				+ " WHERE id_entete=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, critere.getId());
			for (rs = stmt.executeQuery(); rs.next(); liste.add(setDetailCritereEvaluation(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<DetailCritereEvaluationC> getAllDetailCritereEvaluation() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<DetailCritereEvaluationC> liste = new ArrayList<DetailCritereEvaluationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailCritereEvaluation);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			for (; rs.next(); liste.add(setDetailCritereEvaluation(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public DetailCritereEvaluationC getDetailCritereEvaluation(int id) {
		DetailCritereEvaluationC detail = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.detailCritereEvaluation) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				detail = setDetailCritereEvaluation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detail;
	}

	private DetailCritereEvaluationC setDetailCritereEvaluation(ResultSet rs) throws SQLException {
		DetailCritereEvaluationC deta = new DetailCritereEvaluationC();
		deta.setId(rs.getInt("id"));
		if (rs.getObject("id_entete") != null) {
			deta.setCritere(getInstance().getCritereEvaluation(rs.getInt("id_entete")));
		}
		deta.setDesignation(rs.getString("designation"));
		deta.setNote(rs.getDouble("note"));
		deta.setNoteS(HelperC.TraitementMontant.getMontantFormate(deta.getNote(), 2));
		return deta;
	}

	private boolean insertGradePersonnelDetailNiveauFormation(GradePersonnelDetailC grade) {
		boolean saved = false;
		PreparedStatement stmt = null;
		grade.setId(getId(Tables.getTableName(Tables.TableName.gradePersonnelDetail)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.gradePersonnelDetail)
				+ " (id,id_grade,id_niveau,taux_bonus_salaire)  VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, grade.getId());
			if (grade.getGrade() != null) {

				stmt.setInt(2, grade.getGrade().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			if (grade.getNiveau() != null) {

				stmt.setInt(3, grade.getNiveau().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}
			stmt.setDouble(4, grade.getTauxBonusSalaire());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateGradePersonnelDetailNiveauFormation(GradePersonnelDetailC grade) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.gradePersonnelDetail)
				+ " SET  + id_grade=?,id_niveau=?,taux_bonus_salaire=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (grade.getGrade() != null) {

				stmt.setInt(1, grade.getGrade().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			if (grade.getNiveau() != null) {

				stmt.setInt(2, grade.getNiveau().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, grade.getTauxBonusSalaire());
			stmt.setInt(4, grade.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateGradePersonnelDetailNiveauFormation(GradePersonnelDetailC grade) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (grade.getId() == 0) {

				saved = insertGradePersonnelDetailNiveauFormation(grade);
				if (!saved) {
					grade.setId(0);
				}
			} else {

				saved = updateGradePersonnelDetailNiveauFormation(grade);
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

	private GradePersonnelDetailC setGradePersonnelDetailNiveauFormation(ResultSet rs) throws SQLException {
		GradePersonnelDetailC grade = new GradePersonnelDetailC();
		grade.setId(rs.getInt("id"));
		if (rs.getObject("id_grade") != null) {
			grade.setGrade(getGradePersonnel(rs.getInt("id_grade")));
		}
		if (rs.getObject("id_niveau") != null) {
			grade.setNiveau(getBaseById(rs.getInt("id_niveau"), Tables.getTableName(Tables.TableName.niveauFormation)));
		}
		grade.setTauxBonusSalaire(rs.getDouble("taux_bonus_salaire"));
		grade.setTauxBonusSalaireS(HelperC.TraitementMontant.getMontantFormate(grade.getTauxBonusSalaire(), 2));
		return grade;
	}

	public GradePersonnelDetailC getGradePersonnelDetailNiveauFormation(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		GradePersonnelDetailC grade = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.gradePersonnelDetail) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				grade = setGradePersonnelDetailNiveauFormation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grade;
	}

	public boolean deleteGradePersonnelDetailNiveauFormation(GradePersonnelDetailC grade) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(grade.getId(), Tables.getTableName(Tables.TableName.gradePersonnelDetail));

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

	public List<GradePersonnelDetailC> getListeDetailNiveauFormation(GradePersonnelC grad) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<GradePersonnelDetailC> liste = new ArrayList<GradePersonnelDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.gradePersonnelDetail)
				+ " WHERE id_grade=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, grad.getId());
			rs = stmt.executeQuery();
			for (; rs.next(); liste.add(setGradePersonnelDetailNiveauFormation(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public GradePersonnelDetailC getGradePersonnelDetailNiveauFormation(int idGrade, int idNiveau) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		GradePersonnelDetailC grade = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.gradePersonnelDetail)
				+ " WHERE id_grade=? AND id_niveau=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idGrade);
			stmt.setInt(2, idNiveau);
			rs = stmt.executeQuery();
			if (rs.next()) {
				grade = setGradePersonnelDetailNiveauFormation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grade;
	}

	private boolean insertGradePersonnel(GradePersonnelC grade) {
		boolean saved = false;
		PreparedStatement stmt = null;
		grade.setId(getId(Tables.getTableName(Tables.TableName.gradePersonnel)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.gradePersonnel)
				+ " (id,id_categorie_personnel,code,designation,traitement_mensuel,grd_inferieure) VALUES (?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, grade.getId());
			if (grade.getCategoriePersonnel() != null) {

				stmt.setInt(2, grade.getCategoriePersonnel().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setString(3, grade.getCode());
			stmt.setString(4, grade.getDesignation());
			stmt.setDouble(5, grade.getTraitementMensuel());
			stmt.setInt(6, grade.getIdGradeInferieur());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateGradePersonnel(GradePersonnelC grade) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.gradePersonnel)
				+ " SET id_categorie_personnel=?,designation=?,traitement_mensuel=?,grd_inferieure=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (grade.getCategoriePersonnel() != null) {

				stmt.setInt(1, grade.getCategoriePersonnel().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setString(2, grade.getDesignation());
			stmt.setDouble(3, grade.getTraitementMensuel());
			stmt.setInt(4, grade.getIdGradeInferieur());
			stmt.setInt(5, grade.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateGradePersonnel(GradePersonnelC grade) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (grade.getId() == 0) {

				saved = insertGradePersonnel(grade);
				if (!saved) {
					grade.setId(0);
				}
			} else {

				saved = updateGradePersonnel(grade);
			}
			if (saved) {

				for (GradePersonnelDetailC detail : grade.getListNiveau()) {

					detail.setGrade(grade);
					if (detail.getId() == 0) {

						saved = insertGradePersonnelDetailNiveauFormation(detail);
						continue;
					}
					saved = updateGradePersonnelDetailNiveauFormation(detail);
				}

				for (GradePersonnelDetailC detail : grade.getListNiveauDeleted()) {
					delete(detail.getId(), Tables.getTableName(Tables.TableName.gradePersonnelDetail));
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

	private GradePersonnelC setGradePersonnel(ResultSet rs) throws SQLException {
		GradePersonnelC grade = new GradePersonnelC();
		grade.setId(rs.getInt("id"));
		if (rs.getObject("id_categorie_personnel") != null) {
			grade.setCategoriePersonnel(getCategoriePersonnel(rs.getInt("id_categorie_personnel")));
		}
		grade.setDesignation(rs.getString("designation"));
		grade.setCode(rs.getString("code"));
		grade.setTraitementMensuel(rs.getDouble("traitement_mensuel"));
		grade.setIdGradeInferieur(rs.getInt("grd_inferieure"));
		grade.setTraitementMensuelS(HelperC.TraitementMontant.getMontantFormate(grade.getTraitementMensuel(), 2));
		return grade;
	}

	public GradePersonnelC getGradeSuperieur(int idGrdInf) {
		Statement stmt = null;
		ResultSet rs = null;
		GradePersonnelC grade = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.gradePersonnel) + " WHERE grd_inferieure="
				+ idGrdInf;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				grade = setGradePersonnel(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grade;
	}

	public GradePersonnelC getGradePersonnel(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		GradePersonnelC grade = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.gradePersonnel) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				grade = setGradePersonnel(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grade;
	}

	public GradePersonnelC getGradePersonnel(String code) {
		GradePersonnelC grad = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM " + Tables.getTableName(Tables.TableName.gradePersonnel) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);

			rs = stmt.executeQuery();
			if (rs.next()) {
				grad = setGradePersonnel(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grad;
	}

	public List<GradePersonnelC> getListGradeParCategoriePersonnel(int IdCategorie) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<GradePersonnelC> grades = new ArrayList<GradePersonnelC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.gradePersonnel) + " WHERE 1=1 ";
		if (IdCategorie > 0) {
			sql = String.valueOf(sql) + " AND id_categorie_personnel=" + IdCategorie;
		}

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			for (; rs.next(); grades.add(setGradePersonnel(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grades;
	}

	public boolean deleteGradePersonnel(GradePersonnelC grade) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(grade.getId(), Tables.getTableName(Tables.TableName.gradePersonnel));

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

	public boolean insertUpdateGrilleCotisation(GrilleCotisationC grille) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (grille.getId() == 0) {

				saved = insertGrilleCotisation(grille);
				if (!saved) {
					grille.setId(0);
				}
			} else {

				saved = updateGrilleCotisation(grille);
			}
			if (saved) {

				if (grille.getListDetail().size() > 0) {
					for (DetailGrilleCotisationC det : grille.getListDetail()) {

						det.setIdGrille(grille.getId());
						if (det.getId() == 0) {

							insertGrilleCotisationDetail(det);
							continue;
						}
						updateGrilleCotisationDetail(det);
					}
				}

				if (grille.getListDeleted().size() > 0) {

					for (DetailGrilleCotisationC det : grille.getListDeleted()) {
						deleteNotCommited(det.getId(), Tables.getTableName(Tables.TableName.grilleCotisationDetail));
					}
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

	private boolean insertGrilleCotisation(GrilleCotisationC grille) {
		boolean saved = false;
		PreparedStatement stmt = null;
		grille.setId(getId(Tables.getTableName(Tables.TableName.grilleCotisation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.grilleCotisation)
				+ " (id,date_debut,date_fin,coefficient) " + " VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, grille.getId());
			stmt.setObject(2, grille.getDateDebut());
			stmt.setObject(3, grille.getDateFin());
			stmt.setDouble(4, grille.getCoefficient());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateGrilleCotisation(GrilleCotisationC grille) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.grilleCotisation) + " SET  "
				+ " date_debut=?,date_fin=?,coefficient=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, grille.getDateDebut());
			stmt.setObject(2, grille.getDateFin());
			stmt.setDouble(3, grille.getCoefficient());
			stmt.setInt(4, grille.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private GrilleCotisationC setGrilleCotisation(ResultSet rs) throws SQLException {
		GrilleCotisationC grille = new GrilleCotisationC();
		grille.setId(rs.getInt("id"));
		grille.setCoefficient(rs.getDouble("coefficient"));
		grille.setDateDebut(rs.getDate("date_debut"));
		if (rs.getDate("date_fin") != null) {
			grille.setDateFin(rs.getDate("date_fin"));
		}
		return grille;
	}

	public GrilleCotisationC getGrilleCotisation(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		GrilleCotisationC grille = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.grilleCotisation) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				grille = setGrilleCotisation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grille;
	}

	public GrilleCotisationC getGrilleCotisation() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		GrilleCotisationC grille = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.grilleCotisation)
				+ " WHERE date_fin IS NULL ";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				grille = setGrilleCotisation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grille;
	}

	public boolean deleteGrilleCotisation(GrilleCotisationC grille) {
		boolean deleted = false;
		PreparedStatement stmt = null;

		try {
			con.setAutoCommit(false);
			deleted = deleteList(grille.getId(), "id_grille",
					Tables.getTableName(Tables.TableName.grilleCotisationDetail));
			if (deleted) {
				deleted = deleteNotAutocommit(grille.getId(), Tables.getTableName(Tables.TableName.grilleCotisation));
			}
			if (deleted) {

				con.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return deleted;
	}

	private boolean insertGrilleCotisationDetail(DetailGrilleCotisationC detail) {
		boolean saved = false;
		PreparedStatement stmt = null;
		detail.setId(getId(Tables.getTableName(Tables.TableName.grilleCotisationDetail)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.grilleCotisationDetail)
				+ " (id,categorie,tranche_min,tranche_max,points_achete,valeur_achat,id_grille) "
				+ " VALUES (?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, detail.getId());
			stmt.setObject(2, detail.getCategorie());
			stmt.setDouble(3, detail.getTrancheMin());
			stmt.setDouble(4, detail.getTrancheMax());
			stmt.setDouble(5, detail.getPointAchete());
			stmt.setDouble(6, detail.getValeurAchat());
			stmt.setDouble(7, detail.getIdGrille());
			stmt.execute();
			saved = true;
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateGrilleCotisationDetail(DetailGrilleCotisationC detail) {
		boolean saved = false;
		PreparedStatement stmt = null;
		detail.setId(getId(Tables.getTableName(Tables.TableName.grilleCotisationDetail)));
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.grilleCotisationDetail) + " SET  "
				+ " categorie=?,tranche_min=?,tranche_max=?,points_achete=?,valeur_achat=?,id_grille=?" +

				"  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, detail.getCategorie());
			stmt.setDouble(2, detail.getTrancheMin());
			stmt.setDouble(3, detail.getTrancheMax());
			stmt.setDouble(4, detail.getPointAchete());
			stmt.setDouble(5, detail.getValeurAchat());
			stmt.setDouble(6, detail.getIdGrille());
			stmt.setInt(7, detail.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private DetailGrilleCotisationC setGrilleCotisationDetail(ResultSet rs) throws SQLException {
		DetailGrilleCotisationC det = new DetailGrilleCotisationC();
		det.setId(rs.getInt("id"));
		det.setIdGrille(rs.getInt("id_grille"));
		det.setPointAchete(rs.getDouble("points_achete"));
		det.setTrancheMax(rs.getDouble("tranche_max"));
		det.setTrancheMin(rs.getDouble("tranche_min"));
		det.setValeurAchat(rs.getDouble("valeur_achat"));
		det.setCategorie(rs.getString("categorie"));
		return det;
	}

	public List<DetailGrilleCotisationC> getListeGrilleDetail(GrilleCotisationC grille) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DetailGrilleCotisationC> details = new ArrayList<DetailGrilleCotisationC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.grilleCotisationDetail)
				+ " WHERE id_grille=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, grille.getId());
			for (rs = stmt.executeQuery(); rs.next(); details.add(setGrilleCotisationDetail(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return details;
	}

	public DetailGrilleCotisationC getDetail(double montant) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DetailGrilleCotisationC detail = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.grilleCotisationDetail)
				+ " WHERE tranche_min<=" + montant + " && tranche_max>=" + montant;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				detail = setGrilleCotisationDetail(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detail;
	}

	private boolean insertConditionRecrutement(ConditionRecrutementC condition) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.conditionNomination)
				+ " (id,numero_decision,date_debut_application,date_fin_application) VALUES (?,?,?,?)";

		condition.setId(getId(Tables.getTableName(Tables.TableName.conditionNomination)));

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, condition.getId());
			pstmt.setString(2, condition.getNumeroDecision());
			if (condition.getDateDebApplication() != null) {

				pstmt.setObject(3, condition.getDateDebApplication());
			} else {

				pstmt.setObject(3, null);
			}
			if (condition.getDateFinApplication() != null) {

				pstmt.setObject(4, condition.getDateFinApplication());
			} else {

				pstmt.setObject(4, null);
			}
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateConditionRecrutement(ConditionRecrutementC condition) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.conditionNomination)
				+ " SET date_debut_application=?,date_fin_application=? WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			if (condition.getDateDebApplication() != null) {

				pstmt.setObject(1, condition.getDateDebApplication());
			} else {

				pstmt.setObject(1, null);
			}
			if (condition.getDateFinApplication() != null) {

				pstmt.setObject(2, condition.getDateFinApplication());
			} else {

				pstmt.setObject(2, null);
			}
			pstmt.setInt(3, condition.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	public boolean insertUpdateConditionRecrutement(ConditionRecrutementC condition) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (condition.getId() == 0) {

				saved = insertConditionRecrutement(condition);
			} else {

				saved = updateConditionRecrutement(condition);
			}
			if (saved) {

				if (condition.getListDetailCondition().size() > 0) {
					for (ConditionRecrutementDetailC det : condition.getListDetailCondition()) {

						det.setEntete(condition);
						if (det.getId() == 0) {
							saved = insertDetailConditionRecrutement(det);
						}
					}
				}

				if (condition.getListDetailConditionDeleted().size() > 0) {
					for (ConditionRecrutementDetailC det : condition.getListDetailConditionDeleted()) {
						saved = delete(det.getId(), Tables.getTableName(Tables.TableName.conditionNominationDetail));
					}
				}
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

	private ConditionRecrutementC setConditionRecrutement(ResultSet rs) throws SQLException {
		ConditionRecrutementC condition = new ConditionRecrutementC();
		condition.setId(rs.getInt("id"));
		condition.setNumeroDecision(rs.getString("numero_decision"));
		if (rs.getObject("date_debut_application") != null) {
			condition.setDateDebApplication((Date) rs.getObject("date_debut_application"));
		}
		if (rs.getObject("date_fin_application") != null) {
			condition.setDateFinApplication((Date) rs.getObject("date_fin_application"));
		}
		return condition;
	}

	public ConditionRecrutementC getConditionRecrutementById(int idCondition) {
		ConditionRecrutementC cond = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionNomination)
				+ " WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idCondition);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cond = setConditionRecrutement(rs);
			}
			if (cond != null) {
				cond.setListDetailCondition(getAllDetailConditionRecrutementByEntete(cond.getId()));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return cond;
	}

	public ConditionRecrutementC getConditionRecrutementByCode(String numeroDecision) {
		ConditionRecrutementC cond = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionNomination)
				+ " WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setString(1, numeroDecision);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cond = setConditionRecrutement(rs);
			}
			if (cond != null) {
				cond.setListDetailCondition(getAllDetailConditionRecrutementByEntete(cond.getId()));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return cond;
	}

	public List<ConditionRecrutementC> getListeConditionRecrutement(String numeroDecision) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ConditionRecrutementC> liste = new ArrayList<ConditionRecrutementC>();
		ConditionRecrutementC cond = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionNomination)
				+ " WHERE numero_decision LIKE '%" + numeroDecision + "%' ORDER BY numero_decision";

		try {
			stmt = con.prepareStatement(sql);

			for (rs = stmt.executeQuery(sql); rs.next(); liste.add(cond)) {
				cond = setConditionRecrutement(rs);
			}

			if (liste.size() > 0) {

				for (ConditionRecrutementC c : liste) {
					c.setListDetailCondition(getAllDetailConditionRecrutementByEntete(c.getId()));

				}

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public boolean deleteConditionsRecrutement(ConditionRecrutementC condition) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);
			deleted = deleteList(condition.getId(), "id_entete",
					Tables.getTableName(Tables.TableName.conditionNominationDetail));
			if (deleted) {
				deleted = deleteNotAutocommit(condition.getId(),
						Tables.getTableName(Tables.TableName.conditionNomination));
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

	private boolean insertDetailConditionRecrutement(ConditionRecrutementDetailC detail) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.conditionNominationDetail)
				+ " (id,id_entete,id_corp,id_critere_recrutement) VALUES (?,?,?,?)";
		detail.setId(getId(Tables.getTableName(Tables.TableName.conditionNominationDetail)));

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, detail.getId());
			if (detail.getEntete() != null) {
				pstmt.setInt(2, detail.getEntete().getId());
			}
			if (detail.getPersonnel() != null) {
				pstmt.setInt(3, detail.getPersonnel().getId());
			}
			if (detail.getCritereRecrutement() != null) {
				pstmt.setInt(4, detail.getCritereRecrutement().getId());
			}
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private ConditionRecrutementDetailC setDetailConditionRecrutement(ResultSet rs) throws SQLException {
		ConditionRecrutementDetailC detail = new ConditionRecrutementDetailC();
		detail.setId(rs.getInt("id"));
		if (rs.getObject("id_corp") != null) {
			detail.setPersonnel(getBaseById(rs.getInt("id_corp"), Tables.getTableName(Tables.TableName.personnel)));
		}
		if (rs.getObject("id_critere_recrutement") != null) {
			detail.setCritereRecrutement(getBaseById(rs.getInt("id_critere_recrutement"),
					Tables.getTableName(Tables.TableName.criteresRecrutement)));
		}
		return detail;
	}

	private List<ConditionRecrutementDetailC> getAllDetailConditionRecrutementByEntete(int idEntete) {
		List<ConditionRecrutementDetailC> listDetail = new ArrayList<ConditionRecrutementDetailC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionNominationDetail)
				+ " WHERE id_entete=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idEntete);
			rs = pstmt.executeQuery();
			for (; rs.next(); listDetail.add(setDetailConditionRecrutement(rs)))
				;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listDetail;
	}

	public ConditionRecrutementDetailC getDetailConditionRecrutementById(int idDetailCondition) {
		ConditionRecrutementDetailC condition = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionNominationDetail)
				+ " WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idDetailCondition);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				condition = setDetailConditionRecrutement(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return condition;
	}

	public List<ConditionRecrutementDetailC> getAllDetailConditionRecrutementParCorp(int idCorp) {
		List<ConditionRecrutementDetailC> listDetail = new ArrayList<ConditionRecrutementDetailC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionNominationDetail)
				+ " WHERE id_entete=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, idCorp);
			for (rs = pstmt.executeQuery(); rs.next(); listDetail.add(setDetailConditionRecrutement(rs)))
				;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listDetail;
	}

	private boolean insertParametrageAllocationFamilliale(ParametrageAllocationFamillialeC allocation) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageAllocationFamilliale)
				+ "(id,statut_personne,age_max,montant,age_report_max) VALUES (?,?,?,?,?)";
		allocation.setId(getId(Tables.getTableName(Tables.TableName.parametrageAllocationFamilliale)));

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, allocation.getId());
			pstmt.setInt(2, allocation.getStatutPersonnel());
			pstmt.setInt(3, allocation.getAgeMaximal());
			pstmt.setDouble(4, allocation.getMontantAllocation());
			pstmt.setInt(5, allocation.getAgeReportMaximal());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	private boolean updateParametrageAllocationFamilliale(ParametrageAllocationFamillialeC allocation) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageAllocationFamilliale)
				+ " SET age_max=?,montant=?,age_report_max=? WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, allocation.getAgeMaximal());
			pstmt.setDouble(2, allocation.getMontantAllocation());
			pstmt.setInt(3, allocation.getAgeReportMaximal());
			pstmt.setInt(4, allocation.getId());
			pstmt.execute();
			saved = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, null);
		}
		return saved;
	}

	public boolean insertUpdateParametrageAllocationFamilliale(ParametrageAllocationFamillialeC allocation) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (allocation.getId() == 0) {

				saved = insertParametrageAllocationFamilliale(allocation);
			} else {

				saved = updateParametrageAllocationFamilliale(allocation);
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

	private ParametrageAllocationFamillialeC setParametrageAllocationFamilliale(ResultSet rs) throws SQLException {
		ParametrageAllocationFamillialeC allocation = new ParametrageAllocationFamillialeC();
		allocation.setId(rs.getInt("id"));
		allocation.setStatutPersonnel(rs.getInt("statut_personne"));
		allocation.setAgeMaximal(rs.getInt("age_max"));
		allocation.setMontantAllocation(rs.getDouble("montant"));
		allocation.setAgeReportMaximal(rs.getInt("age_report_max"));
		return allocation;
	}

	public ParametrageAllocationFamillialeC getParametrageAllocationFamillialeParId(int IdParametrage) {
		ParametrageAllocationFamillialeC allocation = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAllocationFamilliale)
				+ " WHERE id=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, IdParametrage);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				allocation = setParametrageAllocationFamilliale(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return allocation;
	}

	public ParametrageAllocationFamillialeC getParametrageAllocationFamillialeParStatutPersonne(int statutPersonne) {
		ParametrageAllocationFamillialeC allocation = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAllocationFamilliale)
				+ " WHERE statut_personne=?";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			pstmt.setInt(1, statutPersonne);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				allocation = setParametrageAllocationFamilliale(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return allocation;
	}

	public List<ParametrageAllocationFamillialeC> getAllParametrageAllocationFamilliale() {
		List<ParametrageAllocationFamillialeC> listAllocations = new ArrayList<ParametrageAllocationFamillialeC>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlRequest = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAllocationFamilliale);

		try {
			pstmt = con.prepareStatement(sqlRequest);
			for (rs = pstmt.executeQuery(); rs.next(); listAllocations.add(setParametrageAllocationFamilliale(rs)))
				;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			releaseResource(pstmt, rs);
		}
		return listAllocations;
	}

	public List<GradePersonnelC> getGradesPersonnelParIdCategorie(int idCategorie) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<GradePersonnelC> grades = new ArrayList<GradePersonnelC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.gradePersonnel)
				+ " WHERE id_categorie_personnel=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idCategorie);
			for (rs = stmt.executeQuery(); rs.next(); grades.add(setGradePersonnel(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return grades;
	}

	public List<CategoriePersonnelC> getAllCategoriePersonnelEmploye() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CategoriePersonnelC> liste = new ArrayList<CategoriePersonnelC>();

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.categoriePersonnel);

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			for (; rs.next(); liste.add(setCategoriePersonnel(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, rs);
		}
		return liste;
	}

	public List<CategoriePersonnelC> getListCategoriePersonnelParIdPersonnel(int idPersonnel) {
		Statement pstmt = null;
		ResultSet rs = null;
		List<CategoriePersonnelC> liste = new ArrayList<CategoriePersonnelC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.categoriePersonnel)
				+ " WHERE id_personnel=" + idPersonnel;

		try {
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				liste.add(setCategoriePersonnel(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(pstmt, rs);
		}
		return liste;
	}

	private boolean insertAffectation(AffectationC affectation, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		affectation.setId(getId(Tables.getTableName(Tables.TableName.affectation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.affectation)
				+ " (id,id_employe,id_fonction,date_debut,date_fin,reference,comment) " + " VALUES (?,?,?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, affectation.getId());
			stmt.setInt(2, affectation.getIdEmploye());
			stmt.setInt(3, affectation.getIdFonction());
			stmt.setObject(4, affectation.getDateDebut());
			stmt.setObject(5, affectation.getDateFin());
			stmt.setString(6, affectation.getReference());
			stmt.setString(7, affectation.getComment());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateAffectation(AffectationC affectation, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.affectation)
				+ " SET id_fonction=?,date_debut=?,date_fin=?,reference=?,comment=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, affectation.getIdFonction());
			stmt.setObject(2, affectation.getDateDebut());
			stmt.setObject(3, affectation.getDateFin());
			stmt.setString(4, affectation.getReference());
			stmt.setString(5, affectation.getComment());
			stmt.setInt(6, affectation.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {
			saved = false;
			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateAffectation(AffectationC affectation) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (affectation.getId() == 0) {

				saved = insertAffectation(affectation, conn);
				if (!saved) {
					affectation.setId(0);
				}
			} else {

				saved = updateAffectation(affectation, conn);
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

	private AffectationC setAffectation(ResultSet rs) throws SQLException {
		AffectationC affectation = new AffectationC();
		affectation.setId(rs.getInt("id"));
		affectation.setIdEmploye(rs.getInt("id_employe"));
		affectation.setFonction(getBaseById(rs.getInt("id_fonction"), Tables.getTableName(Tables.TableName.fonction)));
		affectation.setDateDebut(rs.getDate("date_debut"));
		affectation.setDateDebutS(HelperC.convertDate(affectation.getDateDebut()));
		affectation.setComment(rs.getString("comment"));
		if (rs.getObject("date_fin") != null) {

			affectation.setDateFin(rs.getDate("date_fin"));
			affectation.setDateFinS(HelperC.convertDate(affectation.getDateFin()));
		}
		affectation.setReference(rs.getString("reference"));
		return affectation;
	}

	public AffectationC getAffectation(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AffectationC affectation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.affectation) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				affectation = setAffectation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return affectation;
	}

	public AffectationC getAffectationNotCurrent(EmployeC fonctionnaire, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AffectationC affectation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.affectation)
				+ " WHERE id_employe=? AND id<>? AND date_fin IS NULL ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, fonctionnaire.getId());
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				affectation = setAffectation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return affectation;
	}

	public AffectationC getFonctionActuel(int idEmplye) {
		Statement stmt = null;
		ResultSet rs = null;
		AffectationC affectation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.affectation) + " WHERE id_employe="
				+ idEmplye + " AND date_fin IS NULL AND id=(SELECT MAX(id) FROM " + " tbl_affectation WHERE id_employe="
				+ idEmplye + ")";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				affectation = setAffectation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return affectation;
	}

	public AffectationC getAffectationNotCurrent(EmployeC fonctionnaire, Date date, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AffectationC affectation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.affectation)
				+ " WHERE id_employe=? AND id<>? AND date_debut<=? AND date_fin >=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, fonctionnaire.getId());
			stmt.setInt(2, id);
			stmt.setString(3, HelperC.convertDate(date, false));
			stmt.setString(4, HelperC.convertDate(date, false));
			rs = stmt.executeQuery();
			if (rs.next()) {
				affectation = setAffectation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return affectation;
	}

	public AffectationC getAffectationNotCurrent(EmployeC fonctionnaire, Date dateDebut, Date dateFin, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AffectationC affectation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.affectation)
				+ " WHERE id_employe=? AND id<>? " + "AND (date_debut BETWEEN ? AND ? OR date_fin BETWEEN ? AND ?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, fonctionnaire.getId());
			stmt.setInt(2, id);
			stmt.setString(3, HelperC.convertDate(dateDebut, false));
			stmt.setString(4, HelperC.convertDate(dateFin, false));
			stmt.setString(5, HelperC.convertDate(dateDebut, false));
			stmt.setString(6, HelperC.convertDate(dateFin, false));
			rs = stmt.executeQuery();
			if (rs.next()) {
				affectation = setAffectation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return affectation;
	}

	public AffectationC getAffectationByEmployeEtFonction(EmployeC fonctionnaire, Base fonction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AffectationC affectation = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.affectation)
				+ " WHERE id_employe=? AND id_fonction=? AND date_fin IS NULL";

		try {
			stmt = con.prepareStatement(sql);
			if (fonctionnaire != null) {
				stmt.setInt(1, fonctionnaire.getId());
			}
			if (fonction != null) {
				stmt.setInt(2, fonction.getId());
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				affectation = setAffectation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return affectation;
	}

	public Base getFonctionActive(int Idfonctionnaire) {
		Statement stmt = null;
		ResultSet rs = null;
		Base fonction = null;
		String sql = "SELECT id_fonction FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE id="
				+ Idfonctionnaire;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				fonction = getBaseById(rs.getInt("id_fonction"), Tables.getTableName(Tables.TableName.fonction));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return fonction;
	}

	public List<AffectationC> getListeAffectation(String nom, String prenom, String matricule, Base fonction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AffectationC> liste = new ArrayList<AffectationC>();
		String sql = "SELECT A.*,B.matricule FROM " + Tables.getTableName(Tables.TableName.affectation) + " AS A "
				+ "INNER JOIN " + Tables.getTableName(Tables.TableName.employe)
				+ " AS B ON A.id_employe=B.id WHERE 1=1";
		if (matricule != null && !matricule.trim().equals("")) {
			sql = String.valueOf(sql) + " AND B.matricule='" + matricule + "'";
		}
		if (fonction != null) {
			sql = String.valueOf(sql) + " AND A.id_fonction=" + fonction.getId();
		}
		if (nom != null && !nom.equals("")) {
			sql = String.valueOf(sql) + " AND B.nom LIKE'%" + nom + "%'";
		}
		if (prenom != null && !prenom.equals("")) {
			sql = String.valueOf(sql) + " AND B.prenom LIKE'%" + prenom + "%'";
		}
		sql = String.valueOf(sql) + " ORDER BY B.matricule";

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			while (rs.next()) {
				AffectationC affectation = new AffectationC();
				affectation.setId(rs.getInt("id"));
				affectation.setIdEmploye(rs.getInt("id_employe"));
				affectation.setIdFonction(rs.getInt("id_fonction"));

				affectation.setDateDebut(rs.getDate("date_debut"));
				affectation.setDateDebutS(HelperC.convertDate(affectation.getDateDebut()));
				if (rs.getObject("date_fin") != null) {

					affectation.setDateFin(rs.getDate("date_fin"));
					affectation.setDateFinS(HelperC.convertDate(affectation.getDateFin()));
				}
				affectation.setReference(rs.getString("reference"));

				liste.add(affectation);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public boolean deleteAffectation(AffectationC affectation) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(affectation.getId(), Tables.getTableName(Tables.TableName.affectation));

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

	private boolean insertServices(ServicesC service) {
		String sql = "";
		boolean saved = false;
		PreparedStatement stmt = null;
		service.setId(getId(Tables.getTableName(Tables.TableName.services)));
		sql = String.valueOf(sql) + "INSERT INTO " + Tables.getTableName(Tables.TableName.services)
				+ " (id,id_direction,code,designation) " + " VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, service.getId());
			if (service.getDirection() != null) {
				stmt.setInt(2, service.getDirection().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			stmt.setString(3, service.getCode());
			stmt.setString(4, service.getDesignation());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateServices(ServicesC service) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.services)
				+ " SET  id_direction=?,code=?,designation=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (service.getDirection() != null) {
				stmt.setInt(1, service.getDirection().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			stmt.setString(2, service.getCode());
			stmt.setString(3, service.getDesignation());

			stmt.setInt(4, service.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateServices(ServicesC service) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (service.getId() == 0) {

				saved = insertServices(service);
				if (!saved) {
					service.setId(0);
				}
			} else {

				saved = updateServices(service);
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

	private ServicesC setServices(ResultSet rs) throws SQLException {
		ServicesC service = new ServicesC();
		service.setId(rs.getInt("id"));
		if (rs.getObject("id_direction") != null)
			service.setDirection(getDirection(rs.getInt("id_direction")));

		service.setCode(rs.getString("code"));
		service.setDesignation(rs.getString("designation"));

		return service;
	}

	public ServicesC getServices(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ServicesC service = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.services) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				service = setServices(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return service;
	}

	public ServicesC getService(String code, int idDirection) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ServicesC service = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.services)
				+ " WHERE code=? AND id_direction=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, idDirection);

			rs = stmt.executeQuery();
			if (rs.next()) {
				service = setServices(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return service;
	}

	public ServicesC getServicesByDesignationNotCurrent(String designation, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ServicesC service = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.services)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				service = setServices(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return service;
	}

	public List<ServicesC> getListeServices(int idDirection) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ServicesC service = null;
		List<ServicesC> liste = new ArrayList<ServicesC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.services) + " WHERE 1=1 ";
		if (idDirection > 0) {
			sql = String.valueOf(sql) + " AND id_direction=" + idDirection;
		}

		sql = String.valueOf(sql) + " ORDER BY code";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				service = setServices(rs);
				liste.add(service);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public boolean deleteServices(ServicesC service) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(service.getId(), Tables.getTableName(Tables.TableName.services));

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

	private boolean insertSousServices(SousServiceC subService) {
		String sql = "";
		boolean saved = false;
		PreparedStatement stmt = null;
		subService.setId(getId(Tables.getTableName(Tables.TableName.sousService)));
		sql = String.valueOf(sql) + "INSERT INTO " + Tables.getTableName(Tables.TableName.sousService)
				+ " (id,code,designation,id_service,type_sous_service) " + " VALUES (?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, subService.getId());
			stmt.setString(2, subService.getCode());
			stmt.setString(3, subService.getDesignation());
			stmt.setInt(4, subService.getService().getId());
			stmt.setString(5, subService.getTypeSubService());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateSousServices(SousServiceC subService) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.sousService)
				+ " SET  designation=?,id_service=?,type_sous_service=?  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, subService.getDesignation());
			stmt.setInt(2, subService.getService().getId());
			stmt.setString(3, subService.getTypeSubService());

			stmt.setInt(4, subService.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateSousServices(SousServiceC subService) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (subService.getId() == 0) {

				saved = insertSousServices(subService);
				if (!saved) {
					subService.setId(0);
				}
			} else {

				saved = updateSousServices(subService);
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

	private SousServiceC setSousServices(ResultSet rs) throws SQLException {
		SousServiceC sousService = new SousServiceC();
		sousService.setId(rs.getInt("id"));
		sousService.setService(getServices(rs.getInt("id_service")));
		sousService.setCode(rs.getString("code"));
		sousService.setDesignation(rs.getString("designation"));
		sousService.setTypeSubService(rs.getString("type_sous_service"));
		return sousService;
	}

	public SousServiceC getSouServices(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SousServiceC souServ = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.sousService) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				souServ = setSousServices(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return souServ;
	}

	public SousServiceC getSouServices(String code, int idServ) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SousServiceC souServ = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.sousService)
				+ " WHERE code=? AND id_service=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, idServ);

			rs = stmt.executeQuery();
			if (rs.next()) {
				souServ = setSousServices(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return souServ;
	}

	public SousServiceC getSouServices(String code) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SousServiceC souServ = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.sousService) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				souServ = setSousServices(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return souServ;
	}

	public List<SousServiceC> getListSouServices(int idService, String type) {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.sousService) + " WHERE 1=1 ";
		if (idService > 0)
			sql = String.valueOf(sql) + " AND id_service=" + idService;
		if (!type.equals("")) {
			sql = String.valueOf(sql) + " AND type_sous_service='" + type + "'";
		}
		List<SousServiceC> list = new ArrayList<SousServiceC>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(setSousServices(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return list;
	}

	public boolean deleteSousServices(SousServiceC souSrv) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(souSrv.getId(), Tables.getTableName(Tables.TableName.sousService));

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

	private boolean insertParametrageBonificationTitreComplementaire(
			ParametrageBonificationTitreComplementaireC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametrageBonificationTitreComplementaire)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageBonificationTitreComplementaire)
				+ " (id,id_grade,pourcentage,duree_formation) " + " VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getId());
			if (parametrage.getGrade() != null) {

				stmt.setInt(2, parametrage.getGrade().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, parametrage.getPourcentage());
			stmt.setInt(4, parametrage.getDureeFormation());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametrageBonificationTitreComplementaire(
			ParametrageBonificationTitreComplementaireC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageBonificationTitreComplementaire)
				+ " SET  " + " id_grade=?,pourcentage=?,duree_formation=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (parametrage.getGrade() != null) {

				stmt.setInt(1, parametrage.getGrade().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setDouble(2, parametrage.getPourcentage());
			stmt.setInt(3, parametrage.getDureeFormation());
			stmt.setInt(4, parametrage.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateParametrageBonificationTitreComplementaire(
			ParametrageBonificationTitreComplementaireC parametrage) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (parametrage.getId() == 0) {

				saved = insertParametrageBonificationTitreComplementaire(parametrage);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {

				saved = updateParametrageBonificationTitreComplementaire(parametrage);
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

	private ParametrageBonificationTitreComplementaireC setParametrageBonificationTitreComplementaire(ResultSet rs)
			throws SQLException {
		ParametrageBonificationTitreComplementaireC parametrage = new ParametrageBonificationTitreComplementaireC();
		parametrage.setId(rs.getInt("id"));
		if (rs.getObject("id_grade") != null) {
			parametrage.setGrade(getGradePersonnel(rs.getInt("id_grade")));
		}
		parametrage.setPourcentage(rs.getDouble("pourcentage"));
		parametrage.setPourcentageS(HelperC.TraitementMontant.getMontantFormate(parametrage.getPourcentage(), 2));
		parametrage.setDureeFormation(rs.getInt("duree_formation"));
		return parametrage;
	}

	public ParametrageBonificationTitreComplementaireC getParametrageBonificationTitreComplementaire(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageBonificationTitreComplementaireC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageBonificationTitreComplementaire)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageBonificationTitreComplementaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	public boolean deleteParametrageBonificationTitreComplementaire(
			ParametrageBonificationTitreComplementaireC parametrage) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(parametrage.getId(),
					Tables.getTableName(Tables.TableName.parametrageBonificationTitreComplementaire));

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

	public List<ParametrageBonificationTitreComplementaireC> getListeParametrageBonificationTitreComplementaire() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametrageBonificationTitreComplementaireC> ParamBonifications = new ArrayList<ParametrageBonificationTitreComplementaireC>();
		String sql = "SELECT * FROM "
				+ Tables.getTableName(Tables.TableName.parametrageBonificationTitreComplementaire);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			for (; rs.next(); ParamBonifications.add(setParametrageBonificationTitreComplementaire(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return ParamBonifications;
	}

	public ParametrageBonificationTitreComplementaireC getParametrageBonificationTitreComplementaire(
			GradePersonnelC grade) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageBonificationTitreComplementaireC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageBonificationTitreComplementaire)
				+ " WHERE id_grade=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, grade.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageBonificationTitreComplementaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	private boolean insertParametrageBonificationAvancementGrade(ParametrageBonificationAvancementGradeC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametrageBonificationAvancementGrade)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageBonificationAvancementGrade)
				+ " (id,id_grade,taux_bonification) " + " VALUES (?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getId());
			if (parametrage.getGrade() != null) {

				stmt.setInt(2, parametrage.getGrade().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setDouble(3, parametrage.getTauxBonification());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametrageBonificationAvancementGrade(ParametrageBonificationAvancementGradeC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageBonificationAvancementGrade) + " SET  "
				+ " id_grade=?,taux_bonification=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			if (parametrage.getGrade() != null) {

				stmt.setInt(1, parametrage.getGrade().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}
			stmt.setDouble(2, parametrage.getTauxBonification());
			stmt.setInt(3, parametrage.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateParametrageBonificationAvancementGrade(
			ParametrageBonificationAvancementGradeC parametrage) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (parametrage.getId() == 0) {

				saved = insertParametrageBonificationAvancementGrade(parametrage);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {

				saved = updateParametrageBonificationAvancementGrade(parametrage);
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

	private ParametrageBonificationAvancementGradeC setParametrageBonificationAvancementGrade(ResultSet rs)
			throws SQLException {
		ParametrageBonificationAvancementGradeC parametrage = new ParametrageBonificationAvancementGradeC();
		parametrage.setId(rs.getInt("id"));
		if (rs.getObject("id_grade") != null) {
			parametrage.setGrade(getGradePersonnel(rs.getInt("id_grade")));
		}
		parametrage.setTauxBonification(rs.getDouble("taux_bonification"));
		parametrage.setTauxBonificationS(
				HelperC.TraitementMontant.getMontantFormate(parametrage.getTauxBonification(), 0));
		return parametrage;
	}

	public ParametrageBonificationAvancementGradeC getParametrageBonificationAvancementGrade(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageBonificationAvancementGradeC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageBonificationAvancementGrade)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageBonificationAvancementGrade(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	public ParametrageBonificationAvancementGradeC getParametrageBonificationAvancementGrade(GradePersonnelC grade) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageBonificationAvancementGradeC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageBonificationAvancementGrade)
				+ " WHERE id_grade=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, grade.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageBonificationAvancementGrade(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	public boolean deleteParametrageBonificationAvancementGrade(ParametrageBonificationAvancementGradeC parametrage) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(parametrage.getId(),
					Tables.getTableName(Tables.TableName.parametrageBonificationAvancementGrade));

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

	public List<ParametrageBonificationAvancementGradeC> getListeParametrageBonificationAvancement() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametrageBonificationAvancementGradeC> ParamBonifications = new ArrayList<ParametrageBonificationAvancementGradeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageBonificationAvancementGrade);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ParamBonifications.add(setParametrageBonificationAvancementGrade(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return ParamBonifications;
	}

	private boolean insertParametrageAvancementGrade(ParametrageAvancementGradeC parametrage, Connection connx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametrageAvancementGrade)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageAvancementGrade)
				+ " (id, id_personnel, id_categorie, nombre_cotation, type_notation, type_appreciation)"
				+ "  VALUES (?,?,?,?,?,?)";

		try {
			stmt = connx.prepareStatement(sql);

			stmt.setInt(1, parametrage.getId());
			if (parametrage.getPersonnel() != null) {

				stmt.setInt(2, parametrage.getPersonnel().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}

			if (parametrage.getCategorie() != null) {

				stmt.setInt(3, parametrage.getCategorie().getId());
			} else {

				stmt.setObject(3, (Object) null);
			}

			stmt.setInt(4, parametrage.getNombreDeFoisNotation());

			if (parametrage.getTypeNotation() != null)
				stmt.setInt(5, parametrage.getTypeNotation().getId());
			else
				stmt.setObject(5, null);

			if (parametrage.getTypeAppreciation() != null) {

				stmt.setInt(6, parametrage.getTypeAppreciation().getId());
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

	private boolean updateParametrageAvancementGrade(ParametrageAvancementGradeC parametrage, Connection connx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageAvancementGrade)
				+ " SET id_personnel=?, id_categorie=?, nombre_cotation=?, type_notation=?, type_appreciation =? WHERE id=?";

		try {
			stmt = connx.prepareStatement(sql);
			if (parametrage.getPersonnel() != null) {

				stmt.setInt(1, parametrage.getPersonnel().getId());
			} else {

				stmt.setObject(1, (Object) null);
			}

			if (parametrage.getCategorie() != null) {

				stmt.setInt(2, parametrage.getCategorie().getId());
			} else {

				stmt.setObject(2, (Object) null);
			}

			stmt.setInt(3, parametrage.getNombreDeFoisNotation());

			if (parametrage.getTypeNotation() != null)
				stmt.setInt(4, parametrage.getTypeNotation().getId());
			else
				stmt.setObject(4, null);

			if (parametrage.getTypeAppreciation() != null) {

				stmt.setInt(5, parametrage.getTypeAppreciation().getId());
			} else {

				stmt.setObject(5, (Object) null);
			}

			stmt.setInt(6, parametrage.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateParametrageAvancementGrade(ParametrageAvancementGradeC parametrage) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (parametrage.getId() == 0) {

				saved = insertParametrageAvancementGrade(parametrage, conn);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {

				saved = updateParametrageAvancementGrade(parametrage, conn);
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

	private ParametrageAvancementGradeC setParametrageAvancementGrade(ResultSet rs) throws SQLException {
		ParametrageAvancementGradeC parametrage = new ParametrageAvancementGradeC();
		parametrage.setId(rs.getInt("id"));
		if (rs.getObject("type_notation") != null) {
			parametrage.setTypeNotation(
					getBaseById(rs.getInt("type_notation"), Tables.getTableName(Tables.TableName.typeNotation)));
		}
		parametrage.setNombreDeFoisNotation(rs.getInt("nombre_cotation"));
		if (rs.getObject("type_appreciation") != null) {
			parametrage.setTypeAppreciation(getBaseById(rs.getInt("type_appreciation"),
					Tables.getTableName(Tables.TableName.typeAppreciationAvancement)));
		}

		if (rs.getObject("id_categorie") != null) {
			parametrage.setCategorie(getCategoriePersonnel(rs.getInt("id_categorie")));
		}
		if (rs.getObject("id_personnel") != null) {
			parametrage.setPersonnel(
					getBaseById(rs.getInt("id_personnel"), Tables.getTableName(Tables.TableName.personnel)));
		}
		return parametrage;
	}

	public ParametrageAvancementGradeC getParametrageAvancementGrade(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageAvancementGradeC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAvancementGrade)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageAvancementGrade(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	public boolean deleteParametrageAvancementGrade(ParametrageAvancementGradeC parametrage) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			if (deleted) {
				deleted = deleteNotAutocommit(parametrage.getId(),
						Tables.getTableName(Tables.TableName.parametrageAvancementGrade));
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

	public ParametrageAvancementGradeC getParametreAvancementGrade(int idPrs, int idCat, int idNot) {
		Statement stmt = null;
		ResultSet rs = null;
		ParametrageAvancementGradeC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAvancementGrade)
				+ " WHERE 1=1  ";
		if (idPrs > 0)
			sql += " AND id_personnel=" + idPrs;
		if (idCat > 0)
			sql += " AND id_categorie=" + idCat;
		if (idNot > 0)
			sql += " AND type_notation=" + idNot;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				parametrage = setParametrageAvancementGrade(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	public ParametrageAvancementGradeC getParametrageAvancementGrade(GradePersonnelC grade) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageAvancementGradeC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAvancementGrade)
				+ " WHERE id_ancien_grade=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, grade.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageAvancementGrade(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	public List<ParametrageAvancementGradeC> getListeParametrageAvancement() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametrageAvancementGradeC> ParamAvancement = new ArrayList<ParametrageAvancementGradeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAvancementGrade);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ParamAvancement.add(setParametrageAvancementGrade(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return ParamAvancement;
	}

	public boolean insertUpdateParametrageAvancementSalaire(ParametreAvancementSalaireC avanc) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (avanc.getId() == 0) {

				saved = insertParametrageAvancementSalaire(avanc, conn);
				if (!saved) {
					avanc.setId(0);
				}
			} else {

				saved = updateParametrageAvancementSalaire(avanc, conn);
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

	private boolean insertParametrageAvancementSalaire(ParametreAvancementSalaireC avcSal, Connection connex) {
		boolean saved = false;
		PreparedStatement stmt = null;
		avcSal.setId(getId(Tables.getTableName(Tables.TableName.parametrageAvancementSalaire)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageAvancementSalaire)
				+ " (id,taux_avancement,id_personnel,id_categorie,ancien_sal_inf,"
				+ "ancien_sal_sup,avancement_inconditionnel,id_notation) " + " VALUES (?,?,?,?,?,?,?,?)";

		try {
			stmt = connex.prepareStatement(sql);
			stmt.setInt(1, avcSal.getId());
			stmt.setDouble(2, avcSal.getTauxAvancement());
			stmt.setInt(3, avcSal.getIdPersonnel());
			stmt.setInt(4, avcSal.getIdCategorie());
			stmt.setBoolean(5, avcSal.isAncienSalaireInferieur());
			stmt.setBoolean(6, avcSal.isAncienSalaireSuperieur());
			stmt.setBoolean(7, avcSal.isAvancementInconditionnel());
			if (avcSal.getTypeNotation() != null)
				stmt.setInt(8, avcSal.getTypeNotation().getId());
			else
				stmt.setObject(8, null);

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametrageAvancementSalaire(ParametreAvancementSalaireC avcSal, Connection connx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageAvancementSalaire)
				+ " SET  taux_avancement=?,id_personnel=?,id_categorie=?,ancien_sal_inf=?,ancien_sal_sup=?,"
				+ "avancement_inconditionnel =?,id_notation=? WHERE id=?";

		try {
			stmt = connx.prepareStatement(sql);
			stmt.setDouble(1, avcSal.getTauxAvancement());
			stmt.setInt(2, avcSal.getIdPersonnel());
			stmt.setInt(3, avcSal.getIdCategorie());
			stmt.setBoolean(4, avcSal.isAncienSalaireInferieur());
			stmt.setBoolean(5, avcSal.isAncienSalaireSuperieur());
			stmt.setBoolean(6, avcSal.isAvancementInconditionnel());

			if (avcSal.getTypeNotation() != null)
				stmt.setInt(7, avcSal.getTypeNotation().getId());
			else
				stmt.setObject(7, null);

			stmt.setInt(8, avcSal.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private ParametreAvancementSalaireC setParametrageAvancementSalaire(ResultSet rs) throws SQLException {
		ParametreAvancementSalaireC parametrage = new ParametreAvancementSalaireC();
		parametrage.setId(rs.getInt("id"));
		parametrage.setTauxAvancement(rs.getDouble("taux_avancement"));
		parametrage.setIdCategorie(rs.getInt("id_categorie"));
		parametrage.setIdPersonnel(rs.getInt("id_personnel"));
		parametrage.setAncienSalaireInferieur(rs.getBoolean("ancien_sal_inf"));
		parametrage.setAncienSalaireSuperieur(rs.getBoolean("ancien_sal_sup"));
		parametrage.setAvancementInconditionnel(rs.getBoolean("avancement_inconditionnel"));
		if (rs.getObject("id_notation") != null)
			parametrage.setTypeNotation(getTypeNotation(rs.getInt("id_notation")));

		return parametrage;
	}

	public ParametreAvancementSalaireC getParametrageAvancementSalaire(int idPers, int idCat, int idNotation) {
		Statement stmt = null;
		ResultSet rs = null;
		ParametreAvancementSalaireC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAvancementSalaire)
				+ " WHERE 1=1 ";
		if (idPers > 0)
			sql += " AND id_personnel=" + idPers;
		if (idCat > 0)
			sql += " AND id_categorie=" + idCat;
		if (idNotation > 0)
			sql += " AND id_notation=" + idNotation;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				parametrage = setParametrageAvancementSalaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	public List<ParametreAvancementSalaireC> getListParametrageAvancementSalaire(int idPers, int idCat) {
		Statement stmt = null;
		ResultSet rs = null;
		List<ParametreAvancementSalaireC> listParametrage = new ArrayList<ParametreAvancementSalaireC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageAvancementSalaire)
				+ " WHERE 1=1 ";
		if (idPers > 0)
			sql += " AND id_personnel=" + idPers;
		if (idCat > 0)
			sql += " AND id_categorie=" + idCat;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listParametrage.add(setParametrageAvancementSalaire(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listParametrage;
	}

	private boolean insertParametrageDateNotation(ParametrageDateNotationC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametrageDateNotation)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageDateNotation)
				+ " (id,jour_debut,mois_debut,jour_fin,mois_fin,duree_recours_notation) " + " VALUES (?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getId());
			stmt.setInt(2, parametrage.getJourDebut());
			stmt.setInt(3, parametrage.getMoisDebut());
			stmt.setInt(4, parametrage.getJourFin());
			stmt.setInt(5, parametrage.getMoisFin());
			stmt.setInt(6, parametrage.getDureeRecoursNotation());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametrageDateNotation(ParametrageDateNotationC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageDateNotation) + " SET  "
				+ " jour_debut=?,mois_debut=?,jour_fin=?,mois_fin=?,duree_recours_notation=?" + "  WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getJourDebut());
			stmt.setInt(2, parametrage.getMoisDebut());
			stmt.setInt(3, parametrage.getJourFin());
			stmt.setInt(4, parametrage.getMoisFin());
			stmt.setInt(5, parametrage.getDureeRecoursNotation());
			stmt.setInt(6, parametrage.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateParametrageDateNotation(ParametrageDateNotationC parametrage) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (parametrage.getId() == 0) {

				saved = insertParametrageDateNotation(parametrage);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {

				saved = updateParametrageDateNotation(parametrage);
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

	private ParametrageDateNotationC setParametrageDateNotation(ResultSet rs) throws SQLException {
		ParametrageDateNotationC parametrage = new ParametrageDateNotationC();
		parametrage.setId(rs.getInt("id"));
		parametrage.setJourDebut(rs.getInt("jour_debut"));
		parametrage.setMoisDebut(rs.getInt("mois_debut"));
		parametrage.setJourFin(rs.getInt("jour_fin"));
		parametrage.setMoisFin(rs.getInt("mois_fin"));
		parametrage.setDureeRecoursNotation(rs.getInt("duree_recours_notation"));
		return parametrage;
	}

	public ParametrageDateNotationC getParametrageDateNotation(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageDateNotationC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDateNotation) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageDateNotation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	public boolean deleteParametrageDateNotation(ParametrageDateNotationC parametrage) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(parametrage.getId(),
					Tables.getTableName(Tables.TableName.parametrageDateNotation));

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

	public ParametrageDateNotationC getParametrageDateNotation() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageDateNotationC parametrage = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDateNotation)
				+ " ORDER BY id DESC ";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageDateNotation(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return parametrage;
	}

	private EmployeC setFonctionnaire(ResultSet rs) throws SQLException {
		EmployeC fonctionnaire = new EmployeC();
		fonctionnaire.setId(rs.getInt("id"));
		fonctionnaire.setCode(rs.getString("code"));
		fonctionnaire.setMatricule(rs.getString("matricule"));
		fonctionnaire.setNom(rs.getString("nom"));
		fonctionnaire.setPrenom(rs.getString("prenom"));
		fonctionnaire.setPartenaire(rs.getBoolean("partenaire"));
		if (rs.getObject("date_entre") != null) {

			fonctionnaire.setDateEntre((Date) rs.getObject("date_entre"));
			fonctionnaire.setDateEntreS(HelperC.convertDate((Date) rs.getObject("date_entre")));
		}
		if (rs.getObject("date_sortie") != null) {

			fonctionnaire.setDateSortie((Date) rs.getObject("date_sortie"));
			fonctionnaire.setDateSortieS(HelperC.convertDate((Date) rs.getObject("date_sortie")));
		}
		return fonctionnaire;
	}

	public EmployeC getFonctionnaire(String matricule, Date date) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EmployeC fonctionnaire = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE matricule=? "
				+ "AND date_entre<=? AND date_sortie>=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, matricule);
			stmt.setString(2, HelperC.convertDate(date, false));
			stmt.setString(3, HelperC.convertDate(date, false));
			rs = stmt.executeQuery();

			if (rs.next()) {
				fonctionnaire = setFonctionnaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return fonctionnaire;
	}

	public EmployeC getFonctionnaireActif(String matricule, Date date) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EmployeC fonctionnaire = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE code=? "
				+ "AND date_entre<=? AND date_sortie IS NULL";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, matricule);
			stmt.setString(2, HelperC.convertDate(date, false));
			rs = stmt.executeQuery();
			if (rs.next()) {
				fonctionnaire = setFonctionnaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return fonctionnaire;
	}

	public EmployeC getFonctionnaire(String matricule) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EmployeC fonctionnaire = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE matricule=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, matricule);
			rs = stmt.executeQuery();
			if (rs.next()) {
				fonctionnaire = setFonctionnaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return fonctionnaire;
	}

	public List<EmployeC> getListeFonctionnaire(String matricule, String nom, String prenom) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<EmployeC> liste = new ArrayList<EmployeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.employe) + " WHERE 1=1 ";

		if (matricule != null && !matricule.trim().equals("")) {
			sql = String.valueOf(sql) + " AND matricule=?";
		}

		if (nom != null && !nom.equals("")) {
			sql = String.valueOf(sql) + " AND nom LIKE '%" + nom + "%'";
		}
		if (prenom != null && !prenom.equals("")) {
			sql = String.valueOf(sql) + " AND prenom LIKE '%" + prenom + "%'";
		}

		sql = String.valueOf(sql) + " AND partenaire=0";

		sql = String.valueOf(sql) + " ORDER BY matricule";

		System.out.println(sql);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			for (; rs.next(); liste.add(setFonctionnaire(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public boolean deleteFonctionnaire(EmployeC fonctionnaire) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(fonctionnaire.getId(), Tables.getTableName(Tables.TableName.fonctionnaire));

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

	public List<EmployeC> getListeFonctionnaireActif(Base fonction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<EmployeC> liste = new ArrayList<EmployeC>();
		String sql = "SELECT DISTINCT B.* FROM " + Tables.getTableName(Tables.TableName.affectation) + " AS A "
				+ "INNER JOIN " + Tables.getTableName(Tables.TableName.employe) + " AS B ON A.id_fonctionnaire=B.id "
				+ "WHERE A.id_fonction=? AND A.date_fin IS NULL AND B.date_sortie IS NULL ORDER BY B.matricule";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, fonction.getId());
			rs = stmt.executeQuery();
			for (; rs.next(); liste.add(setFonctionnaire(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public EmployeC getUnFonctionnaireActif(Base fonction) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EmployeC fonctionnaire = null;
		String sql = "SELECT DISTINCT B.* FROM " + Tables.getTableName(Tables.TableName.affectation) + " AS A "
				+ "INNER JOIN " + Tables.getTableName(Tables.TableName.employe) + " AS B ON A.id_fonctionnaire=B.id "
				+ "WHERE A.id_fonction=? AND A.date_fin IS NULL AND B.date_sortie IS NULL ORDER BY B.matricule";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, fonction.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				fonctionnaire = setFonctionnaire(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return fonctionnaire;
	}

	private boolean insertConditionPosition(ConditionPositionC condition) {
		boolean saved = false;
		PreparedStatement stmt = null;
		condition.setId(getId(Tables.getTableName(Tables.TableName.conditionPosition)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.conditionPosition)
				+ " (id,positions,conditions) " + " VALUES (?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, condition.getId());
			if (condition.getPosition() != null) {

				stmt.setInt(2, Constante.getPosition(condition.getPosition()));
			} else {

				stmt.setObject(2, (Object) null);
			}
			stmt.setString(3, condition.getCondition());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateConditionPosition(ConditionPositionC condition) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.conditionPosition) + " SET  "
				+ " positions=?,conditions=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Constante.getPosition(condition.getPosition()));
			stmt.setString(2, condition.getCondition());
			stmt.setInt(3, condition.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateConditionPosition(ConditionPositionC condition) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (condition.getId() == 0) {

				saved = insertConditionPosition(condition);
				if (!saved) {
					condition.setId(0);
				}
			} else {

				saved = updateConditionPosition(condition);
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

	private ConditionPositionC setConditionPosition(ResultSet rs) throws SQLException {
		ConditionPositionC condition = new ConditionPositionC();
		condition.setId(rs.getInt("id"));
		if (rs.getObject("positions") != null) {
			condition.setPosition(Constante.getPosition(rs.getInt("positions")));
		}
		condition.setLibellePosition(Constante.getLibellePosition(condition.getPosition()));
		condition.setCondition(rs.getString("conditions"));
		return condition;
	}

	public ConditionPositionC getConditionPosition(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ConditionPositionC condition = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionPosition) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				condition = setConditionPosition(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return condition;
	}

	public ConditionPositionC getConditionPosition(String conditionS, Constante.Position position, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ConditionPositionC condition = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionPosition)
				+ " WHERE conditions=? AND positions=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, conditionS);
			stmt.setInt(2, Constante.getPosition(position));
			stmt.setInt(3, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				condition = setConditionPosition(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return condition;
	}

	public boolean deleteConditionPosition(ConditionPositionC condition) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);
			deleted = deleteNotAutocommit(condition.getId(), Tables.getTableName(Tables.TableName.conditionPosition));
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

	public List<ConditionPositionC> getListeConditionPosition() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ConditionPositionC> condition = new ArrayList<ConditionPositionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionPosition);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			for (; rs.next(); condition.add(setConditionPosition(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return condition;
	}

	public List<ConditionPositionC> getListeConditionPosition(int idPosition) {
		Statement stmt = null;
		ResultSet rs = null;
		List<ConditionPositionC> condition = new ArrayList<ConditionPositionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.conditionPosition) + " WHERE positions="
				+ idPosition;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				condition.add(setConditionPosition(rs));
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return condition;
	}

	private boolean insertParametragePosition(ParametragePositionC parametrage, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametragePosition)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametragePosition)
				+ " (id, id_condition, duree_demande, duree_position,taux_pendant_position, "
				+ "  avancement_grade, ajout_allocation_familiale,ajout_allocation_logement, "
				+ " avancement_traitement, id_personnel, bloquer_paie,id_categorie) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setInt(1, parametrage.getId());
			stmt.setInt(2, parametrage.getIdCondition());
			stmt.setInt(3, parametrage.getDureDemande());
			stmt.setInt(4, parametrage.getDureePosition());
			stmt.setDouble(5, parametrage.getTauxSalaire());
			stmt.setBoolean(6, parametrage.isAvancementGrade());

			stmt.setBoolean(7, parametrage.isAjoutAllocationFamiliale());
			stmt.setBoolean(8, parametrage.isAjoutAllocationLogement());
			stmt.setBoolean(9, parametrage.isAvancementTraitement());

			stmt.setInt(10, parametrage.getIdPersonnel());
			stmt.setBoolean(11, parametrage.isBloquerPaie());
			stmt.setInt(12, parametrage.getIdCategorie());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametragePosition(ParametragePositionC parametrage, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametragePosition)
				+ " SET  id_condition=?, duree_demande=?, duree_position=?,taux_pendant_position=?, "
				+ "  avancement_grade=?, ajout_allocation_familiale=?,ajout_allocation_logement=?, "
				+ " avancement_traitement=?, id_personnel=?, bloquer_paie=?,id_categorie=? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);

			stmt.setInt(1, parametrage.getIdCondition());
			stmt.setInt(2, parametrage.getDureDemande());
			stmt.setInt(3, parametrage.getDureePosition());
			stmt.setDouble(4, parametrage.getTauxSalaire());
			stmt.setBoolean(5, parametrage.isAvancementGrade());

			stmt.setBoolean(6, parametrage.isAjoutAllocationFamiliale());
			stmt.setBoolean(7, parametrage.isAjoutAllocationLogement());
			stmt.setBoolean(8, parametrage.isAvancementTraitement());

			stmt.setInt(9, parametrage.getIdPersonnel());
			stmt.setBoolean(10, parametrage.isBloquerPaie());
			stmt.setInt(11, parametrage.getIdCategorie());
			stmt.setInt(12, parametrage.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateParametragePosition(ParametragePositionC parametrage) {
		boolean saved = false;

		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			if (parametrage.getId() == 0) {

				saved = insertParametragePosition(parametrage, conn);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {

				saved = updateParametragePosition(parametrage, conn);
			}
			for (ParametragePositionDetailC detail : parametrage.getListDetailPrime()) {

				detail.setIdEntete(parametrage.getId());

				if (detail.getId() == 0) {

					saved = insertDetailParametragePosition(detail, conn);

				} else
					saved = updateDetailParametragePosition(detail, conn);
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

	private ParametragePositionC setParametragePosition(ResultSet rs) throws SQLException {
		ParametragePositionC parametrage = new ParametragePositionC();
		parametrage.setId(rs.getInt("id"));

		parametrage.setIdCondition(rs.getInt("id_condition"));
		parametrage.setDureePosition(rs.getInt("duree_position"));
		parametrage.setIdCategorie(rs.getInt("id_categorie"));
		parametrage.setTauxSalaire(rs.getDouble("taux_pendant_position"));
		parametrage.setAjoutAllocationFamiliale(rs.getBoolean("ajout_allocation_familiale"));
		parametrage.setAjoutAllocationLogement(rs.getBoolean("ajout_allocation_logement"));
		parametrage.setAvancementGrade(rs.getBoolean("avancement_grade"));
		parametrage.setAvancementTraitement(rs.getBoolean("avancement_traitement"));
		parametrage.setBloquerPaie(rs.getBoolean("bloquer_paie"));
		parametrage.setIdPersonnel(rs.getInt("id_personnel"));

		return parametrage;
	}

	public ParametragePositionC getParametrePosition(int idCondition, int idperonel) {
		Statement stmt = null;
		ResultSet rs = null;
		ParametragePositionC prm = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePosition)
				+ " WHERE id_condition=" + idCondition + " AND id_personnel=" + idperonel;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				prm = setParametragePosition(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			if (prm != null)
				prm.setListDetailPrime(getListDetailParametragePosition(prm.getId()));
			releaseResource(stmt, null);
		}
		return prm;
	}

	public List<ParametragePositionC> getListParametrePosition() {
		Statement stmt = null;
		ResultSet rs = null;
		List<ParametragePositionC> listprm = new ArrayList<ParametragePositionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePosition);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listprm.add(setParametragePosition(rs));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		for (ParametragePositionC prm : listprm) {
			prm.setListDetailPrime(getListDetailParametragePosition(prm.getId()));
		}
		return listprm;
	}

	private boolean insertDetailParametragePosition(ParametragePositionDetailC detail, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		detail.setId(getId(Tables.getTableName(Tables.TableName.detailParametragePositionPrim)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailParametragePositionPrim)
				+ " (id,id_prime,id_entete,id_param_prime) " + " VALUES (?,?,?,?)";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, detail.getId());

			stmt.setInt(2, detail.getIdPrime());
			stmt.setInt(3, detail.getIdEntete());
			stmt.setInt(4, detail.getIdParm());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDetailParametragePosition(ParametragePositionDetailC detail, Connection conx) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailParametragePositionPrim)
				+ " SET  id_prime=?,id_param_prime =? WHERE id=?";

		try {
			stmt = conx.prepareStatement(sql);
			stmt.setInt(1, detail.getIdPrime());

			stmt.setInt(2, detail.getIdParm());
			stmt.setInt(3, detail.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private ParametragePositionDetailC setDetailParametragePosition(ResultSet rs) throws SQLException {
		ParametragePositionDetailC detail = new ParametragePositionDetailC();
		detail.setId(rs.getInt("id"));
		detail.setIdEntete(rs.getInt("id_entete"));
		detail.setIdParm(rs.getInt("id_param_prime"));
		detail.setIdPrime(rs.getInt("id_prime"));

		return detail;
	}

	public ParametragePositionDetailC getDetailParametragePosition(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametragePositionDetailC detail = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailParametragePositionPrim)
				+ " WHERE id=" + id;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				detail = setDetailParametragePosition(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return detail;
	}

	public List<ParametragePositionDetailC> getListDetailParametragePosition(int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametragePositionDetailC> listDetail = new ArrayList<ParametragePositionDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.detailParametragePositionPrim)
				+ " WHERE id_entete=" + idEntete;

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			while (rs.next()) {
				listDetail.add(setDetailParametragePosition(rs));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}

		return listDetail;
	}

	public boolean deleteParametragePosition(ParametragePositionC parametre) {
		boolean deleted = false;

		try {
			Connection conn = con;
			con.setAutoCommit(false);
			deleted = deleteList(parametre.getId(), "id_entete",
					Tables.getTableName(Tables.TableName.detailParametragePositionPrim), conn);

			if (deleted) {
				deleted = delete(parametre.getId(), Tables.getTableName(Tables.TableName.parametragePosition), conn);
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

	public boolean insertUpdateParametrageJournal(ParametrageJournalC journal) {
		boolean saved = false;

		try {
			Connection conn = con;

			conn.setAutoCommit(false);

			if (journal.getId() == 0) {

				saved = insertPrevisionParametrageJournal(journal, conn);
			} else {

				saved = updateParametreJournal(journal, conn);
			}
			for (ParametrageJournalDetailC det : journal.getListeDetail()) {

				det.setIdEntete(journal.getId());
				if (det.getId() == 0) {

					saved = insertParametrageJournalDetail(det, conn);
					continue;
				}
				saved = updateParametreJournalDetail(det, conn);
			}

			if (journal.getListDeleted().size() > 0) {
				for (ParametrageJournalDetailC det : journal.getListDeleted()) {

					deleteList(det.getId(), "id_entete",
							Tables.getTableName(Tables.TableName.parametrageJournalElement), con);
					delete(det.getId(), Tables.getTableName(Tables.TableName.parametrageJournalDetail), conn);
				}
			}
			if (saved) {

				conn.commit();
			} else {

				con.rollback();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return saved;
	}

	private boolean insertPrevisionParametrageJournal(ParametrageJournalC prmJrnl, Connection connect) {
		boolean saved = false;
		PreparedStatement stmt = null;
		prmJrnl.setId(getId(Tables.getTableName(Tables.TableName.parametrageJournal)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageJournal)
				+ " (id,code,libelle) VALUES (?,?,?)";

		try {
			stmt = connect.prepareStatement(sql);
			stmt.setInt(1, prmJrnl.getId());
			stmt.setString(2, prmJrnl.getCode());
			stmt.setString(3, prmJrnl.getLibelle());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametreJournal(ParametrageJournalC prmJrnl, Connection connect) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageJournal)
				+ " SET  code=?,libelle=? WHERE id=?";

		try {
			stmt = connect.prepareStatement(sql);
			stmt.setString(1, prmJrnl.getCode());
			stmt.setString(2, prmJrnl.getLibelle());
			stmt.setInt(3, prmJrnl.getId());
			stmt.execute();
			if (prmJrnl.getListDeleted().size() > 0) {

				for (ParametrageJournalDetailC det : prmJrnl.getListDeleted()) {
					deleteOneDetailParametrage(det, stmt);
				}
			}

			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private ParametrageJournalC setParametreJournal(ResultSet rs) throws SQLException {
		ParametrageJournalC prmtr = new ParametrageJournalC();
		prmtr.setId(rs.getInt("id"));
		prmtr.setCode(rs.getString("code"));
		prmtr.setLibelle(rs.getString("libelle"));
		return prmtr;
	}

	public List<ParametrageJournalC> getListJournalParametre() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametrageJournalC> listPrmtr = new ArrayList<ParametrageJournalC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageJournal);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next())
				listPrmtr.add(setParametreJournal(rs));

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		for (ParametrageJournalC prmtr : listPrmtr) {
			if (prmtr != null) {
				prmtr.setListeDetail(getListParametrageJournal(prmtr.getId()));
			}
		}
		return listPrmtr;
	}

	public ParametrageJournalC getParametrageJournal(int id, boolean detail) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageJournalC prmtr = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageJournal) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prmtr = setParametreJournal(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (detail && prmtr != null) {
			prmtr.setListeDetail(getListParametrageJournal(prmtr.getId()));
		}
		return prmtr;
	}

	public ParametrageJournalC getParametrageJournal(String code) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageJournalC prmtr = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageJournal) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				prmtr = setParametreJournal(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (prmtr != null) {
			prmtr.setListeDetail(getListParametrageJournal(prmtr.getId()));
		}
		return prmtr;
	}

	public boolean deleteParametrageJournal(ParametrageJournalC prm) {
		Statement stmt = null;
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.parametrageJournal) + " WHERE id="
				+ prm.getId();

		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			for (ParametrageJournalDetailC det : prm.getListeDetail()) {
				deleteList(det.getId(), "id_entete", Tables.getTableName(Tables.TableName.parametrageJournalElement),
						con);
			}

			deleteList(prm.getId(), "id_entete", Tables.getTableName(Tables.TableName.parametrageJournalDetail), con);

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

	private boolean insertParametrageJournalDetail(ParametrageJournalDetailC det, Connection connect) {
		boolean saved = false;
		PreparedStatement stmt = null;
		det.setId(getId(Tables.getTableName(Tables.TableName.parametrageJournalDetail)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageJournalDetail)
				+ " (id,id_entete,titre,libelle,type_parametre) VALUES (?,?,?,?,?)";

		try {
			stmt = connect.prepareStatement(sql);
			stmt.setInt(1, det.getId());
			stmt.setInt(2, det.getIdEntete());
			stmt.setString(3, det.getTitrElement());
			stmt.setString(4, det.getLibelle());
			stmt.setInt(5, det.getTypeElement());

			stmt.execute();
			saved = true;

			for (ParametrageJournalElementC element : det.getListDetailElement()) {

				if (element.getId() == 0) {

					element.setIdEntete(det.getId());
					insertParametrageJournalElement(element, connect);
				}
			}

			for (ParametrageJournalElementC element : det.getLiteDeletedElement()) {
				delete(element.getId(), Tables.getTableName(Tables.TableName.parametrageJournalElement), connect);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametreJournalDetail(ParametrageJournalDetailC det, Connection connect) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageJournalDetail)
				+ " SET  titre=?,libelle=?,type_parametre=? WHERE id=?";

		try {
			stmt = connect.prepareStatement(sql);
			stmt.setString(1, det.getTitrElement());
			stmt.setString(2, det.getLibelle());
			stmt.setInt(3, det.getTypeElement());
			stmt.setInt(4, det.getId());
			stmt.execute();
			saved = true;
			for (ParametrageJournalElementC element : det.getListDetailElement()) {

				if (element.getId() == 0) {

					element.setIdEntete(det.getId());
					insertParametrageJournalElement(element, connect);

					continue;
				}
				updateParametrageJournalElement(element, connect);

			}

			for (ParametrageJournalElementC element : det.getLiteDeletedElement()) {
				delete(element.getId(), Tables.getTableName(Tables.TableName.parametrageJournalElement), connect);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private ParametrageJournalDetailC setParametreJournalDetail(ResultSet rs) throws SQLException {
		ParametrageJournalDetailC det = new ParametrageJournalDetailC();
		det.setId(rs.getInt("id"));
		det.setTitrElement(rs.getString("titre"));
		det.setLibelle(rs.getString("libelle"));
		det.setTypeElement(rs.getInt("type_parametre"));

		return det;
	}

	public List<ParametrageJournalDetailC> getListParametrageJournal(int idParm) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametrageJournalDetailC> listPrmtr = new ArrayList<ParametrageJournalDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageJournalDetail)
				+ " WHERE id_entete=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idParm);
			rs = stmt.executeQuery();
			for (; rs.next(); listPrmtr.add(setParametreJournalDetail(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		if (listPrmtr.size() > 0) {
			for (ParametrageJournalDetailC det : listPrmtr) {
				det.setListDetailElement(getListElementJournal(det.getId()));
			}
		}
		return listPrmtr;
	}

	public List<ParametrageJournalDetailC> getListParametrageJournal(String titre, int idEntete) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametrageJournalDetailC> listPrmtr = new ArrayList<ParametrageJournalDetailC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageJournalDetail)
				+ " WHERE titre=? AND id_entete=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, titre);
			stmt.setInt(2, idEntete);
			rs = stmt.executeQuery();
			for (; rs.next(); listPrmtr.add(setParametreJournalDetail(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listPrmtr;
	}

	public List<String> getListColonne(int idParm) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> listTtr = new ArrayList<String>();
		String sql = "SELECT distinct titre FROM " + Tables.getTableName(Tables.TableName.parametrageJournalDetail)
				+ " WHERE id_entete=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idParm);
			rs = stmt.executeQuery();
			for (; rs.next(); listTtr.add(rs.getString("titre")))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listTtr;
	}

	private boolean deleteOneDetailParametrage(ParametrageJournalDetailC det, Statement stmt) {
		boolean deleted = false;
		String sql = "DELETE FROM " + Tables.getTableName(Tables.TableName.parametrageJournalDetail) + " WHERE id="
				+ det.getId();

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

	private boolean insertParametrageJournalElement(ParametrageJournalElementC element, Connection connect) {
		boolean saved = false;
		PreparedStatement stmt = null;
		element.setId(getId(Tables.getTableName(Tables.TableName.parametrageJournalElement)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageJournalElement)
				+ " (id,id_element,id_entete,signe) VALUES (?,?,?,?)";

		try {
			stmt = connect.prepareStatement(sql);
			stmt.setInt(1, element.getId());
			stmt.setInt(2, element.getIdElement());
			stmt.setInt(3, element.getIdEntete());
			stmt.setInt(4, element.getSigne());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateParametrageJournalElement(ParametrageJournalElementC element, Connection connect) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE  " + Tables.getTableName(Tables.TableName.parametrageJournalElement)
				+ " SET id_element=?,signe=? WHERE id=?";

		try {
			stmt = connect.prepareStatement(sql);

			stmt.setInt(1, element.getIdElement());
			stmt.setInt(2, element.getSigne());
			stmt.setInt(3, element.getId());

			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private ParametrageJournalElementC setParametreJournalElement(ResultSet rs) throws SQLException {
		ParametrageJournalElementC det = new ParametrageJournalElementC();
		det.setId(rs.getInt("id"));
		det.setIdElement(rs.getInt("id_element"));
		det.setSigne(rs.getInt("signe"));
		return det;
	}

	public List<ParametrageJournalElementC> getListElementJournal(int idDetJrnl) {
		Statement stmt = null;
		ResultSet rs = null;
		List<ParametrageJournalElementC> listElement = new ArrayList<ParametrageJournalElementC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageJournalElement)
				+ " WHERE id_entete= " + idDetJrnl;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			for (; rs.next(); listElement.add(setParametreJournalElement(rs)))
				;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return listElement;
	}

	private boolean insertDirection(DirectionC direction) {
		boolean saved = false;
		PreparedStatement stmt = null;
		direction.setId(getId(Tables.getTableName(Tables.TableName.direction)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.direction)
				+ " (id,id_direction_gnl,code,designation)  VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, direction.getId());
			stmt.setInt(2, direction.getOrgane().getId());
			stmt.setString(3, direction.getCode());
			stmt.setString(4, direction.getDesignation());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	private boolean updateDirection(DirectionC direction) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.direction) + " SET   designation=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, direction.getDesignation());
			stmt.setInt(2, direction.getId());
			stmt.execute();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, null);
		}
		return saved;
	}

	public boolean insertUpdateDirection(DirectionC direction) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (direction.getId() == 0) {

				saved = insertDirection(direction);
				if (!saved) {
					direction.setId(0);
				}
			} else {

				saved = updateDirection(direction);
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

	private DirectionC setDirection(ResultSet rs) throws SQLException {
		DirectionC direction = new DirectionC();
		direction.setId(rs.getInt("id"));
		direction.setOrgane(
				getBaseById(rs.getInt("id_direction_gnl"), Tables.getTableName(Tables.TableName.directionGnle)));
		direction.setCode(rs.getString("code"));
		direction.setDesignation(rs.getString("designation"));
		return direction;
	}

	public DirectionC getDirection(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DirectionC direction = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.direction) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				direction = setDirection(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return direction;
	}

	public DirectionC getDirection(String code) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DirectionC direction = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.direction) + " WHERE code=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);

			rs = stmt.executeQuery();
			if (rs.next()) {
				direction = setDirection(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return direction;
	}

	public DirectionC getDirection(String code, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DirectionC direction = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.direction)
				+ " WHERE code=? AND id_direction_gnl=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);
			stmt.setInt(2, id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				direction = setDirection(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return direction;
	}

	public DirectionC getDirectionByDesignationNotCurrent(String designation, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DirectionC direction = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.direction)
				+ " WHERE designation=? AND id<>? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, designation);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				direction = setDirection(rs);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return direction;
	}

	public List<DirectionC> getListeDirection(Base repartition) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DirectionC direction = null;
		List<DirectionC> liste = new ArrayList<DirectionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.direction)
				+ " WHERE id_organe_admin=? ORDER BY code";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, repartition.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				direction = setDirection(rs);
				liste.add(direction);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<DirectionC> getListeDirection() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DirectionC direction = null;
		List<DirectionC> liste = new ArrayList<DirectionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.direction);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				direction = setDirection(rs);
				liste.add(direction);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public List<DirectionC> getListDirectionParOrgane(int idOrgane) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DirectionC direction = null;
		List<DirectionC> liste = new ArrayList<DirectionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.direction)
				+ " WHERE id_direction_gnl=? ORDER BY code";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idOrgane);
			rs = stmt.executeQuery();
			while (rs.next()) {
				direction = setDirection(rs);
				liste.add(direction);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			releaseResource(stmt, rs);
		}
		return liste;
	}

	public boolean deleteDirection(DirectionC direction) {
		boolean deleted = false;

		try {
			con.setAutoCommit(false);
			// deleted = deleteList(direction.getId(), "id_direction",
			// Tables.getTableName(Tables.TableName.directionDetail));

			deleted = deleteNotAutocommit(direction.getId(), Tables.getTableName(Tables.TableName.direction));

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

	public boolean insertUpdateParametrageDecideurSignataire(ParametrageDecideurSignataireC parametrage) {
		boolean saved = false;
		try {
			Connection conn = con;
			conn.setAutoCommit(false);
			if (parametrage.getId() == 0) {
				saved = insertParametrageDecideurSignataire(parametrage, conn);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {
				saved = updateParametrageDecideurSignataire(parametrage, conn);
			}

			if (saved) {

				if (parametrage.getListDetail().size() > 0) {
					for (ParametrageDecideurDetailC det : parametrage.getListDetail()) {
						if (det.getId() == 0) {

							det.setIdEntete(parametrage.getId());
							insertParametrageDecideurSignataireDetail(det, conn);
							continue;
						}
						updateParametrageDecideurSignataireDetail(det, conn);
					}
				}

				if (parametrage.getListDeleted().size() > 0) {
					for (ParametrageDecideurDetailC det : parametrage.getListDeleted()) {
						delete(det.getId(), Tables.getTableName(Tables.TableName.parametrageDecideurDetail), conn);
					}
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

	private boolean insertParametrageDecideurSignataire(ParametrageDecideurSignataireC parametrage,
			Connection connexion) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametrageDecideurSignataire)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageDecideurSignataire)
				+ " (id,type_operation) " + " VALUES (?,?)";

		try {
			stmt = connexion.prepareStatement(sql);

			stmt.setInt(1, parametrage.getId());
			stmt.setInt(2, parametrage.getTypeOperation());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateParametrageDecideurSignataire(ParametrageDecideurSignataireC parametrage,
			Connection connexion) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageDecideurSignataire) + " SET  "
				+ " type_operation=? WHERE id=?";

		try {
			stmt = connexion.prepareStatement(sql);

			stmt.setInt(1, parametrage.getTypeOperation());
			stmt.setInt(2, parametrage.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private ParametrageDecideurSignataireC setParametrageDecideurSignataire(ResultSet rs) throws SQLException {
		ParametrageDecideurSignataireC parametrage = new ParametrageDecideurSignataireC();
		parametrage.setId(rs.getInt("id"));
		if (rs.getObject("type_operation") != null)
			parametrage.setTypeOperation(rs.getInt("type_operation"));

		return parametrage;
	}

	public ParametrageDecideurSignataireC getParametrageDecideurSignataire(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageDecideurSignataireC parametrage = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDecideurSignataire)
				+ " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageDecideurSignataire(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return parametrage;
	}

	public ParametrageDecideurSignataireC getSignataire(int operation) {
		Statement stmt = null;
		ResultSet rs = null;
		ParametrageDecideurSignataireC parametrage = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDecideurSignataire)
				+ " WHERE 1=1 ";

		if (operation > 0) {
			sql = String.valueOf(sql) + " AND type_operation=" + operation;
		}

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				parametrage = setParametrageDecideurSignataire(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}
		if (parametrage != null)
			parametrage.setListDetail(getListParametrageDecideurDetail(parametrage.getId()));
		return parametrage;
	}

	public List<ParametrageDecideurSignataireC> getListParametrageDecideurSignataire(int typeOperation, int idPersonnel,
			int idFonction) {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDecideurSignataire)
				+ " WHERE 1=1 ";
		List<ParametrageDecideurSignataireC> listParametre = new ArrayList<ParametrageDecideurSignataireC>();

		try {
			stmt = con.createStatement();
			if (idFonction > 0)
				sql = String.valueOf(sql) + " AND id_fonction=" + idFonction;
			if (idPersonnel > 0)
				sql = String.valueOf(sql) + " AND id_personnel=" + idPersonnel;
			if (typeOperation > 0)
				sql = String.valueOf(sql) + " AND type_operation=" + typeOperation;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listParametre.add(setParametrageDecideurSignataire(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return listParametre;
	}

	public boolean deleteParametrageDecideurSignataire(ParametrageDecideurSignataireC parametrage) {
		boolean deleted = false;
		try {
			Connection conn = con;
			conn.setAutoCommit(false);

			deleted = deleteList(parametrage.getId(), "id_entete",
					Tables.getTableName(Tables.TableName.parametrageDecideurDetail), conn);
			if (deleted)
				delete(parametrage.getId(), Tables.getTableName(Tables.TableName.parametrageDecideurSignataire), conn);
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

	private boolean insertParametrageDecideurSignataireDetail(ParametrageDecideurDetailC det, Connection connection) {
		boolean saved = false;
		PreparedStatement stmt = null;
		det.setId(getId(Tables.getTableName(Tables.TableName.parametrageDecideurDetail)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageDecideurDetail)
				+ " (id,id_employe,position,id_entete) " + " VALUES (?,?,?,?)";

		try {
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, det.getId());
			stmt.setInt(2, det.getIdEmploye());
			stmt.setInt(3, det.getPosition());
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

	private boolean updateParametrageDecideurSignataireDetail(ParametrageDecideurDetailC det, Connection connection) {
		boolean saved = false;
		PreparedStatement stmt = null;

		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageDecideurDetail)
				+ " SET id_employe=?,position=? WHERE id=? ";

		try {
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, det.getIdEmploye());
			stmt.setInt(2, det.getPosition());
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

	private ParametrageDecideurDetailC setParametrageDecideurDetail(ResultSet rs) throws SQLException {
		ParametrageDecideurDetailC det = new ParametrageDecideurDetailC();
		det.setId(rs.getInt("id"));
		det.setIdEmploye(rs.getInt("id_employe"));
		det.setPosition(rs.getInt("position"));
		det.setIdEntete(rs.getInt("id_entete"));
		return det;
	}

	public List<ParametrageDecideurDetailC> getListParametrageDecideurDetail(int idEntete) {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDecideurDetail)
				+ " WHERE id_entete=" + idEntete;
		List<ParametrageDecideurDetailC> listSignataire = new ArrayList<ParametrageDecideurDetailC>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listSignataire.add(setParametrageDecideurDetail(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}
		if (listSignataire.size() > 0) {
			for (ParametrageDecideurDetailC det : listSignataire) {
				EmployeC emp = FactoryDAO.getInstance().getEmployeSimple(det.getIdEmploye());
				if (emp != null)
					det.setNomEmploye(emp.getNomPrenom());
			}
		}
		return listSignataire;
	}

	private boolean insertParametrageSanction(ParametrageSanctionC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametrageSanction)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageSanction)
				+ " (id, libelle, taux_retenue, duree_min, duree_max, duree_recours,id_retenu) "
				+ " VALUES (?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getId());
			stmt.setString(2, parametrage.getLibelleSanction());
			stmt.setDouble(3, parametrage.getTauxRetenue());
			stmt.setInt(4, parametrage.getDureeMin());
			stmt.setInt(5, parametrage.getDureeMax());
			stmt.setInt(6, parametrage.getDureeRecours());
			if (parametrage.getIdRetenu() > 0)
				stmt.setInt(7, parametrage.getIdRetenu());
			else
				stmt.setObject(7, null);

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateParametrageSanction(ParametrageSanctionC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageSanction)
				+ " SET libelle=?, taux_retenue=?, duree_min=?, duree_max=?,"
				+ " duree_recours=?,id_retenu=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, parametrage.getLibelleSanction());
			stmt.setDouble(2, parametrage.getTauxRetenue());
			stmt.setInt(3, parametrage.getDureeMin());
			stmt.setInt(4, parametrage.getDureeMax());
			stmt.setInt(5, parametrage.getDureeRecours());
			if (parametrage.getIdRetenu() > 0)
				stmt.setInt(6, parametrage.getIdRetenu());

			else
				stmt.setObject(6, null);

			stmt.setInt(7, parametrage.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateParametrageSanction(ParametrageSanctionC parametrage) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (parametrage.getId() == 0) {
				saved = insertParametrageSanction(parametrage);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {
				saved = updateParametrageSanction(parametrage);
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

	private ParametrageSanctionC setParametrageSanction(ResultSet rs) throws SQLException {
		ParametrageSanctionC parametrage = new ParametrageSanctionC();
		parametrage.setId(rs.getInt("id"));
		parametrage.setTauxRetenue(rs.getDouble("taux_retenue"));
		parametrage.setDureeMin(rs.getInt("duree_min"));
		parametrage.setDureeMax(rs.getInt("duree_max"));
		parametrage.setDureeRecours(rs.getInt("duree_recours"));
		parametrage.setLibelleSanction(rs.getString("libelle"));
		if (rs.getObject("id_retenu") != null)
			parametrage.setIdRetenu(rs.getInt("id_retenu"));
		return parametrage;
	}

	public ParametrageSanctionC getParametrageSanction(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageSanctionC parametrage = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageSanction) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageSanction(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return parametrage;
	}

	public ParametrageSanctionC getParametrageSanctionParType(int type) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametrageSanctionC parametrage = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageSanction)
				+ " WHERE type_sanction= " + type;

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametrageSanction(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return parametrage;
	}

	public boolean deleteParametrageSanction(ParametrageSanctionC parametrage) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(parametrage.getId(),
					Tables.getTableName(Tables.TableName.parametrageSanction));
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

	public List<ParametrageSanctionC> getListeParametrageSanction() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametrageSanctionC> ParamRegime = new ArrayList<ParametrageSanctionC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageSanction);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ParamRegime.add(setParametrageSanction(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return ParamRegime;
	}

	private boolean insertParametrageFinCarriere(ParametrageFinCarriereC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametrageFinCarriere)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageFinCarriere)
				+ " (id, id_personnel, age_retraite, periode_prolongation, age_max_retraite, periode_salaire, "
				+ "pourcentage_salaire, periode_anticipe,id_grade) " + " VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, parametrage.getId());
			if (parametrage.getPersonnel() != null) {
				stmt.setInt(2, parametrage.getPersonnel().getId());
			} else {
				stmt.setObject(2, (Object) null);
			}
			stmt.setInt(3, parametrage.getAgeRetraite());
			stmt.setInt(4, parametrage.getPeriodeProlongation());
			stmt.setInt(5, parametrage.getAgeMaxRetraite());
			stmt.setInt(6, parametrage.getPeriodeSalire());
			stmt.setDouble(7, parametrage.getPourcentageSalaire());
			stmt.setInt(8, parametrage.getPeriodeAnticipe());

			if (parametrage.getDernierGrade() != null) {
				stmt.setInt(9, parametrage.getDernierGrade().getId());
			} else {
				stmt.setObject(9, (Object) null);
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

	private boolean updateParametrageFinCarriere(ParametrageFinCarriereC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageFinCarriere) + " SET  "
				+ " id_personnel=?, age_retraite=?, periode_prolongation=?, age_max_retraite=?, periode_salaire=?, "
				+ " pourcentage_salaire=?, periode_anticipe=?, id_grade=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);

			if (parametrage.getPersonnel() != null) {
				stmt.setInt(1, parametrage.getPersonnel().getId());
			} else {
				stmt.setObject(1, (Object) null);
			}
			stmt.setInt(2, parametrage.getAgeRetraite());
			stmt.setInt(3, parametrage.getPeriodeProlongation());
			stmt.setInt(4, parametrage.getAgeMaxRetraite());
			stmt.setInt(5, parametrage.getPeriodeSalire());
			stmt.setDouble(6, parametrage.getPourcentageSalaire());
			stmt.setInt(7, parametrage.getPeriodeAnticipe());

			if (parametrage.getDernierGrade() != null) {
				stmt.setInt(8, parametrage.getDernierGrade().getId());
			} else {
				stmt.setObject(8, (Object) null);
			}

			

			stmt.setInt(9, parametrage.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateParametrageFinCarriere(ParametrageFinCarriereC parametrage) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (parametrage.getId() == 0) {
				saved = insertParametrageFinCarriere(parametrage);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {
				saved = updateParametrageFinCarriere(parametrage);
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

	private ParametrageFinCarriereC setParametrageFinCarriere(ResultSet rs) throws SQLException {
		ParametrageFinCarriereC parametrage = new ParametrageFinCarriereC();
		parametrage.setId(rs.getInt("id"));
		if (rs.getObject("id_personnel") != null)
			parametrage.setPersonnel(
					getBaseById(rs.getInt("id_personnel"), Tables.getTableName(Tables.TableName.personnel)));
		parametrage.setPourcentageSalaire(rs.getDouble("pourcentage_salaire"));
		parametrage.setPeriodeSalire(rs.getInt("periode_salaire"));
		parametrage.setAgeRetraite(rs.getInt("age_retraite"));

		parametrage.setAgeMaxRetraite(rs.getInt("age_max_retraite"));
		parametrage.setPeriodeAnticipe(rs.getInt("periode_anticipe"));
		parametrage.setPeriodeProlongation(rs.getInt("periode_prolongation"));
		if (rs.getObject("id_grade") != null)
			parametrage.setDernierGrade(getGradePersonnel(rs.getInt("id_grade")));

	
		return parametrage;
	}

	public ParametrageFinCarriereC getParametrageFinCarriere(int idPrs) {
		Statement stmt = null;
		ResultSet rs = null;
		ParametrageFinCarriereC parametrage = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageFinCarriere)
				+ " WHERE id_personnel=" + idPrs;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				parametrage = setParametrageFinCarriere(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return parametrage;
	}

	public List<ParametrageFinCarriereC> getListParametrageFinCarriere() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametrageFinCarriereC> listParametre = new ArrayList<ParametrageFinCarriereC>();

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageFinCarriere);

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			while (rs.next()) {
				listParametre.add(setParametrageFinCarriere(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return listParametre;
	}

	public boolean deleteParametrageFinCarriere(ParametrageFinCarriereC parametrage) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(parametrage.getId(),
					Tables.getTableName(Tables.TableName.parametrageFinCarriere));
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

	private boolean insertParametrageDureeConge(ParametrageDureeCongeC nature) {
		boolean saved = false;
		PreparedStatement stmt = null;
		nature.setId(getId(Tables.getTableName(Tables.TableName.parametrageDureeConge)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametrageDureeConge)
				+ " (id, nombre_jour_annuel, nombre_jours_ajoutes, nombre_annees_ajout, Jours_conge,"
				+ " id_type_conge, id_personnel) " + "VALUES (?,?,?,?,?,?,?) ";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, nature.getId());

			stmt.setInt(2, nature.getNombreJoursAnnuel());
			stmt.setInt(3, nature.getNombreJoursAjoutes());
			stmt.setInt(4, nature.getNombreAnneesAjoutJour());

			stmt.setInt(5, nature.getJoursConge());

			if (nature.getType() != null) {
				stmt.setInt(6, nature.getType().getId());
			} else {
				stmt.setObject(6, (Object) null);
			}
			if (nature.getPersonnel() != null) {
				stmt.setInt(7, nature.getPersonnel().getId());
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

	private boolean updateParametrageDureeConge(ParametrageDureeCongeC nature) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametrageDureeConge)
				+ " SET nombre_jour_annuel=?,nombre_jours_ajoutes=?,"
				+ "nombre_annees_ajout=?,Jours_conge=?,id_type_conge=?," + "id_personnel=? WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, nature.getNombreJoursAnnuel());
			stmt.setInt(2, nature.getNombreJoursAjoutes());
			stmt.setInt(3, nature.getNombreAnneesAjoutJour());

			stmt.setInt(4, nature.getJoursConge());

			if (nature.getType() != null) {
				stmt.setInt(5, nature.getType().getId());
			} else {
				stmt.setObject(5, (Object) null);
			}
			if (nature.getPersonnel() != null) {
				stmt.setInt(6, nature.getPersonnel().getId());
			} else {
				stmt.setObject(6, (Object) null);
			}
			stmt.setInt(7, nature.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateParametrageDureeConge(ParametrageDureeCongeC nature) {
		boolean saved = false;

		try {
			con.setAutoCommit(false);
			if (nature.getId() == 0) {
				saved = insertParametrageDureeConge(nature);
			} else {
				saved = updateParametrageDureeConge(nature);
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

	private ParametrageDureeCongeC setParametrageDureeConge(ResultSet rs) throws SQLException {
		ParametrageDureeCongeC nature = new ParametrageDureeCongeC();

		nature.setId(rs.getInt("id"));
		nature.setNombreJoursAnnuel(rs.getInt("nombre_jour_annuel"));
		nature.setNombreJoursAjoutes(rs.getInt("nombre_jours_ajoutes"));
		nature.setNombreAnneesAjoutJour(rs.getInt("nombre_annees_ajout"));

		nature.setJoursConge(rs.getInt("Jours_conge"));
		nature.setLibelleJoursConge(Constante.getLibelleJoursConge(nature.getJoursConge()));
		if (rs.getObject("id_type_conge") != null)
			nature.setType(getTypeConge(rs.getInt("id_type_conge")));
		if (rs.getObject("id_personnel") != null)
			nature.setPersonnel(getInstance().getBaseById(rs.getInt("id_personnel"),
					Tables.getTableName(Tables.TableName.personnel)));

		return nature;
	}

	public ParametrageDureeCongeC getParametrageDureeConge(int id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ParametrageDureeCongeC nature = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDureeConge) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				nature = setParametrageDureeConge(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return nature;
	}

	public ParametrageDureeCongeC getParametrageDureeConge(int idPersnl, int typeConge) {
		ResultSet rs = null;
		Statement stmt = null;
		ParametrageDureeCongeC nature = null;
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDureeConge) + " "
				+ "WHERE id_type_conge=" + typeConge;
		if (idPersnl > 0)
			sql += " AND id_personnel=" + idPersnl;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				nature = setParametrageDureeConge(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return nature;
	}

	public List<ParametrageDureeCongeC> getAllParametrageDureeConge() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<ParametrageDureeCongeC> liste = new ArrayList<ParametrageDureeCongeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDureeConge);

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				liste.add(setParametrageDureeConge(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return liste;
	}

	public List<ParametrageDureeCongeC> getListParametrageDureeConge(TypeCongeC conge) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<ParametrageDureeCongeC> liste = new ArrayList<ParametrageDureeCongeC>();
		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametrageDureeConge)
				+ " WHERE id_type_conge=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, conge.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				liste.add(setParametrageDureeConge(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return liste;
	}

	private boolean insertParametragePlanConge(ParametragePlanCongeC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		parametrage.setId(getId(Tables.getTableName(Tables.TableName.parametragePlanConge)));
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.parametragePlanConge)
				+ " (id, id_service, nombre_employe,id_exercice) " + " VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getId());
			stmt.setInt(2, parametrage.getService().getId());
			stmt.setInt(3, parametrage.getNombreEmploye());
			stmt.setInt(4, parametrage.getIdExercice());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private boolean updateParametragePlanConge(ParametragePlanCongeC parametrage) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.parametragePlanConge) + " SET  "
				+ "  id_service=?, nombre_employe=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parametrage.getService().getId());
			stmt.setInt(2, parametrage.getNombreEmploye());
			stmt.setInt(3, parametrage.getId());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	public boolean insertUpdateParametragePlanConge(ParametragePlanCongeC parametrage) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (parametrage.getId() == 0) {
				saved = insertParametragePlanConge(parametrage);
				if (!saved) {
					parametrage.setId(0);
				}
			} else {
				saved = updateParametragePlanConge(parametrage);
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

	private ParametragePlanCongeC setParametragePlanConge(ResultSet rs) throws SQLException {
		ParametragePlanCongeC parametrage = new ParametragePlanCongeC();
		parametrage.setId(rs.getInt("id"));

		if (rs.getObject("id_service") != null)
			parametrage.setService(getServices(rs.getInt("id_service")));
		parametrage.setDirection(parametrage.getService().getDirection());
		parametrage.setNombreEmploye(rs.getInt("nombre_employe"));
		parametrage.setIdExercice(rs.getInt("id_exercice"));
		return parametrage;
	}

	public ParametragePlanCongeC getParametragePlanConge(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ParametragePlanCongeC parametrage = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePlanConge) + " WHERE id=? ";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parametrage = setParametragePlanConge(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return parametrage;
	}

	public List<ParametragePlanCongeC> getListParametragePlanConge(int idExercice) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ParametragePlanCongeC> list = new ArrayList<ParametragePlanCongeC>();

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.parametragePlanConge)
				+ " WHERE id_exercice=" + idExercice;

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(setParametragePlanConge(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return list;
	}

	public boolean deleteParametragePlanConge(ParametragePlanCongeC parametrage) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(parametrage.getId(),
					Tables.getTableName(Tables.TableName.parametragePlanConge));
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
	//-------------------------LIAISON COMPTA ------------------------------
	
	public boolean insertUpdateLiaisonCompta(LiaisonComptaC link) {
		boolean saved = false;
		try {
			con.setAutoCommit(false);
			if (link.getId() == 0) {
				saved = insertLiaisonCompta(link);
				if (!saved) {
					link.setId(0);
				}
			} else {
				saved = updateLiaisonCompta(link);
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
	private boolean insertLiaisonCompta(LiaisonComptaC link) {
		boolean saved = false;
		PreparedStatement stmt = null;
	
		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.liaisonCompta)
				+ " (server_name, user_code, pass_word, database_name) " + " VALUES (?,?,?,?)";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, link.getServerName());			
			stmt.setString(2, link.getUserCode());			
			stmt.setString(3, link.getPassWord());
			stmt.setString(4, link.getDataBase());

			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}
	
	private boolean updateLiaisonCompta(LiaisonComptaC link) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.liaisonCompta) + " SET  "
				+ "  server_name=?, user_code=?, pass_word=?, database_name=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, link.getServerName());			
			stmt.setString(2, link.getUserCode());			
			stmt.setString(3, link.getPassWord());
			stmt.setString(4, link.getDataBase());
			stmt.setInt(5, link.getId());
			
			stmt.execute();
			saved = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}

		return saved;
	}

	private LiaisonComptaC setLiaisonCompta(ResultSet rs) throws SQLException {
		LiaisonComptaC link = new LiaisonComptaC();
		link.setId(rs.getInt("id"));
		link.setDataBase(rs.getString("database_name"));
		link.setPassWord(rs.getString("pass_word"));
		link.setServerName(rs.getString("server_name"));
		link.setUserCode(rs.getString("user_code"));
		return link;
	}
	public LiaisonComptaC getLiaisonCompta() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LiaisonComptaC link = null;

		String sql = "SELECT * FROM " + Tables.getTableName(Tables.TableName.liaisonCompta) ;

		try {
			stmt = con.prepareStatement(sql);
		
			rs = stmt.executeQuery();
			if (rs.next()) {
				link = setLiaisonCompta (rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, rs);
		}

		return link;
	}
	public boolean deleteLiaisonCompta(LiaisonComptaC link) {
		boolean deleted = false;
		try {
			con.setAutoCommit(false);

			deleted = deleteNotAutocommit(link.getId(),
					Tables.getTableName(Tables.TableName.liaisonCompta));
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
	
}
