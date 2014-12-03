/**
 * 
 */
package cams7.siscom.contaBancaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import cams7.desk.controller.BaseListCtrl;
import cams7.siscom.contaBancaria.repository.ContaBancariaRepository;
import cams7.siscom.contaBancaria.ui.ContaBancariaListView;
import cams7.siscom.jpa.domain.entity.BancoEntity_;
import cams7.siscom.jpa.domain.entity.ContaBancariaEntity;
import cams7.siscom.jpa.domain.entity.ContaBancariaEntity_;
import cams7.siscom.listener.MenuListener;

/**
 * @author cesar
 *
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ContaBancariaListCtrl
		extends
		BaseListCtrl<ContaBancariaListView, ContaBancariaRepository, ContaBancariaEntity> {

	@Autowired
	private ContaBancariaEditCtrl contaBancariaEditCtrl;

	@Autowired
	private ContaBancariaSearchCtrl contaBancariaSearchCtrl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseListCtrl#initCtrl()
	 */
	@Override
	protected void initCtrl() {
		super.initCtrl();

		mudaNaoVisualizacaoCamposTabela(ContaBancariaEntity_.bancoGerente,
				ContaBancariaEntity_.bancoFone);
		mudaVisualizacaoCamposTabela(BancoEntity_.nome);
		mudaPrimeirosCamposTabela(BancoEntity_.nome);
	}

	public ContaBancariaListCtrl() {
		super();
	}

	/**
	 * @param parentListener
	 */
	@Autowired
	public ContaBancariaListCtrl(MenuListener menuListener) {
		super(menuListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setView(cams7.desk.ui.BaseView)
	 */
	@Autowired
	@Override
	protected void setView(ContaBancariaListView view) {
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
	protected void setRepository(ContaBancariaRepository repository) {
		super.setRepository(repository);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseListCtrl#getEditCtrl()
	 */
	protected ContaBancariaEditCtrl getEditCtrl() {
		return contaBancariaEditCtrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseListCtrl#getSearchCtrl()
	 */
	protected ContaBancariaSearchCtrl getSearchCtrl() {
		return contaBancariaSearchCtrl;
	}

}
