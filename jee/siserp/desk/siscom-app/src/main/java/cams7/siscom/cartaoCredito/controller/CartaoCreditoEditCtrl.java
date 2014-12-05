package cams7.siscom.cartaoCredito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cams7.apps.desk.controller.BaseEditCtrl;
import cams7.siscom.cartaoCredito.repository.CartaoCreditoRepository;
import cams7.siscom.cartaoCredito.ui.CartaoCreditoEditView;
import cams7.siscom.jpa.domain.entity.CartaoCreditoEntity;

/**
 * Define a <code>Controller</code> responsável por gerir a tela de
 * inclusão/edição de <code>CartaoCredito</code>.
 * 
 * <p>
 * <code>CartaoCreditoEditCtrl</code> é mapeada como <code>@Component</code> do
 * Spring. Dessa forma uma instância de <code>CartaoCreditoEditCtrl</code> pode
 * ser criada e gerenciada pelo Spring, favorecendo a Inversão de Controle
 * <i>(IoC)</i> e Injeção de Dependência <i>(DI)</i>.
 * </p>
 * 
 * <p>
 * Essa <code>Controller</code> depende de outros componentes da camada
 * <code>Model</code> (DAO) e <code>View</code> (telas). As dependências são
 * resolvidas pelo Spring, através da <strong>Injeção de Dependência</strong> c/
 * a anotação <code>@Autowired</code>.
 * </p>
 * 
 * @author cesar
 *
 */
@Component
public class CartaoCreditoEditCtrl
		extends
		BaseEditCtrl<CartaoCreditoEditView, CartaoCreditoRepository, CartaoCreditoEntity> {

	public CartaoCreditoEditCtrl() {
		super();
	}

	/**
	 * @param listCtrl
	 */
	@Autowired
	public CartaoCreditoEditCtrl(CartaoCreditoListCtrl listCtrl) {
		super(listCtrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setView(cams7.desk.ui.BaseView)
	 */
	@Autowired
	@Override
	protected void setView(CartaoCreditoEditView view) {
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
