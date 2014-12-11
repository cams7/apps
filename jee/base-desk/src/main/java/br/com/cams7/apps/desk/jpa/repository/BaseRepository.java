/**
 * 
 */
package br.com.cams7.apps.desk.jpa.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable>
		extends PagingAndSortingRepository<E, ID> {

	public Page<E> findAllOrdered(Pageable pageable);

	public Page<E> findBySearch(String searchText, Pageable pageable);

	public long countAll();

	public long countBySearch(String searchText);

}
