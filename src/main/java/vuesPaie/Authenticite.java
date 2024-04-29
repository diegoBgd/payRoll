 package vuesPaie;
 
 import classesPaie.Base;
 import classesPaie.CategoriePersonnelC;
 import classesPaie.Constante;
 import classesPaie.DetailGradeC;
 import classesPaie.DroitC;
 import classesPaie.EmployeC;
 import classesPaie.HelperC;
 import classesPaie.OperateurC;
 import classesPaie.ParametrageDecideurSignataireC;
 import java.io.IOException;
 import java.io.Serializable;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import javax.faces.context.FacesContext;
 import javax.servlet.http.HttpSession;
 import persistencePaie.FactoryDAO;
 import persistencePaie.FichierBaseDAO;

 @ManagedBean
 @ViewScoped
 public class Authenticite
   implements Serializable
 {
   private static final long serialVersionUID = 9223372036854775000L;
   private OperateurC operateur;
   private Base fonction;
   private DroitC droit;

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
   int operation;
   int idDirectionUb;
   
            public Authenticite() 
            {
            	 FacesContext context = FacesContext.getCurrentInstance();
                 session = (HttpSession)context.getExternalContext().getSession(true);
            	
			}
   public Base getFonction() {
     return this.fonction;
   }
   
   public void setFonction(Base fonction) {
     this.fonction = fonction;
   }
 
   
   public boolean isUserExest() {
     return this.userExest;
   }
 
   
   public void setUserExest(boolean userExest) {
     this.userExest = userExest;
   }
   
   public ParametrageDecideurSignataireC getSignataire() {
     return this.signataire;
   }
   
   public void setSignataire(ParametrageDecideurSignataireC signataire) {
     this.signataire = signataire;
   }
 
   
   public void message() {
     String _msg = (String)this.session.getAttribute("msg");
     if (_msg != null && !_msg.equals(""))
     {
       if (_msg.trim().equals("droit")) {
         
         HelperC.afficherAttention("ATTENTION", "Vous n'avez pas le droit d'accéder à ce menu!");
         this.session.setAttribute("msg", null);
       } else {
         
         _msg.trim().equals("");
       } 
     }
   }
 
   
   @PostConstruct
   public void initialize() {
     String exist = null;

     this.codeOperateur = (String)this.session.getAttribute("operateur");
     if (this.session.getAttribute("existUser") != null) {
       exist = this.session.getAttribute("existUser").toString();
     }
     if (exist != null) {
       if (exist.equals("true")) {
         this.userExest = true;
       } else {
         this.userExest = false;
       } 
     }
     
     if (this.codeOperateur != null) {
       this.operateur = FichierBaseDAO.getInstance()
         .getOperateur(this.codeOperateur);
     }
   }
 
 
 
   
   public void OrganisationAcademique() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
				if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.organisationAcademique);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       
       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
				}

     
     } catch (IOException e) {
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void structureAdministrative() {
     try {
       if (this.userExest) {
         if (this.operateur == null) {
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/login.jsf");
         } else {
           this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
               this.operateur.getIdEmploye());
					if(fonction!=null) {
           this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
               Constante.Role.structureAdministrative);
           if (this.droit == null) {
             this.session.setAttribute("msg", "droit");
             FacesContext.getCurrentInstance().getExternalContext()
               .redirect("/payRoll/messages.jsf");
           }
         }
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
						
         }
       
       }
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void habilitation() {
     try {
       if (this.userExest)
       {
         if (this.operateur == null)
         {
           FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
         
         }
         else
         {
           this.fonction = FichierBaseDAO.getInstance().getFonctionActive(this.operateur.getIdEmploye());
           this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), Constante.Role.parametrage);
           if (this.droit == null)
           {
             this.session.setAttribute("msg", "droit");
             FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
           }
         
         }
       
       }
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void fichiersBase() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
                 if(fonction!=null){
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.fichierBase);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       }
                 else{
                	 this.session.setAttribute("msg", "droit");
                       FacesContext.getCurrentInstance().getExternalContext()
                	         .redirect("/payRoll/messages.jsf");
                 }
       }
     
     } catch (IOException e) {
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void parametrage() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
			 	 if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.parametrage);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       
       }
			 	else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
     
     }
}
     catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
   
   public void elementsPaie() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
					if(fonction!=null)
					{
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.elementPaie);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       
       } 
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
				}
     } catch (IOException e) {
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void CreditEmploye() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
				if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.creditAvance);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
       } 
     } catch (IOException e) {
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void profilEmploye() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
				  if(fonction!=null)
				  {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.profilEmploye);

         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       
       }
				  else {
					  this.session.setAttribute("msg", "droit");
					  FacesContext.getCurrentInstance().getExternalContext()
					  .redirect("/payRoll/messages.jsf");
				  }
				}
     
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void gestionPosition() {
     try {
       if (this.operateur != null) {
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
					if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.gestionPosition);
					
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } 
       }
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
				}
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
 
   
   public void gestionAbsencePresence() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
				if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.gestionAbsencePresence);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
       }
     
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void gestionConge() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
					if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.gestionConge);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       }
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
       }
     
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
        
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
	      
 
 
 
   
   public void BulletinPaie() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
				 if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.bulletinPaie);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       }
				 else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
       }
     
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void premierEvaluation() {
     try {
       if (this.operateur == null) {
         
         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
       
       }
       else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
         if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.premiereEvaluation);
         
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } else {
           
           this.employe = FactoryDAO.getInstance().getUser(
               this.operateur.getIdEmploye(), false);
           
           if (this.fonction != null && this.employe != null)
           {
             infoMessage = 
               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.premiereEvaluation);
             this.operation = 
               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.premiereEvaluation);
             
             accesFonction();
           }
         
         }
       }

			else {
				this.session.setAttribute("msg", "droit");
				 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
			}
       } 
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   public void deuxiemeEvaluation() {
     try {
       if (this.operateur == null) {
         
         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
       
       }
       else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
          if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.deuxiemeEvaluation);
         
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } else {
           
           this.employe = FactoryDAO.getInstance().getUser(
               this.operateur.getIdEmploye(), false);
           
           if (this.fonction != null && this.employe != null)
           {
             infoMessage = 
               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.deuxiemeEvaluation);
             this.operation = 
               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.deuxiemeEvaluation);
             
             accesFonction();
           }
         }
         } 
			else {
				this.session.setAttribute("msg", "droit");
				 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
			}
       } 
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
   
   public void TroisiemeEvaluation() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
         if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.troisiemeEvaluation);
         
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } else {
           this.employe = FactoryDAO.getInstance().getUser(
               this.operateur.getIdEmploye(), false);
           
           if (this.fonction != null && this.employe != null) {
             
             infoMessage = 
               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.troisiemeEvaluation);
             this.operation = 
               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.troisiemeEvaluation);
             
             accesFonction();
           } 
         } 
					}else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
       } 
     } catch (IOException e) {
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void demandeAvancement() {
     try {
       if (this.operateur == null) {
         
         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
       
       }
       else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
         if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.gestionPosition);
         
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } else {
           
           this.employe = FactoryDAO.getInstance().getUser(
               this.operateur.getIdEmploye(), false);
           
           if (this.fonction != null && this.employe != null)
           {
             infoMessage = 
               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.demandeAvancement);
             this.operation = 
               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.demandeAvancement);
             
             accesFonction();
           }
					}
         
         }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
       } 
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void ValidationAvancementGrade() {
     try {
       if (this.operateur == null) {
         
         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
       
       }
       else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
         if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.gestionPosition);
         
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } else {
           
           this.employe = FactoryDAO.getInstance().getUser(
               this.operateur.getIdEmploye(), false);
           
           if (this.fonction != null && this.employe != null)
           {
             infoMessage = 
               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.changementGrade);
             this.operation = 
               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.changementGrade);
             
             accesFonction();
           }
         
         }
       }
			else {
				this.session.setAttribute("msg", "droit");
				 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
			}
       } 
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void SaisieSanction() {
     try {
       if (this.operateur == null) {
         
         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
       
       }
       else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
         if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.saisieSanction);
         
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } else {
           
           this.employe = FactoryDAO.getInstance().getUser(
               this.operateur.getIdEmploye(), false);
           
           if (this.fonction != null && this.employe != null)
           {
             infoMessage = 
               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.saisieSanction);
             this.operation = 
               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.saisieSanction);
             
             accesFonction();
           }
         
         }
				}else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
       
       } 
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void SaisieRecours() {
     try {
       if (this.operateur == null) {
         
         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
       
       }
       else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
         if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.saisieRecourSanction);
         
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } else {
           
           this.employe = FactoryDAO.getInstance().getUser(
               this.operateur.getIdEmploye(), false);
           
           if (this.fonction != null && this.employe != null)
           {
             infoMessage = 
               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.recoursSanction);
             this.operation = 
               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.recoursSanction);
             
             accesFonction();
           }
         
         }
       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
       } 
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void validerRecours() {
     try {
       if (this.operateur == null)
       {
         FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/login.jsf");
       
       }
       else
       {
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
         if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.traitementRecours);
         
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         } else {
           
           this.employe = FactoryDAO.getInstance().getUser(
               this.operateur.getIdEmploye(), false);
           
           if (this.fonction != null && this.employe != null)
           {
             infoMessage = 
               Constante.getLibelleTypeOperationDecideur(Constante.TypeOperationDecideur.validationRecours);
             this.operation = 
               Constante.getTypeOperationDecideur(Constante.TypeOperationDecideur.validationRecours);
           }
         
         }
       }
				else {
					this.session.setAttribute("msg", "droit");
					 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
				}
       }
     
     }
     catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
 
   
   public void parametrageSanction() {
     try {
       if (this.operateur == null) {
         FacesContext.getCurrentInstance().getExternalContext()
           .redirect("/payRoll/login.jsf");
       } else {
         
         this.fonction = FichierBaseDAO.getInstance().getFonctionActive(
             this.operateur.getIdEmploye());
					if(fonction!=null) {
         this.droit = FichierBaseDAO.getInstance().getDroit(this.fonction.getId(), 
             Constante.Role.saisieSanction);
         if (this.droit == null) {
           this.session.setAttribute("msg", "droit");
           FacesContext.getCurrentInstance().getExternalContext()
             .redirect("/payRoll/messages.jsf");
         }
       }
					else {
						this.session.setAttribute("msg", "droit");
						 FacesContext.getCurrentInstance().getExternalContext().redirect("/payRoll/messages.jsf");
					}
       }
     
     } catch (IOException e) {
       
       System.out.println(e.getMessage());
     } 
   }
 
 
   
   private void accesFonction() {
     if (this.employe != null) {
       this.grade = FactoryDAO.getInstance()
         .getDetailGradeParEmploye(this.employe);
       

       if (this.employe.getDirectionUb() != null) {
         this.idDirectionUb = this.employe.getDirectionUb()
           .getId();
       }
       if (this.employe.getDirection() != null) {
         this.direction = this.employe.getDirection().getId();
       }
       if (this.employe.getService() != null) {
         this.service = this.employe.getService().getId();
       }
       if (this.employe.getSousService() != null) {
         this.sousService = this.employe.getSousService().getId();
       }
       this.idFonction = this.fonction.getId();
     } 

     this.idPersonel = 0;
     this.operation = 0;
     this.idDirectionUb = 0;
     this.direction = 0;
     this.service = 0;
     this.sousService = 0;
     this.idFonction = 0;
   }
 
 
   
   public static String Operation() {
     return infoMessage;
   }
 }
