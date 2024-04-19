/*     */ package persistencePaie;
/*     */ 
/*     */ import classesPaie.BanqueC;
/*     */ import classesPaie.Base;
/*     */ import classesPaie.CategoriePersonnelC;
/*     */ import classesPaie.CotisationC;
/*     */ import classesPaie.DeductionC;
/*     */ import classesPaie.GradePersonnelC;
/*     */ import classesPaie.PrimeIndemniteC;
/*     */ import classesPaie.Tables;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RecuperationElementPaie
/*     */   implements Serializable {
/*     */   private static final long serialVersionUID = 809012547255160668L;
/*     */   static List<PrimeIndemniteC> listPrime;
/*     */   static List<PrimeIndemniteC> listIndeminite;
/*  24 */   int k = 0;
/*     */   static List<CotisationC> listCotisation;
/*     */   static List<DeductionC> listDeduction;
/*     */   static List<Base> listOrganisme;
/*     */   static List<BanqueC> listBk;
/*     */   
/*     */   public static void main(String[] args) {
/*  31 */     int k = 4;
/*     */     
/*  33 */     Connection conn = null;
/*     */     
/*     */     try {
/*  36 */       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
/*  37 */      String connectionString = "jdbc:sqlserver://A-PC-DIEUDONNE\\SQL2;database=PAIEUB1;user=sa;password=asyst@16";

/*  38 */       conn = DriverManager.getConnection(connectionString);
/*  39 */       switch (k) {
/*     */ 
/*     */         
/*     */         case 1:
/*  43 */           listOrganisme = getListOrganisime(conn);
/*  44 */           for (Base b : listOrganisme) {
/*     */             
/*  46 */             FichierBaseDAO.getInstance().insertUpdateBase(b, Tables.getTableName(Tables.TableName.organismesSociaux));
/*  47 */             System.out.println(b.getCode());
/*     */           } 
/*     */           break;
/*     */         case 2:
/*  51 */           listPrime = getListPrime(conn);
/*     */           
/*  53 */           for (PrimeIndemniteC prm : listPrime) {
/*     */             
/*  55 */             FichierBaseDAO.getInstance().insertUpdatePrimeIndemnite(prm);
/*  56 */             System.out.println(prm.getCode());
/*     */           } 
/*     */           break;
/*     */         case 3:
/*  60 */           listCotisation = getListCotisation(conn);
/*     */           
/*  62 */           for (CotisationC cot : listCotisation) {
/*  63 */             FichierBaseDAO.getInstance().insertUpdateCotisation(cot);
/*  64 */             System.out.println(cot.getCode());
/*     */           } 
/*     */           break;
/*     */         case 4:
/*  68 */           listDeduction = getListDeduction(conn);
/*     */           DeductionC d=null;
/*  70 */           for (DeductionC ded : listDeduction) {
/*     */ 				d=FichierBaseDAO.getInstance().getDeduction(ded.getCode());
						if(d!=null)
/*     */            	 ded.setId(d.getId());
/*  73 */             FichierBaseDAO.getInstance().insertUpdateDeduction(ded);
/*     */             
/*  75 */             System.out.println(ded.getCode());
/*     */           } 
/*     */           break;
/*     */         case 5:
/*  79 */           listBk = getListBank(conn);
/*  80 */           for (BanqueC bk : listBk) {
/*  81 */             FichierBaseDAO.getInstance().insertUpdateBanque(bk);
/*  82 */             System.out.println(bk.getCode());
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     
/*  87 */     } catch (Exception e) {
/*     */       
/*  89 */       System.out.println(e.getMessage());
/*  90 */       e.printStackTrace();
/*     */     } 
/*  92 */     if (conn != null) {
/*     */       
/*     */       try {
/*     */         
/*  96 */         conn.close();
/*     */       }
/*  98 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 101 */     if (conn != null) {
/*     */       
/*     */       try {
/*     */         
/* 105 */         conn.close();
/*     */       }
/* 107 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 110 */     if (conn != null) {
/*     */       
/*     */       try {
/*     */         
/* 114 */         conn.close();
/*     */       }
/* 116 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<PrimeIndemniteC> getListPrime(Connection con) {
/* 123 */     Statement stmt = null;
/* 124 */     ResultSet rs = null;
/* 125 */     List<PrimeIndemniteC> listPrime = new ArrayList<PrimeIndemniteC>();
/* 126 */     PrimeIndemniteC prm = null;
/* 127 */     String sql = "SELECT * FROM [PAIEUNIV].[dbo].[tbl_prime_indemnite_soumise_cotisation] ";
/*     */     
/*     */     try {
/* 130 */       stmt = con.createStatement();
/* 131 */       rs = stmt.executeQuery(sql);
/* 132 */       while (rs.next())
/*     */       {
/* 134 */         prm = new PrimeIndemniteC();
/* 135 */         prm.setCode(rs.getString("code_prime"));
/* 136 */         prm.setDesignation(rs.getString("designation"));
/* 137 */         prm.setTauxPrime(rs.getDouble("taux"));
/* 138 */         prm.setMontantMax(rs.getDouble("plafond"));
/* 139 */         prm.setMontantMin(rs.getDouble("plancher"));
/* 140 */         prm.setPrefixeComptable(rs.getString("prefixe_comptable"));
/* 141 */         prm.setTypePrime("P");
/*     */         
/* 143 */         if (rs.getString("type_imposable") == "1")
/*     */         {
/* 145 */           prm.setImposable(false);
/*     */         }
/* 147 */         if (rs.getString("type_imposable") == "2")
/*     */         {
/* 149 */           prm.setImposable(true);
/*     */         }
/* 151 */         listPrime.add(prm);
/*     */       }
/*     */     
/*     */     }
/* 155 */     catch (Exception e) {
/*     */       
/* 157 */       System.out.println(e.getMessage());
/* 158 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 162 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 166 */           rs.close();
/*     */         }
/* 168 */         catch (Exception exception) {}
/*     */       }
/* 170 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 174 */           stmt.close();
/*     */         }
/* 176 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 180 */     return listPrime;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<CotisationC> getListCotisation(Connection con) {
/* 185 */     Statement stmt = null;
/* 186 */     ResultSet rs = null;
/* 187 */     List<CotisationC> listCot = new ArrayList<CotisationC>();
/* 188 */     CotisationC cot = null;
/* 189 */     String sql = "SELECT * FROM [PAIEUNIV].[dbo].[tbl_retenue_cotisation] ";
/*     */     
/*     */     try {
/* 192 */       stmt = con.createStatement();
/* 193 */       rs = stmt.executeQuery(sql);
/* 194 */       while (rs.next())
/*     */       {
/* 196 */         cot = new CotisationC();
/* 197 */         cot.setCode(rs.getString("code_retenu"));
/* 198 */         cot.setDesignation(rs.getString("designation"));
/* 199 */         cot.setChargePtronal(rs.getString("charge_patronale"));
/* 200 */         cot.setPrefixePatronal(rs.getString("organisme_patronale"));
/* 201 */         cot.setPrefixeSalarial(rs.getString("organisme_salariale"));
/* 202 */         listCot.add(cot);
/*     */       }
/*     */     
/*     */     }
/* 206 */     catch (Exception e) {
/*     */       
/* 208 */       System.out.println(e.getMessage());
/* 209 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 213 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 217 */           rs.close();
/*     */         }
/* 219 */         catch (Exception exception) {}
/*     */       }
/* 221 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 225 */           stmt.close();
/*     */         }
/* 227 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 231 */     return listCot;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<DeductionC> getListDeduction(Connection con) {
/* 236 */     Statement stmt = null;
/* 237 */     ResultSet rs = null;
/* 238 */     List<DeductionC> listDed = new ArrayList<DeductionC>();
/* 239 */     DeductionC ded = null;
/* 240 */     String sql = "SELECT * FROM [PAIEUB1].[dbo].[tbl_deduction] ";
/*     */     
/*     */     try {
/* 243 */       stmt = con.createStatement();
/* 244 */       rs = stmt.executeQuery(sql);
/* 245 */       while (rs.next())
/*     */       {
/* 247 */         ded = new DeductionC();
/* 248 */         ded.setCode(rs.getString("code_deduction"));
/* 249 */         ded.setDesignation(rs.getString("designation"));
/* 250 */         ded.setTaux(rs.getDouble("taux"));
/* 251 */         ded.setPrefixeComptable(rs.getString("prefixe_comptable"));
/* 252 */         listDed.add(ded);
/*     */       }
/*     */     
/*     */     }
/* 256 */     catch (Exception e) {
/*     */       
/* 258 */       System.out.println(e.getMessage());
/* 259 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 263 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 267 */           rs.close();
/*     */         }
/* 269 */         catch (Exception exception) {}
/*     */       }
/* 271 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 275 */           stmt.close();
/*     */         }
/* 277 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 281 */     return listDed;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<Base> getListOrganisime(Connection con) {
/* 286 */     Statement stmt = null;
/* 287 */     ResultSet rs = null;
/* 288 */     List<Base> listOrg = new ArrayList<Base>();
/* 289 */     Base org = null;
/* 290 */     String sql = "SELECT * FROM [PAIEUNIV].[dbo].[tbl_organisme_sociaux] ";
/*     */     
/*     */     try {
/* 293 */       stmt = con.createStatement();
/* 294 */       rs = stmt.executeQuery(sql);
/* 295 */       while (rs.next())
/*     */       {
/* 297 */         org = new Base();
/* 298 */         org.setCode(rs.getString("code"));
/* 299 */         org.setDesignation(rs.getString("designation"));
/* 300 */         listOrg.add(org);
/*     */       }
/*     */     
/*     */     }
/* 304 */     catch (Exception e) {
/*     */       
/* 306 */       System.out.println(e.getMessage());
/* 307 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 311 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 315 */           rs.close();
/*     */         }
/* 317 */         catch (Exception exception) {}
/*     */       }
/* 319 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 323 */           stmt.close();
/*     */         }
/* 325 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 329 */     return listOrg;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<BanqueC> getListBank(Connection con) {
/* 334 */     Statement stmt = null;
/* 335 */     ResultSet rs = null;
/* 336 */     List<BanqueC> listBk = new ArrayList<BanqueC>();
/* 337 */     BanqueC bk = null;
/* 338 */     String sql = "SELECT * FROM [PAIEUNIV].[dbo].[tbl_banque] ";
/*     */     
/*     */     try {
/* 341 */       stmt = con.createStatement();
/* 342 */       rs = stmt.executeQuery(sql);
/* 343 */       while (rs.next())
/*     */       {
/* 345 */         bk = new BanqueC();
/* 346 */         bk.setCode(rs.getString("code_banque"));
/* 347 */         bk.setDesignation(rs.getString("nom"));
/* 348 */         bk.setCompte(rs.getString("compte_societe_banque"));
/* 349 */         listBk.add(bk);
/*     */       }
/*     */     
/*     */     }
/* 353 */     catch (Exception e) {
/*     */       
/* 355 */       System.out.println(e.getMessage());
/* 356 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 360 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 364 */           rs.close();
/*     */         }
/* 366 */         catch (Exception exception) {}
/*     */       }
/* 368 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 372 */           stmt.close();
/*     */         }
/* 374 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 378 */     return listBk;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<Base> getListPersonnel(Connection con) {
/* 383 */     Statement stmt = null;
/* 384 */     ResultSet rs = null;
/* 385 */     List<Base> listBse = new ArrayList<Base>();
/* 386 */     Base b = null;
/* 387 */     String sql = "SELECT * FROM [PAIEUB].[dbo].[tbl_service] ";
/*     */     
/*     */     try {
/* 390 */       stmt = con.createStatement();
/* 391 */       rs = stmt.executeQuery(sql);
/* 392 */       while (rs.next())
/*     */       {
/* 394 */         b = new Base();
/* 395 */         b.setCode(rs.getString("code"));
/* 396 */         b.setDesignation(rs.getString("designation"));
/*     */         
/* 398 */         listBse.add(b);
/*     */       }
/*     */     
/*     */     }
/* 402 */     catch (Exception e) {
/*     */       
/* 404 */       System.out.println(e.getMessage());
/* 405 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 409 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 413 */           rs.close();
/*     */         }
/* 415 */         catch (Exception exception) {}
/*     */       }
/* 417 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 421 */           stmt.close();
/*     */         }
/* 423 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 427 */     return listBse;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<CategoriePersonnelC> getListCategoriePersonnel(Connection con) {
/* 432 */     Statement stmt = null;
/* 433 */     ResultSet rs = null;
/* 434 */     List<CategoriePersonnelC> listCat = new ArrayList<CategoriePersonnelC>();
/* 435 */     CategoriePersonnelC categ = null;
/* 436 */     String sql = "SELECT * FROM [PAIEUB].[dbo].[tbl_categorie_employe] ";
/*     */     
/*     */     try {
/* 439 */       stmt = con.createStatement();
/* 440 */       rs = stmt.executeQuery(sql);
/* 441 */       while (rs.next())
/*     */       {
/* 443 */         categ = new CategoriePersonnelC();
/* 444 */         categ.setCode(rs.getString("code"));
/* 445 */         categ.setDesignation(rs.getString("designation"));
/*     */         
/* 447 */         listCat.add(categ);
/*     */       }
/*     */     
/*     */     }
/* 451 */     catch (Exception e) {
/*     */       
/* 453 */       System.out.println(e.getMessage());
/* 454 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 458 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 462 */           rs.close();
/*     */         }
/* 464 */         catch (Exception exception) {}
/*     */       }
/* 466 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 470 */           stmt.close();
/*     */         }
/* 472 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 476 */     return listCat;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<GradePersonnelC> getListGradePersonnel(Connection con) {
/* 481 */     Statement stmt = null;
/* 482 */     ResultSet rs = null;
/* 483 */     List<GradePersonnelC> listGrade = new ArrayList<GradePersonnelC>();
/* 484 */     GradePersonnelC grd = null;
/* 485 */     String sql = "SELECT * FROM [PAIEUB].[dbo].[tbl_grade] ";
/*     */     
/*     */     try {
/* 488 */       stmt = con.createStatement();
/* 489 */       rs = stmt.executeQuery(sql);
/* 490 */       while (rs.next())
/*     */       {
/* 492 */         grd = new GradePersonnelC();
/* 493 */         grd.setCode(rs.getString("code_grade"));
/* 494 */         grd.setDesignation(rs.getString("designation"));
/* 495 */         grd.setCodeCateg(rs.getString("categorie"));
/* 496 */         listGrade.add(grd);
/*     */       }
/*     */     
/*     */     }
/* 500 */     catch (Exception e) {
/*     */       
/* 502 */       System.out.println(e.getMessage());
/* 503 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 507 */       if (rs != null) {
/*     */         
/*     */         try {
/*     */           
/* 511 */           rs.close();
/*     */         }
/* 513 */         catch (Exception exception) {}
/*     */       }
/* 515 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 519 */           stmt.close();
/*     */         }
/* 521 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 525 */     return listGrade;
/*     */   }
/*     */ }


/* Location:              G:\PAIE\!\persistencePaie\RecuperationElementPaie.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */