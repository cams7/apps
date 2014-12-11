/**
 * 
 */
package br.com.cams7.apps.jee.backing;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.as.quickstarts.cdi.injection.TranslateService;
import org.jboss.as.quickstarts.cdi.injection.qualifier.English;
import org.jboss.as.quickstarts.cdi.injection.qualifier.Portuguese;
import org.jboss.as.quickstarts.cdi.injection.qualifier.Spanish;

import br.com.cams7.apps.jee.service.BaseService;
import br.com.cams7.apps.jpa.domain.BaseEntity;
import br.com.cams7.apps.util.ApplicationException;
import br.com.cams7.apps.util.ApplicationUtil;

/**
 * @author cesar
 *
 */
public abstract class BaseEditBean<S extends BaseService<E, ?>, E extends BaseEntity<?>>
		extends AbstractBean<E> {

	// @Inject
	@EJB
	private S service;

	@Inject
	private Event<E> entityEventSrc;

	// @Inject
	// @NewEntity
	private E entity;

	@Inject
	@Spanish
	private TranslateService spanishTranslateService;

	@Inject
	@English
	private TranslateService englishTranslateService;

	@Inject
	@Portuguese
	private TranslateService portugueseTransalteService;

	// @Inject
	// @InjectFacesContext
	// private FacesContext facesContext;

	public BaseEditBean() {
		super();
	}

	public E getEntity() {
		return entity;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initNewEntity() {
		getLog().info("English: " + getEnglishHello());
		getLog().info("Spanish: " + getSpanishHello());
		getLog().info("Portuguese: " + getPortugueseHello());

		Class<E> entityType = getService().getEntityType();

		try {
			entity = (E) ApplicationUtil
					.getNewEntity((Class<BaseEntity<?>>) entityType);
		} catch (ApplicationException e) {
			getLog().error(e.getMessage());
		}
	}

	public String salva() {
		try {
			entity = getService().save(entity);

			getLog().info("New entity: id=" + entity.getId());

			getEntityEventSrc().fire(entity);

			initNewEntity();
		} catch (Exception ex) {
			// addErrorMessage("msg.erro.salvar.mercadoria", ex.getMessage());
			getLog().error("Erro ao salvar mercadoria.", ex);
			return "error";
		}
		return "ok";
	}

	protected S getService() {
		return service;
	}

	protected Event<E> getEntityEventSrc() {
		return entityEventSrc;
	}

	public String getSpanishHello() {
		return spanishTranslateService.hello();
	}

	public String getEnglishHello() {
		return englishTranslateService.hello();
	}

	public String getPortugueseHello() {
		return portugueseTransalteService.hello();
	}

	// private String getMessageFromI18N(String key) {
	// ResourceBundle bundle = ResourceBundle.getBundle("messages_labels",
	// FacesContext.getCurrentInstance().getViewRoot().getLocale());
	// return bundle.getString(key);
	// }
	//
	// protected void addMessage(String id, Severity severity, String key,
	// String message) {
	// FacesMessage facesMessage = new FacesMessage(severity,
	// getMessageFromI18N(key), message);
	// FacesContext.getCurrentInstance().addMessage(id, facesMessage);
	// }
	//
	// protected void addErrorMessage(String id, String key, String message) {
	// addMessage(id, FacesMessage.SEVERITY_ERROR, key, message);
	// }
	//
	// protected void addErrorMessage(String key, String message) {
	// addErrorMessage(null, key, message);
	// }
	//
	// protected void addInfoMessage(String id, String key, String message) {
	// addMessage(id, FacesMessage.SEVERITY_INFO, key, message);
	// }
	//
	// protected void addInfoMessage(String key, String message) {
	// addInfoMessage(null, key, message);
	// }
	//
	// protected void addWarnMessage(String id, String key, String message) {
	// addMessage(id, FacesMessage.SEVERITY_WARN, key, message);
	// }
	//
	// protected void addWarnMessage(String key, String message) {
	// addWarnMessage(null, key, message);
	// }

}
