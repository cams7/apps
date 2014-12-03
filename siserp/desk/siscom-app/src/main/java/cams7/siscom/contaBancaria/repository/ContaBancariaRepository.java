/**
 * 
 */
package cams7.siscom.contaBancaria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cams7.apps.desk.jpa.repository.EntityRepository;
import cams7.siscom.jpa.domain.entity.ContaBancariaEntity;

/**
 * @author cesar
 *
 */
public interface ContaBancariaRepository extends
		EntityRepository<ContaBancariaEntity, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cams7.jpa.repository.EntityRepository#findAllOrdered(org.springframework
	 * .data.domain.Pageable)
	 */
	@Query(value = "select c from ContaBancariaEntity c join fetch c.banco b order by b.nome asc", countQuery = "select COUNT(c) from ContaBancariaEntity c join c.banco b")
	@Override
	public Page<ContaBancariaEntity> findAllOrdered(Pageable pageable);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.jpa.repository.EntityRepository#findBySearch(java.lang.String,
	 * org.springframework.data.domain.Pageable)
	 */
	@Query(value = "select c from ContaBancariaEntity c join fetch c.banco b where b.nome like :banco order by b.nome asc", countQuery = "select COUNT(c) from ContaBancariaEntity c join c.banco b where b.nome like :banco")
	@Override
	public Page<ContaBancariaEntity> findBySearch(
			@Param("banco") String bancoNome, Pageable pageable);

	@Query("select COUNT(c) from ContaBancariaEntity c join c.banco b")
	@Override
	public long countAll();

	@Query("select COUNT(c) from ContaBancariaEntity c join c.banco b where b.nome like :banco")
	@Override
	public long countBySearch(@Param("banco") String bancoNome);

}
