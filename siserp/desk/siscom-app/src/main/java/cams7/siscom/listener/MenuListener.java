/**
 * 
 */
package cams7.siscom.listener;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import cams7.apps.desk.action.AbstractAction;
import cams7.apps.desk.listener.BaseListener;
import cams7.siscom.cartaoCredito.controller.CartaoCreditoListCtrl;
import cams7.siscom.contaBancaria.controller.ContaBancariaListCtrl;
import cams7.siscom.tipoPgto.controller.TipoPgtoListCtrl;
import cams7.siscom.ui.MenuView;
import cams7.siscom.ui.SobreView;

/**
 * @author cesar
 *
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MenuListener extends BaseListener {

	@Autowired
	private MenuView menuView;

	@Autowired
	private CartaoCreditoListCtrl cartaoCreditoListCtrl;

	@Autowired
	private TipoPgtoListCtrl tipoPgtoListCtrl;

	@Autowired
	private ContaBancariaListCtrl contaBancariaListCtrl;

	@Autowired
	private SobreView sobreView;

	public MenuListener() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.listener.BaseListener#init()
	 */
	@PostConstruct
	@Override
	protected void initCtrl() {
		super.initCtrl();

		menuView.initComponents(getBundleMessage());

		menuView.addWindowListener(this);

		registerAction(menuView.getMenuCadastroCartao(), new AbstractAction() {
			public void action() {
				cartaoCreditoListCtrl.show();
			}
		});

		registerAction(menuView.getMenuCadastroTipoPgtoRecebimento(),
				new AbstractAction() {
					public void action() {
						tipoPgtoListCtrl.show();
					}
				});

		registerAction(menuView.getMenuCadastroContaBancaria(),
				new AbstractAction() {
					public void action() {
						contaBancariaListCtrl.show();
					}
				});

		AbstractAction actionSobre = new AbstractAction() {
			@Override
			protected void action() {
				sobreView.initComponents(getBundleMessage());
				sobreView.setVisible(true);
			}
		};
		registerAction(menuView.getMenuSobre(), actionSobre);
		menuView.getMenuAjuda().addListener(actionSobre);

		menuView.setVisible(true);
	}

}
