package br.com.yaw.cdi.service;

import java.util.List;

import javax.ejb.Local;

import br.com.yaw.cdi.model.Mercadoria;

/**
 * Determina a interface de negócio para estipular o serviço da entidade <code>Mercadoria</code>.
 * 
 * <p>Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 * 
 * @author YaW Tecnologia
 */
@Local
public interface MercadoriaService {

	public Mercadoria save(Mercadoria mercadoria);
	
	public void remove(Mercadoria mercadoria);
	
	public Mercadoria find(Long id);
	
	public List<Mercadoria> findAll();

}
