package cams7.siscom.ui;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;

/**
 * <code>JMenu</code> com atalho pré-definido para F1 (hot key).
 * 
 * @author YaW Tecnologia
 */
public class MenuAjuda extends JMenu {
	private static final long serialVersionUID = 1L;

	public MenuAjuda() {
		super();
	}

	/**
	 * @param action
	 *            vinculada com a tecla F1.
	 */
	public void addListener(final cams7.desk.action.AbstractAction action) {
		super.getActionMap().put("click", new javax.swing.AbstractAction() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				action.actionPerformed();
			}
		});
	}

}
