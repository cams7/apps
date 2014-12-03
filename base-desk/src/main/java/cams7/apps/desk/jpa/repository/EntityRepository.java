/**
 * 
 */
package cams7.apps.desk.jpa.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cams7.apps.jpa.domain.BaseEntity;
import cams7.apps.jpa.repository.BaseRepository;

/**
 * @author cesar
 *
 */
public interface EntityRepository<E extends BaseEntity<ID>, ID extends Serializable>
		extends PagingAndSortingRepository<E, ID>, BaseRepository<E, ID> {

	public Page<E> findAllOrdered(Pageable pageable);

	public Page<E> findBySearch(String searchText, Pageable pageable);

}
