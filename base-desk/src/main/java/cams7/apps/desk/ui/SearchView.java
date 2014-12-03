/**
 * 
 */
package cams7.apps.desk.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author cesar
 *
 */
public abstract class SearchView extends FormView {

	private static final long serialVersionUID = 1L;

	private JTextField txtBuscar;
	private JButton btnBuscar;

	public SearchView() {
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

		setTitle(getBundleMessage().getSearchViewTitle());
		setSize(400, 150);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.FormView#montaPanelForm()
	 */
	protected JPanel montaPanelForm() {
		txtBuscar = new JTextField();

		JPanel pnl = new JPanel();
		// 2 linhas
		// 1 coluna
		pnl.setLayout(new GridLayout(2, 1));

		pnl.add(new JLabel(getBundleMessage().getSearchField() + ":"));
		pnl.add(txtBuscar);
		return pnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.BaseView#montaPanelBotoes()
	 */
	protected JPanel montaPanelBotoes() {
		btnBuscar = new JButton(getBundleMessage().getMessage(
				"SearchView.btnBuscar.text"));
		btnBuscar.setIcon(new ImageIcon(getURLBotaoIcon("search_icon.gif")));
		btnBuscar.setActionCommand("busca" + getEntityName() + "Action");
		btnBuscar.setMnemonic(KeyEvent.VK_B);

		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl.add(btnBuscar, BorderLayout.CENTER);
		return pnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.FormView#resetForm()
	 */
	public void resetForm() {
		txtBuscar.setText(EMPTY);
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	/**
	 * @return o texto preenchido no campo de consulta.
	 */
	public String getBuscar() {
		return txtBuscar.getText();
	}

}
