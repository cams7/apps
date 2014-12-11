package br.com.cams7.siscom.mercadoria.service;

import javax.ejb.Local;

import br.com.cams7.apps.jee.service.BaseService;
import br.com.cams7.siscom.jpa.domain.entity.Mercadoria;

/**
 * Determina a interface de negócio para estipular o serviço da entidade
 * <code>Mercadoria</code>.
 * 
 * <p>
 * Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 * 
 * @author YaW Tecnologia
 */
@Local
public interface MercadoriaService extends BaseService<Mercadoria, Long> {

}
