package persistencePaie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classesPaie.CotisationC;
import classesPaie.DetailCotisationEmployeC;
import classesPaie.DetailPrimeEmployeC;
import classesPaie.EmployeC;
import classesPaie.HelperC;
import classesPaie.PrimeIndemniteC;


public class RecupererPrimeAssistant {
static List<EmployeC> listAssistant;

	public static void main(String[] args) 
	{
		 Connection conn = null;
		 try {
		       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		     String connectionString = "jdbc:sqlserver://A-PC-DIEUDONNE\\SQL2;database=PAIEUB1;user=sa;password=asyst@16";
 
		       conn = DriverManager.getConnection(connectionString);
		       listAssistant=getListASS(conn, 3);//RECUPERER LA LISTE DES ASSISTANTS AYANT LE NOMBRE DE PRIME EN PARAMETRE
		       EmployeC empl=null;
		       DetailPrimeEmployeC detPrm=null;
		       PrimeIndemniteC prm=null;
		       List<EmployeC>listEmpl=null;
		       int i=0;
//		       for (EmployeC emp : listAssistant) 
//		       {
//		    	   empl=FactoryDAO.getInstance().getEmploye(emp.getCode(), true);//RECHERCHER SI L'ASSISTANT EXISTE DS LA NOUVELLE BDD (mysql)
//		    	   if(empl!=null)
//		    	   {
//		    		   System.out.println(emp.getCode()+" "+emp.getNomPrenom());
//		    		   System.out.println("--------------");
//		    	   for (DetailPrimeEmployeC det : empl.getListeDetailPrime()) //LISTE DES PRIMES DE L'ASSISTANT AVANT LE BLOCAGE 
//		    	   {
//		    		   detPrm=getPrimeAssistant(empl.getCode(), det.getPrime().getCode(), conn);//CHERCHER UNE PRIME DE L'ASSISTANT X AYANT LE CODE Z L'ANCIENNE BDD(sqlserver)
//		    		   if(detPrm==null)
//		    		   {
//		    			   det.setBloque(true);
//		    			   UtilitaireDAO.getInstance().disablePrimeEmploye(empl.getId(), det.getPrime().getId());//BLOQUER UNE PRIME DANS LA NOUVELLE BDD
//		    		   }
//		    		  
//		    			   System.out.println(det.getPrime().getCode()+" => "+det.isBloque());
//		    	   }
//		    	   
//		    	   }
//			   }
//		  
		       
		       /***
		        * ----------------------------------- RECUPERATION DE L'AJUSTEMENT SALARIAL ET PENSION RETRAITE-----------------------
		        */
		       
		       listEmpl=getListEmploye(conn);
		       
		       DetailPrimeEmployeC detP=null;
		       
		       for (EmployeC employe : listEmpl) 
		       {
		    	  empl=FactoryDAO.getInstance().getEmploye(employe.getCode());
		    	  if(empl!=null)
		    	  {
		    	   detPrm=getPrimeEmploye(employe.getCode(), "ICH", conn);
		    	   
		    	   if(detPrm!=null)
		    	   {
		    		   i++;
		    	   detPrm.setEmploye(empl);
		    	   
		    	   detP=FactoryDAO.getInstance().getDetailPrimeEmploye(0, detPrm.getPrime().getId(), empl.getId());
		    	   if(detP!=null) 
		    		   detPrm.setId(detP.getId());	    		   
		    	   	System.out.println(employe.getCode()+" ===> "+HelperC.decimalNumber(detPrm.getMontant(), 0, true));
		    	   	
		    	   	UtilitaireDAO.getInstance().updatePrimeEmploye(detPrm);
		    	   }
		    	   detPrm=null;
		    	  }
		       }
		       System.out.println("----------");
		       System.out.println(i);
		       
		       
		       /***
		        * ----------------------------------- RECUPERATION DES RETRAITES -----------------------
		        */
//		       EmployeC rt=null;
//		       CotisationC cot=null;
//		       listEmpl=getListRetraite(conn);
//		       
//		       for (EmployeC retr : listEmpl) {
//		    	   rt=FactoryDAO.getInstance().getEmploye(retr.getCode(), true);
//		    	   if(rt!=null)
//		    	   {
		    		// UtilitaireDAO.getInstance().updateSalaireBaseRetraite(rt.getId());
		    		   
//		    		   for (DetailPrimeEmployeC det : rt.getListeDetailPrime())  
//			    	   {
//		    			   UtilitaireDAO.getInstance().disablePrimeEmploye(rt.getId(), det.getPrime().getId());	    			  
//			    	   }
//		    			
//		    		   for (DetailCotisationEmployeC detCot : rt.getListeDetailCotisation()) 
//		    		   {
//		    			   if(detCot.getCotisation().getTypeBaremme()!=1)
//		    				   UtilitaireDAO.getInstance().disableCotisationEmploye(rt.getId(), detCot.getCotisation().getId());
//					   }
//		    		   detP=getPrimeEmploye(rt.getCode(), "PEN", conn);
//		    		   
//		    		   if(detP!=null)
//		    		   {
//		    			   
//		    		   			detPrm=FactoryDAO.getInstance().getDetailPrimeEmploye(0, detP.getPrime().getId(), rt.getId());
//		    		   			if(detPrm!=null)
//		    		   				detP.setId(detPrm.getId());
//		    		   			detP.setEmploye(rt);
//		    		   			UtilitaireDAO.getInstance().updatePrimeEmploye(detP);
//		    		   		
//		    		   }
		    		//   System.out.println(rt.getCode());
//		    	   }
//			}
		       
		       /***
		        * ------------------------------------------------- RECUPERER SALAIRE EMPLOYE --------------------------------------
		        */
//		       listEmpl=getListEmploye(conn);
//		       for (EmployeC employe : listEmpl) {
//				empl=FactoryDAO.getInstance().getEmploye(employe.getCode());
//				if(empl!=null)
//				{
//					UtilitaireDAO.getInstance().updateSalaireBase(empl.getId(), employe.getSalaireBase());
//					System.out.println(employe.getCode()+" "+HelperC.decimalNumber(employe.getSalaireBase(), 0, true));
//				}
//			}
		       
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	private static List<EmployeC> getListASS(Connection con,int count) {
		Statement stmt = null;
		ResultSet rs = null;
		List<EmployeC> listEmpl= new ArrayList<EmployeC>();
		EmployeC emp = null;
		String sql ="SELECT COUNT(A.code_prime),B.nom AS nomEm,A.code_employe AS code "+
				    "FROM [PAIEUB1].[dbo].[tbl_prime_indemnite_soumise_cotisation_octroye] AS A " + 
					" LEFT JOIN [PAIEUB1].[dbo].[tbl_employe] AS B ON A.code_employe=B.code_employe " + 
					" WHERE B.date_sortie IS NULL AND B.categorie='ASS' GROUP BY A.code_employe,B.nom " + 
				    " HAVING COUNT (A.code_prime)="+count+" ORDER BY A.code_employe ";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				emp = new EmployeC();
				emp.setCode(rs.getString("code"));
				emp.setNomPrenom(rs.getString("nomEm"));
				
				listEmpl.add(emp);
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {

			if (rs != null) {

				try {

					rs.close();
				} catch (Exception exception) {
				}
			}
			if (stmt != null) {

				try {

					stmt.close();
				} catch (Exception exception) {
				}
			}
		}

		return listEmpl;
	}

	  private static DetailPrimeEmployeC getPrimeAssistant(String codeEmploye,String codePrim ,Connection con) {
		    Statement stmt = null;
		    ResultSet rs = null;
		    DetailPrimeEmployeC primeIndem = null;
		  
		    PrimeIndemniteC prm = null;
		    
		    String sql="SELECT A.* FROM [PAIEUB1].[dbo].[tbl_prime_indemnite_soumise_cotisation_octroye] AS A" + 
		    		"  LEFT JOIN [PAIEUB1].[dbo].[tbl_employe] AS B ON A.code_employe=B.code_employe" + 
		    		"  WHERE B.date_sortie IS NULL AND B.categorie='ASS' AND A.code_employe='"+codeEmploye+"'"+
		    		"  AND A.code_prime='"+codePrim+"'";
		   try {
		      stmt = con.createStatement();
		      rs = stmt.executeQuery(sql);
		      
		      if (rs.next())
		      {
		        primeIndem = new DetailPrimeEmployeC();
		        primeIndem.setMontant(rs.getDouble("montant"));
		        prm = FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getString("code_prime"));
		        if (prm != null)
		         {
		          primeIndem.setPrime(prm);
		        
		       }
		     
		       }
		    
		    } catch (Exception e) {
		      
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
		     
		     return primeIndem;
		   }

	  private static DetailPrimeEmployeC getPrimeEmploye(String codeEmploye,String codePrim ,Connection con) {
		    Statement stmt = null;
		    ResultSet rs = null;
		    DetailPrimeEmployeC primeIndem = null;
		  
		    PrimeIndemniteC prm = null;
		    
		    String sql="SELECT * FROM [PAIEUB1].[dbo].[tbl_prime_indemnite_soumise_cotisation_paie] " + 
		    		"  WHERE no_dossier='2021' AND code_prime='"+codePrim+"' AND code_employe='"+codeEmploye+"'";
		   try {
		      stmt = con.createStatement();
		      rs = stmt.executeQuery(sql);
		      
		      if (rs.next())
		      {
		        primeIndem = new DetailPrimeEmployeC();
		        primeIndem.setMontant(rs.getDouble("montant"));
		        prm = FichierBaseDAO.getInstance().getPrimeIndemnite(rs.getString("code_prime"));
		        
		        if (prm != null)
		         {
		          primeIndem.setPrime(prm);		        
		         }
		     
		       }
		    
		    } catch (Exception e) {
		      
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
		     
		     return primeIndem;
		   }
	  
	  private static List<EmployeC> getListRetraite(Connection con) {
			Statement stmt = null;
			ResultSet rs = null;
			List<EmployeC> listEmpl= new ArrayList<EmployeC>();
			EmployeC emp = null;
			String sql ="SELECT * FROM [PAIEUB1].[dbo].[tbl_employe]  WHERE montant_base=0 ORDER BY code_employe";

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					emp = new EmployeC();
					emp.setCode(rs.getString("code_employe"));
					emp.setNomPrenom(rs.getString("nom"));
					emp.setSalaireBase(0);
					listEmpl.add(emp);
				}

			} catch (Exception e) {

				System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {

				if (rs != null) {

					try {

						rs.close();
					} catch (Exception exception) {
					}
				}
				if (stmt != null) {

					try {

						stmt.close();
					} catch (Exception exception) {
					}
				}
			}

			return listEmpl;
		}
	  
	  
	  private static List<EmployeC> getListEmploye(Connection con) {
			Statement stmt = null;
			ResultSet rs = null;
			List<EmployeC> listEmpl= new ArrayList<EmployeC>();
			EmployeC emp = null;
			String sql ="SELECT * FROM [PAIEUB1].[dbo].[tbl_employe] WHERE date_sortie IS null ORDER BY code_employe";

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					emp = new EmployeC();
					emp.setCode(rs.getString("code_employe"));
					emp.setNomPrenom(rs.getString("nom"));
					emp.setSalaireBase(rs.getDouble("montant_base"));
					listEmpl.add(emp);
				}

			} catch (Exception e) {

				System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {

				if (rs != null) {

					try {

						rs.close();
					} catch (Exception exception) {
					}
				}
				if (stmt != null) {

					try {

						stmt.close();
					} catch (Exception exception) {
					}
				}
			}

			return listEmpl;
		}
	  
	  private static List<EmployeC> getListEmployeAS(Connection con) {
			Statement stmt = null;
			ResultSet rs = null;
			List<EmployeC> listEmpl= new ArrayList<EmployeC>();
			EmployeC emp = null;
			String sql ="SELECT * FROM [PAIEUB1].[dbo].[tbl_prime_indemnite_soumise_cotisation_paie] " + 
					" WHERE no_dossier='2021' AND code_prime='AS' ";

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					emp = new EmployeC();
					emp.setCode(rs.getString("code_employe"));
					
					listEmpl.add(emp);
				}

			} catch (Exception e) {

				System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {

				if (rs != null) {

					try {

						rs.close();
					} catch (Exception exception) {
					}
				}
				if (stmt != null) {

					try {

						stmt.close();
					} catch (Exception exception) {
					}
				}
			}

			return listEmpl;
		}
}
