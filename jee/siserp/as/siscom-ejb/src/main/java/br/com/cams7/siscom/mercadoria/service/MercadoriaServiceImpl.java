package br.com.cams7.siscom.mercadoria.service;

import javax.ejb.Stateless;

import br.com.cams7.apps.jee.service.BaseServiceImpl;
import br.com.cams7.siscom.jpa.domain.entity.Mercadoria;

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
public class MercadoriaServiceImpl extends BaseServiceImpl<Mercadoria, Long>
		implements MercadoriaService {

	public MercadoriaServiceImpl() {
		super();
	}

}
