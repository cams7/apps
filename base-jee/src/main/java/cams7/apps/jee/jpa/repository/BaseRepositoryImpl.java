package cams7.apps.jee.jpa.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cams7.apps.jee.AbstractBase;
import cams7.apps.jpa.domain.BaseEntity;
import cams7.apps.util.ApplicationUtil;

/**
 * Classe resolve os métodos básicos de cadastro (CRUD) com API da
 * <code>JPA</code>.
 * 
 * @author YaW Tecnologia
 */
public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> implements BaseRepository<E, ID> {

	/**
	 * Classe da entidade, necessário para o método
	 * <code>EntityManager.find</code>.
	 */
	private Class<E> entityType;

	@SuppressWarnings("unchecked")
	public BaseRepositoryImpl() {
		super();
		entityType = (Class<E>) ApplicationUtil.getType(this,
				getEntityArgumentNumber());
	}

	public E save(E entity) {
		if (entity.getId() != null)
			return getEntityManager().merge(entity);

		getEntityManager().persist(entity);
		return entity;
	}

	public void delete(E entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public E findOne(ID id) {
		E entity = getEntityManager().find(getEntityType(), id);
		return entity;
	}

	public Iterable<E> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityType());

		Root<E> root = cq.from(getEntityType());
		cq.select(root);

		TypedQuery<E> query = getEntityManager().createQuery(cq);

		List<E> entities = query.getResultList();
		return entities;
	}

	public List<E> findRange(int[] range) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityType());

		Root<E> root = cq.from(getEntityType());
		cq.select(root);

		TypedQuery<E> query = getEntityManager().createQuery(cq);
		query.setMaxResults(range[1] - range[0]);
		query.setFirstResult(range[0]);

		List<E> entities = query.getResultList();
		return entities;
	}

	public long count() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<E> root = cq.from(getEntityType());
		cq.select(cb.count(root));

		TypedQuery<Long> query = getEntityManager().createQuery(cq);
		long count = query.getSingleResult();
		return count;
	}

	/**
	 * Exige a definição do <code>EntityManager</code> responsável pelas
	 * operações de persistência.
	 */
	protected abstract EntityManager getEntityManager();

	public Class<E> getEntityType() {
		return entityType;
	}

}
