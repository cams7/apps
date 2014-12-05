/**
 * 
 */
package cams7.apps.jee.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import cams7.apps.jee.service.BaseService;
import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public abstract class BaseListProducer<S extends BaseService<E, ?>, E extends BaseEntity<?>>
		extends AbstractBean<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	// @Inject
	@EJB
	private S service;

	private List<E> entities;

	public BaseListProducer() {
		super();
	}

	// @Named provides access the return value via the EL variable name
	// "members" in the UI (e.g.,
	// Facelets or JSP view)
	public List<E> getEntities() {
		return entities;
	}

	@PostConstruct
	public void retrieveAll() {
		entities = (List<E>) getService().findAll();
	}

	public void onEntityListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final E entity) {
		retrieveAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.apps.jee.AbstractBase#getEntityArgumentNumber()
	 */
	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected S getService() {
		return service;
	}

}
