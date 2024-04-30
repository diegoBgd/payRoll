 package classesPaie;
 
 import java.io.Serializable;
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class AvaliseurC
   implements Serializable
 {
   private static final long serialVersionUID = 8091634780255622680L;
   private int id;
   private int idEmploye;
   private int idCredit;
   private String nomAvaliseur;
   private String codeAvaliseur;
   private EmployeC employe;
   
   public int getId() {
     return this.id;
   }
 
   
   public void setId(int id) {
     this.id = id;
   }
 
   
   public int getIdEmploye() {
     return this.idEmploye;
   }
 
   
   public void setIdEmploye(int idEmploye) {
     this.idEmploye = idEmploye;
   }
 
   
   public String getNomAvaliseur() {
     return this.nomAvaliseur;
   }
 
   
   public void setNomAvaliseur(String nomAvaliseur) {
     this.nomAvaliseur = nomAvaliseur;
   }
 
   
   public String getCodeAvaliseur() {
     return this.codeAvaliseur;
   }
 
   
   public void setCodeAvaliseur(String codeAvaliseur) {
     this.codeAvaliseur = codeAvaliseur;
   }
 
   
   public EmployeC getEmploye() {
     return this.employe;
   }
 
   
   public void setEmploye(EmployeC employe) {
     this.employe = employe;
   }
 
   
   public int getIdCredit() {
     return this.idCredit;
   }
 
   
   public void setIdCredit(int idCredit) {
     this.idCredit = idCredit;
   }
 }


