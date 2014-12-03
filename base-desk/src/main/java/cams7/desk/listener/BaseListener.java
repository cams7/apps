/**
 * 
 */
package cams7.desk.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import cams7.desk.action.AbstractAction;
import cams7.desk.event.AbstractEvent;
import cams7.desk.event.AbstractEventListener;
import cams7.desk.util.AppResourceBundleMessage;

/**
 * @author cesar
 *
 */
public abstract class BaseListener implements ActionListener, WindowListener {

	private Logger log;

	private BaseListener parentListener;

	private Map<String, AbstractAction> actions;

	private Map<Class<?>, List<AbstractEventListener<?>>> eventListeners;

	protected final String MESSAGE_SOURCE = "messageSource";

	@Autowired
	private ApplicationContext applicationContext;

	private AppResourceBundleMessage bundleMessage;

	public BaseListener() {
		super();

		log = Logger.getLogger(this.getClass());

		actions = new HashMap<String, AbstractAction>();
		eventListeners = new HashMap<Class<?>, List<AbstractEventListener<?>>>();

	}

	public BaseListener(BaseListener parentListener) {
		this();

		if (parentListener != null)
			this.parentListener = parentListener;
	}

	/**
	 * Registra uma <code>ação</code> a um componente <code>button</code>.
	 * 
	 * @param source
	 * @param action
	 */
	protected void registerAction(AbstractButton source, AbstractAction action) {
		if (source.getActionCommand() == null) {
			throw new RuntimeException("Componente (Button) sem ação definida!");
		}
		getLog().debug(
				"Registrando action: " + action.getClass().getName()
						+ " para o botão: " + source.getText());
		source.addActionListener(this);
		this.actions.put(source.getActionCommand(), action);
	}

	/**
	 * Aciona o <code>AbstractEventListener</code> relacionado ao
	 * <code>AbstractEvent</code> para que o <code>listener</code> trate o
	 * evento.
	 * 
	 * @param event
	 *            referência do evento gerado
	 */
	@SuppressWarnings("unchecked")
	protected void fireEvent(AbstractEvent<?> event) {
		if (eventListeners.get(event.getClass()) != null) {
			List<AbstractEventListener<?>> list = eventListeners.get(event
					.getClass());
			for (int i = 0; i < list.size(); i++) {
				@SuppressWarnings("rawtypes")
				AbstractEventListener eventListener = list.get(i);
				getLog().debug(
						"Evento: " + event.getClass().getName()
								+ " com listener: "
								+ eventListener.getClass().getName());
				eventListener.handleEvent(event);
			}
		}
		if (parentListener != null)
			parentListener.fireEvent(event);
	}

	/**
	 * Registra um <code>listener</code> que deve ser acionado de acordo com o
	 * tipo do <code>evento</code>.
	 * 
	 * @param eventClass
	 *            tipo do evento
	 * @param eventListener
	 *            tratador (<code>listener</code>) do evento
	 */
	protected void registerEventListener(Class<?> eventClass,
			AbstractEventListener<?> eventListener) {
		getLog().debug(
				"Registrando listener: " + eventListener + " para o evento: "
						+ eventClass.getName());
		java.util.List<AbstractEventListener<?>> listenersForEvent = eventListeners
				.get(eventClass);
		if (listenersForEvent == null) {
			listenersForEvent = new ArrayList<AbstractEventListener<?>>();
		}
		listenersForEvent.add(eventListener);
		eventListeners.put(eventClass, listenersForEvent);
	}

	protected AbstractAction getAction(ActionEvent actionEvent) {
		AbstractButton button = (AbstractButton) actionEvent.getSource();
		String actionCommand = button.getActionCommand();
		return actions.get(actionCommand);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			AbstractAction action = getAction(actionEvent);

			if (action != null) {
				getLog().debug("Executando action: " + action.getClass());
				try {
					action.actionPerformed();
				} catch (Exception ex) {
					handlerException(ex);
				}
			}
		} catch (ClassCastException e) {
			handlerException(new IllegalArgumentException(
					"Action source não é um Abstractbutton: " + actionEvent));
		}
	}

	/**
	 * Caso ocorra alguma falha durante a <code>ação</code> apresenta uma
	 * mensagem.
	 * 
	 * @param ex
	 */
	protected void handlerException(Exception ex) {
		getLog().error(ex);
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Método utilizado para liberar recursos carregados pela
	 * <code>Controller</code>.
	 */
	protected void cleanUp() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent windowEvent) {
		cleanUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	public void windowOpened(WindowEvent windowEvent) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	public void windowClosed(WindowEvent windowEvent) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	public void windowIconified(WindowEvent windowEvent) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent
	 * )
	 */
	public void windowDeiconified(WindowEvent windowEvent) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	public void windowActivated(WindowEvent windowEvent) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent
	 * )
	 */
	public void windowDeactivated(WindowEvent windowEvent) {
	}

	protected void initCtrl() {
		bundleMessage = new AppResourceBundleMessage(
				(MessageSource) getApplicationContext().getBean(MESSAGE_SOURCE));
	}

	protected BaseListener getParentListener() {
		return parentListener;
	}

	protected Logger getLog() {
		return log;
	}

	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	protected AppResourceBundleMessage getBundleMessage() {
		return bundleMessage;
	}

	protected void setBundleMessage(AppResourceBundleMessage bundleMessage) {
		this.bundleMessage = bundleMessage;
	}

}
