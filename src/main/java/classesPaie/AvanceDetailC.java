 package classesPaie;
 
 import java.io.Serializable;
 
 
 public class AvanceDetailC
   implements Serializable
 {
   private static final long serialVersionUID = 1730582595801636760L;
   private int id;
   private int mois;
   private int idEntete;
   private double montant;
   private String printMois;
   private String printMontant;
   private boolean seleceted;
   private boolean disable = true;
   
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
   public int getMois() {
     return this.mois;
   }
   public void setMois(int mois) {
     this.mois = mois;
   }
   public double getMontant() {
     return this.montant;
   }
   public void setMontant(double montant) {
     this.montant = montant;
   }
   public int getIdEntete() {
     return this.idEntete;
   }
   public void setIdEntete(int idEntete) {
     this.idEntete = idEntete;
   }
   public String getPrintMois() {
     this.printMois = HelperC.moisEnLettres(getMois());
     return this.printMois;
   }
   public void setPrintMois(String printMois) {
     this.printMois = printMois;
   }
   public String getPrintMontant() {
     this.printMontant = HelperC.decimalNumber(getMontant(), 0, true);
     return this.printMontant;
   }
   public void setPrintMontant(String printMontant) {
     this.printMontant = printMontant;
   }
   public boolean isSeleceted() {
     return this.seleceted;
   }
   public void setSeleceted(boolean seleceted) {
     this.seleceted = seleceted;
   }
   public boolean isDisable() {
     return this.disable;
   }
   public void setDisable(boolean disable) {
     this.disable = disable;
   }
   
   public void checkDetail() {
     if (this.seleceted) {
       this.disable = false;
     } else {
       
       this.disable = true;
       this.montant = 0.0D;
     } 
   }
 }


