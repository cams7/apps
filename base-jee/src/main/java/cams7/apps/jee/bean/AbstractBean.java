/**
 * 
 */
package cams7.apps.jee.bean;

import cams7.apps.jee.AbstractBase;
import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public abstract class AbstractBean<E extends BaseEntity<?>> extends
		AbstractBase<E> {

	public AbstractBean() {
		super();
	}

}
