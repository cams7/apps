package cams7.siscom.cartaoCredito.ui;

import org.springframework.stereotype.Component;

import cams7.desk.ui.SearchView;

/**
 * Tela utilizada para realizar a pesquisa de <code>CartaoCredito</code> com
 * filtro no campo <code>empresaNome</code>.
 * 
 * <p>
 * <code>CartaoCreditoSearchView</code> é mapeada como <code>@Component</code>
 * do Spring. Dessa forma uma instância de <code>CartaoCreditoSearchView</code>
 * pode ser criada e gerenciada pelo Spring, favorecendo a Inversão de Controle
 * <i>(IoC)</i> e Injeção de Dependência <i>(DI)</i>.
 * </p>
 * 
 * @author cesar
 *
 */
@Component
public class CartaoCreditoSearchView extends SearchView {

	private static final long serialVersionUID = 1L;

	public CartaoCreditoSearchView() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.SearchView#initComponents()
	 */
	@Override
	public void initComponents() {
		super.initComponents();
	}
}
