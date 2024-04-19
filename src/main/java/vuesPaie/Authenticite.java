/*     */ package vuesPaie;
/*     */ 
/*     */ import classesPaie.Base;
/*     */ import classesPaie.CategoriePersonnelC;
/*     */ import classesPaie.Constante;
/*     */ import classesPaie.DetailGradeC;
/*     */ import classesPaie.DroitC;
/*     */ import classesPaie.EmployeC;
/*     */ import classesPaie.HelperC;
/*     */ import classesPaie.OperateurC;
/*     */ import classesPaie.ParametrageDecideurSignataireC;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import persistencePaie.FactoryDAO;
/*     */ import persistencePaie.FichierBaseDAO;

/*     */ @ManagedBean
/*     */ @ViewScoped
/*     */ public class Authenticite
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 9223372036854775000L;
/*     */   private OperateurC operateur;
/*     */   private Base fonction;
/*     */   private DroitC droit;

			private HttpSession session;
			private boolean userExest; 
			private ParametrageDecideurSignataireC signataire; 
			private EmployeC employe; 
			private static String infoMessage; 
			int direction; 
			int service; 
			int sousService; 
			int idFonction; 
			CategoriePersonnelC categorie;
			DetailGradeC grade; 
			String codeOperateur;
 
			int idPersonel;
/*     */   int operation;
/*     */   int idDirectionUb;
/*     */   
            public Authenticite() 
            {
            	 FacesContext context = FacesContext.getCurrentInstance();
                 session = (HttpSession)context.getExternalContext().getSession(true);
            	
			}
/*     */   public Base getFonction() {
/*  45 */     return this.fonction;
/*     */   }
/*     */   
/*     */   public void setFonction(Base fonction) {
/*  50 */     this.fonction = fonction;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUserExest() {
/*  55 */     return this.userExest;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserExest(boolean userExest) {
/*  60 */     this.userExest = userExest;
/*     */   }
/*     */   
/*     */   public ParametrageDecideurSignataireC getSignataire() {
/*  64 */     return this.signataire;
/*     */   }
/*     */   
/*     */   public void setSignataire(ParametrageDecideurSignataireC signataire) {
/*  68 */     this.signataire = signataire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void message() {
/*  73 */     String _msg = (String)this.session.getAttribute("msg");
/*  74 */     if (_msg != null && !_msg.equals(""))
/*     */     {
/*  76 */       if (_msg.trim().equals("droit")) {
/*     */         
/*  78 */         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit d'acc�der � ce menu!");
/*  79 */         this.session.setAttribute("msg", null);
/*     */       } else {
/*     */         
/*  82 */         _msg.trim().equals("");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   public void initialize() {
/*  90 */     String exist = null;

/*  91 */     this.codeOperateur = (String)this.session.getAttribute("operateur");
/*  92 */     if (this.session.getAttribute("existUser") != null) {
/*  93 */       exist = this.session.getAttribute("existUser").toString();
/*     */     }
/*  95 */     if (exist != null) {
/*  96 */       if (exist.equals("true")) {
/*  97 */         this.userExest = true;
/*     */       } else {
/*  99 */         this.userExest = false;
/*     */       } 
/*     */     }
/*     */     
/* 103 */     if (this.codeOperateur != null) {
/* 104 */       this.operateur = FichierBaseDAO.getInstance()
/* 105 */         .getOperateur(this.codeOperateur);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void OrganisationAcademique() {
/*     */     try {
/* 114 */       if (this.operateur == null) {
/* 115 */         FacesContext.getCurrentInstance().getExternalContext()
/* 116 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/* 118 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 119 */             this.operateur.getIdEmploye());
				if(fonction!=null) {
/* 120 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 121 */             Constante.Role.organisationAcademique);
/* 122 */         if (this.droit == null) {
/* 123 */           this.session.setAttribute("msg", "droit");
/* 124 */           FacesContext.getCurrentInstance().getExternalContext()
/* 125 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       
/*     */       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				}

/*     */     
/* 130 */     } catch (IOException e) {
/* 131 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void structureAdministrative() {
/*     */     try {
/* 140 */       if (this.userExest) {
/* 141 */         if (this.operateur == null) {
/* 142 */           FacesContext.getCurrentInstance().getExternalContext()
/* 143 */             .redirect("/payRoll/login.jsf");
/*     */         } else {
/* 145 */           this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 146 */               this.operateur.getIdEmploye());
					if(fonction!=null) {
/* 147 */           this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 148 */               Constante.Role.structureAdministrative);
/* 149 */           if (this.droit == null) {
/* 150 */             this.session.setAttribute("msg", "droit");
/* 151 */             FacesContext.getCurrentInstance().getExternalContext()
/* 152 */               .redirect("/payRoll/messages.jsf");
/*     */           }
/*     */         }
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
						
/*     */         }
/*     */       
/*     */       }
/* 158 */     } catch (IOException e) {
/*     */       
/* 160 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void habilitation() {
/*     */     try {
/* 169 */       if (this.userExest)
/*     */       {
/* 171 */         if (this.operateur == null)
/*     */         {
/* 173 */           FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 178 */           this.fonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
/* 179 */           this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), Constante.Role.parametrage);
/* 180 */           if (this.droit == null)
/*     */           {
/* 182 */             this.session.setAttribute("msg", "droit");
/* 183 */             FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 189 */     } catch (IOException e) {
/*     */       
/* 191 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fichiersBase() {
/*     */     try {
/* 199 */       if (this.operateur == null) {
/* 200 */         FacesContext.getCurrentInstance().getExternalContext()
/* 201 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/*     */         
/* 204 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 205 */             this.operateur.getIdEmploye());
                 if(fonction!=null){
/* 206 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 207 */             Constante.Role.fichierBase);
/* 208 */         if (this.droit == null) {
/* 209 */           this.session.setAttribute("msg", "droit");
/* 210 */           FacesContext.getCurrentInstance().getExternalContext()
/* 211 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       }
                 else{
                	 this.session.setAttribute("msg", "droit");
                       FacesContext.getCurrentInstance().getExternalContext()
                	         .redirect("/payRoll/messages.jsf");
                 }
/*     */       }
/*     */     
/* 216 */     } catch (IOException e) {
/* 217 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parametrage() {
/*     */     try {
/* 226 */       if (this.operateur == null) {
/* 227 */         FacesContext.getCurrentInstance().getExternalContext()
/* 228 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/*     */         
/* 231 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 232 */             this.operateur.getIdEmploye());
			 	 if(fonction!=null) {
/* 233 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 234 */             Constante.Role.parametrage);
/* 235 */         if (this.droit == null) {
/* 236 */           this.session.setAttribute("msg", "droit");
/* 237 */           FacesContext.getCurrentInstance().getExternalContext()
/* 238 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       
/*     */       }
			 	else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
/*     */     
/*     */     }
}
/* 244 */     catch (IOException e) {
/*     */       
/* 246 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void elementsPaie() {
/*     */     try {
/* 253 */       if (this.operateur == null) {
/* 254 */         FacesContext.getCurrentInstance().getExternalContext()
/* 255 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/* 257 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 258 */             this.operateur.getIdEmploye());
					if(fonction!=null)
					{
/* 259 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 260 */             Constante.Role.elementPaie);
/* 261 */         if (this.droit == null) {
/* 262 */           this.session.setAttribute("msg", "droit");
/* 263 */           FacesContext.getCurrentInstance().getExternalContext()
/* 264 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       
/*     */       } 
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
				}
/* 268 */     } catch (IOException e) {
/* 269 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void CreditEmploye() {
/*     */     try {
/* 277 */       if (this.operateur == null) {
/* 278 */         FacesContext.getCurrentInstance().getExternalContext()
/* 279 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/* 281 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 282 */             this.operateur.getIdEmploye());
				if(fonction!=null) {
/* 283 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 284 */             Constante.Role.creditAvance);
/* 285 */         if (this.droit == null) {
/* 286 */           this.session.setAttribute("msg", "droit");
/* 287 */           FacesContext.getCurrentInstance().getExternalContext()
/* 288 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
/*     */       } 
/* 292 */     } catch (IOException e) {
/* 293 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void profilEmploye() {
/*     */     try {
/* 302 */       if (this.operateur == null) {
/* 303 */         FacesContext.getCurrentInstance().getExternalContext()
/* 304 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/*     */         
/* 307 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 308 */             this.operateur.getIdEmploye());
				  if(fonction!=null)
				  {
/* 309 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 310 */             Constante.Role.profilEmploye);

/* 311 */         if (this.droit == null) {
/* 312 */           this.session.setAttribute("msg", "droit");
/* 313 */           FacesContext.getCurrentInstance().getExternalContext()
/* 314 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       
/*     */       }
				  else {
					  this.session.setAttribute("msg", "droit");
					  FacesContext.getCurrentInstance().getExternalContext()
					  .redirect("/payRoll/messages.jsf");
				  }
				}
/*     */     
/* 319 */     } catch (IOException e) {
/*     */       
/* 321 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void gestionPosition() {
/*     */     try {
/* 330 */       if (this.operateur != null) {
/* 331 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 332 */             this.operateur.getIdEmploye());
					if(fonction!=null) {
/* 333 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 334 */             Constante.Role.gestionPosition);
					
/* 335 */         if (this.droit == null) {
/* 336 */           this.session.setAttribute("msg", "droit");
/* 337 */           FacesContext.getCurrentInstance().getExternalContext()
/* 338 */             .redirect("/payRoll/messages.jsf");
/*     */         } 
/*     */       }
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
				}
/* 345 */     } catch (IOException e) {
/*     */       
/* 347 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void gestionAbsencePresence() {
/*     */     try {
/* 357 */       if (this.operateur == null) {
/* 358 */         FacesContext.getCurrentInstance().getExternalContext()
/* 359 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/*     */         
/* 362 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 363 */             this.operateur.getIdEmploye());
				if(fonction!=null) {
/* 364 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 365 */             Constante.Role.gestionAbsencePresence);
/* 366 */         if (this.droit == null) {
/* 367 */           this.session.setAttribute("msg", "droit");
/* 368 */           FacesContext.getCurrentInstance().getExternalContext()
/* 369 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
/*     */       }
/*     */     
/* 374 */     } catch (IOException e) {
/*     */       
/* 376 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void gestionConge() {
/*     */     try {
/* 385 */       if (this.operateur == null) {
/* 386 */         FacesContext.getCurrentInstance().getExternalContext()
/* 387 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/*     */         
/* 390 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 391 */             this.operateur.getIdEmploye());
					if(fonction!=null) {
/* 392 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 393 */             Constante.Role.gestionConge);
/* 394 */         if (this.droit == null) {
/* 395 */           this.session.setAttribute("msg", "droit");
/* 396 */           FacesContext.getCurrentInstance().getExternalContext()
/* 397 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       }
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
/*     */       }
/*     */     
/* 402 */     } catch (IOException e) {
/*     */       
/* 404 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
        
	      public void mesuresDisciplinaires() {
		      try {
			  if (this.operateur == null) {
				  FacesContext.getCurrentInstance().getExternalContext()
						  .redirect("/payRoll/login.jsf");
				      }
			  else if (this.operateur != null) {
				  this.fonction = FichierBaseDAO.getInstance()
						.getFonctionActive(this.operateur.getIdEmploye());
				if (fonction != null) {
					  this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(),
							  Constante.Role.mesuresDisciplinaires);
					     
					  if (this.droit == null) {
						  this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext()
							 .redirect("/payRoll/messages.jsf");
						      }
					      } else {
					this.session.setAttribute("msg", "droit");
					FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				      }
			     
			  } catch (IOException e) {
			     
			  System.out.println(e.getMessage());
			      }
		      }
	      public void parametrageRetraite() {
		      try {
			  if (this.operateur == null) {
				  FacesContext.getCurrentInstance().getExternalContext()
						  .redirect("/payRoll/login.jsf");
				      }
			  else if (this.operateur != null) {
				  this.fonction = FichierBaseDAO.getInstance()
						.getFonctionActive(this.operateur.getIdEmploye());
				if (fonction != null) {
					  this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(),
							  Constante.Role.parametrageFinCarriere);
					     
					  if (this.droit == null) {
						  this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext()
							 .redirect("/payRoll/messages.jsf");
						      }
					      } else {
					this.session.setAttribute("msg", "droit");
					FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				      }
			     
			  } catch (IOException e) {
			     
			  System.out.println(e.getMessage());
			      }
		      }
	      
	      public void gestionDemandeProlongation() {
		      try {
			  if (this.operateur == null) {
				  FacesContext.getCurrentInstance().getExternalContext()
						  .redirect("/payRoll/login.jsf");
				      }
			  else if (this.operateur != null) {
				  this.fonction = FichierBaseDAO.getInstance()
						.getFonctionActive(this.operateur.getIdEmploye());
				if (fonction != null) {
					  this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(),
							  Constante.Role.demandeProlongationRetraite);
					     
					  if (this.droit == null) {
						  this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext()
							 .redirect("/payRoll/messages.jsf");
						      }
					      } else {
					this.session.setAttribute("msg", "droit");
					FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				      }
			     
			  } catch (IOException e) {
			     
			  System.out.println(e.getMessage());
			      }
		      }
	      public void validationProlongationRetraite() {
		      try {
			  if (this.operateur == null) {
				  FacesContext.getCurrentInstance().getExternalContext()
						  .redirect("/payRoll/login.jsf");
				      }
			  else if (this.operateur != null) {
				  this.fonction = FichierBaseDAO.getInstance()
						.getFonctionActive(this.operateur.getIdEmploye());
				if (fonction != null) {
					  this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(),
							  Constante.Role.validationProlongation);
					     
					  if (this.droit == null) {
						  this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext()
							 .redirect("/payRoll/messages.jsf");
						      }
					      } else {
					this.session.setAttribute("msg", "droit");
					FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				      }
			     
			  } catch (IOException e) {
			     
			  System.out.println(e.getMessage());
			      }
		      }
	      public void demandeAnticipeRetraite() {
		      try {
			  if (this.operateur == null) {
				  FacesContext.getCurrentInstance().getExternalContext()
						  .redirect("/payRoll/login.jsf");
				      }
			  else if (this.operateur != null) {
				  this.fonction = FichierBaseDAO.getInstance()
						.getFonctionActive(this.operateur.getIdEmploye());
				if (fonction != null) {
					  this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(),
							  Constante.Role.demandeRetraiteAnticipe);
					     
					  if (this.droit == null) {
						  this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext()
							 .redirect("/payRoll/messages.jsf");
						      }
					      } else {
					this.session.setAttribute("msg", "droit");
					FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				      }
			     
			  } catch (IOException e) {
			     
			  System.out.println(e.getMessage());
			      }
		      }
	      public void validerAnticipeRetraite() {
		      try {
			  if (this.operateur == null) {
				  FacesContext.getCurrentInstance().getExternalContext()
						  .redirect("/payRoll/login.jsf");
				      }
			  else if (this.operateur != null) {
				  this.fonction = FichierBaseDAO.getInstance()
						.getFonctionActive(this.operateur.getIdEmploye());
				if (fonction != null) {
					  this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(),
							  Constante.Role.validationRetraiteAnticipe);
					     
					  if (this.droit == null) {
						  this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext()
							 .redirect("/payRoll/messages.jsf");
						      }
					      } else {
					this.session.setAttribute("msg", "droit");
					FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				      }
			     
			  } catch (IOException e) {
			     
			  System.out.println(e.getMessage());
			      }
		      }
	      
	      public void gestionRetraite() {
		      try {
			  if (this.operateur == null) {
				  FacesContext.getCurrentInstance().getExternalContext()
						  .redirect("/payRoll/login.jsf");
				      }
			  else if (this.operateur != null) {
				  this.fonction = FichierBaseDAO.getInstance()
						.getFonctionActive(this.operateur.getIdEmploye());
				if (fonction != null) {
					  this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(),
							  Constante.Role.finCarriere);
					     
					  if (this.droit == null) {
						  this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext()
							 .redirect("/payRoll/messages.jsf");
						      }
					      } else {
					this.session.setAttribute("msg", "droit");
					FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				      }
			     
			  } catch (IOException e) {
			     
			  System.out.println(e.getMessage());
			      }
		      }
	      
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void BulletinPaie() {
/*     */     try {
/* 442 */       if (this.operateur == null) {
/* 443 */         FacesContext.getCurrentInstance().getExternalContext()
/* 444 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/*     */         
/* 447 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 448 */             this.operateur.getIdEmploye());
				 if(fonction!=null) {
/* 449 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 450 */             Constante.Role.bulletinPaie);
/* 451 */         if (this.droit == null) {
/* 452 */           this.session.setAttribute("msg", "droit");
/* 453 */           FacesContext.getCurrentInstance().getExternalContext()
/* 454 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       }
				 else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
/*     */       }
/*     */     
/* 459 */     } catch (IOException e) {
/*     */       
/* 461 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void premierEvaluation() {
/*     */     try {
/* 469 */       if (this.operateur == null) {
/*     */         
/* 471 */         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 476 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 477 */             this.operateur.getIdEmploye());
/*     */         if(fonction!=null) {
/* 479 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 480 */             Constante.Role.premiereEvaluation);
/*     */         
/* 482 */         if (this.droit == null) {
/* 483 */           this.session.setAttribute("msg", "droit");
/* 484 */           FacesContext.getCurrentInstance().getExternalContext()
/* 485 */             .redirect("/payRoll/messages.jsf");
/*     */         } else {
/*     */           
/* 488 */           this.employe = FactoryDAO.getInstance().getUser(
/* 489 */               this.operateur.getIdEmploye(), false);
/*     */           
/* 491 */           if (this.fonction != null && this.employe != null)
/*     */           {
/* 493 */             infoMessage = 
/* 494 */               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.premiereEvaluation);
/* 495 */             this.operation = 
/* 496 */               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.premiereEvaluation);
/*     */             
/* 498 */             accesFonction();
/*     */           }
/*     */         
/*     */         }
/*     */       }

			else {
				this.session.setAttribute("msg", "droit");
				 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
			}
/*     */       } 
/* 504 */     } catch (IOException e) {
/*     */       
/* 506 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void deuxiemeEvaluation() {
/*     */     try {
/* 514 */       if (this.operateur == null) {
/*     */         
/* 516 */         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 521 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 522 */             this.operateur.getIdEmploye());
/*     */          if(fonction!=null) {
/* 524 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 525 */             Constante.Role.deuxiemeEvaluation);
/*     */         
/* 527 */         if (this.droit == null) {
/* 528 */           this.session.setAttribute("msg", "droit");
/* 529 */           FacesContext.getCurrentInstance().getExternalContext()
/* 530 */             .redirect("/payRoll/messages.jsf");
/*     */         } else {
/*     */           
/* 533 */           this.employe = FactoryDAO.getInstance().getUser(
/* 534 */               this.operateur.getIdEmploye(), false);
/*     */           
/* 536 */           if (this.fonction != null && this.employe != null)
/*     */           {
/* 538 */             infoMessage = 
/* 539 */               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.deuxiemeEvaluation);
/* 540 */             this.operation = 
/* 541 */               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.deuxiemeEvaluation);
/*     */             
/* 543 */             accesFonction();
/*     */           }
/*     */         }
/*     */         } 
			else {
				this.session.setAttribute("msg", "droit");
				 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
			}
/*     */       } 
/* 548 */     } catch (IOException e) {
/*     */       
/* 550 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void TroisiemeEvaluation() {
/*     */     try {
/* 557 */       if (this.operateur == null) {
/* 558 */         FacesContext.getCurrentInstance().getExternalContext()
/* 559 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/*     */         
/* 562 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 563 */             this.operateur.getIdEmploye());
/*     */         if(fonction!=null) {
/* 565 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 566 */             Constante.Role.troisiemeEvaluation);
/*     */         
/* 568 */         if (this.droit == null) {
/* 569 */           this.session.setAttribute("msg", "droit");
/* 570 */           FacesContext.getCurrentInstance().getExternalContext()
/* 571 */             .redirect("/payRoll/messages.jsf");
/*     */         } else {
/* 573 */           this.employe = FactoryDAO.getInstance().getUser(
/* 574 */               this.operateur.getIdEmploye(), false);
/*     */           
/* 576 */           if (this.fonction != null && this.employe != null) {
/*     */             
/* 578 */             infoMessage = 
/* 579 */               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.troisiemeEvaluation);
/* 580 */             this.operation = 
/* 581 */               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.troisiemeEvaluation);
/*     */             
/* 583 */             accesFonction();
/*     */           } 
/*     */         } 
					}else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
/*     */       } 
/* 587 */     } catch (IOException e) {
/* 588 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void demandeAvancement() {
/*     */     try {
/* 597 */       if (this.operateur == null) {
/*     */         
/* 599 */         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 604 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 605 */             this.operateur.getIdEmploye());
/*     */         if(fonction!=null) {
/* 607 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 608 */             Constante.Role.gestionPosition);
/*     */         
/* 610 */         if (this.droit == null) {
/* 611 */           this.session.setAttribute("msg", "droit");
/* 612 */           FacesContext.getCurrentInstance().getExternalContext()
/* 613 */             .redirect("/payRoll/messages.jsf");
/*     */         } else {
/*     */           
/* 616 */           this.employe = FactoryDAO.getInstance().getUser(
/* 617 */               this.operateur.getIdEmploye(), false);
/*     */           
/* 619 */           if (this.fonction != null && this.employe != null)
/*     */           {
/* 621 */             infoMessage = 
/* 622 */               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.demandeAvancement);
/* 623 */             this.operation = 
/* 624 */               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.demandeAvancement);
/*     */             
/* 626 */             accesFonction();
/*     */           }
					}
/*     */         
/*     */         }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
/*     */       } 
/* 631 */     } catch (IOException e) {
/*     */       
/* 633 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ValidationAvancementGrade() {
/*     */     try {
/* 642 */       if (this.operateur == null) {
/*     */         
/* 644 */         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 649 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 650 */             this.operateur.getIdEmploye());
/*     */         if(fonction!=null) {
/* 652 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 653 */             Constante.Role.gestionPosition);
/*     */         
/* 655 */         if (this.droit == null) {
/* 656 */           this.session.setAttribute("msg", "droit");
/* 657 */           FacesContext.getCurrentInstance().getExternalContext()
/* 658 */             .redirect("/payRoll/messages.jsf");
/*     */         } else {
/*     */           
/* 661 */           this.employe = FactoryDAO.getInstance().getUser(
/* 662 */               this.operateur.getIdEmploye(), false);
/*     */           
/* 664 */           if (this.fonction != null && this.employe != null)
/*     */           {
/* 666 */             infoMessage = 
/* 667 */               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.changementGrade);
/* 668 */             this.operation = 
/* 669 */               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.changementGrade);
/*     */             
/* 671 */             accesFonction();
/*     */           }
/*     */         
/*     */         }
/*     */       }
			else {
				this.session.setAttribute("msg", "droit");
				 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
			}
/*     */       } 
/* 677 */     } catch (IOException e) {
/*     */       
/* 679 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void SaisieSanction() {
/*     */     try {
/* 688 */       if (this.operateur == null) {
/*     */         
/* 690 */         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 695 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 696 */             this.operateur.getIdEmploye());
/*     */         if(fonction!=null) {
/* 698 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 699 */             Constante.Role.saisieSanction);
/*     */         
/* 701 */         if (this.droit == null) {
/* 702 */           this.session.setAttribute("msg", "droit");
/* 703 */           FacesContext.getCurrentInstance().getExternalContext()
/* 704 */             .redirect("/payRoll/messages.jsf");
/*     */         } else {
/*     */           
/* 707 */           this.employe = FactoryDAO.getInstance().getUser(
/* 708 */               this.operateur.getIdEmploye(), false);
/*     */           
/* 710 */           if (this.fonction != null && this.employe != null)
/*     */           {
/* 712 */             infoMessage = 
/* 713 */               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.saisieSanction);
/* 714 */             this.operation = 
/* 715 */               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.saisieSanction);
/*     */             
/* 717 */             accesFonction();
/*     */           }
/*     */         
/*     */         }
				}else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
/*     */       
/*     */       } 
/* 723 */     } catch (IOException e) {
/*     */       
/* 725 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void SaisieRecours() {
/*     */     try {
/* 734 */       if (this.operateur == null) {
/*     */         
/* 736 */         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 741 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 742 */             this.operateur.getIdEmploye());
/*     */         if(fonction!=null) {
/* 744 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 745 */             Constante.Role.saisieRecourSanction);
/*     */         
/* 747 */         if (this.droit == null) {
/* 748 */           this.session.setAttribute("msg", "droit");
/* 749 */           FacesContext.getCurrentInstance().getExternalContext()
/* 750 */             .redirect("/payRoll/messages.jsf");
/*     */         } else {
/*     */           
/* 753 */           this.employe = FactoryDAO.getInstance().getUser(
/* 754 */               this.operateur.getIdEmploye(), false);
/*     */           
/* 756 */           if (this.fonction != null && this.employe != null)
/*     */           {
/* 758 */             infoMessage = 
/* 759 */               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.recoursSanction);
/* 760 */             this.operation = 
/* 761 */               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.recoursSanction);
/*     */             
/* 763 */             accesFonction();
/*     */           }
/*     */         
/*     */         }
/*     */       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
/*     */       } 
/* 769 */     } catch (IOException e) {
/*     */       
/* 771 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validerRecours() {
/*     */     try {
/* 780 */       if (this.operateur == null)
/*     */       {
/* 782 */         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 787 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 788 */             this.operateur.getIdEmploye());
/*     */         if(fonction!=null) {
/* 790 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 791 */             Constante.Role.traitementRecours);
/*     */         
/* 793 */         if (this.droit == null) {
/* 794 */           this.session.setAttribute("msg", "droit");
/* 795 */           FacesContext.getCurrentInstance().getExternalContext()
/* 796 */             .redirect("/payRoll/messages.jsf");
/*     */         } else {
/*     */           
/* 799 */           this.employe = FactoryDAO.getInstance().getUser(
/* 800 */               this.operateur.getIdEmploye(), false);
/*     */           
/* 802 */           if (this.fonction != null && this.employe != null)
/*     */           {
/* 804 */             infoMessage = 
/* 805 */               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.validationRecours);
/* 806 */             this.operation = 
/* 807 */               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.validationRecours);
/*     */           }
/*     */         
/*     */         }
/*     */       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
/*     */       }
/*     */     
/*     */     }
/* 815 */     catch (IOException e) {
/*     */       
/* 817 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parametrageSanction() {
/*     */     try {
/* 826 */       if (this.operateur == null) {
/* 827 */         FacesContext.getCurrentInstance().getExternalContext()
/* 828 */           .redirect("/payRoll/login.jsf");
/*     */       } else {
/*     */         
/* 831 */         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
/* 832 */             this.operateur.getIdEmploye());
					if(fonction!=null) {
/* 833 */         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
/* 834 */             Constante.Role.saisieSanction);
/* 835 */         if (this.droit == null) {
/* 836 */           this.session.setAttribute("msg", "droit");
/* 837 */           FacesContext.getCurrentInstance().getExternalContext()
/* 838 */             .redirect("/payRoll/messages.jsf");
/*     */         }
/*     */       }
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
/*     */       }
/*     */     
/* 843 */     } catch (IOException e) {
/*     */       
/* 845 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void accesFonction() {
/* 852 */     if (this.employe != null) {
/* 853 */       this.grade = FactoryDAO.getInstance()
/* 854 */         .getDetailGradeParEmploye(this.employe);
/*     */       

/* 860 */       if (this.employe.getDirectionUb() != null) {
/* 861 */         this.idDirectionUb = this.employe.getDirectionUb()
/* 862 */           .getId();
/*     */       }
/* 864 */       if (this.employe.getDirection() != null) {
/* 865 */         this.direction = this.employe.getDirection().getId();
/*     */       }
/* 867 */       if (this.employe.getService() != null) {
/* 868 */         this.service = this.employe.getService().getId();
/*     */       }
/* 870 */       if (this.employe.getSousService() != null) {
/* 871 */         this.sousService = this.employe.getSousService().getId();
/*     */       }
/* 873 */       this.idFonction = this.fonction.getId();
/*     */     } 

/* 891 */     this.idPersonel = 0;
/* 892 */     this.operation = 0;
/* 893 */     this.idDirectionUb = 0;
/* 894 */     this.direction = 0;
/* 895 */     this.service = 0;
/* 896 */     this.sousService = 0;
/* 897 */     this.idFonction = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String Operation() {
/* 903 */     return infoMessage;
/*     */   }
/*     */ }
