/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParametrageJournalDetailC
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3203711879871211729L;
/*     */   private int id;
/*     */   private int typeElement;
/*     */   private int idEntete;
/*     */   private int indexNum;
/*     */   private int nombrElement;
/*     */   private String titrElement;
/*     */   private String libelle;
/*  23 */   private List<ParametrageJournalElementC> listDetailElement = new ArrayList<ParametrageJournalElementC>();
/*  24 */   private List<ParametrageJournalElementC> liteDeletedElement = new ArrayList<ParametrageJournalElementC>();
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  29 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  34 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTypeElement() {
/*  39 */     return this.typeElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeElement(int typeElement) {
/*  44 */     this.typeElement = typeElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitrElement() {
/*  49 */     return this.titrElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitrElement(String titrElement) {
/*  54 */     this.titrElement = titrElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLibelle() {
/*  59 */     return this.libelle;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLibelle(String libelle) {
/*  64 */     this.libelle = libelle;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIdEntete() {
/*  69 */     return this.idEntete;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdEntete(int idEntete) {
/*  74 */     this.idEntete = idEntete;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexNum() {
/*  79 */     return this.indexNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexNum(int indexNum) {
/*  84 */     this.indexNum = indexNum;
/*     */   }
/*     */   
/*     */   public List<ParametrageJournalElementC> getListDetailElement() {
/*  88 */     return this.listDetailElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDetailElement(List<ParametrageJournalElementC> listDetailElement) {
/*  93 */     this.listDetailElement = listDetailElement;
/*     */   }
/*     */   
/*     */   public List<ParametrageJournalElementC> getLiteDeletedElement() {
/*  97 */     return this.liteDeletedElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLiteDeletedElement(List<ParametrageJournalElementC> liteDeletedElement) {
/* 102 */     this.liteDeletedElement = liteDeletedElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNombrElement() {
/* 107 */     this.nombrElement = getListDetailElement().size();
/* 108 */     return this.nombrElement;
/*     */   }
/*     */   
/*     */   public void setNombrElement(int nombrElement) {
/* 112 */     this.nombrElement = nombrElement;
/*     */   }
/*     */ }


