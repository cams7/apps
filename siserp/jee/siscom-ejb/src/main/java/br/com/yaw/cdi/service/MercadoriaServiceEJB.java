package br.com.yaw.cdi.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cams7.siscom.jpa.domain.entity.Mercadoria;

/**
 * Componente <code>EJB</code> que implementa as operações de negócio da
 * entidade <code>Mercadoria</code>.
 * 
 * <p>
 * Herda <code>AbstractPersistence</code> para resolver as operações básicas da
 * persistência de <code>Mercadoria</code>.
 * </p>
 * 
 * @see br.com.yaw.cdi.service.ContaBancariaService
 * @author YaW Tecnologia
 */
@Stateless
public class MercadoriaServiceEJB extends AbstractPersistence<Mercadoria, Long>
		implements MercadoriaService {

	@PersistenceContext(unitName = "appSwingSpringUnit")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MercadoriaServiceEJB() {
		super(Mercadoria.class);
	}

}
