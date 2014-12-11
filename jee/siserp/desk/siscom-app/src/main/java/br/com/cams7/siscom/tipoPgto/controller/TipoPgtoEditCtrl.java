/**
 * 
 */
package br.com.cams7.siscom.tipoPgto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cams7.apps.desk.controller.BaseEditCtrl;
import br.com.cams7.siscom.jpa.domain.entity.TipoPgtoEntity;
import br.com.cams7.siscom.tipoPgto.repository.TipoPgtoRepository;
import br.com.cams7.siscom.tipoPgto.ui.TipoPgtoEditView;

/**
 * @author cesar
 *
 */
@Component
public class TipoPgtoEditCtrl extends
		BaseEditCtrl<TipoPgtoEditView, TipoPgtoRepository, TipoPgtoEntity> {

	public TipoPgtoEditCtrl() {
		super();
	}

	/**
	 * @param listCtrl
	 */
	@Autowired
	public TipoPgtoEditCtrl(TipoPgtoListCtrl listCtrl) {
		super(listCtrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setView(cams7.desk.ui.BaseView)
	 */
	@Autowired
	@Override
	protected void setView(TipoPgtoEditView view) {
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
