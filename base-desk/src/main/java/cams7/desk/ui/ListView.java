/**
 * 
 */
package cams7.desk.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cams7.desk.component.Paginacao;
import cams7.desk.ui.table.BaseTable;
import cams7.jpa.domain.BaseEntity;
import cams7.jpa.domain.util.EntityAttribute;
import cams7.util.ApplicationUtil;

/**
 * @author cesar
 *
 */
public abstract class ListView extends BaseView {

	private static final long serialVersionUID = 1L;
	public static final byte MODEL_ARGUMENT_NUMBER = 0;

	private BaseTable tblEntities;

	private JScrollPane scrollPane;

	private JButton btnNovo;
	private JButton btnBuscar;
	private JButton btnAtualizar;

	private JComboBox<Byte> cmbRegistrosPorPagina;

	private JButton btnPrimeiraPagina;
	private JButton btnAnterior;

	private JFormattedTextField txtAtualPagina;
	private JLabel lblTotalPaginas;

	private JButton btnProxima;
	private JButton btnUltimaPagina;

	public ListView() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.BaseView#initComponents()
	 */
	@Override
	public void initComponents() {

		tblEntities = new BaseTable(getBundleMessage(), getEntityFields()) {
			private static final long serialVersionUID = 1L;
		};

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tblEntities);

		add(scrollPane);

		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl.add(montaPanelRegistrosPorPagina(), BorderLayout.WEST);
		pnl.add(montaPanelPaginacao(), BorderLayout.CENTER);
		pnl.add(montaPanelBotoes(), BorderLayout.EAST);

		add(pnl, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setResizable(false);

		setSize(900, 500);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.BaseView#montaPanelBotoes()
	 */
	protected JPanel montaPanelBotoes() {
		btnNovo = new JButton(getBundleMessage().getMessage(
				"ListView.btnNovo.text"));
		btnNovo.setIcon(new ImageIcon(getURLBotaoIcon("new.png")));
		btnNovo.setActionCommand("nova" + getEntityName() + "Action");
		btnNovo.setMnemonic(KeyEvent.VK_N);

		btnBuscar = new JButton(getBundleMessage().getMessage(
				"ListView.btnBuscar.text"));
		btnBuscar.setIcon(new ImageIcon(getURLBotaoIcon("search_icon.gif")));
		btnBuscar.setActionCommand("busca" + getEntityName() + "Action");
		btnBuscar.setMnemonic(KeyEvent.VK_B);

		btnAtualizar = new JButton(getBundleMessage().getMessage(
				"ListView.btnAtualizar.text"));
		btnAtualizar.setIcon(new ImageIcon(
				getURLBotaoIcon("refresh_icon.gif.png")));
		btnAtualizar.setActionCommand("atualiza" + getEntityName() + "Action");
		btnAtualizar.setMnemonic(KeyEvent.VK_A);

		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl.add(btnNovo, BorderLayout.WEST);
		pnl.add(btnBuscar, BorderLayout.CENTER);
		pnl.add(btnAtualizar, BorderLayout.EAST);

		return pnl;
	}

	protected JPanel montaPanelPaginacao() {
		btnPrimeiraPagina = new JButton();
		btnPrimeiraPagina.setIcon(new ImageIcon(
				getURLIconPagination("pagination-arrow-first.gif")));
		btnPrimeiraPagina.setActionCommand("primeiraPagina" + getEntityName()
				+ "Action");

		btnAnterior = new JButton();
		btnAnterior.setIcon(new ImageIcon(getURLIconPagination("prev.gif")));
		// btnAnterior.setText(getBundleMessage().getMessage(
		// "ListView.btnAnterior.text"));
		btnAnterior.setActionCommand("anterior" + getEntityName() + "Action");

		txtAtualPagina = new JFormattedTextField();
		txtAtualPagina.setValue(Paginacao.ATUAL_PAGINA_DEFAULT);
		txtAtualPagina.setColumns(3);
		lblTotalPaginas = new JLabel();

		btnProxima = new JButton();
		// btnProxima.setText(getBundleMessage().getMessage(
		// "ListView.btnProxima.text"));
		btnProxima.setIcon(new ImageIcon(getURLIconPagination("next.gif")));
		btnProxima.setActionCommand("proxima" + getEntityName() + "Action");

		btnUltimaPagina = new JButton();
		btnUltimaPagina.setIcon(new ImageIcon(
				getURLIconPagination("pagination-arrow-last.gif")));
		btnUltimaPagina.setActionCommand("ultimaPagina" + getEntityName()
				+ "Action");

		JPanel pnl = new JPanel();
		pnl.add(btnPrimeiraPagina);
		pnl.add(btnAnterior);
		pnl.add(new JLabel(getBundleMessage().getMessage(
				"ListView.lblPage.text")));
		pnl.add(txtAtualPagina);
		pnl.add(new JLabel(getBundleMessage().getMessage("ListView.lblOf.text")
				+ " "));
		pnl.add(lblTotalPaginas);
		pnl.add(btnProxima);
		pnl.add(btnUltimaPagina);
		return pnl;
	}

	private JPanel montaPanelRegistrosPorPagina() {
		cmbRegistrosPorPagina = new JComboBox<Byte>(
				Paginacao.REGISTROS_POR_PAGINA);
		cmbRegistrosPorPagina
				.setSelectedItem(Paginacao.REGISTROS_POR_PAGINA_DEFAULT);

		JPanel pnl = new JPanel();
		pnl.add(new JLabel(getBundleMessage().getMessage(
				"ListView.cbRegistrosPorPagina.text")
				+ ":"));
		pnl.add(cmbRegistrosPorPagina);
		return pnl;
	}

	private URL getURLIconPagination(String img) {
		URL imageURL = ApplicationUtil.getURLImage(this.getClass(),
				"pagination/" + img);
		return imageURL;
	}

	public JButton getBtnNovo() {
		return btnNovo;
	}

	public JButton getBtnAtualizar() {
		return btnAtualizar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JComboBox<Byte> getCmbRegistrosPorPagina() {
		return cmbRegistrosPorPagina;
	}

	public JButton getBtnPrimeiraPagina() {
		return btnPrimeiraPagina;
	}

	public JButton getBtnAnterior() {
		return btnAnterior;
	}

	public JFormattedTextField getTxtAtualPagina() {
		return txtAtualPagina;
	}

	public JLabel getLblTotalPaginas() {
		return lblTotalPaginas;
	}

	public JButton getBtnProxima() {
		return btnProxima;
	}

	public JButton getBtnUltimaPagina() {
		return btnUltimaPagina;
	}

	public void refreshTable(List<BaseEntity<?>> entities) {
		tblEntities.reload(entities);
	}

	public void refreshTable(Map<String, EntityAttribute> entityFields) {
		tblEntities.reload(entityFields);
	}

	public BaseEntity<?> getSelectedEntity() {
		BaseEntity<?> entity = tblEntities.getEntitySelected();
		return entity;
	}

	public BaseTable getTblEntities() {
		return tblEntities;
	}

}
