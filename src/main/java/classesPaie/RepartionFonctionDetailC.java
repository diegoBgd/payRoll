package classesPaie;

import java.io.Serializable;

public class RepartionFonctionDetailC implements Serializable {
	private static final long serialVersionUID = -7935871343828768491L;
	private int id;
	private RepartitionFonctionC entete;
	private Base fonction;
	private boolean existe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RepartitionFonctionC getEntete() {
		return this.entete;
	}

	public void setEntete(RepartitionFonctionC entete) {
		this.entete = entete;
	}

	public Base getFonction() {
		return this.fonction;
	}

	public void setFonction(Base fonction) {
		this.fonction = fonction;
	}

	public boolean isExiste() {
		return this.existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}
}
