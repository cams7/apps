/**
 * 
 */
package cams7.apps.jee.service;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cams7.apps.jee.jpa.repository.BaseRepositoryImpl;
import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepositoryImpl<E, ID> implements BaseService<E, ID> {

	@PersistenceContext(unitName = "appSwingSpringUnit")
	private EntityManager entityManager;

	public BaseServiceImpl() {
		super();
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
