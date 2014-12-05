/**
 * 
 */
package org.jboss.as.quickstarts.cdi.injection.qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * @author cesar
 *
 */
@Qualifier
@Target({ TYPE, FIELD, PARAMETER })
@Documented
@Retention(RUNTIME)
public @interface Portuguese {

}
