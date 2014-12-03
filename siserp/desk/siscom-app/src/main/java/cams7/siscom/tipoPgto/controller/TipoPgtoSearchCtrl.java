/**
 * 
 */
package cams7.siscom.tipoPgto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cams7.desk.controller.BaseSearchCtrl;
import cams7.siscom.jpa.domain.entity.TipoPgtoEntity;
import cams7.siscom.tipoPgto.repository.TipoPgtoRepository;
import cams7.siscom.tipoPgto.ui.TipoPgtoSearchView;

/**
 * @author cesar
 *
 */
@Component
public class TipoPgtoSearchCtrl extends
		BaseSearchCtrl<TipoPgtoSearchView, TipoPgtoRepository, TipoPgtoEntity> {

	public TipoPgtoSearchCtrl() {
		super();
	}

	/**
	 * @param listCtrl
	 */
	@Autowired
	public TipoPgtoSearchCtrl(TipoPgtoListCtrl listCtrl) {
		super(listCtrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setView(cams7.desk.ui.BaseView)
	 */
	@Autowired
	@Override
	protected void setView(TipoPgtoSearchView view) {
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

}
