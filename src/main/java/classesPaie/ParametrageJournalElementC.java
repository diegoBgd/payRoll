package classesPaie;

import java.io.Serializable;
import javax.faces.event.ValueChangeEvent;

public class ParametrageJournalElementC implements Serializable {
	private static final long serialVersionUID = 1958116012983966909L;
	private int id;
	private int idElement;
	private int idEntete;
	private int signe;
	private String libelleElment;
	private boolean added;
	int sign;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdElement() {
		return this.idElement;
	}

	public void setIdElement(int idElement) {
		this.idElement = idElement;
	}

	public int getIdEntete() {
		return this.idEntete;
	}

	public void setIdEntete(int idEntete) {
		this.idEntete = idEntete;
	}

	public String getLibelleElment() {
		return this.libelleElment;
	}

	public void setLibelleElment(String libelleElment) {
		this.libelleElment = libelleElment;
	}

	public boolean isAdded() {
		return this.added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}

	public int getSigne() {
		return this.signe;
	}

	public void setSigne(int signe) {
		this.signe = signe;
	}

	public void changeSigne(ValueChangeEvent event) {
		this.signe = ((Integer) event.getNewValue()).intValue();
	}
}
