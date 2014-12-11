package br.com.cams7.siscom.cartaoCredito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cams7.apps.desk.controller.BaseSearchCtrl;
import br.com.cams7.siscom.cartaoCredito.repository.CartaoCreditoRepository;
import br.com.cams7.siscom.cartaoCredito.ui.CartaoCreditoSearchView;
import br.com.cams7.siscom.jpa.domain.entity.CartaoCreditoEntity;

/**
 * Define a <code>Controller</code> responsável por gerir a tela de Busca de
 * <code>CartaoCredito</code> pelo campo <code>empresaNome</code>.
 * 
 * <p>
 * <code>CartaoCreditoSearchCtrl</code> é mapeada como <code>@Component</code>
 * do Spring. Dessa forma uma instância de <code>CartaoCreditoSearchCtrl</code>
 * pode ser criada e gerenciada pelo Spring, favorecendo a Inversão de Controle
 * <i>(IoC)</i> e Injeção de Dependência <i>(DI)</i>.
 * </p>
 * 
 * <p>
 * Essa <code>Controller</code> depende de outros componentes da camada
 * <code>Model</code> (DAO) e <code>View</code> (tela). As dependências são
 * resolvidas pelo Spring, através da <strong>Injeção de Dependência</strong> c/
 * a anotação <code>@Autowired</code>.
 * </p>
 * 
 * @author cesar
 *
 */
@Component
public class CartaoCreditoSearchCtrl
		extends
		BaseSearchCtrl<CartaoCreditoSearchView, CartaoCreditoRepository, CartaoCreditoEntity> {

	public CartaoCreditoSearchCtrl() {
		super();
	}

	/**
	 * @param listCtrl
	 */
	@Autowired
	public CartaoCreditoSearchCtrl(CartaoCreditoListCtrl listCtrl) {
		super(listCtrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setView(cams7.desk.ui.BaseView)
	 */
	@Autowired
	@Override
	protected void setView(CartaoCreditoSearchView view) {
		super.setView(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setRepository(cams7.jpa.repository.
	 * EntityRepository)
	 */
	@Autowired
	@Override
	protected void setRepository(CartaoCreditoRepository repository) {
		super.setRepository(repository);
	}

}
