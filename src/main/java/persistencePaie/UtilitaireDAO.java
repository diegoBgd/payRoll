/*     */ package persistencePaie;
/*     */ 
/*     */ import classesPaie.DetailDeductionC;
import classesPaie.DetailPrimeEmployeC;
import classesPaie.HelperC;
import classesPaie.ParametragePrimeC;
import classesPaie.PrimeIndemniteC;
/*     */ import classesPaie.Tables;

/*     */ import java.io.Serializable;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
import java.util.Date;
/*     */ import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/*     */ public class UtilitaireDAO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -523801802635697709L;
/*     */   private static UtilitaireDAO dao;
/*     */   private static Connection con;
/*     */   

/*     */   private UtilitaireDAO() {
/*  29 */     con = Connexion.getConnection();
/*     */   }
/*     */ 
/*     */   
/*     */   public static UtilitaireDAO getInstance() {
/*  34 */     if (dao == null)
/*     */     {
/*  36 */       dao = new UtilitaireDAO();
/*     */     }
/*  38 */     return dao;
/*     */   }
/*     */ 
/*     */   
/*     */   private void releaseResource(Statement stmt, ResultSet rs) {
/*  43 */     if (rs != null) {
/*     */       
/*     */       try {
/*     */         
/*  47 */         rs.close();
/*     */       }
/*  49 */       catch (SQLException ex) {
/*     */         
/*  51 */         System.out.println(ex.getMessage());
/*     */       } 
/*     */     }
/*  54 */     if (stmt != null) {
/*     */       
/*     */       try {
/*     */         
/*  58 */         stmt.close();
/*     */       }
/*  60 */       catch (SQLException ex) {
/*     */         
/*  62 */         System.out.println(ex.getMessage());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updatePrimeEmploye(DetailPrimeEmployeC det) {
/*  69 */     boolean saved = false;
/*  70 */     Connection conn = con;
/*     */ 
/*     */     
/*     */     
/*     */     try {
/*  75 */       conn.setAutoCommit(false);
/*     */       
/*  77 */       if(det.getId()==0)
	               saved=insertDetailPrimeEmploye(det, conn);
				else
/*  79 */          saved = updateDetailPrimeEmploye(det, conn);
/*     */       
/*  81 */       if (saved) {
/*  82 */         conn.commit();
/*     */       } else {
/*  84 */         conn.rollback();
/*     */       }
/*     */     
/*     */     }
/*  88 */     catch (SQLException e) {
/*     */       
/*  90 */       saved = false;
/*  91 */       e.printStackTrace();
/*  92 */       System.out.println(e.getMessage());
/*     */       try {
/*  94 */         conn.rollback();
/*  95 */       } catch (SQLException e1) {
/*     */         
/*  97 */         e1.printStackTrace();
/*     */       } 
/*     */     } 
/* 100 */     return saved;
/*     */   }

			private boolean insertDetailPrimeEmploye(DetailPrimeEmployeC detP,Connection conx) {
/*  4409 */     boolean saved = false;
/*  4410 */     PreparedStatement stmt = null;
/*  4411 */    
/*  4412 */     String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailPrimeEmploye) + 
/*  4413 */       " (id_employe,id_prime,montant,taux,id_parametre) VALUES (?,?,?,?,?) ";
/*       */     
/*       */     try {
/*  4416 */       stmt = conx.prepareStatement(sql);
/*  4417 */     
/*  4418 */       if (detP.getEmploye() != null) {
/*       */         
/*  4420 */         stmt.setInt(1, detP.getEmploye().getId());
/*       */       } else {
/*       */         
/*  4423 */         stmt.setObject(1, (Object)null);
/*       */       } 
/*       */       
/*  4426 */       if (detP.getPrime() != null) {
/*       */         
/*  4428 */         stmt.setInt(2, detP.getPrime().getId());
/*       */       } else {
/*       */         
/*  4431 */         stmt.setObject(2, (Object)null);
/*       */       } 
/*  4433 */       stmt.setDouble(3, detP.getMontant());
/*  4434 */       stmt.setDouble(4, detP.getTaux());
/*       */       
/*  4436 */       if (detP.getIdParametre() > 0) {
/*  4437 */         stmt.setInt(5, detP.getIdParametre());
/*       */       } else {
/*  4439 */         stmt.setObject(5, (Object)null);
/*       */       } 
/*  4441 */       stmt.execute();
/*  4442 */       saved = true;
/*       */     
/*       */     }
/*  4445 */     catch (SQLException e) {
/*       */       
/*  4447 */       System.out.println(e.getMessage());
/*       */     } finally {
/*       */       
/*  4450 */       releaseResource(stmt, null);
/*       */     } 
/*  4452 */     return saved;
/*       */   }

/*     */   private boolean updateDetailPrimeEmploye(DetailPrimeEmployeC det, Connection connc) {
/* 108 */     boolean saved = false;
/* 109 */     PreparedStatement stmt = null;
/* 110 */     String sql = "UPDATE " + Tables.getTableName(Tables.TableName.detailPrimeEmploye) + 
/* 111 */       " SET montant=?,taux=?,id_parametre=? WHERE id=?";
/*     */     
/*     */     try {
/* 114 */       stmt = connc.prepareStatement(sql);
/*     */ 
/*     */       
/* 117 */       stmt.setDouble(1, det.getMontant());
/* 118 */       stmt.setDouble(2, det.getTaux());
/* 119 */       stmt.setInt(3, det.getIdParametre());
/* 120 */       stmt.setInt(4, det.getId());
/*     */       
/* 122 */       stmt.executeUpdate();
/*     */       
/* 124 */       saved = true;
/*     */     
/*     */     }
/* 127 */     catch (SQLException e) {
/*     */       
/* 129 */       System.out.println(e.getMessage());
/*     */     } finally {
/*     */       
/* 132 */       releaseResource(stmt, null);
/*     */     } 
/* 134 */     return saved;
/*     */   }

			public boolean insertDetailDeductionEmploye(int idEmpl,List<DetailDeductionC> listDed) {
/* 108 */     boolean saved = false;
/* 109 */     PreparedStatement stmt = null;
/* 110 */    		String sql = "INSERT INTO " + Tables.getTableName(Tables.TableName.detailDeductionEmploye) + 
		/*  4413 */       " (id_employe,id_deduction,montant) VALUES (?,?,?) ";
/*     */     
/*     */     try {
/* 114 */       stmt = con.prepareStatement(sql);
/*     */ 
/*     */       for (DetailDeductionC det : listDed) {
	
	 			stmt.setInt(1, idEmpl);
	 			stmt.setInt(2, det.getDeduction().getId());
/* 117 */       stmt.setDouble(3, det.getMontant());
/* 118 */       stmt.addBatch();
/* 120 */      
/*     */       }
/* 122 */       stmt.executeBatch();
/*     */       
/* 124 */       saved = true;
/*     */     
/*     */     }
/* 127 */     catch (SQLException e) {
/*     */       
/* 129 */       System.out.println(e.getMessage());
/*     */     } finally {
/*     */       
/* 132 */       releaseResource(stmt, null);
/*     */     } 
/* 134 */     return saved;
/*     */   }
			
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

