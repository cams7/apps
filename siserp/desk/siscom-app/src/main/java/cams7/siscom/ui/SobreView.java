package cams7.siscom.ui;

import static cams7.util.ApplicationProperties.getBuild;
import static cams7.util.ApplicationProperties.getDesenvolvidoPor;
import static cams7.util.ApplicationProperties.getURL;
import static cams7.util.ApplicationProperties.getTitulo;
import static cams7.util.ApplicationProperties.getVersao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import org.springframework.stereotype.Component;

import cams7.desk.util.AppResourceBundleMessage;
import cams7.util.ApplicationUtil;

/**
 * Tela <i>Sobre</i>. Apresenta detalhes da aplicação.
 * 
 * <p>
 * <code>SobreFrame</code> é mapeada como <code>@Component</code> do Spring.
 * Dessa forma uma instância de <code>SobreFrame</code> pode ser criada e
 * gerenciada pelo Spring, favorecendo a Inversão de Controle <i>(IoC)</i> e
 * Injeção de Dependência <i>(DI)</i>.
 * </p>
 * 
 * @see cams7.util.ApplicationProperties
 * 
 * @author YaW Tecnologia
 */
@Component
public class SobreView extends JDialog {
	private static final long serialVersionUID = 1L;

	private AppResourceBundleMessage bundleMessage;

	public SobreView() {
		super();
		setModal(true);
	}

	public void initComponents(AppResourceBundleMessage bundleMessage) {
		this.bundleMessage = bundleMessage;

		setTitle(getBundleMessage().getMessage("SobreView_title"));
		setSize(900, 230);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(montaLabelsSobre(), BorderLayout.CENTER);
		add(panel);
	}

	private JPanel montaLabelsSobre() {
		JPanel painelLabels = new JPanel();
		painelLabels.setBorder(BorderFactory.createEtchedBorder());
		GroupLayout panelLayout = new GroupLayout(painelLabels);

		JLabel l1 = new JLabel(getBundleMessage().getMessage(
				"SobreView.lblTitulo.text")
				+ ":");
		JLabel l2 = new JLabel(getBundleMessage().getMessage(
				"SobreView.lblVersao.text")
				+ ":");
		JLabel l3 = new JLabel(getBundleMessage().getMessage(
				"SobreView.lblDesenvPor.text")
				+ ":");
		JLabel l4 = new JLabel(getBundleMessage().getMessage(
				"SobreView.lblBuild.text")
				+ ":");
		JLabel l5 = new JLabel(getBundleMessage().getMessage(
				"SobreView.lblSite.text")
				+ ":");

		JLabel lblTitulo = new JLabel(getTitulo());
		JLabel lblVersao = new JLabel(getVersao());
		JLabel lblDesenvPor = new JLabel(getDesenvolvidoPor());
		JLabel lblBuild = new JLabel(getBuild());
		JLabel lblSite = new JLabel(getURL());
		JPanel logo = new JPanel() {

			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				ImageIcon icon = new ImageIcon(getURLLogo());
				g.drawImage(icon.getImage(), 0, 0, null);
			}
		};
		logo.setPreferredSize(new Dimension(160, 80));

		painelLabels.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				createHorizontalGroup(
						panelLayout,
						33,
						groupComponents(panelLayout, l1, l2, l3, l4, l5),
						groupComponents(panelLayout, lblTitulo, lblVersao,
								lblDesenvPor, lblBuild, lblSite)).addComponent(
						logo, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				panelLayout
						.createSequentialGroup()
						.addGap(21, 21, 21)
						.addGroup(
								panelLayout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(logo,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(
												createVerticalGroup(
														panelLayout,
														groupComponents(
																panelLayout,
																l1, lblTitulo),
														groupComponents(
																panelLayout,
																l2, lblVersao),
														groupComponents(
																panelLayout,
																l3,
																lblDesenvPor),
														groupComponents(
																panelLayout,
																l4, lblBuild),
														groupComponents(
																panelLayout,
																l5, lblSite))))
						.addContainerGap(203, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(painelLabels, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(painelLabels, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		return painelLabels;
	}

	private Group groupComponents(GroupLayout layout,
			java.awt.Component... components) {
		Group g = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		for (java.awt.Component c : components) {
			g.addComponent(c);
		}
		return g;
	}

	private Group createHorizontalGroup(GroupLayout layout, int gap,
			Group... columns) {
		Group g = layout.createSequentialGroup().addContainerGap();
		for (Group c : columns) {
			g.addGroup(c).addGap(gap);
		}
		return g;
	}

	private Group createVerticalGroup(GroupLayout layout, Group... columns) {
		SequentialGroup g = layout.createSequentialGroup();
		for (Group c : columns) {
			g.addGroup(c).addPreferredGap(
					LayoutStyle.ComponentPlacement.UNRELATED);
		}
		return g;
	}

	private URL getURLLogo() {
		URL imageURL = ApplicationUtil.getURLImage(this.getClass(),
				"representante_comercial.jpg");
		return imageURL;
	}

	protected AppResourceBundleMessage getBundleMessage() {
		return bundleMessage;
	}

}
