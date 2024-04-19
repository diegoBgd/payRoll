/*    */ package vuesPaie;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.Serializable;
/*    */ import javax.annotation.PostConstruct;
/*    */ import javax.faces.bean.ManagedBean;
/*    */ import javax.faces.bean.ViewScoped;
/*    */ 
/*    */ @ManagedBean
/*    */ @ViewScoped
/*    */ public class ParametreConnection
/*    */   implements Serializable {
/*    */   private String nomUtilisateur;
/*    */   private String nomHost;
/*    */   private String nomBaseDeDonnee;
/*    */   private String motDePasse;
/*    */   private static final long serialVersionUID = 5659574157386814683L;
/*    */   
/*    */   public String getNomUtilisateur() {
/* 22 */     return this.nomUtilisateur;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNomUtilisateur(String nomUtilisateur) {
/* 27 */     this.nomUtilisateur = nomUtilisateur;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomHost() {
/* 32 */     return this.nomHost;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNomHost(String nomHost) {
/* 37 */     this.nomHost = nomHost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomBaseDeDonnee() {
/* 42 */     return this.nomBaseDeDonnee;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNomBaseDeDonnee(String nomBaseDeDonnee) {
/* 47 */     this.nomBaseDeDonnee = nomBaseDeDonnee;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMotDePasse() {
/* 52 */     return this.motDePasse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMotDePasse(String motDePasse) {
/* 57 */     this.motDePasse = motDePasse;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PostConstruct
/*    */   private void init() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void enregistrer() {
/* 70 */     if (!this.nomUtilisateur.equalsIgnoreCase("") || !this.motDePasse.equalsIgnoreCase("") || !this.nomHost.equalsIgnoreCase("") || !this.nomBaseDeDonnee.equalsIgnoreCase(""))
/*    */       
/*    */       try {
/*    */         
/* 74 */         String content = this.nomHost.trim() + " \n" + this.nomUtilisateur.trim() + "\n" + this.motDePasse.trim() + " \n" + this.nomBaseDeDonnee.trim();
/* 75 */         String path = "../resources/connectionBD.txt";
/* 76 */         File file = new File(path);
/* 77 */         if (!file.exists())
/*    */         {
/* 79 */           file.createNewFile();
/*    */         }
/* 81 */         FileWriter fw = new FileWriter(file.getAbsoluteFile());
/* 82 */         BufferedWriter bw = new BufferedWriter(fw);
/* 83 */         bw.write(content);
/* 84 */         bw.close();
/*    */       }
/* 86 */       catch (Exception e) {
/*    */         
/* 88 */         System.out.println(e);
/*    */       }  
/*    */   }
/*    */ }


