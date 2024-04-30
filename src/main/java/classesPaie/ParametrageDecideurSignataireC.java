package classesPaie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametrageDecideurSignataireC implements Serializable {
	private static final long serialVersionUID = -8411807025737927605L;
	private int id;
	private Historique historique;
	private Base personnel;
	private Base fonction;
	private Base directionUniversite;
	private DirectionC direction;
	private ServicesC service;
	private FaculteInstitutC faculte;
	private SousServiceC sousService;
	private int typeOperation;
	private List<ParametrageDecideurDetailC> listDetail = new ArrayList<ParametrageDecideurDetailC>();
	private List<ParametrageDecideurDetailC> listDeleted = new ArrayList<ParametrageDecideurDetailC>();

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

	public Base getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Base personnel) {
		this.personnel = personnel;
	}

	public Base getFonction() {
		return this.fonction;
	}

	public void setFonction(Base fonction) {
		this.fonction = fonction;
	}

	public Base getDirectionUniversite() {
		return this.directionUniversite;
	}

	public void setDirectionUniversite(Base directionUniversite) {
		this.directionUniversite = directionUniversite;
	}

	public DirectionC getDirection() {
		return this.direction;
	}

	public void setDirection(DirectionC direction) {
		this.direction = direction;
	}

	public ServicesC getService() {
		return this.service;
	}

	public void setService(ServicesC service) {
		this.service = service;
	}

	public FaculteInstitutC getFaculte() {
		return this.faculte;
	}

	public void setFaculte(FaculteInstitutC faculte) {
		this.faculte = faculte;
	}

	public SousServiceC getSousService() {
		return this.sousService;
	}

	public void setSousService(SousServiceC sousService) {
		this.sousService = sousService;
	}

	public List<ParametrageDecideurDetailC> getListDetail() {
		return this.listDetail;
	}

	public void setListDetail(List<ParametrageDecideurDetailC> listDetail) {
		this.listDetail = listDetail;
	}

	public List<ParametrageDecideurDetailC> getListDeleted() {
		return this.listDeleted;
	}

	public void setListDeleted(List<ParametrageDecideurDetailC> listDeleted) {
		this.listDeleted = listDeleted;
	}

	public int getTypeOperation() {
		return this.typeOperation;
	}

	public void setTypeOperation(int typeOperation) {
		this.typeOperation = typeOperation;
	}
}
