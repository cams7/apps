/**
 * 
 */
package cams7.siscom.contaBancaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cams7.desk.controller.BaseEditCtrl;
import cams7.siscom.contaBancaria.repository.ContaBancariaRepository;
import cams7.siscom.contaBancaria.ui.ContaBancariaEditView;
import cams7.siscom.jpa.domain.entity.ContaBancariaEntity;

/**
 * @author cesar
 *
 */
@Component
public class ContaBancariaEditCtrl
		extends
		BaseEditCtrl<ContaBancariaEditView, ContaBancariaRepository, ContaBancariaEntity> {

	public ContaBancariaEditCtrl() {
		super();
	}

	@Autowired
	public ContaBancariaEditCtrl(ContaBancariaListCtrl listCtrl) {
		super(listCtrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#setView(cams7.desk.ui.BaseView)
	 */
	@Autowired
	@Override
	protected void setView(ContaBancariaEditView view) {
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

}
