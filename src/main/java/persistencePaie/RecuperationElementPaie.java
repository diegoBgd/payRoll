 package persistencePaie;
 
 import classesPaie.BanqueC;
 import classesPaie.Base;
 import classesPaie.CategoriePersonnelC;
 import classesPaie.CotisationC;
 import classesPaie.DeductionC;
 import classesPaie.GradePersonnelC;
 import classesPaie.PrimeIndemniteC;
 import classesPaie.Tables;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.List;
 
 public class RecuperationElementPaie
   implements Serializable {
   private static final long serialVersionUID = 809012547255160668L;
   static List<PrimeIndemniteC> listPrime;
   static List<PrimeIndemniteC> listIndeminite;
   int k = 0;
   static List<CotisationC> listCotisation;
   static List<DeductionC> listDeduction;
   static List<Base> listOrganisme;
   static List<BanqueC> listBk;
   
   public static void main(String[] args) {
     int k = 4;
     
     Connection conn = null;
     
     try {
       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String connectionString = "jdbc:sqlserver://A-PC-DIEUDONNE\\SQL2;database=PAIEUB1;user=sa;password=asyst@16";

       conn = DriverManager.getConnection(connectionString);
       switch (k) {
 
         
         case 1:
           listOrganisme = getListOrganisime(conn);
           for (Base b : listOrganisme) {
             
             FichierBaseDAO.getInstance().insertUpdateBase(b, Tables.getTableName(Tables.TableName.organismesSociaux));
             System.out.println(b.getCode());
           } 
           break;
         case 2:
           listPrime = getListPrime(conn);
           
           for (PrimeIndemniteC prm : listPrime) {
             
             FichierBaseDAO.getInstance().insertUpdatePrimeIndemnite(prm);
             System.out.println(prm.getCode());
           } 
           break;
         case 3:
           listCotisation = getListCotisation(conn);
           
           for (CotisationC cot : listCotisation) {
             FichierBaseDAO.getInstance().insertUpdateCotisation(cot);
             System.out.println(cot.getCode());
           } 
           break;
         case 4:
           listDeduction = getListDeduction(conn);
           DeductionC d=null;
           for (DeductionC ded : listDeduction) {
 				d=FichierBaseDAO.getInstance().getDeduction(ded.getCode());
						if(d!=null)
            	 ded.setId(d.getId());
             FichierBaseDAO.getInstance().insertUpdateDeduction(ded);
             
             System.out.println(ded.getCode());
           } 
           break;
         case 5:
           listBk = getListBank(conn);
           for (BanqueC bk : listBk) {
             FichierBaseDAO.getInstance().insertUpdateBanque(bk);
             System.out.println(bk.getCode());
           } 
           break;
       } 
     
     } catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     } 
     if (conn != null) {
       
       try {
         
         conn.close();
       }
       catch (Exception exception) {}
     }
     
     if (conn != null) {
       
       try {
         
         conn.close();
       }
       catch (Exception exception) {}
     }
     
     if (conn != null) {
       
       try {
         
         conn.close();
       }
       catch (Exception exception) {}
     }
   }
 
 
   
   private static List<PrimeIndemniteC> getListPrime(Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     List<PrimeIndemniteC> listPrime = new ArrayList<PrimeIndemniteC>();
     PrimeIndemniteC prm = null;
     String sql = "SELECT * FROM [PAIEUNIV].[dbo].[tbl_prime_indemnite_soumise_cotisation] ";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         prm = new PrimeIndemniteC();
         prm.setCode(rs.getString("code_prime"));
         prm.setDesignation(rs.getString("designation"));
         prm.setTauxPrime(rs.getDouble("taux"));
         prm.setMontantMax(rs.getDouble("plafond"));
         prm.setMontantMin(rs.getDouble("plancher"));
         prm.setPrefixeComptable(rs.getString("prefixe_comptable"));
         prm.setTypePrime("P");
         
         if (rs.getString("type_imposable") == "1")
         {
           prm.setImposable(false);
         }
         if (rs.getString("type_imposable") == "2")
         {
           prm.setImposable(true);
         }
         listPrime.add(prm);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listPrime;
   }
 
   
   private static List<CotisationC> getListCotisation(Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     List<CotisationC> listCot = new ArrayList<CotisationC>();
     CotisationC cot = null;
     String sql = "SELECT * FROM [PAIEUNIV].[dbo].[tbl_retenue_cotisation] ";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         cot = new CotisationC();
         cot.setCode(rs.getString("code_retenu"));
         cot.setDesignation(rs.getString("designation"));
         cot.setChargePtronal(rs.getString("charge_patronale"));
         cot.setPrefixePatronal(rs.getString("organisme_patronale"));
         cot.setPrefixeSalarial(rs.getString("organisme_salariale"));
         listCot.add(cot);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listCot;
   }
 
   
   private static List<DeductionC> getListDeduction(Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     List<DeductionC> listDed = new ArrayList<DeductionC>();
     DeductionC ded = null;
     String sql = "SELECT * FROM [PAIEUB1].[dbo].[tbl_deduction] ";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         ded = new DeductionC();
         ded.setCode(rs.getString("code_deduction"));
         ded.setDesignation(rs.getString("designation"));
         ded.setTaux(rs.getDouble("taux"));
         ded.setPrefixeComptable(rs.getString("prefixe_comptable"));
         listDed.add(ded);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listDed;
   }
 
   
   private static List<Base> getListOrganisime(Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     List<Base> listOrg = new ArrayList<Base>();
     Base org = null;
     String sql = "SELECT * FROM [PAIEUNIV].[dbo].[tbl_organisme_sociaux] ";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         org = new Base();
         org.setCode(rs.getString("code"));
         org.setDesignation(rs.getString("designation"));
         listOrg.add(org);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listOrg;
   }
 
   
   private static List<BanqueC> getListBank(Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     List<BanqueC> listBk = new ArrayList<BanqueC>();
     BanqueC bk = null;
     String sql = "SELECT * FROM [PAIEUNIV].[dbo].[tbl_banque] ";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         bk = new BanqueC();
         bk.setCode(rs.getString("code_banque"));
         bk.setDesignation(rs.getString("nom"));
         bk.setCompte(rs.getString("compte_societe_banque"));
         listBk.add(bk);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listBk;
   }
 
   
   private static List<Base> getListPersonnel(Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     List<Base> listBse = new ArrayList<Base>();
     Base b = null;
     String sql = "SELECT * FROM [PAIEUB].[dbo].[tbl_service] ";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         b = new Base();
         b.setCode(rs.getString("code"));
         b.setDesignation(rs.getString("designation"));
         
         listBse.add(b);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listBse;
   }
 
   
   private static List<CategoriePersonnelC> getListCategoriePersonnel(Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     List<CategoriePersonnelC> listCat = new ArrayList<CategoriePersonnelC>();
     CategoriePersonnelC categ = null;
     String sql = "SELECT * FROM [PAIEUB].[dbo].[tbl_categorie_employe] ";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         categ = new CategoriePersonnelC();
         categ.setCode(rs.getString("code"));
         categ.setDesignation(rs.getString("designation"));
         
         listCat.add(categ);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listCat;
   }
 
   
   private static List<GradePersonnelC> getListGradePersonnel(Connection con) {
     Statement stmt = null;
     ResultSet rs = null;
     List<GradePersonnelC> listGrade = new ArrayList<GradePersonnelC>();
     GradePersonnelC grd = null;
     String sql = "SELECT * FROM [PAIEUB].[dbo].[tbl_grade] ";
     
     try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       while (rs.next())
       {
         grd = new GradePersonnelC();
         grd.setCode(rs.getString("code_grade"));
         grd.setDesignation(rs.getString("designation"));
         grd.setCodeCateg(rs.getString("categorie"));
         listGrade.add(grd);
       }
     
     }
     catch (Exception e) {
       
       System.out.println(e.getMessage());
       e.printStackTrace();
     }
     finally {
       
       if (rs != null) {
         
         try {
           
           rs.close();
         }
         catch (Exception exception) {}
       }
       if (stmt != null) {
         
         try {
           
           stmt.close();
         }
         catch (Exception exception) {}
       }
     } 
     
     return listGrade;
   }
 }


