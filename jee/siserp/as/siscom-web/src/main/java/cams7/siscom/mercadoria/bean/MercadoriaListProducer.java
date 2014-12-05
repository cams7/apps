/**
 * 
 */
package cams7.siscom.mercadoria.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import cams7.apps.jee.bean.BaseListProducer;
import cams7.siscom.jpa.domain.entity.Mercadoria;
import cams7.siscom.mercadoria.service.MercadoriaService;

/**
 * @author cesar
 *
 */
@RequestScoped
public class MercadoriaListProducer extends
		BaseListProducer<MercadoriaService, Mercadoria> {

	/**
	 * 
	 */
	public MercadoriaListProducer() {
		super();
	}

	@Named("mercadorias")
	@Produces
	@Override
	public List<Mercadoria> getEntities() {
		return super.getEntities();
	}

}