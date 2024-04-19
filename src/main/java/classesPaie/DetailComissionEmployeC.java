package classesPaie;

public class DetailComissionEmployeC {

	private int id;
	private CotisationC comission;
	private double montant;
	private double taux;
	private boolean exist;
	private String montantS;
	private int idEmpl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CotisationC getComission() {
		return comission;
	}
	public void setComission(CotisationC comission) {
		this.comission = comission;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public String getMontantS() {
		montantS=HelperC.decimalNumber(getMontant(), 0, true);
		return montantS;
	}
	public void setMontantS(String montantS) {
		this.montantS = montantS;
	}
	public int getIdEmpl() {
		return idEmpl;
	}
	public void setIdEmpl(int idEmpl) {
		this.idEmpl = idEmpl;
	}
	
}
