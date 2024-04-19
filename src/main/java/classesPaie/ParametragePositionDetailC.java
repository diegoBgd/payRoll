package classesPaie;

import java.io.Serializable;

public class ParametragePositionDetailC implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1004007018868583231L;
	private int id,idPrime,idParm,idEntete,index;
	private String libellePrime;
	public ParametragePositionDetailC() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPrime() {
		return idPrime;
	}

	public void setIdPrime(int idPrime) {
		this.idPrime = idPrime;
	}

	public int getIdParm() {
		return idParm;
	}

	public void setIdParm(int idParm) {
		this.idParm = idParm;
	}

	public int getIdEntete() {
		return idEntete;
	}

	public void setIdEntete(int idEntete) {
		this.idEntete = idEntete;
	}

	public String getLibellePrime() {
		return libellePrime;
	}

	public void setLibellePrime(String libellePrime) {
		this.libellePrime = libellePrime;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
