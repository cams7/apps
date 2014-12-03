package cams7.siscom.cartaoCredito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import cams7.desk.controller.BaseListCtrl;
import cams7.siscom.cartaoCredito.repository.CartaoCreditoRepository;
import cams7.siscom.cartaoCredito.ui.CartaoCreditoListView;
import cams7.siscom.jpa.domain.entity.CartaoCreditoEntity;
import cams7.siscom.jpa.domain.entity.CartaoCreditoEntity_;
import cams7.siscom.listener.MenuListener;

/**
 * Define a <code>Controller</code> principal do sistema, responsável por gerir
 * a tela com a lista de <code>CartaoCredito</code>.
 * 
 * <p>
 * <code>CartaoCreditoListCtrl</code> é mapeada como <code>@Component</code> do
 * Spring. Dessa forma uma instância de <code>CartaoCreditoListCtrl</code> pode
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
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartaoCreditoListCtrl
		extends
		BaseListCtrl<CartaoCreditoListView, CartaoCreditoRepository, CartaoCreditoEntity> {

	@Autowired
	private CartaoCreditoEditCtrl cartaoCreditoEditCtrl;

	@Autowired
	private CartaoCreditoSearchCtrl cartaoCreditoSearchCtrl;

	public CartaoCreditoListCtrl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseListCtrl#initCtrl()
	 */
	@Override
	protected void initCtrl() {
		super.initCtrl();

		mudaPrimeirosCamposTabela(CartaoCreditoEntity_.empresaNome,
				CartaoCreditoEntity_.pgtoMinimoPercentual,
				CartaoCreditoEntity_.juroParceladoPercentual,
				CartaoCreditoEntity_.multaPercentual,
				CartaoCreditoEntity_.tipoMulta,
				CartaoCreditoEntity_.multaCobradaDia);

	}

	/**
	 * @param menuListener
	 */
	@Autowired
	public CartaoCreditoListCtrl(MenuListener menuListener) {
		super(menuListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setView(cams7.desk.ui.BaseView)
	 */
	@Autowired
	@Override
	protected void setView(CartaoCreditoListView view) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseListCtrl#getEditCtrl()
	 */
	protected CartaoCreditoEditCtrl getEditCtrl() {
		return cartaoCreditoEditCtrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseListCtrl#getSearchCtrl()
	 */
	protected CartaoCreditoSearchCtrl getSearchCtrl() {
		return cartaoCreditoSearchCtrl;
	}

}
