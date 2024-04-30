package classesPaie;

import java.io.Serializable;

public class ServiceDetailC implements Serializable {
	private static final long serialVersionUID = 1444496860828944730L;
	private int id;
	private ServicesC service;
	private Base fonction;
	private boolean existe;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ServicesC getService() {
		return this.service;
	}

	public void setService(ServicesC service) {
		this.service = service;
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
