/**
 * 
 */
package br.com.cams7.siscom.tipoPgto.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import br.com.cams7.apps.desk.ui.EditView;
import br.com.cams7.apps.jpa.domain.BaseEntity;
import br.com.cams7.siscom.jpa.domain.entity.TipoPgtoEntity;

/**
 * @author cesar
 *
 */
@Component
public class TipoPgtoEditView extends EditView {

	private static final long serialVersionUID = 1L;

	private JTextField txtDescricao;

	public TipoPgtoEditView() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.FormView#initComponents()
	 */
	@Override
	public void initComponents() {
		super.initComponents();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.EditView#montaPanelForm()
	 */
	@Override
	protected JPanel montaPanelForm() {
		txtDescricao = new JTextField();

		JPanel pnl = super.montaPanelForm();
		GridLayout layout = new GridLayout(2, 2);
		pnl.setLayout(layout);

		pnl.add(new JLabel(getBundleMessage().getEntityFieldName("descricao")
				+ ":"));
		pnl.add(txtDescricao);

		return pnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.EditView#loadEntityFromPanel()
	 */
	@Override
	protected TipoPgtoEntity loadEntityFromPanel() {
		TipoPgtoEntity tipoPgto = (TipoPgtoEntity) super.loadEntityFromPanel();

		tipoPgto.setDescricao(txtDescricao.getText());

		return tipoPgto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.EditView#resetForm()
	 */
	@Override
	public void resetForm() {
		super.resetForm();

		txtDescricao.setText(EMPTY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.EditView#populaTextFields(cams7.jpa.domain.BaseEntity)
	 */
	@Override
	protected void populaTextFields(BaseEntity<?> entity) {
		super.populaTextFields(entity);

		TipoPgtoEntity tipoPgto = (TipoPgtoEntity) entity;
		txtDescricao.setText(tipoPgto.getDescricao());
	}

}
