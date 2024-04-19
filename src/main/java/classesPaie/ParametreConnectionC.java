/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.Scanner;

		  import javax.faces.context.FacesContext;
		  import javax.servlet.ServletContext;
          
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParametreConnectionC
/*     */   implements Serializable
/*     */ {
/*     */   private String nomUtilisateur;
/*     */   private String nomHost;
/*     */   private String nomBaseDeDonnee;
/*     */   
/*     */   public ParametreConnectionC() {
/*  21 */     ServletContext servletContext = 
/*  22 */       (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
/*  23 */     this.url = servletContext.getRealPath("/resources/dbc/connectionBD.txt");
/*     */   }
/*     */   private String motDePasse; private static final long serialVersionUID = 5659574157386814683L; 
            private String content; private String url;
/*     */   
/*     */   public String getNomUtilisateur() {
/*  28 */     return this.nomUtilisateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomUtilisateur(String nomUtilisateur) {
/*  33 */     this.nomUtilisateur = nomUtilisateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomHost() {
/*  38 */     return this.nomHost;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomHost(String nomHost) {
/*  43 */     this.nomHost = nomHost;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomBaseDeDonnee() {
/*  48 */     return this.nomBaseDeDonnee;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNomBaseDeDonnee(String nomBaseDeDonnee) {
/*  53 */     this.nomBaseDeDonnee = nomBaseDeDonnee;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMotDePasse() {
/*  58 */     return this.motDePasse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMotDePasse(String motDePasse) {
/*  63 */     this.motDePasse = motDePasse;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getContent() {
/*  68 */     return this.content;
/*     */   }
/*     */   
/*     */   public void setContent(String content) {
/*  72 */     this.content = content;
/*     */   }
/*     */   
/*     */   public String getUrl() {
/*  76 */     return this.url;
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/*  80 */     this.url = url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String enregistrer() {
/*  87 */     String msg = "";
/*  88 */     if (!this.nomUtilisateur.equalsIgnoreCase("") && !this.motDePasse.equalsIgnoreCase("") && !this.nomHost.equalsIgnoreCase("") && !this.nomBaseDeDonnee.equalsIgnoreCase("")) {
/*     */       
/*     */       try {
/*     */         
/*  92 */         this.content = String.valueOf(this.nomHost.trim()) + " " + this.nomUtilisateur.trim() + " " + this.motDePasse.trim() + " " + this.nomBaseDeDonnee.trim();
/*     */         
/*  94 */         File file = new File(this.url);
/*  95 */         if (!file.exists())
/*  96 */           file.createNewFile(); 
/*  97 */         FileWriter fw = new FileWriter(file);
/*  98 */         BufferedWriter bw = new BufferedWriter(fw);
/*  99 */         bw.write(this.content);
/* 100 */         bw.close();
/* 101 */         msg = "Opération réussie !";
/*     */       }
/* 103 */       catch (Exception e) {
/*     */         
/* 105 */         msg = "Opération échouée=>" + e.getMessage();
/*     */       } 
/*     */     }
/* 108 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean readChaine() {
			boolean ch=false;
/*     */     try {
/* 114 */       File file = new File(this.url);
/* 115 */       this.content = "";
/*     */       
/* 117 */       if (file.exists())
/*     */       {
/*     */ 
/*     */         
/* 121 */         Scanner sc = new Scanner(file);
/*     */         
/* 123 */         if (sc.hasNextLine())
/*     */         {
/* 125 */           this.content = sc.nextLine();
/*     */         }
/* 127 */         String[] info = this.content.split(" ");
/* 128 */         setNomHost(info[0]);
/* 129 */         setNomUtilisateur(info[1]);
/* 130 */         setMotDePasse(info[2]);
/* 131 */         setNomBaseDeDonnee(info[3]);
				  ch=true;
/*     */       }
/*     */     
/*     */     }
/* 135 */     catch (IOException e) 
              {
				ch=false;
/* 136 */       e.printStackTrace();
/*     */     } 
				return ch;
/*     */   }
            
/*     */ }

