package cams7.siscom.cartaoCredito.ui;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import cams7.apps.desk.ui.EditView;
import cams7.apps.jpa.domain.BaseEntity;
import cams7.siscom.jpa.domain.entity.CartaoCreditoEntity;

/**
 * Tela para incluir/editar o registro de <code>CartaoCredito</code>.
 * 
 * <p>
 * Essa tela trabalha em modo inclusão ou edição de <code>CartaoCredito</code>.
 * Em edição é possível acionar a funcionalidade para remover
 * <code>CartaoCredito</code>.
 * </p>
 * 
 * <p>
 * <code>CartaoCreditoEditView</code> é mapeada como <code>@Component</code> do
 * Spring. Dessa forma uma instância de <code>CartaoCreditoEditView</code> pode
 * ser criada e gerenciada pelo Spring, favorecendo a Inversão de Controle
 * <i>(IoC)</i> e Injeção de Dependência <i>(DI)</i>.
 * </p>
 * 
 * @author cesar
 *
 */
@Component
public class CartaoCreditoEditView extends EditView {
	private static final long serialVersionUID = 1L;

	private JTextField txtCartaoOperadora;
	private JTextField txtCartaoJurosRotativo;

	private JFormattedTextField txtCartaoPgtoMinimo;
	private JFormattedTextField txtCartaoJurosParcelado;
	private JFormattedTextField txtCartaoMulta;

	private JRadioButton rbTotal;
	private JRadioButton rbMinimo;

	private JFormattedTextField txtCartaoMultaCobradaDia;

	private byte MULTA_COBRADADIA = 5;

	public CartaoCreditoEditView() {
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
		setSize(500, 360);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.EditView#montaPanelForm()
	 */
	@Override
	protected JPanel montaPanelForm() {
		txtCartaoOperadora = new JTextField();
		txtCartaoJurosRotativo = new JTextField();

		txtCartaoPgtoMinimo = new JFormattedTextField(getPercentFormat());
		txtCartaoJurosParcelado = new JFormattedTextField(getPercentFormat());
		txtCartaoMulta = new JFormattedTextField(getPercentFormat());

		rbTotal = new JRadioButton(CartaoCreditoEntity.TipoMulta.TOTAL.name());
		rbMinimo = new JRadioButton(CartaoCreditoEntity.TipoMulta.MINIMO.name());

		ButtonGroup bgTipoMulta = new ButtonGroup();
		bgTipoMulta.add(rbTotal);
		bgTipoMulta.add(rbMinimo);

		rbTotal.setSelected(false);
		rbMinimo.setSelected(false);

		txtCartaoMultaCobradaDia = new JFormattedTextField();
		txtCartaoMultaCobradaDia.setValue(new Byte(MULTA_COBRADADIA));

		JPanel pnl = super.montaPanelForm();
		GridLayout layout = new GridLayout(8, 2);
		pnl.setLayout(layout);

		pnl.add(new JLabel(getBundleMessage().getEntityFieldName("empresaNome")
				+ ":"));
		pnl.add(txtCartaoOperadora);
		pnl.add(new JLabel(getBundleMessage().getEntityFieldName(
				"juroRotativoPercentual")
				+ ":"));
		pnl.add(txtCartaoJurosRotativo);

		pnl.add(new JLabel(getBundleMessage().getEntityFieldName(
				"pgtoMinimoPercentual")
				+ ":"));
		pnl.add(txtCartaoPgtoMinimo);
		pnl.add(new JLabel(getBundleMessage().getEntityFieldName(
				"juroParceladoPercentual")
				+ ":"));
		pnl.add(txtCartaoJurosParcelado);
		pnl.add(new JLabel(getBundleMessage().getEntityFieldName(
				"multaPercentual")
				+ ":"));
		pnl.add(txtCartaoMulta);

		pnl.add(new JLabel(getBundleMessage().getEntityFieldName("tipoMulta")
				+ ":"));

		JPanel pnlTipoMulta = new JPanel();
		pnlTipoMulta.add(rbTotal);
		pnlTipoMulta.add(rbMinimo);
		pnl.add(pnlTipoMulta);

		pnl.add(new JLabel(getBundleMessage().getEntityFieldName(
				"multaCobradaDia")
				+ ":"));
		pnl.add(txtCartaoMultaCobradaDia);

		return pnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.EditView#loadEntityFromPanel()
	 */
	@Override
	protected CartaoCreditoEntity loadEntityFromPanel() {
		CartaoCreditoEntity cartaoCredito = (CartaoCreditoEntity) super
				.loadEntityFromPanel();

		cartaoCredito.setEmpresaNome(txtCartaoOperadora.getText());
		cartaoCredito.setJuroRotativoPercentual(txtCartaoJurosRotativo
				.getText());

		Number pgtoMinimo = (Number) txtCartaoPgtoMinimo.getValue();
		if (pgtoMinimo != null)
			cartaoCredito.setPgtoMinimoPercentual(pgtoMinimo.floatValue());

		Number jurosParcelado = (Number) txtCartaoJurosParcelado.getValue();
		if (jurosParcelado != null)
			cartaoCredito.setJuroParceladoPercentual(jurosParcelado
					.floatValue());

		Number multa = (Number) txtCartaoMulta.getValue();
		if (multa != null)
			cartaoCredito.setMultaPercentual(multa.floatValue());

		if (rbTotal.isSelected()) {
			cartaoCredito.setTipoMulta(CartaoCreditoEntity.TipoMulta.TOTAL);
		} else if (rbMinimo.isSelected()) {
			cartaoCredito.setTipoMulta(CartaoCreditoEntity.TipoMulta.MINIMO);
		}

		Number multaCobradaDia = (Number) txtCartaoMultaCobradaDia.getValue();
		if (multaCobradaDia != null)
			cartaoCredito.setMultaCobradaDia(multaCobradaDia.byteValue());

		return cartaoCredito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.EditView#resetForm()
	 */
	@Override
	public void resetForm() {
		super.resetForm();

		txtCartaoOperadora.setText(EMPTY);
		txtCartaoJurosRotativo.setText(EMPTY);

		txtCartaoPgtoMinimo.setValue(null);
		txtCartaoJurosParcelado.setValue(null);
		txtCartaoMulta.setValue(null);

		rbTotal.setSelected(false);
		rbMinimo.setSelected(false);

		txtCartaoMultaCobradaDia.setValue(new Byte(MULTA_COBRADADIA));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.ui.EditView#populaTextFields(cams7.jpa.domain.BaseEntity)
	 */
	@Override
	protected void populaTextFields(BaseEntity<?> entity) {
		super.populaTextFields(entity);

		CartaoCreditoEntity cartaoCredito = (CartaoCreditoEntity) entity;

		txtCartaoOperadora.setText(cartaoCredito.getEmpresaNome());
		txtCartaoJurosRotativo.setText(cartaoCredito
				.getJuroRotativoPercentual());

		txtCartaoPgtoMinimo.setValue(cartaoCredito.getPgtoMinimoPercentual());
		txtCartaoJurosParcelado.setValue(cartaoCredito
				.getJuroParceladoPercentual());
		txtCartaoMulta.setValue(cartaoCredito.getMultaPercentual());

		if (cartaoCredito.getTipoMulta() != null) {
			switch (cartaoCredito.getTipoMulta()) {
			case TOTAL:
				rbTotal.setSelected(true);
				break;
			case MINIMO:
				rbMinimo.setSelected(true);
				break;
			default:
				break;
			}
		} else {
			rbTotal.setSelected(false);
			rbMinimo.setSelected(false);
		}

		txtCartaoMultaCobradaDia.setValue(cartaoCredito.getMultaCobradaDia());

	}

}
