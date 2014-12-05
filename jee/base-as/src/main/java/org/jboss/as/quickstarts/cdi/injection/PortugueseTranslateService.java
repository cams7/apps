/**
 * 
 */
package org.jboss.as.quickstarts.cdi.injection;

import org.jboss.as.quickstarts.cdi.injection.qualifier.Portuguese;

/**
 * @author cesar
 *
 */
@Portuguese
public class PortugueseTranslateService implements TranslateService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jboss.as.quickstarts.cdi.injection.TranslateService#hello()
	 */
	@Override
	public String hello() {
		return "Olá";
	}

}
