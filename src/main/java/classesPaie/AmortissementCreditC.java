 package classesPaie;
 
 import java.io.Serializable;
 
 
 
 
 
 
 
 public class AmortissementCreditC
   implements Serializable
 {
   private static final long serialVersionUID = -4134389965197973655L;
   private String trancheRembourse;
   private String interet;
   private String capital;
   private String montantRestant;
   private String montantTaxe;
   private String dateRemboursement;
   private int numero;
   
   public String getTrancheRembourse() {
     return this.trancheRembourse;
   }
 
   
   public void setTrancheRembourse(String trancheRembourse) {
     this.trancheRembourse = trancheRembourse;
   }
 
   
   public String getInteret() {
     return this.interet;
   }
 
   
   public void setInteret(String interet) {
     this.interet = interet;
   }
 
   
   public String getCapital() {
     return this.capital;
   }
 
   
   public void setCapital(String capital) {
     this.capital = capital;
   }
 
   
   public String getMontantRestant() {
     return this.montantRestant;
   }
 
   
   public void setMontantRestant(String montantRestant) {
     this.montantRestant = montantRestant;
   }
 
   
   public String getMontantTaxe() {
     return this.montantTaxe;
   }
 
   
   public void setMontantTaxe(String montantTaxe) {
     this.montantTaxe = montantTaxe;
   }
 
   
   public String getDateRemboursement() {
     return this.dateRemboursement;
   }
 
   
   public void setDateRemboursement(String dateRemboursement) {
     this.dateRemboursement = dateRemboursement;
   }
 
   
   public int getNumero() {
     return this.numero;
   }
 
   
   public void setNumero(int numero) {
     this.numero = numero;
   }
 }


