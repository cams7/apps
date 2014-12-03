/**
 * 
 */
package cams7.desk.ui;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 * @author cesar
 *
 */
public abstract class FormView extends BaseView {

	private static final long serialVersionUID = 1L;

	private NumberFormat amountFormat;
	private NumberFormat percentFormat;
	private NumberFormat paymentFormat;

	public FormView() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.BaseView#initComponents()
	 */
	public void initComponents() {
		setUpFormats();

		setLocationRelativeTo(null);
		setResizable(false);

		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl.add(montaPanelForm(), BorderLayout.CENTER);
		pnl.add(montaPanelBotoes(), BorderLayout.SOUTH);

		add(pnl);

		setLayout(pnl);

		resetForm();

		setSize(500, 150);
	}

	private void setUpFormats() {
		amountFormat = NumberFormat.getNumberInstance();

		percentFormat = new DecimalFormat("#.00");
		// percentFormat.setMinimumFractionDigits(3);

		paymentFormat = NumberFormat.getCurrencyInstance();
	}

	private void setLayout(JPanel panel) {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
	}

	protected abstract JPanel montaPanelForm();

	public abstract void resetForm();

	protected NumberFormat getAmountFormat() {
		return amountFormat;
	}

	protected NumberFormat getPercentFormat() {
		return percentFormat;
	}

	protected NumberFormat getPaymentFormat() {
		return paymentFormat;
	}

}
