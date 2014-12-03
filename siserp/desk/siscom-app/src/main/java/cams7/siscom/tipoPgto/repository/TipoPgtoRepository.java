/**
 * 
 */
package cams7.siscom.tipoPgto.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cams7.desk.jpa.repository.EntityRepository;
import cams7.siscom.jpa.domain.entity.TipoPgtoEntity;

/**
 * @author cesar
 *
 */
public interface TipoPgtoRepository extends
		EntityRepository<TipoPgtoEntity, Short> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cams7.jpa.repository.EntityRepository#findAllOrdered(org.springframework
	 * .data.domain.Pageable)
	 */
	@Query(value = "select t from TipoPgtoEntity t order by t.descricao asc", countQuery = "select COUNT(t) from TipoPgtoEntity t")
	@Override
	public Page<TipoPgtoEntity> findAllOrdered(Pageable pageable);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.jpa.repository.EntityRepository#findBySearch(java.lang.String,
	 * org.springframework.data.domain.Pageable)
	 */
	@Query(value = "select t from TipoPgtoEntity t where t.descricao like :descricao order by t.descricao asc", countQuery = "select COUNT(t) from TipoPgtoEntity t where t.descricao like :descricao")
	public Page<TipoPgtoEntity> findBySearch(
			@Param("descricao") String descricao, Pageable pageable);

	@Query("select COUNT(t) from TipoPgtoEntity t")
	@Override
	public long countAll();

	@Query("select COUNT(t) from TipoPgtoEntity t where t.descricao like :descricao")
	@Override
	public long countBySearch(@Param("descricao") String descricao);
}
