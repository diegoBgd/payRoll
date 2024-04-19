package classesPaie;

import java.io.Serializable;
import java.util.Date;

public class EcritureComptableC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3592437138310981113L;

	private Date dateEcriture;
	private String libelle,piece,compte,codeJournal;
	private int idExercice;
	private double debit,credit;
	public Date getDateEcriture() {
		return dateEcriture;
	}
	public void setDateEcriture(Date dateEcriture) {
		this.dateEcriture = dateEcriture;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getPiece() {
		return piece;
	}
	public void setPiece(String piece) {
		this.piece = piece;
	}
	public String getCompte() {
		return compte;
	}
	public void setCompte(String compte) {
		this.compte = compte;
	}
	public String getCodeJournal() {
		return codeJournal;
	}
	public void setCodeJournal(String codeJournal) {
		this.codeJournal = codeJournal;
	}
	public int getIdExercice() {
		return idExercice;
	}
	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
}
