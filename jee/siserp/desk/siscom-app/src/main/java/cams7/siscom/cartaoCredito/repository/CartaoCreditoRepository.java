package cams7.siscom.cartaoCredito.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cams7.apps.desk.jpa.repository.BaseRepository;
import cams7.siscom.jpa.domain.entity.CartaoCreditoEntity;

/**
 * Contrato de persistência para a entidade <code>CartaoCredito</code>.
 * 
 * <p>
 * Define as operações basicas de cadastro (CRUD), seguindo o design pattern
 * <code>Data Access Object</code>.
 * </p>
 * 
 * <p>
 * As operações básicas do cadastro são definidas indiretamente pela interface
 * pai, <code>JpaRepository</code>. O Spring Data JPA gera a classe (proxy) que
 * implementa essa interface. Dessa forma métodos especificos do cadastro, como
 * <code>getSearchEntities</code>, devem mapear a consulta <code>JPA-QL</code>.
 * </p>
 * 
 * @author cesar
 *
 */
public interface CartaoCreditoRepository extends
		BaseRepository<CartaoCreditoEntity, Short> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cams7.jpa.repository.EntityRepository#findAllOrdered(org.springframework
	 * .data.domain.Pageable)
	 */
	@Query(value = "select c from CartaoCreditoEntity c order by c.empresaNome asc", countQuery = "select count(c) from CartaoCreditoEntity c")
	@Override
	public Page<CartaoCreditoEntity> findAllOrdered(Pageable pageable);

	/**
	 * Utiliza anotação <code>Query</code> do Spring Data JPA pra definir o
	 * código SQL.
	 * 
	 * @param empresaNome
	 *            Filtro da pesquisa de cartoes.
	 * @return Lista de cartoes com filtro em nome da operadora.
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.jpa.repository.EntityRepository#findBySearch(java.lang.String,
	 * org.springframework.data.domain.Pageable)
	 */
	@Query(value = "select c from CartaoCreditoEntity c where c.empresaNome like :operadora order by c.empresaNome asc", countQuery = "select count(c) from CartaoCreditoEntity c where c.empresaNome like :operadora")
	@Override
	public Page<CartaoCreditoEntity> findBySearch(
			@Param("operadora") String empresaNome, Pageable pageable);

	@Query("select count(c) from CartaoCreditoEntity c")
	@Override
	public long countAll();

	@Query("select count(c) from CartaoCreditoEntity c where c.empresaNome like :operadora")
	@Override
	public long countBySearch(@Param("operadora") String empresaNome);

}
