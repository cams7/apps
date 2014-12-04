/**
 * 
 */
package cams7.apps.jee.jpa.repository;

import java.io.Serializable;

import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> {

	public E save(E entity);

	public E findOne(ID id);

	public Iterable<E> findAll();

	public long count();

	public void delete(E entity);

	public Class<E> getEntityType();
}
