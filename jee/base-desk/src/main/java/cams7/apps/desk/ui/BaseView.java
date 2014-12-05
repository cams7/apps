/**
 * 
 */
package cams7.apps.desk.ui;

import java.net.URL;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import cams7.apps.desk.util.AppResourceBundleMessage;
import cams7.apps.jpa.domain.BaseEntity;
import cams7.apps.jpa.domain.util.EntityAttribute;
import cams7.apps.util.ApplicationUtil;

/**
 * @author cesar
 *
 */
public abstract class BaseView extends JDialog {

	private static final long serialVersionUID = 1L;

	protected final String EMPTY = ApplicationUtil.EMPTY;

	private Logger log;

	private AppResourceBundleMessage bundleMessage;

	private Map<String, EntityAttribute> entityFields;

	public BaseView() {
		super();
		setModal(true);

		log = Logger.getLogger(this.getClass());
	}

	public abstract void initComponents();

	protected abstract JPanel montaPanelBotoes();

	protected Logger getLog() {
		return log;
	}

	protected AppResourceBundleMessage getBundleMessage() {
		return bundleMessage;
	}

	public void setBundleMessage(AppResourceBundleMessage bundleMessage) {
		this.bundleMessage = bundleMessage;
	}

	protected String getEntityName() {
		return getBundleMessage().getTypeName();
	}

	protected URL getURLBotaoIcon(String img) {
		URL imageURL = ApplicationUtil.getURLImage(this.getClass(), "botoes/"
				+ img);
		return imageURL;
	}

	protected Class<BaseEntity<?>> getEntityType() {
		return getBundleMessage().getEntityType();
	}

	public Map<String, EntityAttribute> getEntityFields() {
		return entityFields;
	}

	public void setEntityFields(Map<String, EntityAttribute> entityFields) {
		this.entityFields = entityFields;
	}

}
