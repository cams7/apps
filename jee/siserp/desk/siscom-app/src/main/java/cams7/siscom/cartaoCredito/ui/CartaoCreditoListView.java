package cams7.siscom.cartaoCredito.ui;

import org.springframework.stereotype.Component;

import cams7.apps.desk.ui.ListView;

/**
 * Tela principal da aplicação. Apresenta uma lista com
 * <code>CartaoCredito</code> cadastrados.
 * 
 * <p>
 * A partir dessa tela é possível criar/editar ou pesquisar
 * <code>CartaoCredito</code>.
 * </p>
 * 
 * <p>
 * <code>CartaoCreditoListView</code> é mapeada como <code>@Component</code> do
 * Spring. Dessa forma uma instância de <code>CartaoCreditoListView</code> pode
 * ser criada e gerenciada pelo Spring, favorecendo a Inversão de Controle
 * <i>(IoC)</i> e Injeção de Dependência <i>(DI)</i>.
 * </p>
 * 
 * @author cesar
 *
 */
@Component
public class CartaoCreditoListView extends ListView {
	private static final long serialVersionUID = 1L;

	public CartaoCreditoListView() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.ListView#initComponents()
	 */
	@Override
	public void initComponents() {
		super.initComponents();
		setSize(1000, 500);
	}

}
