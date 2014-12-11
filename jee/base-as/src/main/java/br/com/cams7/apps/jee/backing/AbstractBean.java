/**
 * 
 */
package br.com.cams7.apps.jee.backing;

import br.com.cams7.apps.jee.AbstractBase;
import br.com.cams7.apps.jpa.domain.BaseEntity;

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
