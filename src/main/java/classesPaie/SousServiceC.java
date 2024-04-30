package classesPaie;

public class SousServiceC extends Base {
	private static final long serialVersionUID = 8480493834693360741L;
	private ServicesC service;
	private String typeSubService;

	public ServicesC getService() {
		return this.service;
	}

	public void setService(ServicesC service) {
		this.service = service;
	}

	public String getTypeSubService() {
		return this.typeSubService;
	}

	public void setTypeSubService(String typeSubService) {
		this.typeSubService = typeSubService;
	}
}
