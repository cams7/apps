/**
 * 
 */
package cams7.apps.jpa.repository;

import java.io.Serializable;

import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> {

	public long countAll();

	public long countBySearch(String searchText);
}
