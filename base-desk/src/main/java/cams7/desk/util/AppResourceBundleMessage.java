/**
 * 
 */
package cams7.desk.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;

import cams7.jpa.domain.BaseEntity;
import cams7.util.ApplicationUtil;

import javax.validation.Path;

/**
 * @author cesar
 *
 */
public final class AppResourceBundleMessage implements MessageSourceAware {

	private Locale locale;

	private Class<BaseEntity<?>> entityType;
	private String typeName;

	private MessageSource messageSource;

	@SuppressWarnings("unchecked")
	public <E extends BaseEntity<?>> AppResourceBundleMessage(Locale locale,
			Class<E> entityType, MessageSource messageSource) {
		this.locale = locale;

		this.entityType = (Class<BaseEntity<?>>) entityType;
		if (getEntityType() != null)
			this.typeName = ApplicationUtil.getEntityName(getEntityType());

		setMessageSource(messageSource);
	}

	public AppResourceBundleMessage(Locale locale, MessageSource messageSource) {
		this(locale, null, messageSource);
	}

	public <E extends BaseEntity<?>> AppResourceBundleMessage(
			Class<E> entityType, MessageSource messageSource) {
		this(ApplicationUtil.DEFAULT_LOCALE, entityType, messageSource);
	}

	public AppResourceBundleMessage(MessageSource messageSource) {
		this(ApplicationUtil.DEFAULT_LOCALE, messageSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.MessageSourceAware#setMessageSource(org.
	 * springframework.context.MessageSource)
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public Class<BaseEntity<?>> getEntityType() {
		return entityType;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getMessage(String key, Object... array) {
		try {
			return messageSource.getMessage(key, array, locale);
		} catch (NoSuchMessageException e) {
			return key;
		}
	}

	public String getEntityName() {
		return getMessage(getTypeName() + "_name");
	}

	public String getEntityFieldName(Path field) {
		return getEntityFieldName(field.toString());
	}

	public String getEntityFieldName(String fieldName) {
		return getMessage(getTypeName() + "." + fieldName);
	}

	private String getViewTitle(String sufixo) {
		return getMessage(getTypeName() + "_" + sufixo + ".title");
	}

	public String getEditViewTitle() {
		return getViewTitle("edit");
	}

	public String getNewViewTitle() {
		return getViewTitle("new");
	}

	public String getListViewTitle() {
		return getViewTitle("list");
	}

	public String getSearchViewTitle() {
		return getViewTitle("search");
	}

	public String getSearchField() {
		return getMessage(getTypeName() + "_search.field");
	}

}
