/**
 * 
 */
package cams7.apps.desk.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cams7.apps.desk.action.AbstractAction;
import cams7.apps.desk.action.BooleanExpression;
import cams7.apps.desk.action.ConditionalAction;
import cams7.apps.desk.component.EntityValidator;
import cams7.apps.desk.event.DeleteEvent;
import cams7.apps.desk.event.EditEvent;
import cams7.apps.desk.jpa.repository.EntityRepository;
import cams7.apps.desk.ui.EditView;
import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public abstract class BaseEditCtrl<V extends EditView, D extends EntityRepository<E, ?>, E extends BaseEntity<?>>
		extends BaseCtrl<V, D, E> {

	@Autowired
	private EntityValidator validator;

	public BaseEditCtrl() {
		super();
	}

	public BaseEditCtrl(BaseListCtrl<?, D, E> listCtrl) {
		super(listCtrl);
	}

	/**
	 * Método executado pelo <code>Spring</code>, depois de criar a instância de
	 * <code>BaseEditCtrl</code>.
	 * 
	 * <p>
	 * Faz o registro das ações.
	 * </p>
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#init()
	 */
	@PostConstruct
	@Override
	protected void initCtrl() {
		super.initCtrl();

		initView();

		getView().addWindowListener(this);
		registerAction(getView().getBtnCancelar(), new AbstractAction() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			public void action() {
				cleanUp();
			}
		});

		registerAction(getView().getBtnSalvar(), ConditionalAction.build()
				.addConditional(new BooleanExpression() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see cams7.desk.action.BooleanExpression#conditional()
					 */
					public boolean conditional() {
						@SuppressWarnings("unchecked")
						E entity = (E) getView().getEntity();
						String msg = getValidator().validate(
								getBundleMessage(), entity);
						if (!EMPTY.equals(msg == null ? EMPTY : msg)) {
							JOptionPane.showMessageDialog(
									getView(),
									msg,
									getBundleMessage().getMessage(
											"EntityValidator_title"),
									JOptionPane.INFORMATION_MESSAGE);
							return false;
						}
						return true;
					}
				}).addAction(new AbstractAction() {
					private E entity;

					/*
					 * (non-Javadoc)
					 * 
					 * @see cams7.desk.action.AbstractAction#action()
					 */
					@SuppressWarnings("unchecked")
					@Transactional
					protected void action() {
						entity = (E) getView().getEntity();
						getRepository().save(entity);
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see cams7.desk.action.AbstractAction#posAction()
					 */
					@Override
					public void posAction() {
						cleanUp();
						EditEvent event = new EditEvent(entity);
						fireEvent(event);
						entity = null;
					}

				}));

		registerAction(getView().getBtnExcluir(), new AbstractAction() {
			private E entity;

			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			@SuppressWarnings("unchecked")
			@Transactional
			protected void action() {
				entity = (E) getView().getEntity();

				Serializable id = getView().getEntityId();
				if (id != null) {
					EntityRepository<BaseEntity<Serializable>, Serializable> repository = (EntityRepository<BaseEntity<Serializable>, Serializable>) getRepository();
					repository.delete(id);
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#posAction()
			 */
			@Override
			public void posAction() {
				cleanUp();
				DeleteEvent event = new DeleteEvent(entity);
				fireEvent(event);
				entity = null;
			}
		});
	}

	public void show() {
		getView().setTitle(getBundleMessage().getNewViewTitle());
		getView().setVisible(true);
	}

	public void show(E entity) {
		getView().setEntity(entity);
		getView().setTitle(getBundleMessage().getEditViewTitle());
		getView().setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.listener.BaseListener#cleanUp()
	 */
	@Override
	protected void cleanUp() {
		getView().setVisible(false);
		getView().resetForm();

		super.cleanUp();
	}

	protected EntityValidator getValidator() {
		return validator;
	}

}
