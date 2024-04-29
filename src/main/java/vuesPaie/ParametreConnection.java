 package vuesPaie;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.Serializable;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 
 @ManagedBean
 @ViewScoped
 public class ParametreConnection
   implements Serializable {
   private String nomUtilisateur;
   private String nomHost;
   private String nomBaseDeDonnee;
   private String motDePasse;
   private static final long serialVersionUID = 5659574157386814683L;
   
   public String getNomUtilisateur() {
     return this.nomUtilisateur;
   }
 
   
   public void setNomUtilisateur(String nomUtilisateur) {
     this.nomUtilisateur = nomUtilisateur;
   }
 
   
   public String getNomHost() {
     return this.nomHost;
   }
 
   
   public void setNomHost(String nomHost) {
     this.nomHost = nomHost;
   }
 
   
   public String getNomBaseDeDonnee() {
     return this.nomBaseDeDonnee;
   }
 
   
   public void setNomBaseDeDonnee(String nomBaseDeDonnee) {
     this.nomBaseDeDonnee = nomBaseDeDonnee;
   }
 
   
   public String getMotDePasse() {
     return this.motDePasse;
   }
 
   
   public void setMotDePasse(String motDePasse) {
     this.motDePasse = motDePasse;
   }
 
 
 
   
   @PostConstruct
   private void init() {}
 
 
 
   
   public void enregistrer() {
     if (!this.nomUtilisateur.equalsIgnoreCase("") || !this.motDePasse.equalsIgnoreCase("") || !this.nomHost.equalsIgnoreCase("") || !this.nomBaseDeDonnee.equalsIgnoreCase(""))
       
       try {
         
         String content = this.nomHost.trim() + " \n" + this.nomUtilisateur.trim() + "\n" + this.motDePasse.trim() + " \n" + this.nomBaseDeDonnee.trim();
         String path = "../resources/connectionBD.txt";
         File file = new File(path);
         if (!file.exists())
         {
           file.createNewFile();
         }
         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(content);
         bw.close();
       }
       catch (Exception e) {
         
         System.out.println(e);
       }  
   }
 }


