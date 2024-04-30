 package persistencePaie;
 
 import classesPaie.DetailDeductionC;
import classesPaie.DetailPrimeEmployeC;
import classesPaie.HelperC;
import classesPaie.ParametragePrimeC;
import classesPaie.PrimeIndemniteC;
 import classesPaie.Tables;

 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
import java.util.Date;
 import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

 public class UtilitaireDAO
   implements Serializable
 {
   private static final long serialVersionUID = -523801802635697709L;
   private static UtilitaireDAO dao;
   private static Connection con;
   

   private UtilitaireDAO() {
     con = Connexion.getConnection();
   }
 
   
   public static UtilitaireDAO getInstance() {
     if (dao == null)
     {
       dao = new UtilitaireDAO();
     }
     return dao;
   }
 
   
   private void releaseResource(Statement stmt, ResultSet rs) {
     if (rs != null) {
       
       try {
         
         rs.close();
       }
       catch (SQLException ex) {
         
         System.out.println(ex.getMessage());
       } 
     }
     if (stmt != null) {
       
       try {
         
         stmt.close();
       }
       catch (SQLException ex) {
         
         System.out.println(ex.getMessage());
       } 
     }
   }
 
   
   public boolean updatePrimeEmploye(DetailPrimeEmployeC det) {
     boolean saved = false;
     Connection conn = con;
 
     
     
     try {
       conn.setAutoCommit(false);
       
       if(det.getId()==0)
	               saved=insertDetailPrimeEmploye(det, conn);
				else
          saved = updateDetailPrimeEmploye(det, conn);
       
       if (saved) {
         conn.commit();
       } else {
         conn.rollback();
       }
     
     }
     catch (SQLException e) {
       
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

			private boolean insertDetailPrimeEmploye(DetailPrimeEmployeC detP,Connection conx) {
     boolean saved = false;
     PreparedStatement stmt = null;
    
     String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailPrimeEmploye) + 
       " (id_employe,id_prime,montant,taux,id_parametre) VALUES (?,?,?,?,?) ";
     
     try {
       stmt = conx.prepareStatement(sql);
     
       if (detP.getEmploye() != null) {
         
         stmt.setInt(1, detP.getEmploye().getId());
       } else {
         
         stmt.setObject(1, (Object)null);
       } 
       
       if (detP.getPrime() != null) {
         
         stmt.setInt(2, detP.getPrime().getId());
       } else {
         
         stmt.setObject(2, (Object)null);
       } 
       stmt.setDouble(3, detP.getMontant());
       stmt.setDouble(4, detP.getTaux());
       
       if (detP.getIdParametre() > 0) {
         stmt.setInt(5, detP.getIdParametre());
       } else {
         stmt.setObject(5, (Object)null);
       } 
       stmt.execute();
       saved = true;
     
     }
     catch (SQLException e) {
       
       System.out.println(e.getMessage());
     } finally {
       
       releaseResource(stmt, null);
     } 
     return saved;
   }

   private boolean updateDetailPrimeEmploye(DetailPrimeEmployeC det, Connection connc) {
     boolean saved = false;
     PreparedStatement stmt = null;
     String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailPrimeEmploye) + 
       " SET montant=?,taux=?,id_parametre=? WHERE id=?";
     
     try {
       stmt = connc.prepareStatement(sql);
 
       
       stmt.setDouble(1, det.getMontant());
       stmt.setDouble(2, det.getTaux());
       stmt.setInt(3, det.getIdParametre());
       stmt.setInt(4, det.getId());
       
       stmt.executeUpdate();
       
       saved = true;
     
     }
     catch (SQLException e) {
       
       System.out.println(e.getMessage());
     } finally {
       
       releaseResource(stmt, null);
     } 
     return saved;
   }

			public boolean insertDetailDeductionEmploye(int idEmpl,List<DetailDeductionC> listDed) {
     boolean saved = false;
     PreparedStatement stmt = null;
    		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailDeductionEmploye) + 
		       " (id_employe,id_deduction,montant) VALUES (?,?,?) ";
     
     try {
       stmt = con.prepareStatement(sql);
 
       for (DetailDeductionC det : listDed) {
	
	 			stmt.setInt(1, idEmpl);
	 			stmt.setInt(2, det.getDeduction().getId());
       stmt.setDouble(3, det.getMontant());
       stmt.addBatch();
      
       }
       stmt.executeBatch();
       
       saved = true;
     
     }
     catch (SQLException e) {
       
       System.out.println(e.getMessage());
     } finally {
       
       releaseResource(stmt, null);
     } 
     return saved;
   }
			
			public boolean updateDateSortie(int idEmpl,Date dateSortie) {
				     boolean saved = false;
				     PreparedStatement stmt = null;
				     String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe) + 
				   " SET date_sortie=? WHERE id=?";
				
			    try {
				       stmt = con.prepareStatement(sql);
				       stmt.setObject(1, dateSortie);
				       stmt.setInt(2, idEmpl);
				    
				       stmt.execute();
				       
				      saved = true;
			   
			     }
			    catch (SQLException e) {
	      
	       System.out.println(e.getMessage());
			   } finally {
		     releaseResource(stmt, null);
			    } 
			     return saved;
				  }
			
			public boolean disablePrimeEmploye(int idEmpl,int idPrm) {
			     boolean saved = false;
			     PreparedStatement stmt = null;
			     String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailPrimeEmploye) + 
			   " SET bloque=1 WHERE id_employe=? AND id_prime=?";
			
		    try {
			       stmt = con.prepareStatement(sql);
			       stmt.setInt(1, idEmpl);
			       stmt.setInt(2, idPrm);
			    
			       stmt.execute();
			       
			      saved = true;
		   
		     }
		    catch (SQLException e) {
     
      System.out.println(e.getMessage());
		   } finally {
	     releaseResource(stmt, null);
		    } 
		     return saved;
			  }
			
	
			public boolean disableCotisationEmploye(int idEmpl,int idCot) {
			     boolean saved = false;
			     PreparedStatement stmt = null;
			     String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailCotisationEmploye) + 
			   " SET bloque=1 WHERE id_employe=? AND id_cotisation=?";
			
		    try {
			       stmt = con.prepareStatement(sql);
			       stmt.setInt(1, idEmpl);
			       stmt.setInt(2, idCot);
			    
			       stmt.execute();
			       
			      saved = true;
		   
		     }
		    catch (SQLException e) {
    
     System.out.println(e.getMessage());
		   } finally {
	     releaseResource(stmt, null);
		    } 
		     return saved;
			  }
			
			
	public boolean updateSalaireBaseRetraite(int idEmpl) {
			     boolean saved = false;
			     PreparedStatement stmt = null;
			     String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe) + 
			   " SET salaire_base=0 WHERE id=?";
			
		    try {
			       stmt = con.prepareStatement(sql);
			       stmt.setInt(1, idEmpl);
			    
			       stmt.execute();
			       
			      saved = true;
		   
		     }
		    catch (SQLException e) {
    
     System.out.println(e.getMessage());
		   } finally {
	     releaseResource(stmt, null);
		    } 
		     return saved;
			  }

	public boolean updateSalaireBase(int idEmpl, double salaire) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.employe) + " SET salaire_base=? WHERE id=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, salaire);
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

	public boolean updateCodeLine(int idEmpl,String mot) {
		boolean saved = false;
		PreparedStatement stmt = null;
		String sql = "UPDATE " + Tables.getTableName(Tables.TableName.operateur) + 
					 " SET pwd=? WHERE id_employe=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, mot);
			stmt.setInt(2, idEmpl);

			stmt.execute();
			
			saved = true;
			//System.out.println(stmt.toString());
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			releaseResource(stmt, null);
		}
		return saved;
	}
	
	public String getCodeLine(int idEmpl) 
	{
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	     String mot = "";
	     
	    String sql = "SELECT pwd FROM " + Tables.getTableName(Tables.TableName.operateur) + " WHERE id_employe=? ";

	    try {
	       stmt = con.prepareStatement(sql);
	      stmt.setInt(1, idEmpl);
	      rs = stmt.executeQuery();
	      if (rs.next()) 
	      {
	    	  mot=rs.getString("pwd");
	      }
	    }
	    catch (SQLException e) {
	      System.out.println(e.getMessage());
	     } finally {
	       releaseResource(stmt, rs);
	   } 
	   
	    return mot;
	   }

}

