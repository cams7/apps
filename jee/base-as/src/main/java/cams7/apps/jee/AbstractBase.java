/**
 * 
 */
package cams7.apps.jee;

import org.jboss.logging.Logger;

import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public abstract class AbstractBase<E extends BaseEntity<?>> {

	private final byte ENTITY_ARGUMENT_NUMBER = 0;

	private Logger log;

	public AbstractBase() {
		super();

		log = Logger.getLogger(this.getClass());
	}

	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected Logger getLog() {
		return log;
	}

}
