/*     */ package classesPaie;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ public class ParametreCotisationC
/*     */   implements Serializable
/*     */ {
/*  30 */   private List<CotisationDetailC> listDeleted = new ArrayList<CotisationDetailC>();
/*  31 */   private List<CotisationDetailC> listDetail = new ArrayList<CotisationDetailC>(); 
			private Date dateFinUtilisation; 
			private double baseFixe; 
			private double plancherBase; 
			private double plafondBase; 
			private double forfaitPatronal; 
			private double forfaitSalarial; 
			private double tauxPatronal; 
			private double tauxSalarial; 
			private double plafonPatronal;
			private double plafonSalarial;
            private double tauxPlafonSalarial,tauxPlafonPatronal;   
            private double plancherPatronal; 
		    private double plancherSalarial; 
		    private String libelle; 
		    private String code; 
		    private int priorite; 
		    private int typeBasePatronal; 
		    private int typeBaseSalarial; 
		    private int idCotisation; 
		    private int id; 
		    private static final long serialVersionUID = 2721587510844306357L;
		    
		    public List<CotisationDetailC> getListDetail() {
		    	return this.listDetail;
		    }
/*     */   public void setListDetail(List<CotisationDetailC> listDetail) {
/*  39 */     this.listDetail = listDetail;
/*     */   }
/*     */   
/*     */   public List<CotisationDetailC> getListDeleted() {
/*  43 */     return this.listDeleted;
/*     */   }
/*     */   
/*     */   public void setListDeleted(List<CotisationDetailC> listDeleted) {
/*  47 */     this.listDeleted = listDeleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFinUtilisation() {
/*  52 */     return this.dateFinUtilisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFinUtilisation(Date dateFinUtilisation) {
/*  57 */     this.dateFinUtilisation = dateFinUtilisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  62 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  67 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getIdCotisation() {
/*  71 */     return this.idCotisation;
/*     */   }
/*     */   
/*     */   public void setIdCotisation(int idCotisation) {
/*  75 */     this.idCotisation = idCotisation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxSalarial() {
/*  80 */     return this.tauxSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxSalarial(double tauxSalarial) {
/*  85 */     this.tauxSalarial = tauxSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTauxPatronal() {
/*  90 */     return this.tauxPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTauxPatronal(double tauxPatronal) {
/*  95 */     this.tauxPatronal = tauxPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getForfaitSalarial() {
/* 100 */     return this.forfaitSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForfaitSalarial(double forfaitSalarial) {
/* 105 */     this.forfaitSalarial = forfaitSalarial;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getForfaitPatronal() {
/* 110 */     return this.forfaitPatronal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForfaitPatronal(double forfaitPatronal) {
/* 115 */     this.forfaitPatronal = forfaitPatronal;
/*     */   }
/*     */   
/*     */   public double getPlancherSalarial() {
/* 119 */     return this.plancherSalarial;
/*     */   }
/*     */   
/*     */   public void setPlancherSalarial(double plancherSalarial) {
/* 123 */     this.plancherSalarial = plancherSalarial;
/*     */   }
/*     */   
/*     */   public double getPlancherPatronal() {
/* 127 */     return this.plancherPatronal;
/*     */   }
/*     */   
/*     */   public void setPlancherPatronal(double plancherPatronal) {
/* 131 */     this.plancherPatronal = plancherPatronal;
/*     */   }
/*     */   
/*     */   public double getPlafonSalarial() {
/* 135 */     return this.plafonSalarial;
/*     */   }
/*     */   
/*     */   public void setPlafonSalarial(double plafonSalarial) {
/* 139 */     this.plafonSalarial = plafonSalarial;
/*     */   }
/*     */   
/*     */   public double getPlafonPatronal() {
/* 143 */     return this.plafonPatronal;
/*     */   }
/*     */   
/*     */   public void setPlafonPatronal(double plafonPatronal) {
/* 147 */     this.plafonPatronal = plafonPatronal;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 151 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 155 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getLibelle() {
/* 159 */     return this.libelle;
/*     */   }
/*     */   
/*     */   public void setLibelle(String libelle) {
/* 163 */     this.libelle = libelle;
/*     */   }
/*     */   
/*     */   public int getTypeBaseSalarial() {
/* 167 */     return this.typeBaseSalarial;
/*     */   }
/*     */   
/*     */   public void setTypeBaseSalarial(int typeBaseSalarial) {
/* 171 */     this.typeBaseSalarial = typeBaseSalarial;
/*     */   }
/*     */   
/*     */   public int getTypeBasePatronal() {
/* 175 */     return this.typeBasePatronal;
/*     */   }
/*     */   
/*     */   public void setTypeBasePatronal(int typeBasePatronal) {
/* 179 */     this.typeBasePatronal = typeBasePatronal;
/*     */   }
/*     */   
/*     */   public int getPriorite() {
/* 183 */     return this.priorite;
/*     */   }
/*     */   
/*     */   public void setPriorite(int priorite) {
/* 187 */     this.priorite = priorite;
/*     */   }
/*     */   
/*     */   public double getPlafondBase() {
/* 191 */     return this.plafondBase;
/*     */   }
/*     */   
/*     */   public void setPlafondBase(double plafondBase) {
/* 195 */     this.plafondBase = plafondBase;
/*     */   }
/*     */   
/*     */   public double getPlancherBase() {
/* 199 */     return this.plancherBase;
/*     */   }
/*     */   
/*     */   public void setPlancherBase(double plancherBase) {
/* 203 */     this.plancherBase = plancherBase;
/*     */   }
/*     */   
/*     */   public double getBaseFixe() {
/* 207 */     return this.baseFixe;
/*     */   }
/*     */   
/*     */   public void setBaseFixe(double baseFixe) {
/* 211 */     this.baseFixe = baseFixe;
/*     */   }
			public double getTauxPlafonSalarial() {
				return tauxPlafonSalarial;
			}
			public void setTauxPlafonSalarial(double tauxPlafonSalarial) {
				this.tauxPlafonSalarial = tauxPlafonSalarial;
			}
			public double getTauxPlafonPatronal() {
				return tauxPlafonPatronal;
			}
			public void setTauxPlafonPatronal(double tauxPlafonPatronal) {
				this.tauxPlafonPatronal = tauxPlafonPatronal;
			}
			
			public void changePriority(){
				if(getPriorite()>0)
					setPriorite(priorite);
				System.out.println(getPriorite());
			}

/*     */ }

