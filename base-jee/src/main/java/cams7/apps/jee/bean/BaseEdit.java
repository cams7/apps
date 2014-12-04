/**
 * 
 */
package cams7.apps.jee.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import cams7.apps.jee.service.BaseService;
import cams7.apps.jpa.domain.BaseEntity;
import cams7.apps.util.ApplicationException;
import cams7.apps.util.ApplicationUtil;

/**
 * @author cesar
 *
 */
public abstract class BaseEdit<S extends BaseService<E, ?>, E extends BaseEntity<?>>
		extends AbstractBean<E> {

	// @Inject
	@EJB
	private S service;

	@Inject
	private Event<E> entityEventSrc;

	private E newEntity;

	public E getNewEntity() {
		return newEntity;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initNewEntity() {
		Class<E> entityType = getService().getEntityType();

		try {
			newEntity = (E) ApplicationUtil
					.getNewEntity((Class<BaseEntity<?>>) entityType);
		} catch (ApplicationException e) {
			getLog().error(e.getMessage());
		}
	}

	public String register() throws Exception {
		newEntity = getService().save(newEntity);

		getLog().info("Registering " + newEntity.getId());

		getEntityEventSrc().fire(newEntity);

		initNewEntity();
		return null;
	}

	protected S getService() {
		return service;
	}

	protected Event<E> getEntityEventSrc() {
		return entityEventSrc;
	}

}
