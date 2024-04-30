 package classesPaie;
 
 import java.io.Serializable;
 
 
 
 
 
 
 
 
 
 
 public class PaysC
   implements Serializable
 {
   private static final long serialVersionUID = -6277739791403647026L;
   private int id;
   private String designation;
   private String continent;
   private String nationalite;
   private Historique historique;
   
   public int getId() {
     return this.id;
   }
 
   
   public void setId(int id) {
     this.id = id;
   }
 
   
   public String getDesignation() {
     return this.designation;
   }
 
   
   public void setDesignation(String designation) {
     this.designation = designation;
   }
 
   
   public String getContinent() {
     return this.continent;
   }
 
   
   public void setContinent(String continent) {
     this.continent = continent;
   }
 
   
   public String getNationalite() {
     return this.nationalite;
   }
 
   
   public void setNationalite(String nationalite) {
     this.nationalite = nationalite;
   }
 
   
   public Historique getHistorique() {
     return this.historique;
   }
 
   
   public void setHistorique(Historique historique) {
     this.historique = historique;
   }
 }


