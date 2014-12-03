package cams7.apps.desk.controller;

import java.util.Map;

import org.springframework.context.MessageSource;

import cams7.apps.desk.jpa.repository.EntityRepository;
import cams7.apps.desk.listener.BaseListener;
import cams7.apps.desk.ui.BaseView;
import cams7.apps.desk.util.AppResourceBundleMessage;
import cams7.apps.jpa.domain.BaseEntity;
import cams7.apps.jpa.domain.util.EntityAttribute;
import cams7.apps.util.ApplicationUtil;

/**
 * Classe abstrata que define uma estrutura para componentes da camada
 * controller do padrão arquitetural MVC.
 * 
 * <p>
 * <code>Controller</code> é o componente intermediário entre a apresentação
 * (View) e os componentes de negócio (Serviços + DAO + Model).
 * </p>
 * 
 * <p>
 * Habilita:
 * </p>
 * <ul>
 * <li>Definição de <code>eventos</code> e <code>ações</code> para os
 * componentes gráficos.</li>
 * <li>Apresentar mensagens de erros gerados em <code>ações</code>dos
 * componentes gráficos.</li>
 * <li>Liberar recursos do componente no encerramento da janela.</li>
 * </ul>
 * 
 * @author YaW Tecnologia
 */
public abstract class BaseCtrl<V extends BaseView, D extends EntityRepository<E, ?>, E extends BaseEntity<?>>
		extends BaseListener {

	protected final String EMPTY = ApplicationUtil.EMPTY;

	protected final byte VIEW_ARGUMENT_NUMBER = 0;
	protected final byte DAO_ARGUMENT_NUMBER = 1;
	protected final byte ENTITY_ARGUMENT_NUMBER = 2;

	private V view;
	private D repository;

	private Map<String, EntityAttribute> entityAttributes;

	public BaseCtrl() {
		super();
	}

	/**
	 * Controller possui um auto-relacionamento, útil em situações aonde uma
	 * hierarquia de controladores deve ser respeitada.
	 * 
	 * @param parentListener
	 *            controller <i>pai</i>
	 */
	public BaseCtrl(BaseListener parentListener) {
		super(parentListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.listener.BaseListener#init()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void initCtrl() {
		Class<E> entityType = (Class<E>) ApplicationUtil.getType(this,
				ENTITY_ARGUMENT_NUMBER);

		setBundleMessage(new AppResourceBundleMessage(entityType,
				(MessageSource) getApplicationContext().getBean(MESSAGE_SOURCE)));

	}

	protected void initView() {
		getView().setEntityFields(getEntityAttributes());
		getView().setBundleMessage(getBundleMessage());
		getView().initComponents();
	}

	protected V getView() {
		return view;
	}

	protected void setView(V view) {
		this.view = view;
	}

	protected D getRepository() {
		return repository;
	}

	protected void setRepository(D repository) {
		this.repository = repository;
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getEntityType() {
		return (Class<E>) getBundleMessage().getEntityType();
	}

	protected String getEntityName() {
		return getBundleMessage().getTypeName();
	}

	public Map<String, EntityAttribute> getEntityAttributes() {
		return entityAttributes;
	}

	public void setEntityAttributes(
			Map<String, EntityAttribute> entityAttributes) {
		this.entityAttributes = entityAttributes;
	}

}
