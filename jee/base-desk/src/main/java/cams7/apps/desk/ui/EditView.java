/**
 * 
 */
package cams7.apps.desk.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cams7.apps.jpa.domain.BaseEntity;
import cams7.apps.util.ApplicationException;
import cams7.apps.util.ApplicationUtil;

/**
 * @author cesar
 *
 */
public abstract class EditView extends FormView {

	private static final long serialVersionUID = 1L;

	private JFormattedTextField txtId;
	// private JFormattedTextField txtVersion;

	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir;

	public EditView() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.FormView#montaPanelForm()
	 */
	protected JPanel montaPanelForm() {
		txtId = new JFormattedTextField();
		Serializable id = getEntityId("0");
		if (id instanceof Number) {
			if (id instanceof Byte)
				txtId.setValue((Byte) id);
			else if (id instanceof Short)
				txtId.setValue((Short) id);
			else if (id instanceof Integer)
				txtId.setValue((Integer) id);
			else if (id instanceof Long)
				txtId.setValue((Long) id);
			else if (id instanceof Float)
				txtId.setValue((Float) id);
			else if (id instanceof Double)
				txtId.setValue((Double) id);
		}
		txtId.setEnabled(false);

		JPanel pnl = new JPanel();
		GridLayout layout = new GridLayout(1, 2);
		pnl.setLayout(layout);

		pnl.add(new JLabel(getBundleMessage().getEntityFieldName("id") + ":"));
		pnl.add(txtId);

		// txtVersion = new JFormattedTextField();
		// txtVersion.setVisible(false);

		return pnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.BaseView#montaPanelBotoes()
	 */
	protected JPanel montaPanelBotoes() {
		btnSalvar = new JButton(getBundleMessage().getMessage(
				"EditView.btnSalvar.text"));
		btnSalvar.setIcon(new ImageIcon(getURLBotaoIcon("save.png")));
		btnSalvar.setActionCommand("salva" + getEntityName() + "Action");
		btnSalvar.setMnemonic(KeyEvent.VK_S);

		btnCancelar = new JButton(getBundleMessage().getMessage(
				"EditView.btnCancelar.text"));
		btnCancelar.setIcon(new ImageIcon(getURLBotaoIcon("cancel.png")));
		btnCancelar.setActionCommand("cancela" + getEntityName() + "Action");
		btnCancelar.setMnemonic(KeyEvent.VK_C);

		btnExcluir = new JButton(getBundleMessage().getMessage(
				"EditView.btnExcluir.text"));
		btnExcluir.setIcon(new ImageIcon(getURLBotaoIcon("delete.png")));
		btnExcluir.setActionCommand("exclui" + getEntityName() + "Action");
		btnExcluir.setMnemonic(KeyEvent.VK_E);

		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl.add(btnSalvar, BorderLayout.WEST);
		pnl.add(btnCancelar, BorderLayout.CENTER);
		pnl.add(btnExcluir, BorderLayout.EAST);

		return pnl;
	}

	protected BaseEntity<?> loadEntityFromPanel() {
		try {
			Serializable id = getEntityId(txtId.getText());

			BaseEntity<?> entity = ApplicationUtil.getNewEntity(
					getEntityType(), id);

			// Number version = (Number) txtVersion.getValue();
			// if (version != null)
			// entity.setVersion(version.intValue());

			return entity;
		} catch (ApplicationException e) {
			getLog().error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}

	protected void populaTextFields(BaseEntity<?> entity) {
		txtId.setValue(entity.getId());
		// txtVersion.setValue(entity.getVersion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.FormView#resetForm()
	 */
	public void resetForm() {
		txtId.setValue(null);
		btnExcluir.setVisible(false);
		// txtVersion.setValue(null);
	}

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}

	/**
	 * @return uma nova instância da <code>Entidade</code> com os dados
	 *         preenchidos do campos na tela.
	 */
	public BaseEntity<?> getEntity() {
		return loadEntityFromPanel();
	}

	/**
	 * Limpa e carrega os campos da tela de acordo com objeto
	 * <code>Entidade</code>.
	 * 
	 * @param entity
	 *            referência da <code>Entidade</code> que deve ser apresentada
	 *            na tela.
	 */
	public void setEntity(BaseEntity<?> entity) {
		resetForm();
		if (entity != null) {
			populaTextFields(entity);
			btnExcluir.setVisible(true);
		}
	}

	private Serializable getEntityId(String id) {
		try {
			return ApplicationUtil.getId(getEntityType(), id);
		} catch (ApplicationException e) {
			getLog().error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return o identificador da <code>Entidade</code> em edição. Retorna
	 *         <code>null</code> em modo de inclusão.
	 */
	public Serializable getEntityId() {
		return getEntityId(txtId.getText());
	}

}
