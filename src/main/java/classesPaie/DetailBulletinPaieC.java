package classesPaie;

import java.io.Serializable;

public class DetailBulletinPaieC implements Serializable {
	private static final long serialVersionUID = 34340511368315678L;
	private int id;
	private Historique historique;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Historique getHistorique() {
		return this.historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
}
