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
import classesPaie.BulletinCotisationC;
import classesPaie.BulletinPaieC;
import classesPaie.EcritureComptableC;
import classesPaie.LiaisonComptaC;

public class LiaisonComptaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8890124012871713646L;
	private static LiaisonComptaDAO connexion;
	private static Connection con;

	private LiaisonComptaDAO(String serverNm,String uid,String pwd,String dbName) {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://"+serverNm+":3306/"+dbName +"?characterEncoding=utf8";
			con = DriverManager.getConnection(url, uid,pwd);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException sQLException) {

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static LiaisonComptaDAO getConnection(LiaisonComptaC link) {
		if (connexion == null) {
			connexion = new LiaisonComptaDAO(link.getServerName(),link.getUserCode(),link.getPassWord(),link.getDataBase());
		}
		return connexion;
	}
 
	public List<Base> getPlanComptable(String motCpt, String motLbl) {
		List<Base> listPlan = new ArrayList<Base>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Base b = null;
		String sql = "SELECT * FROM tb_plan_compte WHERE detail=1 ORDER BY reference ASC ";

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
		String sql = "SELECT * FROM tb_plan_compte WHERE reference='" + code + "' AND detail=1";

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
	public Base getExercice(String code) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Base b = null;
		String sql = "SELECT * FROM tb_exercice WHERE reference='" + code + "'";

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
				pstmt.setDouble(8, ecriture.getCredit());

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
	
	public boolean insertPaie(List<BulletinPaieC> listPaie) {
		boolean saved = false;
		PreparedStatement pstmt = null;
		String sqlRequest = "";

		sqlRequest = "INSERT INTO tb_depense (cours,date_operation,exercice,libelle,montant,num_piece,taux,centre_cout,"
				+ "type_charge,devise,fournisseur,taxe,type_depense,mode_reglement) VALUES (?,?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sqlRequest);
			
			for (BulletinPaieC bulletin : listPaie) {

				pstmt.setObject(1, 1);
				pstmt.setObject(2, bulletin.getDatePaie());
				pstmt.setInt(3, bulletin.getIdExercice());
				pstmt.setString(4, bulletin.getCommentaire());				
				pstmt.setDouble(5, bulletin.getTotalNetPay());
				pstmt.setString(6, bulletin.getMoisPrint());

				pstmt.addBatch();
				
				for (BulletinCotisationC bCot : bulletin.getListeCotisation()) {
					
				}
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
	
	private Base getEmploye(String code,Connection conx) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Base b = null;
		String sql = "SELECT * FROM tb_partenaire WHERE reference='" + code + "'";

		try {
			stmt = conx.prepareStatement(sql);
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
}
