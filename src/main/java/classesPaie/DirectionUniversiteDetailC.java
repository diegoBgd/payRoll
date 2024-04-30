package classesPaie;

import java.io.Serializable;

public class DirectionUniversiteDetailC implements Serializable {
	private static final long serialVersionUID = -2966483665858264559L;
	private int id;
	private DirectionUniversiteC entete;
	private Base fonction;
	private boolean existe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DirectionUniversiteC getEntete() {
		return this.entete;
	}

	public void setEntete(DirectionUniversiteC entete) {
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
