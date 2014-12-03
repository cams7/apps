/**
 * 
 */
package cams7.siscom.tipoPgto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import cams7.apps.desk.controller.BaseListCtrl;
import cams7.siscom.jpa.domain.entity.TipoPgtoEntity;
import cams7.siscom.listener.MenuListener;
import cams7.siscom.tipoPgto.repository.TipoPgtoRepository;
import cams7.siscom.tipoPgto.ui.TipoPgtoListView;

/**
 * @author cesar
 *
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TipoPgtoListCtrl extends
		BaseListCtrl<TipoPgtoListView, TipoPgtoRepository, TipoPgtoEntity> {

	@Autowired
	private TipoPgtoEditCtrl tipoPgtoEditCtrl;

	@Autowired
	private TipoPgtoSearchCtrl tipoPgtoSearchCtrl;

	public TipoPgtoListCtrl() {
		super();
	}

	/**
	 * @param menuListener
	 */
	@Autowired
	public TipoPgtoListCtrl(MenuListener menuListener) {
		super(menuListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setView(cams7.desk.ui.BaseView)
	 */
	@Autowired
	@Override
	protected void setView(TipoPgtoListView view) {
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
	protected void setRepository(TipoPgtoRepository repository) {
		super.setRepository(repository);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseListCtrl#getEditCtrl()
	 */
	protected TipoPgtoEditCtrl getEditCtrl() {
		return tipoPgtoEditCtrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseListCtrl#getSearchCtrl()
	 */
	protected TipoPgtoSearchCtrl getSearchCtrl() {
		return tipoPgtoSearchCtrl;
	}

}
