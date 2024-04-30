package classesPaie;

import java.io.Serializable;

public class DirectionDetailC implements Serializable {
	private static final long serialVersionUID = 8759523713201701173L;
	private int id;
	private DirectionC direction;
	private Base fonction;
	private boolean existe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DirectionC getDirection() {
		return this.direction;
	}

	public void setDirection(DirectionC direction) {
		this.direction = direction;
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
