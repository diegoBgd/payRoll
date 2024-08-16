package persistencePaie;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classesPaie.Base;
import classesPaie.EcritureComptableC;

public class LiaisonComptaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8890124012871713646L;
	private static LiaisonComptaDAO connexion;
	private static Connection con;

	private LiaisonComptaDAO() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/gestioncompta?characterEncoding=utf8";
			con = DriverManager.getConnection(url, "root", "123");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException sQLException) {

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connexion == null) {
			connexion = new LiaisonComptaDAO();
		}
		return con;
	}

	public List<Base> getPlanComptable(String motCpt, String motLbl) {
		List<Base> listPlan = new ArrayList<Base>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Base b = null;
		String sql = "SELECT * FROM tb_plan_compte ORDER BY reference ASC";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				b = new Base();
				b.setCode(rs.getString("reference"));
				b.setDesignation(rs.getString("libelle"));
				listPlan.add(b);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return listPlan;
	}

	public List<Base> getListJournal(String code, String motLbl) {
		List<Base> liste = new ArrayList<Base>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Base b = null;
		String sql = "SELECT * FROM tb_journal ORDER BY reference ASC";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				b = new Base();
				b.setCode(rs.getString("reference"));
				b.setDesignation(rs.getString("libelle"));
				liste.add(b);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return liste;
	}

	public Base getJournal(String code) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Base b = null;
		String sql = "SELECT * FROM tb_journal WHERE reference='" + code + "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				b = new Base();
				b.setCode(rs.getString("reference"));
				b.setDesignation(rs.getString("libelle"));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return b;
	}

	public Base getCompte(String code) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Base b = null;
		String sql = "SELECT * FROM tb_plan_compte WHERE reference='" + code + "'";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				b = new Base();
				b.setCode(rs.getString("reference"));
				b.setDesignation(rs.getString("libelle"));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return b;
	}
	
	public boolean insertEcriture(List<EcritureComptableC> listEcriture) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "";

		sqlRequest = "INSERT INTO tb_ecriture (date_operation,exerice_id,compte,"
				+ "journal,libelle_operation,piece,debit,credit) VALUES (?,?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			for (EcritureComptableC ecriture : listEcriture) {

				pstmt.setObject(1, ecriture.getDateEcriture());
				pstmt.setInt(2, ecriture.getIdExercice());
				pstmt.setString(3, ecriture.getCompte());
				pstmt.setString(4, ecriture.getCodeJournal());
				pstmt.setString(5, ecriture.getLibelle());
				pstmt.setString(6, ecriture.getPiece());
				pstmt.setDouble(7, ecriture.getDebit());
				pstmt.setDouble(7, ecriture.getCredit());

				pstmt.addBatch();
			}
 
			pstmt.executeBatch();
			saved = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			try {
				pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return saved;
	}
}
